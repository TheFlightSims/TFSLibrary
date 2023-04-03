/*    */ package org.geotools.referencing.factory.wms;
/*    */ 
/*    */ import org.opengis.parameter.ParameterValueGroup;
/*    */ 
/*    */ final class Auto42003 extends Factlet {
/* 62 */   public static final Auto42003 DEFAULT = new Auto42003();
/*    */   
/*    */   public int code() {
/* 74 */     return 42003;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 81 */     return "WGS 84 / Auto Orthographic";
/*    */   }
/*    */   
/*    */   public String getClassification() {
/* 88 */     return "Orthographic";
/*    */   }
/*    */   
/*    */   protected void setProjectionParameters(ParameterValueGroup parameters, Code code) {
/* 95 */     double latitudeOfOrigin = code.latitude;
/* 96 */     double centralMeridian = code.longitude;
/* 98 */     parameters.parameter("latitude_of_origin").setValue(latitudeOfOrigin);
/* 99 */     parameters.parameter("central_meridian").setValue(centralMeridian);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\wms\Auto42003.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */