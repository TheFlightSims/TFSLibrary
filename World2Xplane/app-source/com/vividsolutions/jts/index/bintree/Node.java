/*     */ package com.vividsolutions.jts.index.bintree;
/*     */ 
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ public class Node extends NodeBase {
/*     */   private Interval interval;
/*     */   
/*     */   private double centre;
/*     */   
/*     */   private int level;
/*     */   
/*     */   public static Node createNode(Interval itemInterval) {
/*  48 */     Key key = new Key(itemInterval);
/*  51 */     Node node = new Node(key.getInterval(), key.getLevel());
/*  52 */     return node;
/*     */   }
/*     */   
/*     */   public static Node createExpanded(Node node, Interval addInterval) {
/*  57 */     Interval expandInt = new Interval(addInterval);
/*  58 */     if (node != null)
/*  58 */       expandInt.expandToInclude(node.interval); 
/*  60 */     Node largerNode = createNode(expandInt);
/*  61 */     if (node != null)
/*  61 */       largerNode.insert(node); 
/*  62 */     return largerNode;
/*     */   }
/*     */   
/*     */   public Node(Interval interval, int level) {
/*  71 */     this.interval = interval;
/*  72 */     this.level = level;
/*  73 */     this.centre = (interval.getMin() + interval.getMax()) / 2.0D;
/*     */   }
/*     */   
/*     */   public Interval getInterval() {
/*  76 */     return this.interval;
/*     */   }
/*     */   
/*     */   protected boolean isSearchMatch(Interval itemInterval) {
/*  82 */     return itemInterval.overlaps(this.interval);
/*     */   }
/*     */   
/*     */   public Node getNode(Interval searchInterval) {
/*  92 */     int subnodeIndex = getSubnodeIndex(searchInterval, this.centre);
/*  94 */     if (subnodeIndex != -1) {
/*  96 */       Node node = getSubnode(subnodeIndex);
/*  98 */       return node.getNode(searchInterval);
/*     */     } 
/* 101 */     return this;
/*     */   }
/*     */   
/*     */   public NodeBase find(Interval searchInterval) {
/* 111 */     int subnodeIndex = getSubnodeIndex(searchInterval, this.centre);
/* 112 */     if (subnodeIndex == -1)
/* 113 */       return this; 
/* 114 */     if (this.subnode[subnodeIndex] != null) {
/* 116 */       Node node = this.subnode[subnodeIndex];
/* 117 */       return node.find(searchInterval);
/*     */     } 
/* 120 */     return this;
/*     */   }
/*     */   
/*     */   void insert(Node node) {
/* 125 */     Assert.isTrue((this.interval == null || this.interval.contains(node.interval)));
/* 126 */     int index = getSubnodeIndex(node.interval, this.centre);
/* 127 */     if (node.level == this.level - 1) {
/* 128 */       this.subnode[index] = node;
/*     */     } else {
/* 133 */       Node childNode = createSubnode(index);
/* 134 */       childNode.insert(node);
/* 135 */       this.subnode[index] = childNode;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Node getSubnode(int index) {
/* 145 */     if (this.subnode[index] == null)
/* 146 */       this.subnode[index] = createSubnode(index); 
/* 148 */     return this.subnode[index];
/*     */   }
/*     */   
/*     */   private Node createSubnode(int index) {
/* 155 */     double min = 0.0D;
/* 156 */     double max = 0.0D;
/* 158 */     switch (index) {
/*     */       case 0:
/* 160 */         min = this.interval.getMin();
/* 161 */         max = this.centre;
/*     */         break;
/*     */       case 1:
/* 164 */         min = this.centre;
/* 165 */         max = this.interval.getMax();
/*     */         break;
/*     */     } 
/* 168 */     Interval subInt = new Interval(min, max);
/* 169 */     Node node = new Node(subInt, this.level - 1);
/* 170 */     return node;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\bintree\Node.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */