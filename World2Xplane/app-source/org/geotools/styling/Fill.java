/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import org.geotools.filter.ConstantExpression;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Fill;
/*     */ import org.opengis.style.Graphic;
/*     */ import org.opengis.style.GraphicFill;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ public interface Fill extends Fill {
/*  83 */   public static final Fill DEFAULT = new ConstantFill() {
/*     */       private void cannotModifyConstant() {
/*  85 */         throw new UnsupportedOperationException("Constant Stroke may not be modified");
/*     */       }
/*     */       
/*  88 */       final Expression COLOR = (Expression)ConstantExpression.color(new Color(128, 128, 128));
/*     */       
/*  89 */       final Expression BGCOLOR = (Expression)ConstantExpression.color(new Color(255, 255, 255, 0));
/*     */       
/*  90 */       final Expression OPACITY = (Expression)ConstantExpression.ONE;
/*     */       
/*     */       public Expression getColor() {
/*  93 */         return this.COLOR;
/*     */       }
/*     */       
/*     */       public Expression getBackgroundColor() {
/*  97 */         return this.BGCOLOR;
/*     */       }
/*     */       
/*     */       public Expression getOpacity() {
/* 101 */         return this.OPACITY;
/*     */       }
/*     */       
/*     */       public Graphic getGraphicFill() {
/* 105 */         return Graphic.NULL;
/*     */       }
/*     */       
/*     */       public Object accept(StyleVisitor visitor, Object extraData) {
/* 109 */         cannotModifyConstant();
/* 110 */         return null;
/*     */       }
/*     */     };
/*     */   
/* 114 */   public static final Fill NULL = new ConstantFill() {
/*     */       private void cannotModifyConstant() {
/* 116 */         throw new UnsupportedOperationException("Constant Stroke may not be modified");
/*     */       }
/*     */       
/*     */       public Expression getColor() {
/* 120 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */       
/*     */       public Expression getBackgroundColor() {
/* 124 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */       
/*     */       public Expression getOpacity() {
/* 128 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */       
/*     */       public Graphic getGraphicFill() {
/* 132 */         return Graphic.NULL;
/*     */       }
/*     */       
/*     */       public Object accept(StyleVisitor visitor, Object extraData) {
/* 136 */         cannotModifyConstant();
/* 137 */         return null;
/*     */       }
/*     */     };
/*     */   
/*     */   Expression getColor();
/*     */   
/*     */   void setColor(Expression paramExpression);
/*     */   
/*     */   Expression getBackgroundColor();
/*     */   
/*     */   void setBackgroundColor(Expression paramExpression);
/*     */   
/*     */   Expression getOpacity();
/*     */   
/*     */   void setOpacity(Expression paramExpression);
/*     */   
/*     */   Graphic getGraphicFill();
/*     */   
/*     */   void setGraphicFill(Graphic paramGraphic);
/*     */   
/*     */   void accept(StyleVisitor paramStyleVisitor);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Fill.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */