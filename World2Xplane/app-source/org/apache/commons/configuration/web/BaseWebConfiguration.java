/*     */ package org.apache.commons.configuration.web;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.configuration.AbstractConfiguration;
/*     */ import org.apache.commons.configuration.PropertyConverter;
/*     */ 
/*     */ abstract class BaseWebConfiguration extends AbstractConfiguration {
/*     */   public boolean isEmpty() {
/*  49 */     return !getKeys().hasNext();
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/*  60 */     return (getProperty(key) != null);
/*     */   }
/*     */   
/*     */   public void clearProperty(String key) {
/*  73 */     throw new UnsupportedOperationException("Read only configuration");
/*     */   }
/*     */   
/*     */   protected void addPropertyDirect(String key, Object obj) {
/*  87 */     throw new UnsupportedOperationException("Read only configuration");
/*     */   }
/*     */   
/*     */   protected Object handleDelimiters(Object value) {
/* 100 */     if (!isDelimiterParsingDisabled() && value instanceof String) {
/* 102 */       List list = PropertyConverter.split((String)value, getListDelimiter());
/* 104 */       value = (list.size() > 1) ? list : list.get(0);
/*     */     } 
/* 107 */     return value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\web\BaseWebConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */