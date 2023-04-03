/*    */ package org.opensphere.geometry.triangulation;
/*    */ 
/*    */ import com.vividsolutions.jts.triangulate.quadedge.QuadEdge;
/*    */ import java.util.Comparator;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DoubleComparator implements Comparator<QuadEdge> {
/*    */   Map<QuadEdge, Double> map;
/*    */   
/*    */   public DoubleComparator(Map<QuadEdge, Double> map) {
/* 47 */     this.map = map;
/*    */   }
/*    */   
/*    */   public int compare(QuadEdge qeA, QuadEdge qeB) {
/* 65 */     if (((Double)this.map.get(qeA)).doubleValue() < ((Double)this.map.get(qeB)).doubleValue())
/* 66 */       return 1; 
/* 67 */     if (this.map.get(qeA) == this.map.get(qeB))
/* 68 */       return 0; 
/* 70 */     return -1;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opensphere\geometry\triangulation\DoubleComparator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */