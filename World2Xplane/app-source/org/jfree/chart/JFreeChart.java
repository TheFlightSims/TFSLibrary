/*      */ package org.jfree.chart;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.Paint;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import org.jfree.chart.block.BlockBorder;
/*      */ import org.jfree.chart.block.BlockParams;
/*      */ import org.jfree.chart.block.EntityBlockResult;
/*      */ import org.jfree.chart.block.LengthConstraintType;
/*      */ import org.jfree.chart.block.RectangleConstraint;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.event.ChartChangeEvent;
/*      */ import org.jfree.chart.event.ChartChangeListener;
/*      */ import org.jfree.chart.event.ChartProgressEvent;
/*      */ import org.jfree.chart.event.ChartProgressListener;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.event.PlotChangeListener;
/*      */ import org.jfree.chart.event.TitleChangeEvent;
/*      */ import org.jfree.chart.event.TitleChangeListener;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.chart.title.LegendTitle;
/*      */ import org.jfree.chart.title.TextTitle;
/*      */ import org.jfree.chart.title.Title;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.Align;
/*      */ import org.jfree.ui.Drawable;
/*      */ import org.jfree.ui.HorizontalAlignment;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.Size2D;
/*      */ import org.jfree.ui.VerticalAlignment;
/*      */ import org.jfree.ui.about.ProjectInfo;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ 
/*      */ public class JFreeChart implements Drawable, TitleChangeListener, PlotChangeListener, Serializable, Cloneable {
/*      */   private static final long serialVersionUID = -3470703747817429120L;
/*      */   
/*  230 */   public static final ProjectInfo INFO = new JFreeChartInfo();
/*      */   
/*  233 */   public static final Font DEFAULT_TITLE_FONT = new Font("SansSerif", 1, 18);
/*      */   
/*  237 */   public static final Paint DEFAULT_BACKGROUND_PAINT = UIManager.getColor("Panel.background");
/*      */   
/*  241 */   public static final Image DEFAULT_BACKGROUND_IMAGE = null;
/*      */   
/*      */   public static final int DEFAULT_BACKGROUND_IMAGE_ALIGNMENT = 15;
/*      */   
/*      */   public static final float DEFAULT_BACKGROUND_IMAGE_ALPHA = 0.5F;
/*      */   
/*      */   private transient RenderingHints renderingHints;
/*      */   
/*      */   private boolean borderVisible;
/*      */   
/*      */   private transient Stroke borderStroke;
/*      */   
/*      */   private transient Paint borderPaint;
/*      */   
/*      */   private RectangleInsets padding;
/*      */   
/*      */   private TextTitle title;
/*      */   
/*      */   private List subtitles;
/*      */   
/*      */   private Plot plot;
/*      */   
/*      */   private transient Paint backgroundPaint;
/*      */   
/*      */   private transient Image backgroundImage;
/*      */   
/*  283 */   private int backgroundImageAlignment = 15;
/*      */   
/*  286 */   private float backgroundImageAlpha = 0.5F;
/*      */   
/*      */   private transient EventListenerList changeListeners;
/*      */   
/*      */   private transient EventListenerList progressListeners;
/*      */   
/*      */   private boolean notify;
/*      */   
/*      */   public JFreeChart(Plot plot) {
/*  313 */     this(null, null, plot, true);
/*      */   }
/*      */   
/*      */   public JFreeChart(String title, Plot plot) {
/*  335 */     this(title, DEFAULT_TITLE_FONT, plot, true);
/*      */   }
/*      */   
/*      */   public JFreeChart(String title, Font titleFont, Plot plot, boolean createLegend) {
/*  358 */     if (plot == null)
/*  359 */       throw new NullPointerException("Null 'plot' argument."); 
/*  363 */     this.progressListeners = new EventListenerList();
/*  364 */     this.changeListeners = new EventListenerList();
/*  365 */     this.notify = true;
/*  368 */     this.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  373 */     this.borderVisible = false;
/*  374 */     this.borderStroke = new BasicStroke(1.0F);
/*  375 */     this.borderPaint = Color.black;
/*  377 */     this.padding = RectangleInsets.ZERO_INSETS;
/*  379 */     this.plot = plot;
/*  380 */     plot.addChangeListener(this);
/*  382 */     this.subtitles = new ArrayList();
/*  385 */     if (createLegend) {
/*  386 */       LegendTitle legend = new LegendTitle((LegendItemSource)this.plot);
/*  387 */       legend.setMargin(new RectangleInsets(1.0D, 1.0D, 1.0D, 1.0D));
/*  388 */       legend.setBorder(new BlockBorder());
/*  389 */       legend.setBackgroundPaint(Color.white);
/*  390 */       legend.setPosition(RectangleEdge.BOTTOM);
/*  391 */       this.subtitles.add(legend);
/*      */     } 
/*  395 */     if (title != null) {
/*  396 */       if (titleFont == null)
/*  397 */         titleFont = DEFAULT_TITLE_FONT; 
/*  399 */       this.title = new TextTitle(title, titleFont);
/*  400 */       this.title.addChangeListener(this);
/*      */     } 
/*  403 */     this.backgroundPaint = DEFAULT_BACKGROUND_PAINT;
/*  405 */     this.backgroundImage = DEFAULT_BACKGROUND_IMAGE;
/*  406 */     this.backgroundImageAlignment = 15;
/*  407 */     this.backgroundImageAlpha = 0.5F;
/*      */   }
/*      */   
/*      */   public RenderingHints getRenderingHints() {
/*  417 */     return this.renderingHints;
/*      */   }
/*      */   
/*      */   public void setRenderingHints(RenderingHints renderingHints) {
/*  429 */     if (renderingHints == null)
/*  430 */       throw new NullPointerException("RenderingHints given are null"); 
/*  432 */     this.renderingHints = renderingHints;
/*  433 */     fireChartChanged();
/*      */   }
/*      */   
/*      */   public boolean isBorderVisible() {
/*  443 */     return this.borderVisible;
/*      */   }
/*      */   
/*      */   public void setBorderVisible(boolean visible) {
/*  453 */     this.borderVisible = visible;
/*  454 */     fireChartChanged();
/*      */   }
/*      */   
/*      */   public Stroke getBorderStroke() {
/*  463 */     return this.borderStroke;
/*      */   }
/*      */   
/*      */   public void setBorderStroke(Stroke stroke) {
/*  472 */     this.borderStroke = stroke;
/*  473 */     fireChartChanged();
/*      */   }
/*      */   
/*      */   public Paint getBorderPaint() {
/*  482 */     return this.borderPaint;
/*      */   }
/*      */   
/*      */   public void setBorderPaint(Paint paint) {
/*  491 */     this.borderPaint = paint;
/*  492 */     fireChartChanged();
/*      */   }
/*      */   
/*      */   public RectangleInsets getPadding() {
/*  501 */     return this.padding;
/*      */   }
/*      */   
/*      */   public void setPadding(RectangleInsets padding) {
/*  511 */     if (padding == null)
/*  512 */       throw new IllegalArgumentException("Null 'padding' argument."); 
/*  514 */     this.padding = padding;
/*  515 */     notifyListeners(new ChartChangeEvent(this));
/*      */   }
/*      */   
/*      */   public TextTitle getTitle() {
/*  527 */     return this.title;
/*      */   }
/*      */   
/*      */   public void setTitle(TextTitle title) {
/*  539 */     this.title = title;
/*  540 */     fireChartChanged();
/*      */   }
/*      */   
/*      */   public void setTitle(String text) {
/*  554 */     if (text != null) {
/*  555 */       if (this.title == null) {
/*  556 */         setTitle(new TextTitle(text, DEFAULT_TITLE_FONT));
/*      */       } else {
/*  559 */         this.title.setText(text);
/*      */       } 
/*      */     } else {
/*  563 */       setTitle((TextTitle)null);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addLegend(LegendTitle legend) {
/*  574 */     addSubtitle((Title)legend);
/*      */   }
/*      */   
/*      */   public LegendTitle getLegend() {
/*  584 */     return getLegend(0);
/*      */   }
/*      */   
/*      */   public LegendTitle getLegend(int index) {
/*  595 */     int seen = 0;
/*  596 */     Iterator iterator = this.subtitles.iterator();
/*  597 */     while (iterator.hasNext()) {
/*  598 */       Title subtitle = iterator.next();
/*  599 */       if (subtitle instanceof LegendTitle) {
/*  600 */         if (seen == index)
/*  601 */           return (LegendTitle)subtitle; 
/*  604 */         seen++;
/*      */       } 
/*      */     } 
/*  608 */     return null;
/*      */   }
/*      */   
/*      */   public void removeLegend() {
/*  616 */     removeSubtitle((Title)getLegend());
/*      */   }
/*      */   
/*      */   public List getSubtitles() {
/*  625 */     return this.subtitles;
/*      */   }
/*      */   
/*      */   public void setSubtitles(List subtitles) {
/*  636 */     if (subtitles == null)
/*  637 */       throw new NullPointerException("Null 'subtitles' argument."); 
/*  639 */     this.subtitles = subtitles;
/*  640 */     fireChartChanged();
/*      */   }
/*      */   
/*      */   public int getSubtitleCount() {
/*  649 */     return this.subtitles.size();
/*      */   }
/*      */   
/*      */   public Title getSubtitle(int index) {
/*  660 */     if (index < 0 || index == getSubtitleCount())
/*  661 */       throw new IllegalArgumentException("Index out of range."); 
/*  663 */     return this.subtitles.get(index);
/*      */   }
/*      */   
/*      */   public void addSubtitle(Title subtitle) {
/*  673 */     if (subtitle == null)
/*  674 */       throw new IllegalArgumentException("Null 'subtitle' argument."); 
/*  676 */     this.subtitles.add(subtitle);
/*  677 */     subtitle.addChangeListener(this);
/*  678 */     fireChartChanged();
/*      */   }
/*      */   
/*      */   public void clearSubtitles() {
/*  686 */     Iterator iterator = this.subtitles.iterator();
/*  687 */     while (iterator.hasNext()) {
/*  688 */       Title t = iterator.next();
/*  689 */       t.removeChangeListener(this);
/*      */     } 
/*  691 */     this.subtitles.clear();
/*  692 */     fireChartChanged();
/*      */   }
/*      */   
/*      */   public void removeSubtitle(Title title) {
/*  702 */     this.subtitles.remove(title);
/*  703 */     fireChartChanged();
/*      */   }
/*      */   
/*      */   public Plot getPlot() {
/*  714 */     return this.plot;
/*      */   }
/*      */   
/*      */   public CategoryPlot getCategoryPlot() {
/*  726 */     return (CategoryPlot)this.plot;
/*      */   }
/*      */   
/*      */   public XYPlot getXYPlot() {
/*  738 */     return (XYPlot)this.plot;
/*      */   }
/*      */   
/*      */   public boolean getAntiAlias() {
/*  748 */     Object o = this.renderingHints.get(RenderingHints.KEY_ANTIALIASING);
/*  749 */     if (o == null)
/*  750 */       return false; 
/*  752 */     return o.equals(RenderingHints.VALUE_ANTIALIAS_ON);
/*      */   }
/*      */   
/*      */   public void setAntiAlias(boolean flag) {
/*  765 */     Object o = this.renderingHints.get(RenderingHints.KEY_ANTIALIASING);
/*  766 */     if (o == null)
/*  767 */       o = RenderingHints.VALUE_ANTIALIAS_DEFAULT; 
/*  769 */     if ((!flag && RenderingHints.VALUE_ANTIALIAS_OFF.equals(o)) || (flag && RenderingHints.VALUE_ANTIALIAS_ON.equals(o)))
/*      */       return; 
/*  774 */     if (flag) {
/*  775 */       this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*      */     } else {
/*  779 */       this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*      */     } 
/*  782 */     fireChartChanged();
/*      */   }
/*      */   
/*      */   public Paint getBackgroundPaint() {
/*  792 */     return this.backgroundPaint;
/*      */   }
/*      */   
/*      */   public void setBackgroundPaint(Paint paint) {
/*  803 */     if (this.backgroundPaint != null) {
/*  804 */       if (!this.backgroundPaint.equals(paint)) {
/*  805 */         this.backgroundPaint = paint;
/*  806 */         fireChartChanged();
/*      */       } 
/*  810 */     } else if (paint != null) {
/*  811 */       this.backgroundPaint = paint;
/*  812 */       fireChartChanged();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Image getBackgroundImage() {
/*  825 */     return this.backgroundImage;
/*      */   }
/*      */   
/*      */   public void setBackgroundImage(Image image) {
/*  836 */     if (this.backgroundImage != null) {
/*  837 */       if (!this.backgroundImage.equals(image)) {
/*  838 */         this.backgroundImage = image;
/*  839 */         fireChartChanged();
/*      */       } 
/*  843 */     } else if (image != null) {
/*  844 */       this.backgroundImage = image;
/*  845 */       fireChartChanged();
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getBackgroundImageAlignment() {
/*  859 */     return this.backgroundImageAlignment;
/*      */   }
/*      */   
/*      */   public void setBackgroundImageAlignment(int alignment) {
/*  869 */     if (this.backgroundImageAlignment != alignment) {
/*  870 */       this.backgroundImageAlignment = alignment;
/*  871 */       fireChartChanged();
/*      */     } 
/*      */   }
/*      */   
/*      */   public float getBackgroundImageAlpha() {
/*  881 */     return this.backgroundImageAlpha;
/*      */   }
/*      */   
/*      */   public void setBackgroundImageAlpha(float alpha) {
/*  892 */     if (this.backgroundImageAlpha != alpha) {
/*  893 */       this.backgroundImageAlpha = alpha;
/*  894 */       fireChartChanged();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isNotify() {
/*  906 */     return this.notify;
/*      */   }
/*      */   
/*      */   public void setNotify(boolean notify) {
/*  916 */     this.notify = notify;
/*  918 */     if (notify)
/*  919 */       notifyListeners(new ChartChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2, Rectangle2D area) {
/*  933 */     draw(g2, area, null, null);
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2, Rectangle2D area, ChartRenderingInfo info) {
/*  945 */     draw(g2, area, null, info);
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2, Rectangle2D chartArea, Point2D anchor, ChartRenderingInfo info) {
/*  964 */     notifyListeners(new ChartProgressEvent(this, this, 1, 0));
/*  971 */     if (info != null) {
/*  972 */       info.clear();
/*  973 */       info.setChartArea(chartArea);
/*      */     } 
/*  977 */     Shape savedClip = g2.getClip();
/*  978 */     g2.clip(chartArea);
/*  980 */     g2.addRenderingHints(this.renderingHints);
/*  983 */     if (this.backgroundPaint != null) {
/*  984 */       g2.setPaint(this.backgroundPaint);
/*  985 */       g2.fill(chartArea);
/*      */     } 
/*  988 */     if (this.backgroundImage != null) {
/*  989 */       Composite originalComposite = g2.getComposite();
/*  990 */       g2.setComposite(AlphaComposite.getInstance(3, this.backgroundImageAlpha));
/*  996 */       Rectangle2D dest = new Rectangle2D.Double(0.0D, 0.0D, this.backgroundImage.getWidth(null), this.backgroundImage.getHeight(null));
/* 1000 */       Align.align(dest, chartArea, this.backgroundImageAlignment);
/* 1001 */       g2.drawImage(this.backgroundImage, (int)dest.getX(), (int)dest.getY(), (int)dest.getWidth(), (int)dest.getHeight(), null);
/* 1005 */       g2.setComposite(originalComposite);
/*      */     } 
/* 1008 */     if (isBorderVisible()) {
/* 1009 */       Paint paint = getBorderPaint();
/* 1010 */       Stroke stroke = getBorderStroke();
/* 1011 */       if (paint != null && stroke != null) {
/* 1012 */         Rectangle2D borderArea = new Rectangle2D.Double(chartArea.getX(), chartArea.getY(), chartArea.getWidth() - 1.0D, chartArea.getHeight() - 1.0D);
/* 1016 */         g2.setPaint(paint);
/* 1017 */         g2.setStroke(stroke);
/* 1018 */         g2.draw(borderArea);
/*      */       } 
/*      */     } 
/* 1023 */     Rectangle2D nonTitleArea = new Rectangle2D.Double();
/* 1024 */     nonTitleArea.setRect(chartArea);
/* 1025 */     this.padding.trim(nonTitleArea);
/* 1027 */     EntityCollection entities = null;
/* 1028 */     if (info != null)
/* 1029 */       entities = info.getEntityCollection(); 
/* 1031 */     if (this.title != null) {
/* 1032 */       EntityCollection e = drawTitle((Title)this.title, g2, nonTitleArea, (entities != null));
/* 1035 */       if (e != null)
/* 1036 */         entities.addAll(e); 
/*      */     } 
/* 1040 */     Iterator iterator = this.subtitles.iterator();
/* 1041 */     while (iterator.hasNext()) {
/* 1042 */       Title currentTitle = iterator.next();
/* 1043 */       EntityCollection e = drawTitle(currentTitle, g2, nonTitleArea, (entities != null));
/* 1046 */       if (e != null)
/* 1047 */         entities.addAll(e); 
/*      */     } 
/* 1051 */     Rectangle2D plotArea = nonTitleArea;
/* 1054 */     PlotRenderingInfo plotInfo = null;
/* 1055 */     if (info != null)
/* 1056 */       plotInfo = info.getPlotInfo(); 
/* 1058 */     this.plot.draw(g2, plotArea, anchor, null, plotInfo);
/* 1060 */     g2.setClip(savedClip);
/* 1062 */     notifyListeners(new ChartProgressEvent(this, this, 2, 100));
/*      */   }
/*      */   
/*      */   private Rectangle2D createAlignedRectangle2D(Size2D dimensions, Rectangle2D frame, HorizontalAlignment hAlign, VerticalAlignment vAlign) {
/* 1082 */     double x = Double.NaN;
/* 1083 */     double y = Double.NaN;
/* 1084 */     if (hAlign == HorizontalAlignment.LEFT) {
/* 1085 */       x = frame.getX();
/* 1087 */     } else if (hAlign == HorizontalAlignment.CENTER) {
/* 1088 */       x = frame.getCenterX() - dimensions.width / 2.0D;
/* 1090 */     } else if (hAlign == HorizontalAlignment.RIGHT) {
/* 1091 */       x = frame.getMaxX() - dimensions.width;
/*      */     } 
/* 1093 */     if (vAlign == VerticalAlignment.TOP) {
/* 1094 */       y = frame.getY();
/* 1096 */     } else if (vAlign == VerticalAlignment.CENTER) {
/* 1097 */       y = frame.getCenterY() - dimensions.height / 2.0D;
/* 1099 */     } else if (vAlign == VerticalAlignment.BOTTOM) {
/* 1100 */       y = frame.getMaxY() - dimensions.height;
/*      */     } 
/* 1103 */     return new Rectangle2D.Double(x, y, dimensions.width, dimensions.height);
/*      */   }
/*      */   
/*      */   protected EntityCollection drawTitle(Title t, Graphics2D g2, Rectangle2D area, boolean entities) {
/* 1125 */     if (t == null)
/* 1126 */       throw new IllegalArgumentException("Null 't' argument."); 
/* 1128 */     if (area == null)
/* 1129 */       throw new IllegalArgumentException("Null 'area' argument."); 
/* 1131 */     Rectangle2D titleArea = new Rectangle2D.Double();
/* 1132 */     RectangleEdge position = t.getPosition();
/* 1133 */     double ww = area.getWidth();
/* 1134 */     if (ww <= 0.0D)
/* 1135 */       return null; 
/* 1137 */     double hh = area.getHeight();
/* 1138 */     if (hh <= 0.0D)
/* 1139 */       return null; 
/* 1141 */     RectangleConstraint constraint = new RectangleConstraint(ww, new Range(0.0D, ww), LengthConstraintType.RANGE, hh, new Range(0.0D, hh), LengthConstraintType.RANGE);
/* 1145 */     Object retValue = null;
/* 1146 */     BlockParams p = new BlockParams();
/* 1147 */     p.setGenerateEntities(entities);
/* 1148 */     if (position == RectangleEdge.TOP) {
/* 1149 */       Size2D size = t.arrange(g2, constraint);
/* 1150 */       titleArea = createAlignedRectangle2D(size, area, t.getHorizontalAlignment(), VerticalAlignment.TOP);
/* 1153 */       retValue = t.draw(g2, titleArea, p);
/* 1154 */       area.setRect(area.getX(), Math.min(area.getY() + size.height, area.getMaxY()), area.getWidth(), Math.max(area.getHeight() - size.height, 0.0D));
/* 1160 */     } else if (position == RectangleEdge.BOTTOM) {
/* 1161 */       Size2D size = t.arrange(g2, constraint);
/* 1162 */       titleArea = createAlignedRectangle2D(size, area, t.getHorizontalAlignment(), VerticalAlignment.BOTTOM);
/* 1165 */       retValue = t.draw(g2, titleArea, p);
/* 1166 */       area.setRect(area.getX(), area.getY(), area.getWidth(), area.getHeight() - size.height);
/* 1171 */     } else if (position == RectangleEdge.RIGHT) {
/* 1172 */       Size2D size = t.arrange(g2, constraint);
/* 1173 */       titleArea = createAlignedRectangle2D(size, area, HorizontalAlignment.RIGHT, t.getVerticalAlignment());
/* 1176 */       retValue = t.draw(g2, titleArea, p);
/* 1177 */       area.setRect(area.getX(), area.getY(), area.getWidth() - size.width, area.getHeight());
/* 1183 */     } else if (position == RectangleEdge.LEFT) {
/* 1184 */       Size2D size = t.arrange(g2, constraint);
/* 1185 */       titleArea = createAlignedRectangle2D(size, area, HorizontalAlignment.LEFT, t.getVerticalAlignment());
/* 1188 */       retValue = t.draw(g2, titleArea, p);
/* 1189 */       area.setRect(area.getX() + size.width, area.getY(), area.getWidth() - size.width, area.getHeight());
/*      */     } else {
/* 1195 */       throw new RuntimeException("Unrecognised title position.");
/*      */     } 
/* 1197 */     EntityCollection result = null;
/* 1198 */     if (retValue instanceof EntityBlockResult) {
/* 1199 */       EntityBlockResult ebr = (EntityBlockResult)retValue;
/* 1200 */       result = ebr.getEntityCollection();
/*      */     } 
/* 1202 */     return result;
/*      */   }
/*      */   
/*      */   public BufferedImage createBufferedImage(int width, int height) {
/* 1214 */     return createBufferedImage(width, height, null);
/*      */   }
/*      */   
/*      */   public BufferedImage createBufferedImage(int width, int height, ChartRenderingInfo info) {
/* 1229 */     return createBufferedImage(width, height, 1, info);
/*      */   }
/*      */   
/*      */   public BufferedImage createBufferedImage(int width, int height, int imageType, ChartRenderingInfo info) {
/* 1248 */     BufferedImage image = new BufferedImage(width, height, imageType);
/* 1249 */     Graphics2D g2 = image.createGraphics();
/* 1250 */     draw(g2, new Rectangle2D.Double(0.0D, 0.0D, width, height), null, info);
/* 1251 */     g2.dispose();
/* 1252 */     return image;
/*      */   }
/*      */   
/*      */   public BufferedImage createBufferedImage(int imageWidth, int imageHeight, double drawWidth, double drawHeight, ChartRenderingInfo info) {
/* 1275 */     BufferedImage image = new BufferedImage(imageWidth, imageHeight, 1);
/* 1278 */     Graphics2D g2 = image.createGraphics();
/* 1279 */     double scaleX = imageWidth / drawWidth;
/* 1280 */     double scaleY = imageHeight / drawHeight;
/* 1281 */     AffineTransform st = AffineTransform.getScaleInstance(scaleX, scaleY);
/* 1282 */     g2.transform(st);
/* 1283 */     draw(g2, new Rectangle2D.Double(0.0D, 0.0D, drawWidth, drawHeight), null, info);
/* 1286 */     g2.dispose();
/* 1287 */     return image;
/*      */   }
/*      */   
/*      */   public void handleClick(int x, int y, ChartRenderingInfo info) {
/* 1307 */     this.plot.handleClick(x, y, info.getPlotInfo());
/*      */   }
/*      */   
/*      */   public void addChangeListener(ChartChangeListener listener) {
/* 1317 */     if (listener == null)
/* 1318 */       throw new IllegalArgumentException("Null 'listener' argument."); 
/* 1320 */     this.changeListeners.add(ChartChangeListener.class, listener);
/*      */   }
/*      */   
/*      */   public void removeChangeListener(ChartChangeListener listener) {
/* 1329 */     if (listener == null)
/* 1330 */       throw new IllegalArgumentException("Null 'listener' argument."); 
/* 1332 */     this.changeListeners.remove(ChartChangeListener.class, listener);
/*      */   }
/*      */   
/*      */   public void fireChartChanged() {
/* 1341 */     ChartChangeEvent event = new ChartChangeEvent(this);
/* 1342 */     notifyListeners(event);
/*      */   }
/*      */   
/*      */   protected void notifyListeners(ChartChangeEvent event) {
/* 1352 */     if (this.notify) {
/* 1353 */       Object[] listeners = this.changeListeners.getListenerList();
/* 1354 */       for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 1355 */         if (listeners[i] == ChartChangeListener.class)
/* 1356 */           ((ChartChangeListener)listeners[i + 1]).chartChanged(event); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addProgressListener(ChartProgressListener listener) {
/* 1371 */     this.progressListeners.add(ChartProgressListener.class, listener);
/*      */   }
/*      */   
/*      */   public void removeProgressListener(ChartProgressListener listener) {
/* 1380 */     this.progressListeners.remove(ChartProgressListener.class, listener);
/*      */   }
/*      */   
/*      */   protected void notifyListeners(ChartProgressEvent event) {
/* 1391 */     Object[] listeners = this.progressListeners.getListenerList();
/* 1392 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 1393 */       if (listeners[i] == ChartProgressListener.class)
/* 1394 */         ((ChartProgressListener)listeners[i + 1]).chartProgress(event); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void titleChanged(TitleChangeEvent event) {
/* 1407 */     event.setChart(this);
/* 1408 */     notifyListeners((ChartChangeEvent)event);
/*      */   }
/*      */   
/*      */   public void plotChanged(PlotChangeEvent event) {
/* 1418 */     event.setChart(this);
/* 1419 */     notifyListeners((ChartChangeEvent)event);
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1430 */     if (obj == this)
/* 1431 */       return true; 
/* 1433 */     if (!(obj instanceof JFreeChart))
/* 1434 */       return false; 
/* 1436 */     JFreeChart that = (JFreeChart)obj;
/* 1437 */     if (!this.renderingHints.equals(that.renderingHints))
/* 1438 */       return false; 
/* 1440 */     if (this.borderVisible != that.borderVisible)
/* 1441 */       return false; 
/* 1443 */     if (!ObjectUtilities.equal(this.borderStroke, that.borderStroke))
/* 1444 */       return false; 
/* 1446 */     if (!PaintUtilities.equal(this.borderPaint, that.borderPaint))
/* 1447 */       return false; 
/* 1449 */     if (!this.padding.equals(that.padding))
/* 1450 */       return false; 
/* 1452 */     if (!ObjectUtilities.equal(this.title, that.title))
/* 1453 */       return false; 
/* 1455 */     if (!ObjectUtilities.equal(this.subtitles, that.subtitles))
/* 1456 */       return false; 
/* 1458 */     if (!ObjectUtilities.equal(this.plot, that.plot))
/* 1459 */       return false; 
/* 1461 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint))
/* 1464 */       return false; 
/* 1466 */     if (!ObjectUtilities.equal(this.backgroundImage, that.backgroundImage))
/* 1469 */       return false; 
/* 1471 */     if (this.backgroundImageAlignment != that.backgroundImageAlignment)
/* 1472 */       return false; 
/* 1474 */     if (this.backgroundImageAlpha != that.backgroundImageAlpha)
/* 1475 */       return false; 
/* 1477 */     if (this.notify != that.notify)
/* 1478 */       return false; 
/* 1480 */     return true;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1491 */     stream.defaultWriteObject();
/* 1492 */     SerialUtilities.writeStroke(this.borderStroke, stream);
/* 1493 */     SerialUtilities.writePaint(this.borderPaint, stream);
/* 1494 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1507 */     stream.defaultReadObject();
/* 1508 */     this.borderStroke = SerialUtilities.readStroke(stream);
/* 1509 */     this.borderPaint = SerialUtilities.readPaint(stream);
/* 1510 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/* 1511 */     this.progressListeners = new EventListenerList();
/* 1512 */     this.changeListeners = new EventListenerList();
/* 1513 */     this.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 1518 */     if (this.title != null)
/* 1519 */       this.title.addChangeListener(this); 
/* 1522 */     for (int i = 0; i < getSubtitleCount(); i++)
/* 1523 */       getSubtitle(i).addChangeListener(this); 
/* 1525 */     this.plot.addChangeListener(this);
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/* 1534 */     System.out.println(INFO.toString());
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1546 */     JFreeChart chart = (JFreeChart)super.clone();
/* 1548 */     chart.renderingHints = (RenderingHints)this.renderingHints.clone();
/* 1553 */     if (this.title != null) {
/* 1554 */       chart.title = (TextTitle)this.title.clone();
/* 1555 */       chart.title.addChangeListener(chart);
/*      */     } 
/* 1558 */     chart.subtitles = new ArrayList();
/* 1559 */     for (int i = 0; i < getSubtitleCount(); i++) {
/* 1560 */       Title subtitle = (Title)getSubtitle(i).clone();
/* 1561 */       chart.subtitles.add(subtitle);
/* 1562 */       subtitle.addChangeListener(chart);
/*      */     } 
/* 1565 */     if (this.plot != null) {
/* 1566 */       chart.plot = (Plot)this.plot.clone();
/* 1567 */       chart.plot.addChangeListener(chart);
/*      */     } 
/* 1570 */     chart.progressListeners = new EventListenerList();
/* 1571 */     chart.changeListeners = new EventListenerList();
/* 1572 */     return chart;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\JFreeChart.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */