/*     */ package org.apache.commons.configuration.beanutils;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.DynaBean;
/*     */ import org.apache.commons.beanutils.DynaClass;
/*     */ import org.apache.commons.configuration.Configuration;
/*     */ import org.apache.commons.configuration.ConfigurationMap;
/*     */ import org.apache.commons.configuration.SubsetConfiguration;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class ConfigurationDynaBean extends ConfigurationMap implements DynaBean {
/*     */   private static final String PROPERTY_DELIMITER = ".";
/*     */   
/*  63 */   private static Log log = LogFactory.getLog(ConfigurationDynaBean.class);
/*     */   
/*     */   public ConfigurationDynaBean(Configuration configuration) {
/*  73 */     super(configuration);
/*  74 */     if (log.isTraceEnabled())
/*  76 */       log.trace("ConfigurationDynaBean(" + configuration + ")"); 
/*     */   }
/*     */   
/*     */   public void set(String name, Object value) {
/*  82 */     if (log.isTraceEnabled())
/*  84 */       log.trace("set(" + name + "," + value + ")"); 
/*  87 */     if (value == null)
/*  89 */       throw new NullPointerException("Error trying to set property to null."); 
/*  92 */     if (value instanceof Collection) {
/*  94 */       Collection collection = (Collection)value;
/*  95 */       Iterator iterator = collection.iterator();
/*  96 */       while (iterator.hasNext())
/*  98 */         getConfiguration().addProperty(name, iterator.next()); 
/* 101 */     } else if (value.getClass().isArray()) {
/* 103 */       int length = Array.getLength(value);
/* 104 */       for (int i = 0; i < length; i++)
/* 106 */         getConfiguration().addProperty(name, Array.get(value, i)); 
/*     */     } else {
/* 111 */       getConfiguration().setProperty(name, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get(String name) {
/* 117 */     if (log.isTraceEnabled())
/* 119 */       log.trace("get(" + name + ")"); 
/* 123 */     Object result = getConfiguration().getProperty(name);
/* 124 */     if (result == null) {
/* 127 */       SubsetConfiguration subsetConfiguration = new SubsetConfiguration(getConfiguration(), name, ".");
/* 128 */       if (!subsetConfiguration.isEmpty())
/* 130 */         result = new ConfigurationDynaBean((Configuration)subsetConfiguration); 
/*     */     } 
/* 134 */     if (log.isDebugEnabled())
/* 136 */       log.debug(name + "=[" + result + "]"); 
/* 139 */     if (result == null)
/* 141 */       throw new IllegalArgumentException("Property '" + name + "' does not exist."); 
/* 143 */     return result;
/*     */   }
/*     */   
/*     */   public boolean contains(String name, String key) {
/* 148 */     Configuration subset = getConfiguration().subset(name);
/* 149 */     if (subset == null)
/* 151 */       throw new IllegalArgumentException("Mapped property '" + name + "' does not exist."); 
/* 154 */     return subset.containsKey(key);
/*     */   }
/*     */   
/*     */   public Object get(String name, int index) {
/* 159 */     if (!checkIndexedProperty(name))
/* 161 */       throw new IllegalArgumentException("Property '" + name + "' is not indexed."); 
/* 165 */     List list = getConfiguration().getList(name);
/* 166 */     return list.get(index);
/*     */   }
/*     */   
/*     */   public Object get(String name, String key) {
/* 171 */     Configuration subset = getConfiguration().subset(name);
/* 172 */     if (subset == null)
/* 174 */       throw new IllegalArgumentException("Mapped property '" + name + "' does not exist."); 
/* 177 */     return subset.getProperty(key);
/*     */   }
/*     */   
/*     */   public DynaClass getDynaClass() {
/* 182 */     return new ConfigurationDynaClass(getConfiguration());
/*     */   }
/*     */   
/*     */   public void remove(String name, String key) {
/* 187 */     SubsetConfiguration subsetConfiguration = new SubsetConfiguration(getConfiguration(), name, ".");
/* 188 */     subsetConfiguration.setProperty(key, null);
/*     */   }
/*     */   
/*     */   public void set(String name, int index, Object value) {
/* 193 */     if (!checkIndexedProperty(name) && index > 0)
/* 195 */       throw new IllegalArgumentException("Property '" + name + "' is not indexed."); 
/* 199 */     Object property = getConfiguration().getProperty(name);
/* 201 */     if (property instanceof List) {
/* 203 */       List list = (List)property;
/* 204 */       list.set(index, value);
/* 205 */       getConfiguration().setProperty(name, list);
/* 207 */     } else if (property.getClass().isArray()) {
/* 209 */       Array.set(property, index, value);
/* 211 */     } else if (index == 0) {
/* 213 */       getConfiguration().setProperty(name, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(String name, String key, Object value) {
/* 219 */     getConfiguration().setProperty(name + "." + key, value);
/*     */   }
/*     */   
/*     */   private boolean checkIndexedProperty(String name) {
/* 233 */     Object property = getConfiguration().getProperty(name);
/* 235 */     if (property == null)
/* 237 */       throw new IllegalArgumentException("Property '" + name + "' does not exist."); 
/* 241 */     return (property instanceof List || property.getClass().isArray());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\beanutils\ConfigurationDynaBean.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */