/*    */ package org.jfree.chart.urls;
/*    */ 
/*    */ import org.jfree.data.xy.XYDataset;
/*    */ import org.jfree.data.xy.XYZDataset;
/*    */ 
/*    */ public class StandardXYZURLGenerator extends StandardXYURLGenerator implements XYZURLGenerator {
/*    */   public String generateURL(XYZDataset dataset, int series, int item) {
/* 63 */     return generateURL((XYDataset)dataset, series, item);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\char\\urls\StandardXYZURLGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */