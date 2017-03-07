pipeline {
		agent {
			label 'Ant'
		}

		stages {
				stage('Build') {
						steps {
								sh 'ant -f build.xml -v'
						}
				}
				stage('Test') {
						steps {
								sh 'ant -f test.xml -v'
								junit 'reports/result.xml'
						}
				}
		}
}
