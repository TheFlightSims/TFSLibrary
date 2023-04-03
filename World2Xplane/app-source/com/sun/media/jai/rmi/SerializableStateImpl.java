/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.media.jai.remote.SerializableState;
/*     */ import javax.media.jai.remote.SerializerFactory;
/*     */ 
/*     */ public abstract class SerializableStateImpl implements SerializableState {
/*     */   protected Class theClass;
/*     */   
/*     */   protected transient Object theObject;
/*     */   
/*     */   public static Class[] getSupportedClasses() {
/*  50 */     throw new RuntimeException(JaiI18N.getString("SerializableStateImpl0"));
/*     */   }
/*     */   
/*     */   public static boolean permitsSubclasses() {
/*  60 */     return false;
/*     */   }
/*     */   
/*     */   protected SerializableStateImpl(Class c, Object o, RenderingHints h) {
/*  68 */     if (c == null || o == null)
/*  69 */       throw new IllegalArgumentException(JaiI18N.getString("SerializableStateImpl1")); 
/*  71 */     boolean isInterface = c.isInterface();
/*  72 */     if (isInterface && !c.isInstance(o))
/*  73 */       throw new IllegalArgumentException(JaiI18N.getString("SerializableStateImpl2")); 
/*  74 */     if (!isInterface) {
/*  75 */       boolean permitsSubclasses = false;
/*     */       try {
/*  77 */         Method m = getClass().getMethod("permitsSubclasses", null);
/*  79 */         permitsSubclasses = ((Boolean)m.invoke(null, null)).booleanValue();
/*  81 */       } catch (Exception e) {
/*  82 */         throw new IllegalArgumentException(JaiI18N.getString("SerializableStateImpl5"));
/*     */       } 
/*  85 */       if (!permitsSubclasses && !c.equals(o.getClass()))
/*  86 */         throw new IllegalArgumentException(JaiI18N.getString("SerializableStateImpl3")); 
/*  87 */       if (permitsSubclasses && !c.isAssignableFrom(o.getClass()))
/*  89 */         throw new IllegalArgumentException(JaiI18N.getString("SerializableStateImpl4")); 
/*     */     } 
/*  93 */     this.theClass = c;
/*  94 */     this.theObject = o;
/*     */   }
/*     */   
/*     */   public Class getObjectClass() {
/*  98 */     return this.theClass;
/*     */   }
/*     */   
/*     */   public Object getObject() {
/* 102 */     return this.theObject;
/*     */   }
/*     */   
/*     */   protected Object getSerializableForm(Object object) {
/* 106 */     if (object instanceof java.io.Serializable)
/* 107 */       return object; 
/* 108 */     if (object != null)
/*     */       try {
/* 110 */         object = SerializerFactory.getState(object, null);
/* 111 */       } catch (Exception e) {
/* 112 */         object = null;
/*     */       }  
/* 114 */     return object;
/*     */   }
/*     */   
/*     */   protected Object getDeserializedFrom(Object object) {
/* 118 */     if (object instanceof SerializableState)
/* 119 */       object = ((SerializableState)object).getObject(); 
/* 120 */     return object;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\SerializableStateImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */