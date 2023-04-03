/*     */ package org.apache.commons.configuration.tree.xpath;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ import org.apache.commons.jxpath.ri.model.NodeIterator;
/*     */ import org.apache.commons.jxpath.ri.model.NodePointer;
/*     */ 
/*     */ abstract class ConfigurationNodeIteratorBase implements NodeIterator {
/*     */   private NodePointer parent;
/*     */   
/*     */   private List subNodes;
/*     */   
/*     */   private int position;
/*     */   
/*     */   private int startOffset;
/*     */   
/*     */   private boolean reverse;
/*     */   
/*     */   protected ConfigurationNodeIteratorBase(NodePointer parent, boolean reverse) {
/*  65 */     this.parent = parent;
/*  66 */     this.reverse = reverse;
/*     */   }
/*     */   
/*     */   public int getPosition() {
/*  76 */     return this.position;
/*     */   }
/*     */   
/*     */   public boolean setPosition(int pos) {
/*  87 */     this.position = pos;
/*  88 */     return (pos >= 1 && pos <= getMaxPosition());
/*     */   }
/*     */   
/*     */   public NodePointer getNodePointer() {
/*  98 */     if (getPosition() < 1 && !setPosition(1))
/* 100 */       return null; 
/* 103 */     return createNodePointer(this.subNodes.get(positionToIndex(getPosition())));
/*     */   }
/*     */   
/*     */   protected NodePointer getParent() {
/* 114 */     return this.parent;
/*     */   }
/*     */   
/*     */   protected int getStartOffset() {
/* 124 */     return this.startOffset;
/*     */   }
/*     */   
/*     */   protected void setStartOffset(int startOffset) {
/* 135 */     this.startOffset = startOffset;
/* 136 */     if (this.reverse) {
/* 138 */       this.startOffset--;
/*     */     } else {
/* 142 */       this.startOffset++;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void initSubNodeList(List nodes) {
/* 154 */     this.subNodes = nodes;
/* 155 */     if (this.reverse)
/* 157 */       setStartOffset(this.subNodes.size()); 
/*     */   }
/*     */   
/*     */   protected int getMaxPosition() {
/* 168 */     return this.reverse ? (getStartOffset() + 1) : (this.subNodes.size() - getStartOffset());
/*     */   }
/*     */   
/*     */   protected NodePointer createNodePointer(ConfigurationNode node) {
/* 182 */     return new ConfigurationNodePointer(getParent(), node);
/*     */   }
/*     */   
/*     */   protected int positionToIndex(int pos) {
/* 194 */     return (this.reverse ? (1 - pos) : (pos - 1)) + getStartOffset();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\xpath\ConfigurationNodeIteratorBase.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */