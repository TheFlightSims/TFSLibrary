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
/*     */ public class CombinedDomainXYPlot extends XYPlot implements Cloneable, PublicCloneable, Serializable, PlotChangeListener {
/*     */   private static final long serialVersionUID = -7765545541261907383L;
/*     */   
/*     */   private List subplots;
/*     */   
/* 123 */   private int totalWeight = 0;
/*     */   
/* 126 */   private double gap = 5.0D;
/*     */   
/*     */   private transient Rectangle2D[] subplotAreas;
/*     */   
/*     */   public CombinedDomainXYPlot() {
/* 137 */     this((ValueAxis)new NumberAxis());
/*     */   }
/*     */   
/*     */   public CombinedDomainXYPlot(ValueAxis domainAxis) {
/* 148 */     super((XYDataset)null, domainAxis, (ValueAxis)null, (XYItemRenderer)null);
/* 155 */     this.subplots = new ArrayList();
/*     */   }
/*     */   
/*     */   public String getPlotType() {
/* 165 */     return "Combined_Domain_XYPlot";
/*     */   }
/*     */   
/*     */   public void setOrientation(PlotOrientation orientation) {
/* 176 */     super.setOrientation(orientation);
/* 177 */     Iterator iterator = this.subplots.iterator();
/* 178 */     while (iterator.hasNext()) {
/* 179 */       XYPlot plot = iterator.next();
/* 180 */       plot.setOrientation(orientation);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Range getDataRange(ValueAxis axis) {
/* 195 */     Range result = null;
/* 196 */     if (this.subplots != null) {
/* 197 */       Iterator iterator = this.subplots.iterator();
/* 198 */       while (iterator.hasNext()) {
/* 199 */         XYPlot subplot = iterator.next();
/* 200 */         result = Range.combine(result, subplot.getDataRange(axis));
/*     */       } 
/*     */     } 
/* 203 */     return result;
/*     */   }
/*     */   
/*     */   public double getGap() {
/* 213 */     return this.gap;
/*     */   }
/*     */   
/*     */   public void setGap(double gap) {
/* 223 */     this.gap = gap;
/* 224 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void add(XYPlot subplot) {
/* 237 */     add(subplot, 1);
/*     */   }
/*     */   
/*     */   public void add(XYPlot subplot, int weight) {
/* 253 */     if (subplot == null)
/* 254 */       throw new IllegalArgumentException("Null 'subplot' argument."); 
/* 256 */     if (weight <= 0)
/* 257 */       throw new IllegalArgumentException("Require weight >= 1."); 
/* 261 */     subplot.setParent(this);
/* 262 */     subplot.setWeight(weight);
/* 263 */     subplot.setInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D), false);
/* 264 */     subplot.setDomainAxis((ValueAxis)null);
/* 265 */     subplot.addChangeListener(this);
/* 266 */     this.subplots.add(subplot);
/* 269 */     this.totalWeight += weight;
/* 271 */     ValueAxis axis = getDomainAxis();
/* 272 */     if (axis != null)
/* 273 */       axis.configure(); 
/* 276 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void remove(XYPlot subplot) {
/* 287 */     if (subplot == null)
/* 288 */       throw new IllegalArgumentException(" Null 'subplot' argument."); 
/* 290 */     int position = -1;
/* 291 */     int size = this.subplots.size();
/* 292 */     int i = 0;
/* 293 */     while (position == -1 && i < size) {
/* 294 */       if (this.subplots.get(i) == subplot)
/* 295 */         position = i; 
/* 297 */       i++;
/*     */     } 
/* 299 */     if (position != -1) {
/* 300 */       this.subplots.remove(position);
/* 301 */       subplot.setParent(null);
/* 302 */       subplot.removeChangeListener(this);
/* 303 */       this.totalWeight -= subplot.getWeight();
/* 305 */       ValueAxis domain = getDomainAxis();
/* 306 */       if (domain != null)
/* 307 */         domain.configure(); 
/* 309 */       notifyListeners(new PlotChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getSubplots() {
/* 319 */     return Collections.unmodifiableList(this.subplots);
/*     */   }
/*     */   
/*     */   protected AxisSpace calculateAxisSpace(Graphics2D g2, Rectangle2D plotArea) {
/* 333 */     AxisSpace space = new AxisSpace();
/* 334 */     PlotOrientation orientation = getOrientation();
/* 337 */     AxisSpace fixed = getFixedDomainAxisSpace();
/* 338 */     if (fixed != null) {
/* 339 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 340 */         space.setLeft(fixed.getLeft());
/* 341 */         space.setRight(fixed.getRight());
/* 343 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 344 */         space.setTop(fixed.getTop());
/* 345 */         space.setBottom(fixed.getBottom());
/*     */       } 
/*     */     } else {
/* 349 */       ValueAxis xAxis = getDomainAxis();
/* 350 */       RectangleEdge xEdge = Plot.resolveDomainAxisLocation(getDomainAxisLocation(), orientation);
/* 353 */       if (xAxis != null)
/* 354 */         space = xAxis.reserveSpace(g2, this, plotArea, xEdge, space); 
/*     */     } 
/* 358 */     Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
/* 361 */     int n = this.subplots.size();
/* 362 */     this.subplotAreas = new Rectangle2D[n];
/* 363 */     double x = adjustedPlotArea.getX();
/* 364 */     double y = adjustedPlotArea.getY();
/* 365 */     double usableSize = 0.0D;
/* 366 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 367 */       usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
/* 369 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 370 */       usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
/*     */     } 
/* 373 */     for (int i = 0; i < n; i++) {
/* 374 */       XYPlot plot = this.subplots.get(i);
/* 377 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 378 */         double w = usableSize * plot.getWeight() / this.totalWeight;
/* 379 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, w, adjustedPlotArea.getHeight());
/* 382 */         x = x + w + this.gap;
/* 384 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 385 */         double h = usableSize * plot.getWeight() / this.totalWeight;
/* 386 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, adjustedPlotArea.getWidth(), h);
/* 389 */         y = y + h + this.gap;
/*     */       } 
/* 392 */       AxisSpace subSpace = plot.calculateRangeAxisSpace(g2, this.subplotAreas[i], (AxisSpace)null);
/* 395 */       space.ensureAtLeast(subSpace);
/*     */     } 
/* 399 */     return space;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/* 421 */     if (info != null)
/* 422 */       info.setPlotArea(area); 
/* 426 */     RectangleInsets insets = getInsets();
/* 427 */     insets.trim(area);
/* 429 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 430 */     Rectangle2D dataArea = space.shrink(area, null);
/* 433 */     setFixedRangeAxisSpaceForSubplots(space);
/* 436 */     ValueAxis axis = getDomainAxis();
/* 437 */     RectangleEdge edge = getDomainAxisEdge();
/* 438 */     double cursor = RectangleEdge.coordinate(dataArea, edge);
/* 439 */     AxisState axisState = axis.draw(g2, cursor, area, dataArea, edge, info);
/* 440 */     if (parentState == null)
/* 441 */       parentState = new PlotState(); 
/* 443 */     parentState.getSharedAxisStates().put(axis, axisState);
/* 446 */     for (int i = 0; i < this.subplots.size(); i++) {
/* 447 */       XYPlot plot = this.subplots.get(i);
/* 448 */       PlotRenderingInfo subplotInfo = null;
/* 449 */       if (info != null) {
/* 450 */         subplotInfo = new PlotRenderingInfo(info.getOwner());
/* 451 */         info.addSubplotInfo(subplotInfo);
/*     */       } 
/* 453 */       plot.draw(g2, this.subplotAreas[i], anchor, parentState, subplotInfo);
/*     */     } 
/* 458 */     if (info != null)
/* 459 */       info.setDataArea(dataArea); 
/*     */   }
/*     */   
/*     */   public LegendItemCollection getLegendItems() {
/* 470 */     LegendItemCollection result = getFixedLegendItems();
/* 471 */     if (result == null) {
/* 472 */       result = new LegendItemCollection();
/* 473 */       if (this.subplots != null) {
/* 474 */         Iterator iterator = this.subplots.iterator();
/* 475 */         while (iterator.hasNext()) {
/* 476 */           XYPlot plot = iterator.next();
/* 477 */           LegendItemCollection more = plot.getLegendItems();
/* 478 */           result.addAll(more);
/*     */         } 
/*     */       } 
/*     */     } 
/* 482 */     return result;
/*     */   }
/*     */   
/*     */   public void zoomRangeAxes(double factor, PlotRenderingInfo info, Point2D source) {
/* 494 */     XYPlot subplot = findSubplot(info, source);
/* 495 */     if (subplot != null)
/* 496 */       subplot.zoomRangeAxes(factor, info, source); 
/*     */   }
/*     */   
/*     */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source) {
/* 510 */     XYPlot subplot = findSubplot(info, source);
/* 511 */     if (subplot != null)
/* 512 */       subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source); 
/*     */   }
/*     */   
/*     */   public XYPlot findSubplot(PlotRenderingInfo info, Point2D source) {
/* 526 */     XYPlot result = null;
/* 527 */     int subplotIndex = info.getSubplotIndex(source);
/* 528 */     if (subplotIndex >= 0)
/* 529 */       result = this.subplots.get(subplotIndex); 
/* 531 */     return result;
/*     */   }
/*     */   
/*     */   public void setRenderer(XYItemRenderer renderer) {
/* 545 */     super.setRenderer(renderer);
/* 549 */     Iterator iterator = this.subplots.iterator();
/* 550 */     while (iterator.hasNext()) {
/* 551 */       XYPlot plot = iterator.next();
/* 552 */       plot.setRenderer(renderer);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void setFixedRangeAxisSpaceForSubplots(AxisSpace space) {
/* 565 */     Iterator iterator = this.subplots.iterator();
/* 566 */     while (iterator.hasNext()) {
/* 567 */       XYPlot plot = iterator.next();
/* 568 */       plot.setFixedRangeAxisSpace(space);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void handleClick(int x, int y, PlotRenderingInfo info) {
/* 581 */     Rectangle2D dataArea = info.getDataArea();
/* 582 */     if (dataArea.contains(x, y))
/* 583 */       for (int i = 0; i < this.subplots.size(); i++) {
/* 584 */         XYPlot subplot = this.subplots.get(i);
/* 585 */         PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
/* 586 */         subplot.handleClick(x, y, subplotInfo);
/*     */       }  
/*     */   }
/*     */   
/*     */   public void plotChanged(PlotChangeEvent event) {
/* 598 */     notifyListeners(event);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 610 */     if (obj == null)
/* 611 */       return false; 
/* 614 */     if (obj == this)
/* 615 */       return true; 
/* 618 */     if (!(obj instanceof CombinedDomainXYPlot))
/* 619 */       return false; 
/* 621 */     if (!super.equals(obj))
/* 622 */       return false; 
/* 625 */     CombinedDomainXYPlot p = (CombinedDomainXYPlot)obj;
/* 626 */     if (this.totalWeight != p.totalWeight)
/* 627 */       return false; 
/* 629 */     if (this.gap != p.gap)
/* 630 */       return false; 
/* 632 */     if (!ObjectUtilities.equal(this.subplots, p.subplots))
/* 633 */       return false; 
/* 636 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 649 */     CombinedDomainXYPlot result = (CombinedDomainXYPlot)super.clone();
/* 650 */     result.subplots = (List)ObjectUtilities.deepClone(this.subplots);
/* 651 */     for (Iterator it = result.subplots.iterator(); it.hasNext(); ) {
/* 652 */       Plot child = it.next();
/* 653 */       child.setParent(result);
/*     */     } 
/* 658 */     ValueAxis domainAxis = result.getDomainAxis();
/* 659 */     if (domainAxis != null)
/* 660 */       domainAxis.configure(); 
/* 663 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\CombinedDomainXYPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */