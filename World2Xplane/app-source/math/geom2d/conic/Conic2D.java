/*    */ package math.geom2d.conic;
/*    */ 
/*    */ import math.geom2d.AffineTransform2D;
/*    */ import math.geom2d.Box2D;
/*    */ import math.geom2d.curve.CurveSet2D;
/*    */ import math.geom2d.domain.Boundary2D;
/*    */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*    */ 
/*    */ public interface Conic2D extends Boundary2D {
/*    */   Type conicType();
/*    */   
/*    */   double[] conicCoefficients();
/*    */   
/*    */   double eccentricity();
/*    */   
/*    */   Conic2D reverse();
/*    */   
/*    */   Conic2D transform(AffineTransform2D paramAffineTransform2D);
/*    */   
/*    */   CurveSet2D<? extends ContinuousOrientedCurve2D> clip(Box2D paramBox2D);
/*    */   
/*    */   public enum Type {
/* 53 */     NOT_A_CONIC, ELLIPSE, HYPERBOLA, PARABOLA, CIRCLE, STRAIGHT_LINE, TWO_LINES, POINT;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\Conic2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */