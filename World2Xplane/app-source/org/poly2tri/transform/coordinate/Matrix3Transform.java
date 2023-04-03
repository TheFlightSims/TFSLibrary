/*    */ package org.poly2tri.transform.coordinate;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.poly2tri.geometry.primitives.Point;
/*    */ 
/*    */ public abstract class Matrix3Transform implements CoordinateTransform {
/*    */   protected double m00;
/*    */   
/*    */   protected double m01;
/*    */   
/*    */   protected double m02;
/*    */   
/*    */   protected double m10;
/*    */   
/*    */   protected double m11;
/*    */   
/*    */   protected double m12;
/*    */   
/*    */   protected double m20;
/*    */   
/*    */   protected double m21;
/*    */   
/*    */   protected double m22;
/*    */   
/*    */   public void transform(Point p, Point store) {
/* 35 */     double px = p.getX();
/* 36 */     double py = p.getY();
/* 37 */     double pz = p.getZ();
/* 38 */     store.set(this.m00 * px + this.m01 * py + this.m02 * pz, this.m10 * px + this.m11 * py + this.m12 * pz, this.m20 * px + this.m21 * py + this.m22 * pz);
/*    */   }
/*    */   
/*    */   public void transform(Point p) {
/* 45 */     double px = p.getX();
/* 46 */     double py = p.getY();
/* 47 */     double pz = p.getZ();
/* 48 */     p.set(this.m00 * px + this.m01 * py + this.m02 * pz, this.m10 * px + this.m11 * py + this.m12 * pz, this.m20 * px + this.m21 * py + this.m22 * pz);
/*    */   }
/*    */   
/*    */   public void transform(List<? extends Point> list) {
/* 55 */     for (Point p : list)
/* 57 */       transform(p); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\transform\coordinate\Matrix3Transform.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */