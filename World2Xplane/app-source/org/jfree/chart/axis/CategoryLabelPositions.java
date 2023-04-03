/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.text.TextBlockAnchor;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ 
/*     */ public class CategoryLabelPositions implements Serializable {
/*     */   private static final long serialVersionUID = -8999557901920364580L;
/*     */   
/*  65 */   public static final CategoryLabelPositions STANDARD = new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_CENTER), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.TOP_CENTER), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.CENTER_RIGHT, CategoryLabelWidthType.RANGE, 0.3F), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT, CategoryLabelWidthType.RANGE, 0.3F));
/*     */   
/*  84 */   public static final CategoryLabelPositions UP_90 = new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, -1.5707963267948966D, CategoryLabelWidthType.RANGE, 0.3F), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.CENTER_RIGHT, TextAnchor.CENTER_RIGHT, -1.5707963267948966D, CategoryLabelWidthType.RANGE, 0.3F), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.BOTTOM_CENTER, TextAnchor.BOTTOM_CENTER, -1.5707963267948966D, CategoryLabelWidthType.CATEGORY, 0.9F), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.TOP_CENTER, TextAnchor.TOP_CENTER, -1.5707963267948966D, CategoryLabelWidthType.CATEGORY, 0.9F));
/*     */   
/* 109 */   public static final CategoryLabelPositions DOWN_90 = new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.CENTER_RIGHT, TextAnchor.CENTER_RIGHT, 1.5707963267948966D, CategoryLabelWidthType.RANGE, 0.3F), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, 1.5707963267948966D, CategoryLabelWidthType.RANGE, 0.3F), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.TOP_CENTER, TextAnchor.TOP_CENTER, 1.5707963267948966D, CategoryLabelWidthType.CATEGORY, 0.9F), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.BOTTOM_CENTER, TextAnchor.BOTTOM_CENTER, 1.5707963267948966D, CategoryLabelWidthType.CATEGORY, 0.9F));
/*     */   
/* 133 */   public static final CategoryLabelPositions UP_45 = createUpRotationLabelPositions(0.7853981633974483D);
/*     */   
/* 137 */   public static final CategoryLabelPositions DOWN_45 = createDownRotationLabelPositions(0.7853981633974483D);
/*     */   
/*     */   private CategoryLabelPosition positionForAxisAtTop;
/*     */   
/*     */   private CategoryLabelPosition positionForAxisAtBottom;
/*     */   
/*     */   private CategoryLabelPosition positionForAxisAtLeft;
/*     */   
/*     */   private CategoryLabelPosition positionForAxisAtRight;
/*     */   
/*     */   public static CategoryLabelPositions createUpRotationLabelPositions(double angle) {
/* 150 */     return new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_LEFT, TextAnchor.BOTTOM_LEFT, -angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.TOP_RIGHT, TextAnchor.TOP_RIGHT, -angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.BOTTOM_RIGHT, TextAnchor.BOTTOM_RIGHT, -angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, -angle, CategoryLabelWidthType.RANGE, 0.5F));
/*     */   }
/*     */   
/*     */   public static CategoryLabelPositions createDownRotationLabelPositions(double angle) {
/* 184 */     return new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_RIGHT, TextAnchor.BOTTOM_RIGHT, angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.TOP_RIGHT, TextAnchor.TOP_RIGHT, angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.BOTTOM_LEFT, TextAnchor.BOTTOM_LEFT, angle, CategoryLabelWidthType.RANGE, 0.5F));
/*     */   }
/*     */   
/*     */   public CategoryLabelPositions() {
/* 236 */     this.positionForAxisAtTop = new CategoryLabelPosition();
/* 237 */     this.positionForAxisAtBottom = new CategoryLabelPosition();
/* 238 */     this.positionForAxisAtLeft = new CategoryLabelPosition();
/* 239 */     this.positionForAxisAtRight = new CategoryLabelPosition();
/*     */   }
/*     */   
/*     */   public CategoryLabelPositions(CategoryLabelPosition top, CategoryLabelPosition bottom, CategoryLabelPosition left, CategoryLabelPosition right) {
/* 259 */     if (top == null)
/* 260 */       throw new IllegalArgumentException("Null 'top' argument."); 
/* 262 */     if (bottom == null)
/* 263 */       throw new IllegalArgumentException("Null 'bottom' argument."); 
/* 265 */     if (left == null)
/* 266 */       throw new IllegalArgumentException("Null 'left' argument."); 
/* 268 */     if (right == null)
/* 269 */       throw new IllegalArgumentException("Null 'right' argument."); 
/* 272 */     this.positionForAxisAtTop = top;
/* 273 */     this.positionForAxisAtBottom = bottom;
/* 274 */     this.positionForAxisAtLeft = left;
/* 275 */     this.positionForAxisAtRight = right;
/*     */   }
/*     */   
/*     */   public CategoryLabelPosition getLabelPosition(RectangleEdge edge) {
/* 288 */     CategoryLabelPosition result = null;
/* 289 */     if (edge == RectangleEdge.TOP) {
/* 290 */       result = this.positionForAxisAtTop;
/* 292 */     } else if (edge == RectangleEdge.BOTTOM) {
/* 293 */       result = this.positionForAxisAtBottom;
/* 295 */     } else if (edge == RectangleEdge.LEFT) {
/* 296 */       result = this.positionForAxisAtLeft;
/* 298 */     } else if (edge == RectangleEdge.RIGHT) {
/* 299 */       result = this.positionForAxisAtRight;
/*     */     } 
/* 301 */     return result;
/*     */   }
/*     */   
/*     */   public static CategoryLabelPositions replaceTopPosition(CategoryLabelPositions base, CategoryLabelPosition top) {
/* 316 */     if (base == null)
/* 317 */       throw new IllegalArgumentException("Null 'base' argument."); 
/* 319 */     if (top == null)
/* 320 */       throw new IllegalArgumentException("Null 'top' argument."); 
/* 323 */     return new CategoryLabelPositions(top, base.getLabelPosition(RectangleEdge.BOTTOM), base.getLabelPosition(RectangleEdge.LEFT), base.getLabelPosition(RectangleEdge.RIGHT));
/*     */   }
/*     */   
/*     */   public static CategoryLabelPositions replaceBottomPosition(CategoryLabelPositions base, CategoryLabelPosition bottom) {
/* 343 */     if (base == null)
/* 344 */       throw new IllegalArgumentException("Null 'base' argument."); 
/* 346 */     if (bottom == null)
/* 347 */       throw new IllegalArgumentException("Null 'bottom' argument."); 
/* 350 */     return new CategoryLabelPositions(base.getLabelPosition(RectangleEdge.TOP), bottom, base.getLabelPosition(RectangleEdge.LEFT), base.getLabelPosition(RectangleEdge.RIGHT));
/*     */   }
/*     */   
/*     */   public static CategoryLabelPositions replaceLeftPosition(CategoryLabelPositions base, CategoryLabelPosition left) {
/* 370 */     if (base == null)
/* 371 */       throw new IllegalArgumentException("Null 'base' argument."); 
/* 373 */     if (left == null)
/* 374 */       throw new IllegalArgumentException("Null 'left' argument."); 
/* 377 */     return new CategoryLabelPositions(base.getLabelPosition(RectangleEdge.TOP), base.getLabelPosition(RectangleEdge.BOTTOM), left, base.getLabelPosition(RectangleEdge.RIGHT));
/*     */   }
/*     */   
/*     */   public static CategoryLabelPositions replaceRightPosition(CategoryLabelPositions base, CategoryLabelPosition right) {
/* 397 */     if (base == null)
/* 398 */       throw new IllegalArgumentException("Null 'base' argument."); 
/* 400 */     if (right == null)
/* 401 */       throw new IllegalArgumentException("Null 'right' argument."); 
/* 404 */     return new CategoryLabelPositions(base.getLabelPosition(RectangleEdge.TOP), base.getLabelPosition(RectangleEdge.BOTTOM), base.getLabelPosition(RectangleEdge.LEFT), right);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 422 */     if (this == obj)
/* 423 */       return true; 
/* 425 */     if (!(obj instanceof CategoryLabelPositions))
/* 426 */       return false; 
/* 429 */     CategoryLabelPositions that = (CategoryLabelPositions)obj;
/* 430 */     if (!this.positionForAxisAtTop.equals(that.positionForAxisAtTop))
/* 431 */       return false; 
/* 433 */     if (!this.positionForAxisAtBottom.equals(that.positionForAxisAtBottom))
/* 435 */       return false; 
/* 437 */     if (!this.positionForAxisAtLeft.equals(that.positionForAxisAtLeft))
/* 438 */       return false; 
/* 440 */     if (!this.positionForAxisAtRight.equals(that.positionForAxisAtRight))
/* 441 */       return false; 
/* 444 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 454 */     int result = 19;
/* 455 */     result = 37 * result + this.positionForAxisAtTop.hashCode();
/* 456 */     result = 37 * result + this.positionForAxisAtBottom.hashCode();
/* 457 */     result = 37 * result + this.positionForAxisAtLeft.hashCode();
/* 458 */     result = 37 * result + this.positionForAxisAtRight.hashCode();
/* 459 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\CategoryLabelPositions.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */