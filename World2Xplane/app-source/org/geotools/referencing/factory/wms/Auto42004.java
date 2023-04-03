/*    */ package org.geotools.referencing.factory.wms;
/*    */ 
/*    */ import org.opengis.parameter.ParameterValueGroup;
/*    */ 
/*    */ final class Auto42004 extends Factlet {
/* 60 */   public static final Auto42004 DEFAULT = new Auto42004();
/*    */   
/*    */   public int code() {
/* 72 */     return 42004;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 79 */     return "WGS 84 / Auto Equirectangular";
/*    */   }
/*    */   
/*    */   public String getClassification() {
/* 86 */     return "Equidistant_Cylindrical";
/*    */   }
/*    */   
/*    */   protected void setProjectionParameters(ParameterValueGroup parameters, Code code) {
/* 93 */     double centralMeridian = code.longitude;
/* 94 */     double standardParallel1 = code.latitude;
/* 96 */     parameters.parameter("central_meridian").setValue(centralMeridian);
/* 97 */     parameters.parameter("latitude_of_origin").setValue(0.0D);
/* 98 */     parameters.parameter("standard_parallel_1").setValue(standardParallel1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\wms\Auto42004.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */