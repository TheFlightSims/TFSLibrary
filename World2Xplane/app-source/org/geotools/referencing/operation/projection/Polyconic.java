/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.CylindricalProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class Polyconic extends MapProjection {
/*     */   private static final long serialVersionUID = 6516419168461705584L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-10D;
/*     */   
/*     */   private static final int MAXIMUM_ITERATIONS = 20;
/*     */   
/*     */   private static final double ITERATION_TOLERANCE = 1.0E-12D;
/*     */   
/*     */   private final double ml0;
/*     */   
/*     */   protected Polyconic(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 101 */     super(parameters);
/* 104 */     this.ml0 = mlfn(this.latitudeOfOrigin, Math.sin(this.latitudeOfOrigin), Math.cos(this.latitudeOfOrigin));
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 111 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double lam, double phi, Point2D ptDst) throws ProjectionException {
/*     */     double x;
/*     */     double y;
/* 123 */     if (Math.abs(phi) <= 1.0E-10D) {
/* 124 */       x = lam;
/* 125 */       y = -this.ml0;
/*     */     } else {
/* 127 */       double sp = Math.sin(phi);
/* 128 */       double cp, ms = (Math.abs(cp = Math.cos(phi)) > 1.0E-10D) ? (msfn(sp, cp) / sp) : 0.0D;
/* 129 */       lam *= sp;
/* 130 */       x = ms * Math.sin(lam);
/* 131 */       y = mlfn(phi, sp, cp) - this.ml0 + ms * (1.0D - Math.cos(lam));
/*     */     } 
/* 134 */     if (ptDst != null) {
/* 135 */       ptDst.setLocation(x, y);
/* 136 */       return ptDst;
/*     */     } 
/* 138 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/*     */     double lam, phi;
/* 150 */     y += this.ml0;
/* 151 */     if (Math.abs(y) <= 1.0E-10D) {
/* 152 */       lam = x;
/* 153 */       phi = 0.0D;
/*     */     } else {
/* 155 */       double r = y * y + x * x;
/* 156 */       phi = y;
/* 157 */       int i = 0;
/* 158 */       for (; i <= 20; i++) {
/* 159 */         double sp = Math.sin(phi);
/* 160 */         double cp = Math.cos(phi);
/* 161 */         if (Math.abs(cp) < 1.0E-12D)
/* 162 */           throw new ProjectionException(129); 
/* 164 */         double s2ph = sp * cp;
/* 165 */         double mlp = Math.sqrt(1.0D - this.excentricitySquared * sp * sp);
/* 166 */         double d1 = sp * mlp / cp;
/* 167 */         double ml = mlfn(phi, sp, cp);
/* 168 */         double mlb = ml * ml + r;
/* 169 */         mlp = (1.0D - this.excentricitySquared) / mlp * mlp * mlp;
/* 170 */         double dPhi = (ml + ml + d1 * mlb - 2.0D * y * (d1 * ml + 1.0D)) / (this.excentricitySquared * s2ph * (mlb - 2.0D * y * ml) / d1 + 2.0D * (y - ml) * (d1 * mlp - 1.0D / s2ph) - mlp - mlp);
/* 173 */         if (Math.abs(dPhi) <= 1.0E-12D)
/*     */           break; 
/* 176 */         phi += dPhi;
/*     */       } 
/* 178 */       if (i > 20)
/* 179 */         throw new ProjectionException(129); 
/* 180 */       double c = Math.sin(phi);
/* 181 */       lam = Math.asin(x * Math.tan(phi) * Math.sqrt(1.0D - this.excentricitySquared * c * c)) / Math.sin(phi);
/*     */     } 
/* 184 */     if (ptDst != null) {
/* 185 */       ptDst.setLocation(lam, phi);
/* 186 */       return ptDst;
/*     */     } 
/* 188 */     return new Point2D.Double(lam, phi);
/*     */   }
/*     */   
/*     */   protected double getToleranceForAssertions(double longitude, double latitude) {
/* 193 */     if (Math.abs(longitude - this.centralMeridian) / 2.0D + Math.abs(latitude - this.latitudeOfOrigin) > 10.0D)
/* 195 */       return 0.1D; 
/* 197 */     return super.getToleranceForAssertions(longitude, latitude);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 205 */     long code = Double.doubleToLongBits(this.ml0);
/* 206 */     return ((int)code ^ (int)(code >>> 32L)) + 37 * super.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 214 */     if (object == this)
/* 216 */       return true; 
/* 218 */     if (super.equals(object)) {
/* 219 */       Polyconic that = (Polyconic)object;
/* 220 */       return equals(this.ml0, that.ml0);
/*     */     } 
/* 222 */     return false;
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 3082828148070128422L;
/*     */     
/* 253 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Polyconic"), new NamedIdentifier(Citations.EPSG, "American Polyconic"), new NamedIdentifier(Citations.EPSG, "9818"), new NamedIdentifier(Citations.GEOTIFF, "Polyconic"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(251)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, LATITUDE_OF_ORIGIN, CENTRAL_MERIDIAN, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 270 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<CylindricalProjection> getOperationType() {
/* 278 */       return CylindricalProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 291 */       if (isSpherical(parameters))
/* 292 */         MapProjection.LOGGER.log(Level.WARNING, "Polyconic spherical case not implemented, falling back on the elliptical equations"); 
/* 293 */       return (MathTransform)new Polyconic(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\Polyconic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */