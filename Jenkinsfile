pipeline {
		agent 'ant'

		stages {
				stage('Build') {
						steps {
								sh 'ant -f build.xml'
						}
				}
				stage('Test') {
						steps {
								sh 'ant -f test.xml'
								junit 'reports/result.xml'
						}
				}
		}
}
