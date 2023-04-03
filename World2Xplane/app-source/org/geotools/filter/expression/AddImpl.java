/*    */ package org.geotools.filter.expression;
/*    */ 
/*    */ import org.geotools.filter.Filters;
/*    */ import org.geotools.filter.MathExpressionImpl;
/*    */ import org.geotools.util.Utilities;
/*    */ import org.opengis.filter.expression.Add;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.expression.ExpressionVisitor;
/*    */ 
/*    */ public class AddImpl extends MathExpressionImpl implements Add {
/*    */   public AddImpl(Expression expr1, Expression expr2) {
/* 39 */     super(expr1, expr2);
/* 41 */     this.expressionType = 105;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) throws IllegalArgumentException {
/* 45 */     ensureOperandsSet();
/* 47 */     double leftDouble = Filters.number(getExpression1().evaluate(feature));
/* 48 */     double rightDouble = Filters.number(getExpression2().evaluate(feature));
/* 50 */     return number(leftDouble + rightDouble);
/*    */   }
/*    */   
/*    */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 54 */     return visitor.visit(this, extraData);
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 66 */     if (obj instanceof AddImpl) {
/* 67 */       AddImpl other = (AddImpl)obj;
/* 69 */       return (Utilities.equals(getExpression1(), other.getExpression1()) && Utilities.equals(getExpression2(), other.getExpression2()));
/*    */     } 
/* 72 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 81 */     int result = 23;
/* 83 */     result = 37 * result + getExpression1().hashCode();
/* 84 */     result = 37 * result + getExpression2().hashCode();
/* 86 */     return result;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 90 */     return "(" + getExpression1().toString() + "+" + getExpression2().toString() + ")";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\AddImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */