/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import com.vividsolutions.jts.util.NumberUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class Coordinate implements Comparable, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 6683108902428366910L;
/*     */   
/*     */   public static final double NULL_ORDINATE = NaND;
/*     */   
/*     */   public static final int X = 0;
/*     */   
/*     */   public static final int Y = 1;
/*     */   
/*     */   public static final int Z = 2;
/*     */   
/*     */   public double x;
/*     */   
/*     */   public double y;
/*     */   
/*     */   public double z;
/*     */   
/*     */   public Coordinate(double x, double y, double z) {
/*  98 */     this.x = x;
/*  99 */     this.y = y;
/* 100 */     this.z = z;
/*     */   }
/*     */   
/*     */   public Coordinate() {
/* 107 */     this(0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Coordinate(Coordinate c) {
/* 117 */     this(c.x, c.y, c.z);
/*     */   }
/*     */   
/*     */   public Coordinate(double x, double y) {
/* 127 */     this(x, y, Double.NaN);
/*     */   }
/*     */   
/*     */   public void setCoordinate(Coordinate other) {
/* 136 */     this.x = other.x;
/* 137 */     this.y = other.y;
/* 138 */     this.z = other.z;
/*     */   }
/*     */   
/*     */   public double getOrdinate(int ordinateIndex) {
/* 152 */     switch (ordinateIndex) {
/*     */       case 0:
/* 153 */         return this.x;
/*     */       case 1:
/* 154 */         return this.y;
/*     */       case 2:
/* 155 */         return this.z;
/*     */     } 
/* 157 */     throw new IllegalArgumentException("Invalid ordinate index: " + ordinateIndex);
/*     */   }
/*     */   
/*     */   public void setOrdinate(int ordinateIndex, double value) {
/* 172 */     switch (ordinateIndex) {
/*     */       case 0:
/* 174 */         this.x = value;
/*     */         return;
/*     */       case 1:
/* 177 */         this.y = value;
/*     */         return;
/*     */       case 2:
/* 180 */         this.z = value;
/*     */         return;
/*     */     } 
/* 183 */     throw new IllegalArgumentException("Invalid ordinate index: " + ordinateIndex);
/*     */   }
/*     */   
/*     */   public boolean equals2D(Coordinate other) {
/* 196 */     if (this.x != other.x)
/* 197 */       return false; 
/* 199 */     if (this.y != other.y)
/* 200 */       return false; 
/* 202 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals2D(Coordinate c, double tolerance) {
/* 214 */     if (!NumberUtil.equalsWithTolerance(this.x, c.x, tolerance))
/* 215 */       return false; 
/* 217 */     if (!NumberUtil.equalsWithTolerance(this.y, c.y, tolerance))
/* 218 */       return false; 
/* 220 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals3D(Coordinate other) {
/* 231 */     return (this.x == other.x && this.y == other.y && (this.z == other.z || (Double.isNaN(this.z) && Double.isNaN(other.z))));
/*     */   }
/*     */   
/*     */   public boolean equalInZ(Coordinate c, double tolerance) {
/* 244 */     return NumberUtil.equalsWithTolerance(this.z, c.z, tolerance);
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 257 */     if (!(other instanceof Coordinate))
/* 258 */       return false; 
/* 260 */     return equals2D((Coordinate)other);
/*     */   }
/*     */   
/*     */   public int compareTo(Object o) {
/* 283 */     Coordinate other = (Coordinate)o;
/* 285 */     if (this.x < other.x)
/* 285 */       return -1; 
/* 286 */     if (this.x > other.x)
/* 286 */       return 1; 
/* 287 */     if (this.y < other.y)
/* 287 */       return -1; 
/* 288 */     if (this.y > other.y)
/* 288 */       return 1; 
/* 289 */     return 0;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 298 */     return "(" + this.x + ", " + this.y + ", " + this.z + ")";
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 303 */       Coordinate coord = (Coordinate)super.clone();
/* 305 */       return coord;
/* 306 */     } catch (CloneNotSupportedException e) {
/* 307 */       Assert.shouldNeverReachHere("this shouldn't happen because this class is Cloneable");
/* 310 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double distance(Coordinate c) {
/* 322 */     double dx = this.x - c.x;
/* 323 */     double dy = this.y - c.y;
/* 324 */     return Math.sqrt(dx * dx + dy * dy);
/*     */   }
/*     */   
/*     */   public double distance3D(Coordinate c) {
/* 334 */     double dx = this.x - c.x;
/* 335 */     double dy = this.y - c.y;
/* 336 */     double dz = this.z - c.z;
/* 337 */     return Math.sqrt(dx * dx + dy * dy + dz * dz);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 347 */     int result = 17;
/* 348 */     result = 37 * result + hashCode(this.x);
/* 349 */     result = 37 * result + hashCode(this.y);
/* 350 */     return result;
/*     */   }
/*     */   
/*     */   public static int hashCode(double x) {
/* 360 */     long f = Double.doubleToLongBits(x);
/* 361 */     return (int)(f ^ f >>> 32L);
/*     */   }
/*     */   
/*     */   public static class DimensionalComparator implements Comparator {
/*     */     public static int compare(double a, double b) {
/* 382 */       if (a < b)
/* 382 */         return -1; 
/* 383 */       if (a > b)
/* 383 */         return 1; 
/* 385 */       if (Double.isNaN(a)) {
/* 386 */         if (Double.isNaN(b))
/* 386 */           return 0; 
/* 387 */         return -1;
/*     */       } 
/* 390 */       if (Double.isNaN(b))
/* 390 */         return 1; 
/* 391 */       return 0;
/*     */     }
/*     */     
/* 394 */     private int dimensionsToTest = 2;
/*     */     
/*     */     public DimensionalComparator() {
/* 401 */       this(2);
/*     */     }
/*     */     
/*     */     public DimensionalComparator(int dimensionsToTest) {
/* 412 */       if (dimensionsToTest != 2 && dimensionsToTest != 3)
/* 413 */         throw new IllegalArgumentException("only 2 or 3 dimensions may be specified"); 
/* 414 */       this.dimensionsToTest = dimensionsToTest;
/*     */     }
/*     */     
/*     */     public int compare(Object o1, Object o2) {
/* 429 */       Coordinate c1 = (Coordinate)o1;
/* 430 */       Coordinate c2 = (Coordinate)o2;
/* 432 */       int compX = compare(c1.x, c2.x);
/* 433 */       if (compX != 0)
/* 433 */         return compX; 
/* 435 */       int compY = compare(c1.y, c2.y);
/* 436 */       if (compY != 0)
/* 436 */         return compY; 
/* 438 */       if (this.dimensionsToTest <= 2)
/* 438 */         return 0; 
/* 440 */       int compZ = compare(c1.z, c2.z);
/* 441 */       return compZ;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\Coordinate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */