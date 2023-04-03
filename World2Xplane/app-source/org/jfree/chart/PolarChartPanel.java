/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PolarPlot;
/*     */ 
/*     */ public class PolarChartPanel extends ChartPanel {
/*     */   private static final String POLAR_ZOOM_IN_ACTION_COMMAND = "Polar Zoom In";
/*     */   
/*     */   private static final String POLAR_ZOOM_OUT_ACTION_COMMAND = "Polar Zoom Out";
/*     */   
/*     */   private static final String POLAR_AUTO_RANGE_ACTION_COMMAND = "Polar Auto Range";
/*     */   
/*     */   public PolarChartPanel(JFreeChart chart) {
/* 101 */     this(chart, true);
/*     */   }
/*     */   
/*     */   public PolarChartPanel(JFreeChart chart, boolean useBuffer) {
/* 111 */     super(chart, useBuffer);
/* 112 */     checkChart(chart);
/* 113 */     setMinimumDrawWidth(200);
/* 114 */     setMinimumDrawHeight(200);
/* 115 */     setMaximumDrawWidth(2000);
/* 116 */     setMaximumDrawHeight(2000);
/*     */   }
/*     */   
/*     */   public void setChart(JFreeChart chart) {
/* 128 */     checkChart(chart);
/* 129 */     super.setChart(chart);
/*     */   }
/*     */   
/*     */   protected JPopupMenu createPopupMenu(boolean properties, boolean save, boolean print, boolean zoom) {
/* 147 */     JPopupMenu result = super.createPopupMenu(properties, save, print, zoom);
/* 148 */     int zoomInIndex = getPopupMenuItem(result, "Zoom In");
/* 149 */     int zoomOutIndex = getPopupMenuItem(result, "Zoom Out");
/* 150 */     int autoIndex = getPopupMenuItem(result, "Auto Range");
/* 151 */     if (zoom) {
/* 152 */       JMenuItem zoomIn = new JMenuItem("Zoom In");
/* 153 */       zoomIn.setActionCommand("Polar Zoom In");
/* 154 */       zoomIn.addActionListener(this);
/* 156 */       JMenuItem zoomOut = new JMenuItem("Zoom Out");
/* 157 */       zoomOut.setActionCommand("Polar Zoom Out");
/* 158 */       zoomOut.addActionListener(this);
/* 160 */       JMenuItem auto = new JMenuItem("Auto Range");
/* 161 */       auto.setActionCommand("Polar Auto Range");
/* 162 */       auto.addActionListener(this);
/* 164 */       if (zoomInIndex != -1) {
/* 165 */         result.remove(zoomInIndex);
/*     */       } else {
/* 168 */         zoomInIndex = result.getComponentCount() - 1;
/*     */       } 
/* 170 */       result.add(zoomIn, zoomInIndex);
/* 171 */       if (zoomOutIndex != -1) {
/* 172 */         result.remove(zoomOutIndex);
/*     */       } else {
/* 175 */         zoomOutIndex = zoomInIndex + 1;
/*     */       } 
/* 177 */       result.add(zoomOut, zoomOutIndex);
/* 178 */       if (autoIndex != -1) {
/* 179 */         result.remove(autoIndex);
/*     */       } else {
/* 182 */         autoIndex = zoomOutIndex + 1;
/*     */       } 
/* 184 */       result.add(auto, autoIndex);
/*     */     } 
/* 186 */     return result;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent event) {
/* 195 */     String command = event.getActionCommand();
/* 197 */     if (command.equals("Polar Zoom In")) {
/* 198 */       PolarPlot plot = (PolarPlot)getChart().getPlot();
/* 199 */       plot.zoom(0.5D);
/* 201 */     } else if (command.equals("Polar Zoom Out")) {
/* 202 */       PolarPlot plot = (PolarPlot)getChart().getPlot();
/* 203 */       plot.zoom(2.0D);
/* 205 */     } else if (command.equals("Polar Auto Range")) {
/* 206 */       PolarPlot plot = (PolarPlot)getChart().getPlot();
/* 207 */       plot.getAxis().setAutoRange(true);
/*     */     } else {
/* 210 */       super.actionPerformed(event);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkChart(JFreeChart chart) {
/* 228 */     Plot plot = chart.getPlot();
/* 229 */     if (!(plot instanceof PolarPlot))
/* 230 */       throw new IllegalArgumentException("plot is not a PolarPlot"); 
/*     */   }
/*     */   
/*     */   private int getPopupMenuItem(JPopupMenu menu, String text) {
/* 243 */     int index = -1;
/* 244 */     for (int i = 0; index == -1 && i < menu.getComponentCount(); i++) {
/* 245 */       Component comp = menu.getComponent(i);
/* 246 */       if (comp instanceof JMenuItem) {
/* 247 */         JMenuItem item = (JMenuItem)comp;
/* 248 */         if (text.equals(item.getText()))
/* 249 */           index = i; 
/*     */       } 
/*     */     } 
/* 253 */     return index;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\PolarChartPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */