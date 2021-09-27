package com.bad.code2.base;

public class Point {
    private Double x;
    private Double y;
    private Double z;
    private boolean isVoluminous;
    public Point(Double x, Double y, Double z){
        this.isVoluminous = true;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point(Double x, Double y){
        this.isVoluminous = false;
        this.x = x;
        this.y = y;
        this.z = null;
    }
    public Double getX(){
        return x;
    }
    public Double getY(){
        return y;
    }
    public Double getZ(){
        return z;
    }
    public String printPos(){
        return isVoluminous ? "x: " + x + " y: " + y :
                              "x: " + x + " y: " + y;
    }
}
