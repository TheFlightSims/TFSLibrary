/*    */ package math.geom2d.polygon.convhull;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import math.geom2d.Angle2D;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.polygon.Polygon2D;
/*    */ import math.geom2d.polygon.SimplePolygon2D;
/*    */ 
/*    */ public class JarvisMarch2D implements ConvexHull2D {
/*    */   public Polygon2D convexHull(Collection<? extends Point2D> points) {
/* 42 */     Point2D lowestPoint = null;
/* 44 */     double ymin = Double.MAX_VALUE;
/* 47 */     for (Point2D point : points) {
/* 48 */       double y = point.y();
/* 49 */       if (y < ymin) {
/* 50 */         ymin = y;
/* 51 */         lowestPoint = point;
/*    */       } 
/*    */     } 
/* 56 */     ArrayList<Point2D> hullPoints = new ArrayList<Point2D>();
/* 59 */     Point2D currentPoint = lowestPoint;
/* 60 */     Point2D nextPoint = null;
/* 61 */     double angle = 0.0D;
/*    */     do {
/* 66 */       hullPoints.add(currentPoint);
/* 67 */       nextPoint = findNextPoint(currentPoint, angle, points);
/* 68 */       angle = Angle2D.horizontalAngle(currentPoint, nextPoint);
/* 69 */       currentPoint = nextPoint;
/* 70 */     } while (currentPoint != lowestPoint);
/* 73 */     return (Polygon2D)new SimplePolygon2D(hullPoints);
/*    */   }
/*    */   
/*    */   private Point2D findNextPoint(Point2D basePoint, double startAngle, Collection<? extends Point2D> points) {
/* 78 */     Point2D minPoint = null;
/* 79 */     double minAngle = Double.MAX_VALUE;
/* 82 */     for (Point2D point : points) {
/* 84 */       if (basePoint.equals(point))
/*    */         continue; 
/* 88 */       double angle = Angle2D.horizontalAngle(basePoint, point);
/* 89 */       angle = Angle2D.formatAngle(angle - startAngle);
/* 92 */       if (angle < minAngle) {
/* 93 */         minAngle = angle;
/* 94 */         minPoint = point;
/*    */       } 
/*    */     } 
/* 98 */     return minPoint;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\convhull\JarvisMarch2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */