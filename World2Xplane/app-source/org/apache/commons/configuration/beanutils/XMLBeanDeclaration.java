/*     */ package org.apache.commons.configuration.beanutils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.configuration.AbstractConfiguration;
/*     */ import org.apache.commons.configuration.ConfigurationRuntimeException;
/*     */ import org.apache.commons.configuration.HierarchicalConfiguration;
/*     */ import org.apache.commons.configuration.PropertyConverter;
/*     */ import org.apache.commons.configuration.SubnodeConfiguration;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ import org.apache.commons.configuration.tree.DefaultConfigurationNode;
/*     */ 
/*     */ public class XMLBeanDeclaration implements BeanDeclaration {
/*     */   public static final String RESERVED_PREFIX = "config-";
/*     */   
/*     */   public static final String ATTR_PREFIX = "[@config-";
/*     */   
/*     */   public static final String ATTR_BEAN_CLASS = "[@config-class]";
/*     */   
/*     */   public static final String ATTR_BEAN_FACTORY = "[@config-factory]";
/*     */   
/*     */   public static final String ATTR_FACTORY_PARAM = "[@config-factoryParam]";
/*     */   
/*     */   private SubnodeConfiguration configuration;
/*     */   
/*     */   private ConfigurationNode node;
/*     */   
/*     */   public XMLBeanDeclaration(HierarchicalConfiguration config, String key) {
/* 143 */     this(config, key, false);
/*     */   }
/*     */   
/*     */   public XMLBeanDeclaration(HierarchicalConfiguration config, String key, boolean optional) {
/* 165 */     if (config == null)
/* 167 */       throw new IllegalArgumentException("Configuration must not be null!"); 
/*     */     try {
/* 173 */       this.configuration = config.configurationAt(key);
/* 174 */       this.node = this.configuration.getRootNode();
/* 176 */     } catch (IllegalArgumentException iex) {
/* 179 */       if (!optional || config.getMaxIndex(key) > 0)
/* 181 */         throw iex; 
/* 183 */       this.configuration = config.configurationAt(null);
/* 184 */       this.node = (ConfigurationNode)new DefaultConfigurationNode();
/*     */     } 
/* 186 */     initSubnodeConfiguration(getConfiguration());
/*     */   }
/*     */   
/*     */   public XMLBeanDeclaration(HierarchicalConfiguration config) {
/* 198 */     this(config, (String)null);
/*     */   }
/*     */   
/*     */   public XMLBeanDeclaration(SubnodeConfiguration config, ConfigurationNode node) {
/* 212 */     if (config == null)
/* 214 */       throw new IllegalArgumentException("Configuration must not be null!"); 
/* 217 */     if (node == null)
/* 219 */       throw new IllegalArgumentException("Node must not be null!"); 
/* 222 */     this.node = node;
/* 223 */     this.configuration = config;
/* 224 */     initSubnodeConfiguration(config);
/*     */   }
/*     */   
/*     */   public SubnodeConfiguration getConfiguration() {
/* 234 */     return this.configuration;
/*     */   }
/*     */   
/*     */   public ConfigurationNode getNode() {
/* 244 */     return this.node;
/*     */   }
/*     */   
/*     */   public String getBeanFactoryName() {
/* 255 */     return getConfiguration().getString("[@config-factory]");
/*     */   }
/*     */   
/*     */   public Object getBeanFactoryParameter() {
/* 266 */     return getConfiguration().getProperty("[@config-factoryParam]");
/*     */   }
/*     */   
/*     */   public String getBeanClassName() {
/* 277 */     return getConfiguration().getString("[@config-class]");
/*     */   }
/*     */   
/*     */   public Map getBeanProperties() {
/* 288 */     Map props = new HashMap();
/* 289 */     for (Iterator it = getNode().getAttributes().iterator(); it.hasNext(); ) {
/* 291 */       ConfigurationNode attr = it.next();
/* 292 */       if (!isReservedNode(attr))
/* 294 */         props.put(attr.getName(), interpolate(attr.getValue())); 
/*     */     } 
/* 298 */     return props;
/*     */   }
/*     */   
/*     */   public Map getNestedBeanDeclarations() {
/* 310 */     Map nested = new HashMap();
/* 311 */     for (Iterator it = getNode().getChildren().iterator(); it.hasNext(); ) {
/* 313 */       ConfigurationNode child = it.next();
/* 314 */       if (!isReservedNode(child)) {
/* 316 */         if (nested.containsKey(child.getName())) {
/*     */           List list;
/* 318 */           Object obj = nested.get(child.getName());
/* 320 */           if (obj instanceof List) {
/* 322 */             list = (List)obj;
/*     */           } else {
/* 326 */             list = new ArrayList();
/* 327 */             list.add(obj);
/* 328 */             nested.put(child.getName(), list);
/*     */           } 
/* 330 */           list.add(createBeanDeclaration(child));
/*     */           continue;
/*     */         } 
/* 334 */         nested.put(child.getName(), createBeanDeclaration(child));
/*     */       } 
/*     */     } 
/* 339 */     return nested;
/*     */   }
/*     */   
/*     */   protected Object interpolate(Object value) {
/* 353 */     return PropertyConverter.interpolate(value, (AbstractConfiguration)getConfiguration().getParent());
/*     */   }
/*     */   
/*     */   protected boolean isReservedNode(ConfigurationNode nd) {
/* 369 */     return (nd.isAttribute() && (nd.getName() == null || nd.getName().startsWith("config-")));
/*     */   }
/*     */   
/*     */   protected BeanDeclaration createBeanDeclaration(ConfigurationNode node) {
/* 390 */     List list = getConfiguration().configurationsAt(node.getName());
/* 391 */     if (list.size() == 1)
/* 393 */       return new XMLBeanDeclaration(list.get(0), node); 
/* 397 */     Iterator iter = list.iterator();
/* 398 */     while (iter.hasNext()) {
/* 400 */       SubnodeConfiguration config = iter.next();
/* 401 */       if (config.getRootNode().equals(node))
/* 403 */         return new XMLBeanDeclaration(config, node); 
/*     */     } 
/* 406 */     throw new ConfigurationRuntimeException("Unable to match node for " + node.getName());
/*     */   }
/*     */   
/*     */   private void initSubnodeConfiguration(SubnodeConfiguration conf) {
/* 418 */     conf.setThrowExceptionOnMissing(false);
/* 419 */     conf.setExpressionEngine(null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\beanutils\XMLBeanDeclaration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */