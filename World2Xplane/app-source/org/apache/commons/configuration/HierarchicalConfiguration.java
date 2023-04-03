/*      */ package org.apache.commons.configuration;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import java.util.Stack;
/*      */ import org.apache.commons.collections.iterators.SingletonIterator;
/*      */ import org.apache.commons.collections.set.ListOrderedSet;
/*      */ import org.apache.commons.configuration.event.ConfigurationEvent;
/*      */ import org.apache.commons.configuration.event.ConfigurationListener;
/*      */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*      */ import org.apache.commons.configuration.tree.ConfigurationNodeVisitor;
/*      */ import org.apache.commons.configuration.tree.ConfigurationNodeVisitorAdapter;
/*      */ import org.apache.commons.configuration.tree.DefaultConfigurationNode;
/*      */ import org.apache.commons.configuration.tree.DefaultExpressionEngine;
/*      */ import org.apache.commons.configuration.tree.ExpressionEngine;
/*      */ import org.apache.commons.configuration.tree.NodeAddData;
/*      */ import org.apache.commons.configuration.tree.ViewNode;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ 
/*      */ public class HierarchicalConfiguration extends AbstractConfiguration implements Serializable, Cloneable {
/*      */   public static final int EVENT_CLEAR_TREE = 10;
/*      */   
/*      */   public static final int EVENT_ADD_NODES = 11;
/*      */   
/*      */   public static final int EVENT_SUBNODE_CHANGED = 12;
/*      */   
/*      */   private static final long serialVersionUID = 3373812230395363192L;
/*      */   
/*      */   private static ExpressionEngine defaultExpressionEngine;
/*      */   
/*      */   private Node root;
/*      */   
/*      */   private ConfigurationNode rootNode;
/*      */   
/*      */   private transient ExpressionEngine expressionEngine;
/*      */   
/*      */   public HierarchicalConfiguration() {
/*  173 */     setRootNode((ConfigurationNode)new Node());
/*      */   }
/*      */   
/*      */   public HierarchicalConfiguration(HierarchicalConfiguration c) {
/*  187 */     this();
/*  188 */     if (c != null) {
/*  190 */       CloneVisitor visitor = new CloneVisitor();
/*  191 */       c.getRootNode().visit((ConfigurationNodeVisitor)visitor);
/*  192 */       setRootNode(visitor.getClone());
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object getReloadLock() {
/*  204 */     return this;
/*      */   }
/*      */   
/*      */   public Node getRoot() {
/*  217 */     if (this.root == null && this.rootNode != null)
/*  220 */       return new Node(this.rootNode); 
/*  223 */     return this.root;
/*      */   }
/*      */   
/*      */   public void setRoot(Node node) {
/*  236 */     if (node == null)
/*  238 */       throw new IllegalArgumentException("Root node must not be null!"); 
/*  240 */     this.root = node;
/*  241 */     this.rootNode = null;
/*      */   }
/*      */   
/*      */   public ConfigurationNode getRootNode() {
/*  252 */     return (this.rootNode != null) ? this.rootNode : (ConfigurationNode)this.root;
/*      */   }
/*      */   
/*      */   public void setRootNode(ConfigurationNode rootNode) {
/*  263 */     if (rootNode == null)
/*  265 */       throw new IllegalArgumentException("Root node must not be null!"); 
/*  267 */     this.rootNode = rootNode;
/*  270 */     this.root = (rootNode instanceof Node) ? (Node)rootNode : null;
/*      */   }
/*      */   
/*      */   public static synchronized ExpressionEngine getDefaultExpressionEngine() {
/*  281 */     if (defaultExpressionEngine == null)
/*  283 */       defaultExpressionEngine = (ExpressionEngine)new DefaultExpressionEngine(); 
/*  285 */     return defaultExpressionEngine;
/*      */   }
/*      */   
/*      */   public static synchronized void setDefaultExpressionEngine(ExpressionEngine engine) {
/*  299 */     if (engine == null)
/*  301 */       throw new IllegalArgumentException("Default expression engine must not be null!"); 
/*  304 */     defaultExpressionEngine = engine;
/*      */   }
/*      */   
/*      */   public ExpressionEngine getExpressionEngine() {
/*  317 */     return (this.expressionEngine != null) ? this.expressionEngine : getDefaultExpressionEngine();
/*      */   }
/*      */   
/*      */   public void setExpressionEngine(ExpressionEngine expressionEngine) {
/*  332 */     this.expressionEngine = expressionEngine;
/*      */   }
/*      */   
/*      */   public Object getProperty(String key) {
/*  344 */     List nodes = fetchNodeList(key);
/*  346 */     if (nodes.size() == 0)
/*  348 */       return null; 
/*  352 */     List list = new ArrayList();
/*  353 */     for (Iterator it = nodes.iterator(); it.hasNext(); ) {
/*  355 */       ConfigurationNode node = it.next();
/*  356 */       if (node.getValue() != null)
/*  358 */         list.add(node.getValue()); 
/*      */     } 
/*  362 */     if (list.size() < 1)
/*  364 */       return null; 
/*  368 */     return (list.size() == 1) ? list.get(0) : list;
/*      */   }
/*      */   
/*      */   protected void addPropertyDirect(String key, Object obj) {
/*  383 */     NodeAddData data = getExpressionEngine().prepareAdd(getRootNode(), key);
/*  384 */     ConfigurationNode node = processNodeAddData(data);
/*  385 */     node.setValue(obj);
/*      */   }
/*      */   
/*      */   public void addNodes(String key, Collection nodes) {
/*      */     ConfigurationNode parent;
/*  411 */     if (nodes == null || nodes.isEmpty())
/*      */       return; 
/*  416 */     fireEvent(11, key, nodes, true);
/*  418 */     List target = fetchNodeList(key);
/*  419 */     if (target.size() == 1) {
/*  422 */       parent = target.get(0);
/*      */     } else {
/*  427 */       parent = processNodeAddData(getExpressionEngine().prepareAdd(getRootNode(), key));
/*      */     } 
/*  431 */     if (parent.isAttribute())
/*  433 */       throw new IllegalArgumentException("Cannot add nodes to an attribute node!"); 
/*  437 */     for (Iterator it = nodes.iterator(); it.hasNext(); ) {
/*  439 */       ConfigurationNode child = it.next();
/*  440 */       if (child.isAttribute()) {
/*  442 */         parent.addAttribute(child);
/*      */       } else {
/*  446 */         parent.addChild(child);
/*      */       } 
/*  448 */       clearReferences(child);
/*      */     } 
/*  450 */     fireEvent(11, key, nodes, false);
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  461 */     return !nodeDefined(getRootNode());
/*      */   }
/*      */   
/*      */   public Configuration subset(String prefix) {
/*  484 */     Collection nodes = fetchNodeList(prefix);
/*  485 */     if (nodes.isEmpty())
/*  487 */       return new HierarchicalConfiguration(); 
/*  490 */     final HierarchicalConfiguration parent = this;
/*  491 */     HierarchicalConfiguration result = new HierarchicalConfiguration() {
/*      */         private final HierarchicalConfiguration val$parent;
/*      */         
/*      */         private final HierarchicalConfiguration this$0;
/*      */         
/*      */         protected Object interpolate(Object value) {
/*  496 */           return parent.interpolate(value);
/*      */         }
/*      */       };
/*  499 */     CloneVisitor visitor = new CloneVisitor();
/*  502 */     Object value = null;
/*  503 */     int valueCount = 0;
/*  504 */     for (Iterator it = nodes.iterator(); it.hasNext(); ) {
/*  506 */       ConfigurationNode nd = it.next();
/*  507 */       if (nd.getValue() != null) {
/*  509 */         value = nd.getValue();
/*  510 */         valueCount++;
/*      */       } 
/*  512 */       nd.visit((ConfigurationNodeVisitor)visitor);
/*  514 */       Iterator iterator1 = visitor.getClone().getChildren().iterator();
/*  515 */       while (iterator1.hasNext())
/*  517 */         result.getRootNode().addChild(iterator1.next()); 
/*  519 */       Iterator it2 = visitor.getClone().getAttributes().iterator();
/*  520 */       while (it2.hasNext())
/*  522 */         result.getRootNode().addAttribute(it2.next()); 
/*      */     } 
/*  528 */     if (valueCount == 1)
/*  530 */       result.getRootNode().setValue(value); 
/*  532 */     return result.isEmpty() ? new HierarchicalConfiguration() : result;
/*      */   }
/*      */   
/*      */   public SubnodeConfiguration configurationAt(String key, boolean supportUpdates) {
/*  585 */     List nodes = fetchNodeList(key);
/*  586 */     if (nodes.size() != 1)
/*  588 */       throw new IllegalArgumentException("Passed in key must select exactly one node: " + key); 
/*  591 */     return supportUpdates ? createSubnodeConfiguration(nodes.get(0), key) : createSubnodeConfiguration(nodes.get(0));
/*      */   }
/*      */   
/*      */   public SubnodeConfiguration configurationAt(String key) {
/*  608 */     return configurationAt(key, false);
/*      */   }
/*      */   
/*      */   public List configurationsAt(String key) {
/*  640 */     List nodes = fetchNodeList(key);
/*  641 */     List configs = new ArrayList(nodes.size());
/*  642 */     for (Iterator it = nodes.iterator(); it.hasNext();)
/*  644 */       configs.add(createSubnodeConfiguration(it.next())); 
/*  646 */     return configs;
/*      */   }
/*      */   
/*      */   protected SubnodeConfiguration createSubnodeConfiguration(ConfigurationNode node) {
/*  660 */     SubnodeConfiguration result = new SubnodeConfiguration(this, node);
/*  661 */     registerSubnodeConfiguration(result);
/*  662 */     return result;
/*      */   }
/*      */   
/*      */   protected SubnodeConfiguration createSubnodeConfiguration(ConfigurationNode node, String subnodeKey) {
/*  678 */     SubnodeConfiguration result = createSubnodeConfiguration(node);
/*  679 */     result.setSubnodeKey(subnodeKey);
/*  680 */     return result;
/*      */   }
/*      */   
/*      */   protected void subnodeConfigurationChanged(ConfigurationEvent event) {
/*  694 */     fireEvent(12, null, event, event.isBeforeUpdate());
/*      */   }
/*      */   
/*      */   void registerSubnodeConfiguration(SubnodeConfiguration config) {
/*  707 */     config.addConfigurationListener(new ConfigurationListener() {
/*      */           private final HierarchicalConfiguration this$0;
/*      */           
/*      */           public void configurationChanged(ConfigurationEvent event) {
/*  711 */             HierarchicalConfiguration.this.subnodeConfigurationChanged(event);
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   public boolean containsKey(String key) {
/*  728 */     return (getProperty(key) != null);
/*      */   }
/*      */   
/*      */   public void setProperty(String key, Object value) {
/*      */     SingletonIterator singletonIterator;
/*  739 */     fireEvent(3, key, value, true);
/*  742 */     Iterator itNodes = fetchNodeList(key).iterator();
/*  744 */     if (!isDelimiterParsingDisabled()) {
/*  746 */       Iterator itValues = PropertyConverter.toIterator(value, getListDelimiter());
/*      */     } else {
/*  750 */       singletonIterator = new SingletonIterator(value);
/*      */     } 
/*  753 */     while (itNodes.hasNext() && singletonIterator.hasNext())
/*  755 */       ((ConfigurationNode)itNodes.next()).setValue(singletonIterator.next()); 
/*  759 */     while (singletonIterator.hasNext())
/*  761 */       addPropertyDirect(key, singletonIterator.next()); 
/*  765 */     while (itNodes.hasNext())
/*  767 */       clearNode(itNodes.next()); 
/*  770 */     fireEvent(3, key, value, false);
/*      */   }
/*      */   
/*      */   public void clear() {
/*  780 */     fireEvent(4, null, null, true);
/*  781 */     getRootNode().removeAttributes();
/*  782 */     getRootNode().removeChildren();
/*  783 */     getRootNode().setValue(null);
/*  784 */     fireEvent(4, null, null, false);
/*      */   }
/*      */   
/*      */   public void clearTree(String key) {
/*  797 */     fireEvent(10, key, null, true);
/*  798 */     List nodes = fetchNodeList(key);
/*  800 */     for (Iterator it = nodes.iterator(); it.hasNext();)
/*  802 */       removeNode(it.next()); 
/*  804 */     fireEvent(10, key, nodes, false);
/*      */   }
/*      */   
/*      */   public void clearProperty(String key) {
/*  816 */     fireEvent(2, key, null, true);
/*  817 */     List nodes = fetchNodeList(key);
/*  819 */     for (Iterator it = nodes.iterator(); it.hasNext();)
/*  821 */       clearNode(it.next()); 
/*  824 */     fireEvent(2, key, null, false);
/*      */   }
/*      */   
/*      */   public Iterator getKeys() {
/*  836 */     DefinedKeysVisitor visitor = new DefinedKeysVisitor();
/*  837 */     getRootNode().visit((ConfigurationNodeVisitor)visitor);
/*  839 */     return visitor.getKeyList().iterator();
/*      */   }
/*      */   
/*      */   public Iterator getKeys(String prefix) {
/*  855 */     DefinedKeysVisitor visitor = new DefinedKeysVisitor(prefix);
/*  856 */     if (containsKey(prefix))
/*  859 */       visitor.getKeyList().add(prefix); 
/*  862 */     List nodes = fetchNodeList(prefix);
/*  864 */     for (Iterator itNodes = nodes.iterator(); itNodes.hasNext(); ) {
/*  866 */       ConfigurationNode node = itNodes.next();
/*  867 */       for (Iterator iterator1 = node.getChildren().iterator(); iterator1.hasNext();)
/*  869 */         ((ConfigurationNode)iterator1.next()).visit((ConfigurationNodeVisitor)visitor); 
/*  871 */       for (Iterator it = node.getAttributes().iterator(); it.hasNext();)
/*  873 */         ((ConfigurationNode)it.next()).visit((ConfigurationNodeVisitor)visitor); 
/*      */     } 
/*  877 */     return visitor.getKeyList().iterator();
/*      */   }
/*      */   
/*      */   public int getMaxIndex(String key) {
/*  891 */     return fetchNodeList(key).size() - 1;
/*      */   }
/*      */   
/*      */   public Object clone() {
/*      */     try {
/*  906 */       HierarchicalConfiguration copy = (HierarchicalConfiguration)super.clone();
/*  910 */       CloneVisitor v = new CloneVisitor();
/*  911 */       getRootNode().visit((ConfigurationNodeVisitor)v);
/*  912 */       copy.setRootNode(v.getClone());
/*  914 */       return copy;
/*  916 */     } catch (CloneNotSupportedException cex) {
/*  919 */       throw new ConfigurationRuntimeException(cex);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Configuration interpolatedConfiguration() {
/*  935 */     HierarchicalConfiguration c = (HierarchicalConfiguration)clone();
/*  936 */     c.getRootNode().visit((ConfigurationNodeVisitor)new ConfigurationNodeVisitorAdapter() {
/*      */           private final HierarchicalConfiguration this$0;
/*      */           
/*      */           public void visitAfterChildren(ConfigurationNode node) {
/*  940 */             node.setValue(HierarchicalConfiguration.this.interpolate(node.getValue()));
/*      */           }
/*      */         });
/*  943 */     return c;
/*      */   }
/*      */   
/*      */   protected List fetchNodeList(String key) {
/*  955 */     return getExpressionEngine().query(getRootNode(), key);
/*      */   }
/*      */   
/*      */   protected void findPropertyNodes(ConfigurationKey.KeyIterator keyPart, Node node, Collection nodes) {}
/*      */   
/*      */   protected boolean nodeDefined(Node node) {
/*  986 */     return nodeDefined((ConfigurationNode)node);
/*      */   }
/*      */   
/*      */   protected boolean nodeDefined(ConfigurationNode node) {
/*  997 */     DefinedVisitor visitor = new DefinedVisitor();
/*  998 */     node.visit((ConfigurationNodeVisitor)visitor);
/*  999 */     return visitor.isDefined();
/*      */   }
/*      */   
/*      */   protected void removeNode(Node node) {
/* 1013 */     removeNode((ConfigurationNode)node);
/*      */   }
/*      */   
/*      */   protected void removeNode(ConfigurationNode node) {
/* 1025 */     ConfigurationNode parent = node.getParentNode();
/* 1026 */     if (parent != null) {
/* 1028 */       parent.removeChild(node);
/* 1029 */       if (!nodeDefined(parent))
/* 1031 */         removeNode(parent); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void clearNode(Node node) {
/* 1046 */     clearNode((ConfigurationNode)node);
/*      */   }
/*      */   
/*      */   protected void clearNode(ConfigurationNode node) {
/* 1057 */     node.setValue(null);
/* 1058 */     if (!nodeDefined(node))
/* 1060 */       removeNode(node); 
/*      */   }
/*      */   
/*      */   protected Node fetchAddNode(ConfigurationKey.KeyIterator keyIt, Node startNode) {
/* 1080 */     return null;
/*      */   }
/*      */   
/*      */   protected Node findLastPathNode(ConfigurationKey.KeyIterator keyIt, Node node) {
/* 1099 */     return null;
/*      */   }
/*      */   
/*      */   protected Node createAddPath(ConfigurationKey.KeyIterator keyIt, Node root) {
/* 1118 */     return null;
/*      */   }
/*      */   
/*      */   protected Node createNode(String name) {
/* 1132 */     return new Node(name);
/*      */   }
/*      */   
/*      */   private ConfigurationNode processNodeAddData(NodeAddData data) {
/*      */     Node node1;
/* 1145 */     ConfigurationNode node = data.getParent();
/* 1148 */     for (Iterator it = data.getPathNodes().iterator(); it.hasNext(); ) {
/* 1150 */       Node node3 = createNode(it.next());
/* 1151 */       node.addChild((ConfigurationNode)node3);
/* 1152 */       node1 = node3;
/*      */     } 
/* 1156 */     Node node2 = createNode(data.getNewNodeName());
/* 1157 */     if (data.isAttribute()) {
/* 1159 */       node1.addAttribute((ConfigurationNode)node2);
/*      */     } else {
/* 1163 */       node1.addChild((ConfigurationNode)node2);
/*      */     } 
/* 1165 */     return (ConfigurationNode)node2;
/*      */   }
/*      */   
/*      */   protected static void clearReferences(ConfigurationNode node) {
/* 1181 */     node.visit((ConfigurationNodeVisitor)new ConfigurationNodeVisitorAdapter() {
/*      */           public void visitBeforeChildren(ConfigurationNode node) {
/* 1185 */             node.setReference(null);
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   private static Node getNodeFor(Object obj) {
/*      */     Node nd;
/* 1203 */     if (obj instanceof ViewNode) {
/* 1205 */       final ViewNode viewNode = (ViewNode)obj;
/* 1206 */       nd = new Node((ConfigurationNode)viewNode) {
/*      */           private final ViewNode val$viewNode;
/*      */           
/*      */           public void setReference(Object reference) {
/* 1210 */             super.setReference(reference);
/* 1212 */             viewNode.setReference(reference);
/*      */           }
/*      */         };
/*      */     } else {
/* 1218 */       nd = (Node)obj;
/*      */     } 
/* 1220 */     return nd;
/*      */   }
/*      */   
/*      */   public static class Node extends DefaultConfigurationNode implements Serializable {
/*      */     private static final long serialVersionUID = -6357500633536941775L;
/*      */     
/*      */     public Node() {}
/*      */     
/*      */     public Node(String name) {
/* 1252 */       super(name);
/*      */     }
/*      */     
/*      */     public Node(String name, Object value) {
/* 1263 */       super(name, value);
/*      */     }
/*      */     
/*      */     public Node(ConfigurationNode src) {
/* 1275 */       this(src.getName(), src.getValue());
/* 1276 */       setReference(src.getReference());
/* 1277 */       for (Iterator iterator1 = src.getChildren().iterator(); iterator1.hasNext(); ) {
/* 1279 */         ConfigurationNode nd = iterator1.next();
/* 1281 */         ConfigurationNode parent = nd.getParentNode();
/* 1282 */         addChild(nd);
/* 1283 */         nd.setParentNode(parent);
/*      */       } 
/* 1286 */       for (Iterator it = src.getAttributes().iterator(); it.hasNext(); ) {
/* 1288 */         ConfigurationNode nd = it.next();
/* 1290 */         ConfigurationNode parent = nd.getParentNode();
/* 1291 */         addAttribute(nd);
/* 1292 */         nd.setParentNode(parent);
/*      */       } 
/*      */     }
/*      */     
/*      */     public Node getParent() {
/* 1303 */       return (Node)getParentNode();
/*      */     }
/*      */     
/*      */     public void setParent(Node node) {
/* 1313 */       setParentNode((ConfigurationNode)node);
/*      */     }
/*      */     
/*      */     public void addChild(Node node) {
/* 1323 */       addChild((ConfigurationNode)node);
/*      */     }
/*      */     
/*      */     public boolean hasChildren() {
/* 1333 */       return (getChildrenCount() > 0 || getAttributeCount() > 0);
/*      */     }
/*      */     
/*      */     public boolean remove(Node child) {
/* 1344 */       return child.isAttribute() ? removeAttribute((ConfigurationNode)child) : removeChild((ConfigurationNode)child);
/*      */     }
/*      */     
/*      */     public boolean remove(String name) {
/* 1355 */       boolean childrenRemoved = removeChild(name);
/* 1356 */       boolean attrsRemoved = removeAttribute(name);
/* 1357 */       return (childrenRemoved || attrsRemoved);
/*      */     }
/*      */     
/*      */     public void visit(HierarchicalConfiguration.NodeVisitor visitor, ConfigurationKey key) {
/* 1373 */       int length = 0;
/* 1374 */       if (key != null) {
/* 1376 */         length = key.length();
/* 1377 */         if (getName() != null)
/* 1379 */           key.append(StringUtils.replace(isAttribute() ? ConfigurationKey.constructAttributeKey(getName()) : getName(), String.valueOf('.'), ConfigurationKey.ESCAPED_DELIMITER)); 
/*      */       } 
/* 1391 */       visitor.visitBeforeChildren(this, key);
/* 1393 */       Iterator it = getChildren().iterator();
/* 1394 */       while (it.hasNext() && !visitor.terminate()) {
/* 1396 */         Object obj = it.next();
/* 1397 */         HierarchicalConfiguration.getNodeFor(obj).visit(visitor, key);
/*      */       } 
/* 1399 */       it = getAttributes().iterator();
/* 1400 */       while (it.hasNext() && !visitor.terminate()) {
/* 1402 */         Object obj = it.next();
/* 1403 */         HierarchicalConfiguration.getNodeFor(obj).visit(visitor, key);
/*      */       } 
/* 1406 */       visitor.visitAfterChildren(this, key);
/* 1407 */       if (key != null)
/* 1409 */         key.setLength(length); 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class NodeVisitor {
/*      */     public void visitBeforeChildren(HierarchicalConfiguration.Node node, ConfigurationKey key) {}
/*      */     
/*      */     public void visitAfterChildren(HierarchicalConfiguration.Node node, ConfigurationKey key) {}
/*      */     
/*      */     public boolean terminate() {
/* 1460 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   static class DefinedVisitor extends ConfigurationNodeVisitorAdapter {
/*      */     private boolean defined;
/*      */     
/*      */     public boolean terminate() {
/* 1483 */       return isDefined();
/*      */     }
/*      */     
/*      */     public void visitBeforeChildren(ConfigurationNode node) {
/* 1493 */       this.defined = (node.getValue() != null);
/*      */     }
/*      */     
/*      */     public boolean isDefined() {
/* 1503 */       return this.defined;
/*      */     }
/*      */   }
/*      */   
/*      */   class DefinedKeysVisitor extends ConfigurationNodeVisitorAdapter {
/* 1524 */     private Set keyList = (Set)new ListOrderedSet();
/*      */     
/* 1525 */     private Stack parentKeys = new Stack();
/*      */     
/*      */     private final HierarchicalConfiguration this$0;
/*      */     
/*      */     public DefinedKeysVisitor() {}
/*      */     
/*      */     public DefinedKeysVisitor(String prefix) {
/* 1536 */       this();
/* 1537 */       this.parentKeys.push(prefix);
/*      */     }
/*      */     
/*      */     public Set getKeyList() {
/* 1547 */       return this.keyList;
/*      */     }
/*      */     
/*      */     public void visitAfterChildren(ConfigurationNode node) {
/* 1558 */       this.parentKeys.pop();
/*      */     }
/*      */     
/*      */     public void visitBeforeChildren(ConfigurationNode node) {
/* 1569 */       String parentKey = this.parentKeys.isEmpty() ? null : this.parentKeys.peek();
/* 1571 */       String key = HierarchicalConfiguration.this.getExpressionEngine().nodeKey(node, parentKey);
/* 1572 */       this.parentKeys.push(key);
/* 1573 */       if (node.getValue() != null)
/* 1575 */         this.keyList.add(key); 
/*      */     }
/*      */   }
/*      */   
/*      */   static class CloneVisitor extends ConfigurationNodeVisitorAdapter {
/* 1597 */     private Stack copyStack = new Stack();
/*      */     
/*      */     private ConfigurationNode result;
/*      */     
/*      */     public void visitAfterChildren(ConfigurationNode node) {
/* 1607 */       ConfigurationNode copy = this.copyStack.pop();
/* 1608 */       if (this.copyStack.isEmpty())
/* 1610 */         this.result = copy; 
/*      */     }
/*      */     
/*      */     public void visitBeforeChildren(ConfigurationNode node) {
/* 1621 */       ConfigurationNode copy = (ConfigurationNode)node.clone();
/* 1622 */       copy.setParentNode(null);
/* 1624 */       if (!this.copyStack.isEmpty())
/* 1626 */         if (node.isAttribute()) {
/* 1628 */           ((ConfigurationNode)this.copyStack.peek()).addAttribute(copy);
/*      */         } else {
/* 1632 */           ((ConfigurationNode)this.copyStack.peek()).addChild(copy);
/*      */         }  
/* 1636 */       this.copyStack.push(copy);
/*      */     }
/*      */     
/*      */     public ConfigurationNode getClone() {
/* 1647 */       return this.result;
/*      */     }
/*      */   }
/*      */   
/*      */   protected static abstract class BuilderVisitor extends NodeVisitor {
/*      */     public void visitBeforeChildren(HierarchicalConfiguration.Node node, ConfigurationKey key) {
/* 1676 */       Collection subNodes = new LinkedList(node.getChildren());
/* 1677 */       subNodes.addAll(node.getAttributes());
/* 1678 */       Iterator children = subNodes.iterator();
/* 1679 */       HierarchicalConfiguration.Node sibling1 = null;
/* 1680 */       HierarchicalConfiguration.Node nd = null;
/* 1682 */       while (children.hasNext()) {
/*      */         do {
/* 1687 */           sibling1 = nd;
/* 1688 */           Object obj = children.next();
/* 1689 */           nd = HierarchicalConfiguration.getNodeFor(obj);
/* 1690 */         } while (nd.getReference() != null && children.hasNext());
/* 1692 */         if (nd.getReference() == null) {
/* 1695 */           List newNodes = new LinkedList();
/* 1696 */           newNodes.add(nd);
/* 1697 */           while (children.hasNext()) {
/* 1699 */             Object obj = children.next();
/* 1700 */             nd = HierarchicalConfiguration.getNodeFor(obj);
/* 1701 */             if (nd.getReference() == null)
/* 1703 */               newNodes.add(nd); 
/*      */           } 
/* 1712 */           HierarchicalConfiguration.Node sibling2 = (nd.getReference() == null) ? null : nd;
/* 1713 */           for (Iterator it = newNodes.iterator(); it.hasNext(); ) {
/* 1715 */             HierarchicalConfiguration.Node insertNode = it.next();
/* 1716 */             if (insertNode.getReference() == null) {
/* 1718 */               Object ref = insert(insertNode, node, sibling1, sibling2);
/* 1719 */               if (ref != null)
/* 1721 */                 insertNode.setReference(ref); 
/* 1723 */               sibling1 = insertNode;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     protected abstract Object insert(HierarchicalConfiguration.Node param1Node1, HierarchicalConfiguration.Node param1Node2, HierarchicalConfiguration.Node param1Node3, HierarchicalConfiguration.Node param1Node4);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\HierarchicalConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */