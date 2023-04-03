/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.axis.AxisSpace;
/*     */ import org.jfree.chart.axis.AxisState;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.NumberAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.event.PlotChangeEvent;
/*     */ import org.jfree.chart.event.PlotChangeListener;
/*     */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class CombinedRangeCategoryPlot extends CategoryPlot implements Zoomable, Cloneable, PublicCloneable, Serializable, PlotChangeListener {
/*     */   private static final long serialVersionUID = 7260210007554504515L;
/*     */   
/*     */   private List subplots;
/*     */   
/*     */   private int totalWeight;
/*     */   
/*     */   private double gap;
/*     */   
/*     */   private transient Rectangle2D[] subplotArea;
/*     */   
/*     */   public CombinedRangeCategoryPlot() {
/* 110 */     this((ValueAxis)new NumberAxis());
/*     */   }
/*     */   
/*     */   public CombinedRangeCategoryPlot(ValueAxis rangeAxis) {
/* 119 */     super((CategoryDataset)null, (CategoryAxis)null, rangeAxis, (CategoryItemRenderer)null);
/* 120 */     this.subplots = new ArrayList();
/* 121 */     this.totalWeight = 0;
/* 122 */     this.gap = 5.0D;
/*     */   }
/*     */   
/*     */   public double getGap() {
/* 131 */     return this.gap;
/*     */   }
/*     */   
/*     */   public void setGap(double gap) {
/* 141 */     this.gap = gap;
/* 142 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void add(CategoryPlot subplot) {
/* 153 */     add(subplot, 1);
/*     */   }
/*     */   
/*     */   public void add(CategoryPlot subplot, int weight) {
/* 164 */     if (subplot == null)
/* 165 */       throw new IllegalArgumentException("Null 'subplot' argument."); 
/* 167 */     if (weight <= 0)
/* 168 */       throw new IllegalArgumentException("Require weight >= 1."); 
/* 171 */     subplot.setParent(this);
/* 172 */     subplot.setWeight(weight);
/* 173 */     subplot.setInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D));
/* 174 */     subplot.setRangeAxis((ValueAxis)null);
/* 175 */     subplot.setOrientation(getOrientation());
/* 176 */     subplot.addChangeListener(this);
/* 177 */     this.subplots.add(subplot);
/* 178 */     this.totalWeight += weight;
/* 181 */     ValueAxis axis = getRangeAxis();
/* 182 */     if (axis != null)
/* 183 */       axis.configure(); 
/* 185 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void remove(CategoryPlot subplot) {
/* 194 */     if (subplot == null)
/* 195 */       throw new IllegalArgumentException(" Null 'subplot' argument."); 
/* 197 */     int position = -1;
/* 198 */     int size = this.subplots.size();
/* 199 */     int i = 0;
/* 200 */     while (position == -1 && i < size) {
/* 201 */       if (this.subplots.get(i) == subplot)
/* 202 */         position = i; 
/* 204 */       i++;
/*     */     } 
/* 206 */     if (position != -1) {
/* 207 */       this.subplots.remove(position);
/* 208 */       subplot.setParent(null);
/* 209 */       subplot.removeChangeListener(this);
/* 210 */       this.totalWeight -= subplot.getWeight();
/* 212 */       ValueAxis range = getRangeAxis();
/* 213 */       if (range != null)
/* 214 */         range.configure(); 
/* 217 */       ValueAxis range2 = getRangeAxis(1);
/* 218 */       if (range2 != null)
/* 219 */         range2.configure(); 
/* 221 */       notifyListeners(new PlotChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getSubplots() {
/* 231 */     return Collections.unmodifiableList(this.subplots);
/*     */   }
/*     */   
/*     */   protected AxisSpace calculateAxisSpace(Graphics2D g2, Rectangle2D plotArea) {
/* 245 */     AxisSpace space = new AxisSpace();
/* 246 */     PlotOrientation orientation = getOrientation();
/* 249 */     AxisSpace fixed = getFixedRangeAxisSpace();
/* 250 */     if (fixed != null) {
/* 251 */       if (orientation == PlotOrientation.VERTICAL) {
/* 252 */         space.setLeft(fixed.getLeft());
/* 253 */         space.setRight(fixed.getRight());
/* 255 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 256 */         space.setTop(fixed.getTop());
/* 257 */         space.setBottom(fixed.getBottom());
/*     */       } 
/*     */     } else {
/* 261 */       ValueAxis valueAxis = getRangeAxis();
/* 262 */       RectangleEdge valueEdge = Plot.resolveRangeAxisLocation(getRangeAxisLocation(), orientation);
/* 265 */       if (valueAxis != null)
/* 266 */         space = valueAxis.reserveSpace(g2, this, plotArea, valueEdge, space); 
/*     */     } 
/* 272 */     Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
/* 274 */     int n = this.subplots.size();
/* 278 */     this.subplotArea = new Rectangle2D[n];
/* 279 */     double x = adjustedPlotArea.getX();
/* 280 */     double y = adjustedPlotArea.getY();
/* 281 */     double usableSize = 0.0D;
/* 282 */     if (orientation == PlotOrientation.VERTICAL) {
/* 283 */       usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
/* 285 */     } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 286 */       usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
/*     */     } 
/* 289 */     for (int i = 0; i < n; i++) {
/* 290 */       CategoryPlot plot = this.subplots.get(i);
/* 293 */       if (orientation == PlotOrientation.VERTICAL) {
/* 294 */         double w = usableSize * plot.getWeight() / this.totalWeight;
/* 295 */         this.subplotArea[i] = new Rectangle2D.Double(x, y, w, adjustedPlotArea.getHeight());
/* 298 */         x = x + w + this.gap;
/* 300 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 301 */         double h = usableSize * plot.getWeight() / this.totalWeight;
/* 302 */         this.subplotArea[i] = new Rectangle2D.Double(x, y, adjustedPlotArea.getWidth(), h);
/* 305 */         y = y + h + this.gap;
/*     */       } 
/* 308 */       AxisSpace subSpace = plot.calculateDomainAxisSpace(g2, this.subplotArea[i], (AxisSpace)null);
/* 311 */       space.ensureAtLeast(subSpace);
/*     */     } 
/* 315 */     return space;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/* 336 */     if (info != null)
/* 337 */       info.setPlotArea(area); 
/* 341 */     RectangleInsets insets = getInsets();
/* 342 */     insets.trim(area);
/* 345 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 346 */     Rectangle2D dataArea = space.shrink(area, null);
/* 349 */     setFixedDomainAxisSpaceForSubplots(space);
/* 352 */     ValueAxis axis = getRangeAxis();
/* 353 */     RectangleEdge rangeEdge = getRangeAxisEdge();
/* 354 */     double cursor = RectangleEdge.coordinate(dataArea, rangeEdge);
/* 355 */     AxisState state = axis.draw(g2, cursor, area, dataArea, rangeEdge, info);
/* 358 */     if (parentState == null)
/* 359 */       parentState = new PlotState(); 
/* 361 */     parentState.getSharedAxisStates().put(axis, state);
/* 364 */     for (int i = 0; i < this.subplots.size(); i++) {
/* 365 */       CategoryPlot plot = this.subplots.get(i);
/* 366 */       PlotRenderingInfo subplotInfo = null;
/* 367 */       if (info != null) {
/* 368 */         subplotInfo = new PlotRenderingInfo(info.getOwner());
/* 369 */         info.addSubplotInfo(subplotInfo);
/*     */       } 
/* 371 */       plot.draw(g2, this.subplotArea[i], (Point2D)null, parentState, subplotInfo);
/*     */     } 
/* 374 */     if (info != null)
/* 375 */       info.setDataArea(dataArea); 
/*     */   }
/*     */   
/*     */   public void setOrientation(PlotOrientation orientation) {
/* 387 */     super.setOrientation(orientation);
/* 389 */     Iterator iterator = this.subplots.iterator();
/* 390 */     while (iterator.hasNext()) {
/* 391 */       CategoryPlot plot = iterator.next();
/* 392 */       plot.setOrientation(orientation);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Range getDataRange(ValueAxis axis) {
/* 407 */     Range result = null;
/* 408 */     if (this.subplots != null) {
/* 409 */       Iterator iterator = this.subplots.iterator();
/* 410 */       while (iterator.hasNext()) {
/* 411 */         CategoryPlot subplot = iterator.next();
/* 412 */         result = Range.combine(result, subplot.getDataRange(axis));
/*     */       } 
/*     */     } 
/* 415 */     return result;
/*     */   }
/*     */   
/*     */   public LegendItemCollection getLegendItems() {
/* 425 */     LegendItemCollection result = getFixedLegendItems();
/* 426 */     if (result == null) {
/* 427 */       result = new LegendItemCollection();
/* 428 */       if (this.subplots != null) {
/* 429 */         Iterator iterator = this.subplots.iterator();
/* 430 */         while (iterator.hasNext()) {
/* 431 */           CategoryPlot plot = iterator.next();
/* 432 */           LegendItemCollection more = plot.getLegendItems();
/* 433 */           result.addAll(more);
/*     */         } 
/*     */       } 
/*     */     } 
/* 437 */     return result;
/*     */   }
/*     */   
/*     */   protected void setFixedDomainAxisSpaceForSubplots(AxisSpace space) {
/* 448 */     Iterator iterator = this.subplots.iterator();
/* 449 */     while (iterator.hasNext()) {
/* 450 */       CategoryPlot plot = iterator.next();
/* 451 */       plot.setFixedDomainAxisSpace(space);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void handleClick(int x, int y, PlotRenderingInfo info) {
/* 466 */     Rectangle2D dataArea = info.getDataArea();
/* 467 */     if (dataArea.contains(x, y))
/* 468 */       for (int i = 0; i < this.subplots.size(); i++) {
/* 469 */         CategoryPlot subplot = this.subplots.get(i);
/* 470 */         PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
/* 471 */         subplot.handleClick(x, y, subplotInfo);
/*     */       }  
/*     */   }
/*     */   
/*     */   public void plotChanged(PlotChangeEvent event) {
/* 484 */     notifyListeners(event);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 495 */     if (obj == this)
/* 496 */       return true; 
/* 498 */     if (!(obj instanceof CombinedRangeCategoryPlot))
/* 499 */       return false; 
/* 501 */     if (!super.equals(obj))
/* 502 */       return false; 
/* 504 */     CombinedRangeCategoryPlot that = (CombinedRangeCategoryPlot)obj;
/* 505 */     if (!ObjectUtilities.equal(this.subplots, that.subplots))
/* 506 */       return false; 
/* 508 */     if (this.totalWeight != that.totalWeight)
/* 509 */       return false; 
/* 511 */     if (this.gap != that.gap)
/* 512 */       return false; 
/* 514 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 526 */     CombinedRangeCategoryPlot result = (CombinedRangeCategoryPlot)super.clone();
/* 528 */     result.subplots = (List)ObjectUtilities.deepClone(this.subplots);
/* 529 */     for (Iterator it = result.subplots.iterator(); it.hasNext(); ) {
/* 530 */       Plot child = it.next();
/* 531 */       child.setParent(result);
/*     */     } 
/* 536 */     ValueAxis rangeAxis = result.getRangeAxis();
/* 537 */     if (rangeAxis != null)
/* 538 */       rangeAxis.configure(); 
/* 541 */     return result;
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 555 */     stream.defaultReadObject();
/* 559 */     ValueAxis rangeAxis = getRangeAxis();
/* 560 */     if (rangeAxis != null)
/* 561 */       rangeAxis.configure(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\CombinedRangeCategoryPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */