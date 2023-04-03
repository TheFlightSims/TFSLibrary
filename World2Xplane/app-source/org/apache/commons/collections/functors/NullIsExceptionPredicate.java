/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.FunctorException;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ 
/*    */ public final class NullIsExceptionPredicate implements Predicate, PredicateDecorator, Serializable {
/*    */   private static final long serialVersionUID = 3243449850504576071L;
/*    */   
/*    */   private final Predicate iPredicate;
/*    */   
/*    */   public static Predicate getInstance(Predicate predicate) {
/* 48 */     if (predicate == null)
/* 49 */       throw new IllegalArgumentException("Predicate must not be null"); 
/* 51 */     return new NullIsExceptionPredicate(predicate);
/*    */   }
/*    */   
/*    */   public NullIsExceptionPredicate(Predicate predicate) {
/* 62 */     this.iPredicate = predicate;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 74 */     if (object == null)
/* 75 */       throw new FunctorException("Input Object must not be null"); 
/* 77 */     return this.iPredicate.evaluate(object);
/*    */   }
/*    */   
/*    */   public Predicate[] getPredicates() {
/* 87 */     return new Predicate[] { this.iPredicate };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\NullIsExceptionPredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */