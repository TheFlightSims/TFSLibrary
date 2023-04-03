/*    */ package org.poly2tri.transform.coordinate;
/*    */ 
/*    */ public class AnyToXYTransform extends Matrix3Transform {
/*    */   public AnyToXYTransform(double nx, double ny, double nz) {
/* 38 */     setSourceNormal(nx, ny, nz);
/*    */   }
/*    */   
/*    */   public void setSourceNormal(double nx, double ny, double nz) {
/* 52 */     double vx = -ny;
/* 53 */     double vy = nx;
/* 54 */     double c = nz;
/* 56 */     double h = (1.0D - c) / (1.0D - c * c);
/* 57 */     double hvx = h * vx;
/* 58 */     double f = (c < 0.0D) ? -c : c;
/* 60 */     if (f < 0.9999D) {
/* 62 */       this.m00 = c + hvx * vx;
/* 63 */       this.m01 = hvx * vy;
/* 64 */       this.m02 = -vy;
/* 65 */       this.m10 = hvx * vy;
/* 66 */       this.m11 = c + h * vy * vy;
/* 67 */       this.m12 = vx;
/* 68 */       this.m20 = vy;
/* 69 */       this.m21 = -vx;
/* 70 */       this.m22 = c;
/*    */     } else {
/* 75 */       this.m00 = 1.0D;
/* 76 */       this.m01 = 0.0D;
/* 77 */       this.m02 = 0.0D;
/* 78 */       this.m10 = 0.0D;
/* 79 */       this.m11 = 1.0D;
/* 80 */       this.m12 = 0.0D;
/* 81 */       this.m20 = 0.0D;
/* 82 */       this.m21 = 0.0D;
/* 83 */       if (c > 0.0D) {
/* 85 */         this.m22 = 1.0D;
/*    */       } else {
/* 89 */         this.m22 = -1.0D;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\transform\coordinate\AnyToXYTransform.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */