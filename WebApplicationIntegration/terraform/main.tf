
terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0"
    }
  }
}

provider "docker" {}

resource "docker_container" "greeting_app" {
  name  = "terraform-greeting-app"
  image = "greeting-app:v4"

  memory   = 512
  memory_swap = 1024
  cpu_set  = "0"
  cpu_shares = 512

  ports {
    internal = 8080
    external = 8084
  }
}

