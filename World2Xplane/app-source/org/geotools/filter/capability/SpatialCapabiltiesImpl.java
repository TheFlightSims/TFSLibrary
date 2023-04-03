/*     */ package org.geotools.filter.capability;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.opengis.filter.capability.GeometryOperand;
/*     */ import org.opengis.filter.capability.SpatialCapabilities;
/*     */ import org.opengis.filter.capability.SpatialOperators;
/*     */ 
/*     */ public class SpatialCapabiltiesImpl implements SpatialCapabilities {
/*     */   Set<GeometryOperand> geometryOperands;
/*     */   
/*     */   SpatialOperatorsImpl spatialOperators;
/*     */   
/*     */   public SpatialCapabiltiesImpl() {
/*  48 */     this.geometryOperands = new HashSet<GeometryOperand>();
/*  49 */     this.spatialOperators = new SpatialOperatorsImpl();
/*     */   }
/*     */   
/*     */   public SpatialCapabiltiesImpl(Collection<GeometryOperand> geometryOperands, SpatialOperators spatialOperators) {
/*  53 */     this.geometryOperands = new HashSet<GeometryOperand>();
/*  54 */     if (geometryOperands != null)
/*  55 */       this.geometryOperands.addAll(geometryOperands); 
/*  57 */     this.spatialOperators = toSpatialOperatorsImpl(spatialOperators);
/*     */   }
/*     */   
/*     */   public SpatialCapabiltiesImpl(GeometryOperand[] geometryOperands, SpatialOperators spatialOperators) {
/*  61 */     this.geometryOperands = new HashSet<GeometryOperand>();
/*  62 */     if (geometryOperands != null)
/*  63 */       this.geometryOperands.addAll(Arrays.asList(geometryOperands)); 
/*  65 */     this.spatialOperators = toSpatialOperatorsImpl(spatialOperators);
/*     */   }
/*     */   
/*     */   public SpatialCapabiltiesImpl(SpatialCapabilities copy) {
/*  68 */     this.spatialOperators = new SpatialOperatorsImpl();
/*  69 */     this.geometryOperands = new HashSet<GeometryOperand>();
/*  70 */     if (copy.getGeometryOperands() != null)
/*  71 */       this.geometryOperands.addAll(copy.getGeometryOperands()); 
/*     */   }
/*     */   
/*     */   private static SpatialOperatorsImpl toSpatialOperatorsImpl(SpatialOperators spatialOperators) {
/*  76 */     if (spatialOperators == null)
/*  77 */       return new SpatialOperatorsImpl(); 
/*  79 */     if (spatialOperators instanceof SpatialOperatorsImpl)
/*  80 */       return (SpatialOperatorsImpl)spatialOperators; 
/*  82 */     return new SpatialOperatorsImpl(spatialOperators);
/*     */   }
/*     */   
/*     */   public void setGeometryOperands(Collection<GeometryOperand> geometryOperands) {
/*  86 */     this.geometryOperands = new HashSet<GeometryOperand>(geometryOperands);
/*     */   }
/*     */   
/*     */   public Collection<GeometryOperand> getGeometryOperands() {
/*  89 */     return this.geometryOperands;
/*     */   }
/*     */   
/*     */   public void setSpatialOperators(SpatialOperatorsImpl spatialOperators) {
/*  92 */     this.spatialOperators = spatialOperators;
/*     */   }
/*     */   
/*     */   public SpatialOperatorsImpl getSpatialOperators() {
/*  95 */     if (this.spatialOperators == null)
/*  96 */       this.spatialOperators = new SpatialOperatorsImpl(); 
/*  98 */     return this.spatialOperators;
/*     */   }
/*     */   
/*     */   public void addAll(SpatialCapabilities copy) {
/* 107 */     if (copy.getGeometryOperands() != null)
/* 108 */       this.geometryOperands.addAll(copy.getGeometryOperands()); 
/* 110 */     this.spatialOperators.addAll(copy.getSpatialOperators());
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 114 */     int prime = 31;
/* 115 */     int result = 1;
/* 116 */     result = 31 * result + ((this.geometryOperands == null) ? 0 : this.geometryOperands.hashCode());
/* 117 */     result = 31 * result + ((this.spatialOperators == null) ? 0 : this.spatialOperators.hashCode());
/* 118 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 122 */     if (this == obj)
/* 123 */       return true; 
/* 124 */     if (obj == null)
/* 125 */       return false; 
/* 126 */     if (getClass() != obj.getClass())
/* 127 */       return false; 
/* 128 */     SpatialCapabiltiesImpl other = (SpatialCapabiltiesImpl)obj;
/* 129 */     if (this.geometryOperands == null) {
/* 130 */       if (other.geometryOperands != null)
/* 131 */         return false; 
/* 132 */     } else if (!this.geometryOperands.equals(other.geometryOperands)) {
/* 133 */       return false;
/*     */     } 
/* 134 */     if (this.spatialOperators == null) {
/* 135 */       if (other.spatialOperators != null)
/* 136 */         return false; 
/* 137 */     } else if (!this.spatialOperators.equals(other.spatialOperators)) {
/* 138 */       return false;
/*     */     } 
/* 139 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\SpatialCapabiltiesImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */