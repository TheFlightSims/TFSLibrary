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
/*     */ public class Robinson extends MapProjection {
/*     */   private static final long serialVersionUID = 8428056162968814860L;
/*     */   
/*     */   static final class Coeff {
/*     */     double c0;
/*     */     
/*     */     double c1;
/*     */     
/*     */     double c2;
/*     */     
/*     */     double c3;
/*     */     
/*     */     public Coeff(double c0, double c1, double c2, double c3) {
/*  59 */       this.c0 = c0;
/*  60 */       this.c1 = c1;
/*  61 */       this.c2 = c2;
/*  62 */       this.c3 = c3;
/*     */     }
/*     */     
/*     */     public Coeff(Coeff other) {
/*  67 */       this.c0 = other.c0;
/*  68 */       this.c1 = other.c1;
/*  69 */       this.c2 = other.c2;
/*  70 */       this.c3 = other.c3;
/*     */     }
/*     */     
/*     */     public final double forward(double z) {
/*  74 */       return this.c0 + z * (this.c1 + z * (this.c2 + z * this.c3));
/*     */     }
/*     */     
/*     */     public final double inverse(double z) {
/*  78 */       return this.c1 + z * (this.c2 + this.c2 + z * 3.0D * this.c3);
/*     */     }
/*     */   }
/*     */   
/*  85 */   private static final Coeff[] X = new Coeff[] { 
/*  85 */       new Coeff(1.0D, -5.67239E-12D, -7.15511E-5D, 3.11028E-6D), new Coeff(0.9986D, -4.82241E-4D, -2.4897E-5D, -1.33094E-6D), new Coeff(0.9954D, -8.31031E-4D, -4.4861E-5D, -9.86588E-7D), new Coeff(0.99D, -0.00135363D, -5.96598E-5D, 3.67749E-6D), new Coeff(0.9822D, -0.00167442D, -4.4975E-6D, -5.72394E-6D), new Coeff(0.973D, -0.00214869D, -9.03565E-5D, 1.88767E-8D), new Coeff(0.96D, -0.00305084D, -9.00732E-5D, 1.64869E-6D), new Coeff(0.9427D, -0.00382792D, -6.53428E-5D, -2.61493E-6D), new Coeff(0.9216D, -0.00467747D, -1.04566E-4D, 4.8122E-6D), new Coeff(0.8962D, -0.00536222D, -3.23834E-5D, -5.43445E-6D), 
/*  85 */       new Coeff(0.8679D, -0.00609364D, -1.139E-4D, 3.32521E-6D), new Coeff(0.835D, -0.00698325D, -6.40219E-5D, 9.34582E-7D), new Coeff(0.7986D, -0.00755337D, -5.00038E-5D, 9.35532E-7D), new Coeff(0.7597D, -0.00798325D, -3.59716E-5D, -2.27604E-6D), new Coeff(0.7186D, -0.00851366D, -7.0112E-5D, -8.63072E-6D), new Coeff(0.6732D, -0.00986209D, -1.99572E-4D, 1.91978E-5D), new Coeff(0.6213D, -0.010418D, 8.83948E-5D, 6.24031E-6D), new Coeff(0.5722D, -0.00906601D, 1.81999E-4D, 6.24033E-6D), new Coeff(0.5322D, 0.0D, 0.0D, 0.0D) };
/*     */   
/* 110 */   private static final Coeff[] Y = new Coeff[] { 
/* 110 */       new Coeff(0.0D, 0.0124D, 3.72529E-10D, 1.15484E-9D), new Coeff(0.062D, 0.0124001D, 1.76951E-8D, -5.92321E-9D), new Coeff(0.124D, 0.0123998D, -7.09668E-8D, 2.25753E-8D), new Coeff(0.186D, 0.0124008D, 2.66917E-7D, -8.44523E-8D), new Coeff(0.248D, 0.0123971D, -9.99682E-7D, 3.15569E-7D), new Coeff(0.31D, 0.0124108D, 3.73349E-6D, -1.1779E-6D), new Coeff(0.372D, 0.0123598D, -1.3935E-5D, 4.39588E-6D), new Coeff(0.434D, 0.0125501D, 5.20034E-5D, -1.00051E-5D), new Coeff(0.4968D, 0.0123198D, -9.80735E-5D, 9.22397E-6D), new Coeff(0.5571D, 0.0120308D, 4.02857E-5D, -5.2901E-6D), 
/* 110 */       new Coeff(0.6176D, 0.0120369D, -3.90662E-5D, 7.36117E-7D), new Coeff(0.6769D, 0.0117015D, -2.80246E-5D, -8.54283E-7D), new Coeff(0.7346D, 0.0113572D, -4.08389E-5D, -5.18524E-7D), new Coeff(0.7903D, 0.0109099D, -4.86169E-5D, -1.0718E-6D), new Coeff(0.8435D, 0.0103433D, -6.46934E-5D, 5.36384E-9D), new Coeff(0.8936D, 0.00969679D, -6.46129E-5D, -8.54894E-6D), new Coeff(0.9394D, 0.00840949D, -1.92847E-4D, -4.21023E-6D), new Coeff(0.9761D, 0.00616525D, -2.56001E-4D, -4.21021E-6D), new Coeff(1.0D, 0.0D, 0.0D, 0.0D) };
/*     */   
/*     */   private static final double FXC = 0.8487D;
/*     */   
/*     */   private static final double FYC = 1.3523D;
/*     */   
/*     */   private static final double C1 = 11.459155902616464D;
/*     */   
/*     */   private static final double RC1 = 0.08726646259971647D;
/*     */   
/* 136 */   private final int NODES = 18;
/*     */   
/*     */   private static final double ONEEPS = 1.000001D;
/*     */   
/*     */   private static final double EPS = 1.0E-12D;
/*     */   
/*     */   protected Robinson(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 148 */     super(parameters);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 155 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double lam, double phi, Point2D ptDst) throws ProjectionException {
/* 166 */     double dphi = Math.abs(phi);
/* 167 */     int i = (int)Math.floor(dphi * 11.459155902616464D);
/* 168 */     if (i >= 18)
/* 169 */       i = 17; 
/* 171 */     dphi = Math.toDegrees(dphi - 0.08726646259971647D * i);
/* 172 */     double x = X[i].forward(dphi) * 0.8487D * lam;
/* 173 */     double y = Y[i].forward(dphi) * 1.3523D;
/* 174 */     if (phi < 0.0D)
/* 175 */       y = -y; 
/* 178 */     if (ptDst != null) {
/* 179 */       ptDst.setLocation(x, y);
/* 180 */       return ptDst;
/*     */     } 
/* 182 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 192 */     double lam = x / 0.8487D;
/* 193 */     double phi = Math.abs(y / 1.3523D);
/* 194 */     if (phi >= 1.0D) {
/* 195 */       if (phi > 1.000001D)
/* 196 */         throw new ProjectionException("Tolerance error occurred appling inverse Robinson projection"); 
/* 198 */       phi = (y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/* 199 */       lam /= (X[18]).c0;
/*     */     } else {
/*     */       double t1;
/* 204 */       int i = (int)Math.floor(phi * 18.0D);
/*     */       while (true) {
/* 205 */         while ((Y[i]).c0 > phi)
/* 206 */           i--; 
/* 207 */         if ((Y[i + 1]).c0 <= phi) {
/* 208 */           i++;
/*     */           continue;
/*     */         } 
/*     */         break;
/*     */       } 
/* 213 */       Coeff T = new Coeff(Y[i]);
/* 215 */       double t = 5.0D * (phi - T.c0) / ((Y[i + 1]).c0 - T.c0);
/* 217 */       T.c0 -= phi;
/*     */       do {
/* 219 */         t1 = T.forward(t) / T.inverse(t);
/* 220 */         t -= t1;
/* 221 */       } while (Math.abs(t1) >= 1.0E-12D);
/* 225 */       phi = Math.toRadians((5 * i) + t);
/* 226 */       if (y < 0.0D)
/* 227 */         phi = -phi; 
/* 229 */       lam /= X[i].forward(t);
/*     */     } 
/* 232 */     if (ptDst != null) {
/* 233 */       ptDst.setLocation(lam, phi);
/* 234 */       return ptDst;
/*     */     } 
/* 236 */     return new Point2D.Double(lam, phi);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 245 */     if (object == this)
/* 247 */       return true; 
/* 249 */     return super.equals(object);
/*     */   }
/*     */   
/*     */   protected double getToleranceForAssertions(double longitude, double latitude) {
/* 255 */     return 2.0D;
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 3586488124601927036L;
/*     */     
/* 285 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.GEOTOOLS, "Robinson"), new NamedIdentifier(Citations.ESRI, "Robinson") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN });
/*     */     
/*     */     public Provider() {
/* 296 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 309 */       return (MathTransform)new Robinson(parameters);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 376 */     System.out.println(1.5707963267948966D);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\Robinson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */