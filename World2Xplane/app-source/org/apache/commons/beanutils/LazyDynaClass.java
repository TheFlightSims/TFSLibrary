/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ public class LazyDynaClass extends BasicDynaClass implements MutableDynaClass {
/*     */   protected boolean restricted;
/*     */   
/*     */   protected boolean returnNull = false;
/*     */   
/*     */   public LazyDynaClass() {
/*  64 */     this((String)null, (DynaProperty[])null);
/*     */   }
/*     */   
/*     */   public LazyDynaClass(String name) {
/*  73 */     this(name, (DynaProperty[])null);
/*     */   }
/*     */   
/*     */   public LazyDynaClass(String name, Class dynaBeanClass) {
/*  83 */     this(name, dynaBeanClass, (DynaProperty[])null);
/*     */   }
/*     */   
/*     */   public LazyDynaClass(String name, DynaProperty[] properties) {
/*  93 */     this(name, LazyDynaBean.class, properties);
/*     */   }
/*     */   
/*     */   public LazyDynaClass(String name, Class dynaBeanClass, DynaProperty[] properties) {
/* 104 */     super(name, dynaBeanClass, properties);
/*     */   }
/*     */   
/*     */   public boolean isRestricted() {
/* 115 */     return this.restricted;
/*     */   }
/*     */   
/*     */   public void setRestricted(boolean restricted) {
/* 126 */     this.restricted = restricted;
/*     */   }
/*     */   
/*     */   public boolean isReturnNull() {
/* 139 */     return this.returnNull;
/*     */   }
/*     */   
/*     */   public void setReturnNull(boolean returnNull) {
/* 151 */     this.returnNull = returnNull;
/*     */   }
/*     */   
/*     */   public void add(String name) {
/* 165 */     add(new DynaProperty(name));
/*     */   }
/*     */   
/*     */   public void add(String name, Class type) {
/* 181 */     if (type == null) {
/* 182 */       add(name);
/*     */     } else {
/* 184 */       add(new DynaProperty(name, type));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(String name, Class type, boolean readable, boolean writeable) {
/* 210 */     throw new UnsupportedOperationException("readable/writable properties not supported");
/*     */   }
/*     */   
/*     */   protected void add(DynaProperty property) {
/* 224 */     if (property.getName() == null)
/* 225 */       throw new IllegalArgumentException("Property name is missing."); 
/* 228 */     if (isRestricted())
/* 229 */       throw new IllegalStateException("DynaClass is currently restricted. No new properties can be added."); 
/* 233 */     if (this.propertiesMap.get(property.getName()) != null)
/*     */       return; 
/* 238 */     DynaProperty[] oldProperties = getDynaProperties();
/* 239 */     DynaProperty[] newProperties = new DynaProperty[oldProperties.length + 1];
/* 240 */     System.arraycopy(oldProperties, 0, newProperties, 0, oldProperties.length);
/* 241 */     newProperties[oldProperties.length] = property;
/* 244 */     setProperties(newProperties);
/*     */   }
/*     */   
/*     */   public void remove(String name) {
/* 263 */     if (name == null)
/* 264 */       throw new IllegalArgumentException("Property name is missing."); 
/* 267 */     if (isRestricted())
/* 268 */       throw new IllegalStateException("DynaClass is currently restricted. No properties can be removed."); 
/* 272 */     if (this.propertiesMap.get(name) == null)
/*     */       return; 
/* 278 */     DynaProperty[] oldProperties = getDynaProperties();
/* 279 */     DynaProperty[] newProperties = new DynaProperty[oldProperties.length - 1];
/* 280 */     int j = 0;
/* 281 */     for (int i = 0; i < oldProperties.length; i++) {
/* 282 */       if (!name.equals(oldProperties[i].getName())) {
/* 283 */         newProperties[j] = oldProperties[i];
/* 284 */         j++;
/*     */       } 
/*     */     } 
/* 289 */     setProperties(newProperties);
/*     */   }
/*     */   
/*     */   public DynaProperty getDynaProperty(String name) {
/* 318 */     if (name == null)
/* 319 */       throw new IllegalArgumentException("Property name is missing."); 
/* 322 */     DynaProperty dynaProperty = (DynaProperty)this.propertiesMap.get(name);
/* 326 */     if (dynaProperty == null && !isReturnNull() && !isRestricted())
/* 327 */       dynaProperty = new DynaProperty(name); 
/* 330 */     return dynaProperty;
/*     */   }
/*     */   
/*     */   public boolean isDynaProperty(String name) {
/* 349 */     if (name == null)
/* 350 */       throw new IllegalArgumentException("Property name is missing."); 
/* 353 */     return !(this.propertiesMap.get(name) == null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\LazyDynaClass.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */