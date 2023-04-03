/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.ResourceBundle;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.event.PlotChangeEvent;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.event.RendererChangeListener;
/*     */ import org.jfree.chart.renderer.WaferMapRenderer;
/*     */ import org.jfree.data.general.Dataset;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.WaferMapDataset;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ 
/*     */ public class WaferMapPlot extends Plot implements RendererChangeListener, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 4668320403707308155L;
/*     */   
/*  81 */   public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5F, 0, 2, 0.0F, new float[] { 2.0F, 2.0F }, 0.0F);
/*     */   
/*  89 */   public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
/*     */   
/*     */   public static final boolean DEFAULT_CROSSHAIR_VISIBLE = false;
/*     */   
/*  95 */   public static final Stroke DEFAULT_CROSSHAIR_STROKE = DEFAULT_GRIDLINE_STROKE;
/*     */   
/*  99 */   public static final Paint DEFAULT_CROSSHAIR_PAINT = Color.blue;
/*     */   
/* 102 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*     */   
/*     */   private PlotOrientation orientation;
/*     */   
/*     */   private WaferMapDataset dataset;
/*     */   
/*     */   private WaferMapRenderer renderer;
/*     */   
/*     */   public WaferMapPlot() {
/* 124 */     this((WaferMapDataset)null);
/*     */   }
/*     */   
/*     */   public WaferMapPlot(WaferMapDataset dataset) {
/* 133 */     this(dataset, (WaferMapRenderer)null);
/*     */   }
/*     */   
/*     */   public WaferMapPlot(WaferMapDataset dataset, WaferMapRenderer renderer) {
/* 146 */     this.orientation = PlotOrientation.VERTICAL;
/* 148 */     this.dataset = dataset;
/* 149 */     if (dataset != null)
/* 150 */       dataset.addChangeListener(this); 
/* 153 */     this.renderer = renderer;
/* 154 */     if (renderer != null) {
/* 155 */       renderer.setPlot(this);
/* 156 */       renderer.addChangeListener(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getPlotType() {
/* 167 */     return "WMAP_Plot";
/*     */   }
/*     */   
/*     */   public WaferMapDataset getDataset() {
/* 176 */     return this.dataset;
/*     */   }
/*     */   
/*     */   public void setDataset(WaferMapDataset dataset) {
/* 188 */     if (this.dataset != null)
/* 189 */       this.dataset.removeChangeListener(this); 
/* 193 */     this.dataset = dataset;
/* 194 */     if (dataset != null) {
/* 195 */       setDatasetGroup(dataset.getGroup());
/* 196 */       dataset.addChangeListener(this);
/*     */     } 
/* 200 */     datasetChanged(new DatasetChangeEvent(this, (Dataset)dataset));
/*     */   }
/*     */   
/*     */   public void setRenderer(WaferMapRenderer renderer) {
/* 212 */     if (this.renderer != null)
/* 213 */       this.renderer.removeChangeListener(this); 
/* 216 */     this.renderer = renderer;
/* 217 */     if (renderer != null)
/* 218 */       renderer.setPlot(this); 
/* 221 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState state, PlotRenderingInfo info) {
/* 239 */     boolean b1 = (area.getWidth() <= 10.0D);
/* 240 */     boolean b2 = (area.getHeight() <= 10.0D);
/* 241 */     if (b1 || b2)
/*     */       return; 
/* 246 */     if (info != null)
/* 247 */       info.setPlotArea(area); 
/* 251 */     RectangleInsets insets = getInsets();
/* 252 */     insets.trim(area);
/* 254 */     drawChipGrid(g2, area);
/* 255 */     drawWaferEdge(g2, area);
/*     */   }
/*     */   
/*     */   protected void drawChipGrid(Graphics2D g2, Rectangle2D plotArea) {
/* 267 */     Shape savedClip = g2.getClip();
/* 268 */     g2.setClip(getWaferEdge(plotArea));
/* 269 */     Rectangle2D chip = new Rectangle2D.Double();
/* 270 */     int xchips = 35;
/* 271 */     int ychips = 20;
/* 272 */     double space = 1.0D;
/* 273 */     if (this.dataset != null) {
/* 274 */       xchips = this.dataset.getMaxChipX() + 2;
/* 275 */       ychips = this.dataset.getMaxChipY() + 2;
/* 276 */       space = this.dataset.getChipSpace();
/*     */     } 
/* 278 */     double startX = plotArea.getX();
/* 279 */     double startY = plotArea.getY();
/* 280 */     double chipWidth = 1.0D;
/* 281 */     double chipHeight = 1.0D;
/* 282 */     if (plotArea.getWidth() != plotArea.getHeight()) {
/* 283 */       double major = 0.0D;
/* 284 */       double minor = 0.0D;
/* 285 */       if (plotArea.getWidth() > plotArea.getHeight()) {
/* 286 */         major = plotArea.getWidth();
/* 287 */         minor = plotArea.getHeight();
/*     */       } else {
/* 290 */         major = plotArea.getHeight();
/* 291 */         minor = plotArea.getWidth();
/*     */       } 
/* 294 */       if (plotArea.getWidth() == minor) {
/* 295 */         startY += (major - minor) / 2.0D;
/* 296 */         chipWidth = (plotArea.getWidth() - space * xchips - 1.0D) / xchips;
/* 298 */         chipHeight = (plotArea.getWidth() - space * ychips - 1.0D) / ychips;
/*     */       } else {
/* 302 */         startX += (major - minor) / 2.0D;
/* 303 */         chipWidth = (plotArea.getHeight() - space * xchips - 1.0D) / xchips;
/* 305 */         chipHeight = (plotArea.getHeight() - space * ychips - 1.0D) / ychips;
/*     */       } 
/*     */     } 
/* 310 */     for (int x = 1; x <= xchips; x++) {
/* 311 */       double upperLeftX = startX - chipWidth + chipWidth * x + space * (x - 1);
/* 313 */       for (int y = 1; y <= ychips; y++) {
/* 314 */         double upperLeftY = startY - chipHeight + chipHeight * y + space * (y - 1);
/* 316 */         chip.setFrame(upperLeftX, upperLeftY, chipWidth, chipHeight);
/* 317 */         g2.setColor(Color.white);
/* 318 */         if (this.dataset.getChipValue(x - 1, ychips - y - 1) != null)
/* 319 */           g2.setPaint(this.renderer.getChipColor(this.dataset.getChipValue(x - 1, ychips - y - 1))); 
/* 325 */         g2.fill(chip);
/* 326 */         g2.setColor(Color.lightGray);
/* 327 */         g2.draw(chip);
/*     */       } 
/*     */     } 
/* 330 */     g2.setClip(savedClip);
/*     */   }
/*     */   
/*     */   protected Ellipse2D getWaferEdge(Rectangle2D plotArea) {
/* 341 */     Ellipse2D edge = new Ellipse2D.Double();
/* 342 */     double diameter = plotArea.getWidth();
/* 343 */     double upperLeftX = plotArea.getX();
/* 344 */     double upperLeftY = plotArea.getY();
/* 346 */     if (plotArea.getWidth() != plotArea.getHeight()) {
/* 347 */       double major = 0.0D;
/* 348 */       double minor = 0.0D;
/* 349 */       if (plotArea.getWidth() > plotArea.getHeight()) {
/* 350 */         major = plotArea.getWidth();
/* 351 */         minor = plotArea.getHeight();
/*     */       } else {
/* 354 */         major = plotArea.getHeight();
/* 355 */         minor = plotArea.getWidth();
/*     */       } 
/* 358 */       diameter = minor;
/* 360 */       if (plotArea.getWidth() == minor) {
/* 361 */         upperLeftY = plotArea.getY() + (major - minor) / 2.0D;
/*     */       } else {
/* 364 */         upperLeftX = plotArea.getX() + (major - minor) / 2.0D;
/*     */       } 
/*     */     } 
/* 367 */     edge.setFrame(upperLeftX, upperLeftY, diameter, diameter);
/* 368 */     return edge;
/*     */   }
/*     */   
/*     */   protected void drawWaferEdge(Graphics2D g2, Rectangle2D plotArea) {
/* 379 */     Ellipse2D waferEdge = getWaferEdge(plotArea);
/* 380 */     g2.setColor(Color.black);
/* 381 */     g2.draw(waferEdge);
/* 385 */     Arc2D notch = null;
/* 386 */     Rectangle2D waferFrame = waferEdge.getFrame();
/* 387 */     double notchDiameter = waferFrame.getWidth() * 0.04D;
/* 388 */     if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 389 */       Rectangle2D notchFrame = new Rectangle2D.Double(waferFrame.getX() + waferFrame.getWidth() - notchDiameter / 2.0D, waferFrame.getY() + waferFrame.getHeight() / 2.0D - notchDiameter / 2.0D, notchDiameter, notchDiameter);
/* 396 */       notch = new Arc2D.Double(notchFrame, 90.0D, 180.0D, 0);
/*     */     } else {
/* 399 */       Rectangle2D notchFrame = new Rectangle2D.Double(waferFrame.getX() + waferFrame.getWidth() / 2.0D - notchDiameter / 2.0D, waferFrame.getY() + waferFrame.getHeight() - notchDiameter / 2.0D, notchDiameter, notchDiameter);
/* 406 */       notch = new Arc2D.Double(notchFrame, 0.0D, 180.0D, 0);
/*     */     } 
/* 408 */     g2.setColor(Color.white);
/* 409 */     g2.fill(notch);
/* 410 */     g2.setColor(Color.black);
/* 411 */     g2.draw(notch);
/*     */   }
/*     */   
/*     */   public LegendItemCollection getLegendItems() {
/* 421 */     return this.renderer.getLegendCollection();
/*     */   }
/*     */   
/*     */   public void rendererChanged(RendererChangeEvent event) {
/* 430 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\WaferMapPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */