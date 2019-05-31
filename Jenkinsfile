node('docker') {

    stage 'Clean Up'
        sh 'docker ps'
        sh 'docker images'
        sh 'docker rm -f app'



    stage 'Checkout'
        checkout scm
    stage 'Build'
        sh "docker build --tag=wildfly-app ./app "
    stage 'Run'
        sh "docker run -d -p 80:80 -it --name app wildfly-app "
   stage 'Run Maven'
        sh "mvn clean test"
}