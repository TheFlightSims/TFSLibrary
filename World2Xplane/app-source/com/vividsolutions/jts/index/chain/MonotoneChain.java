/*     */ package com.vividsolutions.jts.index.chain;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ 
/*     */ public class MonotoneChain {
/*     */   private Coordinate[] pts;
/*     */   
/*     */   private int start;
/*     */   
/*     */   private int end;
/*     */   
/*  86 */   private Envelope env = null;
/*     */   
/*  87 */   private Object context = null;
/*     */   
/*     */   private int id;
/*     */   
/*     */   public MonotoneChain(Coordinate[] pts, int start, int end, Object context) {
/*  92 */     this.pts = pts;
/*  93 */     this.start = start;
/*  94 */     this.end = end;
/*  95 */     this.context = context;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  98 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  99 */     return this.id;
/*     */   }
/*     */   
/*     */   public Object getContext() {
/* 101 */     return this.context;
/*     */   }
/*     */   
/*     */   public Envelope getEnvelope() {
/* 105 */     if (this.env == null) {
/* 106 */       Coordinate p0 = this.pts[this.start];
/* 107 */       Coordinate p1 = this.pts[this.end];
/* 108 */       this.env = new Envelope(p0, p1);
/*     */     } 
/* 110 */     return this.env;
/*     */   }
/*     */   
/*     */   public int getStartIndex() {
/* 113 */     return this.start;
/*     */   }
/*     */   
/*     */   public int getEndIndex() {
/* 114 */     return this.end;
/*     */   }
/*     */   
/*     */   public void getLineSegment(int index, LineSegment ls) {
/* 124 */     ls.p0 = this.pts[index];
/* 125 */     ls.p1 = this.pts[index + 1];
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/* 133 */     Coordinate[] coord = new Coordinate[this.end - this.start + 1];
/* 134 */     int index = 0;
/* 135 */     for (int i = this.start; i <= this.end; i++)
/* 136 */       coord[index++] = this.pts[i]; 
/* 138 */     return coord;
/*     */   }
/*     */   
/*     */   public void select(Envelope searchEnv, MonotoneChainSelectAction mcs) {
/* 158 */     computeSelect(searchEnv, this.start, this.end, mcs);
/*     */   }
/*     */   
/*     */   private void computeSelect(Envelope searchEnv, int start0, int end0, MonotoneChainSelectAction mcs) {
/* 166 */     Coordinate p0 = this.pts[start0];
/* 167 */     Coordinate p1 = this.pts[end0];
/* 168 */     mcs.tempEnv1.init(p0, p1);
/* 172 */     if (end0 - start0 == 1) {
/* 174 */       mcs.select(this, start0);
/*     */       return;
/*     */     } 
/* 178 */     if (!searchEnv.intersects(mcs.tempEnv1))
/*     */       return; 
/* 182 */     int mid = (start0 + end0) / 2;
/* 186 */     if (start0 < mid)
/* 187 */       computeSelect(searchEnv, start0, mid, mcs); 
/* 189 */     if (mid < end0)
/* 190 */       computeSelect(searchEnv, mid, end0, mcs); 
/*     */   }
/*     */   
/*     */   public void computeOverlaps(MonotoneChain mc, MonotoneChainOverlapAction mco) {
/* 210 */     computeOverlaps(this.start, this.end, mc, mc.start, mc.end, mco);
/*     */   }
/*     */   
/*     */   private void computeOverlaps(int start0, int end0, MonotoneChain mc, int start1, int end1, MonotoneChainOverlapAction mco) {
/* 219 */     Coordinate p00 = this.pts[start0];
/* 220 */     Coordinate p01 = this.pts[end0];
/* 221 */     Coordinate p10 = mc.pts[start1];
/* 222 */     Coordinate p11 = mc.pts[end1];
/* 225 */     if (end0 - start0 == 1 && end1 - start1 == 1) {
/* 226 */       mco.overlap(this, start0, mc, start1);
/*     */       return;
/*     */     } 
/* 230 */     mco.tempEnv1.init(p00, p01);
/* 231 */     mco.tempEnv2.init(p10, p11);
/* 232 */     if (!mco.tempEnv1.intersects(mco.tempEnv2))
/*     */       return; 
/* 235 */     int mid0 = (start0 + end0) / 2;
/* 236 */     int mid1 = (start1 + end1) / 2;
/* 240 */     if (start0 < mid0) {
/* 241 */       if (start1 < mid1)
/* 241 */         computeOverlaps(start0, mid0, mc, start1, mid1, mco); 
/* 242 */       if (mid1 < end1)
/* 242 */         computeOverlaps(start0, mid0, mc, mid1, end1, mco); 
/*     */     } 
/* 244 */     if (mid0 < end0) {
/* 245 */       if (start1 < mid1)
/* 245 */         computeOverlaps(mid0, end0, mc, start1, mid1, mco); 
/* 246 */       if (mid1 < end1)
/* 246 */         computeOverlaps(mid0, end0, mc, mid1, end1, mco); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\chain\MonotoneChain.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */