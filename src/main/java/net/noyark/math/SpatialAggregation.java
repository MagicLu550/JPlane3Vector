package net.noyark.math;

public interface SpatialAggregation extends SpatialElement{

    boolean vertical(SpatialAggregation v);

    boolean parallel(SpatialAggregation v);

    default boolean contains(Position p1){
        return false;
    }

    default boolean contains(Line line){
        return false;
    }

    static SpatialAggregation define(Position p1,Position p2){
        return new Line(p1,p2);
    }
    static SpatialAggregation define(Position p1,Position p2,Position p3){
        return new Plane(p1,p2,p3);
    }

    static SpatialAggregation define(Position p1,Position p2,SRCS srcs){
        return srcs.defineLine(p1,p2);
    }
    static SpatialAggregation define(Position p1,Position p2,Position p3,SRCS srcs){
        return srcs.definePlane(p1,p2,p3);
    }

}
