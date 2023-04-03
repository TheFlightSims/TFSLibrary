/*     */ package org.apache.commons.configuration.tree;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class NodeAddData {
/*     */   private ConfigurationNode parent;
/*     */   
/*     */   private List pathNodes;
/*     */   
/*     */   private String newNodeName;
/*     */   
/*     */   private boolean attribute;
/*     */   
/*     */   public NodeAddData() {
/*  73 */     this(null, null);
/*     */   }
/*     */   
/*     */   public NodeAddData(ConfigurationNode parent, String nodeName) {
/*  85 */     setParent(parent);
/*  86 */     setNewNodeName(nodeName);
/*     */   }
/*     */   
/*     */   public boolean isAttribute() {
/*  97 */     return this.attribute;
/*     */   }
/*     */   
/*     */   public void setAttribute(boolean attribute) {
/* 108 */     this.attribute = attribute;
/*     */   }
/*     */   
/*     */   public String getNewNodeName() {
/* 118 */     return this.newNodeName;
/*     */   }
/*     */   
/*     */   public void setNewNodeName(String newNodeName) {
/* 129 */     this.newNodeName = newNodeName;
/*     */   }
/*     */   
/*     */   public ConfigurationNode getParent() {
/* 139 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void setParent(ConfigurationNode parent) {
/* 149 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   public List getPathNodes() {
/* 168 */     return (this.pathNodes != null) ? Collections.unmodifiableList(this.pathNodes) : Collections.EMPTY_LIST;
/*     */   }
/*     */   
/*     */   public void addPathNode(String nodeName) {
/* 181 */     if (this.pathNodes == null)
/* 183 */       this.pathNodes = new LinkedList(); 
/* 185 */     this.pathNodes.add(nodeName);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\NodeAddData.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */