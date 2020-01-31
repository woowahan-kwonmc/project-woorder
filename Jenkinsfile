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
            slackSend(channel: '#jenkins-blue-ocean', color: 'good', message: "결과 : *${currentBuild.currentResult} - * 잡(Job) 이름 : ${env.JOB_NAME} 빌드 횟수 : ${env.BUILD_NUMBER}\napplication-prod.properties 복사 성공\n 상세 정보: ${env.BUILD_URL}")
          }
        }

        stage('user npm build') {
          steps {
            sh '$(pwd)/gradlew userNpmBuild'
            slackSend(channel: '#jenkins-blue-ocean', color: 'good', message: "결과 : *${currentBuild.currentResult} - * 잡(Job) 이름 : ${env.JOB_NAME} 빌드 횟수 : ${env.BUILD_NUMBER}\n유저 프론트 npm 빌드 성공\n 상세 정보: ${env.BUILD_URL}")
          }
        }

        stage('admin npm build') {
          steps {
            sh '$(pwd)/gradlew adminNpmBuild'
            slackSend(channel: '#jenkins-blue-ocean', color: 'good', message: "결과 : *${currentBuild.currentResult} - * 잡(Job) 이름 : ${env.JOB_NAME} 빌드 횟수 : ${env.BUILD_NUMBER}\n어드민 프론트 npm 빌드 성공\n 상세 정보: ${env.BUILD_URL}")
          }
        }

      }
    }

    stage('Test') {
      steps {
        sh '$(pwd)/gradlew test'
        slackSend(channel: '#jenkins-blue-ocean', color: 'good', message: "결과 : *${currentBuild.currentResult} - * 잡(Job) 이름 : ${env.JOB_NAME} 빌드 횟수 : ${env.BUILD_NUMBER}\n테스트 이상 무\n 상세 정보: ${env.BUILD_URL}")
      }
    }

    stage('Build') {
      steps {
        sh '$(pwd)/gradlew build'
        slackSend(channel: '#jenkins-blue-ocean', color: 'good', message: "결과 : *${currentBuild.currentResult} - * 잡(Job) 이름 : ${env.JOB_NAME} 빌드 횟수 : ${env.BUILD_NUMBER}\n빌드 이상 무\n 상세 정보: ${env.BUILD_URL}")
      }
    }

    stage('Deploy') {
      steps {
        sshPublisher(publishers: [sshPublisherDesc(configName: 'ec2-kwonmc-pilot-woorder', transfers: [
        sshTransfer(
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
        slackSend(channel: '#jenkins-blue-ocean', color: 'good', message: "결과 : *${currentBuild.currentResult} - * 잡(Job) 이름 : ${env.JOB_NAME} 빌드 횟수 : ${env.BUILD_NUMBER}\n배포 이상 무\n 상세 정보: ${env.BUILD_URL}")
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