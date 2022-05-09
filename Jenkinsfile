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
        stage('Push docker image to Dockerhub') {
            steps {
        		script {
        		    withCredentials([string(credentialsId: 'akshathkaushal7', variable: 'dockerhub-pwd')]) {
                        sh 'docker login -u akshathkaushal7 -p ${dockerhub-pwd}'
                        sh 'docker push akshathkaushal7/spe-project-backend'
                    }
        		}
        	}
        }
        stage('Deploy using Ansible') {
        	steps {
        		ansiblePlaybook becomeUser: null, colorized: true, disableHostKeyChecking:true, installation: 'ansibleenv', inventory: 'deploy-docker/inventory', playbook: 'deploy-docker/deploy-image.yml', sudoUser:null
        	}
        }
    }
}