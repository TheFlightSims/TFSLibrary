/*    */ package math.geom2d.polygon.convhull;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import math.geom2d.Angle2D;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.polygon.Polygon2D;
/*    */ import math.geom2d.polygon.SimplePolygon2D;
/*    */ 
/*    */ public class GrahamScan2D implements ConvexHull2D {
/*    */   public Polygon2D convexHull(Collection<? extends Point2D> points) {
/* 43 */     int nbPoints = points.size();
/* 47 */     Point2D lowestPoint = null;
/* 48 */     double lowestY = Double.MAX_VALUE;
/* 49 */     for (Point2D point : points) {
/* 50 */       double y = point.y();
/* 51 */       if (y < lowestY) {
/* 52 */         lowestPoint = point;
/* 53 */         lowestY = y;
/*    */       } 
/*    */     } 
/* 58 */     Comparator<Point2D> comparator = 
/* 59 */       new CompareByPseudoAngle(lowestPoint);
/* 62 */     ArrayList<Point2D> sorted = new ArrayList<Point2D>(nbPoints);
/* 63 */     sorted.addAll(points);
/* 64 */     Collections.sort(sorted, comparator);
/* 69 */     int m = 2;
/* 70 */     for (int i = 3; i < nbPoints; i++) {
/* 71 */       while (Point2D.ccw(sorted.get(m), sorted.get(m - 1), 
/* 72 */           sorted.get(i)) >= 0)
/* 73 */         m--; 
/* 74 */       m++;
/* 75 */       Collections.swap(sorted, m, i);
/*    */     } 
/* 79 */     List<Point2D> hull = sorted.subList(0, Math.min(m + 1, nbPoints));
/* 80 */     return (Polygon2D)new SimplePolygon2D(hull);
/*    */   }
/*    */   
/*    */   private class CompareByPseudoAngle implements Comparator<Point2D> {
/*    */     Point2D basePoint;
/*    */     
/*    */     public CompareByPseudoAngle(Point2D base) {
/* 86 */       this.basePoint = base;
/*    */     }
/*    */     
/*    */     public int compare(Point2D point1, Point2D point2) {
/* 93 */       double angle1 = Angle2D.pseudoAngle(this.basePoint, point1);
/* 94 */       double angle2 = Angle2D.pseudoAngle(this.basePoint, point2);
/* 96 */       if (angle1 < angle2)
/* 96 */         return -1; 
/* 97 */       if (angle1 > angle2)
/* 97 */         return 1; 
/* 99 */       return 0;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\convhull\GrahamScan2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */