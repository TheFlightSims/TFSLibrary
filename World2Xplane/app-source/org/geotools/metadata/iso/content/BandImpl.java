/*     */ package org.geotools.metadata.iso.content;
/*     */ 
/*     */ import javax.measure.unit.Unit;
/*     */ import org.opengis.metadata.content.Band;
/*     */ import org.opengis.metadata.content.RangeDimension;
/*     */ 
/*     */ public class BandImpl extends RangeDimensionImpl implements Band {
/*     */   private static final long serialVersionUID = -2302918545469034653L;
/*     */   
/*     */   private Double maxValue;
/*     */   
/*     */   private Double minValue;
/*     */   
/*     */   private Unit units;
/*     */   
/*     */   private Double peakResponse;
/*     */   
/*     */   private Integer bitsPerValue;
/*     */   
/*     */   private Integer toneGradation;
/*     */   
/*     */   private Double scaleFactor;
/*     */   
/*     */   private Double offset;
/*     */   
/*     */   public BandImpl() {}
/*     */   
/*     */   public BandImpl(Band source) {
/* 104 */     super((RangeDimension)source);
/*     */   }
/*     */   
/*     */   public Double getMaxValue() {
/* 112 */     return this.maxValue;
/*     */   }
/*     */   
/*     */   public synchronized void setMaxValue(Double newValue) {
/* 120 */     checkWritePermission();
/* 121 */     this.maxValue = newValue;
/*     */   }
/*     */   
/*     */   public Double getMinValue() {
/* 129 */     return this.minValue;
/*     */   }
/*     */   
/*     */   public synchronized void setMinValue(Double newValue) {
/* 137 */     checkWritePermission();
/* 138 */     this.minValue = newValue;
/*     */   }
/*     */   
/*     */   public Unit getUnits() {
/* 147 */     return this.units;
/*     */   }
/*     */   
/*     */   public synchronized void setUnits(Unit newValue) {
/* 156 */     checkWritePermission();
/* 157 */     this.units = newValue;
/*     */   }
/*     */   
/*     */   public Double getPeakResponse() {
/* 165 */     return this.peakResponse;
/*     */   }
/*     */   
/*     */   public synchronized void setPeakResponse(Double newValue) {
/* 172 */     checkWritePermission();
/* 173 */     this.peakResponse = newValue;
/*     */   }
/*     */   
/*     */   public Integer getBitsPerValue() {
/* 182 */     return this.bitsPerValue;
/*     */   }
/*     */   
/*     */   public synchronized void setBitsPerValue(Integer newValue) {
/* 190 */     checkWritePermission();
/* 191 */     this.bitsPerValue = newValue;
/*     */   }
/*     */   
/*     */   public Integer getToneGradation() {
/* 199 */     return this.toneGradation;
/*     */   }
/*     */   
/*     */   public synchronized void setToneGradation(Integer newValue) {
/* 206 */     checkWritePermission();
/* 207 */     this.toneGradation = newValue;
/*     */   }
/*     */   
/*     */   public Double getScaleFactor() {
/* 215 */     return this.scaleFactor;
/*     */   }
/*     */   
/*     */   public synchronized void setScaleFactor(Double newValue) {
/* 222 */     checkWritePermission();
/* 223 */     this.scaleFactor = newValue;
/*     */   }
/*     */   
/*     */   public Double getOffset() {
/* 231 */     return this.offset;
/*     */   }
/*     */   
/*     */   public synchronized void setOffset(Double newValue) {
/* 238 */     checkWritePermission();
/* 239 */     this.offset = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\content\BandImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */