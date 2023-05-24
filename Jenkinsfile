pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "coursesapi"
        DOCKER_TAG = "latest"
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
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

        stage("Build") {
            steps {
                bat "mvn -B clean package"
            }
        }

        stage("Build Docker Image") {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credentials') {
                        def imageName = "vitorgois/coursesapi:latest"
                        def dockerfileDir = '.'
                        docker.build(imageName, dockerfileDir)
                    }
                }
            }
        }

        stage("Docker Registry Login") {
            steps {
                bat "echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"
            }
        }

        stage("Push Docker Image") {
            steps {
                bat "docker push vitorgois/coursesapi:latest"
            }
        }

    }

    post {
        always {
            archiveArtifacts(artifacts: "**/target/*.jar", fingerprint: true)
            bat "docker logout"
        }
    }
}
