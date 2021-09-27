package com.bad.code2.shape;

import com.bad.code2.base.Point;
import com.bad.code2.base.Shape;

public class Qube implements Shape {
    private Point zeroCorner;
    private Double edgeSize;

    public Qube(Point zeroCorner, Double edgeSize) {
        this.zeroCorner = zeroCorner;
        this.edgeSize = edgeSize;
    }
    @Override
    public Point getPos(){
        return zeroCorner;
    }
    @Override
    public Point getCenter(){
        double halfEdge = edgeSize/2;
            Point center = new Point(zeroCorner.getX()+halfEdge,
                                         zeroCorner.getY()+halfEdge,
                                         zeroCorner.getZ()+ halfEdge);
        return center;
    }
    @Override
    public Double getVolume() {
        return Math.pow(edgeSize, 3);
    }
    @Override
    public  Double getPerimeter(){
        return null;
    }
}
