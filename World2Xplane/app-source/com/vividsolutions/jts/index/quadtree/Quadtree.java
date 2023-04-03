/*     */ package com.vividsolutions.jts.index.quadtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.index.ArrayListVisitor;
/*     */ import com.vividsolutions.jts.index.ItemVisitor;
/*     */ import com.vividsolutions.jts.index.SpatialIndex;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Quadtree implements SpatialIndex, Serializable {
/*     */   private static final long serialVersionUID = -7461163625812743604L;
/*     */   
/*     */   private Root root;
/*     */   
/*     */   public static Envelope ensureExtent(Envelope itemEnv, double minExtent) {
/*  85 */     double minx = itemEnv.getMinX();
/*  86 */     double maxx = itemEnv.getMaxX();
/*  87 */     double miny = itemEnv.getMinY();
/*  88 */     double maxy = itemEnv.getMaxY();
/*  90 */     if (minx != maxx && miny != maxy)
/*  90 */       return itemEnv; 
/*  93 */     if (minx == maxx) {
/*  94 */       minx -= minExtent / 2.0D;
/*  95 */       maxx = minx + minExtent / 2.0D;
/*     */     } 
/*  97 */     if (miny == maxy) {
/*  98 */       miny -= minExtent / 2.0D;
/*  99 */       maxy = miny + minExtent / 2.0D;
/*     */     } 
/* 101 */     return new Envelope(minx, maxx, miny, maxy);
/*     */   }
/*     */   
/* 114 */   private double minExtent = 0.1D;
/*     */   
/*     */   public Quadtree() {
/* 121 */     this.root = new Root();
/*     */   }
/*     */   
/*     */   public int depth() {
/* 132 */     if (this.root != null)
/* 132 */       return this.root.depth(); 
/* 133 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 143 */     if (this.root == null)
/* 143 */       return true; 
/* 144 */     return false;
/*     */   }
/*     */   
/*     */   public int size() {
/* 154 */     if (this.root != null)
/* 154 */       return this.root.size(); 
/* 155 */     return 0;
/*     */   }
/*     */   
/*     */   public void insert(Envelope itemEnv, Object item) {
/* 160 */     collectStats(itemEnv);
/* 161 */     Envelope insertEnv = ensureExtent(itemEnv, this.minExtent);
/* 162 */     this.root.insert(insertEnv, item);
/*     */   }
/*     */   
/*     */   public boolean remove(Envelope itemEnv, Object item) {
/* 174 */     Envelope posEnv = ensureExtent(itemEnv, this.minExtent);
/* 175 */     return this.root.remove(posEnv, item);
/*     */   }
/*     */   
/*     */   public List query(Envelope searchEnv) {
/* 211 */     ArrayListVisitor visitor = new ArrayListVisitor();
/* 212 */     query(searchEnv, (ItemVisitor)visitor);
/* 213 */     return visitor.getItems();
/*     */   }
/*     */   
/*     */   public void query(Envelope searchEnv, ItemVisitor visitor) {
/* 235 */     this.root.visit(searchEnv, visitor);
/*     */   }
/*     */   
/*     */   public List queryAll() {
/* 243 */     List foundItems = new ArrayList();
/* 244 */     this.root.addAllItems(foundItems);
/* 245 */     return foundItems;
/*     */   }
/*     */   
/*     */   private void collectStats(Envelope itemEnv) {
/* 250 */     double delX = itemEnv.getWidth();
/* 251 */     if (delX < this.minExtent && delX > 0.0D)
/* 252 */       this.minExtent = delX; 
/* 254 */     double delY = itemEnv.getHeight();
/* 255 */     if (delY < this.minExtent && delY > 0.0D)
/* 256 */       this.minExtent = delY; 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\quadtree\Quadtree.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */