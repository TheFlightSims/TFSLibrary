/*    */ package org.poly2tri.triangulation.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ import org.poly2tri.triangulation.TriangulationPoint;
/*    */ import org.poly2tri.triangulation.point.TPoint;
/*    */ 
/*    */ public class PointGenerator {
/*    */   public static List<TriangulationPoint> uniformDistribution(int n, double scale) {
/* 36 */     ArrayList<TriangulationPoint> points = new ArrayList<>();
/* 37 */     for (int i = 0; i < n; i++)
/* 39 */       points.add(new TPoint(scale * (0.5D - FastMath.random()), scale * (0.5D - FastMath.random()))); 
/* 41 */     return points;
/*    */   }
/*    */   
/*    */   public static List<TriangulationPoint> uniformGrid(int n, double scale) {
/* 46 */     double x = 0.0D;
/* 47 */     double size = scale / n;
/* 48 */     double halfScale = 0.5D * scale;
/* 50 */     ArrayList<TriangulationPoint> points = new ArrayList<>();
/* 51 */     for (int i = 0; i < n + 1; i++) {
/* 53 */       x = halfScale - i * size;
/* 54 */       for (int j = 0; j < n + 1; j++)
/* 56 */         points.add(new TPoint(x, halfScale - j * size)); 
/*    */     } 
/* 59 */     return points;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulatio\\util\PointGenerator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */