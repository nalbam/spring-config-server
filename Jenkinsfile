#!groovy

node {
    //echo "JOB_NAME    ${env.JOB_NAME}"
    //echo "BRANCH_NAME ${env.BRANCH_NAME}"

    properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '60', numToKeepStr: '10')), pipelineTriggers([])])

    // Get the maven tool.
    // ** NOTE: This 'M3' maven tool must be configured
    // **       in the global configuration.
    def mvnHome = tool 'M3'

    stage('Checkout') {
        checkout scm
    }

    stage('Build') {
        if (env.BRANCH_NAME == 'release') {
            sh '~/toaster/toast.sh version next'
        }
        sh "${mvnHome}/bin/mvn clean package"
    }

    stage('Results') {
        junit '**/target/surefire-reports/TEST-*.xml'
        archive 'target/*.jar'
    }

    stage('Publish') {
        sh '~/toaster/toast.sh version save'
    }
}
