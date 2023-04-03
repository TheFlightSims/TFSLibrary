/*     */ package org.java.plugin.util;
/*     */ 
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class ExtendedProperties extends Properties {
/*     */   private static final long serialVersionUID = 8904709563073950956L;
/*     */   
/*     */   public ExtendedProperties() {}
/*     */   
/*     */   public ExtendedProperties(Properties defs) {
/*  42 */     super(defs);
/*     */   }
/*     */   
/*     */   public String getProperty(String key) {
/*  67 */     String result = super.getProperty(key);
/*  68 */     return (result == null) ? null : expandValue(result);
/*     */   }
/*     */   
/*     */   public String getProperty(String key, String defaultValue) {
/*  76 */     String result = getProperty(key);
/*  77 */     return (result == null) ? expandValue(defaultValue) : result;
/*     */   }
/*     */   
/*     */   public ExtendedProperties getSubset(String prefix) {
/*  86 */     return getSubset(prefix, "");
/*     */   }
/*     */   
/*     */   public ExtendedProperties getSubset(String prefix, String newPrefix) {
/*  97 */     ExtendedProperties result = new ExtendedProperties();
/*  98 */     for (Object object : keySet()) {
/*  99 */       String key = object.toString();
/* 100 */       if (!key.startsWith(prefix) || key.equals(prefix))
/*     */         continue; 
/* 103 */       result.put(key.substring(prefix.length()) + newPrefix, getProperty(key));
/*     */     } 
/* 106 */     return result;
/*     */   }
/*     */   
/*     */   private String expandValue(String value) {
/* 110 */     if (value == null || value.length() < 4)
/* 111 */       return value; 
/* 113 */     StringBuilder result = new StringBuilder(value.length());
/* 114 */     result.append(value);
/* 115 */     int p1 = result.indexOf("${");
/* 116 */     int p2 = result.indexOf("}", p1 + 2);
/* 117 */     while (p1 >= 0 && p2 > p1) {
/* 118 */       String paramName = result.substring(p1 + 2, p2);
/* 119 */       String paramValue = getProperty(paramName);
/* 120 */       if (paramValue != null) {
/* 121 */         result.replace(p1, p2 + 1, paramValue);
/* 122 */         p1 += paramValue.length();
/*     */       } else {
/* 124 */         p1 = p2 + 1;
/*     */       } 
/* 126 */       p1 = result.indexOf("${", p1);
/* 127 */       p2 = result.indexOf("}", p1 + 2);
/*     */     } 
/* 129 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugi\\util\ExtendedProperties.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */