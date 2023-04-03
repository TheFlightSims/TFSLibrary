/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
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
/*     */ public class ObliqueStereographic extends StereographicUSGS {
/*     */   private static final long serialVersionUID = -1454098847621943639L;
/*     */   
/*     */   private static final double ITERATION_TOLERANCE = 1.0E-14D;
/*     */   
/*     */   private static final int MAXIMUM_ITERATIONS = 15;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   private final double C;
/*     */   
/*     */   private final double K;
/*     */   
/*     */   private final double ratexp;
/*     */   
/*     */   private final double phic0;
/*     */   
/*     */   private final double cosc0;
/*     */   
/*     */   private final double sinc0;
/*     */   
/*     */   private final double R2;
/*     */   
/*     */   protected ObliqueStereographic(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 114 */     this(parameters, Provider.PARAMETERS);
/*     */   }
/*     */   
/*     */   ObliqueStereographic(ParameterValueGroup parameters, ParameterDescriptorGroup descriptor) throws ParameterNotFoundException {
/* 128 */     super(parameters, descriptor);
/* 131 */     double sphi = Math.sin(this.latitudeOfOrigin);
/* 132 */     double cphi = Math.cos(this.latitudeOfOrigin);
/* 133 */     cphi *= cphi;
/* 134 */     this.R2 = 2.0D * Math.sqrt(1.0D - this.excentricitySquared) / (1.0D - this.excentricitySquared * sphi * sphi);
/* 135 */     this.C = Math.sqrt(1.0D + this.excentricitySquared * cphi * cphi / (1.0D - this.excentricitySquared));
/* 136 */     this.phic0 = Math.asin(sphi / this.C);
/* 137 */     this.sinc0 = Math.sin(this.phic0);
/* 138 */     this.cosc0 = Math.cos(this.phic0);
/* 139 */     this.ratexp = 0.5D * this.C * this.excentricity;
/* 140 */     this.K = Math.tan(0.5D * this.phic0 + 0.7853981633974483D) / Math.pow(Math.tan(0.5D * this.latitudeOfOrigin + 0.7853981633974483D), this.C) * srat(this.excentricity * sphi, this.ratexp);
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 154 */     assert (ptDst = super.transformNormalized(x, y, ptDst)) != null;
/* 156 */     y = 2.0D * Math.atan(this.K * Math.pow(Math.tan(0.5D * y + 0.7853981633974483D), this.C) * srat(this.excentricity * Math.sin(y), this.ratexp)) - 1.5707963267948966D;
/* 157 */     x *= this.C;
/* 158 */     double sinc = Math.sin(y);
/* 159 */     double cosc = Math.cos(y);
/* 160 */     double cosl = Math.cos(x);
/* 161 */     double k = this.R2 / (1.0D + this.sinc0 * sinc + this.cosc0 * cosc * cosl);
/* 162 */     x = k * cosc * Math.sin(x);
/* 163 */     y = k * (this.cosc0 * sinc - this.sinc0 * cosc * cosl);
/* 165 */     assert checkTransform(x, y, ptDst, 0.1D);
/* 166 */     if (ptDst != null) {
/* 167 */       ptDst.setLocation(x, y);
/* 168 */       return ptDst;
/*     */     } 
/* 170 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 182 */     assert (ptDst = super.inverseTransformNormalized(x, y, ptDst)) != null;
/* 183 */     double rho = Math.hypot(x, y);
/* 184 */     if (Math.abs(rho) < 1.0E-6D) {
/* 185 */       x = 0.0D;
/* 186 */       y = this.phic0;
/*     */     } else {
/* 188 */       double ce = 2.0D * Math.atan2(rho, this.R2);
/* 189 */       double sinc = Math.sin(ce);
/* 190 */       double cosc = Math.cos(ce);
/* 191 */       x = Math.atan2(x * sinc, rho * this.cosc0 * cosc - y * this.sinc0 * sinc);
/* 192 */       y = cosc * this.sinc0 + y * sinc * this.cosc0 / rho;
/* 194 */       if (Math.abs(y) >= 1.0D) {
/* 195 */         y = (y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */       } else {
/* 197 */         y = Math.asin(y);
/*     */       } 
/*     */     } 
/* 201 */     x /= this.C;
/* 202 */     double num = Math.pow(Math.tan(0.5D * y + 0.7853981633974483D) / this.K, 1.0D / this.C);
/* 203 */     int i = 15;
/*     */     while (true) {
/* 204 */       double phi = 2.0D * Math.atan(num * srat(this.excentricity * Math.sin(y), -0.5D * this.excentricity)) - 1.5707963267948966D;
/* 205 */       if (Math.abs(phi - y) < 1.0E-14D)
/*     */         break; 
/* 208 */       y = phi;
/* 209 */       if (--i < 0)
/* 210 */         throw new ProjectionException(129); 
/*     */     } 
/* 217 */     assert checkInverseTransform(x, y, ptDst, 0.01D);
/* 218 */     if (ptDst != null) {
/* 219 */       ptDst.setLocation(x, y);
/* 220 */       return ptDst;
/*     */     } 
/* 222 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   private static double srat(double esinp, double exp) {
/* 229 */     return Math.pow((1.0D - esinp) / (1.0D + esinp), exp);
/*     */   }
/*     */   
/*     */   public static final class Provider extends Stereographic.Provider {
/*     */     private static final long serialVersionUID = 6505988910141381354L;
/*     */     
/* 264 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Oblique_Stereographic"), new NamedIdentifier(Citations.EPSG, "Oblique Stereographic"), new NamedIdentifier(Citations.EPSG, "Roussilhe"), new NamedIdentifier(Citations.EPSG, "9809"), new NamedIdentifier(Citations.GEOTIFF, "CT_ObliqueStereographic"), new NamedIdentifier(Citations.ESRI, "Double_Stereographic"), new NamedIdentifier(Citations.GEOTOOLS, NAME) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 283 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     MathTransform createMathTransform(ParameterValueGroup parameters, ParameterDescriptorGroup descriptor) throws ParameterNotFoundException {
/* 294 */       return (MathTransform)new ObliqueStereographic(parameters, descriptor);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\ObliqueStereographic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */