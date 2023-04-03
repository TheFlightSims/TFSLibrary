/*     */ package org.jfree.base.config;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class SystemPropertyConfiguration extends HierarchicalConfiguration {
/*     */   public void setConfigProperty(String key, String value) {
/*  71 */     throw new UnsupportedOperationException("The SystemPropertyConfiguration is readOnly");
/*     */   }
/*     */   
/*     */   public String getConfigProperty(String key, String defaultValue) {
/*     */     try {
/*  88 */       String value = System.getProperty(key);
/*  89 */       if (value != null)
/*  90 */         return value; 
/*  93 */     } catch (SecurityException se) {}
/*  96 */     return super.getConfigProperty(key, defaultValue);
/*     */   }
/*     */   
/*     */   public boolean isLocallyDefined(String key) {
/*     */     try {
/* 108 */       return System.getProperties().containsKey(key);
/* 110 */     } catch (SecurityException se) {
/* 111 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Enumeration getConfigProperties() {
/*     */     try {
/* 124 */       return System.getProperties().keys();
/* 126 */     } catch (SecurityException se) {
/* 128 */       return (new Vector()).elements();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\config\SystemPropertyConfiguration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */