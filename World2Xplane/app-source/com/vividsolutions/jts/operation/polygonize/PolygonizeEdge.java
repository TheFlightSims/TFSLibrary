/*    */ package com.vividsolutions.jts.operation.polygonize;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import com.vividsolutions.jts.planargraph.Edge;
/*    */ 
/*    */ class PolygonizeEdge extends Edge {
/*    */   private LineString line;
/*    */   
/*    */   public PolygonizeEdge(LineString line) {
/* 53 */     this.line = line;
/*    */   }
/*    */   
/*    */   public LineString getLine() {
/* 55 */     return this.line;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\polygonize\PolygonizeEdge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */