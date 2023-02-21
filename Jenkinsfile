def writeGitHubPullRequestStatus(text, state) {
    setGitHubPullRequestStatus context: 'Jenkins', message: text, state: state
}

pipeline {
    agent any
    environment {
        JOB_NAME = 'againspring-unit-test'
    }

    stages {
        stage('Check java and maven') {
            steps {
                echo 'Check java'
                sh 'java -version'
                sh 'mvn -version'
            }
        }
        stage('Checkout codes') {
            when {
                expression { "${GITHUB_PR_STATE}" == 'OPEN' }
            }
            steps {
                // 
                echo 'Checkout codes'
                checkout([$class: 'GitSCM',
                    branches: [[name: '${GITHUB_PR_SOURCE_BRANCH}']],
                    userRemoteConfigs: [[credentialsId:  'dce2dba9-82cc-4355-9a92-f5dc2049b45b', url: 'git@github.com:chenliu1993/againspring.git']]])
            // sh 'git checkout ${GITHUB_PR_SOURCE_BRANCH}'
            }
        }
        stage('PR Unit Test') {
            steps {
                echo 'Start Unit Test'
                sh 'mvn test'
            }
        }
    }
    post {
        success {
            echo 'Unit tests succeed, clean up the environment'
            writeGitHubPullRequestStatus('Job finished', 'SUCCESS')
        }
        failure {
            echo 'Unit tests failed, clean up the environment'
            writeGitHubPullRequestStatus('Job finished', 'FAILURE')
        }
        always {
            deleteDir()
        }
    }
}
