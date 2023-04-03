/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class CassiniSoldner extends MapProjection {
/*     */   private static final int MAXIMUM_ITERATIONS = 15;
/*     */   
/*     */   private final double ml0;
/*     */   
/*     */   private static final double C1 = 0.16666666666666666D;
/*     */   
/*     */   private static final double C2 = 0.08333333333333333D;
/*     */   
/*     */   private static final double C3 = 0.4166666666666667D;
/*     */   
/*     */   private static final double C4 = 0.3333333333333333D;
/*     */   
/*     */   private static final double C5 = 0.6666666666666666D;
/*     */   
/*     */   protected CassiniSoldner(ParameterValueGroup values) throws ParameterNotFoundException {
/*  76 */     super(values);
/*  77 */     this.ml0 = mlfn(this.latitudeOfOrigin, Math.sin(this.latitudeOfOrigin), Math.cos(this.latitudeOfOrigin));
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/*  84 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/*  92 */     double ph1 = inv_mlfn(this.ml0 + y);
/*  93 */     double tn = Math.tan(ph1);
/*  94 */     double t = tn * tn;
/*  95 */     double n = Math.sin(ph1);
/*  96 */     double r = 1.0D / (1.0D - this.excentricitySquared * n * n);
/*  97 */     n = Math.sqrt(r);
/*  98 */     r *= (1.0D - this.excentricitySquared) * n;
/*  99 */     double dd = x / n;
/* 100 */     double d2 = dd * dd;
/* 101 */     double phi = ph1 - n * tn / r * d2 * (0.5D - (1.0D + 3.0D * t) * d2 * 0.4166666666666667D);
/* 102 */     double lam = dd * (1.0D + t * d2 * (-0.3333333333333333D + (1.0D + 3.0D * t) * d2 * 0.6666666666666666D)) / Math.cos(ph1);
/* 103 */     if (ptDst != null) {
/* 104 */       ptDst.setLocation(lam, phi);
/* 105 */       return ptDst;
/*     */     } 
/* 107 */     return new Point2D.Double(lam, phi);
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double lam, double phi, Point2D ptDst) throws ProjectionException {
/* 116 */     double sinphi = Math.sin(phi);
/* 117 */     double cosphi = Math.cos(phi);
/* 119 */     double n = 1.0D / Math.sqrt(1.0D - this.excentricitySquared * sinphi * sinphi);
/* 120 */     double tn = Math.tan(phi);
/* 121 */     double t = tn * tn;
/* 122 */     double a1 = lam * cosphi;
/* 123 */     double c = cosphi * cosphi * this.excentricitySquared / (1.0D - this.excentricitySquared);
/* 124 */     double a2 = a1 * a1;
/* 126 */     double x = n * a1 * (1.0D - a2 * t * (0.16666666666666666D - (8.0D - t + 8.0D * c) * a2 * 0.08333333333333333D));
/* 127 */     double y = mlfn(phi, sinphi, cosphi) - this.ml0 + n * tn * a2 * (0.5D + (5.0D - t + 6.0D * c) * a2 * 0.4166666666666667D);
/* 129 */     if (ptDst != null) {
/* 130 */       ptDst.setLocation(x, y);
/* 131 */       return ptDst;
/*     */     } 
/* 133 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   private static final class Spherical extends CassiniSoldner {
/*     */     protected Spherical(ParameterValueGroup values) throws ParameterNotFoundException {
/* 145 */       super(values);
/* 146 */       assert this.isSpherical;
/*     */     }
/*     */     
/*     */     protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 155 */       double x1 = Math.asin(Math.cos(y) * Math.sin(x));
/* 156 */       double y1 = Math.atan2(Math.tan(y), Math.cos(x)) - this.latitudeOfOrigin;
/* 157 */       if (ptDst != null) {
/* 158 */         ptDst.setLocation(x1, y1);
/* 159 */         return ptDst;
/*     */       } 
/* 161 */       return new Point2D.Double(x1, y1);
/*     */     }
/*     */     
/*     */     protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 170 */       double dd = y + this.latitudeOfOrigin;
/* 171 */       double phi = Math.asin(Math.sin(dd) * Math.cos(x));
/* 172 */       double lam = Math.atan2(Math.tan(x), Math.cos(dd));
/* 173 */       if (ptDst != null) {
/* 174 */         ptDst.setLocation(lam, phi);
/* 175 */         return ptDst;
/*     */       } 
/* 177 */       return new Point2D.Double(lam, phi);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     static ParameterDescriptorGroup createDescriptorGroup(ReferenceIdentifier[] identifiers) {
/* 201 */       return createDescriptorGroup(identifiers, (GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     }
/*     */     
/* 212 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Cassini_Soldner"), new NamedIdentifier(Citations.EPSG, "Cassini-Soldner"), new NamedIdentifier(Citations.EPSG, "9806"), new NamedIdentifier(Citations.GEOTIFF, "CT_CassiniSoldner"), new NamedIdentifier(Citations.ESRI, "Cassini_Soldner"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(249)) });
/*     */     
/*     */     public Provider() {
/* 226 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     Provider(ParameterDescriptorGroup descriptor) {
/* 233 */       super(descriptor);
/*     */     }
/*     */     
/*     */     public MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 248 */       if (isSpherical(parameters))
/* 249 */         return (MathTransform)new CassiniSoldner.Spherical(parameters); 
/* 251 */       return (MathTransform)new CassiniSoldner(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\CassiniSoldner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */