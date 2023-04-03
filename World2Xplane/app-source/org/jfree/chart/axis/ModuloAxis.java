/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.event.AxisChangeEvent;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ public class ModuloAxis extends NumberAxis {
/*     */   private Range fixedRange;
/*     */   
/*     */   private double displayStart;
/*     */   
/*     */   private double displayEnd;
/*     */   
/*     */   public ModuloAxis(String label, Range fixedRange) {
/*  81 */     super(label);
/*  82 */     this.fixedRange = fixedRange;
/*  83 */     this.displayStart = 270.0D;
/*  84 */     this.displayEnd = 90.0D;
/*     */   }
/*     */   
/*     */   public double getDisplayStart() {
/*  93 */     return this.displayStart;
/*     */   }
/*     */   
/*     */   public double getDisplayEnd() {
/* 102 */     return this.displayEnd;
/*     */   }
/*     */   
/*     */   public void setDisplayRange(double start, double end) {
/* 113 */     this.displayStart = mapValueToFixedRange(start);
/* 114 */     this.displayEnd = mapValueToFixedRange(end);
/* 115 */     if (this.displayStart < this.displayEnd) {
/* 116 */       setRange(this.displayStart, this.displayEnd);
/*     */     } else {
/* 119 */       setRange(this.displayStart, this.fixedRange.getUpperBound() + this.displayEnd - this.fixedRange.getLowerBound());
/*     */     } 
/* 125 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
/*     */   
/*     */   protected void autoAdjustRange() {
/* 133 */     setRange(this.fixedRange, false, false);
/*     */   }
/*     */   
/*     */   public double valueToJava2D(double value, Rectangle2D area, RectangleEdge edge) {
/* 147 */     double result = 0.0D;
/* 148 */     double v = mapValueToFixedRange(value);
/* 149 */     if (this.displayStart < this.displayEnd) {
/* 150 */       result = trans(v, area, edge);
/*     */     } else {
/* 153 */       double cutoff = (this.displayStart + this.displayEnd) / 2.0D;
/* 154 */       double length1 = this.fixedRange.getUpperBound() - this.displayStart;
/* 156 */       double length2 = this.displayEnd - this.fixedRange.getLowerBound();
/* 157 */       if (v > cutoff) {
/* 158 */         result = transStart(v, area, edge, length1, length2);
/*     */       } else {
/* 161 */         result = transEnd(v, area, edge, length1, length2);
/*     */       } 
/*     */     } 
/* 164 */     return result;
/*     */   }
/*     */   
/*     */   private double trans(double value, Rectangle2D area, RectangleEdge edge) {
/* 177 */     double min = 0.0D;
/* 178 */     double max = 0.0D;
/* 179 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 180 */       min = area.getX();
/* 181 */       max = area.getX() + area.getWidth();
/* 183 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/* 184 */       min = area.getMaxY();
/* 185 */       max = area.getMaxY() - area.getHeight();
/*     */     } 
/* 187 */     if (isInverted())
/* 188 */       return max - (value - this.displayStart) / (this.displayEnd - this.displayStart) * (max - min); 
/* 192 */     return min + (value - this.displayStart) / (this.displayEnd - this.displayStart) * (max - min);
/*     */   }
/*     */   
/*     */   private double transStart(double value, Rectangle2D area, RectangleEdge edge, double length1, double length2) {
/* 213 */     double min = 0.0D;
/* 214 */     double max = 0.0D;
/* 215 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 216 */       min = area.getX();
/* 217 */       max = area.getX() + area.getWidth() * length1 / (length1 + length2);
/* 219 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/* 220 */       min = area.getMaxY();
/* 221 */       max = area.getMaxY() - area.getHeight() * length1 / (length1 + length2);
/*     */     } 
/* 224 */     if (isInverted())
/* 225 */       return max - (value - this.displayStart) / (this.fixedRange.getUpperBound() - this.displayStart) * (max - min); 
/* 230 */     return min + (value - this.displayStart) / (this.fixedRange.getUpperBound() - this.displayStart) * (max - min);
/*     */   }
/*     */   
/*     */   private double transEnd(double value, Rectangle2D area, RectangleEdge edge, double length1, double length2) {
/* 251 */     double min = 0.0D;
/* 252 */     double max = 0.0D;
/* 253 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 254 */       max = area.getMaxX();
/* 255 */       min = area.getMaxX() - area.getWidth() * length2 / (length1 + length2);
/* 258 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/* 259 */       max = area.getMinY();
/* 260 */       min = area.getMinY() + area.getHeight() * length2 / (length1 + length2);
/*     */     } 
/* 263 */     if (isInverted())
/* 264 */       return max - (value - this.fixedRange.getLowerBound()) / (this.displayEnd - this.fixedRange.getLowerBound()) * (max - min); 
/* 269 */     return min + (value - this.fixedRange.getLowerBound()) / (this.displayEnd - this.fixedRange.getLowerBound()) * (max - min);
/*     */   }
/*     */   
/*     */   private double mapValueToFixedRange(double value) {
/* 284 */     double lower = this.fixedRange.getLowerBound();
/* 285 */     double length = this.fixedRange.getLength();
/* 286 */     if (value < lower)
/* 287 */       return lower + length + (value - lower) % length; 
/* 290 */     return lower + (value - lower) % length;
/*     */   }
/*     */   
/*     */   public double java2DToValue(double java2DValue, Rectangle2D area, RectangleEdge edge) {
/* 305 */     double result = 0.0D;
/* 306 */     if (this.displayStart < this.displayEnd)
/* 307 */       result = super.java2DToValue(java2DValue, area, edge); 
/* 312 */     return result;
/*     */   }
/*     */   
/*     */   private double getDisplayLength() {
/* 321 */     if (this.displayStart < this.displayEnd)
/* 322 */       return this.displayEnd - this.displayStart; 
/* 325 */     return this.fixedRange.getUpperBound() - this.displayStart + this.displayEnd - this.fixedRange.getLowerBound();
/*     */   }
/*     */   
/*     */   private double getDisplayCentralValue() {
/* 336 */     return mapValueToFixedRange(this.displayStart + getDisplayLength() / 2.0D);
/*     */   }
/*     */   
/*     */   public void resizeRange(double percent) {
/* 352 */     resizeRange(percent, getDisplayCentralValue());
/*     */   }
/*     */   
/*     */   public void resizeRange(double percent, double anchorValue) {
/* 368 */     if (percent > 0.0D) {
/* 369 */       double halfLength = getDisplayLength() * percent / 2.0D;
/* 370 */       setDisplayRange(anchorValue - halfLength, anchorValue + halfLength);
/*     */     } else {
/* 373 */       setAutoRange(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double lengthToJava2D(double length, Rectangle2D area, RectangleEdge edge) {
/* 390 */     double axisLength = 0.0D;
/* 391 */     if (this.displayEnd > this.displayStart) {
/* 392 */       axisLength = this.displayEnd - this.displayStart;
/*     */     } else {
/* 395 */       axisLength = this.fixedRange.getUpperBound() - this.displayStart + this.displayEnd - this.fixedRange.getLowerBound();
/*     */     } 
/* 398 */     double areaLength = 0.0D;
/* 399 */     if (RectangleEdge.isLeftOrRight(edge)) {
/* 400 */       areaLength = area.getHeight();
/*     */     } else {
/* 403 */       areaLength = area.getWidth();
/*     */     } 
/* 405 */     return length / axisLength * areaLength;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\ModuloAxis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */