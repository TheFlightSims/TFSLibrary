/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class AbstractCollectionDecorator implements Collection {
/*     */   protected Collection collection;
/*     */   
/*     */   protected AbstractCollectionDecorator() {}
/*     */   
/*     */   protected AbstractCollectionDecorator(Collection coll) {
/*  63 */     if (coll == null)
/*  64 */       throw new IllegalArgumentException("Collection must not be null"); 
/*  66 */     this.collection = coll;
/*     */   }
/*     */   
/*     */   protected Collection getCollection() {
/*  75 */     return this.collection;
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/*  80 */     return this.collection.add(object);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/*  84 */     return this.collection.addAll(coll);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  88 */     this.collection.clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/*  92 */     return this.collection.contains(object);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  96 */     return this.collection.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 100 */     return this.collection.iterator();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 104 */     return this.collection.remove(object);
/*     */   }
/*     */   
/*     */   public int size() {
/* 108 */     return this.collection.size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 112 */     return this.collection.toArray();
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] object) {
/* 116 */     return this.collection.toArray(object);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 120 */     return this.collection.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 124 */     return this.collection.removeAll(coll);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 128 */     return this.collection.retainAll(coll);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 132 */     if (object == this)
/* 133 */       return true; 
/* 135 */     return this.collection.equals(object);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 139 */     return this.collection.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 143 */     return this.collection.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\collection\AbstractCollectionDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */