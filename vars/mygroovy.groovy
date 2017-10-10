#!/usr/bin/env groovy

import POC
/**
 * Send notifications based on build status string
 */
def call(String buildStatus = 'STARTED'){
 println('shared libary called')
 FunctionExample()
 def poc = POC()
 println poc.getX()
}

def FunctionExample(){
  println('Invoke different function')
}
