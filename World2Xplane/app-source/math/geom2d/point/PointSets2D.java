/*     */ package math.geom2d.point;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import math.geom2d.Point2D;
/*     */ 
/*     */ public class PointSets2D {
/*     */   public static <T extends Point2D> boolean hasMultipleVertices(List<T> points) {
/*  23 */     return hasMultipleVertices(points, false);
/*     */   }
/*     */   
/*     */   public static <T extends Point2D> boolean hasMultipleVertices(List<T> points, boolean closed) {
/*     */     Point2D point2D;
/*  40 */     Iterator<T> iter = points.iterator();
/*  42 */     T previous = null;
/*  44 */     if (closed) {
/*  45 */       point2D = (Point2D)points.get(points.size() - 1);
/*     */     } else {
/*  47 */       point2D = (Point2D)iter.next();
/*     */     } 
/*  51 */     while (iter.hasNext()) {
/*  52 */       Point2D point2D1 = (Point2D)iter.next();
/*  53 */       if (Point2D.distance(point2D1, point2D) < 1.0E-12D)
/*  54 */         return true; 
/*  55 */       point2D = point2D1;
/*     */     } 
/*  57 */     return false;
/*     */   }
/*     */   
/*     */   public static <T extends Point2D> int countMultipleVertices(List<T> points) {
/*  65 */     return countMultipleVertices(points, false);
/*     */   }
/*     */   
/*     */   public static <T extends Point2D> int countMultipleVertices(List<T> points, boolean closed) {
/*     */     Point2D point2D;
/*  75 */     int count = 0;
/*  78 */     Iterator<T> iter = points.iterator();
/*  81 */     T previous = null;
/*  83 */     if (closed) {
/*  84 */       point2D = (Point2D)points.get(points.size() - 1);
/*     */     } else {
/*  86 */       point2D = (Point2D)iter.next();
/*     */     } 
/*  90 */     while (iter.hasNext()) {
/*  91 */       Point2D point2D1 = (Point2D)iter.next();
/*  92 */       if (Point2D.distance(point2D1, point2D) < 1.0E-12D)
/*  93 */         count++; 
/*  94 */       point2D = point2D1;
/*     */     } 
/*  98 */     return count;
/*     */   }
/*     */   
/*     */   public static <T extends Point2D> List<T> filterMultipleVertices(List<T> vertices) {
/* 103 */     return filterMultipleVertices(vertices, false);
/*     */   }
/*     */   
/*     */   public static <T extends Point2D> List<T> filterMultipleVertices(List<T> vertices, boolean closed) {
/*     */     Point2D point2D;
/* 110 */     int size = vertices.size();
/* 111 */     int nMulti = countMultipleVertices(vertices, closed);
/* 114 */     ArrayList<T> result = new ArrayList<T>(size - nMulti);
/* 117 */     if (size == 0)
/* 118 */       return result; 
/* 122 */     Iterator<T> iter = vertices.iterator();
/* 123 */     T previous = null;
/* 124 */     if (closed) {
/* 125 */       point2D = (Point2D)vertices.get(size - 1);
/*     */     } else {
/* 127 */       point2D = (Point2D)iter.next();
/* 128 */       result.add((T)point2D);
/*     */     } 
/* 132 */     while (iter.hasNext()) {
/* 133 */       Point2D point2D1 = (Point2D)iter.next();
/* 134 */       if (Point2D.distance(point2D1, point2D) > 1.0E-12D)
/* 135 */         result.add((T)point2D1); 
/* 137 */       point2D = point2D1;
/*     */     } 
/* 139 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\point\PointSets2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */