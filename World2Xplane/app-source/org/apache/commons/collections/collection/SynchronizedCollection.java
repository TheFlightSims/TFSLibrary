/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class SynchronizedCollection implements Collection, Serializable {
/*     */   private static final long serialVersionUID = 2412805092710877986L;
/*     */   
/*     */   protected final Collection collection;
/*     */   
/*     */   protected final Object lock;
/*     */   
/*     */   public static Collection decorate(Collection coll) {
/*  60 */     return new SynchronizedCollection(coll);
/*     */   }
/*     */   
/*     */   protected SynchronizedCollection(Collection collection) {
/*  71 */     if (collection == null)
/*  72 */       throw new IllegalArgumentException("Collection must not be null"); 
/*  74 */     this.collection = collection;
/*  75 */     this.lock = this;
/*     */   }
/*     */   
/*     */   protected SynchronizedCollection(Collection collection, Object lock) {
/*  86 */     if (collection == null)
/*  87 */       throw new IllegalArgumentException("Collection must not be null"); 
/*  89 */     this.collection = collection;
/*  90 */     this.lock = lock;
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/*  95 */     synchronized (this.lock) {
/*  96 */       return this.collection.add(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 101 */     synchronized (this.lock) {
/* 102 */       return this.collection.addAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 107 */     synchronized (this.lock) {
/* 108 */       this.collection.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 113 */     synchronized (this.lock) {
/* 114 */       return this.collection.contains(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 119 */     synchronized (this.lock) {
/* 120 */       return this.collection.containsAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 125 */     synchronized (this.lock) {
/* 126 */       return this.collection.isEmpty();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 141 */     return this.collection.iterator();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 145 */     synchronized (this.lock) {
/* 146 */       return this.collection.toArray();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] object) {
/* 151 */     synchronized (this.lock) {
/* 152 */       return this.collection.toArray(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 157 */     synchronized (this.lock) {
/* 158 */       return this.collection.remove(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 163 */     synchronized (this.lock) {
/* 164 */       return this.collection.removeAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 169 */     synchronized (this.lock) {
/* 170 */       return this.collection.retainAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 175 */     synchronized (this.lock) {
/* 176 */       return this.collection.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 181 */     synchronized (this.lock) {
/* 182 */       if (object == this)
/* 183 */         return true; 
/* 185 */       return this.collection.equals(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 190 */     synchronized (this.lock) {
/* 191 */       return this.collection.hashCode();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 196 */     synchronized (this.lock) {
/* 197 */       return this.collection.toString();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\collection\SynchronizedCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */