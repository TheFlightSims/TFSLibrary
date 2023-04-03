/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.beans.IntrospectionException;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ 
/*     */ public class MappedPropertyDescriptor extends PropertyDescriptor {
/*     */   private Reference mappedPropertyTypeRef;
/*     */   
/*     */   private MappedMethodReference mappedReadMethodRef;
/*     */   
/*     */   private MappedMethodReference mappedWriteMethodRef;
/*     */   
/*  70 */   private static final Class[] STRING_CLASS_PARAMETER = new Class[] { String.class };
/*     */   
/*     */   static Class class$org$apache$commons$beanutils$MappedPropertyDescriptor;
/*     */   
/*     */   public MappedPropertyDescriptor(String propertyName, Class beanClass) throws IntrospectionException {
/*  93 */     super(propertyName, (Method)null, (Method)null);
/*  95 */     if (propertyName == null || propertyName.length() == 0)
/*  96 */       throw new IntrospectionException("bad property name: " + propertyName + " on class: " + beanClass.getClass().getName()); 
/* 100 */     setName(propertyName);
/* 101 */     String base = capitalizePropertyName(propertyName);
/* 104 */     Method mappedReadMethod = null;
/* 105 */     Method mappedWriteMethod = null;
/*     */     try {
/*     */       try {
/* 108 */         mappedReadMethod = getMethod(beanClass, "get" + base, STRING_CLASS_PARAMETER);
/* 110 */       } catch (IntrospectionException e) {
/* 111 */         mappedReadMethod = getMethod(beanClass, "is" + base, STRING_CLASS_PARAMETER);
/*     */       } 
/* 114 */       Class[] params = { String.class, mappedReadMethod.getReturnType() };
/* 115 */       mappedWriteMethod = getMethod(beanClass, "set" + base, params);
/* 116 */     } catch (IntrospectionException e) {}
/* 123 */     if (mappedReadMethod == null)
/* 124 */       mappedWriteMethod = getMethod(beanClass, "set" + base, 2); 
/* 127 */     if (mappedReadMethod == null && mappedWriteMethod == null)
/* 128 */       throw new IntrospectionException("Property '" + propertyName + "' not found on " + beanClass.getName()); 
/* 132 */     this.mappedReadMethodRef = new MappedMethodReference(mappedReadMethod);
/* 133 */     this.mappedWriteMethodRef = new MappedMethodReference(mappedWriteMethod);
/* 135 */     findMappedPropertyType();
/*     */   }
/*     */   
/*     */   public MappedPropertyDescriptor(String propertyName, Class beanClass, String mappedGetterName, String mappedSetterName) throws IntrospectionException {
/* 160 */     super(propertyName, (Method)null, (Method)null);
/* 162 */     if (propertyName == null || propertyName.length() == 0)
/* 163 */       throw new IntrospectionException("bad property name: " + propertyName); 
/* 166 */     setName(propertyName);
/* 169 */     Method mappedReadMethod = null;
/* 170 */     Method mappedWriteMethod = null;
/* 171 */     mappedReadMethod = getMethod(beanClass, mappedGetterName, STRING_CLASS_PARAMETER);
/* 174 */     if (mappedReadMethod != null) {
/* 175 */       Class[] params = { String.class, mappedReadMethod.getReturnType() };
/* 176 */       mappedWriteMethod = getMethod(beanClass, mappedSetterName, params);
/*     */     } else {
/* 179 */       mappedWriteMethod = getMethod(beanClass, mappedSetterName, 2);
/*     */     } 
/* 182 */     this.mappedReadMethodRef = new MappedMethodReference(mappedReadMethod);
/* 183 */     this.mappedWriteMethodRef = new MappedMethodReference(mappedWriteMethod);
/* 185 */     findMappedPropertyType();
/*     */   }
/*     */   
/*     */   public MappedPropertyDescriptor(String propertyName, Method mappedGetter, Method mappedSetter) throws IntrospectionException {
/* 206 */     super(propertyName, mappedGetter, mappedSetter);
/* 208 */     if (propertyName == null || propertyName.length() == 0)
/* 209 */       throw new IntrospectionException("bad property name: " + propertyName); 
/* 213 */     setName(propertyName);
/* 214 */     this.mappedReadMethodRef = new MappedMethodReference(mappedGetter);
/* 215 */     this.mappedWriteMethodRef = new MappedMethodReference(mappedSetter);
/* 216 */     findMappedPropertyType();
/*     */   }
/*     */   
/*     */   public Class getMappedPropertyType() {
/* 232 */     return this.mappedPropertyTypeRef.get();
/*     */   }
/*     */   
/*     */   public Method getMappedReadMethod() {
/* 242 */     return this.mappedReadMethodRef.get();
/*     */   }
/*     */   
/*     */   public void setMappedReadMethod(Method mappedGetter) throws IntrospectionException {
/* 254 */     this.mappedReadMethodRef = new MappedMethodReference(mappedGetter);
/* 255 */     findMappedPropertyType();
/*     */   }
/*     */   
/*     */   public Method getMappedWriteMethod() {
/* 265 */     return this.mappedWriteMethodRef.get();
/*     */   }
/*     */   
/*     */   public void setMappedWriteMethod(Method mappedSetter) throws IntrospectionException {
/* 277 */     this.mappedWriteMethodRef = new MappedMethodReference(mappedSetter);
/* 278 */     findMappedPropertyType();
/*     */   }
/*     */   
/*     */   private void findMappedPropertyType() throws IntrospectionException {
/*     */     try {
/* 289 */       Method mappedReadMethod = getMappedReadMethod();
/* 290 */       Method mappedWriteMethod = getMappedWriteMethod();
/* 291 */       Class mappedPropertyType = null;
/* 292 */       if (mappedReadMethod != null) {
/* 293 */         if ((mappedReadMethod.getParameterTypes()).length != 1)
/* 294 */           throw new IntrospectionException("bad mapped read method arg count"); 
/* 297 */         mappedPropertyType = mappedReadMethod.getReturnType();
/* 298 */         if (mappedPropertyType == void.class)
/* 299 */           throw new IntrospectionException("mapped read method " + mappedReadMethod.getName() + " returns void"); 
/*     */       } 
/* 305 */       if (mappedWriteMethod != null) {
/* 306 */         Class[] params = mappedWriteMethod.getParameterTypes();
/* 307 */         if (params.length != 2)
/* 308 */           throw new IntrospectionException("bad mapped write method arg count"); 
/* 311 */         if (mappedPropertyType != null && mappedPropertyType != params[1])
/* 313 */           throw new IntrospectionException("type mismatch between mapped read and write methods"); 
/* 316 */         mappedPropertyType = params[1];
/*     */       } 
/* 318 */       this.mappedPropertyTypeRef = new SoftReference(mappedPropertyType);
/* 319 */     } catch (IntrospectionException ex) {
/* 320 */       throw ex;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String capitalizePropertyName(String s) {
/* 331 */     if (s.length() == 0)
/* 332 */       return s; 
/* 335 */     char[] chars = s.toCharArray();
/* 336 */     chars[0] = Character.toUpperCase(chars[0]);
/* 337 */     return new String(chars);
/*     */   }
/*     */   
/*     */   private static Method internalGetMethod(Class initial, String methodName, int parameterCount) {
/* 347 */     for (Class clazz = initial; clazz != null; clazz = clazz.getSuperclass()) {
/* 348 */       Method[] methods = clazz.getDeclaredMethods();
/* 349 */       for (int j = 0; j < methods.length; j++) {
/* 350 */         Method method = methods[j];
/* 351 */         if (method != null) {
/* 355 */           int mods = method.getModifiers();
/* 356 */           if (Modifier.isPublic(mods) && !Modifier.isStatic(mods))
/* 360 */             if (method.getName().equals(methodName) && (method.getParameterTypes()).length == parameterCount)
/* 362 */               return method;  
/*     */         } 
/*     */       } 
/*     */     } 
/* 370 */     Class[] interfaces = initial.getInterfaces();
/* 371 */     for (int i = 0; i < interfaces.length; i++) {
/* 372 */       Method method = internalGetMethod(interfaces[i], methodName, parameterCount);
/* 373 */       if (method != null)
/* 374 */         return method; 
/*     */     } 
/* 378 */     return null;
/*     */   }
/*     */   
/*     */   private static Method getMethod(Class clazz, String methodName, int parameterCount) throws IntrospectionException {
/* 386 */     if (methodName == null)
/* 387 */       return null; 
/* 390 */     Method method = internalGetMethod(clazz, methodName, parameterCount);
/* 391 */     if (method != null)
/* 392 */       return method; 
/* 396 */     throw new IntrospectionException("No method \"" + methodName + "\" with " + parameterCount + " parameter(s)");
/*     */   }
/*     */   
/*     */   private static Method getMethod(Class clazz, String methodName, Class[] parameterTypes) throws IntrospectionException {
/* 405 */     if (methodName == null)
/* 406 */       return null; 
/* 409 */     Method method = MethodUtils.getMatchingAccessibleMethod(clazz, methodName, parameterTypes);
/* 410 */     if (method != null)
/* 411 */       return method; 
/* 414 */     int parameterCount = (parameterTypes == null) ? 0 : parameterTypes.length;
/* 417 */     throw new IntrospectionException("No method \"" + methodName + "\" with " + parameterCount + " parameter(s) of matching types.");
/*     */   }
/*     */   
/*     */   private static class MappedMethodReference {
/*     */     private String className;
/*     */     
/*     */     private String methodName;
/*     */     
/*     */     private Reference methodRef;
/*     */     
/*     */     private Reference classRef;
/*     */     
/*     */     private Reference writeParamTypeRef0;
/*     */     
/*     */     private Reference writeParamTypeRef1;
/*     */     
/*     */     private String[] writeParamClassNames;
/*     */     
/*     */     MappedMethodReference(Method m) {
/* 438 */       if (m != null) {
/* 439 */         this.className = m.getDeclaringClass().getName();
/* 440 */         this.methodName = m.getName();
/* 441 */         this.methodRef = new SoftReference(m);
/* 442 */         this.classRef = new WeakReference(m.getDeclaringClass());
/* 443 */         Class[] types = m.getParameterTypes();
/* 444 */         if (types.length == 2) {
/* 445 */           this.writeParamTypeRef0 = new WeakReference(types[0]);
/* 446 */           this.writeParamTypeRef1 = new WeakReference(types[1]);
/* 447 */           this.writeParamClassNames = new String[2];
/* 448 */           this.writeParamClassNames[0] = types[0].getName();
/* 449 */           this.writeParamClassNames[1] = types[1].getName();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private Method get() {
/* 454 */       if (this.methodRef == null)
/* 455 */         return null; 
/* 457 */       Method m = this.methodRef.get();
/* 458 */       if (m == null) {
/* 459 */         Class clazz = this.classRef.get();
/* 460 */         if (clazz == null) {
/* 461 */           clazz = reLoadClass();
/* 462 */           if (clazz != null)
/* 463 */             this.classRef = new WeakReference(clazz); 
/*     */         } 
/* 466 */         if (clazz == null)
/* 467 */           throw new RuntimeException("Method " + this.methodName + " for " + this.className + " could not be reconstructed - class reference has gone"); 
/* 470 */         Class[] paramTypes = null;
/* 471 */         if (this.writeParamClassNames != null) {
/* 472 */           paramTypes = new Class[2];
/* 473 */           paramTypes[0] = this.writeParamTypeRef0.get();
/* 474 */           if (paramTypes[0] == null) {
/* 475 */             paramTypes[0] = reLoadClass(this.writeParamClassNames[0]);
/* 476 */             if (paramTypes[0] != null)
/* 477 */               this.writeParamTypeRef0 = new WeakReference(paramTypes[0]); 
/*     */           } 
/* 480 */           paramTypes[1] = this.writeParamTypeRef1.get();
/* 481 */           if (paramTypes[1] == null) {
/* 482 */             paramTypes[1] = reLoadClass(this.writeParamClassNames[1]);
/* 483 */             if (paramTypes[1] != null)
/* 484 */               this.writeParamTypeRef1 = new WeakReference(paramTypes[1]); 
/*     */           } 
/*     */         } else {
/* 488 */           paramTypes = MappedPropertyDescriptor.STRING_CLASS_PARAMETER;
/*     */         } 
/*     */         try {
/* 491 */           m = clazz.getMethod(this.methodName, paramTypes);
/* 494 */         } catch (NoSuchMethodException e) {
/* 495 */           throw new RuntimeException("Method " + this.methodName + " for " + this.className + " could not be reconstructed - method not found");
/*     */         } 
/* 498 */         this.methodRef = new SoftReference(m);
/*     */       } 
/* 500 */       return m;
/*     */     }
/*     */     
/*     */     private Class reLoadClass() {
/* 507 */       return reLoadClass(this.className);
/*     */     }
/*     */     
/*     */     private Class reLoadClass(String name) {
/* 515 */       ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
/* 518 */       if (classLoader != null)
/*     */         try {
/* 520 */           return classLoader.loadClass(name);
/* 521 */         } catch (ClassNotFoundException e) {} 
/* 527 */       classLoader = ((MappedPropertyDescriptor.class$org$apache$commons$beanutils$MappedPropertyDescriptor == null) ? (MappedPropertyDescriptor.class$org$apache$commons$beanutils$MappedPropertyDescriptor = MappedPropertyDescriptor.class$("org.apache.commons.beanutils.MappedPropertyDescriptor")) : MappedPropertyDescriptor.class$org$apache$commons$beanutils$MappedPropertyDescriptor).getClassLoader();
/*     */       try {
/* 529 */         return classLoader.loadClass(name);
/* 530 */       } catch (ClassNotFoundException e) {
/* 531 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\MappedPropertyDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */