package net.noyark.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 空间直角坐标系。
 * 空间直角坐标系保证坐标系中的
 * 所有几何对象保持其唯一性，在一个系统中维持运作
 */
public class SRCS {

    // 每一个平面对应一个对象，
    // 判断依据是：这个三个点是否都在某个平面上
    private List<Plane> planes = new ArrayList<>();

    private List<Line> lines = new ArrayList<>();

    public Plane definePlane(Position p1,Position p2,Position p3){

        for(Plane p : planes){
            if(p.contains(p1) && p.contains(p2) && p.contains(p3)){
                return p;
            }
        }
        Plane p = new Plane(p1,p2,p3);
        planes.add(p);
        return p;
    }

    public Line defineLine(Position p1,Position p2){
        for(Line l : lines){
            if(l.contains(p1) && l.contains(p2)){
                return l;
            }
        }
        Line l = new Line(p1,p2);
        lines.add(l);
        return l;
    }

    /**
     * 可视化
     */
    public void show(){

    }

    public static SRCS createSpatialRectangularCoordinateSystem(){
        return new SRCS();
    }




}
