/*    */ package org.geotools.filter.temporal;
/*    */ 
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.temporal.OverlappedBy;
/*    */ import org.opengis.temporal.RelativePosition;
/*    */ 
/*    */ public class OverlappedByImpl extends BinaryTemporalOperatorImpl implements OverlappedBy {
/*    */   public OverlappedByImpl(Expression e1, Expression e2) {
/* 25 */     super(e1, e2);
/*    */   }
/*    */   
/*    */   public OverlappedByImpl(Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 29 */     super(e1, e2, matchAction);
/*    */   }
/*    */   
/*    */   protected boolean doEvaluate(RelativePosition pos) {
/* 34 */     return (pos == RelativePosition.OVERLAPPED_BY);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 38 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\temporal\OverlappedByImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */