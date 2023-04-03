/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.functors.ChainedTransformer;
/*     */ import org.apache.commons.collections.functors.CloneTransformer;
/*     */ import org.apache.commons.collections.functors.ClosureTransformer;
/*     */ import org.apache.commons.collections.functors.ConstantTransformer;
/*     */ import org.apache.commons.collections.functors.EqualPredicate;
/*     */ import org.apache.commons.collections.functors.ExceptionTransformer;
/*     */ import org.apache.commons.collections.functors.FactoryTransformer;
/*     */ import org.apache.commons.collections.functors.InstantiateTransformer;
/*     */ import org.apache.commons.collections.functors.InvokerTransformer;
/*     */ import org.apache.commons.collections.functors.MapTransformer;
/*     */ import org.apache.commons.collections.functors.NOPTransformer;
/*     */ import org.apache.commons.collections.functors.PredicateTransformer;
/*     */ import org.apache.commons.collections.functors.StringValueTransformer;
/*     */ import org.apache.commons.collections.functors.SwitchTransformer;
/*     */ 
/*     */ public class TransformerUtils {
/*     */   public static Transformer exceptionTransformer() {
/*  84 */     return ExceptionTransformer.INSTANCE;
/*     */   }
/*     */   
/*     */   public static Transformer nullTransformer() {
/*  95 */     return ConstantTransformer.NULL_INSTANCE;
/*     */   }
/*     */   
/*     */   public static Transformer nopTransformer() {
/* 108 */     return NOPTransformer.INSTANCE;
/*     */   }
/*     */   
/*     */   public static Transformer cloneTransformer() {
/* 126 */     return CloneTransformer.INSTANCE;
/*     */   }
/*     */   
/*     */   public static Transformer constantTransformer(Object constantToReturn) {
/* 139 */     return ConstantTransformer.getInstance(constantToReturn);
/*     */   }
/*     */   
/*     */   public static Transformer asTransformer(Closure closure) {
/* 153 */     return ClosureTransformer.getInstance(closure);
/*     */   }
/*     */   
/*     */   public static Transformer asTransformer(Predicate predicate) {
/* 167 */     return PredicateTransformer.getInstance(predicate);
/*     */   }
/*     */   
/*     */   public static Transformer asTransformer(Factory factory) {
/* 181 */     return FactoryTransformer.getInstance(factory);
/*     */   }
/*     */   
/*     */   public static Transformer chainedTransformer(Transformer transformer1, Transformer transformer2) {
/* 196 */     return ChainedTransformer.getInstance(transformer1, transformer2);
/*     */   }
/*     */   
/*     */   public static Transformer chainedTransformer(Transformer[] transformers) {
/* 211 */     return ChainedTransformer.getInstance(transformers);
/*     */   }
/*     */   
/*     */   public static Transformer chainedTransformer(Collection transformers) {
/* 227 */     return ChainedTransformer.getInstance(transformers);
/*     */   }
/*     */   
/*     */   public static Transformer switchTransformer(Predicate predicate, Transformer trueTransformer, Transformer falseTransformer) {
/* 244 */     return SwitchTransformer.getInstance(new Predicate[] { predicate }, new Transformer[] { trueTransformer }, falseTransformer);
/*     */   }
/*     */   
/*     */   public static Transformer switchTransformer(Predicate[] predicates, Transformer[] transformers) {
/* 264 */     return SwitchTransformer.getInstance(predicates, transformers, null);
/*     */   }
/*     */   
/*     */   public static Transformer switchTransformer(Predicate[] predicates, Transformer[] transformers, Transformer defaultTransformer) {
/* 286 */     return SwitchTransformer.getInstance(predicates, transformers, defaultTransformer);
/*     */   }
/*     */   
/*     */   public static Transformer switchTransformer(Map predicatesAndTransformers) {
/* 311 */     return SwitchTransformer.getInstance(predicatesAndTransformers);
/*     */   }
/*     */   
/*     */   public static Transformer switchMapTransformer(Map objectsAndTransformers) {
/* 332 */     Transformer[] trs = null;
/* 333 */     Predicate[] preds = null;
/* 334 */     if (objectsAndTransformers == null)
/* 335 */       throw new IllegalArgumentException("The object and transformer map must not be null"); 
/* 337 */     Transformer def = (Transformer)objectsAndTransformers.remove(null);
/* 338 */     int size = objectsAndTransformers.size();
/* 339 */     trs = new Transformer[size];
/* 340 */     preds = new Predicate[size];
/* 341 */     int i = 0;
/* 342 */     for (Iterator it = objectsAndTransformers.entrySet().iterator(); it.hasNext(); ) {
/* 343 */       Map.Entry entry = it.next();
/* 344 */       preds[i] = EqualPredicate.getInstance(entry.getKey());
/* 345 */       trs[i] = (Transformer)entry.getValue();
/* 346 */       i++;
/*     */     } 
/* 348 */     return switchTransformer(preds, trs, def);
/*     */   }
/*     */   
/*     */   public static Transformer instantiateTransformer() {
/* 359 */     return InstantiateTransformer.NO_ARG_INSTANCE;
/*     */   }
/*     */   
/*     */   public static Transformer instantiateTransformer(Class[] paramTypes, Object[] args) {
/* 375 */     return InstantiateTransformer.getInstance(paramTypes, args);
/*     */   }
/*     */   
/*     */   public static Transformer mapTransformer(Map map) {
/* 389 */     return MapTransformer.getInstance(map);
/*     */   }
/*     */   
/*     */   public static Transformer invokerTransformer(String methodName) {
/* 408 */     return InvokerTransformer.getInstance(methodName, null, null);
/*     */   }
/*     */   
/*     */   public static Transformer invokerTransformer(String methodName, Class[] paramTypes, Object[] args) {
/* 426 */     return InvokerTransformer.getInstance(methodName, paramTypes, args);
/*     */   }
/*     */   
/*     */   public static Transformer stringValueTransformer() {
/* 439 */     return StringValueTransformer.INSTANCE;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\TransformerUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */