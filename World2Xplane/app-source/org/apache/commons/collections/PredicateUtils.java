/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.functors.AllPredicate;
/*     */ import org.apache.commons.collections.functors.AndPredicate;
/*     */ import org.apache.commons.collections.functors.AnyPredicate;
/*     */ import org.apache.commons.collections.functors.EqualPredicate;
/*     */ import org.apache.commons.collections.functors.ExceptionPredicate;
/*     */ import org.apache.commons.collections.functors.FalsePredicate;
/*     */ import org.apache.commons.collections.functors.IdentityPredicate;
/*     */ import org.apache.commons.collections.functors.InstanceofPredicate;
/*     */ import org.apache.commons.collections.functors.InvokerTransformer;
/*     */ import org.apache.commons.collections.functors.NonePredicate;
/*     */ import org.apache.commons.collections.functors.NotNullPredicate;
/*     */ import org.apache.commons.collections.functors.NotPredicate;
/*     */ import org.apache.commons.collections.functors.NullIsExceptionPredicate;
/*     */ import org.apache.commons.collections.functors.NullIsFalsePredicate;
/*     */ import org.apache.commons.collections.functors.NullIsTruePredicate;
/*     */ import org.apache.commons.collections.functors.NullPredicate;
/*     */ import org.apache.commons.collections.functors.OnePredicate;
/*     */ import org.apache.commons.collections.functors.OrPredicate;
/*     */ import org.apache.commons.collections.functors.TransformedPredicate;
/*     */ import org.apache.commons.collections.functors.TransformerPredicate;
/*     */ import org.apache.commons.collections.functors.TruePredicate;
/*     */ import org.apache.commons.collections.functors.UniquePredicate;
/*     */ 
/*     */ public class PredicateUtils {
/*     */   public static Predicate exceptionPredicate() {
/*  96 */     return ExceptionPredicate.INSTANCE;
/*     */   }
/*     */   
/*     */   public static Predicate truePredicate() {
/* 107 */     return TruePredicate.INSTANCE;
/*     */   }
/*     */   
/*     */   public static Predicate falsePredicate() {
/* 118 */     return FalsePredicate.INSTANCE;
/*     */   }
/*     */   
/*     */   public static Predicate nullPredicate() {
/* 129 */     return NullPredicate.INSTANCE;
/*     */   }
/*     */   
/*     */   public static Predicate notNullPredicate() {
/* 140 */     return NotNullPredicate.INSTANCE;
/*     */   }
/*     */   
/*     */   public static Predicate equalPredicate(Object value) {
/* 153 */     return EqualPredicate.getInstance(value);
/*     */   }
/*     */   
/*     */   public static Predicate identityPredicate(Object value) {
/* 166 */     return IdentityPredicate.getInstance(value);
/*     */   }
/*     */   
/*     */   public static Predicate instanceofPredicate(Class type) {
/* 181 */     return InstanceofPredicate.getInstance(type);
/*     */   }
/*     */   
/*     */   public static Predicate uniquePredicate() {
/* 197 */     return UniquePredicate.getInstance();
/*     */   }
/*     */   
/*     */   public static Predicate invokerPredicate(String methodName) {
/* 219 */     return asPredicate(InvokerTransformer.getInstance(methodName));
/*     */   }
/*     */   
/*     */   public static Predicate invokerPredicate(String methodName, Class[] paramTypes, Object[] args) {
/* 244 */     return asPredicate(InvokerTransformer.getInstance(methodName, paramTypes, args));
/*     */   }
/*     */   
/*     */   public static Predicate andPredicate(Predicate predicate1, Predicate predicate2) {
/* 262 */     return AndPredicate.getInstance(predicate1, predicate2);
/*     */   }
/*     */   
/*     */   public static Predicate allPredicate(Predicate[] predicates) {
/* 278 */     return AllPredicate.getInstance(predicates);
/*     */   }
/*     */   
/*     */   public static Predicate allPredicate(Collection predicates) {
/* 294 */     return AllPredicate.getInstance(predicates);
/*     */   }
/*     */   
/*     */   public static Predicate orPredicate(Predicate predicate1, Predicate predicate2) {
/* 309 */     return OrPredicate.getInstance(predicate1, predicate2);
/*     */   }
/*     */   
/*     */   public static Predicate anyPredicate(Predicate[] predicates) {
/* 325 */     return AnyPredicate.getInstance(predicates);
/*     */   }
/*     */   
/*     */   public static Predicate anyPredicate(Collection predicates) {
/* 341 */     return AnyPredicate.getInstance(predicates);
/*     */   }
/*     */   
/*     */   public static Predicate eitherPredicate(Predicate predicate1, Predicate predicate2) {
/* 356 */     return onePredicate(new Predicate[] { predicate1, predicate2 });
/*     */   }
/*     */   
/*     */   public static Predicate onePredicate(Predicate[] predicates) {
/* 372 */     return OnePredicate.getInstance(predicates);
/*     */   }
/*     */   
/*     */   public static Predicate onePredicate(Collection predicates) {
/* 388 */     return OnePredicate.getInstance(predicates);
/*     */   }
/*     */   
/*     */   public static Predicate neitherPredicate(Predicate predicate1, Predicate predicate2) {
/* 403 */     return nonePredicate(new Predicate[] { predicate1, predicate2 });
/*     */   }
/*     */   
/*     */   public static Predicate nonePredicate(Predicate[] predicates) {
/* 419 */     return NonePredicate.getInstance(predicates);
/*     */   }
/*     */   
/*     */   public static Predicate nonePredicate(Collection predicates) {
/* 435 */     return NonePredicate.getInstance(predicates);
/*     */   }
/*     */   
/*     */   public static Predicate notPredicate(Predicate predicate) {
/* 449 */     return NotPredicate.getInstance(predicate);
/*     */   }
/*     */   
/*     */   public static Predicate asPredicate(Transformer transformer) {
/* 467 */     return TransformerPredicate.getInstance(transformer);
/*     */   }
/*     */   
/*     */   public static Predicate nullIsExceptionPredicate(Predicate predicate) {
/* 485 */     return NullIsExceptionPredicate.getInstance(predicate);
/*     */   }
/*     */   
/*     */   public static Predicate nullIsFalsePredicate(Predicate predicate) {
/* 500 */     return NullIsFalsePredicate.getInstance(predicate);
/*     */   }
/*     */   
/*     */   public static Predicate nullIsTruePredicate(Predicate predicate) {
/* 515 */     return NullIsTruePredicate.getInstance(predicate);
/*     */   }
/*     */   
/*     */   public static Predicate transformedPredicate(Transformer transformer, Predicate predicate) {
/* 533 */     return TransformedPredicate.getInstance(transformer, predicate);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\PredicateUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */