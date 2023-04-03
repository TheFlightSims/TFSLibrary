/*     */ package org.apache.commons.collections.buffer;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.Buffer;
/*     */ import org.apache.commons.collections.BufferUnderflowException;
/*     */ 
/*     */ public class BlockingBuffer extends SynchronizedBuffer {
/*     */   private static final long serialVersionUID = 1719328905017860541L;
/*     */   
/*     */   private final long timeout;
/*     */   
/*     */   public static Buffer decorate(Buffer buffer) {
/*  66 */     return new BlockingBuffer(buffer);
/*     */   }
/*     */   
/*     */   public static Buffer decorate(Buffer buffer, long timeoutMillis) {
/*  79 */     return new BlockingBuffer(buffer, timeoutMillis);
/*     */   }
/*     */   
/*     */   protected BlockingBuffer(Buffer buffer) {
/*  90 */     super(buffer);
/*  91 */     this.timeout = 0L;
/*     */   }
/*     */   
/*     */   protected BlockingBuffer(Buffer buffer, long timeoutMillis) {
/* 103 */     super(buffer);
/* 104 */     this.timeout = (timeoutMillis < 0L) ? 0L : timeoutMillis;
/*     */   }
/*     */   
/*     */   public boolean add(Object o) {
/* 109 */     synchronized (this.lock) {
/* 110 */       boolean result = this.collection.add(o);
/* 111 */       this.lock.notifyAll();
/* 112 */       return result;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/* 117 */     synchronized (this.lock) {
/* 118 */       boolean result = this.collection.addAll(c);
/* 119 */       this.lock.notifyAll();
/* 120 */       return result;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get() {
/* 132 */     synchronized (this.lock) {
/* 133 */       while (this.collection.isEmpty()) {
/*     */         try {
/* 135 */           if (this.timeout <= 0L) {
/* 136 */             this.lock.wait();
/*     */             continue;
/*     */           } 
/* 138 */           return get(this.timeout);
/* 140 */         } catch (InterruptedException e) {
/* 141 */           PrintWriter out = new PrintWriter(new StringWriter());
/* 142 */           e.printStackTrace(out);
/* 143 */           throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
/*     */         } 
/*     */       } 
/* 146 */       return getBuffer().get();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get(long timeout) {
/* 160 */     synchronized (this.lock) {
/* 161 */       long expiration = System.currentTimeMillis() + timeout;
/* 162 */       long timeLeft = expiration - System.currentTimeMillis();
/* 163 */       while (timeLeft > 0L && this.collection.isEmpty()) {
/*     */         try {
/* 165 */           this.lock.wait(timeLeft);
/* 166 */           timeLeft = expiration - System.currentTimeMillis();
/* 167 */         } catch (InterruptedException e) {
/* 168 */           PrintWriter out = new PrintWriter(new StringWriter());
/* 169 */           e.printStackTrace(out);
/* 170 */           throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
/*     */         } 
/*     */       } 
/* 173 */       if (this.collection.isEmpty())
/* 174 */         throw new BufferUnderflowException("Timeout expired"); 
/* 176 */       return getBuffer().get();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove() {
/* 188 */     synchronized (this.lock) {
/* 189 */       while (this.collection.isEmpty()) {
/*     */         try {
/* 191 */           if (this.timeout <= 0L) {
/* 192 */             this.lock.wait();
/*     */             continue;
/*     */           } 
/* 194 */           return remove(this.timeout);
/* 196 */         } catch (InterruptedException e) {
/* 197 */           PrintWriter out = new PrintWriter(new StringWriter());
/* 198 */           e.printStackTrace(out);
/* 199 */           throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
/*     */         } 
/*     */       } 
/* 202 */       return getBuffer().remove();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove(long timeout) {
/* 216 */     synchronized (this.lock) {
/* 217 */       long expiration = System.currentTimeMillis() + timeout;
/* 218 */       long timeLeft = expiration - System.currentTimeMillis();
/* 219 */       while (timeLeft > 0L && this.collection.isEmpty()) {
/*     */         try {
/* 221 */           this.lock.wait(timeLeft);
/* 222 */           timeLeft = expiration - System.currentTimeMillis();
/* 223 */         } catch (InterruptedException e) {
/* 224 */           PrintWriter out = new PrintWriter(new StringWriter());
/* 225 */           e.printStackTrace(out);
/* 226 */           throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
/*     */         } 
/*     */       } 
/* 229 */       if (this.collection.isEmpty())
/* 230 */         throw new BufferUnderflowException("Timeout expired"); 
/* 232 */       return getBuffer().remove();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\buffer\BlockingBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */