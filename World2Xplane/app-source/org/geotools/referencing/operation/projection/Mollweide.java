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
/*     */ public class Mollweide extends MapProjection {
/*     */   private static final long serialVersionUID = -737778661392950540L;
/*     */   
/*     */   private static final int MAX_ITER = 10;
/*     */   
/*     */   private static final double LOOP_TOL = 1.0E-7D;
/*     */   
/*     */   double C_x;
/*     */   
/*     */   double C_y;
/*     */   
/*     */   double C_p;
/*     */   
/*     */   ParameterDescriptorGroup descriptors;
/*     */   
/*     */   enum ProjectionMode {
/*  62 */     Mollweide, WagnerIV, WagnerV;
/*     */   }
/*     */   
/*     */   protected Mollweide(ProjectionMode mode, ParameterDescriptorGroup descriptors, ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  75 */     super(parameters, descriptors.descriptors());
/*  76 */     this.descriptors = descriptors;
/*  78 */     if (mode == ProjectionMode.WagnerV) {
/*  79 */       this.C_x = 0.90977D;
/*  80 */       this.C_y = 1.65014D;
/*  81 */       this.C_p = 3.00896D;
/*     */     } else {
/*     */       double p;
/*  84 */       if (mode == ProjectionMode.Mollweide) {
/*  85 */         p = 1.5707963267948966D;
/*     */       } else {
/*  87 */         p = 1.0471975511965976D;
/*     */       } 
/*  89 */       double p2 = p + p;
/*  90 */       double sp = Math.sin(p);
/*  91 */       double r = Math.sqrt(6.283185307179586D * sp / (p2 + Math.sin(p2)));
/*  92 */       this.C_x = 2.0D * r / Math.PI;
/*  93 */       this.C_y = r / sp;
/*  94 */       this.C_p = p2 + Math.sin(p2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 103 */     return this.descriptors;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double lam, double phi, Point2D ptDst) throws ProjectionException {
/* 113 */     double k = this.C_p * Math.sin(phi);
/* 114 */     int i = 10;
/* 115 */     for (; i > 0; i--) {
/* 116 */       double V = (phi + Math.sin(phi) - k) / (1.0D + Math.cos(phi));
/* 117 */       phi -= V;
/* 118 */       if (Math.abs(V) < 1.0E-7D)
/*     */         break; 
/*     */     } 
/* 121 */     if (i == 0) {
/* 122 */       phi = (phi < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */     } else {
/* 124 */       phi *= 0.5D;
/*     */     } 
/* 127 */     if (ptDst == null)
/* 128 */       ptDst = new Point2D.Double(); 
/* 130 */     ptDst.setLocation(this.C_x * lam * Math.cos(phi), this.C_y * Math.sin(phi));
/* 131 */     return ptDst;
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 141 */     double phi = aasin(y / this.C_y);
/* 142 */     double lam = x / this.C_x * Math.cos(phi);
/* 143 */     phi += phi;
/* 144 */     phi = aasin((phi + Math.sin(phi)) / this.C_p);
/* 147 */     lam = rollLongitude(lam);
/* 149 */     if (ptDst == null)
/* 150 */       ptDst = new Point2D.Double(); 
/* 152 */     ptDst.setLocation(lam, phi);
/* 154 */     return ptDst;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 164 */     if (object == this)
/* 166 */       return true; 
/* 168 */     return super.equals(object);
/*     */   }
/*     */   
/*     */   protected double getToleranceForAssertions(double longitude, double latitude) {
/* 174 */     return 2.0D;
/*     */   }
/*     */   
/*     */   public static class MollweideProvider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = -2616680275771881688L;
/*     */     
/* 204 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.GEOTOOLS, "Mollweide"), new NamedIdentifier(Citations.ESRI, "Mollweide") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN });
/*     */     
/*     */     public MollweideProvider() {
/* 213 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 227 */       parameters.parameter("semi_minor").setValue(parameters.parameter("semi_major").getValue());
/* 228 */       return (MathTransform)new Mollweide(Mollweide.ProjectionMode.Mollweide, PARAMETERS, parameters);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WagnerIVProvider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 1079407274370647753L;
/*     */     
/* 250 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.GEOTOOLS, "Wagner_IV") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN });
/*     */     
/*     */     public WagnerIVProvider() {
/* 258 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 273 */       parameters.parameter("semi_minor").setValue(parameters.parameter("semi_major").getValue());
/* 274 */       return (MathTransform)new Mollweide(Mollweide.ProjectionMode.WagnerIV, PARAMETERS, parameters);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WagnerVProvider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = -3583284443974045930L;
/*     */     
/* 296 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.GEOTOOLS, "Wagner_V") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN });
/*     */     
/*     */     public WagnerVProvider() {
/* 305 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 320 */       parameters.parameter("semi_minor").setValue(parameters.parameter("semi_major").getValue());
/* 321 */       return (MathTransform)new Mollweide(Mollweide.ProjectionMode.WagnerV, PARAMETERS, parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\Mollweide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */