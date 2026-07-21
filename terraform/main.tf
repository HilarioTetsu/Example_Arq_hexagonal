# --- 1. CONFIGURACIÓN DE RED (VPC, Subnet y Security Group) ---
resource "aws_vpc" "main_vpc" {
  cidr_block = "10.0.0.0/16"
}

resource "aws_subnet" "main_subnet" {
  vpc_id     = aws_vpc.main_vpc.id
  cidr_block = "10.0.1.0/24"
}

resource "aws_security_group" "main_sg" {
  name        = "activos-sg"
  vpc_id      = aws_vpc.main_vpc.id
  description = "Permitir trafico 8080"

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# 1. Repositorio ECR
resource "aws_ecr_repository" "app_repo" {
  name                 = "activos-tecnologicos-repo"
  image_tag_mutability = "MUTABLE"
}

# 2. Cluster de ECS
resource "aws_ecs_cluster" "main_cluster" {
  name = "activos-cluster"
}

# 3. Base de Datos RDS (PostgreSQL)
resource "aws_db_instance" "postgres" {
  allocated_storage    = 10
  db_name              = "activostecnologicos"
  engine               = "postgres"
  engine_version       = "15.3"
  instance_class       = "db.t3.micro"
  username             = "db_admin_local"
  password             = "super_secret_pass_local"
  skip_final_snapshot  = true
}

# 4. Definición de Tarea ECS (Task Definition)
resource "aws_ecs_task_definition" "app_task" {
  family                   = "activos-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"

  container_definitions = jsonencode([
    {
      name      = "activos-app"
      image     = "${aws_ecr_repository.app_repo.repository_url}:latest"
      essential = true
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
        }
      ]
      environment = [
        { name = "SPRING_PROFILES_ACTIVE", value = "local" },
        { name = "SPRING_DATASOURCE_URL", value = "jdbc:postgresql://${aws_db_instance.postgres.endpoint}/activostecnologicos" },
        { name = "SPRING_DATASOURCE_USERNAME", value = aws_db_instance.postgres.username },
        { name = "SPRING_DATASOURCE_PASSWORD", value = aws_db_instance.postgres.password }
      ]
    }
  ])
}

# 5. Servicio ECS
resource "aws_ecs_service" "app_service" {
  name            = "activos-service"
  cluster         = aws_ecs_cluster.main_cluster.id
  task_definition = aws_ecs_task_definition.app_task.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  # --- BLOQUE NUEVO REQUERIDO POR FARGATE ---
  network_configuration {
    # Usamos las referencias a los recursos recién creados
    subnets          = [aws_subnet.main_subnet.id]
    security_groups  = [aws_security_group.main_sg.id]
    assign_public_ip = true
  }
}