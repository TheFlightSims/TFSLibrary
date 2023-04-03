/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.Properties;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public class DefaultConfiguration extends Properties implements Configuration {
/*     */   public String getConfigProperty(String key) {
/*  74 */     return getProperty(key);
/*     */   }
/*     */   
/*     */   public String getConfigProperty(String key, String defaultValue) {
/*  90 */     return getProperty(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Iterator findPropertyKeys(String prefix) {
/* 100 */     TreeSet collector = new TreeSet();
/* 101 */     Enumeration enum1 = keys();
/* 102 */     while (enum1.hasMoreElements()) {
/* 103 */       String key = (String)enum1.nextElement();
/* 104 */       if (key.startsWith(prefix) && 
/* 105 */         !collector.contains(key))
/* 106 */         collector.add(key); 
/*     */     } 
/* 110 */     return Collections.<String>unmodifiableSet(collector).iterator();
/*     */   }
/*     */   
/*     */   public Enumeration getConfigProperties() {
/* 115 */     return keys();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\DefaultConfiguration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */