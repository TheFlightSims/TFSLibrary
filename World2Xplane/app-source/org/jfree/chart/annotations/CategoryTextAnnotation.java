/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAnchor;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ public class CategoryTextAnnotation extends TextAnnotation implements CategoryAnnotation, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 3333360090781320147L;
/*     */   
/*     */   private Comparable category;
/*     */   
/*     */   private CategoryAnchor categoryAnchor;
/*     */   
/*     */   private double value;
/*     */   
/*     */   public CategoryTextAnnotation(String text, Comparable category, double value) {
/*  94 */     super(text);
/*  95 */     if (category == null)
/*  96 */       throw new IllegalArgumentException("Null 'category' argument."); 
/*  98 */     this.category = category;
/*  99 */     this.value = value;
/* 100 */     this.categoryAnchor = CategoryAnchor.MIDDLE;
/*     */   }
/*     */   
/*     */   public Comparable getCategory() {
/* 109 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(Comparable category) {
/* 118 */     if (category == null)
/* 119 */       throw new IllegalArgumentException("Null 'category' argument."); 
/* 121 */     this.category = category;
/*     */   }
/*     */   
/*     */   public CategoryAnchor getCategoryAnchor() {
/* 130 */     return this.categoryAnchor;
/*     */   }
/*     */   
/*     */   public void setCategoryAnchor(CategoryAnchor anchor) {
/* 139 */     if (anchor == null)
/* 140 */       throw new IllegalArgumentException("Null 'anchor' argument."); 
/* 142 */     this.categoryAnchor = anchor;
/*     */   }
/*     */   
/*     */   public double getValue() {
/* 151 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(double value) {
/* 160 */     this.value = value;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea, CategoryAxis domainAxis, ValueAxis rangeAxis) {
/* 175 */     CategoryDataset dataset = plot.getDataset();
/* 176 */     int catIndex = dataset.getColumnIndex(this.category);
/* 177 */     int catCount = dataset.getColumnCount();
/* 179 */     float anchorX = 0.0F;
/* 180 */     float anchorY = 0.0F;
/* 181 */     PlotOrientation orientation = plot.getOrientation();
/* 182 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/* 185 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/* 189 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 190 */       anchorY = (float)domainAxis.getCategoryJava2DCoordinate(this.categoryAnchor, catIndex, catCount, dataArea, domainEdge);
/* 193 */       anchorX = (float)rangeAxis.valueToJava2D(this.value, dataArea, rangeEdge);
/* 197 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 198 */       anchorX = (float)domainAxis.getCategoryJava2DCoordinate(this.categoryAnchor, catIndex, catCount, dataArea, domainEdge);
/* 201 */       anchorY = (float)rangeAxis.valueToJava2D(this.value, dataArea, rangeEdge);
/*     */     } 
/* 205 */     g2.setFont(getFont());
/* 206 */     g2.setPaint(getPaint());
/* 207 */     TextUtilities.drawRotatedString(getText(), g2, anchorX, anchorY, getTextAnchor(), getRotationAngle(), getRotationAnchor());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 227 */     if (obj == this)
/* 228 */       return true; 
/* 230 */     if (!(obj instanceof CategoryTextAnnotation))
/* 231 */       return false; 
/* 233 */     CategoryTextAnnotation that = (CategoryTextAnnotation)obj;
/* 234 */     if (!super.equals(obj))
/* 235 */       return false; 
/* 237 */     if (!this.category.equals(that.getCategory()))
/* 238 */       return false; 
/* 240 */     if (!this.categoryAnchor.equals(that.getCategoryAnchor()))
/* 241 */       return false; 
/* 243 */     if (this.value != that.getValue())
/* 244 */       return false; 
/* 246 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 258 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\CategoryTextAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */