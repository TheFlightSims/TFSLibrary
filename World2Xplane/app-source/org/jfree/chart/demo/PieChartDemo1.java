/*     */ package org.jfree.chart.demo;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Window;
/*     */ import javax.swing.JPanel;
/*     */ import org.jfree.chart.ChartFactory;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.plot.PiePlot;
/*     */ import org.jfree.data.general.DefaultPieDataset;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.ui.ApplicationFrame;
/*     */ import org.jfree.ui.RefineryUtilities;
/*     */ 
/*     */ public class PieChartDemo1 extends ApplicationFrame {
/*     */   public PieChartDemo1(String title) {
/*  71 */     super(title);
/*  72 */     setContentPane(createDemoPanel());
/*     */   }
/*     */   
/*     */   private static PieDataset createDataset() {
/*  81 */     DefaultPieDataset dataset = new DefaultPieDataset();
/*  82 */     dataset.setValue("One", new Double(43.2D));
/*  83 */     dataset.setValue("Two", new Double(10.0D));
/*  84 */     dataset.setValue("Three", new Double(27.5D));
/*  85 */     dataset.setValue("Four", new Double(17.5D));
/*  86 */     dataset.setValue("Five", new Double(11.0D));
/*  87 */     dataset.setValue("Six", new Double(19.4D));
/*  88 */     return (PieDataset)dataset;
/*     */   }
/*     */   
/*     */   private static JFreeChart createChart(PieDataset dataset) {
/* 100 */     JFreeChart chart = ChartFactory.createPieChart("Pie Chart Demo 1", dataset, true, true, false);
/* 108 */     PiePlot plot = (PiePlot)chart.getPlot();
/* 109 */     plot.setSectionOutlinesVisible(false);
/* 110 */     plot.setLabelFont(new Font("SansSerif", 0, 12));
/* 111 */     plot.setNoDataMessage("No data available");
/* 112 */     plot.setCircular(false);
/* 113 */     plot.setLabelGap(0.02D);
/* 114 */     return chart;
/*     */   }
/*     */   
/*     */   public static JPanel createDemoPanel() {
/* 124 */     JFreeChart chart = createChart(createDataset());
/* 125 */     return (JPanel)new ChartPanel(chart);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 135 */     PieChartDemo1 demo = new PieChartDemo1("Pie Chart Demo 1");
/* 136 */     demo.pack();
/* 137 */     RefineryUtilities.centerFrameOnScreen((Window)demo);
/* 138 */     demo.setVisible(true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\demo\PieChartDemo1.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */