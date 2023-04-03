/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public class FilterIterator implements Iterator {
/*     */   private Iterator iterator;
/*     */   
/*     */   private Predicate predicate;
/*     */   
/*     */   private Object nextObject;
/*     */   
/*     */   private boolean nextObjectSet = false;
/*     */   
/*     */   public FilterIterator() {}
/*     */   
/*     */   public FilterIterator(Iterator iterator) {
/*  66 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */   public FilterIterator(Iterator iterator, Predicate predicate) {
/*  78 */     this.iterator = iterator;
/*  79 */     this.predicate = predicate;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  91 */     if (this.nextObjectSet)
/*  92 */       return true; 
/*  94 */     return setNextObject();
/*     */   }
/*     */   
/*     */   public Object next() {
/* 107 */     if (!this.nextObjectSet && 
/* 108 */       !setNextObject())
/* 109 */       throw new NoSuchElementException(); 
/* 112 */     this.nextObjectSet = false;
/* 113 */     return this.nextObject;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 128 */     if (this.nextObjectSet)
/* 129 */       throw new IllegalStateException("remove() cannot be called"); 
/* 131 */     this.iterator.remove();
/*     */   }
/*     */   
/*     */   public Iterator getIterator() {
/* 141 */     return this.iterator;
/*     */   }
/*     */   
/*     */   public void setIterator(Iterator iterator) {
/* 151 */     this.iterator = iterator;
/* 152 */     this.nextObject = null;
/* 153 */     this.nextObjectSet = false;
/*     */   }
/*     */   
/*     */   public Predicate getPredicate() {
/* 163 */     return this.predicate;
/*     */   }
/*     */   
/*     */   public void setPredicate(Predicate predicate) {
/* 172 */     this.predicate = predicate;
/* 173 */     this.nextObject = null;
/* 174 */     this.nextObjectSet = false;
/*     */   }
/*     */   
/*     */   private boolean setNextObject() {
/* 183 */     while (this.iterator.hasNext()) {
/* 184 */       Object object = this.iterator.next();
/* 185 */       if (this.predicate.evaluate(object)) {
/* 186 */         this.nextObject = object;
/* 187 */         this.nextObjectSet = true;
/* 188 */         return true;
/*     */       } 
/*     */     } 
/* 191 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\FilterIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */