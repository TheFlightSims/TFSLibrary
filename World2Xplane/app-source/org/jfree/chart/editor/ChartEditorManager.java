/*    */ package org.jfree.chart.editor;
/*    */ 
/*    */ import org.jfree.chart.JFreeChart;
/*    */ 
/*    */ public class ChartEditorManager {
/* 56 */   static ChartEditorFactory factory = new DefaultChartEditorFactory();
/*    */   
/*    */   public static ChartEditorFactory getChartEditorFactory() {
/* 71 */     return factory;
/*    */   }
/*    */   
/*    */   public static void setChartEditorFactory(ChartEditorFactory f) {
/* 80 */     if (f == null)
/* 81 */       throw new IllegalArgumentException("Null 'f' argument."); 
/* 83 */     factory = f;
/*    */   }
/*    */   
/*    */   public static ChartEditor getChartEditor(JFreeChart chart) {
/* 94 */     return factory.createEditor(chart);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\editor\ChartEditorManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */