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
}
