/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.AngleFormat;
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ 
/*     */ public class Projection implements Cloneable {
/*  31 */   protected double minLatitude = -1.5707963267948966D;
/*     */   
/*  36 */   protected double minLongitude = -3.141592653589793D;
/*     */   
/*  41 */   protected double maxLatitude = 1.5707963267948966D;
/*     */   
/*  46 */   protected double maxLongitude = Math.PI;
/*     */   
/*  51 */   protected double projectionLatitude = 0.0D;
/*     */   
/*  56 */   protected double projectionLongitude = 0.0D;
/*     */   
/*  61 */   protected double standardParallel1 = 0.0D;
/*     */   
/*  66 */   protected double standardParallel2 = 0.0D;
/*     */   
/*  71 */   protected double scaleFactor = 1.0D;
/*     */   
/*  76 */   protected double falseEasting = 0.0D;
/*     */   
/*  81 */   protected double falseNorthing = 0.0D;
/*     */   
/*  86 */   protected double trueScaleLatitude = 0.0D;
/*     */   
/*  91 */   protected double a = 0.0D;
/*     */   
/*  96 */   protected double e = 0.0D;
/*     */   
/* 101 */   protected double es = 0.0D;
/*     */   
/* 106 */   protected double one_es = 0.0D;
/*     */   
/* 111 */   protected double rone_es = 0.0D;
/*     */   
/*     */   protected Ellipsoid ellipsoid;
/*     */   
/*     */   protected boolean spherical;
/*     */   
/*     */   protected boolean geocentric;
/*     */   
/* 131 */   protected String name = null;
/*     */   
/* 136 */   protected double fromMetres = 1.0D;
/*     */   
/* 141 */   private double totalScale = 0.0D;
/*     */   
/*     */   protected static final double EPS10 = 1.0E-10D;
/*     */   
/*     */   protected static final double RTD = 57.29577951308232D;
/*     */   
/*     */   protected static final double DTR = 0.017453292519943295D;
/*     */   
/*     */   protected Projection() {
/* 149 */     setEllipsoid(Ellipsoid.SPHERE);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 154 */       Projection e = (Projection)super.clone();
/* 155 */       return e;
/* 157 */     } catch (CloneNotSupportedException e) {
/* 158 */       throw new InternalError();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Point2D.Double transform(Point2D.Double src, Point2D.Double dst) {
/* 166 */     double x = src.x * 0.017453292519943295D;
/* 167 */     if (this.projectionLongitude != 0.0D)
/* 168 */       x = MapMath.normalizeLongitude(x - this.projectionLongitude); 
/* 169 */     project(x, src.y * 0.017453292519943295D, dst);
/* 170 */     dst.x = this.totalScale * dst.x + this.falseEasting;
/* 171 */     dst.y = this.totalScale * dst.y + this.falseNorthing;
/* 172 */     return dst;
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double x, double y, Point2D.Double dst) {
/* 179 */     dst.x = x;
/* 180 */     dst.y = y;
/* 181 */     return dst;
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPoints, int srcOffset, double[] dstPoints, int dstOffset, int numPoints) {
/* 188 */     Point2D.Double in = new Point2D.Double();
/* 189 */     Point2D.Double out = new Point2D.Double();
/* 190 */     for (int i = 0; i < numPoints; i++) {
/* 191 */       in.x = srcPoints[srcOffset++] * 0.017453292519943295D;
/* 192 */       in.y = srcPoints[srcOffset++] * 0.017453292519943295D;
/* 193 */       transform(in, out);
/* 194 */       dstPoints[dstOffset++] = out.x;
/* 195 */       dstPoints[dstOffset++] = out.y;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Point2D.Double inverseTransform(Point2D.Double src, Point2D.Double dst) {
/* 203 */     double x = (src.x - this.falseEasting) / this.totalScale;
/* 204 */     double y = (src.y - this.falseNorthing) / this.totalScale;
/* 205 */     projectInverse(x, y, dst);
/* 206 */     if (dst.x < -3.141592653589793D) {
/* 207 */       dst.x = -3.141592653589793D;
/* 208 */     } else if (dst.x > Math.PI) {
/* 209 */       dst.x = Math.PI;
/*     */     } 
/* 210 */     if (this.projectionLongitude != 0.0D)
/* 211 */       dst.x = MapMath.normalizeLongitude(dst.x + this.projectionLongitude); 
/* 212 */     dst.x *= 57.29577951308232D;
/* 213 */     dst.y *= 57.29577951308232D;
/* 214 */     return dst;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double x, double y, Point2D.Double dst) {
/* 221 */     dst.x = x;
/* 222 */     dst.y = y;
/* 223 */     return dst;
/*     */   }
/*     */   
/*     */   public void inverseTransform(double[] srcPoints, int srcOffset, double[] dstPoints, int dstOffset, int numPoints) {
/* 230 */     Point2D.Double in = new Point2D.Double();
/* 231 */     Point2D.Double out = new Point2D.Double();
/* 232 */     for (int i = 0; i < numPoints; i++) {
/* 233 */       in.x = srcPoints[srcOffset++];
/* 234 */       in.y = srcPoints[srcOffset++];
/* 235 */       inverseTransform(in, out);
/* 236 */       dstPoints[dstOffset++] = out.x * 57.29577951308232D;
/* 237 */       dstPoints[dstOffset++] = out.y * 57.29577951308232D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Rectangle2D inverseTransform(Rectangle2D r) {
/* 246 */     Point2D.Double in = new Point2D.Double();
/* 247 */     Point2D.Double out = new Point2D.Double();
/* 248 */     Rectangle2D bounds = null;
/* 249 */     if (isRectilinear()) {
/* 250 */       for (int ix = 0; ix < 2; ix++) {
/* 251 */         double x = r.getX() + r.getWidth() * ix;
/* 252 */         for (int iy = 0; iy < 2; iy++) {
/* 253 */           double y = r.getY() + r.getHeight() * iy;
/* 254 */           in.x = x;
/* 255 */           in.y = y;
/* 256 */           inverseTransform(in, out);
/* 257 */           if (ix == 0 && iy == 0) {
/* 258 */             bounds = new Rectangle2D.Double(out.x, out.y, 0.0D, 0.0D);
/*     */           } else {
/* 260 */             bounds.add(out.x, out.y);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 264 */       for (int ix = 0; ix < 7; ix++) {
/* 265 */         double x = r.getX() + r.getWidth() * ix / 6.0D;
/* 266 */         for (int iy = 0; iy < 7; iy++) {
/* 267 */           double y = r.getY() + r.getHeight() * iy / 6.0D;
/* 268 */           in.x = x;
/* 269 */           in.y = y;
/* 270 */           inverseTransform(in, out);
/* 271 */           if (ix == 0 && iy == 0) {
/* 272 */             bounds = new Rectangle2D.Double(out.x, out.y, 0.0D, 0.0D);
/*     */           } else {
/* 274 */             bounds.add(out.x, out.y);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 278 */     return bounds;
/*     */   }
/*     */   
/*     */   public Rectangle2D transform(Rectangle2D r) {
/* 285 */     Point2D.Double in = new Point2D.Double();
/* 286 */     Point2D.Double out = new Point2D.Double();
/* 287 */     Rectangle2D bounds = null;
/* 288 */     if (isRectilinear()) {
/* 289 */       for (int ix = 0; ix < 2; ix++) {
/* 290 */         double x = r.getX() + r.getWidth() * ix;
/* 291 */         for (int iy = 0; iy < 2; iy++) {
/* 292 */           double y = r.getY() + r.getHeight() * iy;
/* 293 */           in.x = x;
/* 294 */           in.y = y;
/* 295 */           transform(in, out);
/* 296 */           if (ix == 0 && iy == 0) {
/* 297 */             bounds = new Rectangle2D.Double(out.x, out.y, 0.0D, 0.0D);
/*     */           } else {
/* 299 */             bounds.add(out.x, out.y);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 303 */       for (int ix = 0; ix < 7; ix++) {
/* 304 */         double x = r.getX() + r.getWidth() * ix / 6.0D;
/* 305 */         for (int iy = 0; iy < 7; iy++) {
/* 306 */           double y = r.getY() + r.getHeight() * iy / 6.0D;
/* 307 */           in.x = x;
/* 308 */           in.y = y;
/* 309 */           transform(in, out);
/* 310 */           if (ix == 0 && iy == 0) {
/* 311 */             bounds = new Rectangle2D.Double(out.x, out.y, 0.0D, 0.0D);
/*     */           } else {
/* 313 */             bounds.add(out.x, out.y);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 317 */     return bounds;
/*     */   }
/*     */   
/*     */   public boolean isConformal() {
/* 324 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEqualArea() {
/* 331 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 338 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isRectilinear() {
/* 345 */     return false;
/*     */   }
/*     */   
/*     */   public boolean parallelsAreParallel() {
/* 352 */     return isRectilinear();
/*     */   }
/*     */   
/*     */   public boolean inside(double x, double y) {
/* 359 */     x = normalizeLongitude((float)(x * 0.017453292519943295D - this.projectionLongitude));
/* 360 */     return (this.minLongitude <= x && x <= this.maxLongitude && this.minLatitude <= y && y <= this.maxLatitude);
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 367 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 371 */     if (this.name != null)
/* 372 */       return this.name; 
/* 373 */     return toString();
/*     */   }
/*     */   
/*     */   public String getPROJ4Description() {
/* 380 */     AngleFormat format = new AngleFormat("DdM", false);
/* 381 */     StringBuffer sb = new StringBuffer();
/* 382 */     sb.append("+proj=" + getName() + " +a=" + this.a);
/* 386 */     if (this.es != 0.0D)
/* 387 */       sb.append(" +es=" + this.es); 
/* 388 */     sb.append(" +lon_0=");
/* 389 */     format.format(this.projectionLongitude, sb, null);
/* 390 */     sb.append(" +lat_0=");
/* 391 */     format.format(this.projectionLatitude, sb, null);
/* 392 */     if (this.falseEasting != 1.0D)
/* 393 */       sb.append(" +x_0=" + this.falseEasting); 
/* 394 */     if (this.falseNorthing != 1.0D)
/* 395 */       sb.append(" +y_0=" + this.falseNorthing); 
/* 396 */     if (this.scaleFactor != 1.0D)
/* 397 */       sb.append(" +k=" + this.scaleFactor); 
/* 398 */     if (this.fromMetres != 1.0D)
/* 399 */       sb.append(" +fr_meters=" + this.fromMetres); 
/* 400 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 404 */     return "None";
/*     */   }
/*     */   
/*     */   public void setMinLatitude(double minLatitude) {
/* 411 */     this.minLatitude = minLatitude;
/*     */   }
/*     */   
/*     */   public double getMinLatitude() {
/* 415 */     return this.minLatitude;
/*     */   }
/*     */   
/*     */   public void setMaxLatitude(double maxLatitude) {
/* 422 */     this.maxLatitude = maxLatitude;
/*     */   }
/*     */   
/*     */   public double getMaxLatitude() {
/* 426 */     return this.maxLatitude;
/*     */   }
/*     */   
/*     */   public double getMaxLatitudeDegrees() {
/* 430 */     return this.maxLatitude * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public double getMinLatitudeDegrees() {
/* 434 */     return this.minLatitude * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public void setMinLongitude(double minLongitude) {
/* 438 */     this.minLongitude = minLongitude;
/*     */   }
/*     */   
/*     */   public double getMinLongitude() {
/* 442 */     return this.minLongitude;
/*     */   }
/*     */   
/*     */   public void setMinLongitudeDegrees(double minLongitude) {
/* 446 */     this.minLongitude = 0.017453292519943295D * minLongitude;
/*     */   }
/*     */   
/*     */   public double getMinLongitudeDegrees() {
/* 450 */     return this.minLongitude * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public void setMaxLongitude(double maxLongitude) {
/* 454 */     this.maxLongitude = maxLongitude;
/*     */   }
/*     */   
/*     */   public double getMaxLongitude() {
/* 458 */     return this.maxLongitude;
/*     */   }
/*     */   
/*     */   public void setMaxLongitudeDegrees(double maxLongitude) {
/* 462 */     this.maxLongitude = 0.017453292519943295D * maxLongitude;
/*     */   }
/*     */   
/*     */   public double getMaxLongitudeDegrees() {
/* 466 */     return this.maxLongitude * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public void setProjectionLatitude(double projectionLatitude) {
/* 473 */     this.projectionLatitude = projectionLatitude;
/*     */   }
/*     */   
/*     */   public double getProjectionLatitude() {
/* 477 */     return this.projectionLatitude;
/*     */   }
/*     */   
/*     */   public void setProjectionLatitudeDegrees(double projectionLatitude) {
/* 484 */     this.projectionLatitude = 0.017453292519943295D * projectionLatitude;
/*     */   }
/*     */   
/*     */   public double getProjectionLatitudeDegrees() {
/* 488 */     return this.projectionLatitude * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public void setProjectionLongitude(double projectionLongitude) {
/* 495 */     this.projectionLongitude = normalizeLongitudeRadians(projectionLongitude);
/*     */   }
/*     */   
/*     */   public double getProjectionLongitude() {
/* 499 */     return this.projectionLongitude;
/*     */   }
/*     */   
/*     */   public void setProjectionLongitudeDegrees(double projectionLongitude) {
/* 506 */     this.projectionLongitude = 0.017453292519943295D * projectionLongitude;
/*     */   }
/*     */   
/*     */   public double getProjectionLongitudeDegrees() {
/* 510 */     return this.projectionLongitude * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public void setTrueScaleLatitude(double trueScaleLatitude) {
/* 517 */     this.trueScaleLatitude = trueScaleLatitude;
/*     */   }
/*     */   
/*     */   public double getTrueScaleLatitude() {
/* 521 */     return this.trueScaleLatitude;
/*     */   }
/*     */   
/*     */   public void setTrueScaleLatitudeDegrees(double trueScaleLatitude) {
/* 528 */     this.trueScaleLatitude = 0.017453292519943295D * trueScaleLatitude;
/*     */   }
/*     */   
/*     */   public double getTrueScaleLatitudeDegrees() {
/* 532 */     return this.trueScaleLatitude * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public void setFalseNorthing(double falseNorthing) {
/* 539 */     this.falseNorthing = falseNorthing;
/*     */   }
/*     */   
/*     */   public double getFalseNorthing() {
/* 543 */     return this.falseNorthing;
/*     */   }
/*     */   
/*     */   public void setFalseEasting(double falseEasting) {
/* 550 */     this.falseEasting = falseEasting;
/*     */   }
/*     */   
/*     */   public double getFalseEasting() {
/* 554 */     return this.falseEasting;
/*     */   }
/*     */   
/*     */   public void setScaleFactor(double scaleFactor) {
/* 561 */     this.scaleFactor = scaleFactor;
/*     */   }
/*     */   
/*     */   public double getScaleFactor() {
/* 565 */     return this.scaleFactor;
/*     */   }
/*     */   
/*     */   public double getEquatorRadius() {
/* 569 */     return this.a;
/*     */   }
/*     */   
/*     */   public void setFromMetres(double fromMetres) {
/* 576 */     this.fromMetres = fromMetres;
/*     */   }
/*     */   
/*     */   public double getFromMetres() {
/* 580 */     return this.fromMetres;
/*     */   }
/*     */   
/*     */   public void setEllipsoid(Ellipsoid ellipsoid) {
/* 584 */     this.ellipsoid = ellipsoid;
/* 585 */     this.a = ellipsoid.equatorRadius;
/* 586 */     this.e = ellipsoid.eccentricity;
/* 587 */     this.es = ellipsoid.eccentricity2;
/*     */   }
/*     */   
/*     */   public Ellipsoid getEllipsoid() {
/* 591 */     return this.ellipsoid;
/*     */   }
/*     */   
/*     */   public int getEPSGCode() {
/* 598 */     return 0;
/*     */   }
/*     */   
/*     */   public void initialize() {
/* 606 */     this.spherical = (this.e == 0.0D);
/* 607 */     this.one_es = 1.0D - this.es;
/* 608 */     this.rone_es = 1.0D / this.one_es;
/* 609 */     this.totalScale = this.a * this.fromMetres;
/*     */   }
/*     */   
/*     */   public static float normalizeLongitude(float angle) {
/* 613 */     if (Double.isInfinite(angle) || Double.isNaN(angle))
/* 614 */       throw new IllegalArgumentException("Infinite longitude"); 
/* 615 */     while (angle > 180.0F)
/* 616 */       angle -= 360.0F; 
/* 617 */     while (angle < -180.0F)
/* 618 */       angle += 360.0F; 
/* 619 */     return angle;
/*     */   }
/*     */   
/*     */   public static double normalizeLongitudeRadians(double angle) {
/* 623 */     if (Double.isInfinite(angle) || Double.isNaN(angle))
/* 624 */       throw new IllegalArgumentException("Infinite longitude"); 
/* 625 */     while (angle > Math.PI)
/* 626 */       angle -= 6.283185307179586D; 
/* 627 */     while (angle < -3.141592653589793D)
/* 628 */       angle += 6.283185307179586D; 
/* 629 */     return angle;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */