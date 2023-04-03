/*    */ package org.apache.commons.collections.buffer;
/*    */ 
/*    */ import org.apache.commons.collections.Buffer;
/*    */ import org.apache.commons.collections.functors.InstanceofPredicate;
/*    */ 
/*    */ public class TypedBuffer {
/*    */   public static Buffer decorate(Buffer buffer, Class type) {
/* 51 */     return new PredicatedBuffer(buffer, InstanceofPredicate.getInstance(type));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\buffer\TypedBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */