/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ 
/*    */ public final class NullIsTruePredicate implements Predicate, PredicateDecorator, Serializable {
/*    */   private static final long serialVersionUID = -7625133768987126273L;
/*    */   
/*    */   private final Predicate iPredicate;
/*    */   
/*    */   public static Predicate getInstance(Predicate predicate) {
/* 47 */     if (predicate == null)
/* 48 */       throw new IllegalArgumentException("Predicate must not be null"); 
/* 50 */     return new NullIsTruePredicate(predicate);
/*    */   }
/*    */   
/*    */   public NullIsTruePredicate(Predicate predicate) {
/* 61 */     this.iPredicate = predicate;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 72 */     if (object == null)
/* 73 */       return true; 
/* 75 */     return this.iPredicate.evaluate(object);
/*    */   }
/*    */   
/*    */   public Predicate[] getPredicates() {
/* 85 */     return new Predicate[] { this.iPredicate };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\NullIsTruePredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */