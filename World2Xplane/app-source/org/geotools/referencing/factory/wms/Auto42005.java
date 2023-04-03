/*    */ package org.geotools.referencing.factory.wms;
/*    */ 
/*    */ import org.opengis.parameter.ParameterValueGroup;
/*    */ 
/*    */ final class Auto42005 extends Factlet {
/* 57 */   public static final Auto42005 DEFAULT = new Auto42005();
/*    */   
/*    */   public int code() {
/* 69 */     return 42005;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 76 */     return "WGS 84 / Auto Mollweider";
/*    */   }
/*    */   
/*    */   public String getClassification() {
/* 83 */     return "Mollweide";
/*    */   }
/*    */   
/*    */   protected void setProjectionParameters(ParameterValueGroup parameters, Code code) {
/* 90 */     double centralMeridian = code.longitude;
/* 92 */     parameters.parameter("central_meridian").setValue(centralMeridian);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\wms\Auto42005.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */