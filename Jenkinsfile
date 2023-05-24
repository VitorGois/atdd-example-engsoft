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

        stage("Build and Test") {
            steps {
                bat "mvn -B clean package"
            }
        }

        stage("Build Docker Image") {
            steps {
                bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} -f Dockerfile ."
            }
        }

    }

    post {
        always {
            archiveArtifacts(artifacts: "**/target/*.jar", fingerprint: true)
            jacoco(
                execPattern: '**/target/classes',
                classPattern: '**/target/test-classes',
                sourcePattern: '**/src/main/java',
                inclusionPattern: '**/com/engsoft/**',
                exclusionPattern: '',
                minimumInstructionCoverage: '80',
                minimumBranchCoverage: '70'
            )
        }
    }
}
