pipeline {
    agent any

    environment {
        DOCKER_USERNAME = "vitorgois"
        DOCKER_IMAGE = "coursesapi"
        DOCKER_TAG = "latest"
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

        stage("Build and Push Image") {
            steps {
                script {
                    def dockerImage = docker.build("${DOCKER_USERNAME}/${DOCKER_IMAGE}:${DOCKER_TAG}", "-f Dockerfile .")
                    docker.withRegistry("https://registry.hub.docker.com", "dockerhub-credentials") {
                        dockerImage.push()
                    }
                }
            }
        }

        stage("Prune Docker data") {
            steps {
                bat "docker system prune -a --volumes -f"
            }
        }

        stage("Start container") {
            steps {
                script {
                    docker.withRegistry("https://registry.hub.docker.com", "dockerhub-credentials") {
                        bat "docker compose up -d --no-color --wait"
                        bat "docker compose ps"
                    }
                }
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
