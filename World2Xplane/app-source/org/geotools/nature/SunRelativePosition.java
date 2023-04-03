/*     */ package org.geotools.nature;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class SunRelativePosition {
/*     */   private static final int DAY_MILLIS = 86400000;
/*     */   
/*     */   private static final double DARK = NaND;
/*     */   
/*     */   public static final double ASTRONOMICAL_TWILIGHT = -18.0D;
/*     */   
/*     */   public static final double NAUTICAL_TWILIGHT = -12.0D;
/*     */   
/*     */   public static final double CIVIL_TWILIGHT = -6.0D;
/*     */   
/* 119 */   private double twilight = -6.0D;
/*     */   
/*     */   private long noonTime;
/*     */   
/*     */   private double azimuth;
/*     */   
/*     */   private double elevation;
/*     */   
/*     */   private double latitude;
/*     */   
/*     */   private double longitude;
/*     */   
/* 148 */   private long time = System.currentTimeMillis();
/*     */   
/*     */   private boolean updated;
/*     */   
/*     */   private static double sunEquationOfCenter(double t) {
/* 166 */     double m = Math.toRadians(sunGeometricMeanAnomaly(t));
/* 167 */     return Math.sin(1.0D * m) * (1.914602D - t * (0.004817D + 1.4E-5D * t)) + Math.sin(2.0D * m) * (0.019993D - t * 1.01E-4D) + Math.sin(3.0D * m) * 2.89E-4D;
/*     */   }
/*     */   
/*     */   private static double sunGeometricMeanLongitude(double t) {
/* 183 */     double L0 = 280.46646D + t * (36000.76983D + 3.032E-4D * t);
/* 184 */     L0 -= 360.0D * Math.floor(L0 / 360.0D);
/* 185 */     return L0;
/*     */   }
/*     */   
/*     */   private static double sunTrueLongitude(double t) {
/* 197 */     return sunGeometricMeanLongitude(t) + sunEquationOfCenter(t);
/*     */   }
/*     */   
/*     */   private static double sunApparentLongitude(double t) {
/* 207 */     double omega = Math.toRadians(125.04D - 1934.136D * t);
/* 208 */     return sunTrueLongitude(t) - 0.00569D - 0.00478D * Math.sin(omega);
/*     */   }
/*     */   
/*     */   private static double sunGeometricMeanAnomaly(double t) {
/* 218 */     return 357.52911D + t * (35999.05029D - 1.537E-4D * t);
/*     */   }
/*     */   
/*     */   private static double sunTrueAnomaly(double t) {
/* 228 */     return sunGeometricMeanAnomaly(t) + sunEquationOfCenter(t);
/*     */   }
/*     */   
/*     */   private static double eccentricityEarthOrbit(double t) {
/* 241 */     return 0.016708634D - t * (4.2037E-5D + 1.267E-7D * t);
/*     */   }
/*     */   
/*     */   private static double sunRadiusVector(double t) {
/* 251 */     double v = Math.toRadians(sunTrueAnomaly(t));
/* 252 */     double e = eccentricityEarthOrbit(t);
/* 253 */     return 1.000001018D * (1.0D - e * e) / (1.0D + e * Math.cos(v));
/*     */   }
/*     */   
/*     */   private static double meanObliquityOfEcliptic(double t) {
/* 263 */     double seconds = 21.448D - t * (46.815D + t * (5.9E-4D - t * 0.001813D));
/* 264 */     return 23.0D + (26.0D + seconds / 60.0D) / 60.0D;
/*     */   }
/*     */   
/*     */   private static double obliquityCorrected(double t) {
/* 275 */     double e0 = meanObliquityOfEcliptic(t);
/* 276 */     double omega = Math.toRadians(125.04D - 1934.136D * t);
/* 277 */     return e0 + 0.00256D * Math.cos(omega);
/*     */   }
/*     */   
/*     */   private static double sunRightAscension(double t) {
/* 292 */     double e = Math.toRadians(obliquityCorrected(t));
/* 293 */     double b = Math.toRadians(sunApparentLongitude(t));
/* 294 */     double y = Math.sin(b) * Math.cos(e);
/* 295 */     double x = Math.cos(b);
/* 296 */     double alpha = Math.atan2(y, x);
/* 297 */     return Math.toDegrees(alpha);
/*     */   }
/*     */   
/*     */   private static double sunDeclination(double t) {
/* 310 */     double e = Math.toRadians(obliquityCorrected(t));
/* 311 */     double b = Math.toRadians(sunApparentLongitude(t));
/* 312 */     double sint = Math.sin(e) * Math.sin(b);
/* 313 */     double theta = Math.asin(sint);
/* 314 */     return Math.toDegrees(theta);
/*     */   }
/*     */   
/*     */   private static double solarNoonTime(double lon, double eqTime) {
/* 326 */     return 720.0D + lon * 4.0D - eqTime;
/*     */   }
/*     */   
/*     */   private static double equationOfTime(double t) {
/* 344 */     double eps = Math.toRadians(obliquityCorrected(t));
/* 345 */     double l0 = Math.toRadians(sunGeometricMeanLongitude(t));
/* 346 */     double m = Math.toRadians(sunGeometricMeanAnomaly(t));
/* 347 */     double e = eccentricityEarthOrbit(t);
/* 348 */     double y = Math.tan(eps / 2.0D);
/* 349 */     y *= y;
/* 351 */     double sin2l0 = Math.sin(2.0D * l0);
/* 352 */     double cos2l0 = Math.cos(2.0D * l0);
/* 353 */     double sin4l0 = Math.sin(4.0D * l0);
/* 354 */     double sin1m = Math.sin(m);
/* 355 */     double sin2m = Math.sin(2.0D * m);
/* 357 */     double etime = y * sin2l0 - 2.0D * e * sin1m + 4.0D * e * y * sin1m * cos2l0 - 0.5D * y * y * sin4l0 - 1.25D * e * e * sin2m;
/* 360 */     return Math.toDegrees(etime) * 4.0D;
/*     */   }
/*     */   
/*     */   private static double refractionCorrection(double zenith) {
/* 375 */     double refractionCorrection, exoatmElevation = 90.0D - zenith;
/* 376 */     if (exoatmElevation > 85.0D)
/* 377 */       return 0.0D; 
/* 380 */     double te = Math.tan(Math.toRadians(exoatmElevation));
/* 381 */     if (exoatmElevation > 5.0D) {
/* 382 */       refractionCorrection = 58.1D / te - 0.07D / te * te * te + 8.6E-5D / te * te * te * te * te;
/* 384 */     } else if (exoatmElevation > -0.575D) {
/* 385 */       refractionCorrection = 1735.0D + exoatmElevation * (-518.2D + exoatmElevation * (103.4D + exoatmElevation * (-12.79D + exoatmElevation * 0.711D)));
/*     */     } else {
/* 391 */       refractionCorrection = -20.774D / te;
/*     */     } 
/* 394 */     return refractionCorrection / 3600.0D;
/*     */   }
/*     */   
/*     */   public SunRelativePosition() {}
/*     */   
/*     */   public SunRelativePosition(double twilight) throws IllegalArgumentException {
/* 412 */     setTwilight(twilight);
/*     */   }
/*     */   
/*     */   private void compute() {
/* 420 */     double latitude = this.latitude;
/* 421 */     double longitude = this.longitude;
/* 425 */     longitude = -longitude;
/* 429 */     double julianDay = Calendar.julianDay(this.time);
/* 430 */     double time = (julianDay - 2451545.0D) / 36525.0D;
/* 432 */     double solarDec = sunDeclination(time);
/* 433 */     double eqTime = equationOfTime(time);
/* 434 */     this.noonTime = Math.round(solarNoonTime(longitude, eqTime) * 60000.0D) + this.time / 86400000L * 86400000L;
/* 441 */     double trueSolarTime = (julianDay + 0.5D - Math.floor(julianDay + 0.5D)) * 1440.0D;
/* 442 */     trueSolarTime += eqTime - 4.0D * longitude;
/* 443 */     trueSolarTime -= 1440.0D * Math.floor(trueSolarTime / 1440.0D);
/* 449 */     longitude = Math.toRadians(longitude);
/* 450 */     latitude = Math.toRadians(latitude);
/* 451 */     solarDec = Math.toRadians(solarDec);
/* 453 */     double csz = Math.sin(latitude) * Math.sin(solarDec) + Math.cos(latitude) * Math.cos(solarDec) * Math.cos(Math.toRadians(trueSolarTime / 4.0D - 180.0D));
/* 458 */     if (csz > 1.0D)
/* 458 */       csz = 1.0D; 
/* 459 */     if (csz < -1.0D)
/* 459 */       csz = -1.0D; 
/* 461 */     double zenith = Math.acos(csz);
/* 462 */     double azDenom = Math.cos(latitude) * Math.sin(zenith);
/* 467 */     if (Math.abs(azDenom) > 0.001D) {
/* 468 */       double azRad = (Math.sin(latitude) * Math.cos(zenith) - Math.sin(solarDec)) / azDenom;
/* 469 */       if (azRad > 1.0D)
/* 469 */         azRad = 1.0D; 
/* 470 */       if (azRad < -1.0D)
/* 470 */         azRad = -1.0D; 
/* 472 */       this.azimuth = 180.0D - Math.toDegrees(Math.acos(azRad));
/* 473 */       if (trueSolarTime > 720.0D)
/* 474 */         this.azimuth = -this.azimuth; 
/*     */     } else {
/* 477 */       this.azimuth = (latitude > 0.0D) ? 180.0D : 0.0D;
/*     */     } 
/* 479 */     this.azimuth -= 360.0D * Math.floor(this.azimuth / 360.0D);
/* 484 */     double refractionCorrection = refractionCorrection(Math.toDegrees(zenith));
/* 485 */     double solarZen = Math.toDegrees(zenith) - refractionCorrection;
/* 487 */     this.elevation = 90.0D - solarZen;
/* 488 */     if (this.elevation < this.twilight) {
/* 490 */       this.azimuth = Double.NaN;
/* 491 */       this.elevation = Double.NaN;
/*     */     } 
/* 493 */     this.updated = true;
/*     */   }
/*     */   
/*     */   public void setCoordinate(double longitude, double latitude) {
/* 504 */     if (latitude > 89.8D)
/* 504 */       latitude = 89.8D; 
/* 505 */     if (latitude < -89.8D)
/* 505 */       latitude = -89.8D; 
/* 506 */     if (latitude != this.latitude || longitude != this.longitude) {
/* 507 */       this.latitude = latitude;
/* 508 */       this.longitude = longitude;
/* 509 */       this.updated = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setCoordinate(Point2D point) {
/* 520 */     setCoordinate(point.getX(), point.getY());
/*     */   }
/*     */   
/*     */   public Point2D getCoordinate() {
/* 530 */     return new Point2D.Double(this.longitude, this.latitude);
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/* 540 */     long time = date.getTime();
/* 541 */     if (time != this.time) {
/* 542 */       this.time = time;
/* 543 */       this.updated = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 553 */     return new Date(this.time);
/*     */   }
/*     */   
/*     */   public void setTwilight(double twilight) throws IllegalArgumentException {
/* 571 */     if (twilight < -90.0D || twilight > -90.0D)
/* 573 */       throw new IllegalArgumentException(String.valueOf(twilight)); 
/* 575 */     this.twilight = twilight;
/* 576 */     this.updated = false;
/*     */   }
/*     */   
/*     */   public double getTwilight() {
/* 584 */     return this.twilight;
/*     */   }
/*     */   
/*     */   public double getAzimuth() {
/* 593 */     if (!this.updated)
/* 594 */       compute(); 
/* 596 */     return this.azimuth;
/*     */   }
/*     */   
/*     */   public double getElevation() {
/* 605 */     if (!this.updated)
/* 606 */       compute(); 
/* 608 */     return this.elevation;
/*     */   }
/*     */   
/*     */   public long getNoonTime() {
/* 617 */     if (!this.updated)
/* 618 */       compute(); 
/* 620 */     return this.noonTime % 86400000L;
/*     */   }
/*     */   
/*     */   public Date getNoonDate() {
/* 629 */     if (!this.updated)
/* 630 */       compute(); 
/* 632 */     return new Date(this.noonTime);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws ParseException {
/* 645 */     DateFormat format = DateFormat.getDateTimeInstance(3, 3);
/* 646 */     format.setTimeZone(TimeZone.getTimeZone("UTC"));
/* 647 */     double longitude = 0.0D;
/* 648 */     double latitude = 0.0D;
/* 649 */     Date time = new Date();
/* 650 */     switch (args.length) {
/*     */       case 3:
/* 651 */         time = format.parse(args[2]);
/*     */       case 2:
/* 652 */         latitude = Double.parseDouble(args[1]);
/*     */       case 1:
/* 653 */         longitude = Double.parseDouble(args[0]);
/*     */         break;
/*     */     } 
/* 655 */     SunRelativePosition calculator = new SunRelativePosition();
/* 656 */     calculator.setDate(time);
/* 657 */     calculator.setCoordinate(longitude, latitude);
/* 658 */     System.out.print("Date (UTC): ");
/* 658 */     System.out.println(format.format(time));
/* 659 */     System.out.print("Longitude:  ");
/* 659 */     System.out.println(longitude);
/* 660 */     System.out.print("Latitude:   ");
/* 660 */     System.out.println(latitude);
/* 661 */     System.out.print("Elevation:  ");
/* 661 */     System.out.println(calculator.getElevation());
/* 662 */     System.out.print("Azimuth:    ");
/* 662 */     System.out.println(calculator.getAzimuth());
/* 663 */     System.out.print("Noon date:  ");
/* 663 */     System.out.println(format.format(calculator.getNoonDate()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\nature\SunRelativePosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */