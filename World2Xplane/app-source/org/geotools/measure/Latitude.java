/*    */ package org.geotools.measure;
/*    */ 
/*    */ public final class Latitude extends Angle {
/*    */   private static final long serialVersionUID = -4496748683919618976L;
/*    */   
/*    */   public static final double MIN_VALUE = -90.0D;
/*    */   
/*    */   public static final double MAX_VALUE = 90.0D;
/*    */   
/*    */   public Latitude(double theta) {
/* 56 */     super(theta);
/*    */   }
/*    */   
/*    */   public Latitude(String theta) throws NumberFormatException {
/* 70 */     super(theta);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\measure\Latitude.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */