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
        stage ('OWASP Dependency-Check Vulnerabilities') {
                    steps {
                        dependencyCheck additionalArguments: '''
                            --out "./"
                            --scan "./"
                            --format "ALL"
                            --prettyPrint''', odcInstallation: 'dependency-check'
                        dependencyCheckPublisher pattern: 'dependency-check-report.xml'
                    }
        }
        stage("Quality Gate") {
            steps {
              timeout(time: 15, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
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