/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public class ConstantTransformer implements Transformer, Serializable {
/*    */   private static final long serialVersionUID = 6374440726369055124L;
/*    */   
/* 41 */   public static final Transformer NULL_INSTANCE = new ConstantTransformer(null);
/*    */   
/*    */   private final Object iConstant;
/*    */   
/*    */   public static Transformer getInstance(Object constantToReturn) {
/* 53 */     if (constantToReturn == null)
/* 54 */       return NULL_INSTANCE; 
/* 56 */     return new ConstantTransformer(constantToReturn);
/*    */   }
/*    */   
/*    */   public ConstantTransformer(Object constantToReturn) {
/* 67 */     this.iConstant = constantToReturn;
/*    */   }
/*    */   
/*    */   public Object transform(Object input) {
/* 77 */     return this.iConstant;
/*    */   }
/*    */   
/*    */   public Object getConstant() {
/* 87 */     return this.iConstant;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\ConstantTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */