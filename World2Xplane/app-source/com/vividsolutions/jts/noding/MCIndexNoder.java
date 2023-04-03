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
/*     */ public class MCIndexNoder extends SinglePassNoder {
/*  53 */   private List monoChains = new ArrayList();
/*     */   
/*  54 */   private SpatialIndex index = (SpatialIndex)new STRtree();
/*     */   
/*  55 */   private int idCounter = 0;
/*     */   
/*     */   private Collection nodedSegStrings;
/*     */   
/*  58 */   private int nOverlaps = 0;
/*     */   
/*     */   public MCIndexNoder() {}
/*     */   
/*     */   public MCIndexNoder(SegmentIntersector si) {
/*  65 */     super(si);
/*     */   }
/*     */   
/*     */   public List getMonotoneChains() {
/*  68 */     return this.monoChains;
/*     */   }
/*     */   
/*     */   public SpatialIndex getIndex() {
/*  70 */     return this.index;
/*     */   }
/*     */   
/*     */   public Collection getNodedSubstrings() {
/*  74 */     return NodedSegmentString.getNodedSubstrings(this.nodedSegStrings);
/*     */   }
/*     */   
/*     */   public void computeNodes(Collection inputSegStrings) {
/*  79 */     this.nodedSegStrings = inputSegStrings;
/*  80 */     for (Iterator<SegmentString> i = inputSegStrings.iterator(); i.hasNext();)
/*  81 */       add(i.next()); 
/*  83 */     intersectChains();
/*     */   }
/*     */   
/*     */   private void intersectChains() {
/*  89 */     MonotoneChainOverlapAction overlapAction = new SegmentOverlapAction(this.segInt);
/*  91 */     for (Iterator<MonotoneChain> i = this.monoChains.iterator(); i.hasNext(); ) {
/*  92 */       MonotoneChain queryChain = i.next();
/*  93 */       List overlapChains = this.index.query(queryChain.getEnvelope());
/*  94 */       for (Iterator<MonotoneChain> j = overlapChains.iterator(); j.hasNext(); ) {
/*  95 */         MonotoneChain testChain = j.next();
/* 100 */         if (testChain.getId() > queryChain.getId()) {
/* 101 */           queryChain.computeOverlaps(testChain, overlapAction);
/* 102 */           this.nOverlaps++;
/*     */         } 
/* 105 */         if (this.segInt.isDone())
/*     */           return; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void add(SegmentString segStr) {
/* 113 */     List segChains = MonotoneChainBuilder.getChains(segStr.getCoordinates(), segStr);
/* 114 */     for (Iterator<MonotoneChain> i = segChains.iterator(); i.hasNext(); ) {
/* 115 */       MonotoneChain mc = i.next();
/* 116 */       mc.setId(this.idCounter++);
/* 117 */       this.index.insert(mc.getEnvelope(), mc);
/* 118 */       this.monoChains.add(mc);
/*     */     } 
/*     */   }
/*     */   
/*     */   public class SegmentOverlapAction extends MonotoneChainOverlapAction {
/* 125 */     private SegmentIntersector si = null;
/*     */     
/*     */     public SegmentOverlapAction(SegmentIntersector si) {
/* 129 */       this.si = si;
/*     */     }
/*     */     
/*     */     public void overlap(MonotoneChain mc1, int start1, MonotoneChain mc2, int start2) {
/* 134 */       SegmentString ss1 = (SegmentString)mc1.getContext();
/* 135 */       SegmentString ss2 = (SegmentString)mc2.getContext();
/* 136 */       this.si.processIntersections(ss1, start1, ss2, start2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\MCIndexNoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */