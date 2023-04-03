/*    */ package org.jfree.chart.plot;
/*    */ 
/*    */ import org.jfree.data.Range;
/*    */ import org.jfree.data.contour.ContourDataset;
/*    */ import org.jfree.data.contour.DefaultContourDataset;
/*    */ 
/*    */ public abstract class ContourPlotUtilities {
/*    */   public static Range visibleRange(ContourDataset data, Range x, Range y) {
/* 68 */     Range range = null;
/* 69 */     range = ((DefaultContourDataset)data).getZValueRange(x, y);
/* 70 */     return range;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\ContourPlotUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */