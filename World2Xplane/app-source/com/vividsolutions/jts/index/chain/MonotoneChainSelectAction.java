/*    */ package com.vividsolutions.jts.index.chain;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.LineSegment;
/*    */ 
/*    */ public class MonotoneChainSelectAction {
/* 47 */   Envelope tempEnv1 = new Envelope();
/*    */   
/* 49 */   LineSegment selectedSegment = new LineSegment();
/*    */   
/*    */   public void select(MonotoneChain mc, int startIndex) {
/* 61 */     mc.getLineSegment(startIndex, this.selectedSegment);
/* 63 */     select(this.selectedSegment);
/*    */   }
/*    */   
/*    */   public void select(LineSegment seg) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\chain\MonotoneChainSelectAction.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */