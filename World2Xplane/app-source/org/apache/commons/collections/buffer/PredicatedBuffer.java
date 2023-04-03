/*    */ package org.apache.commons.collections.buffer;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections.Buffer;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ import org.apache.commons.collections.collection.PredicatedCollection;
/*    */ 
/*    */ public class PredicatedBuffer extends PredicatedCollection implements Buffer {
/*    */   private static final long serialVersionUID = 2307609000539943581L;
/*    */   
/*    */   public static Buffer decorate(Buffer buffer, Predicate predicate) {
/* 60 */     return new PredicatedBuffer(buffer, predicate);
/*    */   }
/*    */   
/*    */   protected PredicatedBuffer(Buffer buffer, Predicate predicate) {
/* 76 */     super((Collection)buffer, predicate);
/*    */   }
/*    */   
/*    */   protected Buffer getBuffer() {
/* 85 */     return (Buffer)getCollection();
/*    */   }
/*    */   
/*    */   public Object get() {
/* 90 */     return getBuffer().get();
/*    */   }
/*    */   
/*    */   public Object remove() {
/* 94 */     return getBuffer().remove();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\buffer\PredicatedBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */