/*     */ package org.geotools.referencing.datum;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.geometry.GeneralDirectPosition;
/*     */ import org.geotools.measure.CoordinateFormat;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ 
/*     */ public class DefaultEllipsoid extends AbstractIdentifiedObject implements Ellipsoid {
/*     */   private static final long serialVersionUID = -1149451543954764081L;
/*     */   
/*  68 */   public static final DefaultEllipsoid WGS84 = createFlattenedSphere("WGS84", 6378137.0D, 298.257223563D, SI.METER);
/*     */   
/*  76 */   public static final DefaultEllipsoid GRS80 = createFlattenedSphere("GRS80", 6378137.0D, 298.257222101D, SI.METER);
/*     */   
/*  82 */   public static final DefaultEllipsoid INTERNATIONAL_1924 = createFlattenedSphere("International 1924", 6378388.0D, 297.0D, SI.METER);
/*     */   
/*  90 */   public static final DefaultEllipsoid CLARKE_1866 = createFlattenedSphere("Clarke 1866", 6378206.4D, 294.9786982D, SI.METER);
/*     */   
/*  98 */   public static final DefaultEllipsoid SPHERE = createEllipsoid("SPHERE", 6371000.0D, 6371000.0D, SI.METER);
/*     */   
/*     */   private final double semiMajorAxis;
/*     */   
/*     */   private final double semiMinorAxis;
/*     */   
/*     */   private final double inverseFlattening;
/*     */   
/*     */   private final boolean ivfDefinitive;
/*     */   
/*     */   private final Unit<Length> unit;
/*     */   
/*     */   protected DefaultEllipsoid(Ellipsoid ellipsoid) {
/* 147 */     super((IdentifiedObject)ellipsoid);
/* 148 */     this.semiMajorAxis = ellipsoid.getSemiMajorAxis();
/* 149 */     this.semiMinorAxis = ellipsoid.getSemiMinorAxis();
/* 150 */     this.inverseFlattening = ellipsoid.getInverseFlattening();
/* 151 */     this.ivfDefinitive = ellipsoid.isIvfDefinitive();
/* 152 */     this.unit = ellipsoid.getAxisUnit();
/*     */   }
/*     */   
/*     */   protected DefaultEllipsoid(Map<String, ?> properties, double semiMajorAxis, double semiMinorAxis, double inverseFlattening, boolean ivfDefinitive, Unit<Length> unit) {
/* 177 */     super(properties);
/* 178 */     this.unit = unit;
/* 179 */     this.semiMajorAxis = check("semiMajorAxis", semiMajorAxis);
/* 180 */     this.semiMinorAxis = check("semiMinorAxis", semiMinorAxis);
/* 181 */     this.inverseFlattening = check("inverseFlattening", inverseFlattening);
/* 182 */     this.ivfDefinitive = ivfDefinitive;
/* 183 */     ensureNonNull("unit", unit);
/* 184 */     ensureLinearUnit(unit);
/*     */   }
/*     */   
/*     */   public static DefaultEllipsoid createEllipsoid(String name, double semiMajorAxis, double semiMinorAxis, Unit<Length> unit) {
/* 201 */     return createEllipsoid(Collections.singletonMap("name", name), semiMajorAxis, semiMinorAxis, unit);
/*     */   }
/*     */   
/*     */   public static DefaultEllipsoid createEllipsoid(Map<String, ?> properties, double semiMajorAxis, double semiMinorAxis, Unit<Length> unit) {
/* 221 */     if (semiMajorAxis == semiMinorAxis)
/* 222 */       return new Spheroid(properties, semiMajorAxis, false, unit); 
/* 224 */     return new DefaultEllipsoid(properties, semiMajorAxis, semiMinorAxis, semiMajorAxis / (semiMajorAxis - semiMinorAxis), false, unit);
/*     */   }
/*     */   
/*     */   public static DefaultEllipsoid createFlattenedSphere(String name, double semiMajorAxis, double inverseFlattening, Unit<Length> unit) {
/* 244 */     return createFlattenedSphere(Collections.singletonMap("name", name), semiMajorAxis, inverseFlattening, unit);
/*     */   }
/*     */   
/*     */   public static DefaultEllipsoid createFlattenedSphere(Map<String, ?> properties, double semiMajorAxis, double inverseFlattening, Unit<Length> unit) {
/* 265 */     if (Double.isInfinite(inverseFlattening))
/* 266 */       return new Spheroid(properties, semiMajorAxis, true, unit); 
/* 268 */     return new DefaultEllipsoid(properties, semiMajorAxis, semiMajorAxis * (1.0D - 1.0D / inverseFlattening), inverseFlattening, true, unit);
/*     */   }
/*     */   
/*     */   public static DefaultEllipsoid wrap(Ellipsoid ellipsoid) {
/* 284 */     if (ellipsoid == null || ellipsoid instanceof DefaultEllipsoid)
/* 285 */       return (DefaultEllipsoid)ellipsoid; 
/* 287 */     if (ellipsoid.isIvfDefinitive())
/* 288 */       return createFlattenedSphere(getProperties((IdentifiedObject)ellipsoid), ellipsoid.getSemiMajorAxis(), ellipsoid.getInverseFlattening(), ellipsoid.getAxisUnit()); 
/* 293 */     return createEllipsoid(getProperties((IdentifiedObject)ellipsoid), ellipsoid.getSemiMajorAxis(), ellipsoid.getSemiMinorAxis(), ellipsoid.getAxisUnit());
/*     */   }
/*     */   
/*     */   static double check(String name, double value) throws IllegalArgumentException {
/* 309 */     if (value > 0.0D)
/* 310 */       return value; 
/* 312 */     throw new IllegalArgumentException(Errors.format(58, name, Double.valueOf(value)));
/*     */   }
/*     */   
/*     */   public Unit<Length> getAxisUnit() {
/* 322 */     return this.unit;
/*     */   }
/*     */   
/*     */   public double getSemiMajorAxis() {
/* 332 */     return this.semiMajorAxis;
/*     */   }
/*     */   
/*     */   public double getSemiMinorAxis() {
/* 342 */     return this.semiMinorAxis;
/*     */   }
/*     */   
/*     */   public double getEccentricity() {
/* 353 */     double f = 1.0D - getSemiMinorAxis() / getSemiMajorAxis();
/* 354 */     return Math.sqrt(2.0D * f - f * f);
/*     */   }
/*     */   
/*     */   public double getInverseFlattening() {
/* 370 */     return this.inverseFlattening;
/*     */   }
/*     */   
/*     */   public boolean isIvfDefinitive() {
/* 384 */     return this.ivfDefinitive;
/*     */   }
/*     */   
/*     */   public boolean isSphere() {
/* 395 */     return (this.semiMajorAxis == this.semiMinorAxis);
/*     */   }
/*     */   
/*     */   public double orthodromicDistance(Point2D P1, Point2D P2) {
/* 409 */     return orthodromicDistance(P1.getX(), P1.getY(), P2.getX(), P2.getY());
/*     */   }
/*     */   
/*     */   public double orthodromicDistance(double x1, double y1, double x2, double y2) {
/* 426 */     x1 = Math.toRadians(x1);
/* 427 */     y1 = Math.toRadians(y1);
/* 428 */     x2 = Math.toRadians(x2);
/* 429 */     y2 = Math.toRadians(y2);
/* 445 */     int MAX_ITERATIONS = 100;
/* 446 */     double EPS = 5.0E-14D;
/* 447 */     double F = 1.0D / getInverseFlattening();
/* 448 */     double R = 1.0D - F;
/* 450 */     double tu1 = R * Math.sin(y1) / Math.cos(y1);
/* 451 */     double tu2 = R * Math.sin(y2) / Math.cos(y2);
/* 452 */     double cu1 = 1.0D / Math.sqrt(tu1 * tu1 + 1.0D);
/* 453 */     double cu2 = 1.0D / Math.sqrt(tu2 * tu2 + 1.0D);
/* 454 */     double su1 = cu1 * tu1;
/* 455 */     double s = cu1 * cu2;
/* 456 */     double baz = s * tu2;
/* 457 */     double faz = baz * tu1;
/* 458 */     double x = x2 - x1;
/* 459 */     for (int i = 0; i < 100; i++) {
/* 460 */       double sx = Math.sin(x);
/* 461 */       double cx = Math.cos(x);
/* 462 */       tu1 = cu2 * sx;
/* 463 */       tu2 = baz - su1 * cu2 * cx;
/* 464 */       double sy = Math.hypot(tu1, tu2);
/* 465 */       double cy = s * cx + faz;
/* 466 */       double y = Math.atan2(sy, cy);
/* 467 */       double SA = s * sx / sy;
/* 468 */       double c2a = 1.0D - SA * SA;
/* 469 */       double cz = faz + faz;
/* 470 */       if (c2a > 0.0D)
/* 471 */         cz = -cz / c2a + cy; 
/* 473 */       double e = cz * cz * 2.0D - 1.0D;
/* 474 */       double c = ((-3.0D * c2a + 4.0D) * F + 4.0D) * c2a * F / 16.0D;
/* 475 */       double d = x;
/* 476 */       x = ((e * cy * c + cz) * sy * c + y) * SA;
/* 477 */       x = (1.0D - c) * x * F + x2 - x1;
/* 479 */       if (Math.abs(d - x) <= 5.0E-14D) {
/* 487 */         x = Math.sqrt((1.0D / R * R - 1.0D) * c2a + 1.0D) + 1.0D;
/* 488 */         x = (x - 2.0D) / x;
/* 489 */         c = 1.0D - x;
/* 490 */         c = (x * x / 4.0D + 1.0D) / c;
/* 491 */         d = (0.375D * x * x - 1.0D) * x;
/* 492 */         x = e * cy;
/* 493 */         s = 1.0D - 2.0D * e;
/* 494 */         s = ((((sy * sy * 4.0D - 3.0D) * s * cz * d / 6.0D - x) * d / 4.0D + cz) * sy * d + y) * c * R * getSemiMajorAxis();
/* 495 */         return s;
/*     */       } 
/*     */     } 
/* 500 */     double LEPS = 1.0E-10D;
/* 501 */     if (Math.abs(x1 - x2) <= 1.0E-10D && Math.abs(y1 - y2) <= 1.0E-10D)
/* 502 */       return 0.0D; 
/* 504 */     if (Math.abs(y1) <= 1.0E-10D && Math.abs(y2) <= 1.0E-10D)
/* 505 */       return Math.abs(x1 - x2) * getSemiMajorAxis(); 
/* 508 */     CoordinateFormat format = new CoordinateFormat();
/* 509 */     throw new ArithmeticException(Errors.format(130, format.format(new GeneralDirectPosition(Math.toDegrees(x1), Math.toDegrees(y1))), format.format(new GeneralDirectPosition(Math.toDegrees(x2), Math.toDegrees(y2)))));
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 524 */     if (object == this)
/* 525 */       return true; 
/* 527 */     if (super.equals(object, compareMetadata)) {
/* 528 */       DefaultEllipsoid that = (DefaultEllipsoid)object;
/* 529 */       return ((!compareMetadata || this.ivfDefinitive == that.ivfDefinitive) && Utilities.equals(this.semiMajorAxis, that.semiMajorAxis) && Utilities.equals(this.semiMinorAxis, that.semiMinorAxis) && Utilities.equals(this.inverseFlattening, that.inverseFlattening) && Utilities.equals(this.unit, that.unit));
/*     */     } 
/* 535 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 550 */     long longCode = 37L * Double.doubleToLongBits(this.semiMajorAxis);
/* 551 */     if (this.ivfDefinitive) {
/* 552 */       longCode = (long)(longCode + this.inverseFlattening);
/*     */     } else {
/* 554 */       longCode = (long)(longCode + this.semiMinorAxis);
/*     */     } 
/* 556 */     return (int)(longCode >>> 32L) ^ (int)longCode;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 569 */     double ivf = getInverseFlattening();
/* 570 */     formatter.append(getAxisUnit().getConverterTo(SI.METER).convert(getSemiMajorAxis()));
/* 571 */     formatter.append(Double.isInfinite(ivf) ? 0.0D : ivf);
/* 572 */     return "SPHEROID";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\datum\DefaultEllipsoid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */