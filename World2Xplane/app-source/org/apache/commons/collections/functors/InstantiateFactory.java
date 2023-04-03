/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.commons.collections.Factory;
/*     */ import org.apache.commons.collections.FunctorException;
/*     */ 
/*     */ public class InstantiateFactory implements Factory, Serializable {
/*     */   private static final long serialVersionUID = -7732226881069447957L;
/*     */   
/*     */   private final Class iClassToInstantiate;
/*     */   
/*     */   private final Class[] iParamTypes;
/*     */   
/*     */   private final Object[] iArgs;
/*     */   
/*  46 */   private transient Constructor iConstructor = null;
/*     */   
/*     */   public static Factory getInstance(Class classToInstantiate, Class[] paramTypes, Object[] args) {
/*  57 */     if (classToInstantiate == null)
/*  58 */       throw new IllegalArgumentException("Class to instantiate must not be null"); 
/*  60 */     if ((paramTypes == null && args != null) || (paramTypes != null && args == null) || (paramTypes != null && args != null && paramTypes.length != args.length))
/*  63 */       throw new IllegalArgumentException("Parameter types must match the arguments"); 
/*  66 */     if (paramTypes == null || paramTypes.length == 0)
/*  67 */       return new InstantiateFactory(classToInstantiate); 
/*  69 */     paramTypes = (Class[])paramTypes.clone();
/*  70 */     args = (Object[])args.clone();
/*  71 */     return new InstantiateFactory(classToInstantiate, paramTypes, args);
/*     */   }
/*     */   
/*     */   public InstantiateFactory(Class classToInstantiate) {
/*  83 */     this.iClassToInstantiate = classToInstantiate;
/*  84 */     this.iParamTypes = null;
/*  85 */     this.iArgs = null;
/*  86 */     findConstructor();
/*     */   }
/*     */   
/*     */   public InstantiateFactory(Class classToInstantiate, Class[] paramTypes, Object[] args) {
/*  99 */     this.iClassToInstantiate = classToInstantiate;
/* 100 */     this.iParamTypes = paramTypes;
/* 101 */     this.iArgs = args;
/* 102 */     findConstructor();
/*     */   }
/*     */   
/*     */   private void findConstructor() {
/*     */     try {
/* 110 */       this.iConstructor = this.iClassToInstantiate.getConstructor(this.iParamTypes);
/* 112 */     } catch (NoSuchMethodException ex) {
/* 113 */       throw new IllegalArgumentException("InstantiateFactory: The constructor must exist and be public ");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object create() {
/* 124 */     if (this.iConstructor == null)
/* 125 */       findConstructor(); 
/*     */     try {
/* 129 */       return this.iConstructor.newInstance(this.iArgs);
/* 131 */     } catch (InstantiationException ex) {
/* 132 */       throw new FunctorException("InstantiateFactory: InstantiationException", ex);
/* 133 */     } catch (IllegalAccessException ex) {
/* 134 */       throw new FunctorException("InstantiateFactory: Constructor must be public", ex);
/* 135 */     } catch (InvocationTargetException ex) {
/* 136 */       throw new FunctorException("InstantiateFactory: Constructor threw an exception", ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\InstantiateFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */