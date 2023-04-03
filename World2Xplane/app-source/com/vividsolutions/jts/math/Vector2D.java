/*     */ package com.vividsolutions.jts.math;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.Angle;
/*     */ import com.vividsolutions.jts.algorithm.RobustDeterminant;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ public class Vector2D {
/*     */   private double x;
/*     */   
/*     */   private double y;
/*     */   
/*     */   public static Vector2D create(double x, double y) {
/*  55 */     return new Vector2D(x, y);
/*     */   }
/*     */   
/*     */   public static Vector2D create(Vector2D v) {
/*  65 */     return new Vector2D(v);
/*     */   }
/*     */   
/*     */   public static Vector2D create(Coordinate coord) {
/*  75 */     return new Vector2D(coord);
/*     */   }
/*     */   
/*     */   public static Vector2D create(Coordinate from, Coordinate to) {
/*  88 */     return new Vector2D(from, to);
/*     */   }
/*     */   
/*     */   public Vector2D() {
/* 102 */     this(0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Vector2D(double x, double y) {
/* 106 */     this.x = x;
/* 107 */     this.y = y;
/*     */   }
/*     */   
/*     */   public Vector2D(Vector2D v) {
/* 111 */     this.x = v.x;
/* 112 */     this.y = v.y;
/*     */   }
/*     */   
/*     */   public Vector2D(Coordinate from, Coordinate to) {
/* 116 */     this.x = to.x - from.x;
/* 117 */     this.y = to.y - from.y;
/*     */   }
/*     */   
/*     */   public Vector2D(Coordinate v) {
/* 121 */     this.x = v.x;
/* 122 */     this.y = v.y;
/*     */   }
/*     */   
/*     */   public double getX() {
/* 126 */     return this.x;
/*     */   }
/*     */   
/*     */   public double getY() {
/* 130 */     return this.y;
/*     */   }
/*     */   
/*     */   public double getComponent(int index) {
/* 134 */     if (index == 0)
/* 135 */       return this.x; 
/* 136 */     return this.y;
/*     */   }
/*     */   
/*     */   public Vector2D add(Vector2D v) {
/* 140 */     return create(this.x + v.x, this.y + v.y);
/*     */   }
/*     */   
/*     */   public Vector2D subtract(Vector2D v) {
/* 144 */     return create(this.x - v.x, this.y - v.y);
/*     */   }
/*     */   
/*     */   public Vector2D multiply(double d) {
/* 154 */     return create(this.x * d, this.y * d);
/*     */   }
/*     */   
/*     */   public Vector2D divide(double d) {
/* 164 */     return create(this.x / d, this.y / d);
/*     */   }
/*     */   
/*     */   public Vector2D negate() {
/* 168 */     return create(-this.x, -this.y);
/*     */   }
/*     */   
/*     */   public double length() {
/* 172 */     return Math.sqrt(this.x * this.x + this.y * this.y);
/*     */   }
/*     */   
/*     */   public double lengthSquared() {
/* 176 */     return this.x * this.x + this.y * this.y;
/*     */   }
/*     */   
/*     */   public Vector2D normalize() {
/* 180 */     double length = length();
/* 181 */     if (length > 0.0D)
/* 182 */       return divide(length); 
/* 183 */     return create(0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Vector2D average(Vector2D v) {
/* 187 */     return weightedSum(v, 0.5D);
/*     */   }
/*     */   
/*     */   public Vector2D weightedSum(Vector2D v, double frac) {
/* 206 */     return create(frac * this.x + (1.0D - frac) * v.x, frac * this.y + (1.0D - frac) * v.y);
/*     */   }
/*     */   
/*     */   public double distance(Vector2D v) {
/* 218 */     double delx = v.x - this.x;
/* 219 */     double dely = v.y - this.y;
/* 220 */     return Math.sqrt(delx * delx + dely * dely);
/*     */   }
/*     */   
/*     */   public double dot(Vector2D v) {
/* 230 */     return this.x * v.x + this.y * v.y;
/*     */   }
/*     */   
/*     */   public double angle() {
/* 235 */     return Math.atan2(this.y, this.x);
/*     */   }
/*     */   
/*     */   public double angle(Vector2D v) {
/* 240 */     return Angle.diff(v.angle(), angle());
/*     */   }
/*     */   
/*     */   public double angleTo(Vector2D v) {
/* 245 */     double a1 = angle();
/* 246 */     double a2 = v.angle();
/* 247 */     double angDel = a2 - a1;
/* 250 */     if (angDel <= -3.141592653589793D)
/* 251 */       return angDel + 6.283185307179586D; 
/* 252 */     if (angDel > Math.PI)
/* 253 */       return angDel - 6.283185307179586D; 
/* 254 */     return angDel;
/*     */   }
/*     */   
/*     */   public Vector2D rotate(double angle) {
/* 259 */     double cos = Math.cos(angle);
/* 260 */     double sin = Math.sin(angle);
/* 261 */     return create(this.x * cos - this.y * sin, this.x * sin + this.y * cos);
/*     */   }
/*     */   
/*     */   public Vector2D rotateByQuarterCircle(int numQuarters) {
/* 279 */     int nQuad = numQuarters % 4;
/* 280 */     if (numQuarters < 0 && nQuad != 0)
/* 281 */       nQuad += 4; 
/* 283 */     switch (nQuad) {
/*     */       case 0:
/* 285 */         return create(this.x, this.y);
/*     */       case 1:
/* 287 */         return create(-this.y, this.x);
/*     */       case 2:
/* 289 */         return create(-this.x, -this.y);
/*     */       case 3:
/* 291 */         return create(this.y, -this.x);
/*     */     } 
/* 293 */     Assert.shouldNeverReachHere();
/* 294 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isParallel(Vector2D v) {
/* 299 */     return (0.0D == RobustDeterminant.signOfDet2x2(this.x, this.y, v.x, v.y));
/*     */   }
/*     */   
/*     */   public Coordinate translate(Coordinate coord) {
/* 303 */     return new Coordinate(this.x + coord.x, this.y + coord.y);
/*     */   }
/*     */   
/*     */   public Coordinate toCoordinate() {
/* 307 */     return new Coordinate(this.x, this.y);
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 317 */     return new Vector2D(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 326 */     return "[" + this.x + ", " + this.y + "]";
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 339 */     if (!(o instanceof Vector2D))
/* 340 */       return false; 
/* 342 */     Vector2D v = (Vector2D)o;
/* 343 */     return (this.x == v.x && this.y == v.y);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 353 */     int result = 17;
/* 354 */     result = 37 * result + Coordinate.hashCode(this.x);
/* 355 */     result = 37 * result + Coordinate.hashCode(this.y);
/* 356 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\math\Vector2D.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */