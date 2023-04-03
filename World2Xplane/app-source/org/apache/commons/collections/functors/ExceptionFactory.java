/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Factory;
/*    */ import org.apache.commons.collections.FunctorException;
/*    */ 
/*    */ public final class ExceptionFactory implements Factory, Serializable {
/*    */   private static final long serialVersionUID = 7179106032121985545L;
/*    */   
/* 39 */   public static final Factory INSTANCE = new ExceptionFactory();
/*    */   
/*    */   public static Factory getInstance() {
/* 48 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public Object create() {
/* 65 */     throw new FunctorException("ExceptionFactory invoked");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\ExceptionFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */