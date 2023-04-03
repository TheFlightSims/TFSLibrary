/*     */ package org.geotools.styling;
/*     */ 
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ 
/*     */ public enum UomOgcMapping {
/*  34 */   METRE(SI.METER, "http://www.opengeospatial.org/se/units/metre"),
/*  34 */   FOOT(NonSI.FOOT, "http://www.opengeospatial.org/se/units/foot"),
/*  35 */   PIXEL(NonSI.PIXEL, "http://www.opengeospatial.org/se/units/pixel");
/*     */   
/*     */   private String seString;
/*     */   
/*     */   private Unit<Length> unit;
/*     */   
/*     */   UomOgcMapping(Unit<Length> unit, String seString) {
/*  52 */     this.unit = unit;
/*  53 */     this.seString = seString;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  58 */     return this.seString;
/*     */   }
/*     */   
/*     */   public String getSEString() {
/*  67 */     return this.seString;
/*     */   }
/*     */   
/*     */   public Unit<Length> getUnit() {
/*  76 */     return this.unit;
/*     */   }
/*     */   
/*     */   public static UomOgcMapping get(String seString) throws IllegalArgumentException {
/*  89 */     for (UomOgcMapping uom : values()) {
/*  90 */       if (uom.getSEString().equals(seString))
/*  91 */         return uom; 
/*     */     } 
/*  93 */     throw new IllegalArgumentException("'" + seString + "' is not a valid OGC SE standard Unit of Measure");
/*     */   }
/*     */   
/*     */   public static UomOgcMapping get(Unit<Length> unit) throws IllegalArgumentException {
/* 107 */     for (UomOgcMapping uom : values()) {
/* 108 */       if (uom.getUnit().equals(unit))
/* 109 */         return uom; 
/*     */     } 
/* 111 */     throw new IllegalArgumentException("'" + unit + "' is not a valid OGC SE standard Unit of Measure");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\UomOgcMapping.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */