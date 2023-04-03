/*     */ package com.vividsolutions.jts.index.quadtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ public class Root extends NodeBase {
/*  51 */   private static final Coordinate origin = new Coordinate(0.0D, 0.0D);
/*     */   
/*     */   public void insert(Envelope itemEnv, Object item) {
/*  62 */     int index = getSubnodeIndex(itemEnv, origin.x, origin.y);
/*  64 */     if (index == -1) {
/*  65 */       add(item);
/*     */       return;
/*     */     } 
/*  72 */     Node node = this.subnode[index];
/*  78 */     if (node == null || !node.getEnvelope().contains(itemEnv)) {
/*  79 */       Node largerNode = Node.createExpanded(node, itemEnv);
/*  80 */       this.subnode[index] = largerNode;
/*     */     } 
/*  86 */     insertContained(this.subnode[index], itemEnv, item);
/*     */   }
/*     */   
/*     */   private void insertContained(Node tree, Envelope itemEnv, Object item) {
/*     */     NodeBase node;
/*  98 */     Assert.isTrue(tree.getEnvelope().contains(itemEnv));
/* 104 */     boolean isZeroX = IntervalSize.isZeroWidth(itemEnv.getMinX(), itemEnv.getMaxX());
/* 105 */     boolean isZeroY = IntervalSize.isZeroWidth(itemEnv.getMinY(), itemEnv.getMaxY());
/* 107 */     if (isZeroX || isZeroY) {
/* 108 */       node = tree.find(itemEnv);
/*     */     } else {
/* 110 */       node = tree.getNode(itemEnv);
/*     */     } 
/* 111 */     node.add(item);
/*     */   }
/*     */   
/*     */   protected boolean isSearchMatch(Envelope searchEnv) {
/* 116 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\quadtree\Root.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */