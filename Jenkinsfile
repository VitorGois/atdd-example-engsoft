pipeline {
    agent any

    environment {
        APP_PORT = 8080
        DOCKER_USERNAME = "vitorgois"
        DOCKER_IMAGE = "coursesapi"
        DOCKER_TAG = "latest"
        COVERAGE_PERCENTAGE = ''
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

        stage('Jacoco') {
            steps {              
                // Gerar relatório de cobertura Jacoco
                sh 'mvn jacoco:report'
                
                script {
                    // Extrair porcentagem de cobertura do relatório XML
                    def coverageReport = readFile('target/site/jacoco/jacoco.xml')
                    def coverageXml = new XmlSlurper().parseText(coverageReport)
                    def coveragePercentage = coverageXml.'counter'.'@covered'.toInteger() / coverageXml.'counter'.'@missed'.toInteger()
                    
                    // Armazenar a porcentagem de cobertura como uma variável de ambiente
                    env.COVERAGE_PERCENTAGE = coveragePercentage.toString()
                    
                    // Exibir a porcentagem de cobertura
                    echo "Porcentagem de Cobertura: ${env.COVERAGE_PERCENTAGE}%"
                }
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
            when {
                expression {
                    // Verificar se a cobertura está acima de 70%
                    env.COVERAGE_PERCENTAGE.toFloat() > 70.0
                }
            }
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
