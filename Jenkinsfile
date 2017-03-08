pipeline {
  agent none

  node {
    gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
    shortCommit = gitCommit.take(6)
  }

  stages {
    stage('Unit Testing') {
      agent {
        label 'Slave 1'
      }
      steps {
        sh 'ant -f test.xml -v'
        junit 'reports/result.xml'
      }
    }
    stage('Build') {
      agent {
        label 'Slave 1'
      }
      steps {
        sh 'ant -f build.xml -v'
      }
    }
    stage('Deploy to Apache'){
      agent {
        label 'Slave 1'
      }
      steps {
        sh "cp dist/rectangle_${env.BUILD_NUMBER}_${shortCommit}.jar /var/www/html/rectangles/all/"
      }
    }
    stage('Test on CentOS'){
      agent {
        label 'CentOS'
      }
      steps {
        sh "wget http://brandon4232.mylabserver.com/rectangles/all/rectangle_${env.BUILD_NUMBER}_${shortCommit}.jar"
        sh "java -jar rectangle_${env.BUILD_NUMBER}_${shortCommit}.jar 2 3"
      }
    }
    stage('Test on Debian') {
      agent {
        docker 'openjdk:8u121-jre'
      }
      steps {
        sh "wget http://brandon4232.mylabserver.com/rectangles/all/rectangle_${env.BUILD_NUMBER}_${shortCommit}.jar"
        sh "java -jar rectangle_${env.BUILD_NUMBER}_${shortCommit}.jar 2 3"
      }
    }
    stage('Promote to Stable'){
      agent {
        label 'Slave 1'
      }
      steps {
        sh "cp /var/www/html/rectangles/all/rectangle_${env.BUILD_NUMBER}_${shortCommit}.jar /var/www/html/rectangles/stable/"
      }
    }
  }
}
