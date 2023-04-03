/*    */ package org.geotools.filter.temporal;
/*    */ 
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.temporal.Begins;
/*    */ import org.opengis.temporal.RelativePosition;
/*    */ 
/*    */ public class BeginsImpl extends BinaryTemporalOperatorImpl implements Begins {
/*    */   public BeginsImpl(Expression e1, Expression e2) {
/* 33 */     super(e1, e2);
/*    */   }
/*    */   
/*    */   public BeginsImpl(Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 37 */     super(e1, e2, matchAction);
/*    */   }
/*    */   
/*    */   protected boolean doEvaluate(RelativePosition pos) {
/* 42 */     return (pos == RelativePosition.BEGINS);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 46 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\temporal\BeginsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */