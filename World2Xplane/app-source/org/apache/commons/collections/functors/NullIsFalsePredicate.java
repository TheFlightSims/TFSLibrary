/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ 
/*    */ public final class NullIsFalsePredicate implements Predicate, PredicateDecorator, Serializable {
/*    */   private static final long serialVersionUID = -2997501534564735525L;
/*    */   
/*    */   private final Predicate iPredicate;
/*    */   
/*    */   public static Predicate getInstance(Predicate predicate) {
/* 47 */     if (predicate == null)
/* 48 */       throw new IllegalArgumentException("Predicate must not be null"); 
/* 50 */     return new NullIsFalsePredicate(predicate);
/*    */   }
/*    */   
/*    */   public NullIsFalsePredicate(Predicate predicate) {
/* 61 */     this.iPredicate = predicate;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 72 */     if (object == null)
/* 73 */       return false; 
/* 75 */     return this.iPredicate.evaluate(object);
/*    */   }
/*    */   
/*    */   public Predicate[] getPredicates() {
/* 85 */     return new Predicate[] { this.iPredicate };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\NullIsFalsePredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */