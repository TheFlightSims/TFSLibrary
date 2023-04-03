/*    */ package math.geom2d.circulinear.buffer;
/*    */ 
/*    */ import math.geom2d.Angle2D;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.Vector2D;
/*    */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*    */ import math.geom2d.circulinear.CirculinearElement2D;
/*    */ import math.geom2d.conic.CircleArc2D;
/*    */ import math.geom2d.curve.Curve2D;
/*    */ import math.geom2d.curve.Curves2D;
/*    */ import math.geom2d.line.LineSegment2D;
/*    */ 
/*    */ public class RoundJoinFactory implements JoinFactory {
/*    */   public CirculinearContinuousCurve2D createJoin(CirculinearElement2D curve1, CirculinearElement2D curve2, double dist) {
/* 38 */     Point2D center = curve2.firstPoint();
/* 39 */     Curves2D.JunctionType junctionType = 
/* 40 */       Curves2D.getJunctionType((Curve2D)curve1, (Curve2D)curve2);
/* 43 */     Vector2D direction1 = curve1.tangent(curve1.t1());
/* 44 */     Vector2D direction2 = curve2.tangent(curve2.t0());
/* 47 */     double angle1 = direction1.angle();
/* 48 */     double angle2 = direction2.angle();
/* 51 */     if ((dist > 0.0D && junctionType == Curves2D.JunctionType.REENTRANT) || (dist <= 0.0D && junctionType == Curves2D.JunctionType.SALIENT)) {
/* 52 */       Point2D p1 = Point2D.createPolar(center, dist, angle1 - 1.5707963267948966D);
/* 53 */       Point2D p2 = Point2D.createPolar(center, dist, angle2 - 1.5707963267948966D);
/* 54 */       return (CirculinearContinuousCurve2D)new LineSegment2D(p1, p2);
/*    */     } 
/* 59 */     if (dist > 0.0D) {
/* 60 */       startAngle = angle1 - 1.5707963267948966D;
/* 61 */       endAngle = angle2 - 1.5707963267948966D;
/*    */     } else {
/* 63 */       startAngle = angle1 + 1.5707963267948966D;
/* 64 */       endAngle = angle2 + 1.5707963267948966D;
/*    */     } 
/* 68 */     double startAngle = Angle2D.formatAngle(startAngle);
/* 69 */     double endAngle = Angle2D.formatAngle(endAngle);
/* 73 */     if (junctionType == Curves2D.JunctionType.FLAT)
/* 74 */       return (CirculinearContinuousCurve2D)new CircleArc2D(center, Math.abs(dist), startAngle, 0.0D); 
/* 77 */     return (CirculinearContinuousCurve2D)new CircleArc2D(
/* 78 */         center, Math.abs(dist), startAngle, endAngle, (dist > 0.0D));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\buffer\RoundJoinFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */