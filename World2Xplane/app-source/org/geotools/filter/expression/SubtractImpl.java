/*    */ package org.geotools.filter.expression;
/*    */ 
/*    */ import org.geotools.filter.Filters;
/*    */ import org.geotools.filter.MathExpressionImpl;
/*    */ import org.geotools.util.Utilities;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.expression.ExpressionVisitor;
/*    */ import org.opengis.filter.expression.Subtract;
/*    */ 
/*    */ public class SubtractImpl extends MathExpressionImpl implements Subtract {
/*    */   public SubtractImpl(Expression expr1, Expression expr2) {
/* 39 */     super(expr1, expr2);
/* 42 */     this.expressionType = 106;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) throws IllegalArgumentException {
/* 46 */     ensureOperandsSet();
/* 48 */     double leftDouble = Filters.number(getExpression1().evaluate(feature));
/* 49 */     double rightDouble = Filters.number(getExpression2().evaluate(feature));
/* 51 */     return number(leftDouble - rightDouble);
/*    */   }
/*    */   
/*    */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 55 */     return visitor.visit(this, extraData);
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 67 */     if (obj instanceof SubtractImpl) {
/* 68 */       SubtractImpl other = (SubtractImpl)obj;
/* 70 */       return (Utilities.equals(getExpression1(), other.getExpression1()) && Utilities.equals(getExpression2(), other.getExpression2()));
/*    */     } 
/* 73 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 82 */     int result = 23;
/* 84 */     result = 37 * result + getExpression1().hashCode();
/* 85 */     result = 37 * result + getExpression2().hashCode();
/* 87 */     return result;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 91 */     return "(" + getExpression1().toString() + "-" + getExpression2().toString() + ")";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\SubtractImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */