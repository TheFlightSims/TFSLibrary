/*    */ package math.geom2d.circulinear.buffer;
/*    */ 
/*    */ import math.geom2d.Angle2D;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.Vector2D;
/*    */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*    */ import math.geom2d.circulinear.CirculinearElement2D;
/*    */ import math.geom2d.polygon.Polyline2D;
/*    */ 
/*    */ public class MiterJoinFactory implements JoinFactory {
/* 26 */   private double minDenom = 1.0E-100D;
/*    */   
/*    */   public CirculinearContinuousCurve2D createJoin(CirculinearElement2D curve1, CirculinearElement2D curve2, double dist) {
/* 35 */     Point2D pc1 = curve1.lastPoint();
/* 36 */     Point2D pc2 = curve2.firstPoint();
/* 39 */     Vector2D vect1 = curve1.tangent(curve1.t1());
/* 40 */     Vector2D vect2 = curve2.tangent(curve2.t0());
/* 41 */     double theta1 = vect1.angle();
/* 42 */     double theta2 = vect2.angle();
/* 46 */     Point2D center = Point2D.midPoint(pc1, pc2);
/* 49 */     Point2D p1 = Point2D.createPolar(center, dist, theta1 - 1.5707963267948966D);
/* 50 */     Point2D p2 = Point2D.createPolar(center, dist, theta2 - 1.5707963267948966D);
/* 52 */     double dtheta = Angle2D.formatAngle(theta2 - theta1);
/* 53 */     if (dtheta > Math.PI)
/* 54 */       dtheta -= 6.283185307179586D; 
/* 56 */     double denom = Math.cos(dtheta / 2.0D);
/* 58 */     denom = Math.max(denom, this.minDenom);
/* 60 */     double hypot = dist / denom;
/* 62 */     if ((((dtheta > 0.0D) ? 1 : 0) ^ ((dist < 0.0D) ? 1 : 0)) != 0) {
/* 64 */       double angle = theta1 - 1.5707963267948966D + dtheta / 2.0D;
/* 65 */       Point2D pt = Point2D.createPolar(center, hypot, angle);
/* 66 */       return (CirculinearContinuousCurve2D)new Polyline2D(new Point2D[] { p1, pt, p2 });
/*    */     } 
/* 70 */     return (CirculinearContinuousCurve2D)new Polyline2D(new Point2D[] { pc1, pc2 });
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\buffer\MiterJoinFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */