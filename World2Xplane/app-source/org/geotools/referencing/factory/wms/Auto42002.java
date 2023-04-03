/*     */ package org.geotools.referencing.factory.wms;
/*     */ 
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ 
/*     */ final class Auto42002 extends Factlet {
/*  63 */   public static final Auto42002 DEFAULT = new Auto42002();
/*     */   
/*     */   public int code() {
/*  75 */     return 42002;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  82 */     return "WGS 84 / Auto Tr. Mercator";
/*     */   }
/*     */   
/*     */   public String getClassification() {
/*  89 */     return "Transverse_Mercator";
/*     */   }
/*     */   
/*     */   protected void setProjectionParameters(ParameterValueGroup parameters, Code code) {
/*  96 */     double centralMeridian = code.longitude;
/*  97 */     double falseNorthing = (code.latitude >= 0.0D) ? 0.0D : 1.0E7D;
/*  99 */     parameters.parameter("latitude_of_origin").setValue(0.0D);
/* 100 */     parameters.parameter("central_meridian").setValue(centralMeridian);
/* 101 */     parameters.parameter("false_easting").setValue(500000.0D);
/* 102 */     parameters.parameter("false_northing").setValue(falseNorthing);
/* 103 */     parameters.parameter("scale_factor").setValue(0.9996D);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\wms\Auto42002.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */