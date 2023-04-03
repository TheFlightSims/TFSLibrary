/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.map.LinkedMap;
/*     */ 
/*     */ public class BaseConfiguration extends AbstractConfiguration implements Cloneable {
/*  55 */   private Map store = (Map)new LinkedMap();
/*     */   
/*     */   protected void addPropertyDirect(String key, Object value) {
/*  66 */     Object previousValue = getProperty(key);
/*  68 */     if (previousValue == null) {
/*  70 */       this.store.put(key, value);
/*  72 */     } else if (previousValue instanceof List) {
/*  75 */       ((List)previousValue).add(value);
/*     */     } else {
/*  80 */       List list = new ArrayList();
/*  81 */       list.add(previousValue);
/*  82 */       list.add(value);
/*  84 */       this.store.put(key, list);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getProperty(String key) {
/*  97 */     return this.store.get(key);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 108 */     return this.store.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/* 121 */     return this.store.containsKey(key);
/*     */   }
/*     */   
/*     */   protected void clearPropertyDirect(String key) {
/* 131 */     if (containsKey(key))
/* 133 */       this.store.remove(key); 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 139 */     fireEvent(4, null, null, true);
/* 140 */     this.store.clear();
/* 141 */     fireEvent(4, null, null, false);
/*     */   }
/*     */   
/*     */   public Iterator getKeys() {
/* 152 */     return this.store.keySet().iterator();
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 167 */       BaseConfiguration copy = (BaseConfiguration)super.clone();
/* 168 */       copy.store = (Map)ConfigurationUtils.clone(this.store);
/* 171 */       for (Iterator it = this.store.entrySet().iterator(); it.hasNext(); ) {
/* 173 */         Map.Entry e = it.next();
/* 174 */         if (e.getValue() instanceof Collection)
/* 176 */           copy.store.put(e.getKey(), new ArrayList((Collection)e.getValue())); 
/*     */       } 
/* 181 */       return copy;
/* 183 */     } catch (CloneNotSupportedException cex) {
/* 186 */       throw new ConfigurationRuntimeException(cex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\BaseConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */