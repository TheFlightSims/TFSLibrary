/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class BoundedFifoBuffer extends AbstractCollection implements Buffer, BoundedCollection {
/*     */   private final Object[] m_elements;
/*     */   
/*  59 */   private int m_start = 0;
/*     */   
/*  60 */   private int m_end = 0;
/*     */   
/*     */   private boolean m_full = false;
/*     */   
/*     */   private final int maxElements;
/*     */   
/*     */   public BoundedFifoBuffer() {
/*  69 */     this(32);
/*     */   }
/*     */   
/*     */   public BoundedFifoBuffer(int size) {
/*  80 */     if (size <= 0)
/*  81 */       throw new IllegalArgumentException("The size must be greater than 0"); 
/*  83 */     this.m_elements = new Object[size];
/*  84 */     this.maxElements = this.m_elements.length;
/*     */   }
/*     */   
/*     */   public BoundedFifoBuffer(Collection coll) {
/*  96 */     this(coll.size());
/*  97 */     addAll(coll);
/*     */   }
/*     */   
/*     */   public int size() {
/* 106 */     int size = 0;
/* 108 */     if (this.m_end < this.m_start) {
/* 109 */       size = this.maxElements - this.m_start + this.m_end;
/* 110 */     } else if (this.m_end == this.m_start) {
/* 111 */       size = this.m_full ? this.maxElements : 0;
/*     */     } else {
/* 113 */       size = this.m_end - this.m_start;
/*     */     } 
/* 116 */     return size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 125 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 134 */     return (size() == this.maxElements);
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 143 */     return this.maxElements;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 150 */     this.m_full = false;
/* 151 */     this.m_start = 0;
/* 152 */     this.m_end = 0;
/* 153 */     Arrays.fill(this.m_elements, (Object)null);
/*     */   }
/*     */   
/*     */   public boolean add(Object element) {
/* 165 */     if (null == element)
/* 166 */       throw new NullPointerException("Attempted to add null object to buffer"); 
/* 169 */     if (this.m_full)
/* 170 */       throw new BufferOverflowException("The buffer cannot hold more than " + this.maxElements + " objects."); 
/* 173 */     this.m_elements[this.m_end++] = element;
/* 175 */     if (this.m_end >= this.maxElements)
/* 176 */       this.m_end = 0; 
/* 179 */     if (this.m_end == this.m_start)
/* 180 */       this.m_full = true; 
/* 183 */     return true;
/*     */   }
/*     */   
/*     */   public Object get() {
/* 193 */     if (isEmpty())
/* 194 */       throw new BufferUnderflowException("The buffer is already empty"); 
/* 197 */     return this.m_elements[this.m_start];
/*     */   }
/*     */   
/*     */   public Object remove() {
/* 207 */     if (isEmpty())
/* 208 */       throw new BufferUnderflowException("The buffer is already empty"); 
/* 211 */     Object element = this.m_elements[this.m_start];
/* 213 */     if (null != element) {
/* 214 */       this.m_elements[this.m_start++] = null;
/* 216 */       if (this.m_start >= this.maxElements)
/* 217 */         this.m_start = 0; 
/* 220 */       this.m_full = false;
/*     */     } 
/* 223 */     return element;
/*     */   }
/*     */   
/*     */   private int increment(int index) {
/* 233 */     index++;
/* 234 */     if (index >= this.maxElements)
/* 235 */       index = 0; 
/* 237 */     return index;
/*     */   }
/*     */   
/*     */   private int decrement(int index) {
/* 247 */     index--;
/* 248 */     if (index < 0)
/* 249 */       index = this.maxElements - 1; 
/* 251 */     return index;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 260 */     return new Iterator(this) {
/*     */         private int index;
/*     */         
/*     */         private int lastReturnedIndex;
/*     */         
/*     */         private boolean isFirst;
/*     */         
/*     */         private final BoundedFifoBuffer this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 267 */           return (this.isFirst || this.index != this.this$0.m_end);
/*     */         }
/*     */         
/*     */         public Object next() {
/* 272 */           if (!hasNext())
/* 272 */             throw new NoSuchElementException(); 
/* 273 */           this.isFirst = false;
/* 274 */           this.lastReturnedIndex = this.index;
/* 275 */           this.index = this.this$0.increment(this.index);
/* 276 */           return this.this$0.m_elements[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 280 */           if (this.lastReturnedIndex == -1)
/* 280 */             throw new IllegalStateException(); 
/* 283 */           if (this.lastReturnedIndex == this.this$0.m_start) {
/* 284 */             this.this$0.remove();
/* 285 */             this.lastReturnedIndex = -1;
/*     */             return;
/*     */           } 
/* 290 */           int i = this.lastReturnedIndex + 1;
/* 291 */           while (i != this.this$0.m_end) {
/* 292 */             if (i >= this.this$0.maxElements) {
/* 293 */               this.this$0.m_elements[i - 1] = this.this$0.m_elements[0];
/* 294 */               i = 0;
/*     */               continue;
/*     */             } 
/* 296 */             this.this$0.m_elements[i - 1] = this.this$0.m_elements[i];
/* 297 */             i++;
/*     */           } 
/* 301 */           this.lastReturnedIndex = -1;
/* 302 */           this.this$0.m_end = this.this$0.decrement(this.this$0.m_end);
/* 303 */           this.this$0.m_elements[this.this$0.m_end] = null;
/* 304 */           this.this$0.m_full = false;
/* 305 */           this.index = this.this$0.decrement(this.index);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\BoundedFifoBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */