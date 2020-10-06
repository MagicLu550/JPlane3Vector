package net.noyark.math;

public class Line implements Relationship {

    private Position position01;

    private Position position02;


    public Line(Position position01, Position position02) {
        this.position01 = position01;
        this.position02 = position02;
    }

    public boolean vertical(Relationship v){
        if(v instanceof Line){
            Vector3 v1 = ((Line) v).getVector();
            return v1.vertical(this.getVector());
        }
        if(v instanceof Vector3){
            return v.vertical(this.getVector());
        }
        if(v instanceof Plane){
            Position[] positions = ((Plane) v).getPositions();
            Line line1 = new Line(positions[0],positions[1]);
            Line line2 = new Line(positions[1],positions[2]);
            return line1.vertical(this) && line2.vertical(this);
        }
        return false;
    }

    @Override
    public boolean parallel(Relationship v) {
        if(v instanceof Line){
            Vector3 v1 = ((Line) v).getVector();
            return v1.parallel(this.getVector());
        }
        if(v instanceof Vector3){
            return v.parallel(this.getVector());
        }
        if(v instanceof Plane){
            Position[] positions = ((Plane) v).getPositions();
            Line line1 = new Line(positions[0],positions[1]);
            Line line2 = new Line(positions[1],positions[2]);
            return line1.parallel(this) && line2.parallel(this);
        }
        return false;
    }

    /**
     * 获得方向向量
     * @return
     */
    public Vector3 getVector(){
        return Vector3.createVector(position01,position02);
    }
}
