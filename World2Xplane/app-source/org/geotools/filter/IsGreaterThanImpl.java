/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.PropertyIsGreaterThan;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public class IsGreaterThanImpl extends MultiCompareFilterImpl implements PropertyIsGreaterThan {
/*    */   protected IsGreaterThanImpl(FilterFactory factory) {
/* 32 */     this(factory, (Expression)null, (Expression)null);
/*    */   }
/*    */   
/*    */   protected IsGreaterThanImpl(FilterFactory factory, Expression expression1, Expression expression2) {
/* 36 */     super(factory, expression1, expression2);
/* 39 */     this.filterType = 16;
/*    */   }
/*    */   
/*    */   protected IsGreaterThanImpl(FilterFactory factory, Expression expression1, Expression expression2, MultiValuedFilter.MatchAction matchAction) {
/* 44 */     super(factory, expression1, expression2, matchAction);
/* 47 */     this.filterType = 16;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Object v1, Object v2) {
/* 52 */     Object[] values = eval(v1, v2);
/* 53 */     Comparable value1 = comparable(values[0]);
/* 54 */     Comparable value2 = comparable(values[1]);
/* 56 */     return (value1 != null && value2 != null && compare(value1, value2) > 0);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 60 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\IsGreaterThanImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */