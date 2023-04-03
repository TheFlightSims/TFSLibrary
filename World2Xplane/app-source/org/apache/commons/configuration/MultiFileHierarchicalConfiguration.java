/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.commons.configuration.event.ConfigurationErrorEvent;
/*     */ import org.apache.commons.configuration.event.ConfigurationErrorListener;
/*     */ import org.apache.commons.configuration.event.ConfigurationEvent;
/*     */ import org.apache.commons.configuration.event.ConfigurationListener;
/*     */ import org.apache.commons.configuration.interpol.ConfigurationInterpolator;
/*     */ import org.apache.commons.configuration.reloading.ReloadingStrategy;
/*     */ import org.apache.commons.configuration.resolver.EntityResolverSupport;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ import org.apache.commons.configuration.tree.ExpressionEngine;
/*     */ import org.apache.commons.lang.text.StrLookup;
/*     */ import org.apache.commons.lang.text.StrSubstitutor;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.xml.sax.EntityResolver;
/*     */ 
/*     */ public class MultiFileHierarchicalConfiguration extends AbstractHierarchicalFileConfiguration implements ConfigurationListener, ConfigurationErrorListener, EntityResolverSupport {
/*  68 */   private static ThreadLocal recursive = new ThreadLocal() {
/*     */       protected synchronized Object initialValue() {
/*  72 */         return Boolean.FALSE;
/*     */       }
/*     */     };
/*     */   
/*  77 */   private final Map configurationsMap = new HashMap();
/*     */   
/*     */   private String pattern;
/*     */   
/*     */   private boolean init;
/*     */   
/*     */   private boolean ignoreException = true;
/*     */   
/*     */   private boolean schemaValidation;
/*     */   
/*     */   private boolean validating;
/*     */   
/*     */   private boolean attributeSplittingDisabled;
/*     */   
/*  98 */   private String loggerName = MultiFileHierarchicalConfiguration.class.getName();
/*     */   
/*     */   private ReloadingStrategy fileStrategy;
/*     */   
/*     */   private EntityResolver entityResolver;
/*     */   
/* 107 */   private StrSubstitutor localSubst = new StrSubstitutor((StrLookup)new ConfigurationInterpolator());
/*     */   
/*     */   public MultiFileHierarchicalConfiguration() {
/* 115 */     this.init = true;
/* 116 */     setLogger(LogFactory.getLog(this.loggerName));
/*     */   }
/*     */   
/*     */   public MultiFileHierarchicalConfiguration(String pathPattern) {
/* 126 */     this.pattern = pathPattern;
/* 127 */     this.init = true;
/* 128 */     setLogger(LogFactory.getLog(this.loggerName));
/*     */   }
/*     */   
/*     */   public void setLoggerName(String name) {
/* 133 */     this.loggerName = name;
/*     */   }
/*     */   
/*     */   public void setFilePattern(String pathPattern) {
/* 142 */     this.pattern = pathPattern;
/*     */   }
/*     */   
/*     */   public boolean isSchemaValidation() {
/* 147 */     return this.schemaValidation;
/*     */   }
/*     */   
/*     */   public void setSchemaValidation(boolean schemaValidation) {
/* 152 */     this.schemaValidation = schemaValidation;
/*     */   }
/*     */   
/*     */   public boolean isValidating() {
/* 157 */     return this.validating;
/*     */   }
/*     */   
/*     */   public void setValidating(boolean validating) {
/* 162 */     this.validating = validating;
/*     */   }
/*     */   
/*     */   public boolean isAttributeSplittingDisabled() {
/* 167 */     return this.attributeSplittingDisabled;
/*     */   }
/*     */   
/*     */   public void setAttributeSplittingDisabled(boolean attributeSplittingDisabled) {
/* 172 */     this.attributeSplittingDisabled = attributeSplittingDisabled;
/*     */   }
/*     */   
/*     */   public ReloadingStrategy getReloadingStrategy() {
/* 177 */     return this.fileStrategy;
/*     */   }
/*     */   
/*     */   public void setReloadingStrategy(ReloadingStrategy strategy) {
/* 182 */     this.fileStrategy = strategy;
/*     */   }
/*     */   
/*     */   public void setEntityResolver(EntityResolver entityResolver) {
/* 187 */     this.entityResolver = entityResolver;
/*     */   }
/*     */   
/*     */   public EntityResolver getEntityResolver() {
/* 192 */     return this.entityResolver;
/*     */   }
/*     */   
/*     */   public void setIgnoreException(boolean ignoreException) {
/* 202 */     this.ignoreException = ignoreException;
/*     */   }
/*     */   
/*     */   public void addProperty(String key, Object value) {
/* 207 */     getConfiguration().addProperty(key, value);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 212 */     getConfiguration().clear();
/*     */   }
/*     */   
/*     */   public void clearProperty(String key) {
/* 217 */     getConfiguration().clearProperty(key);
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/* 222 */     return getConfiguration().containsKey(key);
/*     */   }
/*     */   
/*     */   public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
/* 227 */     return getConfiguration().getBigDecimal(key, defaultValue);
/*     */   }
/*     */   
/*     */   public BigDecimal getBigDecimal(String key) {
/* 232 */     return getConfiguration().getBigDecimal(key);
/*     */   }
/*     */   
/*     */   public BigInteger getBigInteger(String key, BigInteger defaultValue) {
/* 237 */     return getConfiguration().getBigInteger(key, defaultValue);
/*     */   }
/*     */   
/*     */   public BigInteger getBigInteger(String key) {
/* 242 */     return getConfiguration().getBigInteger(key);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String key, boolean defaultValue) {
/* 247 */     return getConfiguration().getBoolean(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Boolean getBoolean(String key, Boolean defaultValue) {
/* 252 */     return getConfiguration().getBoolean(key, defaultValue);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String key) {
/* 257 */     return getConfiguration().getBoolean(key);
/*     */   }
/*     */   
/*     */   public byte getByte(String key, byte defaultValue) {
/* 262 */     return getConfiguration().getByte(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Byte getByte(String key, Byte defaultValue) {
/* 267 */     return getConfiguration().getByte(key, defaultValue);
/*     */   }
/*     */   
/*     */   public byte getByte(String key) {
/* 272 */     return getConfiguration().getByte(key);
/*     */   }
/*     */   
/*     */   public double getDouble(String key, double defaultValue) {
/* 277 */     return getConfiguration().getDouble(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Double getDouble(String key, Double defaultValue) {
/* 282 */     return getConfiguration().getDouble(key, defaultValue);
/*     */   }
/*     */   
/*     */   public double getDouble(String key) {
/* 287 */     return getConfiguration().getDouble(key);
/*     */   }
/*     */   
/*     */   public float getFloat(String key, float defaultValue) {
/* 292 */     return getConfiguration().getFloat(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Float getFloat(String key, Float defaultValue) {
/* 297 */     return getConfiguration().getFloat(key, defaultValue);
/*     */   }
/*     */   
/*     */   public float getFloat(String key) {
/* 302 */     return getConfiguration().getFloat(key);
/*     */   }
/*     */   
/*     */   public int getInt(String key, int defaultValue) {
/* 307 */     return getConfiguration().getInt(key, defaultValue);
/*     */   }
/*     */   
/*     */   public int getInt(String key) {
/* 312 */     return getConfiguration().getInt(key);
/*     */   }
/*     */   
/*     */   public Integer getInteger(String key, Integer defaultValue) {
/* 317 */     return getConfiguration().getInteger(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Iterator getKeys() {
/* 322 */     return getConfiguration().getKeys();
/*     */   }
/*     */   
/*     */   public Iterator getKeys(String prefix) {
/* 327 */     return getConfiguration().getKeys(prefix);
/*     */   }
/*     */   
/*     */   public List getList(String key, List defaultValue) {
/* 332 */     return getConfiguration().getList(key, defaultValue);
/*     */   }
/*     */   
/*     */   public List getList(String key) {
/* 337 */     return getConfiguration().getList(key);
/*     */   }
/*     */   
/*     */   public long getLong(String key, long defaultValue) {
/* 342 */     return getConfiguration().getLong(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Long getLong(String key, Long defaultValue) {
/* 347 */     return getConfiguration().getLong(key, defaultValue);
/*     */   }
/*     */   
/*     */   public long getLong(String key) {
/* 352 */     return getConfiguration().getLong(key);
/*     */   }
/*     */   
/*     */   public Properties getProperties(String key) {
/* 357 */     return getConfiguration().getProperties(key);
/*     */   }
/*     */   
/*     */   public Object getProperty(String key) {
/* 362 */     return getConfiguration().getProperty(key);
/*     */   }
/*     */   
/*     */   public short getShort(String key, short defaultValue) {
/* 367 */     return getConfiguration().getShort(key, defaultValue);
/*     */   }
/*     */   
/*     */   public Short getShort(String key, Short defaultValue) {
/* 372 */     return getConfiguration().getShort(key, defaultValue);
/*     */   }
/*     */   
/*     */   public short getShort(String key) {
/* 377 */     return getConfiguration().getShort(key);
/*     */   }
/*     */   
/*     */   public String getString(String key, String defaultValue) {
/* 382 */     return getConfiguration().getString(key, defaultValue);
/*     */   }
/*     */   
/*     */   public String getString(String key) {
/* 387 */     return getConfiguration().getString(key);
/*     */   }
/*     */   
/*     */   public String[] getStringArray(String key) {
/* 392 */     return getConfiguration().getStringArray(key);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 397 */     return getConfiguration().isEmpty();
/*     */   }
/*     */   
/*     */   public void setProperty(String key, Object value) {
/* 402 */     if (this.init)
/* 404 */       getConfiguration().setProperty(key, value); 
/*     */   }
/*     */   
/*     */   public Configuration subset(String prefix) {
/* 410 */     return getConfiguration().subset(prefix);
/*     */   }
/*     */   
/*     */   public Object getReloadLock() {
/* 415 */     return getConfiguration().getReloadLock();
/*     */   }
/*     */   
/*     */   public HierarchicalConfiguration.Node getRoot() {
/* 420 */     return getConfiguration().getRoot();
/*     */   }
/*     */   
/*     */   public void setRoot(HierarchicalConfiguration.Node node) {
/* 425 */     if (this.init) {
/* 427 */       getConfiguration().setRoot(node);
/*     */     } else {
/* 431 */       super.setRoot(node);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ConfigurationNode getRootNode() {
/* 437 */     return getConfiguration().getRootNode();
/*     */   }
/*     */   
/*     */   public void setRootNode(ConfigurationNode rootNode) {
/* 442 */     if (this.init) {
/* 444 */       getConfiguration().setRootNode(rootNode);
/*     */     } else {
/* 448 */       super.setRootNode(rootNode);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ExpressionEngine getExpressionEngine() {
/* 454 */     return super.getExpressionEngine();
/*     */   }
/*     */   
/*     */   public void setExpressionEngine(ExpressionEngine expressionEngine) {
/* 459 */     super.setExpressionEngine(expressionEngine);
/*     */   }
/*     */   
/*     */   public void addNodes(String key, Collection nodes) {
/* 464 */     getConfiguration().addNodes(key, nodes);
/*     */   }
/*     */   
/*     */   public SubnodeConfiguration configurationAt(String key, boolean supportUpdates) {
/* 469 */     return getConfiguration().configurationAt(key, supportUpdates);
/*     */   }
/*     */   
/*     */   public SubnodeConfiguration configurationAt(String key) {
/* 474 */     return getConfiguration().configurationAt(key);
/*     */   }
/*     */   
/*     */   public List configurationsAt(String key) {
/* 479 */     return getConfiguration().configurationsAt(key);
/*     */   }
/*     */   
/*     */   public void clearTree(String key) {
/* 484 */     getConfiguration().clearTree(key);
/*     */   }
/*     */   
/*     */   public int getMaxIndex(String key) {
/* 489 */     return getConfiguration().getMaxIndex(key);
/*     */   }
/*     */   
/*     */   public Configuration interpolatedConfiguration() {
/* 494 */     return getConfiguration().interpolatedConfiguration();
/*     */   }
/*     */   
/*     */   public void addConfigurationListener(ConfigurationListener l) {
/* 499 */     super.addConfigurationListener(l);
/*     */   }
/*     */   
/*     */   public boolean removeConfigurationListener(ConfigurationListener l) {
/* 504 */     return super.removeConfigurationListener(l);
/*     */   }
/*     */   
/*     */   public Collection getConfigurationListeners() {
/* 509 */     return super.getConfigurationListeners();
/*     */   }
/*     */   
/*     */   public void clearConfigurationListeners() {
/* 514 */     super.clearConfigurationListeners();
/*     */   }
/*     */   
/*     */   public void addErrorListener(ConfigurationErrorListener l) {
/* 519 */     super.addErrorListener(l);
/*     */   }
/*     */   
/*     */   public boolean removeErrorListener(ConfigurationErrorListener l) {
/* 524 */     return super.removeErrorListener(l);
/*     */   }
/*     */   
/*     */   public void clearErrorListeners() {
/* 529 */     super.clearErrorListeners();
/*     */   }
/*     */   
/*     */   public Collection getErrorListeners() {
/* 534 */     return super.getErrorListeners();
/*     */   }
/*     */   
/*     */   public void save(Writer writer) throws ConfigurationException {
/* 539 */     if (this.init)
/* 541 */       getConfiguration().save(writer); 
/*     */   }
/*     */   
/*     */   public void load(Reader reader) throws ConfigurationException {
/* 547 */     if (this.init)
/* 549 */       getConfiguration().load(reader); 
/*     */   }
/*     */   
/*     */   public void load() throws ConfigurationException {
/* 555 */     getConfiguration();
/*     */   }
/*     */   
/*     */   public void load(String fileName) throws ConfigurationException {
/* 560 */     getConfiguration().load(fileName);
/*     */   }
/*     */   
/*     */   public void load(File file) throws ConfigurationException {
/* 565 */     getConfiguration().load(file);
/*     */   }
/*     */   
/*     */   public void load(URL url) throws ConfigurationException {
/* 570 */     getConfiguration().load(url);
/*     */   }
/*     */   
/*     */   public void load(InputStream in) throws ConfigurationException {
/* 575 */     getConfiguration().load(in);
/*     */   }
/*     */   
/*     */   public void load(InputStream in, String encoding) throws ConfigurationException {
/* 580 */     getConfiguration().load(in, encoding);
/*     */   }
/*     */   
/*     */   public void save() throws ConfigurationException {
/* 585 */     getConfiguration().save();
/*     */   }
/*     */   
/*     */   public void save(String fileName) throws ConfigurationException {
/* 590 */     getConfiguration().save(fileName);
/*     */   }
/*     */   
/*     */   public void save(File file) throws ConfigurationException {
/* 595 */     getConfiguration().save(file);
/*     */   }
/*     */   
/*     */   public void save(URL url) throws ConfigurationException {
/* 600 */     getConfiguration().save(url);
/*     */   }
/*     */   
/*     */   public void save(OutputStream out) throws ConfigurationException {
/* 605 */     getConfiguration().save(out);
/*     */   }
/*     */   
/*     */   public void save(OutputStream out, String encoding) throws ConfigurationException {
/* 610 */     getConfiguration().save(out, encoding);
/*     */   }
/*     */   
/*     */   public void configurationChanged(ConfigurationEvent event) {
/* 615 */     if (event.getSource() instanceof XMLConfiguration) {
/* 617 */       Iterator iter = getConfigurationListeners().iterator();
/* 618 */       while (iter.hasNext()) {
/* 620 */         ConfigurationListener listener = iter.next();
/* 621 */         listener.configurationChanged(event);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void configurationError(ConfigurationErrorEvent event) {
/* 628 */     if (event.getSource() instanceof XMLConfiguration) {
/* 630 */       Iterator iter = getErrorListeners().iterator();
/* 631 */       while (iter.hasNext()) {
/* 633 */         ConfigurationErrorListener listener = iter.next();
/* 634 */         listener.configurationError(event);
/*     */       } 
/*     */     } 
/* 638 */     if (event.getType() == 20)
/* 640 */       if (isThrowable(event.getCause()))
/* 642 */         throw new ConfigurationRuntimeException(event.getCause());  
/*     */   }
/*     */   
/*     */   protected Object resolveContainerStore(String key) {
/* 654 */     if (((Boolean)recursive.get()).booleanValue())
/* 656 */       return null; 
/* 658 */     recursive.set(Boolean.TRUE);
/*     */     try {
/* 661 */       return super.resolveContainerStore(key);
/*     */     } finally {
/* 665 */       recursive.set(Boolean.FALSE);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeConfiguration() {
/* 674 */     String path = getSubstitutor().replace(this.pattern);
/* 675 */     synchronized (this.configurationsMap) {
/* 677 */       this.configurationsMap.remove(path);
/*     */     } 
/*     */   }
/*     */   
/*     */   private AbstractHierarchicalFileConfiguration getConfiguration() {
/* 689 */     if (this.pattern == null)
/* 691 */       throw new ConfigurationRuntimeException("File pattern must be defined"); 
/* 693 */     String path = this.localSubst.replace(this.pattern);
/* 694 */     synchronized (this.configurationsMap) {
/* 696 */       if (this.configurationsMap.containsKey(path))
/* 698 */         return (AbstractHierarchicalFileConfiguration)this.configurationsMap.get(path); 
/*     */     } 
/* 702 */     if (path.equals(this.pattern)) {
/* 704 */       XMLConfiguration xMLConfiguration = new XMLConfiguration() {
/*     */           private final MultiFileHierarchicalConfiguration this$0;
/*     */           
/*     */           public void load() throws ConfigurationException {}
/*     */           
/*     */           public void save() throws ConfigurationException {}
/*     */         };
/* 713 */       synchronized (this.configurationsMap) {
/* 715 */         this.configurationsMap.put(this.pattern, xMLConfiguration);
/*     */       } 
/* 717 */       return xMLConfiguration;
/*     */     } 
/* 720 */     XMLConfiguration configuration = new XMLConfiguration();
/* 722 */     if (this.loggerName != null) {
/* 724 */       Log log = LogFactory.getLog(this.loggerName);
/* 725 */       if (log != null)
/* 727 */         configuration.setLogger(log); 
/*     */     } 
/* 730 */     configuration.setBasePath(getBasePath());
/* 731 */     configuration.setFileName(path);
/* 732 */     configuration.setFileSystem(getFileSystem());
/* 733 */     configuration.setExpressionEngine(getExpressionEngine());
/* 734 */     ReloadingStrategy strategy = createReloadingStrategy();
/* 735 */     if (strategy != null)
/* 737 */       configuration.setReloadingStrategy(strategy); 
/* 739 */     configuration.setDelimiterParsingDisabled(isDelimiterParsingDisabled());
/* 740 */     configuration.setValidating(this.validating);
/* 741 */     configuration.setSchemaValidation(this.schemaValidation);
/* 742 */     configuration.setEntityResolver(this.entityResolver);
/* 743 */     configuration.setAttributeSplittingDisabled(this.attributeSplittingDisabled);
/* 744 */     configuration.setListDelimiter(getListDelimiter());
/* 745 */     configuration.addConfigurationListener(this);
/* 746 */     configuration.addErrorListener(this);
/*     */     try {
/* 750 */       configuration.load();
/* 752 */     } catch (ConfigurationException ce) {
/* 754 */       if (isThrowable((Throwable)ce))
/* 756 */         throw new ConfigurationRuntimeException(ce); 
/*     */     } 
/* 759 */     synchronized (this.configurationsMap) {
/* 761 */       if (!this.configurationsMap.containsKey(path))
/* 763 */         this.configurationsMap.put(path, configuration); 
/*     */     } 
/* 767 */     return configuration;
/*     */   }
/*     */   
/*     */   private boolean isThrowable(Throwable throwable) {
/* 772 */     if (!this.ignoreException)
/* 774 */       return true; 
/* 776 */     Throwable cause = throwable.getCause();
/* 777 */     while (cause != null && !(cause instanceof org.xml.sax.SAXParseException))
/* 779 */       cause = cause.getCause(); 
/* 781 */     return (cause != null);
/*     */   }
/*     */   
/*     */   private ReloadingStrategy createReloadingStrategy() {
/* 790 */     if (this.fileStrategy == null)
/* 792 */       return null; 
/*     */     try {
/* 796 */       ReloadingStrategy strategy = (ReloadingStrategy)BeanUtils.cloneBean(this.fileStrategy);
/* 797 */       strategy.setConfiguration(null);
/* 798 */       return strategy;
/* 800 */     } catch (Exception ex) {
/* 802 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\MultiFileHierarchicalConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */