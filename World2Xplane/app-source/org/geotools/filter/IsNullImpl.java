/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public class IsNullImpl extends CompareFilterImpl implements NullFilter {
/*    */   public IsNullImpl(FilterFactory factory, Expression expression) {
/* 31 */     super(factory, expression, (Expression)null);
/* 33 */     this.filterType = 21;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object feature) {
/* 37 */     Expression expr = getExpression();
/* 38 */     Object value = eval(expr, feature);
/* 39 */     return (value == null);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 43 */     return visitor.visit(this, extraData);
/*    */   }
/*    */   
/*    */   public void nullCheckValue(Expression nullCheck) throws IllegalFilterException {
/* 48 */     setExpression(nullCheck);
/*    */   }
/*    */   
/*    */   public Expression getNullCheckValue() {
/* 52 */     return (Expression)getExpression();
/*    */   }
/*    */   
/*    */   public Expression getExpression() {
/* 56 */     return getExpression1();
/*    */   }
/*    */   
/*    */   public void setExpression(Expression expression) {
/* 60 */     setExpression1(expression);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\IsNullImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */