/*    */ package math.geom2d.circulinear.buffer;
/*    */ 
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.Vector2D;
/*    */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*    */ import math.geom2d.line.LineSegment2D;
/*    */ 
/*    */ public class ButtCapFactory implements CapFactory {
/*    */   public CirculinearContinuousCurve2D createCap(Point2D center, Vector2D direction, double dist) {
/* 28 */     double theta = direction.angle();
/* 29 */     Point2D p1 = Point2D.createPolar(center, dist / 2.0D, theta - 1.5707963267948966D);
/* 30 */     Point2D p2 = Point2D.createPolar(center, dist / 2.0D, theta + 1.5707963267948966D);
/* 31 */     return (CirculinearContinuousCurve2D)new LineSegment2D(p1, p2);
/*    */   }
/*    */   
/*    */   public CirculinearContinuousCurve2D createCap(Point2D p1, Point2D p2) {
/* 38 */     return (CirculinearContinuousCurve2D)new LineSegment2D(p1, p2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\buffer\ButtCapFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */