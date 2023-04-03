/*     */ package org.geotools.referencing.factory.wms;
/*     */ 
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ 
/*     */ final class Auto42001 extends Factlet {
/*  64 */   public static final Auto42001 DEFAULT = new Auto42001();
/*     */   
/*     */   public int code() {
/*  76 */     return 42001;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  83 */     return "WGS 84 / Auto UTM";
/*     */   }
/*     */   
/*     */   public String getClassification() {
/*  90 */     return "Transverse_Mercator";
/*     */   }
/*     */   
/*     */   protected void setProjectionParameters(ParameterValueGroup parameters, Code code) {
/*  97 */     double zone = Math.min(Math.floor((code.longitude + 180.0D) / 6.0D) + 1.0D, 60.0D);
/*  98 */     double centralMeridian = -183.0D + zone * 6.0D;
/*  99 */     double falseNorthing = (code.latitude >= 0.0D) ? 0.0D : 1.0E7D;
/* 101 */     parameters.parameter("latitude_of_origin").setValue(0.0D);
/* 102 */     parameters.parameter("central_meridian").setValue(centralMeridian);
/* 103 */     parameters.parameter("false_easting").setValue(500000.0D);
/* 104 */     parameters.parameter("false_northing").setValue(falseNorthing);
/* 105 */     parameters.parameter("scale_factor").setValue(0.9996D);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\wms\Auto42001.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */