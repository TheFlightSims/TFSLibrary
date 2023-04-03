/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public final class OnePredicate implements Predicate, PredicateDecorator, Serializable {
/*     */   private static final long serialVersionUID = -8125389089924745785L;
/*     */   
/*     */   private final Predicate[] iPredicates;
/*     */   
/*     */   public static Predicate getInstance(Predicate[] predicates) {
/*  58 */     FunctorUtils.validate(predicates);
/*  59 */     if (predicates.length == 0)
/*  60 */       return FalsePredicate.INSTANCE; 
/*  62 */     if (predicates.length == 1)
/*  63 */       return predicates[0]; 
/*  65 */     predicates = FunctorUtils.copy(predicates);
/*  66 */     return new OnePredicate(predicates);
/*     */   }
/*     */   
/*     */   public static Predicate getInstance(Collection predicates) {
/*  78 */     Predicate[] preds = FunctorUtils.validate(predicates);
/*  79 */     return new OnePredicate(preds);
/*     */   }
/*     */   
/*     */   public OnePredicate(Predicate[] predicates) {
/*  90 */     this.iPredicates = predicates;
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object object) {
/* 101 */     boolean match = false;
/* 102 */     for (int i = 0; i < this.iPredicates.length; i++) {
/* 103 */       if (this.iPredicates[i].evaluate(object)) {
/* 104 */         if (match)
/* 105 */           return false; 
/* 107 */         match = true;
/*     */       } 
/*     */     } 
/* 110 */     return match;
/*     */   }
/*     */   
/*     */   public Predicate[] getPredicates() {
/* 120 */     return this.iPredicates;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\OnePredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */