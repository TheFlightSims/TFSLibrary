/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class SegmentStringDissolver {
/*     */   private SegmentStringMerger merger;
/*     */   
/*  79 */   private Map ocaMap = new TreeMap<>();
/*     */   
/*     */   public SegmentStringDissolver(SegmentStringMerger merger) {
/*  90 */     this.merger = merger;
/*     */   }
/*     */   
/*     */   public SegmentStringDissolver() {
/*  97 */     this(null);
/*     */   }
/*     */   
/*     */   public void dissolve(Collection segStrings) {
/* 106 */     for (Iterator<SegmentString> i = segStrings.iterator(); i.hasNext();)
/* 107 */       dissolve(i.next()); 
/*     */   }
/*     */   
/*     */   private void add(OrientedCoordinateArray oca, SegmentString segString) {
/* 113 */     this.ocaMap.put(oca, segString);
/*     */   }
/*     */   
/*     */   public void dissolve(SegmentString segString) {
/* 124 */     OrientedCoordinateArray oca = new OrientedCoordinateArray(segString.getCoordinates());
/* 125 */     SegmentString existing = findMatching(oca, segString);
/* 126 */     if (existing == null) {
/* 127 */       add(oca, segString);
/* 130 */     } else if (this.merger != null) {
/* 131 */       boolean isSameOrientation = CoordinateArrays.equals(existing.getCoordinates(), segString.getCoordinates());
/* 133 */       this.merger.merge(existing, segString, isSameOrientation);
/*     */     } 
/*     */   }
/*     */   
/*     */   private SegmentString findMatching(OrientedCoordinateArray oca, SegmentString segString) {
/* 141 */     SegmentString matchSS = (SegmentString)this.ocaMap.get(oca);
/* 148 */     return matchSS;
/*     */   }
/*     */   
/*     */   public Collection getDissolved() {
/* 169 */     return this.ocaMap.values();
/*     */   }
/*     */   
/*     */   public static interface SegmentStringMerger {
/*     */     void merge(SegmentString param1SegmentString1, SegmentString param1SegmentString2, boolean param1Boolean);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\SegmentStringDissolver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */