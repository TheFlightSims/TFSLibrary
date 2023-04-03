/*     */ package org.apache.commons.configuration.beanutils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.DynaBean;
/*     */ import org.apache.commons.beanutils.DynaClass;
/*     */ import org.apache.commons.beanutils.DynaProperty;
/*     */ import org.apache.commons.configuration.Configuration;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class ConfigurationDynaClass implements DynaClass {
/*  43 */   private static Log log = LogFactory.getLog(ConfigurationDynaClass.class);
/*     */   
/*     */   private Configuration configuration;
/*     */   
/*     */   public ConfigurationDynaClass(Configuration configuration) {
/*  56 */     if (log.isTraceEnabled())
/*  58 */       log.trace("ConfigurationDynaClass(" + configuration + ")"); 
/*  60 */     this.configuration = configuration;
/*     */   }
/*     */   
/*     */   public DynaProperty getDynaProperty(String name) {
/*  65 */     if (log.isTraceEnabled())
/*  67 */       log.trace("getDynaProperty(" + name + ")"); 
/*  70 */     if (name == null)
/*  72 */       throw new IllegalArgumentException("Property name must not be null!"); 
/*  75 */     Object value = this.configuration.getProperty(name);
/*  76 */     if (value == null)
/*  78 */       return null; 
/*  82 */     Class type = value.getClass();
/*  84 */     if (type == Byte.class)
/*  86 */       type = byte.class; 
/*  88 */     if (type == Character.class) {
/*  90 */       type = char.class;
/*  92 */     } else if (type == Boolean.class) {
/*  94 */       type = boolean.class;
/*  96 */     } else if (type == Double.class) {
/*  98 */       type = double.class;
/* 100 */     } else if (type == Float.class) {
/* 102 */       type = float.class;
/* 104 */     } else if (type == Integer.class) {
/* 106 */       type = int.class;
/* 108 */     } else if (type == Long.class) {
/* 110 */       type = long.class;
/* 112 */     } else if (type == Short.class) {
/* 114 */       type = short.class;
/*     */     } 
/* 117 */     return new DynaProperty(name, type);
/*     */   }
/*     */   
/*     */   public DynaProperty[] getDynaProperties() {
/* 123 */     if (log.isTraceEnabled())
/* 125 */       log.trace("getDynaProperties()"); 
/* 128 */     Iterator keys = this.configuration.getKeys();
/* 129 */     List properties = new ArrayList();
/* 130 */     while (keys.hasNext()) {
/* 132 */       String key = keys.next();
/* 133 */       DynaProperty property = getDynaProperty(key);
/* 134 */       properties.add(property);
/*     */     } 
/* 137 */     DynaProperty[] propertyArray = new DynaProperty[properties.size()];
/* 138 */     properties.toArray(propertyArray);
/* 139 */     if (log.isDebugEnabled())
/* 141 */       log.debug("Found " + properties.size() + " properties."); 
/* 144 */     return propertyArray;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 149 */     return ConfigurationDynaBean.class.getName();
/*     */   }
/*     */   
/*     */   public DynaBean newInstance() throws IllegalAccessException, InstantiationException {
/* 154 */     return new ConfigurationDynaBean(this.configuration);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\beanutils\ConfigurationDynaClass.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */