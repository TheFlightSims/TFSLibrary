/*      */ package org.jfree.chart;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Point;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.awt.event.MouseMotionListener;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.print.PageFormat;
/*      */ import java.awt.print.Printable;
/*      */ import java.awt.print.PrinterException;
/*      */ import java.awt.print.PrinterJob;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.Serializable;
/*      */ import java.util.EventListener;
/*      */ import java.util.ResourceBundle;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JPopupMenu;
/*      */ import javax.swing.ToolTipManager;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import javax.swing.filechooser.FileFilter;
/*      */ import org.jfree.chart.editor.ChartEditor;
/*      */ import org.jfree.chart.editor.ChartEditorManager;
/*      */ import org.jfree.chart.entity.ChartEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.event.ChartChangeEvent;
/*      */ import org.jfree.chart.event.ChartChangeListener;
/*      */ import org.jfree.chart.event.ChartProgressEvent;
/*      */ import org.jfree.chart.event.ChartProgressListener;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.Zoomable;
/*      */ import org.jfree.ui.ExtensionFileFilter;
/*      */ 
/*      */ public class ChartPanel extends JPanel implements ChartChangeListener, ChartProgressListener, ActionListener, MouseListener, MouseMotionListener, Printable, Serializable {
/*      */   private static final long serialVersionUID = 6046366297214274674L;
/*      */   
/*      */   public static final boolean DEFAULT_BUFFER_USED = false;
/*      */   
/*      */   public static final int DEFAULT_WIDTH = 680;
/*      */   
/*      */   public static final int DEFAULT_HEIGHT = 420;
/*      */   
/*      */   public static final int DEFAULT_MINIMUM_DRAW_WIDTH = 300;
/*      */   
/*      */   public static final int DEFAULT_MINIMUM_DRAW_HEIGHT = 200;
/*      */   
/*      */   public static final int DEFAULT_MAXIMUM_DRAW_WIDTH = 800;
/*      */   
/*      */   public static final int DEFAULT_MAXIMUM_DRAW_HEIGHT = 600;
/*      */   
/*      */   public static final int DEFAULT_ZOOM_TRIGGER_DISTANCE = 10;
/*      */   
/*      */   public static final String PROPERTIES_COMMAND = "PROPERTIES";
/*      */   
/*      */   public static final String SAVE_COMMAND = "SAVE";
/*      */   
/*      */   public static final String PRINT_COMMAND = "PRINT";
/*      */   
/*      */   public static final String ZOOM_IN_BOTH_COMMAND = "ZOOM_IN_BOTH";
/*      */   
/*      */   public static final String ZOOM_IN_DOMAIN_COMMAND = "ZOOM_IN_DOMAIN";
/*      */   
/*      */   public static final String ZOOM_IN_RANGE_COMMAND = "ZOOM_IN_RANGE";
/*      */   
/*      */   public static final String ZOOM_OUT_BOTH_COMMAND = "ZOOM_OUT_BOTH";
/*      */   
/*      */   public static final String ZOOM_OUT_DOMAIN_COMMAND = "ZOOM_DOMAIN_BOTH";
/*      */   
/*      */   public static final String ZOOM_OUT_RANGE_COMMAND = "ZOOM_RANGE_BOTH";
/*      */   
/*      */   public static final String ZOOM_RESET_BOTH_COMMAND = "ZOOM_RESET_BOTH";
/*      */   
/*      */   public static final String ZOOM_RESET_DOMAIN_COMMAND = "ZOOM_RESET_DOMAIN";
/*      */   
/*      */   public static final String ZOOM_RESET_RANGE_COMMAND = "ZOOM_RESET_RANGE";
/*      */   
/*      */   private JFreeChart chart;
/*      */   
/*      */   private EventListenerList chartMouseListeners;
/*      */   
/*      */   private boolean useBuffer;
/*      */   
/*      */   private boolean refreshBuffer;
/*      */   
/*      */   private Image chartBuffer;
/*      */   
/*      */   private int chartBufferHeight;
/*      */   
/*      */   private int chartBufferWidth;
/*      */   
/*      */   private int minimumDrawWidth;
/*      */   
/*      */   private int minimumDrawHeight;
/*      */   
/*      */   private int maximumDrawWidth;
/*      */   
/*      */   private int maximumDrawHeight;
/*      */   
/*      */   private JPopupMenu popup;
/*      */   
/*      */   private ChartRenderingInfo info;
/*      */   
/*      */   private Point2D anchor;
/*      */   
/*      */   private double scaleX;
/*      */   
/*      */   private double scaleY;
/*      */   
/*  318 */   private PlotOrientation orientation = PlotOrientation.VERTICAL;
/*      */   
/*      */   private boolean domainZoomable = false;
/*      */   
/*      */   private boolean rangeZoomable = false;
/*      */   
/*  331 */   private Point zoomPoint = null;
/*      */   
/*  334 */   private transient Rectangle2D zoomRectangle = null;
/*      */   
/*      */   private boolean fillZoomRectangle = false;
/*      */   
/*      */   private int zoomTriggerDistance;
/*      */   
/*      */   private boolean horizontalAxisTrace = false;
/*      */   
/*      */   private boolean verticalAxisTrace = false;
/*      */   
/*      */   private transient Line2D verticalTraceLine;
/*      */   
/*      */   private transient Line2D horizontalTraceLine;
/*      */   
/*      */   private JMenuItem zoomInBothMenuItem;
/*      */   
/*      */   private JMenuItem zoomInDomainMenuItem;
/*      */   
/*      */   private JMenuItem zoomInRangeMenuItem;
/*      */   
/*      */   private JMenuItem zoomOutBothMenuItem;
/*      */   
/*      */   private JMenuItem zoomOutDomainMenuItem;
/*      */   
/*      */   private JMenuItem zoomOutRangeMenuItem;
/*      */   
/*      */   private JMenuItem zoomResetBothMenuItem;
/*      */   
/*      */   private JMenuItem zoomResetDomainMenuItem;
/*      */   
/*      */   private JMenuItem zoomResetRangeMenuItem;
/*      */   
/*      */   private boolean enforceFileExtensions;
/*      */   
/*      */   private boolean ownToolTipDelaysActive;
/*      */   
/*      */   private int originalToolTipInitialDelay;
/*      */   
/*      */   private int originalToolTipReshowDelay;
/*      */   
/*      */   private int originalToolTipDismissDelay;
/*      */   
/*      */   private int ownToolTipInitialDelay;
/*      */   
/*      */   private int ownToolTipReshowDelay;
/*      */   
/*      */   private int ownToolTipDismissDelay;
/*      */   
/*  406 */   private double zoomInFactor = 0.5D;
/*      */   
/*  409 */   private double zoomOutFactor = 2.0D;
/*      */   
/*  412 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.LocalizationBundle");
/*      */   
/*      */   public ChartPanel(JFreeChart chart) {
/*  422 */     this(chart, 680, 420, 300, 200, 800, 600, false, true, true, true, true, true);
/*      */   }
/*      */   
/*      */   public ChartPanel(JFreeChart chart, boolean useBuffer) {
/*  449 */     this(chart, 680, 420, 300, 200, 800, 600, useBuffer, true, true, true, true, true);
/*      */   }
/*      */   
/*      */   public ChartPanel(JFreeChart chart, boolean properties, boolean save, boolean print, boolean zoom, boolean tooltips) {
/*  488 */     this(chart, 680, 420, 300, 200, 800, 600, false, properties, save, print, zoom, tooltips);
/*      */   }
/*      */   
/*      */   public ChartPanel(JFreeChart chart, int width, int height, int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth, int maximumDrawHeight, boolean useBuffer, boolean properties, boolean save, boolean print, boolean zoom, boolean tooltips) {
/*  543 */     this.chart = chart;
/*  544 */     this.chartMouseListeners = new EventListenerList();
/*  545 */     if (chart != null) {
/*  546 */       chart.addChangeListener(this);
/*  547 */       Plot plot = chart.getPlot();
/*  548 */       this.domainZoomable = false;
/*  549 */       this.rangeZoomable = false;
/*  550 */       if (plot instanceof Zoomable) {
/*  551 */         Zoomable z = (Zoomable)plot;
/*  552 */         this.domainZoomable = z.isDomainZoomable();
/*  553 */         this.rangeZoomable = z.isRangeZoomable();
/*  554 */         this.orientation = z.getOrientation();
/*      */       } 
/*      */     } 
/*  557 */     this.info = new ChartRenderingInfo();
/*  558 */     setPreferredSize(new Dimension(width, height));
/*  559 */     this.useBuffer = useBuffer;
/*  560 */     this.refreshBuffer = false;
/*  561 */     this.minimumDrawWidth = minimumDrawWidth;
/*  562 */     this.minimumDrawHeight = minimumDrawHeight;
/*  563 */     this.maximumDrawWidth = maximumDrawWidth;
/*  564 */     this.maximumDrawHeight = maximumDrawHeight;
/*  565 */     this.zoomTriggerDistance = 10;
/*  568 */     this.popup = null;
/*  569 */     if (properties || save || print || zoom)
/*  570 */       this.popup = createPopupMenu(properties, save, print, zoom); 
/*  573 */     enableEvents(16L);
/*  574 */     enableEvents(32L);
/*  575 */     setDisplayToolTips(tooltips);
/*  576 */     addMouseListener(this);
/*  577 */     addMouseMotionListener(this);
/*  579 */     this.enforceFileExtensions = true;
/*  583 */     ToolTipManager ttm = ToolTipManager.sharedInstance();
/*  584 */     this.ownToolTipInitialDelay = ttm.getInitialDelay();
/*  585 */     this.ownToolTipDismissDelay = ttm.getDismissDelay();
/*  586 */     this.ownToolTipReshowDelay = ttm.getReshowDelay();
/*      */   }
/*      */   
/*      */   public JFreeChart getChart() {
/*  596 */     return this.chart;
/*      */   }
/*      */   
/*      */   public void setChart(JFreeChart chart) {
/*  607 */     if (this.chart != null) {
/*  608 */       this.chart.removeChangeListener(this);
/*  609 */       this.chart.removeProgressListener(this);
/*      */     } 
/*  613 */     this.chart = chart;
/*  614 */     if (chart != null) {
/*  615 */       this.chart.addChangeListener(this);
/*  616 */       this.chart.addProgressListener(this);
/*  617 */       Plot plot = chart.getPlot();
/*  618 */       this.domainZoomable = false;
/*  619 */       this.rangeZoomable = false;
/*  620 */       if (plot instanceof Zoomable) {
/*  621 */         Zoomable z = (Zoomable)plot;
/*  622 */         this.domainZoomable = z.isDomainZoomable();
/*  623 */         this.rangeZoomable = z.isRangeZoomable();
/*  624 */         this.orientation = z.getOrientation();
/*      */       } 
/*      */     } else {
/*  628 */       this.domainZoomable = false;
/*  629 */       this.rangeZoomable = false;
/*      */     } 
/*  631 */     if (this.useBuffer)
/*  632 */       this.refreshBuffer = true; 
/*  634 */     repaint();
/*      */   }
/*      */   
/*      */   public int getMinimumDrawWidth() {
/*  647 */     return this.minimumDrawWidth;
/*      */   }
/*      */   
/*      */   public void setMinimumDrawWidth(int width) {
/*  660 */     this.minimumDrawWidth = width;
/*      */   }
/*      */   
/*      */   public int getMaximumDrawWidth() {
/*  672 */     return this.maximumDrawWidth;
/*      */   }
/*      */   
/*      */   public void setMaximumDrawWidth(int width) {
/*  685 */     this.maximumDrawWidth = width;
/*      */   }
/*      */   
/*      */   public int getMinimumDrawHeight() {
/*  697 */     return this.minimumDrawHeight;
/*      */   }
/*      */   
/*      */   public void setMinimumDrawHeight(int height) {
/*  710 */     this.minimumDrawHeight = height;
/*      */   }
/*      */   
/*      */   public int getMaximumDrawHeight() {
/*  722 */     return this.maximumDrawHeight;
/*      */   }
/*      */   
/*      */   public void setMaximumDrawHeight(int height) {
/*  735 */     this.maximumDrawHeight = height;
/*      */   }
/*      */   
/*      */   public double getScaleX() {
/*  745 */     return this.scaleX;
/*      */   }
/*      */   
/*      */   public double getScaleY() {
/*  755 */     return this.scaleY;
/*      */   }
/*      */   
/*      */   public Point2D getAnchor() {
/*  764 */     return this.anchor;
/*      */   }
/*      */   
/*      */   protected void setAnchor(Point2D anchor) {
/*  774 */     this.anchor = anchor;
/*      */   }
/*      */   
/*      */   public JPopupMenu getPopupMenu() {
/*  783 */     return this.popup;
/*      */   }
/*      */   
/*      */   public void setPopupMenu(JPopupMenu popup) {
/*  792 */     this.popup = popup;
/*      */   }
/*      */   
/*      */   public ChartRenderingInfo getChartRenderingInfo() {
/*  801 */     return this.info;
/*      */   }
/*      */   
/*      */   public void setMouseZoomable(boolean flag) {
/*  811 */     setMouseZoomable(flag, true);
/*      */   }
/*      */   
/*      */   public void setMouseZoomable(boolean flag, boolean fillRectangle) {
/*  822 */     setDomainZoomable(flag);
/*  823 */     setRangeZoomable(flag);
/*  824 */     setFillZoomRectangle(fillRectangle);
/*      */   }
/*      */   
/*      */   public boolean isDomainZoomable() {
/*  834 */     return this.domainZoomable;
/*      */   }
/*      */   
/*      */   public void setDomainZoomable(boolean flag) {
/*  845 */     if (flag) {
/*  846 */       Plot plot = this.chart.getPlot();
/*  847 */       if (plot instanceof Zoomable) {
/*  848 */         Zoomable z = (Zoomable)plot;
/*  849 */         this.domainZoomable = (flag && z.isDomainZoomable());
/*      */       } 
/*      */     } else {
/*  853 */       this.domainZoomable = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isRangeZoomable() {
/*  864 */     return this.rangeZoomable;
/*      */   }
/*      */   
/*      */   public void setRangeZoomable(boolean flag) {
/*  873 */     if (flag) {
/*  874 */       Plot plot = this.chart.getPlot();
/*  875 */       if (plot instanceof Zoomable) {
/*  876 */         Zoomable z = (Zoomable)plot;
/*  877 */         this.rangeZoomable = (flag && z.isRangeZoomable());
/*      */       } 
/*      */     } else {
/*  881 */       this.rangeZoomable = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean getFillZoomRectangle() {
/*  892 */     return this.fillZoomRectangle;
/*      */   }
/*      */   
/*      */   public void setFillZoomRectangle(boolean flag) {
/*  902 */     this.fillZoomRectangle = flag;
/*      */   }
/*      */   
/*      */   public int getZoomTriggerDistance() {
/*  912 */     return this.zoomTriggerDistance;
/*      */   }
/*      */   
/*      */   public void setZoomTriggerDistance(int distance) {
/*  922 */     this.zoomTriggerDistance = distance;
/*      */   }
/*      */   
/*      */   public boolean getHorizontalAxisTrace() {
/*  932 */     return this.horizontalAxisTrace;
/*      */   }
/*      */   
/*      */   public void setHorizontalAxisTrace(boolean flag) {
/*  942 */     this.horizontalAxisTrace = flag;
/*      */   }
/*      */   
/*      */   protected Line2D getHorizontalTraceLine() {
/*  951 */     return this.horizontalTraceLine;
/*      */   }
/*      */   
/*      */   protected void setHorizontalTraceLine(Line2D line) {
/*  960 */     this.horizontalTraceLine = line;
/*      */   }
/*      */   
/*      */   public boolean getVerticalAxisTrace() {
/*  970 */     return this.verticalAxisTrace;
/*      */   }
/*      */   
/*      */   public void setVerticalAxisTrace(boolean flag) {
/*  980 */     this.verticalAxisTrace = flag;
/*      */   }
/*      */   
/*      */   protected Line2D getVerticalTraceLine() {
/*  989 */     return this.verticalTraceLine;
/*      */   }
/*      */   
/*      */   protected void setVerticalTraceLine(Line2D line) {
/*  998 */     this.verticalTraceLine = line;
/*      */   }
/*      */   
/*      */   public boolean isEnforceFileExtensions() {
/* 1008 */     return this.enforceFileExtensions;
/*      */   }
/*      */   
/*      */   public void setEnforceFileExtensions(boolean enforce) {
/* 1017 */     this.enforceFileExtensions = enforce;
/*      */   }
/*      */   
/*      */   public void setDisplayToolTips(boolean flag) {
/* 1029 */     if (flag) {
/* 1030 */       ToolTipManager.sharedInstance().registerComponent(this);
/*      */     } else {
/* 1033 */       ToolTipManager.sharedInstance().unregisterComponent(this);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getToolTipText(MouseEvent e) {
/* 1046 */     String result = null;
/* 1047 */     if (this.info != null) {
/* 1048 */       EntityCollection entities = this.info.getEntityCollection();
/* 1049 */       if (entities != null) {
/* 1050 */         Insets insets = getInsets();
/* 1051 */         ChartEntity entity = entities.getEntity((int)((e.getX() - insets.left) / this.scaleX), (int)((e.getY() - insets.top) / this.scaleY));
/* 1055 */         if (entity != null)
/* 1056 */           result = entity.getToolTipText(); 
/*      */       } 
/*      */     } 
/* 1060 */     return result;
/*      */   }
/*      */   
/*      */   public Point translateJava2DToScreen(Point2D java2DPoint) {
/* 1072 */     Insets insets = getInsets();
/* 1073 */     int x = (int)(java2DPoint.getX() * this.scaleX + insets.left);
/* 1074 */     int y = (int)(java2DPoint.getY() * this.scaleY + insets.top);
/* 1075 */     return new Point(x, y);
/*      */   }
/*      */   
/*      */   public Point2D translateScreenToJava2D(Point screenPoint) {
/* 1086 */     Insets insets = getInsets();
/* 1087 */     double x = (screenPoint.getX() - insets.left) / this.scaleX;
/* 1088 */     double y = (screenPoint.getY() - insets.top) / this.scaleY;
/* 1089 */     return new Point2D.Double(x, y);
/*      */   }
/*      */   
/*      */   public Rectangle2D scale(Rectangle2D rect) {
/* 1101 */     Insets insets = getInsets();
/* 1102 */     double x = rect.getX() * getScaleX() + insets.left;
/* 1103 */     double y = rect.getY() * getScaleY() + insets.top;
/* 1104 */     double w = rect.getWidth() * getScaleX();
/* 1105 */     double h = rect.getHeight() * getScaleY();
/* 1106 */     return new Rectangle2D.Double(x, y, w, h);
/*      */   }
/*      */   
/*      */   public ChartEntity getEntityForPoint(int viewX, int viewY) {
/* 1122 */     ChartEntity result = null;
/* 1123 */     if (this.info != null) {
/* 1124 */       Insets insets = getInsets();
/* 1125 */       double x = (viewX - insets.left) / this.scaleX;
/* 1126 */       double y = (viewY - insets.top) / this.scaleY;
/* 1127 */       EntityCollection entities = this.info.getEntityCollection();
/* 1128 */       result = (entities != null) ? entities.getEntity(x, y) : null;
/*      */     } 
/* 1130 */     return result;
/*      */   }
/*      */   
/*      */   public boolean getRefreshBuffer() {
/* 1141 */     return this.refreshBuffer;
/*      */   }
/*      */   
/*      */   public void setRefreshBuffer(boolean flag) {
/* 1152 */     this.refreshBuffer = flag;
/*      */   }
/*      */   
/*      */   public void paintComponent(Graphics g) {
/* 1164 */     super.paintComponent(g);
/* 1165 */     if (this.chart == null)
/*      */       return; 
/* 1168 */     Graphics2D g2 = (Graphics2D)g.create();
/* 1171 */     Dimension size = getSize();
/* 1172 */     Insets insets = getInsets();
/* 1173 */     Rectangle2D available = new Rectangle2D.Double(insets.left, insets.top, size.getWidth() - insets.left - insets.right, size.getHeight() - insets.top - insets.bottom);
/* 1180 */     boolean scale = false;
/* 1181 */     double drawWidth = available.getWidth();
/* 1182 */     double drawHeight = available.getHeight();
/* 1183 */     this.scaleX = 1.0D;
/* 1184 */     this.scaleY = 1.0D;
/* 1186 */     if (drawWidth < this.minimumDrawWidth) {
/* 1187 */       this.scaleX = drawWidth / this.minimumDrawWidth;
/* 1188 */       drawWidth = this.minimumDrawWidth;
/* 1189 */       scale = true;
/* 1191 */     } else if (drawWidth > this.maximumDrawWidth) {
/* 1192 */       this.scaleX = drawWidth / this.maximumDrawWidth;
/* 1193 */       drawWidth = this.maximumDrawWidth;
/* 1194 */       scale = true;
/*      */     } 
/* 1197 */     if (drawHeight < this.minimumDrawHeight) {
/* 1198 */       this.scaleY = drawHeight / this.minimumDrawHeight;
/* 1199 */       drawHeight = this.minimumDrawHeight;
/* 1200 */       scale = true;
/* 1202 */     } else if (drawHeight > this.maximumDrawHeight) {
/* 1203 */       this.scaleY = drawHeight / this.maximumDrawHeight;
/* 1204 */       drawHeight = this.maximumDrawHeight;
/* 1205 */       scale = true;
/*      */     } 
/* 1208 */     Rectangle2D chartArea = new Rectangle2D.Double(0.0D, 0.0D, drawWidth, drawHeight);
/* 1213 */     if (this.useBuffer) {
/* 1216 */       if (this.chartBuffer == null || this.chartBufferWidth != available.getWidth() || this.chartBufferHeight != available.getHeight()) {
/* 1220 */         this.chartBufferWidth = (int)available.getWidth();
/* 1221 */         this.chartBufferHeight = (int)available.getHeight();
/* 1222 */         this.chartBuffer = createImage(this.chartBufferWidth, this.chartBufferHeight);
/* 1225 */         this.refreshBuffer = true;
/*      */       } 
/* 1229 */       if (this.refreshBuffer) {
/* 1231 */         Rectangle2D bufferArea = new Rectangle2D.Double(0.0D, 0.0D, this.chartBufferWidth, this.chartBufferHeight);
/* 1235 */         Graphics2D bufferG2 = (Graphics2D)this.chartBuffer.getGraphics();
/* 1237 */         if (scale) {
/* 1238 */           AffineTransform saved = bufferG2.getTransform();
/* 1239 */           AffineTransform st = AffineTransform.getScaleInstance(this.scaleX, this.scaleY);
/* 1242 */           bufferG2.transform(st);
/* 1243 */           this.chart.draw(bufferG2, chartArea, this.anchor, this.info);
/* 1246 */           bufferG2.setTransform(saved);
/*      */         } else {
/* 1249 */           this.chart.draw(bufferG2, bufferArea, this.anchor, this.info);
/*      */         } 
/* 1254 */         this.refreshBuffer = false;
/*      */       } 
/* 1259 */       g2.drawImage(this.chartBuffer, insets.left, insets.right, this);
/*      */     } else {
/* 1266 */       AffineTransform saved = g2.getTransform();
/* 1267 */       g2.translate(insets.left, insets.top);
/* 1268 */       if (scale) {
/* 1269 */         AffineTransform st = AffineTransform.getScaleInstance(this.scaleX, this.scaleY);
/* 1272 */         g2.transform(st);
/*      */       } 
/* 1274 */       this.chart.draw(g2, chartArea, this.anchor, this.info);
/* 1275 */       g2.setTransform(saved);
/*      */     } 
/* 1279 */     this.anchor = null;
/* 1280 */     this.verticalTraceLine = null;
/* 1281 */     this.horizontalTraceLine = null;
/*      */   }
/*      */   
/*      */   public void chartChanged(ChartChangeEvent event) {
/* 1291 */     this.refreshBuffer = true;
/* 1292 */     Plot plot = this.chart.getPlot();
/* 1293 */     if (plot instanceof Zoomable) {
/* 1294 */       Zoomable z = (Zoomable)plot;
/* 1295 */       this.orientation = z.getOrientation();
/*      */     } 
/* 1297 */     repaint();
/*      */   }
/*      */   
/*      */   public void chartProgress(ChartProgressEvent event) {}
/*      */   
/*      */   public void actionPerformed(ActionEvent event) {
/* 1316 */     String command = event.getActionCommand();
/* 1318 */     if (command.equals("PROPERTIES")) {
/* 1319 */       attemptEditChartProperties();
/* 1321 */     } else if (command.equals("SAVE")) {
/*      */       try {
/* 1323 */         doSaveAs();
/* 1325 */       } catch (IOException e) {
/* 1326 */         e.printStackTrace();
/*      */       } 
/* 1329 */     } else if (command.equals("PRINT")) {
/* 1330 */       createChartPrintJob();
/* 1332 */     } else if (command.equals("ZOOM_IN_BOTH")) {
/* 1333 */       zoomInBoth(this.zoomPoint.getX(), this.zoomPoint.getY());
/* 1335 */     } else if (command.equals("ZOOM_IN_DOMAIN")) {
/* 1336 */       zoomInDomain(this.zoomPoint.getX(), this.zoomPoint.getY());
/* 1338 */     } else if (command.equals("ZOOM_IN_RANGE")) {
/* 1339 */       zoomInRange(this.zoomPoint.getX(), this.zoomPoint.getY());
/* 1341 */     } else if (command.equals("ZOOM_OUT_BOTH")) {
/* 1342 */       zoomOutBoth(this.zoomPoint.getX(), this.zoomPoint.getY());
/* 1344 */     } else if (command.equals("ZOOM_DOMAIN_BOTH")) {
/* 1345 */       zoomOutDomain(this.zoomPoint.getX(), this.zoomPoint.getY());
/* 1347 */     } else if (command.equals("ZOOM_RANGE_BOTH")) {
/* 1348 */       zoomOutRange(this.zoomPoint.getX(), this.zoomPoint.getY());
/* 1350 */     } else if (command.equals("ZOOM_RESET_BOTH")) {
/* 1351 */       restoreAutoBounds();
/* 1353 */     } else if (command.equals("ZOOM_RESET_DOMAIN")) {
/* 1354 */       restoreAutoDomainBounds();
/* 1356 */     } else if (command.equals("ZOOM_RESET_RANGE")) {
/* 1357 */       restoreAutoRangeBounds();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void mouseEntered(MouseEvent e) {
/* 1370 */     if (!this.ownToolTipDelaysActive) {
/* 1371 */       ToolTipManager ttm = ToolTipManager.sharedInstance();
/* 1373 */       this.originalToolTipInitialDelay = ttm.getInitialDelay();
/* 1374 */       ttm.setInitialDelay(this.ownToolTipInitialDelay);
/* 1376 */       this.originalToolTipReshowDelay = ttm.getReshowDelay();
/* 1377 */       ttm.setReshowDelay(this.ownToolTipReshowDelay);
/* 1379 */       this.originalToolTipDismissDelay = ttm.getDismissDelay();
/* 1380 */       ttm.setDismissDelay(this.ownToolTipDismissDelay);
/* 1382 */       this.ownToolTipDelaysActive = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void mouseExited(MouseEvent e) {
/* 1394 */     if (this.ownToolTipDelaysActive) {
/* 1396 */       ToolTipManager ttm = ToolTipManager.sharedInstance();
/* 1397 */       ttm.setInitialDelay(this.originalToolTipInitialDelay);
/* 1398 */       ttm.setReshowDelay(this.originalToolTipReshowDelay);
/* 1399 */       ttm.setDismissDelay(this.originalToolTipDismissDelay);
/* 1400 */       this.ownToolTipDelaysActive = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void mousePressed(MouseEvent e) {
/* 1413 */     if (this.zoomRectangle == null) {
/* 1414 */       Rectangle2D screenDataArea = getScreenDataArea(e.getX(), e.getY());
/* 1415 */       if (screenDataArea != null) {
/* 1416 */         this.zoomPoint = getPointInRectangle(e.getX(), e.getY(), screenDataArea);
/*      */       } else {
/* 1421 */         this.zoomPoint = null;
/*      */       } 
/* 1423 */       if (e.isPopupTrigger() && 
/* 1424 */         this.popup != null)
/* 1425 */         displayPopupMenu(e.getX(), e.getY()); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private Point getPointInRectangle(int x, int y, Rectangle2D area) {
/* 1442 */     x = (int)Math.max(Math.ceil(area.getMinX()), Math.min(x, Math.floor(area.getMaxX())));
/* 1445 */     y = (int)Math.max(Math.ceil(area.getMinY()), Math.min(y, Math.floor(area.getMaxY())));
/* 1448 */     return new Point(x, y);
/*      */   }
/*      */   
/*      */   public void mouseDragged(MouseEvent e) {
/* 1459 */     if (this.popup != null && this.popup.isShowing())
/*      */       return; 
/* 1463 */     if (this.zoomPoint == null)
/*      */       return; 
/* 1466 */     Graphics2D g2 = (Graphics2D)getGraphics();
/* 1469 */     g2.setXORMode(Color.gray);
/* 1470 */     if (this.zoomRectangle != null)
/* 1471 */       if (this.fillZoomRectangle) {
/* 1472 */         g2.fill(this.zoomRectangle);
/*      */       } else {
/* 1475 */         g2.draw(this.zoomRectangle);
/*      */       }  
/* 1479 */     boolean hZoom = false;
/* 1480 */     boolean vZoom = false;
/* 1481 */     if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 1482 */       hZoom = this.rangeZoomable;
/* 1483 */       vZoom = this.domainZoomable;
/*      */     } else {
/* 1486 */       hZoom = this.domainZoomable;
/* 1487 */       vZoom = this.rangeZoomable;
/*      */     } 
/* 1489 */     Rectangle2D scaledDataArea = getScreenDataArea((int)this.zoomPoint.getX(), (int)this.zoomPoint.getY());
/* 1492 */     if (hZoom && vZoom) {
/* 1494 */       double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
/* 1495 */       double ymax = Math.min(e.getY(), scaledDataArea.getMaxY());
/* 1496 */       this.zoomRectangle = new Rectangle2D.Double(this.zoomPoint.getX(), this.zoomPoint.getY(), xmax - this.zoomPoint.getX(), ymax - this.zoomPoint.getY());
/* 1501 */     } else if (hZoom) {
/* 1502 */       double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
/* 1503 */       this.zoomRectangle = new Rectangle2D.Double(this.zoomPoint.getX(), scaledDataArea.getMinY(), xmax - this.zoomPoint.getX(), scaledDataArea.getHeight());
/* 1508 */     } else if (vZoom) {
/* 1509 */       double ymax = Math.min(e.getY(), scaledDataArea.getMaxY());
/* 1510 */       this.zoomRectangle = new Rectangle2D.Double(scaledDataArea.getMinX(), this.zoomPoint.getY(), scaledDataArea.getWidth(), ymax - this.zoomPoint.getY());
/*      */     } 
/* 1516 */     if (this.zoomRectangle != null)
/* 1518 */       if (this.fillZoomRectangle) {
/* 1519 */         g2.fill(this.zoomRectangle);
/*      */       } else {
/* 1522 */         g2.draw(this.zoomRectangle);
/*      */       }  
/* 1525 */     g2.dispose();
/*      */   }
/*      */   
/*      */   public void mouseReleased(MouseEvent e) {
/* 1538 */     if (this.zoomRectangle != null) {
/* 1539 */       boolean hZoom = false;
/* 1540 */       boolean vZoom = false;
/* 1541 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 1542 */         hZoom = this.rangeZoomable;
/* 1543 */         vZoom = this.domainZoomable;
/*      */       } else {
/* 1546 */         hZoom = this.domainZoomable;
/* 1547 */         vZoom = this.rangeZoomable;
/*      */       } 
/* 1550 */       boolean zoomTrigger1 = (hZoom && Math.abs(e.getX() - this.zoomPoint.getX()) >= this.zoomTriggerDistance);
/* 1552 */       boolean zoomTrigger2 = (vZoom && Math.abs(e.getY() - this.zoomPoint.getY()) >= this.zoomTriggerDistance);
/* 1554 */       if (zoomTrigger1 || zoomTrigger2) {
/* 1555 */         if ((hZoom && e.getX() < this.zoomPoint.getX()) || (vZoom && e.getY() < this.zoomPoint.getY())) {
/* 1557 */           restoreAutoBounds();
/*      */         } else {
/*      */           double x, y, w, h;
/* 1561 */           Rectangle2D screenDataArea = getScreenDataArea((int)this.zoomPoint.getX(), (int)this.zoomPoint.getY());
/* 1568 */           if (!vZoom) {
/* 1569 */             x = this.zoomPoint.getX();
/* 1570 */             y = screenDataArea.getMinY();
/* 1571 */             w = Math.min(this.zoomRectangle.getWidth(), screenDataArea.getMaxX() - this.zoomPoint.getX());
/* 1575 */             h = screenDataArea.getHeight();
/* 1577 */           } else if (!hZoom) {
/* 1578 */             x = screenDataArea.getMinX();
/* 1579 */             y = this.zoomPoint.getY();
/* 1580 */             w = screenDataArea.getWidth();
/* 1581 */             h = Math.min(this.zoomRectangle.getHeight(), screenDataArea.getMaxY() - this.zoomPoint.getY());
/*      */           } else {
/* 1587 */             x = this.zoomPoint.getX();
/* 1588 */             y = this.zoomPoint.getY();
/* 1589 */             w = Math.min(this.zoomRectangle.getWidth(), screenDataArea.getMaxX() - this.zoomPoint.getX());
/* 1593 */             h = Math.min(this.zoomRectangle.getHeight(), screenDataArea.getMaxY() - this.zoomPoint.getY());
/*      */           } 
/* 1598 */           Rectangle2D zoomArea = new Rectangle2D.Double(x, y, w, h);
/* 1599 */           zoom(zoomArea);
/*      */         } 
/* 1601 */         this.zoomPoint = null;
/* 1602 */         this.zoomRectangle = null;
/*      */       } else {
/* 1605 */         Graphics2D g2 = (Graphics2D)getGraphics();
/* 1606 */         g2.setXORMode(Color.gray);
/* 1607 */         if (this.fillZoomRectangle) {
/* 1608 */           g2.fill(this.zoomRectangle);
/*      */         } else {
/* 1611 */           g2.draw(this.zoomRectangle);
/*      */         } 
/* 1613 */         g2.dispose();
/* 1614 */         this.zoomPoint = null;
/* 1615 */         this.zoomRectangle = null;
/*      */       } 
/* 1620 */     } else if (e.isPopupTrigger() && 
/* 1621 */       this.popup != null) {
/* 1622 */       displayPopupMenu(e.getX(), e.getY());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void mouseClicked(MouseEvent event) {
/* 1636 */     Insets insets = getInsets();
/* 1637 */     int x = (int)((event.getX() - insets.left) / this.scaleX);
/* 1638 */     int y = (int)((event.getY() - insets.top) / this.scaleY);
/* 1640 */     this.anchor = new Point2D.Double(x, y);
/* 1641 */     this.chart.setNotify(true);
/* 1643 */     Object[] listeners = this.chartMouseListeners.getListeners(ChartMouseListener.class);
/* 1645 */     if (listeners.length == 0)
/*      */       return; 
/* 1649 */     ChartEntity entity = null;
/* 1650 */     if (this.info != null) {
/* 1651 */       EntityCollection entities = this.info.getEntityCollection();
/* 1652 */       if (entities != null)
/* 1653 */         entity = entities.getEntity(x, y); 
/*      */     } 
/* 1656 */     ChartMouseEvent chartEvent = new ChartMouseEvent(getChart(), event, entity);
/* 1658 */     for (int i = listeners.length - 1; i >= 0; i--)
/* 1659 */       ((ChartMouseListener)listeners[i]).chartMouseClicked(chartEvent); 
/*      */   }
/*      */   
/*      */   public void mouseMoved(MouseEvent e) {
/* 1670 */     if (this.horizontalAxisTrace)
/* 1671 */       drawHorizontalAxisTrace(e.getX()); 
/* 1673 */     if (this.verticalAxisTrace)
/* 1674 */       drawVerticalAxisTrace(e.getY()); 
/* 1676 */     Object[] listeners = this.chartMouseListeners.getListeners(ChartMouseListener.class);
/* 1678 */     if (listeners.length == 0)
/*      */       return; 
/* 1681 */     Insets insets = getInsets();
/* 1682 */     int x = (int)((e.getX() - insets.left) / this.scaleX);
/* 1683 */     int y = (int)((e.getY() - insets.top) / this.scaleY);
/* 1685 */     ChartEntity entity = null;
/* 1686 */     if (this.info != null) {
/* 1687 */       EntityCollection entities = this.info.getEntityCollection();
/* 1688 */       if (entities != null)
/* 1689 */         entity = entities.getEntity(x, y); 
/*      */     } 
/* 1692 */     ChartMouseEvent event = new ChartMouseEvent(getChart(), e, entity);
/* 1693 */     for (int i = listeners.length - 1; i >= 0; i--)
/* 1694 */       ((ChartMouseListener)listeners[i]).chartMouseMoved(event); 
/*      */   }
/*      */   
/*      */   public void zoomInBoth(double x, double y) {
/* 1706 */     zoomInDomain(x, y);
/* 1707 */     zoomInRange(x, y);
/*      */   }
/*      */   
/*      */   public void zoomInDomain(double x, double y) {
/* 1719 */     Plot p = this.chart.getPlot();
/* 1720 */     if (p instanceof Zoomable) {
/* 1721 */       Zoomable plot = (Zoomable)p;
/* 1722 */       plot.zoomDomainAxes(this.zoomInFactor, this.info.getPlotInfo(), translateScreenToJava2D(new Point((int)x, (int)y)));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void zoomInRange(double x, double y) {
/* 1738 */     Plot p = this.chart.getPlot();
/* 1739 */     if (p instanceof Zoomable) {
/* 1740 */       Zoomable z = (Zoomable)p;
/* 1741 */       z.zoomRangeAxes(this.zoomInFactor, this.info.getPlotInfo(), translateScreenToJava2D(new Point((int)x, (int)y)));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void zoomOutBoth(double x, double y) {
/* 1755 */     zoomOutDomain(x, y);
/* 1756 */     zoomOutRange(x, y);
/*      */   }
/*      */   
/*      */   public void zoomOutDomain(double x, double y) {
/* 1768 */     Plot p = this.chart.getPlot();
/* 1769 */     if (p instanceof Zoomable) {
/* 1770 */       Zoomable z = (Zoomable)p;
/* 1771 */       z.zoomDomainAxes(this.zoomOutFactor, this.info.getPlotInfo(), translateScreenToJava2D(new Point((int)x, (int)y)));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void zoomOutRange(double x, double y) {
/* 1787 */     Plot p = this.chart.getPlot();
/* 1788 */     if (p instanceof Zoomable) {
/* 1789 */       Zoomable z = (Zoomable)p;
/* 1790 */       z.zoomRangeAxes(this.zoomOutFactor, this.info.getPlotInfo(), translateScreenToJava2D(new Point((int)x, (int)y)));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void zoom(Rectangle2D selection) {
/* 1806 */     Point2D selectOrigin = translateScreenToJava2D(new Point((int)Math.ceil(selection.getX()), (int)Math.ceil(selection.getY())));
/* 1812 */     PlotRenderingInfo plotInfo = this.info.getPlotInfo();
/* 1813 */     Rectangle2D scaledDataArea = getScreenDataArea((int)selection.getCenterX(), (int)selection.getCenterY());
/* 1816 */     if (selection.getHeight() > 0.0D && selection.getWidth() > 0.0D) {
/* 1818 */       double hLower = (selection.getMinX() - scaledDataArea.getMinX()) / scaledDataArea.getWidth();
/* 1820 */       double hUpper = (selection.getMaxX() - scaledDataArea.getMinX()) / scaledDataArea.getWidth();
/* 1822 */       double vLower = (scaledDataArea.getMaxY() - selection.getMaxY()) / scaledDataArea.getHeight();
/* 1824 */       double vUpper = (scaledDataArea.getMaxY() - selection.getMinY()) / scaledDataArea.getHeight();
/* 1827 */       Plot p = this.chart.getPlot();
/* 1828 */       if (p instanceof Zoomable) {
/* 1829 */         Zoomable z = (Zoomable)p;
/* 1830 */         if (z.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 1831 */           z.zoomDomainAxes(vLower, vUpper, plotInfo, selectOrigin);
/* 1832 */           z.zoomRangeAxes(hLower, hUpper, plotInfo, selectOrigin);
/*      */         } else {
/* 1835 */           z.zoomDomainAxes(hLower, hUpper, plotInfo, selectOrigin);
/* 1836 */           z.zoomRangeAxes(vLower, vUpper, plotInfo, selectOrigin);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void restoreAutoBounds() {
/* 1848 */     restoreAutoDomainBounds();
/* 1849 */     restoreAutoRangeBounds();
/*      */   }
/*      */   
/*      */   public void restoreAutoDomainBounds() {
/* 1856 */     Plot p = this.chart.getPlot();
/* 1857 */     if (p instanceof Zoomable) {
/* 1858 */       Zoomable z = (Zoomable)p;
/* 1859 */       z.zoomDomainAxes(0.0D, this.info.getPlotInfo(), this.zoomPoint);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void restoreAutoRangeBounds() {
/* 1867 */     Plot p = this.chart.getPlot();
/* 1868 */     if (p instanceof org.jfree.chart.plot.ValueAxisPlot) {
/* 1869 */       Zoomable z = (Zoomable)p;
/* 1870 */       z.zoomRangeAxes(0.0D, this.info.getPlotInfo(), this.zoomPoint);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Rectangle2D getScreenDataArea() {
/* 1881 */     Rectangle2D dataArea = this.info.getPlotInfo().getDataArea();
/* 1882 */     Insets insets = getInsets();
/* 1883 */     double x = dataArea.getX() * this.scaleX + insets.left;
/* 1884 */     double y = dataArea.getY() * this.scaleY + insets.top;
/* 1885 */     double w = dataArea.getWidth() * this.scaleX;
/* 1886 */     double h = dataArea.getHeight() * this.scaleY;
/* 1887 */     return new Rectangle2D.Double(x, y, w, h);
/*      */   }
/*      */   
/*      */   public Rectangle2D getScreenDataArea(int x, int y) {
/*      */     Rectangle2D result;
/* 1900 */     PlotRenderingInfo plotInfo = this.info.getPlotInfo();
/* 1902 */     if (plotInfo.getSubplotCount() == 0) {
/* 1903 */       result = getScreenDataArea();
/*      */     } else {
/* 1908 */       Point2D selectOrigin = translateScreenToJava2D(new Point(x, y));
/* 1909 */       int subplotIndex = plotInfo.getSubplotIndex(selectOrigin);
/* 1910 */       if (subplotIndex == -1)
/* 1911 */         return null; 
/* 1913 */       result = scale(plotInfo.getSubplotInfo(subplotIndex).getDataArea());
/*      */     } 
/* 1915 */     return result;
/*      */   }
/*      */   
/*      */   public int getInitialDelay() {
/* 1926 */     return this.ownToolTipInitialDelay;
/*      */   }
/*      */   
/*      */   public int getReshowDelay() {
/* 1937 */     return this.ownToolTipReshowDelay;
/*      */   }
/*      */   
/*      */   public int getDismissDelay() {
/* 1949 */     return this.ownToolTipDismissDelay;
/*      */   }
/*      */   
/*      */   public void setInitialDelay(int delay) {
/* 1961 */     this.ownToolTipInitialDelay = delay;
/*      */   }
/*      */   
/*      */   public void setReshowDelay(int delay) {
/* 1973 */     this.ownToolTipReshowDelay = delay;
/*      */   }
/*      */   
/*      */   public void setDismissDelay(int delay) {
/* 1985 */     this.ownToolTipDismissDelay = delay;
/*      */   }
/*      */   
/*      */   public double getZoomInFactor() {
/* 1994 */     return this.zoomInFactor;
/*      */   }
/*      */   
/*      */   public void setZoomInFactor(double factor) {
/* 2003 */     this.zoomInFactor = factor;
/*      */   }
/*      */   
/*      */   public double getZoomOutFactor() {
/* 2012 */     return this.zoomOutFactor;
/*      */   }
/*      */   
/*      */   public void setZoomOutFactor(double factor) {
/* 2021 */     this.zoomOutFactor = factor;
/*      */   }
/*      */   
/*      */   private void drawHorizontalAxisTrace(int x) {
/* 2032 */     Graphics2D g2 = (Graphics2D)getGraphics();
/* 2033 */     Rectangle2D dataArea = getScreenDataArea();
/* 2035 */     g2.setXORMode(Color.orange);
/* 2036 */     if ((int)dataArea.getMinX() < x && x < (int)dataArea.getMaxX()) {
/* 2038 */       if (this.verticalTraceLine != null) {
/* 2039 */         g2.draw(this.verticalTraceLine);
/* 2040 */         this.verticalTraceLine.setLine(x, (int)dataArea.getMinY(), x, (int)dataArea.getMaxY());
/*      */       } else {
/* 2045 */         this.verticalTraceLine = new Line2D.Float(x, (int)dataArea.getMinY(), x, (int)dataArea.getMaxY());
/*      */       } 
/* 2049 */       g2.draw(this.verticalTraceLine);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void drawVerticalAxisTrace(int y) {
/* 2062 */     Graphics2D g2 = (Graphics2D)getGraphics();
/* 2063 */     Rectangle2D dataArea = getScreenDataArea();
/* 2065 */     g2.setXORMode(Color.orange);
/* 2066 */     if ((int)dataArea.getMinY() < y && y < (int)dataArea.getMaxY()) {
/* 2068 */       if (this.horizontalTraceLine != null) {
/* 2069 */         g2.draw(this.horizontalTraceLine);
/* 2070 */         this.horizontalTraceLine.setLine((int)dataArea.getMinX(), y, (int)dataArea.getMaxX(), y);
/*      */       } else {
/* 2075 */         this.horizontalTraceLine = new Line2D.Float((int)dataArea.getMinX(), y, (int)dataArea.getMaxX(), y);
/*      */       } 
/* 2079 */       g2.draw(this.horizontalTraceLine);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void attemptEditChartProperties() {
/* 2090 */     ChartEditor editor = ChartEditorManager.getChartEditor(this.chart);
/* 2091 */     int result = JOptionPane.showConfirmDialog(this, editor, localizationResources.getString("Chart_Properties"), 2, -1);
/* 2094 */     if (result == 0)
/* 2095 */       editor.updateChart(this.chart); 
/*      */   }
/*      */   
/*      */   public void doSaveAs() throws IOException {
/* 2108 */     JFileChooser fileChooser = new JFileChooser();
/* 2109 */     ExtensionFileFilter filter = new ExtensionFileFilter(localizationResources.getString("PNG_Image_Files"), ".png");
/* 2112 */     fileChooser.addChoosableFileFilter((FileFilter)filter);
/* 2114 */     int option = fileChooser.showSaveDialog(this);
/* 2115 */     if (option == 0) {
/* 2116 */       String filename = fileChooser.getSelectedFile().getPath();
/* 2117 */       if (isEnforceFileExtensions() && 
/* 2118 */         !filename.endsWith(".png"))
/* 2119 */         filename = filename + ".png"; 
/* 2122 */       ChartUtilities.saveChartAsPNG(new File(filename), this.chart, getWidth(), getHeight());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void createChartPrintJob() {
/* 2134 */     PrinterJob job = PrinterJob.getPrinterJob();
/* 2135 */     PageFormat pf = job.defaultPage();
/* 2136 */     PageFormat pf2 = job.pageDialog(pf);
/* 2137 */     if (pf2 != pf) {
/* 2138 */       job.setPrintable(this, pf2);
/* 2139 */       if (job.printDialog())
/*      */         try {
/* 2141 */           job.print();
/* 2143 */         } catch (PrinterException e) {
/* 2144 */           JOptionPane.showMessageDialog(this, e);
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public int print(Graphics g, PageFormat pf, int pageIndex) {
/* 2163 */     if (pageIndex != 0)
/* 2164 */       return 1; 
/* 2166 */     Graphics2D g2 = (Graphics2D)g;
/* 2167 */     double x = pf.getImageableX();
/* 2168 */     double y = pf.getImageableY();
/* 2169 */     double w = pf.getImageableWidth();
/* 2170 */     double h = pf.getImageableHeight();
/* 2171 */     this.chart.draw(g2, new Rectangle2D.Double(x, y, w, h), this.anchor, null);
/* 2174 */     return 0;
/*      */   }
/*      */   
/*      */   public void addChartMouseListener(ChartMouseListener listener) {
/* 2184 */     if (listener == null)
/* 2185 */       throw new IllegalArgumentException("Null 'listener' argument."); 
/* 2187 */     this.chartMouseListeners.add(ChartMouseListener.class, listener);
/*      */   }
/*      */   
/*      */   public void removeChartMouseListener(ChartMouseListener listener) {
/* 2197 */     this.chartMouseListeners.remove(ChartMouseListener.class, listener);
/*      */   }
/*      */   
/*      */   public EventListener[] getListeners(Class listenerType) {
/* 2209 */     if (listenerType == ChartMouseListener.class)
/* 2211 */       return this.chartMouseListeners.getListeners((Class)listenerType); 
/* 2214 */     return super.getListeners((Class)listenerType);
/*      */   }
/*      */   
/*      */   protected JPopupMenu createPopupMenu(boolean properties, boolean save, boolean print, boolean zoom) {
/* 2233 */     JPopupMenu result = new JPopupMenu("Chart:");
/* 2234 */     boolean separator = false;
/* 2236 */     if (properties) {
/* 2237 */       JMenuItem propertiesItem = new JMenuItem(localizationResources.getString("Properties..."));
/* 2240 */       propertiesItem.setActionCommand("PROPERTIES");
/* 2241 */       propertiesItem.addActionListener(this);
/* 2242 */       result.add(propertiesItem);
/* 2243 */       separator = true;
/*      */     } 
/* 2246 */     if (save) {
/* 2247 */       if (separator) {
/* 2248 */         result.addSeparator();
/* 2249 */         separator = false;
/*      */       } 
/* 2251 */       JMenuItem saveItem = new JMenuItem(localizationResources.getString("Save_as..."));
/* 2254 */       saveItem.setActionCommand("SAVE");
/* 2255 */       saveItem.addActionListener(this);
/* 2256 */       result.add(saveItem);
/* 2257 */       separator = true;
/*      */     } 
/* 2260 */     if (print) {
/* 2261 */       if (separator) {
/* 2262 */         result.addSeparator();
/* 2263 */         separator = false;
/*      */       } 
/* 2265 */       JMenuItem printItem = new JMenuItem(localizationResources.getString("Print..."));
/* 2268 */       printItem.setActionCommand("PRINT");
/* 2269 */       printItem.addActionListener(this);
/* 2270 */       result.add(printItem);
/* 2271 */       separator = true;
/*      */     } 
/* 2274 */     if (zoom) {
/* 2275 */       if (separator) {
/* 2276 */         result.addSeparator();
/* 2277 */         separator = false;
/*      */       } 
/* 2280 */       JMenu zoomInMenu = new JMenu(localizationResources.getString("Zoom_In"));
/* 2284 */       this.zoomInBothMenuItem = new JMenuItem(localizationResources.getString("All_Axes"));
/* 2287 */       this.zoomInBothMenuItem.setActionCommand("ZOOM_IN_BOTH");
/* 2288 */       this.zoomInBothMenuItem.addActionListener(this);
/* 2289 */       zoomInMenu.add(this.zoomInBothMenuItem);
/* 2291 */       zoomInMenu.addSeparator();
/* 2293 */       this.zoomInDomainMenuItem = new JMenuItem(localizationResources.getString("Domain_Axis"));
/* 2296 */       this.zoomInDomainMenuItem.setActionCommand("ZOOM_IN_DOMAIN");
/* 2297 */       this.zoomInDomainMenuItem.addActionListener(this);
/* 2298 */       zoomInMenu.add(this.zoomInDomainMenuItem);
/* 2300 */       this.zoomInRangeMenuItem = new JMenuItem(localizationResources.getString("Range_Axis"));
/* 2303 */       this.zoomInRangeMenuItem.setActionCommand("ZOOM_IN_RANGE");
/* 2304 */       this.zoomInRangeMenuItem.addActionListener(this);
/* 2305 */       zoomInMenu.add(this.zoomInRangeMenuItem);
/* 2307 */       result.add(zoomInMenu);
/* 2309 */       JMenu zoomOutMenu = new JMenu(localizationResources.getString("Zoom_Out"));
/* 2313 */       this.zoomOutBothMenuItem = new JMenuItem(localizationResources.getString("All_Axes"));
/* 2316 */       this.zoomOutBothMenuItem.setActionCommand("ZOOM_OUT_BOTH");
/* 2317 */       this.zoomOutBothMenuItem.addActionListener(this);
/* 2318 */       zoomOutMenu.add(this.zoomOutBothMenuItem);
/* 2320 */       zoomOutMenu.addSeparator();
/* 2322 */       this.zoomOutDomainMenuItem = new JMenuItem(localizationResources.getString("Domain_Axis"));
/* 2325 */       this.zoomOutDomainMenuItem.setActionCommand("ZOOM_DOMAIN_BOTH");
/* 2328 */       this.zoomOutDomainMenuItem.addActionListener(this);
/* 2329 */       zoomOutMenu.add(this.zoomOutDomainMenuItem);
/* 2331 */       this.zoomOutRangeMenuItem = new JMenuItem(localizationResources.getString("Range_Axis"));
/* 2334 */       this.zoomOutRangeMenuItem.setActionCommand("ZOOM_RANGE_BOTH");
/* 2335 */       this.zoomOutRangeMenuItem.addActionListener(this);
/* 2336 */       zoomOutMenu.add(this.zoomOutRangeMenuItem);
/* 2338 */       result.add(zoomOutMenu);
/* 2340 */       JMenu autoRangeMenu = new JMenu(localizationResources.getString("Auto_Range"));
/* 2344 */       this.zoomResetBothMenuItem = new JMenuItem(localizationResources.getString("All_Axes"));
/* 2347 */       this.zoomResetBothMenuItem.setActionCommand("ZOOM_RESET_BOTH");
/* 2350 */       this.zoomResetBothMenuItem.addActionListener(this);
/* 2351 */       autoRangeMenu.add(this.zoomResetBothMenuItem);
/* 2353 */       autoRangeMenu.addSeparator();
/* 2354 */       this.zoomResetDomainMenuItem = new JMenuItem(localizationResources.getString("Domain_Axis"));
/* 2357 */       this.zoomResetDomainMenuItem.setActionCommand("ZOOM_RESET_DOMAIN");
/* 2360 */       this.zoomResetDomainMenuItem.addActionListener(this);
/* 2361 */       autoRangeMenu.add(this.zoomResetDomainMenuItem);
/* 2363 */       this.zoomResetRangeMenuItem = new JMenuItem(localizationResources.getString("Range_Axis"));
/* 2366 */       this.zoomResetRangeMenuItem.setActionCommand("ZOOM_RESET_RANGE");
/* 2369 */       this.zoomResetRangeMenuItem.addActionListener(this);
/* 2370 */       autoRangeMenu.add(this.zoomResetRangeMenuItem);
/* 2372 */       result.addSeparator();
/* 2373 */       result.add(autoRangeMenu);
/*      */     } 
/* 2377 */     return result;
/*      */   }
/*      */   
/*      */   protected void displayPopupMenu(int x, int y) {
/* 2390 */     if (this.popup != null) {
/* 2394 */       Plot plot = this.chart.getPlot();
/* 2395 */       boolean isDomainZoomable = false;
/* 2396 */       boolean isRangeZoomable = false;
/* 2397 */       if (plot instanceof Zoomable) {
/* 2398 */         Zoomable z = (Zoomable)plot;
/* 2399 */         isDomainZoomable = z.isDomainZoomable();
/* 2400 */         isRangeZoomable = z.isRangeZoomable();
/*      */       } 
/* 2403 */       if (this.zoomInDomainMenuItem != null)
/* 2404 */         this.zoomInDomainMenuItem.setEnabled(isDomainZoomable); 
/* 2406 */       if (this.zoomOutDomainMenuItem != null)
/* 2407 */         this.zoomOutDomainMenuItem.setEnabled(isDomainZoomable); 
/* 2409 */       if (this.zoomResetDomainMenuItem != null)
/* 2410 */         this.zoomResetDomainMenuItem.setEnabled(isDomainZoomable); 
/* 2413 */       if (this.zoomInRangeMenuItem != null)
/* 2414 */         this.zoomInRangeMenuItem.setEnabled(isRangeZoomable); 
/* 2416 */       if (this.zoomOutRangeMenuItem != null)
/* 2417 */         this.zoomOutRangeMenuItem.setEnabled(isRangeZoomable); 
/* 2420 */       if (this.zoomResetRangeMenuItem != null)
/* 2421 */         this.zoomResetRangeMenuItem.setEnabled(isRangeZoomable); 
/* 2424 */       if (this.zoomInBothMenuItem != null)
/* 2425 */         this.zoomInBothMenuItem.setEnabled(isDomainZoomable & isRangeZoomable); 
/* 2429 */       if (this.zoomOutBothMenuItem != null)
/* 2430 */         this.zoomOutBothMenuItem.setEnabled(isDomainZoomable & isRangeZoomable); 
/* 2434 */       if (this.zoomResetBothMenuItem != null)
/* 2435 */         this.zoomResetBothMenuItem.setEnabled(isDomainZoomable & isRangeZoomable); 
/* 2440 */       this.popup.show(this, x, y);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\ChartPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */