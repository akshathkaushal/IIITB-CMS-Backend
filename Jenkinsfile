pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    environment {
    	DOCKERHUB_CREDENTIALS=credentials('akshathkaushal_dockerhub_id')
    }
    stages {
        stage('Download the git repo') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/backend-refactored']], extensions: [], userRemoteConfigs: [[credentialsId: 'Akshath_Github_Private_Repo', url: 'git@github.com:akshathkaushal/IIITB-CMS-Backend.git']]])
            }
        }
        stage('Maven build and test') {
            steps {
            	script {
            		sh 'mvn -Dmaven.test.failure.ignore=true clean package'
            	}
            }
            post {
            	success {
            		junit '**/target/surefire-reports/TEST-*.xml'
            		archiveArtifacts 'target/*.jar '
            	}
            }
        }
        stage('Build docker image') {
        	steps {
        		script {
        			sh 'docker build -t akshathkaushal7/spe-project-backend .'
        		}
        	}
        }
        stage("Login to DockerHub") {
            steps {
                script {
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                }
            }
        }
        stage('Push docker image to Dockerhub') {
            steps {
                script {
                    sh 'docker push akshathkaushal7/spe-project-backend:latest'
                }
            }
        }
        stage('Deploy using Ansible') {
            steps {
//                 ansiblePlaybook become: true, installation: 'ansibleenv', inventory: 'deploy-docker/inventory', playbook: 'deploy-docker/deploy-image.yml', vaultCredentialsId: 'ansible_vault'
                script {
                    sh 'ansible-playbook -i deploy-docker/inventory deploy-docker/deploy-image.yml --extra-vars "ansible_sudo_pass=akshath"'
                }
            }
        }
    }
}