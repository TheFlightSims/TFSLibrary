/*     */ package com.vividsolutions.jts.index.bintree;
/*     */ 
/*     */ import com.vividsolutions.jts.index.quadtree.IntervalSize;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ public class Root extends NodeBase {
/*     */   private static final double origin = 0.0D;
/*     */   
/*     */   public void insert(Interval itemInterval, Object item) {
/*  62 */     int index = getSubnodeIndex(itemInterval, 0.0D);
/*  64 */     if (index == -1) {
/*  65 */       add(item);
/*     */       return;
/*     */     } 
/*  72 */     Node node = this.subnode[index];
/*  78 */     if (node == null || !node.getInterval().contains(itemInterval)) {
/*  79 */       Node largerNode = Node.createExpanded(node, itemInterval);
/*  80 */       this.subnode[index] = largerNode;
/*     */     } 
/*  86 */     insertContained(this.subnode[index], itemInterval, item);
/*     */   }
/*     */   
/*     */   private void insertContained(Node tree, Interval itemInterval, Object item) {
/*     */     NodeBase node;
/*  97 */     Assert.isTrue(tree.getInterval().contains(itemInterval));
/* 103 */     boolean isZeroArea = IntervalSize.isZeroWidth(itemInterval.getMin(), itemInterval.getMax());
/* 105 */     if (isZeroArea) {
/* 106 */       node = tree.find(itemInterval);
/*     */     } else {
/* 108 */       node = tree.getNode(itemInterval);
/*     */     } 
/* 109 */     node.add(item);
/*     */   }
/*     */   
/*     */   protected boolean isSearchMatch(Interval interval) {
/* 117 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\bintree\Root.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */