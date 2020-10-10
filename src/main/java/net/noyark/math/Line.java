package net.noyark.math;

public class Line implements SpatialAggregation {

    private Position position01;

    private Position position02;


    public Line(Position position01, Position position02) {
        this.position01 = position01;
        this.position02 = position02;
    }

    @Override
    public double direction(SpatialAggregation v) {
        if(!v.parallel(this))return -1;
        // 线线距离
        if(v instanceof Line){
            return direction(((Line) v).position01);
        }else{
            return Vector3Math.linePlaneDirectionFormula(
                    Vector3.createVector(this.position01,((Plane)v).getPositions()[0]),
                    ((Plane)v).getNormalVector()
            );
        }
    }

    @Override
    public double direction(Position p) {
        return Vector3Math.lineLineDirectionFormula(Vector3.createVector(p,this.position01),this.getVector());
    }

    //到时候会改造这部分代码
    public boolean vertical(SpatialAggregation v){
        // 线和线垂直
        if(v instanceof Line){
            Vector3 v1 = ((Line) v).getVector();
            return v1.vertical(this.getVector());
            //线和向量垂直
        }else if(v instanceof Vector3){
            return v.vertical(this.getVector());
        }else{
            // 线和平面垂直
            return ((Plane) v).getNormalVector().isCollineation(this.getVector());
        }
    }

    @Override
    public Angle getAngle(Line l) {
        return new Angle(
                Math.acos(
                        Math.abs(
                                l.getVector().dotProduct(l.getVector())
                        )
                )
        );
    }

    @Override
    public Angle getAngle(Plane p2) {
        return p2.getAngle(this);
    }

    @Override
    public boolean parallel(SpatialAggregation v) {
        //线和线平行
        if(v instanceof Line){
            Vector3 v1 = ((Line) v).getVector();
            return v1.parallel(this.getVector()) && (!this.contains((Line)v));
            // 线和向量平行
        }else if(v instanceof Vector3){
            return v.parallel(this.getVector());
        }else{
            // 线和平面平行
            return (!v.contains(this))
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
     * 判断一点是否在一条直线上
     * @param p
     * @return
     */
    public boolean contains(Position p){
        return Vector3.createVector(p,position01).isCollineation(this.getVector());
    }

    /**
     * 两条直线是否重合
     * @param line
     * @return
     */
    @Override
    public boolean contains(Line line) {
        return contains(line.position01) && contains(line.position02);
    }

    /**
     * 获得方向向量
     * @return
     */
    public Vector3 getVector(){
        return Vector3.createVector(position01,position02);
    }
}
