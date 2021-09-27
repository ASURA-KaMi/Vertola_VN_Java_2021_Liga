package com.bad.code2.shape;

import com.bad.code2.base.Point;
import com.bad.code2.base.Shape;

public class Square implements Shape {
    private Point leftCorner;
    private Double edgeSize;

    public Square(Point leftCorner, Double edgeSize) {
        this.leftCorner = leftCorner;
        this.edgeSize = edgeSize;
    }

    @Override
    public Point getPos() {
        return leftCorner;
    }
    @Override
    public Point getCenter() {
        double halfEdge = edgeSize/2;
        Point center = new Point(leftCorner.getX()+halfEdge,
                                 leftCorner.getY()+halfEdge);
        return center;
    }
    @Override
    public Double getVolume(){
        return null;
    }

    @Override
    public Double getPerimeter() {
        return edgeSize * edgeSize;
    }
}
