/*    */ package math.geom2d.circulinear.buffer;
/*    */ 
/*    */ import math.geom2d.Angle2D;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.Vector2D;
/*    */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*    */ import math.geom2d.polygon.Polyline2D;
/*    */ 
/*    */ public class SquareCapFactory implements CapFactory {
/*    */   public CirculinearContinuousCurve2D createCap(Point2D center, Vector2D direction, double dist) {
/* 29 */     double theta = direction.angle();
/* 30 */     return (CirculinearContinuousCurve2D)createCap(center, theta, dist);
/*    */   }
/*    */   
/*    */   public CirculinearContinuousCurve2D createCap(Point2D p1, Point2D p2) {
/* 37 */     Point2D center = Point2D.midPoint(p1, p2);
/* 38 */     double dist = Point2D.distance(p1, p2) / 2.0D;
/* 39 */     double theta = Angle2D.horizontalAngle(p1, p2) - 1.5707963267948966D;
/* 40 */     return (CirculinearContinuousCurve2D)createCap(center, theta, dist);
/*    */   }
/*    */   
/*    */   private Polyline2D createCap(Point2D center, double theta, double dist) {
/* 47 */     Point2D p1 = Point2D.createPolar(center, dist, theta - 1.5707963267948966D);
/* 48 */     Point2D p4 = Point2D.createPolar(center, dist, theta + 1.5707963267948966D);
/* 49 */     Point2D p2 = Point2D.createPolar(p1, dist, theta);
/* 50 */     Point2D p3 = Point2D.createPolar(p4, dist, theta);
/* 51 */     return new Polyline2D(new Point2D[] { p1, p2, p3, p4 });
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\buffer\SquareCapFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */