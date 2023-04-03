/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public class IsGreaterThanOrEqualToImpl extends MultiCompareFilterImpl implements PropertyIsGreaterThanOrEqualTo {
/*    */   protected IsGreaterThanOrEqualToImpl(FilterFactory factory) {
/* 33 */     this(factory, (Expression)null, (Expression)null);
/*    */   }
/*    */   
/*    */   protected IsGreaterThanOrEqualToImpl(FilterFactory factory, Expression expression1, Expression expression2) {
/* 37 */     this(factory, expression1, expression2, false);
/*    */   }
/*    */   
/*    */   public IsGreaterThanOrEqualToImpl(FilterFactory factory, Expression expr1, Expression expr2, boolean matchCase) {
/* 42 */     super(factory, expr1, expr2, matchCase);
/* 45 */     this.filterType = 18;
/*    */   }
/*    */   
/*    */   protected IsGreaterThanOrEqualToImpl(FilterFactory factory, Expression expression1, Expression expression2, MultiValuedFilter.MatchAction matchAction) {
/* 50 */     this(factory, expression1, expression2, false, matchAction);
/*    */   }
/*    */   
/*    */   public IsGreaterThanOrEqualToImpl(FilterFactory factory, Expression expr1, Expression expr2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/* 55 */     super(factory, expr1, expr2, matchCase, matchAction);
/* 58 */     this.filterType = 18;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Object v1, Object v2) {
/* 63 */     Object[] values = eval(v1, v2);
/* 64 */     Comparable value1 = comparable(values[0]);
/* 65 */     Comparable value2 = comparable(values[1]);
/* 67 */     return (value1 != null && value2 != null && compare(value1, value2) >= 0);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 71 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\IsGreaterThanOrEqualToImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */