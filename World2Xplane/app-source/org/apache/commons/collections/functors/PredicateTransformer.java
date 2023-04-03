/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public class PredicateTransformer implements Transformer, Serializable {
/*    */   private static final long serialVersionUID = 5278818408044349346L;
/*    */   
/*    */   private final Predicate iPredicate;
/*    */   
/*    */   public static Transformer getInstance(Predicate predicate) {
/* 49 */     if (predicate == null)
/* 50 */       throw new IllegalArgumentException("Predicate must not be null"); 
/* 52 */     return new PredicateTransformer(predicate);
/*    */   }
/*    */   
/*    */   public PredicateTransformer(Predicate predicate) {
/* 63 */     this.iPredicate = predicate;
/*    */   }
/*    */   
/*    */   public Object transform(Object input) {
/* 73 */     return this.iPredicate.evaluate(input) ? Boolean.TRUE : Boolean.FALSE;
/*    */   }
/*    */   
/*    */   public Predicate getPredicate() {
/* 83 */     return this.iPredicate;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\PredicateTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */