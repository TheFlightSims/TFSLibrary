/*     */ package com.world2xplane.Geom.Quadtree;
/*     */ 
/*     */ public class QuadTreeNode {
/*     */   private double x;
/*     */   
/*     */   private double y;
/*     */   
/*     */   private double w;
/*     */   
/*     */   private double h;
/*     */   
/*     */   private QuadTreeNode opt_parent;
/*     */   
/*     */   private QuadTreePoint point;
/*     */   
/*     */   public enum NodeType {
/*  28 */     EMPTY, LEAF, POINTER;
/*     */   }
/*     */   
/*  39 */   private NodeType nodetype = NodeType.EMPTY;
/*     */   
/*     */   private QuadTreeNode nw;
/*     */   
/*     */   private QuadTreeNode ne;
/*     */   
/*     */   private QuadTreeNode sw;
/*     */   
/*     */   private QuadTreeNode se;
/*     */   
/*     */   public QuadTreeNode(double x, double y, double w, double h, QuadTreeNode opt_parent) {
/*  56 */     this.x = x;
/*  57 */     this.y = y;
/*  58 */     this.w = w;
/*  59 */     this.h = h;
/*  60 */     this.opt_parent = opt_parent;
/*     */   }
/*     */   
/*     */   public double getX() {
/*  64 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setX(double x) {
/*  68 */     this.x = x;
/*     */   }
/*     */   
/*     */   public double getY() {
/*  72 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(double y) {
/*  76 */     this.y = y;
/*     */   }
/*     */   
/*     */   public double getW() {
/*  80 */     return this.w;
/*     */   }
/*     */   
/*     */   public void setW(double w) {
/*  84 */     this.w = w;
/*     */   }
/*     */   
/*     */   public double getH() {
/*  88 */     return this.h;
/*     */   }
/*     */   
/*     */   public void setH(double h) {
/*  92 */     this.h = h;
/*     */   }
/*     */   
/*     */   public QuadTreeNode getParent() {
/*  96 */     return this.opt_parent;
/*     */   }
/*     */   
/*     */   public void setParent(QuadTreeNode opt_parent) {
/* 100 */     this.opt_parent = opt_parent;
/*     */   }
/*     */   
/*     */   public void setPoint(QuadTreePoint point) {
/* 104 */     this.point = point;
/*     */   }
/*     */   
/*     */   public QuadTreePoint getPoint() {
/* 108 */     return this.point;
/*     */   }
/*     */   
/*     */   public void setNodeType(NodeType nodetype) {
/* 112 */     this.nodetype = nodetype;
/*     */   }
/*     */   
/*     */   public NodeType getNodeType() {
/* 116 */     return this.nodetype;
/*     */   }
/*     */   
/*     */   public void setNw(QuadTreeNode nw) {
/* 121 */     this.nw = nw;
/*     */   }
/*     */   
/*     */   public void setNe(QuadTreeNode ne) {
/* 125 */     this.ne = ne;
/*     */   }
/*     */   
/*     */   public void setSw(QuadTreeNode sw) {
/* 129 */     this.sw = sw;
/*     */   }
/*     */   
/*     */   public void setSe(QuadTreeNode se) {
/* 133 */     this.se = se;
/*     */   }
/*     */   
/*     */   public QuadTreeNode getNe() {
/* 137 */     return this.ne;
/*     */   }
/*     */   
/*     */   public QuadTreeNode getNw() {
/* 141 */     return this.nw;
/*     */   }
/*     */   
/*     */   public QuadTreeNode getSw() {
/* 145 */     return this.sw;
/*     */   }
/*     */   
/*     */   public QuadTreeNode getSe() {
/* 149 */     return this.se;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\Quadtree\QuadTreeNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */