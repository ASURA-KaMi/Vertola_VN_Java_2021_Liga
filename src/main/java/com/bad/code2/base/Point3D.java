package com.bad.code2.base;

public class Point3D {
    private Double x;
    private Double y;
    private Double z;
    public Point3D(Double x, Double y, Double z){
        this.x = x;
        this.y = y;
        this.z = z;
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
        return ("x: " + x + " y: " + y + " z: " + z);
    }
}
