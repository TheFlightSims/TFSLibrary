/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public abstract class PropertyGeneratorImpl implements PropertyGenerator {
/*     */   private String[] propertyNames;
/*     */   
/*     */   private Class[] propertyClasses;
/*     */   
/*     */   private Class[] supportedOpClasses;
/*     */   
/*     */   protected PropertyGeneratorImpl(String[] propertyNames, Class[] propertyClasses, Class[] supportedOpClasses) {
/*  54 */     if (propertyNames == null || propertyClasses == null || supportedOpClasses == null)
/*  57 */       throw new IllegalArgumentException(JaiI18N.getString("PropertyGeneratorImpl0")); 
/*  58 */     if (propertyNames.length == 0 || propertyClasses.length == 0 || supportedOpClasses.length == 0)
/*  61 */       throw new IllegalArgumentException(JaiI18N.getString("PropertyGeneratorImpl1")); 
/*  62 */     if (propertyNames.length != propertyClasses.length)
/*  63 */       throw new IllegalArgumentException(JaiI18N.getString("PropertyGeneratorImpl2")); 
/*  66 */     for (int i = 0; i < propertyClasses.length; i++) {
/*  67 */       if (propertyClasses[i].isPrimitive())
/*  68 */         throw new IllegalArgumentException(JaiI18N.getString("PropertyGeneratorImpl4")); 
/*     */     } 
/*  72 */     this.propertyNames = propertyNames;
/*  73 */     this.propertyClasses = propertyClasses;
/*  74 */     this.supportedOpClasses = supportedOpClasses;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/*  85 */     return this.propertyNames;
/*     */   }
/*     */   
/*     */   public Class getClass(String propertyName) {
/* 103 */     if (propertyName == null)
/* 104 */       throw new IllegalArgumentException(JaiI18N.getString("PropertyGeneratorImpl0")); 
/* 108 */     int numProperties = this.propertyNames.length;
/* 109 */     for (int i = 0; i < numProperties; i++) {
/* 110 */       if (propertyName.equalsIgnoreCase(this.propertyNames[i]))
/* 111 */         return this.propertyClasses[i]; 
/*     */     } 
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canGenerateProperties(Object opNode) {
/* 128 */     if (opNode == null)
/* 129 */       throw new IllegalArgumentException(JaiI18N.getString("PropertyGeneratorImpl0")); 
/* 132 */     int numClasses = this.supportedOpClasses.length;
/* 133 */     if (numClasses == 1)
/* 134 */       return this.supportedOpClasses[0].isInstance(opNode); 
/* 137 */     for (int i = 0; i < numClasses; i++) {
/* 138 */       if (this.supportedOpClasses[i].isInstance(opNode))
/* 139 */         return true; 
/*     */     } 
/* 144 */     return false;
/*     */   }
/*     */   
/*     */   public abstract Object getProperty(String paramString, Object paramObject);
/*     */   
/*     */   public Object getProperty(String name, RenderedOp op) {
/* 197 */     return getProperty(name, op);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name, RenderableOp op) {
/* 223 */     return getProperty(name, op);
/*     */   }
/*     */   
/*     */   protected void validate(String name, Object opNode) {
/* 241 */     if (name == null)
/* 242 */       throw new IllegalArgumentException(JaiI18N.getString("PropertyGeneratorImpl0")); 
/* 243 */     if (!canGenerateProperties(opNode))
/* 244 */       throw new IllegalArgumentException(JaiI18N.getString("PropertyGeneratorImpl3")); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\PropertyGeneratorImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */