pipeline {
    agent any

    environment {
        APP_PORT = 8080
        DOCKER_IMAGE = "coursesapi"
        DOCKER_TAG = "latest"
        DOCKERHUB_CREDENTIALS = credentials("dockerhub")
    }

    tools {
        maven "maven-3.9.2"
        jdk "jdk-17"
    }

    stages {
        stage("Checkout") {
            steps {
                checkout scm
            }
        }

        stage("Build and Test") {
            steps {
                bat "mvn -B clean package"
            }
        }

        stage("Verify Tooling") {
            steps {
                bat """
                docker version
                docker info
                docker compose version 
                curl --version
                """
            }
        }

        stage("Docker Build and Push") {
            steps {
                bat "docker build -t vitorgois/${DOCKER_IMAGE}:${DOCKER_TAG} ."
                bat "echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"
                bat "docker push vitorgois/${DOCKER_IMAGE}:${DOCKER_TAG}"
            }
        }

        stage("Prune Docker data") {
            steps {
                bat "docker system prune -a --volumes -f"
            }
        }

        stage("Start container") {
            steps {
                bat "docker compose up -d --no-color --wait"
                bat "docker compose ps"
            }
        }

        stage("Run tests against the container") {
            steps {
                bat "curl http://localhost:${APP_PORT}"
            }
        }
    }

    post {
        always {
            archiveArtifacts(artifacts: "**/target/*.jar", fingerprint: true)
            jacoco(
                execPattern: "**/**.exec",
                classPattern: "**/classes",
                sourcePattern: "**/src/main/java",
                inclusionPattern: "**/*.java,**/*.groovy,**/*.kt,**/*.kts",
                exclusionPattern: "",
                minimumInstructionCoverage: "70",
                minimumBranchCoverage: "70"
            )
            bat "docker logout"
        }
    }
}
