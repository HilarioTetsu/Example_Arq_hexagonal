Write-Host "=== 1. COMPILANDO IMAGEN DEL BACKEND ===" -ForegroundColor Cyan
docker build -t activos-backend:latest .

Write-Host "=== 2. CONFIGURANDO ECR Y TAGGEANDO IMAGEN ===" -ForegroundColor Cyan
# Evitamos el error rojo si el repositorio ya fue creado en la ejecución anterior
try {
    docker exec localstack_infra awslocal ecr create-repository --repository-name activos-repo 2>$null
} catch {}
docker tag activos-backend:latest 000000000000.dkr.ecr.us-east-1.localhost.localstack.cloud:4566/activos-repo:latest

Write-Host "=== 3. CREANDO BASE DE DATOS EN AWS RDS (PRO) ===" -ForegroundColor Cyan
try {
    docker exec localstack_infra awslocal rds create-db-instance --db-instance-identifier activos-rds-local --db-instance-class db.t3.micro --engine postgres --master-username db_admin_local --master-user-password super_secret_pass_local --db-name activos_db 2>$null
} catch {}

Write-Host "=== 4. DESPLEGANDO EN AWS ECS (ELASTIC CONTAINER SERVICE) ===" -ForegroundColor Cyan
# Copiamos el nuevo archivo JSON al contenedor
docker cp task-definition.json localstack_infra:/tmp/task-definition.json

Write-Host " -> Creando Clúster ECS..." -ForegroundColor DarkGray
docker exec localstack_infra awslocal ecs create-cluster --cluster-name activos-cluster | Out-Null

Write-Host " -> Registrando Definición de Tarea..." -ForegroundColor DarkGray
docker exec localstack_infra awslocal ecs register-task-definition --cli-input-json file:///tmp/task-definition.json | Out-Null

Write-Host " -> Lanzando el Servicio ECS..." -ForegroundColor DarkGray
docker exec localstack_infra awslocal ecs create-service --cluster activos-cluster --service-name activos-service --task-definition activos-task --desired-count 1 | Out-Null

Write-Host "==========================================================" -ForegroundColor Green
Write-Host "¡Arquitectura Empresarial ECS desplegada exitosamente!" -ForegroundColor Green
Write-Host "Tu backend Spring Boot ahora corre dentro de un clúster de AWS." -ForegroundColor Green
Write-Host "==========================================================" -ForegroundColor Green