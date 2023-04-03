/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ 
/*     */ public class ContextClassLoaderLocal {
/* 104 */   private Map valueByClassLoader = new WeakHashMap();
/*     */   
/*     */   private boolean globalValueInitialized = false;
/*     */   
/*     */   private Object globalValue;
/*     */   
/*     */   protected Object initialValue() {
/* 129 */     return null;
/*     */   }
/*     */   
/*     */   public synchronized Object get() {
/* 144 */     this.valueByClassLoader.isEmpty();
/*     */     try {
/* 147 */       ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
/* 148 */       if (contextClassLoader != null) {
/* 150 */         Object value = this.valueByClassLoader.get(contextClassLoader);
/* 151 */         if (value == null && !this.valueByClassLoader.containsKey(contextClassLoader)) {
/* 153 */           value = initialValue();
/* 154 */           this.valueByClassLoader.put(contextClassLoader, value);
/*     */         } 
/* 156 */         return value;
/*     */       } 
/* 160 */     } catch (SecurityException e) {}
/* 163 */     if (!this.globalValueInitialized) {
/* 164 */       this.globalValue = initialValue();
/* 165 */       this.globalValueInitialized = true;
/*     */     } 
/* 167 */     return this.globalValue;
/*     */   }
/*     */   
/*     */   public synchronized void set(Object value) {
/* 181 */     this.valueByClassLoader.isEmpty();
/*     */     try {
/* 184 */       ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
/* 185 */       if (contextClassLoader != null) {
/* 186 */         this.valueByClassLoader.put(contextClassLoader, value);
/*     */         return;
/*     */       } 
/* 190 */     } catch (SecurityException e) {}
/* 193 */     this.globalValue = value;
/* 194 */     this.globalValueInitialized = true;
/*     */   }
/*     */   
/*     */   public synchronized void unset() {
/*     */     try {
/* 203 */       ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
/* 204 */       unset(contextClassLoader);
/* 206 */     } catch (SecurityException e) {}
/*     */   }
/*     */   
/*     */   public synchronized void unset(ClassLoader classLoader) {
/* 214 */     this.valueByClassLoader.remove(classLoader);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\ContextClassLoaderLocal.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */