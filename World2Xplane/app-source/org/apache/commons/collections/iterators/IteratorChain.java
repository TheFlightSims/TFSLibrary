/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ 
/*     */ public class IteratorChain implements Iterator {
/*  54 */   protected final List iteratorChain = new ArrayList();
/*     */   
/*  56 */   protected int currentIteratorIndex = 0;
/*     */   
/*  58 */   protected Iterator currentIterator = null;
/*     */   
/*  64 */   protected Iterator lastUsedIterator = null;
/*     */   
/*     */   protected boolean isLocked = false;
/*     */   
/*     */   public IteratorChain() {}
/*     */   
/*     */   public IteratorChain(Iterator iterator) {
/*  90 */     addIterator(iterator);
/*     */   }
/*     */   
/*     */   public IteratorChain(Iterator a, Iterator b) {
/* 103 */     addIterator(a);
/* 104 */     addIterator(b);
/*     */   }
/*     */   
/*     */   public IteratorChain(Iterator[] iterators) {
/* 116 */     for (int i = 0; i < iterators.length; i++)
/* 117 */       addIterator(iterators[i]); 
/*     */   }
/*     */   
/*     */   public IteratorChain(Collection iterators) {
/* 131 */     for (Iterator it = iterators.iterator(); it.hasNext(); ) {
/* 132 */       Iterator item = it.next();
/* 133 */       addIterator(item);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addIterator(Iterator iterator) {
/* 146 */     checkLocked();
/* 147 */     if (iterator == null)
/* 148 */       throw new NullPointerException("Iterator must not be null"); 
/* 150 */     this.iteratorChain.add(iterator);
/*     */   }
/*     */   
/*     */   public void setIterator(int index, Iterator iterator) throws IndexOutOfBoundsException {
/* 163 */     checkLocked();
/* 164 */     if (iterator == null)
/* 165 */       throw new NullPointerException("Iterator must not be null"); 
/* 167 */     this.iteratorChain.set(index, iterator);
/*     */   }
/*     */   
/*     */   public List getIterators() {
/* 176 */     return UnmodifiableList.decorate(this.iteratorChain);
/*     */   }
/*     */   
/*     */   public int size() {
/* 185 */     return this.iteratorChain.size();
/*     */   }
/*     */   
/*     */   public boolean isLocked() {
/* 196 */     return this.isLocked;
/*     */   }
/*     */   
/*     */   private void checkLocked() {
/* 203 */     if (this.isLocked == true)
/* 204 */       throw new UnsupportedOperationException("IteratorChain cannot be changed after the first use of a method from the Iterator interface"); 
/*     */   }
/*     */   
/*     */   private void lockChain() {
/* 213 */     if (!this.isLocked)
/* 214 */       this.isLocked = true; 
/*     */   }
/*     */   
/*     */   protected void updateCurrentIterator() {
/* 223 */     if (this.currentIterator == null) {
/* 224 */       if (this.iteratorChain.isEmpty()) {
/* 225 */         this.currentIterator = EmptyIterator.INSTANCE;
/*     */       } else {
/* 227 */         this.currentIterator = this.iteratorChain.get(0);
/*     */       } 
/* 231 */       this.lastUsedIterator = this.currentIterator;
/*     */     } 
/* 234 */     while (!this.currentIterator.hasNext() && this.currentIteratorIndex < this.iteratorChain.size() - 1) {
/* 235 */       this.currentIteratorIndex++;
/* 236 */       this.currentIterator = this.iteratorChain.get(this.currentIteratorIndex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 247 */     lockChain();
/* 248 */     updateCurrentIterator();
/* 249 */     this.lastUsedIterator = this.currentIterator;
/* 251 */     return this.currentIterator.hasNext();
/*     */   }
/*     */   
/*     */   public Object next() {
/* 261 */     lockChain();
/* 262 */     updateCurrentIterator();
/* 263 */     this.lastUsedIterator = this.currentIterator;
/* 265 */     return this.currentIterator.next();
/*     */   }
/*     */   
/*     */   public void remove() {
/* 283 */     lockChain();
/* 284 */     if (this.currentIterator == null)
/* 285 */       updateCurrentIterator(); 
/* 287 */     this.lastUsedIterator.remove();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\IteratorChain.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */