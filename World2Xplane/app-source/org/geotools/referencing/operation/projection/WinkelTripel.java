/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import javax.measure.unit.NonSI;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class WinkelTripel extends MapProjection {
/*     */   private static final long serialVersionUID = -8643765000703074857L;
/*     */   
/*     */   private final double cosphi1;
/*     */   
/*     */   private final ProjectionMode mode;
/*     */   
/*     */   private ParameterDescriptorGroup descriptors;
/*     */   
/*     */   private enum ProjectionMode {
/*  64 */     Winkel, Aitoff;
/*     */   }
/*     */   
/*     */   protected WinkelTripel(ProjectionMode mode, ParameterDescriptorGroup descriptors, ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  83 */     super(parameters, descriptors.descriptors());
/*  84 */     this.descriptors = descriptors;
/*  85 */     this.invertible = false;
/*  88 */     if (mode == ProjectionMode.Winkel) {
/*  89 */       Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/*  90 */       double phi1 = doubleValue(expected, WinkelProvider.STANDARD_PARALLEL_1, parameters);
/*  91 */       this.cosphi1 = Math.cos(phi1);
/*     */     } else {
/*  93 */       this.cosphi1 = 0.0D;
/*     */     } 
/*  95 */     this.mode = mode;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 102 */     return this.descriptors;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double lam, double phi, Point2D ptDst) throws ProjectionException {
/* 116 */     double c, d, y, x = 2.0D * d * Math.cos(phi) * Math.sin(c) * (y = 1.0D / Math.sin(d));
/* 117 */     y *= d * Math.sin(phi);
/* 119 */     x = y = 0.0D;
/* 122 */     if (this.mode == ProjectionMode.Winkel) {
/* 123 */       x = (x + lam * this.cosphi1) * 0.5D;
/* 124 */       y = (y + phi) * 0.5D;
/*     */     } 
/* 127 */     if (ptDst != null) {
/* 128 */       ptDst.setLocation(x, y);
/* 129 */       return ptDst;
/*     */     } 
/* 131 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 138 */     throw new UnsupportedOperationException("Cannot invert this transformation");
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 146 */     long code = Double.doubleToLongBits(this.cosphi1);
/* 147 */     return ((int)code ^ (int)(code >>> 32L)) + 37 * super.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 155 */     if (object == this)
/* 157 */       return true; 
/* 159 */     if (super.equals(object)) {
/* 160 */       WinkelTripel that = (WinkelTripel)object;
/* 161 */       return equals(this.cosphi1, that.cosphi1);
/*     */     } 
/* 163 */     return false;
/*     */   }
/*     */   
/*     */   public static class WinkelProvider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = -2484567298319140781L;
/*     */     
/* 194 */     public static final ParameterDescriptor STANDARD_PARALLEL_1 = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "standard_parallel_1"), new NamedIdentifier(Citations.EPSG, "Latitude of 1st standard parallel"), new NamedIdentifier(Citations.GEOTIFF, "StdParallel1") }Math.toDegrees(0.880689235D), -90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 205 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Winkel_Tripel"), new NamedIdentifier(Citations.GEOTOOLS, "Winkel Tripel") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, STANDARD_PARALLEL_1 });
/*     */     
/*     */     public WinkelProvider() {
/* 216 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 229 */       return (MathTransform)new WinkelTripel(WinkelTripel.ProjectionMode.Winkel, PARAMETERS, parameters);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class AitoffProvider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 1189973109778926762L;
/*     */     
/* 251 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Aitoff"), new NamedIdentifier(Citations.GEOTOOLS, "Aitoff") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR });
/*     */     
/*     */     public AitoffProvider() {
/* 262 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 275 */       return (MathTransform)new WinkelTripel(WinkelTripel.ProjectionMode.Aitoff, PARAMETERS, parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\WinkelTripel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */