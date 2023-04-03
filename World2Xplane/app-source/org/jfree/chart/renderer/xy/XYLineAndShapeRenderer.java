/*      */ package org.jfree.chart.renderer.xy;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.plot.CrosshairState;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.util.BooleanList;
/*      */ import org.jfree.util.BooleanUtilities;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ 
/*      */ public class XYLineAndShapeRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*      */   private static final long serialVersionUID = -7435246895986425885L;
/*      */   
/*      */   private Boolean linesVisible;
/*      */   
/*      */   private BooleanList seriesLinesVisible;
/*      */   
/*      */   private boolean baseLinesVisible;
/*      */   
/*      */   private transient Shape legendLine;
/*      */   
/*      */   private Boolean shapesVisible;
/*      */   
/*      */   private BooleanList seriesShapesVisible;
/*      */   
/*      */   private boolean baseShapesVisible;
/*      */   
/*      */   private Boolean shapesFilled;
/*      */   
/*      */   private BooleanList seriesShapesFilled;
/*      */   
/*      */   private boolean baseShapesFilled;
/*      */   
/*      */   private boolean drawOutlines;
/*      */   
/*      */   private boolean useFillPaint;
/*      */   
/*      */   private boolean useOutlinePaint;
/*      */   
/*      */   private boolean drawSeriesLineAsPath;
/*      */   
/*      */   public XYLineAndShapeRenderer() {
/*  169 */     this(true, true);
/*      */   }
/*      */   
/*      */   public XYLineAndShapeRenderer(boolean lines, boolean shapes) {
/*  179 */     this.linesVisible = null;
/*  180 */     this.seriesLinesVisible = new BooleanList();
/*  181 */     this.baseLinesVisible = lines;
/*  182 */     this.legendLine = new Line2D.Double(-7.0D, 0.0D, 7.0D, 0.0D);
/*  184 */     this.shapesVisible = null;
/*  185 */     this.seriesShapesVisible = new BooleanList();
/*  186 */     this.baseShapesVisible = shapes;
/*  188 */     this.shapesFilled = null;
/*  189 */     this.useFillPaint = false;
/*  190 */     this.seriesShapesFilled = new BooleanList();
/*  191 */     this.baseShapesFilled = true;
/*  193 */     this.drawOutlines = true;
/*  194 */     this.useOutlinePaint = false;
/*  197 */     this.drawSeriesLineAsPath = false;
/*      */   }
/*      */   
/*      */   public boolean getDrawSeriesLineAsPath() {
/*  209 */     return this.drawSeriesLineAsPath;
/*      */   }
/*      */   
/*      */   public void setDrawSeriesLineAsPath(boolean flag) {
/*  221 */     if (this.drawSeriesLineAsPath != flag) {
/*  222 */       this.drawSeriesLineAsPath = flag;
/*  223 */       notifyListeners(new RendererChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getPassCount() {
/*  235 */     return 2;
/*      */   }
/*      */   
/*      */   public boolean getItemLineVisible(int series, int item) {
/*  250 */     Boolean flag = this.linesVisible;
/*  251 */     if (flag == null)
/*  252 */       flag = getSeriesLinesVisible(series); 
/*  254 */     if (flag != null)
/*  255 */       return flag.booleanValue(); 
/*  258 */     return this.baseLinesVisible;
/*      */   }
/*      */   
/*      */   public Boolean getLinesVisible() {
/*  270 */     return this.linesVisible;
/*      */   }
/*      */   
/*      */   public void setLinesVisible(Boolean visible) {
/*  282 */     this.linesVisible = visible;
/*  283 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void setLinesVisible(boolean visible) {
/*  294 */     setLinesVisible(BooleanUtilities.valueOf(visible));
/*      */   }
/*      */   
/*      */   public Boolean getSeriesLinesVisible(int series) {
/*  306 */     return this.seriesLinesVisible.getBoolean(series);
/*      */   }
/*      */   
/*      */   public void setSeriesLinesVisible(int series, Boolean flag) {
/*  316 */     this.seriesLinesVisible.setBoolean(series, flag);
/*  317 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void setSeriesLinesVisible(int series, boolean visible) {
/*  327 */     setSeriesLinesVisible(series, BooleanUtilities.valueOf(visible));
/*      */   }
/*      */   
/*      */   public boolean getBaseLinesVisible() {
/*  336 */     return this.baseLinesVisible;
/*      */   }
/*      */   
/*      */   public void setBaseLinesVisible(boolean flag) {
/*  345 */     this.baseLinesVisible = flag;
/*  346 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Shape getLegendLine() {
/*  355 */     return this.legendLine;
/*      */   }
/*      */   
/*      */   public void setLegendLine(Shape line) {
/*  365 */     if (line == null)
/*  366 */       throw new IllegalArgumentException("Null 'line' argument."); 
/*  368 */     this.legendLine = line;
/*  369 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getItemShapeVisible(int series, int item) {
/*  388 */     Boolean flag = this.shapesVisible;
/*  389 */     if (flag == null)
/*  390 */       flag = getSeriesShapesVisible(series); 
/*  392 */     if (flag != null)
/*  393 */       return flag.booleanValue(); 
/*  396 */     return this.baseShapesVisible;
/*      */   }
/*      */   
/*      */   public Boolean getShapesVisible() {
/*  407 */     return this.shapesVisible;
/*      */   }
/*      */   
/*      */   public void setShapesVisible(Boolean visible) {
/*  417 */     this.shapesVisible = visible;
/*  418 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void setShapesVisible(boolean visible) {
/*  428 */     setShapesVisible(BooleanUtilities.valueOf(visible));
/*      */   }
/*      */   
/*      */   public Boolean getSeriesShapesVisible(int series) {
/*  440 */     return this.seriesShapesVisible.getBoolean(series);
/*      */   }
/*      */   
/*      */   public void setSeriesShapesVisible(int series, boolean visible) {
/*  451 */     setSeriesShapesVisible(series, BooleanUtilities.valueOf(visible));
/*      */   }
/*      */   
/*      */   public void setSeriesShapesVisible(int series, Boolean flag) {
/*  462 */     this.seriesShapesVisible.setBoolean(series, flag);
/*  463 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getBaseShapesVisible() {
/*  472 */     return this.baseShapesVisible;
/*      */   }
/*      */   
/*      */   public void setBaseShapesVisible(boolean flag) {
/*  481 */     this.baseShapesVisible = flag;
/*  482 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getItemShapeFilled(int series, int item) {
/*  501 */     Boolean flag = this.shapesFilled;
/*  502 */     if (flag == null)
/*  503 */       flag = getSeriesShapesFilled(series); 
/*  505 */     if (flag != null)
/*  506 */       return flag.booleanValue(); 
/*  509 */     return this.baseShapesFilled;
/*      */   }
/*      */   
/*      */   public void setShapesFilled(boolean filled) {
/*  520 */     setShapesFilled(BooleanUtilities.valueOf(filled));
/*      */   }
/*      */   
/*      */   public void setShapesFilled(Boolean filled) {
/*  530 */     this.shapesFilled = filled;
/*  531 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Boolean getSeriesShapesFilled(int series) {
/*  543 */     return this.seriesShapesFilled.getBoolean(series);
/*      */   }
/*      */   
/*      */   public void setSeriesShapesFilled(int series, boolean flag) {
/*  553 */     setSeriesShapesFilled(series, BooleanUtilities.valueOf(flag));
/*      */   }
/*      */   
/*      */   public void setSeriesShapesFilled(int series, Boolean flag) {
/*  563 */     this.seriesShapesFilled.setBoolean(series, flag);
/*  564 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getBaseShapesFilled() {
/*  573 */     return this.baseShapesFilled;
/*      */   }
/*      */   
/*      */   public void setBaseShapesFilled(boolean flag) {
/*  582 */     this.baseShapesFilled = flag;
/*  583 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getDrawOutlines() {
/*  593 */     return this.drawOutlines;
/*      */   }
/*      */   
/*      */   public void setDrawOutlines(boolean flag) {
/*  607 */     this.drawOutlines = flag;
/*  608 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getUseFillPaint() {
/*  619 */     return this.useFillPaint;
/*      */   }
/*      */   
/*      */   public void setUseFillPaint(boolean flag) {
/*  630 */     this.useFillPaint = flag;
/*  631 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getUseOutlinePaint() {
/*  642 */     return this.useOutlinePaint;
/*      */   }
/*      */   
/*      */   public void setUseOutlinePaint(boolean flag) {
/*  653 */     this.useOutlinePaint = flag;
/*  654 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public static class State extends XYItemRendererState {
/*      */     public GeneralPath seriesPath;
/*      */     
/*      */     private boolean lastPointGood;
/*      */     
/*      */     public State(PlotRenderingInfo info) {
/*  679 */       super(info);
/*      */     }
/*      */     
/*      */     public boolean isLastPointGood() {
/*  689 */       return this.lastPointGood;
/*      */     }
/*      */     
/*      */     public void setLastPointGood(boolean good) {
/*  699 */       this.lastPointGood = good;
/*      */     }
/*      */   }
/*      */   
/*      */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info) {
/*  725 */     State state = new State(info);
/*  726 */     state.seriesPath = new GeneralPath();
/*  727 */     return state;
/*      */   }
/*      */   
/*      */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/*  763 */     if (!getItemVisible(series, item))
/*      */       return; 
/*  768 */     if (isLinePass(pass)) {
/*  769 */       if (item == 0 && 
/*  770 */         this.drawSeriesLineAsPath) {
/*  771 */         State s = (State)state;
/*  772 */         s.seriesPath.reset();
/*  773 */         s.lastPointGood = false;
/*      */       } 
/*  777 */       if (getItemLineVisible(series, item))
/*  778 */         if (this.drawSeriesLineAsPath) {
/*  779 */           drawPrimaryLineAsPath(state, g2, plot, dataset, pass, series, item, domainAxis, rangeAxis, dataArea);
/*      */         } else {
/*  785 */           drawPrimaryLine(state, g2, plot, dataset, pass, series, item, domainAxis, rangeAxis, dataArea);
/*      */         }  
/*  793 */     } else if (isItemPass(pass)) {
/*  796 */       EntityCollection entities = null;
/*  797 */       if (info != null)
/*  798 */         entities = info.getOwner().getEntityCollection(); 
/*  801 */       drawSecondaryPass(g2, plot, dataset, pass, series, item, domainAxis, dataArea, rangeAxis, crosshairState, entities);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean isLinePass(int pass) {
/*  817 */     return (pass == 0);
/*      */   }
/*      */   
/*      */   protected boolean isItemPass(int pass) {
/*  829 */     return (pass == 1);
/*      */   }
/*      */   
/*      */   protected void drawPrimaryLine(XYItemRendererState state, Graphics2D g2, XYPlot plot, XYDataset dataset, int pass, int series, int item, ValueAxis domainAxis, ValueAxis rangeAxis, Rectangle2D dataArea) {
/*  858 */     if (item == 0)
/*      */       return; 
/*  863 */     double x1 = dataset.getXValue(series, item);
/*  864 */     double y1 = dataset.getYValue(series, item);
/*  865 */     if (Double.isNaN(y1) || Double.isNaN(x1))
/*      */       return; 
/*  869 */     double x0 = dataset.getXValue(series, item - 1);
/*  870 */     double y0 = dataset.getYValue(series, item - 1);
/*  871 */     if (Double.isNaN(y0) || Double.isNaN(x0))
/*      */       return; 
/*  875 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/*  876 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/*  878 */     double transX0 = domainAxis.valueToJava2D(x0, dataArea, xAxisLocation);
/*  879 */     double transY0 = rangeAxis.valueToJava2D(y0, dataArea, yAxisLocation);
/*  881 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/*  882 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/*  885 */     if (Double.isNaN(transX0) || Double.isNaN(transY0) || Double.isNaN(transX1) || Double.isNaN(transY1))
/*      */       return; 
/*  890 */     PlotOrientation orientation = plot.getOrientation();
/*  891 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  892 */       state.workingLine.setLine(transY0, transX0, transY1, transX1);
/*  894 */     } else if (orientation == PlotOrientation.VERTICAL) {
/*  895 */       state.workingLine.setLine(transX0, transY0, transX1, transY1);
/*      */     } 
/*  898 */     if (state.workingLine.intersects(dataArea))
/*  899 */       drawFirstPassShape(g2, pass, series, item, state.workingLine); 
/*      */   }
/*      */   
/*      */   protected void drawFirstPassShape(Graphics2D g2, int pass, int series, int item, Shape shape) {
/*  917 */     g2.setStroke(getItemStroke(series, item));
/*  918 */     g2.setPaint(getItemPaint(series, item));
/*  919 */     g2.draw(shape);
/*      */   }
/*      */   
/*      */   protected void drawPrimaryLineAsPath(XYItemRendererState state, Graphics2D g2, XYPlot plot, XYDataset dataset, int pass, int series, int item, ValueAxis domainAxis, ValueAxis rangeAxis, Rectangle2D dataArea) {
/*  952 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/*  953 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/*  956 */     double x1 = dataset.getXValue(series, item);
/*  957 */     double y1 = dataset.getYValue(series, item);
/*  958 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/*  959 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/*  961 */     State s = (State)state;
/*  963 */     if (!Double.isNaN(transX1) && !Double.isNaN(transY1)) {
/*  964 */       float x = (float)transX1;
/*  965 */       float y = (float)transY1;
/*  966 */       PlotOrientation orientation = plot.getOrientation();
/*  967 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  968 */         x = (float)transY1;
/*  969 */         y = (float)transX1;
/*      */       } 
/*  971 */       if (s.isLastPointGood()) {
/*  972 */         s.seriesPath.lineTo(x, y);
/*      */       } else {
/*  975 */         s.seriesPath.moveTo(x, y);
/*      */       } 
/*  977 */       s.setLastPointGood(true);
/*      */     } else {
/*  980 */       s.setLastPointGood(false);
/*      */     } 
/*  983 */     if (item == dataset.getItemCount(series) - 1)
/*  985 */       drawFirstPassShape(g2, pass, series, item, s.seriesPath); 
/*      */   }
/*      */   
/*      */   protected void drawSecondaryPass(Graphics2D g2, XYPlot plot, XYDataset dataset, int pass, int series, int item, ValueAxis domainAxis, Rectangle2D dataArea, ValueAxis rangeAxis, CrosshairState crosshairState, EntityCollection entities) {
/* 1016 */     Shape entityArea = null;
/* 1019 */     double x1 = dataset.getXValue(series, item);
/* 1020 */     double y1 = dataset.getYValue(series, item);
/* 1021 */     if (Double.isNaN(y1) || Double.isNaN(x1))
/*      */       return; 
/* 1025 */     PlotOrientation orientation = plot.getOrientation();
/* 1026 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 1027 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 1028 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/* 1029 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/* 1031 */     if (getItemShapeVisible(series, item)) {
/* 1032 */       Shape shape = getItemShape(series, item);
/* 1033 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1034 */         shape = ShapeUtilities.createTranslatedShape(shape, transY1, transX1);
/* 1038 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 1039 */         shape = ShapeUtilities.createTranslatedShape(shape, transX1, transY1);
/*      */       } 
/* 1043 */       entityArea = shape;
/* 1044 */       if (shape.intersects(dataArea)) {
/* 1045 */         if (getItemShapeFilled(series, item)) {
/* 1046 */           if (this.useFillPaint) {
/* 1047 */             g2.setPaint(getItemFillPaint(series, item));
/*      */           } else {
/* 1050 */             g2.setPaint(getItemPaint(series, item));
/*      */           } 
/* 1052 */           g2.fill(shape);
/*      */         } 
/* 1054 */         if (this.drawOutlines) {
/* 1055 */           if (getUseOutlinePaint()) {
/* 1056 */             g2.setPaint(getItemOutlinePaint(series, item));
/*      */           } else {
/* 1059 */             g2.setPaint(getItemPaint(series, item));
/*      */           } 
/* 1061 */           g2.setStroke(getItemOutlineStroke(series, item));
/* 1062 */           g2.draw(shape);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1068 */     if (isItemLabelVisible(series, item)) {
/* 1069 */       double xx = transX1;
/* 1070 */       double yy = transY1;
/* 1071 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1072 */         xx = transY1;
/* 1073 */         yy = transX1;
/*      */       } 
/* 1075 */       drawItemLabel(g2, orientation, dataset, series, item, xx, yy, (y1 < 0.0D));
/*      */     } 
/* 1079 */     updateCrosshairValues(crosshairState, x1, y1, transX1, transY1, plot.getOrientation());
/* 1084 */     if (entities != null)
/* 1085 */       addEntity(entities, entityArea, dataset, series, item, transX1, transY1); 
/*      */   }
/*      */   
/*      */   public LegendItem getLegendItem(int datasetIndex, int series) {
/* 1102 */     XYPlot plot = getPlot();
/* 1103 */     if (plot == null)
/* 1104 */       return null; 
/* 1107 */     LegendItem result = null;
/* 1108 */     XYDataset dataset = plot.getDataset(datasetIndex);
/* 1109 */     if (dataset != null && 
/* 1110 */       getItemVisible(series, 0)) {
/* 1111 */       String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/* 1114 */       String description = label;
/* 1115 */       String toolTipText = null;
/* 1116 */       if (getLegendItemToolTipGenerator() != null)
/* 1117 */         toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/* 1121 */       String urlText = null;
/* 1122 */       if (getLegendItemURLGenerator() != null)
/* 1123 */         urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/* 1127 */       boolean shapeIsVisible = getItemShapeVisible(series, 0);
/* 1128 */       Shape shape = getSeriesShape(series);
/* 1129 */       boolean shapeIsFilled = getItemShapeFilled(series, 0);
/* 1130 */       Paint fillPaint = this.useFillPaint ? getSeriesFillPaint(series) : getSeriesPaint(series);
/* 1132 */       boolean shapeOutlineVisible = this.drawOutlines;
/* 1133 */       Paint outlinePaint = this.useOutlinePaint ? getSeriesOutlinePaint(series) : getSeriesPaint(series);
/* 1136 */       Stroke outlineStroke = getSeriesOutlineStroke(series);
/* 1137 */       boolean lineVisible = getItemLineVisible(series, 0);
/* 1138 */       Stroke lineStroke = getSeriesStroke(series);
/* 1139 */       Paint linePaint = getSeriesPaint(series);
/* 1140 */       result = new LegendItem(label, description, toolTipText, urlText, shapeIsVisible, shape, shapeIsFilled, fillPaint, shapeOutlineVisible, outlinePaint, outlineStroke, lineVisible, this.legendLine, lineStroke, linePaint);
/*      */     } 
/* 1148 */     return result;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1160 */     return super.clone();
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1172 */     if (obj == this)
/* 1173 */       return true; 
/* 1175 */     if (!(obj instanceof XYLineAndShapeRenderer))
/* 1176 */       return false; 
/* 1178 */     if (!super.equals(obj))
/* 1179 */       return false; 
/* 1181 */     XYLineAndShapeRenderer that = (XYLineAndShapeRenderer)obj;
/* 1182 */     if (!ObjectUtilities.equal(this.linesVisible, that.linesVisible))
/* 1183 */       return false; 
/* 1185 */     if (!ObjectUtilities.equal(this.seriesLinesVisible, that.seriesLinesVisible))
/* 1188 */       return false; 
/* 1190 */     if (this.baseLinesVisible != that.baseLinesVisible)
/* 1191 */       return false; 
/* 1193 */     if (!ShapeUtilities.equal(this.legendLine, that.legendLine))
/* 1194 */       return false; 
/* 1196 */     if (!ObjectUtilities.equal(this.shapesVisible, that.shapesVisible))
/* 1197 */       return false; 
/* 1199 */     if (!ObjectUtilities.equal(this.seriesShapesVisible, that.seriesShapesVisible))
/* 1202 */       return false; 
/* 1204 */     if (this.baseShapesVisible != that.baseShapesVisible)
/* 1205 */       return false; 
/* 1207 */     if (!ObjectUtilities.equal(this.shapesFilled, that.shapesFilled))
/* 1208 */       return false; 
/* 1210 */     if (!ObjectUtilities.equal(this.seriesShapesFilled, that.seriesShapesFilled))
/* 1213 */       return false; 
/* 1215 */     if (this.baseShapesFilled != that.baseShapesFilled)
/* 1216 */       return false; 
/* 1218 */     if (this.drawOutlines != that.drawOutlines)
/* 1219 */       return false; 
/* 1221 */     if (this.useOutlinePaint != that.useOutlinePaint)
/* 1222 */       return false; 
/* 1225 */     return true;
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1239 */     stream.defaultReadObject();
/* 1240 */     this.legendLine = SerialUtilities.readShape(stream);
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1251 */     stream.defaultWriteObject();
/* 1252 */     SerialUtilities.writeShape(this.legendLine, stream);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYLineAndShapeRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */