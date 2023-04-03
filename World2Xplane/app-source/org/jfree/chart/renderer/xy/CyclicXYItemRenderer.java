/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CyclicNumberAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.DomainOrder;
/*     */ import org.jfree.data.general.DatasetChangeListener;
/*     */ import org.jfree.data.general.DatasetGroup;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ public class CyclicXYItemRenderer extends StandardXYItemRenderer implements Serializable {
/*     */   private static final long serialVersionUID = 4035912243303764892L;
/*     */   
/*     */   public CyclicXYItemRenderer() {}
/*     */   
/*     */   public CyclicXYItemRenderer(int type) {
/*  96 */     super(type);
/*     */   }
/*     */   
/*     */   public CyclicXYItemRenderer(int type, XYToolTipGenerator labelGenerator) {
/* 106 */     super(type, labelGenerator);
/*     */   }
/*     */   
/*     */   public CyclicXYItemRenderer(int type, XYToolTipGenerator labelGenerator, XYURLGenerator urlGenerator) {
/* 119 */     super(type, labelGenerator, urlGenerator);
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 157 */     if (!getPlotLines() || (!(domainAxis instanceof CyclicNumberAxis) && !(rangeAxis instanceof CyclicNumberAxis)) || item <= 0) {
/* 159 */       super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
/*     */       return;
/*     */     } 
/* 168 */     Number xn = dataset.getX(series, item - 1);
/* 169 */     Number yn = dataset.getY(series, item - 1);
/* 171 */     if (yn == null || xn == null) {
/* 172 */       super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
/*     */       return;
/*     */     } 
/* 179 */     double[] x = new double[2];
/* 180 */     double[] y = new double[2];
/* 181 */     x[0] = xn.doubleValue();
/* 182 */     y[0] = yn.doubleValue();
/* 185 */     xn = dataset.getX(series, item);
/* 186 */     yn = dataset.getY(series, item);
/* 188 */     if (yn == null || xn == null)
/*     */       return; 
/* 191 */     x[1] = xn.doubleValue();
/* 192 */     y[1] = yn.doubleValue();
/* 195 */     double xcycleBound = Double.NaN;
/* 196 */     double ycycleBound = Double.NaN;
/* 197 */     boolean xBoundMapping = false, yBoundMapping = false;
/* 198 */     CyclicNumberAxis cnax = null, cnay = null;
/* 200 */     if (domainAxis instanceof CyclicNumberAxis) {
/* 201 */       cnax = (CyclicNumberAxis)domainAxis;
/* 202 */       xcycleBound = cnax.getCycleBound();
/* 203 */       xBoundMapping = cnax.isBoundMappedToLastCycle();
/* 207 */       if (x[0] != x[1] && ((xcycleBound >= x[0] && xcycleBound <= x[1]) || (xcycleBound >= x[1] && xcycleBound <= x[0]))) {
/* 212 */         double[] nx = new double[3];
/* 213 */         double[] ny = new double[3];
/* 214 */         nx[0] = x[0];
/* 214 */         nx[2] = x[1];
/* 214 */         ny[0] = y[0];
/* 214 */         ny[2] = y[1];
/* 215 */         nx[1] = xcycleBound;
/* 216 */         ny[1] = (y[1] - y[0]) * (xcycleBound - x[0]) / (x[1] - x[0]) + y[0];
/* 218 */         x = nx;
/* 218 */         y = ny;
/*     */       } 
/*     */     } 
/* 222 */     if (rangeAxis instanceof CyclicNumberAxis) {
/* 223 */       cnay = (CyclicNumberAxis)rangeAxis;
/* 224 */       ycycleBound = cnay.getCycleBound();
/* 225 */       yBoundMapping = cnay.isBoundMappedToLastCycle();
/* 228 */       if (y[0] != y[1] && ((ycycleBound >= y[0] && ycycleBound <= y[1]) || (ycycleBound >= y[1] && ycycleBound <= y[0]))) {
/* 231 */         double[] nx = new double[x.length + 1];
/* 232 */         double[] ny = new double[y.length + 1];
/* 233 */         nx[0] = x[0];
/* 233 */         nx[2] = x[1];
/* 233 */         ny[0] = y[0];
/* 233 */         ny[2] = y[1];
/* 234 */         ny[1] = ycycleBound;
/* 235 */         nx[1] = (x[1] - x[0]) * (ycycleBound - y[0]) / (y[1] - y[0]) + x[0];
/* 237 */         if (x.length == 3) {
/* 238 */           nx[3] = x[2];
/* 238 */           ny[3] = y[2];
/*     */         } 
/* 240 */         x = nx;
/* 240 */         y = ny;
/* 242 */       } else if (x.length == 3 && y[1] != y[2] && ((ycycleBound >= y[1] && ycycleBound <= y[2]) || (ycycleBound >= y[2] && ycycleBound <= y[1]))) {
/* 245 */         double[] nx = new double[4];
/* 246 */         double[] ny = new double[4];
/* 247 */         nx[0] = x[0];
/* 247 */         nx[1] = x[1];
/* 247 */         nx[3] = x[2];
/* 248 */         ny[0] = y[0];
/* 248 */         ny[1] = y[1];
/* 248 */         ny[3] = y[2];
/* 249 */         ny[2] = ycycleBound;
/* 250 */         nx[2] = (x[2] - x[1]) * (ycycleBound - y[1]) / (y[2] - y[1]) + x[1];
/* 252 */         x = nx;
/* 252 */         y = ny;
/*     */       } 
/*     */     } 
/* 257 */     if (x.length == 2) {
/* 258 */       super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
/*     */       return;
/*     */     } 
/* 265 */     OverwriteDataSet newset = new OverwriteDataSet(x, y, dataset);
/* 267 */     if (cnax != null) {
/* 268 */       if (xcycleBound == x[0])
/* 269 */         cnax.setBoundMappedToLastCycle((x[1] <= xcycleBound)); 
/* 271 */       if (xcycleBound == x[1])
/* 272 */         cnax.setBoundMappedToLastCycle((x[0] <= xcycleBound)); 
/*     */     } 
/* 275 */     if (cnay != null) {
/* 276 */       if (ycycleBound == y[0])
/* 277 */         cnay.setBoundMappedToLastCycle((y[1] <= ycycleBound)); 
/* 279 */       if (ycycleBound == y[1])
/* 280 */         cnay.setBoundMappedToLastCycle((y[0] <= ycycleBound)); 
/*     */     } 
/* 283 */     super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, newset, series, 1, crosshairState, pass);
/* 288 */     if (cnax != null) {
/* 289 */       if (xcycleBound == x[1])
/* 290 */         cnax.setBoundMappedToLastCycle((x[2] <= xcycleBound)); 
/* 292 */       if (xcycleBound == x[2])
/* 293 */         cnax.setBoundMappedToLastCycle((x[1] <= xcycleBound)); 
/*     */     } 
/* 296 */     if (cnay != null) {
/* 297 */       if (ycycleBound == y[1])
/* 298 */         cnay.setBoundMappedToLastCycle((y[2] <= ycycleBound)); 
/* 300 */       if (ycycleBound == y[2])
/* 301 */         cnay.setBoundMappedToLastCycle((y[1] <= ycycleBound)); 
/*     */     } 
/* 304 */     super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, newset, series, 2, crosshairState, pass);
/* 309 */     if (x.length == 4) {
/* 310 */       if (cnax != null) {
/* 311 */         if (xcycleBound == x[2])
/* 312 */           cnax.setBoundMappedToLastCycle((x[3] <= xcycleBound)); 
/* 314 */         if (xcycleBound == x[3])
/* 315 */           cnax.setBoundMappedToLastCycle((x[2] <= xcycleBound)); 
/*     */       } 
/* 318 */       if (cnay != null) {
/* 319 */         if (ycycleBound == y[2])
/* 320 */           cnay.setBoundMappedToLastCycle((y[3] <= ycycleBound)); 
/* 322 */         if (ycycleBound == y[3])
/* 323 */           cnay.setBoundMappedToLastCycle((y[2] <= ycycleBound)); 
/*     */       } 
/* 326 */       super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, newset, series, 3, crosshairState, pass);
/*     */     } 
/* 332 */     if (cnax != null)
/* 333 */       cnax.setBoundMappedToLastCycle(xBoundMapping); 
/* 335 */     if (cnay != null)
/* 336 */       cnay.setBoundMappedToLastCycle(yBoundMapping); 
/*     */   }
/*     */   
/*     */   protected static class OverwriteDataSet implements XYDataset {
/*     */     protected XYDataset delegateSet;
/*     */     
/*     */     Double[] x;
/*     */     
/*     */     Double[] y;
/*     */     
/*     */     public OverwriteDataSet(double[] x, double[] y, XYDataset delegateSet) {
/* 360 */       this.delegateSet = delegateSet;
/* 361 */       this.x = new Double[x.length];
/* 361 */       this.y = new Double[y.length];
/* 362 */       for (int i = 0; i < x.length; i++) {
/* 363 */         this.x[i] = new Double(x[i]);
/* 364 */         this.y[i] = new Double(y[i]);
/*     */       } 
/*     */     }
/*     */     
/*     */     public DomainOrder getDomainOrder() {
/* 374 */       return DomainOrder.NONE;
/*     */     }
/*     */     
/*     */     public int getItemCount(int series) {
/* 385 */       return this.x.length;
/*     */     }
/*     */     
/*     */     public Number getX(int series, int item) {
/* 397 */       return this.x[item];
/*     */     }
/*     */     
/*     */     public double getXValue(int series, int item) {
/* 410 */       double result = Double.NaN;
/* 411 */       Number x = getX(series, item);
/* 412 */       if (x != null)
/* 413 */         result = x.doubleValue(); 
/* 415 */       return result;
/*     */     }
/*     */     
/*     */     public Number getY(int series, int item) {
/* 427 */       return this.y[item];
/*     */     }
/*     */     
/*     */     public double getYValue(int series, int item) {
/* 440 */       double result = Double.NaN;
/* 441 */       Number y = getY(series, item);
/* 442 */       if (y != null)
/* 443 */         result = y.doubleValue(); 
/* 445 */       return result;
/*     */     }
/*     */     
/*     */     public int getSeriesCount() {
/* 454 */       return this.delegateSet.getSeriesCount();
/*     */     }
/*     */     
/*     */     public Comparable getSeriesKey(int series) {
/* 465 */       return this.delegateSet.getSeriesKey(series);
/*     */     }
/*     */     
/*     */     public int indexOf(Comparable seriesName) {
/* 476 */       return this.delegateSet.indexOf(seriesName);
/*     */     }
/*     */     
/*     */     public void addChangeListener(DatasetChangeListener listener) {}
/*     */     
/*     */     public void removeChangeListener(DatasetChangeListener listener) {}
/*     */     
/*     */     public DatasetGroup getGroup() {
/* 504 */       return this.delegateSet.getGroup();
/*     */     }
/*     */     
/*     */     public void setGroup(DatasetGroup group) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\CyclicXYItemRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */