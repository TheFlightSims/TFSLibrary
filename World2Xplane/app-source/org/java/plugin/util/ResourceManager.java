/*     */ package org.java.plugin.util;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public final class ResourceManager {
/*  42 */   private static final Object FAKE_BUNDLE = new Object();
/*     */   
/*  43 */   private static final Map<String, Object> bundles = Collections.synchronizedMap(new HashMap<String, Object>());
/*     */   
/*     */   public static String getMessage(String packageName, String messageKey) {
/*  54 */     return getMessage(packageName, messageKey, Locale.getDefault(), null);
/*     */   }
/*     */   
/*     */   public static String getMessage(String packageName, String messageKey, Object data) {
/*  68 */     return getMessage(packageName, messageKey, Locale.getDefault(), data);
/*     */   }
/*     */   
/*     */   public static String getMessage(String packageName, String messageKey, Locale locale) {
/*  80 */     return getMessage(packageName, messageKey, locale, null);
/*     */   }
/*     */   
/*     */   public static String getMessage(String packageName, String messageKey, Locale locale, Object data) {
/*  95 */     Object obj = bundles.get(packageName + '|' + locale);
/*  96 */     if (obj == null) {
/*     */       try {
/*  98 */         obj = ResourceBundle.getBundle(packageName + ".Resources", locale);
/* 100 */       } catch (MissingResourceException mre) {
/* 101 */         obj = FAKE_BUNDLE;
/*     */       } 
/* 103 */       bundles.put(packageName + '|' + locale, obj);
/*     */     } 
/* 105 */     if (obj == FAKE_BUNDLE)
/* 106 */       return "resource " + packageName + '.' + messageKey + " not found for locale " + locale; 
/*     */     try {
/* 110 */       String result = ((ResourceBundle)obj).getString(messageKey);
/* 111 */       return (data == null) ? result : processParams(result, data);
/* 112 */     } catch (MissingResourceException mre) {
/* 113 */       return "resource " + packageName + '.' + messageKey + " not found for locale " + locale;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String processParams(String str, Object data) {
/* 119 */     String result = str;
/* 120 */     if (data != null && data.getClass().isArray()) {
/* 121 */       Object[] params = (Object[])data;
/* 122 */       for (int i = 0; i < params.length; i++)
/* 123 */         result = replaceAll(result, "{" + i + "}", "" + params[i]); 
/* 125 */     } else if (data instanceof java.util.Collection) {
/* 126 */       int i = 0;
/* 127 */       for (Object object : data)
/* 128 */         result = replaceAll(result, "{" + i++ + "}", "" + object); 
/*     */     } else {
/* 131 */       result = replaceAll(result, "{0}", "" + data);
/*     */     } 
/* 133 */     return result;
/*     */   }
/*     */   
/*     */   private static String replaceAll(String str, String from, String to) {
/* 138 */     String result = str;
/* 139 */     int p = 0;
/*     */     while (true) {
/* 141 */       p = result.indexOf(from, p);
/* 142 */       if (p == -1)
/*     */         break; 
/* 145 */       result = result.substring(0, p) + to + result.substring(p + from.length());
/* 147 */       p += to.length();
/*     */     } 
/* 149 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugi\\util\ResourceManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */