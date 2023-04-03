/*     */ package com.vividsolutions.jts.index.kdtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class KdTree {
/*  56 */   private KdNode root = null;
/*     */   
/*  57 */   private KdNode last = null;
/*     */   
/*     */   private long numberOfNodes;
/*     */   
/*     */   private double tolerance;
/*     */   
/*     */   public KdTree() {
/*  67 */     this(0.0D);
/*     */   }
/*     */   
/*     */   public KdTree(double tolerance) {
/*  79 */     this.tolerance = tolerance;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  89 */     if (this.root == null)
/*  89 */       return true; 
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public KdNode insert(Coordinate p) {
/* 101 */     return insert(p, null);
/*     */   }
/*     */   
/*     */   public KdNode insert(Coordinate p, Object data) {
/* 116 */     if (this.root == null) {
/* 117 */       this.root = new KdNode(p, data);
/* 118 */       return this.root;
/*     */     } 
/* 121 */     KdNode currentNode = this.root;
/* 122 */     KdNode leafNode = this.root;
/* 123 */     boolean isOddLevel = true;
/* 124 */     boolean isLessThan = true;
/* 131 */     while (currentNode != this.last) {
/* 133 */       if (currentNode != null) {
/* 134 */         boolean isInTolerance = (p.distance(currentNode.getCoordinate()) <= this.tolerance);
/* 138 */         if (isInTolerance) {
/* 139 */           currentNode.increment();
/* 140 */           return currentNode;
/*     */         } 
/*     */       } 
/* 144 */       if (isOddLevel) {
/* 145 */         isLessThan = (p.x < currentNode.getX());
/*     */       } else {
/* 147 */         isLessThan = (p.y < currentNode.getY());
/*     */       } 
/* 149 */       leafNode = currentNode;
/* 150 */       if (isLessThan) {
/* 151 */         currentNode = currentNode.getLeft();
/*     */       } else {
/* 153 */         currentNode = currentNode.getRight();
/*     */       } 
/* 156 */       isOddLevel = !isOddLevel;
/*     */     } 
/* 160 */     this.numberOfNodes++;
/* 161 */     KdNode node = new KdNode(p, data);
/* 162 */     node.setLeft(this.last);
/* 163 */     node.setRight(this.last);
/* 164 */     if (isLessThan) {
/* 165 */       leafNode.setLeft(node);
/*     */     } else {
/* 167 */       leafNode.setRight(node);
/*     */     } 
/* 169 */     return node;
/*     */   }
/*     */   
/*     */   private void queryNode(KdNode currentNode, KdNode bottomNode, Envelope queryEnv, boolean odd, List<KdNode> result) {
/*     */     double min, max, discriminant;
/* 174 */     if (currentNode == bottomNode)
/*     */       return; 
/* 180 */     if (odd) {
/* 181 */       min = queryEnv.getMinX();
/* 182 */       max = queryEnv.getMaxX();
/* 183 */       discriminant = currentNode.getX();
/*     */     } else {
/* 185 */       min = queryEnv.getMinY();
/* 186 */       max = queryEnv.getMaxY();
/* 187 */       discriminant = currentNode.getY();
/*     */     } 
/* 189 */     boolean searchLeft = (min < discriminant);
/* 190 */     boolean searchRight = (discriminant <= max);
/* 192 */     if (searchLeft)
/* 193 */       queryNode(currentNode.getLeft(), bottomNode, queryEnv, !odd, result); 
/* 195 */     if (queryEnv.contains(currentNode.getCoordinate()))
/* 196 */       result.add(currentNode); 
/* 198 */     if (searchRight)
/* 199 */       queryNode(currentNode.getRight(), bottomNode, queryEnv, !odd, result); 
/*     */   }
/*     */   
/*     */   public List query(Envelope queryEnv) {
/* 212 */     List result = new ArrayList();
/* 213 */     queryNode(this.root, this.last, queryEnv, true, result);
/* 214 */     return result;
/*     */   }
/*     */   
/*     */   public void query(Envelope queryEnv, List result) {
/* 226 */     queryNode(this.root, this.last, queryEnv, true, result);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\kdtree\KdTree.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */