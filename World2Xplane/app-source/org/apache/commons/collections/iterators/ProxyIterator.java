/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class ProxyIterator implements Iterator {
/*     */   private Iterator iterator;
/*     */   
/*     */   public ProxyIterator() {}
/*     */   
/*     */   public ProxyIterator(Iterator iterator) {
/*  54 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  66 */     return getIterator().hasNext();
/*     */   }
/*     */   
/*     */   public Object next() {
/*  77 */     return getIterator().next();
/*     */   }
/*     */   
/*     */   public void remove() {
/*  85 */     getIterator().remove();
/*     */   }
/*     */   
/*     */   public Iterator getIterator() {
/*  94 */     return this.iterator;
/*     */   }
/*     */   
/*     */   public void setIterator(Iterator iterator) {
/* 100 */     this.iterator = iterator;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\ProxyIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */