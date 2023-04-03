/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.text.TextBlockAnchor;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ 
/*     */ public class CategoryLabelPosition implements Serializable {
/*     */   private static final long serialVersionUID = 5168681143844183864L;
/*     */   
/*     */   private RectangleAnchor categoryAnchor;
/*     */   
/*     */   private TextBlockAnchor labelAnchor;
/*     */   
/*     */   private TextAnchor rotationAnchor;
/*     */   
/*     */   private double angle;
/*     */   
/*     */   private CategoryLabelWidthType widthType;
/*     */   
/*     */   private float widthRatio;
/*     */   
/*     */   public CategoryLabelPosition() {
/*  91 */     this(RectangleAnchor.CENTER, TextBlockAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 0.0D, CategoryLabelWidthType.CATEGORY, 0.95F);
/*     */   }
/*     */   
/*     */   public CategoryLabelPosition(RectangleAnchor categoryAnchor, TextBlockAnchor labelAnchor) {
/* 107 */     this(categoryAnchor, labelAnchor, TextAnchor.CENTER, 0.0D, CategoryLabelWidthType.CATEGORY, 0.95F);
/*     */   }
/*     */   
/*     */   public CategoryLabelPosition(RectangleAnchor categoryAnchor, TextBlockAnchor labelAnchor, CategoryLabelWidthType widthType, float widthRatio) {
/* 128 */     this(categoryAnchor, labelAnchor, TextAnchor.CENTER, 0.0D, widthType, widthRatio);
/*     */   }
/*     */   
/*     */   public CategoryLabelPosition(RectangleAnchor categoryAnchor, TextBlockAnchor labelAnchor, TextAnchor rotationAnchor, double angle, CategoryLabelWidthType widthType, float widthRatio) {
/* 157 */     if (categoryAnchor == null)
/* 158 */       throw new IllegalArgumentException("Null 'categoryAnchor' argument."); 
/* 162 */     if (labelAnchor == null)
/* 163 */       throw new IllegalArgumentException("Null 'labelAnchor' argument."); 
/* 167 */     if (rotationAnchor == null)
/* 168 */       throw new IllegalArgumentException("Null 'rotationAnchor' argument."); 
/* 172 */     if (widthType == null)
/* 173 */       throw new IllegalArgumentException("Null 'widthType' argument."); 
/* 176 */     this.categoryAnchor = categoryAnchor;
/* 177 */     this.labelAnchor = labelAnchor;
/* 178 */     this.rotationAnchor = rotationAnchor;
/* 179 */     this.angle = angle;
/* 180 */     this.widthType = widthType;
/* 181 */     this.widthRatio = widthRatio;
/*     */   }
/*     */   
/*     */   public RectangleAnchor getCategoryAnchor() {
/* 191 */     return this.categoryAnchor;
/*     */   }
/*     */   
/*     */   public TextBlockAnchor getLabelAnchor() {
/* 200 */     return this.labelAnchor;
/*     */   }
/*     */   
/*     */   public TextAnchor getRotationAnchor() {
/* 209 */     return this.rotationAnchor;
/*     */   }
/*     */   
/*     */   public double getAngle() {
/* 218 */     return this.angle;
/*     */   }
/*     */   
/*     */   public CategoryLabelWidthType getWidthType() {
/* 227 */     return this.widthType;
/*     */   }
/*     */   
/*     */   public float getWidthRatio() {
/* 236 */     return this.widthRatio;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 247 */     if (obj == this)
/* 248 */       return true; 
/* 250 */     if (!(obj instanceof CategoryLabelPosition))
/* 251 */       return false; 
/* 253 */     CategoryLabelPosition that = (CategoryLabelPosition)obj;
/* 254 */     if (!this.categoryAnchor.equals(that.categoryAnchor))
/* 255 */       return false; 
/* 257 */     if (!this.labelAnchor.equals(that.labelAnchor))
/* 258 */       return false; 
/* 260 */     if (!this.rotationAnchor.equals(that.rotationAnchor))
/* 261 */       return false; 
/* 263 */     if (this.angle != that.angle)
/* 264 */       return false; 
/* 266 */     if (this.widthType != that.widthType)
/* 267 */       return false; 
/* 269 */     if (this.widthRatio != that.widthRatio)
/* 270 */       return false; 
/* 272 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 281 */     int result = 19;
/* 282 */     result = 37 * result + this.categoryAnchor.hashCode();
/* 283 */     result = 37 * result + this.labelAnchor.hashCode();
/* 284 */     result = 37 * result + this.rotationAnchor.hashCode();
/* 285 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\CategoryLabelPosition.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */