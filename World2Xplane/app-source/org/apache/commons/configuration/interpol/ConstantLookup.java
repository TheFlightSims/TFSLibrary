/*     */ package org.apache.commons.configuration.interpol;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.ClassUtils;
/*     */ import org.apache.commons.lang.text.StrLookup;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class ConstantLookup extends StrLookup {
/*     */   private static final char FIELD_SEPRATOR = '.';
/*     */   
/*  61 */   private static Map constantCache = new HashMap();
/*     */   
/*  64 */   private Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   public String lookup(String var) {
/*     */     String result;
/*  80 */     if (var == null)
/*  82 */       return null; 
/*  86 */     synchronized (constantCache) {
/*  88 */       result = (String)constantCache.get(var);
/*     */     } 
/*  90 */     if (result != null)
/*  92 */       return result; 
/*  95 */     int fieldPos = var.lastIndexOf('.');
/*  96 */     if (fieldPos < 0)
/*  98 */       return null; 
/*     */     try {
/* 102 */       Object value = resolveField(var.substring(0, fieldPos), var.substring(fieldPos + 1));
/* 104 */       if (value != null) {
/* 106 */         synchronized (constantCache) {
/* 111 */           constantCache.put(var, String.valueOf(value));
/*     */         } 
/* 113 */         result = value.toString();
/*     */       } 
/* 116 */     } catch (Exception ex) {
/* 118 */       this.log.warn("Could not obtain value for variable " + var, ex);
/*     */     } 
/* 121 */     return result;
/*     */   }
/*     */   
/*     */   public static void clear() {
/* 129 */     synchronized (constantCache) {
/* 131 */       constantCache.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object resolveField(String className, String fieldName) throws Exception {
/* 150 */     Class clazz = fetchClass(className);
/* 151 */     Field field = clazz.getField(fieldName);
/* 152 */     return field.get(null);
/*     */   }
/*     */   
/*     */   protected Class fetchClass(String className) throws ClassNotFoundException {
/* 169 */     return ClassUtils.getClass(className);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\interpol\ConstantLookup.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */