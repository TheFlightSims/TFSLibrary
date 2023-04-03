/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public class SwitchTransformer implements Transformer, Serializable {
/*     */   private static final long serialVersionUID = -6404460890903469332L;
/*     */   
/*     */   private final Predicate[] iPredicates;
/*     */   
/*     */   private final Transformer[] iTransformers;
/*     */   
/*     */   private final Transformer iDefault;
/*     */   
/*     */   public static Transformer getInstance(Predicate[] predicates, Transformer[] transformers, Transformer defaultTransformer) {
/*  58 */     FunctorUtils.validate(predicates);
/*  59 */     FunctorUtils.validate(transformers);
/*  60 */     if (predicates.length != transformers.length)
/*  61 */       throw new IllegalArgumentException("The predicate and transformer arrays must be the same size"); 
/*  63 */     if (predicates.length == 0)
/*  64 */       return (defaultTransformer == null) ? ConstantTransformer.NULL_INSTANCE : defaultTransformer; 
/*  66 */     predicates = FunctorUtils.copy(predicates);
/*  67 */     transformers = FunctorUtils.copy(transformers);
/*  68 */     return new SwitchTransformer(predicates, transformers, defaultTransformer);
/*     */   }
/*     */   
/*     */   public static Transformer getInstance(Map predicatesAndTransformers) {
/*  89 */     Transformer[] transformers = null;
/*  90 */     Predicate[] preds = null;
/*  91 */     if (predicatesAndTransformers == null)
/*  92 */       throw new IllegalArgumentException("The predicate and transformer map must not be null"); 
/*  94 */     if (predicatesAndTransformers.size() == 0)
/*  95 */       return ConstantTransformer.NULL_INSTANCE; 
/*  98 */     Transformer defaultTransformer = (Transformer)predicatesAndTransformers.remove(null);
/*  99 */     int size = predicatesAndTransformers.size();
/* 100 */     if (size == 0)
/* 101 */       return (defaultTransformer == null) ? ConstantTransformer.NULL_INSTANCE : defaultTransformer; 
/* 103 */     transformers = new Transformer[size];
/* 104 */     preds = new Predicate[size];
/* 105 */     int i = 0;
/* 106 */     for (Iterator it = predicatesAndTransformers.entrySet().iterator(); it.hasNext(); ) {
/* 107 */       Map.Entry entry = it.next();
/* 108 */       preds[i] = (Predicate)entry.getKey();
/* 109 */       transformers[i] = (Transformer)entry.getValue();
/* 110 */       i++;
/*     */     } 
/* 112 */     return new SwitchTransformer(preds, transformers, defaultTransformer);
/*     */   }
/*     */   
/*     */   public SwitchTransformer(Predicate[] predicates, Transformer[] transformers, Transformer defaultTransformer) {
/* 125 */     this.iPredicates = predicates;
/* 126 */     this.iTransformers = transformers;
/* 127 */     this.iDefault = (defaultTransformer == null) ? ConstantTransformer.NULL_INSTANCE : defaultTransformer;
/*     */   }
/*     */   
/*     */   public Object transform(Object input) {
/* 138 */     for (int i = 0; i < this.iPredicates.length; i++) {
/* 139 */       if (this.iPredicates[i].evaluate(input) == true)
/* 140 */         return this.iTransformers[i].transform(input); 
/*     */     } 
/* 143 */     return this.iDefault.transform(input);
/*     */   }
/*     */   
/*     */   public Predicate[] getPredicates() {
/* 153 */     return this.iPredicates;
/*     */   }
/*     */   
/*     */   public Transformer[] getTransformers() {
/* 163 */     return this.iTransformers;
/*     */   }
/*     */   
/*     */   public Transformer getDefaultTransformer() {
/* 173 */     return this.iDefault;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\SwitchTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */