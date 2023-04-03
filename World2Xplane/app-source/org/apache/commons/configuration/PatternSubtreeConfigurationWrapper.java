/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.configuration.event.ConfigurationErrorListener;
/*     */ import org.apache.commons.configuration.event.ConfigurationListener;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ import org.apache.commons.configuration.tree.ExpressionEngine;
/*     */ 
/*     */ public class PatternSubtreeConfigurationWrapper extends AbstractHierarchicalFileConfiguration {
/*  48 */   private static ThreadLocal recursive = new ThreadLocal() {
/*     */       protected synchronized Object initialValue() {
/*  52 */         return Boolean.FALSE;
/*     */       }
/*     */     };
/*     */   
/*     */   private final AbstractHierarchicalFileConfiguration config;
/*     */   
/*     */   private final String path;
/*     */   
/*     */   private final boolean trailing;
/*     */   
/*     */   private boolean init;
/*     */   
/*     */   public PatternSubtreeConfigurationWrapper(AbstractHierarchicalFileConfiguration config, String path) {
/*  75 */     this.config = config;
/*  76 */     this.path = path;
/*  77 */     this.trailing = path.endsWith("/");
/*  78 */     this.init = true;
/*     */   }
/*     */   
/*     */   public Object getReloadLock() {
/*  83 */     return this.config.getReloadLock();
/*     */   }
/*     */   
/*     */   public void addProperty(String key, Object value) {
/*  88 */     this.config.addProperty(makePath(key), value);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  93 */     getConfig().clear();
/*     */   }
/*     */   
/*     */   public void clearProperty(String key) {
/*  98 */     this.config.clearProperty(makePath(key));
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/* 103 */     return this.config.containsKey(makePath(key));
/*     */   }
/*     */   
/*     */   public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
/* 108 */     return this.config.getBigDecimal(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public BigDecimal getBigDecimal(String key) {
/* 113 */     return this.config.getBigDecimal(makePath(key));
/*     */   }
/*     */   
/*     */   public BigInteger getBigInteger(String key, BigInteger defaultValue) {
/* 118 */     return this.config.getBigInteger(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public BigInteger getBigInteger(String key) {
/* 123 */     return this.config.getBigInteger(makePath(key));
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String key, boolean defaultValue) {
/* 128 */     return this.config.getBoolean(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public Boolean getBoolean(String key, Boolean defaultValue) {
/* 133 */     return this.config.getBoolean(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String key) {
/* 138 */     return this.config.getBoolean(makePath(key));
/*     */   }
/*     */   
/*     */   public byte getByte(String key, byte defaultValue) {
/* 143 */     return this.config.getByte(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public Byte getByte(String key, Byte defaultValue) {
/* 148 */     return this.config.getByte(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public byte getByte(String key) {
/* 153 */     return this.config.getByte(makePath(key));
/*     */   }
/*     */   
/*     */   public double getDouble(String key, double defaultValue) {
/* 158 */     return this.config.getDouble(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public Double getDouble(String key, Double defaultValue) {
/* 163 */     return this.config.getDouble(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public double getDouble(String key) {
/* 168 */     return this.config.getDouble(makePath(key));
/*     */   }
/*     */   
/*     */   public float getFloat(String key, float defaultValue) {
/* 173 */     return this.config.getFloat(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public Float getFloat(String key, Float defaultValue) {
/* 178 */     return this.config.getFloat(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public float getFloat(String key) {
/* 183 */     return this.config.getFloat(makePath(key));
/*     */   }
/*     */   
/*     */   public int getInt(String key, int defaultValue) {
/* 188 */     return this.config.getInt(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public int getInt(String key) {
/* 193 */     return this.config.getInt(makePath(key));
/*     */   }
/*     */   
/*     */   public Integer getInteger(String key, Integer defaultValue) {
/* 198 */     return this.config.getInteger(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public Iterator getKeys() {
/* 203 */     return this.config.getKeys(makePath());
/*     */   }
/*     */   
/*     */   public Iterator getKeys(String prefix) {
/* 208 */     return this.config.getKeys(makePath(prefix));
/*     */   }
/*     */   
/*     */   public List getList(String key, List defaultValue) {
/* 213 */     return this.config.getList(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public List getList(String key) {
/* 218 */     return this.config.getList(makePath(key));
/*     */   }
/*     */   
/*     */   public long getLong(String key, long defaultValue) {
/* 223 */     return this.config.getLong(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public Long getLong(String key, Long defaultValue) {
/* 228 */     return this.config.getLong(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public long getLong(String key) {
/* 233 */     return this.config.getLong(makePath(key));
/*     */   }
/*     */   
/*     */   public Properties getProperties(String key) {
/* 238 */     return this.config.getProperties(makePath(key));
/*     */   }
/*     */   
/*     */   public Object getProperty(String key) {
/* 243 */     return this.config.getProperty(makePath(key));
/*     */   }
/*     */   
/*     */   public short getShort(String key, short defaultValue) {
/* 248 */     return this.config.getShort(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public Short getShort(String key, Short defaultValue) {
/* 253 */     return this.config.getShort(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public short getShort(String key) {
/* 258 */     return this.config.getShort(makePath(key));
/*     */   }
/*     */   
/*     */   public String getString(String key, String defaultValue) {
/* 263 */     return this.config.getString(makePath(key), defaultValue);
/*     */   }
/*     */   
/*     */   public String getString(String key) {
/* 268 */     return this.config.getString(makePath(key));
/*     */   }
/*     */   
/*     */   public String[] getStringArray(String key) {
/* 273 */     return this.config.getStringArray(makePath(key));
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 278 */     return getConfig().isEmpty();
/*     */   }
/*     */   
/*     */   public void setProperty(String key, Object value) {
/* 283 */     getConfig().setProperty(key, value);
/*     */   }
/*     */   
/*     */   public Configuration subset(String prefix) {
/* 288 */     return getConfig().subset(prefix);
/*     */   }
/*     */   
/*     */   public HierarchicalConfiguration.Node getRoot() {
/* 293 */     return getConfig().getRoot();
/*     */   }
/*     */   
/*     */   public void setRoot(HierarchicalConfiguration.Node node) {
/* 298 */     if (this.init) {
/* 300 */       getConfig().setRoot(node);
/*     */     } else {
/* 304 */       super.setRoot(node);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ConfigurationNode getRootNode() {
/* 310 */     return getConfig().getRootNode();
/*     */   }
/*     */   
/*     */   public void setRootNode(ConfigurationNode rootNode) {
/* 315 */     if (this.init) {
/* 317 */       getConfig().setRootNode(rootNode);
/*     */     } else {
/* 321 */       super.setRootNode(rootNode);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ExpressionEngine getExpressionEngine() {
/* 327 */     return this.config.getExpressionEngine();
/*     */   }
/*     */   
/*     */   public void setExpressionEngine(ExpressionEngine expressionEngine) {
/* 332 */     if (this.init) {
/* 334 */       this.config.setExpressionEngine(expressionEngine);
/*     */     } else {
/* 338 */       super.setExpressionEngine(expressionEngine);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addNodes(String key, Collection nodes) {
/* 344 */     getConfig().addNodes(key, nodes);
/*     */   }
/*     */   
/*     */   public SubnodeConfiguration configurationAt(String key, boolean supportUpdates) {
/* 349 */     return this.config.configurationAt(makePath(key), supportUpdates);
/*     */   }
/*     */   
/*     */   public SubnodeConfiguration configurationAt(String key) {
/* 354 */     return this.config.configurationAt(makePath(key));
/*     */   }
/*     */   
/*     */   public List configurationsAt(String key) {
/* 359 */     return this.config.configurationsAt(makePath(key));
/*     */   }
/*     */   
/*     */   public void clearTree(String key) {
/* 364 */     this.config.clearTree(makePath(key));
/*     */   }
/*     */   
/*     */   public int getMaxIndex(String key) {
/* 369 */     return this.config.getMaxIndex(makePath(key));
/*     */   }
/*     */   
/*     */   public Configuration interpolatedConfiguration() {
/* 374 */     return getConfig().interpolatedConfiguration();
/*     */   }
/*     */   
/*     */   public void addConfigurationListener(ConfigurationListener l) {
/* 379 */     getConfig().addConfigurationListener(l);
/*     */   }
/*     */   
/*     */   public boolean removeConfigurationListener(ConfigurationListener l) {
/* 384 */     return getConfig().removeConfigurationListener(l);
/*     */   }
/*     */   
/*     */   public Collection getConfigurationListeners() {
/* 389 */     return getConfig().getConfigurationListeners();
/*     */   }
/*     */   
/*     */   public void clearConfigurationListeners() {
/* 394 */     getConfig().clearConfigurationListeners();
/*     */   }
/*     */   
/*     */   public void addErrorListener(ConfigurationErrorListener l) {
/* 399 */     getConfig().addErrorListener(l);
/*     */   }
/*     */   
/*     */   public boolean removeErrorListener(ConfigurationErrorListener l) {
/* 404 */     return getConfig().removeErrorListener(l);
/*     */   }
/*     */   
/*     */   public void clearErrorListeners() {
/* 409 */     getConfig().clearErrorListeners();
/*     */   }
/*     */   
/*     */   public void save(Writer writer) throws ConfigurationException {
/* 414 */     this.config.save(writer);
/*     */   }
/*     */   
/*     */   public void load(Reader reader) throws ConfigurationException {
/* 419 */     this.config.load(reader);
/*     */   }
/*     */   
/*     */   public Collection getErrorListeners() {
/* 424 */     return getConfig().getErrorListeners();
/*     */   }
/*     */   
/*     */   protected Object resolveContainerStore(String key) {
/* 429 */     if (((Boolean)recursive.get()).booleanValue())
/* 431 */       return null; 
/* 433 */     recursive.set(Boolean.TRUE);
/*     */     try {
/* 436 */       return super.resolveContainerStore(key);
/*     */     } finally {
/* 440 */       recursive.set(Boolean.FALSE);
/*     */     } 
/*     */   }
/*     */   
/*     */   private HierarchicalConfiguration getConfig() {
/* 446 */     return this.config.configurationAt(makePath());
/*     */   }
/*     */   
/*     */   private String makePath() {
/* 451 */     String pathPattern = this.trailing ? this.path.substring(0, this.path.length() - 1) : this.path;
/* 452 */     return getSubstitutor().replace(pathPattern);
/*     */   }
/*     */   
/*     */   private String makePath(String item) {
/*     */     String pathPattern;
/* 462 */     if ((item.length() == 0 || item.startsWith("/")) && this.trailing) {
/* 464 */       pathPattern = this.path.substring(0, this.path.length() - 1);
/* 466 */     } else if (!item.startsWith("/") || !this.trailing) {
/* 468 */       pathPattern = this.path + "/";
/*     */     } else {
/* 472 */       pathPattern = this.path;
/*     */     } 
/* 474 */     return getSubstitutor().replace(pathPattern) + item;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\PatternSubtreeConfigurationWrapper.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */