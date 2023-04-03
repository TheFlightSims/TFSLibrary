/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.PropertyUtil;
/*     */ import java.awt.Image;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.media.jai.util.CaselessStringKey;
/*     */ 
/*     */ public class PropertySourceImpl implements PropertySource, Serializable {
/*  76 */   protected transient Map properties = new Hashtable();
/*     */   
/*  77 */   protected transient Map propertySources = new Hashtable();
/*     */   
/*  78 */   protected Set cachedPropertyNames = Collections.synchronizedSet(new HashSet());
/*     */   
/*     */   protected PropertySourceImpl() {}
/*     */   
/*     */   public PropertySourceImpl(Map propertyMap, PropertySource propertySource) {
/* 109 */     this();
/* 113 */     if (propertyMap == null && propertySource == null) {
/* 114 */       boolean throwException = false;
/*     */       try {
/* 116 */         Class rootClass = Class.forName("javax.media.jai.PropertySourceImpl");
/* 118 */         throwException = getClass().equals(rootClass);
/* 119 */       } catch (Exception e) {}
/* 122 */       if (throwException)
/* 123 */         throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*     */     } 
/* 128 */     if (propertyMap != null) {
/* 129 */       Iterator keys = propertyMap.keySet().iterator();
/* 130 */       while (keys.hasNext()) {
/* 131 */         Object key = keys.next();
/* 132 */         if (key instanceof String) {
/* 133 */           this.properties.put(new CaselessStringKey((String)key), propertyMap.get(key));
/*     */           continue;
/*     */         } 
/* 135 */         if (key instanceof CaselessStringKey)
/* 136 */           this.properties.put(key, propertyMap.get(key)); 
/*     */       } 
/*     */     } 
/* 142 */     if (propertySource != null) {
/* 143 */       String[] names = propertySource.getPropertyNames();
/* 144 */       if (names != null) {
/* 145 */         int length = names.length;
/* 146 */         for (int i = 0; i < length; i++)
/* 147 */           this.propertySources.put(new CaselessStringKey(names[i]), propertySource); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 163 */     synchronized (this.properties) {
/* 164 */       if (this.properties.size() + this.propertySources.size() == 0)
/* 165 */         return null; 
/* 169 */       Set propertyNames = Collections.synchronizedSet(new HashSet(this.properties.keySet()));
/* 173 */       propertyNames.addAll(this.propertySources.keySet());
/* 176 */       int length = propertyNames.size();
/* 177 */       String[] names = new String[length];
/* 178 */       Iterator elements = propertyNames.iterator();
/* 179 */       int index = 0;
/* 180 */       while (elements.hasNext() && index < length)
/* 181 */         names[index++] = ((CaselessStringKey)elements.next()).getName(); 
/* 184 */       return names;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames(String prefix) {
/* 201 */     return PropertyUtil.getPropertyNames(getPropertyNames(), prefix);
/*     */   }
/*     */   
/*     */   public Class getPropertyClass(String propertyName) {
/* 226 */     if (propertyName == null)
/* 227 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 229 */     synchronized (this.properties) {
/* 230 */       Class propertyClass = null;
/* 231 */       Object value = this.properties.get(new CaselessStringKey(propertyName));
/* 232 */       if (value != null)
/* 233 */         propertyClass = value.getClass(); 
/* 235 */       return propertyClass;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getProperty(String propertyName) {
/* 262 */     if (propertyName == null)
/* 263 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 266 */     synchronized (this.properties) {
/* 267 */       CaselessStringKey key = new CaselessStringKey(propertyName);
/* 270 */       Object value = this.properties.get(key);
/* 272 */       if (value == null) {
/* 274 */         PropertySource propertySource = (PropertySource)this.propertySources.get(key);
/* 276 */         if (propertySource != null) {
/* 277 */           value = propertySource.getProperty(propertyName);
/* 278 */           if (value != Image.UndefinedProperty) {
/* 280 */             this.properties.put(key, value);
/* 281 */             this.cachedPropertyNames.add(key);
/*     */           } 
/*     */         } else {
/* 284 */           value = Image.UndefinedProperty;
/*     */         } 
/*     */       } 
/* 288 */       return value;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map getProperties() {
/* 306 */     if (this.properties.size() + this.propertySources.size() == 0)
/* 307 */       return null; 
/* 310 */     synchronized (this.properties) {
/* 311 */       Hashtable props = null;
/* 313 */       String[] propertyNames = getPropertyNames();
/* 314 */       if (propertyNames != null) {
/* 315 */         int length = propertyNames.length;
/* 316 */         props = new Hashtable(this.properties.size());
/* 317 */         for (int i = 0; i < length; i++) {
/* 318 */           String name = propertyNames[i];
/* 319 */           Object value = getProperty(name);
/* 320 */           props.put(name, value);
/*     */         } 
/*     */       } 
/* 324 */       return props;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void writeMap(ObjectOutputStream out, Map map) throws IOException {
/* 334 */     Hashtable table = new Hashtable();
/* 337 */     Iterator keys = map.keySet().iterator();
/* 338 */     while (keys.hasNext()) {
/* 339 */       Object key = keys.next();
/* 340 */       Object value = map.get(key);
/* 341 */       if (value instanceof Serializable)
/* 342 */         table.put(key, value); 
/*     */     } 
/* 347 */     out.writeObject(table);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 355 */     out.defaultWriteObject();
/* 357 */     synchronized (this.properties) {
/* 360 */       writeMap(out, this.properties);
/* 361 */       writeMap(out, this.propertySources);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 371 */     in.defaultReadObject();
/* 374 */     this.properties = (Map)in.readObject();
/* 375 */     this.propertySources = (Map)in.readObject();
/* 379 */     Iterator names = this.cachedPropertyNames.iterator();
/* 380 */     Set propertyNames = this.properties.keySet();
/* 381 */     while (names.hasNext()) {
/* 382 */       if (!propertyNames.contains(names.next()))
/* 383 */         names.remove(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PropertySourceImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */