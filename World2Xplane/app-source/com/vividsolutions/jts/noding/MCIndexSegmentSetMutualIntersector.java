/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.index.SpatialIndex;
/*     */ import com.vividsolutions.jts.index.chain.MonotoneChain;
/*     */ import com.vividsolutions.jts.index.chain.MonotoneChainBuilder;
/*     */ import com.vividsolutions.jts.index.chain.MonotoneChainOverlapAction;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MCIndexSegmentSetMutualIntersector implements SegmentSetMutualIntersector {
/*  59 */   private STRtree index = new STRtree();
/*     */   
/*     */   public MCIndexSegmentSetMutualIntersector(Collection baseSegStrings) {
/*  68 */     initBaseSegments(baseSegStrings);
/*     */   }
/*     */   
/*     */   public SpatialIndex getIndex() {
/*  78 */     return (SpatialIndex)this.index;
/*     */   }
/*     */   
/*     */   private void initBaseSegments(Collection segStrings) {
/*  82 */     for (Iterator<SegmentString> i = segStrings.iterator(); i.hasNext();)
/*  83 */       addToIndex(i.next()); 
/*  86 */     this.index.build();
/*     */   }
/*     */   
/*     */   private void addToIndex(SegmentString segStr) {
/*  91 */     List segChains = MonotoneChainBuilder.getChains(segStr.getCoordinates(), segStr);
/*  92 */     for (Iterator<MonotoneChain> i = segChains.iterator(); i.hasNext(); ) {
/*  93 */       MonotoneChain mc = i.next();
/*  94 */       this.index.insert(mc.getEnvelope(), mc);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void process(Collection segStrings, SegmentIntersector segInt) {
/* 108 */     List monoChains = new ArrayList();
/* 109 */     for (Iterator<SegmentString> i = segStrings.iterator(); i.hasNext();)
/* 110 */       addToMonoChains(i.next(), monoChains); 
/* 112 */     intersectChains(monoChains, segInt);
/*     */   }
/*     */   
/*     */   private void addToMonoChains(SegmentString segStr, List<MonotoneChain> monoChains) {
/* 119 */     List segChains = MonotoneChainBuilder.getChains(segStr.getCoordinates(), segStr);
/* 120 */     for (Iterator<MonotoneChain> i = segChains.iterator(); i.hasNext(); ) {
/* 121 */       MonotoneChain mc = i.next();
/* 122 */       monoChains.add(mc);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intersectChains(List monoChains, SegmentIntersector segInt) {
/* 128 */     MonotoneChainOverlapAction overlapAction = new SegmentOverlapAction(segInt);
/* 130 */     for (Iterator<MonotoneChain> i = monoChains.iterator(); i.hasNext(); ) {
/* 131 */       MonotoneChain queryChain = i.next();
/* 132 */       List overlapChains = this.index.query(queryChain.getEnvelope());
/* 133 */       for (Iterator<MonotoneChain> j = overlapChains.iterator(); j.hasNext(); ) {
/* 134 */         MonotoneChain testChain = j.next();
/* 135 */         queryChain.computeOverlaps(testChain, overlapAction);
/* 136 */         if (segInt.isDone())
/*     */           return; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public class SegmentOverlapAction extends MonotoneChainOverlapAction {
/* 144 */     private SegmentIntersector si = null;
/*     */     
/*     */     public SegmentOverlapAction(SegmentIntersector si) {
/* 148 */       this.si = si;
/*     */     }
/*     */     
/*     */     public void overlap(MonotoneChain mc1, int start1, MonotoneChain mc2, int start2) {
/* 153 */       SegmentString ss1 = (SegmentString)mc1.getContext();
/* 154 */       SegmentString ss2 = (SegmentString)mc2.getContext();
/* 155 */       this.si.processIntersections(ss1, start1, ss2, start2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\MCIndexSegmentSetMutualIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */