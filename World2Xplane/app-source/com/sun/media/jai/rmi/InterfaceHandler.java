/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Hashtable;
/*     */ import javax.media.jai.remote.SerializableState;
/*     */ 
/*     */ class InterfaceHandler implements InvocationHandler {
/*     */   private Hashtable interfaceMap;
/*     */   
/*     */   public InterfaceHandler(Class[] interfaces, SerializableState[] implementations) {
/* 107 */     if (interfaces == null || implementations == null)
/* 108 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 109 */     if (interfaces.length != implementations.length)
/* 110 */       throw new IllegalArgumentException(JaiI18N.getString("InterfaceHandler0")); 
/* 113 */     int numInterfaces = interfaces.length;
/* 114 */     this.interfaceMap = new Hashtable(numInterfaces);
/* 115 */     for (int i = 0; i < numInterfaces; i++) {
/* 116 */       Class iface = interfaces[i];
/* 117 */       SerializableState state = implementations[i];
/* 119 */       if (!iface.isAssignableFrom(state.getObjectClass()))
/* 120 */         throw new RuntimeException(JaiI18N.getString("InterfaceHandler1")); 
/* 123 */       Object impl = state.getObject();
/* 124 */       this.interfaceMap.put(iface, impl);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
/* 130 */     Class key = method.getDeclaringClass();
/* 131 */     if (!this.interfaceMap.containsKey(key)) {
/* 132 */       Class[] classes = (Class[])this.interfaceMap.keySet().toArray((Object[])new Class[0]);
/* 134 */       for (int i = 0; i < classes.length; i++) {
/* 135 */         Class aClass = classes[i];
/* 136 */         if (key.isAssignableFrom(aClass)) {
/* 137 */           this.interfaceMap.put(key, this.interfaceMap.get(aClass));
/*     */           break;
/*     */         } 
/*     */       } 
/* 141 */       if (!this.interfaceMap.containsKey(key))
/* 142 */         throw new RuntimeException(key.getName() + JaiI18N.getString("InterfaceHandler2")); 
/*     */     } 
/* 147 */     Object result = null;
/*     */     try {
/* 149 */       Object impl = this.interfaceMap.get(key);
/* 150 */       result = method.invoke(impl, args);
/* 151 */     } catch (IllegalAccessException e) {
/* 152 */       throw new RuntimeException(method.getName() + JaiI18N.getString("InterfaceHandler3"));
/*     */     } 
/* 156 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\InterfaceHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */