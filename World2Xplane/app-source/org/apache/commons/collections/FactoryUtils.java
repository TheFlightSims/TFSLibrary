/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import org.apache.commons.collections.functors.ConstantFactory;
/*     */ import org.apache.commons.collections.functors.ExceptionFactory;
/*     */ import org.apache.commons.collections.functors.InstantiateFactory;
/*     */ import org.apache.commons.collections.functors.PrototypeFactory;
/*     */ 
/*     */ public class FactoryUtils {
/*     */   public static Factory exceptionFactory() {
/*  59 */     return ExceptionFactory.INSTANCE;
/*     */   }
/*     */   
/*     */   public static Factory nullFactory() {
/*  71 */     return ConstantFactory.NULL_INSTANCE;
/*     */   }
/*     */   
/*     */   public static Factory constantFactory(Object constantToReturn) {
/*  86 */     return ConstantFactory.getInstance(constantToReturn);
/*     */   }
/*     */   
/*     */   public static Factory prototypeFactory(Object prototype) {
/* 107 */     return PrototypeFactory.getInstance(prototype);
/*     */   }
/*     */   
/*     */   public static Factory instantiateFactory(Class classToInstantiate) {
/* 121 */     return InstantiateFactory.getInstance(classToInstantiate, null, null);
/*     */   }
/*     */   
/*     */   public static Factory instantiateFactory(Class classToInstantiate, Class[] paramTypes, Object[] args) {
/* 139 */     return InstantiateFactory.getInstance(classToInstantiate, paramTypes, args);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\FactoryUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */