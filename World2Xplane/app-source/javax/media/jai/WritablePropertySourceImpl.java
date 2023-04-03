/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.util.CaselessStringKey;
/*     */ 
/*     */ public class WritablePropertySourceImpl extends PropertySourceImpl implements WritablePropertySource {
/*  53 */   protected PropertyChangeSupportJAI manager = null;
/*     */   
/*     */   public WritablePropertySourceImpl() {}
/*     */   
/*     */   public WritablePropertySourceImpl(Map propertyMap, PropertySource source, PropertyChangeSupportJAI manager) {
/*  91 */     super(propertyMap, source);
/*  92 */     this.manager = manager;
/*     */   }
/*     */   
/*     */   public Object getProperty(String propertyName) {
/* 120 */     if (propertyName == null)
/* 121 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 124 */     synchronized (this.properties) {
/* 126 */       boolean isMapped = this.properties.containsKey(new CaselessStringKey(propertyName));
/* 130 */       Object value = super.getProperty(propertyName);
/* 135 */       if (this.manager != null && !isMapped && value != Image.UndefinedProperty) {
/* 139 */         Object eventSource = this.manager.getPropertyChangeEventSource();
/* 141 */         PropertySourceChangeEvent evt = new PropertySourceChangeEvent(eventSource, propertyName, Image.UndefinedProperty, value);
/* 145 */         this.manager.firePropertyChange(evt);
/*     */       } 
/* 148 */       return value;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setProperty(String propertyName, Object propertyValue) {
/* 178 */     if (propertyName == null || propertyValue == null)
/* 179 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 182 */     synchronized (this.properties) {
/* 183 */       CaselessStringKey key = new CaselessStringKey(propertyName);
/* 186 */       Object oldValue = this.properties.put(key, propertyValue);
/* 187 */       if (oldValue == null)
/* 188 */         oldValue = Image.UndefinedProperty; 
/* 192 */       this.cachedPropertyNames.remove(key);
/* 194 */       if (this.manager != null && !oldValue.equals(propertyValue)) {
/* 195 */         Object eventSource = this.manager.getPropertyChangeEventSource();
/* 197 */         PropertySourceChangeEvent evt = new PropertySourceChangeEvent(eventSource, propertyName, oldValue, propertyValue);
/* 200 */         this.manager.firePropertyChange(evt);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeProperty(String propertyName) {
/* 219 */     if (propertyName == null)
/* 220 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 223 */     synchronized (this.properties) {
/* 224 */       CaselessStringKey key = new CaselessStringKey(propertyName);
/* 227 */       Object oldValue = this.properties.remove(key);
/* 231 */       this.propertySources.remove(key);
/* 232 */       this.cachedPropertyNames.remove(key);
/* 234 */       if (this.manager != null && oldValue != null) {
/* 235 */         Object eventSource = this.manager.getPropertyChangeEventSource();
/* 237 */         PropertySourceChangeEvent evt = new PropertySourceChangeEvent(eventSource, propertyName, oldValue, Image.UndefinedProperty);
/* 241 */         this.manager.firePropertyChange(evt);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addProperties(Map propertyMap) {
/* 263 */     if (propertyMap != null)
/* 264 */       synchronized (this.properties) {
/* 265 */         Iterator keys = propertyMap.keySet().iterator();
/* 266 */         while (keys.hasNext()) {
/* 267 */           Object key = keys.next();
/* 268 */           if (key instanceof String) {
/* 269 */             setProperty((String)key, propertyMap.get(key));
/*     */             continue;
/*     */           } 
/* 271 */           if (key instanceof CaselessStringKey)
/* 272 */             setProperty(((CaselessStringKey)key).getName(), propertyMap.get(key)); 
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   public void addProperties(PropertySource propertySource) {
/* 296 */     if (propertySource != null)
/* 297 */       synchronized (this.properties) {
/* 298 */         String[] names = propertySource.getPropertyNames();
/* 299 */         if (names != null) {
/* 300 */           int length = names.length;
/* 301 */           for (int i = 0; i < length; i++)
/* 302 */             this.propertySources.put(new CaselessStringKey(names[i]), propertySource); 
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   public void clearProperties() {
/* 319 */     synchronized (this.properties) {
/* 320 */       String[] names = getPropertyNames();
/* 321 */       if (names != null) {
/* 322 */         int length = names.length;
/* 323 */         for (int i = 0; i < length; i++)
/* 324 */           removeProperty(names[i]); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearPropertyMap() {
/* 338 */     synchronized (this.properties) {
/* 339 */       Iterator keys = this.properties.keySet().iterator();
/* 340 */       while (keys.hasNext()) {
/* 341 */         CaselessStringKey key = keys.next();
/* 342 */         Object oldValue = this.properties.get(key);
/* 343 */         keys.remove();
/* 345 */         if (this.manager != null) {
/* 346 */           Object eventSource = this.manager.getPropertyChangeEventSource();
/* 348 */           PropertySourceChangeEvent evt = new PropertySourceChangeEvent(eventSource, key.getName(), oldValue, Image.UndefinedProperty);
/* 353 */           this.manager.firePropertyChange(evt);
/*     */         } 
/*     */       } 
/* 358 */       this.cachedPropertyNames.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearPropertySourceMap() {
/* 367 */     synchronized (this.properties) {
/* 368 */       this.propertySources.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearCachedProperties() {
/* 381 */     synchronized (this.properties) {
/* 382 */       Iterator names = this.cachedPropertyNames.iterator();
/* 383 */       while (names.hasNext()) {
/* 384 */         CaselessStringKey name = names.next();
/* 385 */         Object oldValue = this.properties.remove(name);
/* 386 */         names.remove();
/* 387 */         if (this.manager != null) {
/* 388 */           Object eventSource = this.manager.getPropertyChangeEventSource();
/* 390 */           PropertySourceChangeEvent evt = new PropertySourceChangeEvent(eventSource, name.getName(), oldValue, Image.UndefinedProperty);
/* 395 */           this.manager.firePropertyChange(evt);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removePropertySource(PropertySource propertySource) {
/* 406 */     synchronized (this.properties) {
/* 407 */       Iterator keys = this.propertySources.keySet().iterator();
/* 408 */       while (keys.hasNext()) {
/* 409 */         Object ps = this.propertySources.get(keys.next());
/* 410 */         if (ps.equals(propertySource))
/* 411 */           keys.remove(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(PropertyChangeListener listener) {
/* 426 */     getEventManager().addPropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 440 */     getEventManager().addPropertyChangeListener(propertyName, listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(PropertyChangeListener listener) {
/* 453 */     getEventManager().removePropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 465 */     getEventManager().removePropertyChangeListener(propertyName, listener);
/*     */   }
/*     */   
/*     */   private PropertyChangeSupportJAI getEventManager() {
/* 473 */     if (this.manager == null)
/* 474 */       synchronized (this) {
/* 475 */         this.manager = new PropertyChangeSupportJAI(this);
/*     */       }  
/* 478 */     return this.manager;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\WritablePropertySourceImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */