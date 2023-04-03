/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.event.PlotChangeEvent;
/*     */ import org.jfree.chart.title.TextTitle;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.CategoryToPieDataset;
/*     */ import org.jfree.data.general.Dataset;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.TableOrder;
/*     */ 
/*     */ public class MultiplePiePlot extends Plot implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -355377800470807389L;
/*     */   
/*     */   private JFreeChart pieChart;
/*     */   
/*     */   private CategoryDataset dataset;
/*     */   
/*     */   private TableOrder dataExtractOrder;
/*     */   
/*  95 */   private double limit = 0.0D;
/*     */   
/*     */   public MultiplePiePlot() {
/* 101 */     this((CategoryDataset)null);
/*     */   }
/*     */   
/*     */   public MultiplePiePlot(CategoryDataset dataset) {
/* 111 */     this.dataset = dataset;
/* 112 */     PiePlot piePlot = new PiePlot(null);
/* 113 */     this.pieChart = new JFreeChart(piePlot);
/* 114 */     this.pieChart.removeLegend();
/* 115 */     this.dataExtractOrder = TableOrder.BY_COLUMN;
/* 116 */     this.pieChart.setBackgroundPaint(null);
/* 117 */     TextTitle seriesTitle = new TextTitle("Series Title", new Font("SansSerif", 1, 12));
/* 120 */     seriesTitle.setPosition(RectangleEdge.BOTTOM);
/* 121 */     this.pieChart.setTitle(seriesTitle);
/*     */   }
/*     */   
/*     */   public CategoryDataset getDataset() {
/* 130 */     return this.dataset;
/*     */   }
/*     */   
/*     */   public void setDataset(CategoryDataset dataset) {
/* 142 */     if (this.dataset != null)
/* 143 */       this.dataset.removeChangeListener(this); 
/* 147 */     this.dataset = dataset;
/* 148 */     if (dataset != null) {
/* 149 */       setDatasetGroup(dataset.getGroup());
/* 150 */       dataset.addChangeListener(this);
/*     */     } 
/* 154 */     datasetChanged(new DatasetChangeEvent(this, (Dataset)dataset));
/*     */   }
/*     */   
/*     */   public JFreeChart getPieChart() {
/* 163 */     return this.pieChart;
/*     */   }
/*     */   
/*     */   public void setPieChart(JFreeChart pieChart) {
/* 172 */     this.pieChart = pieChart;
/* 173 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public TableOrder getDataExtractOrder() {
/* 182 */     return this.dataExtractOrder;
/*     */   }
/*     */   
/*     */   public void setDataExtractOrder(TableOrder order) {
/* 192 */     if (order == null)
/* 193 */       throw new IllegalArgumentException("Null 'order' argument"); 
/* 195 */     this.dataExtractOrder = order;
/* 196 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getLimit() {
/* 206 */     return this.limit;
/*     */   }
/*     */   
/*     */   public void setLimit(double limit) {
/* 216 */     this.limit = limit;
/* 217 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public String getPlotType() {
/* 226 */     return "Multiple Pie Plot";
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/* 248 */     RectangleInsets insets = getInsets();
/* 249 */     insets.trim(area);
/* 250 */     drawBackground(g2, area);
/* 251 */     drawOutline(g2, area);
/* 254 */     if (DatasetUtilities.isEmptyOrNull(this.dataset)) {
/* 255 */       drawNoDataMessage(g2, area);
/*     */       return;
/*     */     } 
/* 259 */     int pieCount = 0;
/* 260 */     if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 261 */       pieCount = this.dataset.getRowCount();
/*     */     } else {
/* 264 */       pieCount = this.dataset.getColumnCount();
/*     */     } 
/* 268 */     int displayCols = (int)Math.ceil(Math.sqrt(pieCount));
/* 269 */     int displayRows = (int)Math.ceil(pieCount / displayCols);
/* 273 */     if (displayCols > displayRows && area.getWidth() < area.getHeight()) {
/* 274 */       int temp = displayCols;
/* 275 */       displayCols = displayRows;
/* 276 */       displayRows = temp;
/*     */     } 
/* 281 */     int x = (int)area.getX();
/* 282 */     int y = (int)area.getY();
/* 283 */     int width = (int)area.getWidth() / displayCols;
/* 284 */     int height = (int)area.getHeight() / displayRows;
/* 285 */     int row = 0;
/* 286 */     int column = 0;
/* 287 */     int diff = displayRows * displayCols - pieCount;
/* 288 */     int xoffset = 0;
/* 289 */     Rectangle rect = new Rectangle();
/* 291 */     for (int pieIndex = 0; pieIndex < pieCount; pieIndex++) {
/*     */       CategoryToPieDataset categoryToPieDataset1;
/* 292 */       rect.setBounds(x + xoffset + width * column, y + height * row, width, height);
/* 297 */       String title = null;
/* 298 */       if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 299 */         title = this.dataset.getRowKey(pieIndex).toString();
/*     */       } else {
/* 302 */         title = this.dataset.getColumnKey(pieIndex).toString();
/*     */       } 
/* 304 */       this.pieChart.setTitle(title);
/* 306 */       PieDataset piedataset = null;
/* 307 */       CategoryToPieDataset categoryToPieDataset2 = new CategoryToPieDataset(this.dataset, this.dataExtractOrder, pieIndex);
/* 310 */       if (this.limit > 0.0D) {
/* 311 */         piedataset = DatasetUtilities.createConsolidatedPieDataset((PieDataset)categoryToPieDataset2, "Other", this.limit);
/*     */       } else {
/* 316 */         categoryToPieDataset1 = categoryToPieDataset2;
/*     */       } 
/* 318 */       PiePlot piePlot = (PiePlot)this.pieChart.getPlot();
/* 319 */       piePlot.setDataset((PieDataset)categoryToPieDataset1);
/* 320 */       piePlot.setPieIndex(pieIndex);
/* 321 */       ChartRenderingInfo subinfo = null;
/* 322 */       if (info != null)
/* 323 */         subinfo = new ChartRenderingInfo(); 
/* 325 */       this.pieChart.draw(g2, rect, subinfo);
/* 326 */       if (info != null) {
/* 327 */         info.getOwner().getEntityCollection().addAll(subinfo.getEntityCollection());
/* 330 */         info.addSubplotInfo(subinfo.getPlotInfo());
/*     */       } 
/* 333 */       column++;
/* 334 */       if (column == displayCols) {
/* 335 */         column = 0;
/* 336 */         row++;
/* 338 */         if (row == displayRows - 1 && diff != 0)
/* 339 */           xoffset = diff * width / 2; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public LegendItemCollection getLegendItems() {
/* 353 */     LegendItemCollection result = new LegendItemCollection();
/* 355 */     if (this.dataset != null) {
/* 356 */       List keys = null;
/* 358 */       if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 359 */         keys = this.dataset.getColumnKeys();
/* 361 */       } else if (this.dataExtractOrder == TableOrder.BY_COLUMN) {
/* 362 */         keys = this.dataset.getRowKeys();
/*     */       } 
/* 365 */       if (keys != null) {
/* 366 */         int section = 0;
/* 367 */         Iterator iterator = keys.iterator();
/* 368 */         while (iterator.hasNext()) {
/* 369 */           String label = iterator.next().toString();
/* 370 */           String description = label;
/* 371 */           PiePlot plot = (PiePlot)this.pieChart.getPlot();
/* 372 */           Paint paint = plot.getSectionPaint(section);
/* 373 */           Paint outlinePaint = plot.getSectionOutlinePaint(section);
/* 374 */           Stroke outlineStroke = plot.getSectionOutlineStroke(section);
/* 376 */           LegendItem item = new LegendItem(label, description, null, null, Plot.DEFAULT_LEGEND_ITEM_CIRCLE, paint, outlineStroke, outlinePaint);
/* 380 */           result.add(item);
/* 381 */           section++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 385 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 395 */     if (obj == this)
/* 396 */       return true; 
/* 398 */     if (!(obj instanceof MultiplePiePlot))
/* 399 */       return false; 
/* 401 */     MultiplePiePlot that = (MultiplePiePlot)obj;
/* 402 */     if (this.dataExtractOrder != that.dataExtractOrder)
/* 403 */       return false; 
/* 405 */     if (this.limit != that.limit)
/* 406 */       return false; 
/* 408 */     if (!ObjectUtilities.equal(this.pieChart, that.pieChart))
/* 409 */       return false; 
/* 411 */     if (!super.equals(obj))
/* 412 */       return false; 
/* 414 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\MultiplePiePlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */