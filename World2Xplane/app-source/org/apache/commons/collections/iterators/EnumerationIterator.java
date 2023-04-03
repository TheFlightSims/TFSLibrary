/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class EnumerationIterator implements Iterator {
/*     */   private Collection collection;
/*     */   
/*     */   private Enumeration enumeration;
/*     */   
/*     */   private Object last;
/*     */   
/*     */   public EnumerationIterator() {
/*  49 */     this(null, null);
/*     */   }
/*     */   
/*     */   public EnumerationIterator(Enumeration enumeration) {
/*  59 */     this(enumeration, null);
/*     */   }
/*     */   
/*     */   public EnumerationIterator(Enumeration enumeration, Collection collection) {
/*  71 */     this.enumeration = enumeration;
/*  72 */     this.collection = collection;
/*  73 */     this.last = null;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  85 */     return this.enumeration.hasMoreElements();
/*     */   }
/*     */   
/*     */   public Object next() {
/*  95 */     this.last = this.enumeration.nextElement();
/*  96 */     return this.last;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 110 */     if (this.collection != null) {
/* 111 */       if (this.last != null) {
/* 112 */         this.collection.remove(this.last);
/*     */       } else {
/* 114 */         throw new IllegalStateException("next() must have been called for remove() to function");
/*     */       } 
/*     */     } else {
/* 117 */       throw new UnsupportedOperationException("No Collection associated with this Iterator");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Enumeration getEnumeration() {
/* 129 */     return this.enumeration;
/*     */   }
/*     */   
/*     */   public void setEnumeration(Enumeration enumeration) {
/* 138 */     this.enumeration = enumeration;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\EnumerationIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */