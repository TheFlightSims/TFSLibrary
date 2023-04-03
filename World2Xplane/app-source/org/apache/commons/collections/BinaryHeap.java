/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public final class BinaryHeap extends AbstractCollection implements PriorityQueue, Buffer {
/*     */   private static final int DEFAULT_CAPACITY = 13;
/*     */   
/*     */   int m_size;
/*     */   
/*     */   Object[] m_elements;
/*     */   
/*     */   boolean m_isMinHeap;
/*     */   
/*     */   Comparator m_comparator;
/*     */   
/*     */   public BinaryHeap() {
/*  93 */     this(13, true);
/*     */   }
/*     */   
/*     */   public BinaryHeap(Comparator comparator) {
/* 104 */     this();
/* 105 */     this.m_comparator = comparator;
/*     */   }
/*     */   
/*     */   public BinaryHeap(int capacity) {
/* 117 */     this(capacity, true);
/*     */   }
/*     */   
/*     */   public BinaryHeap(int capacity, Comparator comparator) {
/* 130 */     this(capacity);
/* 131 */     this.m_comparator = comparator;
/*     */   }
/*     */   
/*     */   public BinaryHeap(boolean isMinHeap) {
/* 141 */     this(13, isMinHeap);
/*     */   }
/*     */   
/*     */   public BinaryHeap(boolean isMinHeap, Comparator comparator) {
/* 153 */     this(isMinHeap);
/* 154 */     this.m_comparator = comparator;
/*     */   }
/*     */   
/*     */   public BinaryHeap(int capacity, boolean isMinHeap) {
/* 169 */     if (capacity <= 0)
/* 170 */       throw new IllegalArgumentException("invalid capacity"); 
/* 172 */     this.m_isMinHeap = isMinHeap;
/* 175 */     this.m_elements = new Object[capacity + 1];
/*     */   }
/*     */   
/*     */   public BinaryHeap(int capacity, boolean isMinHeap, Comparator comparator) {
/* 190 */     this(capacity, isMinHeap);
/* 191 */     this.m_comparator = comparator;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 199 */     this.m_elements = new Object[this.m_elements.length];
/* 200 */     this.m_size = 0;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 210 */     return (this.m_size == 0);
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 221 */     return (this.m_elements.length == this.m_size + 1);
/*     */   }
/*     */   
/*     */   public void insert(Object element) {
/* 230 */     if (isFull())
/* 231 */       grow(); 
/* 234 */     if (this.m_isMinHeap) {
/* 235 */       percolateUpMinHeap(element);
/*     */     } else {
/* 237 */       percolateUpMaxHeap(element);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object peek() throws NoSuchElementException {
/* 248 */     if (isEmpty())
/* 249 */       throw new NoSuchElementException(); 
/* 251 */     return this.m_elements[1];
/*     */   }
/*     */   
/*     */   public Object pop() throws NoSuchElementException {
/* 262 */     Object result = peek();
/* 263 */     this.m_elements[1] = this.m_elements[this.m_size--];
/* 267 */     this.m_elements[this.m_size + 1] = null;
/* 269 */     if (this.m_size != 0)
/* 271 */       if (this.m_isMinHeap) {
/* 272 */         percolateDownMinHeap(1);
/*     */       } else {
/* 274 */         percolateDownMaxHeap(1);
/*     */       }  
/* 278 */     return result;
/*     */   }
/*     */   
/*     */   protected void percolateDownMinHeap(int index) {
/* 289 */     Object element = this.m_elements[index];
/* 290 */     int hole = index;
/* 292 */     while (hole * 2 <= this.m_size) {
/* 293 */       int child = hole * 2;
/* 297 */       if (child != this.m_size && compare(this.m_elements[child + 1], this.m_elements[child]) < 0)
/* 298 */         child++; 
/* 302 */       if (compare(this.m_elements[child], element) >= 0)
/*     */         break; 
/* 306 */       this.m_elements[hole] = this.m_elements[child];
/* 307 */       hole = child;
/*     */     } 
/* 310 */     this.m_elements[hole] = element;
/*     */   }
/*     */   
/*     */   protected void percolateDownMaxHeap(int index) {
/* 321 */     Object element = this.m_elements[index];
/* 322 */     int hole = index;
/* 324 */     while (hole * 2 <= this.m_size) {
/* 325 */       int child = hole * 2;
/* 329 */       if (child != this.m_size && compare(this.m_elements[child + 1], this.m_elements[child]) > 0)
/* 330 */         child++; 
/* 334 */       if (compare(this.m_elements[child], element) <= 0)
/*     */         break; 
/* 338 */       this.m_elements[hole] = this.m_elements[child];
/* 339 */       hole = child;
/*     */     } 
/* 342 */     this.m_elements[hole] = element;
/*     */   }
/*     */   
/*     */   protected void percolateUpMinHeap(int index) {
/* 353 */     int hole = index;
/* 354 */     Object element = this.m_elements[hole];
/* 355 */     while (hole > 1 && compare(element, this.m_elements[hole / 2]) < 0) {
/* 358 */       int next = hole / 2;
/* 359 */       this.m_elements[hole] = this.m_elements[next];
/* 360 */       hole = next;
/*     */     } 
/* 362 */     this.m_elements[hole] = element;
/*     */   }
/*     */   
/*     */   protected void percolateUpMinHeap(Object element) {
/* 373 */     this.m_elements[++this.m_size] = element;
/* 374 */     percolateUpMinHeap(this.m_size);
/*     */   }
/*     */   
/*     */   protected void percolateUpMaxHeap(int index) {
/* 385 */     int hole = index;
/* 386 */     Object element = this.m_elements[hole];
/* 388 */     while (hole > 1 && compare(element, this.m_elements[hole / 2]) > 0) {
/* 391 */       int next = hole / 2;
/* 392 */       this.m_elements[hole] = this.m_elements[next];
/* 393 */       hole = next;
/*     */     } 
/* 396 */     this.m_elements[hole] = element;
/*     */   }
/*     */   
/*     */   protected void percolateUpMaxHeap(Object element) {
/* 407 */     this.m_elements[++this.m_size] = element;
/* 408 */     percolateUpMaxHeap(this.m_size);
/*     */   }
/*     */   
/*     */   private int compare(Object a, Object b) {
/* 420 */     if (this.m_comparator != null)
/* 421 */       return this.m_comparator.compare(a, b); 
/* 423 */     return ((Comparable)a).compareTo(b);
/*     */   }
/*     */   
/*     */   protected void grow() {
/* 431 */     Object[] elements = new Object[this.m_elements.length * 2];
/* 432 */     System.arraycopy(this.m_elements, 0, elements, 0, this.m_elements.length);
/* 433 */     this.m_elements = elements;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 443 */     StringBuffer sb = new StringBuffer();
/* 445 */     sb.append("[ ");
/* 447 */     for (int i = 1; i < this.m_size + 1; i++) {
/* 448 */       if (i != 1)
/* 449 */         sb.append(", "); 
/* 451 */       sb.append(this.m_elements[i]);
/*     */     } 
/* 454 */     sb.append(" ]");
/* 456 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 466 */     return new Iterator(this) {
/*     */         private int index;
/*     */         
/*     */         private int lastReturnedIndex;
/*     */         
/*     */         private final BinaryHeap this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 472 */           return (this.index <= this.this$0.m_size);
/*     */         }
/*     */         
/*     */         public Object next() {
/* 476 */           if (!hasNext())
/* 476 */             throw new NoSuchElementException(); 
/* 477 */           this.lastReturnedIndex = this.index;
/* 478 */           this.index++;
/* 479 */           return this.this$0.m_elements[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 483 */           if (this.lastReturnedIndex == -1)
/* 484 */             throw new IllegalStateException(); 
/* 486 */           this.this$0.m_elements[this.lastReturnedIndex] = this.this$0.m_elements[this.this$0.m_size];
/* 487 */           this.this$0.m_elements[this.this$0.m_size] = null;
/* 488 */           this.this$0.m_size--;
/* 489 */           if (this.this$0.m_size != 0 && this.lastReturnedIndex <= this.this$0.m_size) {
/* 490 */             int compareToParent = 0;
/* 491 */             if (this.lastReturnedIndex > 1)
/* 492 */               compareToParent = this.this$0.compare(this.this$0.m_elements[this.lastReturnedIndex], this.this$0.m_elements[this.lastReturnedIndex / 2]); 
/* 495 */             if (this.this$0.m_isMinHeap) {
/* 496 */               if (this.lastReturnedIndex > 1 && compareToParent < 0) {
/* 497 */                 this.this$0.percolateUpMinHeap(this.lastReturnedIndex);
/*     */               } else {
/* 499 */                 this.this$0.percolateDownMinHeap(this.lastReturnedIndex);
/*     */               } 
/* 502 */             } else if (this.lastReturnedIndex > 1 && compareToParent > 0) {
/* 503 */               this.this$0.percolateUpMaxHeap(this.lastReturnedIndex);
/*     */             } else {
/* 505 */               this.this$0.percolateDownMaxHeap(this.lastReturnedIndex);
/*     */             } 
/*     */           } 
/* 509 */           this.index--;
/* 510 */           this.lastReturnedIndex = -1;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 524 */     insert(object);
/* 525 */     return true;
/*     */   }
/*     */   
/*     */   public Object get() {
/*     */     try {
/* 536 */       return peek();
/* 537 */     } catch (NoSuchElementException e) {
/* 538 */       throw new BufferUnderflowException();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove() {
/*     */     try {
/* 550 */       return pop();
/* 551 */     } catch (NoSuchElementException e) {
/* 552 */       throw new BufferUnderflowException();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 562 */     return this.m_size;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\BinaryHeap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */