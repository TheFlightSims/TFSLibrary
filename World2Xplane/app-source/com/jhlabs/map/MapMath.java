/*     */ package com.jhlabs.map;
/*     */ 
/*     */ import com.jhlabs.map.proj.ProjectionException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ 
/*     */ public class MapMath {
/*     */   public static final double HALFPI = 1.5707963267948966D;
/*     */   
/*     */   public static final double QUARTERPI = 0.7853981633974483D;
/*     */   
/*     */   public static final double TWOPI = 6.283185307179586D;
/*     */   
/*     */   public static final double RTD = 57.29577951308232D;
/*     */   
/*     */   public static final double DTR = 0.017453292519943295D;
/*     */   
/*  29 */   public static final Rectangle2D WORLD_BOUNDS_RAD = new Rectangle2D.Double(-3.141592653589793D, -1.5707963267948966D, 6.283185307179586D, Math.PI);
/*     */   
/*  30 */   public static final Rectangle2D WORLD_BOUNDS = new Rectangle2D.Double(-180.0D, -90.0D, 360.0D, 180.0D);
/*     */   
/*     */   public static final int DONT_INTERSECT = 0;
/*     */   
/*     */   public static final int DO_INTERSECT = 1;
/*     */   
/*     */   public static final int COLLINEAR = 2;
/*     */   
/*     */   private static final int N_ITER = 15;
/*     */   
/*     */   private static final double C00 = 1.0D;
/*     */   
/*     */   private static final double C02 = 0.25D;
/*     */   
/*     */   private static final double C04 = 0.046875D;
/*     */   
/*     */   private static final double C06 = 0.01953125D;
/*     */   
/*     */   private static final double C08 = 0.01068115234375D;
/*     */   
/*     */   private static final double C22 = 0.75D;
/*     */   
/*     */   private static final double C44 = 0.46875D;
/*     */   
/*     */   private static final double C46 = 0.013020833333333334D;
/*     */   
/*     */   private static final double C48 = 0.007120768229166667D;
/*     */   
/*     */   private static final double C66 = 0.3645833333333333D;
/*     */   
/*     */   private static final double C68 = 0.005696614583333333D;
/*     */   
/*     */   private static final double C88 = 0.3076171875D;
/*     */   
/*     */   private static final int MAX_ITER = 10;
/*     */   
/*     */   private static final double P00 = 0.3333333333333333D;
/*     */   
/*     */   private static final double P01 = 0.17222222222222222D;
/*     */   
/*     */   private static final double P02 = 0.10257936507936508D;
/*     */   
/*     */   private static final double P10 = 0.06388888888888888D;
/*     */   
/*     */   private static final double P11 = 0.0664021164021164D;
/*     */   
/*     */   private static final double P20 = 0.016415012942191543D;
/*     */   
/*     */   public static double sind(double v) {
/*  36 */     return Math.sin(v * 0.017453292519943295D);
/*     */   }
/*     */   
/*     */   public static double cosd(double v) {
/*  40 */     return Math.cos(v * 0.017453292519943295D);
/*     */   }
/*     */   
/*     */   public static double tand(double v) {
/*  44 */     return Math.tan(v * 0.017453292519943295D);
/*     */   }
/*     */   
/*     */   public static double asind(double v) {
/*  48 */     return Math.asin(v) * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public static double acosd(double v) {
/*  52 */     return Math.acos(v) * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public static double atand(double v) {
/*  56 */     return Math.atan(v) * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public static double atan2d(double y, double x) {
/*  60 */     return Math.atan2(y, x) * 57.29577951308232D;
/*     */   }
/*     */   
/*     */   public static double asin(double v) {
/*  64 */     if (Math.abs(v) > 1.0D)
/*  65 */       return (v < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D; 
/*  66 */     return Math.asin(v);
/*     */   }
/*     */   
/*     */   public static double acos(double v) {
/*  70 */     if (Math.abs(v) > 1.0D)
/*  71 */       return (v < 0.0D) ? Math.PI : 0.0D; 
/*  72 */     return Math.acos(v);
/*     */   }
/*     */   
/*     */   public static double sqrt(double v) {
/*  76 */     return (v < 0.0D) ? 0.0D : Math.sqrt(v);
/*     */   }
/*     */   
/*     */   public static double distance(double dx, double dy) {
/*  80 */     return Math.sqrt(dx * dx + dy * dy);
/*     */   }
/*     */   
/*     */   public static double distance(Point2D.Double a, Point2D.Double b) {
/*  84 */     return distance(a.x - b.x, a.y - b.y);
/*     */   }
/*     */   
/*     */   public static double hypot(double x, double y) {
/*  88 */     if (x < 0.0D) {
/*  89 */       x = -x;
/*  90 */     } else if (x == 0.0D) {
/*  91 */       return (y < 0.0D) ? -y : y;
/*     */     } 
/*  92 */     if (y < 0.0D) {
/*  93 */       y = -y;
/*  94 */     } else if (y == 0.0D) {
/*  95 */       return x;
/*     */     } 
/*  96 */     if (x < y) {
/*  97 */       x /= y;
/*  98 */       return y * Math.sqrt(1.0D + x * x);
/*     */     } 
/* 100 */     y /= x;
/* 101 */     return x * Math.sqrt(1.0D + y * y);
/*     */   }
/*     */   
/*     */   public static double atan2(double y, double x) {
/* 106 */     return Math.atan2(y, x);
/*     */   }
/*     */   
/*     */   public static double trunc(double v) {
/* 110 */     return (v < 0.0D) ? Math.ceil(v) : Math.floor(v);
/*     */   }
/*     */   
/*     */   public static double frac(double v) {
/* 114 */     return v - trunc(v);
/*     */   }
/*     */   
/*     */   public static double degToRad(double v) {
/* 118 */     return v * Math.PI / 180.0D;
/*     */   }
/*     */   
/*     */   public static double radToDeg(double v) {
/* 122 */     return v * 180.0D / Math.PI;
/*     */   }
/*     */   
/*     */   public static double dmsToRad(double d, double m, double s) {
/* 127 */     if (d >= 0.0D)
/* 128 */       return (d + m / 60.0D + s / 3600.0D) * Math.PI / 180.0D; 
/* 129 */     return (d - m / 60.0D - s / 3600.0D) * Math.PI / 180.0D;
/*     */   }
/*     */   
/*     */   public static double dmsToDeg(double d, double m, double s) {
/* 134 */     if (d >= 0.0D)
/* 135 */       return d + m / 60.0D + s / 3600.0D; 
/* 136 */     return d - m / 60.0D - s / 3600.0D;
/*     */   }
/*     */   
/*     */   public static double normalizeLatitude(double angle) {
/* 140 */     if (Double.isInfinite(angle) || Double.isNaN(angle))
/* 141 */       throw new ProjectionException("Infinite latitude"); 
/* 142 */     while (angle > 1.5707963267948966D)
/* 143 */       angle -= Math.PI; 
/* 144 */     while (angle < -1.5707963267948966D)
/* 145 */       angle += Math.PI; 
/* 146 */     return angle;
/*     */   }
/*     */   
/*     */   public static double normalizeLongitude(double angle) {
/* 151 */     if (Double.isInfinite(angle) || Double.isNaN(angle))
/* 152 */       throw new ProjectionException("Infinite longitude"); 
/* 153 */     while (angle > Math.PI)
/* 154 */       angle -= 6.283185307179586D; 
/* 155 */     while (angle < -3.141592653589793D)
/* 156 */       angle += 6.283185307179586D; 
/* 157 */     return angle;
/*     */   }
/*     */   
/*     */   public static double normalizeAngle(double angle) {
/* 162 */     if (Double.isInfinite(angle) || Double.isNaN(angle))
/* 163 */       throw new ProjectionException("Infinite angle"); 
/* 164 */     while (angle > 6.283185307179586D)
/* 165 */       angle -= 6.283185307179586D; 
/* 166 */     while (angle < 0.0D)
/* 167 */       angle += 6.283185307179586D; 
/* 168 */     return angle;
/*     */   }
/*     */   
/*     */   public static double greatCircleDistance(double lon1, double lat1, double lon2, double lat2) {
/* 186 */     double dlat = Math.sin((lat2 - lat1) / 2.0D);
/* 187 */     double dlon = Math.sin((lon2 - lon1) / 2.0D);
/* 188 */     double r = Math.sqrt(dlat * dlat + Math.cos(lat1) * Math.cos(lat2) * dlon * dlon);
/* 189 */     return 2.0D * Math.asin(r);
/*     */   }
/*     */   
/*     */   public static double sphericalAzimuth(double lat0, double lon0, double lat, double lon) {
/* 193 */     double diff = lon - lon0;
/* 194 */     double coslat = Math.cos(lat);
/* 196 */     return Math.atan2(coslat * Math.sin(diff), Math.cos(lat0) * Math.sin(lat) - Math.sin(lat0) * coslat * Math.cos(diff));
/*     */   }
/*     */   
/*     */   public static boolean sameSigns(double a, double b) {
/* 204 */     return (((a < 0.0D) ? true : false) == ((b < 0.0D) ? true : false));
/*     */   }
/*     */   
/*     */   public static boolean sameSigns(int a, int b) {
/* 208 */     return (((a < 0) ? true : false) == ((b < 0) ? true : false));
/*     */   }
/*     */   
/*     */   public static double takeSign(double a, double b) {
/* 212 */     a = Math.abs(a);
/* 213 */     if (b < 0.0D)
/* 214 */       return -a; 
/* 215 */     return a;
/*     */   }
/*     */   
/*     */   public static int takeSign(int a, int b) {
/* 219 */     a = Math.abs(a);
/* 220 */     if (b < 0)
/* 221 */       return -a; 
/* 222 */     return a;
/*     */   }
/*     */   
/*     */   public static int intersectSegments(Point2D.Double aStart, Point2D.Double aEnd, Point2D.Double bStart, Point2D.Double bEnd, Point2D.Double p) {
/* 234 */     double a1 = aEnd.y - aStart.y;
/* 235 */     double b1 = aStart.x - aEnd.x;
/* 236 */     double c1 = aEnd.x * aStart.y - aStart.x * aEnd.y;
/* 237 */     double r3 = a1 * bStart.x + b1 * bStart.y + c1;
/* 238 */     double r4 = a1 * bEnd.x + b1 * bEnd.y + c1;
/* 240 */     if (r3 != 0.0D && r4 != 0.0D && sameSigns(r3, r4))
/* 241 */       return 0; 
/* 243 */     double a2 = bEnd.y - bStart.y;
/* 244 */     double b2 = bStart.x - bEnd.x;
/* 245 */     double c2 = bEnd.x * bStart.y - bStart.x * bEnd.y;
/* 246 */     double r1 = a2 * aStart.x + b2 * aStart.y + c2;
/* 247 */     double r2 = a2 * aEnd.x + b2 * aEnd.y + c2;
/* 249 */     if (r1 != 0.0D && r2 != 0.0D && sameSigns(r1, r2))
/* 250 */       return 0; 
/* 252 */     double denom = a1 * b2 - a2 * b1;
/* 253 */     if (denom == 0.0D)
/* 254 */       return 2; 
/* 256 */     double offset = (denom < 0.0D) ? (-denom / 2.0D) : (denom / 2.0D);
/* 258 */     double num = b1 * c2 - b2 * c1;
/* 259 */     p.x = ((num < 0.0D) ? (num - offset) : (num + offset)) / denom;
/* 261 */     num = a2 * c1 - a1 * c2;
/* 262 */     p.y = ((num < 0.0D) ? (num - offset) : (num + offset)) / denom;
/* 264 */     return 1;
/*     */   }
/*     */   
/*     */   public static double dot(Point2D.Double a, Point2D.Double b) {
/* 268 */     return a.x * b.x + a.y * b.y;
/*     */   }
/*     */   
/*     */   public static Point2D.Double perpendicular(Point2D.Double a) {
/* 272 */     return new Point2D.Double(-a.y, a.x);
/*     */   }
/*     */   
/*     */   public static Point2D.Double add(Point2D.Double a, Point2D.Double b) {
/* 276 */     return new Point2D.Double(a.x + b.x, a.y + b.y);
/*     */   }
/*     */   
/*     */   public static Point2D.Double subtract(Point2D.Double a, Point2D.Double b) {
/* 280 */     return new Point2D.Double(a.x - b.x, a.y - b.y);
/*     */   }
/*     */   
/*     */   public static Point2D.Double multiply(Point2D.Double a, Point2D.Double b) {
/* 284 */     return new Point2D.Double(a.x * b.x, a.y * b.y);
/*     */   }
/*     */   
/*     */   public static double cross(Point2D.Double a, Point2D.Double b) {
/* 288 */     return a.x * b.y - b.x * a.y;
/*     */   }
/*     */   
/*     */   public static double cross(double x1, double y1, double x2, double y2) {
/* 292 */     return x1 * y2 - x2 * y1;
/*     */   }
/*     */   
/*     */   public static void normalize(Point2D.Double a) {
/* 296 */     double d = distance(a.x, a.y);
/* 297 */     a.x /= d;
/* 298 */     a.y /= d;
/*     */   }
/*     */   
/*     */   public static void negate(Point2D.Double a) {
/* 302 */     a.x = -a.x;
/* 303 */     a.y = -a.y;
/*     */   }
/*     */   
/*     */   public static double longitudeDistance(double l1, double l2) {
/* 307 */     return Math.min(Math.abs(l1 - l2), ((l1 < 0.0D) ? (l1 + Math.PI) : (Math.PI - l1)) + ((l2 < 0.0D) ? (l2 + Math.PI) : (Math.PI - l2)));
/*     */   }
/*     */   
/*     */   public static double geocentricLatitude(double lat, double flatness) {
/* 314 */     double f = 1.0D - flatness;
/* 315 */     return Math.atan(f * f * Math.tan(lat));
/*     */   }
/*     */   
/*     */   public static double geographicLatitude(double lat, double flatness) {
/* 319 */     double f = 1.0D - flatness;
/* 320 */     return Math.atan(Math.tan(lat) / f * f);
/*     */   }
/*     */   
/*     */   public static double tsfn(double phi, double sinphi, double e) {
/* 324 */     sinphi *= e;
/* 325 */     return Math.tan(0.5D * (1.5707963267948966D - phi)) / Math.pow((1.0D - sinphi) / (1.0D + sinphi), 0.5D * e);
/*     */   }
/*     */   
/*     */   public static double msfn(double sinphi, double cosphi, double es) {
/* 330 */     return cosphi / Math.sqrt(1.0D - es * sinphi * sinphi);
/*     */   }
/*     */   
/*     */   public static double phi2(double ts, double e) {
/* 339 */     double dphi, eccnth = 0.5D * e;
/* 340 */     double phi = 1.5707963267948966D - 2.0D * Math.atan(ts);
/* 341 */     int i = 15;
/*     */     do {
/* 343 */       double con = e * Math.sin(phi);
/* 344 */       dphi = 1.5707963267948966D - 2.0D * Math.atan(ts * Math.pow((1.0D - con) / (1.0D + con), eccnth)) - phi;
/* 345 */       phi += dphi;
/* 346 */     } while (Math.abs(dphi) > 1.0E-10D && --i != 0);
/* 347 */     if (i <= 0)
/* 348 */       throw new ProjectionException(); 
/* 349 */     return phi;
/*     */   }
/*     */   
/*     */   public static double[] enfn(double es) {
/* 368 */     double[] en = new double[5];
/* 369 */     en[0] = 1.0D - es * (0.25D + es * (0.046875D + es * (0.01953125D + es * 0.01068115234375D)));
/* 370 */     en[1] = es * (0.75D - es * (0.046875D + es * (0.01953125D + es * 0.01068115234375D)));
/*     */     double t;
/* 371 */     en[2] = (t = es * es) * (0.46875D - es * (0.013020833333333334D + es * 0.007120768229166667D));
/* 372 */     en[3] = (t *= es) * (0.3645833333333333D - es * 0.005696614583333333D);
/* 373 */     en[4] = t * es * 0.3076171875D;
/* 374 */     return en;
/*     */   }
/*     */   
/*     */   public static double mlfn(double phi, double sphi, double cphi, double[] en) {
/* 378 */     cphi *= sphi;
/* 379 */     sphi *= sphi;
/* 380 */     return en[0] * phi - cphi * (en[1] + sphi * (en[2] + sphi * (en[3] + sphi * en[4])));
/*     */   }
/*     */   
/*     */   public static double inv_mlfn(double arg, double es, double[] en) {
/* 384 */     double k = 1.0D / (1.0D - es);
/* 386 */     double phi = arg;
/* 387 */     for (int i = 10; i != 0; i--) {
/* 388 */       double s = Math.sin(phi);
/* 389 */       double t = 1.0D - es * s * s;
/* 390 */       phi -= t = (mlfn(phi, s, Math.cos(phi), en) - arg) * t * Math.sqrt(t) * k;
/* 391 */       if (Math.abs(t) < 1.0E-11D)
/* 392 */         return phi; 
/*     */     } 
/* 394 */     return phi;
/*     */   }
/*     */   
/*     */   public static double[] authset(double es) {
/* 406 */     double[] APA = new double[3];
/* 407 */     APA[0] = es * 0.3333333333333333D;
/* 408 */     double t = es * es;
/* 409 */     APA[0] = APA[0] + t * 0.17222222222222222D;
/* 410 */     APA[1] = t * 0.06388888888888888D;
/* 411 */     t *= es;
/* 412 */     APA[0] = APA[0] + t * 0.10257936507936508D;
/* 413 */     APA[1] = APA[1] + t * 0.0664021164021164D;
/* 414 */     APA[2] = t * 0.016415012942191543D;
/* 415 */     return APA;
/*     */   }
/*     */   
/*     */   public static double authlat(double beta, double[] APA) {
/* 419 */     double t = beta + beta;
/* 420 */     return beta + APA[0] * Math.sin(t) + APA[1] * Math.sin(t + t) + APA[2] * Math.sin(t + t + t);
/*     */   }
/*     */   
/*     */   public static double qsfn(double sinphi, double e, double one_es) {
/* 426 */     if (e >= 1.0E-7D) {
/* 427 */       double con = e * sinphi;
/* 428 */       return one_es * (sinphi / (1.0D - con * con) - 0.5D / e * Math.log((1.0D - con) / (1.0D + con)));
/*     */     } 
/* 431 */     return sinphi + sinphi;
/*     */   }
/*     */   
/*     */   public static double niceNumber(double x, boolean round) {
/*     */     double nf;
/* 444 */     int expv = (int)Math.floor(Math.log(x) / Math.log(10.0D));
/* 445 */     double f = x / Math.pow(10.0D, expv);
/* 446 */     if (round) {
/* 447 */       if (f < 1.5D) {
/* 448 */         nf = 1.0D;
/* 449 */       } else if (f < 3.0D) {
/* 450 */         nf = 2.0D;
/* 451 */       } else if (f < 7.0D) {
/* 452 */         nf = 5.0D;
/*     */       } else {
/* 454 */         nf = 10.0D;
/*     */       } 
/* 455 */     } else if (f <= 1.0D) {
/* 456 */       nf = 1.0D;
/* 457 */     } else if (f <= 2.0D) {
/* 458 */       nf = 2.0D;
/* 459 */     } else if (f <= 5.0D) {
/* 460 */       nf = 5.0D;
/*     */     } else {
/* 462 */       nf = 10.0D;
/*     */     } 
/* 463 */     return nf * Math.pow(10.0D, expv);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\MapMath.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */