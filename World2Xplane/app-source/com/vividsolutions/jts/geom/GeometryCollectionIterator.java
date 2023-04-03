/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class GeometryCollectionIterator implements Iterator {
/*     */   private Geometry parent;
/*     */   
/*     */   private boolean atStart;
/*     */   
/*     */   private int max;
/*     */   
/*     */   private int index;
/*     */   
/*     */   private GeometryCollectionIterator subcollectionIterator;
/*     */   
/*     */   public GeometryCollectionIterator(Geometry parent) {
/*  86 */     this.parent = parent;
/*  87 */     this.atStart = true;
/*  88 */     this.index = 0;
/*  89 */     this.max = parent.getNumGeometries();
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  98 */     if (this.atStart)
/*  99 */       return true; 
/* 101 */     if (this.subcollectionIterator != null) {
/* 102 */       if (this.subcollectionIterator.hasNext())
/* 103 */         return true; 
/* 105 */       this.subcollectionIterator = null;
/*     */     } 
/* 107 */     if (this.index >= this.max)
/* 108 */       return false; 
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   public Object next() {
/* 120 */     if (this.atStart) {
/* 121 */       this.atStart = false;
/* 122 */       return this.parent;
/*     */     } 
/* 124 */     if (this.subcollectionIterator != null) {
/* 125 */       if (this.subcollectionIterator.hasNext())
/* 126 */         return this.subcollectionIterator.next(); 
/* 129 */       this.subcollectionIterator = null;
/*     */     } 
/* 132 */     if (this.index >= this.max)
/* 133 */       throw new NoSuchElementException(); 
/* 135 */     Geometry obj = this.parent.getGeometryN(this.index++);
/* 136 */     if (obj instanceof GeometryCollection) {
/* 137 */       this.subcollectionIterator = new GeometryCollectionIterator(obj);
/* 139 */       return this.subcollectionIterator.next();
/*     */     } 
/* 141 */     return obj;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 150 */     throw new UnsupportedOperationException(getClass().getName());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\GeometryCollectionIterator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */