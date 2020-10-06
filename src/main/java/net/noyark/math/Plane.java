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
public class Plane implements Relationship {

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
    public boolean vertical(Relationship v) {

        return false;
    }


    /**
     * 证明平行
     * @param v
     * @return
     */
    @Override
    public boolean parallel(Relationship v) {
        return false;
    }

    /**
     * 证明一条线位于一个平面内
     * @param line
     * @return
     */
    public boolean contains(Line line){
        //获得两个点
        Position p1 = line.getPosition01();
        Position p2 = line.getPosition02();
        //之后证明两个点分别在两个平面上
        return containsPoint(p1) && containsPoint(p2);
    }

    //(x1,x2,y2) = a(x2,y2,z2) + b(x3,y3,z3)
    //(x1,x2,y2) = (ax2+bx3,ay2+by3,az2+bz3)
    //解方程
    //a = (x1 - bx3)/x2
    //a = (x2 - by3)/y2
    // (x1 - bx3)/x2 = (x2 - by3)/y2
    // b1 = (x1y2 - x2 * x2) / （y2x3 - y3x2）
    // 同理： b2 = (y2 * y2 - z3x2) / (z2y2 - y3z3)
    // 推到a的方程
    // bx3 = x1 - ax2
    // by3 = x2 - ay2
    // b = (x1 - ax2)/x3
    // b =( x2 - ay2)/y3
    // (x1 - ax2)/x3 = (x2 - zy2)/y3
    // (x2 - ay2)x3 = (x1-ax2)y3
    // x2x3 -ay2x3 = x1y3 - ax2y3
    // ay2x3 - ax2y3 = x2x3 - x1y3
    // a(y2x3 - x2y3) = x2x3 - x1y3
    // a = (x2x3 - x1y3) / (y2x3 - x2y3)
    // 同理：a2 = （z3x2 - y2y3) / (y2z3 - z2y3)
    public boolean containsPoint(Position p1){

        Vector3 v1 = Vector3.createVector(p1,position01);//1
        Vector3 p1p2 = Vector3.createVector(position01,position02);
        Vector3 p1p3 = Vector3.createVector(position01,position03);


        double x1 = v1.getX();
        double x2 = p1p2.getX();
        double x3 = p1p3.getX();
        double y2 = p1p2.getY();
        double y3 = p1p3.getY();
        double z2 = p1p2.getZ();
        double z3 = p1p3.getZ();

        double b1 = (x1*y2 - Math.pow(x2,2))/(y2 * x3 - y3 * x2);
        double b2 = (y2 * y2 - z3 * x2)/(z2 * y2 - y3 * y3*z3);

        double a1 = (x2 * x3 - x1 * y3) / (y2 *x3 - z2 * y3);

        double a2 = (z3 * x2 - y2 * y3) / (y2 * z3 - z2 * y3);


        return b1 == b2 && a1 == a2;
    }

    /**
     * 求两平面二面角
     * @param p2
     * @return
     */
    public double getDihedralAngle(Plane p2){
        Vector3 v1 = this.getNormalVector();
        Vector3 v2 = p2.getNormalVector();
        return Math.abs(v1.dotProduct(v2)) / (v1.mod() * v2.mod());
    }

    public Position[] getPositions() {
        return new Position[]{position01,position02,position03};
    }
}
