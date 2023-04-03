/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.Closure;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ class FunctorUtils {
/*     */   static Predicate[] copy(Predicate[] predicates) {
/*  51 */     if (predicates == null)
/*  52 */       return null; 
/*  54 */     return (Predicate[])predicates.clone();
/*     */   }
/*     */   
/*     */   static void validate(Predicate[] predicates) {
/*  63 */     if (predicates == null)
/*  64 */       throw new IllegalArgumentException("The predicate array must not be null"); 
/*  66 */     for (int i = 0; i < predicates.length; i++) {
/*  67 */       if (predicates[i] == null)
/*  68 */         throw new IllegalArgumentException("The predicate array must not contain a null predicate, index " + i + " was null"); 
/*     */     } 
/*     */   }
/*     */   
/*     */   static Predicate[] validate(Collection predicates) {
/*  80 */     if (predicates == null)
/*  81 */       throw new IllegalArgumentException("The predicate collection must not be null"); 
/*  84 */     Predicate[] preds = new Predicate[predicates.size()];
/*  85 */     int i = 0;
/*  86 */     for (Iterator it = predicates.iterator(); it.hasNext(); ) {
/*  87 */       preds[i] = it.next();
/*  88 */       if (preds[i] == null)
/*  89 */         throw new IllegalArgumentException("The predicate collection must not contain a null predicate, index " + i + " was null"); 
/*  91 */       i++;
/*     */     } 
/*  93 */     return preds;
/*     */   }
/*     */   
/*     */   static Closure[] copy(Closure[] closures) {
/* 103 */     if (closures == null)
/* 104 */       return null; 
/* 106 */     return (Closure[])closures.clone();
/*     */   }
/*     */   
/*     */   static void validate(Closure[] closures) {
/* 115 */     if (closures == null)
/* 116 */       throw new IllegalArgumentException("The closure array must not be null"); 
/* 118 */     for (int i = 0; i < closures.length; i++) {
/* 119 */       if (closures[i] == null)
/* 120 */         throw new IllegalArgumentException("The closure array must not contain a null closure, index " + i + " was null"); 
/*     */     } 
/*     */   }
/*     */   
/*     */   static Transformer[] copy(Transformer[] transformers) {
/* 132 */     if (transformers == null)
/* 133 */       return null; 
/* 135 */     return (Transformer[])transformers.clone();
/*     */   }
/*     */   
/*     */   static void validate(Transformer[] transformers) {
/* 144 */     if (transformers == null)
/* 145 */       throw new IllegalArgumentException("The transformer array must not be null"); 
/* 147 */     for (int i = 0; i < transformers.length; i++) {
/* 148 */       if (transformers[i] == null)
/* 149 */         throw new IllegalArgumentException("The transformer array must not contain a null transformer, index " + i + " was null"); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\FunctorUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */