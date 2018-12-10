pipeline {
    agent none
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /Users/mploed/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /Users/mploed/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Integration Test') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /Users/mploed/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn integration-test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy approval'){
            steps {
                input "Deploy to Production?"
            }
        }
        stage('Deploy to Dev') {
                agent {
                    docker {
                        image 'maven:3-alpine'
                        args '-v /Users/mploed/.m2:/root/.m2'
                    }
                }
                steps {
                    pushToCloudFoundry(
                      target: 'api.run.pivotal.io',
                      organization: 'michael.ploed-org',
                      cloudSpace: 'production',
                      credentialsId: '3c8d45c3-9168-46c0-ac8d-fad8eefa8f8c'
                    )
                }

        }
    }
}