/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
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
/*      */ import java.util.TreeMap;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.annotations.XYAnnotation;
/*      */ import org.jfree.chart.axis.Axis;
/*      */ import org.jfree.chart.axis.AxisCollection;
/*      */ import org.jfree.chart.axis.AxisLocation;
/*      */ import org.jfree.chart.axis.AxisSpace;
/*      */ import org.jfree.chart.axis.AxisState;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.axis.ValueTick;
/*      */ import org.jfree.chart.event.ChartChangeEventType;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeListener;
/*      */ import org.jfree.chart.renderer.xy.XYItemRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYItemRendererState;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.general.Dataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.Layer;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectList;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ 
/*      */ public class XYPlot extends Plot implements ValueAxisPlot, Zoomable, RendererChangeListener, Cloneable, PublicCloneable, Serializable {
/*      */   private static final long serialVersionUID = 7044148245716569264L;
/*      */   
/*  250 */   public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5F, 0, 2, 0.0F, new float[] { 2.0F, 2.0F }, 0.0F);
/*      */   
/*  260 */   public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
/*      */   
/*      */   public static final boolean DEFAULT_CROSSHAIR_VISIBLE = false;
/*      */   
/*  266 */   public static final Stroke DEFAULT_CROSSHAIR_STROKE = DEFAULT_GRIDLINE_STROKE;
/*      */   
/*  270 */   public static final Paint DEFAULT_CROSSHAIR_PAINT = Color.blue;
/*      */   
/*  273 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */   private PlotOrientation orientation;
/*      */   
/*      */   private RectangleInsets axisOffset;
/*      */   
/*      */   private ObjectList domainAxes;
/*      */   
/*      */   private ObjectList domainAxisLocations;
/*      */   
/*      */   private ObjectList rangeAxes;
/*      */   
/*      */   private ObjectList rangeAxisLocations;
/*      */   
/*      */   private ObjectList datasets;
/*      */   
/*      */   private ObjectList renderers;
/*      */   
/*      */   private Map datasetToDomainAxisMap;
/*      */   
/*      */   private Map datasetToRangeAxisMap;
/*      */   
/*  315 */   private transient Point2D quadrantOrigin = new Point2D.Double(0.0D, 0.0D);
/*      */   
/*  318 */   private transient Paint[] quadrantPaint = new Paint[] { null, null, null, null };
/*      */   
/*      */   private boolean domainGridlinesVisible;
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
/*      */   private boolean rangeZeroBaselineVisible;
/*      */   
/*      */   private transient Stroke rangeZeroBaselineStroke;
/*      */   
/*      */   private transient Paint rangeZeroBaselinePaint;
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
/*      */   private transient Paint domainTickBandPaint;
/*      */   
/*      */   private transient Paint rangeTickBandPaint;
/*      */   
/*      */   private AxisSpace fixedDomainAxisSpace;
/*      */   
/*      */   private AxisSpace fixedRangeAxisSpace;
/*      */   
/*  422 */   private DatasetRenderingOrder datasetRenderingOrder = DatasetRenderingOrder.REVERSE;
/*      */   
/*  429 */   private SeriesRenderingOrder seriesRenderingOrder = SeriesRenderingOrder.REVERSE;
/*      */   
/*      */   private int weight;
/*      */   
/*      */   private LegendItemCollection fixedLegendItems;
/*      */   
/*      */   public XYPlot() {
/*  448 */     this((XYDataset)null, (ValueAxis)null, (ValueAxis)null, (XYItemRenderer)null);
/*      */   }
/*      */   
/*      */   public XYPlot(XYDataset dataset, ValueAxis domainAxis, ValueAxis rangeAxis, XYItemRenderer renderer) {
/*  466 */     this.orientation = PlotOrientation.VERTICAL;
/*  467 */     this.weight = 1;
/*  468 */     this.axisOffset = RectangleInsets.ZERO_INSETS;
/*  471 */     this.domainAxes = new ObjectList();
/*  472 */     this.domainAxisLocations = new ObjectList();
/*  473 */     this.foregroundDomainMarkers = new HashMap();
/*  474 */     this.backgroundDomainMarkers = new HashMap();
/*  476 */     this.rangeAxes = new ObjectList();
/*  477 */     this.rangeAxisLocations = new ObjectList();
/*  478 */     this.foregroundRangeMarkers = new HashMap();
/*  479 */     this.backgroundRangeMarkers = new HashMap();
/*  481 */     this.datasets = new ObjectList();
/*  482 */     this.renderers = new ObjectList();
/*  484 */     this.datasetToDomainAxisMap = new TreeMap();
/*  485 */     this.datasetToRangeAxisMap = new TreeMap();
/*  487 */     this.datasets.set(0, dataset);
/*  488 */     if (dataset != null)
/*  489 */       dataset.addChangeListener(this); 
/*  492 */     this.renderers.set(0, renderer);
/*  493 */     if (renderer != null) {
/*  494 */       renderer.setPlot(this);
/*  495 */       renderer.addChangeListener(this);
/*      */     } 
/*  498 */     this.domainAxes.set(0, domainAxis);
/*  499 */     mapDatasetToDomainAxis(0, 0);
/*  500 */     if (domainAxis != null) {
/*  501 */       domainAxis.setPlot(this);
/*  502 */       domainAxis.addChangeListener(this);
/*      */     } 
/*  504 */     this.domainAxisLocations.set(0, AxisLocation.BOTTOM_OR_LEFT);
/*  506 */     this.rangeAxes.set(0, rangeAxis);
/*  507 */     mapDatasetToRangeAxis(0, 0);
/*  508 */     if (rangeAxis != null) {
/*  509 */       rangeAxis.setPlot(this);
/*  510 */       rangeAxis.addChangeListener(this);
/*      */     } 
/*  512 */     this.rangeAxisLocations.set(0, AxisLocation.BOTTOM_OR_LEFT);
/*  514 */     configureDomainAxes();
/*  515 */     configureRangeAxes();
/*  517 */     this.domainGridlinesVisible = true;
/*  518 */     this.domainGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  519 */     this.domainGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*  521 */     this.rangeGridlinesVisible = true;
/*  522 */     this.rangeGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  523 */     this.rangeGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*  525 */     this.rangeZeroBaselineVisible = false;
/*  526 */     this.rangeZeroBaselinePaint = Color.black;
/*  527 */     this.rangeZeroBaselineStroke = new BasicStroke(0.5F);
/*  529 */     this.domainCrosshairVisible = false;
/*  530 */     this.domainCrosshairValue = 0.0D;
/*  531 */     this.domainCrosshairStroke = DEFAULT_CROSSHAIR_STROKE;
/*  532 */     this.domainCrosshairPaint = DEFAULT_CROSSHAIR_PAINT;
/*  534 */     this.rangeCrosshairVisible = false;
/*  535 */     this.rangeCrosshairValue = 0.0D;
/*  536 */     this.rangeCrosshairStroke = DEFAULT_CROSSHAIR_STROKE;
/*  537 */     this.rangeCrosshairPaint = DEFAULT_CROSSHAIR_PAINT;
/*  539 */     this.annotations = new ArrayList();
/*      */   }
/*      */   
/*      */   public String getPlotType() {
/*  549 */     return localizationResources.getString("XY_Plot");
/*      */   }
/*      */   
/*      */   public PlotOrientation getOrientation() {
/*  558 */     return this.orientation;
/*      */   }
/*      */   
/*      */   public void setOrientation(PlotOrientation orientation) {
/*  567 */     if (orientation == null)
/*  568 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/*  570 */     if (orientation != this.orientation) {
/*  571 */       this.orientation = orientation;
/*  572 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public RectangleInsets getAxisOffset() {
/*  582 */     return this.axisOffset;
/*      */   }
/*      */   
/*      */   public void setAxisOffset(RectangleInsets offset) {
/*  591 */     if (offset == null)
/*  592 */       throw new IllegalArgumentException("Null 'offset' argument."); 
/*  594 */     this.axisOffset = offset;
/*  595 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public ValueAxis getDomainAxis() {
/*  606 */     return getDomainAxis(0);
/*      */   }
/*      */   
/*      */   public ValueAxis getDomainAxis(int index) {
/*  617 */     ValueAxis result = null;
/*  618 */     if (index < this.domainAxes.size())
/*  619 */       result = (ValueAxis)this.domainAxes.get(index); 
/*  621 */     if (result == null) {
/*  622 */       Plot parent = getParent();
/*  623 */       if (parent instanceof XYPlot) {
/*  624 */         XYPlot xy = (XYPlot)parent;
/*  625 */         result = xy.getDomainAxis(index);
/*      */       } 
/*      */     } 
/*  628 */     return result;
/*      */   }
/*      */   
/*      */   public void setDomainAxis(ValueAxis axis) {
/*  638 */     setDomainAxis(0, axis);
/*      */   }
/*      */   
/*      */   public void setDomainAxis(int index, ValueAxis axis) {
/*  649 */     setDomainAxis(index, axis, true);
/*      */   }
/*      */   
/*      */   public void setDomainAxis(int index, ValueAxis axis, boolean notify) {
/*  661 */     ValueAxis existing = getDomainAxis(index);
/*  662 */     if (existing != null)
/*  663 */       existing.removeChangeListener(this); 
/*  665 */     if (axis != null)
/*  666 */       axis.setPlot(this); 
/*  668 */     this.domainAxes.set(index, axis);
/*  669 */     if (axis != null) {
/*  670 */       axis.configure();
/*  671 */       axis.addChangeListener(this);
/*      */     } 
/*  673 */     if (notify)
/*  674 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setDomainAxes(ValueAxis[] axes) {
/*  685 */     for (int i = 0; i < axes.length; i++)
/*  686 */       setDomainAxis(i, axes[i], false); 
/*  688 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public AxisLocation getDomainAxisLocation() {
/*  697 */     return (AxisLocation)this.domainAxisLocations.get(0);
/*      */   }
/*      */   
/*      */   public void setDomainAxisLocation(AxisLocation location) {
/*  708 */     setDomainAxisLocation(location, true);
/*      */   }
/*      */   
/*      */   public void setDomainAxisLocation(AxisLocation location, boolean notify) {
/*  719 */     if (location == null)
/*  720 */       throw new IllegalArgumentException("Null 'location' argument."); 
/*  722 */     this.domainAxisLocations.set(0, location);
/*  723 */     if (notify)
/*  724 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public RectangleEdge getDomainAxisEdge() {
/*  735 */     return Plot.resolveDomainAxisLocation(getDomainAxisLocation(), this.orientation);
/*      */   }
/*      */   
/*      */   public int getDomainAxisCount() {
/*  746 */     return this.domainAxes.size();
/*      */   }
/*      */   
/*      */   public void clearDomainAxes() {
/*  754 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/*  755 */       ValueAxis axis = (ValueAxis)this.domainAxes.get(i);
/*  756 */       if (axis != null)
/*  757 */         axis.removeChangeListener(this); 
/*      */     } 
/*  760 */     this.domainAxes.clear();
/*  761 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void configureDomainAxes() {
/*  768 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/*  769 */       ValueAxis axis = (ValueAxis)this.domainAxes.get(i);
/*  770 */       if (axis != null)
/*  771 */         axis.configure(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public AxisLocation getDomainAxisLocation(int index) {
/*  786 */     AxisLocation result = null;
/*  787 */     if (index < this.domainAxisLocations.size())
/*  788 */       result = (AxisLocation)this.domainAxisLocations.get(index); 
/*  790 */     if (result == null)
/*  791 */       result = AxisLocation.getOpposite(getDomainAxisLocation()); 
/*  793 */     return result;
/*      */   }
/*      */   
/*      */   public void setDomainAxisLocation(int index, AxisLocation location) {
/*  804 */     this.domainAxisLocations.set(index, location);
/*  805 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public RectangleEdge getDomainAxisEdge(int index) {
/*  816 */     AxisLocation location = getDomainAxisLocation(index);
/*  817 */     RectangleEdge result = Plot.resolveDomainAxisLocation(location, this.orientation);
/*  820 */     if (result == null)
/*  821 */       result = RectangleEdge.opposite(getDomainAxisEdge()); 
/*  823 */     return result;
/*      */   }
/*      */   
/*      */   public ValueAxis getRangeAxis() {
/*  834 */     return getRangeAxis(0);
/*      */   }
/*      */   
/*      */   public void setRangeAxis(ValueAxis axis) {
/*  846 */     if (axis != null)
/*  847 */       axis.setPlot(this); 
/*  851 */     ValueAxis existing = getRangeAxis();
/*  852 */     if (existing != null)
/*  853 */       existing.removeChangeListener(this); 
/*  856 */     this.rangeAxes.set(0, axis);
/*  857 */     if (axis != null) {
/*  858 */       axis.configure();
/*  859 */       axis.addChangeListener(this);
/*      */     } 
/*  861 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public AxisLocation getRangeAxisLocation() {
/*  871 */     return (AxisLocation)this.rangeAxisLocations.get(0);
/*      */   }
/*      */   
/*      */   public void setRangeAxisLocation(AxisLocation location) {
/*  882 */     setRangeAxisLocation(location, true);
/*      */   }
/*      */   
/*      */   public void setRangeAxisLocation(AxisLocation location, boolean notify) {
/*  893 */     if (location == null)
/*  894 */       throw new IllegalArgumentException("Null 'location' argument."); 
/*  896 */     this.rangeAxisLocations.set(0, location);
/*  897 */     if (notify)
/*  898 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public RectangleEdge getRangeAxisEdge() {
/*  909 */     return Plot.resolveRangeAxisLocation(getRangeAxisLocation(), this.orientation);
/*      */   }
/*      */   
/*      */   public ValueAxis getRangeAxis(int index) {
/*  922 */     ValueAxis result = null;
/*  923 */     if (index < this.rangeAxes.size())
/*  924 */       result = (ValueAxis)this.rangeAxes.get(index); 
/*  926 */     if (result == null) {
/*  927 */       Plot parent = getParent();
/*  928 */       if (parent instanceof XYPlot) {
/*  929 */         XYPlot xy = (XYPlot)parent;
/*  930 */         result = xy.getRangeAxis(index);
/*      */       } 
/*      */     } 
/*  933 */     return result;
/*      */   }
/*      */   
/*      */   public void setRangeAxis(int index, ValueAxis axis) {
/*  944 */     setRangeAxis(index, axis, true);
/*      */   }
/*      */   
/*      */   public void setRangeAxis(int index, ValueAxis axis, boolean notify) {
/*  955 */     ValueAxis existing = getRangeAxis(index);
/*  956 */     if (existing != null)
/*  957 */       existing.removeChangeListener(this); 
/*  959 */     if (axis != null)
/*  960 */       axis.setPlot(this); 
/*  962 */     this.rangeAxes.set(index, axis);
/*  963 */     if (axis != null) {
/*  964 */       axis.configure();
/*  965 */       axis.addChangeListener(this);
/*      */     } 
/*  967 */     if (notify)
/*  968 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setRangeAxes(ValueAxis[] axes) {
/*  979 */     for (int i = 0; i < axes.length; i++)
/*  980 */       setRangeAxis(i, axes[i], false); 
/*  982 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public int getRangeAxisCount() {
/*  991 */     return this.rangeAxes.size();
/*      */   }
/*      */   
/*      */   public void clearRangeAxes() {
/*  999 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 1000 */       ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
/* 1001 */       if (axis != null)
/* 1002 */         axis.removeChangeListener(this); 
/*      */     } 
/* 1005 */     this.rangeAxes.clear();
/* 1006 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void configureRangeAxes() {
/* 1013 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 1014 */       ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
/* 1015 */       if (axis != null)
/* 1016 */         axis.configure(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public AxisLocation getRangeAxisLocation(int index) {
/* 1031 */     AxisLocation result = null;
/* 1032 */     if (index < this.rangeAxisLocations.size())
/* 1033 */       result = (AxisLocation)this.rangeAxisLocations.get(index); 
/* 1035 */     if (result == null)
/* 1036 */       result = AxisLocation.getOpposite(getRangeAxisLocation()); 
/* 1038 */     return result;
/*      */   }
/*      */   
/*      */   public void setRangeAxisLocation(int index, AxisLocation location) {
/* 1049 */     this.rangeAxisLocations.set(index, location);
/* 1050 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public RectangleEdge getRangeAxisEdge(int index) {
/* 1061 */     AxisLocation location = getRangeAxisLocation(index);
/* 1062 */     RectangleEdge result = Plot.resolveRangeAxisLocation(location, this.orientation);
/* 1065 */     if (result == null)
/* 1066 */       result = RectangleEdge.opposite(getRangeAxisEdge()); 
/* 1068 */     return result;
/*      */   }
/*      */   
/*      */   public XYDataset getDataset() {
/* 1077 */     return getDataset(0);
/*      */   }
/*      */   
/*      */   public XYDataset getDataset(int index) {
/* 1088 */     XYDataset result = null;
/* 1089 */     if (this.datasets.size() > index)
/* 1090 */       result = (XYDataset)this.datasets.get(index); 
/* 1092 */     return result;
/*      */   }
/*      */   
/*      */   public void setDataset(XYDataset dataset) {
/* 1102 */     setDataset(0, dataset);
/*      */   }
/*      */   
/*      */   public void setDataset(int index, XYDataset dataset) {
/* 1112 */     XYDataset existing = getDataset(index);
/* 1113 */     if (existing != null)
/* 1114 */       existing.removeChangeListener(this); 
/* 1116 */     this.datasets.set(index, dataset);
/* 1117 */     if (dataset != null)
/* 1118 */       dataset.addChangeListener(this); 
/* 1122 */     DatasetChangeEvent event = new DatasetChangeEvent(this, (Dataset)dataset);
/* 1123 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */   public int getDatasetCount() {
/* 1132 */     return this.datasets.size();
/*      */   }
/*      */   
/*      */   public int indexOf(XYDataset dataset) {
/* 1144 */     int result = -1;
/* 1145 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 1146 */       if (dataset == this.datasets.get(i)) {
/* 1147 */         result = i;
/*      */         break;
/*      */       } 
/*      */     } 
/* 1151 */     return result;
/*      */   }
/*      */   
/*      */   public void mapDatasetToDomainAxis(int index, int axisIndex) {
/* 1162 */     this.datasetToDomainAxisMap.put(new Integer(index), new Integer(axisIndex));
/* 1166 */     datasetChanged(new DatasetChangeEvent(this, (Dataset)getDataset(index)));
/*      */   }
/*      */   
/*      */   public void mapDatasetToRangeAxis(int index, int axisIndex) {
/* 1177 */     this.datasetToRangeAxisMap.put(new Integer(index), new Integer(axisIndex));
/* 1181 */     datasetChanged(new DatasetChangeEvent(this, (Dataset)getDataset(index)));
/*      */   }
/*      */   
/*      */   public XYItemRenderer getRenderer() {
/* 1190 */     return getRenderer(0);
/*      */   }
/*      */   
/*      */   public XYItemRenderer getRenderer(int index) {
/* 1201 */     XYItemRenderer result = null;
/* 1202 */     if (this.renderers.size() > index)
/* 1203 */       result = (XYItemRenderer)this.renderers.get(index); 
/* 1205 */     return result;
/*      */   }
/*      */   
/*      */   public void setRenderer(XYItemRenderer renderer) {
/* 1217 */     setRenderer(0, renderer);
/*      */   }
/*      */   
/*      */   public void setRenderer(int index, XYItemRenderer renderer) {
/* 1228 */     setRenderer(index, renderer, true);
/*      */   }
/*      */   
/*      */   public void setRenderer(int index, XYItemRenderer renderer, boolean notify) {
/* 1241 */     XYItemRenderer existing = getRenderer(index);
/* 1242 */     if (existing != null)
/* 1243 */       existing.removeChangeListener(this); 
/* 1245 */     this.renderers.set(index, renderer);
/* 1246 */     if (renderer != null) {
/* 1247 */       renderer.setPlot(this);
/* 1248 */       renderer.addChangeListener(this);
/*      */     } 
/* 1250 */     configureDomainAxes();
/* 1251 */     configureRangeAxes();
/* 1252 */     if (notify)
/* 1253 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setRenderers(XYItemRenderer[] renderers) {
/* 1264 */     for (int i = 0; i < renderers.length; i++)
/* 1265 */       setRenderer(i, renderers[i], false); 
/* 1267 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public DatasetRenderingOrder getDatasetRenderingOrder() {
/* 1276 */     return this.datasetRenderingOrder;
/*      */   }
/*      */   
/*      */   public void setDatasetRenderingOrder(DatasetRenderingOrder order) {
/* 1288 */     if (order == null)
/* 1289 */       throw new IllegalArgumentException("Null 'order' argument."); 
/* 1291 */     this.datasetRenderingOrder = order;
/* 1292 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public SeriesRenderingOrder getSeriesRenderingOrder() {
/* 1301 */     return this.seriesRenderingOrder;
/*      */   }
/*      */   
/*      */   public void setSeriesRenderingOrder(SeriesRenderingOrder order) {
/* 1313 */     if (order == null)
/* 1314 */       throw new IllegalArgumentException("Null 'order' argument."); 
/* 1316 */     this.seriesRenderingOrder = order;
/* 1317 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public int getIndexOf(XYItemRenderer renderer) {
/* 1329 */     return this.renderers.indexOf(renderer);
/*      */   }
/*      */   
/*      */   public XYItemRenderer getRendererForDataset(XYDataset dataset) {
/* 1342 */     XYItemRenderer result = null;
/* 1343 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 1344 */       if (this.datasets.get(i) == dataset) {
/* 1345 */         result = (XYItemRenderer)this.renderers.get(i);
/* 1346 */         if (result == null)
/* 1347 */           result = getRenderer(); 
/*      */         break;
/*      */       } 
/*      */     } 
/* 1352 */     return result;
/*      */   }
/*      */   
/*      */   public int getWeight() {
/* 1362 */     return this.weight;
/*      */   }
/*      */   
/*      */   public void setWeight(int weight) {
/* 1371 */     this.weight = weight;
/*      */   }
/*      */   
/*      */   public boolean isDomainGridlinesVisible() {
/* 1381 */     return this.domainGridlinesVisible;
/*      */   }
/*      */   
/*      */   public void setDomainGridlinesVisible(boolean visible) {
/* 1394 */     if (this.domainGridlinesVisible != visible) {
/* 1395 */       this.domainGridlinesVisible = visible;
/* 1396 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Stroke getDomainGridlineStroke() {
/* 1407 */     return this.domainGridlineStroke;
/*      */   }
/*      */   
/*      */   public void setDomainGridlineStroke(Stroke stroke) {
/* 1418 */     this.domainGridlineStroke = stroke;
/* 1419 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getDomainGridlinePaint() {
/* 1429 */     return this.domainGridlinePaint;
/*      */   }
/*      */   
/*      */   public void setDomainGridlinePaint(Paint paint) {
/* 1440 */     this.domainGridlinePaint = paint;
/* 1441 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isRangeGridlinesVisible() {
/* 1451 */     return this.rangeGridlinesVisible;
/*      */   }
/*      */   
/*      */   public void setRangeGridlinesVisible(boolean visible) {
/* 1464 */     if (this.rangeGridlinesVisible != visible) {
/* 1465 */       this.rangeGridlinesVisible = visible;
/* 1466 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Stroke getRangeGridlineStroke() {
/* 1477 */     return this.rangeGridlineStroke;
/*      */   }
/*      */   
/*      */   public void setRangeGridlineStroke(Stroke stroke) {
/* 1487 */     if (stroke == null)
/* 1488 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/* 1490 */     this.rangeGridlineStroke = stroke;
/* 1491 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getRangeGridlinePaint() {
/* 1501 */     return this.rangeGridlinePaint;
/*      */   }
/*      */   
/*      */   public void setRangeGridlinePaint(Paint paint) {
/* 1511 */     this.rangeGridlinePaint = paint;
/* 1512 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isRangeZeroBaselineVisible() {
/* 1522 */     return this.rangeZeroBaselineVisible;
/*      */   }
/*      */   
/*      */   public void setRangeZeroBaselineVisible(boolean visible) {
/* 1533 */     this.rangeZeroBaselineVisible = visible;
/* 1534 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getRangeZeroBaselineStroke() {
/* 1543 */     return this.rangeZeroBaselineStroke;
/*      */   }
/*      */   
/*      */   public void setRangeZeroBaselineStroke(Stroke stroke) {
/* 1553 */     if (stroke == null)
/* 1554 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/* 1556 */     this.rangeZeroBaselineStroke = stroke;
/* 1557 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getRangeZeroBaselinePaint() {
/* 1567 */     return this.rangeZeroBaselinePaint;
/*      */   }
/*      */   
/*      */   public void setRangeZeroBaselinePaint(Paint paint) {
/* 1577 */     this.rangeZeroBaselinePaint = paint;
/* 1578 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getDomainTickBandPaint() {
/* 1588 */     return this.domainTickBandPaint;
/*      */   }
/*      */   
/*      */   public void setDomainTickBandPaint(Paint paint) {
/* 1597 */     this.domainTickBandPaint = paint;
/* 1598 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getRangeTickBandPaint() {
/* 1608 */     return this.rangeTickBandPaint;
/*      */   }
/*      */   
/*      */   public void setRangeTickBandPaint(Paint paint) {
/* 1617 */     this.rangeTickBandPaint = paint;
/* 1618 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Point2D getQuadrantOrigin() {
/* 1628 */     return this.quadrantOrigin;
/*      */   }
/*      */   
/*      */   public void setQuadrantOrigin(Point2D origin) {
/* 1638 */     if (origin == null)
/* 1639 */       throw new IllegalArgumentException("Null 'origin' argument."); 
/* 1641 */     this.quadrantOrigin = origin;
/* 1642 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getQuadrantPaint(int index) {
/* 1653 */     if (index < 0 || index > 3)
/* 1654 */       throw new IllegalArgumentException("The index should be in the range 0 to 3."); 
/* 1658 */     return this.quadrantPaint[index];
/*      */   }
/*      */   
/*      */   public void setQuadrantPaint(int index, Paint paint) {
/* 1669 */     if (index < 0 || index > 3)
/* 1670 */       throw new IllegalArgumentException("The index should be in the range 0 to 3."); 
/* 1674 */     this.quadrantPaint[index] = paint;
/* 1675 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void addDomainMarker(Marker marker) {
/* 1689 */     addDomainMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */   public void addDomainMarker(Marker marker, Layer layer) {
/* 1703 */     addDomainMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */   public void clearDomainMarkers() {
/* 1711 */     if (this.foregroundDomainMarkers != null)
/* 1712 */       this.foregroundDomainMarkers.clear(); 
/* 1714 */     if (this.backgroundDomainMarkers != null)
/* 1715 */       this.backgroundDomainMarkers.clear(); 
/* 1717 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void clearDomainMarkers(int index) {
/* 1727 */     Integer key = new Integer(index);
/* 1728 */     if (this.backgroundDomainMarkers != null) {
/* 1729 */       Collection markers = (Collection)this.backgroundDomainMarkers.get(key);
/* 1731 */       if (markers != null)
/* 1732 */         markers.clear(); 
/*      */     } 
/* 1735 */     if (this.foregroundRangeMarkers != null) {
/* 1736 */       Collection markers = (Collection)this.foregroundDomainMarkers.get(key);
/* 1738 */       if (markers != null)
/* 1739 */         markers.clear(); 
/*      */     } 
/* 1742 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void addDomainMarker(int index, Marker marker, Layer layer) {
/* 1759 */     if (layer == Layer.FOREGROUND) {
/* 1760 */       Collection markers = (Collection)this.foregroundDomainMarkers.get(new Integer(index));
/* 1763 */       if (markers == null) {
/* 1764 */         markers = new ArrayList();
/* 1765 */         this.foregroundDomainMarkers.put(new Integer(index), markers);
/*      */       } 
/* 1767 */       markers.add(marker);
/* 1769 */     } else if (layer == Layer.BACKGROUND) {
/* 1770 */       Collection markers = (Collection)this.backgroundDomainMarkers.get(new Integer(index));
/* 1773 */       if (markers == null) {
/* 1774 */         markers = new ArrayList();
/* 1775 */         this.backgroundDomainMarkers.put(new Integer(index), markers);
/*      */       } 
/* 1777 */       markers.add(marker);
/*      */     } 
/* 1779 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void addRangeMarker(Marker marker) {
/* 1792 */     addRangeMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */   public void addRangeMarker(Marker marker, Layer layer) {
/* 1806 */     addRangeMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */   public void clearRangeMarkers() {
/* 1814 */     if (this.foregroundRangeMarkers != null)
/* 1815 */       this.foregroundRangeMarkers.clear(); 
/* 1817 */     if (this.backgroundRangeMarkers != null)
/* 1818 */       this.backgroundRangeMarkers.clear(); 
/* 1820 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void addRangeMarker(int index, Marker marker, Layer layer) {
/* 1836 */     if (layer == Layer.FOREGROUND) {
/* 1837 */       Collection markers = (Collection)this.foregroundRangeMarkers.get(new Integer(index));
/* 1840 */       if (markers == null) {
/* 1841 */         markers = new ArrayList();
/* 1842 */         this.foregroundRangeMarkers.put(new Integer(index), markers);
/*      */       } 
/* 1844 */       markers.add(marker);
/* 1846 */     } else if (layer == Layer.BACKGROUND) {
/* 1847 */       Collection markers = (Collection)this.backgroundRangeMarkers.get(new Integer(index));
/* 1850 */       if (markers == null) {
/* 1851 */         markers = new ArrayList();
/* 1852 */         this.backgroundRangeMarkers.put(new Integer(index), markers);
/*      */       } 
/* 1854 */       markers.add(marker);
/*      */     } 
/* 1856 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void clearRangeMarkers(int index) {
/* 1866 */     Integer key = new Integer(index);
/* 1867 */     if (this.backgroundRangeMarkers != null) {
/* 1868 */       Collection markers = (Collection)this.backgroundRangeMarkers.get(key);
/* 1870 */       if (markers != null)
/* 1871 */         markers.clear(); 
/*      */     } 
/* 1874 */     if (this.foregroundRangeMarkers != null) {
/* 1875 */       Collection markers = (Collection)this.foregroundRangeMarkers.get(key);
/* 1877 */       if (markers != null)
/* 1878 */         markers.clear(); 
/*      */     } 
/* 1881 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void addAnnotation(XYAnnotation annotation) {
/* 1891 */     if (annotation == null)
/* 1892 */       throw new IllegalArgumentException("Null 'annotation' argument."); 
/* 1894 */     this.annotations.add(annotation);
/* 1895 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean removeAnnotation(XYAnnotation annotation) {
/* 1907 */     if (annotation == null)
/* 1908 */       throw new IllegalArgumentException("Null 'annotation' argument."); 
/* 1910 */     boolean removed = this.annotations.remove(annotation);
/* 1911 */     if (removed)
/* 1912 */       notifyListeners(new PlotChangeEvent(this)); 
/* 1914 */     return removed;
/*      */   }
/*      */   
/*      */   public void clearAnnotations() {
/* 1922 */     this.annotations.clear();
/* 1923 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   protected AxisSpace calculateAxisSpace(Graphics2D g2, Rectangle2D plotArea) {
/* 1936 */     AxisSpace space = new AxisSpace();
/* 1937 */     space = calculateDomainAxisSpace(g2, plotArea, space);
/* 1938 */     space = calculateRangeAxisSpace(g2, plotArea, space);
/* 1939 */     return space;
/*      */   }
/*      */   
/*      */   protected AxisSpace calculateDomainAxisSpace(Graphics2D g2, Rectangle2D plotArea, AxisSpace space) {
/* 1955 */     if (space == null)
/* 1956 */       space = new AxisSpace(); 
/* 1960 */     if (this.fixedDomainAxisSpace != null) {
/* 1961 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 1962 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getLeft(), RectangleEdge.LEFT);
/* 1965 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getRight(), RectangleEdge.RIGHT);
/* 1969 */       } else if (this.orientation == PlotOrientation.VERTICAL) {
/* 1970 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getTop(), RectangleEdge.TOP);
/* 1973 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getBottom(), RectangleEdge.BOTTOM);
/*      */       } 
/*      */     } else {
/* 1980 */       for (int i = 0; i < this.domainAxes.size(); i++) {
/* 1981 */         Axis axis = (Axis)this.domainAxes.get(i);
/* 1982 */         if (axis != null) {
/* 1983 */           RectangleEdge edge = getDomainAxisEdge(i);
/* 1984 */           space = axis.reserveSpace(g2, this, plotArea, edge, space);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1989 */     return space;
/*      */   }
/*      */   
/*      */   protected AxisSpace calculateRangeAxisSpace(Graphics2D g2, Rectangle2D plotArea, AxisSpace space) {
/* 2006 */     if (space == null)
/* 2007 */       space = new AxisSpace(); 
/* 2011 */     if (this.fixedRangeAxisSpace != null) {
/* 2012 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 2013 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getTop(), RectangleEdge.TOP);
/* 2016 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getBottom(), RectangleEdge.BOTTOM);
/* 2020 */       } else if (this.orientation == PlotOrientation.VERTICAL) {
/* 2021 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getLeft(), RectangleEdge.LEFT);
/* 2024 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getRight(), RectangleEdge.RIGHT);
/*      */       } 
/*      */     } else {
/* 2031 */       for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 2032 */         Axis axis = (Axis)this.rangeAxes.get(i);
/* 2033 */         if (axis != null) {
/* 2034 */           RectangleEdge edge = getRangeAxisEdge(i);
/* 2035 */           space = axis.reserveSpace(g2, this, plotArea, edge, space);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2039 */     return space;
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/* 2062 */     boolean b1 = (area.getWidth() <= 10.0D);
/* 2063 */     boolean b2 = (area.getHeight() <= 10.0D);
/* 2064 */     if (b1 || b2)
/*      */       return; 
/* 2069 */     if (info != null)
/* 2070 */       info.setPlotArea(area); 
/* 2074 */     RectangleInsets insets = getInsets();
/* 2075 */     insets.trim(area);
/* 2077 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 2078 */     Rectangle2D dataArea = space.shrink(area, null);
/* 2079 */     this.axisOffset.trim(dataArea);
/* 2081 */     if (info != null)
/* 2082 */       info.setDataArea(dataArea); 
/* 2086 */     drawBackground(g2, dataArea);
/* 2087 */     Map axisStateMap = drawAxes(g2, area, dataArea, info);
/* 2089 */     if (anchor != null && !dataArea.contains(anchor))
/* 2090 */       anchor = null; 
/* 2092 */     CrosshairState crosshairState = new CrosshairState();
/* 2093 */     crosshairState.setCrosshairDistance(Double.POSITIVE_INFINITY);
/* 2094 */     crosshairState.setAnchor(anchor);
/* 2095 */     crosshairState.setCrosshairX(getDomainCrosshairValue());
/* 2096 */     crosshairState.setCrosshairY(getRangeCrosshairValue());
/* 2097 */     Shape originalClip = g2.getClip();
/* 2098 */     Composite originalComposite = g2.getComposite();
/* 2100 */     g2.clip(dataArea);
/* 2101 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/* 2107 */     AxisState domainAxisState = (AxisState)axisStateMap.get(getDomainAxis());
/* 2109 */     if (domainAxisState == null && 
/* 2110 */       parentState != null)
/* 2111 */       domainAxisState = (AxisState)parentState.getSharedAxisStates().get(getDomainAxis()); 
/* 2118 */     AxisState rangeAxisState = (AxisState)axisStateMap.get(getRangeAxis());
/* 2119 */     if (rangeAxisState == null && 
/* 2120 */       parentState != null)
/* 2121 */       rangeAxisState = (AxisState)parentState.getSharedAxisStates().get(getRangeAxis()); 
/* 2127 */     if (domainAxisState != null)
/* 2128 */       drawDomainTickBands(g2, dataArea, domainAxisState.getTicks()); 
/* 2130 */     if (rangeAxisState != null)
/* 2131 */       drawRangeTickBands(g2, dataArea, rangeAxisState.getTicks()); 
/* 2133 */     if (domainAxisState != null)
/* 2134 */       drawDomainGridlines(g2, dataArea, domainAxisState.getTicks()); 
/* 2136 */     if (rangeAxisState != null) {
/* 2137 */       drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
/* 2138 */       drawZeroRangeBaseline(g2, dataArea);
/*      */     } 
/*      */     int i;
/* 2142 */     for (i = 0; i < this.renderers.size(); i++)
/* 2143 */       drawDomainMarkers(g2, dataArea, i, Layer.BACKGROUND); 
/* 2145 */     for (i = 0; i < this.renderers.size(); i++)
/* 2146 */       drawRangeMarkers(g2, dataArea, i, Layer.BACKGROUND); 
/* 2150 */     boolean foundData = false;
/* 2151 */     DatasetRenderingOrder order = getDatasetRenderingOrder();
/* 2152 */     if (order == DatasetRenderingOrder.FORWARD) {
/* 2155 */       int rendererCount = this.renderers.size();
/*      */       int k;
/* 2156 */       for (k = 0; k < rendererCount; k++) {
/* 2157 */         XYItemRenderer r = getRenderer(k);
/* 2158 */         if (r != null) {
/* 2159 */           ValueAxis domainAxis = getDomainAxisForDataset(k);
/* 2160 */           ValueAxis rangeAxis = getRangeAxisForDataset(k);
/* 2161 */           r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis, Layer.BACKGROUND, info);
/*      */         } 
/*      */       } 
/* 2169 */       for (k = 0; k < getDatasetCount(); k++)
/* 2170 */         foundData = (render(g2, dataArea, k, info, crosshairState) || foundData); 
/* 2175 */       for (k = 0; k < rendererCount; k++) {
/* 2176 */         XYItemRenderer r = getRenderer(k);
/* 2177 */         if (r != null) {
/* 2178 */           ValueAxis domainAxis = getDomainAxisForDataset(k);
/* 2179 */           ValueAxis rangeAxis = getRangeAxisForDataset(k);
/* 2180 */           r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis, Layer.FOREGROUND, info);
/*      */         } 
/*      */       } 
/* 2188 */     } else if (order == DatasetRenderingOrder.REVERSE) {
/* 2191 */       int rendererCount = this.renderers.size();
/*      */       int k;
/* 2192 */       for (k = rendererCount - 1; k >= 0; k--) {
/* 2193 */         XYItemRenderer r = getRenderer(k);
/* 2194 */         if (r != null) {
/* 2195 */           ValueAxis domainAxis = getDomainAxisForDataset(k);
/* 2196 */           ValueAxis rangeAxis = getRangeAxisForDataset(k);
/* 2197 */           r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis, Layer.BACKGROUND, info);
/*      */         } 
/*      */       } 
/* 2204 */       for (k = getDatasetCount() - 1; k >= 0; k--)
/* 2205 */         foundData = (render(g2, dataArea, k, info, crosshairState) || foundData); 
/* 2210 */       for (k = rendererCount - 1; k >= 0; k--) {
/* 2211 */         XYItemRenderer r = getRenderer(k);
/* 2212 */         if (r != null) {
/* 2213 */           ValueAxis domainAxis = getDomainAxisForDataset(k);
/* 2214 */           ValueAxis rangeAxis = getRangeAxisForDataset(k);
/* 2215 */           r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis, Layer.FOREGROUND, info);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2224 */     PlotOrientation orient = getOrientation();
/* 2227 */     if (!this.domainCrosshairLockedOnData && anchor != null) {
/* 2228 */       double xx = getDomainAxis().java2DToValue(anchor.getX(), dataArea, getDomainAxisEdge());
/* 2231 */       crosshairState.setCrosshairX(xx);
/*      */     } 
/* 2233 */     setDomainCrosshairValue(crosshairState.getCrosshairX(), false);
/* 2234 */     if (isDomainCrosshairVisible()) {
/* 2235 */       double x = getDomainCrosshairValue();
/* 2236 */       Paint paint = getDomainCrosshairPaint();
/* 2237 */       Stroke stroke = getDomainCrosshairStroke();
/* 2238 */       if (orient == PlotOrientation.HORIZONTAL) {
/* 2239 */         drawHorizontalLine(g2, dataArea, x, stroke, paint);
/* 2241 */       } else if (orient == PlotOrientation.VERTICAL) {
/* 2242 */         drawVerticalLine(g2, dataArea, x, stroke, paint);
/*      */       } 
/*      */     } 
/* 2247 */     if (!this.rangeCrosshairLockedOnData && anchor != null) {
/* 2248 */       double yy = getRangeAxis().java2DToValue(anchor.getY(), dataArea, getRangeAxisEdge());
/* 2251 */       crosshairState.setCrosshairY(yy);
/*      */     } 
/* 2253 */     setRangeCrosshairValue(crosshairState.getCrosshairY(), false);
/* 2254 */     if (isRangeCrosshairVisible() && getRangeAxis().getRange().contains(getRangeCrosshairValue())) {
/* 2256 */       double y = getRangeCrosshairValue();
/* 2257 */       Paint paint = getRangeCrosshairPaint();
/* 2258 */       Stroke stroke = getRangeCrosshairStroke();
/* 2259 */       if (orient == PlotOrientation.HORIZONTAL) {
/* 2260 */         drawVerticalLine(g2, dataArea, y, stroke, paint);
/* 2262 */       } else if (orient == PlotOrientation.VERTICAL) {
/* 2263 */         drawHorizontalLine(g2, dataArea, y, stroke, paint);
/*      */       } 
/*      */     } 
/* 2267 */     if (!foundData)
/* 2268 */       drawNoDataMessage(g2, dataArea); 
/*      */     int j;
/* 2271 */     for (j = 0; j < this.renderers.size(); j++)
/* 2272 */       drawDomainMarkers(g2, dataArea, j, Layer.FOREGROUND); 
/* 2274 */     for (j = 0; j < this.renderers.size(); j++)
/* 2275 */       drawRangeMarkers(g2, dataArea, j, Layer.FOREGROUND); 
/* 2278 */     drawAnnotations(g2, dataArea, info);
/* 2279 */     g2.setClip(originalClip);
/* 2280 */     g2.setComposite(originalComposite);
/* 2282 */     drawOutline(g2, dataArea);
/*      */   }
/*      */   
/*      */   public void drawBackground(Graphics2D g2, Rectangle2D area) {
/* 2293 */     fillBackground(g2, area);
/* 2294 */     drawQuadrants(g2, area);
/* 2295 */     drawBackgroundImage(g2, area);
/*      */   }
/*      */   
/*      */   protected void drawQuadrants(Graphics2D g2, Rectangle2D area) {
/* 2308 */     boolean somethingToDraw = false;
/* 2310 */     ValueAxis xAxis = getDomainAxis();
/* 2311 */     double x = this.quadrantOrigin.getX();
/* 2312 */     double xx = xAxis.valueToJava2D(x, area, getDomainAxisEdge());
/* 2314 */     ValueAxis yAxis = getRangeAxis();
/* 2315 */     double y = this.quadrantOrigin.getY();
/* 2316 */     double yy = yAxis.valueToJava2D(y, area, getRangeAxisEdge());
/* 2318 */     double xmin = xAxis.getLowerBound();
/* 2319 */     double xxmin = xAxis.valueToJava2D(xmin, area, getDomainAxisEdge());
/* 2321 */     double xmax = xAxis.getUpperBound();
/* 2322 */     double xxmax = xAxis.valueToJava2D(xmax, area, getDomainAxisEdge());
/* 2324 */     double ymin = yAxis.getLowerBound();
/* 2325 */     double yymin = yAxis.valueToJava2D(ymin, area, getRangeAxisEdge());
/* 2327 */     double ymax = yAxis.getUpperBound();
/* 2328 */     double yymax = yAxis.valueToJava2D(ymax, area, getRangeAxisEdge());
/* 2330 */     Rectangle2D[] r = { null, null, null, null };
/* 2331 */     if (this.quadrantPaint[0] != null && 
/* 2332 */       x > xmin && y < ymax) {
/* 2333 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 2334 */         r[0] = new Rectangle2D.Double(Math.min(yymax, yy), Math.min(xxmin, xx), Math.abs(yy - yymax), Math.abs(xx - xxmin));
/*      */       } else {
/* 2340 */         r[0] = new Rectangle2D.Double(Math.min(xxmin, xx), Math.min(yymax, yy), Math.abs(xx - xxmin), Math.abs(yy - yymax));
/*      */       } 
/* 2345 */       somethingToDraw = true;
/*      */     } 
/* 2348 */     if (this.quadrantPaint[1] != null && 
/* 2349 */       x < xmax && y < ymax) {
/* 2350 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 2351 */         r[1] = new Rectangle2D.Double(Math.min(yymax, yy), Math.min(xxmax, xx), Math.abs(yy - yymax), Math.abs(xx - xxmax));
/*      */       } else {
/* 2357 */         r[1] = new Rectangle2D.Double(Math.min(xx, xxmax), Math.min(yymax, yy), Math.abs(xx - xxmax), Math.abs(yy - yymax));
/*      */       } 
/* 2362 */       somethingToDraw = true;
/*      */     } 
/* 2365 */     if (this.quadrantPaint[2] != null && 
/* 2366 */       x > xmin && y > ymin) {
/* 2367 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 2368 */         r[2] = new Rectangle2D.Double(Math.min(yymin, yy), Math.min(xxmin, xx), Math.abs(yy - yymin), Math.abs(xx - xxmin));
/*      */       } else {
/* 2374 */         r[2] = new Rectangle2D.Double(Math.min(xxmin, xx), Math.min(yymin, yy), Math.abs(xx - xxmin), Math.abs(yy - yymin));
/*      */       } 
/* 2379 */       somethingToDraw = true;
/*      */     } 
/* 2382 */     if (this.quadrantPaint[3] != null && 
/* 2383 */       x < xmax && y > ymin) {
/* 2384 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 2385 */         r[3] = new Rectangle2D.Double(Math.min(yymin, yy), Math.min(xxmax, xx), Math.abs(yy - yymin), Math.abs(xx - xxmax));
/*      */       } else {
/* 2391 */         r[3] = new Rectangle2D.Double(Math.min(xx, xxmax), Math.min(yymin, yy), Math.abs(xx - xxmax), Math.abs(yy - yymin));
/*      */       } 
/* 2396 */       somethingToDraw = true;
/*      */     } 
/* 2399 */     if (somethingToDraw) {
/* 2400 */       Composite originalComposite = g2.getComposite();
/* 2401 */       g2.setComposite(AlphaComposite.getInstance(3, getBackgroundAlpha()));
/* 2406 */       for (int i = 0; i < 4; i++) {
/* 2407 */         if (this.quadrantPaint[i] != null && r[i] != null) {
/* 2408 */           g2.setPaint(this.quadrantPaint[i]);
/* 2409 */           g2.fill(r[i]);
/*      */         } 
/*      */       } 
/* 2412 */       g2.setComposite(originalComposite);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void drawDomainTickBands(Graphics2D g2, Rectangle2D dataArea, List ticks) {
/* 2426 */     Paint bandPaint = getDomainTickBandPaint();
/* 2427 */     if (bandPaint != null) {
/* 2428 */       boolean fillBand = false;
/* 2429 */       ValueAxis xAxis = getDomainAxis();
/* 2430 */       double previous = xAxis.getLowerBound();
/* 2431 */       Iterator iterator = ticks.iterator();
/* 2432 */       while (iterator.hasNext()) {
/* 2433 */         ValueTick tick = iterator.next();
/* 2434 */         double current = tick.getValue();
/* 2435 */         if (fillBand)
/* 2436 */           getRenderer().fillDomainGridBand(g2, this, xAxis, dataArea, previous, current); 
/* 2440 */         previous = current;
/* 2441 */         fillBand = !fillBand;
/*      */       } 
/* 2443 */       double end = xAxis.getUpperBound();
/* 2444 */       if (fillBand)
/* 2445 */         getRenderer().fillDomainGridBand(g2, this, xAxis, dataArea, previous, end); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void drawRangeTickBands(Graphics2D g2, Rectangle2D dataArea, List ticks) {
/* 2463 */     Paint bandPaint = getRangeTickBandPaint();
/* 2464 */     if (bandPaint != null) {
/* 2465 */       boolean fillBand = false;
/* 2466 */       ValueAxis axis = getRangeAxis();
/* 2467 */       double previous = axis.getLowerBound();
/* 2468 */       Iterator iterator = ticks.iterator();
/* 2469 */       while (iterator.hasNext()) {
/* 2470 */         ValueTick tick = iterator.next();
/* 2471 */         double current = tick.getValue();
/* 2472 */         if (fillBand)
/* 2473 */           getRenderer().fillRangeGridBand(g2, this, axis, dataArea, previous, current); 
/* 2477 */         previous = current;
/* 2478 */         fillBand = !fillBand;
/*      */       } 
/* 2480 */       double end = axis.getUpperBound();
/* 2481 */       if (fillBand)
/* 2482 */         getRenderer().fillRangeGridBand(g2, this, axis, dataArea, previous, end); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Map drawAxes(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, PlotRenderingInfo plotState) {
/* 2505 */     AxisCollection axisCollection = new AxisCollection();
/*      */     int index;
/* 2508 */     for (index = 0; index < this.domainAxes.size(); index++) {
/* 2509 */       ValueAxis axis = (ValueAxis)this.domainAxes.get(index);
/* 2510 */       if (axis != null)
/* 2511 */         axisCollection.add((Axis)axis, getDomainAxisEdge(index)); 
/*      */     } 
/* 2516 */     for (index = 0; index < this.rangeAxes.size(); index++) {
/* 2517 */       ValueAxis yAxis = (ValueAxis)this.rangeAxes.get(index);
/* 2518 */       if (yAxis != null)
/* 2519 */         axisCollection.add((Axis)yAxis, getRangeAxisEdge(index)); 
/*      */     } 
/* 2523 */     Map axisStateMap = new HashMap();
/* 2526 */     double cursor = dataArea.getMinY() - this.axisOffset.calculateTopOutset(dataArea.getHeight());
/* 2529 */     Iterator iterator = axisCollection.getAxesAtTop().iterator();
/* 2530 */     while (iterator.hasNext()) {
/* 2531 */       ValueAxis axis = iterator.next();
/* 2532 */       AxisState info = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.TOP, plotState);
/* 2535 */       cursor = info.getCursor();
/* 2536 */       axisStateMap.put(axis, info);
/*      */     } 
/* 2540 */     cursor = dataArea.getMaxY() + this.axisOffset.calculateBottomOutset(dataArea.getHeight());
/* 2542 */     iterator = axisCollection.getAxesAtBottom().iterator();
/* 2543 */     while (iterator.hasNext()) {
/* 2544 */       ValueAxis axis = iterator.next();
/* 2545 */       AxisState info = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.BOTTOM, plotState);
/* 2548 */       cursor = info.getCursor();
/* 2549 */       axisStateMap.put(axis, info);
/*      */     } 
/* 2553 */     cursor = dataArea.getMinX() - this.axisOffset.calculateLeftOutset(dataArea.getWidth());
/* 2555 */     iterator = axisCollection.getAxesAtLeft().iterator();
/* 2556 */     while (iterator.hasNext()) {
/* 2557 */       ValueAxis axis = iterator.next();
/* 2558 */       AxisState info = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.LEFT, plotState);
/* 2561 */       cursor = info.getCursor();
/* 2562 */       axisStateMap.put(axis, info);
/*      */     } 
/* 2566 */     cursor = dataArea.getMaxX() + this.axisOffset.calculateRightOutset(dataArea.getWidth());
/* 2568 */     iterator = axisCollection.getAxesAtRight().iterator();
/* 2569 */     while (iterator.hasNext()) {
/* 2570 */       ValueAxis axis = iterator.next();
/* 2571 */       AxisState info = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.RIGHT, plotState);
/* 2574 */       cursor = info.getCursor();
/* 2575 */       axisStateMap.put(axis, info);
/*      */     } 
/* 2578 */     return axisStateMap;
/*      */   }
/*      */   
/*      */   public boolean render(Graphics2D g2, Rectangle2D dataArea, int index, PlotRenderingInfo info, CrosshairState crosshairState) {
/* 2603 */     boolean foundData = false;
/* 2604 */     XYDataset dataset = getDataset(index);
/* 2605 */     if (!DatasetUtilities.isEmptyOrNull(dataset)) {
/* 2606 */       foundData = true;
/* 2607 */       ValueAxis xAxis = getDomainAxisForDataset(index);
/* 2608 */       ValueAxis yAxis = getRangeAxisForDataset(index);
/* 2609 */       XYItemRenderer renderer = getRenderer(index);
/* 2610 */       if (renderer == null)
/* 2611 */         renderer = getRenderer(); 
/* 2614 */       XYItemRendererState state = renderer.initialise(g2, dataArea, this, dataset, info);
/* 2617 */       int passCount = renderer.getPassCount();
/* 2619 */       SeriesRenderingOrder seriesOrder = getSeriesRenderingOrder();
/* 2620 */       if (seriesOrder == SeriesRenderingOrder.REVERSE) {
/* 2622 */         for (int pass = 0; pass < passCount; pass++) {
/* 2623 */           int seriesCount = dataset.getSeriesCount();
/* 2624 */           for (int series = seriesCount - 1; series >= 0; series--) {
/* 2625 */             int itemCount = dataset.getItemCount(series);
/* 2626 */             for (int item = 0; item < itemCount; item++)
/* 2627 */               renderer.drawItem(g2, state, dataArea, info, this, xAxis, yAxis, dataset, series, item, crosshairState, pass); 
/*      */           } 
/*      */         } 
/*      */       } else {
/* 2638 */         for (int pass = 0; pass < passCount; pass++) {
/* 2639 */           int seriesCount = dataset.getSeriesCount();
/* 2640 */           for (int series = 0; series < seriesCount; series++) {
/* 2641 */             int itemCount = dataset.getItemCount(series);
/* 2642 */             for (int item = 0; item < itemCount; item++)
/* 2643 */               renderer.drawItem(g2, state, dataArea, info, this, xAxis, yAxis, dataset, series, item, crosshairState, pass); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2653 */     return foundData;
/*      */   }
/*      */   
/*      */   public ValueAxis getDomainAxisForDataset(int index) {
/* 2665 */     if (index < 0 || index >= getDatasetCount())
/* 2666 */       throw new IllegalArgumentException("Index 'index' out of bounds."); 
/* 2669 */     ValueAxis valueAxis = null;
/* 2670 */     Integer axisIndex = (Integer)this.datasetToDomainAxisMap.get(new Integer(index));
/* 2673 */     if (axisIndex != null) {
/* 2674 */       valueAxis = getDomainAxis(axisIndex.intValue());
/*      */     } else {
/* 2677 */       valueAxis = getDomainAxis(0);
/*      */     } 
/* 2679 */     return valueAxis;
/*      */   }
/*      */   
/*      */   public ValueAxis getRangeAxisForDataset(int index) {
/* 2692 */     if (index < 0 || index >= getDatasetCount())
/* 2693 */       throw new IllegalArgumentException("Index 'index' out of bounds."); 
/* 2696 */     ValueAxis valueAxis = null;
/* 2697 */     Integer axisIndex = (Integer)this.datasetToRangeAxisMap.get(new Integer(index));
/* 2699 */     if (axisIndex != null) {
/* 2700 */       valueAxis = getRangeAxis(axisIndex.intValue());
/*      */     } else {
/* 2703 */       valueAxis = getRangeAxis(0);
/*      */     } 
/* 2705 */     return valueAxis;
/*      */   }
/*      */   
/*      */   protected void drawDomainGridlines(Graphics2D g2, Rectangle2D dataArea, List ticks) {
/* 2720 */     if (getRenderer() == null)
/*      */       return; 
/* 2725 */     if (isDomainGridlinesVisible()) {
/* 2726 */       Stroke gridStroke = getDomainGridlineStroke();
/* 2727 */       Paint gridPaint = getDomainGridlinePaint();
/* 2728 */       if (gridStroke != null && gridPaint != null) {
/* 2729 */         Iterator iterator = ticks.iterator();
/* 2730 */         while (iterator.hasNext()) {
/* 2731 */           ValueTick tick = iterator.next();
/* 2732 */           getRenderer().drawDomainGridLine(g2, this, getDomainAxis(), dataArea, tick.getValue());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawRangeGridlines(Graphics2D g2, Rectangle2D area, List ticks) {
/* 2752 */     if (isRangeGridlinesVisible()) {
/* 2753 */       Stroke gridStroke = getRangeGridlineStroke();
/* 2754 */       Paint gridPaint = getRangeGridlinePaint();
/* 2755 */       ValueAxis axis = getRangeAxis();
/* 2756 */       if (axis != null) {
/* 2757 */         Iterator iterator = ticks.iterator();
/* 2758 */         while (iterator.hasNext()) {
/* 2759 */           ValueTick tick = iterator.next();
/* 2760 */           if (tick.getValue() != 0.0D || !isRangeZeroBaselineVisible())
/* 2762 */             getRenderer().drawRangeLine(g2, this, getRangeAxis(), area, tick.getValue(), gridPaint, gridStroke); 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawZeroRangeBaseline(Graphics2D g2, Rectangle2D area) {
/* 2779 */     if (isRangeZeroBaselineVisible())
/* 2780 */       getRenderer().drawRangeLine(g2, this, getRangeAxis(), area, 0.0D, this.rangeZeroBaselinePaint, this.rangeZeroBaselineStroke); 
/*      */   }
/*      */   
/*      */   public void drawAnnotations(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info) {
/* 2798 */     Iterator iterator = this.annotations.iterator();
/* 2799 */     while (iterator.hasNext()) {
/* 2800 */       XYAnnotation annotation = iterator.next();
/* 2801 */       ValueAxis xAxis = getDomainAxis();
/* 2802 */       ValueAxis yAxis = getRangeAxis();
/* 2803 */       annotation.draw(g2, this, dataArea, xAxis, yAxis, 0, info);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawDomainMarkers(Graphics2D g2, Rectangle2D dataArea, int index, Layer layer) {
/* 2820 */     XYItemRenderer r = getRenderer(index);
/* 2821 */     if (r == null)
/*      */       return; 
/* 2825 */     Collection markers = getDomainMarkers(index, layer);
/* 2826 */     ValueAxis axis = getDomainAxisForDataset(index);
/* 2827 */     if (markers != null && axis != null) {
/* 2828 */       Iterator iterator = markers.iterator();
/* 2829 */       while (iterator.hasNext()) {
/* 2830 */         Marker marker = iterator.next();
/* 2831 */         r.drawDomainMarker(g2, this, axis, marker, dataArea);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawRangeMarkers(Graphics2D g2, Rectangle2D dataArea, int index, Layer layer) {
/* 2849 */     XYItemRenderer r = getRenderer(index);
/* 2850 */     if (r == null)
/*      */       return; 
/* 2854 */     Collection markers = getRangeMarkers(index, layer);
/* 2855 */     ValueAxis axis = getRangeAxis(index);
/* 2857 */     if (markers != null && axis != null) {
/* 2858 */       Iterator iterator = markers.iterator();
/* 2859 */       while (iterator.hasNext()) {
/* 2860 */         Marker marker = iterator.next();
/* 2861 */         r.drawRangeMarker(g2, this, axis, marker, dataArea);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Collection getDomainMarkers(Layer layer) {
/* 2875 */     return getDomainMarkers(0, layer);
/*      */   }
/*      */   
/*      */   public Collection getRangeMarkers(Layer layer) {
/* 2886 */     return getRangeMarkers(0, layer);
/*      */   }
/*      */   
/*      */   public Collection getDomainMarkers(int index, Layer layer) {
/* 2899 */     Collection result = null;
/* 2900 */     Integer key = new Integer(index);
/* 2901 */     if (layer == Layer.FOREGROUND) {
/* 2902 */       result = (Collection)this.foregroundDomainMarkers.get(key);
/* 2904 */     } else if (layer == Layer.BACKGROUND) {
/* 2905 */       result = (Collection)this.backgroundDomainMarkers.get(key);
/*      */     } 
/* 2907 */     if (result != null)
/* 2908 */       result = Collections.unmodifiableCollection(result); 
/* 2910 */     return result;
/*      */   }
/*      */   
/*      */   public Collection getRangeMarkers(int index, Layer layer) {
/* 2923 */     Collection result = null;
/* 2924 */     Integer key = new Integer(index);
/* 2925 */     if (layer == Layer.FOREGROUND) {
/* 2926 */       result = (Collection)this.foregroundRangeMarkers.get(key);
/* 2928 */     } else if (layer == Layer.BACKGROUND) {
/* 2929 */       result = (Collection)this.backgroundRangeMarkers.get(key);
/*      */     } 
/* 2931 */     if (result != null)
/* 2932 */       result = Collections.unmodifiableCollection(result); 
/* 2934 */     return result;
/*      */   }
/*      */   
/*      */   protected void drawHorizontalLine(Graphics2D g2, Rectangle2D dataArea, double value, Stroke stroke, Paint paint) {
/* 2951 */     ValueAxis axis = getRangeAxis();
/* 2952 */     if (getOrientation() == PlotOrientation.HORIZONTAL)
/* 2953 */       axis = getDomainAxis(); 
/* 2955 */     if (axis.getRange().contains(value)) {
/* 2956 */       double yy = axis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/* 2957 */       Line2D line = new Line2D.Double(dataArea.getMinX(), yy, dataArea.getMaxX(), yy);
/* 2960 */       g2.setStroke(stroke);
/* 2961 */       g2.setPaint(paint);
/* 2962 */       g2.draw(line);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawVerticalLine(Graphics2D g2, Rectangle2D dataArea, double value, Stroke stroke, Paint paint) {
/* 2979 */     ValueAxis axis = getDomainAxis();
/* 2980 */     if (getOrientation() == PlotOrientation.HORIZONTAL)
/* 2981 */       axis = getRangeAxis(); 
/* 2983 */     if (axis.getRange().contains(value)) {
/* 2984 */       double xx = axis.valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
/* 2987 */       Line2D line = new Line2D.Double(xx, dataArea.getMinY(), xx, dataArea.getMaxY());
/* 2990 */       g2.setStroke(stroke);
/* 2991 */       g2.setPaint(paint);
/* 2992 */       g2.draw(line);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void handleClick(int x, int y, PlotRenderingInfo info) {
/* 3006 */     Rectangle2D dataArea = info.getDataArea();
/* 3007 */     if (dataArea.contains(x, y)) {
/* 3009 */       ValueAxis da = getDomainAxis();
/* 3010 */       if (da != null) {
/* 3011 */         double hvalue = da.java2DToValue(x, info.getDataArea(), getDomainAxisEdge());
/* 3015 */         setDomainCrosshairValue(hvalue);
/*      */       } 
/* 3019 */       ValueAxis ra = getRangeAxis();
/* 3020 */       if (ra != null) {
/* 3021 */         double vvalue = ra.java2DToValue(y, info.getDataArea(), getRangeAxisEdge());
/* 3024 */         setRangeCrosshairValue(vvalue);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private List getDatasetsMappedToDomainAxis(Integer axisIndex) {
/* 3038 */     if (axisIndex == null)
/* 3039 */       throw new IllegalArgumentException("Null 'axisIndex' argument."); 
/* 3041 */     List result = new ArrayList();
/* 3042 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 3043 */       Integer mappedAxis = (Integer)this.datasetToDomainAxisMap.get(new Integer(i));
/* 3046 */       if (mappedAxis == null) {
/* 3047 */         if (axisIndex.equals(ZERO))
/* 3048 */           result.add(this.datasets.get(i)); 
/* 3052 */       } else if (mappedAxis.equals(axisIndex)) {
/* 3053 */         result.add(this.datasets.get(i));
/*      */       } 
/*      */     } 
/* 3057 */     return result;
/*      */   }
/*      */   
/*      */   private List getDatasetsMappedToRangeAxis(Integer axisIndex) {
/* 3069 */     if (axisIndex == null)
/* 3070 */       throw new IllegalArgumentException("Null 'axisIndex' argument."); 
/* 3072 */     List result = new ArrayList();
/* 3073 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 3074 */       Integer mappedAxis = (Integer)this.datasetToRangeAxisMap.get(new Integer(i));
/* 3077 */       if (mappedAxis == null) {
/* 3078 */         if (axisIndex.equals(ZERO))
/* 3079 */           result.add(this.datasets.get(i)); 
/* 3083 */       } else if (mappedAxis.equals(axisIndex)) {
/* 3084 */         result.add(this.datasets.get(i));
/*      */       } 
/*      */     } 
/* 3088 */     return result;
/*      */   }
/*      */   
/*      */   protected int getDomainAxisIndex(ValueAxis axis) {
/* 3099 */     int result = this.domainAxes.indexOf(axis);
/* 3100 */     if (result < 0) {
/* 3102 */       Plot parent = getParent();
/* 3103 */       if (parent instanceof XYPlot) {
/* 3104 */         XYPlot p = (XYPlot)parent;
/* 3105 */         result = p.getDomainAxisIndex(axis);
/*      */       } 
/*      */     } 
/* 3108 */     return result;
/*      */   }
/*      */   
/*      */   protected int getRangeAxisIndex(ValueAxis axis) {
/* 3119 */     int result = this.rangeAxes.indexOf(axis);
/* 3120 */     if (result < 0) {
/* 3122 */       Plot parent = getParent();
/* 3123 */       if (parent instanceof XYPlot) {
/* 3124 */         XYPlot p = (XYPlot)parent;
/* 3125 */         result = p.getRangeAxisIndex(axis);
/*      */       } 
/*      */     } 
/* 3128 */     return result;
/*      */   }
/*      */   
/*      */   public Range getDataRange(ValueAxis axis) {
/* 3140 */     Range result = null;
/* 3141 */     List mappedDatasets = new ArrayList();
/* 3142 */     boolean isDomainAxis = true;
/* 3145 */     int domainIndex = getDomainAxisIndex(axis);
/* 3146 */     if (domainIndex >= 0) {
/* 3147 */       isDomainAxis = true;
/* 3148 */       mappedDatasets.addAll(getDatasetsMappedToDomainAxis(new Integer(domainIndex)));
/*      */     } 
/* 3154 */     int rangeIndex = getRangeAxisIndex(axis);
/* 3155 */     if (rangeIndex >= 0) {
/* 3156 */       isDomainAxis = false;
/* 3157 */       mappedDatasets.addAll(getDatasetsMappedToRangeAxis(new Integer(rangeIndex)));
/*      */     } 
/* 3164 */     Iterator iterator = mappedDatasets.iterator();
/* 3165 */     while (iterator.hasNext()) {
/* 3166 */       XYDataset d = iterator.next();
/* 3167 */       if (d != null) {
/* 3168 */         XYItemRenderer r = getRendererForDataset(d);
/* 3169 */         if (isDomainAxis) {
/* 3170 */           if (r != null) {
/* 3171 */             result = Range.combine(result, r.findDomainBounds(d));
/*      */             continue;
/*      */           } 
/* 3174 */           result = Range.combine(result, DatasetUtilities.findDomainBounds(d));
/*      */           continue;
/*      */         } 
/* 3180 */         if (r != null) {
/* 3181 */           result = Range.combine(result, r.findRangeBounds(d));
/*      */           continue;
/*      */         } 
/* 3184 */         result = Range.combine(result, DatasetUtilities.findRangeBounds(d));
/*      */       } 
/*      */     } 
/* 3191 */     return result;
/*      */   }
/*      */   
/*      */   public void datasetChanged(DatasetChangeEvent event) {
/* 3203 */     configureDomainAxes();
/* 3204 */     configureRangeAxes();
/* 3205 */     if (getParent() != null) {
/* 3206 */       getParent().datasetChanged(event);
/*      */     } else {
/* 3209 */       PlotChangeEvent e = new PlotChangeEvent(this);
/* 3210 */       e.setType(ChartChangeEventType.DATASET_UPDATED);
/* 3211 */       notifyListeners(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void rendererChanged(RendererChangeEvent event) {
/* 3221 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isDomainCrosshairVisible() {
/* 3230 */     return this.domainCrosshairVisible;
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairVisible(boolean flag) {
/* 3240 */     if (this.domainCrosshairVisible != flag) {
/* 3241 */       this.domainCrosshairVisible = flag;
/* 3242 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isDomainCrosshairLockedOnData() {
/* 3254 */     return this.domainCrosshairLockedOnData;
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairLockedOnData(boolean flag) {
/* 3265 */     if (this.domainCrosshairLockedOnData != flag) {
/* 3266 */       this.domainCrosshairLockedOnData = flag;
/* 3267 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getDomainCrosshairValue() {
/* 3278 */     return this.domainCrosshairValue;
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairValue(double value) {
/* 3288 */     setDomainCrosshairValue(value, true);
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairValue(double value, boolean notify) {
/* 3300 */     this.domainCrosshairValue = value;
/* 3301 */     if (isDomainCrosshairVisible() && notify)
/* 3302 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Stroke getDomainCrosshairStroke() {
/* 3312 */     return this.domainCrosshairStroke;
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairStroke(Stroke stroke) {
/* 3322 */     this.domainCrosshairStroke = stroke;
/* 3323 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getDomainCrosshairPaint() {
/* 3332 */     return this.domainCrosshairPaint;
/*      */   }
/*      */   
/*      */   public void setDomainCrosshairPaint(Paint paint) {
/* 3342 */     this.domainCrosshairPaint = paint;
/* 3343 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isRangeCrosshairVisible() {
/* 3352 */     return this.rangeCrosshairVisible;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairVisible(boolean flag) {
/* 3362 */     if (this.rangeCrosshairVisible != flag) {
/* 3363 */       this.rangeCrosshairVisible = flag;
/* 3364 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isRangeCrosshairLockedOnData() {
/* 3376 */     return this.rangeCrosshairLockedOnData;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairLockedOnData(boolean flag) {
/* 3387 */     if (this.rangeCrosshairLockedOnData != flag) {
/* 3388 */       this.rangeCrosshairLockedOnData = flag;
/* 3389 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getRangeCrosshairValue() {
/* 3400 */     return this.rangeCrosshairValue;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairValue(double value) {
/* 3412 */     setRangeCrosshairValue(value, true);
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairValue(double value, boolean notify) {
/* 3426 */     this.rangeCrosshairValue = value;
/* 3427 */     if (isRangeCrosshairVisible() && notify)
/* 3428 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Stroke getRangeCrosshairStroke() {
/* 3438 */     return this.rangeCrosshairStroke;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairStroke(Stroke stroke) {
/* 3448 */     this.rangeCrosshairStroke = stroke;
/* 3449 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getRangeCrosshairPaint() {
/* 3458 */     return this.rangeCrosshairPaint;
/*      */   }
/*      */   
/*      */   public void setRangeCrosshairPaint(Paint paint) {
/* 3468 */     this.rangeCrosshairPaint = paint;
/* 3469 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public AxisSpace getFixedDomainAxisSpace() {
/* 3478 */     return this.fixedDomainAxisSpace;
/*      */   }
/*      */   
/*      */   public void setFixedDomainAxisSpace(AxisSpace space) {
/* 3487 */     this.fixedDomainAxisSpace = space;
/*      */   }
/*      */   
/*      */   public AxisSpace getFixedRangeAxisSpace() {
/* 3496 */     return this.fixedRangeAxisSpace;
/*      */   }
/*      */   
/*      */   public void setFixedRangeAxisSpace(AxisSpace space) {
/* 3505 */     this.fixedRangeAxisSpace = space;
/*      */   }
/*      */   
/*      */   public void zoomDomainAxes(double factor, PlotRenderingInfo info, Point2D source) {
/* 3517 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/* 3518 */       ValueAxis domainAxis = (ValueAxis)this.domainAxes.get(i);
/* 3519 */       if (domainAxis != null)
/* 3520 */         domainAxis.resizeRange(factor); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source) {
/* 3539 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/* 3540 */       ValueAxis domainAxis = (ValueAxis)this.domainAxes.get(i);
/* 3541 */       if (domainAxis != null)
/* 3542 */         domainAxis.zoomRange(lowerPercent, upperPercent); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void zoomRangeAxes(double factor, PlotRenderingInfo info, Point2D source) {
/* 3556 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 3557 */       ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
/* 3558 */       if (rangeAxis != null)
/* 3559 */         rangeAxis.resizeRange(factor); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source) {
/* 3574 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 3575 */       ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
/* 3576 */       if (rangeAxis != null)
/* 3577 */         rangeAxis.zoomRange(lowerPercent, upperPercent); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isDomainZoomable() {
/* 3588 */     return true;
/*      */   }
/*      */   
/*      */   public boolean isRangeZoomable() {
/* 3597 */     return true;
/*      */   }
/*      */   
/*      */   public int getSeriesCount() {
/* 3607 */     int result = 0;
/* 3608 */     XYDataset dataset = getDataset();
/* 3609 */     if (dataset != null)
/* 3610 */       result = dataset.getSeriesCount(); 
/* 3612 */     return result;
/*      */   }
/*      */   
/*      */   public LegendItemCollection getFixedLegendItems() {
/* 3621 */     return this.fixedLegendItems;
/*      */   }
/*      */   
/*      */   public void setFixedLegendItems(LegendItemCollection items) {
/* 3632 */     this.fixedLegendItems = items;
/* 3633 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public LegendItemCollection getLegendItems() {
/* 3644 */     if (this.fixedLegendItems != null)
/* 3645 */       return this.fixedLegendItems; 
/* 3647 */     LegendItemCollection result = new LegendItemCollection();
/* 3648 */     int count = this.datasets.size();
/* 3649 */     for (int datasetIndex = 0; datasetIndex < count; datasetIndex++) {
/* 3650 */       XYDataset dataset = getDataset(datasetIndex);
/* 3651 */       if (dataset != null) {
/* 3652 */         XYItemRenderer renderer = getRenderer(datasetIndex);
/* 3653 */         if (renderer == null)
/* 3654 */           renderer = getRenderer(0); 
/* 3656 */         if (renderer != null) {
/* 3657 */           int seriesCount = dataset.getSeriesCount();
/* 3658 */           for (int i = 0; i < seriesCount; i++) {
/* 3659 */             if (renderer.isSeriesVisible(i) && renderer.isSeriesVisibleInLegend(i)) {
/* 3661 */               LegendItem item = renderer.getLegendItem(datasetIndex, i);
/* 3664 */               if (item != null)
/* 3665 */                 result.add(item); 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 3672 */     return result;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 3684 */     if (obj == this)
/* 3685 */       return true; 
/* 3687 */     if (!(obj instanceof XYPlot))
/* 3688 */       return false; 
/* 3690 */     if (!super.equals(obj))
/* 3691 */       return false; 
/* 3694 */     XYPlot that = (XYPlot)obj;
/* 3695 */     if (this.weight != that.weight)
/* 3696 */       return false; 
/* 3698 */     if (this.orientation != that.orientation)
/* 3699 */       return false; 
/* 3701 */     if (!this.domainAxes.equals(that.domainAxes))
/* 3702 */       return false; 
/* 3704 */     if (!this.domainAxisLocations.equals(that.domainAxisLocations))
/* 3705 */       return false; 
/* 3707 */     if (this.rangeCrosshairLockedOnData != that.rangeCrosshairLockedOnData)
/* 3709 */       return false; 
/* 3711 */     if (this.domainGridlinesVisible != that.domainGridlinesVisible)
/* 3712 */       return false; 
/* 3714 */     if (this.rangeGridlinesVisible != that.rangeGridlinesVisible)
/* 3715 */       return false; 
/* 3717 */     if (this.rangeZeroBaselineVisible != that.rangeZeroBaselineVisible)
/* 3718 */       return false; 
/* 3720 */     if (this.domainCrosshairVisible != that.domainCrosshairVisible)
/* 3721 */       return false; 
/* 3723 */     if (this.domainCrosshairValue != that.domainCrosshairValue)
/* 3724 */       return false; 
/* 3726 */     if (this.domainCrosshairLockedOnData != that.domainCrosshairLockedOnData)
/* 3728 */       return false; 
/* 3730 */     if (this.rangeCrosshairVisible != that.rangeCrosshairVisible)
/* 3731 */       return false; 
/* 3733 */     if (this.rangeCrosshairValue != that.rangeCrosshairValue)
/* 3734 */       return false; 
/* 3736 */     if (!ObjectUtilities.equal(this.axisOffset, that.axisOffset))
/* 3737 */       return false; 
/* 3739 */     if (!ObjectUtilities.equal(this.renderers, that.renderers))
/* 3740 */       return false; 
/* 3742 */     if (!ObjectUtilities.equal(this.rangeAxes, that.rangeAxes))
/* 3743 */       return false; 
/* 3745 */     if (!this.rangeAxisLocations.equals(that.rangeAxisLocations))
/* 3746 */       return false; 
/* 3748 */     if (!ObjectUtilities.equal(this.datasetToDomainAxisMap, that.datasetToDomainAxisMap))
/* 3751 */       return false; 
/* 3753 */     if (!ObjectUtilities.equal(this.datasetToRangeAxisMap, that.datasetToRangeAxisMap))
/* 3756 */       return false; 
/* 3758 */     if (!ObjectUtilities.equal(this.domainGridlineStroke, that.domainGridlineStroke))
/* 3760 */       return false; 
/* 3762 */     if (!PaintUtilities.equal(this.domainGridlinePaint, that.domainGridlinePaint))
/* 3764 */       return false; 
/* 3766 */     if (!ObjectUtilities.equal(this.rangeGridlineStroke, that.rangeGridlineStroke))
/* 3768 */       return false; 
/* 3770 */     if (!PaintUtilities.equal(this.rangeGridlinePaint, that.rangeGridlinePaint))
/* 3772 */       return false; 
/* 3774 */     if (!PaintUtilities.equal(this.rangeZeroBaselinePaint, that.rangeZeroBaselinePaint))
/* 3777 */       return false; 
/* 3779 */     if (!ObjectUtilities.equal(this.rangeZeroBaselineStroke, that.rangeZeroBaselineStroke))
/* 3782 */       return false; 
/* 3784 */     if (!ObjectUtilities.equal(this.domainCrosshairStroke, that.domainCrosshairStroke))
/* 3787 */       return false; 
/* 3789 */     if (!PaintUtilities.equal(this.domainCrosshairPaint, that.domainCrosshairPaint))
/* 3792 */       return false; 
/* 3794 */     if (!ObjectUtilities.equal(this.rangeCrosshairStroke, that.rangeCrosshairStroke))
/* 3797 */       return false; 
/* 3799 */     if (!PaintUtilities.equal(this.rangeCrosshairPaint, that.rangeCrosshairPaint))
/* 3802 */       return false; 
/* 3804 */     if (!ObjectUtilities.equal(this.foregroundDomainMarkers, that.foregroundDomainMarkers))
/* 3807 */       return false; 
/* 3809 */     if (!ObjectUtilities.equal(this.backgroundDomainMarkers, that.backgroundDomainMarkers))
/* 3812 */       return false; 
/* 3814 */     if (!ObjectUtilities.equal(this.foregroundRangeMarkers, that.foregroundRangeMarkers))
/* 3817 */       return false; 
/* 3819 */     if (!ObjectUtilities.equal(this.backgroundRangeMarkers, that.backgroundRangeMarkers))
/* 3822 */       return false; 
/* 3824 */     if (!ObjectUtilities.equal(this.foregroundDomainMarkers, that.foregroundDomainMarkers))
/* 3827 */       return false; 
/* 3829 */     if (!ObjectUtilities.equal(this.backgroundDomainMarkers, that.backgroundDomainMarkers))
/* 3832 */       return false; 
/* 3834 */     if (!ObjectUtilities.equal(this.foregroundRangeMarkers, that.foregroundRangeMarkers))
/* 3837 */       return false; 
/* 3839 */     if (!ObjectUtilities.equal(this.backgroundRangeMarkers, that.backgroundRangeMarkers))
/* 3842 */       return false; 
/* 3844 */     if (!ObjectUtilities.equal(this.annotations, that.annotations))
/* 3847 */       return false; 
/* 3849 */     return true;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 3863 */     XYPlot clone = (XYPlot)super.clone();
/* 3864 */     clone.domainAxes = (ObjectList)ObjectUtilities.clone(this.domainAxes);
/*      */     int i;
/* 3865 */     for (i = 0; i < this.domainAxes.size(); i++) {
/* 3866 */       ValueAxis axis = (ValueAxis)this.domainAxes.get(i);
/* 3867 */       if (axis != null) {
/* 3868 */         ValueAxis clonedAxis = (ValueAxis)axis.clone();
/* 3869 */         clone.domainAxes.set(i, clonedAxis);
/* 3870 */         clonedAxis.setPlot(clone);
/* 3871 */         clonedAxis.addChangeListener(clone);
/*      */       } 
/*      */     } 
/* 3874 */     clone.domainAxisLocations = (ObjectList)this.domainAxisLocations.clone();
/* 3877 */     clone.rangeAxes = (ObjectList)ObjectUtilities.clone(this.rangeAxes);
/* 3878 */     for (i = 0; i < this.rangeAxes.size(); i++) {
/* 3879 */       ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
/* 3880 */       if (axis != null) {
/* 3881 */         ValueAxis clonedAxis = (ValueAxis)axis.clone();
/* 3882 */         clone.rangeAxes.set(i, clonedAxis);
/* 3883 */         clonedAxis.setPlot(clone);
/* 3884 */         clonedAxis.addChangeListener(clone);
/*      */       } 
/*      */     } 
/* 3887 */     clone.rangeAxisLocations = (ObjectList)ObjectUtilities.clone(this.rangeAxisLocations);
/* 3891 */     clone.datasets = (ObjectList)ObjectUtilities.clone(this.datasets);
/* 3892 */     for (i = 0; i < clone.datasets.size(); i++) {
/* 3893 */       XYDataset d = getDataset(i);
/* 3894 */       if (d != null)
/* 3895 */         d.addChangeListener(clone); 
/*      */     } 
/* 3899 */     clone.datasetToDomainAxisMap = new TreeMap();
/* 3900 */     clone.datasetToDomainAxisMap.putAll(this.datasetToDomainAxisMap);
/* 3901 */     clone.datasetToRangeAxisMap = new TreeMap();
/* 3902 */     clone.datasetToRangeAxisMap.putAll(this.datasetToRangeAxisMap);
/* 3904 */     clone.renderers = (ObjectList)ObjectUtilities.clone(this.renderers);
/* 3905 */     for (i = 0; i < this.renderers.size(); i++) {
/* 3906 */       XYItemRenderer renderer2 = (XYItemRenderer)this.renderers.get(i);
/* 3907 */       if (renderer2 instanceof PublicCloneable) {
/* 3908 */         PublicCloneable pc = (PublicCloneable)renderer2;
/* 3909 */         clone.renderers.set(i, pc.clone());
/*      */       } 
/*      */     } 
/* 3912 */     clone.foregroundDomainMarkers = (Map)ObjectUtilities.clone(this.foregroundDomainMarkers);
/* 3915 */     clone.backgroundDomainMarkers = (Map)ObjectUtilities.clone(this.backgroundDomainMarkers);
/* 3918 */     clone.foregroundRangeMarkers = (Map)ObjectUtilities.clone(this.foregroundRangeMarkers);
/* 3921 */     clone.backgroundRangeMarkers = (Map)ObjectUtilities.clone(this.backgroundRangeMarkers);
/* 3924 */     clone.annotations = (List)ObjectUtilities.deepClone(this.annotations);
/* 3925 */     if (this.fixedDomainAxisSpace != null)
/* 3926 */       clone.fixedDomainAxisSpace = (AxisSpace)ObjectUtilities.clone(this.fixedDomainAxisSpace); 
/* 3930 */     if (this.fixedRangeAxisSpace != null)
/* 3931 */       clone.fixedRangeAxisSpace = (AxisSpace)ObjectUtilities.clone(this.fixedRangeAxisSpace); 
/* 3935 */     return clone;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 3947 */     stream.defaultWriteObject();
/* 3948 */     SerialUtilities.writeStroke(this.domainGridlineStroke, stream);
/* 3949 */     SerialUtilities.writePaint(this.domainGridlinePaint, stream);
/* 3950 */     SerialUtilities.writeStroke(this.rangeGridlineStroke, stream);
/* 3951 */     SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
/* 3952 */     SerialUtilities.writeStroke(this.rangeZeroBaselineStroke, stream);
/* 3953 */     SerialUtilities.writePaint(this.rangeZeroBaselinePaint, stream);
/* 3954 */     SerialUtilities.writeStroke(this.domainCrosshairStroke, stream);
/* 3955 */     SerialUtilities.writePaint(this.domainCrosshairPaint, stream);
/* 3956 */     SerialUtilities.writeStroke(this.rangeCrosshairStroke, stream);
/* 3957 */     SerialUtilities.writePaint(this.rangeCrosshairPaint, stream);
/* 3958 */     SerialUtilities.writePaint(this.domainTickBandPaint, stream);
/* 3959 */     SerialUtilities.writePaint(this.rangeTickBandPaint, stream);
/* 3960 */     SerialUtilities.writePoint2D(this.quadrantOrigin, stream);
/* 3961 */     for (int i = 0; i < 4; i++)
/* 3962 */       SerialUtilities.writePaint(this.quadrantPaint[i], stream); 
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 3977 */     stream.defaultReadObject();
/* 3978 */     this.domainGridlineStroke = SerialUtilities.readStroke(stream);
/* 3979 */     this.domainGridlinePaint = SerialUtilities.readPaint(stream);
/* 3980 */     this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
/* 3981 */     this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
/* 3982 */     this.rangeZeroBaselineStroke = SerialUtilities.readStroke(stream);
/* 3983 */     this.rangeZeroBaselinePaint = SerialUtilities.readPaint(stream);
/* 3984 */     this.domainCrosshairStroke = SerialUtilities.readStroke(stream);
/* 3985 */     this.domainCrosshairPaint = SerialUtilities.readPaint(stream);
/* 3986 */     this.rangeCrosshairStroke = SerialUtilities.readStroke(stream);
/* 3987 */     this.rangeCrosshairPaint = SerialUtilities.readPaint(stream);
/* 3988 */     this.domainTickBandPaint = SerialUtilities.readPaint(stream);
/* 3989 */     this.rangeTickBandPaint = SerialUtilities.readPaint(stream);
/* 3990 */     this.quadrantOrigin = SerialUtilities.readPoint2D(stream);
/* 3991 */     this.quadrantPaint = new Paint[4];
/* 3992 */     for (int i = 0; i < 4; i++)
/* 3993 */       this.quadrantPaint[i] = SerialUtilities.readPaint(stream); 
/* 3998 */     int domainAxisCount = this.domainAxes.size();
/* 3999 */     for (int j = 0; j < domainAxisCount; j++) {
/* 4000 */       Axis axis = (Axis)this.domainAxes.get(j);
/* 4001 */       if (axis != null) {
/* 4002 */         axis.setPlot(this);
/* 4003 */         axis.addChangeListener(this);
/*      */       } 
/*      */     } 
/* 4006 */     int rangeAxisCount = this.rangeAxes.size();
/* 4007 */     for (int k = 0; k < rangeAxisCount; k++) {
/* 4008 */       Axis axis = (Axis)this.rangeAxes.get(k);
/* 4009 */       if (axis != null) {
/* 4010 */         axis.setPlot(this);
/* 4011 */         axis.addChangeListener(this);
/*      */       } 
/*      */     } 
/* 4014 */     int datasetCount = this.datasets.size();
/* 4015 */     for (int m = 0; m < datasetCount; m++) {
/* 4016 */       Dataset dataset = (Dataset)this.datasets.get(m);
/* 4017 */       if (dataset != null)
/* 4018 */         dataset.addChangeListener(this); 
/*      */     } 
/* 4021 */     int rendererCount = this.renderers.size();
/* 4022 */     for (int n = 0; n < rendererCount; n++) {
/* 4023 */       XYItemRenderer renderer = (XYItemRenderer)this.renderers.get(n);
/* 4024 */       if (renderer != null)
/* 4025 */         renderer.addChangeListener(this); 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\XYPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */