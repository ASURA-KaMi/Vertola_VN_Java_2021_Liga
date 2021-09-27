package com.bad.code2.shape;

import com.bad.code2.base.Point;
import com.bad.code2.base.Shape2D;

public class Square implements Shape2D {
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


    public Double getPerimeter() {
        return edgeSize * edgeSize;
    }
}
