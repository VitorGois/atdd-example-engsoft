pipeline {
    agent any

    tools {
        maven 'maven-3.9.2'
        jdk 'jdk-17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean package'
            }
        }
    }

    post {
        always {
            archiveArtifacts(artifacts: '**/target/*.jar', fingerprint: true)
        }
    }
}
