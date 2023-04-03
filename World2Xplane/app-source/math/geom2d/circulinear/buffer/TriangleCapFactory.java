/*    */ package math.geom2d.circulinear.buffer;
/*    */ 
/*    */ import math.geom2d.Angle2D;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.Vector2D;
/*    */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*    */ import math.geom2d.polygon.Polyline2D;
/*    */ 
/*    */ public class TriangleCapFactory implements CapFactory {
/*    */   public CirculinearContinuousCurve2D createCap(Point2D center, Vector2D direction, double dist) {
/* 29 */     double theta = direction.angle();
/* 30 */     Point2D p1 = Point2D.createPolar(center, dist, theta - 1.5707963267948966D);
/* 31 */     Point2D p2 = Point2D.createPolar(center, dist, theta);
/* 32 */     Point2D p3 = Point2D.createPolar(center, dist, theta + 1.5707963267948966D);
/* 33 */     return (CirculinearContinuousCurve2D)new Polyline2D(new Point2D[] { p1, p2, p3 });
/*    */   }
/*    */   
/*    */   public CirculinearContinuousCurve2D createCap(Point2D p1, Point2D p2) {
/* 40 */     Point2D mid = Point2D.midPoint(p1, p2);
/* 41 */     double rho = Point2D.distance(p1, p2) / 2.0D;
/* 42 */     double theta = Angle2D.horizontalAngle(p1, p2) - 1.5707963267948966D;
/* 43 */     Point2D pt = Point2D.createPolar(mid, rho, theta);
/* 44 */     return (CirculinearContinuousCurve2D)new Polyline2D(new Point2D[] { p1, pt, p2 });
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\buffer\TriangleCapFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */