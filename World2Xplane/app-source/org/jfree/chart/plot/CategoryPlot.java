/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.annotations.CategoryAnnotation;
/*      */ import org.jfree.chart.axis.Axis;
/*      */ import org.jfree.chart.axis.AxisCollection;
/*      */ import org.jfree.chart.axis.AxisLocation;
/*      */ import org.jfree.chart.axis.AxisSpace;
/*      */ import org.jfree.chart.axis.AxisState;
/*      */ import org.jfree.chart.axis.CategoryAnchor;
/*      */ import org.jfree.chart.axis.CategoryAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.axis.ValueTick;
/*      */ import org.jfree.chart.event.ChartChangeEventType;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeListener;
/*      */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
/*      */ import org.jfree.chart.renderer.category.CategoryItemRendererState;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.general.Dataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.Layer;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectList;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ import org.jfree.util.SortOrder;
/*      */ 
/*      */ public class CategoryPlot extends Plot implements ValueAxisPlot, Zoomable, RendererChangeListener, Cloneable, PublicCloneable, Serializable {
/*      */   private static final long serialVersionUID = -3537691700434728188L;
/*      */   
/*      */   public static final boolean DEFAULT_DOMAIN_GRIDLINES_VISIBLE = false;
/*      */   
/*      */   public static final boolean DEFAULT_RANGE_GRIDLINES_VISIBLE = true;
/*      */   
/*  221 */   public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5F, 0, 2, 0.0F, new float[] { 2.0F, 2.0F }, 0.0F);
/*      */   
/*  229 */   public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
/*      */   
/*  232 */   public static final Font DEFAULT_VALUE_LABEL_FONT = new Font("SansSerif", 0, 10);
/*      */   
/*  236 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */   private PlotOrientation orientation;
/*      */   
/*      */   private RectangleInsets axisOffset;
/*      */   
/*      */   private ObjectList domainAxes;
/*      */   
/*      */   private ObjectList domainAxisLocations;
/*      */   
/*      */   private boolean drawSharedDomainAxis;
/*      */   
/*      */   private ObjectList rangeAxes;
/*      */   
/*      */   private ObjectList rangeAxisLocations;
/*      */   
/*      */   private ObjectList datasets;
/*      */   
/*      */   private ObjectList datasetToDomainAxisMap;
/*      */   
/*      */   private ObjectList datasetToRangeAxisMap;
/*      */   
/*      */   private ObjectList renderers;
/*      */   
/*  276 */   private DatasetRenderingOrder renderingOrder = DatasetRenderingOrder.REVERSE;
/*      */   
/*  283 */   private SortOrder columnRenderingOrder = SortOrder.ASCENDING;
/*      */   
/*  289 */   private SortOrder rowRenderingOrder = SortOrder.ASCENDING;
/*      */   
/*      */   private boolean domainGridlinesVisible;
/*      */   
/*      */   private CategoryAnchor domainGridlinePosition;
/*      */   
/*      */   private transient Stroke domainGridlineStroke;
/*      */   
/*      */   private transient Paint domainGridlinePaint;
/*      */   
/*      */   private boolean rangeGridlinesVisible;
/*      */   
/*      */   private transient Stroke rangeGridlineStroke;
/*      */   
/*      */   private transient Paint rangeGridlinePaint;
/*      */   
/*      */   private double anchorValue;
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
/*      */   private Map foregroundDomainMarkers;
/*      */   
/*      */   private Map backgroundDomainMarkers;
/*      */   
/*      */   private Map foregroundRangeMarkers;
/*      */   
/*      */   private Map backgroundRangeMarkers;
/*      */   
/*      */   private List annotations;
/*      */   
/*      */   private int weight;
/*      */   
/*      */   private AxisSpace fixedDomainAxisSpace;
/*      */   
/*      */   private AxisSpace fixedRangeAxisSpace;
/*      */   
/*      */   private LegendItemCollection fixedLegendItems;
/*      */   
/*      */   public CategoryPlot() {
/*  380 */     this((CategoryDataset)null, (CategoryAxis)null, (ValueAxis)null, (CategoryItemRenderer)null);
/*      */   }
/*      */   
/*      */   public CategoryPlot(CategoryDataset dataset, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryItemRenderer renderer) {
/*  399 */     this.orientation = PlotOrientation.VERTICAL;
/*  402 */     this.domainAxes = new ObjectList();
/*  403 */     this.domainAxisLocations = new ObjectList();
/*  404 */     this.rangeAxes = new ObjectList();
/*  405 */     this.rangeAxisLocations = new ObjectList();
/*  407 */     this.datasetToDomainAxisMap = new ObjectList();
/*  408 */     this.datasetToRangeAxisMap = new ObjectList();
/*  410 */     this.renderers = new ObjectList();
/*  412 */     this.datasets = new ObjectList();
/*  413 */     this.datasets.set(0, dataset);
/*  414 */     if (dataset != null)
/*  415 */       dataset.addChangeListener(this); 
/*  418 */     this.axisOffset = RectangleInsets.ZERO_INSETS;
/*  420 */     setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT, false);
/*  421 */     setRangeAxisLocation(AxisLocation.TOP_OR_LEFT, false);
/*  423 */     this.renderers.set(0, renderer);
/*  424 */     if (renderer != null) {
/*  425 */       renderer.setPlot(this);
/*  426 */       renderer.addChangeListener(this);
/*      */     } 
/*  429 */     this.domainAxes.set(0, domainAxis);
/*  430 */     mapDatasetToDomainAxis(0, 0);
/*  431 */     if (domainAxis != null) {
/*  432 */       domainAxis.setPlot(this);
/*  433 */       domainAxis.addChangeListener(this);
/*      */     } 
/*  435 */     this.drawSharedDomainAxis = false;
/*  437 */     this.rangeAxes.set(0, rangeAxis);
/*  438 */     mapDatasetToRangeAxis(0, 0);
/*  439 */     if (rangeAxis != null) {
/*  440 */       rangeAxis.setPlot(this);
/*  441 */       rangeAxis.addChangeListener(this);
/*      */     } 
/*  444 */     configureDomainAxes();
/*  445 */     configureRangeAxes();
/*  447 */     this.domainGridlinesVisible = false;
/*  448 */     this.domainGridlinePosition = CategoryAnchor.MIDDLE;
/*  449 */     this.domainGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  450 */     this.domainGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*  452 */     this.rangeGridlinesVisible = true;
/*  453 */     this.rangeGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  454 */     this.rangeGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*  456 */     this.foregroundDomainMarkers = new HashMap();
/*  457 */     this.backgroundDomainMarkers = new HashMap();
/*  458 */     this.foregroundRangeMarkers = new HashMap();
/*  459 */     this.backgroundRangeMarkers = new HashMap();
/*  461 */     Marker baseline = new ValueMarker(0.0D, new Color(0.8F, 0.8F, 0.8F, 0.5F), new BasicStroke(1.0F), new Color(0.85F, 0.85F, 0.95F, 0.5F), new BasicStroke(1.0F), 0.6F);
/*  465 */     addRangeMarker(baseline, Layer.BACKGROUND);
/*  467 */     this.anchorValue = 0.0D;
/*  468 */     this.annotations = new ArrayList();
/*      */   }
/*      */   
/*      */   public String getPlotType() {
/*  478 */     return localizationResources.getString("Category_Plot");
/*      */   }
/*      */   
/*      */   public PlotOrientation getOrientation() {
/*  487 */     return this.orientation;
/*      */   }
/*      */   
/*      */   public void setOrientation(PlotOrientation orientation) {
/*  497 */     if (orientation == null)
/*  498 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/*  500 */     this.orientation = orientation;
/*  501 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public RectangleInsets getAxisOffset() {
/*  510 */     return this.axisOffset;
/*      */   }
/*      */   
/*      */   public void setAxisOffset(RectangleInsets offset) {
/*  519 */     if (offset == null)
/*  520 */       throw new IllegalArgumentException("Null 'offset' argument."); 
/*  522 */     this.axisOffset = offset;
/*  523 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public CategoryAxis getDomainAxis() {
/*  535 */     return getDomainAxis(0);
/*      */   }
/*      */   
/*      */   public CategoryAxis getDomainAxis(int index) {
/*  546 */     CategoryAxis result = null;
/*  547 */     if (index < this.domainAxes.size())
/*  548 */       result = (CategoryAxis)this.domainAxes.get(index); 
/*  550 */     if (result == null) {
/*  551 */       Plot parent = getParent();
/*  552 */       if (parent instanceof CategoryPlot) {
/*  553 */         CategoryPlot cp = (CategoryPlot)parent;
/*  554 */         result = cp.getDomainAxis(index);
/*      */       } 
/*      */     } 
/*  557 */     return result;
/*      */   }
/*      */   
/*      */   public void setDomainAxis(CategoryAxis axis) {
/*  567 */     setDomainAxis(0, axis);
/*      */   }
/*      */   
/*      */   public void setDomainAxis(int index, CategoryAxis axis) {
/*  578 */     setDomainAxis(index, axis, true);
/*      */   }
/*      */   
/*      */   public void setDomainAxis(int index, CategoryAxis axis, boolean notify) {
/*  590 */     CategoryAxis existing = (CategoryAxis)this.domainAxes.get(index);
/*  591 */     if (existing != null)
/*  592 */       existing.removeChangeListener(this); 
/*  594 */     if (axis != null)
/*  595 */       axis.setPlot(this); 
/*  597 */     this.domainAxes.set(index, axis);
/*  598 */     if (axis != null) {
/*  599 */       axis.configure();
/*  600 */       axis.addChangeListener(this);
/*      */     } 
/*  602 */     if (notify)
/*  603 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setDomainAxes(CategoryAxis[] axes) {
/*  614 */     for (int i = 0; i < axes.length; i++)
/*  615 */       setDomainAxis(i, axes[i], false); 
/*  617 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public AxisLocation getDomainAxisLocation() {
/*  626 */     return getDomainAxisLocation(0);
/*      */   }
/*      */   
/*      */   public AxisLocation getDomainAxisLocation(int index) {
/*  637 */     AxisLocation result = null;
/*  638 */     if (index < this.domainAxisLocations.size())
/*  639 */       result = (AxisLocation)this.domainAxisLocations.get(index); 
/*  641 */     if (result == null)
/*  642 */       result = AxisLocation.getOpposite(getDomainAxisLocation(0)); 
/*  644 */     return result;
/*      */   }
/*      */   
/*      */   public void setDomainAxisLocation(AxisLocation location) {
/*  656 */     setDomainAxisLocation(location, true);
/*      */   }
/*      */   
/*      */   public void setDomainAxisLocation(AxisLocation location, boolean notify) {
/*  666 */     if (location == null)
/*  667 */       throw new IllegalArgumentException("Null 'location' argument."); 
/*  669 */     setDomainAxisLocation(0, location);
/*      */   }
/*      */   
/*      */   public void setDomainAxisLocation(int index, AxisLocation location) {
/*  682 */     this.domainAxisLocations.set(index, location);
/*  683 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public RectangleEdge getDomainAxisEdge() {
/*  693 */     return getDomainAxisEdge(0);
/*      */   }
/*      */   
/*      */   public RectangleEdge getDomainAxisEdge(int index) {
/*  704 */     RectangleEdge result = null;
/*  705 */     AxisLocation location = getDomainAxisLocation(index);
/*  706 */     if (location != null) {
/*  707 */       result = Plot.resolveDomainAxisLocation(location, this.orientation);
/*      */     } else {
/*  710 */       result = RectangleEdge.opposite(getDomainAxisEdge(0));
/*      */     } 
/*  712 */     return result;
/*      */   }
/*      */   
/*      */   public int getDomainAxisCount() {
/*  721 */     return this.domainAxes.size();
/*      */   }
/*      */   
/*      */   public void clearDomainAxes() {
/*  729 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/*  730 */       CategoryAxis axis = (CategoryAxis)this.domainAxes.get(i);
/*  731 */       if (axis != null)
/*  732 */         axis.removeChangeListener(this); 
/*      */     } 
/*  735 */     this.domainAxes.clear();
/*  736 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void configureDomainAxes() {
/*  743 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/*  744 */       CategoryAxis axis = (CategoryAxis)this.domainAxes.get(i);
/*  745 */       if (axis != null)
/*  746 */         axis.configure(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public ValueAxis getRangeAxis() {
/*  759 */     return getRangeAxis(0);
/*      */   }
/*      */   
/*      */   public ValueAxis getRangeAxis(int index) {
/*  770 */     ValueAxis result = null;
/*  771 */     if (index < this.rangeAxes.size())
/*  772 */       result = (ValueAxis)this.rangeAxes.get(index); 
/*  774 */     if (result == null) {
/*  775 */       Plot parent = getParent();
/*  776 */       if (parent instanceof CategoryPlot) {
/*  777 */         CategoryPlot cp = (CategoryPlot)parent;
/*  778 */         result = cp.getRangeAxis(index);
/*      */       } 
/*      */     } 
/*  781 */     return result;
/*      */   }
/*      */   
/*      */   public void setRangeAxis(ValueAxis axis) {
/*  791 */     setRangeAxis(0, axis);
/*      */   }
/*      */   
/*      */   public void setRangeAxis(int index, ValueAxis axis) {
/*  802 */     setRangeAxis(index, axis, true);
/*      */   }
/*      */   
/*      */   public void setRangeAxis(int index, ValueAxis axis, boolean notify) {
/*  814 */     ValueAxis existing = (ValueAxis)this.rangeAxes.get(index);
/*  815 */     if (existing != null)
/*  816 */       existing.removeChangeListener(this); 
/*  818 */     if (axis != null)
/*  819 */       axis.setPlot(this); 
/*  821 */     this.rangeAxes.set(index, axis);
/*  822 */     if (axis != null) {
/*  823 */       axis.configure();
/*  824 */       axis.addChangeListener(this);
/*      */     } 
/*  826 */     if (notify)
/*  827 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setRangeAxes(ValueAxis[] axes) {
/*  838 */     for (int i = 0; i < axes.length; i++)
/*  839 */       setRangeAxis(i, axes[i], false); 
/*  841 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public AxisLocation getRangeAxisLocation() {
/*  850 */     return getRangeAxisLocation(0);
/*      */   }
/*      */   
/*      */   public AxisLocation getRangeAxisLocation(int index) {
/*  861 */     AxisLocation result = null;
/*  862 */     if (index < this.rangeAxisLocations.size())
/*  863 */       result = (AxisLocation)this.rangeAxisLocations.get(index); 
/*  865 */     if (result == null)
/*  866 */       result = AxisLocation.getOpposite(getRangeAxisLocation(0)); 
/*  868 */     return result;
/*      */   }
/*      */   
/*      */   public void setRangeAxisLocation(AxisLocation location) {
/*  879 */     setRangeAxisLocation(location, true);
/*      */   }
/*      */   
/*      */   public void setRangeAxisLocation(AxisLocation location, boolean notify) {
/*  890 */     setRangeAxisLocation(0, location, notify);
/*      */   }
/*      */   
/*      */   public void setRangeAxisLocation(int index, AxisLocation location) {
/*  901 */     setRangeAxisLocation(index, location, true);
/*      */   }
/*      */   
/*      */   public void setRangeAxisLocation(int index, AxisLocation location, boolean notify) {
/*  915 */     this.rangeAxisLocations.set(index, location);
/*  916 */     if (notify)
/*  917 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public RectangleEdge getRangeAxisEdge() {
/*  927 */     return getRangeAxisEdge(0);
/*      */   }
/*      */   
/*      */   public RectangleEdge getRangeAxisEdge(int index) {
/*  938 */     AxisLocation location = getRangeAxisLocation(index);
/*  939 */     RectangleEdge result = Plot.resolveRangeAxisLocation(location, this.orientation);
/*  942 */     if (result == null)
/*  943 */       result = RectangleEdge.opposite(getRangeAxisEdge(0)); 
/*  945 */     return result;
/*      */   }
/*      */   
/*      */   public int getRangeAxisCount() {
/*  954 */     return this.rangeAxes.size();
/*      */   }
/*      */   
/*      */   public void clearRangeAxes() {
/*  962 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/*  963 */       ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
/*  964 */       if (axis != null)
/*  965 */         axis.removeChangeListener(this); 
/*      */     } 
/*  968 */     this.rangeAxes.clear();
/*  969 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void configureRangeAxes() {
/*  976 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/*  977 */       ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
/*  978 */       if (axis != null)
/*  979 */         axis.configure(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public CategoryDataset getDataset() {
/*  990 */     return getDataset(0);
/*      */   }
/*      */   
/*      */   public CategoryDataset getDataset(int index) {
/* 1001 */     CategoryDataset result = null;
/* 1002 */     if (this.datasets.size() > index)
/* 1003 */       result = (CategoryDataset)this.datasets.get(index); 
/* 1005 */     return result;
/*      */   }
/*      */   
/*      */   public void setDataset(CategoryDataset dataset) {
/* 1018 */     setDataset(0, dataset);
/*      */   }
/*      */   
/*      */   public void setDataset(int index, CategoryDataset dataset) {
/* 1029 */     CategoryDataset existing = (CategoryDataset)this.datasets.get(index);
/* 1030 */     if (existing != null)
/* 1031 */       existing.removeChangeListener(this); 
/* 1033 */     this.datasets.set(index, dataset);
/* 1034 */     if (dataset != null)
/* 1035 */       dataset.addChangeListener(this); 
/* 1039 */     DatasetChangeEvent event = new DatasetChangeEvent(this, (Dataset)dataset);
/* 1040 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */   public void mapDatasetToDomainAxis(int index, int axisIndex) {
/* 1051 */     this.datasetToDomainAxisMap.set(index, new Integer(axisIndex));
/* 1053 */     datasetChanged(new DatasetChangeEvent(this, (Dataset)getDataset(index)));
/*      */   }
/*      */   
/*      */   public CategoryAxis getDomainAxisForDataset(int index) {
/* 1065 */     CategoryAxis result = getDomainAxis();
/* 1066 */     Integer axisIndex = (Integer)this.datasetToDomainAxisMap.get(index);
/* 1067 */     if (axisIndex != null)
/* 1068 */       result = getDomainAxis(axisIndex.intValue()); 
/* 1070 */     return result;
/*      */   }
/*      */   
/*      */   public void mapDatasetToRangeAxis(int index, int axisIndex) {
/* 1080 */     this.datasetToRangeAxisMap.set(index, new Integer(axisIndex));
/* 1082 */     datasetChanged(new DatasetChangeEvent(this, (Dataset)getDataset(index)));
/*      */   }
/*      */   
/*      */   public ValueAxis getRangeAxisForDataset(int index) {
/* 1094 */     ValueAxis result = getRangeAxis();
/* 1095 */     Integer axisIndex = (Integer)this.datasetToRangeAxisMap.get(index);
/* 1096 */     if (axisIndex != null)
/* 1097 */       result = getRangeAxis(axisIndex.intValue()); 
/* 1099 */     return result;
/*      */   }
/*      */   
/*      */   public CategoryItemRenderer getRenderer() {
/* 1108 */     return getRenderer(0);
/*      */   }
/*      */   
/*      */   public CategoryItemRenderer getRenderer(int index) {
/* 1119 */     CategoryItemRenderer result = null;
/* 1120 */     if (this.renderers.size() > index)
/* 1121 */       result = (CategoryItemRenderer)this.renderers.get(index); 
/* 1123 */     return result;
/*      */   }
/*      */   
/*      */   public void setRenderer(CategoryItemRenderer renderer) {
/* 1134 */     setRenderer(0, renderer, true);
/*      */   }
/*      */   
/*      */   public void setRenderer(CategoryItemRenderer renderer, boolean notify) {
/* 1153 */     setRenderer(0, renderer, notify);
/*      */   }
/*      */   
/*      */   public void setRenderer(int index, CategoryItemRenderer renderer) {
/* 1164 */     setRenderer(index, renderer, true);
/*      */   }
/*      */   
/*      */   public void setRenderer(int index, CategoryItemRenderer renderer, boolean notify) {
/* 1179 */     CategoryItemRenderer existing = (CategoryItemRenderer)this.renderers.get(index);
/* 1181 */     if (existing != null)
/* 1182 */       existing.removeChangeListener(this); 
/* 1186 */     this.renderers.set(index, renderer);
/* 1187 */     if (renderer != null) {
/* 1188 */       renderer.setPlot(this);
/* 1189 */       renderer.addChangeListener(this);
/*      */     } 
/* 1192 */     configureDomainAxes();
/* 1193 */     configureRangeAxes();
/* 1195 */     if (notify)
/* 1196 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setRenderers(CategoryItemRenderer[] renderers) {
/* 1207 */     for (int i = 0; i < renderers.length; i++)
/* 1208 */       setRenderer(i, renderers[i], false); 
/* 1210 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public CategoryItemRenderer getRendererForDataset(CategoryDataset dataset) {
/* 1222 */     CategoryItemRenderer result = null;
/* 1223 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 1224 */       if (this.datasets.get(i) == dataset) {
/* 1225 */         result = (CategoryItemRenderer)this.renderers.get(i);
/*      */         break;
/*      */       } 
/*      */     } 
/* 1229 */     return result;
/*      */   }
/*      */   
/*      */   public int getIndexOf(CategoryItemRenderer renderer) {
/* 1241 */     return this.renderers.indexOf(renderer);
/*      */   }
/*      */   
/*      */   public DatasetRenderingOrder getDatasetRenderingOrder() {
/* 1250 */     return this.renderingOrder;
/*      */   }
/*      */   
/*      */   public void setDatasetRenderingOrder(DatasetRenderingOrder order) {
/* 1262 */     if (order == null)
/* 1263 */       throw new IllegalArgumentException("Null 'order' argument."); 
/* 1265 */     this.renderingOrder = order;
/* 1266 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public SortOrder getColumnRenderingOrder() {
/* 1275 */     return this.columnRenderingOrder;
/*      */   }
/*      */   
/*      */   public void setColumnRenderingOrder(SortOrder order) {
/* 1284 */     this.columnRenderingOrder = order;
/*      */   }
/*      */   
/*      */   public SortOrder getRowRenderingOrder() {
/* 1293 */     return this.rowRenderingOrder;
/*      */   }
/*      */   
/*      */   public void setRowRenderingOrder(SortOrder order) {
/* 1302 */     if (order == null)
/* 1303 */       throw new IllegalArgumentException("Null 'order' argument."); 
/* 1305 */     this.rowRenderingOrder = order;
/*      */   }
/*      */   
/*      */   public boolean isDomainGridlinesVisible() {
/* 1314 */     return this.domainGridlinesVisible;
/*      */   }
/*      */   
/*      */   public void setDomainGridlinesVisible(boolean visible) {
/* 1327 */     if (this.domainGridlinesVisible != visible) {
/* 1328 */       this.domainGridlinesVisible = visible;
/* 1329 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public CategoryAnchor getDomainGridlinePosition() {
/* 1339 */     return this.domainGridlinePosition;
/*      */   }
/*      */   
/*      */   public void setDomainGridlinePosition(CategoryAnchor position) {
/* 1348 */     this.domainGridlinePosition = position;
/* 1349 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getDomainGridlineStroke() {
/* 1358 */     return this.domainGridlineStroke;
/*      */   }
/*      */   
/*      */   public void setDomainGridlineStroke(Stroke stroke) {
/* 1368 */     this.domainGridlineStroke = stroke;
/* 1369 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getDomainGridlinePaint() {
/* 1378 */     return this.domainGridlinePaint;
/*      */   }
/*      */   
/*      */   public void setDomainGridlinePaint(Paint paint) {
/* 1388 */     this.domainGridlinePaint = paint;
/* 1389 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isRangeGridlinesVisible() {
/* 1398 */     return this.rangeGridlinesVisible;
/*      */   }
/*      */   
/*      */   public void setRangeGridlinesVisible(boolean visible) {
/* 1409 */     if (this.rangeGridlinesVisible != visible) {
/* 1410 */       this.rangeGridlinesVisible = visible;
/* 1411 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Stroke getRangeGridlineStroke() {
/* 1421 */     return this.rangeGridlineStroke;
/*      */   }
/*      */   
/*      */   public void setRangeGridlineStroke(Stroke stroke) {
/* 1431 */     this.rangeGridlineStroke = stroke;
/* 1432 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getRangeGridlinePaint() {
/* 1441 */     return this.rangeGridlinePaint;
/*      */   }
/*      */   
/*      */   public void setRangeGridlinePaint(Paint paint) {
/* 1451 */     this.rangeGridlinePaint = paint;
/* 1452 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public LegendItemCollection getFixedLegendItems() {
/* 1461 */     return this.fixedLegendItems;
/*      */   }
/*      */   
/*      */   public void setFixedLegendItems(LegendItemCollection items) {
/* 1472 */     this.fixedLegendItems = items;
/* 1473 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public LegendItemCollection getLegendItems() {
/* 1484 */     LegendItemCollection result = this.fixedLegendItems;
/* 1485 */     if (result == null) {
/* 1486 */       result = new LegendItemCollection();
/* 1488 */       int count = this.datasets.size();
/* 1489 */       for (int datasetIndex = 0; datasetIndex < count; datasetIndex++) {
/* 1490 */         CategoryDataset dataset = getDataset(datasetIndex);
/* 1491 */         if (dataset != null) {
/* 1492 */           CategoryItemRenderer renderer = getRenderer(datasetIndex);
/* 1493 */           if (renderer != null) {
/* 1494 */             int seriesCount = dataset.getRowCount();
/* 1495 */             for (int i = 0; i < seriesCount; i++) {
/* 1496 */               LegendItem item = renderer.getLegendItem(datasetIndex, i);
/* 1499 */               if (item != null)
/* 1500 */                 result.add(item); 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1507 */     return result;
/*      */   }
/*      */   
/*      */   public void handleClick(int x, int y, PlotRenderingInfo info) {
/* 1520 */     Rectangle2D dataArea = info.getDataArea();
/* 1521 */     if (dataArea.contains(x, y)) {
/* 1523 */       double java2D = 0.0D;
/* 1524 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 1525 */         java2D = x;
/* 1527 */       } else if (this.orientation == PlotOrientation.VERTICAL) {
/* 1528 */         java2D = y;
/*      */       } 
/* 1530 */       RectangleEdge edge = Plot.resolveRangeAxisLocation(getRangeAxisLocation(), this.orientation);
/* 1533 */       double value = getRangeAxis().java2DToValue(java2D, info.getDataArea(), edge);
/* 1536 */       setAnchorValue(value);
/* 1537 */       setRangeCrosshairValue(value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void zoom(double percent) {
/* 1553 */     if (percent > 0.0D) {
/* 1554 */       double range = getRangeAxis().getRange().getLength();
/* 1555 */       double scaledRange = range * percent;
/* 1556 */       getRangeAxis().setRange(this.anchorValue - scaledRange / 2.0D, this.anchorValue + scaledRange / 2.0D);
/*      */     } else {
/* 1562 */       getRangeAxis().setAutoRange(true);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void datasetChanged(DatasetChangeEvent event) {
/* 1576 */     int count = this.rangeAxes.size();
/* 1577 */     for (int axisIndex = 0; axisIndex < count; axisIndex++) {
/* 1578 */       ValueAxis yAxis = getRangeAxis(axisIndex);
/* 1579 */       if (yAxis != null)
/* 1580 */         yAxis.configure(); 
/*      */     } 
/* 1583 */     if (getParent() != null) {
/* 1584 */       getParent().datasetChanged(event);
/*      */     } else {
/* 1587 */       PlotChangeEvent e = new PlotChangeEvent(this);
/* 1588 */       e.setType(ChartChangeEventType.DATASET_UPDATED);
/* 1589 */       notifyListeners(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void rendererChanged(RendererChangeEvent event) {
/* 1600 */     Plot parent = getParent();
/* 1601 */     if (parent != null) {
/* 1602 */       if (parent instanceof RendererChangeListener) {
/* 1603 */         RendererChangeListener rcl = (RendererChangeListener)parent;
/* 1604 */         rcl.rendererChanged(event);
/*      */       } else {
/* 1609 */         throw new RuntimeException("The renderer has changed and I don't know what to do!");
/*      */       } 
/*      */     } else {
/* 1615 */       PlotChangeEvent e = new PlotChangeEvent(this);
/* 1616 */       notifyListeners(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addDomainMarker(CategoryMarker marker) {
/* 1629 */     addDomainMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */   public void addDomainMarker(CategoryMarker marker, Layer layer) {
/* 1643 */     addDomainMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */   public void addDomainMarker(int index, CategoryMarker marker, Layer layer) {
/* 1658 */     if (layer == Layer.FOREGROUND) {
/* 1659 */       Collection markers = (Collection)this.foregroundDomainMarkers.get(new Integer(index));
/* 1662 */       if (markers == null) {
/* 1663 */         markers = new ArrayList();
/* 1664 */         this.foregroundDomainMarkers.put(new Integer(index), markers);
/*      */       } 
/* 1666 */       markers.add(marker);
/* 1668 */     } else if (layer == Layer.BACKGROUND) {
/* 1669 */       Collection markers = (Collection)this.backgroundDomainMarkers.get(new Integer(index));
/* 1672 */       if (markers == null) {
/* 1673 */         markers = new ArrayList();
/* 1674 */         this.backgroundDomainMarkers.put(new Integer(index), markers);
/*      */       } 
/* 1676 */       markers.add(marker);
/*      */     } 
/* 1678 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void clearDomainMarkers() {
/* 1686 */     if (this.backgroundDomainMarkers != null)
/* 1687 */       this.backgroundDomainMarkers.clear(); 
/* 1689 */     if (this.foregroundDomainMarkers != null)
/* 1690 */       this.foregroundDomainMarkers.clear(); 
/* 1692 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Collection getDomainMarkers(Layer layer) {
/* 1703 */     return getDomainMarkers(0, layer);
/*      */   }
/*      */   
/*      */   public Collection getDomainMarkers(int index, Layer layer) {
/* 1716 */     Collection result = null;
/* 1717 */     Integer key = new Integer(index);
/* 1718 */     if (layer == Layer.FOREGROUND) {
/* 1719 */       result = (Collection)this.foregroundDomainMarkers.get(key);
/* 1721 */     } else if (layer == Layer.BACKGROUND) {
/* 1722 */       result = (Collection)this.backgroundDomainMarkers.get(key);
/*      */     } 
/* 1724 */     if (result != null)
/* 1725 */       result = Collections.unmodifiableCollection(result); 
/* 1727 */     return result;
/*      */   }
/*      */   
/*      */   public void clearDomainMarkers(int index) {
/* 1736 */     Integer key = new Integer(index);
/* 1737 */     if (this.backgroundDomainMarkers != null) {
/* 1738 */       Collection markers = (Collection)this.backgroundDomainMarkers.get(key);
/* 1740 */       if (markers != null)
/* 1741 */         markers.clear(); 
/*      */     } 
/* 1744 */     if (this.foregroundDomainMarkers != null) {
/* 1745 */       Collection markers = (Collection)this.foregroundDomainMarkers.get(key);
/* 1747 */       if (markers != null)
/* 1748 */         markers.clear(); 
/*      */     } 
/* 1751 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void addRangeMarker(Marker marker) {
/* 1763 */     addRangeMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */   public void addRangeMarker(Marker marker, Layer layer) {
/* 1777 */     addRangeMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */   public void addRangeMarker(int index, Marker marker, Layer layer) {
/* 1792 */     if (layer == Layer.FOREGROUND) {
/* 1793 */       Collection markers = (Collection)this.foregroundRangeMarkers.get(new Integer(index));
/* 1796 */       if (markers == null) {
/* 1797 */         markers = new ArrayList();
/* 1798 */         this.foregroundRangeMarkers.put(new Integer(index), markers);
/*      */       } 
/* 1800 */       markers.add(marker);
/* 1802 */     } else if (layer == Layer.BACKGROUND) {
/* 1803 */       Collection markers = (Collection)this.backgroundRangeMarkers.get(new Integer(index));
/* 1806 */       if (markers == null) {
/* 1807 */         markers = new ArrayList();
/* 1808 */         this.backgroundRangeMarkers.put(new Integer(index), markers);
/*      */       } 
/* 1810 */       markers.add(marker);
/*      */     } 
/* 1812 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void clearRangeMarkers() {
/* 1820 */     if (this.backgroundRangeMarkers != null)
/* 1821 */       this.backgroundRangeMarkers.clear(); 
/* 1823 */     if (this.foregroundRangeMarkers != null)
/* 1824 */       this.foregroundRangeMarkers.clear(); 
/* 1826 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Collection getRangeMarkers(Layer layer) {
/* 1837 */     return getRangeMarkers(0, layer);
/*      */   }
/*      */   
/*      */   public Collection getRangeMarkers(int index, Layer layer) {
/* 1850 */     Collection result = null;
/* 1851 */     Integer key = new Integer(index);
/* 1852 */     if (layer == Layer.FOREGROUND) {
/* 1853 */       result = (Collection)this.foregroundRangeMarkers.get(key);
/* 1855 */     } else if (layer == Layer.BACKGROUND) {
/* 1856 */       result = (Collection)this.backgroundRangeMarkers.get(key);
/*      */     } 
/* 1858 */     if (result != null)
/* 1859 */       result = Collections.unmodifiableCollection(result); 
/* 1861 */     return result;
/*      */   }
/*      */   
/*      */   public void clearRangeMarkers(int index) {
/* 1870 */     Integer key = new Integer(index);
/* 1871 */     if (this.backgroundRangeMarkers != null) {
/* 1872 */       Collection markers = (Collection)this.backgroundRangeMarkers.get(key);
/* 1874 */       if (markers != null)
/* 1875 */         markers.clear(); 
/*      */     } 
/* 1878 */     if (this.foregroundRangeMarkers != null) {
/* 1879 */       Collection markers = (Collection)this.foregroundRangeMarkers.get(key);
/* 1881 */       if (markers != null)
/* 1882 */         markers.clear(); 
/*      */     } 
/* 1885 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isRangeCrosshairVisible() {
/* 1894 */     return this.rangeCrosshairVisible;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairVisible(boolean flag) {
/* 1904 */     if (this.rangeCrosshairVisible != flag) {
/* 1905 */       this.rangeCrosshairVisible = flag;
/* 1906 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isRangeCrosshairLockedOnData() {
/* 1918 */     return this.rangeCrosshairLockedOnData;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairLockedOnData(boolean flag) {
/* 1929 */     if (this.rangeCrosshairLockedOnData != flag) {
/* 1930 */       this.rangeCrosshairLockedOnData = flag;
/* 1931 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getRangeCrosshairValue() {
/* 1942 */     return this.rangeCrosshairValue;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairValue(double value) {
/* 1955 */     setRangeCrosshairValue(value, true);
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairValue(double value, boolean notify) {
/* 1971 */     this.rangeCrosshairValue = value;
/* 1972 */     if (isRangeCrosshairVisible() && notify)
/* 1973 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Stroke getRangeCrosshairStroke() {
/* 1985 */     return this.rangeCrosshairStroke;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairStroke(Stroke stroke) {
/* 1996 */     this.rangeCrosshairStroke = stroke;
/* 1997 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getRangeCrosshairPaint() {
/* 2006 */     return this.rangeCrosshairPaint;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairPaint(Paint paint) {
/* 2016 */     this.rangeCrosshairPaint = paint;
/* 2017 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public List getAnnotations() {
/* 2026 */     return this.annotations;
/*      */   }
/*      */   
/*      */   public void addAnnotation(CategoryAnnotation annotation) {
/* 2036 */     if (annotation == null)
/* 2037 */       throw new IllegalArgumentException("Null 'annotation' argument."); 
/* 2039 */     this.annotations.add(annotation);
/* 2040 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean removeAnnotation(CategoryAnnotation annotation) {
/* 2052 */     if (annotation == null)
/* 2053 */       throw new IllegalArgumentException("Null 'annotation' argument."); 
/* 2055 */     boolean removed = this.annotations.remove(annotation);
/* 2056 */     if (removed)
/* 2057 */       notifyListeners(new PlotChangeEvent(this)); 
/* 2059 */     return removed;
/*      */   }
/*      */   
/*      */   public void clearAnnotations() {
/* 2067 */     this.annotations.clear();
/* 2068 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   protected AxisSpace calculateDomainAxisSpace(Graphics2D g2, Rectangle2D plotArea, AxisSpace space) {
/* 2084 */     if (space == null)
/* 2085 */       space = new AxisSpace(); 
/* 2089 */     if (this.fixedDomainAxisSpace != null) {
/* 2090 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 2091 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getLeft(), RectangleEdge.LEFT);
/* 2094 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getRight(), RectangleEdge.RIGHT);
/* 2098 */       } else if (this.orientation == PlotOrientation.VERTICAL) {
/* 2099 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getTop(), RectangleEdge.TOP);
/* 2102 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getBottom(), RectangleEdge.BOTTOM);
/*      */       } 
/*      */     } else {
/* 2109 */       RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(getDomainAxisLocation(), this.orientation);
/* 2112 */       if (this.drawSharedDomainAxis)
/* 2113 */         space = getDomainAxis().reserveSpace(g2, this, plotArea, domainEdge, space); 
/* 2119 */       for (int i = 0; i < this.domainAxes.size(); i++) {
/* 2120 */         Axis xAxis = (Axis)this.domainAxes.get(i);
/* 2121 */         if (xAxis != null) {
/* 2122 */           RectangleEdge edge = getDomainAxisEdge(i);
/* 2123 */           space = xAxis.reserveSpace(g2, this, plotArea, edge, space);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2128 */     return space;
/*      */   }
/*      */   
/*      */   protected AxisSpace calculateRangeAxisSpace(Graphics2D g2, Rectangle2D plotArea, AxisSpace space) {
/* 2145 */     if (space == null)
/* 2146 */       space = new AxisSpace(); 
/* 2150 */     if (this.fixedRangeAxisSpace != null) {
/* 2151 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 2152 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getTop(), RectangleEdge.TOP);
/* 2155 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getBottom(), RectangleEdge.BOTTOM);
/* 2159 */       } else if (this.orientation == PlotOrientation.VERTICAL) {
/* 2160 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getLeft(), RectangleEdge.LEFT);
/* 2163 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getRight(), RectangleEdge.RIGHT);
/*      */       } 
/*      */     } else {
/* 2170 */       for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 2171 */         Axis yAxis = (Axis)this.rangeAxes.get(i);
/* 2172 */         if (yAxis != null) {
/* 2173 */           RectangleEdge edge = getRangeAxisEdge(i);
/* 2174 */           space = yAxis.reserveSpace(g2, this, plotArea, edge, space);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2178 */     return space;
/*      */   }
/*      */   
/*      */   protected AxisSpace calculateAxisSpace(Graphics2D g2, Rectangle2D plotArea) {
/* 2193 */     AxisSpace space = new AxisSpace();
/* 2194 */     space = calculateRangeAxisSpace(g2, plotArea, space);
/* 2195 */     space = calculateDomainAxisSpace(g2, plotArea, space);
/* 2196 */     return space;
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo state) {
/* 2221 */     boolean b1 = (area.getWidth() <= 10.0D);
/* 2222 */     boolean b2 = (area.getHeight() <= 10.0D);
/* 2223 */     if (b1 || b2)
/*      */       return; 
/* 2228 */     if (state == null)
/* 2232 */       state = new PlotRenderingInfo(null); 
/* 2234 */     state.setPlotArea(area);
/* 2237 */     RectangleInsets insets = getInsets();
/* 2238 */     insets.trim(area);
/* 2241 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 2242 */     Rectangle2D dataArea = space.shrink(area, null);
/* 2243 */     this.axisOffset.trim(dataArea);
/* 2245 */     if (state != null)
/* 2246 */       state.setDataArea(dataArea); 
/* 2251 */     if (getRenderer() != null) {
/* 2252 */       getRenderer().drawBackground(g2, this, dataArea);
/*      */     } else {
/* 2255 */       drawBackground(g2, dataArea);
/*      */     } 
/* 2258 */     Map axisStateMap = drawAxes(g2, area, dataArea, state);
/* 2260 */     drawDomainGridlines(g2, dataArea);
/* 2262 */     AxisState rangeAxisState = (AxisState)axisStateMap.get(getRangeAxis());
/* 2263 */     if (rangeAxisState == null && 
/* 2264 */       parentState != null)
/* 2265 */       rangeAxisState = (AxisState)parentState.getSharedAxisStates().get(getRangeAxis()); 
/* 2271 */     if (rangeAxisState != null)
/* 2272 */       drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks()); 
/*      */     int i;
/* 2276 */     for (i = 0; i < this.renderers.size(); i++)
/* 2277 */       drawDomainMarkers(g2, dataArea, i, Layer.BACKGROUND); 
/* 2279 */     for (i = 0; i < this.renderers.size(); i++)
/* 2280 */       drawRangeMarkers(g2, dataArea, i, Layer.BACKGROUND); 
/* 2284 */     boolean foundData = false;
/* 2285 */     Shape savedClip = g2.getClip();
/* 2286 */     g2.clip(dataArea);
/* 2288 */     Composite originalComposite = g2.getComposite();
/* 2289 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/* 2293 */     DatasetRenderingOrder order = getDatasetRenderingOrder();
/* 2294 */     if (order == DatasetRenderingOrder.FORWARD) {
/* 2295 */       for (int k = 0; k < this.datasets.size(); k++)
/* 2296 */         foundData = (render(g2, dataArea, k, state) || foundData); 
/*      */     } else {
/* 2300 */       for (int k = this.datasets.size() - 1; k >= 0; k--)
/* 2301 */         foundData = (render(g2, dataArea, k, state) || foundData); 
/*      */     } 
/* 2304 */     g2.setClip(savedClip);
/* 2305 */     g2.setComposite(originalComposite);
/* 2307 */     if (!foundData)
/* 2308 */       drawNoDataMessage(g2, dataArea); 
/* 2312 */     if (isRangeCrosshairVisible())
/* 2313 */       drawRangeLine(g2, dataArea, getRangeCrosshairValue(), getRangeCrosshairStroke(), getRangeCrosshairPaint()); 
/*      */     int j;
/* 2320 */     for (j = 0; j < this.renderers.size(); j++)
/* 2321 */       drawDomainMarkers(g2, dataArea, j, Layer.FOREGROUND); 
/* 2323 */     for (j = 0; j < this.renderers.size(); j++)
/* 2324 */       drawRangeMarkers(g2, dataArea, j, Layer.FOREGROUND); 
/* 2328 */     drawAnnotations(g2, dataArea);
/* 2331 */     if (getRenderer() != null) {
/* 2332 */       getRenderer().drawOutline(g2, this, dataArea);
/*      */     } else {
/* 2335 */       drawOutline(g2, dataArea);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Map drawAxes(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, PlotRenderingInfo plotState) {
/* 2356 */     AxisCollection axisCollection = new AxisCollection();
/*      */     int index;
/* 2359 */     for (index = 0; index < this.domainAxes.size(); index++) {
/* 2360 */       CategoryAxis xAxis = (CategoryAxis)this.domainAxes.get(index);
/* 2361 */       if (xAxis != null)
/* 2362 */         axisCollection.add((Axis)xAxis, getDomainAxisEdge(index)); 
/*      */     } 
/* 2367 */     for (index = 0; index < this.rangeAxes.size(); index++) {
/* 2368 */       ValueAxis yAxis = (ValueAxis)this.rangeAxes.get(index);
/* 2369 */       if (yAxis != null)
/* 2370 */         axisCollection.add((Axis)yAxis, getRangeAxisEdge(index)); 
/*      */     } 
/* 2374 */     Map axisStateMap = new HashMap();
/* 2377 */     double cursor = dataArea.getMinY() - this.axisOffset.calculateTopOutset(dataArea.getHeight());
/* 2380 */     Iterator iterator = axisCollection.getAxesAtTop().iterator();
/* 2381 */     while (iterator.hasNext()) {
/* 2382 */       Axis axis = iterator.next();
/* 2383 */       if (axis != null) {
/* 2384 */         AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.TOP, plotState);
/* 2387 */         cursor = axisState.getCursor();
/* 2388 */         axisStateMap.put(axis, axisState);
/*      */       } 
/*      */     } 
/* 2393 */     cursor = dataArea.getMaxY() + this.axisOffset.calculateBottomOutset(dataArea.getHeight());
/* 2395 */     iterator = axisCollection.getAxesAtBottom().iterator();
/* 2396 */     while (iterator.hasNext()) {
/* 2397 */       Axis axis = iterator.next();
/* 2398 */       if (axis != null) {
/* 2399 */         AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.BOTTOM, plotState);
/* 2403 */         cursor = axisState.getCursor();
/* 2404 */         axisStateMap.put(axis, axisState);
/*      */       } 
/*      */     } 
/* 2409 */     cursor = dataArea.getMinX() - this.axisOffset.calculateLeftOutset(dataArea.getWidth());
/* 2411 */     iterator = axisCollection.getAxesAtLeft().iterator();
/* 2412 */     while (iterator.hasNext()) {
/* 2413 */       Axis axis = iterator.next();
/* 2414 */       if (axis != null) {
/* 2415 */         AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.LEFT, plotState);
/* 2419 */         cursor = axisState.getCursor();
/* 2420 */         axisStateMap.put(axis, axisState);
/*      */       } 
/*      */     } 
/* 2425 */     cursor = dataArea.getMaxX() + this.axisOffset.calculateRightOutset(dataArea.getWidth());
/* 2427 */     iterator = axisCollection.getAxesAtRight().iterator();
/* 2428 */     while (iterator.hasNext()) {
/* 2429 */       Axis axis = iterator.next();
/* 2430 */       if (axis != null) {
/* 2431 */         AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.RIGHT, plotState);
/* 2435 */         cursor = axisState.getCursor();
/* 2436 */         axisStateMap.put(axis, axisState);
/*      */       } 
/*      */     } 
/* 2440 */     return axisStateMap;
/*      */   }
/*      */   
/*      */   public boolean render(Graphics2D g2, Rectangle2D dataArea, int index, PlotRenderingInfo info) {
/* 2458 */     boolean foundData = false;
/* 2459 */     CategoryDataset currentDataset = getDataset(index);
/* 2460 */     CategoryItemRenderer renderer = getRenderer(index);
/* 2461 */     CategoryAxis domainAxis = getDomainAxisForDataset(index);
/* 2462 */     ValueAxis rangeAxis = getRangeAxisForDataset(index);
/* 2463 */     boolean hasData = !DatasetUtilities.isEmptyOrNull(currentDataset);
/* 2464 */     if (hasData && renderer != null) {
/* 2466 */       foundData = true;
/* 2467 */       CategoryItemRendererState state = renderer.initialise(g2, dataArea, this, index, info);
/* 2470 */       int columnCount = currentDataset.getColumnCount();
/* 2471 */       int rowCount = currentDataset.getRowCount();
/* 2472 */       int passCount = renderer.getPassCount();
/* 2473 */       for (int pass = 0; pass < passCount; pass++) {
/* 2474 */         if (this.columnRenderingOrder == SortOrder.ASCENDING) {
/* 2475 */           for (int column = 0; column < columnCount; column++) {
/* 2476 */             if (this.rowRenderingOrder == SortOrder.ASCENDING) {
/* 2477 */               for (int row = 0; row < rowCount; row++)
/* 2478 */                 renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column, pass); 
/*      */             } else {
/* 2485 */               for (int row = rowCount - 1; row >= 0; row--)
/* 2486 */                 renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column, pass); 
/*      */             } 
/*      */           } 
/*      */         } else {
/* 2495 */           for (int column = columnCount - 1; column >= 0; column--) {
/* 2496 */             if (this.rowRenderingOrder == SortOrder.ASCENDING) {
/* 2497 */               for (int row = 0; row < rowCount; row++)
/* 2498 */                 renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column, pass); 
/*      */             } else {
/* 2505 */               for (int row = rowCount - 1; row >= 0; row--)
/* 2506 */                 renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column, pass); 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2516 */     return foundData;
/*      */   }
/*      */   
/*      */   protected void drawDomainGridlines(Graphics2D g2, Rectangle2D dataArea) {
/* 2529 */     if (isDomainGridlinesVisible()) {
/* 2530 */       CategoryAnchor anchor = getDomainGridlinePosition();
/* 2531 */       RectangleEdge domainAxisEdge = getDomainAxisEdge();
/* 2532 */       Stroke gridStroke = getDomainGridlineStroke();
/* 2533 */       Paint gridPaint = getDomainGridlinePaint();
/* 2534 */       if (gridStroke != null && gridPaint != null) {
/* 2536 */         CategoryDataset data = getDataset();
/* 2537 */         if (data != null) {
/* 2538 */           CategoryAxis axis = getDomainAxis();
/* 2539 */           if (axis != null) {
/* 2540 */             int columnCount = data.getColumnCount();
/* 2541 */             for (int c = 0; c < columnCount; c++) {
/* 2542 */               double xx = axis.getCategoryJava2DCoordinate(anchor, c, columnCount, dataArea, domainAxisEdge);
/* 2545 */               CategoryItemRenderer renderer1 = getRenderer();
/* 2546 */               if (renderer1 != null)
/* 2547 */                 renderer1.drawDomainGridline(g2, this, dataArea, xx); 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawRangeGridlines(Graphics2D g2, Rectangle2D dataArea, List ticks) {
/* 2568 */     if (isRangeGridlinesVisible()) {
/* 2569 */       Stroke gridStroke = getRangeGridlineStroke();
/* 2570 */       Paint gridPaint = getRangeGridlinePaint();
/* 2571 */       if (gridStroke != null && gridPaint != null) {
/* 2572 */         ValueAxis axis = getRangeAxis();
/* 2573 */         if (axis != null) {
/* 2574 */           Iterator iterator = ticks.iterator();
/* 2575 */           while (iterator.hasNext()) {
/* 2576 */             ValueTick tick = iterator.next();
/* 2577 */             CategoryItemRenderer renderer1 = getRenderer();
/* 2578 */             if (renderer1 != null)
/* 2579 */               renderer1.drawRangeGridline(g2, this, getRangeAxis(), dataArea, tick.getValue()); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawAnnotations(Graphics2D g2, Rectangle2D dataArea) {
/* 2598 */     if (getAnnotations() != null) {
/* 2599 */       Iterator iterator = getAnnotations().iterator();
/* 2600 */       while (iterator.hasNext()) {
/* 2601 */         CategoryAnnotation annotation = iterator.next();
/* 2603 */         annotation.draw(g2, this, dataArea, getDomainAxis(), getRangeAxis());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawDomainMarkers(Graphics2D g2, Rectangle2D dataArea, int index, Layer layer) {
/* 2623 */     CategoryItemRenderer r = getRenderer(index);
/* 2624 */     if (r == null)
/*      */       return; 
/* 2628 */     Collection markers = getDomainMarkers(index, layer);
/* 2629 */     CategoryAxis axis = getDomainAxisForDataset(index);
/* 2630 */     if (markers != null && axis != null) {
/* 2631 */       Iterator iterator = markers.iterator();
/* 2632 */       while (iterator.hasNext()) {
/* 2633 */         CategoryMarker marker = iterator.next();
/* 2634 */         r.drawDomainMarker(g2, this, axis, marker, dataArea);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawRangeMarkers(Graphics2D g2, Rectangle2D dataArea, int index, Layer layer) {
/* 2652 */     CategoryItemRenderer r = getRenderer(index);
/* 2653 */     if (r == null)
/*      */       return; 
/* 2657 */     Collection markers = getRangeMarkers(index, layer);
/* 2658 */     ValueAxis axis = getRangeAxisForDataset(index);
/* 2659 */     if (markers != null && axis != null) {
/* 2660 */       Iterator iterator = markers.iterator();
/* 2661 */       while (iterator.hasNext()) {
/* 2662 */         Marker marker = iterator.next();
/* 2663 */         r.drawRangeMarker(g2, this, axis, marker, dataArea);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawRangeLine(Graphics2D g2, Rectangle2D dataArea, double value, Stroke stroke, Paint paint) {
/* 2683 */     double java2D = getRangeAxis().valueToJava2D(value, dataArea, getRangeAxisEdge());
/* 2686 */     Line2D line = null;
/* 2687 */     if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 2688 */       line = new Line2D.Double(java2D, dataArea.getMinY(), java2D, dataArea.getMaxY());
/* 2692 */     } else if (this.orientation == PlotOrientation.VERTICAL) {
/* 2693 */       line = new Line2D.Double(dataArea.getMinX(), java2D, dataArea.getMaxX(), java2D);
/*      */     } 
/* 2697 */     g2.setStroke(stroke);
/* 2698 */     g2.setPaint(paint);
/* 2699 */     g2.draw(line);
/*      */   }
/*      */   
/*      */   public Range getDataRange(ValueAxis axis) {
/* 2714 */     Range result = null;
/* 2715 */     List mappedDatasets = new ArrayList();
/* 2717 */     int rangeIndex = this.rangeAxes.indexOf(axis);
/* 2718 */     if (rangeIndex >= 0) {
/* 2719 */       mappedDatasets.addAll(datasetsMappedToRangeAxis(rangeIndex));
/* 2721 */     } else if (axis == getRangeAxis()) {
/* 2722 */       mappedDatasets.addAll(datasetsMappedToRangeAxis(0));
/*      */     } 
/* 2727 */     Iterator iterator = mappedDatasets.iterator();
/* 2728 */     while (iterator.hasNext()) {
/* 2729 */       CategoryDataset d = iterator.next();
/* 2730 */       CategoryItemRenderer r = getRendererForDataset(d);
/* 2731 */       if (r != null)
/* 2732 */         result = Range.combine(result, r.findRangeBounds(d)); 
/*      */     } 
/* 2735 */     return result;
/*      */   }
/*      */   
/*      */   private List datasetsMappedToRangeAxis(int index) {
/* 2748 */     List result = new ArrayList();
/* 2749 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 2750 */       Object dataset = this.datasets.get(i);
/* 2751 */       if (dataset != null) {
/* 2752 */         Integer m = (Integer)this.datasetToRangeAxisMap.get(i);
/* 2753 */         if (m == null) {
/* 2755 */           if (index == 0)
/* 2756 */             result.add(dataset); 
/* 2760 */         } else if (m.intValue() == index) {
/* 2761 */           result.add(dataset);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2766 */     return result;
/*      */   }
/*      */   
/*      */   public int getWeight() {
/* 2776 */     return this.weight;
/*      */   }
/*      */   
/*      */   public void setWeight(int weight) {
/* 2785 */     this.weight = weight;
/*      */   }
/*      */   
/*      */   public AxisSpace getFixedDomainAxisSpace() {
/* 2794 */     return this.fixedDomainAxisSpace;
/*      */   }
/*      */   
/*      */   public void setFixedDomainAxisSpace(AxisSpace space) {
/* 2803 */     this.fixedDomainAxisSpace = space;
/*      */   }
/*      */   
/*      */   public AxisSpace getFixedRangeAxisSpace() {
/* 2812 */     return this.fixedRangeAxisSpace;
/*      */   }
/*      */   
/*      */   public void setFixedRangeAxisSpace(AxisSpace space) {
/* 2821 */     this.fixedRangeAxisSpace = space;
/*      */   }
/*      */   
/*      */   public List getCategories() {
/* 2830 */     List result = null;
/* 2831 */     if (getDataset() != null)
/* 2832 */       result = Collections.unmodifiableList(getDataset().getColumnKeys()); 
/* 2834 */     return result;
/*      */   }
/*      */   
/*      */   public boolean getDrawSharedDomainAxis() {
/* 2844 */     return this.drawSharedDomainAxis;
/*      */   }
/*      */   
/*      */   public void setDrawSharedDomainAxis(boolean draw) {
/* 2854 */     this.drawSharedDomainAxis = draw;
/* 2855 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isDomainZoomable() {
/* 2864 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isRangeZoomable() {
/* 2873 */     return true;
/*      */   }
/*      */   
/*      */   public void zoomDomainAxes(double factor, PlotRenderingInfo state, Point2D source) {}
/*      */   
/*      */   public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source) {}
/*      */   
/*      */   public void zoomRangeAxes(double factor, PlotRenderingInfo state, Point2D source) {
/* 2912 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 2913 */       ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
/* 2914 */       if (rangeAxis != null)
/* 2915 */         rangeAxis.resizeRange(factor); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source) {
/* 2930 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 2931 */       ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
/* 2932 */       if (rangeAxis != null)
/* 2933 */         rangeAxis.zoomRange(lowerPercent, upperPercent); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getAnchorValue() {
/* 2944 */     return this.anchorValue;
/*      */   }
/*      */   
/*      */   public void setAnchorValue(double value) {
/* 2953 */     setAnchorValue(value, true);
/*      */   }
/*      */   
/*      */   public void setAnchorValue(double value, boolean notify) {
/* 2963 */     this.anchorValue = value;
/* 2964 */     if (notify)
/* 2965 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 2978 */     if (obj == this)
/* 2979 */       return true; 
/* 2982 */     if (!(obj instanceof CategoryPlot))
/* 2983 */       return false; 
/* 2985 */     if (!super.equals(obj))
/* 2986 */       return false; 
/* 2989 */     CategoryPlot that = (CategoryPlot)obj;
/* 2991 */     if (this.orientation != that.orientation)
/* 2992 */       return false; 
/* 2994 */     if (!ObjectUtilities.equal(this.axisOffset, that.axisOffset))
/* 2995 */       return false; 
/* 2997 */     if (!this.domainAxes.equals(that.domainAxes))
/* 2998 */       return false; 
/* 3000 */     if (!this.domainAxisLocations.equals(that.domainAxisLocations))
/* 3001 */       return false; 
/* 3003 */     if (this.drawSharedDomainAxis != that.drawSharedDomainAxis)
/* 3004 */       return false; 
/* 3006 */     if (!this.rangeAxes.equals(that.rangeAxes))
/* 3007 */       return false; 
/* 3009 */     if (!this.rangeAxisLocations.equals(that.rangeAxisLocations))
/* 3010 */       return false; 
/* 3012 */     if (!ObjectUtilities.equal(this.datasetToDomainAxisMap, that.datasetToDomainAxisMap))
/* 3015 */       return false; 
/* 3017 */     if (!ObjectUtilities.equal(this.datasetToRangeAxisMap, that.datasetToRangeAxisMap))
/* 3020 */       return false; 
/* 3022 */     if (!ObjectUtilities.equal(this.renderers, that.renderers))
/* 3023 */       return false; 
/* 3025 */     if (this.renderingOrder != that.renderingOrder)
/* 3026 */       return false; 
/* 3028 */     if (this.columnRenderingOrder != that.columnRenderingOrder)
/* 3029 */       return false; 
/* 3031 */     if (this.rowRenderingOrder != that.rowRenderingOrder)
/* 3032 */       return false; 
/* 3034 */     if (this.domainGridlinesVisible != that.domainGridlinesVisible)
/* 3035 */       return false; 
/* 3037 */     if (this.domainGridlinePosition != that.domainGridlinePosition)
/* 3038 */       return false; 
/* 3040 */     if (!ObjectUtilities.equal(this.domainGridlineStroke, that.domainGridlineStroke))
/* 3043 */       return false; 
/* 3045 */     if (!PaintUtilities.equal(this.domainGridlinePaint, that.domainGridlinePaint))
/* 3048 */       return false; 
/* 3050 */     if (this.rangeGridlinesVisible != that.rangeGridlinesVisible)
/* 3051 */       return false; 
/* 3053 */     if (!ObjectUtilities.equal(this.rangeGridlineStroke, that.rangeGridlineStroke))
/* 3056 */       return false; 
/* 3058 */     if (!PaintUtilities.equal(this.rangeGridlinePaint, that.rangeGridlinePaint))
/* 3061 */       return false; 
/* 3063 */     if (this.anchorValue != that.anchorValue)
/* 3064 */       return false; 
/* 3066 */     if (this.rangeCrosshairVisible != that.rangeCrosshairVisible)
/* 3067 */       return false; 
/* 3069 */     if (this.rangeCrosshairValue != that.rangeCrosshairValue)
/* 3070 */       return false; 
/* 3072 */     if (!ObjectUtilities.equal(this.rangeCrosshairStroke, that.rangeCrosshairStroke))
/* 3075 */       return false; 
/* 3077 */     if (!PaintUtilities.equal(this.rangeCrosshairPaint, that.rangeCrosshairPaint))
/* 3080 */       return false; 
/* 3082 */     if (this.rangeCrosshairLockedOnData != that.rangeCrosshairLockedOnData)
/* 3085 */       return false; 
/* 3087 */     if (!ObjectUtilities.equal(this.foregroundRangeMarkers, that.foregroundRangeMarkers))
/* 3090 */       return false; 
/* 3092 */     if (!ObjectUtilities.equal(this.backgroundRangeMarkers, that.backgroundRangeMarkers))
/* 3095 */       return false; 
/* 3097 */     if (!ObjectUtilities.equal(this.annotations, that.annotations))
/* 3098 */       return false; 
/* 3100 */     if (this.weight != that.weight)
/* 3101 */       return false; 
/* 3103 */     if (!ObjectUtilities.equal(this.fixedDomainAxisSpace, that.fixedDomainAxisSpace))
/* 3106 */       return false; 
/* 3108 */     if (!ObjectUtilities.equal(this.fixedRangeAxisSpace, that.fixedRangeAxisSpace))
/* 3111 */       return false; 
/* 3114 */     return true;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 3127 */     CategoryPlot clone = (CategoryPlot)super.clone();
/* 3129 */     clone.domainAxes = new ObjectList();
/*      */     int i;
/* 3130 */     for (i = 0; i < this.domainAxes.size(); i++) {
/* 3131 */       CategoryAxis xAxis = (CategoryAxis)this.domainAxes.get(i);
/* 3132 */       if (xAxis != null) {
/* 3133 */         CategoryAxis clonedAxis = (CategoryAxis)xAxis.clone();
/* 3134 */         clone.setDomainAxis(i, clonedAxis);
/*      */       } 
/*      */     } 
/* 3137 */     clone.domainAxisLocations = (ObjectList)this.domainAxisLocations.clone();
/* 3140 */     clone.rangeAxes = new ObjectList();
/* 3141 */     for (i = 0; i < this.rangeAxes.size(); i++) {
/* 3142 */       ValueAxis yAxis = (ValueAxis)this.rangeAxes.get(i);
/* 3143 */       if (yAxis != null) {
/* 3144 */         ValueAxis clonedAxis = (ValueAxis)yAxis.clone();
/* 3145 */         clone.setRangeAxis(i, clonedAxis);
/*      */       } 
/*      */     } 
/* 3148 */     clone.rangeAxisLocations = (ObjectList)this.rangeAxisLocations.clone();
/* 3150 */     clone.datasets = (ObjectList)this.datasets.clone();
/* 3151 */     for (i = 0; i < clone.datasets.size(); i++) {
/* 3152 */       CategoryDataset dataset = clone.getDataset(i);
/* 3153 */       if (dataset != null)
/* 3154 */         dataset.addChangeListener(clone); 
/*      */     } 
/* 3157 */     clone.datasetToDomainAxisMap = (ObjectList)this.datasetToDomainAxisMap.clone();
/* 3159 */     clone.datasetToRangeAxisMap = (ObjectList)this.datasetToRangeAxisMap.clone();
/* 3161 */     clone.renderers = (ObjectList)this.renderers.clone();
/* 3162 */     if (this.fixedDomainAxisSpace != null)
/* 3163 */       clone.fixedDomainAxisSpace = (AxisSpace)ObjectUtilities.clone(this.fixedDomainAxisSpace); 
/* 3167 */     if (this.fixedRangeAxisSpace != null)
/* 3168 */       clone.fixedRangeAxisSpace = (AxisSpace)ObjectUtilities.clone(this.fixedRangeAxisSpace); 
/* 3173 */     return clone;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 3185 */     stream.defaultWriteObject();
/* 3186 */     SerialUtilities.writeStroke(this.domainGridlineStroke, stream);
/* 3187 */     SerialUtilities.writePaint(this.domainGridlinePaint, stream);
/* 3188 */     SerialUtilities.writeStroke(this.rangeGridlineStroke, stream);
/* 3189 */     SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
/* 3190 */     SerialUtilities.writeStroke(this.rangeCrosshairStroke, stream);
/* 3191 */     SerialUtilities.writePaint(this.rangeCrosshairPaint, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 3205 */     stream.defaultReadObject();
/* 3206 */     this.domainGridlineStroke = SerialUtilities.readStroke(stream);
/* 3207 */     this.domainGridlinePaint = SerialUtilities.readPaint(stream);
/* 3208 */     this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
/* 3209 */     this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
/* 3210 */     this.rangeCrosshairStroke = SerialUtilities.readStroke(stream);
/* 3211 */     this.rangeCrosshairPaint = SerialUtilities.readPaint(stream);
/*      */     int i;
/* 3213 */     for (i = 0; i < this.domainAxes.size(); i++) {
/* 3214 */       CategoryAxis xAxis = (CategoryAxis)this.domainAxes.get(i);
/* 3215 */       if (xAxis != null) {
/* 3216 */         xAxis.setPlot(this);
/* 3217 */         xAxis.addChangeListener(this);
/*      */       } 
/*      */     } 
/* 3220 */     for (i = 0; i < this.rangeAxes.size(); i++) {
/* 3221 */       ValueAxis yAxis = (ValueAxis)this.rangeAxes.get(i);
/* 3222 */       if (yAxis != null) {
/* 3223 */         yAxis.setPlot(this);
/* 3224 */         yAxis.addChangeListener(this);
/*      */       } 
/*      */     } 
/* 3227 */     int datasetCount = this.datasets.size();
/* 3228 */     for (int j = 0; j < datasetCount; j++) {
/* 3229 */       Dataset dataset = (Dataset)this.datasets.get(j);
/* 3230 */       if (dataset != null)
/* 3231 */         dataset.addChangeListener(this); 
/*      */     } 
/* 3234 */     int rendererCount = this.renderers.size();
/* 3235 */     for (int k = 0; k < rendererCount; k++) {
/* 3236 */       CategoryItemRenderer renderer = (CategoryItemRenderer)this.renderers.get(k);
/* 3238 */       if (renderer != null)
/* 3239 */         renderer.addChangeListener(this); 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\CategoryPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */