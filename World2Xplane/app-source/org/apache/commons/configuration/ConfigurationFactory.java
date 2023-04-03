/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.configuration.plist.PropertyListConfiguration;
/*     */ import org.apache.commons.configuration.plist.XMLPropertyListConfiguration;
/*     */ import org.apache.commons.digester.AbstractObjectCreationFactory;
/*     */ import org.apache.commons.digester.CallMethodRule;
/*     */ import org.apache.commons.digester.Digester;
/*     */ import org.apache.commons.digester.ObjectCreationFactory;
/*     */ import org.apache.commons.digester.Rule;
/*     */ import org.apache.commons.digester.Substitutor;
/*     */ import org.apache.commons.digester.substitution.MultiVariableExpander;
/*     */ import org.apache.commons.digester.substitution.VariableExpander;
/*     */ import org.apache.commons.digester.substitution.VariableSubstitutor;
/*     */ import org.apache.commons.digester.xmlrules.DigesterLoader;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class ConfigurationFactory {
/*     */   private static final String SEC_ROOT = "configuration/";
/*     */   
/*     */   private static final String SEC_OVERRIDE = "configuration/override/";
/*     */   
/*     */   private static final String SEC_ADDITIONAL = "configuration/additional/";
/*     */   
/*     */   private static final String ATTR_OPTIONAL = "optional";
/*     */   
/*     */   private static final String ATTR_FILENAME = "fileName";
/*     */   
/*     */   private static final String METH_LOAD = "load";
/*     */   
/*     */   private static final String DEF_BASE_PATH = ".";
/*     */   
/*  92 */   private static Log log = LogFactory.getLog(ConfigurationFactory.class);
/*     */   
/*     */   private String configurationFileName;
/*     */   
/*     */   private URL configurationURL;
/*     */   
/*     */   private String implicitBasePath;
/*     */   
/*     */   private String basePath;
/*     */   
/*     */   private URL digesterRules;
/*     */   
/*     */   private String digesterRuleNamespaceURI;
/*     */   
/*     */   static Class class$org$apache$commons$configuration$JNDIConfiguration;
/*     */   
/*     */   static Class class$org$apache$commons$configuration$SystemConfiguration;
/*     */   
/*     */   public ConfigurationFactory() {
/* 121 */     setBasePath(".");
/*     */   }
/*     */   
/*     */   public ConfigurationFactory(String configurationFileName) {
/* 130 */     setConfigurationFileName(configurationFileName);
/*     */   }
/*     */   
/*     */   public Configuration getConfiguration() throws ConfigurationException {
/*     */     Digester digester;
/* 146 */     InputStream input = null;
/* 147 */     ConfigurationBuilder builder = new ConfigurationBuilder();
/* 148 */     URL url = getConfigurationURL();
/*     */     try {
/* 151 */       if (url == null)
/* 153 */         url = ConfigurationUtils.locate(this.implicitBasePath, getConfigurationFileName()); 
/* 155 */       input = url.openStream();
/* 157 */     } catch (Exception e) {
/* 159 */       log.error("Exception caught opening stream to URL", e);
/* 160 */       throw new ConfigurationException("Exception caught opening stream to URL", e);
/*     */     } 
/* 163 */     if (getDigesterRules() == null) {
/* 165 */       digester = new Digester();
/* 166 */       configureNamespace(digester);
/* 167 */       initDefaultDigesterRules(digester);
/*     */     } else {
/* 171 */       digester = DigesterLoader.createDigester(getDigesterRules());
/* 174 */       configureNamespace(digester);
/*     */     } 
/* 178 */     digester.setUseContextClassLoader(true);
/* 180 */     enableDigesterSubstitutor(digester);
/* 182 */     digester.push(builder);
/*     */     try {
/* 186 */       digester.parse(input);
/* 187 */       input.close();
/* 189 */     } catch (SAXException saxe) {
/* 191 */       log.error("SAX Exception caught", saxe);
/* 192 */       throw new ConfigurationException("SAX Exception caught", saxe);
/* 194 */     } catch (IOException ioe) {
/* 196 */       log.error("IO Exception caught", ioe);
/* 197 */       throw new ConfigurationException("IO Exception caught", ioe);
/*     */     } 
/* 199 */     return builder.getConfiguration();
/*     */   }
/*     */   
/*     */   public String getConfigurationFileName() {
/* 209 */     return this.configurationFileName;
/*     */   }
/*     */   
/*     */   public void setConfigurationFileName(String configurationFileName) {
/* 219 */     File file = (new File(configurationFileName)).getAbsoluteFile();
/* 220 */     this.configurationFileName = file.getName();
/* 221 */     this.implicitBasePath = file.getParent();
/*     */   }
/*     */   
/*     */   public URL getConfigurationURL() {
/* 231 */     return this.configurationURL;
/*     */   }
/*     */   
/*     */   public void setConfigurationURL(URL url) {
/* 242 */     this.configurationURL = url;
/* 243 */     this.implicitBasePath = url.toString();
/*     */   }
/*     */   
/*     */   public URL getDigesterRules() {
/* 253 */     return this.digesterRules;
/*     */   }
/*     */   
/*     */   public void setDigesterRules(URL digesterRules) {
/* 263 */     this.digesterRules = digesterRules;
/*     */   }
/*     */   
/*     */   protected void enableDigesterSubstitutor(Digester digester) {
/* 273 */     Map systemProperties = System.getProperties();
/* 274 */     MultiVariableExpander expander = new MultiVariableExpander();
/* 275 */     expander.addSource("$", systemProperties);
/* 278 */     VariableSubstitutor variableSubstitutor = new VariableSubstitutor((VariableExpander)expander);
/* 279 */     digester.setSubstitutor((Substitutor)variableSubstitutor);
/*     */   }
/*     */   
/*     */   protected void initDefaultDigesterRules(Digester digester) {
/* 293 */     initDigesterSectionRules(digester, "configuration/", false);
/* 294 */     initDigesterSectionRules(digester, "configuration/override/", false);
/* 295 */     initDigesterSectionRules(digester, "configuration/additional/", true);
/*     */   }
/*     */   
/*     */   protected void initDigesterSectionRules(Digester digester, String matchString, boolean additional) {
/* 309 */     setupDigesterInstance(digester, matchString + "properties", (ObjectCreationFactory)new PropertiesConfigurationFactory(), "load", additional);
/* 316 */     setupDigesterInstance(digester, matchString + "plist", (ObjectCreationFactory)new PropertyListConfigurationFactory(), "load", additional);
/* 323 */     setupDigesterInstance(digester, matchString + "xml", (ObjectCreationFactory)new FileConfigurationFactory(XMLConfiguration.class), "load", additional);
/* 330 */     setupDigesterInstance(digester, matchString + "hierarchicalXml", (ObjectCreationFactory)new FileConfigurationFactory(XMLConfiguration.class), "load", additional);
/* 337 */     setupDigesterInstance(digester, matchString + "jndi", (ObjectCreationFactory)new JNDIConfigurationFactory(), null, additional);
/* 344 */     setupDigesterInstance(digester, matchString + "system", (ObjectCreationFactory)new SystemConfigurationFactory(), null, additional);
/*     */   }
/*     */   
/*     */   protected void setupDigesterInstance(Digester digester, String matchString, ObjectCreationFactory factory, String method, boolean additional) {
/* 370 */     if (additional)
/* 372 */       setupUnionRules(digester, matchString); 
/* 375 */     digester.addFactoryCreate(matchString, factory);
/* 376 */     digester.addSetProperties(matchString);
/* 378 */     if (method != null)
/* 380 */       digester.addRule(matchString, (Rule)new CallOptionalMethodRule(method)); 
/* 383 */     digester.addSetNext(matchString, "addConfiguration", Configuration.class.getName());
/*     */   }
/*     */   
/*     */   protected void setupUnionRules(Digester digester, String matchString) {
/* 394 */     digester.addObjectCreate(matchString, AdditionalConfigurationData.class);
/* 396 */     digester.addSetProperties(matchString);
/* 397 */     digester.addSetNext(matchString, "addAdditionalConfig", AdditionalConfigurationData.class.getName());
/*     */   }
/*     */   
/*     */   public String getDigesterRuleNamespaceURI() {
/* 408 */     return this.digesterRuleNamespaceURI;
/*     */   }
/*     */   
/*     */   public void setDigesterRuleNamespaceURI(String digesterRuleNamespaceURI) {
/* 418 */     this.digesterRuleNamespaceURI = digesterRuleNamespaceURI;
/*     */   }
/*     */   
/*     */   private void configureNamespace(Digester digester) {
/* 430 */     if (getDigesterRuleNamespaceURI() != null) {
/* 432 */       digester.setNamespaceAware(true);
/* 433 */       digester.setRuleNamespaceURI(getDigesterRuleNamespaceURI());
/*     */     } else {
/* 437 */       digester.setNamespaceAware(false);
/*     */     } 
/* 439 */     digester.setValidating(false);
/*     */   }
/*     */   
/*     */   public String getBasePath() {
/* 451 */     String path = (StringUtils.isEmpty(this.basePath) || ".".equals(this.basePath)) ? this.implicitBasePath : this.basePath;
/* 453 */     return StringUtils.isEmpty(path) ? "." : path;
/*     */   }
/*     */   
/*     */   public void setBasePath(String basePath) {
/* 469 */     this.basePath = basePath;
/*     */   }
/*     */   
/*     */   public class DigesterConfigurationFactory extends AbstractObjectCreationFactory {
/*     */     private Class clazz;
/*     */     
/*     */     private final ConfigurationFactory this$0;
/*     */     
/*     */     public DigesterConfigurationFactory(Class clazz) {
/* 489 */       this.clazz = clazz;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attribs) throws Exception {
/* 501 */       return this.clazz.newInstance();
/*     */     }
/*     */   }
/*     */   
/*     */   public class FileConfigurationFactory extends DigesterConfigurationFactory {
/*     */     private final ConfigurationFactory this$0;
/*     */     
/*     */     public FileConfigurationFactory(Class clazz) {
/* 520 */       super(clazz);
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) throws Exception {
/* 532 */       FileConfiguration conf = createConfiguration(attributes);
/* 533 */       conf.setBasePath(ConfigurationFactory.this.getBasePath());
/* 534 */       return conf;
/*     */     }
/*     */     
/*     */     protected FileConfiguration createConfiguration(Attributes attributes) throws Exception {
/* 546 */       return (FileConfiguration)super.createObject(attributes);
/*     */     }
/*     */   }
/*     */   
/*     */   public class PropertiesConfigurationFactory extends FileConfigurationFactory {
/*     */     private final ConfigurationFactory this$0;
/*     */     
/*     */     public PropertiesConfigurationFactory() {
/* 563 */       super(null);
/*     */     }
/*     */     
/*     */     protected FileConfiguration createConfiguration(Attributes attributes) throws Exception {
/* 578 */       String filename = attributes.getValue("fileName");
/* 580 */       if (filename != null && filename.toLowerCase().trim().endsWith(".xml"))
/* 582 */         return new XMLPropertiesConfiguration(); 
/* 586 */       return new PropertiesConfiguration();
/*     */     }
/*     */   }
/*     */   
/*     */   public class PropertyListConfigurationFactory extends FileConfigurationFactory {
/*     */     private final ConfigurationFactory this$0;
/*     */     
/*     */     public PropertyListConfigurationFactory() {
/* 604 */       super(null);
/*     */     }
/*     */     
/*     */     protected FileConfiguration createConfiguration(Attributes attributes) throws Exception {
/* 619 */       String filename = attributes.getValue("fileName");
/* 621 */       if (filename != null && filename.toLowerCase().trim().endsWith(".xml"))
/* 623 */         return (FileConfiguration)new XMLPropertyListConfiguration(); 
/* 627 */       return (FileConfiguration)new PropertyListConfiguration();
/*     */     }
/*     */   }
/*     */   
/*     */   private class JNDIConfigurationFactory extends DigesterConfigurationFactory {
/*     */     private final ConfigurationFactory this$0;
/*     */     
/*     */     public JNDIConfigurationFactory() {
/* 643 */       super((ConfigurationFactory.class$org$apache$commons$configuration$JNDIConfiguration == null) ? (ConfigurationFactory.class$org$apache$commons$configuration$JNDIConfiguration = ConfigurationFactory.class$("org.apache.commons.configuration.JNDIConfiguration")) : ConfigurationFactory.class$org$apache$commons$configuration$JNDIConfiguration);
/*     */     }
/*     */   }
/*     */   
/*     */   private class SystemConfigurationFactory extends DigesterConfigurationFactory {
/*     */     private final ConfigurationFactory this$0;
/*     */     
/*     */     public SystemConfigurationFactory() {
/* 658 */       super((ConfigurationFactory.class$org$apache$commons$configuration$SystemConfiguration == null) ? (ConfigurationFactory.class$org$apache$commons$configuration$SystemConfiguration = ConfigurationFactory.class$("org.apache.commons.configuration.SystemConfiguration")) : ConfigurationFactory.class$org$apache$commons$configuration$SystemConfiguration);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class AdditionalConfigurationData {
/*     */     private Configuration configuration;
/*     */     
/*     */     private String at;
/*     */     
/*     */     public String getAt() {
/* 681 */       return this.at;
/*     */     }
/*     */     
/*     */     public void setAt(String string) {
/* 691 */       this.at = string;
/*     */     }
/*     */     
/*     */     public Configuration getConfiguration() {
/* 701 */       return this.configuration;
/*     */     }
/*     */     
/*     */     public void addConfiguration(Configuration config) {
/* 714 */       this.configuration = config;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ConfigurationBuilder {
/* 735 */     private CompositeConfiguration config = new CompositeConfiguration();
/*     */     
/* 736 */     private Collection additionalConfigs = new LinkedList();
/*     */     
/*     */     public void addConfiguration(Configuration conf) {
/* 747 */       this.config.addConfiguration(conf);
/*     */     }
/*     */     
/*     */     public void addAdditionalConfig(ConfigurationFactory.AdditionalConfigurationData data) {
/* 758 */       this.additionalConfigs.add(data);
/*     */     }
/*     */     
/*     */     public CompositeConfiguration getConfiguration() {
/* 768 */       if (!this.additionalConfigs.isEmpty()) {
/* 770 */         Configuration unionConfig = createAdditionalConfiguration(this.additionalConfigs);
/* 771 */         if (unionConfig != null)
/* 773 */           addConfiguration(unionConfig); 
/* 775 */         this.additionalConfigs.clear();
/*     */       } 
/* 778 */       return this.config;
/*     */     }
/*     */     
/*     */     protected Configuration createAdditionalConfiguration(Collection configs) {
/* 793 */       HierarchicalConfiguration result = new HierarchicalConfiguration();
/* 795 */       for (Iterator it = configs.iterator(); it.hasNext(); ) {
/* 797 */         ConfigurationFactory.AdditionalConfigurationData cdata = it.next();
/* 799 */         result.addNodes(cdata.getAt(), createRootNode(cdata).getChildren());
/*     */       } 
/* 803 */       return result.isEmpty() ? null : result;
/*     */     }
/*     */     
/*     */     private HierarchicalConfiguration.Node createRootNode(ConfigurationFactory.AdditionalConfigurationData cdata) {
/* 814 */       if (cdata.getConfiguration() instanceof HierarchicalConfiguration)
/* 817 */         return ((HierarchicalConfiguration)cdata.getConfiguration()).getRoot(); 
/* 822 */       HierarchicalConfiguration hc = new HierarchicalConfiguration();
/* 823 */       ConfigurationUtils.copy(cdata.getConfiguration(), hc);
/* 824 */       return hc.getRoot();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class CallOptionalMethodRule extends CallMethodRule {
/*     */     private boolean optional;
/*     */     
/*     */     public CallOptionalMethodRule(String methodName) {
/* 852 */       super(methodName);
/*     */     }
/*     */     
/*     */     public void begin(Attributes attrs) throws Exception {
/* 863 */       this.optional = (attrs.getValue("optional") != null && PropertyConverter.toBoolean(attrs.getValue("optional")).booleanValue());
/* 866 */       super.begin(attrs);
/*     */     }
/*     */     
/*     */     public void end() throws Exception {
/*     */       try {
/* 879 */         super.end();
/* 881 */       } catch (Exception ex) {
/* 883 */         if (this.optional) {
/* 885 */           ConfigurationFactory.log.warn("Could not create optional configuration!", ex);
/*     */         } else {
/* 889 */           throw ex;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\ConfigurationFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */