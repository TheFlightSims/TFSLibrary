/*     */ package org.apache.commons.collections.buffer;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.Buffer;
/*     */ import org.apache.commons.collections.BufferUnderflowException;
/*     */ 
/*     */ public class PriorityBuffer extends AbstractCollection implements Buffer, Serializable {
/*     */   private static final long serialVersionUID = 6891186490470027896L;
/*     */   
/*     */   private static final int DEFAULT_CAPACITY = 13;
/*     */   
/*     */   protected Object[] elements;
/*     */   
/*     */   protected int size;
/*     */   
/*     */   protected boolean ascendingOrder;
/*     */   
/*     */   protected Comparator comparator;
/*     */   
/*     */   public PriorityBuffer() {
/* 101 */     this(13, true, null);
/*     */   }
/*     */   
/*     */   public PriorityBuffer(Comparator comparator) {
/* 112 */     this(13, true, comparator);
/*     */   }
/*     */   
/*     */   public PriorityBuffer(boolean ascendingOrder) {
/* 123 */     this(13, ascendingOrder, null);
/*     */   }
/*     */   
/*     */   public PriorityBuffer(boolean ascendingOrder, Comparator comparator) {
/* 135 */     this(13, ascendingOrder, comparator);
/*     */   }
/*     */   
/*     */   public PriorityBuffer(int capacity) {
/* 146 */     this(capacity, true, null);
/*     */   }
/*     */   
/*     */   public PriorityBuffer(int capacity, Comparator comparator) {
/* 159 */     this(capacity, true, comparator);
/*     */   }
/*     */   
/*     */   public PriorityBuffer(int capacity, boolean ascendingOrder) {
/* 172 */     this(capacity, ascendingOrder, null);
/*     */   }
/*     */   
/*     */   public PriorityBuffer(int capacity, boolean ascendingOrder, Comparator comparator) {
/* 188 */     if (capacity <= 0)
/* 189 */       throw new IllegalArgumentException("invalid capacity"); 
/* 191 */     this.ascendingOrder = ascendingOrder;
/* 194 */     this.elements = new Object[capacity + 1];
/* 195 */     this.comparator = comparator;
/*     */   }
/*     */   
/*     */   public boolean isAscendingOrder() {
/* 205 */     return this.ascendingOrder;
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 214 */     return this.comparator;
/*     */   }
/*     */   
/*     */   public int size() {
/* 224 */     return this.size;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 231 */     this.elements = new Object[this.elements.length];
/* 232 */     this.size = 0;
/*     */   }
/*     */   
/*     */   public boolean add(Object element) {
/* 244 */     if (isAtCapacity())
/* 245 */       grow(); 
/* 248 */     if (this.ascendingOrder) {
/* 249 */       percolateUpMinHeap(element);
/*     */     } else {
/* 251 */       percolateUpMaxHeap(element);
/*     */     } 
/* 253 */     return true;
/*     */   }
/*     */   
/*     */   public Object get() {
/* 263 */     if (isEmpty())
/* 264 */       throw new BufferUnderflowException(); 
/* 266 */     return this.elements[1];
/*     */   }
/*     */   
/*     */   public Object remove() {
/* 277 */     Object result = get();
/* 278 */     this.elements[1] = this.elements[this.size--];
/* 282 */     this.elements[this.size + 1] = null;
/* 284 */     if (this.size != 0)
/* 286 */       if (this.ascendingOrder) {
/* 287 */         percolateDownMinHeap(1);
/*     */       } else {
/* 289 */         percolateDownMaxHeap(1);
/*     */       }  
/* 293 */     return result;
/*     */   }
/*     */   
/*     */   protected boolean isAtCapacity() {
/* 304 */     return (this.elements.length == this.size + 1);
/*     */   }
/*     */   
/*     */   protected void percolateDownMinHeap(int index) {
/* 316 */     Object element = this.elements[index];
/* 317 */     int hole = index;
/* 319 */     while (hole * 2 <= this.size) {
/* 320 */       int child = hole * 2;
/* 324 */       if (child != this.size && compare(this.elements[child + 1], this.elements[child]) < 0)
/* 325 */         child++; 
/* 329 */       if (compare(this.elements[child], element) >= 0)
/*     */         break; 
/* 333 */       this.elements[hole] = this.elements[child];
/* 334 */       hole = child;
/*     */     } 
/* 337 */     this.elements[hole] = element;
/*     */   }
/*     */   
/*     */   protected void percolateDownMaxHeap(int index) {
/* 348 */     Object element = this.elements[index];
/* 349 */     int hole = index;
/* 351 */     while (hole * 2 <= this.size) {
/* 352 */       int child = hole * 2;
/* 356 */       if (child != this.size && compare(this.elements[child + 1], this.elements[child]) > 0)
/* 357 */         child++; 
/* 361 */       if (compare(this.elements[child], element) <= 0)
/*     */         break; 
/* 365 */       this.elements[hole] = this.elements[child];
/* 366 */       hole = child;
/*     */     } 
/* 369 */     this.elements[hole] = element;
/*     */   }
/*     */   
/*     */   protected void percolateUpMinHeap(int index) {
/* 380 */     int hole = index;
/* 381 */     Object element = this.elements[hole];
/* 382 */     while (hole > 1 && compare(element, this.elements[hole / 2]) < 0) {
/* 385 */       int next = hole / 2;
/* 386 */       this.elements[hole] = this.elements[next];
/* 387 */       hole = next;
/*     */     } 
/* 389 */     this.elements[hole] = element;
/*     */   }
/*     */   
/*     */   protected void percolateUpMinHeap(Object element) {
/* 400 */     this.elements[++this.size] = element;
/* 401 */     percolateUpMinHeap(this.size);
/*     */   }
/*     */   
/*     */   protected void percolateUpMaxHeap(int index) {
/* 412 */     int hole = index;
/* 413 */     Object element = this.elements[hole];
/* 415 */     while (hole > 1 && compare(element, this.elements[hole / 2]) > 0) {
/* 418 */       int next = hole / 2;
/* 419 */       this.elements[hole] = this.elements[next];
/* 420 */       hole = next;
/*     */     } 
/* 423 */     this.elements[hole] = element;
/*     */   }
/*     */   
/*     */   protected void percolateUpMaxHeap(Object element) {
/* 434 */     this.elements[++this.size] = element;
/* 435 */     percolateUpMaxHeap(this.size);
/*     */   }
/*     */   
/*     */   protected int compare(Object a, Object b) {
/* 447 */     if (this.comparator != null)
/* 448 */       return this.comparator.compare(a, b); 
/* 450 */     return ((Comparable)a).compareTo(b);
/*     */   }
/*     */   
/*     */   protected void grow() {
/* 458 */     Object[] array = new Object[this.elements.length * 2];
/* 459 */     System.arraycopy(this.elements, 0, array, 0, this.elements.length);
/* 460 */     this.elements = array;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 470 */     return new Iterator(this) {
/*     */         private int index;
/*     */         
/*     */         private int lastReturnedIndex;
/*     */         
/*     */         private final PriorityBuffer this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 476 */           return (this.index <= this.this$0.size);
/*     */         }
/*     */         
/*     */         public Object next() {
/* 480 */           if (!hasNext())
/* 481 */             throw new NoSuchElementException(); 
/* 483 */           this.lastReturnedIndex = this.index;
/* 484 */           this.index++;
/* 485 */           return this.this$0.elements[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 489 */           if (this.lastReturnedIndex == -1)
/* 490 */             throw new IllegalStateException(); 
/* 492 */           this.this$0.elements[this.lastReturnedIndex] = this.this$0.elements[this.this$0.size];
/* 493 */           this.this$0.elements[this.this$0.size] = null;
/* 494 */           this.this$0.size--;
/* 495 */           if (this.this$0.size != 0 && this.lastReturnedIndex <= this.this$0.size) {
/* 496 */             int compareToParent = 0;
/* 497 */             if (this.lastReturnedIndex > 1)
/* 498 */               compareToParent = this.this$0.compare(this.this$0.elements[this.lastReturnedIndex], this.this$0.elements[this.lastReturnedIndex / 2]); 
/* 501 */             if (this.this$0.ascendingOrder) {
/* 502 */               if (this.lastReturnedIndex > 1 && compareToParent < 0) {
/* 503 */                 this.this$0.percolateUpMinHeap(this.lastReturnedIndex);
/*     */               } else {
/* 505 */                 this.this$0.percolateDownMinHeap(this.lastReturnedIndex);
/*     */               } 
/* 508 */             } else if (this.lastReturnedIndex > 1 && compareToParent > 0) {
/* 509 */               this.this$0.percolateUpMaxHeap(this.lastReturnedIndex);
/*     */             } else {
/* 511 */               this.this$0.percolateDownMaxHeap(this.lastReturnedIndex);
/*     */             } 
/*     */           } 
/* 515 */           this.index--;
/* 516 */           this.lastReturnedIndex = -1;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public String toString() {
/* 529 */     StringBuffer sb = new StringBuffer();
/* 531 */     sb.append("[ ");
/* 533 */     for (int i = 1; i < this.size + 1; i++) {
/* 534 */       if (i != 1)
/* 535 */         sb.append(", "); 
/* 537 */       sb.append(this.elements[i]);
/*     */     } 
/* 540 */     sb.append(" ]");
/* 542 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\buffer\PriorityBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */