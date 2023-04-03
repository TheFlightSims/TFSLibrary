/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ 
/*     */ public class CollatingIterator implements Iterator {
/*  46 */   private Comparator comparator = null;
/*     */   
/*  49 */   private ArrayList iterators = null;
/*     */   
/*  52 */   private ArrayList values = null;
/*     */   
/*  55 */   private BitSet valueSet = null;
/*     */   
/*  58 */   private int lastReturned = -1;
/*     */   
/*     */   public CollatingIterator() {
/*  68 */     this((Comparator)null, 2);
/*     */   }
/*     */   
/*     */   public CollatingIterator(Comparator comp) {
/*  79 */     this(comp, 2);
/*     */   }
/*     */   
/*     */   public CollatingIterator(Comparator comp, int initIterCapacity) {
/*  93 */     this.iterators = new ArrayList(initIterCapacity);
/*  94 */     setComparator(comp);
/*     */   }
/*     */   
/*     */   public CollatingIterator(Comparator comp, Iterator a, Iterator b) {
/* 108 */     this(comp, 2);
/* 109 */     addIterator(a);
/* 110 */     addIterator(b);
/*     */   }
/*     */   
/*     */   public CollatingIterator(Comparator comp, Iterator[] iterators) {
/* 123 */     this(comp, iterators.length);
/* 124 */     for (int i = 0; i < iterators.length; i++)
/* 125 */       addIterator(iterators[i]); 
/*     */   }
/*     */   
/*     */   public CollatingIterator(Comparator comp, Collection iterators) {
/* 141 */     this(comp, iterators.size());
/* 142 */     for (Iterator it = iterators.iterator(); it.hasNext(); ) {
/* 143 */       Iterator item = it.next();
/* 144 */       addIterator(item);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addIterator(Iterator iterator) {
/* 158 */     checkNotStarted();
/* 159 */     if (iterator == null)
/* 160 */       throw new NullPointerException("Iterator must not be null"); 
/* 162 */     this.iterators.add(iterator);
/*     */   }
/*     */   
/*     */   public void setIterator(int index, Iterator iterator) {
/* 175 */     checkNotStarted();
/* 176 */     if (iterator == null)
/* 177 */       throw new NullPointerException("Iterator must not be null"); 
/* 179 */     this.iterators.set(index, iterator);
/*     */   }
/*     */   
/*     */   public List getIterators() {
/* 188 */     return UnmodifiableList.decorate(this.iterators);
/*     */   }
/*     */   
/*     */   public Comparator getComparator() {
/* 195 */     return this.comparator;
/*     */   }
/*     */   
/*     */   public void setComparator(Comparator comp) {
/* 204 */     checkNotStarted();
/* 205 */     this.comparator = comp;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 216 */     start();
/* 217 */     return (anyValueSet(this.valueSet) || anyHasNext(this.iterators));
/*     */   }
/*     */   
/*     */   public Object next() throws NoSuchElementException {
/* 227 */     if (!hasNext())
/* 228 */       throw new NoSuchElementException(); 
/* 230 */     int leastIndex = least();
/* 231 */     if (leastIndex == -1)
/* 232 */       throw new NoSuchElementException(); 
/* 234 */     Object val = this.values.get(leastIndex);
/* 235 */     clear(leastIndex);
/* 236 */     this.lastReturned = leastIndex;
/* 237 */     return val;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 249 */     if (this.lastReturned == -1)
/* 250 */       throw new IllegalStateException("No value can be removed at present"); 
/* 252 */     Iterator it = this.iterators.get(this.lastReturned);
/* 253 */     it.remove();
/*     */   }
/*     */   
/*     */   private void start() {
/* 262 */     if (this.values == null) {
/* 263 */       this.values = new ArrayList(this.iterators.size());
/* 264 */       this.valueSet = new BitSet(this.iterators.size());
/* 265 */       for (int i = 0; i < this.iterators.size(); i++) {
/* 266 */         this.values.add(null);
/* 267 */         this.valueSet.clear(i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean set(int i) {
/* 282 */     Iterator it = this.iterators.get(i);
/* 283 */     if (it.hasNext()) {
/* 284 */       this.values.set(i, it.next());
/* 285 */       this.valueSet.set(i);
/* 286 */       return true;
/*     */     } 
/* 288 */     this.values.set(i, null);
/* 289 */     this.valueSet.clear(i);
/* 290 */     return false;
/*     */   }
/*     */   
/*     */   private void clear(int i) {
/* 299 */     this.values.set(i, null);
/* 300 */     this.valueSet.clear(i);
/*     */   }
/*     */   
/*     */   private void checkNotStarted() throws IllegalStateException {
/* 310 */     if (this.values != null)
/* 311 */       throw new IllegalStateException("Can't do that after next or hasNext has been called."); 
/*     */   }
/*     */   
/*     */   private int least() {
/* 322 */     int leastIndex = -1;
/* 323 */     Object leastObject = null;
/* 324 */     for (int i = 0; i < this.values.size(); i++) {
/* 325 */       if (!this.valueSet.get(i))
/* 326 */         set(i); 
/* 328 */       if (this.valueSet.get(i))
/* 329 */         if (leastIndex == -1) {
/* 330 */           leastIndex = i;
/* 331 */           leastObject = this.values.get(i);
/*     */         } else {
/* 333 */           Object curObject = this.values.get(i);
/* 334 */           if (this.comparator.compare(curObject, leastObject) < 0) {
/* 335 */             leastObject = curObject;
/* 336 */             leastIndex = i;
/*     */           } 
/*     */         }  
/*     */     } 
/* 341 */     return leastIndex;
/*     */   }
/*     */   
/*     */   private boolean anyValueSet(BitSet set) {
/* 349 */     for (int i = 0; i < set.size(); i++) {
/* 350 */       if (set.get(i))
/* 351 */         return true; 
/*     */     } 
/* 354 */     return false;
/*     */   }
/*     */   
/*     */   private boolean anyHasNext(ArrayList iters) {
/* 362 */     for (int i = 0; i < iters.size(); i++) {
/* 363 */       Iterator it = iters.get(i);
/* 364 */       if (it.hasNext())
/* 365 */         return true; 
/*     */     } 
/* 368 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\CollatingIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */