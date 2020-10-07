package net.noyark.math;

public class Test {
    public static void main(String[] args) {
        Plane p1 = new Plane(new Position(0,0,1)
                ,new Position(0,2,2)
                ,new Position(3,4,5)
        );
        Plane p2 = new Plane(new Position(0,0,1)
                ,new Position(0,2,2)
                ,new Position(3,4,5)
        );
        System.out.println(p1.containsPoint(new Position(0,0,1)));
    }
}
