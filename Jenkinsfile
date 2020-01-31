pipeline {
  agent any
  stages {
    stage('Clean') {
      steps {
        sh '$(pwd)/gradlew clean'
      }
    }

    stage('Set Up') {
      parallel {
        stage('cp server properties') {
          steps {
            sh '$(pwd)/gradlew copyServerProperties'
          }
        }

        stage('user npm build') {
          steps {
            sh '$(pwd)/gradlew userNpmBuild'
          }
        }

        stage('admin npm build') {
          steps {
            sh '$(pwd)/gradlew adminNpmBuild'
          }
        }

      }
    }

    stage('Test') {
      steps {
        sh '$(pwd)/gradlew test'
      }
    }

    stage('Build') {
      steps {
        sh '$(pwd)/gradlew build'
      }
    }

    stage('Deploy') {
      steps {
        sshPublisher(publishers: [
                  sshPublisherDesc(
                    configName: 'ec2-kwonmc-pilot-woorder',
                    transfers: [sshTransfer(
                        cleanRemote: false,
                        excludes: '',
                        execCommand: 'sh /home/ubuntu/deploy-woorder/scripts/init_server.sh',
                        execTimeout: 120000,
                        flatten: false,
                        makeEmptyDirs: false,
                        noDefaultExcludes: false,
                        patternSeparator: '[, ]+',
                        remoteDirectory: '/deploy-woorder',
                        remoteDirectorySDF: false,
                        removePrefix: 'build/libs',
                        sourceFiles: 'build/libs/*.jar')
                        ],
                        usePromotionTimestamp: false,
                        useWorkspaceInPromotion: false,
                        verbose: false
                        )])
              sh 'echo "deploy"'
            }
          }

          stage('Clean Up Workspace') {
            steps {
              cleanWs(cleanWhenSuccess: true, deleteDirs: true, cleanWhenAborted: true, cleanWhenFailure: true, cleanWhenNotBuilt: true, cleanWhenUnstable: true)
              sh 'echo "clean up complete"'
            }
          }

        }
      }