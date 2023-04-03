/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MapConfiguration extends AbstractConfiguration implements Cloneable {
/*     */   protected Map map;
/*     */   
/*     */   private boolean trimmingDisabled;
/*     */   
/*     */   public MapConfiguration(Map map) {
/*  97 */     this.map = map;
/*     */   }
/*     */   
/*     */   public Map getMap() {
/* 107 */     return this.map;
/*     */   }
/*     */   
/*     */   public boolean isTrimmingDisabled() {
/* 119 */     return this.trimmingDisabled;
/*     */   }
/*     */   
/*     */   public void setTrimmingDisabled(boolean trimmingDisabled) {
/* 133 */     this.trimmingDisabled = trimmingDisabled;
/*     */   }
/*     */   
/*     */   public Object getProperty(String key) {
/* 138 */     Object value = this.map.get(key);
/* 139 */     if (value instanceof String && !isDelimiterParsingDisabled()) {
/* 141 */       List list = PropertyConverter.split((String)value, getListDelimiter(), !isTrimmingDisabled());
/* 142 */       return (list.size() > 1) ? list : list.get(0);
/*     */     } 
/* 146 */     return value;
/*     */   }
/*     */   
/*     */   protected void addPropertyDirect(String key, Object value) {
/* 152 */     Object previousValue = getProperty(key);
/* 154 */     if (previousValue == null) {
/* 156 */       this.map.put(key, value);
/* 158 */     } else if (previousValue instanceof List) {
/* 161 */       ((List)previousValue).add(value);
/*     */     } else {
/* 166 */       List list = new ArrayList();
/* 167 */       list.add(previousValue);
/* 168 */       list.add(value);
/* 170 */       this.map.put(key, list);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 176 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/* 181 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */   protected void clearPropertyDirect(String key) {
/* 186 */     this.map.remove(key);
/*     */   }
/*     */   
/*     */   public Iterator getKeys() {
/* 191 */     return this.map.keySet().iterator();
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 205 */       MapConfiguration copy = (MapConfiguration)super.clone();
/* 206 */       copy.clearConfigurationListeners();
/* 207 */       copy.map = (Map)ConfigurationUtils.clone(this.map);
/* 208 */       return copy;
/* 210 */     } catch (CloneNotSupportedException cex) {
/* 213 */       throw new ConfigurationRuntimeException(cex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\MapConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */