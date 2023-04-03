/*      */ package org.jfree.chart.renderer;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Arrays;
/*      */ import java.util.EventListener;
/*      */ import java.util.List;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeListener;
/*      */ import org.jfree.chart.labels.ItemLabelAnchor;
/*      */ import org.jfree.chart.labels.ItemLabelPosition;
/*      */ import org.jfree.chart.plot.DrawingSupplier;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.BooleanList;
/*      */ import org.jfree.util.BooleanUtilities;
/*      */ import org.jfree.util.ObjectList;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintList;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.ShapeList;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ import org.jfree.util.StrokeList;
/*      */ 
/*      */ public abstract class AbstractRenderer implements Cloneable, Serializable {
/*      */   private static final long serialVersionUID = -828267569428206075L;
/*      */   
/*  122 */   public static final Double ZERO = new Double(0.0D);
/*      */   
/*  125 */   public static final Paint DEFAULT_PAINT = Color.blue;
/*      */   
/*  128 */   public static final Paint DEFAULT_OUTLINE_PAINT = Color.gray;
/*      */   
/*  131 */   public static final Stroke DEFAULT_STROKE = new BasicStroke(1.0F);
/*      */   
/*  134 */   public static final Stroke DEFAULT_OUTLINE_STROKE = new BasicStroke(1.0F);
/*      */   
/*  137 */   public static final Shape DEFAULT_SHAPE = new Rectangle2D.Double(-3.0D, -3.0D, 6.0D, 6.0D);
/*      */   
/*  141 */   public static final Font DEFAULT_VALUE_LABEL_FONT = new Font("SansSerif", 0, 10);
/*      */   
/*  145 */   public static final Paint DEFAULT_VALUE_LABEL_PAINT = Color.black;
/*      */   
/*      */   private Boolean seriesVisible;
/*      */   
/*      */   private BooleanList seriesVisibleList;
/*      */   
/*      */   private boolean baseSeriesVisible;
/*      */   
/*      */   private Boolean seriesVisibleInLegend;
/*      */   
/*      */   private BooleanList seriesVisibleInLegendList;
/*      */   
/*      */   private boolean baseSeriesVisibleInLegend;
/*      */   
/*      */   private transient Paint paint;
/*      */   
/*      */   private PaintList paintList;
/*      */   
/*      */   private transient Paint basePaint;
/*      */   
/*      */   private transient Paint fillPaint;
/*      */   
/*      */   private PaintList fillPaintList;
/*      */   
/*      */   private transient Paint baseFillPaint;
/*      */   
/*      */   private transient Paint outlinePaint;
/*      */   
/*      */   private PaintList outlinePaintList;
/*      */   
/*      */   private transient Paint baseOutlinePaint;
/*      */   
/*      */   private transient Stroke stroke;
/*      */   
/*      */   private StrokeList strokeList;
/*      */   
/*      */   private transient Stroke baseStroke;
/*      */   
/*      */   private transient Stroke outlineStroke;
/*      */   
/*      */   private StrokeList outlineStrokeList;
/*      */   
/*      */   private transient Stroke baseOutlineStroke;
/*      */   
/*      */   private transient Shape shape;
/*      */   
/*      */   private ShapeList shapeList;
/*      */   
/*      */   private transient Shape baseShape;
/*      */   
/*      */   private Boolean itemLabelsVisible;
/*      */   
/*      */   private BooleanList itemLabelsVisibleList;
/*      */   
/*      */   private Boolean baseItemLabelsVisible;
/*      */   
/*      */   private Font itemLabelFont;
/*      */   
/*      */   private ObjectList itemLabelFontList;
/*      */   
/*      */   private Font baseItemLabelFont;
/*      */   
/*      */   private transient Paint itemLabelPaint;
/*      */   
/*      */   private PaintList itemLabelPaintList;
/*      */   
/*      */   private transient Paint baseItemLabelPaint;
/*      */   
/*      */   private ItemLabelPosition positiveItemLabelPosition;
/*      */   
/*      */   private ObjectList positiveItemLabelPositionList;
/*      */   
/*      */   private ItemLabelPosition basePositiveItemLabelPosition;
/*      */   
/*      */   private ItemLabelPosition negativeItemLabelPosition;
/*      */   
/*      */   private ObjectList negativeItemLabelPositionList;
/*      */   
/*      */   private ItemLabelPosition baseNegativeItemLabelPosition;
/*      */   
/*  268 */   private double itemLabelAnchorOffset = 2.0D;
/*      */   
/*      */   private Boolean createEntities;
/*      */   
/*      */   private BooleanList createEntitiesList;
/*      */   
/*      */   private boolean baseCreateEntities;
/*      */   
/*      */   private transient EventListenerList listenerList;
/*      */   
/*      */   public AbstractRenderer() {
/*  296 */     this.seriesVisible = null;
/*  297 */     this.seriesVisibleList = new BooleanList();
/*  298 */     this.baseSeriesVisible = true;
/*  300 */     this.seriesVisibleInLegend = null;
/*  301 */     this.seriesVisibleInLegendList = new BooleanList();
/*  302 */     this.baseSeriesVisibleInLegend = true;
/*  304 */     this.paint = null;
/*  305 */     this.paintList = new PaintList();
/*  306 */     this.basePaint = DEFAULT_PAINT;
/*  308 */     this.fillPaint = null;
/*  309 */     this.fillPaintList = new PaintList();
/*  310 */     this.baseFillPaint = Color.white;
/*  312 */     this.outlinePaint = null;
/*  313 */     this.outlinePaintList = new PaintList();
/*  314 */     this.baseOutlinePaint = DEFAULT_OUTLINE_PAINT;
/*  316 */     this.stroke = null;
/*  317 */     this.strokeList = new StrokeList();
/*  318 */     this.baseStroke = DEFAULT_STROKE;
/*  320 */     this.outlineStroke = null;
/*  321 */     this.outlineStrokeList = new StrokeList();
/*  322 */     this.baseOutlineStroke = DEFAULT_OUTLINE_STROKE;
/*  324 */     this.shape = null;
/*  325 */     this.shapeList = new ShapeList();
/*  326 */     this.baseShape = DEFAULT_SHAPE;
/*  328 */     this.itemLabelsVisible = null;
/*  329 */     this.itemLabelsVisibleList = new BooleanList();
/*  330 */     this.baseItemLabelsVisible = Boolean.FALSE;
/*  332 */     this.itemLabelFont = null;
/*  333 */     this.itemLabelFontList = new ObjectList();
/*  334 */     this.baseItemLabelFont = new Font("SansSerif", 0, 10);
/*  336 */     this.itemLabelPaint = null;
/*  337 */     this.itemLabelPaintList = new PaintList();
/*  338 */     this.baseItemLabelPaint = Color.black;
/*  340 */     this.positiveItemLabelPosition = null;
/*  341 */     this.positiveItemLabelPositionList = new ObjectList();
/*  342 */     this.basePositiveItemLabelPosition = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
/*  346 */     this.negativeItemLabelPosition = null;
/*  347 */     this.negativeItemLabelPositionList = new ObjectList();
/*  348 */     this.baseNegativeItemLabelPosition = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
/*  352 */     this.createEntities = null;
/*  353 */     this.createEntitiesList = new BooleanList();
/*  354 */     this.baseCreateEntities = true;
/*  356 */     this.listenerList = new EventListenerList();
/*      */   }
/*      */   
/*      */   public boolean getItemVisible(int series, int item) {
/*  379 */     return isSeriesVisible(series);
/*      */   }
/*      */   
/*      */   public boolean isSeriesVisible(int series) {
/*  391 */     boolean result = this.baseSeriesVisible;
/*  392 */     if (this.seriesVisible != null) {
/*  393 */       result = this.seriesVisible.booleanValue();
/*      */     } else {
/*  396 */       Boolean b = this.seriesVisibleList.getBoolean(series);
/*  397 */       if (b != null)
/*  398 */         result = b.booleanValue(); 
/*      */     } 
/*  401 */     return result;
/*      */   }
/*      */   
/*      */   public Boolean getSeriesVisible() {
/*  412 */     return this.seriesVisible;
/*      */   }
/*      */   
/*      */   public void setSeriesVisible(Boolean visible) {
/*  424 */     setSeriesVisible(visible, true);
/*      */   }
/*      */   
/*      */   public void setSeriesVisible(Boolean visible, boolean notify) {
/*  437 */     this.seriesVisible = visible;
/*  438 */     if (notify)
/*  439 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Boolean getSeriesVisible(int series) {
/*  451 */     return this.seriesVisibleList.getBoolean(series);
/*      */   }
/*      */   
/*      */   public void setSeriesVisible(int series, Boolean visible) {
/*  462 */     setSeriesVisible(series, visible, true);
/*      */   }
/*      */   
/*      */   public void setSeriesVisible(int series, Boolean visible, boolean notify) {
/*  475 */     this.seriesVisibleList.setBoolean(series, visible);
/*  476 */     if (notify)
/*  477 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public boolean getBaseSeriesVisible() {
/*  487 */     return this.baseSeriesVisible;
/*      */   }
/*      */   
/*      */   public void setBaseSeriesVisible(boolean visible) {
/*  498 */     setBaseSeriesVisible(visible, true);
/*      */   }
/*      */   
/*      */   public void setBaseSeriesVisible(boolean visible, boolean notify) {
/*  509 */     this.baseSeriesVisible = visible;
/*  510 */     if (notify)
/*  511 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public boolean isSeriesVisibleInLegend(int series) {
/*  526 */     boolean result = this.baseSeriesVisibleInLegend;
/*  527 */     if (this.seriesVisibleInLegend != null) {
/*  528 */       result = this.seriesVisibleInLegend.booleanValue();
/*      */     } else {
/*  531 */       Boolean b = this.seriesVisibleInLegendList.getBoolean(series);
/*  532 */       if (b != null)
/*  533 */         result = b.booleanValue(); 
/*      */     } 
/*  536 */     return result;
/*      */   }
/*      */   
/*      */   public Boolean getSeriesVisibleInLegend() {
/*  548 */     return this.seriesVisibleInLegend;
/*      */   }
/*      */   
/*      */   public void setSeriesVisibleInLegend(Boolean visible) {
/*  560 */     setSeriesVisibleInLegend(visible, true);
/*      */   }
/*      */   
/*      */   public void setSeriesVisibleInLegend(Boolean visible, boolean notify) {
/*  573 */     this.seriesVisibleInLegend = visible;
/*  574 */     if (notify)
/*  575 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Boolean getSeriesVisibleInLegend(int series) {
/*  590 */     return this.seriesVisibleInLegendList.getBoolean(series);
/*      */   }
/*      */   
/*      */   public void setSeriesVisibleInLegend(int series, Boolean visible) {
/*  601 */     setSeriesVisibleInLegend(series, visible, true);
/*      */   }
/*      */   
/*      */   public void setSeriesVisibleInLegend(int series, Boolean visible, boolean notify) {
/*  615 */     this.seriesVisibleInLegendList.setBoolean(series, visible);
/*  616 */     if (notify)
/*  617 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public boolean getBaseSeriesVisibleInLegend() {
/*  627 */     return this.baseSeriesVisibleInLegend;
/*      */   }
/*      */   
/*      */   public void setBaseSeriesVisibleInLegend(boolean visible) {
/*  638 */     setBaseSeriesVisibleInLegend(visible, true);
/*      */   }
/*      */   
/*      */   public void setBaseSeriesVisibleInLegend(boolean visible, boolean notify) {
/*  649 */     this.baseSeriesVisibleInLegend = visible;
/*  650 */     if (notify)
/*  651 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Paint getItemPaint(int row, int column) {
/*  670 */     return getSeriesPaint(row);
/*      */   }
/*      */   
/*      */   public Paint getSeriesPaint(int series) {
/*  683 */     if (this.paint != null)
/*  684 */       return this.paint; 
/*  688 */     Paint seriesPaint = this.paintList.getPaint(series);
/*  689 */     if (seriesPaint == null) {
/*  690 */       DrawingSupplier supplier = getDrawingSupplier();
/*  691 */       if (supplier != null) {
/*  692 */         seriesPaint = supplier.getNextPaint();
/*  693 */         this.paintList.setPaint(series, seriesPaint);
/*      */       } else {
/*  696 */         seriesPaint = this.basePaint;
/*      */       } 
/*      */     } 
/*  699 */     return seriesPaint;
/*      */   }
/*      */   
/*      */   public void setPaint(Paint paint) {
/*  711 */     setPaint(paint, true);
/*      */   }
/*      */   
/*      */   public void setPaint(Paint paint, boolean notify) {
/*  722 */     this.paint = paint;
/*  723 */     if (notify)
/*  724 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setSeriesPaint(int series, Paint paint) {
/*  736 */     setSeriesPaint(series, paint, true);
/*      */   }
/*      */   
/*      */   public void setSeriesPaint(int series, Paint paint, boolean notify) {
/*  748 */     this.paintList.setPaint(series, paint);
/*  749 */     if (notify)
/*  750 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Paint getBasePaint() {
/*  760 */     return this.basePaint;
/*      */   }
/*      */   
/*      */   public void setBasePaint(Paint paint) {
/*  771 */     setBasePaint(paint, true);
/*      */   }
/*      */   
/*      */   public void setBasePaint(Paint paint, boolean notify) {
/*  782 */     this.basePaint = paint;
/*  783 */     if (notify)
/*  784 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Paint getItemFillPaint(int row, int column) {
/*  802 */     return getSeriesFillPaint(row);
/*      */   }
/*      */   
/*      */   public Paint getSeriesFillPaint(int series) {
/*  815 */     if (this.fillPaint != null)
/*  816 */       return this.fillPaint; 
/*  820 */     Paint seriesFillPaint = this.fillPaintList.getPaint(series);
/*  821 */     if (seriesFillPaint == null)
/*  822 */       seriesFillPaint = this.baseFillPaint; 
/*  824 */     return seriesFillPaint;
/*      */   }
/*      */   
/*      */   public void setSeriesFillPaint(int series, Paint paint) {
/*  836 */     setSeriesFillPaint(series, paint, true);
/*      */   }
/*      */   
/*      */   public void setSeriesFillPaint(int series, Paint paint, boolean notify) {
/*  848 */     this.fillPaintList.setPaint(series, paint);
/*  849 */     if (notify)
/*  850 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setFillPaint(Paint paint) {
/*  860 */     setFillPaint(paint, true);
/*      */   }
/*      */   
/*      */   public void setFillPaint(Paint paint, boolean notify) {
/*  871 */     this.fillPaint = paint;
/*  872 */     if (notify)
/*  873 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Paint getBaseFillPaint() {
/*  883 */     return this.baseFillPaint;
/*      */   }
/*      */   
/*      */   public void setBaseFillPaint(Paint paint) {
/*  894 */     setBaseFillPaint(paint, true);
/*      */   }
/*      */   
/*      */   public void setBaseFillPaint(Paint paint, boolean notify) {
/*  905 */     if (paint == null)
/*  906 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  908 */     this.baseFillPaint = paint;
/*  909 */     if (notify)
/*  910 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Paint getItemOutlinePaint(int row, int column) {
/*  928 */     return getSeriesOutlinePaint(row);
/*      */   }
/*      */   
/*      */   public Paint getSeriesOutlinePaint(int series) {
/*  941 */     if (this.outlinePaint != null)
/*  942 */       return this.outlinePaint; 
/*  946 */     Paint seriesOutlinePaint = this.outlinePaintList.getPaint(series);
/*  947 */     if (seriesOutlinePaint == null) {
/*  948 */       DrawingSupplier supplier = getDrawingSupplier();
/*  949 */       if (supplier != null) {
/*  950 */         seriesOutlinePaint = supplier.getNextOutlinePaint();
/*  951 */         this.outlinePaintList.setPaint(series, seriesOutlinePaint);
/*      */       } else {
/*  954 */         seriesOutlinePaint = this.baseOutlinePaint;
/*      */       } 
/*      */     } 
/*  957 */     return seriesOutlinePaint;
/*      */   }
/*      */   
/*      */   public void setSeriesOutlinePaint(int series, Paint paint) {
/*  969 */     setSeriesOutlinePaint(series, paint, true);
/*      */   }
/*      */   
/*      */   public void setSeriesOutlinePaint(int series, Paint paint, boolean notify) {
/*  981 */     this.outlinePaintList.setPaint(series, paint);
/*  982 */     if (notify)
/*  983 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setOutlinePaint(Paint paint) {
/*  993 */     setOutlinePaint(paint, true);
/*      */   }
/*      */   
/*      */   public void setOutlinePaint(Paint paint, boolean notify) {
/* 1004 */     this.outlinePaint = paint;
/* 1005 */     if (notify)
/* 1006 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Paint getBaseOutlinePaint() {
/* 1016 */     return this.baseOutlinePaint;
/*      */   }
/*      */   
/*      */   public void setBaseOutlinePaint(Paint paint) {
/* 1027 */     setBaseOutlinePaint(paint, true);
/*      */   }
/*      */   
/*      */   public void setBaseOutlinePaint(Paint paint, boolean notify) {
/* 1038 */     if (paint == null)
/* 1039 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 1041 */     this.baseOutlinePaint = paint;
/* 1042 */     if (notify)
/* 1043 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Stroke getItemStroke(int row, int column) {
/* 1061 */     return getSeriesStroke(row);
/*      */   }
/*      */   
/*      */   public Stroke getSeriesStroke(int series) {
/* 1074 */     if (this.stroke != null)
/* 1075 */       return this.stroke; 
/* 1079 */     Stroke result = this.strokeList.getStroke(series);
/* 1080 */     if (result == null) {
/* 1081 */       DrawingSupplier supplier = getDrawingSupplier();
/* 1082 */       if (supplier != null) {
/* 1083 */         result = supplier.getNextStroke();
/* 1084 */         this.strokeList.setStroke(series, result);
/*      */       } else {
/* 1087 */         result = this.baseStroke;
/*      */       } 
/*      */     } 
/* 1090 */     return result;
/*      */   }
/*      */   
/*      */   public void setStroke(Stroke stroke) {
/* 1101 */     setStroke(stroke, true);
/*      */   }
/*      */   
/*      */   public void setStroke(Stroke stroke, boolean notify) {
/* 1112 */     this.stroke = stroke;
/* 1113 */     if (notify)
/* 1114 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setSeriesStroke(int series, Stroke stroke) {
/* 1126 */     setSeriesStroke(series, stroke, true);
/*      */   }
/*      */   
/*      */   public void setSeriesStroke(int series, Stroke stroke, boolean notify) {
/* 1138 */     this.strokeList.setStroke(series, stroke);
/* 1139 */     if (notify)
/* 1140 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Stroke getBaseStroke() {
/* 1150 */     return this.baseStroke;
/*      */   }
/*      */   
/*      */   public void setBaseStroke(Stroke stroke) {
/* 1160 */     setBaseStroke(stroke, true);
/*      */   }
/*      */   
/*      */   public void setBaseStroke(Stroke stroke, boolean notify) {
/* 1171 */     if (stroke == null)
/* 1172 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/* 1174 */     this.baseStroke = stroke;
/* 1175 */     if (notify)
/* 1176 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Stroke getItemOutlineStroke(int row, int column) {
/* 1193 */     return getSeriesOutlineStroke(row);
/*      */   }
/*      */   
/*      */   public Stroke getSeriesOutlineStroke(int series) {
/* 1206 */     if (this.outlineStroke != null)
/* 1207 */       return this.outlineStroke; 
/* 1211 */     Stroke result = this.outlineStrokeList.getStroke(series);
/* 1212 */     if (result == null) {
/* 1213 */       DrawingSupplier supplier = getDrawingSupplier();
/* 1214 */       if (supplier != null) {
/* 1215 */         result = supplier.getNextOutlineStroke();
/* 1216 */         this.outlineStrokeList.setStroke(series, result);
/*      */       } else {
/* 1219 */         result = this.baseOutlineStroke;
/*      */       } 
/*      */     } 
/* 1222 */     return result;
/*      */   }
/*      */   
/*      */   public void setOutlineStroke(Stroke stroke) {
/* 1233 */     setOutlineStroke(stroke, true);
/*      */   }
/*      */   
/*      */   public void setOutlineStroke(Stroke stroke, boolean notify) {
/* 1244 */     this.outlineStroke = stroke;
/* 1245 */     if (notify)
/* 1246 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setSeriesOutlineStroke(int series, Stroke stroke) {
/* 1258 */     setSeriesOutlineStroke(series, stroke, true);
/*      */   }
/*      */   
/*      */   public void setSeriesOutlineStroke(int series, Stroke stroke, boolean notify) {
/* 1271 */     this.outlineStrokeList.setStroke(series, stroke);
/* 1272 */     if (notify)
/* 1273 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Stroke getBaseOutlineStroke() {
/* 1283 */     return this.baseOutlineStroke;
/*      */   }
/*      */   
/*      */   public void setBaseOutlineStroke(Stroke stroke) {
/* 1293 */     setBaseOutlineStroke(stroke, true);
/*      */   }
/*      */   
/*      */   public void setBaseOutlineStroke(Stroke stroke, boolean notify) {
/* 1305 */     if (stroke == null)
/* 1306 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/* 1308 */     this.baseOutlineStroke = stroke;
/* 1309 */     if (notify)
/* 1310 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Shape getItemShape(int row, int column) {
/* 1328 */     return getSeriesShape(row);
/*      */   }
/*      */   
/*      */   public Shape getSeriesShape(int series) {
/* 1341 */     if (this.shape != null)
/* 1342 */       return this.shape; 
/* 1346 */     Shape result = this.shapeList.getShape(series);
/* 1347 */     if (result == null) {
/* 1348 */       DrawingSupplier supplier = getDrawingSupplier();
/* 1349 */       if (supplier != null) {
/* 1350 */         result = supplier.getNextShape();
/* 1351 */         this.shapeList.setShape(series, result);
/*      */       } else {
/* 1354 */         result = this.baseShape;
/*      */       } 
/*      */     } 
/* 1357 */     return result;
/*      */   }
/*      */   
/*      */   public void setShape(Shape shape) {
/* 1368 */     setShape(shape, true);
/*      */   }
/*      */   
/*      */   public void setShape(Shape shape, boolean notify) {
/* 1379 */     this.shape = shape;
/* 1380 */     if (notify)
/* 1381 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setSeriesShape(int series, Shape shape) {
/* 1393 */     setSeriesShape(series, shape, true);
/*      */   }
/*      */   
/*      */   public void setSeriesShape(int series, Shape shape, boolean notify) {
/* 1405 */     this.shapeList.setShape(series, shape);
/* 1406 */     if (notify)
/* 1407 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Shape getBaseShape() {
/* 1417 */     return this.baseShape;
/*      */   }
/*      */   
/*      */   public void setBaseShape(Shape shape) {
/* 1428 */     setBaseShape(shape, true);
/*      */   }
/*      */   
/*      */   public void setBaseShape(Shape shape, boolean notify) {
/* 1439 */     if (shape == null)
/* 1440 */       throw new IllegalArgumentException("Null 'shape' argument."); 
/* 1442 */     this.baseShape = shape;
/* 1443 */     if (notify)
/* 1444 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public boolean isItemLabelVisible(int row, int column) {
/* 1460 */     return isSeriesItemLabelsVisible(row);
/*      */   }
/*      */   
/*      */   public boolean isSeriesItemLabelsVisible(int series) {
/* 1474 */     if (this.itemLabelsVisible != null)
/* 1475 */       return this.itemLabelsVisible.booleanValue(); 
/* 1479 */     Boolean b = this.itemLabelsVisibleList.getBoolean(series);
/* 1480 */     if (b == null)
/* 1481 */       b = this.baseItemLabelsVisible; 
/* 1483 */     if (b == null)
/* 1484 */       b = Boolean.FALSE; 
/* 1486 */     return b.booleanValue();
/*      */   }
/*      */   
/*      */   public void setItemLabelsVisible(boolean visible) {
/* 1496 */     setItemLabelsVisible(BooleanUtilities.valueOf(visible));
/*      */   }
/*      */   
/*      */   public void setItemLabelsVisible(Boolean visible) {
/* 1508 */     setItemLabelsVisible(visible, true);
/*      */   }
/*      */   
/*      */   public void setItemLabelsVisible(Boolean visible, boolean notify) {
/* 1521 */     this.itemLabelsVisible = visible;
/* 1522 */     if (notify)
/* 1523 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setSeriesItemLabelsVisible(int series, boolean visible) {
/* 1534 */     setSeriesItemLabelsVisible(series, BooleanUtilities.valueOf(visible));
/*      */   }
/*      */   
/*      */   public void setSeriesItemLabelsVisible(int series, Boolean visible) {
/* 1544 */     setSeriesItemLabelsVisible(series, visible, true);
/*      */   }
/*      */   
/*      */   public void setSeriesItemLabelsVisible(int series, Boolean visible, boolean notify) {
/* 1558 */     this.itemLabelsVisibleList.setBoolean(series, visible);
/* 1559 */     if (notify)
/* 1560 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Boolean getBaseItemLabelsVisible() {
/* 1570 */     return this.baseItemLabelsVisible;
/*      */   }
/*      */   
/*      */   public void setBaseItemLabelsVisible(boolean visible) {
/* 1579 */     setBaseItemLabelsVisible(BooleanUtilities.valueOf(visible));
/*      */   }
/*      */   
/*      */   public void setBaseItemLabelsVisible(Boolean visible) {
/* 1588 */     setBaseItemLabelsVisible(visible, true);
/*      */   }
/*      */   
/*      */   public void setBaseItemLabelsVisible(Boolean visible, boolean notify) {
/* 1600 */     this.baseItemLabelsVisible = visible;
/* 1601 */     if (notify)
/* 1602 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Font getItemLabelFont(int row, int column) {
/* 1617 */     Font result = this.itemLabelFont;
/* 1618 */     if (result == null) {
/* 1619 */       result = getSeriesItemLabelFont(row);
/* 1620 */       if (result == null)
/* 1621 */         result = this.baseItemLabelFont; 
/*      */     } 
/* 1624 */     return result;
/*      */   }
/*      */   
/*      */   public Font getItemLabelFont() {
/* 1634 */     return this.itemLabelFont;
/*      */   }
/*      */   
/*      */   public void setItemLabelFont(Font font) {
/* 1646 */     setItemLabelFont(font, true);
/*      */   }
/*      */   
/*      */   public void setItemLabelFont(Font font, boolean notify) {
/* 1658 */     this.itemLabelFont = font;
/* 1659 */     if (notify)
/* 1660 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Font getSeriesItemLabelFont(int series) {
/* 1672 */     return (Font)this.itemLabelFontList.get(series);
/*      */   }
/*      */   
/*      */   public void setSeriesItemLabelFont(int series, Font font) {
/* 1683 */     setSeriesItemLabelFont(series, font, true);
/*      */   }
/*      */   
/*      */   public void setSeriesItemLabelFont(int series, Font font, boolean notify) {
/* 1696 */     this.itemLabelFontList.set(series, font);
/* 1697 */     if (notify)
/* 1698 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Font getBaseItemLabelFont() {
/* 1709 */     return this.baseItemLabelFont;
/*      */   }
/*      */   
/*      */   public void setBaseItemLabelFont(Font font) {
/* 1719 */     if (font == null)
/* 1720 */       throw new IllegalArgumentException("Null 'font' argument."); 
/* 1722 */     setBaseItemLabelFont(font, true);
/*      */   }
/*      */   
/*      */   public void setBaseItemLabelFont(Font font, boolean notify) {
/* 1734 */     this.baseItemLabelFont = font;
/* 1735 */     if (notify)
/* 1736 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Paint getItemLabelPaint(int row, int column) {
/* 1751 */     Paint result = this.itemLabelPaint;
/* 1752 */     if (result == null) {
/* 1753 */       result = getSeriesItemLabelPaint(row);
/* 1754 */       if (result == null)
/* 1755 */         result = this.baseItemLabelPaint; 
/*      */     } 
/* 1758 */     return result;
/*      */   }
/*      */   
/*      */   public Paint getItemLabelPaint() {
/* 1769 */     return this.itemLabelPaint;
/*      */   }
/*      */   
/*      */   public void setItemLabelPaint(Paint paint) {
/* 1779 */     setItemLabelPaint(paint, true);
/*      */   }
/*      */   
/*      */   public void setItemLabelPaint(Paint paint, boolean notify) {
/* 1791 */     this.itemLabelPaint = paint;
/* 1792 */     if (notify)
/* 1793 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Paint getSeriesItemLabelPaint(int series) {
/* 1805 */     return this.itemLabelPaintList.getPaint(series);
/*      */   }
/*      */   
/*      */   public void setSeriesItemLabelPaint(int series, Paint paint) {
/* 1816 */     setSeriesItemLabelPaint(series, paint, true);
/*      */   }
/*      */   
/*      */   public void setSeriesItemLabelPaint(int series, Paint paint, boolean notify) {
/* 1830 */     this.itemLabelPaintList.setPaint(series, paint);
/* 1831 */     if (notify)
/* 1832 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Paint getBaseItemLabelPaint() {
/* 1842 */     return this.baseItemLabelPaint;
/*      */   }
/*      */   
/*      */   public void setBaseItemLabelPaint(Paint paint) {
/* 1853 */     setBaseItemLabelPaint(paint, true);
/*      */   }
/*      */   
/*      */   public void setBaseItemLabelPaint(Paint paint, boolean notify) {
/* 1865 */     if (paint == null)
/* 1866 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 1868 */     this.baseItemLabelPaint = paint;
/* 1869 */     if (notify)
/* 1870 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public ItemLabelPosition getPositiveItemLabelPosition(int row, int column) {
/* 1885 */     return getSeriesPositiveItemLabelPosition(row);
/*      */   }
/*      */   
/*      */   public ItemLabelPosition getPositiveItemLabelPosition() {
/* 1894 */     return this.positiveItemLabelPosition;
/*      */   }
/*      */   
/*      */   public void setPositiveItemLabelPosition(ItemLabelPosition position) {
/* 1906 */     setPositiveItemLabelPosition(position, true);
/*      */   }
/*      */   
/*      */   public void setPositiveItemLabelPosition(ItemLabelPosition position, boolean notify) {
/* 1918 */     this.positiveItemLabelPosition = position;
/* 1919 */     if (notify)
/* 1920 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public ItemLabelPosition getSeriesPositiveItemLabelPosition(int series) {
/* 1934 */     if (this.positiveItemLabelPosition != null)
/* 1935 */       return this.positiveItemLabelPosition; 
/* 1939 */     ItemLabelPosition position = (ItemLabelPosition)this.positiveItemLabelPositionList.get(series);
/* 1941 */     if (position == null)
/* 1942 */       position = this.basePositiveItemLabelPosition; 
/* 1944 */     return position;
/*      */   }
/*      */   
/*      */   public void setSeriesPositiveItemLabelPosition(int series, ItemLabelPosition position) {
/* 1957 */     setSeriesPositiveItemLabelPosition(series, position, true);
/*      */   }
/*      */   
/*      */   public void setSeriesPositiveItemLabelPosition(int series, ItemLabelPosition position, boolean notify) {
/* 1972 */     this.positiveItemLabelPositionList.set(series, position);
/* 1973 */     if (notify)
/* 1974 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public ItemLabelPosition getBasePositiveItemLabelPosition() {
/* 1984 */     return this.basePositiveItemLabelPosition;
/*      */   }
/*      */   
/*      */   public void setBasePositiveItemLabelPosition(ItemLabelPosition position) {
/* 1994 */     setBasePositiveItemLabelPosition(position, true);
/*      */   }
/*      */   
/*      */   public void setBasePositiveItemLabelPosition(ItemLabelPosition position, boolean notify) {
/* 2006 */     if (position == null)
/* 2007 */       throw new IllegalArgumentException("Null 'position' argument."); 
/* 2009 */     this.basePositiveItemLabelPosition = position;
/* 2010 */     if (notify)
/* 2011 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public ItemLabelPosition getNegativeItemLabelPosition(int row, int column) {
/* 2028 */     return getSeriesNegativeItemLabelPosition(row);
/*      */   }
/*      */   
/*      */   public ItemLabelPosition getNegativeItemLabelPosition() {
/* 2037 */     return this.negativeItemLabelPosition;
/*      */   }
/*      */   
/*      */   public void setNegativeItemLabelPosition(ItemLabelPosition position) {
/* 2049 */     setNegativeItemLabelPosition(position, true);
/*      */   }
/*      */   
/*      */   public void setNegativeItemLabelPosition(ItemLabelPosition position, boolean notify) {
/* 2062 */     this.negativeItemLabelPosition = position;
/* 2063 */     if (notify)
/* 2064 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public ItemLabelPosition getSeriesNegativeItemLabelPosition(int series) {
/* 2078 */     if (this.negativeItemLabelPosition != null)
/* 2079 */       return this.negativeItemLabelPosition; 
/* 2083 */     ItemLabelPosition position = (ItemLabelPosition)this.negativeItemLabelPositionList.get(series);
/* 2085 */     if (position == null)
/* 2086 */       position = this.baseNegativeItemLabelPosition; 
/* 2088 */     return position;
/*      */   }
/*      */   
/*      */   public void setSeriesNegativeItemLabelPosition(int series, ItemLabelPosition position) {
/* 2101 */     setSeriesNegativeItemLabelPosition(series, position, true);
/*      */   }
/*      */   
/*      */   public void setSeriesNegativeItemLabelPosition(int series, ItemLabelPosition position, boolean notify) {
/* 2116 */     this.negativeItemLabelPositionList.set(series, position);
/* 2117 */     if (notify)
/* 2118 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public ItemLabelPosition getBaseNegativeItemLabelPosition() {
/* 2128 */     return this.baseNegativeItemLabelPosition;
/*      */   }
/*      */   
/*      */   public void setBaseNegativeItemLabelPosition(ItemLabelPosition position) {
/* 2138 */     setBaseNegativeItemLabelPosition(position, true);
/*      */   }
/*      */   
/*      */   public void setBaseNegativeItemLabelPosition(ItemLabelPosition position, boolean notify) {
/* 2150 */     if (position == null)
/* 2151 */       throw new IllegalArgumentException("Null 'position' argument."); 
/* 2153 */     this.baseNegativeItemLabelPosition = position;
/* 2154 */     if (notify)
/* 2155 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public double getItemLabelAnchorOffset() {
/* 2165 */     return this.itemLabelAnchorOffset;
/*      */   }
/*      */   
/*      */   public void setItemLabelAnchorOffset(double offset) {
/* 2174 */     this.itemLabelAnchorOffset = offset;
/* 2175 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getItemCreateEntity(int series, int item) {
/* 2188 */     if (this.createEntities != null)
/* 2189 */       return this.createEntities.booleanValue(); 
/* 2192 */     Boolean b = getSeriesCreateEntities(series);
/* 2193 */     if (b != null)
/* 2194 */       return b.booleanValue(); 
/* 2197 */     return this.baseCreateEntities;
/*      */   }
/*      */   
/*      */   public Boolean getCreateEntities() {
/* 2211 */     return this.createEntities;
/*      */   }
/*      */   
/*      */   public void setCreateEntities(Boolean create) {
/* 2224 */     setCreateEntities(create, true);
/*      */   }
/*      */   
/*      */   public void setCreateEntities(Boolean create, boolean notify) {
/* 2238 */     this.createEntities = create;
/* 2239 */     if (notify)
/* 2240 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public Boolean getSeriesCreateEntities(int series) {
/* 2253 */     return this.createEntitiesList.getBoolean(series);
/*      */   }
/*      */   
/*      */   public void setSeriesCreateEntities(int series, Boolean create) {
/* 2264 */     setSeriesCreateEntities(series, create, true);
/*      */   }
/*      */   
/*      */   public void setSeriesCreateEntities(int series, Boolean create, boolean notify) {
/* 2278 */     this.createEntitiesList.setBoolean(series, create);
/* 2279 */     if (notify)
/* 2280 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public boolean getBaseCreateEntities() {
/* 2290 */     return this.baseCreateEntities;
/*      */   }
/*      */   
/*      */   public void setBaseCreateEntities(boolean create) {
/* 2302 */     setBaseCreateEntities(create, true);
/*      */   }
/*      */   
/*      */   public void setBaseCreateEntities(boolean create, boolean notify) {
/* 2314 */     this.baseCreateEntities = create;
/* 2315 */     if (notify)
/* 2316 */       notifyListeners(new RendererChangeEvent(this)); 
/*      */   }
/*      */   
/* 2321 */   private static final double ADJ = Math.cos(0.5235987755982988D);
/*      */   
/* 2324 */   private static final double OPP = Math.sin(0.5235987755982988D);
/*      */   
/*      */   protected Point2D calculateLabelAnchorPoint(ItemLabelAnchor anchor, double x, double y, PlotOrientation orientation) {
/* 2341 */     Point2D result = null;
/* 2343 */     if (anchor == ItemLabelAnchor.CENTER) {
/* 2344 */       result = new Point2D.Double(x, y);
/* 2346 */     } else if (anchor == ItemLabelAnchor.INSIDE1) {
/* 2347 */       result = new Point2D.Double(x + OPP * this.itemLabelAnchorOffset, y - ADJ * this.itemLabelAnchorOffset);
/* 2352 */     } else if (anchor == ItemLabelAnchor.INSIDE2) {
/* 2353 */       result = new Point2D.Double(x + ADJ * this.itemLabelAnchorOffset, y - OPP * this.itemLabelAnchorOffset);
/* 2358 */     } else if (anchor == ItemLabelAnchor.INSIDE3) {
/* 2359 */       result = new Point2D.Double(x + this.itemLabelAnchorOffset, y);
/* 2361 */     } else if (anchor == ItemLabelAnchor.INSIDE4) {
/* 2362 */       result = new Point2D.Double(x + ADJ * this.itemLabelAnchorOffset, y + OPP * this.itemLabelAnchorOffset);
/* 2367 */     } else if (anchor == ItemLabelAnchor.INSIDE5) {
/* 2368 */       result = new Point2D.Double(x + OPP * this.itemLabelAnchorOffset, y + ADJ * this.itemLabelAnchorOffset);
/* 2373 */     } else if (anchor == ItemLabelAnchor.INSIDE6) {
/* 2374 */       result = new Point2D.Double(x, y + this.itemLabelAnchorOffset);
/* 2376 */     } else if (anchor == ItemLabelAnchor.INSIDE7) {
/* 2377 */       result = new Point2D.Double(x - OPP * this.itemLabelAnchorOffset, y + ADJ * this.itemLabelAnchorOffset);
/* 2382 */     } else if (anchor == ItemLabelAnchor.INSIDE8) {
/* 2383 */       result = new Point2D.Double(x - ADJ * this.itemLabelAnchorOffset, y + OPP * this.itemLabelAnchorOffset);
/* 2388 */     } else if (anchor == ItemLabelAnchor.INSIDE9) {
/* 2389 */       result = new Point2D.Double(x - this.itemLabelAnchorOffset, y);
/* 2391 */     } else if (anchor == ItemLabelAnchor.INSIDE10) {
/* 2392 */       result = new Point2D.Double(x - ADJ * this.itemLabelAnchorOffset, y - OPP * this.itemLabelAnchorOffset);
/* 2397 */     } else if (anchor == ItemLabelAnchor.INSIDE11) {
/* 2398 */       result = new Point2D.Double(x - OPP * this.itemLabelAnchorOffset, y - ADJ * this.itemLabelAnchorOffset);
/* 2403 */     } else if (anchor == ItemLabelAnchor.INSIDE12) {
/* 2404 */       result = new Point2D.Double(x, y - this.itemLabelAnchorOffset);
/* 2406 */     } else if (anchor == ItemLabelAnchor.OUTSIDE1) {
/* 2407 */       result = new Point2D.Double(x + 2.0D * OPP * this.itemLabelAnchorOffset, y - 2.0D * ADJ * this.itemLabelAnchorOffset);
/* 2412 */     } else if (anchor == ItemLabelAnchor.OUTSIDE2) {
/* 2413 */       result = new Point2D.Double(x + 2.0D * ADJ * this.itemLabelAnchorOffset, y - 2.0D * OPP * this.itemLabelAnchorOffset);
/* 2418 */     } else if (anchor == ItemLabelAnchor.OUTSIDE3) {
/* 2419 */       result = new Point2D.Double(x + 2.0D * this.itemLabelAnchorOffset, y);
/* 2423 */     } else if (anchor == ItemLabelAnchor.OUTSIDE4) {
/* 2424 */       result = new Point2D.Double(x + 2.0D * ADJ * this.itemLabelAnchorOffset, y + 2.0D * OPP * this.itemLabelAnchorOffset);
/* 2429 */     } else if (anchor == ItemLabelAnchor.OUTSIDE5) {
/* 2430 */       result = new Point2D.Double(x + 2.0D * OPP * this.itemLabelAnchorOffset, y + 2.0D * ADJ * this.itemLabelAnchorOffset);
/* 2435 */     } else if (anchor == ItemLabelAnchor.OUTSIDE6) {
/* 2436 */       result = new Point2D.Double(x, y + 2.0D * this.itemLabelAnchorOffset);
/* 2440 */     } else if (anchor == ItemLabelAnchor.OUTSIDE7) {
/* 2441 */       result = new Point2D.Double(x - 2.0D * OPP * this.itemLabelAnchorOffset, y + 2.0D * ADJ * this.itemLabelAnchorOffset);
/* 2446 */     } else if (anchor == ItemLabelAnchor.OUTSIDE8) {
/* 2447 */       result = new Point2D.Double(x - 2.0D * ADJ * this.itemLabelAnchorOffset, y + 2.0D * OPP * this.itemLabelAnchorOffset);
/* 2452 */     } else if (anchor == ItemLabelAnchor.OUTSIDE9) {
/* 2453 */       result = new Point2D.Double(x - 2.0D * this.itemLabelAnchorOffset, y);
/* 2457 */     } else if (anchor == ItemLabelAnchor.OUTSIDE10) {
/* 2458 */       result = new Point2D.Double(x - 2.0D * ADJ * this.itemLabelAnchorOffset, y - 2.0D * OPP * this.itemLabelAnchorOffset);
/* 2463 */     } else if (anchor == ItemLabelAnchor.OUTSIDE11) {
/* 2464 */       result = new Point2D.Double(x - 2.0D * OPP * this.itemLabelAnchorOffset, y - 2.0D * ADJ * this.itemLabelAnchorOffset);
/* 2469 */     } else if (anchor == ItemLabelAnchor.OUTSIDE12) {
/* 2470 */       result = new Point2D.Double(x, y - 2.0D * this.itemLabelAnchorOffset);
/*      */     } 
/* 2475 */     return result;
/*      */   }
/*      */   
/*      */   public void addChangeListener(RendererChangeListener listener) {
/* 2485 */     if (listener == null)
/* 2486 */       throw new IllegalArgumentException("Null 'listener' argument."); 
/* 2488 */     this.listenerList.add(RendererChangeListener.class, listener);
/*      */   }
/*      */   
/*      */   public void removeChangeListener(RendererChangeListener listener) {
/* 2498 */     if (listener == null)
/* 2499 */       throw new IllegalArgumentException("Null 'listener' argument."); 
/* 2501 */     this.listenerList.remove(RendererChangeListener.class, listener);
/*      */   }
/*      */   
/*      */   public boolean hasListener(EventListener listener) {
/* 2514 */     List list = Arrays.asList(this.listenerList.getListenerList());
/* 2515 */     return list.contains(listener);
/*      */   }
/*      */   
/*      */   public void notifyListeners(RendererChangeEvent event) {
/* 2525 */     Object[] ls = this.listenerList.getListenerList();
/* 2526 */     for (int i = ls.length - 2; i >= 0; i -= 2) {
/* 2527 */       if (ls[i] == RendererChangeListener.class)
/* 2528 */         ((RendererChangeListener)ls[i + 1]).rendererChanged(event); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 2542 */     if (obj == this)
/* 2543 */       return true; 
/* 2545 */     if (!(obj instanceof AbstractRenderer))
/* 2546 */       return false; 
/* 2548 */     AbstractRenderer that = (AbstractRenderer)obj;
/* 2549 */     if (!ObjectUtilities.equal(this.seriesVisible, that.seriesVisible))
/* 2550 */       return false; 
/* 2552 */     if (!this.seriesVisibleList.equals(that.seriesVisibleList))
/* 2553 */       return false; 
/* 2555 */     if (this.baseSeriesVisible != that.baseSeriesVisible)
/* 2556 */       return false; 
/* 2558 */     if (!ObjectUtilities.equal(this.seriesVisibleInLegend, that.seriesVisibleInLegend))
/* 2560 */       return false; 
/* 2562 */     if (!this.seriesVisibleInLegendList.equals(that.seriesVisibleInLegendList))
/* 2564 */       return false; 
/* 2566 */     if (this.baseSeriesVisibleInLegend != that.baseSeriesVisibleInLegend)
/* 2567 */       return false; 
/* 2569 */     if (!PaintUtilities.equal(this.paint, that.paint))
/* 2570 */       return false; 
/* 2572 */     if (!ObjectUtilities.equal(this.paintList, that.paintList))
/* 2573 */       return false; 
/* 2575 */     if (!PaintUtilities.equal(this.basePaint, that.basePaint))
/* 2576 */       return false; 
/* 2578 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint))
/* 2579 */       return false; 
/* 2581 */     if (!ObjectUtilities.equal(this.fillPaintList, that.fillPaintList))
/* 2582 */       return false; 
/* 2584 */     if (!PaintUtilities.equal(this.baseFillPaint, that.baseFillPaint))
/* 2585 */       return false; 
/* 2587 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint))
/* 2588 */       return false; 
/* 2590 */     if (!ObjectUtilities.equal(this.outlinePaintList, that.outlinePaintList))
/* 2592 */       return false; 
/* 2594 */     if (!PaintUtilities.equal(this.baseOutlinePaint, that.baseOutlinePaint))
/* 2596 */       return false; 
/* 2598 */     if (!ObjectUtilities.equal(this.stroke, that.stroke))
/* 2599 */       return false; 
/* 2601 */     if (!ObjectUtilities.equal(this.strokeList, that.strokeList))
/* 2602 */       return false; 
/* 2604 */     if (!ObjectUtilities.equal(this.baseStroke, that.baseStroke))
/* 2605 */       return false; 
/* 2607 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke))
/* 2608 */       return false; 
/* 2610 */     if (!ObjectUtilities.equal(this.outlineStrokeList, that.outlineStrokeList))
/* 2613 */       return false; 
/* 2615 */     if (!ObjectUtilities.equal(this.baseOutlineStroke, that.baseOutlineStroke))
/* 2618 */       return false; 
/* 2620 */     if (!ObjectUtilities.equal(this.shape, that.shape))
/* 2621 */       return false; 
/* 2623 */     if (!ObjectUtilities.equal(this.shapeList, that.shapeList))
/* 2624 */       return false; 
/* 2626 */     if (!ObjectUtilities.equal(this.baseShape, that.baseShape))
/* 2627 */       return false; 
/* 2629 */     if (!ObjectUtilities.equal(this.itemLabelsVisible, that.itemLabelsVisible))
/* 2632 */       return false; 
/* 2634 */     if (!ObjectUtilities.equal(this.itemLabelsVisibleList, that.itemLabelsVisibleList))
/* 2637 */       return false; 
/* 2639 */     if (!ObjectUtilities.equal(this.baseItemLabelsVisible, that.baseItemLabelsVisible))
/* 2642 */       return false; 
/* 2644 */     if (!ObjectUtilities.equal(this.itemLabelFont, that.itemLabelFont))
/* 2645 */       return false; 
/* 2647 */     if (!ObjectUtilities.equal(this.itemLabelFontList, that.itemLabelFontList))
/* 2650 */       return false; 
/* 2652 */     if (!ObjectUtilities.equal(this.baseItemLabelFont, that.baseItemLabelFont))
/* 2655 */       return false; 
/* 2658 */     if (!PaintUtilities.equal(this.itemLabelPaint, that.itemLabelPaint))
/* 2659 */       return false; 
/* 2661 */     if (!ObjectUtilities.equal(this.itemLabelPaintList, that.itemLabelPaintList))
/* 2664 */       return false; 
/* 2666 */     if (!PaintUtilities.equal(this.baseItemLabelPaint, that.baseItemLabelPaint))
/* 2669 */       return false; 
/* 2672 */     if (!ObjectUtilities.equal(this.positiveItemLabelPosition, that.positiveItemLabelPosition))
/* 2675 */       return false; 
/* 2677 */     if (!ObjectUtilities.equal(this.positiveItemLabelPositionList, that.positiveItemLabelPositionList))
/* 2681 */       return false; 
/* 2683 */     if (!ObjectUtilities.equal(this.basePositiveItemLabelPosition, that.basePositiveItemLabelPosition))
/* 2687 */       return false; 
/* 2690 */     if (!ObjectUtilities.equal(this.negativeItemLabelPosition, that.negativeItemLabelPosition))
/* 2693 */       return false; 
/* 2695 */     if (!ObjectUtilities.equal(this.negativeItemLabelPositionList, that.negativeItemLabelPositionList))
/* 2699 */       return false; 
/* 2701 */     if (!ObjectUtilities.equal(this.baseNegativeItemLabelPosition, that.baseNegativeItemLabelPosition))
/* 2705 */       return false; 
/* 2707 */     if (this.itemLabelAnchorOffset != that.itemLabelAnchorOffset)
/* 2708 */       return false; 
/* 2710 */     if (!ObjectUtilities.equal(this.createEntities, that.createEntities))
/* 2711 */       return false; 
/* 2713 */     if (!ObjectUtilities.equal(this.createEntitiesList, that.createEntitiesList))
/* 2715 */       return false; 
/* 2717 */     if (this.baseCreateEntities != that.baseCreateEntities)
/* 2718 */       return false; 
/* 2720 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 2729 */     int result = 193;
/* 2730 */     result = 37 * result + ObjectUtilities.hashCode(this.stroke);
/* 2731 */     result = 37 * result + ObjectUtilities.hashCode(this.baseStroke);
/* 2732 */     result = 37 * result + ObjectUtilities.hashCode(this.outlineStroke);
/* 2733 */     result = 37 * result + ObjectUtilities.hashCode(this.baseOutlineStroke);
/* 2734 */     return result;
/*      */   }
/*      */   
/*      */   protected Object clone() throws CloneNotSupportedException {
/* 2746 */     AbstractRenderer clone = (AbstractRenderer)super.clone();
/* 2749 */     if (this.paintList != null)
/* 2750 */       clone.paintList = (PaintList)this.paintList.clone(); 
/* 2754 */     if (this.fillPaintList != null)
/* 2755 */       clone.fillPaintList = (PaintList)this.fillPaintList.clone(); 
/* 2758 */     if (this.outlinePaintList != null)
/* 2759 */       clone.outlinePaintList = (PaintList)this.outlinePaintList.clone(); 
/* 2764 */     if (this.strokeList != null)
/* 2765 */       clone.strokeList = (StrokeList)this.strokeList.clone(); 
/* 2770 */     if (this.outlineStrokeList != null)
/* 2771 */       clone.outlineStrokeList = (StrokeList)this.outlineStrokeList.clone(); 
/* 2776 */     if (this.shape != null)
/* 2777 */       clone.shape = ShapeUtilities.clone(this.shape); 
/* 2779 */     if (this.baseShape != null)
/* 2780 */       clone.baseShape = ShapeUtilities.clone(this.baseShape); 
/* 2784 */     if (this.itemLabelsVisibleList != null)
/* 2785 */       clone.itemLabelsVisibleList = (BooleanList)this.itemLabelsVisibleList.clone(); 
/* 2791 */     if (this.itemLabelFontList != null)
/* 2792 */       clone.itemLabelFontList = (ObjectList)this.itemLabelFontList.clone(); 
/* 2798 */     if (this.itemLabelPaintList != null)
/* 2799 */       clone.itemLabelPaintList = (PaintList)this.itemLabelPaintList.clone(); 
/* 2805 */     if (this.positiveItemLabelPositionList != null)
/* 2806 */       clone.positiveItemLabelPositionList = (ObjectList)this.positiveItemLabelPositionList.clone(); 
/* 2812 */     if (this.negativeItemLabelPositionList != null)
/* 2813 */       clone.negativeItemLabelPositionList = (ObjectList)this.negativeItemLabelPositionList.clone(); 
/* 2818 */     return clone;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 2830 */     stream.defaultWriteObject();
/* 2831 */     SerialUtilities.writePaint(this.paint, stream);
/* 2832 */     SerialUtilities.writePaint(this.basePaint, stream);
/* 2833 */     SerialUtilities.writePaint(this.fillPaint, stream);
/* 2834 */     SerialUtilities.writePaint(this.baseFillPaint, stream);
/* 2835 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 2836 */     SerialUtilities.writePaint(this.baseOutlinePaint, stream);
/* 2837 */     SerialUtilities.writeStroke(this.stroke, stream);
/* 2838 */     SerialUtilities.writeStroke(this.baseStroke, stream);
/* 2839 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 2840 */     SerialUtilities.writeStroke(this.baseOutlineStroke, stream);
/* 2841 */     SerialUtilities.writeShape(this.shape, stream);
/* 2842 */     SerialUtilities.writeShape(this.baseShape, stream);
/* 2843 */     SerialUtilities.writePaint(this.itemLabelPaint, stream);
/* 2844 */     SerialUtilities.writePaint(this.baseItemLabelPaint, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 2859 */     stream.defaultReadObject();
/* 2860 */     this.paint = SerialUtilities.readPaint(stream);
/* 2861 */     this.basePaint = SerialUtilities.readPaint(stream);
/* 2862 */     this.fillPaint = SerialUtilities.readPaint(stream);
/* 2863 */     this.baseFillPaint = SerialUtilities.readPaint(stream);
/* 2864 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 2865 */     this.baseOutlinePaint = SerialUtilities.readPaint(stream);
/* 2866 */     this.stroke = SerialUtilities.readStroke(stream);
/* 2867 */     this.baseStroke = SerialUtilities.readStroke(stream);
/* 2868 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 2869 */     this.baseOutlineStroke = SerialUtilities.readStroke(stream);
/* 2870 */     this.shape = SerialUtilities.readShape(stream);
/* 2871 */     this.baseShape = SerialUtilities.readShape(stream);
/* 2872 */     this.itemLabelPaint = SerialUtilities.readPaint(stream);
/* 2873 */     this.baseItemLabelPaint = SerialUtilities.readPaint(stream);
/* 2877 */     this.listenerList = new EventListenerList();
/*      */   }
/*      */   
/*      */   public abstract DrawingSupplier getDrawingSupplier();
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\AbstractRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */