/*     */ package org.geotools.filter;
/*     */ 
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public abstract class MathExpressionImpl extends DefaultExpression implements MathExpression {
/*  42 */   private Expression leftValue = null;
/*     */   
/*  45 */   private Expression rightValue = null;
/*     */   
/*     */   protected MathExpressionImpl() {}
/*     */   
/*     */   protected MathExpressionImpl(Expression e1, Expression e2) {
/*  56 */     this.leftValue = e1;
/*  57 */     this.rightValue = e2;
/*     */   }
/*     */   
/*     */   public final void addLeftValue(Expression leftValue) throws IllegalFilterException {
/*  72 */     setExpression1(leftValue);
/*     */   }
/*     */   
/*     */   public final void addRightValue(Expression rightValue) throws IllegalFilterException {
/*  87 */     setExpression2(rightValue);
/*     */   }
/*     */   
/*     */   public final Expression getLeftValue() {
/*  96 */     return (Expression)getExpression1();
/*     */   }
/*     */   
/*     */   public Expression getExpression1() {
/* 106 */     return this.leftValue;
/*     */   }
/*     */   
/*     */   public void setExpression1(Expression expression) {
/* 116 */     Expression leftValue = (Expression)expression;
/* 117 */     if (isGeometryExpression(leftValue.getType()))
/* 118 */       throw new IllegalFilterException("Attempted to add Geometry expression to math expression."); 
/* 121 */     this.leftValue = leftValue;
/*     */   }
/*     */   
/*     */   public final Expression getRightValue() {
/* 132 */     return (Expression)getExpression2();
/*     */   }
/*     */   
/*     */   public Expression getExpression2() {
/* 141 */     return this.rightValue;
/*     */   }
/*     */   
/*     */   public void setExpression2(Expression expression) {
/* 150 */     Expression rightValue = (Expression)expression;
/* 152 */     if (isGeometryExpression(rightValue.getType()))
/* 153 */       throw new IllegalFilterException("Attempted to add Geometry expression to math expression."); 
/* 156 */     this.rightValue = rightValue;
/*     */   }
/*     */   
/*     */   public short getType() {
/* 165 */     return this.expressionType;
/*     */   }
/*     */   
/*     */   protected void ensureOperandsSet() throws IllegalArgumentException {
/* 174 */     if (this.leftValue == null || this.rightValue == null)
/* 175 */       throw new IllegalArgumentException("Attempted read math expression with missing sub expressions."); 
/*     */   }
/*     */   
/*     */   protected Object number(double number) {
/* 183 */     return new Double(number);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\MathExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */