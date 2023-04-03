/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class EastNorth {
/*     */   protected final double x;
/*     */   
/*     */   protected final double y;
/*     */   
/*     */   public EastNorth(double east, double north) {
/*  39 */     this.x = east;
/*  40 */     this.y = north;
/*     */   }
/*     */   
/*     */   public double east() {
/*  44 */     return this.x;
/*     */   }
/*     */   
/*     */   public double north() {
/*  48 */     return this.y;
/*     */   }
/*     */   
/*     */   public EastNorth add(double dx, double dy) {
/*  52 */     return new EastNorth(this.x + dx, this.y + dy);
/*     */   }
/*     */   
/*     */   public EastNorth add(EastNorth other) {
/*  56 */     return new EastNorth(this.x + other.x, this.y + other.y);
/*     */   }
/*     */   
/*     */   public EastNorth scale(double s) {
/*  60 */     return new EastNorth(s * this.x, s * this.y);
/*     */   }
/*     */   
/*     */   public EastNorth interpolate(EastNorth en2, double proportion) {
/*  64 */     return new EastNorth(this.x + proportion * (en2.x - this.x), this.y + proportion * (en2.y - this.y));
/*     */   }
/*     */   
/*     */   public EastNorth getCenter(EastNorth en2) {
/*  69 */     return new EastNorth((this.x + en2.x) / 2.0D, (this.y + en2.y) / 2.0D);
/*     */   }
/*     */   
/*     */   public double length() {
/*  82 */     return FastMath.sqrt(this.x * this.x + this.y * this.y);
/*     */   }
/*     */   
/*     */   public double heading(EastNorth other) {
/*  93 */     double hd = FastMath.atan2(other.east() - east(), other.north() - north());
/*  94 */     if (hd < 0.0D)
/*  95 */       hd = 6.283185307179586D + hd; 
/*  97 */     return hd;
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 106 */     return (!Double.isNaN(this.x) && !Double.isNaN(this.y) && !Double.isInfinite(this.x) && !Double.isInfinite(this.y));
/*     */   }
/*     */   
/*     */   public EastNorth sub(EastNorth en) {
/* 110 */     return new EastNorth(en.east() - east(), en.north() - north());
/*     */   }
/*     */   
/*     */   public EastNorth rotate(EastNorth pivot, double angle) {
/* 122 */     double cosPhi = FastMath.cos(angle);
/* 123 */     double sinPhi = FastMath.sin(angle);
/* 124 */     double x = east() - pivot.east();
/* 125 */     double y = north() - pivot.north();
/* 126 */     double nx = cosPhi * x + sinPhi * y + pivot.east();
/* 127 */     double ny = -sinPhi * x + cosPhi * y + pivot.north();
/* 128 */     return new EastNorth(nx, ny);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 133 */     return "EastNorth[e=" + this.x + ", n=" + this.y + "]";
/*     */   }
/*     */   
/*     */   public boolean equalsEpsilon(EastNorth other, double e) {
/* 142 */     return (FastMath.abs(this.x - other.x) < e && FastMath.abs(this.y - other.y) < e);
/*     */   }
/*     */   
/*     */   public double getX() {
/* 151 */     return this.x;
/*     */   }
/*     */   
/*     */   public double getY() {
/* 155 */     return this.y;
/*     */   }
/*     */   
/*     */   protected final double distance(EastNorth coor) {
/* 166 */     return distance(coor.x, coor.y);
/*     */   }
/*     */   
/*     */   public final double distance(double px, double py) {
/* 178 */     double dx = this.x - px;
/* 179 */     double dy = this.y - py;
/* 180 */     return FastMath.sqrt(dx * dx + dy * dy);
/*     */   }
/*     */   
/*     */   protected final double distanceSq(EastNorth coor) {
/* 191 */     return distanceSq(coor.x, coor.y);
/*     */   }
/*     */   
/*     */   public final double distanceSq(double px, double py) {
/* 203 */     double dx = this.x - px;
/* 204 */     double dy = this.y - py;
/* 205 */     return dx * dx + dy * dy;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 212 */     int prime = 31;
/* 213 */     int result = 1;
/* 215 */     long temp = Double.doubleToLongBits(this.x);
/* 216 */     result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 217 */     temp = Double.doubleToLongBits(this.y);
/* 218 */     result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 219 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 224 */     if (this == obj)
/* 225 */       return true; 
/* 226 */     if (obj == null || getClass() != obj.getClass())
/* 227 */       return false; 
/* 228 */     EastNorth other = (EastNorth)obj;
/* 229 */     if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x))
/* 230 */       return false; 
/* 231 */     if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y))
/* 232 */       return false; 
/* 233 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\EastNorth.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */