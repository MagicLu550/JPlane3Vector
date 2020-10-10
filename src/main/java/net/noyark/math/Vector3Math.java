package net.noyark.math;


public class Vector3Math {

    /**
     * alpha是凭借线
     * @param alpha
     * @param normal
     * @return
     */
    static double linePlaneDirectionFormula(Vector3 alpha,Vector3 normal){
            return Math.abs(alpha.dotProduct(normal)) / normal.mod();
    }

    static double lineLineDirectionFormula(Vector3 alpha,Vector3 l){
        Vector3 muu = l.scalar(1/l.mod());
        return Math.sqrt(Math.pow(alpha.mod(),2) - Math.pow((alpha.dotProduct(muu)),2));
    }
}
