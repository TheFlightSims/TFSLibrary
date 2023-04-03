/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class Angle {
/*     */   public static final double PI_TIMES_2 = 6.283185307179586D;
/*     */   
/*     */   public static final double PI_OVER_2 = 1.5707963267948966D;
/*     */   
/*     */   public static final double PI_OVER_4 = 0.7853981633974483D;
/*     */   
/*     */   public static final int COUNTERCLOCKWISE = 1;
/*     */   
/*     */   public static final int CLOCKWISE = -1;
/*     */   
/*     */   public static final int NONE = 0;
/*     */   
/*     */   public static double toDegrees(double radians) {
/*  63 */     return radians * 180.0D / Math.PI;
/*     */   }
/*     */   
/*     */   public static double toRadians(double angleDegrees) {
/*  73 */     return angleDegrees * Math.PI / 180.0D;
/*     */   }
/*     */   
/*     */   public static double angle(Coordinate p0, Coordinate p1) {
/*  85 */     double dx = p1.x - p0.x;
/*  86 */     double dy = p1.y - p0.y;
/*  87 */     return Math.atan2(dy, dx);
/*     */   }
/*     */   
/*     */   public static double angle(Coordinate p) {
/*  98 */     return Math.atan2(p.y, p.x);
/*     */   }
/*     */   
/*     */   public static boolean isAcute(Coordinate p0, Coordinate p1, Coordinate p2) {
/* 115 */     double dx0 = p0.x - p1.x;
/* 116 */     double dy0 = p0.y - p1.y;
/* 117 */     double dx1 = p2.x - p1.x;
/* 118 */     double dy1 = p2.y - p1.y;
/* 119 */     double dotprod = dx0 * dx1 + dy0 * dy1;
/* 120 */     return (dotprod > 0.0D);
/*     */   }
/*     */   
/*     */   public static boolean isObtuse(Coordinate p0, Coordinate p1, Coordinate p2) {
/* 136 */     double dx0 = p0.x - p1.x;
/* 137 */     double dy0 = p0.y - p1.y;
/* 138 */     double dx1 = p2.x - p1.x;
/* 139 */     double dy1 = p2.y - p1.y;
/* 140 */     double dotprod = dx0 * dx1 + dy0 * dy1;
/* 141 */     return (dotprod < 0.0D);
/*     */   }
/*     */   
/*     */   public static double angleBetween(Coordinate tip1, Coordinate tail, Coordinate tip2) {
/* 155 */     double a1 = angle(tail, tip1);
/* 156 */     double a2 = angle(tail, tip2);
/* 158 */     return diff(a1, a2);
/*     */   }
/*     */   
/*     */   public static double angleBetweenOriented(Coordinate tip1, Coordinate tail, Coordinate tip2) {
/* 178 */     double a1 = angle(tail, tip1);
/* 179 */     double a2 = angle(tail, tip2);
/* 180 */     double angDel = a2 - a1;
/* 183 */     if (angDel <= -3.141592653589793D)
/* 184 */       return angDel + 6.283185307179586D; 
/* 185 */     if (angDel > Math.PI)
/* 186 */       return angDel - 6.283185307179586D; 
/* 187 */     return angDel;
/*     */   }
/*     */   
/*     */   public static double interiorAngle(Coordinate p0, Coordinate p1, Coordinate p2) {
/* 205 */     double anglePrev = angle(p1, p0);
/* 206 */     double angleNext = angle(p1, p2);
/* 207 */     return Math.abs(angleNext - anglePrev);
/*     */   }
/*     */   
/*     */   public static int getTurn(double ang1, double ang2) {
/* 220 */     double crossproduct = Math.sin(ang2 - ang1);
/* 222 */     if (crossproduct > 0.0D)
/* 223 */       return 1; 
/* 225 */     if (crossproduct < 0.0D)
/* 226 */       return -1; 
/* 228 */     return 0;
/*     */   }
/*     */   
/*     */   public static double normalize(double angle) {
/* 240 */     while (angle > Math.PI)
/* 241 */       angle -= 6.283185307179586D; 
/* 242 */     while (angle <= -3.141592653589793D)
/* 243 */       angle += 6.283185307179586D; 
/* 244 */     return angle;
/*     */   }
/*     */   
/*     */   public static double normalizePositive(double angle) {
/* 268 */     if (angle < 0.0D) {
/* 269 */       while (angle < 0.0D)
/* 270 */         angle += 6.283185307179586D; 
/* 272 */       if (angle >= 6.283185307179586D)
/* 273 */         angle = 0.0D; 
/*     */     } else {
/* 276 */       while (angle >= 6.283185307179586D)
/* 277 */         angle -= 6.283185307179586D; 
/* 279 */       if (angle < 0.0D)
/* 280 */         angle = 0.0D; 
/*     */     } 
/* 282 */     return angle;
/*     */   }
/*     */   
/*     */   public static double diff(double ang1, double ang2) {
/*     */     double delAngle;
/* 297 */     if (ang1 < ang2) {
/* 298 */       delAngle = ang2 - ang1;
/*     */     } else {
/* 300 */       delAngle = ang1 - ang2;
/*     */     } 
/* 303 */     if (delAngle > Math.PI)
/* 304 */       delAngle = 6.283185307179586D - delAngle; 
/* 307 */     return delAngle;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\Angle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */