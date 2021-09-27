package com.bad.code2.base;

public class Point {
    private Double x;
    private Double y;
    public Point(Double x, Double y){
        this.x = x;
        this.y = y;
    }
    public Double getX(){
        return x;
    }
    public Double getY(){
        return y;
    }
    public String printPos(){
        return ("x: " + x + " y: " + y);
    }
}
