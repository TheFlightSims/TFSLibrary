/*     */ package org.geotools.filter.expression;
/*     */ 
/*     */ import org.geotools.filter.AttributeExpression;
/*     */ import org.geotools.filter.FilterVisitor;
/*     */ import org.geotools.filter.FunctionExpression;
/*     */ import org.geotools.filter.LiteralExpression;
/*     */ import org.geotools.filter.MathExpression;
/*     */ import org.opengis.filter.expression.Add;
/*     */ import org.opengis.filter.expression.BinaryExpression;
/*     */ import org.opengis.filter.expression.Divide;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.expression.Multiply;
/*     */ import org.opengis.filter.expression.NilExpression;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.expression.Subtract;
/*     */ 
/*     */ public class FilterVisitorExpressionWrapper implements ExpressionVisitor {
/*     */   FilterVisitor delegate;
/*     */   
/*     */   public FilterVisitorExpressionWrapper(FilterVisitor delegate) {
/*  49 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */   private Object visitMath(BinaryExpression expression, Object data) {
/*  53 */     if (expression instanceof MathExpression)
/*  54 */       this.delegate.visit((MathExpression)expression); 
/*  57 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(NilExpression expression, Object extraData) {
/*  61 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Add expression, Object extraData) {
/*  64 */     return visitMath((BinaryExpression)expression, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Divide expression, Object extraData) {
/*  68 */     return visitMath((BinaryExpression)expression, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Multiply expression, Object extraData) {
/*  72 */     return visitMath((BinaryExpression)expression, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Subtract expression, Object extraData) {
/*  76 */     return visitMath((BinaryExpression)expression, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Function expression, Object extraData) {
/*  80 */     if (expression instanceof FunctionExpression)
/*  81 */       this.delegate.visit((FunctionExpression)expression); 
/*  84 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(Literal expression, Object extraData) {
/*  88 */     if (expression instanceof LiteralExpression)
/*  89 */       this.delegate.visit((LiteralExpression)expression); 
/*  92 */     return extraData;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyName expression, Object extraData) {
/*  96 */     if (expression instanceof AttributeExpression)
/*  97 */       this.delegate.visit((AttributeExpression)expression); 
/* 100 */     return extraData;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\FilterVisitorExpressionWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */