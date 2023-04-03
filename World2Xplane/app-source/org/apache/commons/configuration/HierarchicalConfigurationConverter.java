/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ abstract class HierarchicalConfigurationConverter {
/*     */   public void process(Configuration config) {
/*  58 */     if (config != null) {
/*  60 */       ConfigurationKey keyEmpty = new ConfigurationKey();
/*  61 */       ConfigurationKey keyLast = keyEmpty;
/*  62 */       Set keySet = new HashSet();
/*  64 */       for (Iterator it = config.getKeys(); it.hasNext(); ) {
/*  66 */         String key = it.next();
/*  67 */         if (keySet.contains(key))
/*     */           continue; 
/*  72 */         ConfigurationKey keyAct = new ConfigurationKey(key);
/*  73 */         closeElements(keyLast, keyAct);
/*  74 */         String elem = openElements(keyLast, keyAct, config, keySet);
/*  75 */         fireValue(elem, config.getProperty(key));
/*  76 */         keyLast = keyAct;
/*     */       } 
/*  80 */       closeElements(keyLast, keyEmpty);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void elementStart(String paramString, Object paramObject);
/*     */   
/*     */   protected abstract void elementEnd(String paramString);
/*     */   
/*     */   protected void closeElements(ConfigurationKey keyLast, ConfigurationKey keyAct) {
/* 117 */     ConfigurationKey keyDiff = keyAct.differenceKey(keyLast);
/* 118 */     Iterator it = reverseIterator(keyDiff);
/* 119 */     if (it.hasNext())
/* 122 */       it.next(); 
/* 125 */     while (it.hasNext())
/* 127 */       elementEnd(it.next()); 
/*     */   }
/*     */   
/*     */   protected Iterator reverseIterator(ConfigurationKey key) {
/* 141 */     List list = new ArrayList();
/* 142 */     for (ConfigurationKey.KeyIterator it = key.iterator(); it.hasNext();)
/* 144 */       list.add(it.nextKey()); 
/* 147 */     Collections.reverse(list);
/* 148 */     return list.iterator();
/*     */   }
/*     */   
/*     */   protected String openElements(ConfigurationKey keyLast, ConfigurationKey keyAct, Configuration config, Set keySet) {
/* 165 */     ConfigurationKey.KeyIterator it = keyLast.differenceKey(keyAct).iterator();
/* 166 */     ConfigurationKey k = keyLast.commonKey(keyAct);
/* 167 */     it.nextKey();
/* 167 */     for (; it.hasNext(); it.nextKey()) {
/* 169 */       k.append(it.currentKey(true));
/* 170 */       elementStart(it.currentKey(true), config.getProperty(k.toString()));
/* 171 */       keySet.add(k.toString());
/*     */     } 
/* 173 */     return it.currentKey(true);
/*     */   }
/*     */   
/*     */   protected void fireValue(String name, Object value) {
/* 187 */     if (value != null && value instanceof Collection) {
/* 189 */       for (Iterator it = ((Collection)value).iterator(); it.hasNext();)
/* 191 */         fireValue(name, it.next()); 
/*     */     } else {
/* 196 */       elementStart(name, value);
/* 197 */       elementEnd(name);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\HierarchicalConfigurationConverter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */