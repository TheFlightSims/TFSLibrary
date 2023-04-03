/*     */ package org.geotools.styling;
/*     */ 
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ abstract class ConstantDisplacement implements Displacement {
/*     */   private void cannotModifyConstant() {
/* 109 */     throw new UnsupportedOperationException("Constant Displacement may not be modified");
/*     */   }
/*     */   
/*     */   public void setDisplacementX(Expression x) {
/* 113 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setDisplacementY(Expression y) {
/* 117 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 121 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 125 */     cannotModifyConstant();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ConstantDisplacement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */