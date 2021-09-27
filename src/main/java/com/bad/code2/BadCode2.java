package com.bad.code2;

import com.bad.code2.base.Point;
import com.bad.code2.base.Point3D;
import com.bad.code2.shape.Qube;
import com.bad.code2.shape.Square;

public class BadCode2 {
    public static void main(String... args) {
        Point point = new Point(1d, 1d);
        Point3D point3D = new Point3D(1d, 1d, 1d);
        Square square = new Square(point, 5d);
        Qube qube = new Qube(point3D, 10d);
        System.out.println("Square left corner position: " + square.getPos().printPos());
        System.out.println("Square center position: " + square.getCenter().printPos());
        System.out.println("Square perimeter: " + square.getPerimeter());

        System.out.println("Qube left corner position: " + qube.getPos().printPos());
        System.out.println("Qube center position: " + qube.getCenter().printPos());
        System.out.println("Qube volume: " + qube.getVolume());
    }

}
