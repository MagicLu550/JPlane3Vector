package net.noyark.math;

/**
 * 表征一个平面
 * 1. 求平面交线向量
 * 2. 两线交点
 * 3. 平面平行，平面垂直
 * 4. 投影
 * 5. 一条线的方向向量
 * 6. 一条线在平面的射影
 * 7. 一条线的投影
 * 8. 构建复杂几何模型（Shape类
 */
public class Plane implements SpatialAggregation {

    private Position position01;

    private Position position02;

    private Position position03;

    private Vector3 normalVector;


    public Plane(Position position01, Position position02, Position position03) {
        this.position01 = position01;
        this.position02 = position02;
        this.position03 = position03;
    }


    public Vector3 getNormalVector(){
        if(normalVector != null)return normalVector;
        Vector3 v1 = Vector3.createVector(position01,position02);
        Vector3 v2 = Vector3.createVector(position02,position03);
        double xUnder = (v1.getZ()*v2.getX() - v1.getX() * v2.getZ());
        double yUnder = (v2.getZ() * v1.getX() - v1.getZ() * v2.getX());
        double lambda = xUnder * yUnder;
        double x = (lambda * v2.getZ() * v1.getY() - lambda * v1.getZ() * v2.getY() )/ xUnder;
        double z = lambda * (v1.getY()*v2.getX() - v2.getY() * v1.getX())/yUnder;
        this.normalVector = Vector3.createVector(new Position(x,lambda,z));
        return normalVector;
    }

    /**
     * 证明垂直
     * @param v
     * @return
     */
    @Override
    public boolean vertical(SpatialAggregation v) {
        // 平面和平面垂直
        if(v instanceof Plane){
            return ((Plane) v).normalVector.vertical(this.normalVector);
        // 平面和线垂直
        }else if(v instanceof Line){
            return v.vertical(this);
        // 平面和向量垂直
        }else{
            return v.parallel(this.normalVector);
        }
    }

    @Override
    public double direction(Position p) {
        return Vector3Math.linePlaneDirectionFormula(Vector3.createVector(p,this.position01),this.getNormalVector());
    }

    /**
     * 在使用前请判断一下是否平行，否则计算会不准确
     * @param v
     * @return
     */
    @Override
    public double direction(SpatialAggregation v) {
        if(!v.parallel(this))return -1;
        // 线面距离
        if(v instanceof Line){
            return v.direction(this);
        }else{
            // 面面距离
            return direction(((Plane)v).position01);
        }
    }

    /**
     * 证明平行
     * @param v
     * @return
     */
    @Override
    public boolean parallel(SpatialAggregation v) {
        // 平面和平面平行
        if(v instanceof  Plane){
            return ((Plane) v).normalVector.isCollineation(this.normalVector);
        }else if(v instanceof Line){
            // 平面和线平行
            return v.parallel(this);
        }else{
            // 平面和向量平行
            return v.vertical(this.normalVector);
        }
    }

    /**
     * 证明一条线位于一个平面上
     * @param line
     * @return
     */
    public boolean contains(Line line){
        Position p1 = line.getPosition01();
        Position p2 = line.getPosition02();
        return contains(p1) && contains(p2);
    }

    /**
     * 判断一点是否在该平面内
     * @param p1
     * @return
     */
    public boolean contains(Position p1){
        return crossComparison(
                Vector3.createVector(p1,position01),
                Vector3.createVector(position01,position02),
                Vector3.createVector(position01,position03)
        );
    }


    /**
     * 求两平面二面角
     * @param p2
     * @return
     */
    public Angle getAngle(Plane p2){
        Vector3 v1 = this.getNormalVector();
        Vector3 v2 = p2.getNormalVector();
        return new Angle(
                Math.acos(
                        Math.abs(v1.dotProduct(v2)) / (v1.mod() * v2.mod())
                )
        );
    }

    /**
     * 求一线和一面的夹角
     */
    public Angle getAngle(Line l){
        return new Angle(
                Math.asin(
                        Math.abs(
                                this.getNormalVector().dotProduct(l.getVector())
                        )
                )
        );
    }

    public Position[] getPositions() {
        return new Position[]{position01,position02,position03};
    }

    /**
     * 交叉运算比较，向量共面
     * @param v1
     * @param p1p2
     * @param p1p3
     * @return
     */
    private boolean crossComparison(Vector3 v1,Vector3 p1p2,Vector3 p1p3){
        double x1 = v1.getX();
        double x2 = p1p2.getX();
        double x3 = p1p3.getX();
        double y1 = v1.getY();
        double y2 = p1p2.getY();
        double y3 = p1p3.getY();
        double z1 = v1.getZ();
        double z2 = p1p2.getZ();
        double z3 = p1p3.getZ();
        double b1 = (x2 * y1 - x1 * y2) / (y3 * x2 - y2 * x3);
        double b2 = (z1 * y2 - z2 * y1) / (z3 * y2 - y3 * z2);
        double a1 = (y3 * x1 - y1 * x3) / (x2 * y3 - y2 * x3);
        double a2 = (z3 * y1 - z1 * y3) / (y2 * z3 - z2 * y3);
        return b1 == b2 && a1 == a2;
    }

}
