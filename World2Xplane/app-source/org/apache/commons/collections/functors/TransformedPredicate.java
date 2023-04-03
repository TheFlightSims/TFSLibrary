/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public final class TransformedPredicate implements Predicate, PredicateDecorator, Serializable {
/*     */   private static final long serialVersionUID = -5596090919668315834L;
/*     */   
/*     */   private final Transformer iTransformer;
/*     */   
/*     */   private final Predicate iPredicate;
/*     */   
/*     */   public static Predicate getInstance(Transformer transformer, Predicate predicate) {
/*  52 */     if (transformer == null)
/*  53 */       throw new IllegalArgumentException("The transformer to call must not be null"); 
/*  55 */     if (predicate == null)
/*  56 */       throw new IllegalArgumentException("The predicate to call must not be null"); 
/*  58 */     return new TransformedPredicate(transformer, predicate);
/*     */   }
/*     */   
/*     */   public TransformedPredicate(Transformer transformer, Predicate predicate) {
/*  69 */     this.iTransformer = transformer;
/*  70 */     this.iPredicate = predicate;
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object object) {
/*  81 */     Object result = this.iTransformer.transform(object);
/*  82 */     return this.iPredicate.evaluate(result);
/*     */   }
/*     */   
/*     */   public Predicate[] getPredicates() {
/*  92 */     return new Predicate[] { this.iPredicate };
/*     */   }
/*     */   
/*     */   public Transformer getTransformer() {
/* 101 */     return this.iTransformer;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\TransformedPredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */