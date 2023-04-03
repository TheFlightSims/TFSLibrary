/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.RectangularShape;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.ClipPath;
/*      */ import org.jfree.chart.annotations.XYAnnotation;
/*      */ import org.jfree.chart.axis.AxisSpace;
/*      */ import org.jfree.chart.axis.ColorBar;
/*      */ import org.jfree.chart.axis.NumberAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.ChartEntity;
/*      */ import org.jfree.chart.entity.ContourEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.labels.ContourToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardContourToolTipGenerator;
/*      */ import org.jfree.chart.urls.XYURLGenerator;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.contour.ContourDataset;
/*      */ import org.jfree.data.general.Dataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ 
/*      */ public class ContourPlot extends Plot implements ContourValuePlot, ValueAxisPlot, PropertyChangeListener, Serializable, Cloneable {
/*      */   private static final long serialVersionUID = 7861072556590502247L;
/*      */   
/*  126 */   protected static final RectangleInsets DEFAULT_INSETS = new RectangleInsets(2.0D, 2.0D, 100.0D, 10.0D);
/*      */   
/*      */   private ValueAxis domainAxis;
/*      */   
/*      */   private ValueAxis rangeAxis;
/*      */   
/*      */   private ContourDataset dataset;
/*      */   
/*  139 */   private ColorBar colorBar = null;
/*      */   
/*      */   private RectangleEdge colorBarLocation;
/*      */   
/*      */   private boolean domainCrosshairVisible;
/*      */   
/*      */   private double domainCrosshairValue;
/*      */   
/*      */   private transient Stroke domainCrosshairStroke;
/*      */   
/*      */   private transient Paint domainCrosshairPaint;
/*      */   
/*      */   private boolean domainCrosshairLockedOnData = true;
/*      */   
/*      */   private boolean rangeCrosshairVisible;
/*      */   
/*      */   private double rangeCrosshairValue;
/*      */   
/*      */   private transient Stroke rangeCrosshairStroke;
/*      */   
/*      */   private transient Paint rangeCrosshairPaint;
/*      */   
/*      */   private boolean rangeCrosshairLockedOnData = true;
/*      */   
/*  189 */   private double dataAreaRatio = 0.0D;
/*      */   
/*      */   private List domainMarkers;
/*      */   
/*      */   private List rangeMarkers;
/*      */   
/*      */   private List annotations;
/*      */   
/*      */   private ContourToolTipGenerator toolTipGenerator;
/*      */   
/*      */   private XYURLGenerator urlGenerator;
/*      */   
/*      */   private boolean renderAsPoints = false;
/*      */   
/*  216 */   private double ptSizePct = 0.05D;
/*      */   
/*  219 */   private transient ClipPath clipPath = null;
/*      */   
/*  222 */   private transient Paint missingPaint = null;
/*      */   
/*  225 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */   public ContourPlot() {
/*  232 */     this((ContourDataset)null, (ValueAxis)null, (ValueAxis)null, (ColorBar)null);
/*      */   }
/*      */   
/*      */   public ContourPlot(ContourDataset dataset, ValueAxis domainAxis, ValueAxis rangeAxis, ColorBar colorBar) {
/*  250 */     this.dataset = dataset;
/*  251 */     if (dataset != null)
/*  252 */       dataset.addChangeListener(this); 
/*  255 */     this.domainAxis = domainAxis;
/*  256 */     if (domainAxis != null) {
/*  257 */       domainAxis.setPlot(this);
/*  258 */       domainAxis.addChangeListener(this);
/*      */     } 
/*  261 */     this.rangeAxis = rangeAxis;
/*  262 */     if (rangeAxis != null) {
/*  263 */       rangeAxis.setPlot(this);
/*  264 */       rangeAxis.addChangeListener(this);
/*      */     } 
/*  267 */     this.colorBar = colorBar;
/*  268 */     if (colorBar != null) {
/*  269 */       colorBar.getAxis().setPlot(this);
/*  270 */       colorBar.getAxis().addChangeListener(this);
/*  271 */       colorBar.configure(this);
/*      */     } 
/*  273 */     this.colorBarLocation = RectangleEdge.LEFT;
/*  275 */     this.toolTipGenerator = (ContourToolTipGenerator)new StandardContourToolTipGenerator();
/*      */   }
/*      */   
/*      */   public RectangleEdge getColorBarLocation() {
/*  285 */     return this.colorBarLocation;
/*      */   }
/*      */   
/*      */   public void setColorBarLocation(RectangleEdge edge) {
/*  295 */     this.colorBarLocation = edge;
/*  296 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public ContourDataset getDataset() {
/*  305 */     return this.dataset;
/*      */   }
/*      */   
/*      */   public void setDataset(ContourDataset dataset) {
/*  318 */     ContourDataset existing = this.dataset;
/*  319 */     if (existing != null)
/*  320 */       existing.removeChangeListener(this); 
/*  324 */     this.dataset = dataset;
/*  325 */     if (dataset != null) {
/*  326 */       setDatasetGroup(dataset.getGroup());
/*  327 */       dataset.addChangeListener(this);
/*      */     } 
/*  331 */     DatasetChangeEvent event = new DatasetChangeEvent(this, (Dataset)dataset);
/*  332 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */   public ValueAxis getDomainAxis() {
/*  343 */     ValueAxis result = this.domainAxis;
/*  345 */     return result;
/*      */   }
/*      */   
/*      */   public void setDomainAxis(ValueAxis axis) {
/*  357 */     if (isCompatibleDomainAxis(axis)) {
/*  359 */       if (axis != null) {
/*  360 */         axis.setPlot(this);
/*  361 */         axis.addChangeListener(this);
/*      */       } 
/*  365 */       if (this.domainAxis != null)
/*  366 */         this.domainAxis.removeChangeListener(this); 
/*  369 */       this.domainAxis = axis;
/*  370 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public ValueAxis getRangeAxis() {
/*  383 */     ValueAxis result = this.rangeAxis;
/*  385 */     return result;
/*      */   }
/*      */   
/*      */   public void setRangeAxis(ValueAxis axis) {
/*  399 */     if (axis != null) {
/*  400 */       axis.setPlot(this);
/*  401 */       axis.addChangeListener(this);
/*      */     } 
/*  405 */     if (this.rangeAxis != null)
/*  406 */       this.rangeAxis.removeChangeListener(this); 
/*  409 */     this.rangeAxis = axis;
/*  410 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void setColorBarAxis(ColorBar axis) {
/*  421 */     this.colorBar = axis;
/*  422 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getDataAreaRatio() {
/*  432 */     return this.dataAreaRatio;
/*      */   }
/*      */   
/*      */   public void setDataAreaRatio(double ratio) {
/*  441 */     this.dataAreaRatio = ratio;
/*      */   }
/*      */   
/*      */   public void addDomainMarker(Marker marker) {
/*  454 */     if (this.domainMarkers == null)
/*  455 */       this.domainMarkers = new ArrayList(); 
/*  457 */     this.domainMarkers.add(marker);
/*  458 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void clearDomainMarkers() {
/*  466 */     if (this.domainMarkers != null) {
/*  467 */       this.domainMarkers.clear();
/*  468 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addRangeMarker(Marker marker) {
/*  482 */     if (this.rangeMarkers == null)
/*  483 */       this.rangeMarkers = new ArrayList(); 
/*  485 */     this.rangeMarkers.add(marker);
/*  486 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void clearRangeMarkers() {
/*  494 */     if (this.rangeMarkers != null) {
/*  495 */       this.rangeMarkers.clear();
/*  496 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addAnnotation(XYAnnotation annotation) {
/*  507 */     if (this.annotations == null)
/*  508 */       this.annotations = new ArrayList(); 
/*  510 */     this.annotations.add(annotation);
/*  511 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void clearAnnotations() {
/*  519 */     if (this.annotations != null) {
/*  520 */       this.annotations.clear();
/*  521 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isCompatibleDomainAxis(ValueAxis axis) {
/*  535 */     return true;
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/*  560 */     boolean b1 = (area.getWidth() <= 10.0D);
/*  561 */     boolean b2 = (area.getHeight() <= 10.0D);
/*  562 */     if (b1 || b2)
/*      */       return; 
/*  567 */     if (info != null)
/*  568 */       info.setPlotArea(area); 
/*  572 */     RectangleInsets insets = getInsets();
/*  573 */     insets.trim(area);
/*  575 */     AxisSpace space = new AxisSpace();
/*  577 */     space = this.domainAxis.reserveSpace(g2, this, area, RectangleEdge.BOTTOM, space);
/*  580 */     space = this.rangeAxis.reserveSpace(g2, this, area, RectangleEdge.LEFT, space);
/*  584 */     Rectangle2D estimatedDataArea = space.shrink(area, null);
/*  586 */     AxisSpace space2 = new AxisSpace();
/*  587 */     space2 = this.colorBar.reserveSpace(g2, this, area, estimatedDataArea, this.colorBarLocation, space2);
/*  591 */     Rectangle2D adjustedPlotArea = space2.shrink(area, null);
/*  593 */     Rectangle2D dataArea = space.shrink(adjustedPlotArea, null);
/*  595 */     Rectangle2D colorBarArea = space2.reserved(area, this.colorBarLocation);
/*  600 */     if (getDataAreaRatio() != 0.0D) {
/*  601 */       double ratio = getDataAreaRatio();
/*  602 */       Rectangle2D tmpDataArea = (Rectangle2D)dataArea.clone();
/*  603 */       double h = tmpDataArea.getHeight();
/*  604 */       double w = tmpDataArea.getWidth();
/*  606 */       if (ratio > 0.0D) {
/*  607 */         if (w * ratio <= h) {
/*  608 */           h = ratio * w;
/*      */         } else {
/*  611 */           w = h / ratio;
/*      */         } 
/*      */       } else {
/*  615 */         ratio *= -1.0D;
/*  616 */         double xLength = getDomainAxis().getRange().getLength();
/*  617 */         double yLength = getRangeAxis().getRange().getLength();
/*  618 */         double unitRatio = yLength / xLength;
/*  620 */         ratio = unitRatio * ratio;
/*  622 */         if (w * ratio <= h) {
/*  623 */           h = ratio * w;
/*      */         } else {
/*  626 */           w = h / ratio;
/*      */         } 
/*      */       } 
/*  630 */       dataArea.setRect(tmpDataArea.getX() + tmpDataArea.getWidth() / 2.0D - w / 2.0D, tmpDataArea.getY(), w, h);
/*      */     } 
/*  636 */     if (info != null)
/*  637 */       info.setDataArea(dataArea); 
/*  640 */     CrosshairState crosshairState = new CrosshairState();
/*  641 */     crosshairState.setCrosshairDistance(Double.POSITIVE_INFINITY);
/*  644 */     drawBackground(g2, dataArea);
/*  646 */     double cursor = dataArea.getMaxY();
/*  647 */     if (this.domainAxis != null)
/*  648 */       this.domainAxis.draw(g2, cursor, adjustedPlotArea, dataArea, RectangleEdge.BOTTOM, info); 
/*  654 */     if (this.rangeAxis != null) {
/*  655 */       cursor = dataArea.getMinX();
/*  656 */       this.rangeAxis.draw(g2, cursor, adjustedPlotArea, dataArea, RectangleEdge.LEFT, info);
/*      */     } 
/*  661 */     if (this.colorBar != null) {
/*  662 */       cursor = 0.0D;
/*  663 */       cursor = this.colorBar.draw(g2, cursor, adjustedPlotArea, dataArea, colorBarArea, this.colorBarLocation);
/*      */     } 
/*  668 */     Shape originalClip = g2.getClip();
/*  669 */     Composite originalComposite = g2.getComposite();
/*  671 */     g2.clip(dataArea);
/*  672 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*  675 */     render(g2, dataArea, info, crosshairState);
/*  677 */     if (this.domainMarkers != null) {
/*  678 */       Iterator iterator = this.domainMarkers.iterator();
/*  679 */       while (iterator.hasNext()) {
/*  680 */         Marker marker = iterator.next();
/*  681 */         drawDomainMarker(g2, this, getDomainAxis(), marker, dataArea);
/*      */       } 
/*      */     } 
/*  685 */     if (this.rangeMarkers != null) {
/*  686 */       Iterator iterator = this.rangeMarkers.iterator();
/*  687 */       while (iterator.hasNext()) {
/*  688 */         Marker marker = iterator.next();
/*  689 */         drawRangeMarker(g2, this, getRangeAxis(), marker, dataArea);
/*      */       } 
/*      */     } 
/*  710 */     g2.setClip(originalClip);
/*  711 */     g2.setComposite(originalComposite);
/*  712 */     drawOutline(g2, dataArea);
/*      */   }
/*      */   
/*      */   public void render(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, CrosshairState crosshairState) {
/*  733 */     ContourDataset data = getDataset();
/*  734 */     if (data != null) {
/*  736 */       ColorBar zAxis = getColorBar();
/*  738 */       if (this.clipPath != null) {
/*  739 */         GeneralPath clipper = getClipPath().draw(g2, dataArea, this.domainAxis, this.rangeAxis);
/*  742 */         if (this.clipPath.isClip())
/*  743 */           g2.clip(clipper); 
/*      */       } 
/*  747 */       if (this.renderAsPoints) {
/*  748 */         pointRenderer(g2, dataArea, info, this, this.domainAxis, this.rangeAxis, zAxis, data, crosshairState);
/*      */       } else {
/*  753 */         contourRenderer(g2, dataArea, info, this, this.domainAxis, this.rangeAxis, zAxis, data, crosshairState);
/*      */       } 
/*  759 */       setDomainCrosshairValue(crosshairState.getCrosshairX(), false);
/*  760 */       if (isDomainCrosshairVisible())
/*  761 */         drawVerticalLine(g2, dataArea, getDomainCrosshairValue(), getDomainCrosshairStroke(), getDomainCrosshairPaint()); 
/*  768 */       setRangeCrosshairValue(crosshairState.getCrosshairY(), false);
/*  769 */       if (isRangeCrosshairVisible())
/*  770 */         drawHorizontalLine(g2, dataArea, getRangeCrosshairValue(), getRangeCrosshairStroke(), getRangeCrosshairPaint()); 
/*  777 */     } else if (this.clipPath != null) {
/*  778 */       getClipPath().draw(g2, dataArea, this.domainAxis, this.rangeAxis);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void contourRenderer(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, ContourPlot plot, ValueAxis horizontalAxis, ValueAxis verticalAxis, ColorBar colorBar, ContourDataset data, CrosshairState crosshairState) {
/*  808 */     Rectangle2D.Double entityArea = null;
/*  809 */     EntityCollection entities = null;
/*  810 */     if (info != null)
/*  811 */       entities = info.getOwner().getEntityCollection(); 
/*  814 */     Rectangle2D.Double rect = null;
/*  815 */     rect = new Rectangle2D.Double();
/*  818 */     Object antiAlias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
/*  819 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*  824 */     Number[] xNumber = data.getXValues();
/*  825 */     Number[] yNumber = data.getYValues();
/*  826 */     Number[] zNumber = data.getZValues();
/*  828 */     double[] x = new double[xNumber.length];
/*  829 */     double[] y = new double[yNumber.length];
/*  831 */     for (int i = 0; i < x.length; i++) {
/*  832 */       x[i] = xNumber[i].doubleValue();
/*  833 */       y[i] = yNumber[i].doubleValue();
/*      */     } 
/*  836 */     int[] xIndex = data.indexX();
/*  837 */     int[] indexX = data.getXIndices();
/*  838 */     boolean vertInverted = ((NumberAxis)verticalAxis).isInverted();
/*  839 */     boolean horizInverted = false;
/*  840 */     if (horizontalAxis instanceof NumberAxis)
/*  841 */       horizInverted = ((NumberAxis)horizontalAxis).isInverted(); 
/*  843 */     double transX = 0.0D;
/*  844 */     double transXm1 = 0.0D;
/*  845 */     double transXp1 = 0.0D;
/*  846 */     double transDXm1 = 0.0D;
/*  847 */     double transDXp1 = 0.0D;
/*  848 */     double transDX = 0.0D;
/*  849 */     double transY = 0.0D;
/*  850 */     double transYm1 = 0.0D;
/*  851 */     double transYp1 = 0.0D;
/*  852 */     double transDYm1 = 0.0D;
/*  853 */     double transDYp1 = 0.0D;
/*  854 */     double transDY = 0.0D;
/*  855 */     int iMax = xIndex[xIndex.length - 1];
/*  856 */     for (int k = 0; k < x.length; k++) {
/*  857 */       int j = xIndex[k];
/*  858 */       if (indexX[j] == k) {
/*  859 */         if (j == 0) {
/*  860 */           transX = horizontalAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM);
/*  863 */           transXm1 = transX;
/*  864 */           transXp1 = horizontalAxis.valueToJava2D(x[indexX[j + 1]], dataArea, RectangleEdge.BOTTOM);
/*  867 */           transDXm1 = Math.abs(0.5D * (transX - transXm1));
/*  868 */           transDXp1 = Math.abs(0.5D * (transX - transXp1));
/*  870 */         } else if (j == iMax) {
/*  871 */           transX = horizontalAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM);
/*  874 */           transXm1 = horizontalAxis.valueToJava2D(x[indexX[j - 1]], dataArea, RectangleEdge.BOTTOM);
/*  877 */           transXp1 = transX;
/*  878 */           transDXm1 = Math.abs(0.5D * (transX - transXm1));
/*  879 */           transDXp1 = Math.abs(0.5D * (transX - transXp1));
/*      */         } else {
/*  882 */           transX = horizontalAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM);
/*  885 */           transXp1 = horizontalAxis.valueToJava2D(x[indexX[j + 1]], dataArea, RectangleEdge.BOTTOM);
/*  888 */           transDXm1 = transDXp1;
/*  889 */           transDXp1 = Math.abs(0.5D * (transX - transXp1));
/*      */         } 
/*  892 */         if (horizInverted) {
/*  893 */           transX -= transDXp1;
/*      */         } else {
/*  896 */           transX -= transDXm1;
/*      */         } 
/*  899 */         transDX = transDXm1 + transDXp1;
/*  901 */         transY = verticalAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT);
/*  904 */         transYm1 = transY;
/*  905 */         if (k + 1 == y.length)
/*      */           continue; 
/*  908 */         transYp1 = verticalAxis.valueToJava2D(y[k + 1], dataArea, RectangleEdge.LEFT);
/*  911 */         transDYm1 = Math.abs(0.5D * (transY - transYm1));
/*  912 */         transDYp1 = Math.abs(0.5D * (transY - transYp1));
/*  914 */       } else if ((j < indexX.length - 1 && indexX[j + 1] - 1 == k) || k == x.length - 1) {
/*  917 */         transY = verticalAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT);
/*  920 */         transYm1 = verticalAxis.valueToJava2D(y[k - 1], dataArea, RectangleEdge.LEFT);
/*  923 */         transYp1 = transY;
/*  924 */         transDYm1 = Math.abs(0.5D * (transY - transYm1));
/*  925 */         transDYp1 = Math.abs(0.5D * (transY - transYp1));
/*      */       } else {
/*  928 */         transY = verticalAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT);
/*  931 */         transYp1 = verticalAxis.valueToJava2D(y[k + 1], dataArea, RectangleEdge.LEFT);
/*  934 */         transDYm1 = transDYp1;
/*  935 */         transDYp1 = Math.abs(0.5D * (transY - transYp1));
/*      */       } 
/*  937 */       if (vertInverted) {
/*  938 */         transY -= transDYm1;
/*      */       } else {
/*  941 */         transY -= transDYp1;
/*      */       } 
/*  944 */       transDY = transDYm1 + transDYp1;
/*  946 */       rect.setRect(transX, transY, transDX, transDY);
/*  947 */       if (zNumber[k] != null) {
/*  948 */         g2.setPaint(colorBar.getPaint(zNumber[k].doubleValue()));
/*  949 */         g2.fill(rect);
/*  951 */       } else if (this.missingPaint != null) {
/*  952 */         g2.setPaint(this.missingPaint);
/*  953 */         g2.fill(rect);
/*      */       } 
/*  956 */       entityArea = rect;
/*  959 */       if (entities != null) {
/*  960 */         String tip = "";
/*  961 */         if (getToolTipGenerator() != null)
/*  962 */           tip = this.toolTipGenerator.generateToolTip(data, k); 
/*  966 */         String url = null;
/*  972 */         ContourEntity entity = new ContourEntity((Rectangle2D.Double)entityArea.clone(), tip, url);
/*  975 */         entity.setIndex(k);
/*  976 */         entities.add((ChartEntity)entity);
/*      */       } 
/*  981 */       if (plot.isDomainCrosshairLockedOnData()) {
/*  982 */         if (plot.isRangeCrosshairLockedOnData()) {
/*  984 */           crosshairState.updateCrosshairPoint(x[k], y[k], transX, transY, PlotOrientation.VERTICAL);
/*      */         } else {
/*  990 */           crosshairState.updateCrosshairX(transX);
/*      */         } 
/*  994 */       } else if (plot.isRangeCrosshairLockedOnData()) {
/*  996 */         crosshairState.updateCrosshairY(transY);
/*      */       } 
/*      */       continue;
/*      */     } 
/* 1001 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias);
/*      */   }
/*      */   
/*      */   public void pointRenderer(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, ContourPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, ColorBar colorBar, ContourDataset data, CrosshairState crosshairState) {
/* 1032 */     RectangularShape entityArea = null;
/* 1033 */     EntityCollection entities = null;
/* 1034 */     if (info != null)
/* 1035 */       entities = info.getOwner().getEntityCollection(); 
/* 1040 */     RectangularShape rect = new Ellipse2D.Double();
/* 1044 */     Object antiAlias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
/* 1045 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/* 1051 */     Number[] xNumber = data.getXValues();
/* 1052 */     Number[] yNumber = data.getYValues();
/* 1053 */     Number[] zNumber = data.getZValues();
/* 1055 */     double[] x = new double[xNumber.length];
/* 1056 */     double[] y = new double[yNumber.length];
/* 1058 */     for (int i = 0; i < x.length; i++) {
/* 1059 */       x[i] = xNumber[i].doubleValue();
/* 1060 */       y[i] = yNumber[i].doubleValue();
/*      */     } 
/* 1063 */     double transX = 0.0D;
/* 1064 */     double transDX = 0.0D;
/* 1065 */     double transY = 0.0D;
/* 1066 */     double transDY = 0.0D;
/* 1067 */     double size = dataArea.getWidth() * this.ptSizePct;
/* 1068 */     for (int k = 0; k < x.length; k++) {
/* 1070 */       transX = domainAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM) - 0.5D * size;
/* 1073 */       transY = rangeAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT) - 0.5D * size;
/* 1075 */       transDX = size;
/* 1076 */       transDY = size;
/* 1078 */       rect.setFrame(transX, transY, transDX, transDY);
/* 1080 */       if (zNumber[k] != null) {
/* 1081 */         g2.setPaint(colorBar.getPaint(zNumber[k].doubleValue()));
/* 1082 */         g2.fill(rect);
/* 1084 */       } else if (this.missingPaint != null) {
/* 1085 */         g2.setPaint(this.missingPaint);
/* 1086 */         g2.fill(rect);
/*      */       } 
/* 1090 */       entityArea = rect;
/* 1093 */       if (entities != null) {
/* 1094 */         String tip = null;
/* 1095 */         if (getToolTipGenerator() != null)
/* 1096 */           tip = this.toolTipGenerator.generateToolTip(data, k); 
/* 1098 */         String url = null;
/* 1104 */         ContourEntity entity = new ContourEntity((RectangularShape)entityArea.clone(), tip, url);
/* 1107 */         entity.setIndex(k);
/* 1108 */         entities.add((ChartEntity)entity);
/*      */       } 
/* 1112 */       if (plot.isDomainCrosshairLockedOnData()) {
/* 1113 */         if (plot.isRangeCrosshairLockedOnData()) {
/* 1115 */           crosshairState.updateCrosshairPoint(x[k], y[k], transX, transY, PlotOrientation.VERTICAL);
/*      */         } else {
/* 1121 */           crosshairState.updateCrosshairX(transX);
/*      */         } 
/* 1125 */       } else if (plot.isRangeCrosshairLockedOnData()) {
/* 1127 */         crosshairState.updateCrosshairY(transY);
/*      */       } 
/*      */     } 
/* 1133 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias);
/*      */   }
/*      */   
/*      */   protected void drawVerticalLine(Graphics2D g2, Rectangle2D dataArea, double value, Stroke stroke, Paint paint) {
/* 1151 */     double xx = getDomainAxis().valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
/* 1154 */     Line2D line = new Line2D.Double(xx, dataArea.getMinY(), xx, dataArea.getMaxY());
/* 1157 */     g2.setStroke(stroke);
/* 1158 */     g2.setPaint(paint);
/* 1159 */     g2.draw(line);
/*      */   }
/*      */   
/*      */   protected void drawHorizontalLine(Graphics2D g2, Rectangle2D dataArea, double value, Stroke stroke, Paint paint) {
/* 1176 */     double yy = getRangeAxis().valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/* 1179 */     Line2D line = new Line2D.Double(dataArea.getMinX(), yy, dataArea.getMaxX(), yy);
/* 1182 */     g2.setStroke(stroke);
/* 1183 */     g2.setPaint(paint);
/* 1184 */     g2.draw(line);
/*      */   }
/*      */   
/*      */   public void handleClick(int x, int y, PlotRenderingInfo info) {}
/*      */   
/*      */   public void zoom(double percent) {
/* 1227 */     if (percent <= 0.0D) {
/* 1237 */       getRangeAxis().setAutoRange(true);
/* 1238 */       getDomainAxis().setAutoRange(true);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getPlotType() {
/* 1249 */     return localizationResources.getString("Contour_Plot");
/*      */   }
/*      */   
/*      */   public Range getDataRange(ValueAxis axis) {
/* 1261 */     if (this.dataset == null)
/* 1262 */       return null; 
/* 1265 */     Range result = null;
/* 1267 */     if (axis == getDomainAxis()) {
/* 1268 */       result = DatasetUtilities.findDomainBounds((XYDataset)this.dataset);
/* 1270 */     } else if (axis == getRangeAxis()) {
/* 1271 */       result = DatasetUtilities.findRangeBounds((XYDataset)this.dataset);
/*      */     } 
/* 1274 */     return result;
/*      */   }
/*      */   
/*      */   public Range getContourDataRange() {
/* 1285 */     Range result = null;
/* 1287 */     ContourDataset data = getDataset();
/* 1289 */     if (data != null) {
/* 1290 */       Range h = getDomainAxis().getRange();
/* 1291 */       Range v = getRangeAxis().getRange();
/* 1292 */       result = visibleRange(data, h, v);
/*      */     } 
/* 1295 */     return result;
/*      */   }
/*      */   
/*      */   public void propertyChange(PropertyChangeEvent event) {
/* 1306 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void datasetChanged(DatasetChangeEvent event) {
/* 1318 */     if (this.domainAxis != null)
/* 1319 */       this.domainAxis.configure(); 
/* 1321 */     if (this.rangeAxis != null)
/* 1322 */       this.rangeAxis.configure(); 
/* 1324 */     if (this.colorBar != null)
/* 1325 */       this.colorBar.configure(this); 
/* 1327 */     super.datasetChanged(event);
/*      */   }
/*      */   
/*      */   public ColorBar getColorBar() {
/* 1336 */     return this.colorBar;
/*      */   }
/*      */   
/*      */   public boolean isDomainCrosshairVisible() {
/* 1345 */     return this.domainCrosshairVisible;
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairVisible(boolean flag) {
/* 1355 */     if (this.domainCrosshairVisible != flag) {
/* 1356 */       this.domainCrosshairVisible = flag;
/* 1357 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isDomainCrosshairLockedOnData() {
/* 1369 */     return this.domainCrosshairLockedOnData;
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairLockedOnData(boolean flag) {
/* 1379 */     if (this.domainCrosshairLockedOnData != flag) {
/* 1380 */       this.domainCrosshairLockedOnData = flag;
/* 1381 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getDomainCrosshairValue() {
/* 1391 */     return this.domainCrosshairValue;
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairValue(double value) {
/* 1404 */     setDomainCrosshairValue(value, true);
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairValue(double value, boolean notify) {
/* 1420 */     this.domainCrosshairValue = value;
/* 1421 */     if (isDomainCrosshairVisible() && notify)
/* 1422 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Stroke getDomainCrosshairStroke() {
/* 1433 */     return this.domainCrosshairStroke;
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairStroke(Stroke stroke) {
/* 1443 */     this.domainCrosshairStroke = stroke;
/* 1444 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getDomainCrosshairPaint() {
/* 1453 */     return this.domainCrosshairPaint;
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairPaint(Paint paint) {
/* 1463 */     this.domainCrosshairPaint = paint;
/* 1464 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isRangeCrosshairVisible() {
/* 1473 */     return this.rangeCrosshairVisible;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairVisible(boolean flag) {
/* 1483 */     if (this.rangeCrosshairVisible != flag) {
/* 1484 */       this.rangeCrosshairVisible = flag;
/* 1485 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isRangeCrosshairLockedOnData() {
/* 1497 */     return this.rangeCrosshairLockedOnData;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairLockedOnData(boolean flag) {
/* 1508 */     if (this.rangeCrosshairLockedOnData != flag) {
/* 1509 */       this.rangeCrosshairLockedOnData = flag;
/* 1510 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getRangeCrosshairValue() {
/* 1521 */     return this.rangeCrosshairValue;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairValue(double value) {
/* 1534 */     setRangeCrosshairValue(value, true);
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairValue(double value, boolean notify) {
/* 1550 */     this.rangeCrosshairValue = value;
/* 1551 */     if (isRangeCrosshairVisible() && notify)
/* 1552 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Stroke getRangeCrosshairStroke() {
/* 1563 */     return this.rangeCrosshairStroke;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairStroke(Stroke stroke) {
/* 1573 */     this.rangeCrosshairStroke = stroke;
/* 1574 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getRangeCrosshairPaint() {
/* 1583 */     return this.rangeCrosshairPaint;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairPaint(Paint paint) {
/* 1593 */     this.rangeCrosshairPaint = paint;
/* 1594 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public ContourToolTipGenerator getToolTipGenerator() {
/* 1603 */     return this.toolTipGenerator;
/*      */   }
/*      */   
/*      */   public void setToolTipGenerator(ContourToolTipGenerator generator) {
/* 1614 */     this.toolTipGenerator = generator;
/*      */   }
/*      */   
/*      */   public XYURLGenerator getURLGenerator() {
/* 1624 */     return this.urlGenerator;
/*      */   }
/*      */   
/*      */   public void setURLGenerator(XYURLGenerator urlGenerator) {
/* 1635 */     this.urlGenerator = urlGenerator;
/*      */   }
/*      */   
/*      */   public void drawDomainMarker(Graphics2D g2, ContourPlot plot, ValueAxis domainAxis, Marker marker, Rectangle2D dataArea) {
/* 1654 */     if (marker instanceof ValueMarker) {
/* 1655 */       ValueMarker vm = (ValueMarker)marker;
/* 1656 */       double value = vm.getValue();
/* 1657 */       Range range = domainAxis.getRange();
/* 1658 */       if (!range.contains(value))
/*      */         return; 
/* 1662 */       double x = domainAxis.valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
/* 1665 */       Line2D line = new Line2D.Double(x, dataArea.getMinY(), x, dataArea.getMaxY());
/* 1668 */       Paint paint = marker.getOutlinePaint();
/* 1669 */       Stroke stroke = marker.getOutlineStroke();
/* 1670 */       g2.setPaint((paint != null) ? paint : Plot.DEFAULT_OUTLINE_PAINT);
/* 1671 */       g2.setStroke((stroke != null) ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
/* 1672 */       g2.draw(line);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void drawRangeMarker(Graphics2D g2, ContourPlot plot, ValueAxis rangeAxis, Marker marker, Rectangle2D dataArea) {
/* 1692 */     if (marker instanceof ValueMarker) {
/* 1693 */       ValueMarker vm = (ValueMarker)marker;
/* 1694 */       double value = vm.getValue();
/* 1695 */       Range range = rangeAxis.getRange();
/* 1696 */       if (!range.contains(value))
/*      */         return; 
/* 1700 */       double y = rangeAxis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/* 1703 */       Line2D line = new Line2D.Double(dataArea.getMinX(), y, dataArea.getMaxX(), y);
/* 1706 */       Paint paint = marker.getOutlinePaint();
/* 1707 */       Stroke stroke = marker.getOutlineStroke();
/* 1708 */       g2.setPaint((paint != null) ? paint : Plot.DEFAULT_OUTLINE_PAINT);
/* 1709 */       g2.setStroke((stroke != null) ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
/* 1710 */       g2.draw(line);
/*      */     } 
/*      */   }
/*      */   
/*      */   public ClipPath getClipPath() {
/* 1720 */     return this.clipPath;
/*      */   }
/*      */   
/*      */   public void setClipPath(ClipPath clipPath) {
/* 1728 */     this.clipPath = clipPath;
/*      */   }
/*      */   
/*      */   public double getPtSizePct() {
/* 1736 */     return this.ptSizePct;
/*      */   }
/*      */   
/*      */   public boolean isRenderAsPoints() {
/* 1744 */     return this.renderAsPoints;
/*      */   }
/*      */   
/*      */   public void setPtSizePct(double ptSizePct) {
/* 1752 */     this.ptSizePct = ptSizePct;
/*      */   }
/*      */   
/*      */   public void setRenderAsPoints(boolean renderAsPoints) {
/* 1760 */     this.renderAsPoints = renderAsPoints;
/*      */   }
/*      */   
/*      */   public void axisChanged(AxisChangeEvent event) {
/* 1769 */     Object source = event.getSource();
/* 1770 */     if (source.equals(this.rangeAxis) || source.equals(this.domainAxis)) {
/* 1771 */       ColorBar cba = this.colorBar;
/* 1772 */       if (this.colorBar.getAxis().isAutoRange())
/* 1773 */         cba.getAxis().configure(); 
/*      */     } 
/* 1777 */     super.axisChanged(event);
/*      */   }
/*      */   
/*      */   public Range visibleRange(ContourDataset data, Range x, Range y) {
/* 1790 */     Range range = null;
/* 1791 */     range = data.getZValueRange(x, y);
/* 1792 */     return range;
/*      */   }
/*      */   
/*      */   public Paint getMissingPaint() {
/* 1800 */     return this.missingPaint;
/*      */   }
/*      */   
/*      */   public void setMissingPaint(Paint paint) {
/* 1809 */     this.missingPaint = paint;
/*      */   }
/*      */   
/*      */   public void zoomDomainAxes(double x, double y, double factor) {}
/*      */   
/*      */   public void zoomDomainAxes(double x, double y, double lowerPercent, double upperPercent) {}
/*      */   
/*      */   public void zoomRangeAxes(double x, double y, double factor) {}
/*      */   
/*      */   public void zoomRangeAxes(double x, double y, double lowerPercent, double upperPercent) {}
/*      */   
/*      */   public boolean isDomainZoomable() {
/* 1867 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isRangeZoomable() {
/* 1876 */     return false;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1884 */     ContourPlot clone = (ContourPlot)super.clone();
/* 1886 */     if (this.domainAxis != null) {
/* 1887 */       clone.domainAxis = (ValueAxis)this.domainAxis.clone();
/* 1888 */       clone.domainAxis.setPlot(clone);
/* 1889 */       clone.domainAxis.addChangeListener(clone);
/*      */     } 
/* 1891 */     if (this.rangeAxis != null) {
/* 1892 */       clone.rangeAxis = (ValueAxis)this.rangeAxis.clone();
/* 1893 */       clone.rangeAxis.setPlot(clone);
/* 1894 */       clone.rangeAxis.addChangeListener(clone);
/*      */     } 
/* 1897 */     if (clone.dataset != null)
/* 1898 */       clone.dataset.addChangeListener(clone); 
/* 1901 */     if (this.colorBar != null)
/* 1902 */       clone.colorBar = (ColorBar)this.colorBar.clone(); 
/* 1905 */     clone.domainMarkers = (List)ObjectUtilities.deepClone(this.domainMarkers);
/* 1908 */     clone.rangeMarkers = (List)ObjectUtilities.deepClone(this.rangeMarkers);
/* 1911 */     clone.annotations = (List)ObjectUtilities.deepClone(this.annotations);
/* 1913 */     if (this.clipPath != null)
/* 1914 */       clone.clipPath = (ClipPath)this.clipPath.clone(); 
/* 1917 */     return clone;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\ContourPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */