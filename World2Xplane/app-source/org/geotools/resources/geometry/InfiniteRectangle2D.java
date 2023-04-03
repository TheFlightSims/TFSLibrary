/*     */ package org.geotools.resources.geometry;
/*     */ 
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ final class InfiniteRectangle2D extends Rectangle2D implements Serializable {
/*     */   private static final long serialVersionUID = 5281254268988984523L;
/*     */   
/*  47 */   public static final Rectangle2D INFINITY = new InfiniteRectangle2D();
/*     */   
/*     */   public double getX() {
/*  59 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getY() {
/*  66 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getMinX() {
/*  74 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getMinY() {
/*  82 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getMaxX() {
/*  90 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getMaxY() {
/*  98 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/* 105 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/* 112 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getCenterX() {
/* 120 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public double getCenterY() {
/* 128 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public void add(Rectangle2D rect) {}
/*     */   
/*     */   public void add(Point2D point) {}
/*     */   
/*     */   public void add(double x, double y) {}
/*     */   
/*     */   public int outcode(double x, double y) {
/* 156 */     return 0;
/*     */   }
/*     */   
/*     */   public int outcode(Point2D point) {
/* 164 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D point) {
/* 172 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(Rectangle2D rect) {
/* 180 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 188 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y, double w, double h) {
/* 196 */     return true;
/*     */   }
/*     */   
/*     */   public boolean intersects(Rectangle2D rect) {
/* 204 */     return true;
/*     */   }
/*     */   
/*     */   public boolean intersects(double x, double y, double w, double h) {
/* 212 */     return true;
/*     */   }
/*     */   
/*     */   public boolean intersectsLine(double x, double y, double u, double v) {
/* 220 */     return true;
/*     */   }
/*     */   
/*     */   public boolean intersectsLine(Line2D line) {
/* 228 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 235 */     return false;
/*     */   }
/*     */   
/*     */   public Rectangle2D getFrame() {
/* 244 */     return this;
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 253 */     return this;
/*     */   }
/*     */   
/*     */   public Rectangle2D createUnion(Rectangle2D rect) {
/* 261 */     return this;
/*     */   }
/*     */   
/*     */   public Rectangle2D createIntersection(Rectangle2D rect) {
/* 268 */     return (Rectangle2D)rect.clone();
/*     */   }
/*     */   
/*     */   public void setRect(double x, double y, double w, double h) {
/* 278 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 285 */     return INFINITY;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\geometry\InfiniteRectangle2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */