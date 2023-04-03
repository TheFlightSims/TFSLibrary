/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ import math.geom2d.Point2D;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class CoordinateConversion {
/*     */   public double[] utm2LatLon(String UTM) {
/*  40 */     UTM2LatLon c = new UTM2LatLon();
/*  41 */     return c.convertUTMToLatLong(UTM);
/*     */   }
/*     */   
/*     */   public Point2D latLon2UTM(double latitude, double longitude) {
/*  46 */     LatLon2UTM c = new LatLon2UTM();
/*  47 */     return c.convertLatLonToUTM(latitude, longitude);
/*     */   }
/*     */   
/*     */   private void validate(double latitude, double longitude) {
/*  53 */     if (latitude < -90.0D || latitude > 90.0D || longitude < -180.0D || longitude >= 180.0D)
/*  56 */       throw new IllegalArgumentException("Legal ranges: latitude [-90,90], longitude [-180,180)."); 
/*     */   }
/*     */   
/*     */   public String latLon2MGRUTM(double latitude, double longitude) {
/*  64 */     LatLon2MGRUTM c = new LatLon2MGRUTM();
/*  65 */     return c.convertLatLonToMGRUTM(latitude, longitude);
/*     */   }
/*     */   
/*     */   public double[] mgrutm2LatLon(String MGRUTM) {
/*  71 */     MGRUTM2LatLon c = new MGRUTM2LatLon();
/*  72 */     return c.convertMGRUTMToLatLong(MGRUTM);
/*     */   }
/*     */   
/*     */   public double degreeToRadian(double degree) {
/*  77 */     return degree * Math.PI / 180.0D;
/*     */   }
/*     */   
/*     */   public double radianToDegree(double radian) {
/*  82 */     return radian * 180.0D / Math.PI;
/*     */   }
/*     */   
/*     */   private double POW(double a, double b) {
/*  87 */     return FastMath.pow(a, b);
/*     */   }
/*     */   
/*     */   private double SIN(double value) {
/*  92 */     return FastMath.sin(value);
/*     */   }
/*     */   
/*     */   private double COS(double value) {
/*  97 */     return FastMath.cos(value);
/*     */   }
/*     */   
/*     */   private double TAN(double value) {
/* 102 */     return FastMath.tan(value);
/*     */   }
/*     */   
/*     */   private class LatLon2UTM {
/*     */     public Point2D convertLatLonToUTM(double latitude, double longitude) {
/* 109 */       CoordinateConversion.this.validate(latitude, longitude);
/* 110 */       String UTM = "";
/* 112 */       setVariables(latitude, longitude);
/* 114 */       String longZone = getLongZone(longitude);
/* 115 */       CoordinateConversion.LatZones latZones = new CoordinateConversion.LatZones();
/* 116 */       String latZone = latZones.getLatZone(latitude);
/* 118 */       double _easting = getEasting();
/* 119 */       double _northing = getNorthing(latitude);
/* 121 */       UTM = longZone + " " + latZone + " " + (int)_easting + " " + (int)_northing;
/* 126 */       return new Point2D(_easting, _northing);
/*     */     }
/*     */     
/*     */     protected void setVariables(double latitude, double longitude) {
/*     */       double var1;
/* 132 */       latitude = CoordinateConversion.this.degreeToRadian(latitude);
/* 133 */       this.rho = this.equatorialRadius * (1.0D - this.e * this.e) / CoordinateConversion.this.POW(1.0D - CoordinateConversion.this.POW(this.e * CoordinateConversion.this.SIN(latitude), 2.0D), 1.5D);
/* 136 */       this.nu = this.equatorialRadius / CoordinateConversion.this.POW(1.0D - CoordinateConversion.this.POW(this.e * CoordinateConversion.this.SIN(latitude), 2.0D), 0.5D);
/* 139 */       if (longitude < 0.0D) {
/* 141 */         var1 = ((int)((180.0D + longitude) / 6.0D) + 1);
/*     */       } else {
/* 145 */         var1 = ((int)(longitude / 6.0D) + 31);
/*     */       } 
/* 147 */       double var2 = 6.0D * var1 - 183.0D;
/* 148 */       double var3 = longitude - var2;
/* 149 */       this.p = var3 * 3600.0D / 10000.0D;
/* 151 */       this.S = this.A0 * latitude - this.B0 * CoordinateConversion.this.SIN(2.0D * latitude) + this.C0 * CoordinateConversion.this.SIN(4.0D * latitude) - this.D0 * CoordinateConversion.this.SIN(6.0D * latitude) + this.E0 * CoordinateConversion.this.SIN(8.0D * latitude);
/* 154 */       this.K1 = this.S * this.k0;
/* 155 */       this.K2 = this.nu * CoordinateConversion.this.SIN(latitude) * CoordinateConversion.this.COS(latitude) * CoordinateConversion.this.POW(this.sin1, 2.0D) * this.k0 * 1.0E8D / 2.0D;
/* 157 */       this.K3 = CoordinateConversion.this.POW(this.sin1, 4.0D) * this.nu * CoordinateConversion.this.SIN(latitude) * FastMath.pow(CoordinateConversion.this.COS(latitude), 3.0D) / 24.0D * (5.0D - CoordinateConversion.this.POW(CoordinateConversion.this.TAN(latitude), 2.0D) + 9.0D * this.e1sq * CoordinateConversion.this.POW(CoordinateConversion.this.COS(latitude), 2.0D) + 4.0D * CoordinateConversion.this.POW(this.e1sq, 2.0D) * CoordinateConversion.this.POW(CoordinateConversion.this.COS(latitude), 4.0D)) * this.k0 * 1.0E16D;
/* 163 */       this.K4 = this.nu * CoordinateConversion.this.COS(latitude) * this.sin1 * this.k0 * 10000.0D;
/* 165 */       this.K5 = CoordinateConversion.this.POW(this.sin1 * CoordinateConversion.this.COS(latitude), 3.0D) * this.nu / 6.0D * (1.0D - CoordinateConversion.this.POW(CoordinateConversion.this.TAN(latitude), 2.0D) + this.e1sq * CoordinateConversion.this.POW(CoordinateConversion.this.COS(latitude), 2.0D)) * this.k0 * 1.0E12D;
/* 169 */       this.A6 = CoordinateConversion.this.POW(this.p * this.sin1, 6.0D) * this.nu * CoordinateConversion.this.SIN(latitude) * CoordinateConversion.this.POW(CoordinateConversion.this.COS(latitude), 5.0D) / 720.0D * (61.0D - 58.0D * CoordinateConversion.this.POW(CoordinateConversion.this.TAN(latitude), 2.0D) + CoordinateConversion.this.POW(CoordinateConversion.this.TAN(latitude), 4.0D) + 270.0D * this.e1sq * CoordinateConversion.this.POW(CoordinateConversion.this.COS(latitude), 2.0D) - 330.0D * this.e1sq * CoordinateConversion.this.POW(CoordinateConversion.this.SIN(latitude), 2.0D)) * this.k0 * 1.0E24D;
/*     */     }
/*     */     
/*     */     protected String getLongZone(double longitude) {
/* 178 */       double longZone = 0.0D;
/* 179 */       if (longitude < 0.0D) {
/* 181 */         longZone = (180.0D + longitude) / 6.0D + 1.0D;
/*     */       } else {
/* 185 */         longZone = longitude / 6.0D + 31.0D;
/*     */       } 
/* 187 */       String val = String.valueOf((int)longZone);
/* 188 */       if (val.length() == 1)
/* 190 */         val = "0" + val; 
/* 192 */       return val;
/*     */     }
/*     */     
/*     */     protected double getNorthing(double latitude) {
/* 197 */       double northing = this.K1 + this.K2 * this.p * this.p + this.K3 * CoordinateConversion.this.POW(this.p, 4.0D);
/* 198 */       if (latitude < 0.0D)
/* 200 */         northing = 1.0E7D + northing; 
/* 202 */       return northing;
/*     */     }
/*     */     
/*     */     protected double getEasting() {
/* 207 */       return 500000.0D + this.K4 * this.p + this.K5 * CoordinateConversion.this.POW(this.p, 3.0D);
/*     */     }
/*     */     
/* 213 */     double equatorialRadius = 6378137.0D;
/*     */     
/* 216 */     double polarRadius = 6356752.314D;
/*     */     
/* 219 */     double flattening = 0.00335281066474748D;
/*     */     
/* 222 */     double inverseFlattening = 298.257223563D;
/*     */     
/* 225 */     double rm = CoordinateConversion.this.POW(this.equatorialRadius * this.polarRadius, 0.5D);
/*     */     
/* 228 */     double k0 = 0.9996D;
/*     */     
/* 231 */     double e = FastMath.sqrt(1.0D - CoordinateConversion.this.POW(this.polarRadius / this.equatorialRadius, 2.0D));
/*     */     
/* 233 */     double e1sq = this.e * this.e / (1.0D - this.e * this.e);
/*     */     
/* 235 */     double n = (this.equatorialRadius - this.polarRadius) / (this.equatorialRadius + this.polarRadius);
/*     */     
/* 239 */     double rho = 6368573.744D;
/*     */     
/* 242 */     double nu = 6389236.914D;
/*     */     
/* 246 */     double S = 5103266.421D;
/*     */     
/* 248 */     double A0 = 6367449.146D;
/*     */     
/* 250 */     double B0 = 16038.42955D;
/*     */     
/* 252 */     double C0 = 16.83261333D;
/*     */     
/* 254 */     double D0 = 0.021984404D;
/*     */     
/* 256 */     double E0 = 3.12705E-4D;
/*     */     
/* 260 */     double p = -0.483084D;
/*     */     
/* 262 */     double sin1 = 4.84814E-6D;
/*     */     
/* 265 */     double K1 = 5101225.115D;
/*     */     
/* 267 */     double K2 = 3750.291596D;
/*     */     
/* 269 */     double K3 = 1.397608151D;
/*     */     
/* 271 */     double K4 = 214839.3105D;
/*     */     
/* 273 */     double K5 = -2.995382942D;
/*     */     
/* 275 */     double A6 = -1.00541E-7D;
/*     */     
/*     */     private LatLon2UTM() {}
/*     */   }
/*     */   
/*     */   private class LatLon2MGRUTM extends LatLon2UTM {
/*     */     private LatLon2MGRUTM() {}
/*     */     
/*     */     public String convertLatLonToMGRUTM(double latitude, double longitude) {
/* 283 */       CoordinateConversion.this.validate(latitude, longitude);
/* 284 */       String mgrUTM = "";
/* 286 */       setVariables(latitude, longitude);
/* 288 */       String longZone = getLongZone(longitude);
/* 289 */       CoordinateConversion.LatZones latZones = new CoordinateConversion.LatZones();
/* 290 */       String latZone = latZones.getLatZone(latitude);
/* 292 */       double _easting = getEasting();
/* 293 */       double _northing = getNorthing(latitude);
/* 294 */       CoordinateConversion.Digraphs digraphs = new CoordinateConversion.Digraphs();
/* 295 */       String digraph1 = digraphs.getDigraph1(Integer.parseInt(longZone), _easting);
/* 297 */       String digraph2 = digraphs.getDigraph2(Integer.parseInt(longZone), _northing);
/* 300 */       String easting = String.valueOf((int)_easting);
/* 301 */       if (easting.length() < 5)
/* 303 */         easting = "00000" + easting; 
/* 305 */       easting = easting.substring(easting.length() - 5);
/* 308 */       String northing = String.valueOf((int)_northing);
/* 309 */       if (northing.length() < 5)
/* 311 */         northing = "0000" + northing; 
/* 313 */       northing = northing.substring(northing.length() - 5);
/* 315 */       mgrUTM = longZone + latZone + digraph1 + digraph2 + easting + northing;
/* 316 */       return mgrUTM;
/*     */     }
/*     */   }
/*     */   
/*     */   private class MGRUTM2LatLon extends UTM2LatLon {
/*     */     private MGRUTM2LatLon() {}
/*     */     
/*     */     public double[] convertMGRUTMToLatLong(String mgrutm) {
/* 324 */       double[] latlon = { 0.0D, 0.0D };
/* 326 */       int zone = Integer.parseInt(mgrutm.substring(0, 2));
/* 327 */       String latZone = mgrutm.substring(2, 3);
/* 329 */       String digraph1 = mgrutm.substring(3, 4);
/* 330 */       String digraph2 = mgrutm.substring(4, 5);
/* 331 */       this.easting = Double.parseDouble(mgrutm.substring(5, 10));
/* 332 */       this.northing = Double.parseDouble(mgrutm.substring(10, 15));
/* 334 */       CoordinateConversion.LatZones lz = new CoordinateConversion.LatZones();
/* 335 */       double latZoneDegree = lz.getLatZoneDegree(latZone);
/* 337 */       double a1 = latZoneDegree * 4.0E7D / 360.0D;
/* 338 */       double a2 = 2000000.0D * FastMath.floor(a1 / 2000000.0D);
/* 340 */       CoordinateConversion.Digraphs digraphs = new CoordinateConversion.Digraphs();
/* 342 */       double digraph2Index = digraphs.getDigraph2Index(digraph2);
/* 344 */       double startindexEquator = 1.0D;
/* 345 */       if (1 + zone % 2 == 1)
/* 347 */         startindexEquator = 6.0D; 
/* 350 */       double a3 = a2 + (digraph2Index - startindexEquator) * 100000.0D;
/* 351 */       if (a3 <= 0.0D)
/* 353 */         a3 = 1.0E7D + a3; 
/* 355 */       this.northing = a3 + this.northing;
/* 357 */       this.zoneCM = (-183 + 6 * zone);
/* 358 */       double digraph1Index = digraphs.getDigraph1Index(digraph1);
/* 359 */       int a5 = 1 + zone % 3;
/* 360 */       double[] a6 = { 16.0D, 0.0D, 8.0D };
/* 361 */       double a7 = 100000.0D * (digraph1Index - a6[a5 - 1]);
/* 362 */       this.easting += a7;
/* 364 */       setVariables();
/* 366 */       double latitude = 0.0D;
/* 367 */       latitude = 180.0D * (this.phi1 - this.fact1 * (this.fact2 + this.fact3 + this.fact4)) / Math.PI;
/* 369 */       if (latZoneDegree < 0.0D)
/* 371 */         latitude = 90.0D - latitude; 
/* 374 */       double d = this._a2 * 180.0D / Math.PI;
/* 375 */       double longitude = this.zoneCM - d;
/* 377 */       if (getHemisphere(latZone).equals("S"))
/* 379 */         latitude = -latitude; 
/* 382 */       latlon[0] = latitude;
/* 383 */       latlon[1] = longitude;
/* 384 */       return latlon;
/*     */     }
/*     */   }
/*     */   
/*     */   private class UTM2LatLon {
/*     */     double easting;
/*     */     
/*     */     double northing;
/*     */     
/*     */     int zone;
/*     */     
/* 396 */     String southernHemisphere = "ACDEFGHJKLM";
/*     */     
/*     */     double arc;
/*     */     
/*     */     double mu;
/*     */     
/*     */     double ei;
/*     */     
/*     */     double ca;
/*     */     
/*     */     double cb;
/*     */     
/*     */     double cc;
/*     */     
/*     */     double cd;
/*     */     
/*     */     double n0;
/*     */     
/*     */     double r0;
/*     */     
/*     */     double _a1;
/*     */     
/*     */     double dd0;
/*     */     
/*     */     double t0;
/*     */     
/*     */     double Q0;
/*     */     
/*     */     double lof1;
/*     */     
/*     */     double lof2;
/*     */     
/*     */     double lof3;
/*     */     
/*     */     double _a2;
/*     */     
/*     */     double phi1;
/*     */     
/*     */     double fact1;
/*     */     
/*     */     double fact2;
/*     */     
/*     */     double fact3;
/*     */     
/*     */     double fact4;
/*     */     
/*     */     double zoneCM;
/*     */     
/*     */     double _a3;
/*     */     
/*     */     protected String getHemisphere(String latZone) {
/* 400 */       String hemisphere = "N";
/* 401 */       if (this.southernHemisphere.indexOf(latZone) > -1)
/* 403 */         hemisphere = "S"; 
/* 405 */       return hemisphere;
/*     */     }
/*     */     
/*     */     public double[] convertUTMToLatLong(String UTM) {
/* 410 */       double[] latlon = { 0.0D, 0.0D };
/* 411 */       String[] utm = UTM.split(" ");
/* 412 */       this.zone = Integer.parseInt(utm[0]);
/* 413 */       String latZone = utm[1];
/* 414 */       this.easting = Double.parseDouble(utm[2]);
/* 415 */       this.northing = Double.parseDouble(utm[3]);
/* 416 */       String hemisphere = getHemisphere(latZone);
/* 417 */       double latitude = 0.0D;
/* 418 */       double longitude = 0.0D;
/* 420 */       if (hemisphere.equals("S"))
/* 422 */         this.northing = 1.0E7D - this.northing; 
/* 424 */       setVariables();
/* 425 */       latitude = 180.0D * (this.phi1 - this.fact1 * (this.fact2 + this.fact3 + this.fact4)) / Math.PI;
/* 427 */       if (this.zone > 0) {
/* 429 */         this.zoneCM = (6 * this.zone) - 183.0D;
/*     */       } else {
/* 433 */         this.zoneCM = 3.0D;
/*     */       } 
/* 437 */       longitude = this.zoneCM - this._a3;
/* 438 */       if (hemisphere.equals("S"))
/* 440 */         latitude = -latitude; 
/* 443 */       latlon[0] = latitude;
/* 444 */       latlon[1] = longitude;
/* 445 */       return latlon;
/*     */     }
/*     */     
/*     */     protected void setVariables() {
/* 451 */       this.arc = this.northing / this.k0;
/* 452 */       this.mu = this.arc / this.a * (1.0D - CoordinateConversion.this.POW(this.e, 2.0D) / 4.0D - 3.0D * CoordinateConversion.this.POW(this.e, 4.0D) / 64.0D - 5.0D * CoordinateConversion.this.POW(this.e, 6.0D) / 256.0D);
/* 455 */       this.ei = (1.0D - CoordinateConversion.this.POW(1.0D - this.e * this.e, 0.5D)) / (1.0D + CoordinateConversion.this.POW(1.0D - this.e * this.e, 0.5D));
/* 458 */       this.ca = 3.0D * this.ei / 2.0D - 27.0D * CoordinateConversion.this.POW(this.ei, 3.0D) / 32.0D;
/* 460 */       this.cb = 21.0D * CoordinateConversion.this.POW(this.ei, 2.0D) / 16.0D - 55.0D * CoordinateConversion.this.POW(this.ei, 4.0D) / 32.0D;
/* 461 */       this.cc = 151.0D * CoordinateConversion.this.POW(this.ei, 3.0D) / 96.0D;
/* 462 */       this.cd = 1097.0D * CoordinateConversion.this.POW(this.ei, 4.0D) / 512.0D;
/* 463 */       this.phi1 = this.mu + this.ca * CoordinateConversion.this.SIN(2.0D * this.mu) + this.cb * CoordinateConversion.this.SIN(4.0D * this.mu) + this.cc * CoordinateConversion.this.SIN(6.0D * this.mu) + this.cd * CoordinateConversion.this.SIN(8.0D * this.mu);
/* 466 */       this.n0 = this.a / CoordinateConversion.this.POW(1.0D - CoordinateConversion.this.POW(this.e * CoordinateConversion.this.SIN(this.phi1), 2.0D), 0.5D);
/* 468 */       this.r0 = this.a * (1.0D - this.e * this.e) / CoordinateConversion.this.POW(1.0D - CoordinateConversion.this.POW(this.e * CoordinateConversion.this.SIN(this.phi1), 2.0D), 1.5D);
/* 469 */       this.fact1 = this.n0 * CoordinateConversion.this.TAN(this.phi1) / this.r0;
/* 471 */       this._a1 = 500000.0D - this.easting;
/* 472 */       this.dd0 = this._a1 / this.n0 * this.k0;
/* 473 */       this.fact2 = this.dd0 * this.dd0 / 2.0D;
/* 475 */       this.t0 = CoordinateConversion.this.POW(CoordinateConversion.this.TAN(this.phi1), 2.0D);
/* 476 */       this.Q0 = this.e1sq * CoordinateConversion.this.POW(CoordinateConversion.this.COS(this.phi1), 2.0D);
/* 477 */       this.fact3 = (5.0D + 3.0D * this.t0 + 10.0D * this.Q0 - 4.0D * this.Q0 * this.Q0 - 9.0D * this.e1sq) * CoordinateConversion.this.POW(this.dd0, 4.0D) / 24.0D;
/* 480 */       this.fact4 = (61.0D + 90.0D * this.t0 + 298.0D * this.Q0 + 45.0D * this.t0 * this.t0 - 252.0D * this.e1sq - 3.0D * this.Q0 * this.Q0) * CoordinateConversion.this.POW(this.dd0, 6.0D) / 720.0D;
/* 485 */       this.lof1 = this._a1 / this.n0 * this.k0;
/* 486 */       this.lof2 = (1.0D + 2.0D * this.t0 + this.Q0) * CoordinateConversion.this.POW(this.dd0, 3.0D) / 6.0D;
/* 487 */       this.lof3 = (5.0D - 2.0D * this.Q0 + 28.0D * this.t0 - 3.0D * CoordinateConversion.this.POW(this.Q0, 2.0D) + 8.0D * this.e1sq + 24.0D * CoordinateConversion.this.POW(this.t0, 2.0D)) * CoordinateConversion.this.POW(this.dd0, 5.0D) / 120.0D;
/* 489 */       this._a2 = (this.lof1 - this.lof2 + this.lof3) / CoordinateConversion.this.COS(this.phi1);
/* 490 */       this._a3 = this._a2 * 180.0D / Math.PI;
/*     */     }
/*     */     
/* 542 */     double b = 6356752.314D;
/*     */     
/* 544 */     double a = 6378137.0D;
/*     */     
/* 546 */     double e = 0.081819191D;
/*     */     
/* 548 */     double e1sq = 0.006739497D;
/*     */     
/* 550 */     double k0 = 0.9996D;
/*     */     
/*     */     private UTM2LatLon() {}
/*     */   }
/*     */   
/*     */   private class Digraphs {
/* 556 */     private Map digraph1 = new Hashtable<>();
/*     */     
/* 558 */     private Map digraph2 = new Hashtable<>();
/*     */     
/* 560 */     private String[] digraph1Array = new String[] { 
/* 560 */         "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", 
/* 560 */         "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", 
/* 560 */         "W", "X", "Y", "Z" };
/*     */     
/* 564 */     private String[] digraph2Array = new String[] { 
/* 564 */         "V", "A", "B", "C", "D", "E", "F", "G", "H", "J", 
/* 564 */         "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", 
/* 564 */         "V" };
/*     */     
/*     */     public Digraphs() {
/* 569 */       this.digraph1.put(new Integer(1), "A");
/* 570 */       this.digraph1.put(new Integer(2), "B");
/* 571 */       this.digraph1.put(new Integer(3), "C");
/* 572 */       this.digraph1.put(new Integer(4), "D");
/* 573 */       this.digraph1.put(new Integer(5), "E");
/* 574 */       this.digraph1.put(new Integer(6), "F");
/* 575 */       this.digraph1.put(new Integer(7), "G");
/* 576 */       this.digraph1.put(new Integer(8), "H");
/* 577 */       this.digraph1.put(new Integer(9), "J");
/* 578 */       this.digraph1.put(new Integer(10), "K");
/* 579 */       this.digraph1.put(new Integer(11), "L");
/* 580 */       this.digraph1.put(new Integer(12), "M");
/* 581 */       this.digraph1.put(new Integer(13), "N");
/* 582 */       this.digraph1.put(new Integer(14), "P");
/* 583 */       this.digraph1.put(new Integer(15), "Q");
/* 584 */       this.digraph1.put(new Integer(16), "R");
/* 585 */       this.digraph1.put(new Integer(17), "S");
/* 586 */       this.digraph1.put(new Integer(18), "T");
/* 587 */       this.digraph1.put(new Integer(19), "U");
/* 588 */       this.digraph1.put(new Integer(20), "V");
/* 589 */       this.digraph1.put(new Integer(21), "W");
/* 590 */       this.digraph1.put(new Integer(22), "X");
/* 591 */       this.digraph1.put(new Integer(23), "Y");
/* 592 */       this.digraph1.put(new Integer(24), "Z");
/* 594 */       this.digraph2.put(new Integer(0), "V");
/* 595 */       this.digraph2.put(new Integer(1), "A");
/* 596 */       this.digraph2.put(new Integer(2), "B");
/* 597 */       this.digraph2.put(new Integer(3), "C");
/* 598 */       this.digraph2.put(new Integer(4), "D");
/* 599 */       this.digraph2.put(new Integer(5), "E");
/* 600 */       this.digraph2.put(new Integer(6), "F");
/* 601 */       this.digraph2.put(new Integer(7), "G");
/* 602 */       this.digraph2.put(new Integer(8), "H");
/* 603 */       this.digraph2.put(new Integer(9), "J");
/* 604 */       this.digraph2.put(new Integer(10), "K");
/* 605 */       this.digraph2.put(new Integer(11), "L");
/* 606 */       this.digraph2.put(new Integer(12), "M");
/* 607 */       this.digraph2.put(new Integer(13), "N");
/* 608 */       this.digraph2.put(new Integer(14), "P");
/* 609 */       this.digraph2.put(new Integer(15), "Q");
/* 610 */       this.digraph2.put(new Integer(16), "R");
/* 611 */       this.digraph2.put(new Integer(17), "S");
/* 612 */       this.digraph2.put(new Integer(18), "T");
/* 613 */       this.digraph2.put(new Integer(19), "U");
/* 614 */       this.digraph2.put(new Integer(20), "V");
/*     */     }
/*     */     
/*     */     public int getDigraph1Index(String letter) {
/* 620 */       for (int i = 0; i < this.digraph1Array.length; i++) {
/* 622 */         if (this.digraph1Array[i].equals(letter))
/* 624 */           return i + 1; 
/*     */       } 
/* 628 */       return -1;
/*     */     }
/*     */     
/*     */     public int getDigraph2Index(String letter) {
/* 633 */       for (int i = 0; i < this.digraph2Array.length; i++) {
/* 635 */         if (this.digraph2Array[i].equals(letter))
/* 637 */           return i; 
/*     */       } 
/* 641 */       return -1;
/*     */     }
/*     */     
/*     */     public String getDigraph1(int longZone, double easting) {
/* 646 */       int a1 = longZone;
/* 647 */       double a2 = (8 * (a1 - 1) % 3 + 1);
/* 649 */       double a3 = easting;
/* 650 */       double a4 = a2 + (int)(a3 / 100000.0D) - 1.0D;
/* 651 */       return (String)this.digraph1.get(new Integer((int)FastMath.floor(a4)));
/*     */     }
/*     */     
/*     */     public String getDigraph2(int longZone, double northing) {
/* 656 */       int a1 = longZone;
/* 657 */       double a2 = (1 + 5 * (a1 - 1) % 2);
/* 658 */       double a3 = northing;
/* 659 */       double a4 = a2 + (int)(a3 / 100000.0D);
/* 660 */       a4 = (a2 + (int)(a3 / 100000.0D)) % 20.0D;
/* 661 */       a4 = FastMath.floor(a4);
/* 662 */       if (a4 < 0.0D)
/* 664 */         a4 += 19.0D; 
/* 666 */       return (String)this.digraph2.get(new Integer((int)FastMath.floor(a4)));
/*     */     }
/*     */   }
/*     */   
/*     */   private class LatZones {
/* 674 */     private char[] letters = new char[] { 
/* 674 */         'A', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 
/* 674 */         'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 
/* 674 */         'X', 'Z' };
/*     */     
/* 677 */     private int[] degrees = new int[] { 
/* 677 */         -90, -84, -72, -64, -56, -48, -40, -32, -24, -16, 
/* 677 */         -8, 0, 8, 16, 24, 32, 40, 48, 56, 64, 
/* 677 */         72, 84 };
/*     */     
/* 680 */     private char[] negLetters = new char[] { 
/* 680 */         'A', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 
/* 680 */         'M' };
/*     */     
/* 683 */     private int[] negDegrees = new int[] { 
/* 683 */         -90, -84, -72, -64, -56, -48, -40, -32, -24, -16, 
/* 683 */         -8 };
/*     */     
/* 686 */     private char[] posLetters = new char[] { 
/* 686 */         'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
/* 686 */         'Z' };
/*     */     
/* 689 */     private int[] posDegrees = new int[] { 
/* 689 */         0, 8, 16, 24, 32, 40, 48, 56, 64, 72, 
/* 689 */         84 };
/*     */     
/* 691 */     private int arrayLength = 22;
/*     */     
/*     */     public int getLatZoneDegree(String letter) {
/* 699 */       char ltr = letter.charAt(0);
/* 700 */       for (int i = 0; i < this.arrayLength; i++) {
/* 702 */         if (this.letters[i] == ltr)
/* 704 */           return this.degrees[i]; 
/*     */       } 
/* 707 */       return -100;
/*     */     }
/*     */     
/*     */     public String getLatZone(double latitude) {
/* 712 */       int latIndex = -2;
/* 713 */       int lat = (int)latitude;
/* 715 */       if (lat >= 0) {
/* 717 */         int len = this.posLetters.length;
/* 718 */         for (int i = 0; i < len; ) {
/* 720 */           if (lat == this.posDegrees[i]) {
/* 722 */             latIndex = i;
/*     */             break;
/*     */           } 
/* 726 */           if (lat > this.posDegrees[i]) {
/*     */             i++;
/*     */             continue;
/*     */           } 
/* 732 */           latIndex = i - 1;
/*     */         } 
/*     */       } else {
/* 739 */         int len = this.negLetters.length;
/* 740 */         for (int i = 0; i < len; i++) {
/* 742 */           if (lat == this.negDegrees[i]) {
/* 744 */             latIndex = i;
/*     */             break;
/*     */           } 
/* 748 */           if (lat < this.negDegrees[i]) {
/* 750 */             latIndex = i - 1;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 762 */       if (latIndex == -1)
/* 764 */         latIndex = 0; 
/* 766 */       if (lat >= 0) {
/* 768 */         if (latIndex == -2)
/* 770 */           latIndex = this.posLetters.length - 1; 
/* 772 */         return String.valueOf(this.posLetters[latIndex]);
/*     */       } 
/* 776 */       if (latIndex == -2)
/* 778 */         latIndex = this.negLetters.length - 1; 
/* 780 */       return String.valueOf(this.negLetters[latIndex]);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\CoordinateConversion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */