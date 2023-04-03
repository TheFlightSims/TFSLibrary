/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public final class AnyPredicate implements Predicate, PredicateDecorator, Serializable {
/*     */   private static final long serialVersionUID = 7429999530934647542L;
/*     */   
/*     */   private final Predicate[] iPredicates;
/*     */   
/*     */   public static Predicate getInstance(Predicate[] predicates) {
/*  58 */     FunctorUtils.validate(predicates);
/*  59 */     if (predicates.length == 0)
/*  60 */       return FalsePredicate.INSTANCE; 
/*  62 */     if (predicates.length == 1)
/*  63 */       return predicates[0]; 
/*  65 */     return new AnyPredicate(FunctorUtils.copy(predicates));
/*     */   }
/*     */   
/*     */   public static Predicate getInstance(Collection predicates) {
/*  80 */     Predicate[] preds = FunctorUtils.validate(predicates);
/*  81 */     if (preds.length == 0)
/*  82 */       return FalsePredicate.INSTANCE; 
/*  84 */     if (preds.length == 1)
/*  85 */       return preds[0]; 
/*  87 */     return new AnyPredicate(preds);
/*     */   }
/*     */   
/*     */   public AnyPredicate(Predicate[] predicates) {
/*  98 */     this.iPredicates = predicates;
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object object) {
/* 108 */     for (int i = 0; i < this.iPredicates.length; i++) {
/* 109 */       if (this.iPredicates[i].evaluate(object))
/* 110 */         return true; 
/*     */     } 
/* 113 */     return false;
/*     */   }
/*     */   
/*     */   public Predicate[] getPredicates() {
/* 123 */     return this.iPredicates;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\AnyPredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */