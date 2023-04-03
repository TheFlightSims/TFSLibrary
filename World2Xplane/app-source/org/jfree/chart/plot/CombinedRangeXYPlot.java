/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.axis.AxisSpace;
/*     */ import org.jfree.chart.axis.AxisState;
/*     */ import org.jfree.chart.axis.NumberAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.event.PlotChangeEvent;
/*     */ import org.jfree.chart.event.PlotChangeListener;
/*     */ import org.jfree.chart.renderer.xy.XYItemRenderer;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class CombinedRangeXYPlot extends XYPlot implements Zoomable, Cloneable, PublicCloneable, Serializable, PlotChangeListener {
/*     */   private static final long serialVersionUID = -5177814085082031168L;
/*     */   
/*     */   private List subplots;
/*     */   
/* 127 */   private int totalWeight = 0;
/*     */   
/* 130 */   private double gap = 5.0D;
/*     */   
/*     */   private transient Rectangle2D[] subplotAreas;
/*     */   
/*     */   public CombinedRangeXYPlot() {
/* 139 */     this((ValueAxis)new NumberAxis());
/*     */   }
/*     */   
/*     */   public CombinedRangeXYPlot(ValueAxis rangeAxis) {
/* 149 */     super((XYDataset)null, (ValueAxis)null, rangeAxis, (XYItemRenderer)null);
/* 154 */     this.subplots = new ArrayList();
/*     */   }
/*     */   
/*     */   public String getPlotType() {
/* 164 */     return localizationResources.getString("Combined_Range_XYPlot");
/*     */   }
/*     */   
/*     */   public double getGap() {
/* 173 */     return this.gap;
/*     */   }
/*     */   
/*     */   public void setGap(double gap) {
/* 182 */     this.gap = gap;
/*     */   }
/*     */   
/*     */   public void add(XYPlot subplot) {
/* 191 */     add(subplot, 1);
/*     */   }
/*     */   
/*     */   public void add(XYPlot subplot, int weight) {
/* 205 */     if (weight <= 0) {
/* 206 */       String msg = "The 'weight' must be positive.";
/* 207 */       throw new IllegalArgumentException(msg);
/*     */     } 
/* 211 */     subplot.setParent(this);
/* 212 */     subplot.setWeight(weight);
/* 213 */     subplot.setInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D));
/* 214 */     subplot.setRangeAxis((ValueAxis)null);
/* 215 */     subplot.addChangeListener(this);
/* 216 */     this.subplots.add(subplot);
/* 219 */     this.totalWeight += weight;
/* 220 */     configureRangeAxes();
/* 221 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void remove(XYPlot subplot) {
/* 231 */     if (subplot == null)
/* 232 */       throw new IllegalArgumentException(" Null 'subplot' argument."); 
/* 234 */     int position = -1;
/* 235 */     int size = this.subplots.size();
/* 236 */     int i = 0;
/* 237 */     while (position == -1 && i < size) {
/* 238 */       if (this.subplots.get(i) == subplot)
/* 239 */         position = i; 
/* 241 */       i++;
/*     */     } 
/* 243 */     if (position != -1) {
/* 244 */       subplot.setParent(null);
/* 245 */       subplot.removeChangeListener(this);
/* 246 */       this.totalWeight -= subplot.getWeight();
/* 247 */       configureRangeAxes();
/* 248 */       notifyListeners(new PlotChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getSubplots() {
/* 258 */     return Collections.unmodifiableList(this.subplots);
/*     */   }
/*     */   
/*     */   protected AxisSpace calculateAxisSpace(Graphics2D g2, Rectangle2D plotArea) {
/* 272 */     AxisSpace space = new AxisSpace();
/* 273 */     PlotOrientation orientation = getOrientation();
/* 276 */     AxisSpace fixed = getFixedRangeAxisSpace();
/* 277 */     if (fixed != null) {
/* 278 */       if (orientation == PlotOrientation.VERTICAL) {
/* 279 */         space.setLeft(fixed.getLeft());
/* 280 */         space.setRight(fixed.getRight());
/* 282 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 283 */         space.setTop(fixed.getTop());
/* 284 */         space.setBottom(fixed.getBottom());
/*     */       } 
/*     */     } else {
/* 288 */       ValueAxis valueAxis = getRangeAxis();
/* 289 */       RectangleEdge valueEdge = Plot.resolveRangeAxisLocation(getRangeAxisLocation(), orientation);
/* 292 */       if (valueAxis != null)
/* 293 */         space = valueAxis.reserveSpace(g2, this, plotArea, valueEdge, space); 
/*     */     } 
/* 299 */     Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
/* 301 */     int n = this.subplots.size();
/* 305 */     this.subplotAreas = new Rectangle2D[n];
/* 306 */     double x = adjustedPlotArea.getX();
/* 307 */     double y = adjustedPlotArea.getY();
/* 308 */     double usableSize = 0.0D;
/* 309 */     if (orientation == PlotOrientation.VERTICAL) {
/* 310 */       usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
/* 312 */     } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 313 */       usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
/*     */     } 
/* 316 */     for (int i = 0; i < n; i++) {
/* 317 */       XYPlot plot = this.subplots.get(i);
/* 320 */       if (orientation == PlotOrientation.VERTICAL) {
/* 321 */         double w = usableSize * plot.getWeight() / this.totalWeight;
/* 322 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, w, adjustedPlotArea.getHeight());
/* 325 */         x = x + w + this.gap;
/* 327 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 328 */         double h = usableSize * plot.getWeight() / this.totalWeight;
/* 329 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, adjustedPlotArea.getWidth(), h);
/* 332 */         y = y + h + this.gap;
/*     */       } 
/* 335 */       AxisSpace subSpace = plot.calculateDomainAxisSpace(g2, this.subplotAreas[i], (AxisSpace)null);
/* 338 */       space.ensureAtLeast(subSpace);
/*     */     } 
/* 342 */     return space;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/* 364 */     if (info != null)
/* 365 */       info.setPlotArea(area); 
/* 369 */     RectangleInsets insets = getInsets();
/* 370 */     insets.trim(area);
/* 372 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 373 */     Rectangle2D dataArea = space.shrink(area, null);
/* 377 */     setFixedDomainAxisSpaceForSubplots(space);
/* 380 */     ValueAxis axis = getRangeAxis();
/* 381 */     RectangleEdge edge = getRangeAxisEdge();
/* 382 */     double cursor = RectangleEdge.coordinate(dataArea, edge);
/* 383 */     AxisState axisState = axis.draw(g2, cursor, area, dataArea, edge, info);
/* 385 */     if (parentState == null)
/* 386 */       parentState = new PlotState(); 
/* 388 */     parentState.getSharedAxisStates().put(axis, axisState);
/* 391 */     for (int i = 0; i < this.subplots.size(); i++) {
/* 392 */       XYPlot plot = this.subplots.get(i);
/* 393 */       PlotRenderingInfo subplotInfo = null;
/* 394 */       if (info != null) {
/* 395 */         subplotInfo = new PlotRenderingInfo(info.getOwner());
/* 396 */         info.addSubplotInfo(subplotInfo);
/*     */       } 
/* 398 */       plot.draw(g2, this.subplotAreas[i], anchor, parentState, subplotInfo);
/*     */     } 
/* 403 */     if (info != null)
/* 404 */       info.setDataArea(dataArea); 
/*     */   }
/*     */   
/*     */   public LegendItemCollection getLegendItems() {
/* 415 */     LegendItemCollection result = getFixedLegendItems();
/* 416 */     if (result == null) {
/* 417 */       result = new LegendItemCollection();
/* 419 */       if (this.subplots != null) {
/* 420 */         Iterator iterator = this.subplots.iterator();
/* 421 */         while (iterator.hasNext()) {
/* 422 */           XYPlot plot = iterator.next();
/* 423 */           LegendItemCollection more = plot.getLegendItems();
/* 424 */           result.addAll(more);
/*     */         } 
/*     */       } 
/*     */     } 
/* 428 */     return result;
/*     */   }
/*     */   
/*     */   public void zoomDomainAxes(double factor, PlotRenderingInfo info, Point2D source) {
/* 440 */     XYPlot subplot = findSubplot(info, source);
/* 441 */     if (subplot != null)
/* 442 */       subplot.zoomDomainAxes(factor, info, source); 
/*     */   }
/*     */   
/*     */   public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source) {
/* 456 */     XYPlot subplot = findSubplot(info, source);
/* 457 */     if (subplot != null)
/* 458 */       subplot.zoomDomainAxes(lowerPercent, upperPercent, info, source); 
/*     */   }
/*     */   
/*     */   public XYPlot findSubplot(PlotRenderingInfo info, Point2D source) {
/* 472 */     XYPlot result = null;
/* 473 */     int subplotIndex = info.getSubplotIndex(source);
/* 474 */     if (subplotIndex >= 0)
/* 475 */       result = this.subplots.get(subplotIndex); 
/* 477 */     return result;
/*     */   }
/*     */   
/*     */   public void setRenderer(XYItemRenderer renderer) {
/* 491 */     super.setRenderer(renderer);
/* 495 */     Iterator iterator = this.subplots.iterator();
/* 496 */     while (iterator.hasNext()) {
/* 497 */       XYPlot plot = iterator.next();
/* 498 */       plot.setRenderer(renderer);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setOrientation(PlotOrientation orientation) {
/* 510 */     super.setOrientation(orientation);
/* 512 */     Iterator iterator = this.subplots.iterator();
/* 513 */     while (iterator.hasNext()) {
/* 514 */       XYPlot plot = iterator.next();
/* 515 */       plot.setOrientation(orientation);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Range getDataRange(ValueAxis axis) {
/* 530 */     Range result = null;
/* 531 */     if (this.subplots != null) {
/* 532 */       Iterator iterator = this.subplots.iterator();
/* 533 */       while (iterator.hasNext()) {
/* 534 */         XYPlot subplot = iterator.next();
/* 535 */         result = Range.combine(result, subplot.getDataRange(axis));
/*     */       } 
/*     */     } 
/* 538 */     return result;
/*     */   }
/*     */   
/*     */   protected void setFixedDomainAxisSpaceForSubplots(AxisSpace space) {
/* 550 */     Iterator iterator = this.subplots.iterator();
/* 551 */     while (iterator.hasNext()) {
/* 552 */       XYPlot plot = iterator.next();
/* 553 */       plot.setFixedDomainAxisSpace(space);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void handleClick(int x, int y, PlotRenderingInfo info) {
/* 567 */     Rectangle2D dataArea = info.getDataArea();
/* 568 */     if (dataArea.contains(x, y))
/* 569 */       for (int i = 0; i < this.subplots.size(); i++) {
/* 570 */         XYPlot subplot = this.subplots.get(i);
/* 571 */         PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
/* 572 */         subplot.handleClick(x, y, subplotInfo);
/*     */       }  
/*     */   }
/*     */   
/*     */   public void plotChanged(PlotChangeEvent event) {
/* 585 */     notifyListeners(event);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 597 */     if (obj == this)
/* 598 */       return true; 
/* 601 */     if (!(obj instanceof CombinedRangeXYPlot))
/* 602 */       return false; 
/* 604 */     if (!super.equals(obj))
/* 605 */       return false; 
/* 607 */     CombinedRangeXYPlot that = (CombinedRangeXYPlot)obj;
/* 608 */     if (!ObjectUtilities.equal(this.subplots, that.subplots))
/* 609 */       return false; 
/* 611 */     if (this.totalWeight != that.totalWeight)
/* 612 */       return false; 
/* 614 */     if (this.gap != that.gap)
/* 615 */       return false; 
/* 617 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 630 */     CombinedRangeXYPlot result = (CombinedRangeXYPlot)super.clone();
/* 631 */     result.subplots = (List)ObjectUtilities.deepClone(this.subplots);
/* 632 */     for (Iterator it = result.subplots.iterator(); it.hasNext(); ) {
/* 633 */       Plot child = it.next();
/* 634 */       child.setParent(result);
/*     */     } 
/* 639 */     ValueAxis rangeAxis = result.getRangeAxis();
/* 640 */     if (rangeAxis != null)
/* 641 */       rangeAxis.configure(); 
/* 644 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\CombinedRangeXYPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */