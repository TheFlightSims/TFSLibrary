/*    */ package com.vividsolutions.jts.triangulate.quadedge;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class QuadEdgeUtil {
/*    */   public static List findEdgesIncidentOnOrigin(QuadEdge start) {
/* 60 */     List<QuadEdge> incEdge = new ArrayList();
/* 62 */     QuadEdge qe = start;
/*    */     do {
/* 64 */       incEdge.add(qe);
/* 65 */       qe = qe.oNext();
/* 66 */     } while (qe != start);
/* 68 */     return incEdge;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\quadedge\QuadEdgeUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */