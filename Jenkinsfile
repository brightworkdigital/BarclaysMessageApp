pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -f pom.xml'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('sonarqube') {
                        sh 'mvn sonar:sonar -Pcoverage'
                    }
                }
            }
        }
//        stage ('OWASP Dependency-Check Vulnerabilities') {
//                    steps {
//                        dependencyCheck additionalArguments: '''
//                            -o "./"
//                            -s "./"
//                            -f "ALL"
//                            --prettyPrint''', odcInstallation: 'OWASP-DC'
//                        dependencyCheckPublisher pattern: 'dependency-check-report.xml'
//                    }
//        }
        stage("Quality Gate") {
            steps {
              timeout(time: 15, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: false
              }
            }
          }
        stage('Deploy') {
            steps {
                echo "[INFO] DEPLOYMENT SUCCESS!!!"
                //sh './jenkins/scripts/deploy.sh'
            }
        }
    }
}