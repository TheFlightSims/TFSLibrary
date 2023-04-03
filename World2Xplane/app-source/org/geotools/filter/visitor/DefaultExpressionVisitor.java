/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import org.opengis.filter.expression.Add;
/*     */ import org.opengis.filter.expression.Divide;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.expression.Multiply;
/*     */ import org.opengis.filter.expression.NilExpression;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.expression.Subtract;
/*     */ 
/*     */ public abstract class DefaultExpressionVisitor implements ExpressionVisitor {
/*     */   public Object visit(NilExpression expression, Object data) {
/*  62 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Add expression, Object data) {
/*  66 */     data = expression.getExpression1().accept(this, data);
/*  67 */     data = expression.getExpression2().accept(this, data);
/*  68 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Divide expression, Object data) {
/*  72 */     data = expression.getExpression1().accept(this, data);
/*  73 */     data = expression.getExpression2().accept(this, data);
/*  74 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Function expression, Object data) {
/*  78 */     if (expression.getParameters() != null)
/*  79 */       for (Expression parameter : expression.getParameters())
/*  80 */         data = parameter.accept(this, data);  
/*  83 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Literal expression, Object data) {
/*  87 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Multiply expression, Object data) {
/*  91 */     data = expression.getExpression1().accept(this, data);
/*  92 */     data = expression.getExpression2().accept(this, data);
/*  93 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyName expression, Object data) {
/*  97 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Subtract expression, Object data) {
/* 101 */     data = expression.getExpression1().accept(this, data);
/* 102 */     data = expression.getExpression2().accept(this, data);
/* 103 */     return data;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\DefaultExpressionVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */