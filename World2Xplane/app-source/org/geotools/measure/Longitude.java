/*    */ package org.geotools.measure;
/*    */ 
/*    */ public final class Longitude extends Angle {
/*    */   private static final long serialVersionUID = -8614900608052762636L;
/*    */   
/*    */   public static final double MIN_VALUE = -180.0D;
/*    */   
/*    */   public static final double MAX_VALUE = 180.0D;
/*    */   
/*    */   public Longitude(double theta) {
/* 55 */     super(theta);
/*    */   }
/*    */   
/*    */   public Longitude(String theta) throws NumberFormatException {
/* 69 */     super(theta);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\measure\Longitude.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */