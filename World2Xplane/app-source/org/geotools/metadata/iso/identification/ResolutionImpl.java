/*     */ package org.geotools.metadata.iso.identification;
/*     */ 
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.identification.RepresentativeFraction;
/*     */ import org.opengis.metadata.identification.Resolution;
/*     */ 
/*     */ public class ResolutionImpl extends MetadataEntity implements Resolution {
/*     */   private static final long serialVersionUID = -4644465057871958482L;
/*     */   
/*     */   private RepresentativeFraction equivalentScale;
/*     */   
/*     */   private Double distance;
/*     */   
/*     */   public ResolutionImpl() {}
/*     */   
/*     */   public ResolutionImpl(Resolution source) {
/*  72 */     super(source);
/*     */   }
/*     */   
/*     */   public RepresentativeFraction getEquivalentScale() {
/*  81 */     return this.equivalentScale;
/*     */   }
/*     */   
/*     */   public synchronized void setEquivalentScale(RepresentativeFraction newValue) {
/*  90 */     checkWritePermission();
/*  91 */     this.equivalentScale = newValue;
/*     */   }
/*     */   
/*     */   public Double getDistance() {
/* 100 */     return this.distance;
/*     */   }
/*     */   
/*     */   public synchronized void setDistance(Double newValue) {
/* 107 */     checkWritePermission();
/* 108 */     this.distance = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\identification\ResolutionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */