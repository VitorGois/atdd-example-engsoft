pipeline {
    agent any

    stages {
        stage("verify tooling") {
            steps {
                bat '''
                docker version
                docker info
                docker compose version 
                curl --version
                '''
            }
        }

        stage('Prune Docker data') {
            steps {
                bat 'docker system prune -a --volumes -f'
            }
        }

        stage('Start container') {
            steps {
                bat 'docker compose up -d --no-color --wait'
                bat 'docker compose ps'
            }
        }

        stage('Run tests against the container') {
            steps {
                bat 'curl http://localhost:8080'
            }
        }
    }

    post {
        always {
            discordSend channel: '#Servidor-Jenkins', color: 'GREEN', message: 'O pipeline foi concluído com sucesso!'
        }
        failure {
            discordSend channel: '#Servidor-Jenkins', color: 'RED', message: 'O pipeline falhou!'
        }
    }
  
}