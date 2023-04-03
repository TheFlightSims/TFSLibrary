/*      */ package org.jfree.chart.renderer.xy;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Point;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.ImageObserver;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.labels.XYToolTipGenerator;
/*      */ import org.jfree.chart.plot.CrosshairState;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.chart.urls.XYURLGenerator;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.util.BooleanList;
/*      */ import org.jfree.util.BooleanUtilities;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ import org.jfree.util.UnitType;
/*      */ 
/*      */ public class StandardXYItemRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*      */   private static final long serialVersionUID = -3271351259436865995L;
/*      */   
/*      */   public static final int SHAPES = 1;
/*      */   
/*      */   public static final int LINES = 2;
/*      */   
/*      */   public static final int SHAPES_AND_LINES = 3;
/*      */   
/*      */   public static final int IMAGES = 4;
/*      */   
/*      */   public static final int DISCONTINUOUS = 8;
/*      */   
/*      */   public static final int DISCONTINUOUS_LINES = 10;
/*      */   
/*      */   private boolean baseShapesVisible;
/*      */   
/*      */   private boolean plotLines;
/*      */   
/*      */   private boolean plotImages;
/*      */   
/*      */   private boolean plotDiscontinuous;
/*      */   
/*  180 */   private UnitType gapThresholdType = UnitType.RELATIVE;
/*      */   
/*  183 */   private double gapThreshold = 1.0D;
/*      */   
/*      */   private Boolean shapesFilled;
/*      */   
/*      */   private BooleanList seriesShapesFilled;
/*      */   
/*      */   private boolean baseShapesFilled;
/*      */   
/*      */   private boolean drawSeriesLineAsPath;
/*      */   
/*      */   private transient Shape legendLine;
/*      */   
/*      */   public StandardXYItemRenderer() {
/*  213 */     this(2, (XYToolTipGenerator)null);
/*      */   }
/*      */   
/*      */   public StandardXYItemRenderer(int type) {
/*  225 */     this(type, (XYToolTipGenerator)null);
/*      */   }
/*      */   
/*      */   public StandardXYItemRenderer(int type, XYToolTipGenerator toolTipGenerator) {
/*  240 */     this(type, toolTipGenerator, (XYURLGenerator)null);
/*      */   }
/*      */   
/*      */   public StandardXYItemRenderer(int type, XYToolTipGenerator toolTipGenerator, XYURLGenerator urlGenerator) {
/*  259 */     setToolTipGenerator(toolTipGenerator);
/*  260 */     setURLGenerator(urlGenerator);
/*  261 */     if ((type & 0x1) != 0)
/*  262 */       this.baseShapesVisible = true; 
/*  264 */     if ((type & 0x2) != 0)
/*  265 */       this.plotLines = true; 
/*  267 */     if ((type & 0x4) != 0)
/*  268 */       this.plotImages = true; 
/*  270 */     if ((type & 0x8) != 0)
/*  271 */       this.plotDiscontinuous = true; 
/*  274 */     this.shapesFilled = null;
/*  275 */     this.seriesShapesFilled = new BooleanList();
/*  276 */     this.baseShapesFilled = true;
/*  277 */     this.legendLine = new Line2D.Double(-7.0D, 0.0D, 7.0D, 0.0D);
/*  278 */     this.drawSeriesLineAsPath = false;
/*      */   }
/*      */   
/*      */   public boolean getBaseShapesVisible() {
/*  287 */     return this.baseShapesVisible;
/*      */   }
/*      */   
/*      */   public void setBaseShapesVisible(boolean flag) {
/*  297 */     if (this.baseShapesVisible != flag) {
/*  298 */       this.baseShapesVisible = flag;
/*  299 */       notifyListeners(new RendererChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean getItemShapeFilled(int series, int item) {
/*  319 */     return getSeriesShapesFilled(series);
/*      */   }
/*      */   
/*      */   public boolean getSeriesShapesFilled(int series) {
/*  333 */     if (this.shapesFilled != null)
/*  334 */       return this.shapesFilled.booleanValue(); 
/*  338 */     Boolean flag = this.seriesShapesFilled.getBoolean(series);
/*  339 */     if (flag != null)
/*  340 */       return flag.booleanValue(); 
/*  343 */     return this.baseShapesFilled;
/*      */   }
/*      */   
/*      */   public void setShapesFilled(boolean filled) {
/*  355 */     setShapesFilled(BooleanUtilities.valueOf(filled));
/*      */   }
/*      */   
/*      */   public void setShapesFilled(Boolean filled) {
/*  364 */     this.shapesFilled = filled;
/*      */   }
/*      */   
/*      */   public void setSeriesShapesFilled(int series, Boolean flag) {
/*  374 */     this.seriesShapesFilled.setBoolean(series, flag);
/*      */   }
/*      */   
/*      */   public boolean getBaseShapesFilled() {
/*  383 */     return this.baseShapesFilled;
/*      */   }
/*      */   
/*      */   public void setBaseShapesFilled(boolean flag) {
/*  392 */     this.baseShapesFilled = flag;
/*      */   }
/*      */   
/*      */   public boolean getPlotLines() {
/*  401 */     return this.plotLines;
/*      */   }
/*      */   
/*      */   public void setPlotLines(boolean flag) {
/*  411 */     if (this.plotLines != flag) {
/*  412 */       this.plotLines = flag;
/*  413 */       notifyListeners(new RendererChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public UnitType getGapThresholdType() {
/*  423 */     return this.gapThresholdType;
/*      */   }
/*      */   
/*      */   public void setGapThresholdType(UnitType thresholdType) {
/*  432 */     if (thresholdType == null)
/*  433 */       throw new IllegalArgumentException("Null 'thresholdType' argument."); 
/*  437 */     this.gapThresholdType = thresholdType;
/*  438 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getGapThreshold() {
/*  447 */     return this.gapThreshold;
/*      */   }
/*      */   
/*      */   public void setGapThreshold(double t) {
/*  456 */     this.gapThreshold = t;
/*  457 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getPlotImages() {
/*  466 */     return this.plotImages;
/*      */   }
/*      */   
/*      */   public void setPlotImages(boolean flag) {
/*  476 */     if (this.plotImages != flag) {
/*  477 */       this.plotImages = flag;
/*  478 */       notifyListeners(new RendererChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean getPlotDiscontinuous() {
/*  488 */     return this.plotDiscontinuous;
/*      */   }
/*      */   
/*      */   public boolean getDrawSeriesLineAsPath() {
/*  498 */     return this.drawSeriesLineAsPath;
/*      */   }
/*      */   
/*      */   public void setDrawSeriesLineAsPath(boolean flag) {
/*  508 */     this.drawSeriesLineAsPath = flag;
/*      */   }
/*      */   
/*      */   public Shape getLegendLine() {
/*  517 */     return this.legendLine;
/*      */   }
/*      */   
/*      */   public void setLegendLine(Shape line) {
/*  527 */     if (line == null)
/*  528 */       throw new IllegalArgumentException("Null 'line' argument."); 
/*  530 */     this.legendLine = line;
/*  531 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public LegendItem getLegendItem(int datasetIndex, int series) {
/*  543 */     XYPlot plot = getPlot();
/*  544 */     if (plot == null)
/*  545 */       return null; 
/*  547 */     LegendItem result = null;
/*  548 */     XYDataset dataset = plot.getDataset(datasetIndex);
/*  549 */     if (dataset != null && 
/*  550 */       getItemVisible(series, 0)) {
/*  551 */       String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*  554 */       String description = label;
/*  555 */       String toolTipText = null;
/*  556 */       if (getLegendItemToolTipGenerator() != null)
/*  557 */         toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/*  561 */       String urlText = null;
/*  562 */       if (getLegendItemURLGenerator() != null)
/*  563 */         urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/*  567 */       Shape shape = getSeriesShape(series);
/*  568 */       boolean shapeFilled = getSeriesShapesFilled(series);
/*  569 */       Paint paint = getSeriesPaint(series);
/*  570 */       Paint linePaint = paint;
/*  571 */       Stroke lineStroke = getSeriesStroke(series);
/*  572 */       result = new LegendItem(label, description, toolTipText, urlText, this.baseShapesVisible, shape, shapeFilled, paint, !shapeFilled, paint, lineStroke, this.plotLines, this.legendLine, lineStroke, linePaint);
/*      */     } 
/*  578 */     return result;
/*      */   }
/*      */   
/*      */   public static class State extends XYItemRendererState {
/*      */     public GeneralPath seriesPath;
/*      */     
/*      */     private boolean lastPointGood;
/*      */     
/*      */     public State(PlotRenderingInfo info) {
/*  603 */       super(info);
/*      */     }
/*      */     
/*      */     public boolean isLastPointGood() {
/*  613 */       return this.lastPointGood;
/*      */     }
/*      */     
/*      */     public void setLastPointGood(boolean good) {
/*  623 */       this.lastPointGood = good;
/*      */     }
/*      */   }
/*      */   
/*      */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info) {
/*  649 */     State state = new State(info);
/*  650 */     state.seriesPath = new GeneralPath();
/*  651 */     return state;
/*      */   }
/*      */   
/*      */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/*  686 */     if (!getItemVisible(series, item))
/*      */       return; 
/*  690 */     Shape entityArea = null;
/*  691 */     EntityCollection entities = null;
/*  692 */     if (info != null)
/*  693 */       entities = info.getOwner().getEntityCollection(); 
/*  696 */     PlotOrientation orientation = plot.getOrientation();
/*  697 */     Paint paint = getItemPaint(series, item);
/*  698 */     Stroke seriesStroke = getItemStroke(series, item);
/*  699 */     g2.setPaint(paint);
/*  700 */     g2.setStroke(seriesStroke);
/*  703 */     double x1 = dataset.getXValue(series, item);
/*  704 */     double y1 = dataset.getYValue(series, item);
/*  705 */     if (Double.isNaN(x1) || Double.isNaN(y1))
/*      */       return; 
/*  709 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/*  710 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/*  711 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/*  712 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/*  714 */     if (getPlotLines()) {
/*  715 */       if (item == 0 && 
/*  716 */         this.drawSeriesLineAsPath) {
/*  717 */         State s = (State)state;
/*  718 */         s.seriesPath.reset();
/*  719 */         s.lastPointGood = false;
/*      */       } 
/*  723 */       if (this.drawSeriesLineAsPath) {
/*  724 */         State s = (State)state;
/*  726 */         if (!Double.isNaN(transX1) && !Double.isNaN(transY1)) {
/*  727 */           float x = (float)transX1;
/*  728 */           float y = (float)transY1;
/*  729 */           if (orientation == PlotOrientation.HORIZONTAL) {
/*  730 */             x = (float)transY1;
/*  731 */             y = (float)transX1;
/*      */           } 
/*  733 */           if (s.isLastPointGood()) {
/*  735 */             s.seriesPath.lineTo(x, y);
/*      */           } else {
/*  738 */             s.seriesPath.moveTo(x, y);
/*      */           } 
/*  740 */           s.setLastPointGood(true);
/*      */         } else {
/*  743 */           s.setLastPointGood(false);
/*      */         } 
/*  745 */         if (item == dataset.getItemCount(series) - 1) {
/*  747 */           g2.setStroke(getSeriesStroke(series));
/*  748 */           g2.setPaint(getSeriesPaint(series));
/*  749 */           g2.draw(s.seriesPath);
/*      */         } 
/*  753 */       } else if (item != 0) {
/*  755 */         double x0 = dataset.getXValue(series, item - 1);
/*  756 */         double y0 = dataset.getYValue(series, item - 1);
/*  757 */         if (!Double.isNaN(x0) && !Double.isNaN(y0)) {
/*  758 */           boolean drawLine = true;
/*  759 */           if (getPlotDiscontinuous()) {
/*  762 */             int numX = dataset.getItemCount(series);
/*  763 */             double minX = dataset.getXValue(series, 0);
/*  764 */             double maxX = dataset.getXValue(series, numX - 1);
/*  765 */             if (this.gapThresholdType == UnitType.ABSOLUTE) {
/*  766 */               drawLine = (Math.abs(x1 - x0) <= this.gapThreshold);
/*      */             } else {
/*  769 */               drawLine = (Math.abs(x1 - x0) <= (maxX - minX) / numX * getGapThreshold());
/*      */             } 
/*      */           } 
/*  773 */           if (drawLine) {
/*  774 */             double transX0 = domainAxis.valueToJava2D(x0, dataArea, xAxisLocation);
/*  777 */             double transY0 = rangeAxis.valueToJava2D(y0, dataArea, yAxisLocation);
/*  782 */             if (Double.isNaN(transX0) || Double.isNaN(transY0) || Double.isNaN(transX1) || Double.isNaN(transY1))
/*      */               return; 
/*  787 */             if (orientation == PlotOrientation.HORIZONTAL) {
/*  788 */               state.workingLine.setLine(transY0, transX0, transY1, transX1);
/*  792 */             } else if (orientation == PlotOrientation.VERTICAL) {
/*  793 */               state.workingLine.setLine(transX0, transY0, transX1, transY1);
/*      */             } 
/*  798 */             if (state.workingLine.intersects(dataArea))
/*  799 */               g2.draw(state.workingLine); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  806 */     if (getBaseShapesVisible()) {
/*  808 */       Shape shape = getItemShape(series, item);
/*  809 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  810 */         shape = ShapeUtilities.createTranslatedShape(shape, transY1, transX1);
/*  814 */       } else if (orientation == PlotOrientation.VERTICAL) {
/*  815 */         shape = ShapeUtilities.createTranslatedShape(shape, transX1, transY1);
/*      */       } 
/*  819 */       if (shape.intersects(dataArea))
/*  820 */         if (getItemShapeFilled(series, item)) {
/*  821 */           g2.fill(shape);
/*      */         } else {
/*  824 */           g2.draw(shape);
/*      */         }  
/*  827 */       entityArea = shape;
/*      */     } 
/*  831 */     if (getPlotImages()) {
/*  832 */       Image image = getImage((Plot)plot, series, item, transX1, transY1);
/*  833 */       if (image != null) {
/*  834 */         Point hotspot = getImageHotspot((Plot)plot, series, item, transX1, transY1, image);
/*  837 */         g2.drawImage(image, (int)(transX1 - hotspot.getX()), (int)(transY1 - hotspot.getY()), (ImageObserver)null);
/*  841 */         entityArea = new Rectangle2D.Double(transX1 - hotspot.getX(), transY1 - hotspot.getY(), image.getWidth(null), image.getHeight(null));
/*      */       } 
/*      */     } 
/*  850 */     if (isItemLabelVisible(series, item)) {
/*  851 */       double xx = transX1;
/*  852 */       double yy = transY1;
/*  853 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  854 */         xx = transY1;
/*  855 */         yy = transX1;
/*      */       } 
/*  857 */       drawItemLabel(g2, orientation, dataset, series, item, xx, yy, (y1 < 0.0D));
/*      */     } 
/*  862 */     updateCrosshairValues(crosshairState, x1, y1, transX1, transY1, orientation);
/*  867 */     if (entities != null)
/*  868 */       addEntity(entities, entityArea, dataset, series, item, transX1, transY1); 
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/*  884 */     if (obj == this)
/*  885 */       return true; 
/*  887 */     if (!(obj instanceof StandardXYItemRenderer))
/*  888 */       return false; 
/*  890 */     if (!super.equals(obj))
/*  891 */       return false; 
/*  893 */     StandardXYItemRenderer that = (StandardXYItemRenderer)obj;
/*  894 */     if (this.baseShapesVisible != that.baseShapesVisible)
/*  895 */       return false; 
/*  897 */     if (this.plotLines != that.plotLines)
/*  898 */       return false; 
/*  900 */     if (this.plotImages != that.plotImages)
/*  901 */       return false; 
/*  903 */     if (this.plotDiscontinuous != that.plotDiscontinuous)
/*  904 */       return false; 
/*  906 */     if (this.gapThresholdType != that.gapThresholdType)
/*  907 */       return false; 
/*  909 */     if (this.gapThreshold != that.gapThreshold)
/*  910 */       return false; 
/*  912 */     if (!ObjectUtilities.equal(this.shapesFilled, that.shapesFilled))
/*  913 */       return false; 
/*  915 */     if (!ShapeUtilities.equal(this.legendLine, that.legendLine))
/*  916 */       return false; 
/*  918 */     return true;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/*  930 */     return super.clone();
/*      */   }
/*      */   
/*      */   protected Image getImage(Plot plot, int series, int item, double x, double y) {
/*  956 */     return null;
/*      */   }
/*      */   
/*      */   protected Point getImageHotspot(Plot plot, int series, int item, double x, double y, Image image) {
/*  979 */     int height = image.getHeight(null);
/*  980 */     int width = image.getWidth(null);
/*  981 */     return new Point(width / 2, height / 2);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/*  995 */     stream.defaultReadObject();
/*  996 */     this.legendLine = SerialUtilities.readShape(stream);
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1007 */     stream.defaultWriteObject();
/* 1008 */     SerialUtilities.writeShape(this.legendLine, stream);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\StandardXYItemRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */