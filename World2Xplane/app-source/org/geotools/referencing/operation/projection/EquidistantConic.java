/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import org.geotools.measure.Latitude;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.PlanarProjection;
/*     */ 
/*     */ public class EquidistantConic extends MapProjection {
/*     */   private static final long serialVersionUID = 5885522933843653157L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   private final double rho0;
/*     */   
/*     */   private final double n;
/*     */   
/*     */   private final double c;
/*     */   
/*     */   private final double phi1;
/*     */   
/*     */   private double phi2;
/*     */   
/*     */   protected EquidistantConic(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 103 */     super(parameters);
/* 109 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 110 */     this.phi1 = doubleValue(expected, Provider.STANDARD_PARALLEL_1, parameters);
/* 111 */     ensureLatitudeInRange(Provider.STANDARD_PARALLEL_1, this.phi1, true);
/* 112 */     this.phi2 = doubleValue(expected, Provider.STANDARD_PARALLEL_2, parameters);
/* 113 */     if (Double.isNaN(this.phi2))
/* 114 */       this.phi2 = this.phi1; 
/* 116 */     ensureLatitudeInRange(Provider.STANDARD_PARALLEL_2, this.phi2, true);
/* 119 */     if (Math.abs(this.phi1 + this.phi2) < 1.0E-6D)
/* 120 */       throw new IllegalArgumentException(Errors.format(5, new Latitude(Math.toDegrees(this.phi1)), new Latitude(Math.toDegrees(this.phi2)))); 
/* 124 */     double n = Math.sin(this.phi1), sinphi = n;
/* 125 */     double cosphi = Math.cos(this.phi1);
/* 126 */     boolean secant = (Math.abs(this.phi1 - this.phi2) >= 1.0E-6D);
/* 129 */     if (secant)
/* 130 */       n = (cosphi - Math.cos(this.phi2)) / (this.phi2 - this.phi1); 
/* 132 */     this.c = this.phi1 + Math.cos(this.phi1) / n;
/* 133 */     this.rho0 = this.c - this.latitudeOfOrigin;
/* 134 */     this.en0 = this.en1 = this.en2 = this.en3 = this.en4 = 0.0D;
/* 137 */     double m1 = msfn(sinphi, cosphi);
/* 139 */     double ml1 = mlfn(this.phi1, sinphi, cosphi);
/* 140 */     if (secant) {
/* 141 */       sinphi = Math.sin(this.phi2);
/* 142 */       cosphi = Math.cos(this.phi2);
/* 143 */       n = (m1 - msfn(sinphi, cosphi)) / (mlfn(this.phi2, sinphi, cosphi) - ml1);
/*     */     } 
/* 146 */     this.c = ml1 + m1 / n;
/* 147 */     this.rho0 = this.c - mlfn(this.latitudeOfOrigin, Math.sin(this.latitudeOfOrigin), Math.cos(this.latitudeOfOrigin));
/* 149 */     this.n = n;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 156 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 164 */     ParameterValueGroup values = super.getParameterValues();
/* 165 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 166 */     set(expected, Provider.STANDARD_PARALLEL_1, values, this.phi1);
/* 167 */     set(expected, Provider.STANDARD_PARALLEL_2, values, this.phi2);
/* 168 */     return values;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 176 */     if (object == this)
/* 178 */       return true; 
/* 181 */     return super.equals(object);
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 192 */     double cosphi = Math.cos(y);
/* 193 */     double sinphi = Math.sin(y);
/* 194 */     double rho = this.c - (this.isSpherical ? y : mlfn(y, sinphi, cosphi));
/* 198 */     double x1 = rho * Math.sin(x *= this.n);
/* 199 */     double y1 = this.rho0 - rho * Math.cos(x);
/* 201 */     if (ptDst != null) {
/* 202 */       ptDst.setLocation(x1, y1);
/* 203 */       return ptDst;
/*     */     } 
/* 205 */     return new Point2D.Double(x1, y1);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 215 */     double phi, lam, rho = Math.hypot(x, y = this.rho0 - y);
/* 218 */     if (rho != 0.0D) {
/* 219 */       if (this.n < 0.0D) {
/* 220 */         rho = -rho;
/* 221 */         x = -x;
/* 222 */         y = -y;
/*     */       } 
/* 224 */       phi = this.c - rho;
/* 225 */       if (!this.isSpherical)
/* 226 */         phi = inv_mlfn(phi); 
/* 228 */       lam = Math.atan2(x, y) / this.n;
/*     */     } else {
/* 230 */       lam = 0.0D;
/* 231 */       phi = (this.n > 0.0D) ? 1.5707963267948966D : -1.5707963267948966D;
/*     */     } 
/* 234 */     if (ptDst != null) {
/* 235 */       ptDst.setLocation(lam, phi);
/* 236 */       return ptDst;
/*     */     } 
/* 238 */     return new Point2D.Double(lam, phi);
/*     */   }
/*     */   
/*     */   public static final class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 1995516958029802849L;
/*     */     
/* 268 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.GEOTIFF, "CT_Equidistant_Conic"), new NamedIdentifier(Citations.ESRI, "Equidistant_Conic"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(250)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, STANDARD_PARALLEL_1, STANDARD_PARALLEL_2, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 284 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<PlanarProjection> getOperationType() {
/* 292 */       return PlanarProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException, FactoryException {
/* 305 */       return (MathTransform)new EquidistantConic(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\EquidistantConic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */