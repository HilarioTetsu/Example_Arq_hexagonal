terraform {
  required_version = ">= 1.0"
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region                      = "us-east-1"
  access_key                  = "mock_access_key"
  secret_key                  = "mock_secret_key"
  skip_credentials_validation = true
  skip_metadata_api_check     = true
  skip_requesting_account_id  = true

  # (Nuevo) Necesario para evitar llamadas a STS real
  s3_use_path_style           = true

  # Redirección de servicios de AWS hacia tu LocalStack
  endpoints {
    ecr            = "http://localhost:4566"
    ecs            = "http://localhost:4566"
    rds            = "http://localhost:4566"
    iam            = "http://localhost:4566"
    logs           = "http://localhost:4566"
    # --- LA SOLUCIÓN AL ERROR ---
    ec2            = "http://localhost:4566"
  }
}