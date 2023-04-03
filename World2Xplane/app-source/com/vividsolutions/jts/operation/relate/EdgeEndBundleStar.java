/*    */ package com.vividsolutions.jts.operation.relate;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.IntersectionMatrix;
/*    */ import com.vividsolutions.jts.geomgraph.EdgeEnd;
/*    */ import com.vividsolutions.jts.geomgraph.EdgeEndStar;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class EdgeEndBundleStar extends EdgeEndStar {
/*    */   public void insert(EdgeEnd e) {
/* 70 */     EdgeEndBundle eb = (EdgeEndBundle)this.edgeMap.get(e);
/* 71 */     if (eb == null) {
/* 72 */       eb = new EdgeEndBundle(e);
/* 73 */       insertEdgeEnd(e, eb);
/*    */     } else {
/* 76 */       eb.insert(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   void updateIM(IntersectionMatrix im) {
/* 85 */     for (Iterator<EdgeEndBundle> it = iterator(); it.hasNext(); ) {
/* 86 */       EdgeEndBundle esb = it.next();
/* 87 */       esb.updateIM(im);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\relate\EdgeEndBundleStar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */