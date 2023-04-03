/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.functors.ChainedClosure;
/*     */ import org.apache.commons.collections.functors.EqualPredicate;
/*     */ import org.apache.commons.collections.functors.ExceptionClosure;
/*     */ import org.apache.commons.collections.functors.ForClosure;
/*     */ import org.apache.commons.collections.functors.IfClosure;
/*     */ import org.apache.commons.collections.functors.InvokerTransformer;
/*     */ import org.apache.commons.collections.functors.NOPClosure;
/*     */ import org.apache.commons.collections.functors.SwitchClosure;
/*     */ import org.apache.commons.collections.functors.TransformerClosure;
/*     */ import org.apache.commons.collections.functors.WhileClosure;
/*     */ 
/*     */ public class ClosureUtils {
/*     */   public static Closure exceptionClosure() {
/*  75 */     return ExceptionClosure.INSTANCE;
/*     */   }
/*     */   
/*     */   public static Closure nopClosure() {
/*  87 */     return NOPClosure.INSTANCE;
/*     */   }
/*     */   
/*     */   public static Closure asClosure(Transformer transformer) {
/* 101 */     return TransformerClosure.getInstance(transformer);
/*     */   }
/*     */   
/*     */   public static Closure forClosure(int count, Closure closure) {
/* 116 */     return ForClosure.getInstance(count, closure);
/*     */   }
/*     */   
/*     */   public static Closure whileClosure(Predicate predicate, Closure closure) {
/* 131 */     return WhileClosure.getInstance(predicate, closure, false);
/*     */   }
/*     */   
/*     */   public static Closure doWhileClosure(Closure closure, Predicate predicate) {
/* 146 */     return WhileClosure.getInstance(predicate, closure, true);
/*     */   }
/*     */   
/*     */   public static Closure invokerClosure(String methodName) {
/* 162 */     return asClosure(InvokerTransformer.getInstance(methodName));
/*     */   }
/*     */   
/*     */   public static Closure invokerClosure(String methodName, Class[] paramTypes, Object[] args) {
/* 181 */     return asClosure(InvokerTransformer.getInstance(methodName, paramTypes, args));
/*     */   }
/*     */   
/*     */   public static Closure chainedClosure(Closure closure1, Closure closure2) {
/* 196 */     return ChainedClosure.getInstance(closure1, closure2);
/*     */   }
/*     */   
/*     */   public static Closure chainedClosure(Closure[] closures) {
/* 211 */     return ChainedClosure.getInstance(closures);
/*     */   }
/*     */   
/*     */   public static Closure chainedClosure(Collection closures) {
/* 228 */     return ChainedClosure.getInstance(closures);
/*     */   }
/*     */   
/*     */   public static Closure ifClosure(Predicate predicate, Closure trueClosure) {
/* 245 */     return IfClosure.getInstance(predicate, trueClosure);
/*     */   }
/*     */   
/*     */   public static Closure ifClosure(Predicate predicate, Closure trueClosure, Closure falseClosure) {
/* 262 */     return IfClosure.getInstance(predicate, trueClosure, falseClosure);
/*     */   }
/*     */   
/*     */   public static Closure switchClosure(Predicate[] predicates, Closure[] closures) {
/* 283 */     return SwitchClosure.getInstance(predicates, closures, null);
/*     */   }
/*     */   
/*     */   public static Closure switchClosure(Predicate[] predicates, Closure[] closures, Closure defaultClosure) {
/* 306 */     return SwitchClosure.getInstance(predicates, closures, defaultClosure);
/*     */   }
/*     */   
/*     */   public static Closure switchClosure(Map predicatesAndClosures) {
/* 330 */     return SwitchClosure.getInstance(predicatesAndClosures);
/*     */   }
/*     */   
/*     */   public static Closure switchMapClosure(Map objectsAndClosures) {
/* 351 */     Closure[] trs = null;
/* 352 */     Predicate[] preds = null;
/* 353 */     if (objectsAndClosures == null)
/* 354 */       throw new IllegalArgumentException("The object and closure map must not be null"); 
/* 356 */     Closure def = (Closure)objectsAndClosures.remove(null);
/* 357 */     int size = objectsAndClosures.size();
/* 358 */     trs = new Closure[size];
/* 359 */     preds = new Predicate[size];
/* 360 */     int i = 0;
/* 361 */     for (Iterator it = objectsAndClosures.entrySet().iterator(); it.hasNext(); ) {
/* 362 */       Map.Entry entry = it.next();
/* 363 */       preds[i] = EqualPredicate.getInstance(entry.getKey());
/* 364 */       trs[i] = (Closure)entry.getValue();
/* 365 */       i++;
/*     */     } 
/* 367 */     return switchClosure(preds, trs, def);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\ClosureUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */