/*    */ package math.geom2d.polygon;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.line.LineSegment2D;
/*    */ import math.geom2d.line.LinearShape2D;
/*    */ import math.geom2d.point.PointSets2D;
/*    */ 
/*    */ public abstract class Polylines2D {
/*    */   public static final boolean hasMultipleVertices(LinearCurve2D polyline) {
/* 28 */     return hasMultipleVertices(polyline, false);
/*    */   }
/*    */   
/*    */   public static final boolean hasMultipleVertices(LinearCurve2D polyline, boolean closed) {
/* 38 */     if (PointSets2D.hasMultipleVertices(polyline.vertices))
/* 39 */       return true; 
/* 42 */     if (closed) {
/* 43 */       Point2D p1 = polyline.firstPoint();
/* 44 */       Point2D p2 = polyline.lastPoint();
/* 45 */       if (p1.distance(p2) < 1.0E-12D)
/* 46 */         return true; 
/*    */     } 
/* 49 */     return false;
/*    */   }
/*    */   
/*    */   public static Collection<Point2D> intersect(LinearCurve2D poly1, LinearCurve2D poly2) {
/* 64 */     ArrayList<Point2D> points = new ArrayList<Point2D>();
/* 68 */     for (LineSegment2D edge1 : poly1.edges()) {
/* 69 */       for (LineSegment2D edge2 : poly2.edges()) {
/* 71 */         Point2D point = edge1.intersection((LinearShape2D)edge2);
/* 72 */         if (point != null)
/* 74 */           if (!points.contains(point))
/* 75 */             points.add(point);  
/*    */       } 
/*    */     } 
/* 80 */     return points;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\Polylines2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */