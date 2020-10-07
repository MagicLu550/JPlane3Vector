package net.noyark.math;

public class Line implements Relationship {

    private Position position01;

    private Position position02;


    public Line(Position position01, Position position02) {
        this.position01 = position01;
        this.position02 = position02;
    }

    //到时候会改造这部分代码
    public boolean vertical(Relationship v){
        if(v instanceof Line){
            Vector3 v1 = ((Line) v).getVector();
            return v1.vertical(this.getVector());
        }else if(v instanceof Vector3){
            return v.vertical(this.getVector());
        }else{
            // 是否和该平面的法向量共线
            return ((Plane) v).getNormalVector().isCollineation(this.getVector());
        }
    }

    @Override
    public boolean parallel(Relationship v) {
        if(v instanceof Line){
            Vector3 v1 = ((Line) v).getVector();
            return v1.parallel(this.getVector());
        }else if(v instanceof Vector3){
            return v.parallel(this.getVector());
        }else{
            // 不在该平面且和该平面法向量垂直
            return (!((Plane) v).contains(this))
                    && this.vertical(((Plane)v).getNormalVector());
        }
    }

    public Position getPosition01() {
        return position01;
    }

    public Position getPosition02() {
        return position02;
    }

    /**
     * 获得方向向量
     * @return
     */
    public Vector3 getVector(){
        return Vector3.createVector(position01,position02);
    }
}
