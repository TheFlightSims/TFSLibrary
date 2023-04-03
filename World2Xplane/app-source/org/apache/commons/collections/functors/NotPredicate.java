/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ 
/*    */ public final class NotPredicate implements Predicate, PredicateDecorator, Serializable {
/*    */   private static final long serialVersionUID = -2654603322338049674L;
/*    */   
/*    */   private final Predicate iPredicate;
/*    */   
/*    */   public static Predicate getInstance(Predicate predicate) {
/* 47 */     if (predicate == null)
/* 48 */       throw new IllegalArgumentException("Predicate must not be null"); 
/* 50 */     return new NotPredicate(predicate);
/*    */   }
/*    */   
/*    */   public NotPredicate(Predicate predicate) {
/* 61 */     this.iPredicate = predicate;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 71 */     return !this.iPredicate.evaluate(object);
/*    */   }
/*    */   
/*    */   public Predicate[] getPredicates() {
/* 81 */     return new Predicate[] { this.iPredicate };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\NotPredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */