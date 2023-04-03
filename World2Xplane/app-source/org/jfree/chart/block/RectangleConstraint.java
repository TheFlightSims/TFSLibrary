/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.ui.Size2D;
/*     */ 
/*     */ public class RectangleConstraint {
/*  61 */   public static final RectangleConstraint NONE = new RectangleConstraint(0.0D, null, LengthConstraintType.NONE, 0.0D, null, LengthConstraintType.NONE);
/*     */   
/*     */   private double width;
/*     */   
/*     */   private Range widthRange;
/*     */   
/*     */   private LengthConstraintType widthConstraintType;
/*     */   
/*     */   private double height;
/*     */   
/*     */   private Range heightRange;
/*     */   
/*     */   private LengthConstraintType heightConstraintType;
/*     */   
/*     */   public RectangleConstraint(double w, double h) {
/*  90 */     this(w, null, LengthConstraintType.FIXED, h, null, LengthConstraintType.FIXED);
/*     */   }
/*     */   
/*     */   public RectangleConstraint(Range w, Range h) {
/* 103 */     this(0.0D, w, LengthConstraintType.RANGE, 0.0D, h, LengthConstraintType.RANGE);
/*     */   }
/*     */   
/*     */   public RectangleConstraint(Range w, double h) {
/* 117 */     this(0.0D, w, LengthConstraintType.RANGE, h, null, LengthConstraintType.FIXED);
/*     */   }
/*     */   
/*     */   public RectangleConstraint(double w, Range h) {
/* 131 */     this(w, null, LengthConstraintType.FIXED, 0.0D, h, LengthConstraintType.RANGE);
/*     */   }
/*     */   
/*     */   public RectangleConstraint(double w, Range widthRange, LengthConstraintType widthConstraintType, double h, Range heightRange, LengthConstraintType heightConstraintType) {
/* 151 */     if (widthConstraintType == null)
/* 152 */       throw new IllegalArgumentException("Null 'widthType' argument."); 
/* 154 */     if (heightConstraintType == null)
/* 155 */       throw new IllegalArgumentException("Null 'heightType' argument."); 
/* 157 */     this.width = w;
/* 158 */     this.widthRange = widthRange;
/* 159 */     this.widthConstraintType = widthConstraintType;
/* 160 */     this.height = h;
/* 161 */     this.heightRange = heightRange;
/* 162 */     this.heightConstraintType = heightConstraintType;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/* 171 */     return this.width;
/*     */   }
/*     */   
/*     */   public Range getWidthRange() {
/* 180 */     return this.widthRange;
/*     */   }
/*     */   
/*     */   public LengthConstraintType getWidthConstraintType() {
/* 189 */     return this.widthConstraintType;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/* 198 */     return this.height;
/*     */   }
/*     */   
/*     */   public Range getHeightRange() {
/* 207 */     return this.heightRange;
/*     */   }
/*     */   
/*     */   public LengthConstraintType getHeightConstraintType() {
/* 216 */     return this.heightConstraintType;
/*     */   }
/*     */   
/*     */   public RectangleConstraint toUnconstrainedWidth() {
/* 226 */     if (this.widthConstraintType == LengthConstraintType.NONE)
/* 227 */       return this; 
/* 230 */     return new RectangleConstraint(this.width, this.widthRange, LengthConstraintType.NONE, this.height, this.heightRange, this.heightConstraintType);
/*     */   }
/*     */   
/*     */   public RectangleConstraint toUnconstrainedHeight() {
/* 244 */     if (this.heightConstraintType == LengthConstraintType.NONE)
/* 245 */       return this; 
/* 248 */     return new RectangleConstraint(this.width, this.widthRange, this.widthConstraintType, 0.0D, this.heightRange, LengthConstraintType.NONE);
/*     */   }
/*     */   
/*     */   public RectangleConstraint toFixedWidth(double width) {
/* 264 */     return new RectangleConstraint(width, this.widthRange, LengthConstraintType.FIXED, this.height, this.heightRange, this.heightConstraintType);
/*     */   }
/*     */   
/*     */   public RectangleConstraint toFixedHeight(double height) {
/* 279 */     return new RectangleConstraint(this.width, this.widthRange, this.widthConstraintType, height, this.heightRange, LengthConstraintType.FIXED);
/*     */   }
/*     */   
/*     */   public RectangleConstraint toRangeWidth(Range range) {
/* 294 */     if (range == null)
/* 295 */       throw new IllegalArgumentException("Null 'range' argument."); 
/* 297 */     return new RectangleConstraint(range.getUpperBound(), range, LengthConstraintType.RANGE, this.height, this.heightRange, this.heightConstraintType);
/*     */   }
/*     */   
/*     */   public RectangleConstraint toRangeHeight(Range range) {
/* 312 */     if (range == null)
/* 313 */       throw new IllegalArgumentException("Null 'range' argument."); 
/* 315 */     return new RectangleConstraint(this.width, this.widthRange, this.widthConstraintType, range.getUpperBound(), range, LengthConstraintType.RANGE);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 328 */     return "RectangleConstraint[" + this.widthConstraintType.toString() + ": width=" + this.width + ", height=" + this.height + "]";
/*     */   }
/*     */   
/*     */   public Size2D calculateConstrainedSize(Size2D base) {
/* 342 */     Size2D result = new Size2D();
/* 343 */     if (this.widthConstraintType == LengthConstraintType.NONE) {
/* 344 */       result.width = base.width;
/* 345 */       if (this.heightConstraintType == LengthConstraintType.NONE) {
/* 346 */         result.height = base.height;
/* 348 */       } else if (this.heightConstraintType == LengthConstraintType.RANGE) {
/* 349 */         result.height = this.heightRange.constrain(base.height);
/* 351 */       } else if (this.heightConstraintType == LengthConstraintType.FIXED) {
/* 352 */         result.height = this.height;
/*     */       } 
/* 355 */     } else if (this.widthConstraintType == LengthConstraintType.RANGE) {
/* 356 */       result.width = this.widthRange.constrain(base.width);
/* 357 */       if (this.heightConstraintType == LengthConstraintType.NONE) {
/* 358 */         result.height = base.height;
/* 360 */       } else if (this.heightConstraintType == LengthConstraintType.RANGE) {
/* 361 */         result.height = this.heightRange.constrain(base.height);
/* 363 */       } else if (this.heightConstraintType == LengthConstraintType.FIXED) {
/* 364 */         result.height = this.height;
/*     */       } 
/* 367 */     } else if (this.widthConstraintType == LengthConstraintType.FIXED) {
/* 368 */       result.width = this.width;
/* 369 */       if (this.heightConstraintType == LengthConstraintType.NONE) {
/* 370 */         result.height = base.height;
/* 372 */       } else if (this.heightConstraintType == LengthConstraintType.RANGE) {
/* 373 */         result.height = this.heightRange.constrain(base.height);
/* 375 */       } else if (this.heightConstraintType == LengthConstraintType.FIXED) {
/* 376 */         result.height = this.height;
/*     */       } 
/*     */     } 
/* 379 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\RectangleConstraint.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */