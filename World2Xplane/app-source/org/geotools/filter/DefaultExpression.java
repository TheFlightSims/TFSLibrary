/*     */ package org.geotools.filter;
/*     */ 
/*     */ import org.geotools.filter.expression.ExpressionAbstract;
/*     */ import org.geotools.filter.expression.FilterVisitorExpressionWrapper;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ 
/*     */ public abstract class DefaultExpression extends ExpressionAbstract implements Expression {
/*     */   protected short expressionType;
/*     */   
/*     */   protected boolean permissiveConstruction;
/*     */   
/*     */   public short getType() {
/*  53 */     return this.expressionType;
/*     */   }
/*     */   
/*     */   public final Object getValue(SimpleFeature feature) {
/*  62 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   public Object evaluate(SimpleFeature feature) {
/*  78 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object object) {
/*  90 */     return new Object();
/*     */   }
/*     */   
/*     */   public final void accept(FilterVisitor visitor) {
/*  98 */     accept((ExpressionVisitor)new FilterVisitorExpressionWrapper(visitor), null);
/*     */   }
/*     */   
/*     */   protected static boolean isAttributeExpression(short expressionType) {
/* 118 */     return (expressionType == 109 || expressionType == 110 || expressionType == 111);
/*     */   }
/*     */   
/*     */   protected static boolean isMathExpression(short expressionType) {
/* 132 */     return (expressionType == 105 || expressionType == 106 || expressionType == 107 || expressionType == 108);
/*     */   }
/*     */   
/*     */   protected static boolean isMathExpression(Expression expression) {
/* 145 */     return (expression instanceof org.opengis.filter.expression.Add || expression instanceof org.opengis.filter.expression.Subtract || expression instanceof org.opengis.filter.expression.Multiply || expression instanceof org.opengis.filter.expression.Divide);
/*     */   }
/*     */   
/*     */   protected static boolean isLiteralExpression(short expressionType) {
/* 159 */     return (expressionType == 104 || expressionType == 101 || expressionType == 102 || expressionType == 103);
/*     */   }
/*     */   
/*     */   protected static boolean isGeometryExpression(short expressionType) {
/* 173 */     return (expressionType == 112 || expressionType == 104);
/*     */   }
/*     */   
/*     */   protected static boolean isExpression(short expressionType) {
/* 185 */     return (isMathExpression(expressionType) || isAttributeExpression(expressionType) || isLiteralExpression(expressionType) || isFunctionExpression(expressionType));
/*     */   }
/*     */   
/*     */   public static boolean isFunctionExpression(short expressionType) {
/* 192 */     return (expressionType == 114);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\DefaultExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */