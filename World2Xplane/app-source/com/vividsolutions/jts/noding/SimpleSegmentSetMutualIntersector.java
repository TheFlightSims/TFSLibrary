/*    */ package com.vividsolutions.jts.noding;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class SimpleSegmentSetMutualIntersector implements SegmentSetMutualIntersector {
/*    */   private final Collection baseSegStrings;
/*    */   
/*    */   public SimpleSegmentSetMutualIntersector(Collection segStrings) {
/* 57 */     this.baseSegStrings = segStrings;
/*    */   }
/*    */   
/*    */   public void process(Collection segStrings, SegmentIntersector segInt) {
/* 69 */     for (Iterator<SegmentString> i = this.baseSegStrings.iterator(); i.hasNext(); ) {
/* 70 */       SegmentString baseSS = i.next();
/* 71 */       for (Iterator<SegmentString> j = segStrings.iterator(); j.hasNext(); ) {
/* 72 */         SegmentString ss = j.next();
/* 73 */         intersect(baseSS, ss, segInt);
/* 74 */         if (segInt.isDone())
/*    */           return; 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void intersect(SegmentString ss0, SegmentString ss1, SegmentIntersector segInt) {
/* 90 */     Coordinate[] pts0 = ss0.getCoordinates();
/* 91 */     Coordinate[] pts1 = ss1.getCoordinates();
/* 92 */     for (int i0 = 0; i0 < pts0.length - 1; i0++) {
/* 93 */       for (int i1 = 0; i1 < pts1.length - 1; i1++) {
/* 94 */         segInt.processIntersections(ss0, i0, ss1, i1);
/* 95 */         if (segInt.isDone())
/*    */           return; 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\SimpleSegmentSetMutualIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */