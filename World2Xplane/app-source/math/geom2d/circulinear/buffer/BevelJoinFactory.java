/*    */ package math.geom2d.circulinear.buffer;
/*    */ 
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*    */ import math.geom2d.circulinear.CirculinearElement2D;
/*    */ import math.geom2d.line.LineSegment2D;
/*    */ 
/*    */ public class BevelJoinFactory implements JoinFactory {
/*    */   public LineSegment2D createJoin(CirculinearElement2D curve1, CirculinearElement2D curve2, double dist) {
/* 27 */     Point2D p1 = curve1.lastPoint();
/* 28 */     Point2D p2 = curve2.firstPoint();
/* 29 */     return new LineSegment2D(p1, p2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\buffer\BevelJoinFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */