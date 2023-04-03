/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
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
/*     */ public class WorldVanDerGrintenI extends MapProjection {
/*     */   private static final long serialVersionUID = -4432651736803211463L;
/*     */   
/*     */   private static final double TOL = 1.0E-10D;
/*     */   
/*     */   private static final double THIRD = 0.3333333333333333D;
/*     */   
/*     */   private static final double TWO_THRD = 0.6666666666666666D;
/*     */   
/*     */   private static final double C2_27 = 0.07407407407407407D;
/*     */   
/*     */   private static final double PI4_3 = 4.188790204786391D;
/*     */   
/*     */   private static final double PISQ = 9.869604401089358D;
/*     */   
/*     */   private static final double TPISQ = 19.739208802178716D;
/*     */   
/*     */   private static final double HPISQ = 4.934802200544679D;
/*     */   
/*     */   public WorldVanDerGrintenI(ParameterValueGroup values) throws ParameterNotFoundException {
/*  48 */     super(values);
/*     */   }
/*     */   
/*     */   public WorldVanDerGrintenI(ParameterValueGroup values, Collection<GeneralParameterDescriptor> expected) throws ParameterNotFoundException {
/*  54 */     super(values, expected);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/*  61 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double lambda, double phi, Point2D ptDst) throws ProjectionException {
/*  74 */     double x, y, p2 = Math.abs(phi / 1.5707963267948966D);
/*  75 */     if (p2 - 1.0E-10D > 1.0D)
/*  76 */       throw new ProjectionException(); 
/*  77 */     if (p2 > 1.0D)
/*  78 */       p2 = 1.0D; 
/*  79 */     if (Math.abs(phi) <= 1.0E-10D) {
/*  80 */       x = lambda;
/*  81 */       y = 0.0D;
/*  82 */     } else if (Math.abs(lambda) <= 1.0E-10D || Math.abs(p2 - 1.0D) < 1.0E-10D) {
/*  83 */       x = 0.0D;
/*  84 */       y = Math.PI * Math.tan(0.5D * Math.asin(p2));
/*  85 */       if (phi < 0.0D)
/*  86 */         y = -y; 
/*     */     } else {
/*  89 */       double al = 0.5D * Math.abs(Math.PI / lambda - lambda / Math.PI);
/*  90 */       double al2 = al * al;
/*  91 */       double g = Math.sqrt(1.0D - p2 * p2);
/*  92 */       g /= p2 + g - 1.0D;
/*  93 */       double g2 = g * g;
/*  94 */       p2 = g * (2.0D / p2 - 1.0D);
/*  95 */       p2 *= p2;
/*  96 */       x = g - p2;
/*  97 */       g = p2 + al2;
/*  98 */       x = Math.PI * (al * x + Math.sqrt(al2 * x * x - g * (g2 - p2))) / g;
/*  99 */       if (lambda < 0.0D)
/* 100 */         x = -x; 
/* 101 */       y = Math.abs(x / Math.PI);
/* 102 */       y = 1.0D - y * (y + 2.0D * al);
/* 103 */       if (y < -1.0E-10D)
/* 104 */         throw new ProjectionException(); 
/* 105 */       if (y < 0.0D) {
/* 106 */         y = 0.0D;
/*     */       } else {
/* 108 */         y = Math.sqrt(y) * ((phi < 0.0D) ? -3.141592653589793D : Math.PI);
/*     */       } 
/*     */     } 
/* 111 */     if (ptDst != null) {
/* 112 */       ptDst.setLocation(x, y);
/* 113 */       return ptDst;
/*     */     } 
/* 115 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 127 */     double lambda, phi, x2 = x * x;
/*     */     double ay;
/* 128 */     if ((ay = Math.abs(y)) < 1.0E-10D) {
/* 129 */       phi = 0.0D;
/* 130 */       double d1 = x2 * x2 + 19.739208802178716D * (x2 + 4.934802200544679D);
/* 131 */       lambda = (Math.abs(x) <= 1.0E-10D) ? 0.0D : (0.5D * (x2 - 9.869604401089358D + Math.sqrt(d1)) / x);
/* 133 */       if (ptDst != null) {
/* 134 */         ptDst.setLocation(lambda, phi);
/* 135 */         return ptDst;
/*     */       } 
/* 137 */       return new Point2D.Double(lambda, phi);
/*     */     } 
/* 140 */     double y2 = y * y;
/* 141 */     double r = x2 + y2, r2 = r * r;
/* 142 */     double c1 = -3.141592653589793D * ay * (r + 9.869604401089358D);
/* 143 */     double c3 = r2 + 6.283185307179586D * (ay * r + Math.PI * (y2 + Math.PI * (ay + 1.5707963267948966D)));
/* 144 */     double c2 = c1 + 9.869604401089358D * (r - 3.0D * y2);
/* 145 */     double c0 = Math.PI * ay;
/* 146 */     c2 /= c3;
/* 147 */     double al = c1 / c3 - 0.3333333333333333D * c2 * c2;
/* 148 */     double m = 2.0D * Math.sqrt(-0.3333333333333333D * al);
/* 149 */     double d = 0.07407407407407407D * c2 * c2 * c2 + (c0 * c0 - 0.3333333333333333D * c2 * c1) / c3;
/*     */     double t;
/* 151 */     if ((t = Math.abs(d = 3.0D * d / al * m)) - 1.0E-10D <= 1.0D) {
/* 152 */       d = (t > 1.0D) ? ((d > 0.0D) ? 0.0D : Math.PI) : Math.acos(d);
/* 153 */       phi = Math.PI * (m * Math.cos(d * 0.3333333333333333D + 4.188790204786391D) - 0.3333333333333333D * c2);
/* 154 */       if (y < 0.0D)
/* 155 */         phi = -phi; 
/* 156 */       t = r2 + 19.739208802178716D * (x2 - y2 + 4.934802200544679D);
/* 157 */       lambda = (Math.abs(x) <= 1.0E-10D) ? 0.0D : (0.5D * (r - 9.869604401089358D + ((t <= 0.0D) ? 0.0D : Math.sqrt(t))) / x);
/*     */     } else {
/* 160 */       throw new ProjectionException();
/*     */     } 
/* 162 */     if (ptDst != null) {
/* 163 */       ptDst.setLocation(lambda, phi);
/* 164 */       return ptDst;
/*     */     } 
/* 166 */     return new Point2D.Double(lambda, phi);
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = -4432651736803211463L;
/*     */     
/* 178 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { 
/* 178 */           new NamedIdentifier(Citations.OGC, "World_Van_der_Grinten_I"), new NamedIdentifier(Citations.GEOTOOLS, "World_Van_der_Grinten_I"), new NamedIdentifier(Citations.ESRI, "World_Van_der_Grinten_I"), new NamedIdentifier(Citations.GEOTIFF, "World_Van_der_Grinten_I"), new NamedIdentifier(Citations.OGC, "Van_der_Grinten_I"), new NamedIdentifier(Citations.GEOTOOLS, "Van_der_Grinten_I"), new NamedIdentifier(Citations.GEOTIFF, "Van_der_Grinten_I"), new NamedIdentifier(Citations.ESRI, "Van_der_Grinten_I"), new NamedIdentifier(Citations.OGC, "CT_VanDerGrinten"), new NamedIdentifier(Citations.GEOTOOLS, "CT_VanDerGrinten"), 
/* 178 */           new NamedIdentifier(Citations.GEOTIFF, "CT_VanDerGrinten"), new NamedIdentifier(Citations.ESRI, "CT_VanDerGrinten"), new NamedIdentifier(Citations.EPSG, "54029"), new NamedIdentifier(Citations.ESRI, "54029"), new NamedIdentifier(Citations.OGC, "Van der Grinten WGS84"), new NamedIdentifier(Citations.GEOTOOLS, "Van der Grinten WGS84"), new NamedIdentifier(Citations.GEOTIFF, "Van der Grinten WGS84"), new NamedIdentifier(Citations.ESRI, "Van der Grinten WGS84"), new NamedIdentifier(Citations.EPSG, "Van der Grinten WGS84"), new NamedIdentifier(Citations.OGC, "Van der Grinten"), 
/* 178 */           new NamedIdentifier(Citations.GEOTOOLS, "Van der Grinten"), new NamedIdentifier(Citations.GEOTIFF, "Van der Grinten"), new NamedIdentifier(Citations.ESRI, "Van der Grinten"), new NamedIdentifier(Citations.EPSG, "Van der Grinten") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 211 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 224 */       return (MathTransform)new WorldVanDerGrintenI(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\WorldVanDerGrintenI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */