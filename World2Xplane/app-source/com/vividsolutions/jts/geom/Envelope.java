/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Envelope implements Serializable {
/*     */   private static final long serialVersionUID = 5873921885273102420L;
/*     */   
/*     */   private double minx;
/*     */   
/*     */   private double maxx;
/*     */   
/*     */   private double miny;
/*     */   
/*     */   private double maxy;
/*     */   
/*     */   public int hashCode() {
/*  59 */     int result = 17;
/*  60 */     result = 37 * result + Coordinate.hashCode(this.minx);
/*  61 */     result = 37 * result + Coordinate.hashCode(this.maxx);
/*  62 */     result = 37 * result + Coordinate.hashCode(this.miny);
/*  63 */     result = 37 * result + Coordinate.hashCode(this.maxy);
/*  64 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean intersects(Coordinate p1, Coordinate p2, Coordinate q) {
/*  78 */     if (q.x >= ((p1.x < p2.x) ? p1.x : p2.x) && q.x <= ((p1.x > p2.x) ? p1.x : p2.x) && q.y >= ((p1.y < p2.y) ? p1.y : p2.y) && q.y <= ((p1.y > p2.y) ? p1.y : p2.y))
/*  80 */       return true; 
/*  82 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean intersects(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2) {
/*  98 */     double minq = Math.min(q1.x, q2.x);
/*  99 */     double maxq = Math.max(q1.x, q2.x);
/* 100 */     double minp = Math.min(p1.x, p2.x);
/* 101 */     double maxp = Math.max(p1.x, p2.x);
/* 103 */     if (minp > maxq)
/* 104 */       return false; 
/* 105 */     if (maxp < minq)
/* 106 */       return false; 
/* 108 */     minq = Math.min(q1.y, q2.y);
/* 109 */     maxq = Math.max(q1.y, q2.y);
/* 110 */     minp = Math.min(p1.y, p2.y);
/* 111 */     maxp = Math.max(p1.y, p2.y);
/* 113 */     if (minp > maxq)
/* 114 */       return false; 
/* 115 */     if (maxp < minq)
/* 116 */       return false; 
/* 117 */     return true;
/*     */   }
/*     */   
/*     */   public Envelope() {
/* 144 */     init();
/*     */   }
/*     */   
/*     */   public Envelope(double x1, double x2, double y1, double y2) {
/* 157 */     init(x1, x2, y1, y2);
/*     */   }
/*     */   
/*     */   public Envelope(Coordinate p1, Coordinate p2) {
/* 168 */     init(p1.x, p2.x, p1.y, p2.y);
/*     */   }
/*     */   
/*     */   public Envelope(Coordinate p) {
/* 178 */     init(p.x, p.x, p.y, p.y);
/*     */   }
/*     */   
/*     */   public Envelope(Envelope env) {
/* 188 */     init(env);
/*     */   }
/*     */   
/*     */   public void init() {
/* 196 */     setToNull();
/*     */   }
/*     */   
/*     */   public void init(double x1, double x2, double y1, double y2) {
/* 209 */     if (x1 < x2) {
/* 210 */       this.minx = x1;
/* 211 */       this.maxx = x2;
/*     */     } else {
/* 214 */       this.minx = x2;
/* 215 */       this.maxx = x1;
/*     */     } 
/* 217 */     if (y1 < y2) {
/* 218 */       this.miny = y1;
/* 219 */       this.maxy = y2;
/*     */     } else {
/* 222 */       this.miny = y2;
/* 223 */       this.maxy = y1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void init(Coordinate p1, Coordinate p2) {
/* 235 */     init(p1.x, p2.x, p1.y, p2.y);
/*     */   }
/*     */   
/*     */   public void init(Coordinate p) {
/* 245 */     init(p.x, p.x, p.y, p.y);
/*     */   }
/*     */   
/*     */   public void init(Envelope env) {
/* 255 */     this.minx = env.minx;
/* 256 */     this.maxx = env.maxx;
/* 257 */     this.miny = env.miny;
/* 258 */     this.maxy = env.maxy;
/*     */   }
/*     */   
/*     */   public void setToNull() {
/* 267 */     this.minx = 0.0D;
/* 268 */     this.maxx = -1.0D;
/* 269 */     this.miny = 0.0D;
/* 270 */     this.maxy = -1.0D;
/*     */   }
/*     */   
/*     */   public boolean isNull() {
/* 281 */     return (this.maxx < this.minx);
/*     */   }
/*     */   
/*     */   public double getWidth() {
/* 290 */     if (isNull())
/* 291 */       return 0.0D; 
/* 293 */     return this.maxx - this.minx;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/* 302 */     if (isNull())
/* 303 */       return 0.0D; 
/* 305 */     return this.maxy - this.miny;
/*     */   }
/*     */   
/*     */   public double getMinX() {
/* 315 */     return this.minx;
/*     */   }
/*     */   
/*     */   public double getMaxX() {
/* 325 */     return this.maxx;
/*     */   }
/*     */   
/*     */   public double getMinY() {
/* 335 */     return this.miny;
/*     */   }
/*     */   
/*     */   public double getMaxY() {
/* 345 */     return this.maxy;
/*     */   }
/*     */   
/*     */   public double getArea() {
/* 356 */     return getWidth() * getHeight();
/*     */   }
/*     */   
/*     */   public double minExtent() {
/* 366 */     if (isNull())
/* 366 */       return 0.0D; 
/* 367 */     double w = getWidth();
/* 368 */     double h = getHeight();
/* 369 */     if (w < h)
/* 369 */       return w; 
/* 370 */     return h;
/*     */   }
/*     */   
/*     */   public double maxExtent() {
/* 380 */     if (isNull())
/* 380 */       return 0.0D; 
/* 381 */     double w = getWidth();
/* 382 */     double h = getHeight();
/* 383 */     if (w > h)
/* 383 */       return w; 
/* 384 */     return h;
/*     */   }
/*     */   
/*     */   public void expandToInclude(Coordinate p) {
/* 396 */     expandToInclude(p.x, p.y);
/*     */   }
/*     */   
/*     */   public void expandBy(double distance) {
/* 407 */     expandBy(distance, distance);
/*     */   }
/*     */   
/*     */   public void expandBy(double deltaX, double deltaY) {
/* 419 */     if (isNull())
/*     */       return; 
/* 421 */     this.minx -= deltaX;
/* 422 */     this.maxx += deltaX;
/* 423 */     this.miny -= deltaY;
/* 424 */     this.maxy += deltaY;
/* 427 */     if (this.minx > this.maxx || this.miny > this.maxy)
/* 428 */       setToNull(); 
/*     */   }
/*     */   
/*     */   public void expandToInclude(double x, double y) {
/* 440 */     if (isNull()) {
/* 441 */       this.minx = x;
/* 442 */       this.maxx = x;
/* 443 */       this.miny = y;
/* 444 */       this.maxy = y;
/*     */     } else {
/* 447 */       if (x < this.minx)
/* 448 */         this.minx = x; 
/* 450 */       if (x > this.maxx)
/* 451 */         this.maxx = x; 
/* 453 */       if (y < this.miny)
/* 454 */         this.miny = y; 
/* 456 */       if (y > this.maxy)
/* 457 */         this.maxy = y; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void expandToInclude(Envelope other) {
/* 471 */     if (other.isNull())
/*     */       return; 
/* 474 */     if (isNull()) {
/* 475 */       this.minx = other.getMinX();
/* 476 */       this.maxx = other.getMaxX();
/* 477 */       this.miny = other.getMinY();
/* 478 */       this.maxy = other.getMaxY();
/*     */     } else {
/* 481 */       if (other.minx < this.minx)
/* 482 */         this.minx = other.minx; 
/* 484 */       if (other.maxx > this.maxx)
/* 485 */         this.maxx = other.maxx; 
/* 487 */       if (other.miny < this.miny)
/* 488 */         this.miny = other.miny; 
/* 490 */       if (other.maxy > this.maxy)
/* 491 */         this.maxy = other.maxy; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void translate(double transX, double transY) {
/* 503 */     if (isNull())
/*     */       return; 
/* 506 */     init(getMinX() + transX, getMaxX() + transX, getMinY() + transY, getMaxY() + transY);
/*     */   }
/*     */   
/*     */   public Coordinate centre() {
/* 517 */     if (isNull())
/* 517 */       return null; 
/* 518 */     return new Coordinate((getMinX() + getMaxX()) / 2.0D, (getMinY() + getMaxY()) / 2.0D);
/*     */   }
/*     */   
/*     */   public Envelope intersection(Envelope env) {
/* 532 */     if (isNull() || env.isNull() || !intersects(env))
/* 532 */       return new Envelope(); 
/* 534 */     double intMinX = (this.minx > env.minx) ? this.minx : env.minx;
/* 535 */     double intMinY = (this.miny > env.miny) ? this.miny : env.miny;
/* 536 */     double intMaxX = (this.maxx < env.maxx) ? this.maxx : env.maxx;
/* 537 */     double intMaxY = (this.maxy < env.maxy) ? this.maxy : env.maxy;
/* 538 */     return new Envelope(intMinX, intMaxX, intMinY, intMaxY);
/*     */   }
/*     */   
/*     */   public boolean intersects(Envelope other) {
/* 552 */     if (isNull() || other.isNull())
/* 552 */       return false; 
/* 553 */     return (other.minx <= this.maxx && other.maxx >= this.minx && other.miny <= this.maxy && other.maxy >= this.miny);
/*     */   }
/*     */   
/*     */   public boolean overlaps(Envelope other) {
/* 564 */     return intersects(other);
/*     */   }
/*     */   
/*     */   public boolean intersects(Coordinate p) {
/* 575 */     return intersects(p.x, p.y);
/*     */   }
/*     */   
/*     */   public boolean overlaps(Coordinate p) {
/* 581 */     return intersects(p);
/*     */   }
/*     */   
/*     */   public boolean intersects(double x, double y) {
/* 592 */     if (isNull())
/* 592 */       return false; 
/* 593 */     return (x <= this.maxx && x >= this.minx && y <= this.maxy && y >= this.miny);
/*     */   }
/*     */   
/*     */   public boolean overlaps(double x, double y) {
/* 602 */     return intersects(x, y);
/*     */   }
/*     */   
/*     */   public boolean contains(Envelope other) {
/* 618 */     return covers(other);
/*     */   }
/*     */   
/*     */   public boolean contains(Coordinate p) {
/* 635 */     return covers(p);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 654 */     return covers(x, y);
/*     */   }
/*     */   
/*     */   public boolean covers(double x, double y) {
/* 668 */     if (isNull())
/* 668 */       return false; 
/* 669 */     return (x >= this.minx && x <= this.maxx && y >= this.miny && y <= this.maxy);
/*     */   }
/*     */   
/*     */   public boolean covers(Coordinate p) {
/* 684 */     return covers(p.x, p.y);
/*     */   }
/*     */   
/*     */   public boolean covers(Envelope other) {
/* 695 */     if (isNull() || other.isNull())
/* 695 */       return false; 
/* 696 */     return (other.getMinX() >= this.minx && other.getMaxX() <= this.maxx && other.getMinY() >= this.miny && other.getMaxY() <= this.maxy);
/*     */   }
/*     */   
/*     */   public double distance(Envelope env) {
/* 710 */     if (intersects(env))
/* 710 */       return 0.0D; 
/* 712 */     double dx = 0.0D;
/* 713 */     if (this.maxx < env.minx) {
/* 714 */       dx = env.minx - this.maxx;
/* 715 */     } else if (this.minx > env.maxx) {
/* 716 */       dx = this.minx - env.maxx;
/*     */     } 
/* 718 */     double dy = 0.0D;
/* 719 */     if (this.maxy < env.miny) {
/* 720 */       dy = env.miny - this.maxy;
/* 721 */     } else if (this.miny > env.maxy) {
/* 721 */       dy = this.miny - env.maxy;
/*     */     } 
/* 724 */     if (dx == 0.0D)
/* 724 */       return dy; 
/* 725 */     if (dy == 0.0D)
/* 725 */       return dx; 
/* 726 */     return Math.sqrt(dx * dx + dy * dy);
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 730 */     if (!(other instanceof Envelope))
/* 731 */       return false; 
/* 733 */     Envelope otherEnvelope = (Envelope)other;
/* 734 */     if (isNull())
/* 735 */       return otherEnvelope.isNull(); 
/* 737 */     return (this.maxx == otherEnvelope.getMaxX() && this.maxy == otherEnvelope.getMaxY() && this.minx == otherEnvelope.getMinX() && this.miny == otherEnvelope.getMinY());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 745 */     return "Env[" + this.minx + " : " + this.maxx + ", " + this.miny + " : " + this.maxy + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\Envelope.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */