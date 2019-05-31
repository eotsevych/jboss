node('docker') {

    stage 'Clean Up'
        sh 'docker ps'
        sh 'docker images'
        sh 'docker rm -f app'
        sh 'docker rm -f grid_chrome_1'
        sh 'docker rm -f grid_firefox_1'
        sh 'docker rm -f selenium-hub'


    stage 'Checkout'
        checkout scm
    stage 'Build'
        sh "docker build --tag=wildfly-app ./app "
    stage 'Run'
        sh "docker run -d -p 80:80 -it --name app wildfly-app "
   stage 'Up Grid'
        sh "docker-compose -d up"
   stage 'Run Maven'
        sh "mvn clean test"
}