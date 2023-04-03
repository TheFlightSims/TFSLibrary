/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import org.geotools.measure.Latitude;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ 
/*     */ public abstract class LambertConformal extends MapProjection {
/*     */   private static final long serialVersionUID = 1275881689637308614L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   private static final double BELGE_A = 1.42043136359877E-4D;
/*     */   
/*     */   private final double phi1;
/*     */   
/*     */   private final double phi2;
/*     */   
/*     */   private final double n;
/*     */   
/*     */   private final double F;
/*     */   
/*     */   private final double rho0;
/*     */   
/*     */   private final boolean belgium;
/*     */   
/*     */   protected LambertConformal(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 123 */     this(parameters, false);
/*     */   }
/*     */   
/*     */   LambertConformal(ParameterValueGroup parameters, boolean belgium) throws ParameterNotFoundException {
/* 137 */     super(parameters);
/* 138 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 139 */     boolean sp2 = expected.contains(MapProjection.AbstractProvider.STANDARD_PARALLEL_2);
/* 140 */     this.belgium = belgium;
/* 141 */     if (sp2) {
/* 143 */       this.phi1 = doubleValue(expected, MapProjection.AbstractProvider.STANDARD_PARALLEL_1, parameters);
/* 144 */       ensureLatitudeInRange(MapProjection.AbstractProvider.STANDARD_PARALLEL_1, this.phi1, true);
/* 145 */       double phi2 = doubleValue(expected, MapProjection.AbstractProvider.STANDARD_PARALLEL_2, parameters);
/* 146 */       if (Double.isNaN(phi2))
/* 147 */         phi2 = this.phi1; 
/* 149 */       this.phi2 = phi2;
/* 150 */       ensureLatitudeInRange(MapProjection.AbstractProvider.STANDARD_PARALLEL_2, phi2, true);
/*     */     } else {
/* 152 */       if (belgium)
/* 153 */         throw new IllegalArgumentException(); 
/* 156 */       this.phi1 = this.phi2 = this.latitudeOfOrigin;
/*     */     } 
/* 159 */     if (Math.abs(this.phi1 + this.phi2) < 1.0E-6D)
/* 160 */       throw new IllegalArgumentException(Errors.format(5, new Latitude(Math.toDegrees(this.phi1)), new Latitude(Math.toDegrees(this.phi2)))); 
/* 164 */     double cosphi1 = Math.cos(this.phi1);
/* 165 */     double sinphi1 = Math.sin(this.phi1);
/* 166 */     boolean secant = (Math.abs(this.phi1 - this.phi2) > 1.0E-6D);
/* 167 */     if (this.isSpherical) {
/* 168 */       if (secant) {
/* 169 */         this.n = Math.log(cosphi1 / Math.cos(this.phi2)) / Math.log(Math.tan(0.7853981633974483D + 0.5D * this.phi2) / Math.tan(0.7853981633974483D + 0.5D * this.phi1));
/*     */       } else {
/* 172 */         this.n = sinphi1;
/*     */       } 
/* 174 */       this.F = cosphi1 * Math.pow(Math.tan(0.7853981633974483D + 0.5D * this.phi1), this.n) / this.n;
/* 175 */       if (Math.abs(Math.abs(this.latitudeOfOrigin) - 1.5707963267948966D) >= 1.0E-6D) {
/* 176 */         this.rho0 = this.F * Math.pow(Math.tan(0.7853981633974483D + 0.5D * this.latitudeOfOrigin), -this.n);
/*     */       } else {
/* 178 */         this.rho0 = 0.0D;
/*     */       } 
/*     */     } else {
/* 181 */       double m1 = msfn(sinphi1, cosphi1);
/* 182 */       double t1 = tsfn(this.phi1, sinphi1);
/* 183 */       if (secant) {
/* 184 */         double sinphi2 = Math.sin(this.phi2);
/* 185 */         double m2 = msfn(sinphi2, Math.cos(this.phi2));
/* 186 */         double t2 = tsfn(this.phi2, sinphi2);
/* 187 */         this.n = Math.log(m1 / m2) / Math.log(t1 / t2);
/*     */       } else {
/* 189 */         this.n = sinphi1;
/*     */       } 
/* 191 */       this.F = m1 * Math.pow(t1, -this.n) / this.n;
/* 192 */       if (Math.abs(Math.abs(this.latitudeOfOrigin) - 1.5707963267948966D) >= 1.0E-6D) {
/* 193 */         this.rho0 = this.F * Math.pow(tsfn(this.latitudeOfOrigin, Math.sin(this.latitudeOfOrigin)), this.n);
/*     */       } else {
/* 195 */         this.rho0 = 0.0D;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 205 */     ParameterValueGroup values = super.getParameterValues();
/* 206 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 207 */     set(expected, MapProjection.AbstractProvider.STANDARD_PARALLEL_1, values, this.phi1);
/* 208 */     set(expected, MapProjection.AbstractProvider.STANDARD_PARALLEL_2, values, this.phi2);
/* 209 */     return values;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/*     */     double rho;
/* 222 */     if (Math.abs(Math.abs(y) - 1.5707963267948966D) < 1.0E-6D) {
/* 223 */       if (y * this.n <= 0.0D)
/* 224 */         throw new ProjectionException(y); 
/* 226 */       rho = 0.0D;
/* 228 */     } else if (this.isSpherical) {
/* 229 */       rho = this.F * Math.pow(Math.tan(0.7853981633974483D + 0.5D * y), -this.n);
/*     */     } else {
/* 231 */       rho = this.F * Math.pow(tsfn(y, Math.sin(y)), this.n);
/*     */     } 
/* 233 */     x *= this.n;
/* 234 */     if (this.belgium)
/* 235 */       x -= 1.42043136359877E-4D; 
/* 237 */     y = this.rho0 - rho * Math.cos(x);
/* 238 */     x = rho * Math.sin(x);
/* 239 */     if (ptDst != null) {
/* 240 */       ptDst.setLocation(x, y);
/* 241 */       return ptDst;
/*     */     } 
/* 243 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 254 */     y = this.rho0 - y;
/* 255 */     double rho = Math.hypot(x, y);
/* 256 */     if (rho > 1.0E-6D) {
/* 257 */       if (this.n < 0.0D) {
/* 258 */         rho = -rho;
/* 259 */         x = -x;
/* 260 */         y = -y;
/*     */       } 
/* 262 */       double theta = Math.atan2(x, y);
/* 263 */       if (this.belgium)
/* 264 */         theta += 1.42043136359877E-4D; 
/* 266 */       x = theta / this.n;
/* 267 */       if (this.isSpherical) {
/* 268 */         y = 2.0D * Math.atan(Math.pow(this.F / rho, 1.0D / this.n)) - 1.5707963267948966D;
/*     */       } else {
/* 270 */         y = cphi2(Math.pow(rho / this.F, 1.0D / this.n));
/*     */       } 
/*     */     } else {
/* 273 */       x = 0.0D;
/* 274 */       y = (this.n < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */     } 
/* 276 */     if (ptDst != null) {
/* 277 */       ptDst.setLocation(x, y);
/* 278 */       return ptDst;
/*     */     } 
/* 280 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 293 */     long code = Double.doubleToLongBits(this.F);
/* 294 */     return ((int)code ^ (int)(code >>> 32L)) + 37 * super.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 302 */     if (object == this)
/* 304 */       return true; 
/* 306 */     if (super.equals(object)) {
/* 307 */       LambertConformal that = (LambertConformal)object;
/* 308 */       return (this.belgium == that.belgium && equals(this.n, that.n) && equals(this.F, that.F) && equals(this.rho0, that.rho0) && equals(this.phi1, that.phi1) && equals(this.phi2, that.phi2));
/*     */     } 
/* 315 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\LambertConformal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */