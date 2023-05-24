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
                execPattern: '**/**.exec',
                classPattern: '**/classes',
                sourcePattern: '**/src/main/java',
                inclusionPattern: '**/*.java,**/*.groovy,**/*.kt,**/*.kts',
                exclusionPattern: '',
                minimumInstructionCoverage: '70',
                minimumBranchCoverage: '70'
            )
        }
    }
}
