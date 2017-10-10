#!/usr/bin/env groovy

/**
 * Send notifications based on build status string
 */
def call(String buildStatus = 'STARTED'){
 println('shared libary called')
 FunctionExample()
}

def FunctionExample(){
  println('Invoke different function')
}
