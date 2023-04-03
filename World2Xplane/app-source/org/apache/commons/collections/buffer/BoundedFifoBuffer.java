/*     */ package org.apache.commons.collections.buffer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.BoundedCollection;
/*     */ import org.apache.commons.collections.Buffer;
/*     */ import org.apache.commons.collections.BufferOverflowException;
/*     */ import org.apache.commons.collections.BufferUnderflowException;
/*     */ 
/*     */ public class BoundedFifoBuffer extends AbstractCollection implements Buffer, BoundedCollection, Serializable {
/*     */   private static final long serialVersionUID = 5603722811189451017L;
/*     */   
/*     */   private transient Object[] elements;
/*     */   
/*  75 */   private transient int start = 0;
/*     */   
/*  84 */   private transient int end = 0;
/*     */   
/*     */   private transient boolean full = false;
/*     */   
/*     */   private final int maxElements;
/*     */   
/*     */   public BoundedFifoBuffer() {
/*  97 */     this(32);
/*     */   }
/*     */   
/*     */   public BoundedFifoBuffer(int size) {
/* 108 */     if (size <= 0)
/* 109 */       throw new IllegalArgumentException("The size must be greater than 0"); 
/* 111 */     this.elements = new Object[size];
/* 112 */     this.maxElements = this.elements.length;
/*     */   }
/*     */   
/*     */   public BoundedFifoBuffer(Collection coll) {
/* 124 */     this(coll.size());
/* 125 */     addAll(coll);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 136 */     out.defaultWriteObject();
/* 137 */     out.writeInt(size());
/* 138 */     for (Iterator it = iterator(); it.hasNext();)
/* 139 */       out.writeObject(it.next()); 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 151 */     in.defaultReadObject();
/* 152 */     this.elements = new Object[this.maxElements];
/* 153 */     int size = in.readInt();
/* 154 */     for (int i = 0; i < size; i++)
/* 155 */       this.elements[i] = in.readObject(); 
/* 157 */     this.start = 0;
/* 158 */     this.full = (size == this.maxElements);
/* 159 */     if (this.full) {
/* 160 */       this.end = 0;
/*     */     } else {
/* 162 */       this.end = size;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 173 */     int size = 0;
/* 175 */     if (this.end < this.start) {
/* 176 */       size = this.maxElements - this.start + this.end;
/* 177 */     } else if (this.end == this.start) {
/* 178 */       size = this.full ? this.maxElements : 0;
/*     */     } else {
/* 180 */       size = this.end - this.start;
/*     */     } 
/* 183 */     return size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 192 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 201 */     return (size() == this.maxElements);
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 210 */     return this.maxElements;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 217 */     this.full = false;
/* 218 */     this.start = 0;
/* 219 */     this.end = 0;
/* 220 */     Arrays.fill(this.elements, (Object)null);
/*     */   }
/*     */   
/*     */   public boolean add(Object element) {
/* 232 */     if (null == element)
/* 233 */       throw new NullPointerException("Attempted to add null object to buffer"); 
/* 236 */     if (this.full)
/* 237 */       throw new BufferOverflowException("The buffer cannot hold more than " + this.maxElements + " objects."); 
/* 240 */     this.elements[this.end++] = element;
/* 242 */     if (this.end >= this.maxElements)
/* 243 */       this.end = 0; 
/* 246 */     if (this.end == this.start)
/* 247 */       this.full = true; 
/* 250 */     return true;
/*     */   }
/*     */   
/*     */   public Object get() {
/* 260 */     if (isEmpty())
/* 261 */       throw new BufferUnderflowException("The buffer is already empty"); 
/* 264 */     return this.elements[this.start];
/*     */   }
/*     */   
/*     */   public Object remove() {
/* 274 */     if (isEmpty())
/* 275 */       throw new BufferUnderflowException("The buffer is already empty"); 
/* 278 */     Object element = this.elements[this.start];
/* 280 */     if (null != element) {
/* 281 */       this.elements[this.start++] = null;
/* 283 */       if (this.start >= this.maxElements)
/* 284 */         this.start = 0; 
/* 287 */       this.full = false;
/*     */     } 
/* 290 */     return element;
/*     */   }
/*     */   
/*     */   private int increment(int index) {
/* 300 */     index++;
/* 301 */     if (index >= this.maxElements)
/* 302 */       index = 0; 
/* 304 */     return index;
/*     */   }
/*     */   
/*     */   private int decrement(int index) {
/* 314 */     index--;
/* 315 */     if (index < 0)
/* 316 */       index = this.maxElements - 1; 
/* 318 */     return index;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 327 */     return new Iterator(this) {
/*     */         private int index;
/*     */         
/*     */         private int lastReturnedIndex;
/*     */         
/*     */         private boolean isFirst;
/*     */         
/*     */         private final BoundedFifoBuffer this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 334 */           return (this.isFirst || this.index != this.this$0.end);
/*     */         }
/*     */         
/*     */         public Object next() {
/* 339 */           if (!hasNext())
/* 340 */             throw new NoSuchElementException(); 
/* 342 */           this.isFirst = false;
/* 343 */           this.lastReturnedIndex = this.index;
/* 344 */           this.index = this.this$0.increment(this.index);
/* 345 */           return this.this$0.elements[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 349 */           if (this.lastReturnedIndex == -1)
/* 350 */             throw new IllegalStateException(); 
/* 354 */           if (this.lastReturnedIndex == this.this$0.start) {
/* 355 */             this.this$0.remove();
/* 356 */             this.lastReturnedIndex = -1;
/*     */             return;
/*     */           } 
/* 360 */           int pos = this.lastReturnedIndex + 1;
/* 361 */           if (this.this$0.start < this.lastReturnedIndex && pos < this.this$0.end) {
/* 363 */             System.arraycopy(this.this$0.elements, pos, this.this$0.elements, this.lastReturnedIndex, this.this$0.end - pos);
/*     */           } else {
/* 367 */             while (pos != this.this$0.end) {
/* 368 */               if (pos >= this.this$0.maxElements) {
/* 369 */                 this.this$0.elements[pos - 1] = this.this$0.elements[0];
/* 370 */                 pos = 0;
/*     */                 continue;
/*     */               } 
/* 372 */               this.this$0.elements[this.this$0.decrement(pos)] = this.this$0.elements[pos];
/* 373 */               pos = this.this$0.increment(pos);
/*     */             } 
/*     */           } 
/* 378 */           this.lastReturnedIndex = -1;
/* 379 */           this.this$0.end = this.this$0.decrement(this.this$0.end);
/* 380 */           this.this$0.elements[this.this$0.end] = null;
/* 381 */           this.this$0.full = false;
/* 382 */           this.index = this.this$0.decrement(this.index);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\buffer\BoundedFifoBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */