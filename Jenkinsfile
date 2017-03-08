pipeline {
  agent none
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
        sh 'cp dist/rectangle.jar /var/www/html/rectangles/all/'
      }
    }
    stage('Test on CentOS'){
      agent {
        label 'CentOS'
      }
      steps {
        sh 'wget http://brandon4232.mylabserver.com/rectangles/all/rectangle.jar'
        sh 'java -jar rectangle.jar 2 3'
      }
    }
    stage('Test on Debian') {
      agent {
        docker 'openjdk:8u121-jre'
      }
      steps {
        sh 'apt-get install wget'
        sh 'wget http://brandon4232.mylabserver.com/rectangles/all/rectangle.jar'
        sh 'java -jar rectangle.jar 2 3'
      }
    }
    stage('Promote to Stable'){
      agent {
        label 'Slave 1'
      }
      steps {
        sh 'cp /var/www/html/rectangles/all/rectangle.jar /var/www/html/rectangles/stable/'
      }
    }
  }
}
