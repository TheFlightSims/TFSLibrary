/*    */ package org.poly2tri.triangulation.util;
/*    */ 
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ import org.poly2tri.geometry.polygon.Polygon;
/*    */ import org.poly2tri.geometry.polygon.PolygonPoint;
/*    */ 
/*    */ public class PolygonGenerator {
/*    */   private static final double PI_2 = 6.283185307179586D;
/*    */   
/*    */   public static Polygon RandomCircleSweep(double scale, int vertexCount) {
/* 34 */     double radius = scale / 4.0D;
/* 36 */     PolygonPoint[] points = new PolygonPoint[vertexCount];
/* 37 */     for (int i = 0; i < vertexCount; ) {
/*    */       while (true) {
/* 39 */         if (i % 250 == 0) {
/* 40 */           radius += scale / 2.0D * (0.5D - FastMath.random());
/* 41 */         } else if (i % 50 == 0) {
/* 42 */           radius += scale / 5.0D * (0.5D - FastMath.random());
/*    */         } else {
/* 44 */           radius += 25.0D * scale / vertexCount * (0.5D - FastMath.random());
/*    */         } 
/* 46 */         radius = (radius > scale / 2.0D) ? (scale / 2.0D) : radius;
/* 47 */         radius = (radius < scale / 10.0D) ? (scale / 10.0D) : radius;
/* 48 */         if (radius >= scale / 10.0D && radius <= scale / 2.0D) {
/* 49 */           PolygonPoint point = new PolygonPoint(radius * FastMath.cos(6.283185307179586D * i / vertexCount), radius * FastMath.sin(6.283185307179586D * i / vertexCount));
/* 51 */           points[i] = point;
/*    */           break;
/*    */         } 
/*    */       } 
/*    */       i++;
/*    */     } 
/* 53 */     return new Polygon(points);
/*    */   }
/*    */   
/*    */   public static Polygon RandomCircleSweep2(double scale, int vertexCount) {
/* 59 */     double radius = scale / 4.0D;
/* 61 */     PolygonPoint[] points = new PolygonPoint[vertexCount];
/* 62 */     for (int i = 0; i < vertexCount; ) {
/*    */       while (true) {
/* 64 */         radius += scale / 5.0D * (0.5D - FastMath.random());
/* 65 */         radius = (radius > scale / 2.0D) ? (scale / 2.0D) : radius;
/* 66 */         radius = (radius < scale / 10.0D) ? (scale / 10.0D) : radius;
/* 67 */         if (radius >= scale / 10.0D && radius <= scale / 2.0D) {
/* 68 */           PolygonPoint point = new PolygonPoint(radius * FastMath.cos(6.283185307179586D * i / vertexCount), radius * FastMath.sin(6.283185307179586D * i / vertexCount));
/* 70 */           points[i] = point;
/*    */           break;
/*    */         } 
/*    */       } 
/*    */       i++;
/*    */     } 
/* 72 */     return new Polygon(points);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulatio\\util\PolygonGenerator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */