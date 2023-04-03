/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public class TransformIterator implements Iterator {
/*     */   private Iterator iterator;
/*     */   
/*     */   private Transformer transformer;
/*     */   
/*     */   public TransformIterator() {}
/*     */   
/*     */   public TransformIterator(Iterator iterator) {
/*  57 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */   public TransformIterator(Iterator iterator, Transformer transformer) {
/*  70 */     this.iterator = iterator;
/*  71 */     this.transformer = transformer;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  76 */     return this.iterator.hasNext();
/*     */   }
/*     */   
/*     */   public Object next() {
/*  88 */     return transform(this.iterator.next());
/*     */   }
/*     */   
/*     */   public void remove() {
/*  92 */     this.iterator.remove();
/*     */   }
/*     */   
/*     */   public Iterator getIterator() {
/* 102 */     return this.iterator;
/*     */   }
/*     */   
/*     */   public void setIterator(Iterator iterator) {
/* 112 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */   public Transformer getTransformer() {
/* 122 */     return this.transformer;
/*     */   }
/*     */   
/*     */   public void setTransformer(Transformer transformer) {
/* 132 */     this.transformer = transformer;
/*     */   }
/*     */   
/*     */   protected Object transform(Object source) {
/* 144 */     if (this.transformer != null)
/* 145 */       return this.transformer.transform(source); 
/* 147 */     return source;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\TransformIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */