/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ public class LambertEqualAreaConicProjection extends AlbersProjection {
/*    */   public LambertEqualAreaConicProjection() {
/* 28 */     this(false);
/*    */   }
/*    */   
/*    */   public LambertEqualAreaConicProjection(boolean south) {
/* 32 */     this.minLatitude = Math.toRadians(0.0D);
/* 33 */     this.maxLatitude = Math.toRadians(90.0D);
/* 34 */     this.projectionLatitude1 = south ? -0.7853981633974483D : 0.7853981633974483D;
/* 35 */     this.projectionLatitude2 = south ? -1.5707963267948966D : 1.5707963267948966D;
/* 36 */     initialize();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 40 */     return "Lambert Equal Area Conic";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\LambertEqualAreaConicProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */