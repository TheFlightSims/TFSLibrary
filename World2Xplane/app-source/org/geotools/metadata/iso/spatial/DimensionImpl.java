/*     */ package org.geotools.metadata.iso.spatial;
/*     */ 
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.spatial.Dimension;
/*     */ import org.opengis.metadata.spatial.DimensionNameType;
/*     */ 
/*     */ public class DimensionImpl extends MetadataEntity implements Dimension {
/*     */   private static final long serialVersionUID = -2572515000574007266L;
/*     */   
/*     */   private DimensionNameType dimensionName;
/*     */   
/*     */   private Integer dimensionSize;
/*     */   
/*     */   private Double resolution;
/*     */   
/*     */   public DimensionImpl() {}
/*     */   
/*     */   public DimensionImpl(Dimension source) {
/*  72 */     super(source);
/*     */   }
/*     */   
/*     */   public DimensionImpl(DimensionNameType dimensionName, int dimensionSize) {
/*  79 */     setDimensionName(dimensionName);
/*  80 */     setDimensionSize(Integer.valueOf(dimensionSize));
/*     */   }
/*     */   
/*     */   public DimensionNameType getDimensionName() {
/*  87 */     return this.dimensionName;
/*     */   }
/*     */   
/*     */   public synchronized void setDimensionName(DimensionNameType newValue) {
/*  94 */     checkWritePermission();
/*  95 */     this.dimensionName = newValue;
/*     */   }
/*     */   
/*     */   public Integer getDimensionSize() {
/* 102 */     return this.dimensionSize;
/*     */   }
/*     */   
/*     */   public synchronized void setDimensionSize(Integer newValue) {
/* 109 */     checkWritePermission();
/* 110 */     this.dimensionSize = newValue;
/*     */   }
/*     */   
/*     */   public Double getResolution() {
/* 117 */     return this.resolution;
/*     */   }
/*     */   
/*     */   public synchronized void setResolution(Double newValue) {
/* 124 */     checkWritePermission();
/* 125 */     this.resolution = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\spatial\DimensionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */