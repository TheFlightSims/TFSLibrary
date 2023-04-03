/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.FunctorException;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public final class TransformerPredicate implements Predicate, Serializable {
/*    */   private static final long serialVersionUID = -2407966402920578741L;
/*    */   
/*    */   private final Transformer iTransformer;
/*    */   
/*    */   public static Predicate getInstance(Transformer transformer) {
/* 49 */     if (transformer == null)
/* 50 */       throw new IllegalArgumentException("The transformer to call must not be null"); 
/* 52 */     return new TransformerPredicate(transformer);
/*    */   }
/*    */   
/*    */   public TransformerPredicate(Transformer transformer) {
/* 63 */     this.iTransformer = transformer;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 74 */     Object result = this.iTransformer.transform(object);
/* 75 */     if (!(result instanceof Boolean))
/* 76 */       throw new FunctorException("Transformer must return an instanceof Boolean, it was a " + ((result == null) ? "null object" : result.getClass().getName())); 
/* 80 */     return ((Boolean)result).booleanValue();
/*    */   }
/*    */   
/*    */   public Transformer getTransformer() {
/* 90 */     return this.iTransformer;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\TransformerPredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */