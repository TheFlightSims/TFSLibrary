/*     */ package org.apache.commons.beanutils.converters;
/*     */ 
/*     */ public final class ClassConverter extends AbstractConverter {
/*     */   public ClassConverter() {}
/*     */   
/*     */   public ClassConverter(Object defaultValue) {
/*  53 */     super(defaultValue);
/*     */   }
/*     */   
/*     */   protected Class getDefaultType() {
/*  63 */     return Class.class;
/*     */   }
/*     */   
/*     */   protected String convertToString(Object value) {
/*  74 */     return (value instanceof Class) ? ((Class)value).getName() : value.toString();
/*     */   }
/*     */   
/*     */   protected Object convertToType(Class type, Object value) throws Throwable {
/*  87 */     ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
/*  89 */     if (classLoader != null)
/*     */       try {
/*  91 */         return classLoader.loadClass(value.toString());
/*  92 */       } catch (ClassNotFoundException ex) {} 
/*  99 */     classLoader = ClassConverter.class.getClassLoader();
/* 100 */     return classLoader.loadClass(value.toString());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\ClassConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */