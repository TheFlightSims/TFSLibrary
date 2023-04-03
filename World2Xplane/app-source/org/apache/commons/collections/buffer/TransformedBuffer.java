/*    */ package org.apache.commons.collections.buffer;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections.Buffer;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ import org.apache.commons.collections.collection.TransformedCollection;
/*    */ 
/*    */ public class TransformedBuffer extends TransformedCollection implements Buffer {
/*    */   private static final long serialVersionUID = -7901091318986132033L;
/*    */   
/*    */   public static Buffer decorate(Buffer buffer, Transformer transformer) {
/* 55 */     return new TransformedBuffer(buffer, transformer);
/*    */   }
/*    */   
/*    */   protected TransformedBuffer(Buffer buffer, Transformer transformer) {
/* 70 */     super((Collection)buffer, transformer);
/*    */   }
/*    */   
/*    */   protected Buffer getBuffer() {
/* 79 */     return (Buffer)this.collection;
/*    */   }
/*    */   
/*    */   public Object get() {
/* 84 */     return getBuffer().get();
/*    */   }
/*    */   
/*    */   public Object remove() {
/* 88 */     return getBuffer().remove();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\buffer\TransformedBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */