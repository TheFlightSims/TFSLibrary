/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public final class NonePredicate implements Predicate, PredicateDecorator, Serializable {
/*     */   private static final long serialVersionUID = 2007613066565892961L;
/*     */   
/*     */   private final Predicate[] iPredicates;
/*     */   
/*     */   public static Predicate getInstance(Predicate[] predicates) {
/*  57 */     FunctorUtils.validate(predicates);
/*  58 */     if (predicates.length == 0)
/*  59 */       return TruePredicate.INSTANCE; 
/*  61 */     predicates = FunctorUtils.copy(predicates);
/*  62 */     return new NonePredicate(predicates);
/*     */   }
/*     */   
/*     */   public static Predicate getInstance(Collection predicates) {
/*  76 */     Predicate[] preds = FunctorUtils.validate(predicates);
/*  77 */     if (preds.length == 0)
/*  78 */       return TruePredicate.INSTANCE; 
/*  80 */     return new NonePredicate(preds);
/*     */   }
/*     */   
/*     */   public NonePredicate(Predicate[] predicates) {
/*  91 */     this.iPredicates = predicates;
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object object) {
/* 101 */     for (int i = 0; i < this.iPredicates.length; i++) {
/* 102 */       if (this.iPredicates[i].evaluate(object))
/* 103 */         return false; 
/*     */     } 
/* 106 */     return true;
/*     */   }
/*     */   
/*     */   public Predicate[] getPredicates() {
/* 116 */     return this.iPredicates;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\NonePredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */