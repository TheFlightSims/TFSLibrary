/*    */ package org.geotools.filter.temporal;
/*    */ 
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.temporal.EndedBy;
/*    */ import org.opengis.temporal.RelativePosition;
/*    */ 
/*    */ public class EndedByImpl extends BinaryTemporalOperatorImpl implements EndedBy {
/*    */   public EndedByImpl(Expression e1, Expression e2) {
/* 25 */     super(e1, e2);
/*    */   }
/*    */   
/*    */   public EndedByImpl(Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 29 */     super(e1, e2, matchAction);
/*    */   }
/*    */   
/*    */   protected boolean doEvaluate(RelativePosition pos) {
/* 34 */     return (pos == RelativePosition.ENDED_BY);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 38 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\temporal\EndedByImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */