/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.FunctorException;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ 
/*    */ public final class ExceptionPredicate implements Predicate, Serializable {
/*    */   private static final long serialVersionUID = 7179106032121985545L;
/*    */   
/* 38 */   public static final Predicate INSTANCE = new ExceptionPredicate();
/*    */   
/*    */   public static Predicate getInstance() {
/* 47 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 65 */     throw new FunctorException("ExceptionPredicate invoked");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\ExceptionPredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */