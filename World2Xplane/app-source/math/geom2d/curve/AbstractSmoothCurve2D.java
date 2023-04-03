/*    */ package math.geom2d.curve;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.Vector2D;
/*    */ 
/*    */ public abstract class AbstractSmoothCurve2D extends AbstractContinuousCurve2D implements SmoothCurve2D, Cloneable {
/*    */   public Vector2D leftTangent(double t) {
/* 30 */     return tangent(t);
/*    */   }
/*    */   
/*    */   public Vector2D rightTangent(double t) {
/* 37 */     return tangent(t);
/*    */   }
/*    */   
/*    */   public Vector2D normal(double t) {
/* 44 */     return tangent(t).rotate(-1.5707963267948966D);
/*    */   }
/*    */   
/*    */   public Collection<? extends SmoothCurve2D> smoothPieces() {
/* 51 */     return wrapCurve(this);
/*    */   }
/*    */   
/*    */   public Collection<Point2D> singularPoints() {
/* 60 */     return new ArrayList<Point2D>(0);
/*    */   }
/*    */   
/*    */   public Collection<Point2D> vertices() {
/* 69 */     ArrayList<Point2D> array = new ArrayList<Point2D>(2);
/* 70 */     if (!Double.isInfinite(t0()))
/* 71 */       array.add(firstPoint()); 
/* 72 */     if (!Double.isInfinite(t1()))
/* 73 */       array.add(lastPoint()); 
/* 74 */     return array;
/*    */   }
/*    */   
/*    */   public boolean isSingular(double pos) {
/* 83 */     return false;
/*    */   }
/*    */   
/*    */   public abstract SmoothCurve2D clone();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\curve\AbstractSmoothCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */