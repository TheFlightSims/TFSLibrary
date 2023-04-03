/*    */ package com.vividsolutions.jts.noding;
/*    */ 
/*    */ import java.util.Collection;
/*    */ 
/*    */ public abstract class SinglePassNoder implements Noder {
/*    */   protected SegmentIntersector segInt;
/*    */   
/*    */   public SinglePassNoder() {}
/*    */   
/*    */   public SinglePassNoder(SegmentIntersector segInt) {
/* 57 */     setSegmentIntersector(segInt);
/*    */   }
/*    */   
/*    */   public void setSegmentIntersector(SegmentIntersector segInt) {
/* 71 */     this.segInt = segInt;
/*    */   }
/*    */   
/*    */   public abstract void computeNodes(Collection paramCollection);
/*    */   
/*    */   public abstract Collection getNodedSubstrings();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\SinglePassNoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */