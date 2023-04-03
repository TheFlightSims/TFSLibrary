/*    */ package com.vividsolutions.jts.noding;
/*    */ 
/*    */ import java.util.Collection;
/*    */ 
/*    */ public class FastSegmentSetIntersectionFinder {
/*    */   private final SegmentSetMutualIntersector segSetMutInt;
/*    */   
/*    */   public FastSegmentSetIntersectionFinder(Collection baseSegStrings) {
/* 61 */     this.segSetMutInt = new MCIndexSegmentSetMutualIntersector(baseSegStrings);
/*    */   }
/*    */   
/*    */   public SegmentSetMutualIntersector getSegmentSetIntersector() {
/* 72 */     return this.segSetMutInt;
/*    */   }
/*    */   
/*    */   public boolean intersects(Collection segStrings) {
/* 83 */     SegmentIntersectionDetector intFinder = new SegmentIntersectionDetector();
/* 84 */     return intersects(segStrings, intFinder);
/*    */   }
/*    */   
/*    */   public boolean intersects(Collection segStrings, SegmentIntersectionDetector intDetector) {
/* 97 */     this.segSetMutInt.process(segStrings, intDetector);
/* 98 */     return intDetector.hasIntersection();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\FastSegmentSetIntersectionFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */