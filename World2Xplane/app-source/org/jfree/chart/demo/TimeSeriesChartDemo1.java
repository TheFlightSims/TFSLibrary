/*     */ package org.jfree.chart.demo;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Window;
/*     */ import java.text.SimpleDateFormat;
/*     */ import javax.swing.JPanel;
/*     */ import org.jfree.chart.ChartFactory;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.axis.DateAxis;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.renderer.xy.XYItemRenderer;
/*     */ import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
/*     */ import org.jfree.data.time.Month;
/*     */ import org.jfree.data.time.RegularTimePeriod;
/*     */ import org.jfree.data.time.TimeSeries;
/*     */ import org.jfree.data.time.TimeSeriesCollection;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.ApplicationFrame;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.RefineryUtilities;
/*     */ 
/*     */ public class TimeSeriesChartDemo1 extends ApplicationFrame {
/*     */   public TimeSeriesChartDemo1(String title) {
/*  80 */     super(title);
/*  81 */     ChartPanel chartPanel = (ChartPanel)createDemoPanel();
/*  82 */     chartPanel.setPreferredSize(new Dimension(500, 270));
/*  83 */     chartPanel.setMouseZoomable(true, false);
/*  84 */     setContentPane((Container)chartPanel);
/*     */   }
/*     */   
/*     */   private static JFreeChart createChart(XYDataset dataset) {
/*  96 */     JFreeChart chart = ChartFactory.createTimeSeriesChart("Legal & General Unit Trust Prices", "Date", "Price Per Unit", dataset, true, true, false);
/* 106 */     chart.setBackgroundPaint(Color.white);
/* 108 */     XYPlot plot = (XYPlot)chart.getPlot();
/* 109 */     plot.setBackgroundPaint(Color.lightGray);
/* 110 */     plot.setDomainGridlinePaint(Color.white);
/* 111 */     plot.setRangeGridlinePaint(Color.white);
/* 112 */     plot.setAxisOffset(new RectangleInsets(5.0D, 5.0D, 5.0D, 5.0D));
/* 113 */     plot.setDomainCrosshairVisible(true);
/* 114 */     plot.setRangeCrosshairVisible(true);
/* 116 */     XYItemRenderer r = plot.getRenderer();
/* 117 */     if (r instanceof XYLineAndShapeRenderer) {
/* 118 */       XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)r;
/* 119 */       renderer.setBaseShapesVisible(true);
/* 120 */       renderer.setBaseShapesFilled(true);
/*     */     } 
/* 123 */     DateAxis axis = (DateAxis)plot.getDomainAxis();
/* 124 */     axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
/* 126 */     return chart;
/*     */   }
/*     */   
/*     */   private static XYDataset createDataset() {
/* 137 */     TimeSeries s1 = new TimeSeries("L&G European Index Trust", Month.class);
/* 138 */     s1.add((RegularTimePeriod)new Month(2, 2001), 181.8D);
/* 139 */     s1.add((RegularTimePeriod)new Month(3, 2001), 167.3D);
/* 140 */     s1.add((RegularTimePeriod)new Month(4, 2001), 153.8D);
/* 141 */     s1.add((RegularTimePeriod)new Month(5, 2001), 167.6D);
/* 142 */     s1.add((RegularTimePeriod)new Month(6, 2001), 158.8D);
/* 143 */     s1.add((RegularTimePeriod)new Month(7, 2001), 148.3D);
/* 144 */     s1.add((RegularTimePeriod)new Month(8, 2001), 153.9D);
/* 145 */     s1.add((RegularTimePeriod)new Month(9, 2001), 142.7D);
/* 146 */     s1.add((RegularTimePeriod)new Month(10, 2001), 123.2D);
/* 147 */     s1.add((RegularTimePeriod)new Month(11, 2001), 131.8D);
/* 148 */     s1.add((RegularTimePeriod)new Month(12, 2001), 139.6D);
/* 149 */     s1.add((RegularTimePeriod)new Month(1, 2002), 142.9D);
/* 150 */     s1.add((RegularTimePeriod)new Month(2, 2002), 138.7D);
/* 151 */     s1.add((RegularTimePeriod)new Month(3, 2002), 137.3D);
/* 152 */     s1.add((RegularTimePeriod)new Month(4, 2002), 143.9D);
/* 153 */     s1.add((RegularTimePeriod)new Month(5, 2002), 139.8D);
/* 154 */     s1.add((RegularTimePeriod)new Month(6, 2002), 137.0D);
/* 155 */     s1.add((RegularTimePeriod)new Month(7, 2002), 132.8D);
/* 157 */     TimeSeries s2 = new TimeSeries("L&G UK Index Trust", Month.class);
/* 158 */     s2.add((RegularTimePeriod)new Month(2, 2001), 129.6D);
/* 159 */     s2.add((RegularTimePeriod)new Month(3, 2001), 123.2D);
/* 160 */     s2.add((RegularTimePeriod)new Month(4, 2001), 117.2D);
/* 161 */     s2.add((RegularTimePeriod)new Month(5, 2001), 124.1D);
/* 162 */     s2.add((RegularTimePeriod)new Month(6, 2001), 122.6D);
/* 163 */     s2.add((RegularTimePeriod)new Month(7, 2001), 119.2D);
/* 164 */     s2.add((RegularTimePeriod)new Month(8, 2001), 116.5D);
/* 165 */     s2.add((RegularTimePeriod)new Month(9, 2001), 112.7D);
/* 166 */     s2.add((RegularTimePeriod)new Month(10, 2001), 101.5D);
/* 167 */     s2.add((RegularTimePeriod)new Month(11, 2001), 106.1D);
/* 168 */     s2.add((RegularTimePeriod)new Month(12, 2001), 110.3D);
/* 169 */     s2.add((RegularTimePeriod)new Month(1, 2002), 111.7D);
/* 170 */     s2.add((RegularTimePeriod)new Month(2, 2002), 111.0D);
/* 171 */     s2.add((RegularTimePeriod)new Month(3, 2002), 109.6D);
/* 172 */     s2.add((RegularTimePeriod)new Month(4, 2002), 113.2D);
/* 173 */     s2.add((RegularTimePeriod)new Month(5, 2002), 111.6D);
/* 174 */     s2.add((RegularTimePeriod)new Month(6, 2002), 108.8D);
/* 175 */     s2.add((RegularTimePeriod)new Month(7, 2002), 101.6D);
/* 177 */     TimeSeriesCollection dataset = new TimeSeriesCollection();
/* 178 */     dataset.addSeries(s1);
/* 179 */     dataset.addSeries(s2);
/* 181 */     dataset.setDomainIsPointsInTime(true);
/* 183 */     return (XYDataset)dataset;
/*     */   }
/*     */   
/*     */   public static JPanel createDemoPanel() {
/* 193 */     JFreeChart chart = createChart(createDataset());
/* 194 */     return (JPanel)new ChartPanel(chart);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 204 */     TimeSeriesChartDemo1 demo = new TimeSeriesChartDemo1("Time Series Chart Demo 1");
/* 207 */     demo.pack();
/* 208 */     RefineryUtilities.centerFrameOnScreen((Window)demo);
/* 209 */     demo.setVisible(true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\demo\TimeSeriesChartDemo1.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */