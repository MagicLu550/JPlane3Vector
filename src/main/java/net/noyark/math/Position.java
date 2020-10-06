package net.noyark.math;

/**
 * 点类
 */
public class Position {

    public static final Position ZERO = new Position(0,0,0);

    double x,y,z;

    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //点距离公式
    public double direction(Position p){
        return Math.sqrt(Math.pow((this.x - p.x),2)+Math.pow((this.y - p.y),2)+Math.pow((this.z - p.z),2));
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
