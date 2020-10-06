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
public class Plane {

    private Position position01;

    private Position position02;

    private Position position03;


    public Plane(Position position01, Position position02, Position position03) {
        this.position01 = position01;
        this.position02 = position02;
        this.position03 = position03;
    }


    public Vector3 getNormalVector(){
        Vector3 v1 = Vector3.createVector(position01,position02);
        Vector3 v2 = Vector3.createVector(position02,position03);
        double xUnder = (v1.getZ()*v2.getX() - v1.getX() * v2.getZ());
        double yUnder = (v2.getZ() * v1.getX() - v1.getZ() * v2.getX());
        double lambda = xUnder * yUnder;
        double x = (lambda * v2.getZ() * v1.getY() - lambda * v1.getZ() * v2.getY() )/ xUnder;
        double z = lambda * (v1.getY()*v2.getX() - v2.getY() * v1.getX())/yUnder;
        return Vector3.createVector(new Position(x,lambda,z));
    }

    /**
     * 求两平面二面角
     * @param p2
     * @return
     */
    public double getDihedralAngle(Plane p2){
        Vector3 v1 = this.getNormalVector();
        Vector3 v2 = p2.getNormalVector();
        return v1.dotProduct(v2) / (v1.mod() * v2.mod());
    }

    public Position[] getPositions() {
        return new Position[]{position01,position02,position03};
    }
}
