/*     */ package org.geotools.referencing.factory.wms;
/*     */ 
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ 
/*     */ final class Code {
/*     */   public final String authority;
/*     */   
/*     */   public final int code;
/*     */   
/*     */   public final double longitude;
/*     */   
/*     */   public final double latitude;
/*     */   
/*     */   final Class type;
/*     */   
/*     */   public Code(String text, Class type) throws NoSuchAuthorityCodeException {
/*  75 */     String parts[], authority = "AUTO";
/*  76 */     int code = 0;
/*  77 */     int unit = 9001;
/*  78 */     double longitude = Double.NaN;
/*  79 */     double latitude = Double.NaN;
/*  88 */     if (text.startsWith("AUTO")) {
/*  89 */       parts = text.replaceAll("AUTO(2)?\\s*:", "").split("\\s*,\\s*");
/*     */     } else {
/*  91 */       parts = text.split("\\s*,\\s*");
/*     */     } 
/*  94 */     if (parts.length < 3)
/*  95 */       throw noSuchAuthorityCode(type, text); 
/*     */     try {
/*  99 */       if (parts.length < 4) {
/* 101 */         code = Integer.parseInt(parts[0]);
/* 102 */         longitude = Double.parseDouble(parts[1]);
/* 103 */         latitude = Double.parseDouble(parts[2]);
/*     */       } else {
/* 106 */         code = Integer.parseInt(parts[0]);
/* 107 */         unit = Integer.parseInt(parts[1]);
/* 108 */         longitude = Double.parseDouble(parts[2]);
/* 109 */         latitude = Double.parseDouble(parts[3]);
/*     */       } 
/* 111 */     } catch (NumberFormatException exception) {
/* 113 */       NoSuchAuthorityCodeException e = noSuchAuthorityCode(type, text);
/* 114 */       e.initCause(exception);
/* 115 */       throw e;
/*     */     } 
/* 118 */     if (longitude < -180.0D || longitude > 180.0D || latitude < -90.0D || latitude > 90.0D)
/* 123 */       throw noSuchAuthorityCode(type, text); 
/* 125 */     this.authority = authority;
/* 126 */     this.code = code;
/* 127 */     this.longitude = longitude;
/* 128 */     this.latitude = latitude;
/* 129 */     this.type = type;
/*     */   }
/*     */   
/*     */   private static NoSuchAuthorityCodeException noSuchAuthorityCode(Class type, String code) {
/* 142 */     String authority = "AUTO";
/* 143 */     return new NoSuchAuthorityCodeException(Errors.format(138, code, "AUTO", type), "AUTO", code);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 151 */     return this.authority + ':' + this.code + ',' + this.longitude + ',' + this.latitude;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\wms\Code.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */