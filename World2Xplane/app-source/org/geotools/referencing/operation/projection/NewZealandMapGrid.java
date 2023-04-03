/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import org.geotools.math.Complex;
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
/*     */ public class NewZealandMapGrid extends MapProjection {
/*     */   private static final long serialVersionUID = 8394817836243729133L;
/*     */   
/*  63 */   private static final Complex[] A = new Complex[] { new Complex(0.7557853228D, 0.0D), new Complex(0.249204646D, 0.003371507D), new Complex(-0.001541739D, 0.04105856D), new Complex(-0.10162907D, 0.01727609D), new Complex(-0.26623489D, -0.36249218D), new Complex(-0.6870983D, -1.1651967D) };
/*     */   
/*  75 */   private static final Complex[] B = new Complex[] { new Complex(1.3231270439D, 0.0D), new Complex(-0.577245789D, -0.007809598D), new Complex(0.508307513D, -0.112208952D), new Complex(-0.15094762D, 0.18200602D), new Complex(1.01418179D, 1.64497696D), new Complex(1.9660549D, 2.5127645D) };
/*     */   
/*  87 */   private static final double[] TPHI = new double[] { 1.5627014243D, 0.5185406398D, -0.03333098D, -0.1052906D, -0.0368594D, 0.007317D, 0.0122D, 0.00394D, -0.0013D };
/*     */   
/*  95 */   private static final double[] TPSI = new double[] { 0.6399175073D, -0.1358797613D, 0.063294409D, -0.02526853D, 0.0117879D, -0.0055161D, 0.0026906D, -0.001333D, 6.7E-4D, -3.4E-4D };
/*     */   
/*     */   protected NewZealandMapGrid() {
/* 104 */     this(Provider.PARAMETERS.createValue());
/*     */   }
/*     */   
/*     */   protected NewZealandMapGrid(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 116 */     super(parameters);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 123 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   final boolean isExpectedParameter(Collection<GeneralParameterDescriptor> expected, ParameterDescriptor param) {
/* 135 */     return ModifiedParameterDescriptor.contains(expected, param);
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 147 */     double dphi = (y - this.latitudeOfOrigin) * 2.0626480624709633D;
/* 148 */     double dphi_pow_i = dphi;
/* 149 */     double dpsi = 0.0D;
/* 150 */     for (int i = 0; i < TPSI.length; i++) {
/* 151 */       dpsi += TPSI[i] * dphi_pow_i;
/* 152 */       dphi_pow_i *= dphi;
/*     */     } 
/* 155 */     Complex theta = new Complex(dpsi, x);
/* 156 */     Complex power = new Complex(theta);
/* 157 */     Complex z = new Complex();
/* 158 */     z.multiply(A[0], power);
/* 159 */     for (int j = 1; j < A.length; j++) {
/* 160 */       power.multiply(power, theta);
/* 161 */       z.addMultiply(z, A[j], power);
/*     */     } 
/* 163 */     if (ptDst != null) {
/* 164 */       ptDst.setLocation(z.imag, z.real);
/* 165 */       return ptDst;
/*     */     } 
/* 167 */     return new Point2D.Double(z.imag, z.real);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 179 */     Complex z = new Complex(y, x);
/* 180 */     Complex power = new Complex(z);
/* 181 */     Complex theta = new Complex();
/* 182 */     theta.multiply(B[0], z);
/* 183 */     for (int j = 1; j < B.length; j++) {
/* 184 */       power.multiply(power, z);
/* 185 */       theta.addMultiply(theta, B[j], power);
/*     */     } 
/* 189 */     Complex num = new Complex();
/* 190 */     Complex denom = new Complex();
/* 191 */     Complex t = new Complex();
/* 192 */     for (int k = 0; k < 3; k++) {
/* 193 */       power.power(theta, 2);
/* 194 */       num.addMultiply(z, A[1], power);
/*     */       int m;
/* 195 */       for (m = 2; m < A.length; m++) {
/* 196 */         power.multiply(power, theta);
/* 197 */         t.multiply(A[m], power);
/* 198 */         t.multiply(t, m);
/* 199 */         num.add(num, t);
/*     */       } 
/* 201 */       power.real = 1.0D;
/* 202 */       power.imag = 0.0D;
/* 203 */       denom.copy(A[0]);
/* 204 */       for (m = 1; m < A.length; m++) {
/* 205 */         power.multiply(power, theta);
/* 206 */         t.multiply(A[m], power);
/* 207 */         t.multiply(t, (m + 1));
/* 208 */         denom.add(denom, t);
/*     */       } 
/* 210 */       theta.divide(num, denom);
/*     */     } 
/* 212 */     double dpsi = theta.real;
/* 213 */     double dpsi_pow_i = dpsi;
/* 214 */     double dphi = TPHI[0] * dpsi;
/* 215 */     for (int i = 1; i < TPHI.length; i++) {
/* 216 */       dpsi_pow_i *= dpsi;
/* 217 */       dphi += TPHI[i] * dpsi_pow_i;
/*     */     } 
/* 219 */     dphi = dphi / 2.0626480624709633D + this.latitudeOfOrigin;
/* 220 */     if (ptDst != null) {
/* 221 */       ptDst.setLocation(theta.imag, dphi);
/* 222 */       return ptDst;
/*     */     } 
/* 224 */     return new Point2D.Double(theta.imag, dphi);
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = -7716733400419275656L;
/*     */     
/* 257 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup(new ReferenceIdentifier[] { (ReferenceIdentifier)new NamedIdentifier(Citations.OGC, "New_Zealand_Map_Grid"), (ReferenceIdentifier)new NamedIdentifier(Citations.EPSG, "New Zealand Map Grid"), (ReferenceIdentifier)new NamedIdentifier(Citations.EPSG, "27200") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { (ParameterDescriptor)new ModifiedParameterDescriptor(SEMI_MAJOR, 6378388.0D), (ParameterDescriptor)new ModifiedParameterDescriptor(SEMI_MINOR, 6356911.9461279465D), (ParameterDescriptor)new ModifiedParameterDescriptor(LATITUDE_OF_ORIGIN, -41.0D), (ParameterDescriptor)new ModifiedParameterDescriptor(CENTRAL_MERIDIAN, 173.0D), (ParameterDescriptor)new ModifiedParameterDescriptor(FALSE_EASTING, 2510000.0D), (ParameterDescriptor)new ModifiedParameterDescriptor(FALSE_NORTHING, 6023150.0D) });
/*     */     
/*     */     public Provider() {
/* 276 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 291 */       return (MathTransform)new NewZealandMapGrid(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\NewZealandMapGrid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */