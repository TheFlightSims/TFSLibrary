/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ 
/*    */ public final class OrPredicate implements Predicate, PredicateDecorator, Serializable {
/*    */   private static final long serialVersionUID = -8791518325735182855L;
/*    */   
/*    */   private final Predicate iPredicate1;
/*    */   
/*    */   private final Predicate iPredicate2;
/*    */   
/*    */   public static Predicate getInstance(Predicate predicate1, Predicate predicate2) {
/* 50 */     if (predicate1 == null || predicate2 == null)
/* 51 */       throw new IllegalArgumentException("Predicate must not be null"); 
/* 53 */     return new OrPredicate(predicate1, predicate2);
/*    */   }
/*    */   
/*    */   public OrPredicate(Predicate predicate1, Predicate predicate2) {
/* 65 */     this.iPredicate1 = predicate1;
/* 66 */     this.iPredicate2 = predicate2;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 76 */     return (this.iPredicate1.evaluate(object) || this.iPredicate2.evaluate(object));
/*    */   }
/*    */   
/*    */   public Predicate[] getPredicates() {
/* 86 */     return new Predicate[] { this.iPredicate1, this.iPredicate2 };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\OrPredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */