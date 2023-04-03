/*     */ package com.world2xplane.GUI;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.text.DecimalFormat;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.Timer;
/*     */ import org.jfree.chart.ChartFactory;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.axis.DateAxis;
/*     */ import org.jfree.chart.axis.NumberAxis;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.time.Millisecond;
/*     */ import org.jfree.data.time.RegularTimePeriod;
/*     */ import org.jfree.data.time.TimeSeries;
/*     */ import org.jfree.data.time.TimeSeriesCollection;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ 
/*     */ public class MemoryUsageDemo extends JPanel {
/*     */   private TimeSeries total;
/*     */   
/*     */   private TimeSeries free;
/*     */   
/*     */   public MemoryUsageDemo(int maxAge) {
/*  84 */     super(new BorderLayout());
/*  88 */     this.total = new TimeSeries("Total Memory", Millisecond.class);
/*  89 */     this.total.setMaximumItemAge(maxAge);
/*  90 */     this.free = new TimeSeries("Free Memory", Millisecond.class);
/*  91 */     this.free.setMaximumItemAge(maxAge);
/*  92 */     TimeSeriesCollection dataset = new TimeSeriesCollection();
/*  93 */     dataset.addSeries(this.total);
/*  94 */     dataset.addSeries(this.free);
/*  99 */     JFreeChart chart = ChartFactory.createTimeSeriesChart("Memory Usage", "", "Memory (MB)", (XYDataset)dataset, true, true, false);
/* 100 */     chart.setBackgroundPaint(Color.white);
/* 103 */     chart.setBorderVisible(true);
/* 104 */     chart.setBorderPaint(Color.black);
/* 105 */     chart.setPadding(RectangleInsets.ZERO_INSETS);
/* 106 */     chart.setAntiAlias(true);
/* 108 */     XYPlot plot = (XYPlot)chart.getPlot();
/* 109 */     plot.setBackgroundPaint(Color.lightGray);
/* 110 */     DateAxis axis = (DateAxis)plot.getDomainAxis();
/* 111 */     axis.setVisible(false);
/* 113 */     NumberAxis nAxis = (NumberAxis)plot.getRangeAxis();
/* 114 */     nAxis.setNumberFormatOverride(new DecimalFormat("##"));
/* 116 */     ChartPanel chartPanel = new ChartPanel(chart, true);
/* 117 */     add((Component)chartPanel);
/*     */   }
/*     */   
/*     */   private void addTotalObservation(double y) {
/* 127 */     this.total.add((RegularTimePeriod)new Millisecond(), y);
/*     */   }
/*     */   
/*     */   private void addFreeObservation(double y) {
/* 136 */     this.free.add((RegularTimePeriod)new Millisecond(), y);
/*     */   }
/*     */   
/*     */   class DataGenerator extends Timer implements ActionListener {
/*     */     DataGenerator(int interval) {
/* 150 */       super(interval, null);
/* 151 */       addActionListener(this);
/*     */     }
/*     */     
/*     */     public void actionPerformed(ActionEvent event) {
/* 164 */       long f = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024L / 1024L;
/* 165 */       long t = Runtime.getRuntime().maxMemory() / 1024L / 1024L;
/* 166 */       MemoryUsageDemo.this.addTotalObservation(t);
/* 167 */       MemoryUsageDemo.this.addFreeObservation(f);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 179 */     JFrame frame = new JFrame("Memory Usage Demo");
/* 180 */     MemoryUsageDemo panel = new MemoryUsageDemo(30000);
/* 181 */     frame.getContentPane().add(panel, "Center");
/* 182 */     frame.setBounds(200, 120, 600, 280);
/* 183 */     frame.setVisible(true);
/* 184 */     panel.getClass();
/* 184 */     (new DataGenerator(1000)).start();
/* 186 */     frame.addWindowListener(new WindowAdapter() {
/*     */           public void windowClosing(WindowEvent e) {
/* 188 */             System.exit(0);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\GUI\MemoryUsageDemo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */