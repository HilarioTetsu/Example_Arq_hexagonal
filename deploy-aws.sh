#!/bin/bash
echo "=== 1. COMPILANDO Y CREANDO IMAGEN DOCKER DEL BACKEND ==="
docker build -t activos-backend:latest .

echo "=== 2. CREANDO REGISTRO DE CONTENEDORES EN AWS (ECR) ==="
awslocal ecr create-repository --repository-name activos-repo

# Taggeo y subida de la imagen al ECR local de AWS
docker tag activos-backend:latest localhost:4566/activos-repo:latest

echo "=== 3. CREANDO LA BASE DE DATOS EN AWS RDS (AHORA SÍ PRO) ==="
awslocal rds create-db-instance \
  --db-instance-identifier activos-rds-local \
  --db-instance-class db.t3.micro \
  --engine postgres \
  --master-username db_admin_local \
  --master-user-password super_secret_pass_local \
  --db-name activos_db

echo "=== 4. DESPLEGANDO APLICACIÓN EN AWS APP RUNNER (PERFIL PROD) ==="
awslocal apprunner create-service \
  --service-name activos-backend-service \
  --source-configuration '{
    "ImageRepository": {
      "ImageIdentifier": "localhost:4566/activos-repo:latest",
      "ImageConfiguration": {
        "Port": "8080",
        "RuntimeEnvironmentVariables": [
          {"Name": "SPRING_PROFILES_ACTIVE", "Value": "prod"},
          {"Name": "RDS_HOSTNAME", "Value": "localhost.localstack.cloud"},
          {"Name": "RDS_PORT", "Value": "4510"},
          {"Name": "RDS_DB_NAME", "Value": "activos_db"},
          {"Name": "RDS_USERNAME", "Value": "db_admin_local"},
          {"Name": "RDS_PASSWORD", "Value": "super_secret_pass_local"},
          {"Name": "SPRING_JPA_HIBERNATE_DDL_AUTO", "Value": "update"}
        ]
      },
      "ImageRepositoryType": "ECR"
    }
  }'

echo "=========================================================="
echo "¡Simulación de despliegue completada!"
echo "Tu aplicación ahora está siendo gestionada por AWS App Runner."
echo "=========================================================="