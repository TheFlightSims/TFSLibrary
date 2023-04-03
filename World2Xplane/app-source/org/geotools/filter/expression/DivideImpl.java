/*    */ package org.geotools.filter.expression;
/*    */ 
/*    */ import org.geotools.filter.Filters;
/*    */ import org.geotools.filter.MathExpressionImpl;
/*    */ import org.geotools.util.Utilities;
/*    */ import org.opengis.filter.expression.Divide;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.expression.ExpressionVisitor;
/*    */ 
/*    */ public class DivideImpl extends MathExpressionImpl implements Divide {
/*    */   public DivideImpl(Expression expr1, Expression expr2) {
/* 39 */     super(expr1, expr2);
/* 42 */     this.expressionType = 108;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) throws IllegalArgumentException {
/* 46 */     ensureOperandsSet();
/* 48 */     double leftDouble = Filters.number(getExpression1().evaluate(feature));
/* 49 */     double rightDouble = Filters.number(getExpression2().evaluate(feature));
/* 51 */     return number(leftDouble / rightDouble);
/*    */   }
/*    */   
/*    */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 55 */     return visitor.visit(this, extraData);
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 67 */     if (obj instanceof DivideImpl) {
/* 68 */       DivideImpl other = (DivideImpl)obj;
/* 70 */       return (Utilities.equals(getExpression1(), other.getExpression1()) && Utilities.equals(getExpression2(), other.getExpression2()));
/*    */     } 
/* 73 */     return false;
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
/* 92 */     return "(" + getExpression1().toString() + "/" + getExpression2().toString() + ")";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\DivideImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */