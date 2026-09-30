pipeline {
    agent { label 'node1' }

    options {
        timeout(time: 20, unit: 'MINUTES')
    }

    environment {
        MAVEN_OPTS = '-Xmx512m'
        SONAR_ORG  = 'sharathmk2000594'
        SONAR_KEY  = 'Sharathmk2000594_ci-demo'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests compile'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn -B test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarcloud') {
                    sh 'mvn -B org.sonarsource.scanner.maven:sonar-maven-plugin:5.8.0.7211:sonar -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.token=$SONAR_AUTH_TOKEN -Dsonar.organization=$SONAR_ORG -Dsonar.projectKey=$SONAR_KEY'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    timeout(time: 5, unit: 'MINUTES') {
                        sh '''
                          TASK=$(grep ceTaskId target/sonar/report-task.txt | cut -d= -f2)
                          until [ "$(curl -s -u $SONAR_TOKEN: "https://sonarcloud.io/api/ce/task?id=$TASK" | jq -r .task.status)" = "SUCCESS" ]; do
                            echo "waiting for SonarQube to process the analysis..."
                            sleep 5
                          done
                          GATE=$(curl -s -u $SONAR_TOKEN: "https://sonarcloud.io/api/qualitygates/project_status?projectKey=$SONAR_KEY" | jq -r .projectStatus.status)
                          echo "Quality Gate: $GATE"
                          [ "$GATE" = "OK" ]
                        '''
                    }
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B -DskipTests package'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Publish to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-cred', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
                    sh 'mvn -B -DskipTests -s settings.xml deploy'
                }
            }
        }
    }

    post {
        success { echo 'Pipeline passed: artifact published to Nexus' }
        failure { echo 'Pipeline failed, check the first red stage' }
    }
}
