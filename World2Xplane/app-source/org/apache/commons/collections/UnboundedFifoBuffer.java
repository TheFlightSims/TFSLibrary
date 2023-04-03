/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class UnboundedFifoBuffer extends AbstractCollection implements Buffer {
/*     */   protected Object[] m_buffer;
/*     */   
/*     */   protected int m_head;
/*     */   
/*     */   protected int m_tail;
/*     */   
/*     */   public UnboundedFifoBuffer() {
/*  70 */     this(32);
/*     */   }
/*     */   
/*     */   public UnboundedFifoBuffer(int initialSize) {
/*  81 */     if (initialSize <= 0)
/*  82 */       throw new IllegalArgumentException("The size must be greater than 0"); 
/*  84 */     this.m_buffer = new Object[initialSize + 1];
/*  85 */     this.m_head = 0;
/*  86 */     this.m_tail = 0;
/*     */   }
/*     */   
/*     */   public int size() {
/*  95 */     int size = 0;
/*  97 */     if (this.m_tail < this.m_head) {
/*  98 */       size = this.m_buffer.length - this.m_head + this.m_tail;
/*     */     } else {
/* 100 */       size = this.m_tail - this.m_head;
/*     */     } 
/* 103 */     return size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 112 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public boolean add(Object obj) {
/* 124 */     if (obj == null)
/* 125 */       throw new NullPointerException("Attempted to add null object to buffer"); 
/* 128 */     if (size() + 1 >= this.m_buffer.length) {
/* 129 */       Object[] tmp = new Object[(this.m_buffer.length - 1) * 2 + 1];
/* 131 */       int j = 0;
/*     */       int i;
/* 132 */       for (i = this.m_head; i != this.m_tail; ) {
/* 133 */         tmp[j] = this.m_buffer[i];
/* 134 */         this.m_buffer[i] = null;
/* 136 */         j++;
/* 137 */         i++;
/* 138 */         if (i == this.m_buffer.length)
/* 139 */           i = 0; 
/*     */       } 
/* 143 */       this.m_buffer = tmp;
/* 144 */       this.m_head = 0;
/* 145 */       this.m_tail = j;
/*     */     } 
/* 148 */     this.m_buffer[this.m_tail] = obj;
/* 149 */     this.m_tail++;
/* 150 */     if (this.m_tail >= this.m_buffer.length)
/* 151 */       this.m_tail = 0; 
/* 153 */     return true;
/*     */   }
/*     */   
/*     */   public Object get() {
/* 163 */     if (isEmpty())
/* 164 */       throw new BufferUnderflowException("The buffer is already empty"); 
/* 167 */     return this.m_buffer[this.m_head];
/*     */   }
/*     */   
/*     */   public Object remove() {
/* 177 */     if (isEmpty())
/* 178 */       throw new BufferUnderflowException("The buffer is already empty"); 
/* 181 */     Object element = this.m_buffer[this.m_head];
/* 183 */     if (null != element) {
/* 184 */       this.m_buffer[this.m_head] = null;
/* 186 */       this.m_head++;
/* 187 */       if (this.m_head >= this.m_buffer.length)
/* 188 */         this.m_head = 0; 
/*     */     } 
/* 192 */     return element;
/*     */   }
/*     */   
/*     */   private int increment(int index) {
/* 202 */     index++;
/* 203 */     if (index >= this.m_buffer.length)
/* 204 */       index = 0; 
/* 206 */     return index;
/*     */   }
/*     */   
/*     */   private int decrement(int index) {
/* 216 */     index--;
/* 217 */     if (index < 0)
/* 218 */       index = this.m_buffer.length - 1; 
/* 220 */     return index;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 229 */     return new Iterator(this) {
/*     */         private int index;
/*     */         
/*     */         private int lastReturnedIndex;
/*     */         
/*     */         private final UnboundedFifoBuffer this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 235 */           return (this.index != this.this$0.m_tail);
/*     */         }
/*     */         
/*     */         public Object next() {
/* 240 */           if (!hasNext())
/* 241 */             throw new NoSuchElementException(); 
/* 242 */           this.lastReturnedIndex = this.index;
/* 243 */           this.index = this.this$0.increment(this.index);
/* 244 */           return this.this$0.m_buffer[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 248 */           if (this.lastReturnedIndex == -1)
/* 249 */             throw new IllegalStateException(); 
/* 252 */           if (this.lastReturnedIndex == this.this$0.m_head) {
/* 253 */             this.this$0.remove();
/* 254 */             this.lastReturnedIndex = -1;
/*     */             return;
/*     */           } 
/* 259 */           int i = this.this$0.increment(this.lastReturnedIndex);
/* 260 */           while (i != this.this$0.m_tail) {
/* 261 */             this.this$0.m_buffer[this.this$0.decrement(i)] = this.this$0.m_buffer[i];
/* 262 */             i = this.this$0.increment(i);
/*     */           } 
/* 265 */           this.lastReturnedIndex = -1;
/* 266 */           this.this$0.m_tail = this.this$0.decrement(this.this$0.m_tail);
/* 267 */           this.this$0.m_buffer[this.this$0.m_tail] = null;
/* 268 */           this.index = this.this$0.decrement(this.index);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\UnboundedFifoBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */