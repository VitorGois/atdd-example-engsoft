pipeline {
    agent any
    
    environment {
        JDK_17_HOME = tool(name: 'JDK_17', type: 'jdk')
        PATH = "${env.JDK_17_HOME}/bin:${env.PATH}"
    }
    
    stages {       
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}
