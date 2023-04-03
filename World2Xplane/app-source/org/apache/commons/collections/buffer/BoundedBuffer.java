/*     */ package org.apache.commons.collections.buffer;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.BoundedCollection;
/*     */ import org.apache.commons.collections.Buffer;
/*     */ import org.apache.commons.collections.BufferOverflowException;
/*     */ import org.apache.commons.collections.BufferUnderflowException;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ 
/*     */ public class BoundedBuffer extends SynchronizedBuffer implements BoundedCollection {
/*     */   private static final long serialVersionUID = 1536432911093974264L;
/*     */   
/*     */   private final int maximumSize;
/*     */   
/*     */   private final long timeout;
/*     */   
/*     */   public static BoundedBuffer decorate(Buffer buffer, int maximumSize) {
/*  71 */     return new BoundedBuffer(buffer, maximumSize, 0L);
/*     */   }
/*     */   
/*     */   public static BoundedBuffer decorate(Buffer buffer, int maximumSize, long timeout) {
/*  86 */     return new BoundedBuffer(buffer, maximumSize, timeout);
/*     */   }
/*     */   
/*     */   protected BoundedBuffer(Buffer buffer, int maximumSize, long timeout) {
/* 101 */     super(buffer);
/* 102 */     if (maximumSize < 1)
/* 103 */       throw new IllegalArgumentException(); 
/* 105 */     this.maximumSize = maximumSize;
/* 106 */     this.timeout = timeout;
/*     */   }
/*     */   
/*     */   public Object remove() {
/* 111 */     synchronized (this.lock) {
/* 112 */       Object returnValue = getBuffer().remove();
/* 113 */       this.lock.notifyAll();
/* 114 */       return returnValue;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean add(Object o) {
/* 119 */     synchronized (this.lock) {
/* 120 */       timeoutWait(1);
/* 121 */       return getBuffer().add(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/* 126 */     synchronized (this.lock) {
/* 127 */       timeoutWait(c.size());
/* 128 */       return getBuffer().addAll(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 133 */     return (Iterator)new NotifyingIterator(this, this.collection.iterator());
/*     */   }
/*     */   
/*     */   private void timeoutWait(int nAdditions) {
/* 138 */     if (nAdditions > this.maximumSize)
/* 139 */       throw new BufferOverflowException("Buffer size cannot exceed " + this.maximumSize); 
/* 142 */     if (this.timeout <= 0L) {
/* 144 */       if (getBuffer().size() + nAdditions > this.maximumSize)
/* 145 */         throw new BufferOverflowException("Buffer size cannot exceed " + this.maximumSize); 
/*     */       return;
/*     */     } 
/* 150 */     long expiration = System.currentTimeMillis() + this.timeout;
/* 151 */     long timeLeft = expiration - System.currentTimeMillis();
/* 152 */     while (timeLeft > 0L && getBuffer().size() + nAdditions > this.maximumSize) {
/*     */       try {
/* 154 */         this.lock.wait(timeLeft);
/* 155 */         timeLeft = expiration - System.currentTimeMillis();
/* 156 */       } catch (InterruptedException ex) {
/* 157 */         PrintWriter out = new PrintWriter(new StringWriter());
/* 158 */         ex.printStackTrace(out);
/* 159 */         throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
/*     */       } 
/*     */     } 
/* 163 */     if (getBuffer().size() + nAdditions > this.maximumSize)
/* 164 */       throw new BufferOverflowException("Timeout expired"); 
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 170 */     return (size() == maxSize());
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 174 */     return this.maximumSize;
/*     */   }
/*     */   
/*     */   private class NotifyingIterator extends AbstractIteratorDecorator {
/*     */     private final BoundedBuffer this$0;
/*     */     
/*     */     public NotifyingIterator(BoundedBuffer this$0, Iterator it) {
/* 184 */       super(it);
/*     */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 188 */       synchronized (this.this$0.lock) {
/* 189 */         this.iterator.remove();
/* 190 */         this.this$0.lock.notifyAll();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\buffer\BoundedBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */