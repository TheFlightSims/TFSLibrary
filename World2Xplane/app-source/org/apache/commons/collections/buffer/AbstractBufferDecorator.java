/*    */ package org.apache.commons.collections.buffer;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections.Buffer;
/*    */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*    */ 
/*    */ public abstract class AbstractBufferDecorator extends AbstractCollectionDecorator implements Buffer {
/*    */   protected AbstractBufferDecorator() {}
/*    */   
/*    */   protected AbstractBufferDecorator(Buffer buffer) {
/* 49 */     super((Collection)buffer);
/*    */   }
/*    */   
/*    */   protected Buffer getBuffer() {
/* 58 */     return (Buffer)getCollection();
/*    */   }
/*    */   
/*    */   public Object get() {
/* 63 */     return getBuffer().get();
/*    */   }
/*    */   
/*    */   public Object remove() {
/* 67 */     return getBuffer().remove();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\buffer\AbstractBufferDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */