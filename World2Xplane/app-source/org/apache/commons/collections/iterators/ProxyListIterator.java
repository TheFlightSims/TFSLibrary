/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ public class ProxyListIterator implements ListIterator {
/*     */   private ListIterator iterator;
/*     */   
/*     */   public ProxyListIterator() {}
/*     */   
/*     */   public ProxyListIterator(ListIterator iterator) {
/*  56 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */   public void add(Object o) {
/*  68 */     getListIterator().add(o);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  77 */     return getListIterator().hasNext();
/*     */   }
/*     */   
/*     */   public boolean hasPrevious() {
/*  86 */     return getListIterator().hasPrevious();
/*     */   }
/*     */   
/*     */   public Object next() {
/*  95 */     return getListIterator().next();
/*     */   }
/*     */   
/*     */   public int nextIndex() {
/* 104 */     return getListIterator().nextIndex();
/*     */   }
/*     */   
/*     */   public Object previous() {
/* 113 */     return getListIterator().previous();
/*     */   }
/*     */   
/*     */   public int previousIndex() {
/* 122 */     return getListIterator().previousIndex();
/*     */   }
/*     */   
/*     */   public void remove() {
/* 131 */     getListIterator().remove();
/*     */   }
/*     */   
/*     */   public void set(Object o) {
/* 140 */     getListIterator().set(o);
/*     */   }
/*     */   
/*     */   public ListIterator getListIterator() {
/* 151 */     return this.iterator;
/*     */   }
/*     */   
/*     */   public void setListIterator(ListIterator iterator) {
/* 159 */     this.iterator = iterator;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\ProxyListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */