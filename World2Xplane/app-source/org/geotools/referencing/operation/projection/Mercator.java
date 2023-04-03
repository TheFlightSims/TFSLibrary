/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ 
/*     */ public abstract class Mercator extends MapProjection {
/*     */   private static final long serialVersionUID = 6146741819833248649L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   protected final double standardParallel;
/*     */   
/*     */   protected Mercator(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  92 */     super(parameters);
/*  93 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/*  94 */     if (expected.contains(MapProjection.AbstractProvider.STANDARD_PARALLEL_1)) {
/* 101 */       this.standardParallel = Math.abs(doubleValue(expected, MapProjection.AbstractProvider.STANDARD_PARALLEL_1, parameters));
/* 102 */       ensureLatitudeInRange(MapProjection.AbstractProvider.STANDARD_PARALLEL_1, this.standardParallel, false);
/* 103 */       if (this.isSpherical) {
/* 104 */         this.scaleFactor *= Math.cos(this.standardParallel);
/*     */       } else {
/* 106 */         this.scaleFactor *= msfn(Math.sin(this.standardParallel), Math.cos(this.standardParallel));
/*     */       } 
/* 108 */       this.globalScale = this.scaleFactor * this.semiMajor;
/*     */     } else {
/* 111 */       this.standardParallel = Double.NaN;
/*     */     } 
/* 120 */     double sinPhi = Math.sin(this.latitudeOfOrigin);
/* 121 */     this.globalScale *= Math.cos(this.latitudeOfOrigin) / Math.sqrt(1.0D - this.excentricitySquared * sinPhi * sinPhi);
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 129 */     ParameterValueGroup values = super.getParameterValues();
/* 130 */     if (!Double.isNaN(this.standardParallel)) {
/* 131 */       Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 132 */       set(expected, MapProjection.AbstractProvider.STANDARD_PARALLEL_1, values, this.standardParallel);
/*     */     } 
/* 134 */     return values;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 145 */     if (Math.abs(y) > 1.5707953267948966D)
/* 146 */       throw new ProjectionException(y); 
/* 148 */     y = -Math.log(tsfn(y, Math.sin(y)));
/* 150 */     if (ptDst != null) {
/* 151 */       ptDst.setLocation(x, y);
/* 152 */       return ptDst;
/*     */     } 
/* 154 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 164 */     y = Math.exp(-y);
/* 165 */     y = cphi2(y);
/* 167 */     if (ptDst != null) {
/* 168 */       ptDst.setLocation(x, y);
/* 169 */       return ptDst;
/*     */     } 
/* 171 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   static abstract class Spherical extends Mercator {
/*     */     private static final long serialVersionUID = 2383414176395616561L;
/*     */     
/*     */     protected Spherical(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 195 */       super(parameters);
/* 196 */       ensureSpherical();
/*     */     }
/*     */     
/*     */     protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 208 */       if (Math.abs(y) > 1.5707953267948966D)
/* 209 */         throw new ProjectionException(y); 
/* 212 */       assert (ptDst = super.transformNormalized(x, y, ptDst)) != null;
/* 214 */       y = Math.log(Math.tan(0.7853981633974483D + 0.5D * y));
/* 216 */       assert checkTransform(x, y, ptDst);
/* 217 */       if (ptDst != null) {
/* 218 */         ptDst.setLocation(x, y);
/* 219 */         return ptDst;
/*     */       } 
/* 221 */       return new Point2D.Double(x, y);
/*     */     }
/*     */     
/*     */     protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 233 */       assert (ptDst = super.inverseTransformNormalized(x, y, ptDst)) != null;
/* 235 */       y = 1.5707963267948966D - 2.0D * Math.atan(Math.exp(-y));
/* 237 */       assert checkInverseTransform(x, y, ptDst);
/* 238 */       if (ptDst != null) {
/* 239 */         ptDst.setLocation(x, y);
/* 240 */         return ptDst;
/*     */       } 
/* 242 */       return new Point2D.Double(x, y);
/*     */     }
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 252 */     long code = Double.doubleToLongBits(this.standardParallel);
/* 253 */     return ((int)code ^ (int)(code >>> 32L)) + 37 * super.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 261 */     if (object == this)
/* 263 */       return true; 
/* 265 */     if (super.equals(object)) {
/* 266 */       Mercator that = (Mercator)object;
/* 267 */       return equals(this.standardParallel, that.standardParallel);
/*     */     } 
/* 269 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\Mercator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */