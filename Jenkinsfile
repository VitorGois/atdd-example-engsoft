pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                withMaven(maven: 'maven-3.9.2') {
                    sh 'mvn clean package'
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts(artifacts: '**/target/*.jar', fingerprint: true)
        }
    }
}
