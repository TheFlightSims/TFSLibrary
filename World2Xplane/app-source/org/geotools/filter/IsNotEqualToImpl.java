/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.PropertyIsNotEqualTo;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public class IsNotEqualToImpl extends MultiCompareFilterImpl implements PropertyIsNotEqualTo {
/*    */   IsEqualsToImpl delegate;
/*    */   
/*    */   protected IsNotEqualToImpl(FilterFactory factory) {
/* 34 */     this(factory, null, null);
/*    */   }
/*    */   
/*    */   protected IsNotEqualToImpl(FilterFactory factory, Expression e1, Expression e2) {
/* 38 */     this(factory, e1, e2, true);
/*    */   }
/*    */   
/*    */   protected IsNotEqualToImpl(FilterFactory factory, MultiValuedFilter.MatchAction matchAction) {
/* 42 */     this(factory, (Expression)null, (Expression)null, matchAction);
/*    */   }
/*    */   
/*    */   protected IsNotEqualToImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 46 */     this(factory, e1, e2, true, matchAction);
/*    */   }
/*    */   
/*    */   protected IsNotEqualToImpl(FilterFactory factory, Expression expression1, Expression expression2, boolean matchCase) {
/* 50 */     this(factory, expression1, expression2, matchCase, MultiValuedFilter.MatchAction.ANY);
/*    */   }
/*    */   
/*    */   protected IsNotEqualToImpl(FilterFactory factory, Expression expression1, Expression expression2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/* 54 */     super(factory, expression1, expression2, matchCase, matchAction);
/* 55 */     this.delegate = new IsEqualsToImpl(factory, expression1, expression2, matchCase);
/* 58 */     this.filterType = 23;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Object v1, Object v2) {
/* 63 */     return !this.delegate.evaluateInternal(v1, v2);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 67 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\IsNotEqualToImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */