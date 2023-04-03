/*     */ package org.apache.commons.configuration.tree;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class NodeCombiner {
/*  63 */   protected Set listNodes = new HashSet();
/*     */   
/*     */   public void addListNode(String nodeName) {
/*  74 */     this.listNodes.add(nodeName);
/*     */   }
/*     */   
/*     */   public Set getListNodes() {
/*  84 */     return Collections.unmodifiableSet(this.listNodes);
/*     */   }
/*     */   
/*     */   public boolean isListNode(ConfigurationNode node) {
/*  97 */     return this.listNodes.contains(node.getName());
/*     */   }
/*     */   
/*     */   public abstract ConfigurationNode combine(ConfigurationNode paramConfigurationNode1, ConfigurationNode paramConfigurationNode2);
/*     */   
/*     */   protected ViewNode createViewNode() {
/* 122 */     return new ViewNode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\NodeCombiner.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */