/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class NonRobustLineIntersector extends LineIntersector {
/*     */   public static boolean isSameSignAndNonZero(double a, double b) {
/*  58 */     if (a == 0.0D || b == 0.0D)
/*  59 */       return false; 
/*  61 */     return ((a < 0.0D && b < 0.0D) || (a > 0.0D && b > 0.0D));
/*     */   }
/*     */   
/*     */   public void computeIntersection(Coordinate p, Coordinate p1, Coordinate p2) {
/*  82 */     this.isProper = false;
/*  88 */     double a1 = p2.y - p1.y;
/*  89 */     double b1 = p1.x - p2.x;
/*  90 */     double c1 = p2.x * p1.y - p1.x * p2.y;
/*  95 */     double r = a1 * p.x + b1 * p.y + c1;
/*  98 */     if (r != 0.0D) {
/*  99 */       this.result = 0;
/*     */       return;
/*     */     } 
/* 105 */     double dist = rParameter(p1, p2, p);
/* 106 */     if (dist < 0.0D || dist > 1.0D) {
/* 107 */       this.result = 0;
/*     */       return;
/*     */     } 
/* 111 */     this.isProper = true;
/* 112 */     if (p.equals(p1) || p.equals(p2))
/* 113 */       this.isProper = false; 
/* 115 */     this.result = 1;
/*     */   }
/*     */   
/*     */   protected int computeIntersect(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate p4) {
/* 147 */     this.isProper = false;
/* 153 */     double a1 = p2.y - p1.y;
/* 154 */     double b1 = p1.x - p2.x;
/* 155 */     double c1 = p2.x * p1.y - p1.x * p2.y;
/* 160 */     double r3 = a1 * p3.x + b1 * p3.y + c1;
/* 161 */     double r4 = a1 * p4.x + b1 * p4.y + c1;
/* 167 */     if (r3 != 0.0D && r4 != 0.0D && isSameSignAndNonZero(r3, r4))
/* 170 */       return 0; 
/* 176 */     double a2 = p4.y - p3.y;
/* 177 */     double b2 = p3.x - p4.x;
/* 178 */     double c2 = p4.x * p3.y - p3.x * p4.y;
/* 183 */     double r1 = a2 * p1.x + b2 * p1.y + c2;
/* 184 */     double r2 = a2 * p2.x + b2 * p2.y + c2;
/* 191 */     if (r1 != 0.0D && r2 != 0.0D && isSameSignAndNonZero(r1, r2))
/* 194 */       return 0; 
/* 200 */     double denom = a1 * b2 - a2 * b1;
/* 201 */     if (denom == 0.0D)
/* 202 */       return computeCollinearIntersection(p1, p2, p3, p4); 
/* 204 */     double numX = b1 * c2 - b2 * c1;
/* 205 */     this.pa.x = numX / denom;
/* 213 */     double numY = a2 * c1 - a1 * c2;
/* 214 */     this.pa.y = numY / denom;
/* 218 */     this.isProper = true;
/* 219 */     if (this.pa.equals(p1) || this.pa.equals(p2) || this.pa.equals(p3) || this.pa.equals(p4))
/* 220 */       this.isProper = false; 
/* 225 */     if (this.precisionModel != null)
/* 226 */       this.precisionModel.makePrecise(this.pa); 
/* 228 */     return 1;
/*     */   }
/*     */   
/*     */   private int computeCollinearIntersection(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate p4) {
/*     */     Coordinate q3, q4;
/* 255 */     double t3, t4, r1 = 0.0D;
/* 256 */     double r2 = 1.0D;
/* 257 */     double r3 = rParameter(p1, p2, p3);
/* 258 */     double r4 = rParameter(p1, p2, p4);
/* 260 */     if (r3 < r4) {
/* 261 */       q3 = p3;
/* 262 */       t3 = r3;
/* 263 */       q4 = p4;
/* 264 */       t4 = r4;
/*     */     } else {
/* 267 */       q3 = p4;
/* 268 */       t3 = r4;
/* 269 */       q4 = p3;
/* 270 */       t4 = r3;
/*     */     } 
/* 273 */     if (t3 > r2 || t4 < r1)
/* 274 */       return 0; 
/* 278 */     if (q4 == p1) {
/* 279 */       this.pa.setCoordinate(p1);
/* 280 */       return 1;
/*     */     } 
/* 282 */     if (q3 == p2) {
/* 283 */       this.pa.setCoordinate(p2);
/* 284 */       return 1;
/*     */     } 
/* 288 */     this.pa.setCoordinate(p1);
/* 289 */     if (t3 > r1)
/* 290 */       this.pa.setCoordinate(q3); 
/* 292 */     this.pb.setCoordinate(p2);
/* 293 */     if (t4 < r2)
/* 294 */       this.pb.setCoordinate(q4); 
/* 296 */     return 2;
/*     */   }
/*     */   
/*     */   private double rParameter(Coordinate p1, Coordinate p2, Coordinate p) {
/* 309 */     double r, dx = Math.abs(p2.x - p1.x);
/* 310 */     double dy = Math.abs(p2.y - p1.y);
/* 311 */     if (dx > dy) {
/* 312 */       r = (p.x - p1.x) / (p2.x - p1.x);
/*     */     } else {
/* 315 */       r = (p.y - p1.y) / (p2.y - p1.y);
/*     */     } 
/* 317 */     return r;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\NonRobustLineIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */