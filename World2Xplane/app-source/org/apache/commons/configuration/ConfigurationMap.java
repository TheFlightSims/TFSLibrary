/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ConfigurationMap extends AbstractMap {
/*     */   private Configuration configuration;
/*     */   
/*     */   public ConfigurationMap(Configuration configuration) {
/*  54 */     this.configuration = configuration;
/*     */   }
/*     */   
/*     */   public Configuration getConfiguration() {
/*  65 */     return this.configuration;
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/*  76 */     return new ConfigurationSet(this.configuration);
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/*  90 */     String strKey = String.valueOf(key);
/*  91 */     Object old = this.configuration.getProperty(strKey);
/*  92 */     this.configuration.setProperty(strKey, value);
/*  93 */     return old;
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 106 */     return this.configuration.getProperty(String.valueOf(key));
/*     */   }
/*     */   
/*     */   static class ConfigurationSet extends AbstractSet {
/*     */     private Configuration configuration;
/*     */     
/*     */     private final class Entry implements Map.Entry {
/*     */       private Object key;
/*     */       
/*     */       private final ConfigurationMap.ConfigurationSet this$0;
/*     */       
/*     */       private Entry(Object key) {
/* 127 */         this.key = key;
/*     */       }
/*     */       
/*     */       public Object getKey() {
/* 132 */         return this.key;
/*     */       }
/*     */       
/*     */       public Object getValue() {
/* 137 */         return ConfigurationMap.ConfigurationSet.this.configuration.getProperty((String)this.key);
/*     */       }
/*     */       
/*     */       public Object setValue(Object value) {
/* 142 */         Object old = getValue();
/* 143 */         ConfigurationMap.ConfigurationSet.this.configuration.setProperty((String)this.key, value);
/* 144 */         return old;
/*     */       }
/*     */     }
/*     */     
/*     */     private final class ConfigurationSetIterator implements Iterator {
/* 158 */       private Iterator keys = ConfigurationMap.ConfigurationSet.this.configuration.getKeys();
/*     */       
/*     */       private final ConfigurationMap.ConfigurationSet this$0;
/*     */       
/*     */       public boolean hasNext() {
/* 163 */         return this.keys.hasNext();
/*     */       }
/*     */       
/*     */       public Object next() {
/* 168 */         return new ConfigurationMap.ConfigurationSet.Entry(this.keys.next());
/*     */       }
/*     */       
/*     */       public void remove() {
/* 173 */         this.keys.remove();
/*     */       }
/*     */       
/*     */       private ConfigurationSetIterator() {}
/*     */     }
/*     */     
/*     */     ConfigurationSet(Configuration configuration) {
/* 179 */       this.configuration = configuration;
/*     */     }
/*     */     
/*     */     public int size() {
/* 188 */       int count = 0;
/* 189 */       for (Iterator iterator = this.configuration.getKeys(); iterator.hasNext(); ) {
/* 191 */         iterator.next();
/* 192 */         count++;
/*     */       } 
/* 194 */       return count;
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 202 */       return new ConfigurationSetIterator();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\ConfigurationMap.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */