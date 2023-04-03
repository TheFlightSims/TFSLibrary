/*     */ package org.geotools.metadata.iso.extent;
/*     */ 
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.extent.VerticalExtent;
/*     */ import org.opengis.referencing.crs.VerticalCRS;
/*     */ 
/*     */ public class VerticalExtentImpl extends MetadataEntity implements VerticalExtent {
/*     */   private static final long serialVersionUID = -3214554246909844079L;
/*     */   
/*     */   private Double minimumValue;
/*     */   
/*     */   private Double maximumValue;
/*     */   
/*     */   private VerticalCRS verticalCRS;
/*     */   
/*     */   public VerticalExtentImpl() {}
/*     */   
/*     */   public VerticalExtentImpl(VerticalExtent source) {
/*  76 */     super(source);
/*     */   }
/*     */   
/*     */   public VerticalExtentImpl(Double minimumValue, Double maximumValue, VerticalCRS verticalCRS) {
/*  88 */     setMinimumValue(minimumValue);
/*  89 */     setMaximumValue(maximumValue);
/*  90 */     setVerticalCRS(verticalCRS);
/*     */   }
/*     */   
/*     */   public Double getMinimumValue() {
/*  97 */     return this.minimumValue;
/*     */   }
/*     */   
/*     */   public synchronized void setMinimumValue(Double newValue) {
/* 104 */     checkWritePermission();
/* 105 */     this.minimumValue = newValue;
/*     */   }
/*     */   
/*     */   public Double getMaximumValue() {
/* 112 */     return this.maximumValue;
/*     */   }
/*     */   
/*     */   public synchronized void setMaximumValue(Double newValue) {
/* 119 */     checkWritePermission();
/* 120 */     this.maximumValue = newValue;
/*     */   }
/*     */   
/*     */   public VerticalCRS getVerticalCRS() {
/* 132 */     return this.verticalCRS;
/*     */   }
/*     */   
/*     */   public synchronized void setVerticalCRS(VerticalCRS newValue) {
/* 142 */     checkWritePermission();
/* 143 */     this.verticalCRS = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\extent\VerticalExtentImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */