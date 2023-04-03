/*    */ package org.jfree.chart;
/*    */ 
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JScrollPane;
/*    */ 
/*    */ public class ChartFrame extends JFrame {
/*    */   private ChartPanel chartPanel;
/*    */   
/*    */   public ChartFrame(String title, JFreeChart chart) {
/* 66 */     this(title, chart, false);
/*    */   }
/*    */   
/*    */   public ChartFrame(String title, JFreeChart chart, boolean scrollPane) {
/* 78 */     super(title);
/* 79 */     setDefaultCloseOperation(2);
/* 80 */     this.chartPanel = new ChartPanel(chart);
/* 81 */     if (scrollPane) {
/* 82 */       setContentPane(new JScrollPane(this.chartPanel));
/*    */     } else {
/* 85 */       setContentPane(this.chartPanel);
/*    */     } 
/*    */   }
/*    */   
/*    */   public ChartPanel getChartPanel() {
/* 95 */     return this.chartPanel;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\ChartFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */