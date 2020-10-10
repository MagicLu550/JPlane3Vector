package net.noyark.math;

public class Angle {

    private double angle;

    public Angle(double angle){
        this.angle = angle;
    }

    public double cos(){
        return Math.cos(angle);
    }

    public double sin(){
        return Math.sin(angle);
    }

    public double cot(){
        return 1/tan();
    }

    public double tan(){
        return Math.tan(angle);
    }

    public double getAngle(){
        return angle;
    }


}
