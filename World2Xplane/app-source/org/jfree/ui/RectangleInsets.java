/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.UnitType;
/*     */ 
/*     */ public class RectangleInsets implements Serializable {
/*     */   private static final long serialVersionUID = 1902273207559319996L;
/*     */   
/*  69 */   public static final RectangleInsets ZERO_INSETS = new RectangleInsets(UnitType.ABSOLUTE, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   
/*     */   private UnitType unitType;
/*     */   
/*     */   private double top;
/*     */   
/*     */   private double left;
/*     */   
/*     */   private double bottom;
/*     */   
/*     */   private double right;
/*     */   
/*     */   public RectangleInsets(double top, double left, double bottom, double right) {
/*  98 */     this(UnitType.ABSOLUTE, top, left, bottom, right);
/*     */   }
/*     */   
/*     */   public RectangleInsets(UnitType unitType, double top, double left, double bottom, double right) {
/* 114 */     if (unitType == null)
/* 115 */       throw new IllegalArgumentException("Null 'unitType' argument."); 
/* 117 */     this.unitType = unitType;
/* 118 */     this.top = top;
/* 119 */     this.bottom = bottom;
/* 120 */     this.left = left;
/* 121 */     this.right = right;
/*     */   }
/*     */   
/*     */   public UnitType getUnitType() {
/* 131 */     return this.unitType;
/*     */   }
/*     */   
/*     */   public double getTop() {
/* 140 */     return this.top;
/*     */   }
/*     */   
/*     */   public double getBottom() {
/* 149 */     return this.bottom;
/*     */   }
/*     */   
/*     */   public double getLeft() {
/* 158 */     return this.left;
/*     */   }
/*     */   
/*     */   public double getRight() {
/* 167 */     return this.right;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 178 */     if (obj == this)
/* 179 */       return true; 
/* 181 */     if (!(obj instanceof RectangleInsets))
/* 182 */       return false; 
/* 184 */     RectangleInsets that = (RectangleInsets)obj;
/* 185 */     if (that.unitType != this.unitType)
/* 186 */       return false; 
/* 188 */     if (this.left != that.left)
/* 189 */       return false; 
/* 191 */     if (this.right != that.right)
/* 192 */       return false; 
/* 194 */     if (this.top != that.top)
/* 195 */       return false; 
/* 197 */     if (this.bottom != that.bottom)
/* 198 */       return false; 
/* 200 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 211 */     int result = (this.unitType != null) ? this.unitType.hashCode() : 0;
/* 212 */     long temp = (this.top != 0.0D) ? Double.doubleToLongBits(this.top) : 0L;
/* 213 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 214 */     temp = (this.bottom != 0.0D) ? Double.doubleToLongBits(this.bottom) : 0L;
/* 215 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 216 */     temp = (this.left != 0.0D) ? Double.doubleToLongBits(this.left) : 0L;
/* 217 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 218 */     temp = (this.right != 0.0D) ? Double.doubleToLongBits(this.right) : 0L;
/* 219 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 220 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 230 */     return "RectangleInsets[t=" + this.top + ",l=" + this.left + ",b=" + this.bottom + ",r=" + this.right + "]";
/*     */   }
/*     */   
/*     */   public Rectangle2D createAdjustedRectangle(Rectangle2D base, LengthAdjustmentType horizontal, LengthAdjustmentType vertical) {
/* 250 */     if (base == null)
/* 251 */       throw new IllegalArgumentException("Null 'base' argument."); 
/* 253 */     double x = base.getX();
/* 254 */     double y = base.getY();
/* 255 */     double w = base.getWidth();
/* 256 */     double h = base.getHeight();
/* 257 */     if (horizontal == LengthAdjustmentType.EXPAND) {
/* 258 */       double leftOutset = calculateLeftOutset(w);
/* 259 */       x -= leftOutset;
/* 260 */       w = w + leftOutset + calculateRightOutset(w);
/* 262 */     } else if (horizontal == LengthAdjustmentType.CONTRACT) {
/* 263 */       double leftMargin = calculateLeftInset(w);
/* 264 */       x += leftMargin;
/* 265 */       w = w - leftMargin - calculateRightInset(w);
/*     */     } 
/* 267 */     if (vertical == LengthAdjustmentType.EXPAND) {
/* 268 */       double topMargin = calculateTopOutset(h);
/* 269 */       y -= topMargin;
/* 270 */       h = h + topMargin + calculateBottomOutset(h);
/* 272 */     } else if (vertical == LengthAdjustmentType.CONTRACT) {
/* 273 */       double topMargin = calculateTopInset(h);
/* 274 */       y += topMargin;
/* 275 */       h = h - topMargin - calculateBottomInset(h);
/*     */     } 
/* 277 */     return new Rectangle2D.Double(x, y, w, h);
/*     */   }
/*     */   
/*     */   public Rectangle2D createInsetRectangle(Rectangle2D base) {
/* 288 */     return createInsetRectangle(base, true, true);
/*     */   }
/*     */   
/*     */   public Rectangle2D createInsetRectangle(Rectangle2D base, boolean horizontal, boolean vertical) {
/* 303 */     if (base == null)
/* 304 */       throw new IllegalArgumentException("Null 'base' argument."); 
/* 306 */     double topMargin = 0.0D;
/* 307 */     double bottomMargin = 0.0D;
/* 308 */     if (vertical) {
/* 309 */       topMargin = calculateTopInset(base.getHeight());
/* 310 */       bottomMargin = calculateBottomInset(base.getHeight());
/*     */     } 
/* 312 */     double leftMargin = 0.0D;
/* 313 */     double rightMargin = 0.0D;
/* 314 */     if (horizontal) {
/* 315 */       leftMargin = calculateLeftInset(base.getWidth());
/* 316 */       rightMargin = calculateRightInset(base.getWidth());
/*     */     } 
/* 318 */     return new Rectangle2D.Double(base.getX() + leftMargin, base.getY() + topMargin, base.getWidth() - leftMargin - rightMargin, base.getHeight() - topMargin - bottomMargin);
/*     */   }
/*     */   
/*     */   public Rectangle2D createOutsetRectangle(Rectangle2D base) {
/* 334 */     return createOutsetRectangle(base, true, true);
/*     */   }
/*     */   
/*     */   public Rectangle2D createOutsetRectangle(Rectangle2D base, boolean horizontal, boolean vertical) {
/* 349 */     if (base == null)
/* 350 */       throw new IllegalArgumentException("Null 'base' argument."); 
/* 352 */     double topMargin = 0.0D;
/* 353 */     double bottomMargin = 0.0D;
/* 354 */     if (vertical) {
/* 355 */       topMargin = calculateTopOutset(base.getHeight());
/* 356 */       bottomMargin = calculateBottomOutset(base.getHeight());
/*     */     } 
/* 358 */     double leftMargin = 0.0D;
/* 359 */     double rightMargin = 0.0D;
/* 360 */     if (horizontal) {
/* 361 */       leftMargin = calculateLeftOutset(base.getWidth());
/* 362 */       rightMargin = calculateRightOutset(base.getWidth());
/*     */     } 
/* 364 */     return new Rectangle2D.Double(base.getX() - leftMargin, base.getY() - topMargin, base.getWidth() + leftMargin + rightMargin, base.getHeight() + topMargin + bottomMargin);
/*     */   }
/*     */   
/*     */   public double calculateTopInset(double height) {
/* 380 */     double result = this.top;
/* 381 */     if (this.unitType == UnitType.RELATIVE)
/* 382 */       result = this.top * height; 
/* 384 */     return result;
/*     */   }
/*     */   
/*     */   public double calculateTopOutset(double height) {
/* 395 */     double result = this.top;
/* 396 */     if (this.unitType == UnitType.RELATIVE)
/* 397 */       result = height / (1.0D - this.top - this.bottom) * this.top; 
/* 399 */     return result;
/*     */   }
/*     */   
/*     */   public double calculateBottomInset(double height) {
/* 410 */     double result = this.bottom;
/* 411 */     if (this.unitType == UnitType.RELATIVE)
/* 412 */       result = this.bottom * height; 
/* 414 */     return result;
/*     */   }
/*     */   
/*     */   public double calculateBottomOutset(double height) {
/* 425 */     double result = this.bottom;
/* 426 */     if (this.unitType == UnitType.RELATIVE)
/* 427 */       result = height / (1.0D - this.top - this.bottom) * this.bottom; 
/* 429 */     return result;
/*     */   }
/*     */   
/*     */   public double calculateLeftInset(double width) {
/* 440 */     double result = this.left;
/* 441 */     if (this.unitType == UnitType.RELATIVE)
/* 442 */       result = this.left * width; 
/* 444 */     return result;
/*     */   }
/*     */   
/*     */   public double calculateLeftOutset(double width) {
/* 455 */     double result = this.left;
/* 456 */     if (this.unitType == UnitType.RELATIVE)
/* 457 */       result = width / (1.0D - this.left - this.right) * this.left; 
/* 459 */     return result;
/*     */   }
/*     */   
/*     */   public double calculateRightInset(double width) {
/* 470 */     double result = this.right;
/* 471 */     if (this.unitType == UnitType.RELATIVE)
/* 472 */       result = this.right * width; 
/* 474 */     return result;
/*     */   }
/*     */   
/*     */   public double calculateRightOutset(double width) {
/* 485 */     double result = this.right;
/* 486 */     if (this.unitType == UnitType.RELATIVE)
/* 487 */       result = width / (1.0D - this.left - this.right) * this.right; 
/* 489 */     return result;
/*     */   }
/*     */   
/*     */   public double trimWidth(double width) {
/* 500 */     return width - calculateLeftInset(width) - calculateRightInset(width);
/*     */   }
/*     */   
/*     */   public double extendWidth(double width) {
/* 511 */     return width + calculateLeftOutset(width) + calculateRightOutset(width);
/*     */   }
/*     */   
/*     */   public double trimHeight(double height) {
/* 522 */     return height - calculateTopInset(height) - calculateBottomInset(height);
/*     */   }
/*     */   
/*     */   public double extendHeight(double height) {
/* 534 */     return height + calculateTopOutset(height) + calculateBottomOutset(height);
/*     */   }
/*     */   
/*     */   public void trim(Rectangle2D area) {
/* 544 */     double w = area.getWidth();
/* 545 */     double h = area.getHeight();
/* 546 */     double l = calculateLeftInset(w);
/* 547 */     double r = calculateRightInset(w);
/* 548 */     double t = calculateTopInset(h);
/* 549 */     double b = calculateBottomInset(h);
/* 550 */     area.setRect(area.getX() + l, area.getY() + t, w - l - r, h - t - b);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\RectangleInsets.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */