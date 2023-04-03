/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Vector;
/*     */ import org.apache.commons.collections.ExtendedProperties;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public final class ConfigurationConverter {
/*     */   public static Configuration getConfiguration(ExtendedProperties eprops) {
/*  54 */     return new MapConfiguration((Map)eprops);
/*     */   }
/*     */   
/*     */   public static Configuration getConfiguration(Properties props) {
/*  65 */     return new MapConfiguration(props);
/*     */   }
/*     */   
/*     */   public static ExtendedProperties getExtendedProperties(Configuration config) {
/*  76 */     ExtendedProperties props = new ExtendedProperties();
/*  78 */     Iterator keys = config.getKeys();
/*  80 */     while (keys.hasNext()) {
/*  82 */       String key = keys.next();
/*  83 */       Object property = config.getProperty(key);
/*  86 */       if (property instanceof List)
/*  88 */         property = new Vector((List)property); 
/*  91 */       props.setProperty(key, property);
/*     */     } 
/*  94 */     return props;
/*     */   }
/*     */   
/*     */   public static Properties getProperties(Configuration config) {
/* 107 */     Properties props = new Properties();
/* 109 */     char delimiter = (config instanceof AbstractConfiguration) ? ((AbstractConfiguration)config).getListDelimiter() : ',';
/* 112 */     Iterator keys = config.getKeys();
/* 113 */     while (keys.hasNext()) {
/* 115 */       String key = keys.next();
/* 116 */       List list = config.getList(key);
/* 119 */       props.setProperty(key, StringUtils.join(list.iterator(), delimiter));
/*     */     } 
/* 122 */     return props;
/*     */   }
/*     */   
/*     */   public static Map getMap(Configuration config) {
/* 133 */     return new ConfigurationMap(config);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\ConfigurationConverter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */