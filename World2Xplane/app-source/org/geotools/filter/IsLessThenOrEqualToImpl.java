/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public class IsLessThenOrEqualToImpl extends MultiCompareFilterImpl implements PropertyIsLessThanOrEqualTo {
/*    */   protected IsLessThenOrEqualToImpl(FilterFactory factory) {
/* 32 */     this(factory, (Expression)null, (Expression)null);
/*    */   }
/*    */   
/*    */   protected IsLessThenOrEqualToImpl(FilterFactory factory, Expression expression1, Expression expression2) {
/* 36 */     this(factory, expression1, expression2, false);
/*    */   }
/*    */   
/*    */   protected IsLessThenOrEqualToImpl(FilterFactory factory, Expression expression1, Expression expression2, boolean matchCase) {
/* 40 */     super(factory, expression1, expression2, matchCase);
/* 43 */     this.filterType = 17;
/*    */   }
/*    */   
/*    */   protected IsLessThenOrEqualToImpl(FilterFactory factory, Expression expression1, Expression expression2, MultiValuedFilter.MatchAction matchAction) {
/* 47 */     this(factory, expression1, expression2, false, matchAction);
/*    */   }
/*    */   
/*    */   protected IsLessThenOrEqualToImpl(FilterFactory factory, Expression expression1, Expression expression2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/* 51 */     super(factory, expression1, expression2, matchCase, matchAction);
/* 54 */     this.filterType = 17;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Object v1, Object v2) {
/* 59 */     Object[] values = eval(v1, v2);
/* 60 */     Comparable value1 = comparable(values[0]);
/* 61 */     Comparable value2 = comparable(values[1]);
/* 63 */     return (value1 != null && value2 != null && compare(value1, value2) <= 0);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 67 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\IsLessThenOrEqualToImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */