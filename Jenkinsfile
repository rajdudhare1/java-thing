pipeline {
  agent none

  stages {
    stage('Unit Testing') {
      agent {
        label 'Slave 1'
      }
      steps {
        sh 'printenv'
        sh 'ant -f test.xml -v'
        junit "reports/${env.BUILD_NUMBER}_result.xml"
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
        sh "cp dist/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/all/"
      }
    }
    stage('Test on CentOS'){
      agent {
        label 'CentOS'
      }
      steps {
        sh "wget http://brandon4232.mylabserver.com/rectangles/all/rectangle_${env.BUILD_NUMBER}.jar"
        sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 2 3"
      }
    }
    stage('Test on Debian') {
      agent {
        docker 'openjdk:8u121-jre'
      }
      steps {
        sh "wget http://brandon4232.mylabserver.com/rectangles/all/rectangle_${env.BUILD_NUMBER}.jar"
        sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 2 3"
      }
    }
    stage('Promote to Green'){
      agent {
        label 'Slave 1'
      }
      steps {
        sh "cp /var/www/html/rectangles/all/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/green/"
      }
    }
    stage('Promote to Master'){
      agent {
        label 'Slave 1'
      }
      sh "git stash"
      sh "git checkout master"
    }
  }
}
