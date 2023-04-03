/*    */ package org.poly2tri.triangulation.point;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import org.poly2tri.triangulation.TriangulationPoint;
/*    */ 
/*    */ public class FloatBufferPoint extends TriangulationPoint {
/*    */   private final FloatBuffer _fb;
/*    */   
/*    */   private final int _ix;
/*    */   
/*    */   private final int _iy;
/*    */   
/*    */   private final int _iz;
/*    */   
/*    */   public FloatBufferPoint(FloatBuffer fb, int index) {
/* 36 */     this._fb = fb;
/* 37 */     this._ix = index;
/* 38 */     this._iy = index + 1;
/* 39 */     this._iz = index + 2;
/*    */   }
/*    */   
/*    */   public final double getX() {
/* 44 */     return this._fb.get(this._ix);
/*    */   }
/*    */   
/*    */   public final double getY() {
/* 48 */     return this._fb.get(this._iy);
/*    */   }
/*    */   
/*    */   public final double getZ() {
/* 52 */     return this._fb.get(this._iz);
/*    */   }
/*    */   
/*    */   public final float getXf() {
/* 57 */     return this._fb.get(this._ix);
/*    */   }
/*    */   
/*    */   public final float getYf() {
/* 61 */     return this._fb.get(this._iy);
/*    */   }
/*    */   
/*    */   public final float getZf() {
/* 65 */     return this._fb.get(this._iz);
/*    */   }
/*    */   
/*    */   public void set(double x, double y, double z) {
/* 71 */     this._fb.put(this._ix, (float)x);
/* 72 */     this._fb.put(this._iy, (float)y);
/* 73 */     this._fb.put(this._iz, (float)z);
/*    */   }
/*    */   
/*    */   public static TriangulationPoint[] toPoints(FloatBuffer fb) {
/* 78 */     FloatBufferPoint[] points = new FloatBufferPoint[fb.limit() / 3];
/* 79 */     for (int i = 0, j = 0; i < points.length; i++, j += 3)
/* 81 */       points[i] = new FloatBufferPoint(fb, j); 
/* 83 */     return (TriangulationPoint[])points;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\point\FloatBufferPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */