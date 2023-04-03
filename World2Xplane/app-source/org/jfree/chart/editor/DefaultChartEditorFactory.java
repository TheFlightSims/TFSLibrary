/*    */ package org.jfree.chart.editor;
/*    */ 
/*    */ import org.jfree.chart.JFreeChart;
/*    */ 
/*    */ public class DefaultChartEditorFactory implements ChartEditorFactory {
/*    */   public ChartEditor createEditor(JFreeChart chart) {
/* 66 */     return new DefaultChartEditor(chart);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\editor\DefaultChartEditorFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */