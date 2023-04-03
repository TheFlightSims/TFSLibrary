/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.commons.collections.FunctorException;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public class InstantiateTransformer implements Transformer, Serializable {
/*     */   private static final long serialVersionUID = 3786388740793356347L;
/*     */   
/*  40 */   public static final Transformer NO_ARG_INSTANCE = new InstantiateTransformer();
/*     */   
/*     */   private final Class[] iParamTypes;
/*     */   
/*     */   private final Object[] iArgs;
/*     */   
/*     */   public static Transformer getInstance(Class[] paramTypes, Object[] args) {
/*  55 */     if ((paramTypes == null && args != null) || (paramTypes != null && args == null) || (paramTypes != null && args != null && paramTypes.length != args.length))
/*  58 */       throw new IllegalArgumentException("Parameter types must match the arguments"); 
/*  61 */     if (paramTypes == null || paramTypes.length == 0)
/*  62 */       return NO_ARG_INSTANCE; 
/*  64 */     paramTypes = (Class[])paramTypes.clone();
/*  65 */     args = (Object[])args.clone();
/*  67 */     return new InstantiateTransformer(paramTypes, args);
/*     */   }
/*     */   
/*     */   private InstantiateTransformer() {
/*  75 */     this.iParamTypes = null;
/*  76 */     this.iArgs = null;
/*     */   }
/*     */   
/*     */   public InstantiateTransformer(Class[] paramTypes, Object[] args) {
/*  88 */     this.iParamTypes = paramTypes;
/*  89 */     this.iArgs = args;
/*     */   }
/*     */   
/*     */   public Object transform(Object input) {
/*     */     try {
/* 100 */       if (!(input instanceof Class))
/* 101 */         throw new FunctorException("InstantiateTransformer: Input object was not an instanceof Class, it was a " + ((input == null) ? "null object" : input.getClass().getName())); 
/* 105 */       Constructor con = ((Class)input).getConstructor(this.iParamTypes);
/* 106 */       return con.newInstance(this.iArgs);
/* 108 */     } catch (NoSuchMethodException ex) {
/* 109 */       throw new FunctorException("InstantiateTransformer: The constructor must exist and be public ");
/* 110 */     } catch (InstantiationException ex) {
/* 111 */       throw new FunctorException("InstantiateTransformer: InstantiationException", ex);
/* 112 */     } catch (IllegalAccessException ex) {
/* 113 */       throw new FunctorException("InstantiateTransformer: Constructor must be public", ex);
/* 114 */     } catch (InvocationTargetException ex) {
/* 115 */       throw new FunctorException("InstantiateTransformer: Constructor threw an exception", ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\InstantiateTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */