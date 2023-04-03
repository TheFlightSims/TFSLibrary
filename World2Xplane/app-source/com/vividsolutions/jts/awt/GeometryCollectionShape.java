/*     */ package com.vividsolutions.jts.awt;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class GeometryCollectionShape implements Shape {
/*  53 */   private ArrayList shapes = new ArrayList();
/*     */   
/*     */   public void add(Shape shape) {
/*  59 */     this.shapes.add(shape);
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/*  64 */     throw new UnsupportedOperationException("Method getBounds() not yet implemented.");
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/*  69 */     Rectangle2D rectangle = null;
/*  71 */     for (Iterator<Shape> i = this.shapes.iterator(); i.hasNext(); ) {
/*  72 */       Shape shape = i.next();
/*  74 */       if (rectangle == null) {
/*  75 */         rectangle = shape.getBounds2D();
/*     */         continue;
/*     */       } 
/*  77 */       rectangle.add(shape.getBounds2D());
/*     */     } 
/*  81 */     return rectangle;
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/*  86 */     throw new UnsupportedOperationException("Method contains() not yet implemented.");
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/*  92 */     throw new UnsupportedOperationException("Method contains() not yet implemented.");
/*     */   }
/*     */   
/*     */   public boolean intersects(double x, double y, double w, double h) {
/*  98 */     throw new UnsupportedOperationException("Method intersects() not yet implemented.");
/*     */   }
/*     */   
/*     */   public boolean intersects(Rectangle2D r) {
/* 104 */     throw new UnsupportedOperationException("Method intersects() not yet implemented.");
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y, double w, double h) {
/* 110 */     throw new UnsupportedOperationException("Method contains() not yet implemented.");
/*     */   }
/*     */   
/*     */   public boolean contains(Rectangle2D r) {
/* 116 */     throw new UnsupportedOperationException("Method contains() not yet implemented.");
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at) {
/* 121 */     return new ShapeCollectionPathIterator(this.shapes, at);
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at, double flatness) {
/* 126 */     return getPathIterator(at);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\awt\GeometryCollectionShape.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */