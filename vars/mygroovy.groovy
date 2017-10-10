#!/usr/bin/env groovy

//import POC
/**
 * Send notifications based on build status string
 */

class POC{
int x, y, z;
POC(){
x = 2;
y = 3;
z = 4;
}
int getX(){
return x;
}
int getY(){
return y;
}
int getz(){
return z;
}
}

def call(String buildStatus = 'STARTED'){
 println('shared libary called')
 FunctionExample()
 def poc = POC()
 println poc.getX()
}

def FunctionExample(){
  println('Invoke different function')
}
