/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public final class AllPredicate implements Predicate, PredicateDecorator, Serializable {
/*     */   private static final long serialVersionUID = -3094696765038308799L;
/*     */   
/*     */   private final Predicate[] iPredicates;
/*     */   
/*     */   public static Predicate getInstance(Predicate[] predicates) {
/*  58 */     FunctorUtils.validate(predicates);
/*  59 */     if (predicates.length == 0)
/*  60 */       return TruePredicate.INSTANCE; 
/*  62 */     if (predicates.length == 1)
/*  63 */       return predicates[0]; 
/*  65 */     predicates = FunctorUtils.copy(predicates);
/*  66 */     return new AllPredicate(predicates);
/*     */   }
/*     */   
/*     */   public static Predicate getInstance(Collection predicates) {
/*  81 */     Predicate[] preds = FunctorUtils.validate(predicates);
/*  82 */     if (preds.length == 0)
/*  83 */       return TruePredicate.INSTANCE; 
/*  85 */     if (preds.length == 1)
/*  86 */       return preds[0]; 
/*  88 */     return new AllPredicate(preds);
/*     */   }
/*     */   
/*     */   public AllPredicate(Predicate[] predicates) {
/*  99 */     this.iPredicates = predicates;
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object object) {
/* 109 */     for (int i = 0; i < this.iPredicates.length; i++) {
/* 110 */       if (!this.iPredicates[i].evaluate(object))
/* 111 */         return false; 
/*     */     } 
/* 114 */     return true;
/*     */   }
/*     */   
/*     */   public Predicate[] getPredicates() {
/* 124 */     return this.iPredicates;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\AllPredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */