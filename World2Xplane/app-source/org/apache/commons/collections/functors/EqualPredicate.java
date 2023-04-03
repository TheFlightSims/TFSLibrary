/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ 
/*    */ public final class EqualPredicate implements Predicate, Serializable {
/*    */   private static final long serialVersionUID = 5633766978029907089L;
/*    */   
/*    */   private final Object iValue;
/*    */   
/*    */   public static Predicate getInstance(Object object) {
/* 48 */     if (object == null)
/* 49 */       return NullPredicate.INSTANCE; 
/* 51 */     return new EqualPredicate(object);
/*    */   }
/*    */   
/*    */   public EqualPredicate(Object object) {
/* 62 */     this.iValue = object;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 72 */     return this.iValue.equals(object);
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 82 */     return this.iValue;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\EqualPredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */