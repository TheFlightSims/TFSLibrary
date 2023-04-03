/*     */ package com.vividsolutions.jts.awt;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class ShapeCollectionPathIterator implements PathIterator {
/*     */   private Iterator shapeIterator;
/*     */   
/*  50 */   private PathIterator currentPathIterator = new PathIterator() {
/*     */       public int getWindingRule() {
/*  52 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public boolean isDone() {
/*  56 */         return true;
/*     */       }
/*     */       
/*     */       public void next() {}
/*     */       
/*     */       public int currentSegment(float[] coords) {
/*  63 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public int currentSegment(double[] coords) {
/*  67 */         throw new UnsupportedOperationException();
/*     */       }
/*     */     };
/*     */   
/*     */   private AffineTransform affineTransform;
/*     */   
/*     */   private boolean done = false;
/*     */   
/*     */   public ShapeCollectionPathIterator(Collection shapes, AffineTransform affineTransform) {
/*  82 */     this.shapeIterator = shapes.iterator();
/*  83 */     this.affineTransform = affineTransform;
/*  84 */     next();
/*     */   }
/*     */   
/*     */   public int getWindingRule() {
/*  95 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/*  99 */     return this.done;
/*     */   }
/*     */   
/*     */   public void next() {
/* 103 */     this.currentPathIterator.next();
/* 105 */     if (this.currentPathIterator.isDone() && !this.shapeIterator.hasNext()) {
/* 106 */       this.done = true;
/*     */       return;
/*     */     } 
/* 109 */     if (this.currentPathIterator.isDone())
/* 110 */       this.currentPathIterator = ((Shape)this.shapeIterator.next()).getPathIterator(this.affineTransform); 
/*     */   }
/*     */   
/*     */   public int currentSegment(float[] coords) {
/* 115 */     return this.currentPathIterator.currentSegment(coords);
/*     */   }
/*     */   
/*     */   public int currentSegment(double[] coords) {
/* 119 */     return this.currentPathIterator.currentSegment(coords);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\awt\ShapeCollectionPathIterator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */