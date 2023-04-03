/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.LegendItemSource;
/*      */ import org.jfree.chart.axis.AxisLocation;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.event.AxisChangeListener;
/*      */ import org.jfree.chart.event.ChartChangeEventType;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.event.PlotChangeListener;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetChangeListener;
/*      */ import org.jfree.data.general.DatasetGroup;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.G2TextMeasurer;
/*      */ import org.jfree.text.TextBlock;
/*      */ import org.jfree.text.TextBlockAnchor;
/*      */ import org.jfree.text.TextMeasurer;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.Align;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ 
/*      */ public abstract class Plot implements AxisChangeListener, DatasetChangeListener, LegendItemSource, PublicCloneable, Cloneable, Serializable {
/*      */   private static final long serialVersionUID = -8831571430103671324L;
/*      */   
/*  180 */   public static final Number ZERO = new Integer(0);
/*      */   
/*  183 */   public static final RectangleInsets DEFAULT_INSETS = new RectangleInsets(4.0D, 8.0D, 4.0D, 8.0D);
/*      */   
/*  187 */   public static final Stroke DEFAULT_OUTLINE_STROKE = new BasicStroke(0.5F);
/*      */   
/*  190 */   public static final Paint DEFAULT_OUTLINE_PAINT = Color.gray;
/*      */   
/*      */   public static final float DEFAULT_FOREGROUND_ALPHA = 1.0F;
/*      */   
/*      */   public static final float DEFAULT_BACKGROUND_ALPHA = 1.0F;
/*      */   
/*  199 */   public static final Paint DEFAULT_BACKGROUND_PAINT = Color.white;
/*      */   
/*      */   public static final int MINIMUM_WIDTH_TO_DRAW = 10;
/*      */   
/*      */   public static final int MINIMUM_HEIGHT_TO_DRAW = 10;
/*      */   
/*  208 */   public static final Shape DEFAULT_LEGEND_ITEM_BOX = new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D);
/*      */   
/*  212 */   public static final Shape DEFAULT_LEGEND_ITEM_CIRCLE = new Ellipse2D.Double(-4.0D, -4.0D, 8.0D, 8.0D);
/*      */   
/*      */   private Plot parent;
/*      */   
/*      */   private DatasetGroup datasetGroup;
/*      */   
/*      */   private String noDataMessage;
/*      */   
/*      */   private Font noDataMessageFont;
/*      */   
/*      */   private transient Paint noDataMessagePaint;
/*      */   
/*      */   private RectangleInsets insets;
/*      */   
/*      */   private transient Stroke outlineStroke;
/*      */   
/*      */   private transient Paint outlinePaint;
/*      */   
/*      */   private transient Paint backgroundPaint;
/*      */   
/*      */   private transient Image backgroundImage;
/*      */   
/*  246 */   private int backgroundImageAlignment = 15;
/*      */   
/*      */   private float foregroundAlpha;
/*      */   
/*      */   private float backgroundAlpha;
/*      */   
/*      */   private DrawingSupplier drawingSupplier;
/*      */   
/*      */   private transient EventListenerList listenerList;
/*      */   
/*      */   protected Plot() {
/*  265 */     this.parent = null;
/*  266 */     this.insets = DEFAULT_INSETS;
/*  267 */     this.backgroundPaint = DEFAULT_BACKGROUND_PAINT;
/*  268 */     this.backgroundAlpha = 1.0F;
/*  269 */     this.backgroundImage = null;
/*  270 */     this.outlineStroke = DEFAULT_OUTLINE_STROKE;
/*  271 */     this.outlinePaint = DEFAULT_OUTLINE_PAINT;
/*  272 */     this.foregroundAlpha = 1.0F;
/*  274 */     this.noDataMessage = null;
/*  275 */     this.noDataMessageFont = new Font("SansSerif", 0, 12);
/*  276 */     this.noDataMessagePaint = Color.black;
/*  278 */     this.drawingSupplier = new DefaultDrawingSupplier();
/*  280 */     this.listenerList = new EventListenerList();
/*      */   }
/*      */   
/*      */   public DatasetGroup getDatasetGroup() {
/*  290 */     return this.datasetGroup;
/*      */   }
/*      */   
/*      */   protected void setDatasetGroup(DatasetGroup group) {
/*  299 */     this.datasetGroup = group;
/*      */   }
/*      */   
/*      */   public String getNoDataMessage() {
/*  309 */     return this.noDataMessage;
/*      */   }
/*      */   
/*      */   public void setNoDataMessage(String message) {
/*  318 */     this.noDataMessage = message;
/*      */   }
/*      */   
/*      */   public Font getNoDataMessageFont() {
/*  327 */     return this.noDataMessageFont;
/*      */   }
/*      */   
/*      */   public void setNoDataMessageFont(Font font) {
/*  336 */     this.noDataMessageFont = font;
/*      */   }
/*      */   
/*      */   public Paint getNoDataMessagePaint() {
/*  345 */     return this.noDataMessagePaint;
/*      */   }
/*      */   
/*      */   public void setNoDataMessagePaint(Paint paint) {
/*  354 */     this.noDataMessagePaint = paint;
/*      */   }
/*      */   
/*      */   public Plot getParent() {
/*  374 */     return this.parent;
/*      */   }
/*      */   
/*      */   public void setParent(Plot parent) {
/*  383 */     this.parent = parent;
/*      */   }
/*      */   
/*      */   public Plot getRootPlot() {
/*  393 */     Plot p = getParent();
/*  394 */     if (p == null)
/*  395 */       return this; 
/*  398 */     return p.getRootPlot();
/*      */   }
/*      */   
/*      */   public boolean isSubplot() {
/*  410 */     return (getParent() != null);
/*      */   }
/*      */   
/*      */   public RectangleInsets getInsets() {
/*  419 */     return this.insets;
/*      */   }
/*      */   
/*      */   public void setInsets(RectangleInsets insets) {
/*  429 */     setInsets(insets, true);
/*      */   }
/*      */   
/*      */   public void setInsets(RectangleInsets insets, boolean notify) {
/*  441 */     if (insets == null)
/*  442 */       throw new IllegalArgumentException("Null 'insets' argument."); 
/*  444 */     if (!this.insets.equals(insets)) {
/*  445 */       this.insets = insets;
/*  446 */       if (notify)
/*  447 */         notifyListeners(new PlotChangeEvent(this)); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Paint getBackgroundPaint() {
/*  459 */     return this.backgroundPaint;
/*      */   }
/*      */   
/*      */   public void setBackgroundPaint(Paint paint) {
/*  470 */     if (paint == null) {
/*  471 */       if (this.backgroundPaint != null) {
/*  472 */         this.backgroundPaint = null;
/*  473 */         notifyListeners(new PlotChangeEvent(this));
/*      */       } 
/*      */     } else {
/*  477 */       if (this.backgroundPaint != null && 
/*  478 */         this.backgroundPaint.equals(paint))
/*      */         return; 
/*  482 */       this.backgroundPaint = paint;
/*  483 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public float getBackgroundAlpha() {
/*  494 */     return this.backgroundAlpha;
/*      */   }
/*      */   
/*      */   public void setBackgroundAlpha(float alpha) {
/*  505 */     if (this.backgroundAlpha != alpha) {
/*  506 */       this.backgroundAlpha = alpha;
/*  507 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public DrawingSupplier getDrawingSupplier() {
/*  518 */     DrawingSupplier result = null;
/*  519 */     Plot p = getParent();
/*  520 */     if (p != null) {
/*  521 */       result = p.getDrawingSupplier();
/*      */     } else {
/*  524 */       result = this.drawingSupplier;
/*      */     } 
/*  526 */     return result;
/*      */   }
/*      */   
/*      */   public void setDrawingSupplier(DrawingSupplier supplier) {
/*  538 */     this.drawingSupplier = supplier;
/*  539 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Image getBackgroundImage() {
/*  549 */     return this.backgroundImage;
/*      */   }
/*      */   
/*      */   public void setBackgroundImage(Image image) {
/*  558 */     this.backgroundImage = image;
/*  559 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public int getBackgroundImageAlignment() {
/*  570 */     return this.backgroundImageAlignment;
/*      */   }
/*      */   
/*      */   public void setBackgroundImageAlignment(int alignment) {
/*  582 */     if (this.backgroundImageAlignment != alignment) {
/*  583 */       this.backgroundImageAlignment = alignment;
/*  584 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Stroke getOutlineStroke() {
/*  594 */     return this.outlineStroke;
/*      */   }
/*      */   
/*      */   public void setOutlineStroke(Stroke stroke) {
/*  606 */     if (stroke == null) {
/*  607 */       if (this.outlineStroke != null) {
/*  608 */         this.outlineStroke = null;
/*  609 */         notifyListeners(new PlotChangeEvent(this));
/*      */       } 
/*      */     } else {
/*  613 */       if (this.outlineStroke != null && 
/*  614 */         this.outlineStroke.equals(stroke))
/*      */         return; 
/*  618 */       this.outlineStroke = stroke;
/*  619 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Paint getOutlinePaint() {
/*  630 */     return this.outlinePaint;
/*      */   }
/*      */   
/*      */   public void setOutlinePaint(Paint paint) {
/*  642 */     if (paint == null) {
/*  643 */       if (this.outlinePaint != null) {
/*  644 */         this.outlinePaint = null;
/*  645 */         notifyListeners(new PlotChangeEvent(this));
/*      */       } 
/*      */     } else {
/*  649 */       if (this.outlinePaint != null && 
/*  650 */         this.outlinePaint.equals(paint))
/*      */         return; 
/*  654 */       this.outlinePaint = paint;
/*  655 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public float getForegroundAlpha() {
/*  666 */     return this.foregroundAlpha;
/*      */   }
/*      */   
/*      */   public void setForegroundAlpha(float alpha) {
/*  676 */     if (this.foregroundAlpha != alpha) {
/*  677 */       this.foregroundAlpha = alpha;
/*  678 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public LegendItemCollection getLegendItems() {
/*  691 */     return null;
/*      */   }
/*      */   
/*      */   public void addChangeListener(PlotChangeListener listener) {
/*  700 */     this.listenerList.add(PlotChangeListener.class, listener);
/*      */   }
/*      */   
/*      */   public void removeChangeListener(PlotChangeListener listener) {
/*  709 */     this.listenerList.remove(PlotChangeListener.class, listener);
/*      */   }
/*      */   
/*      */   public void notifyListeners(PlotChangeEvent event) {
/*  719 */     Object[] listeners = this.listenerList.getListenerList();
/*  720 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/*  721 */       if (listeners[i] == PlotChangeListener.class)
/*  722 */         ((PlotChangeListener)listeners[i + 1]).plotChanged(event); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void drawBackground(Graphics2D g2, Rectangle2D area) {
/*  759 */     fillBackground(g2, area);
/*  760 */     drawBackgroundImage(g2, area);
/*      */   }
/*      */   
/*      */   protected void fillBackground(Graphics2D g2, Rectangle2D area) {
/*  770 */     if (this.backgroundPaint != null) {
/*  771 */       Composite originalComposite = g2.getComposite();
/*  772 */       g2.setComposite(AlphaComposite.getInstance(3, this.backgroundAlpha));
/*  777 */       g2.setPaint(this.backgroundPaint);
/*  778 */       g2.fill(area);
/*  779 */       g2.setComposite(originalComposite);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawBackgroundImage(Graphics2D g2, Rectangle2D area) {
/*  791 */     if (this.backgroundImage != null) {
/*  792 */       Composite originalComposite = g2.getComposite();
/*  793 */       g2.setComposite(AlphaComposite.getInstance(2, this.backgroundAlpha));
/*  796 */       Rectangle2D dest = new Rectangle2D.Double(0.0D, 0.0D, this.backgroundImage.getWidth(null), this.backgroundImage.getHeight(null));
/*  801 */       Align.align(dest, area, this.backgroundImageAlignment);
/*  802 */       g2.drawImage(this.backgroundImage, (int)dest.getX(), (int)dest.getY(), (int)dest.getWidth() + 1, (int)dest.getHeight() + 1, null);
/*  807 */       g2.setComposite(originalComposite);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void drawOutline(Graphics2D g2, Rectangle2D area) {
/*  821 */     if (this.outlineStroke != null && this.outlinePaint != null) {
/*  822 */       g2.setStroke(this.outlineStroke);
/*  823 */       g2.setPaint(this.outlinePaint);
/*  824 */       g2.draw(area);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawNoDataMessage(Graphics2D g2, Rectangle2D area) {
/*  836 */     Shape savedClip = g2.getClip();
/*  837 */     g2.clip(area);
/*  838 */     String message = this.noDataMessage;
/*  839 */     if (message != null) {
/*  840 */       g2.setFont(this.noDataMessageFont);
/*  841 */       g2.setPaint(this.noDataMessagePaint);
/*  842 */       TextBlock block = TextUtilities.createTextBlock(this.noDataMessage, this.noDataMessageFont, this.noDataMessagePaint, 0.9F * (float)area.getWidth(), (TextMeasurer)new G2TextMeasurer(g2));
/*  847 */       block.draw(g2, (float)area.getCenterX(), (float)area.getCenterY(), TextBlockAnchor.CENTER);
/*      */     } 
/*  852 */     g2.setClip(savedClip);
/*      */   }
/*      */   
/*      */   public void handleClick(int x, int y, PlotRenderingInfo info) {}
/*      */   
/*      */   public void zoom(double percent) {}
/*      */   
/*      */   public void axisChanged(AxisChangeEvent event) {
/*  886 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void datasetChanged(DatasetChangeEvent event) {
/*  898 */     PlotChangeEvent newEvent = new PlotChangeEvent(this);
/*  899 */     newEvent.setType(ChartChangeEventType.DATASET_UPDATED);
/*  900 */     notifyListeners(newEvent);
/*      */   }
/*      */   
/*      */   protected double getRectX(double x, double w1, double w2, RectangleEdge edge) {
/*  916 */     double result = x;
/*  917 */     if (edge == RectangleEdge.LEFT) {
/*  918 */       result += w1;
/*  920 */     } else if (edge == RectangleEdge.RIGHT) {
/*  921 */       result += w2;
/*      */     } 
/*  923 */     return result;
/*      */   }
/*      */   
/*      */   protected double getRectY(double y, double h1, double h2, RectangleEdge edge) {
/*  940 */     double result = y;
/*  941 */     if (edge == RectangleEdge.TOP) {
/*  942 */       result += h1;
/*  944 */     } else if (edge == RectangleEdge.BOTTOM) {
/*  945 */       result += h2;
/*      */     } 
/*  947 */     return result;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/*  959 */     if (obj == this)
/*  960 */       return true; 
/*  962 */     if (!(obj instanceof Plot))
/*  963 */       return false; 
/*  965 */     Plot that = (Plot)obj;
/*  966 */     if (!ObjectUtilities.equal(this.noDataMessage, that.noDataMessage))
/*  967 */       return false; 
/*  969 */     if (!ObjectUtilities.equal(this.noDataMessageFont, that.noDataMessageFont))
/*  972 */       return false; 
/*  974 */     if (!PaintUtilities.equal(this.noDataMessagePaint, that.noDataMessagePaint))
/*  977 */       return false; 
/*  979 */     if (!ObjectUtilities.equal(this.insets, that.insets))
/*  980 */       return false; 
/*  982 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke))
/*  983 */       return false; 
/*  985 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint))
/*  986 */       return false; 
/*  988 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint))
/*  989 */       return false; 
/*  991 */     if (!ObjectUtilities.equal(this.backgroundImage, that.backgroundImage))
/*  994 */       return false; 
/*  996 */     if (this.backgroundImageAlignment != that.backgroundImageAlignment)
/*  997 */       return false; 
/*  999 */     if (this.foregroundAlpha != that.foregroundAlpha)
/* 1000 */       return false; 
/* 1002 */     if (this.backgroundAlpha != that.backgroundAlpha)
/* 1003 */       return false; 
/* 1005 */     if (!this.drawingSupplier.equals(that.drawingSupplier))
/* 1006 */       return false; 
/* 1008 */     return true;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1021 */     Plot clone = (Plot)super.clone();
/* 1024 */     if (this.datasetGroup != null)
/* 1025 */       clone.datasetGroup = (DatasetGroup)ObjectUtilities.clone(this.datasetGroup); 
/* 1028 */     clone.drawingSupplier = (DrawingSupplier)ObjectUtilities.clone(this.drawingSupplier);
/* 1030 */     clone.listenerList = new EventListenerList();
/* 1031 */     return clone;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1043 */     stream.defaultWriteObject();
/* 1044 */     SerialUtilities.writePaint(this.noDataMessagePaint, stream);
/* 1045 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 1046 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 1048 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1061 */     stream.defaultReadObject();
/* 1062 */     this.noDataMessagePaint = SerialUtilities.readPaint(stream);
/* 1063 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 1064 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 1066 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/* 1068 */     this.listenerList = new EventListenerList();
/*      */   }
/*      */   
/*      */   public static RectangleEdge resolveDomainAxisLocation(AxisLocation location, PlotOrientation orientation) {
/* 1083 */     if (location == null)
/* 1084 */       throw new IllegalArgumentException("Null 'location' argument."); 
/* 1086 */     if (orientation == null)
/* 1087 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1090 */     RectangleEdge result = null;
/* 1092 */     if (location == AxisLocation.TOP_OR_RIGHT) {
/* 1093 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1094 */         result = RectangleEdge.RIGHT;
/* 1096 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 1097 */         result = RectangleEdge.TOP;
/*      */       } 
/* 1100 */     } else if (location == AxisLocation.TOP_OR_LEFT) {
/* 1101 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1102 */         result = RectangleEdge.LEFT;
/* 1104 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 1105 */         result = RectangleEdge.TOP;
/*      */       } 
/* 1108 */     } else if (location == AxisLocation.BOTTOM_OR_RIGHT) {
/* 1109 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1110 */         result = RectangleEdge.RIGHT;
/* 1112 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 1113 */         result = RectangleEdge.BOTTOM;
/*      */       } 
/* 1116 */     } else if (location == AxisLocation.BOTTOM_OR_LEFT) {
/* 1117 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1118 */         result = RectangleEdge.LEFT;
/* 1120 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 1121 */         result = RectangleEdge.BOTTOM;
/*      */       } 
/*      */     } 
/* 1125 */     if (result == null)
/* 1126 */       throw new IllegalStateException("resolveDomainAxisLocation()"); 
/* 1128 */     return result;
/*      */   }
/*      */   
/*      */   public static RectangleEdge resolveRangeAxisLocation(AxisLocation location, PlotOrientation orientation) {
/* 1143 */     if (location == null)
/* 1144 */       throw new IllegalArgumentException("Null 'location' argument."); 
/* 1146 */     if (orientation == null)
/* 1147 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1150 */     RectangleEdge result = null;
/* 1152 */     if (location == AxisLocation.TOP_OR_RIGHT) {
/* 1153 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1154 */         result = RectangleEdge.TOP;
/* 1156 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 1157 */         result = RectangleEdge.RIGHT;
/*      */       } 
/* 1160 */     } else if (location == AxisLocation.TOP_OR_LEFT) {
/* 1161 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1162 */         result = RectangleEdge.TOP;
/* 1164 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 1165 */         result = RectangleEdge.LEFT;
/*      */       } 
/* 1168 */     } else if (location == AxisLocation.BOTTOM_OR_RIGHT) {
/* 1169 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1170 */         result = RectangleEdge.BOTTOM;
/* 1172 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 1173 */         result = RectangleEdge.RIGHT;
/*      */       } 
/* 1176 */     } else if (location == AxisLocation.BOTTOM_OR_LEFT) {
/* 1177 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1178 */         result = RectangleEdge.BOTTOM;
/* 1180 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 1181 */         result = RectangleEdge.LEFT;
/*      */       } 
/*      */     } 
/* 1186 */     if (result == null)
/* 1187 */       throw new IllegalStateException("resolveRangeAxisLocation()"); 
/* 1189 */     return result;
/*      */   }
/*      */   
/*      */   public abstract String getPlotType();
/*      */   
/*      */   public abstract void draw(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D, Point2D paramPoint2D, PlotState paramPlotState, PlotRenderingInfo paramPlotRenderingInfo);
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\Plot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */