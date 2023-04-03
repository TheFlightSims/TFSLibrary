/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.configuration.event.ConfigurationEvent;
/*     */ import org.apache.commons.configuration.event.ConfigurationListener;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ import org.apache.commons.configuration.tree.DefaultConfigurationKey;
/*     */ import org.apache.commons.configuration.tree.DefaultConfigurationNode;
/*     */ import org.apache.commons.configuration.tree.DefaultExpressionEngine;
/*     */ import org.apache.commons.configuration.tree.ExpressionEngine;
/*     */ import org.apache.commons.configuration.tree.NodeCombiner;
/*     */ import org.apache.commons.configuration.tree.TreeUtils;
/*     */ import org.apache.commons.configuration.tree.UnionCombiner;
/*     */ import org.apache.commons.configuration.tree.ViewNode;
/*     */ 
/*     */ public class CombinedConfiguration extends HierarchicalReloadableConfiguration implements ConfigurationListener, Cloneable {
/*     */   public static final int EVENT_COMBINED_INVALIDATE = 40;
/*     */   
/*     */   private static final long serialVersionUID = 8338574525528692307L;
/*     */   
/* 187 */   private static final DefaultExpressionEngine AT_ENGINE = new DefaultExpressionEngine();
/*     */   
/* 190 */   private static final NodeCombiner DEFAULT_COMBINER = (NodeCombiner)new UnionCombiner();
/*     */   
/*     */   private static final String PROP_RELOAD_CHECK = "CombinedConfigurationReloadCheck";
/*     */   
/*     */   private NodeCombiner nodeCombiner;
/*     */   
/*     */   private volatile ConfigurationNode combinedRoot;
/*     */   
/*     */   private List configurations;
/*     */   
/*     */   private Map namedConfigurations;
/*     */   
/*     */   private boolean ignoreReloadExceptions = true;
/*     */   
/*     */   private boolean reloadRequired;
/*     */   
/*     */   private ExpressionEngine conversionExpressionEngine;
/*     */   
/*     */   private boolean forceReloadCheck;
/*     */   
/*     */   public CombinedConfiguration(NodeCombiner comb) {
/* 231 */     setNodeCombiner((comb != null) ? comb : DEFAULT_COMBINER);
/* 232 */     clear();
/*     */   }
/*     */   
/*     */   public CombinedConfiguration(NodeCombiner comb, Lock lock) {
/* 237 */     super(lock);
/* 238 */     setNodeCombiner((comb != null) ? comb : DEFAULT_COMBINER);
/* 239 */     clear();
/*     */   }
/*     */   
/*     */   public CombinedConfiguration(Lock lock) {
/* 244 */     this((NodeCombiner)null, lock);
/*     */   }
/*     */   
/*     */   public CombinedConfiguration() {
/* 255 */     this((NodeCombiner)null, (Lock)null);
/*     */   }
/*     */   
/*     */   public NodeCombiner getNodeCombiner() {
/* 266 */     return this.nodeCombiner;
/*     */   }
/*     */   
/*     */   public void setNodeCombiner(NodeCombiner nodeCombiner) {
/* 280 */     if (nodeCombiner == null)
/* 282 */       throw new IllegalArgumentException("Node combiner must not be null!"); 
/* 285 */     this.nodeCombiner = nodeCombiner;
/* 286 */     invalidate();
/*     */   }
/*     */   
/*     */   public boolean isForceReloadCheck() {
/* 297 */     return this.forceReloadCheck;
/*     */   }
/*     */   
/*     */   public void setForceReloadCheck(boolean forceReloadCheck) {
/* 314 */     this.forceReloadCheck = forceReloadCheck;
/*     */   }
/*     */   
/*     */   public ExpressionEngine getConversionExpressionEngine() {
/* 326 */     return this.conversionExpressionEngine;
/*     */   }
/*     */   
/*     */   public void setConversionExpressionEngine(ExpressionEngine conversionExpressionEngine) {
/* 347 */     this.conversionExpressionEngine = conversionExpressionEngine;
/*     */   }
/*     */   
/*     */   public boolean isIgnoreReloadExceptions() {
/* 356 */     return this.ignoreReloadExceptions;
/*     */   }
/*     */   
/*     */   public void setIgnoreReloadExceptions(boolean ignoreReloadExceptions) {
/* 367 */     this.ignoreReloadExceptions = ignoreReloadExceptions;
/*     */   }
/*     */   
/*     */   public void addConfiguration(AbstractConfiguration config, String name, String at) {
/* 389 */     if (config == null)
/* 391 */       throw new IllegalArgumentException("Added configuration must not be null!"); 
/* 394 */     if (name != null && this.namedConfigurations.containsKey(name))
/* 396 */       throw new ConfigurationRuntimeException("A configuration with the name '" + name + "' already exists in this combined configuration!"); 
/* 402 */     ConfigData cd = new ConfigData(config, name, at);
/* 403 */     if (getLogger().isDebugEnabled())
/* 405 */       getLogger().debug("Adding configuration " + config + " with name " + name); 
/* 407 */     this.configurations.add(cd);
/* 408 */     if (name != null)
/* 410 */       this.namedConfigurations.put(name, config); 
/* 413 */     config.addConfigurationListener(this);
/* 414 */     invalidate();
/*     */   }
/*     */   
/*     */   public void addConfiguration(AbstractConfiguration config, String name) {
/* 427 */     addConfiguration(config, name, (String)null);
/*     */   }
/*     */   
/*     */   public void addConfiguration(AbstractConfiguration config) {
/* 439 */     addConfiguration(config, (String)null, (String)null);
/*     */   }
/*     */   
/*     */   public int getNumberOfConfigurations() {
/* 450 */     return this.configurations.size();
/*     */   }
/*     */   
/*     */   public Configuration getConfiguration(int index) {
/* 463 */     ConfigData cd = this.configurations.get(index);
/* 464 */     return cd.getConfiguration();
/*     */   }
/*     */   
/*     */   public Configuration getConfiguration(String name) {
/* 476 */     return (Configuration)this.namedConfigurations.get(name);
/*     */   }
/*     */   
/*     */   public List getConfigurations() {
/* 486 */     List list = new ArrayList();
/* 487 */     Iterator iter = this.configurations.iterator();
/* 488 */     while (iter.hasNext())
/* 490 */       list.add(((ConfigData)iter.next()).getConfiguration()); 
/* 492 */     return list;
/*     */   }
/*     */   
/*     */   public List getConfigurationNameList() {
/* 504 */     List list = new ArrayList();
/* 505 */     Iterator iter = this.configurations.iterator();
/* 506 */     while (iter.hasNext())
/* 508 */       list.add(((ConfigData)iter.next()).getName()); 
/* 510 */     return list;
/*     */   }
/*     */   
/*     */   public boolean removeConfiguration(Configuration config) {
/* 521 */     for (int index = 0; index < getNumberOfConfigurations(); index++) {
/* 523 */       if (((ConfigData)this.configurations.get(index)).getConfiguration() == config) {
/* 525 */         removeConfigurationAt(index);
/* 526 */         return true;
/*     */       } 
/*     */     } 
/* 530 */     return false;
/*     */   }
/*     */   
/*     */   public Configuration removeConfigurationAt(int index) {
/* 541 */     ConfigData cd = this.configurations.remove(index);
/* 542 */     if (cd.getName() != null)
/* 544 */       this.namedConfigurations.remove(cd.getName()); 
/* 546 */     cd.getConfiguration().removeConfigurationListener(this);
/* 547 */     invalidate();
/* 548 */     return cd.getConfiguration();
/*     */   }
/*     */   
/*     */   public Configuration removeConfiguration(String name) {
/* 560 */     Configuration conf = getConfiguration(name);
/* 561 */     if (conf != null)
/* 563 */       removeConfiguration(conf); 
/* 565 */     return conf;
/*     */   }
/*     */   
/*     */   public Set getConfigurationNames() {
/* 578 */     return this.namedConfigurations.keySet();
/*     */   }
/*     */   
/*     */   public void invalidate() {
/* 591 */     this.reloadRequired = true;
/* 592 */     fireEvent(40, null, null, false);
/*     */   }
/*     */   
/*     */   public void configurationChanged(ConfigurationEvent event) {
/* 604 */     if (event.getType() == 21) {
/* 606 */       fireEvent(event.getType(), event.getPropertyName(), event.getPropertyValue(), event.isBeforeUpdate());
/* 608 */     } else if (!event.isBeforeUpdate()) {
/* 610 */       invalidate();
/*     */     } 
/*     */   }
/*     */   
/*     */   public ConfigurationNode getRootNode() {
/* 623 */     synchronized (getReloadLock()) {
/* 625 */       if (this.reloadRequired || this.combinedRoot == null) {
/* 627 */         this.combinedRoot = constructCombinedNode();
/* 628 */         this.reloadRequired = false;
/*     */       } 
/* 630 */       return this.combinedRoot;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 639 */     fireEvent(4, null, null, true);
/* 640 */     this.configurations = new ArrayList();
/* 641 */     this.namedConfigurations = new HashMap();
/* 642 */     fireEvent(4, null, null, false);
/* 643 */     invalidate();
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 657 */     CombinedConfiguration copy = (CombinedConfiguration)super.clone();
/* 658 */     copy.clear();
/* 659 */     for (Iterator it = this.configurations.iterator(); it.hasNext(); ) {
/* 661 */       ConfigData cd = it.next();
/* 662 */       copy.addConfiguration((AbstractConfiguration)ConfigurationUtils.cloneConfiguration(cd.getConfiguration()), cd.getName(), cd.getAt());
/*     */     } 
/* 667 */     copy.setRootNode((ConfigurationNode)new DefaultConfigurationNode());
/* 668 */     return copy;
/*     */   }
/*     */   
/*     */   public Configuration getSource(String key) {
/* 696 */     if (key == null)
/* 698 */       throw new IllegalArgumentException("Key must not be null!"); 
/* 701 */     List nodes = fetchNodeList(key);
/* 702 */     if (nodes.isEmpty())
/* 704 */       return null; 
/* 707 */     Iterator it = nodes.iterator();
/* 708 */     Configuration source = findSourceConfiguration(it.next());
/* 710 */     while (it.hasNext()) {
/* 712 */       Configuration src = findSourceConfiguration(it.next());
/* 714 */       if (src != source)
/* 716 */         throw new IllegalArgumentException("The key " + key + " is defined by multiple sources!"); 
/*     */     } 
/* 721 */     return source;
/*     */   }
/*     */   
/*     */   protected List fetchNodeList(String key) {
/* 735 */     if (isForceReloadCheck())
/* 737 */       performReloadCheck(); 
/* 740 */     return super.fetchNodeList(key);
/*     */   }
/*     */   
/*     */   protected void performReloadCheck() {
/* 754 */     for (Iterator it = this.configurations.iterator(); it.hasNext();) {
/*     */       try {
/* 760 */         ((ConfigData)it.next()).getConfiguration().getProperty("CombinedConfigurationReloadCheck");
/* 763 */       } catch (Exception ex) {
/* 765 */         if (!this.ignoreReloadExceptions)
/* 767 */           throw new ConfigurationRuntimeException(ex); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private ConfigurationNode constructCombinedNode() {
/* 780 */     if (getNumberOfConfigurations() < 1) {
/* 782 */       if (getLogger().isDebugEnabled())
/* 784 */         getLogger().debug("No configurations defined for " + this); 
/* 786 */       return (ConfigurationNode)new ViewNode();
/*     */     } 
/* 791 */     Iterator it = this.configurations.iterator();
/* 792 */     ConfigurationNode node = ((ConfigData)it.next()).getTransformedRoot();
/* 794 */     while (it.hasNext())
/* 796 */       node = getNodeCombiner().combine(node, ((ConfigData)it.next()).getTransformedRoot()); 
/* 799 */     if (getLogger().isDebugEnabled()) {
/* 801 */       ByteArrayOutputStream os = new ByteArrayOutputStream();
/* 802 */       PrintStream stream = new PrintStream(os);
/* 803 */       TreeUtils.printTree(stream, node);
/* 804 */       getLogger().debug(os.toString());
/*     */     } 
/* 806 */     return node;
/*     */   }
/*     */   
/*     */   private Configuration findSourceConfiguration(ConfigurationNode node) {
/* 818 */     synchronized (getReloadLock()) {
/* 820 */       ConfigurationNode root = null;
/* 821 */       ConfigurationNode current = node;
/* 824 */       while (current != null) {
/* 826 */         root = current;
/* 827 */         current = current.getParentNode();
/*     */       } 
/* 831 */       for (Iterator it = this.configurations.iterator(); it.hasNext(); ) {
/* 833 */         ConfigData cd = it.next();
/* 834 */         if (root == cd.getRootNode())
/* 836 */           return cd.getConfiguration(); 
/*     */       } 
/*     */     } 
/* 841 */     return this;
/*     */   }
/*     */   
/*     */   class ConfigData {
/*     */     private AbstractConfiguration configuration;
/*     */     
/*     */     private String name;
/*     */     
/*     */     private Collection atPath;
/*     */     
/*     */     private String at;
/*     */     
/*     */     private ConfigurationNode rootNode;
/*     */     
/*     */     private final CombinedConfiguration this$0;
/*     */     
/*     */     public ConfigData(AbstractConfiguration config, String n, String at) {
/* 875 */       this.configuration = config;
/* 876 */       this.name = n;
/* 877 */       this.atPath = parseAt(at);
/* 878 */       this.at = at;
/*     */     }
/*     */     
/*     */     public AbstractConfiguration getConfiguration() {
/* 888 */       return this.configuration;
/*     */     }
/*     */     
/*     */     public String getName() {
/* 898 */       return this.name;
/*     */     }
/*     */     
/*     */     public String getAt() {
/* 908 */       return this.at;
/*     */     }
/*     */     
/*     */     public ConfigurationNode getRootNode() {
/* 919 */       return this.rootNode;
/*     */     }
/*     */     
/*     */     public ConfigurationNode getTransformedRoot() {
/* 931 */       ViewNode result = new ViewNode();
/* 932 */       ViewNode atParent = result;
/* 934 */       if (this.atPath != null)
/* 937 */         for (Iterator it = this.atPath.iterator(); it.hasNext(); ) {
/* 939 */           ViewNode node = new ViewNode();
/* 940 */           node.setName(it.next());
/* 941 */           atParent.addChild((ConfigurationNode)node);
/* 942 */           atParent = node;
/*     */         }  
/* 947 */       ConfigurationNode root = ConfigurationUtils.convertToHierarchical(getConfiguration(), CombinedConfiguration.this.getConversionExpressionEngine()).getRootNode();
/* 950 */       atParent.appendChildren(root);
/* 951 */       atParent.appendAttributes(root);
/* 952 */       this.rootNode = root;
/* 954 */       return (ConfigurationNode)result;
/*     */     }
/*     */     
/*     */     private Collection parseAt(String at) {
/* 965 */       if (at == null)
/* 967 */         return null; 
/* 970 */       Collection result = new ArrayList();
/* 971 */       DefaultConfigurationKey.KeyIterator it = (new DefaultConfigurationKey(CombinedConfiguration.AT_ENGINE, at)).iterator();
/* 973 */       while (it.hasNext())
/* 975 */         result.add(it.nextKey()); 
/* 977 */       return result;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\CombinedConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */