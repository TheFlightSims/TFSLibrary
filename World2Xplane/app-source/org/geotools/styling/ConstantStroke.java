/*     */ package org.geotools.styling;
/*     */ 
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Graphic;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ abstract class ConstantStroke implements Stroke {
/*     */   private void cannotModifyConstant() {
/* 336 */     throw new UnsupportedOperationException("Constant Stroke may not be modified");
/*     */   }
/*     */   
/*     */   public void setColor(Expression color) {
/* 340 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setWidth(Expression width) {
/* 344 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setOpacity(Expression opacity) {
/* 349 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setLineJoin(Expression lineJoin) {
/* 353 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setLineCap(Expression lineCap) {
/* 357 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setDashArray(float[] dashArray) {
/* 361 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setDashOffset(Expression dashOffset) {
/* 365 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setGraphicFill(Graphic graphicFill) {
/* 369 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setGraphicStroke(Graphic graphicStroke) {
/* 373 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 377 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 381 */     cannotModifyConstant();
/* 382 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ConstantStroke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */