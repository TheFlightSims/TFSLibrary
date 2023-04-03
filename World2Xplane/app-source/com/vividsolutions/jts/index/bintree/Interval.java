/*     */ package com.vividsolutions.jts.index.bintree;
/*     */ 
/*     */ public class Interval {
/*     */   public double min;
/*     */   
/*     */   public double max;
/*     */   
/*     */   public Interval() {
/*  47 */     this.min = 0.0D;
/*  48 */     this.max = 0.0D;
/*     */   }
/*     */   
/*     */   public Interval(double min, double max) {
/*  53 */     init(min, max);
/*     */   }
/*     */   
/*     */   public Interval(Interval interval) {
/*  57 */     init(interval.min, interval.max);
/*     */   }
/*     */   
/*     */   public void init(double min, double max) {
/*  61 */     this.min = min;
/*  62 */     this.max = max;
/*  63 */     if (min > max) {
/*  64 */       this.min = max;
/*  65 */       this.max = min;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getMin() {
/*  68 */     return this.min;
/*     */   }
/*     */   
/*     */   public double getMax() {
/*  69 */     return this.max;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/*  70 */     return this.max - this.min;
/*     */   }
/*     */   
/*     */   public void expandToInclude(Interval interval) {
/*  74 */     if (interval.max > this.max)
/*  74 */       this.max = interval.max; 
/*  75 */     if (interval.min < this.min)
/*  75 */       this.min = interval.min; 
/*     */   }
/*     */   
/*     */   public boolean overlaps(Interval interval) {
/*  79 */     return overlaps(interval.min, interval.max);
/*     */   }
/*     */   
/*     */   public boolean overlaps(double min, double max) {
/*  84 */     if (this.min > max || this.max < min)
/*  84 */       return false; 
/*  85 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(Interval interval) {
/*  90 */     return contains(interval.min, interval.max);
/*     */   }
/*     */   
/*     */   public boolean contains(double min, double max) {
/*  94 */     return (min >= this.min && max <= this.max);
/*     */   }
/*     */   
/*     */   public boolean contains(double p) {
/*  98 */     return (p >= this.min && p <= this.max);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 103 */     return "[" + this.min + ", " + this.max + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\bintree\Interval.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */