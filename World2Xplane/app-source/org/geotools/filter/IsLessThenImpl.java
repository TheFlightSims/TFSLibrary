/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.PropertyIsLessThan;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public class IsLessThenImpl extends MultiCompareFilterImpl implements PropertyIsLessThan {
/*    */   protected IsLessThenImpl(FilterFactory factory) {
/* 33 */     this(factory, (Expression)null, (Expression)null);
/*    */   }
/*    */   
/*    */   protected IsLessThenImpl(FilterFactory factory, Expression expression1, Expression expression2) {
/* 37 */     this(factory, expression1, expression2, false);
/*    */   }
/*    */   
/*    */   protected IsLessThenImpl(FilterFactory factory, Expression expression1, Expression expression2, boolean matchCase) {
/* 41 */     super(factory, expression1, expression2, matchCase);
/* 44 */     this.filterType = 15;
/*    */   }
/*    */   
/*    */   protected IsLessThenImpl(FilterFactory factory, Expression expression1, Expression expression2, MultiValuedFilter.MatchAction matchAction) {
/* 48 */     this(factory, expression1, expression2, false, matchAction);
/*    */   }
/*    */   
/*    */   protected IsLessThenImpl(FilterFactory factory, Expression expression1, Expression expression2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/* 52 */     super(factory, expression1, expression2, matchCase, matchAction);
/* 55 */     this.filterType = 15;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Object v1, Object v2) {
/* 60 */     Object[] values = eval(v1, v2);
/* 61 */     Comparable value1 = comparable(values[0]);
/* 62 */     Comparable value2 = comparable(values[1]);
/* 64 */     return (value1 != null && value2 != null && compare(value1, value2) < 0);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 68 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\IsLessThenImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */