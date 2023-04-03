/*    */ package com.vividsolutions.jts.noding;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class SimpleNoder extends SinglePassNoder {
/*    */   private Collection nodedSegStrings;
/*    */   
/*    */   public Collection getNodedSubstrings() {
/* 58 */     return NodedSegmentString.getNodedSubstrings(this.nodedSegStrings);
/*    */   }
/*    */   
/*    */   public void computeNodes(Collection inputSegStrings) {
/* 63 */     this.nodedSegStrings = inputSegStrings;
/* 64 */     for (Iterator<SegmentString> i0 = inputSegStrings.iterator(); i0.hasNext(); ) {
/* 65 */       SegmentString edge0 = i0.next();
/* 66 */       for (Iterator<SegmentString> i1 = inputSegStrings.iterator(); i1.hasNext(); ) {
/* 67 */         SegmentString edge1 = i1.next();
/* 68 */         computeIntersects(edge0, edge1);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void computeIntersects(SegmentString e0, SegmentString e1) {
/* 75 */     Coordinate[] pts0 = e0.getCoordinates();
/* 76 */     Coordinate[] pts1 = e1.getCoordinates();
/* 77 */     for (int i0 = 0; i0 < pts0.length - 1; i0++) {
/* 78 */       for (int i1 = 0; i1 < pts1.length - 1; i1++)
/* 79 */         this.segInt.processIntersections(e0, i0, e1, i1); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\SimpleNoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */