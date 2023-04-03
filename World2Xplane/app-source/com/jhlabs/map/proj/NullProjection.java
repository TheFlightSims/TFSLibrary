/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class NullProjection extends Projection {
/*    */   public Point2D.Double transform(Point2D.Double src, Point2D.Double dst) {
/* 28 */     dst.x = src.x;
/* 29 */     dst.y = src.y;
/* 30 */     return dst;
/*    */   }
/*    */   
/*    */   public Shape projectPath(Shape path, AffineTransform t, boolean filled) {
/* 34 */     if (t != null)
/* 35 */       t.createTransformedShape(path); 
/* 36 */     return path;
/*    */   }
/*    */   
/*    */   public Shape getBoundingShape() {
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   public boolean isRectilinear() {
/* 44 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 48 */     return "Null";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\NullProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */