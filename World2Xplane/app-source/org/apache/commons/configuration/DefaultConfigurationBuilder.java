/*      */ package org.apache.commons.configuration;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.apache.commons.configuration.beanutils.BeanDeclaration;
/*      */ import org.apache.commons.configuration.beanutils.BeanFactory;
/*      */ import org.apache.commons.configuration.beanutils.BeanHelper;
/*      */ import org.apache.commons.configuration.beanutils.DefaultBeanFactory;
/*      */ import org.apache.commons.configuration.beanutils.XMLBeanDeclaration;
/*      */ import org.apache.commons.configuration.event.ConfigurationErrorListener;
/*      */ import org.apache.commons.configuration.event.ConfigurationListener;
/*      */ import org.apache.commons.configuration.interpol.ConfigurationInterpolator;
/*      */ import org.apache.commons.configuration.resolver.CatalogResolver;
/*      */ import org.apache.commons.configuration.resolver.EntityResolverSupport;
/*      */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*      */ import org.apache.commons.configuration.tree.NodeCombiner;
/*      */ import org.apache.commons.configuration.tree.OverrideCombiner;
/*      */ import org.apache.commons.configuration.tree.UnionCombiner;
/*      */ import org.apache.commons.lang.text.StrLookup;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.xml.sax.EntityResolver;
/*      */ 
/*      */ public class DefaultConfigurationBuilder extends XMLConfiguration implements ConfigurationBuilder {
/*  228 */   public static final String ADDITIONAL_NAME = DefaultConfigurationBuilder.class.getName() + "/ADDITIONAL_CONFIG";
/*      */   
/*      */   public static final int EVENT_ERR_LOAD_OPTIONAL = 51;
/*      */   
/*  239 */   static final String CONFIG_BEAN_FACTORY_NAME = DefaultConfigurationBuilder.class.getName() + ".CONFIG_BEAN_FACTORY_NAME";
/*      */   
/*      */   static final String ATTR_NAME = "[@config-name]";
/*      */   
/*      */   static final String ATTR_ATNAME = "at";
/*      */   
/*      */   static final String ATTR_AT_RES = "[@config-at]";
/*      */   
/*      */   static final String ATTR_AT = "[@at]";
/*      */   
/*      */   static final String ATTR_OPTIONALNAME = "optional";
/*      */   
/*      */   static final String ATTR_OPTIONAL_RES = "[@config-optional]";
/*      */   
/*      */   static final String ATTR_OPTIONAL = "[@optional]";
/*      */   
/*      */   static final String ATTR_FILENAME = "[@fileName]";
/*      */   
/*      */   static final String ATTR_FORCECREATE = "[@config-forceCreate]";
/*      */   
/*      */   static final String KEY_SYSTEM_PROPS = "[@systemProperties]";
/*      */   
/*      */   static final String SEC_HEADER = "header";
/*      */   
/*      */   static final String KEY_UNION = "additional";
/*      */   
/*  297 */   static final String[] CONFIG_SECTIONS = new String[] { "additional", "override", "header" };
/*      */   
/*      */   static final String KEY_OVERRIDE = "override";
/*      */   
/*      */   static final String KEY_OVERRIDE_LIST = "header.combiner.override.list-nodes.node";
/*      */   
/*      */   static final String KEY_ADDITIONAL_LIST = "header.combiner.additional.list-nodes.node";
/*      */   
/*      */   static final String KEY_CONFIGURATION_PROVIDERS = "header.providers.provider";
/*      */   
/*      */   static final String KEY_PROVIDER_KEY = "[@config-tag]";
/*      */   
/*      */   static final String KEY_CONFIGURATION_LOOKUPS = "header.lookups.lookup";
/*      */   
/*      */   static final String KEY_ENTITY_RESOLVER = "header.entity-resolver";
/*      */   
/*      */   static final String KEY_LOOKUP_KEY = "[@config-prefix]";
/*      */   
/*      */   static final String FILE_SYSTEM = "header.fileSystem";
/*      */   
/*      */   static final String KEY_RESULT = "header.result";
/*      */   
/*      */   static final String KEY_COMBINER = "header.result.nodeCombiner";
/*      */   
/*      */   static final String EXT_XML = ".xml";
/*      */   
/*  367 */   private static final ConfigurationProvider PROPERTIES_PROVIDER = new FileExtensionConfigurationProvider(XMLPropertiesConfiguration.class, PropertiesConfiguration.class, ".xml");
/*      */   
/*  372 */   private static final ConfigurationProvider XML_PROVIDER = new XMLConfigurationProvider();
/*      */   
/*  375 */   private static final ConfigurationProvider JNDI_PROVIDER = new ConfigurationProvider(JNDIConfiguration.class);
/*      */   
/*  379 */   private static final ConfigurationProvider SYSTEM_PROVIDER = new ConfigurationProvider(SystemConfiguration.class);
/*      */   
/*  383 */   private static final ConfigurationProvider INI_PROVIDER = new FileConfigurationProvider(HierarchicalINIConfiguration.class);
/*      */   
/*  387 */   private static final ConfigurationProvider ENV_PROVIDER = new ConfigurationProvider(EnvironmentConfiguration.class);
/*      */   
/*  391 */   private static final ConfigurationProvider PLIST_PROVIDER = new FileExtensionConfigurationProvider("org.apache.commons.configuration.plist.XMLPropertyListConfiguration", "org.apache.commons.configuration.plist.PropertyListConfiguration", ".xml");
/*      */   
/*  397 */   private static final ConfigurationProvider BUILDER_PROVIDER = new ConfigurationBuilderProvider();
/*      */   
/*  400 */   private static final String[] DEFAULT_TAGS = new String[] { "properties", "xml", "hierarchicalXml", "jndi", "system", "plist", "configuration", "ini", "env" };
/*      */   
/*  406 */   private static final ConfigurationProvider[] DEFAULT_PROVIDERS = new ConfigurationProvider[] { PROPERTIES_PROVIDER, XML_PROVIDER, XML_PROVIDER, JNDI_PROVIDER, SYSTEM_PROVIDER, PLIST_PROVIDER, BUILDER_PROVIDER, INI_PROVIDER, ENV_PROVIDER };
/*      */   
/*      */   private static final long serialVersionUID = -3113777854714492123L;
/*      */   
/*      */   private CombinedConfiguration constructedConfiguration;
/*      */   
/*      */   private Map providers;
/*      */   
/*      */   private String configurationBasePath;
/*      */   
/*      */   static Class class$org$apache$commons$configuration$Configuration;
/*      */   
/*      */   static Class class$org$apache$commons$configuration$XMLConfiguration;
/*      */   
/*      */   public DefaultConfigurationBuilder() {
/*  435 */     this.providers = new HashMap();
/*  436 */     registerDefaultProviders();
/*  437 */     registerBeanFactory();
/*  438 */     setLogger(LogFactory.getLog(getClass()));
/*  439 */     addErrorLogListener();
/*      */   }
/*      */   
/*      */   public DefaultConfigurationBuilder(File file) {
/*  450 */     this();
/*  451 */     setFile(file);
/*      */   }
/*      */   
/*      */   public DefaultConfigurationBuilder(String fileName) throws ConfigurationException {
/*  464 */     this();
/*  465 */     setFileName(fileName);
/*      */   }
/*      */   
/*      */   public DefaultConfigurationBuilder(URL url) throws ConfigurationException {
/*  477 */     this();
/*  478 */     setURL(url);
/*      */   }
/*      */   
/*      */   public String getConfigurationBasePath() {
/*  489 */     return (this.configurationBasePath != null) ? this.configurationBasePath : getBasePath();
/*      */   }
/*      */   
/*      */   public void setConfigurationBasePath(String configurationBasePath) {
/*  506 */     this.configurationBasePath = configurationBasePath;
/*      */   }
/*      */   
/*      */   public void addConfigurationProvider(String tagName, ConfigurationProvider provider) {
/*  520 */     if (tagName == null)
/*  522 */       throw new IllegalArgumentException("Tag name must not be null!"); 
/*  524 */     if (provider == null)
/*  526 */       throw new IllegalArgumentException("Provider must not be null!"); 
/*  529 */     this.providers.put(tagName, provider);
/*      */   }
/*      */   
/*      */   public ConfigurationProvider removeConfigurationProvider(String tagName) {
/*  541 */     return (ConfigurationProvider)this.providers.remove(tagName);
/*      */   }
/*      */   
/*      */   public ConfigurationProvider providerForTag(String tagName) {
/*  553 */     return (ConfigurationProvider)this.providers.get(tagName);
/*      */   }
/*      */   
/*      */   public Configuration getConfiguration() throws ConfigurationException {
/*  566 */     return getConfiguration(true);
/*      */   }
/*      */   
/*      */   public CombinedConfiguration getConfiguration(boolean load) throws ConfigurationException {
/*  585 */     if (load)
/*  587 */       load(); 
/*  590 */     initFileSystem();
/*  591 */     initSystemProperties();
/*  592 */     configureEntityResolver();
/*  593 */     registerConfiguredProviders();
/*  594 */     registerConfiguredLookups();
/*  596 */     CombinedConfiguration result = createResultConfiguration();
/*  597 */     this.constructedConfiguration = result;
/*  599 */     List overrides = fetchTopLevelOverrideConfigs();
/*  600 */     overrides.addAll(fetchChildConfigs("override"));
/*  601 */     initCombinedConfiguration(result, overrides, "header.combiner.override.list-nodes.node");
/*  603 */     List additionals = fetchChildConfigs("additional");
/*  604 */     if (!additionals.isEmpty()) {
/*  606 */       CombinedConfiguration addConfig = createAdditionalsConfiguration(result);
/*  607 */       result.addConfiguration(addConfig, ADDITIONAL_NAME);
/*  608 */       initCombinedConfiguration(addConfig, additionals, "header.combiner.additional.list-nodes.node");
/*      */     } 
/*  612 */     return result;
/*      */   }
/*      */   
/*      */   protected CombinedConfiguration createResultConfiguration() throws ConfigurationException {
/*  629 */     XMLBeanDeclaration decl = new XMLBeanDeclaration(this, "header.result", true);
/*  630 */     CombinedConfiguration result = (CombinedConfiguration)BeanHelper.createBean((BeanDeclaration)decl, CombinedConfiguration.class);
/*  633 */     if (getMaxIndex("header.result.nodeCombiner") < 0)
/*  636 */       result.setNodeCombiner((NodeCombiner)new OverrideCombiner()); 
/*  639 */     return result;
/*      */   }
/*      */   
/*      */   protected CombinedConfiguration createAdditionalsConfiguration(CombinedConfiguration resultConfig) {
/*  658 */     CombinedConfiguration addConfig = new CombinedConfiguration((NodeCombiner)new UnionCombiner());
/*  660 */     addConfig.setDelimiterParsingDisabled(resultConfig.isDelimiterParsingDisabled());
/*  662 */     addConfig.setForceReloadCheck(resultConfig.isForceReloadCheck());
/*  663 */     addConfig.setIgnoreReloadExceptions(resultConfig.isIgnoreReloadExceptions());
/*  665 */     return addConfig;
/*      */   }
/*      */   
/*      */   protected void initCombinedConfiguration(CombinedConfiguration config, List containedConfigs, String keyListNodes) throws ConfigurationException {
/*  682 */     List listNodes = getList(keyListNodes);
/*  683 */     for (Iterator iterator = listNodes.iterator(); iterator.hasNext();)
/*  685 */       config.getNodeCombiner().addListNode(iterator.next()); 
/*  688 */     for (Iterator it = containedConfigs.iterator(); it.hasNext(); ) {
/*  690 */       HierarchicalConfiguration conf = it.next();
/*  692 */       ConfigurationDeclaration decl = new ConfigurationDeclaration(this, conf);
/*  694 */       if (getLogger().isDebugEnabled())
/*  696 */         getLogger().debug("Creating configuration " + decl.getBeanClassName() + " with name " + decl.getConfiguration().getString("[@config-name]")); 
/*  699 */       AbstractConfiguration newConf = createConfigurationAt(decl);
/*  700 */       if (newConf != null)
/*  702 */         config.addConfiguration(newConf, decl.getConfiguration().getString("[@config-name]"), decl.getAt()); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void registerDefaultProviders() {
/*  715 */     for (int i = 0; i < DEFAULT_TAGS.length; i++)
/*  717 */       addConfigurationProvider(DEFAULT_TAGS[i], DEFAULT_PROVIDERS[i]); 
/*      */   }
/*      */   
/*      */   protected void registerConfiguredProviders() throws ConfigurationException {
/*  728 */     List nodes = configurationsAt("header.providers.provider");
/*  729 */     for (Iterator it = nodes.iterator(); it.hasNext(); ) {
/*  731 */       HierarchicalConfiguration config = it.next();
/*  732 */       XMLBeanDeclaration decl = new XMLBeanDeclaration(config);
/*  733 */       String key = config.getString("[@config-tag]");
/*  734 */       addConfigurationProvider(key, (ConfigurationProvider)BeanHelper.createBean((BeanDeclaration)decl));
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void registerConfiguredLookups() throws ConfigurationException {
/*  746 */     List nodes = configurationsAt("header.lookups.lookup");
/*  747 */     for (Iterator it = nodes.iterator(); it.hasNext(); ) {
/*  749 */       HierarchicalConfiguration config = it.next();
/*  750 */       XMLBeanDeclaration decl = new XMLBeanDeclaration(config);
/*  751 */       String key = config.getString("[@config-prefix]");
/*  752 */       StrLookup lookup = (StrLookup)BeanHelper.createBean((BeanDeclaration)decl);
/*  753 */       BeanHelper.setProperty(lookup, "configuration", this);
/*  754 */       ConfigurationInterpolator.registerGlobalLookup(key, lookup);
/*  755 */       getInterpolator().registerLookup(key, lookup);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void initFileSystem() throws ConfigurationException {
/*  761 */     if (getMaxIndex("header.fileSystem") == 0) {
/*  763 */       HierarchicalConfiguration config = configurationAt("header.fileSystem");
/*  764 */       XMLBeanDeclaration decl = new XMLBeanDeclaration(config);
/*  765 */       setFileSystem((FileSystem)BeanHelper.createBean((BeanDeclaration)decl));
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void initSystemProperties() throws ConfigurationException {
/*  775 */     String fileName = getString("[@systemProperties]");
/*  776 */     if (fileName != null)
/*      */       try {
/*  780 */         SystemConfiguration.setSystemProperties(getConfigurationBasePath(), fileName);
/*  782 */       } catch (Exception ex) {
/*  784 */         throw new ConfigurationException("Error setting system properties from " + fileName, ex);
/*      */       }  
/*      */   }
/*      */   
/*      */   protected void configureEntityResolver() throws ConfigurationException {
/*  792 */     if (getMaxIndex("header.entity-resolver") == 0) {
/*  794 */       XMLBeanDeclaration decl = new XMLBeanDeclaration(this, "header.entity-resolver", true);
/*  795 */       EntityResolver resolver = (EntityResolver)BeanHelper.createBean((BeanDeclaration)decl, CatalogResolver.class);
/*  796 */       BeanHelper.setProperty(resolver, "fileSystem", getFileSystem());
/*  797 */       BeanHelper.setProperty(resolver, "baseDir", getBasePath());
/*  798 */       BeanHelper.setProperty(resolver, "substitutor", getSubstitutor());
/*  799 */       setEntityResolver(resolver);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Object interpolate(Object value) {
/*  815 */     Object result = super.interpolate(value);
/*  816 */     if (this.constructedConfiguration != null)
/*  818 */       result = this.constructedConfiguration.interpolate(result); 
/*  820 */     return result;
/*      */   }
/*      */   
/*      */   protected void fireError(int type, String propName, Object propValue, Throwable ex) {
/*  828 */     super.fireError(type, propName, propValue, ex);
/*      */   }
/*      */   
/*      */   private AbstractConfiguration createConfigurationAt(ConfigurationDeclaration decl) throws ConfigurationException {
/*      */     try {
/*  844 */       return (AbstractConfiguration)BeanHelper.createBean((BeanDeclaration)decl);
/*  846 */     } catch (Exception ex) {
/*  849 */       throw new ConfigurationException(ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   private List fetchChildConfigs(ConfigurationNode node) {
/*  862 */     List children = node.getChildren();
/*  863 */     List result = new ArrayList(children.size());
/*  864 */     for (Iterator it = children.iterator(); it.hasNext();)
/*  866 */       result.add(createSubnodeConfiguration((ConfigurationNode)it.next())); 
/*  868 */     return result;
/*      */   }
/*      */   
/*      */   private List fetchChildConfigs(String key) {
/*  880 */     List nodes = fetchNodeList(key);
/*  881 */     if (nodes.size() > 0)
/*  883 */       return fetchChildConfigs(nodes.get(0)); 
/*  887 */     return Collections.EMPTY_LIST;
/*      */   }
/*      */   
/*      */   private List fetchTopLevelOverrideConfigs() {
/*  903 */     List configs = fetchChildConfigs(getRootNode());
/*  904 */     for (Iterator it = configs.iterator(); it.hasNext(); ) {
/*  906 */       String nodeName = ((SubnodeConfiguration)it.next()).getRootNode().getName();
/*  908 */       for (int i = 0; i < CONFIG_SECTIONS.length; i++) {
/*  910 */         if (CONFIG_SECTIONS[i].equals(nodeName)) {
/*  912 */           it.remove();
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  917 */     return configs;
/*      */   }
/*      */   
/*      */   private void registerBeanFactory() {
/*  927 */     synchronized (DefaultConfigurationBuilder.class) {
/*  929 */       if (!BeanHelper.registeredFactoryNames().contains(CONFIG_BEAN_FACTORY_NAME))
/*  932 */         BeanHelper.registerBeanFactory(CONFIG_BEAN_FACTORY_NAME, new ConfigurationBeanFactory()); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static class ConfigurationProvider extends DefaultBeanFactory {
/*      */     private Class configurationClass;
/*      */     
/*      */     private String configurationClassName;
/*      */     
/*      */     public ConfigurationProvider() {
/*  971 */       this((Class)null);
/*      */     }
/*      */     
/*      */     public ConfigurationProvider(Class configClass) {
/*  982 */       setConfigurationClass(configClass);
/*      */     }
/*      */     
/*      */     public ConfigurationProvider(String configClassName) {
/*  995 */       setConfigurationClassName(configClassName);
/*      */     }
/*      */     
/*      */     public Class getConfigurationClass() {
/* 1005 */       return this.configurationClass;
/*      */     }
/*      */     
/*      */     public void setConfigurationClass(Class configurationClass) {
/* 1015 */       this.configurationClass = configurationClass;
/*      */     }
/*      */     
/*      */     public String getConfigurationClassName() {
/* 1027 */       return this.configurationClassName;
/*      */     }
/*      */     
/*      */     public void setConfigurationClassName(String configurationClassName) {
/* 1038 */       this.configurationClassName = configurationClassName;
/*      */     }
/*      */     
/*      */     public AbstractConfiguration getConfiguration(DefaultConfigurationBuilder.ConfigurationDeclaration decl) throws Exception {
/* 1057 */       return (AbstractConfiguration)createBean(fetchConfigurationClass(), (BeanDeclaration)decl, null);
/*      */     }
/*      */     
/*      */     public AbstractConfiguration getEmptyConfiguration(DefaultConfigurationBuilder.ConfigurationDeclaration decl) throws Exception {
/* 1079 */       return null;
/*      */     }
/*      */     
/*      */     protected synchronized Class fetchConfigurationClass() throws Exception {
/* 1092 */       if (getConfigurationClass() == null)
/* 1094 */         setConfigurationClass(loadClass(getConfigurationClassName())); 
/* 1096 */       return getConfigurationClass();
/*      */     }
/*      */     
/*      */     protected Class loadClass(String className) throws ClassNotFoundException {
/* 1111 */       return (className != null) ? Class.forName(className, true, getClass().getClassLoader()) : null;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ConfigurationDeclaration extends XMLBeanDeclaration {
/*      */     private DefaultConfigurationBuilder configurationBuilder;
/*      */     
/*      */     public ConfigurationDeclaration(DefaultConfigurationBuilder builder, HierarchicalConfiguration config) {
/* 1146 */       super(config);
/* 1147 */       this.configurationBuilder = builder;
/*      */     }
/*      */     
/*      */     public DefaultConfigurationBuilder getConfigurationBuilder() {
/* 1157 */       return this.configurationBuilder;
/*      */     }
/*      */     
/*      */     public String getAt() {
/* 1167 */       String result = getConfiguration().getString("[@config-at]");
/* 1168 */       return (result == null) ? getConfiguration().getString("[@at]") : result;
/*      */     }
/*      */     
/*      */     public boolean isOptional() {
/* 1180 */       Boolean value = getConfiguration().getBoolean("[@config-optional]", (Boolean)null);
/* 1182 */       if (value == null)
/* 1184 */         value = getConfiguration().getBoolean("[@optional]", Boolean.FALSE); 
/* 1187 */       return value.booleanValue();
/*      */     }
/*      */     
/*      */     public boolean isForceCreate() {
/* 1204 */       return getConfiguration().getBoolean("[@config-forceCreate]", false);
/*      */     }
/*      */     
/*      */     public String getBeanFactoryName() {
/* 1216 */       return DefaultConfigurationBuilder.CONFIG_BEAN_FACTORY_NAME;
/*      */     }
/*      */     
/*      */     public String getBeanClassName() {
/* 1227 */       return null;
/*      */     }
/*      */     
/*      */     protected boolean isReservedNode(ConfigurationNode nd) {
/* 1239 */       if (super.isReservedNode(nd))
/* 1241 */         return true; 
/* 1244 */       return (nd.isAttribute() && (("at".equals(nd.getName()) && nd.getParentNode().getAttributeCount("config-at") == 0) || ("optional".equals(nd.getName()) && nd.getParentNode().getAttributeCount("config-optional") == 0)));
/*      */     }
/*      */     
/*      */     protected Object interpolate(Object value) {
/* 1261 */       return getConfigurationBuilder().interpolate(value);
/*      */     }
/*      */   }
/*      */   
/*      */   static class ConfigurationBeanFactory implements BeanFactory {
/* 1274 */     private Log logger = LogFactory.getLog((DefaultConfigurationBuilder.class$org$apache$commons$configuration$DefaultConfigurationBuilder == null) ? (DefaultConfigurationBuilder.class$org$apache$commons$configuration$DefaultConfigurationBuilder = DefaultConfigurationBuilder.class$("org.apache.commons.configuration.DefaultConfigurationBuilder")) : DefaultConfigurationBuilder.class$org$apache$commons$configuration$DefaultConfigurationBuilder);
/*      */     
/*      */     public Object createBean(Class beanClass, BeanDeclaration data, Object param) throws Exception {
/* 1295 */       DefaultConfigurationBuilder.ConfigurationDeclaration decl = (DefaultConfigurationBuilder.ConfigurationDeclaration)data;
/* 1296 */       String tagName = decl.getNode().getName();
/* 1297 */       DefaultConfigurationBuilder.ConfigurationProvider provider = decl.getConfigurationBuilder().providerForTag(tagName);
/* 1299 */       if (provider == null)
/* 1301 */         throw new ConfigurationRuntimeException("No ConfigurationProvider registered for tag " + tagName); 
/*      */       try {
/* 1308 */         return provider.getConfiguration(decl);
/* 1310 */       } catch (Exception ex) {
/* 1313 */         if (!decl.isOptional())
/* 1315 */           throw ex; 
/* 1319 */         if (this.logger.isDebugEnabled())
/* 1321 */           this.logger.debug("Load failed for optional configuration " + tagName + ": " + ex.getMessage()); 
/* 1325 */         decl.getConfigurationBuilder().fireError(51, decl.getConfiguration().getString("[@config-name]"), (Object)null, ex);
/* 1330 */         if (decl.isForceCreate())
/*      */           try {
/* 1334 */             return provider.getEmptyConfiguration(decl);
/* 1336 */           } catch (Exception ex2) {} 
/* 1342 */         return null;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Class getDefaultBeanClass() {
/* 1356 */       return (DefaultConfigurationBuilder.class$org$apache$commons$configuration$Configuration == null) ? (DefaultConfigurationBuilder.class$org$apache$commons$configuration$Configuration = DefaultConfigurationBuilder.class$("org.apache.commons.configuration.Configuration")) : DefaultConfigurationBuilder.class$org$apache$commons$configuration$Configuration;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class FileConfigurationProvider extends ConfigurationProvider {
/*      */     public FileConfigurationProvider() {}
/*      */     
/*      */     public FileConfigurationProvider(Class configClass) {
/* 1383 */       super(configClass);
/*      */     }
/*      */     
/*      */     public FileConfigurationProvider(String configClassName) {
/* 1395 */       super(configClassName);
/*      */     }
/*      */     
/*      */     public AbstractConfiguration getConfiguration(DefaultConfigurationBuilder.ConfigurationDeclaration decl) throws Exception {
/* 1410 */       AbstractConfiguration result = getEmptyConfiguration(decl);
/* 1411 */       if (result instanceof FileSystemBased) {
/* 1413 */         DefaultConfigurationBuilder builder = decl.getConfigurationBuilder();
/* 1414 */         if (builder.getFileSystem() != null)
/* 1416 */           ((FileSystemBased)result).setFileSystem(builder.getFileSystem()); 
/*      */       } 
/* 1419 */       ((FileConfiguration)result).load();
/* 1420 */       return result;
/*      */     }
/*      */     
/*      */     public AbstractConfiguration getEmptyConfiguration(DefaultConfigurationBuilder.ConfigurationDeclaration decl) throws Exception {
/* 1443 */       AbstractConfiguration config = super.getConfiguration(decl);
/* 1449 */       if (config instanceof EntityResolverSupport) {
/* 1451 */         DefaultConfigurationBuilder builder = decl.getConfigurationBuilder();
/* 1452 */         EntityResolver resolver = builder.getEntityResolver();
/* 1453 */         ((EntityResolverSupport)config).setEntityResolver(resolver);
/*      */       } 
/* 1456 */       return config;
/*      */     }
/*      */     
/*      */     protected void initBeanInstance(Object bean, BeanDeclaration data) throws Exception {
/* 1471 */       FileConfiguration config = (FileConfiguration)bean;
/* 1472 */       config.setBasePath(((DefaultConfigurationBuilder.ConfigurationDeclaration)data).getConfigurationBuilder().getConfigurationBasePath());
/* 1474 */       super.initBeanInstance(bean, data);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class XMLConfigurationProvider extends FileConfigurationProvider {
/*      */     public XMLConfigurationProvider() {
/* 1493 */       super((DefaultConfigurationBuilder.class$org$apache$commons$configuration$XMLConfiguration == null) ? (DefaultConfigurationBuilder.class$org$apache$commons$configuration$XMLConfiguration = DefaultConfigurationBuilder.class$("org.apache.commons.configuration.XMLConfiguration")) : DefaultConfigurationBuilder.class$org$apache$commons$configuration$XMLConfiguration);
/*      */     }
/*      */     
/*      */     public AbstractConfiguration getEmptyConfiguration(DefaultConfigurationBuilder.ConfigurationDeclaration decl) throws Exception {
/* 1508 */       XMLConfiguration config = (XMLConfiguration)super.getEmptyConfiguration(decl);
/* 1511 */       DefaultConfigurationBuilder builder = decl.getConfigurationBuilder();
/* 1513 */       EntityResolver resolver = builder.getEntityResolver();
/* 1514 */       if (resolver instanceof org.apache.commons.configuration.resolver.EntityRegistry) {
/* 1517 */         config.getRegisteredEntities().putAll(builder.getRegisteredEntities());
/*      */       } else {
/* 1522 */         config.setEntityResolver(resolver);
/*      */       } 
/* 1524 */       return config;
/*      */     }
/*      */   }
/*      */   
/*      */   static class FileExtensionConfigurationProvider extends FileConfigurationProvider {
/*      */     private Class matchingClass;
/*      */     
/*      */     private String matchingClassName;
/*      */     
/*      */     private Class defaultClass;
/*      */     
/*      */     private String defaultClassName;
/*      */     
/*      */     private String fileExtension;
/*      */     
/*      */     public FileExtensionConfigurationProvider(Class matchingClass, Class defaultClass, String extension) {
/* 1578 */       this.matchingClass = matchingClass;
/* 1579 */       this.defaultClass = defaultClass;
/* 1580 */       this.fileExtension = extension;
/*      */     }
/*      */     
/*      */     public FileExtensionConfigurationProvider(String matchingClassName, String defaultClassName, String extension) {
/* 1598 */       this.matchingClassName = matchingClassName;
/* 1599 */       this.defaultClassName = defaultClassName;
/* 1600 */       this.fileExtension = extension;
/*      */     }
/*      */     
/*      */     protected synchronized Class fetchMatchingClass() throws Exception {
/* 1613 */       if (this.matchingClass == null)
/* 1615 */         this.matchingClass = loadClass(this.matchingClassName); 
/* 1617 */       return this.matchingClass;
/*      */     }
/*      */     
/*      */     protected synchronized Class fetchDefaultClass() throws Exception {
/* 1630 */       if (this.defaultClass == null)
/* 1632 */         this.defaultClass = loadClass(this.defaultClassName); 
/* 1634 */       return this.defaultClass;
/*      */     }
/*      */     
/*      */     protected Object createBeanInstance(Class beanClass, BeanDeclaration data) throws Exception {
/* 1649 */       String fileName = ((DefaultConfigurationBuilder.ConfigurationDeclaration)data).getConfiguration().getString("[@fileName]");
/* 1651 */       if (fileName != null && fileName.toLowerCase().trim().endsWith(this.fileExtension))
/* 1654 */         return super.createBeanInstance(fetchMatchingClass(), data); 
/* 1658 */       return super.createBeanInstance(fetchDefaultClass(), data);
/*      */     }
/*      */   }
/*      */   
/*      */   static class ConfigurationBuilderProvider extends ConfigurationProvider {
/*      */     public ConfigurationBuilderProvider() {
/* 1674 */       super((DefaultConfigurationBuilder.class$org$apache$commons$configuration$DefaultConfigurationBuilder == null) ? (DefaultConfigurationBuilder.class$org$apache$commons$configuration$DefaultConfigurationBuilder = DefaultConfigurationBuilder.class$("org.apache.commons.configuration.DefaultConfigurationBuilder")) : DefaultConfigurationBuilder.class$org$apache$commons$configuration$DefaultConfigurationBuilder);
/*      */     }
/*      */     
/*      */     public AbstractConfiguration getConfiguration(DefaultConfigurationBuilder.ConfigurationDeclaration decl) throws Exception {
/* 1688 */       DefaultConfigurationBuilder builder = (DefaultConfigurationBuilder)super.getConfiguration(decl);
/* 1690 */       return builder.getConfiguration(true);
/*      */     }
/*      */     
/*      */     public AbstractConfiguration getEmptyConfiguration(DefaultConfigurationBuilder.ConfigurationDeclaration decl) throws Exception {
/* 1706 */       return new CombinedConfiguration();
/*      */     }
/*      */     
/*      */     protected void initBeanInstance(Object bean, BeanDeclaration data) throws Exception {
/* 1717 */       DefaultConfigurationBuilder.ConfigurationDeclaration decl = (DefaultConfigurationBuilder.ConfigurationDeclaration)data;
/* 1718 */       initChildBuilder(decl.getConfigurationBuilder(), (DefaultConfigurationBuilder)bean);
/* 1720 */       super.initBeanInstance(bean, data);
/*      */     }
/*      */     
/*      */     private static void initChildBuilder(DefaultConfigurationBuilder parent, DefaultConfigurationBuilder child) {
/* 1736 */       child.setAttributeSplittingDisabled(parent.isAttributeSplittingDisabled());
/* 1738 */       child.setBasePath(parent.getBasePath());
/* 1739 */       child.setDelimiterParsingDisabled(parent.isDelimiterParsingDisabled());
/* 1741 */       child.setListDelimiter(parent.getListDelimiter());
/* 1742 */       child.setThrowExceptionOnMissing(parent.isThrowExceptionOnMissing());
/* 1743 */       child.setLogger(parent.getLogger());
/* 1745 */       child.clearConfigurationListeners();
/* 1746 */       Iterator iterator = parent.getConfigurationListeners().iterator();
/* 1747 */       while (iterator.hasNext())
/* 1749 */         child.addConfigurationListener(iterator.next()); 
/* 1752 */       child.clearErrorListeners();
/* 1753 */       Iterator it = parent.getErrorListeners().iterator();
/* 1754 */       while (it.hasNext())
/* 1756 */         child.addErrorListener(it.next()); 
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\DefaultConfigurationBuilder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */