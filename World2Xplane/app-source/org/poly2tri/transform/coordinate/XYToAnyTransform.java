/*    */ package org.poly2tri.transform.coordinate;
/*    */ 
/*    */ public class XYToAnyTransform extends Matrix3Transform {
/*    */   public XYToAnyTransform(double nx, double ny, double nz) {
/* 40 */     setTargetNormal(nx, ny, nz);
/*    */   }
/*    */   
/*    */   public void setTargetNormal(double nx, double ny, double nz) {
/* 54 */     double vx = ny;
/* 55 */     double vy = -nx;
/* 56 */     double c = nz;
/* 58 */     double h = (1.0D - c) / (1.0D - c * c);
/* 59 */     double hvx = h * vx;
/* 60 */     double f = (c < 0.0D) ? -c : c;
/* 62 */     if (f < 0.9999D) {
/* 64 */       this.m00 = c + hvx * vx;
/* 65 */       this.m01 = hvx * vy;
/* 66 */       this.m02 = -vy;
/* 67 */       this.m10 = hvx * vy;
/* 68 */       this.m11 = c + h * vy * vy;
/* 69 */       this.m12 = vx;
/* 70 */       this.m20 = vy;
/* 71 */       this.m21 = -vx;
/* 72 */       this.m22 = c;
/*    */     } else {
/* 77 */       this.m00 = 1.0D;
/* 78 */       this.m01 = 0.0D;
/* 79 */       this.m02 = 0.0D;
/* 80 */       this.m10 = 0.0D;
/* 81 */       this.m11 = 1.0D;
/* 82 */       this.m12 = 0.0D;
/* 83 */       this.m20 = 0.0D;
/* 84 */       this.m21 = 0.0D;
/* 85 */       if (c > 0.0D) {
/* 87 */         this.m22 = 1.0D;
/*    */       } else {
/* 91 */         this.m22 = -1.0D;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\transform\coordinate\XYToAnyTransform.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */