/*    */ package org.poly2tri.triangulation.delaunay.sweep;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.poly2tri.triangulation.TriangulationPoint;
/*    */ 
/*    */ public class DTSweepPointComparator implements Comparator<TriangulationPoint> {
/*    */   public int compare(TriangulationPoint p1, TriangulationPoint p2) {
/* 33 */     if (p1.getY() < p2.getY())
/* 35 */       return -1; 
/* 37 */     if (p1.getY() > p2.getY())
/* 39 */       return 1; 
/* 43 */     if (p1.getX() < p2.getX())
/* 45 */       return -1; 
/* 47 */     if (p1.getX() > p2.getX())
/* 49 */       return 1; 
/* 53 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\delaunay\sweep\DTSweepPointComparator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */