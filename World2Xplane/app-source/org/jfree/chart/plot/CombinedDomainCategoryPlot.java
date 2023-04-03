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
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.event.PlotChangeEvent;
/*     */ import org.jfree.chart.event.PlotChangeListener;
/*     */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class CombinedDomainCategoryPlot extends CategoryPlot implements Zoomable, Cloneable, PublicCloneable, Serializable, PlotChangeListener {
/*     */   private static final long serialVersionUID = 8207194522653701572L;
/*     */   
/*     */   private List subplots;
/*     */   
/*     */   private int totalWeight;
/*     */   
/*     */   private double gap;
/*     */   
/*     */   private transient Rectangle2D[] subplotAreas;
/*     */   
/*     */   public CombinedDomainCategoryPlot() {
/* 106 */     this(new CategoryAxis());
/*     */   }
/*     */   
/*     */   public CombinedDomainCategoryPlot(CategoryAxis domainAxis) {
/* 116 */     super((CategoryDataset)null, domainAxis, (ValueAxis)null, (CategoryItemRenderer)null);
/* 117 */     this.subplots = new ArrayList();
/* 118 */     this.totalWeight = 0;
/* 119 */     this.gap = 5.0D;
/*     */   }
/*     */   
/*     */   public double getGap() {
/* 128 */     return this.gap;
/*     */   }
/*     */   
/*     */   public void setGap(double gap) {
/* 138 */     this.gap = gap;
/* 139 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void add(CategoryPlot subplot) {
/* 149 */     add(subplot, 1);
/*     */   }
/*     */   
/*     */   public void add(CategoryPlot subplot, int weight) {
/* 160 */     if (subplot == null)
/* 161 */       throw new IllegalArgumentException("Null 'subplot' argument."); 
/* 163 */     if (weight < 1)
/* 164 */       throw new IllegalArgumentException("Require weight >= 1."); 
/* 166 */     subplot.setParent(this);
/* 167 */     subplot.setWeight(weight);
/* 168 */     subplot.setInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D));
/* 169 */     subplot.setDomainAxis((CategoryAxis)null);
/* 170 */     subplot.setOrientation(getOrientation());
/* 171 */     subplot.addChangeListener(this);
/* 172 */     this.subplots.add(subplot);
/* 173 */     this.totalWeight += weight;
/* 174 */     CategoryAxis axis = getDomainAxis();
/* 175 */     if (axis != null)
/* 176 */       axis.configure(); 
/* 178 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void remove(CategoryPlot subplot) {
/* 190 */     if (subplot == null)
/* 191 */       throw new IllegalArgumentException("Null 'subplot' argument."); 
/* 193 */     int position = -1;
/* 194 */     int size = this.subplots.size();
/* 195 */     int i = 0;
/* 196 */     while (position == -1 && i < size) {
/* 197 */       if (this.subplots.get(i) == subplot)
/* 198 */         position = i; 
/* 200 */       i++;
/*     */     } 
/* 202 */     if (position != -1) {
/* 203 */       this.subplots.remove(position);
/* 204 */       subplot.setParent(null);
/* 205 */       subplot.removeChangeListener(this);
/* 206 */       this.totalWeight -= subplot.getWeight();
/* 208 */       CategoryAxis domain = getDomainAxis();
/* 209 */       if (domain != null)
/* 210 */         domain.configure(); 
/* 212 */       notifyListeners(new PlotChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getSubplots() {
/* 222 */     return Collections.unmodifiableList(this.subplots);
/*     */   }
/*     */   
/*     */   public CategoryPlot findSubplot(PlotRenderingInfo info, Point2D source) {
/* 235 */     CategoryPlot result = null;
/* 236 */     int subplotIndex = info.getSubplotIndex(source);
/* 237 */     if (subplotIndex >= 0)
/* 238 */       result = this.subplots.get(subplotIndex); 
/* 240 */     return result;
/*     */   }
/*     */   
/*     */   public void zoomRangeAxes(double factor, PlotRenderingInfo info, Point2D source) {
/* 252 */     CategoryPlot subplot = findSubplot(info, source);
/* 253 */     if (subplot != null)
/* 254 */       subplot.zoomRangeAxes(factor, info, source); 
/*     */   }
/*     */   
/*     */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source) {
/* 268 */     CategoryPlot subplot = findSubplot(info, source);
/* 269 */     if (subplot != null)
/* 270 */       subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source); 
/*     */   }
/*     */   
/*     */   protected AxisSpace calculateAxisSpace(Graphics2D g2, Rectangle2D plotArea) {
/* 285 */     AxisSpace space = new AxisSpace();
/* 286 */     PlotOrientation orientation = getOrientation();
/* 289 */     AxisSpace fixed = getFixedDomainAxisSpace();
/* 290 */     if (fixed != null) {
/* 291 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 292 */         space.setLeft(fixed.getLeft());
/* 293 */         space.setRight(fixed.getRight());
/* 295 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 296 */         space.setTop(fixed.getTop());
/* 297 */         space.setBottom(fixed.getBottom());
/*     */       } 
/*     */     } else {
/* 301 */       CategoryAxis categoryAxis = getDomainAxis();
/* 302 */       RectangleEdge categoryEdge = Plot.resolveDomainAxisLocation(getDomainAxisLocation(), orientation);
/* 305 */       if (categoryAxis != null) {
/* 306 */         space = categoryAxis.reserveSpace(g2, this, plotArea, categoryEdge, space);
/* 311 */       } else if (getDrawSharedDomainAxis()) {
/* 312 */         space = getDomainAxis().reserveSpace(g2, this, plotArea, categoryEdge, space);
/*     */       } 
/*     */     } 
/* 319 */     Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
/* 322 */     int n = this.subplots.size();
/* 323 */     this.subplotAreas = new Rectangle2D[n];
/* 324 */     double x = adjustedPlotArea.getX();
/* 325 */     double y = adjustedPlotArea.getY();
/* 326 */     double usableSize = 0.0D;
/* 327 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 328 */       usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
/* 330 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 331 */       usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
/*     */     } 
/* 334 */     for (int i = 0; i < n; i++) {
/* 335 */       CategoryPlot plot = this.subplots.get(i);
/* 338 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 339 */         double w = usableSize * plot.getWeight() / this.totalWeight;
/* 340 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, w, adjustedPlotArea.getHeight());
/* 343 */         x = x + w + this.gap;
/* 345 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 346 */         double h = usableSize * plot.getWeight() / this.totalWeight;
/* 347 */         this.subplotAreas[i] = new Rectangle2D.Double(x, y, adjustedPlotArea.getWidth(), h);
/* 350 */         y = y + h + this.gap;
/*     */       } 
/* 353 */       AxisSpace subSpace = plot.calculateRangeAxisSpace(g2, this.subplotAreas[i], (AxisSpace)null);
/* 356 */       space.ensureAtLeast(subSpace);
/*     */     } 
/* 360 */     return space;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/* 383 */     if (info != null)
/* 384 */       info.setPlotArea(area); 
/* 388 */     RectangleInsets insets = getInsets();
/* 389 */     area.setRect(area.getX() + insets.getLeft(), area.getY() + insets.getTop(), area.getWidth() - insets.getLeft() - insets.getRight(), area.getHeight() - insets.getTop() - insets.getBottom());
/* 398 */     setFixedRangeAxisSpaceForSubplots((AxisSpace)null);
/* 399 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 400 */     Rectangle2D dataArea = space.shrink(area, null);
/* 403 */     setFixedRangeAxisSpaceForSubplots(space);
/* 406 */     CategoryAxis axis = getDomainAxis();
/* 407 */     RectangleEdge domainEdge = getDomainAxisEdge();
/* 408 */     double cursor = RectangleEdge.coordinate(dataArea, domainEdge);
/* 409 */     AxisState axisState = axis.draw(g2, cursor, area, dataArea, domainEdge, info);
/* 412 */     if (parentState == null)
/* 413 */       parentState = new PlotState(); 
/* 415 */     parentState.getSharedAxisStates().put(axis, axisState);
/* 418 */     for (int i = 0; i < this.subplots.size(); i++) {
/* 419 */       CategoryPlot plot = this.subplots.get(i);
/* 420 */       PlotRenderingInfo subplotInfo = null;
/* 421 */       if (info != null) {
/* 422 */         subplotInfo = new PlotRenderingInfo(info.getOwner());
/* 423 */         info.addSubplotInfo(subplotInfo);
/*     */       } 
/* 425 */       plot.draw(g2, this.subplotAreas[i], (Point2D)null, parentState, subplotInfo);
/*     */     } 
/* 428 */     if (info != null)
/* 429 */       info.setDataArea(dataArea); 
/*     */   }
/*     */   
/*     */   protected void setFixedRangeAxisSpaceForSubplots(AxisSpace space) {
/* 442 */     Iterator iterator = this.subplots.iterator();
/* 443 */     while (iterator.hasNext()) {
/* 444 */       CategoryPlot plot = iterator.next();
/* 445 */       plot.setFixedRangeAxisSpace(space);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setOrientation(PlotOrientation orientation) {
/* 457 */     super.setOrientation(orientation);
/* 459 */     Iterator iterator = this.subplots.iterator();
/* 460 */     while (iterator.hasNext()) {
/* 461 */       CategoryPlot plot = iterator.next();
/* 462 */       plot.setOrientation(orientation);
/*     */     } 
/*     */   }
/*     */   
/*     */   public LegendItemCollection getLegendItems() {
/* 473 */     LegendItemCollection result = getFixedLegendItems();
/* 474 */     if (result == null) {
/* 475 */       result = new LegendItemCollection();
/* 476 */       if (this.subplots != null) {
/* 477 */         Iterator iterator = this.subplots.iterator();
/* 478 */         while (iterator.hasNext()) {
/* 479 */           CategoryPlot plot = iterator.next();
/* 480 */           LegendItemCollection more = plot.getLegendItems();
/* 481 */           result.addAll(more);
/*     */         } 
/*     */       } 
/*     */     } 
/* 485 */     return result;
/*     */   }
/*     */   
/*     */   public List getCategories() {
/* 496 */     List result = new ArrayList();
/* 498 */     if (this.subplots != null) {
/* 499 */       Iterator iterator = this.subplots.iterator();
/* 500 */       while (iterator.hasNext()) {
/* 501 */         CategoryPlot plot = iterator.next();
/* 502 */         List more = plot.getCategories();
/* 503 */         Iterator moreIterator = more.iterator();
/* 504 */         while (moreIterator.hasNext()) {
/* 505 */           Comparable category = moreIterator.next();
/* 506 */           if (!result.contains(category))
/* 507 */             result.add(category); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 513 */     return Collections.unmodifiableList(result);
/*     */   }
/*     */   
/*     */   public void handleClick(int x, int y, PlotRenderingInfo info) {
/* 526 */     Rectangle2D dataArea = info.getDataArea();
/* 527 */     if (dataArea.contains(x, y))
/* 528 */       for (int i = 0; i < this.subplots.size(); i++) {
/* 529 */         CategoryPlot subplot = this.subplots.get(i);
/* 530 */         PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
/* 531 */         subplot.handleClick(x, y, subplotInfo);
/*     */       }  
/*     */   }
/*     */   
/*     */   public void plotChanged(PlotChangeEvent event) {
/* 544 */     notifyListeners(event);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 555 */     if (obj == this)
/* 556 */       return true; 
/* 558 */     if (!(obj instanceof CombinedDomainCategoryPlot))
/* 559 */       return false; 
/* 561 */     if (!super.equals(obj))
/* 562 */       return false; 
/* 564 */     CombinedDomainCategoryPlot plot = (CombinedDomainCategoryPlot)obj;
/* 565 */     if (!ObjectUtilities.equal(this.subplots, plot.subplots))
/* 566 */       return false; 
/* 568 */     if (this.totalWeight != plot.totalWeight)
/* 569 */       return false; 
/* 571 */     if (this.gap != plot.gap)
/* 572 */       return false; 
/* 574 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 587 */     CombinedDomainCategoryPlot result = (CombinedDomainCategoryPlot)super.clone();
/* 589 */     result.subplots = (List)ObjectUtilities.deepClone(this.subplots);
/* 590 */     for (Iterator it = result.subplots.iterator(); it.hasNext(); ) {
/* 591 */       Plot child = it.next();
/* 592 */       child.setParent(result);
/*     */     } 
/* 594 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\CombinedDomainCategoryPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */