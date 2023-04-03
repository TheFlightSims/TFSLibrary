/*     */ package com.vividsolutions.jts.index.quadtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ 
/*     */ public class Key {
/*     */   public static int computeQuadLevel(Envelope env) {
/*  50 */     double dx = env.getWidth();
/*  51 */     double dy = env.getHeight();
/*  52 */     double dMax = (dx > dy) ? dx : dy;
/*  53 */     int level = DoubleBits.exponent(dMax) + 1;
/*  54 */     return level;
/*     */   }
/*     */   
/*  58 */   private Coordinate pt = new Coordinate();
/*     */   
/*  59 */   private int level = 0;
/*     */   
/*  61 */   private Envelope env = null;
/*     */   
/*     */   public Key(Envelope itemEnv) {
/*  65 */     computeKey(itemEnv);
/*     */   }
/*     */   
/*     */   public Coordinate getPoint() {
/*  68 */     return this.pt;
/*     */   }
/*     */   
/*     */   public int getLevel() {
/*  69 */     return this.level;
/*     */   }
/*     */   
/*     */   public Envelope getEnvelope() {
/*  70 */     return this.env;
/*     */   }
/*     */   
/*     */   public Coordinate getCentre() {
/*  74 */     return new Coordinate((this.env.getMinX() + this.env.getMaxX()) / 2.0D, (this.env.getMinY() + this.env.getMaxY()) / 2.0D);
/*     */   }
/*     */   
/*     */   public void computeKey(Envelope itemEnv) {
/*  85 */     this.level = computeQuadLevel(itemEnv);
/*  86 */     this.env = new Envelope();
/*  87 */     computeKey(this.level, itemEnv);
/*  89 */     while (!this.env.contains(itemEnv)) {
/*  90 */       this.level++;
/*  91 */       computeKey(this.level, itemEnv);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeKey(int level, Envelope itemEnv) {
/*  97 */     double quadSize = DoubleBits.powerOf2(level);
/*  98 */     this.pt.x = Math.floor(itemEnv.getMinX() / quadSize) * quadSize;
/*  99 */     this.pt.y = Math.floor(itemEnv.getMinY() / quadSize) * quadSize;
/* 100 */     this.env.init(this.pt.x, this.pt.x + quadSize, this.pt.y, this.pt.y + quadSize);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\quadtree\Key.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */