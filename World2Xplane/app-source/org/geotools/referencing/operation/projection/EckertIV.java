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
/*     */ public class EckertIV extends MapProjection {
/*     */   private static final long serialVersionUID = 1635471013603509976L;
/*     */   
/*     */   private static final double C_x = 0.4222382003157712D;
/*     */   
/*     */   private static final double C_y = 1.3265004281770023D;
/*     */   
/*     */   private static final double C_p = 3.5707963267948966D;
/*     */   
/*     */   private static final double RC_p = 0.2800495767557787D;
/*     */   
/*     */   private static final double EPS = 1.0E-7D;
/*     */   
/*     */   private static final int NITER = 6;
/*     */   
/*     */   protected EckertIV(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  80 */     super(parameters);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/*  87 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double lam, double phi, Point2D ptDst) throws ProjectionException {
/*  97 */     double p = 3.5707963267948966D * Math.sin(phi);
/*  98 */     double V = phi * phi;
/*  99 */     phi *= 0.895168D + V * (0.0218849D + V * 0.00826809D);
/* 100 */     int i = 6;
/* 102 */     double c = Math.cos(phi);
/* 103 */     double s = Math.sin(phi);
/* 104 */     phi -= V = (phi + s * (c + 2.0D) - p) / (1.0D + c * (c + 2.0D) - s * s);
/* 105 */     for (; i > 0 && Math.abs(V) >= 1.0E-7D; i--);
/* 109 */     if (ptDst == null)
/* 110 */       ptDst = new Point2D.Double(); 
/* 112 */     if (i == 0) {
/* 113 */       ptDst.setLocation(0.4222382003157712D * lam, (phi < 0.0D) ? -1.3265004281770023D : 1.3265004281770023D);
/*     */     } else {
/* 115 */       ptDst.setLocation(0.4222382003157712D * lam * (1.0D + Math.cos(phi)), 1.3265004281770023D * Math.sin(phi));
/*     */     } 
/* 117 */     return ptDst;
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 127 */     double phi = aasin(y / 1.3265004281770023D);
/* 128 */     double c = Math.cos(phi);
/* 129 */     double lam = x / 0.4222382003157712D * (1.0D + c);
/* 130 */     phi = aasin((phi + Math.sin(phi) * (c + 2.0D)) / 3.5707963267948966D);
/* 132 */     if (ptDst == null)
/* 133 */       ptDst = new Point2D.Double(); 
/* 135 */     ptDst.setLocation(lam, phi);
/* 137 */     return ptDst;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 147 */     if (object == this)
/* 149 */       return true; 
/* 151 */     return super.equals(object);
/*     */   }
/*     */   
/*     */   protected double getToleranceForAssertions(double longitude, double latitude) {
/* 157 */     return 2.0D;
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 1136453952351519284L;
/*     */     
/* 186 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.GEOTOOLS, "Eckert_IV"), new NamedIdentifier(Citations.ESRI, "Eckert_IV") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN });
/*     */     
/*     */     public Provider() {
/* 195 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 209 */       parameters.parameter("semi_minor").setValue(parameters.parameter("semi_major").getValue());
/* 210 */       return (MathTransform)new EckertIV(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\EckertIV.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */