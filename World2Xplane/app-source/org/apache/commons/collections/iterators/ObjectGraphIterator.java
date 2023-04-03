/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ArrayStack;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public class ObjectGraphIterator implements Iterator {
/*  81 */   protected final ArrayStack stack = new ArrayStack(8);
/*     */   
/*     */   protected Object root;
/*     */   
/*     */   protected Transformer transformer;
/*     */   
/*     */   protected boolean hasNext = false;
/*     */   
/*     */   protected Iterator currentIterator;
/*     */   
/*     */   protected Object currentValue;
/*     */   
/*     */   protected Iterator lastUsedIterator;
/*     */   
/*     */   public ObjectGraphIterator(Object root, Transformer transformer) {
/* 108 */     if (root instanceof Iterator) {
/* 109 */       this.currentIterator = (Iterator)root;
/*     */     } else {
/* 111 */       this.root = root;
/*     */     } 
/* 113 */     this.transformer = transformer;
/*     */   }
/*     */   
/*     */   public ObjectGraphIterator(Iterator rootIterator) {
/* 128 */     this.currentIterator = rootIterator;
/* 129 */     this.transformer = null;
/*     */   }
/*     */   
/*     */   protected void updateCurrentIterator() {
/* 137 */     if (this.hasNext)
/*     */       return; 
/* 140 */     if (this.currentIterator == null) {
/* 141 */       if (this.root != null) {
/* 144 */         if (this.transformer == null) {
/* 145 */           findNext(this.root);
/*     */         } else {
/* 147 */           findNext(this.transformer.transform(this.root));
/*     */         } 
/* 149 */         this.root = null;
/*     */       } 
/*     */     } else {
/* 152 */       findNextByIterator(this.currentIterator);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void findNext(Object value) {
/* 162 */     if (value instanceof Iterator) {
/* 164 */       findNextByIterator((Iterator)value);
/*     */     } else {
/* 167 */       this.currentValue = value;
/* 168 */       this.hasNext = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void findNextByIterator(Iterator iterator) {
/* 178 */     if (iterator != this.currentIterator) {
/* 180 */       if (this.currentIterator != null)
/* 181 */         this.stack.push(this.currentIterator); 
/* 183 */       this.currentIterator = iterator;
/*     */     } 
/* 186 */     while (this.currentIterator.hasNext() && !this.hasNext) {
/* 187 */       Object next = this.currentIterator.next();
/* 188 */       if (this.transformer != null)
/* 189 */         next = this.transformer.transform(next); 
/* 191 */       findNext(next);
/*     */     } 
/* 193 */     if (!this.hasNext)
/* 195 */       if (!this.stack.isEmpty()) {
/* 199 */         this.currentIterator = (Iterator)this.stack.pop();
/* 200 */         findNextByIterator(this.currentIterator);
/*     */       }  
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 211 */     updateCurrentIterator();
/* 212 */     return this.hasNext;
/*     */   }
/*     */   
/*     */   public Object next() {
/* 222 */     updateCurrentIterator();
/* 223 */     if (!this.hasNext)
/* 224 */       throw new NoSuchElementException("No more elements in the iteration"); 
/* 226 */     this.lastUsedIterator = this.currentIterator;
/* 227 */     Object result = this.currentValue;
/* 228 */     this.currentValue = null;
/* 229 */     this.hasNext = false;
/* 230 */     return result;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 247 */     if (this.lastUsedIterator == null)
/* 248 */       throw new IllegalStateException("Iterator remove() cannot be called at this time"); 
/* 250 */     this.lastUsedIterator.remove();
/* 251 */     this.lastUsedIterator = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\ObjectGraphIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */