/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.configuration.event.ConfigurationErrorListener;
/*     */ import org.apache.commons.configuration.event.ConfigurationListener;
/*     */ import org.apache.commons.configuration.interpol.ConfigurationInterpolator;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ import org.apache.commons.configuration.tree.ExpressionEngine;
/*     */ import org.apache.commons.configuration.tree.NodeCombiner;
/*     */ import org.apache.commons.lang.text.StrLookup;
/*     */ import org.apache.commons.lang.text.StrSubstitutor;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class DynamicCombinedConfiguration extends CombinedConfiguration {
/*  55 */   private static ThreadLocal recursive = new ThreadLocal() {
/*     */       protected synchronized Object initialValue() {
/*  59 */         return Boolean.FALSE;
/*     */       }
/*     */     };
/*     */   
/*  64 */   private Map configs = new HashMap();
/*     */   
/*  67 */   private List configurations = new ArrayList();
/*     */   
/*  70 */   private Map namedConfigurations = new HashMap();
/*     */   
/*     */   private String keyPattern;
/*     */   
/*     */   private NodeCombiner nodeCombiner;
/*     */   
/*  79 */   private String loggerName = DynamicCombinedConfiguration.class.getName();
/*     */   
/*  82 */   private StrSubstitutor localSubst = new StrSubstitutor((StrLookup)new ConfigurationInterpolator());
/*     */   
/*     */   public DynamicCombinedConfiguration(NodeCombiner comb) {
/*  94 */     setNodeCombiner(comb);
/*  95 */     setIgnoreReloadExceptions(false);
/*  96 */     setLogger(LogFactory.getLog(DynamicCombinedConfiguration.class));
/*     */   }
/*     */   
/*     */   public DynamicCombinedConfiguration() {
/* 108 */     setIgnoreReloadExceptions(false);
/* 109 */     setLogger(LogFactory.getLog(DynamicCombinedConfiguration.class));
/*     */   }
/*     */   
/*     */   public void setKeyPattern(String pattern) {
/* 114 */     this.keyPattern = pattern;
/*     */   }
/*     */   
/*     */   public String getKeyPattern() {
/* 119 */     return this.keyPattern;
/*     */   }
/*     */   
/*     */   public void setLoggerName(String name) {
/* 128 */     this.loggerName = name;
/*     */   }
/*     */   
/*     */   public NodeCombiner getNodeCombiner() {
/* 139 */     return this.nodeCombiner;
/*     */   }
/*     */   
/*     */   public void setNodeCombiner(NodeCombiner nodeCombiner) {
/* 153 */     if (nodeCombiner == null)
/* 155 */       throw new IllegalArgumentException("Node combiner must not be null!"); 
/* 158 */     this.nodeCombiner = nodeCombiner;
/* 159 */     invalidateAll();
/*     */   }
/*     */   
/*     */   public void addConfiguration(AbstractConfiguration config, String name, String at) {
/* 180 */     ConfigData cd = new ConfigData(config, name, at);
/* 181 */     this.configurations.add(cd);
/* 182 */     if (name != null)
/* 184 */       this.namedConfigurations.put(name, config); 
/*     */   }
/*     */   
/*     */   public int getNumberOfConfigurations() {
/* 195 */     return this.configurations.size();
/*     */   }
/*     */   
/*     */   public Configuration getConfiguration(int index) {
/* 208 */     ConfigData cd = this.configurations.get(index);
/* 209 */     return cd.getConfiguration();
/*     */   }
/*     */   
/*     */   public Configuration getConfiguration(String name) {
/* 221 */     return (Configuration)this.namedConfigurations.get(name);
/*     */   }
/*     */   
/*     */   public Set getConfigurationNames() {
/* 234 */     return this.namedConfigurations.keySet();
/*     */   }
/*     */   
/*     */   public Configuration removeConfiguration(String name) {
/* 246 */     Configuration conf = getConfiguration(name);
/* 247 */     if (conf != null)
/* 249 */       removeConfiguration(conf); 
/* 251 */     return conf;
/*     */   }
/*     */   
/*     */   public boolean removeConfiguration(Configuration config) {
/* 262 */     for (int index = 0; index < getNumberOfConfigurations(); index++) {
/* 264 */       if (((ConfigData)this.configurations.get(index)).getConfiguration() == config)
/* 266 */         removeConfigurationAt(index); 
/*     */     } 
/* 271 */     return super.removeConfiguration(config);
/*     */   }
/*     */   
/*     */   public Configuration removeConfigurationAt(int index) {
/* 282 */     ConfigData cd = this.configurations.remove(index);
/* 283 */     if (cd.getName() != null)
/* 285 */       this.namedConfigurations.remove(cd.getName()); 
/* 287 */     return super.removeConfigurationAt(index);
/*     */   }
/*     */   
/*     */   public ConfigurationNode getRootNode() {
/* 298 */     return getCurrentConfig().getRootNode();
/*     */   }
/*     */   
/*     */   public void setRootNode(ConfigurationNode rootNode) {
/* 303 */     if (this.configs != null) {
/* 305 */       getCurrentConfig().setRootNode(rootNode);
/*     */     } else {
/* 309 */       super.setRootNode(rootNode);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addProperty(String key, Object value) {
/* 315 */     getCurrentConfig().addProperty(key, value);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 320 */     if (this.configs != null)
/* 322 */       getCurrentConfig().clear(); 
/*     */   }
/*     */   
/*     */   public void clearProperty(String key) {
/* 328 */     getCurrentConfig().clearProperty(key);
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/* 333 */     return getCurrentConfig().containsKey(key);
/*     */   }
/*     */   
/*     */   public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
/* 338 */     return getCurrentConfig().getBigDecimal(key, defaultValue);
/*     */   }
/*     */   
/*     */   public BigDecimal getBigDecimal(String key) {
/* 343 */     return getCurrentConfig().getBigDecimal(key);
/*     */   }
/*     */   
/*     */   public BigInteger getBigInteger(String key, BigInteger defaultValue) {
/* 348 */     return getCurrentConfig().getBigInteger(key, defaultValue);
/*     */   }
/*     */   
/*     */   public BigInteger getBigInteger(String key) {
/* 353 */     return getCurrentConfig().getBigInteger(key);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String key, boolean defaultValue) {
/* 358 */     return getCurrentConfig().getBoolean(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Boolean getBoolean(String key, Boolean defaultValue) {
/* 363 */     return getCurrentConfig().getBoolean(key, defaultValue);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String key) {
/* 368 */     return getCurrentConfig().getBoolean(key);
/*     */   }
/*     */   
/*     */   public byte getByte(String key, byte defaultValue) {
/* 373 */     return getCurrentConfig().getByte(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Byte getByte(String key, Byte defaultValue) {
/* 378 */     return getCurrentConfig().getByte(key, defaultValue);
/*     */   }
/*     */   
/*     */   public byte getByte(String key) {
/* 383 */     return getCurrentConfig().getByte(key);
/*     */   }
/*     */   
/*     */   public double getDouble(String key, double defaultValue) {
/* 388 */     return getCurrentConfig().getDouble(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Double getDouble(String key, Double defaultValue) {
/* 393 */     return getCurrentConfig().getDouble(key, defaultValue);
/*     */   }
/*     */   
/*     */   public double getDouble(String key) {
/* 398 */     return getCurrentConfig().getDouble(key);
/*     */   }
/*     */   
/*     */   public float getFloat(String key, float defaultValue) {
/* 403 */     return getCurrentConfig().getFloat(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Float getFloat(String key, Float defaultValue) {
/* 408 */     return getCurrentConfig().getFloat(key, defaultValue);
/*     */   }
/*     */   
/*     */   public float getFloat(String key) {
/* 413 */     return getCurrentConfig().getFloat(key);
/*     */   }
/*     */   
/*     */   public int getInt(String key, int defaultValue) {
/* 418 */     return getCurrentConfig().getInt(key, defaultValue);
/*     */   }
/*     */   
/*     */   public int getInt(String key) {
/* 423 */     return getCurrentConfig().getInt(key);
/*     */   }
/*     */   
/*     */   public Integer getInteger(String key, Integer defaultValue) {
/* 428 */     return getCurrentConfig().getInteger(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Iterator getKeys() {
/* 433 */     return getCurrentConfig().getKeys();
/*     */   }
/*     */   
/*     */   public Iterator getKeys(String prefix) {
/* 438 */     return getCurrentConfig().getKeys(prefix);
/*     */   }
/*     */   
/*     */   public List getList(String key, List defaultValue) {
/* 443 */     return getCurrentConfig().getList(key, defaultValue);
/*     */   }
/*     */   
/*     */   public List getList(String key) {
/* 448 */     return getCurrentConfig().getList(key);
/*     */   }
/*     */   
/*     */   public long getLong(String key, long defaultValue) {
/* 453 */     return getCurrentConfig().getLong(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Long getLong(String key, Long defaultValue) {
/* 458 */     return getCurrentConfig().getLong(key, defaultValue);
/*     */   }
/*     */   
/*     */   public long getLong(String key) {
/* 463 */     return getCurrentConfig().getLong(key);
/*     */   }
/*     */   
/*     */   public Properties getProperties(String key) {
/* 468 */     return getCurrentConfig().getProperties(key);
/*     */   }
/*     */   
/*     */   public Object getProperty(String key) {
/* 473 */     return getCurrentConfig().getProperty(key);
/*     */   }
/*     */   
/*     */   public short getShort(String key, short defaultValue) {
/* 478 */     return getCurrentConfig().getShort(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Short getShort(String key, Short defaultValue) {
/* 483 */     return getCurrentConfig().getShort(key, defaultValue);
/*     */   }
/*     */   
/*     */   public short getShort(String key) {
/* 488 */     return getCurrentConfig().getShort(key);
/*     */   }
/*     */   
/*     */   public String getString(String key, String defaultValue) {
/* 493 */     return getCurrentConfig().getString(key, defaultValue);
/*     */   }
/*     */   
/*     */   public String getString(String key) {
/* 498 */     return getCurrentConfig().getString(key);
/*     */   }
/*     */   
/*     */   public String[] getStringArray(String key) {
/* 503 */     return getCurrentConfig().getStringArray(key);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 508 */     return getCurrentConfig().isEmpty();
/*     */   }
/*     */   
/*     */   public void setProperty(String key, Object value) {
/* 513 */     if (this.configs != null)
/* 515 */       getCurrentConfig().setProperty(key, value); 
/*     */   }
/*     */   
/*     */   public Configuration subset(String prefix) {
/* 521 */     return getCurrentConfig().subset(prefix);
/*     */   }
/*     */   
/*     */   public HierarchicalConfiguration.Node getRoot() {
/* 526 */     return getCurrentConfig().getRoot();
/*     */   }
/*     */   
/*     */   public void setRoot(HierarchicalConfiguration.Node node) {
/* 531 */     if (this.configs != null) {
/* 533 */       getCurrentConfig().setRoot(node);
/*     */     } else {
/* 537 */       super.setRoot(node);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ExpressionEngine getExpressionEngine() {
/* 543 */     return super.getExpressionEngine();
/*     */   }
/*     */   
/*     */   public void setExpressionEngine(ExpressionEngine expressionEngine) {
/* 548 */     super.setExpressionEngine(expressionEngine);
/*     */   }
/*     */   
/*     */   public void addNodes(String key, Collection nodes) {
/* 553 */     getCurrentConfig().addNodes(key, nodes);
/*     */   }
/*     */   
/*     */   public SubnodeConfiguration configurationAt(String key, boolean supportUpdates) {
/* 558 */     return getCurrentConfig().configurationAt(key, supportUpdates);
/*     */   }
/*     */   
/*     */   public SubnodeConfiguration configurationAt(String key) {
/* 563 */     return getCurrentConfig().configurationAt(key);
/*     */   }
/*     */   
/*     */   public List configurationsAt(String key) {
/* 568 */     return getCurrentConfig().configurationsAt(key);
/*     */   }
/*     */   
/*     */   public void clearTree(String key) {
/* 573 */     getCurrentConfig().clearTree(key);
/*     */   }
/*     */   
/*     */   public int getMaxIndex(String key) {
/* 578 */     return getCurrentConfig().getMaxIndex(key);
/*     */   }
/*     */   
/*     */   public Configuration interpolatedConfiguration() {
/* 583 */     return getCurrentConfig().interpolatedConfiguration();
/*     */   }
/*     */   
/*     */   public Configuration getSource(String key) {
/* 611 */     if (key == null)
/* 613 */       throw new IllegalArgumentException("Key must not be null!"); 
/* 615 */     return getCurrentConfig().getSource(key);
/*     */   }
/*     */   
/*     */   public void addConfigurationListener(ConfigurationListener l) {
/* 620 */     super.addConfigurationListener(l);
/* 622 */     Iterator iter = this.configs.values().iterator();
/* 623 */     while (iter.hasNext()) {
/* 625 */       CombinedConfiguration config = iter.next();
/* 626 */       config.addConfigurationListener(l);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean removeConfigurationListener(ConfigurationListener l) {
/* 632 */     Iterator iter = this.configs.values().iterator();
/* 633 */     while (iter.hasNext()) {
/* 635 */       CombinedConfiguration config = iter.next();
/* 636 */       config.removeConfigurationListener(l);
/*     */     } 
/* 638 */     return super.removeConfigurationListener(l);
/*     */   }
/*     */   
/*     */   public Collection getConfigurationListeners() {
/* 643 */     return super.getConfigurationListeners();
/*     */   }
/*     */   
/*     */   public void clearConfigurationListeners() {
/* 648 */     Iterator iter = this.configs.values().iterator();
/* 649 */     while (iter.hasNext()) {
/* 651 */       CombinedConfiguration config = iter.next();
/* 652 */       config.clearConfigurationListeners();
/*     */     } 
/* 654 */     super.clearConfigurationListeners();
/*     */   }
/*     */   
/*     */   public void addErrorListener(ConfigurationErrorListener l) {
/* 659 */     Iterator iter = this.configs.values().iterator();
/* 660 */     while (iter.hasNext()) {
/* 662 */       CombinedConfiguration config = iter.next();
/* 663 */       config.addErrorListener(l);
/*     */     } 
/* 665 */     super.addErrorListener(l);
/*     */   }
/*     */   
/*     */   public boolean removeErrorListener(ConfigurationErrorListener l) {
/* 670 */     Iterator iter = this.configs.values().iterator();
/* 671 */     while (iter.hasNext()) {
/* 673 */       CombinedConfiguration config = iter.next();
/* 674 */       config.removeErrorListener(l);
/*     */     } 
/* 676 */     return super.removeErrorListener(l);
/*     */   }
/*     */   
/*     */   public void clearErrorListeners() {
/* 681 */     Iterator iter = this.configs.values().iterator();
/* 682 */     while (iter.hasNext()) {
/* 684 */       CombinedConfiguration config = iter.next();
/* 685 */       config.clearErrorListeners();
/*     */     } 
/* 687 */     super.clearErrorListeners();
/*     */   }
/*     */   
/*     */   public Collection getErrorListeners() {
/* 692 */     return super.getErrorListeners();
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 708 */     return super.clone();
/*     */   }
/*     */   
/*     */   public void invalidate() {
/* 723 */     getCurrentConfig().invalidate();
/*     */   }
/*     */   
/*     */   public void invalidateAll() {
/* 728 */     if (this.configs == null)
/*     */       return; 
/* 732 */     Iterator iter = this.configs.values().iterator();
/* 733 */     while (iter.hasNext()) {
/* 735 */       CombinedConfiguration config = iter.next();
/* 736 */       config.invalidate();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object resolveContainerStore(String key) {
/* 747 */     if (((Boolean)recursive.get()).booleanValue())
/* 749 */       return null; 
/* 751 */     recursive.set(Boolean.TRUE);
/*     */     try {
/* 754 */       return super.resolveContainerStore(key);
/*     */     } finally {
/* 758 */       recursive.set(Boolean.FALSE);
/*     */     } 
/*     */   }
/*     */   
/*     */   private CombinedConfiguration getCurrentConfig() {
/*     */     CombinedConfiguration config;
/* 764 */     String key = this.localSubst.replace(this.keyPattern);
/* 766 */     synchronized (getNodeCombiner()) {
/* 768 */       config = (CombinedConfiguration)this.configs.get(key);
/* 769 */       if (config == null) {
/* 771 */         config = new CombinedConfiguration(getNodeCombiner());
/* 772 */         if (this.loggerName != null) {
/* 774 */           Log log = LogFactory.getLog(this.loggerName);
/* 775 */           if (log != null)
/* 777 */             config.setLogger(log); 
/*     */         } 
/* 780 */         config.setIgnoreReloadExceptions(isIgnoreReloadExceptions());
/* 781 */         config.setExpressionEngine(getExpressionEngine());
/* 782 */         config.setDelimiterParsingDisabled(isDelimiterParsingDisabled());
/* 783 */         config.setConversionExpressionEngine(getConversionExpressionEngine());
/* 784 */         config.setListDelimiter(getListDelimiter());
/* 785 */         Iterator iter = getErrorListeners().iterator();
/* 786 */         while (iter.hasNext()) {
/* 788 */           ConfigurationErrorListener listener = iter.next();
/* 789 */           config.addErrorListener(listener);
/*     */         } 
/* 791 */         iter = getConfigurationListeners().iterator();
/* 792 */         while (iter.hasNext()) {
/* 794 */           ConfigurationListener listener = (ConfigurationListener)iter.next();
/* 795 */           config.addConfigurationListener(listener);
/*     */         } 
/* 797 */         config.setForceReloadCheck(isForceReloadCheck());
/* 798 */         iter = this.configurations.iterator();
/* 799 */         while (iter.hasNext()) {
/* 801 */           ConfigData data = (ConfigData)iter.next();
/* 802 */           config.addConfiguration(data.getConfiguration(), data.getName(), data.getAt());
/*     */         } 
/* 805 */         this.configs.put(key, config);
/*     */       } 
/*     */     } 
/* 808 */     if (getLogger().isDebugEnabled())
/* 810 */       getLogger().debug("Returning config for " + key + ": " + config); 
/* 812 */     return config;
/*     */   }
/*     */   
/*     */   static class ConfigData {
/*     */     private AbstractConfiguration configuration;
/*     */     
/*     */     private String name;
/*     */     
/*     */     private String at;
/*     */     
/*     */     public ConfigData(AbstractConfiguration config, String n, String at) {
/* 839 */       this.configuration = config;
/* 840 */       this.name = n;
/* 841 */       this.at = at;
/*     */     }
/*     */     
/*     */     public AbstractConfiguration getConfiguration() {
/* 851 */       return this.configuration;
/*     */     }
/*     */     
/*     */     public String getName() {
/* 861 */       return this.name;
/*     */     }
/*     */     
/*     */     public String getAt() {
/* 871 */       return this.at;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\DynamicCombinedConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */