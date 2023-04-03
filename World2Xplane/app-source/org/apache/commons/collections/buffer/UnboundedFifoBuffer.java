/*     */ package org.apache.commons.collections.buffer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.Buffer;
/*     */ import org.apache.commons.collections.BufferUnderflowException;
/*     */ 
/*     */ public class UnboundedFifoBuffer extends AbstractCollection implements Buffer, Serializable {
/*     */   private static final long serialVersionUID = -3482960336579541419L;
/*     */   
/*     */   protected transient Object[] buffer;
/*     */   
/*     */   protected transient int head;
/*     */   
/*     */   protected transient int tail;
/*     */   
/*     */   public UnboundedFifoBuffer() {
/*  89 */     this(32);
/*     */   }
/*     */   
/*     */   public UnboundedFifoBuffer(int initialSize) {
/* 100 */     if (initialSize <= 0)
/* 101 */       throw new IllegalArgumentException("The size must be greater than 0"); 
/* 103 */     this.buffer = new Object[initialSize + 1];
/* 104 */     this.head = 0;
/* 105 */     this.tail = 0;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 116 */     out.defaultWriteObject();
/* 117 */     out.writeInt(size());
/* 118 */     for (Iterator it = iterator(); it.hasNext();)
/* 119 */       out.writeObject(it.next()); 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 131 */     in.defaultReadObject();
/* 132 */     int size = in.readInt();
/* 133 */     this.buffer = new Object[size + 1];
/* 134 */     for (int i = 0; i < size; i++)
/* 135 */       this.buffer[i] = in.readObject(); 
/* 137 */     this.head = 0;
/* 138 */     this.tail = size;
/*     */   }
/*     */   
/*     */   public int size() {
/* 148 */     int size = 0;
/* 150 */     if (this.tail < this.head) {
/* 151 */       size = this.buffer.length - this.head + this.tail;
/*     */     } else {
/* 153 */       size = this.tail - this.head;
/*     */     } 
/* 156 */     return size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 165 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public boolean add(Object obj) {
/* 176 */     if (obj == null)
/* 177 */       throw new NullPointerException("Attempted to add null object to buffer"); 
/* 180 */     if (size() + 1 >= this.buffer.length) {
/* 182 */       Object[] tmp = new Object[(this.buffer.length - 1) * 2 + 1];
/* 183 */       int j = 0;
/*     */       int i;
/* 185 */       for (i = this.head; i != this.tail; ) {
/* 186 */         tmp[j] = this.buffer[i];
/* 187 */         this.buffer[i] = null;
/* 189 */         j++;
/* 190 */         i = increment(i);
/*     */       } 
/* 192 */       this.buffer = tmp;
/* 193 */       this.head = 0;
/* 194 */       this.tail = j;
/*     */     } 
/* 197 */     this.buffer[this.tail] = obj;
/* 198 */     this.tail = increment(this.tail);
/* 199 */     return true;
/*     */   }
/*     */   
/*     */   public Object get() {
/* 209 */     if (isEmpty())
/* 210 */       throw new BufferUnderflowException("The buffer is already empty"); 
/* 213 */     return this.buffer[this.head];
/*     */   }
/*     */   
/*     */   public Object remove() {
/* 223 */     if (isEmpty())
/* 224 */       throw new BufferUnderflowException("The buffer is already empty"); 
/* 227 */     Object element = this.buffer[this.head];
/* 228 */     if (element != null) {
/* 229 */       this.buffer[this.head] = null;
/* 230 */       this.head = increment(this.head);
/*     */     } 
/* 232 */     return element;
/*     */   }
/*     */   
/*     */   private int increment(int index) {
/* 242 */     index++;
/* 243 */     if (index >= this.buffer.length)
/* 244 */       index = 0; 
/* 246 */     return index;
/*     */   }
/*     */   
/*     */   private int decrement(int index) {
/* 256 */     index--;
/* 257 */     if (index < 0)
/* 258 */       index = this.buffer.length - 1; 
/* 260 */     return index;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 269 */     return new Iterator(this) {
/*     */         private int index;
/*     */         
/*     */         private int lastReturnedIndex;
/*     */         
/*     */         private final UnboundedFifoBuffer this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 275 */           return (this.index != this.this$0.tail);
/*     */         }
/*     */         
/*     */         public Object next() {
/* 280 */           if (!hasNext())
/* 281 */             throw new NoSuchElementException(); 
/* 283 */           this.lastReturnedIndex = this.index;
/* 284 */           this.index = this.this$0.increment(this.index);
/* 285 */           return this.this$0.buffer[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 289 */           if (this.lastReturnedIndex == -1)
/* 290 */             throw new IllegalStateException(); 
/* 294 */           if (this.lastReturnedIndex == this.this$0.head) {
/* 295 */             this.this$0.remove();
/* 296 */             this.lastReturnedIndex = -1;
/*     */             return;
/*     */           } 
/* 301 */           int i = this.this$0.increment(this.lastReturnedIndex);
/* 302 */           while (i != this.this$0.tail) {
/* 303 */             this.this$0.buffer[this.this$0.decrement(i)] = this.this$0.buffer[i];
/* 304 */             i = this.this$0.increment(i);
/*     */           } 
/* 307 */           this.lastReturnedIndex = -1;
/* 308 */           this.this$0.tail = this.this$0.decrement(this.this$0.tail);
/* 309 */           this.this$0.buffer[this.this$0.tail] = null;
/* 310 */           this.index = this.this$0.decrement(this.index);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\buffer\UnboundedFifoBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */