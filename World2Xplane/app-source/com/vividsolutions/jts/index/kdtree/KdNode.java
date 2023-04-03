/*     */ package com.vividsolutions.jts.index.kdtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class KdNode {
/*  45 */   private Coordinate p = null;
/*     */   
/*     */   private Object data;
/*     */   
/*     */   private KdNode left;
/*     */   
/*     */   private KdNode right;
/*     */   
/*     */   private int count;
/*     */   
/*     */   public KdNode(double _x, double _y, Object data) {
/*  59 */     this.p = new Coordinate(_x, _y);
/*  60 */     this.left = null;
/*  61 */     this.right = null;
/*  62 */     this.count = 1;
/*  63 */     this.data = data;
/*     */   }
/*     */   
/*     */   public KdNode(Coordinate p, Object data) {
/*  73 */     this.p = new Coordinate(p);
/*  74 */     this.left = null;
/*  75 */     this.right = null;
/*  76 */     this.count = 1;
/*  77 */     this.data = data;
/*     */   }
/*     */   
/*     */   public double getX() {
/*  86 */     return this.p.x;
/*     */   }
/*     */   
/*     */   public double getY() {
/*  95 */     return this.p.y;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/* 104 */     return this.p;
/*     */   }
/*     */   
/*     */   public Object getData() {
/* 112 */     return this.data;
/*     */   }
/*     */   
/*     */   public KdNode getLeft() {
/* 121 */     return this.left;
/*     */   }
/*     */   
/*     */   public KdNode getRight() {
/* 130 */     return this.right;
/*     */   }
/*     */   
/*     */   void increment() {
/* 135 */     this.count++;
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 144 */     return this.count;
/*     */   }
/*     */   
/*     */   public boolean isRepeated() {
/* 153 */     return (this.count > 1);
/*     */   }
/*     */   
/*     */   void setLeft(KdNode _left) {
/* 158 */     this.left = _left;
/*     */   }
/*     */   
/*     */   void setRight(KdNode _right) {
/* 163 */     this.right = _right;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\kdtree\KdNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */