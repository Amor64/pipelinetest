#!/usr/bin/env groovy

class POC implements java.io.Serializable {
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
