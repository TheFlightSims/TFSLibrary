/*    */ package org.apache.commons.collections.buffer;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections.Buffer;
/*    */ import org.apache.commons.collections.collection.SynchronizedCollection;
/*    */ 
/*    */ public class SynchronizedBuffer extends SynchronizedCollection implements Buffer {
/*    */   private static final long serialVersionUID = -6859936183953626253L;
/*    */   
/*    */   public static Buffer decorate(Buffer buffer) {
/* 48 */     return new SynchronizedBuffer(buffer);
/*    */   }
/*    */   
/*    */   protected SynchronizedBuffer(Buffer buffer) {
/* 59 */     super((Collection)buffer);
/*    */   }
/*    */   
/*    */   protected SynchronizedBuffer(Buffer buffer, Object lock) {
/* 70 */     super((Collection)buffer, lock);
/*    */   }
/*    */   
/*    */   protected Buffer getBuffer() {
/* 79 */     return (Buffer)this.collection;
/*    */   }
/*    */   
/*    */   public Object get() {
/* 84 */     synchronized (this.lock) {
/* 85 */       return getBuffer().get();
/*    */     } 
/*    */   }
/*    */   
/*    */   public Object remove() {
/* 90 */     synchronized (this.lock) {
/* 91 */       return getBuffer().remove();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\buffer\SynchronizedBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */