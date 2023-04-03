/*     */ package com.vividsolutions.jts.index.intervalrtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.index.ItemVisitor;
/*     */ import com.vividsolutions.jts.io.WKTWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SortedPackedIntervalRTree {
/*  58 */   private List leaves = new ArrayList();
/*     */   
/*  59 */   private IntervalRTreeNode root = null;
/*     */   
/*     */   private int level;
/*     */   
/*     */   public void insert(double min, double max, Object item) {
/*  77 */     if (this.root != null)
/*  78 */       throw new IllegalStateException("Index cannot be added to once it has been queried"); 
/*  79 */     this.leaves.add(new IntervalRTreeLeafNode(min, max, item));
/*     */   }
/*     */   
/*     */   private void init() {
/*  84 */     if (this.root != null)
/*     */       return; 
/*  85 */     buildRoot();
/*     */   }
/*     */   
/*     */   private synchronized void buildRoot() {
/*  90 */     if (this.root != null)
/*     */       return; 
/*  91 */     this.root = buildTree();
/*     */   }
/*     */   
/*     */   private IntervalRTreeNode buildTree() {
/*  98 */     Collections.sort(this.leaves, new IntervalRTreeNode.NodeComparator());
/* 101 */     List<IntervalRTreeNode> src = this.leaves;
/* 102 */     List<IntervalRTreeNode> temp = null;
/* 103 */     List<IntervalRTreeNode> dest = new ArrayList();
/*     */     while (true) {
/* 106 */       buildLevel(src, dest);
/* 107 */       if (dest.size() == 1)
/* 108 */         return dest.get(0); 
/* 110 */       temp = src;
/* 111 */       src = dest;
/* 112 */       dest = temp;
/*     */     } 
/*     */   }
/*     */   
/*     */   public SortedPackedIntervalRTree() {
/* 116 */     this.level = 0;
/*     */   }
/*     */   
/*     */   private void buildLevel(List<IntervalRTreeNode> src, List<IntervalRTreeNode> dest) {
/* 120 */     this.level++;
/* 121 */     dest.clear();
/* 122 */     for (int i = 0; i < src.size(); i += 2) {
/* 123 */       IntervalRTreeNode n1 = src.get(i);
/* 124 */       IntervalRTreeNode n2 = (i + 1 < src.size()) ? src.get(i) : null;
/* 126 */       if (n2 == null) {
/* 127 */         dest.add(n1);
/*     */       } else {
/* 129 */         IntervalRTreeNode node = new IntervalRTreeBranchNode(src.get(i), src.get(i + 1));
/* 134 */         dest.add(node);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void printNode(IntervalRTreeNode node) {
/* 141 */     System.out.println(WKTWriter.toLineString(new Coordinate(node.min, this.level), new Coordinate(node.max, this.level)));
/*     */   }
/*     */   
/*     */   public void query(double min, double max, ItemVisitor visitor) {
/* 154 */     init();
/* 156 */     this.root.query(min, max, visitor);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\intervalrtree\SortedPackedIntervalRTree.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */