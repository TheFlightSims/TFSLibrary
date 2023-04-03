/*     */ package org.jfree.chart.demo;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Window;
/*     */ import org.jfree.chart.ChartFactory;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.CategoryLabelPositions;
/*     */ import org.jfree.chart.axis.NumberAxis;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.renderer.category.BarRenderer;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.DefaultCategoryDataset;
/*     */ import org.jfree.ui.ApplicationFrame;
/*     */ import org.jfree.ui.RefineryUtilities;
/*     */ 
/*     */ public class BarChartDemo1 extends ApplicationFrame {
/*     */   public BarChartDemo1(String title) {
/*  76 */     super(title);
/*  77 */     CategoryDataset dataset = createDataset();
/*  78 */     JFreeChart chart = createChart(dataset);
/*  79 */     ChartPanel chartPanel = new ChartPanel(chart, false);
/*  80 */     chartPanel.setPreferredSize(new Dimension(500, 270));
/*  81 */     setContentPane((Container)chartPanel);
/*     */   }
/*     */   
/*     */   private static CategoryDataset createDataset() {
/*  93 */     String series1 = "First";
/*  94 */     String series2 = "Second";
/*  95 */     String series3 = "Third";
/*  98 */     String category1 = "Category 1";
/*  99 */     String category2 = "Category 2";
/* 100 */     String category3 = "Category 3";
/* 101 */     String category4 = "Category 4";
/* 102 */     String category5 = "Category 5";
/* 105 */     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
/* 107 */     dataset.addValue(1.0D, series1, category1);
/* 108 */     dataset.addValue(4.0D, series1, category2);
/* 109 */     dataset.addValue(3.0D, series1, category3);
/* 110 */     dataset.addValue(5.0D, series1, category4);
/* 111 */     dataset.addValue(5.0D, series1, category5);
/* 113 */     dataset.addValue(5.0D, series2, category1);
/* 114 */     dataset.addValue(7.0D, series2, category2);
/* 115 */     dataset.addValue(6.0D, series2, category3);
/* 116 */     dataset.addValue(8.0D, series2, category4);
/* 117 */     dataset.addValue(4.0D, series2, category5);
/* 119 */     dataset.addValue(4.0D, series3, category1);
/* 120 */     dataset.addValue(3.0D, series3, category2);
/* 121 */     dataset.addValue(2.0D, series3, category3);
/* 122 */     dataset.addValue(3.0D, series3, category4);
/* 123 */     dataset.addValue(6.0D, series3, category5);
/* 125 */     return (CategoryDataset)dataset;
/*     */   }
/*     */   
/*     */   private static JFreeChart createChart(CategoryDataset dataset) {
/* 139 */     JFreeChart chart = ChartFactory.createBarChart("Bar Chart Demo", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
/* 153 */     chart.setBackgroundPaint(Color.white);
/* 156 */     CategoryPlot plot = chart.getCategoryPlot();
/* 157 */     plot.setBackgroundPaint(Color.lightGray);
/* 158 */     plot.setDomainGridlinePaint(Color.white);
/* 159 */     plot.setDomainGridlinesVisible(true);
/* 160 */     plot.setRangeGridlinePaint(Color.white);
/* 163 */     NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
/* 164 */     rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
/* 167 */     BarRenderer renderer = (BarRenderer)plot.getRenderer();
/* 168 */     renderer.setDrawBarOutline(false);
/* 171 */     GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
/* 175 */     GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
/* 179 */     GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
/* 183 */     renderer.setSeriesPaint(0, gp0);
/* 184 */     renderer.setSeriesPaint(1, gp1);
/* 185 */     renderer.setSeriesPaint(2, gp2);
/* 187 */     CategoryAxis domainAxis = plot.getDomainAxis();
/* 188 */     domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.5235987755982988D));
/* 193 */     return chart;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 204 */     BarChartDemo1 demo = new BarChartDemo1("Bar Chart Demo");
/* 205 */     demo.pack();
/* 206 */     RefineryUtilities.centerFrameOnScreen((Window)demo);
/* 207 */     demo.setVisible(true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\demo\BarChartDemo1.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */