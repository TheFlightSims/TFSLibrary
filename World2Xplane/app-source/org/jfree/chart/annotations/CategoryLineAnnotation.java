/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAnchor;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ public class CategoryLineAnnotation implements CategoryAnnotation, Cloneable, Serializable {
/*     */   private Comparable category1;
/*     */   
/*     */   private double value1;
/*     */   
/*     */   private Comparable category2;
/*     */   
/*     */   private double value2;
/*     */   
/*  88 */   private transient Paint paint = Color.black;
/*     */   
/*  91 */   private transient Stroke stroke = new BasicStroke(1.0F);
/*     */   
/*     */   public CategoryLineAnnotation(Comparable category1, double value1, Comparable category2, double value2, Paint paint, Stroke stroke) {
/* 105 */     if (category1 == null)
/* 106 */       throw new IllegalArgumentException("Null 'category1' argument."); 
/* 108 */     if (category2 == null)
/* 109 */       throw new IllegalArgumentException("Null 'category2' argument."); 
/* 111 */     if (paint == null)
/* 112 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 114 */     if (stroke == null)
/* 115 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/* 117 */     this.category1 = category1;
/* 118 */     this.value1 = value1;
/* 119 */     this.category2 = category2;
/* 120 */     this.value2 = value2;
/* 121 */     this.paint = paint;
/* 122 */     this.stroke = stroke;
/*     */   }
/*     */   
/*     */   public Comparable getCategory1() {
/* 131 */     return this.category1;
/*     */   }
/*     */   
/*     */   public void setCategory1(Comparable category) {
/* 140 */     if (category == null)
/* 141 */       throw new IllegalArgumentException("Null 'category' argument."); 
/* 143 */     this.category1 = category;
/*     */   }
/*     */   
/*     */   public double getValue1() {
/* 152 */     return this.value1;
/*     */   }
/*     */   
/*     */   public void setValue1(double value) {
/* 161 */     this.value1 = value;
/*     */   }
/*     */   
/*     */   public Comparable getCategory2() {
/* 170 */     return this.category2;
/*     */   }
/*     */   
/*     */   public void setCategory2(Comparable category) {
/* 179 */     if (category == null)
/* 180 */       throw new IllegalArgumentException("Null 'category' argument."); 
/* 182 */     this.category2 = category;
/*     */   }
/*     */   
/*     */   public double getValue2() {
/* 191 */     return this.value2;
/*     */   }
/*     */   
/*     */   public void setValue2(double value) {
/* 200 */     this.value2 = value;
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/* 209 */     return this.paint;
/*     */   }
/*     */   
/*     */   public void setPaint(Paint paint) {
/* 218 */     if (paint == null)
/* 219 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 221 */     this.paint = paint;
/*     */   }
/*     */   
/*     */   public Stroke getStroke() {
/* 230 */     return this.stroke;
/*     */   }
/*     */   
/*     */   public void setStroke(Stroke stroke) {
/* 239 */     if (stroke == null)
/* 240 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/* 242 */     this.stroke = stroke;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea, CategoryAxis domainAxis, ValueAxis rangeAxis) {
/* 257 */     CategoryDataset dataset = plot.getDataset();
/* 258 */     int catIndex1 = dataset.getColumnIndex(this.category1);
/* 259 */     int catIndex2 = dataset.getColumnIndex(this.category2);
/* 260 */     int catCount = dataset.getColumnCount();
/* 262 */     double lineX1 = 0.0D;
/* 263 */     double lineY1 = 0.0D;
/* 264 */     double lineX2 = 0.0D;
/* 265 */     double lineY2 = 0.0D;
/* 266 */     PlotOrientation orientation = plot.getOrientation();
/* 267 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/* 269 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/* 272 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 273 */       lineY1 = domainAxis.getCategoryJava2DCoordinate(CategoryAnchor.MIDDLE, catIndex1, catCount, dataArea, domainEdge);
/* 276 */       lineX1 = rangeAxis.valueToJava2D(this.value1, dataArea, rangeEdge);
/* 277 */       lineY2 = domainAxis.getCategoryJava2DCoordinate(CategoryAnchor.MIDDLE, catIndex2, catCount, dataArea, domainEdge);
/* 280 */       lineX2 = rangeAxis.valueToJava2D(this.value2, dataArea, rangeEdge);
/* 282 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 283 */       lineX1 = domainAxis.getCategoryJava2DCoordinate(CategoryAnchor.MIDDLE, catIndex1, catCount, dataArea, domainEdge);
/* 286 */       lineY1 = rangeAxis.valueToJava2D(this.value1, dataArea, rangeEdge);
/* 287 */       lineX2 = domainAxis.getCategoryJava2DCoordinate(CategoryAnchor.MIDDLE, catIndex2, catCount, dataArea, domainEdge);
/* 290 */       lineY2 = rangeAxis.valueToJava2D(this.value2, dataArea, rangeEdge);
/*     */     } 
/* 292 */     g2.setPaint(this.paint);
/* 293 */     g2.setStroke(this.stroke);
/* 294 */     g2.drawLine((int)lineX1, (int)lineY1, (int)lineX2, (int)lineY2);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 305 */     if (obj == this)
/* 306 */       return true; 
/* 308 */     if (!(obj instanceof CategoryLineAnnotation))
/* 309 */       return false; 
/* 311 */     CategoryLineAnnotation that = (CategoryLineAnnotation)obj;
/* 312 */     if (!this.category1.equals(that.getCategory1()))
/* 313 */       return false; 
/* 315 */     if (this.value1 != that.getValue1())
/* 316 */       return false; 
/* 318 */     if (!this.category2.equals(that.getCategory2()))
/* 319 */       return false; 
/* 321 */     if (this.value2 != that.getValue2())
/* 322 */       return false; 
/* 324 */     if (!PaintUtilities.equal(this.paint, that.paint))
/* 325 */       return false; 
/* 327 */     if (!ObjectUtilities.equal(this.stroke, that.stroke))
/* 328 */       return false; 
/* 330 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 340 */     return this.category1.hashCode() + this.category2.hashCode();
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 352 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 363 */     stream.defaultWriteObject();
/* 364 */     SerialUtilities.writePaint(this.paint, stream);
/* 365 */     SerialUtilities.writeStroke(this.stroke, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 378 */     stream.defaultReadObject();
/* 379 */     this.paint = SerialUtilities.readPaint(stream);
/* 380 */     this.stroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\CategoryLineAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */