/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public final class LazySet<E> extends AbstractSet<E> {
/*     */   private final Iterator<? extends E> iterator;
/*     */   
/*     */   private E[] elements;
/*     */   
/*     */   private int size;
/*     */   
/*     */   public LazySet(Iterator<? extends E> iterator) {
/*  59 */     this.iterator = iterator;
/*  60 */     this.elements = (E[])new Object[4];
/*     */   }
/*     */   
/*     */   private void addNext() {
/*  69 */     if (this.size >= this.elements.length)
/*  70 */       this.elements = XArray.resize(this.elements, this.size * 2); 
/*  72 */     this.elements[this.size++] = this.iterator.next();
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/*  80 */     return new Iter();
/*     */   }
/*     */   
/*     */   public int size() {
/*  88 */     while (this.iterator.hasNext())
/*  89 */       addNext(); 
/*  91 */     return this.size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  99 */     return (this.size == 0 && !this.iterator.hasNext());
/*     */   }
/*     */   
/*     */   final boolean exists(int index) {
/* 111 */     return (index < this.size || this.iterator.hasNext());
/*     */   }
/*     */   
/*     */   public E get(int index) {
/* 118 */     while (index >= this.size) {
/* 119 */       if (!this.iterator.hasNext())
/* 120 */         throw new IndexOutOfBoundsException(String.valueOf(index)); 
/* 122 */       addNext();
/*     */     } 
/* 124 */     return this.elements[index];
/*     */   }
/*     */   
/*     */   private final class Iter implements Iterator<E> {
/*     */     private int cursor;
/*     */     
/*     */     private Iter() {}
/*     */     
/*     */     public boolean hasNext() {
/* 136 */       return LazySet.this.exists(this.cursor);
/*     */     }
/*     */     
/*     */     public E next() {
/* 141 */       return LazySet.this.get(this.cursor++);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 146 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\LazySet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */