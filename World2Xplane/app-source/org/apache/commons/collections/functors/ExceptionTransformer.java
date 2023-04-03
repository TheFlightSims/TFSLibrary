/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.FunctorException;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public final class ExceptionTransformer implements Transformer, Serializable {
/*    */   private static final long serialVersionUID = 7179106032121985545L;
/*    */   
/* 39 */   public static final Transformer INSTANCE = new ExceptionTransformer();
/*    */   
/*    */   public static Transformer getInstance() {
/* 48 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public Object transform(Object input) {
/* 66 */     throw new FunctorException("ExceptionTransformer invoked");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\ExceptionTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */