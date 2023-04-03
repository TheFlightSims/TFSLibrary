/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.xy.TableXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StackedXYAreaRenderer2 extends XYAreaRenderer2 implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 7752676509764539182L;
/*     */   
/*     */   public StackedXYAreaRenderer2() {
/*  87 */     this((XYToolTipGenerator)null, (XYURLGenerator)null);
/*     */   }
/*     */   
/*     */   public StackedXYAreaRenderer2(XYToolTipGenerator labelGenerator, XYURLGenerator urlGenerator) {
/*  99 */     super(labelGenerator, urlGenerator);
/*     */   }
/*     */   
/*     */   public Range findRangeBounds(XYDataset dataset) {
/* 112 */     double min = Double.POSITIVE_INFINITY;
/* 113 */     double max = Double.NEGATIVE_INFINITY;
/* 114 */     TableXYDataset d = (TableXYDataset)dataset;
/* 115 */     int itemCount = d.getItemCount();
/* 116 */     for (int i = 0; i < itemCount; i++) {
/* 117 */       double[] stackValues = getStackValues((TableXYDataset)dataset, d.getSeriesCount(), i);
/* 120 */       min = Math.min(min, stackValues[0]);
/* 121 */       max = Math.max(max, stackValues[1]);
/*     */     } 
/* 123 */     return new Range(min, max);
/*     */   }
/*     */   
/*     */   public int getPassCount() {
/* 132 */     return 1;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/*     */     float transY1;
/* 166 */     Shape entityArea = null;
/* 167 */     EntityCollection entities = null;
/* 168 */     if (info != null)
/* 169 */       entities = info.getOwner().getEntityCollection(); 
/* 172 */     TableXYDataset tdataset = (TableXYDataset)dataset;
/* 175 */     double x1 = dataset.getXValue(series, item);
/* 176 */     double y1 = dataset.getYValue(series, item);
/* 177 */     if (Double.isNaN(y1))
/* 178 */       y1 = 0.0D; 
/* 180 */     double[] stack1 = getStackValues(tdataset, series, item);
/* 184 */     double x0 = dataset.getXValue(series, Math.max(item - 1, 0));
/* 185 */     double y0 = dataset.getYValue(series, Math.max(item - 1, 0));
/* 186 */     if (Double.isNaN(y0))
/* 187 */       y0 = 0.0D; 
/* 189 */     double[] stack0 = getStackValues(tdataset, series, Math.max(item - 1, 0));
/* 193 */     int itemCount = dataset.getItemCount(series);
/* 194 */     double x2 = dataset.getXValue(series, Math.min(item + 1, itemCount - 1));
/* 197 */     double y2 = dataset.getYValue(series, Math.min(item + 1, itemCount - 1));
/* 200 */     if (Double.isNaN(y2))
/* 201 */       y2 = 0.0D; 
/* 203 */     double[] stack2 = getStackValues(tdataset, series, Math.min(item + 1, itemCount - 1));
/* 207 */     double xleft = (x0 + x1) / 2.0D;
/* 208 */     double xright = (x1 + x2) / 2.0D;
/* 209 */     double[] stackLeft = averageStackValues(stack0, stack1);
/* 210 */     double[] stackRight = averageStackValues(stack1, stack2);
/* 211 */     double[] adjStackLeft = adjustedStackValues(stack0, stack1);
/* 212 */     double[] adjStackRight = adjustedStackValues(stack1, stack2);
/* 214 */     RectangleEdge edge0 = plot.getDomainAxisEdge();
/* 215 */     float transX1 = (float)domainAxis.valueToJava2D(x1, dataArea, edge0);
/* 216 */     float transXLeft = (float)domainAxis.valueToJava2D(xleft, dataArea, edge0);
/* 218 */     float transXRight = (float)domainAxis.valueToJava2D(xright, dataArea, edge0);
/* 222 */     RectangleEdge edge1 = plot.getRangeAxisEdge();
/* 224 */     GeneralPath left = new GeneralPath();
/* 225 */     GeneralPath right = new GeneralPath();
/* 226 */     if (y1 >= 0.0D) {
/* 227 */       transY1 = (float)rangeAxis.valueToJava2D(y1 + stack1[1], dataArea, edge1);
/* 229 */       float transStack1 = (float)rangeAxis.valueToJava2D(stack1[1], dataArea, edge1);
/* 232 */       float transStackLeft = (float)rangeAxis.valueToJava2D(adjStackLeft[1], dataArea, edge1);
/* 237 */       if (y0 >= 0.0D) {
/* 238 */         double yleft = (y0 + y1) / 2.0D + stackLeft[1];
/* 239 */         float transYLeft = (float)rangeAxis.valueToJava2D(yleft, dataArea, edge1);
/* 241 */         left.moveTo(transX1, transY1);
/* 242 */         left.lineTo(transX1, transStack1);
/* 243 */         left.lineTo(transXLeft, transStackLeft);
/* 244 */         left.lineTo(transXLeft, transYLeft);
/* 245 */         left.closePath();
/*     */       } else {
/* 248 */         left.moveTo(transX1, transStack1);
/* 249 */         left.lineTo(transX1, transY1);
/* 250 */         left.lineTo(transXLeft, transStackLeft);
/* 251 */         left.closePath();
/*     */       } 
/* 254 */       float transStackRight = (float)rangeAxis.valueToJava2D(adjStackRight[1], dataArea, edge1);
/* 258 */       if (y2 >= 0.0D) {
/* 259 */         double yright = (y1 + y2) / 2.0D + stackRight[1];
/* 260 */         float transYRight = (float)rangeAxis.valueToJava2D(yright, dataArea, edge1);
/* 262 */         right.moveTo(transX1, transStack1);
/* 263 */         right.lineTo(transX1, transY1);
/* 264 */         right.lineTo(transXRight, transYRight);
/* 265 */         right.lineTo(transXRight, transStackRight);
/* 266 */         right.closePath();
/*     */       } else {
/* 269 */         right.moveTo(transX1, transStack1);
/* 270 */         right.lineTo(transX1, transY1);
/* 271 */         right.lineTo(transXRight, transStackRight);
/* 272 */         right.closePath();
/*     */       } 
/*     */     } else {
/* 276 */       transY1 = (float)rangeAxis.valueToJava2D(y1 + stack1[0], dataArea, edge1);
/* 278 */       float transStack1 = (float)rangeAxis.valueToJava2D(stack1[0], dataArea, edge1);
/* 281 */       float transStackLeft = (float)rangeAxis.valueToJava2D(adjStackLeft[0], dataArea, edge1);
/* 286 */       if (y0 >= 0.0D) {
/* 287 */         left.moveTo(transX1, transStack1);
/* 288 */         left.lineTo(transX1, transY1);
/* 289 */         left.lineTo(transXLeft, transStackLeft);
/* 290 */         left.clone();
/*     */       } else {
/* 293 */         double yleft = (y0 + y1) / 2.0D + stackLeft[0];
/* 294 */         float transYLeft = (float)rangeAxis.valueToJava2D(yleft, dataArea, edge1);
/* 297 */         left.moveTo(transX1, transY1);
/* 298 */         left.lineTo(transX1, transStack1);
/* 299 */         left.lineTo(transXLeft, transStackLeft);
/* 300 */         left.lineTo(transXLeft, transYLeft);
/* 301 */         left.closePath();
/*     */       } 
/* 303 */       float transStackRight = (float)rangeAxis.valueToJava2D(adjStackRight[0], dataArea, edge1);
/* 308 */       if (y2 >= 0.0D) {
/* 309 */         right.moveTo(transX1, transStack1);
/* 310 */         right.lineTo(transX1, transY1);
/* 311 */         right.lineTo(transXRight, transStackRight);
/* 312 */         right.closePath();
/*     */       } else {
/* 315 */         double yright = (y1 + y2) / 2.0D + stackRight[0];
/* 316 */         float transYRight = (float)rangeAxis.valueToJava2D(yright, dataArea, edge1);
/* 319 */         right.moveTo(transX1, transStack1);
/* 320 */         right.lineTo(transX1, transY1);
/* 321 */         right.lineTo(transXRight, transYRight);
/* 322 */         right.lineTo(transXRight, transStackRight);
/* 323 */         right.closePath();
/*     */       } 
/*     */     } 
/* 328 */     Paint itemPaint = getItemPaint(series, item);
/* 329 */     if (pass == 0) {
/* 330 */       g2.setPaint(itemPaint);
/* 331 */       g2.fill(left);
/* 332 */       g2.fill(right);
/*     */     } 
/* 336 */     if (entities != null) {
/* 337 */       GeneralPath gp = new GeneralPath(left);
/* 338 */       gp.append(right, false);
/* 339 */       entityArea = gp;
/* 340 */       addEntity(entities, entityArea, dataset, series, item, transX1, transY1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private double[] getStackValues(TableXYDataset dataset, int series, int index) {
/* 360 */     double[] result = new double[2];
/* 361 */     for (int i = 0; i < series; i++) {
/* 362 */       double v = dataset.getYValue(i, index);
/* 363 */       if (!Double.isNaN(v))
/* 364 */         if (v >= 0.0D) {
/* 365 */           result[1] = result[1] + v;
/*     */         } else {
/* 368 */           result[0] = result[0] + v;
/*     */         }  
/*     */     } 
/* 372 */     return result;
/*     */   }
/*     */   
/*     */   private double[] averageStackValues(double[] stack1, double[] stack2) {
/* 384 */     double[] result = new double[2];
/* 385 */     result[0] = (stack1[0] + stack2[0]) / 2.0D;
/* 386 */     result[1] = (stack1[1] + stack2[1]) / 2.0D;
/* 387 */     return result;
/*     */   }
/*     */   
/*     */   private double[] adjustedStackValues(double[] stack1, double[] stack2) {
/* 399 */     double[] result = new double[2];
/* 400 */     if (stack1[0] == 0.0D || stack2[0] == 0.0D) {
/* 401 */       result[0] = 0.0D;
/*     */     } else {
/* 404 */       result[0] = (stack1[0] + stack2[0]) / 2.0D;
/*     */     } 
/* 406 */     if (stack1[1] == 0.0D || stack2[1] == 0.0D) {
/* 407 */       result[1] = 0.0D;
/*     */     } else {
/* 410 */       result[1] = (stack1[1] + stack2[1]) / 2.0D;
/*     */     } 
/* 412 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 423 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\StackedXYAreaRenderer2.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */