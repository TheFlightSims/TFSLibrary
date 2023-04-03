/*    */ package math.geom2d.circulinear.buffer;
/*    */ 
/*    */ import math.geom2d.Angle2D;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.Vector2D;
/*    */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*    */ import math.geom2d.conic.CircleArc2D;
/*    */ 
/*    */ public class RoundCapFactory implements CapFactory {
/*    */   public CirculinearContinuousCurve2D createCap(Point2D center, Vector2D direction, double dist) {
/* 35 */     double angle = direction.angle();
/* 36 */     double angle1 = Angle2D.formatAngle(angle - 1.5707963267948966D);
/* 37 */     double angle2 = Angle2D.formatAngle(angle + 1.5707963267948966D);
/* 38 */     return (CirculinearContinuousCurve2D)new CircleArc2D(center, dist, angle1, angle2, true);
/*    */   }
/*    */   
/*    */   public CirculinearContinuousCurve2D createCap(Point2D p1, Point2D p2) {
/* 42 */     Point2D center = Point2D.midPoint(p1, p2);
/* 43 */     double radius = p1.distance(p2) / 2.0D;
/* 45 */     double angle1 = Angle2D.horizontalAngle(center, p1);
/* 46 */     double angle2 = Angle2D.horizontalAngle(center, p2);
/* 47 */     return (CirculinearContinuousCurve2D)new CircleArc2D(center, radius, angle1, angle2, true);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\buffer\RoundCapFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */