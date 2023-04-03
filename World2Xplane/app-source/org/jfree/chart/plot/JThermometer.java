/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.CardLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.io.Serializable;
/*     */ import java.text.DecimalFormat;
/*     */ import javax.swing.JPanel;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.title.TextTitle;
/*     */ import org.jfree.chart.title.Title;
/*     */ import org.jfree.data.general.DefaultValueDataset;
/*     */ import org.jfree.data.general.ValueDataset;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ 
/*     */ public class JThermometer extends JPanel implements Serializable {
/*     */   private static final long serialVersionUID = 1079905665515589820L;
/*     */   
/*     */   private DefaultValueDataset data;
/*     */   
/*     */   private JFreeChart chart;
/*     */   
/*     */   private ChartPanel panel;
/*     */   
/*  90 */   private ThermometerPlot plot = new ThermometerPlot();
/*     */   
/*     */   public JThermometer() {
/*  96 */     super(new CardLayout());
/*  97 */     this.plot.setInsets(new RectangleInsets(5.0D, 5.0D, 5.0D, 5.0D));
/*  98 */     this.data = new DefaultValueDataset();
/* 100 */     this.plot.setDataset((ValueDataset)this.data);
/* 101 */     this.chart = new JFreeChart(null, JFreeChart.DEFAULT_TITLE_FONT, this.plot, false);
/* 104 */     this.panel = new ChartPanel(this.chart);
/* 105 */     add((Component)this.panel, "Panel");
/* 106 */     setBackground(getBackground());
/*     */   }
/*     */   
/*     */   public void addSubtitle(Title subtitle) {
/* 115 */     this.chart.addSubtitle(subtitle);
/*     */   }
/*     */   
/*     */   public void addSubtitle(String subtitle) {
/* 124 */     this.chart.addSubtitle((Title)new TextTitle(subtitle));
/*     */   }
/*     */   
/*     */   public void addSubtitle(String subtitle, Font font) {
/* 134 */     this.chart.addSubtitle((Title)new TextTitle(subtitle, font));
/*     */   }
/*     */   
/*     */   public void setValueFormat(DecimalFormat df) {
/* 143 */     this.plot.setValueFormat(df);
/*     */   }
/*     */   
/*     */   public void setRange(double lower, double upper) {
/* 153 */     this.plot.setRange(lower, upper);
/*     */   }
/*     */   
/*     */   public void setSubrangeInfo(int range, double displayLow, double displayHigh) {
/* 165 */     this.plot.setSubrangeInfo(range, displayLow, displayHigh);
/*     */   }
/*     */   
/*     */   public void setSubrangeInfo(int range, double rangeLow, double rangeHigh, double displayLow, double displayHigh) {
/* 181 */     this.plot.setSubrangeInfo(range, rangeLow, rangeHigh, displayLow, displayHigh);
/*     */   }
/*     */   
/*     */   public void setValueLocation(int loc) {
/* 192 */     this.plot.setValueLocation(loc);
/* 193 */     this.panel.repaint();
/*     */   }
/*     */   
/*     */   public void setValuePaint(Paint paint) {
/* 202 */     this.plot.setValuePaint(paint);
/*     */   }
/*     */   
/*     */   public Number getValue() {
/* 211 */     if (this.data != null)
/* 212 */       return this.data.getValue(); 
/* 215 */     return null;
/*     */   }
/*     */   
/*     */   public void setValue(double value) {
/* 225 */     setValue(new Double(value));
/*     */   }
/*     */   
/*     */   public void setValue(Number value) {
/* 234 */     if (this.data != null)
/* 235 */       this.data.setValue(value); 
/*     */   }
/*     */   
/*     */   public void setUnits(int i) {
/* 245 */     if (this.plot != null)
/* 246 */       this.plot.setUnits(i); 
/*     */   }
/*     */   
/*     */   public void setOutlinePaint(Paint p) {
/* 256 */     if (this.plot != null)
/* 257 */       this.plot.setOutlinePaint(p); 
/*     */   }
/*     */   
/*     */   public void setForeground(Color fg) {
/* 267 */     super.setForeground(fg);
/* 268 */     if (this.plot != null)
/* 269 */       this.plot.setThermometerPaint(fg); 
/*     */   }
/*     */   
/*     */   public void setBackground(Color bg) {
/* 279 */     super.setBackground(bg);
/* 280 */     if (this.plot != null)
/* 281 */       this.plot.setBackgroundPaint(bg); 
/* 283 */     if (this.chart != null)
/* 284 */       this.chart.setBackgroundPaint(bg); 
/* 286 */     if (this.panel != null)
/* 287 */       this.panel.setBackground(bg); 
/*     */   }
/*     */   
/*     */   public void setValueFont(Font f) {
/* 297 */     if (this.plot != null)
/* 298 */       this.plot.setValueFont(f); 
/*     */   }
/*     */   
/*     */   public Font getTickLabelFont() {
/* 308 */     ValueAxis axis = this.plot.getRangeAxis();
/* 309 */     return axis.getTickLabelFont();
/*     */   }
/*     */   
/*     */   public void setTickLabelFont(Font font) {
/* 318 */     ValueAxis axis = this.plot.getRangeAxis();
/* 319 */     axis.setTickLabelFont(font);
/*     */   }
/*     */   
/*     */   public void changeTickFontSize(int delta) {
/* 328 */     Font f = getTickLabelFont();
/* 329 */     String fName = f.getFontName();
/* 330 */     Font newFont = new Font(fName, f.getStyle(), f.getSize() + delta);
/* 331 */     setTickLabelFont(newFont);
/*     */   }
/*     */   
/*     */   public void setTickFontStyle(int style) {
/* 340 */     Font f = getTickLabelFont();
/* 341 */     String fName = f.getFontName();
/* 342 */     Font newFont = new Font(fName, style, f.getSize());
/* 343 */     setTickLabelFont(newFont);
/*     */   }
/*     */   
/*     */   public void setFollowDataInSubranges(boolean flag) {
/* 353 */     this.plot.setFollowDataInSubranges(flag);
/*     */   }
/*     */   
/*     */   public void setShowValueLines(boolean b) {
/* 362 */     this.plot.setShowValueLines(b);
/*     */   }
/*     */   
/*     */   public void setShowAxisLocation(int location) {
/* 371 */     this.plot.setAxisLocation(location);
/*     */   }
/*     */   
/*     */   public int getShowAxisLocation() {
/* 380 */     return this.plot.getAxisLocation();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\JThermometer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */