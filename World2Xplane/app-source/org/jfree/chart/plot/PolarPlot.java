/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Point;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.axis.AxisState;
/*      */ import org.jfree.chart.axis.NumberTick;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeListener;
/*      */ import org.jfree.chart.renderer.PolarItemRenderer;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.general.Dataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ 
/*      */ public class PolarPlot extends Plot implements ValueAxisPlot, Zoomable, RendererChangeListener, Cloneable, Serializable {
/*      */   private static final long serialVersionUID = 3794383185924179525L;
/*      */   
/*      */   private static final int MARGIN = 20;
/*      */   
/*      */   private static final double ANNOTATION_MARGIN = 7.0D;
/*      */   
/*  115 */   public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5F, 0, 2, 0.0F, new float[] { 2.0F, 2.0F }, 0.0F);
/*      */   
/*  122 */   public static final Paint DEFAULT_GRIDLINE_PAINT = Color.gray;
/*      */   
/*  125 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */   private List angleTicks;
/*      */   
/*      */   private ValueAxis axis;
/*      */   
/*      */   private XYDataset dataset;
/*      */   
/*      */   private PolarItemRenderer renderer;
/*      */   
/*      */   private boolean angleLabelsVisible = true;
/*      */   
/*  150 */   private Font angleLabelFont = new Font("SansSerif", 0, 12);
/*      */   
/*  153 */   private Paint angleLabelPaint = Color.black;
/*      */   
/*      */   private boolean angleGridlinesVisible;
/*      */   
/*      */   private transient Stroke angleGridlineStroke;
/*      */   
/*      */   private transient Paint angleGridlinePaint;
/*      */   
/*      */   private boolean radiusGridlinesVisible;
/*      */   
/*      */   private transient Stroke radiusGridlineStroke;
/*      */   
/*      */   private transient Paint radiusGridlinePaint;
/*      */   
/*  174 */   private List cornerTextItems = new ArrayList();
/*      */   
/*      */   public PolarPlot() {
/*  183 */     this((XYDataset)null, (ValueAxis)null, (PolarItemRenderer)null);
/*      */   }
/*      */   
/*      */   public PolarPlot(XYDataset dataset, ValueAxis radiusAxis, PolarItemRenderer renderer) {
/*  199 */     this.dataset = dataset;
/*  200 */     if (this.dataset != null)
/*  201 */       this.dataset.addChangeListener(this); 
/*  204 */     this.angleTicks = new ArrayList();
/*  205 */     this.angleTicks.add(new NumberTick(new Double(0.0D), "0", TextAnchor.CENTER, TextAnchor.CENTER, 0.0D));
/*  207 */     this.angleTicks.add(new NumberTick(new Double(45.0D), "45", TextAnchor.CENTER, TextAnchor.CENTER, 0.0D));
/*  209 */     this.angleTicks.add(new NumberTick(new Double(90.0D), "90", TextAnchor.CENTER, TextAnchor.CENTER, 0.0D));
/*  211 */     this.angleTicks.add(new NumberTick(new Double(135.0D), "135", TextAnchor.CENTER, TextAnchor.CENTER, 0.0D));
/*  213 */     this.angleTicks.add(new NumberTick(new Double(180.0D), "180", TextAnchor.CENTER, TextAnchor.CENTER, 0.0D));
/*  215 */     this.angleTicks.add(new NumberTick(new Double(225.0D), "225", TextAnchor.CENTER, TextAnchor.CENTER, 0.0D));
/*  217 */     this.angleTicks.add(new NumberTick(new Double(270.0D), "270", TextAnchor.CENTER, TextAnchor.CENTER, 0.0D));
/*  219 */     this.angleTicks.add(new NumberTick(new Double(315.0D), "315", TextAnchor.CENTER, TextAnchor.CENTER, 0.0D));
/*  222 */     this.axis = radiusAxis;
/*  223 */     if (this.axis != null) {
/*  224 */       this.axis.setPlot(this);
/*  225 */       this.axis.addChangeListener(this);
/*      */     } 
/*  228 */     this.renderer = renderer;
/*  229 */     if (this.renderer != null) {
/*  230 */       this.renderer.setPlot(this);
/*  231 */       this.renderer.addChangeListener(this);
/*      */     } 
/*  234 */     this.angleGridlinesVisible = true;
/*  235 */     this.angleGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  236 */     this.angleGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*  238 */     this.radiusGridlinesVisible = true;
/*  239 */     this.radiusGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  240 */     this.radiusGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*      */   }
/*      */   
/*      */   public void addCornerTextItem(String text) {
/*  249 */     if (text == null)
/*  250 */       throw new IllegalArgumentException("Null 'text' argument."); 
/*  252 */     this.cornerTextItems.add(text);
/*  253 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void removeCornerTextItem(String text) {
/*  262 */     boolean removed = this.cornerTextItems.remove(text);
/*  263 */     if (removed)
/*  264 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void clearCornerTextItems() {
/*  272 */     if (this.cornerTextItems.size() > 0) {
/*  273 */       this.cornerTextItems.clear();
/*  274 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getPlotType() {
/*  284 */     return localizationResources.getString("Polar_Plot");
/*      */   }
/*      */   
/*      */   public ValueAxis getAxis() {
/*  293 */     return this.axis;
/*      */   }
/*      */   
/*      */   public void setAxis(ValueAxis axis) {
/*  303 */     if (axis != null)
/*  304 */       axis.setPlot(this); 
/*  308 */     if (this.axis != null)
/*  309 */       this.axis.removeChangeListener(this); 
/*  312 */     this.axis = axis;
/*  313 */     if (this.axis != null) {
/*  314 */       this.axis.configure();
/*  315 */       this.axis.addChangeListener(this);
/*      */     } 
/*  317 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public XYDataset getDataset() {
/*  326 */     return this.dataset;
/*      */   }
/*      */   
/*      */   public void setDataset(XYDataset dataset) {
/*  338 */     XYDataset existing = this.dataset;
/*  339 */     if (existing != null)
/*  340 */       existing.removeChangeListener(this); 
/*  344 */     this.dataset = dataset;
/*  345 */     if (this.dataset != null) {
/*  346 */       setDatasetGroup(this.dataset.getGroup());
/*  347 */       this.dataset.addChangeListener(this);
/*      */     } 
/*  351 */     DatasetChangeEvent event = new DatasetChangeEvent(this, (Dataset)this.dataset);
/*  352 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */   public PolarItemRenderer getRenderer() {
/*  361 */     return this.renderer;
/*      */   }
/*      */   
/*      */   public void setRenderer(PolarItemRenderer renderer) {
/*  373 */     if (this.renderer != null)
/*  374 */       this.renderer.removeChangeListener(this); 
/*  377 */     this.renderer = renderer;
/*  378 */     if (this.renderer != null)
/*  379 */       this.renderer.setPlot(this); 
/*  382 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isAngleLabelsVisible() {
/*  391 */     return this.angleLabelsVisible;
/*      */   }
/*      */   
/*      */   public void setAngleLabelsVisible(boolean visible) {
/*  401 */     if (this.angleLabelsVisible != visible) {
/*  402 */       this.angleLabelsVisible = visible;
/*  403 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Font getAngleLabelFont() {
/*  413 */     return this.angleLabelFont;
/*      */   }
/*      */   
/*      */   public void setAngleLabelFont(Font font) {
/*  423 */     if (font == null)
/*  424 */       throw new IllegalArgumentException("Null 'font' argument."); 
/*  426 */     this.angleLabelFont = font;
/*  427 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getAngleLabelPaint() {
/*  436 */     return this.angleLabelPaint;
/*      */   }
/*      */   
/*      */   public void setAngleLabelPaint(Paint paint) {
/*  446 */     this.angleLabelPaint = paint;
/*  447 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isAngleGridlinesVisible() {
/*  457 */     return this.angleGridlinesVisible;
/*      */   }
/*      */   
/*      */   public void setAngleGridlinesVisible(boolean visible) {
/*  470 */     if (this.angleGridlinesVisible != visible) {
/*  471 */       this.angleGridlinesVisible = visible;
/*  472 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Stroke getAngleGridlineStroke() {
/*  483 */     return this.angleGridlineStroke;
/*      */   }
/*      */   
/*      */   public void setAngleGridlineStroke(Stroke stroke) {
/*  494 */     this.angleGridlineStroke = stroke;
/*  495 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getAngleGridlinePaint() {
/*  505 */     return this.angleGridlinePaint;
/*      */   }
/*      */   
/*      */   public void setAngleGridlinePaint(Paint paint) {
/*  516 */     this.angleGridlinePaint = paint;
/*  517 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isRadiusGridlinesVisible() {
/*  527 */     return this.radiusGridlinesVisible;
/*      */   }
/*      */   
/*      */   public void setRadiusGridlinesVisible(boolean visible) {
/*  540 */     if (this.radiusGridlinesVisible != visible) {
/*  541 */       this.radiusGridlinesVisible = visible;
/*  542 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Stroke getRadiusGridlineStroke() {
/*  553 */     return this.radiusGridlineStroke;
/*      */   }
/*      */   
/*      */   public void setRadiusGridlineStroke(Stroke stroke) {
/*  564 */     this.radiusGridlineStroke = stroke;
/*  565 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getRadiusGridlinePaint() {
/*  575 */     return this.radiusGridlinePaint;
/*      */   }
/*      */   
/*      */   public void setRadiusGridlinePaint(Paint paint) {
/*  586 */     this.radiusGridlinePaint = paint;
/*  587 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/*  618 */     boolean b1 = (area.getWidth() <= 10.0D);
/*  619 */     boolean b2 = (area.getHeight() <= 10.0D);
/*  620 */     if (b1 || b2)
/*      */       return; 
/*  625 */     if (info != null)
/*  626 */       info.setPlotArea(area); 
/*  630 */     RectangleInsets insets = getInsets();
/*  631 */     insets.trim(area);
/*  633 */     Rectangle2D dataArea = area;
/*  634 */     if (info != null)
/*  635 */       info.setDataArea(dataArea); 
/*  639 */     drawBackground(g2, dataArea);
/*  640 */     double h = Math.min(dataArea.getWidth() / 2.0D, dataArea.getHeight() / 2.0D) - 20.0D;
/*  643 */     Rectangle2D quadrant = new Rectangle2D.Double(dataArea.getCenterX(), dataArea.getCenterY(), h, h);
/*  646 */     AxisState state = drawAxis(g2, area, quadrant);
/*  647 */     if (this.renderer != null) {
/*  648 */       Shape originalClip = g2.getClip();
/*  649 */       Composite originalComposite = g2.getComposite();
/*  651 */       g2.clip(dataArea);
/*  652 */       g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*  658 */       drawGridlines(g2, dataArea, this.angleTicks, state.getTicks());
/*  661 */       render(g2, dataArea, info);
/*  663 */       g2.setClip(originalClip);
/*  664 */       g2.setComposite(originalComposite);
/*      */     } 
/*  666 */     drawOutline(g2, dataArea);
/*  667 */     drawCornerTextItems(g2, dataArea);
/*      */   }
/*      */   
/*      */   protected void drawCornerTextItems(Graphics2D g2, Rectangle2D area) {
/*  677 */     if (this.cornerTextItems.isEmpty())
/*      */       return; 
/*  681 */     g2.setColor(Color.black);
/*  682 */     double width = 0.0D;
/*  683 */     double height = 0.0D;
/*  684 */     for (Iterator it = this.cornerTextItems.iterator(); it.hasNext(); ) {
/*  685 */       String msg = it.next();
/*  686 */       FontMetrics fm = g2.getFontMetrics();
/*  687 */       Rectangle2D bounds = TextUtilities.getTextBounds(msg, g2, fm);
/*  688 */       width = Math.max(width, bounds.getWidth());
/*  689 */       height += bounds.getHeight();
/*      */     } 
/*  692 */     double xadj = 14.0D;
/*  693 */     double yadj = 7.0D;
/*  694 */     width += xadj;
/*  695 */     height += yadj;
/*  697 */     double x = area.getMaxX() - width;
/*  698 */     double y = area.getMaxY() - height;
/*  699 */     g2.drawRect((int)x, (int)y, (int)width, (int)height);
/*  700 */     x += 7.0D;
/*  701 */     for (Iterator iterator1 = this.cornerTextItems.iterator(); iterator1.hasNext(); ) {
/*  702 */       String msg = iterator1.next();
/*  703 */       Rectangle2D bounds = TextUtilities.getTextBounds(msg, g2, g2.getFontMetrics());
/*  706 */       y += bounds.getHeight();
/*  707 */       g2.drawString(msg, (int)x, (int)y);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected AxisState drawAxis(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea) {
/*  722 */     return this.axis.draw(g2, dataArea.getMinY(), plotArea, dataArea, RectangleEdge.TOP, null);
/*      */   }
/*      */   
/*      */   protected void render(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info) {
/*  742 */     if (!DatasetUtilities.isEmptyOrNull(this.dataset)) {
/*  743 */       int seriesCount = this.dataset.getSeriesCount();
/*  744 */       for (int series = 0; series < seriesCount; series++)
/*  745 */         this.renderer.drawSeries(g2, dataArea, info, this, this.dataset, series); 
/*      */     } else {
/*  751 */       drawNoDataMessage(g2, dataArea);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawGridlines(Graphics2D g2, Rectangle2D dataArea, List angularTicks, List radialTicks) {
/*  767 */     if (this.renderer == null)
/*      */       return; 
/*  772 */     if (isAngleGridlinesVisible()) {
/*  773 */       Stroke gridStroke = getAngleGridlineStroke();
/*  774 */       Paint gridPaint = getAngleGridlinePaint();
/*  775 */       if (gridStroke != null && gridPaint != null)
/*  776 */         this.renderer.drawAngularGridLines(g2, this, angularTicks, dataArea); 
/*      */     } 
/*  783 */     if (isRadiusGridlinesVisible()) {
/*  784 */       Stroke gridStroke = getRadiusGridlineStroke();
/*  785 */       Paint gridPaint = getRadiusGridlinePaint();
/*  786 */       if (gridStroke != null && gridPaint != null)
/*  787 */         this.renderer.drawRadialGridLines(g2, this, this.axis, radialTicks, dataArea); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void zoom(double percent) {
/*  800 */     if (percent > 0.0D) {
/*  801 */       double radius = getMaxRadius();
/*  802 */       double scaledRadius = radius * percent;
/*  803 */       this.axis.setUpperBound(scaledRadius);
/*  804 */       getAxis().setAutoRange(false);
/*      */     } else {
/*  807 */       getAxis().setAutoRange(true);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Range getDataRange(ValueAxis axis) {
/*  819 */     Range result = null;
/*  820 */     if (this.dataset != null)
/*  821 */       result = Range.combine(result, DatasetUtilities.findRangeBounds(this.dataset)); 
/*  824 */     return result;
/*      */   }
/*      */   
/*      */   public void datasetChanged(DatasetChangeEvent event) {
/*  836 */     if (this.axis != null)
/*  837 */       this.axis.configure(); 
/*  840 */     if (getParent() != null) {
/*  841 */       getParent().datasetChanged(event);
/*      */     } else {
/*  844 */       super.datasetChanged(event);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void rendererChanged(RendererChangeEvent event) {
/*  856 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public int getSeriesCount() {
/*  866 */     int result = 0;
/*  868 */     if (this.dataset != null)
/*  869 */       result = this.dataset.getSeriesCount(); 
/*  871 */     return result;
/*      */   }
/*      */   
/*      */   public LegendItemCollection getLegendItems() {
/*  882 */     LegendItemCollection result = new LegendItemCollection();
/*  885 */     if (this.dataset != null && 
/*  886 */       this.renderer != null) {
/*  887 */       int seriesCount = this.dataset.getSeriesCount();
/*  888 */       for (int i = 0; i < seriesCount; i++) {
/*  889 */         LegendItem item = this.renderer.getLegendItem(i);
/*  890 */         result.add(item);
/*      */       } 
/*      */     } 
/*  894 */     return result;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/*  905 */     if (obj == this)
/*  906 */       return true; 
/*  908 */     if (!(obj instanceof PolarPlot))
/*  909 */       return false; 
/*  911 */     if (!super.equals(obj))
/*  912 */       return false; 
/*  914 */     PolarPlot that = (PolarPlot)obj;
/*  915 */     if (!ObjectUtilities.equal(this.axis, that.axis))
/*  916 */       return false; 
/*  918 */     if (!ObjectUtilities.equal(this.renderer, that.renderer))
/*  919 */       return false; 
/*  921 */     if (this.angleGridlinesVisible != that.angleGridlinesVisible)
/*  922 */       return false; 
/*  924 */     if (this.angleLabelsVisible != that.angleLabelsVisible)
/*  925 */       return false; 
/*  927 */     if (!this.angleLabelFont.equals(that.angleLabelFont))
/*  928 */       return false; 
/*  930 */     if (!PaintUtilities.equal(this.angleLabelPaint, that.angleLabelPaint))
/*  931 */       return false; 
/*  933 */     if (!ObjectUtilities.equal(this.angleGridlineStroke, that.angleGridlineStroke))
/*  936 */       return false; 
/*  938 */     if (!PaintUtilities.equal(this.angleGridlinePaint, that.angleGridlinePaint))
/*  941 */       return false; 
/*  943 */     if (this.radiusGridlinesVisible != that.radiusGridlinesVisible)
/*  944 */       return false; 
/*  946 */     if (!ObjectUtilities.equal(this.radiusGridlineStroke, that.radiusGridlineStroke))
/*  949 */       return false; 
/*  951 */     if (!PaintUtilities.equal(this.radiusGridlinePaint, that.radiusGridlinePaint))
/*  954 */       return false; 
/*  956 */     return true;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/*  969 */     PolarPlot clone = (PolarPlot)super.clone();
/*  970 */     if (this.axis != null) {
/*  971 */       clone.axis = (ValueAxis)ObjectUtilities.clone(this.axis);
/*  972 */       clone.axis.setPlot(clone);
/*  973 */       clone.axis.addChangeListener(clone);
/*      */     } 
/*  976 */     if (clone.dataset != null)
/*  977 */       clone.dataset.addChangeListener(clone); 
/*  980 */     if (this.renderer != null)
/*  981 */       clone.renderer = (PolarItemRenderer)ObjectUtilities.clone(this.renderer); 
/*  985 */     return clone;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/*  996 */     stream.defaultWriteObject();
/*  997 */     SerialUtilities.writeStroke(this.angleGridlineStroke, stream);
/*  998 */     SerialUtilities.writePaint(this.angleGridlinePaint, stream);
/*  999 */     SerialUtilities.writeStroke(this.radiusGridlineStroke, stream);
/* 1000 */     SerialUtilities.writePaint(this.radiusGridlinePaint, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1014 */     stream.defaultReadObject();
/* 1015 */     this.angleGridlineStroke = SerialUtilities.readStroke(stream);
/* 1016 */     this.angleGridlinePaint = SerialUtilities.readPaint(stream);
/* 1017 */     this.radiusGridlineStroke = SerialUtilities.readStroke(stream);
/* 1018 */     this.radiusGridlinePaint = SerialUtilities.readPaint(stream);
/* 1020 */     if (this.axis != null) {
/* 1021 */       this.axis.setPlot(this);
/* 1022 */       this.axis.addChangeListener(this);
/*      */     } 
/* 1025 */     if (this.dataset != null)
/* 1026 */       this.dataset.addChangeListener(this); 
/*      */   }
/*      */   
/*      */   public void zoomDomainAxes(double factor, PlotRenderingInfo state, Point2D source) {}
/*      */   
/*      */   public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source) {}
/*      */   
/*      */   public void zoomRangeAxes(double factor, PlotRenderingInfo state, Point2D source) {
/* 1066 */     zoom(factor);
/*      */   }
/*      */   
/*      */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source) {
/* 1079 */     zoom((upperPercent + lowerPercent) / 2.0D);
/*      */   }
/*      */   
/*      */   public boolean isDomainZoomable() {
/* 1088 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isRangeZoomable() {
/* 1097 */     return true;
/*      */   }
/*      */   
/*      */   public PlotOrientation getOrientation() {
/* 1106 */     return PlotOrientation.HORIZONTAL;
/*      */   }
/*      */   
/*      */   public double getMaxRadius() {
/* 1120 */     return this.axis.getUpperBound();
/*      */   }
/*      */   
/*      */   public Point translateValueThetaRadiusToJava2D(double angleDegrees, double radius, Rectangle2D dataArea) {
/* 1136 */     double radians = Math.toRadians(angleDegrees - 90.0D);
/* 1138 */     double minx = dataArea.getMinX() + 20.0D;
/* 1139 */     double maxx = dataArea.getMaxX() - 20.0D;
/* 1140 */     double miny = dataArea.getMinY() + 20.0D;
/* 1141 */     double maxy = dataArea.getMaxY() - 20.0D;
/* 1143 */     double lengthX = maxx - minx;
/* 1144 */     double lengthY = maxy - miny;
/* 1145 */     double length = Math.min(lengthX, lengthY);
/* 1147 */     double midX = minx + lengthX / 2.0D;
/* 1148 */     double midY = miny + lengthY / 2.0D;
/* 1150 */     double axisMin = this.axis.getLowerBound();
/* 1151 */     double axisMax = getMaxRadius();
/* 1153 */     double xv = length / 2.0D * Math.cos(radians);
/* 1154 */     double yv = length / 2.0D * Math.sin(radians);
/* 1156 */     float x = (float)(midX + xv * (radius - axisMin) / (axisMax - axisMin));
/* 1158 */     float y = (float)(midY + yv * (radius - axisMin) / (axisMax - axisMin));
/* 1161 */     int ix = Math.round(x);
/* 1162 */     int iy = Math.round(y);
/* 1164 */     Point p = new Point(ix, iy);
/* 1165 */     return p;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\PolarPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */