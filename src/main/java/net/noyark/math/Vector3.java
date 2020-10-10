package net.noyark.math;

public class Vector3 implements SpatialAggregation {

    public static final Vector3 ZERO = Vector3.createVector(
            Position.ZERO,
            Position.ZERO
    );

    private Position start;

    private Position end;

    private double x;

    private double y;

    private double z;

    public Vector3(Position a,Position b){
        this.x = a.x - b.x;
        this.y = a.y - b.y;
        this.z = a.z - b.z;
        this.start = a;
        this.end = b;
    }

    /**
     * 两个向量是否共线
     * vec = new Vector3(1,2,3);
     * vec2 = new Vector3(2,3,6);
     *
     * @param vector3
     * @return
     */
    public boolean isCollineation(Vector3 vector3){
        double lambda = vector3.x/this.x;
        return vector3.y/this.y == lambda && (vector3.z / this.z == lambda);
    }

    public boolean parallel(SpatialAggregation relationship){
        if(relationship instanceof Vector3){
            return isCollineation((Vector3) relationship);
        }else{
            return relationship.parallel(this);
        }
    }

    /**
     * 向量的模
     * @return
     */
    public double mod(){
        return start.direction(this.end);
    }

    /**
     * 加减后的向量默认起点为原心
     * @param vector3
     * @return
     */
    public Vector3 add(Vector3 vector3){
        return createVector(new Position(this.x + vector3.x,this.y + vector3.y,this.z + vector3.z));
    }

    public Vector3 reduce(Vector3 vector3){
        return createVector(new Position(this.x - vector3.x,this.y - vector3.y,this.z - vector3.z));
    }

    public Vector3 scalar(int lamb){
        return createVector(new Position(this.x * lamb,this.y * lamb,this.z * lamb));
    }

    //数乘
    public double dotProduct(Vector3 v){
        return this.x * v.x + this.y * v.y + this.z + v.z;
    }

    public boolean vertical(SpatialAggregation v){
        if(v instanceof Vector3){
            return dotProduct((Vector3) v) == 0;
        }else{
            return v.vertical(this);
        }
    }



    /**
     * 向量的既定起点
     * @return
     */
    public Position getStart() {
        return start;
    }

    /**
     * 向量的既定终点
     * @return
     */
    public Position getEnd() {
        return end;
    }

    /**
     * 两个向量是否相等
     * @param vector3
     * @return
     */
    public boolean equals(Vector3 vector3){
        return  this.x == vector3.x && this.y == vector3.y && this.z == vector3.z;
    }

    @Override
    public String toString() {
        return "("+x+","+y+","+z+")";
    }

    public static Vector3 createVector(Position p1, Position p2){
        return new Vector3(p1,p2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public static Vector3 createVector(Position p){
        return new Vector3(Position.ZERO,p);
    }

}
