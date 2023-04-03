/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class LinearProjection extends Projection {
/*    */   public Point2D.Double transform(Point2D.Double src, Point2D.Double dst) {
/* 25 */     dst.x = src.x;
/* 26 */     dst.y = src.y;
/* 27 */     return dst;
/*    */   }
/*    */   
/*    */   public void transform(double[] srcPoints, int srcOffset, double[] dstPoints, int dstOffset, int numPoints) {
/* 31 */     for (int i = 0; i < numPoints; i++) {
/* 32 */       dstPoints[dstOffset++] = srcPoints[srcOffset++];
/* 33 */       dstPoints[dstOffset++] = srcPoints[srcOffset++];
/*    */     } 
/*    */   }
/*    */   
/*    */   public Point2D.Double inverseTransform(Point2D.Double src, Point2D.Double dst) {
/* 38 */     dst.x = src.x;
/* 39 */     dst.y = src.y;
/* 40 */     return dst;
/*    */   }
/*    */   
/*    */   public void inverseTransform(double[] srcPoints, int srcOffset, double[] dstPoints, int dstOffset, int numPoints) {
/* 44 */     for (int i = 0; i < numPoints; i++) {
/* 45 */       dstPoints[dstOffset++] = srcPoints[srcOffset++];
/* 46 */       dstPoints[dstOffset++] = srcPoints[srcOffset++];
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 51 */     return true;
/*    */   }
/*    */   
/*    */   public boolean isRectilinear() {
/* 55 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 59 */     return "Linear";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\LinearProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */