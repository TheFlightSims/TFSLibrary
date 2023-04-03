/*     */ package org.jfree.base.config;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.Properties;
/*     */ import java.util.TreeSet;
/*     */ import org.jfree.util.Configuration;
/*     */ 
/*     */ public class HierarchicalConfiguration implements ModifiableConfiguration {
/*  80 */   private Properties configuration = new Properties();
/*     */   
/*     */   private transient Configuration parentConfiguration;
/*     */   
/*     */   public HierarchicalConfiguration() {}
/*     */   
/*     */   public HierarchicalConfiguration(Configuration parentConfiguration) {
/*  89 */     this();
/*  90 */     this.parentConfiguration = parentConfiguration;
/*     */   }
/*     */   
/*     */   public String getConfigProperty(String key) {
/* 100 */     return getConfigProperty(key, null);
/*     */   }
/*     */   
/*     */   public String getConfigProperty(String key, String defaultValue) {
/* 115 */     String value = this.configuration.getProperty(key);
/* 116 */     if (value == null)
/* 117 */       if (isRootConfig()) {
/* 118 */         value = defaultValue;
/*     */       } else {
/* 121 */         value = this.parentConfiguration.getConfigProperty(key, defaultValue);
/*     */       }  
/* 124 */     return value;
/*     */   }
/*     */   
/*     */   public void setConfigProperty(String key, String value) {
/* 134 */     if (key == null)
/* 135 */       throw new NullPointerException(); 
/* 138 */     if (value == null) {
/* 139 */       this.configuration.remove(key);
/*     */     } else {
/* 142 */       this.configuration.setProperty(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isRootConfig() {
/* 152 */     return (this.parentConfiguration == null);
/*     */   }
/*     */   
/*     */   public boolean isLocallyDefined(String key) {
/* 163 */     return this.configuration.containsKey(key);
/*     */   }
/*     */   
/*     */   protected Properties getConfiguration() {
/* 172 */     return this.configuration;
/*     */   }
/*     */   
/*     */   public void insertConfiguration(HierarchicalConfiguration config) {
/* 182 */     config.setParentConfig(getParentConfig());
/* 183 */     setParentConfig(config);
/*     */   }
/*     */   
/*     */   protected void setParentConfig(Configuration config) {
/* 193 */     if (this.parentConfiguration == this)
/* 194 */       throw new IllegalArgumentException("Cannot add myself as parent configuration."); 
/* 196 */     this.parentConfiguration = config;
/*     */   }
/*     */   
/*     */   protected Configuration getParentConfig() {
/* 206 */     return this.parentConfiguration;
/*     */   }
/*     */   
/*     */   public Enumeration getConfigProperties() {
/* 217 */     return this.configuration.keys();
/*     */   }
/*     */   
/*     */   public Iterator findPropertyKeys(String prefix) {
/* 227 */     TreeSet keys = new TreeSet();
/* 228 */     collectPropertyKeys(prefix, this, keys);
/* 229 */     return Collections.unmodifiableSet(keys).iterator();
/*     */   }
/*     */   
/*     */   private void collectPropertyKeys(String prefix, Configuration config, TreeSet collector) {
/* 242 */     Enumeration enum1 = config.getConfigProperties();
/* 243 */     while (enum1.hasMoreElements()) {
/* 244 */       String key = enum1.nextElement();
/* 245 */       if (key.startsWith(prefix) && 
/* 246 */         !collector.contains(key))
/* 247 */         collector.add(key); 
/*     */     } 
/* 252 */     if (config instanceof HierarchicalConfiguration) {
/* 253 */       HierarchicalConfiguration hconfig = (HierarchicalConfiguration)config;
/* 254 */       if (hconfig.parentConfiguration != null)
/* 255 */         collectPropertyKeys(prefix, hconfig.parentConfiguration, collector); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isParentSaved() {
/* 267 */     return true;
/*     */   }
/*     */   
/*     */   protected void configurationLoaded() {}
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 285 */     out.defaultWriteObject();
/* 286 */     if (!isParentSaved()) {
/* 287 */       out.writeBoolean(false);
/*     */     } else {
/* 290 */       out.writeBoolean(true);
/* 291 */       out.writeObject(this.parentConfiguration);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 305 */     in.defaultReadObject();
/* 306 */     boolean readParent = in.readBoolean();
/* 307 */     if (readParent) {
/* 308 */       this.parentConfiguration = (ModifiableConfiguration)in.readObject();
/*     */     } else {
/* 311 */       this.parentConfiguration = null;
/*     */     } 
/* 313 */     configurationLoaded();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\config\HierarchicalConfiguration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */