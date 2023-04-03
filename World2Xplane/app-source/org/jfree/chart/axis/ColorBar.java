/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.plot.ColorPalette;
/*     */ import org.jfree.chart.plot.ContourPlot;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.RainbowPalette;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ public class ColorBar implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -2101776212647268103L;
/*     */   
/*     */   public static final int DEFAULT_COLORBAR_THICKNESS = 0;
/*     */   
/*     */   public static final double DEFAULT_COLORBAR_THICKNESS_PERCENT = 0.1D;
/*     */   
/*     */   public static final int DEFAULT_OUTERGAP = 2;
/*     */   
/*     */   private ValueAxis axis;
/*     */   
/*  93 */   private int colorBarThickness = 0;
/*     */   
/*  98 */   private double colorBarThicknessPercent = 0.1D;
/*     */   
/* 102 */   private ColorPalette colorPalette = null;
/*     */   
/* 105 */   private int colorBarLength = 0;
/*     */   
/*     */   private int outerGap;
/*     */   
/*     */   public ColorBar(String label) {
/* 118 */     NumberAxis a = new NumberAxis(label);
/* 119 */     a.setAutoRangeIncludesZero(false);
/* 120 */     this.axis = a;
/* 121 */     this.axis.setLowerMargin(0.0D);
/* 122 */     this.axis.setUpperMargin(0.0D);
/* 124 */     this.colorPalette = (ColorPalette)new RainbowPalette();
/* 125 */     this.colorBarThickness = 0;
/* 126 */     this.colorBarThicknessPercent = 0.1D;
/* 127 */     this.outerGap = 2;
/* 128 */     this.colorPalette.setMinZ(this.axis.getRange().getLowerBound());
/* 129 */     this.colorPalette.setMaxZ(this.axis.getRange().getUpperBound());
/*     */   }
/*     */   
/*     */   public void configure(ContourPlot plot) {
/* 139 */     double minZ = plot.getDataset().getMinZValue();
/* 140 */     double maxZ = plot.getDataset().getMaxZValue();
/* 141 */     setMinimumValue(minZ);
/* 142 */     setMaximumValue(maxZ);
/*     */   }
/*     */   
/*     */   public ValueAxis getAxis() {
/* 151 */     return this.axis;
/*     */   }
/*     */   
/*     */   public void setAxis(ValueAxis axis) {
/* 160 */     this.axis = axis;
/*     */   }
/*     */   
/*     */   public void autoAdjustRange() {
/* 167 */     this.axis.autoAdjustRange();
/* 168 */     this.colorPalette.setMinZ(this.axis.getLowerBound());
/* 169 */     this.colorPalette.setMaxZ(this.axis.getUpperBound());
/*     */   }
/*     */   
/*     */   public double draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, Rectangle2D reservedArea, RectangleEdge edge) {
/* 191 */     Rectangle2D colorBarArea = null;
/* 193 */     double thickness = calculateBarThickness(dataArea, edge);
/* 194 */     if (this.colorBarThickness > 0)
/* 195 */       thickness = this.colorBarThickness; 
/* 198 */     double length = 0.0D;
/* 199 */     if (RectangleEdge.isLeftOrRight(edge)) {
/* 200 */       length = dataArea.getHeight();
/*     */     } else {
/* 203 */       length = dataArea.getWidth();
/*     */     } 
/* 206 */     if (this.colorBarLength > 0)
/* 207 */       length = this.colorBarLength; 
/* 210 */     if (edge == RectangleEdge.BOTTOM) {
/* 211 */       colorBarArea = new Rectangle2D.Double(dataArea.getX(), plotArea.getMaxY() + this.outerGap, length, thickness);
/* 216 */     } else if (edge == RectangleEdge.TOP) {
/* 217 */       colorBarArea = new Rectangle2D.Double(dataArea.getX(), reservedArea.getMinY() + this.outerGap, length, thickness);
/* 222 */     } else if (edge == RectangleEdge.LEFT) {
/* 223 */       colorBarArea = new Rectangle2D.Double(plotArea.getX() - thickness - this.outerGap, dataArea.getMinY(), thickness, length);
/* 228 */     } else if (edge == RectangleEdge.RIGHT) {
/* 229 */       colorBarArea = new Rectangle2D.Double(plotArea.getMaxX() + this.outerGap, dataArea.getMinY(), thickness, length);
/*     */     } 
/* 236 */     this.axis.refreshTicks(g2, new AxisState(), colorBarArea, edge);
/* 240 */     drawColorBar(g2, colorBarArea, edge);
/* 242 */     AxisState state = null;
/* 243 */     if (edge == RectangleEdge.TOP) {
/* 244 */       cursor = colorBarArea.getMinY();
/* 245 */       state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, RectangleEdge.TOP, null);
/* 249 */     } else if (edge == RectangleEdge.BOTTOM) {
/* 250 */       cursor = colorBarArea.getMaxY();
/* 251 */       state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, RectangleEdge.BOTTOM, null);
/* 256 */     } else if (edge == RectangleEdge.LEFT) {
/* 257 */       cursor = colorBarArea.getMinX();
/* 258 */       state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, RectangleEdge.LEFT, null);
/* 262 */     } else if (edge == RectangleEdge.RIGHT) {
/* 263 */       cursor = colorBarArea.getMaxX();
/* 264 */       state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, RectangleEdge.RIGHT, null);
/*     */     } 
/* 269 */     return state.getCursor();
/*     */   }
/*     */   
/*     */   public void drawColorBar(Graphics2D g2, Rectangle2D colorBarArea, RectangleEdge edge) {
/* 284 */     Object antiAlias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
/* 285 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/* 291 */     Stroke strokeSaved = g2.getStroke();
/* 292 */     g2.setStroke(new BasicStroke(1.0F));
/* 294 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 295 */       double y1 = colorBarArea.getY();
/* 296 */       double y2 = colorBarArea.getMaxY();
/* 297 */       double xx = colorBarArea.getX();
/* 298 */       Line2D line = new Line2D.Double();
/* 299 */       while (xx <= colorBarArea.getMaxX()) {
/* 300 */         double value = this.axis.java2DToValue(xx, colorBarArea, edge);
/* 301 */         line.setLine(xx, y1, xx, y2);
/* 302 */         g2.setPaint(getPaint(value));
/* 303 */         g2.draw(line);
/* 304 */         xx++;
/*     */       } 
/*     */     } else {
/* 308 */       double y1 = colorBarArea.getX();
/* 309 */       double y2 = colorBarArea.getMaxX();
/* 310 */       double xx = colorBarArea.getY();
/* 311 */       Line2D line = new Line2D.Double();
/* 312 */       while (xx <= colorBarArea.getMaxY()) {
/* 313 */         double value = this.axis.java2DToValue(xx, colorBarArea, edge);
/* 314 */         line.setLine(y1, xx, y2, xx);
/* 315 */         g2.setPaint(getPaint(value));
/* 316 */         g2.draw(line);
/* 317 */         xx++;
/*     */       } 
/*     */     } 
/* 321 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias);
/* 322 */     g2.setStroke(strokeSaved);
/*     */   }
/*     */   
/*     */   public ColorPalette getColorPalette() {
/* 332 */     return this.colorPalette;
/*     */   }
/*     */   
/*     */   public Paint getPaint(double value) {
/* 343 */     return this.colorPalette.getPaint(value);
/*     */   }
/*     */   
/*     */   public void setColorPalette(ColorPalette palette) {
/* 352 */     this.colorPalette = palette;
/*     */   }
/*     */   
/*     */   public void setMaximumValue(double value) {
/* 361 */     this.colorPalette.setMaxZ(value);
/* 362 */     this.axis.setUpperBound(value);
/*     */   }
/*     */   
/*     */   public void setMinimumValue(double value) {
/* 371 */     this.colorPalette.setMinZ(value);
/* 372 */     this.axis.setLowerBound(value);
/*     */   }
/*     */   
/*     */   public AxisSpace reserveSpace(Graphics2D g2, Plot plot, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, AxisSpace space) {
/* 392 */     AxisSpace result = this.axis.reserveSpace(g2, plot, plotArea, edge, space);
/* 395 */     double thickness = calculateBarThickness(dataArea, edge);
/* 396 */     result.add(thickness + (2 * this.outerGap), edge);
/* 397 */     return result;
/*     */   }
/*     */   
/*     */   private double calculateBarThickness(Rectangle2D plotArea, RectangleEdge edge) {
/* 411 */     double result = 0.0D;
/* 412 */     if (RectangleEdge.isLeftOrRight(edge)) {
/* 413 */       result = plotArea.getWidth() * this.colorBarThicknessPercent;
/*     */     } else {
/* 416 */       result = plotArea.getHeight() * this.colorBarThicknessPercent;
/*     */     } 
/* 418 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 431 */     ColorBar clone = (ColorBar)super.clone();
/* 432 */     clone.axis = (ValueAxis)this.axis.clone();
/* 433 */     return clone;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 446 */     if (obj == this)
/* 447 */       return true; 
/* 449 */     if (!(obj instanceof ColorBar))
/* 450 */       return false; 
/* 452 */     ColorBar that = (ColorBar)obj;
/* 453 */     if (!this.axis.equals(that.axis))
/* 454 */       return false; 
/* 456 */     if (this.colorBarThickness != that.colorBarThickness)
/* 457 */       return false; 
/* 459 */     if (this.colorBarThicknessPercent != that.colorBarThicknessPercent)
/* 460 */       return false; 
/* 462 */     if (!this.colorPalette.equals(that.colorPalette))
/* 463 */       return false; 
/* 465 */     if (this.colorBarLength != that.colorBarLength)
/* 466 */       return false; 
/* 468 */     if (this.outerGap != that.outerGap)
/* 469 */       return false; 
/* 471 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 481 */     return this.axis.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\ColorBar.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */