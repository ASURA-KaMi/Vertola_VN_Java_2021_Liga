package com.bad.code2.shape;

import com.bad.code2.base.Point;
import com.bad.code2.base.Point3D;
import com.bad.code2.base.Shape3D;

public class Qube implements Shape3D {
    private Point3D zeroCorner;
    private Double edgeSize;

    public Qube(Point3D zeroCorner, Double edgeSize) {
        this.zeroCorner = zeroCorner;
        this.edgeSize = edgeSize;
    }
    @Override
    public Point3D getPos(){
        return zeroCorner;
    }
    @Override
    public Point3D getCenter(){
        double halfEdge = edgeSize/2;
            Point3D center = new Point3D(zeroCorner.getX()+halfEdge,
                                         zeroCorner.getY()+halfEdge,
                                         zeroCorner.getZ()+ halfEdge);
        return center;
    }
    @Override
    public Double getVolume() {
        return Math.pow(edgeSize, 3);
    }
}
