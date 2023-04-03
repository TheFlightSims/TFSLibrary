/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ public class PlateCarreeProjection extends CylindricalProjection {
/*    */   public boolean hasInverse() {
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   public boolean isRectilinear() {
/* 28 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 32 */     return "Plate Carrée";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\PlateCarreeProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */