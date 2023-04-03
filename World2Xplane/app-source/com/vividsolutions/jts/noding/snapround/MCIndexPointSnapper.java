/*     */ package com.vividsolutions.jts.noding.snapround;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.index.ItemVisitor;
/*     */ import com.vividsolutions.jts.index.SpatialIndex;
/*     */ import com.vividsolutions.jts.index.chain.MonotoneChain;
/*     */ import com.vividsolutions.jts.index.chain.MonotoneChainSelectAction;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import com.vividsolutions.jts.noding.NodedSegmentString;
/*     */ import com.vividsolutions.jts.noding.SegmentString;
/*     */ 
/*     */ public class MCIndexPointSnapper {
/*     */   private STRtree index;
/*     */   
/*     */   public MCIndexPointSnapper(SpatialIndex index) {
/*  58 */     this.index = (STRtree)index;
/*     */   }
/*     */   
/*     */   public boolean snap(HotPixel hotPixel, SegmentString parentEdge, int hotPixelVertexIndex) {
/*  74 */     final Envelope pixelEnv = hotPixel.getSafeEnvelope();
/*  75 */     final HotPixelSnapAction hotPixelSnapAction = new HotPixelSnapAction(hotPixel, parentEdge, hotPixelVertexIndex);
/*  77 */     this.index.query(pixelEnv, new ItemVisitor() {
/*     */           public void visitItem(Object item) {
/*  79 */             MonotoneChain testChain = (MonotoneChain)item;
/*  80 */             testChain.select(pixelEnv, hotPixelSnapAction);
/*     */           }
/*     */         });
/*  84 */     return hotPixelSnapAction.isNodeAdded();
/*     */   }
/*     */   
/*     */   public boolean snap(HotPixel hotPixel) {
/*  89 */     return snap(hotPixel, null, -1);
/*     */   }
/*     */   
/*     */   public class HotPixelSnapAction extends MonotoneChainSelectAction {
/*     */     private HotPixel hotPixel;
/*     */     
/*     */     private SegmentString parentEdge;
/*     */     
/*     */     private int hotPixelVertexIndex;
/*     */     
/*     */     private boolean isNodeAdded = false;
/*     */     
/*     */     public HotPixelSnapAction(HotPixel hotPixel, SegmentString parentEdge, int hotPixelVertexIndex) {
/* 103 */       this.hotPixel = hotPixel;
/* 104 */       this.parentEdge = parentEdge;
/* 105 */       this.hotPixelVertexIndex = hotPixelVertexIndex;
/*     */     }
/*     */     
/*     */     public boolean isNodeAdded() {
/* 108 */       return this.isNodeAdded;
/*     */     }
/*     */     
/*     */     public void select(MonotoneChain mc, int startIndex) {
/* 112 */       NodedSegmentString ss = (NodedSegmentString)mc.getContext();
/* 123 */       if (this.parentEdge != null && 
/* 124 */         ss == this.parentEdge && startIndex == this.hotPixelVertexIndex)
/*     */         return; 
/* 129 */       this.isNodeAdded = this.hotPixel.addSnappedNode(ss, startIndex);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\snapround\MCIndexPointSnapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */