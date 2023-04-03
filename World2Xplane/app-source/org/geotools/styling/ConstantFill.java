/*     */ package org.geotools.styling;
/*     */ 
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Graphic;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ abstract class ConstantFill implements Fill {
/*     */   private void cannotModifyConstant() {
/* 240 */     throw new UnsupportedOperationException("Constant Fill may not be modified");
/*     */   }
/*     */   
/*     */   public void setColor(Expression color) {
/* 244 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setBackgroundColor(Expression backgroundColor) {
/* 248 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setOpacity(Expression opacity) {
/* 252 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setGraphicFill(Graphic graphicFill) {
/* 256 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 260 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 264 */     cannotModifyConstant();
/* 265 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ConstantFill.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */