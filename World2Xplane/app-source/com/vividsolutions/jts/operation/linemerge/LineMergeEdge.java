/*    */ package com.vividsolutions.jts.operation.linemerge;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import com.vividsolutions.jts.planargraph.Edge;
/*    */ 
/*    */ public class LineMergeEdge extends Edge {
/*    */   private LineString line;
/*    */   
/*    */   public LineMergeEdge(LineString line) {
/* 51 */     this.line = line;
/*    */   }
/*    */   
/*    */   public LineString getLine() {
/* 57 */     return this.line;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\linemerge\LineMergeEdge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */