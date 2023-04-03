/*     */ package au.com.objectix.jgridshift;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class GridShift implements Serializable {
/*     */   private static final double METRE_PER_SECOND = 30.92208077590933D;
/*     */   
/*     */   private static final double RADIANS_PER_SECOND = 4.84813681109536E-6D;
/*     */   
/*     */   private double lon;
/*     */   
/*     */   private double lat;
/*     */   
/*     */   private double lonShift;
/*     */   
/*     */   private double latShift;
/*     */   
/*     */   private double lonAccuracy;
/*     */   
/*     */   private double latAccuracy;
/*     */   
/*     */   boolean latAccuracyAvailable;
/*     */   
/*     */   boolean lonAccuracyAvailable;
/*     */   
/*     */   private String subGridName;
/*     */   
/*     */   public double getLatSeconds() {
/*  54 */     return this.lat;
/*     */   }
/*     */   
/*     */   public double getLatDegrees() {
/*  61 */     return this.lat / 3600.0D;
/*     */   }
/*     */   
/*     */   public double getLatShiftSeconds() {
/*  68 */     return this.latShift;
/*     */   }
/*     */   
/*     */   public double getLatShiftDegrees() {
/*  75 */     return this.latShift / 3600.0D;
/*     */   }
/*     */   
/*     */   public double getShiftedLatSeconds() {
/*  82 */     return this.lat + this.latShift;
/*     */   }
/*     */   
/*     */   public double getShiftedLatDegrees() {
/*  89 */     return (this.lat + this.latShift) / 3600.0D;
/*     */   }
/*     */   
/*     */   public boolean isLatAccuracyAvailable() {
/*  96 */     return this.latAccuracyAvailable;
/*     */   }
/*     */   
/*     */   public double getLatAccuracySeconds() {
/* 103 */     if (!this.latAccuracyAvailable)
/* 104 */       throw new IllegalStateException("Latitude Accuracy not available"); 
/* 106 */     return this.latAccuracy;
/*     */   }
/*     */   
/*     */   public double getLatAccuracyDegrees() {
/* 113 */     if (!this.latAccuracyAvailable)
/* 114 */       throw new IllegalStateException("Latitude Accuracy not available"); 
/* 116 */     return this.latAccuracy / 3600.0D;
/*     */   }
/*     */   
/*     */   public double getLatAccuracyMetres() {
/* 123 */     if (!this.latAccuracyAvailable)
/* 124 */       throw new IllegalStateException("Latitude Accuracy not available"); 
/* 126 */     return this.latAccuracy * 30.92208077590933D;
/*     */   }
/*     */   
/*     */   public double getLonPositiveWestSeconds() {
/* 133 */     return this.lon;
/*     */   }
/*     */   
/*     */   public double getLonPositiveEastDegrees() {
/* 140 */     return this.lon / -3600.0D;
/*     */   }
/*     */   
/*     */   public double getLonShiftPositiveWestSeconds() {
/* 147 */     return this.lonShift;
/*     */   }
/*     */   
/*     */   public double getLonShiftPositiveEastDegrees() {
/* 154 */     return this.lonShift / -3600.0D;
/*     */   }
/*     */   
/*     */   public double getShiftedLonPositiveWestSeconds() {
/* 161 */     return this.lon + this.lonShift;
/*     */   }
/*     */   
/*     */   public double getShiftedLonPositiveEastDegrees() {
/* 168 */     return (this.lon + this.lonShift) / -3600.0D;
/*     */   }
/*     */   
/*     */   public boolean isLonAccuracyAvailable() {
/* 175 */     return this.lonAccuracyAvailable;
/*     */   }
/*     */   
/*     */   public double getLonAccuracySeconds() {
/* 182 */     if (!this.lonAccuracyAvailable)
/* 183 */       throw new IllegalStateException("Longitude Accuracy not available"); 
/* 185 */     return this.lonAccuracy;
/*     */   }
/*     */   
/*     */   public double getLonAccuracyDegrees() {
/* 192 */     if (!this.lonAccuracyAvailable)
/* 193 */       throw new IllegalStateException("Longitude Accuracy not available"); 
/* 195 */     return this.lonAccuracy / 3600.0D;
/*     */   }
/*     */   
/*     */   public double getLonAccuracyMetres() {
/* 202 */     if (!this.lonAccuracyAvailable)
/* 203 */       throw new IllegalStateException("Longitude Accuracy not available"); 
/* 205 */     return this.lonAccuracy * 30.92208077590933D * Math.cos(4.84813681109536E-6D * this.lat);
/*     */   }
/*     */   
/*     */   public void setLatSeconds(double d) {
/* 212 */     this.lat = d;
/*     */   }
/*     */   
/*     */   public void setLatDegrees(double d) {
/* 219 */     this.lat = d * 3600.0D;
/*     */   }
/*     */   
/*     */   public void setLatAccuracyAvailable(boolean b) {
/* 226 */     this.latAccuracyAvailable = b;
/*     */   }
/*     */   
/*     */   public void setLatAccuracySeconds(double d) {
/* 233 */     this.latAccuracy = d;
/*     */   }
/*     */   
/*     */   public void setLatShiftSeconds(double d) {
/* 240 */     this.latShift = d;
/*     */   }
/*     */   
/*     */   public void setLonPositiveWestSeconds(double d) {
/* 247 */     this.lon = d;
/*     */   }
/*     */   
/*     */   public void setLonPositiveEastDegrees(double d) {
/* 254 */     this.lon = d * -3600.0D;
/*     */   }
/*     */   
/*     */   public void setLonAccuracyAvailable(boolean b) {
/* 261 */     this.lonAccuracyAvailable = b;
/*     */   }
/*     */   
/*     */   public void setLonAccuracySeconds(double d) {
/* 268 */     this.lonAccuracy = d;
/*     */   }
/*     */   
/*     */   public void setLonShiftPositiveWestSeconds(double d) {
/* 275 */     this.lonShift = d;
/*     */   }
/*     */   
/*     */   public String getSubGridName() {
/* 282 */     return this.subGridName;
/*     */   }
/*     */   
/*     */   public void setSubGridName(String string) {
/* 289 */     this.subGridName = string;
/*     */   }
/*     */   
/*     */   public void copy(GridShift gs) {
/* 297 */     this.lon = gs.lon;
/* 298 */     this.lat = gs.lat;
/* 299 */     this.lonShift = gs.lonShift;
/* 300 */     this.latShift = gs.latShift;
/* 301 */     this.lonAccuracy = gs.lonAccuracy;
/* 302 */     this.latAccuracy = gs.latAccuracy;
/* 303 */     this.latAccuracyAvailable = gs.latAccuracyAvailable;
/* 304 */     this.lonAccuracyAvailable = gs.lonAccuracyAvailable;
/* 305 */     this.subGridName = gs.subGridName;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\au\com\objectix\jgridshift\GridShift.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */