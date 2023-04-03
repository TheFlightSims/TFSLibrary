/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.geotools.filter.ConstantExpression;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.AnchorPoint;
/*     */ import org.opengis.style.Displacement;
/*     */ import org.opengis.style.GraphicalSymbol;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ abstract class ConstantGraphic implements Graphic {
/*     */   private void cannotModifyConstant() {
/* 386 */     throw new UnsupportedOperationException("Constant Graphic may not be modified");
/*     */   }
/*     */   
/*     */   public void setDisplacement(Displacement offset) {
/* 390 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setExternalGraphics(ExternalGraphic[] externalGraphics) {
/* 394 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void addExternalGraphic(ExternalGraphic externalGraphic) {
/* 398 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setMarks(Mark[] marks) {
/* 402 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void addMark(Mark mark) {
/* 406 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setGap(Expression gap) {
/* 410 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setInitialGap(Expression initialGap) {
/* 414 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setSymbols(Symbol[] symbols) {
/* 418 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void addSymbol(Symbol symbol) {
/* 422 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setOpacity(Expression opacity) {
/* 426 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setSize(Expression size) {
/* 430 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setRotation(Expression rotation) {
/* 434 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public void setAnchorPoint(AnchorPoint anchor) {
/* 438 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 442 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 446 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public List<GraphicalSymbol> graphicalSymbols() {
/* 450 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public AnchorPoint getAnchorPoint() {
/* 454 */     return AnchorPoint.DEFAULT;
/*     */   }
/*     */   
/*     */   public void setAnchorPoint(AnchorPoint anchorPoint) {
/* 458 */     cannotModifyConstant();
/*     */   }
/*     */   
/*     */   public Expression getGap() {
/* 462 */     return (Expression)ConstantExpression.constant(0);
/*     */   }
/*     */   
/*     */   public Expression getInitialGap() {
/* 466 */     return (Expression)ConstantExpression.constant(0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ConstantGraphic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */