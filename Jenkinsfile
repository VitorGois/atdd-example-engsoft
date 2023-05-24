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
                bat "mvn -B clean package -DskipTests"
            }
        }

        stage("Build Docker Image") {
            steps {
                bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} -f Dockerfile ."
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

    }

    post {
        always {
            archiveArtifacts(artifacts: "**/target/*.jar", fingerprint: true)
            jacoco(reportBuildPolicy: 'always', execPattern: '**/target/jacoco.exec')
        }
    }
}
