pipeline {
  agent any
  stages {
    stage('Clone') {
      agent any
      steps {
        git(url: 'https://github.com/woowahan-kwonmc/project-woorder.git', branch: 'master', changelog: true, credentialsId: '554289a8-7fb7-44d9-baaa-39161e09815a')
      }
    }

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
        cleanWs()
        sh 'echo "clean up complete"'
      }
    }

  }
}