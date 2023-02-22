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
                echo 'Checkout codes'
                checkout([$class: 'GitSCM',
                    branches: [[name: '${GITHUB_PR_SOURCE_BRANCH}']],
                    userRemoteConfigs: [[credentialsId:  'dce2dba9-82cc-4355-9a92-f5dc2049b45b', url: 'git@github.com:chenliu1993/againspring.git']]])
            }
        }
        stage('PR Unit Test') {
            steps {
                echo 'Start Unit Test'
                sh 'mvn clean install'
                sh 'mvn compile'
                sh 'mvn test'
            }
        }
    }
    post {
        success {
            //
            echo 'Unit tests succeed, clean up the environment'
            setGitHubPullRequestStatus context: 'againspring-unit-test', message: 'Unit test succeed', state: 'SUCCESS'
            githubPRComment comment: githubPRMessage('SpringBoot Sample Unit Test Success.'), statusVerifier: allowRunOnStatus('SUCCESS'), errorHandler: statusOnPublisherError('UNSTABLE')
        }
        failure {
            echo 'Unit tests failed, clean up the environment'
            setGitHubPullRequestStatus context: 'againspring-unit-test', message: 'Unit test fail', state: 'FAILURE'
            githubPRComment comment: githubPRMessage('SpringBoot Sample Unit Test failed.'), statusVerifier: allowRunOnStatus('FAILURE'), errorHandler: statusOnPublisherError('UNSTABLE')
        }
        always {
            deleteDir()
        }
    }
}
