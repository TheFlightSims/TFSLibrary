/*    */ package org.geotools.filter.expression;
/*    */ 
/*    */ import org.geotools.filter.Filters;
/*    */ import org.geotools.filter.MathExpressionImpl;
/*    */ import org.geotools.util.Utilities;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.expression.ExpressionVisitor;
/*    */ import org.opengis.filter.expression.Multiply;
/*    */ 
/*    */ public class MultiplyImpl extends MathExpressionImpl implements Multiply {
/*    */   public MultiplyImpl(Expression expr1, Expression expr2) {
/* 40 */     super(expr1, expr2);
/* 43 */     this.expressionType = 107;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) throws IllegalArgumentException {
/* 47 */     ensureOperandsSet();
/* 49 */     double leftDouble = Filters.number(getExpression1().evaluate(feature));
/* 50 */     double rightDouble = Filters.number(getExpression2().evaluate(feature));
/* 52 */     return number(leftDouble * rightDouble);
/*    */   }
/*    */   
/*    */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 56 */     return visitor.visit(this, extraData);
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 68 */     if (obj instanceof MultiplyImpl) {
/* 69 */       MultiplyImpl other = (MultiplyImpl)obj;
/* 71 */       return (Utilities.equals(getExpression1(), other.getExpression1()) && Utilities.equals(getExpression2(), other.getExpression2()));
/*    */     } 
/* 74 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 83 */     int result = 23;
/* 85 */     result = 37 * result + getExpression1().hashCode();
/* 86 */     result = 37 * result + getExpression2().hashCode();
/* 88 */     return result;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 92 */     return "(" + getExpression1().toString() + "*" + getExpression2().toString() + ")";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\MultiplyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */