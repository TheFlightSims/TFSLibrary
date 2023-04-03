/*     */ package org.geotools.referencing.factory.gridshift;
/*     */ 
/*     */ import org.geotools.referencing.operation.builder.LocalizationGrid;
/*     */ 
/*     */ public class NADConGridShift extends LocalizationGrid {
/*     */   private double minX;
/*     */   
/*     */   private double minY;
/*     */   
/*     */   private double maxX;
/*     */   
/*     */   private double maxY;
/*     */   
/*     */   private double dx;
/*     */   
/*     */   private double dy;
/*     */   
/*     */   public NADConGridShift(double xmin, double ymin, double xmax, double ymax, double dx, double dy, int width, int height) {
/*  60 */     super(width, height);
/*  61 */     this.minX = xmin;
/*  62 */     this.maxX = xmax;
/*  63 */     this.minY = ymin;
/*  64 */     this.maxY = ymax;
/*  65 */     this.dx = dx;
/*  66 */     this.dy = dy;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*  77 */     long code = Double.doubleToLongBits(this.minX) + 37L * (Double.doubleToLongBits(this.minY) + 37L * (Double.doubleToLongBits(this.maxX) + 37L * (Double.doubleToLongBits(this.maxY) + 37L * (Double.doubleToLongBits(this.dx) + 37L * Double.doubleToLongBits(this.dy)))));
/*  84 */     return (int)code ^ (int)(code >>> 32L);
/*     */   }
/*     */   
/*     */   public final boolean equals(Object object) {
/*  95 */     if (object == this)
/*  97 */       return true; 
/* 100 */     if (super.equals(object)) {
/* 101 */       NADConGridShift that = (NADConGridShift)object;
/* 103 */       return (Double.doubleToLongBits(this.minX) == Double.doubleToLongBits(that.minX) && Double.doubleToLongBits(this.minY) == Double.doubleToLongBits(that.minY) && Double.doubleToLongBits(this.maxX) == Double.doubleToLongBits(that.maxX) && Double.doubleToLongBits(this.maxY) == Double.doubleToLongBits(that.maxY) && Double.doubleToLongBits(this.dx) == Double.doubleToLongBits(that.dx) && Double.doubleToLongBits(this.dy) == Double.doubleToLongBits(that.dy));
/*     */     } 
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public double getMinX() {
/* 118 */     return this.minX;
/*     */   }
/*     */   
/*     */   public double getMinY() {
/* 125 */     return this.minY;
/*     */   }
/*     */   
/*     */   public double getMaxX() {
/* 132 */     return this.maxX;
/*     */   }
/*     */   
/*     */   public double getMaxY() {
/* 139 */     return this.maxY;
/*     */   }
/*     */   
/*     */   public double getDx() {
/* 146 */     return this.dx;
/*     */   }
/*     */   
/*     */   public double getDy() {
/* 153 */     return this.dy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\gridshift\NADConGridShift.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */