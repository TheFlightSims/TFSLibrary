/*    */ package com.vividsolutions.jts.index.chain;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.LineSegment;
/*    */ 
/*    */ public class MonotoneChainOverlapAction {
/* 48 */   Envelope tempEnv1 = new Envelope();
/*    */   
/* 49 */   Envelope tempEnv2 = new Envelope();
/*    */   
/* 51 */   protected LineSegment overlapSeg1 = new LineSegment();
/*    */   
/* 52 */   protected LineSegment overlapSeg2 = new LineSegment();
/*    */   
/*    */   public void overlap(MonotoneChain mc1, int start1, MonotoneChain mc2, int start2) {
/* 62 */     mc1.getLineSegment(start1, this.overlapSeg1);
/* 63 */     mc2.getLineSegment(start2, this.overlapSeg2);
/* 64 */     overlap(this.overlapSeg1, this.overlapSeg2);
/*    */   }
/*    */   
/*    */   public void overlap(LineSegment seg1, LineSegment seg2) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\chain\MonotoneChainOverlapAction.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */