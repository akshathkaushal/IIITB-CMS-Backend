pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    stages {
        stage('Download the git repo') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/backend-refactored']], extensions: [], userRemoteConfigs: [[credentialsId: 'Akshath_Github_Private_Repo', url: 'git@github.com:akshathkaushal/IIITB-CMS-Backend.git']]])
            }
        }
    }
}