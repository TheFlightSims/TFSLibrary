/*     */ package org.apache.commons.configuration.tree;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultExpressionEngine implements ExpressionEngine {
/*     */   public static final String DEFAULT_PROPERTY_DELIMITER = ".";
/*     */   
/*     */   public static final String DEFAULT_ESCAPED_DELIMITER = "..";
/*     */   
/*     */   public static final String DEFAULT_ATTRIBUTE_START = "[@";
/*     */   
/*     */   public static final String DEFAULT_ATTRIBUTE_END = "]";
/*     */   
/*     */   public static final String DEFAULT_INDEX_START = "(";
/*     */   
/*     */   public static final String DEFAULT_INDEX_END = ")";
/*     */   
/* 130 */   private String propertyDelimiter = ".";
/*     */   
/* 133 */   private String escapedDelimiter = "..";
/*     */   
/* 136 */   private String attributeStart = "[@";
/*     */   
/* 139 */   private String attributeEnd = "]";
/*     */   
/* 142 */   private String indexStart = "(";
/*     */   
/* 145 */   private String indexEnd = ")";
/*     */   
/*     */   public String getAttributeEnd() {
/* 154 */     return this.attributeEnd;
/*     */   }
/*     */   
/*     */   public void setAttributeEnd(String attributeEnd) {
/* 165 */     this.attributeEnd = attributeEnd;
/*     */   }
/*     */   
/*     */   public String getAttributeStart() {
/* 175 */     return this.attributeStart;
/*     */   }
/*     */   
/*     */   public void setAttributeStart(String attributeStart) {
/* 186 */     this.attributeStart = attributeStart;
/*     */   }
/*     */   
/*     */   public String getEscapedDelimiter() {
/* 196 */     return this.escapedDelimiter;
/*     */   }
/*     */   
/*     */   public void setEscapedDelimiter(String escapedDelimiter) {
/* 210 */     this.escapedDelimiter = escapedDelimiter;
/*     */   }
/*     */   
/*     */   public String getIndexEnd() {
/* 220 */     return this.indexEnd;
/*     */   }
/*     */   
/*     */   public void setIndexEnd(String indexEnd) {
/* 230 */     this.indexEnd = indexEnd;
/*     */   }
/*     */   
/*     */   public String getIndexStart() {
/* 240 */     return this.indexStart;
/*     */   }
/*     */   
/*     */   public void setIndexStart(String indexStart) {
/* 251 */     this.indexStart = indexStart;
/*     */   }
/*     */   
/*     */   public String getPropertyDelimiter() {
/* 261 */     return this.propertyDelimiter;
/*     */   }
/*     */   
/*     */   public void setPropertyDelimiter(String propertyDelimiter) {
/* 272 */     this.propertyDelimiter = propertyDelimiter;
/*     */   }
/*     */   
/*     */   public List query(ConfigurationNode root, String key) {
/* 285 */     List nodes = new LinkedList();
/* 286 */     findNodesForKey((new DefaultConfigurationKey(this, key)).iterator(), root, nodes);
/* 288 */     return nodes;
/*     */   }
/*     */   
/*     */   public String nodeKey(ConfigurationNode node, String parentKey) {
/* 304 */     if (parentKey == null)
/* 307 */       return ""; 
/* 312 */     DefaultConfigurationKey key = new DefaultConfigurationKey(this, parentKey);
/* 314 */     if (node.isAttribute()) {
/* 316 */       key.appendAttribute(node.getName());
/*     */     } else {
/* 320 */       key.append(node.getName(), true);
/*     */     } 
/* 322 */     return key.toString();
/*     */   }
/*     */   
/*     */   public NodeAddData prepareAdd(ConfigurationNode root, String key) {
/* 415 */     DefaultConfigurationKey.KeyIterator it = (new DefaultConfigurationKey(this, key)).iterator();
/* 417 */     if (!it.hasNext())
/* 419 */       throw new IllegalArgumentException("Key for add operation must be defined!"); 
/* 423 */     NodeAddData result = new NodeAddData();
/* 424 */     result.setParent(findLastPathNode(it, root));
/* 426 */     while (it.hasNext()) {
/* 428 */       if (!it.isPropertyKey())
/* 430 */         throw new IllegalArgumentException("Invalid key for add operation: " + key + " (Attribute key in the middle.)"); 
/* 434 */       result.addPathNode(it.currentKey());
/* 435 */       it.next();
/*     */     } 
/* 438 */     result.setNewNodeName(it.currentKey());
/* 439 */     result.setAttribute(!it.isPropertyKey());
/* 440 */     return result;
/*     */   }
/*     */   
/*     */   protected void findNodesForKey(DefaultConfigurationKey.KeyIterator keyPart, ConfigurationNode node, Collection nodes) {
/* 455 */     if (!keyPart.hasNext()) {
/* 457 */       nodes.add(node);
/*     */     } else {
/* 462 */       String key = keyPart.nextKey(false);
/* 463 */       if (keyPart.isPropertyKey())
/* 465 */         processSubNodes(keyPart, node.getChildren(key), nodes); 
/* 467 */       if (keyPart.isAttribute())
/* 469 */         processSubNodes(keyPart, node.getAttributes(key), nodes); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ConfigurationNode findLastPathNode(DefaultConfigurationKey.KeyIterator keyIt, ConfigurationNode node) {
/* 486 */     String keyPart = keyIt.nextKey(false);
/* 488 */     if (keyIt.hasNext()) {
/* 490 */       if (!keyIt.isPropertyKey())
/* 493 */         throw new IllegalArgumentException("Invalid path for add operation: Attribute key in the middle!"); 
/* 497 */       int idx = keyIt.hasIndex() ? keyIt.getIndex() : (node.getChildrenCount(keyPart) - 1);
/* 499 */       if (idx < 0 || idx >= node.getChildrenCount(keyPart))
/* 501 */         return node; 
/* 505 */       return findLastPathNode(keyIt, node.getChildren(keyPart).get(idx));
/*     */     } 
/* 512 */     return node;
/*     */   }
/*     */   
/*     */   private void processSubNodes(DefaultConfigurationKey.KeyIterator keyPart, List subNodes, Collection nodes) {
/* 528 */     if (keyPart.hasIndex()) {
/* 530 */       if (keyPart.getIndex() >= 0 && keyPart.getIndex() < subNodes.size())
/* 532 */         findNodesForKey((DefaultConfigurationKey.KeyIterator)keyPart.clone(), subNodes.get(keyPart.getIndex()), nodes); 
/*     */     } else {
/* 539 */       for (Iterator it = subNodes.iterator(); it.hasNext();)
/* 541 */         findNodesForKey((DefaultConfigurationKey.KeyIterator)keyPart.clone(), it.next(), nodes); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\DefaultExpressionEngine.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */