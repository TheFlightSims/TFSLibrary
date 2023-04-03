/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Closure;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public class TransformerClosure implements Closure, Serializable {
/*    */   private static final long serialVersionUID = -5194992589193388969L;
/*    */   
/*    */   private final Transformer iTransformer;
/*    */   
/*    */   public static Closure getInstance(Transformer transformer) {
/* 50 */     if (transformer == null)
/* 51 */       return NOPClosure.INSTANCE; 
/* 53 */     return new TransformerClosure(transformer);
/*    */   }
/*    */   
/*    */   public TransformerClosure(Transformer transformer) {
/* 64 */     this.iTransformer = transformer;
/*    */   }
/*    */   
/*    */   public void execute(Object input) {
/* 73 */     this.iTransformer.transform(input);
/*    */   }
/*    */   
/*    */   public Transformer getTransformer() {
/* 83 */     return this.iTransformer;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\TransformerClosure.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */