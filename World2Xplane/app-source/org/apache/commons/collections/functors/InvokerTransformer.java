/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import org.apache.commons.collections.FunctorException;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public class InvokerTransformer implements Transformer, Serializable {
/*     */   private static final long serialVersionUID = -8653385846894047688L;
/*     */   
/*     */   private final String iMethodName;
/*     */   
/*     */   private final Class[] iParamTypes;
/*     */   
/*     */   private final Object[] iArgs;
/*     */   
/*     */   public static Transformer getInstance(String methodName) {
/*  54 */     if (methodName == null)
/*  55 */       throw new IllegalArgumentException("The method to invoke must not be null"); 
/*  57 */     return new InvokerTransformer(methodName);
/*     */   }
/*     */   
/*     */   public static Transformer getInstance(String methodName, Class[] paramTypes, Object[] args) {
/*  69 */     if (methodName == null)
/*  70 */       throw new IllegalArgumentException("The method to invoke must not be null"); 
/*  72 */     if ((paramTypes == null && args != null) || (paramTypes != null && args == null) || (paramTypes != null && args != null && paramTypes.length != args.length))
/*  75 */       throw new IllegalArgumentException("The parameter types must match the arguments"); 
/*  77 */     if (paramTypes == null || paramTypes.length == 0)
/*  78 */       return new InvokerTransformer(methodName); 
/*  80 */     paramTypes = (Class[])paramTypes.clone();
/*  81 */     args = (Object[])args.clone();
/*  82 */     return new InvokerTransformer(methodName, paramTypes, args);
/*     */   }
/*     */   
/*     */   private InvokerTransformer(String methodName) {
/*  93 */     this.iMethodName = methodName;
/*  94 */     this.iParamTypes = null;
/*  95 */     this.iArgs = null;
/*     */   }
/*     */   
/*     */   public InvokerTransformer(String methodName, Class[] paramTypes, Object[] args) {
/* 108 */     this.iMethodName = methodName;
/* 109 */     this.iParamTypes = paramTypes;
/* 110 */     this.iArgs = args;
/*     */   }
/*     */   
/*     */   public Object transform(Object input) {
/* 120 */     if (input == null)
/* 121 */       return null; 
/*     */     try {
/* 124 */       Class cls = input.getClass();
/* 125 */       Method method = cls.getMethod(this.iMethodName, this.iParamTypes);
/* 126 */       return method.invoke(input, this.iArgs);
/* 128 */     } catch (NoSuchMethodException ex) {
/* 129 */       throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + input.getClass() + "' does not exist");
/* 130 */     } catch (IllegalAccessException ex) {
/* 131 */       throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + input.getClass() + "' cannot be accessed");
/* 132 */     } catch (InvocationTargetException ex) {
/* 133 */       throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + input.getClass() + "' threw an exception", ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\InvokerTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */