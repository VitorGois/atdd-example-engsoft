pipeline {
    agent any
    
    environment {
        JDK_17_HOME = tool(name: 'JDK_17', type: 'jdk')
        PATH = "${env.JDK_17_HOME}/bin:${env.PATH}"
    }
    
    stages {       
        stage('Instalar Maven') {
            steps {
                tool name: 'Maven_3.9.2', type: 'maven'
            }
        }

        stage('Build') {
            steps {
                sh "'${tool 'Maven_3.9.2'}/bin/mvn' clean package"
            }
        }
    }
}
