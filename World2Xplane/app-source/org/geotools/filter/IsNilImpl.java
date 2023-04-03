/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.PropertyIsNil;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public class IsNilImpl extends CompareFilterImpl implements PropertyIsNil {
/*    */   Object nilReason;
/*    */   
/*    */   IsNullImpl delegate;
/*    */   
/*    */   public IsNilImpl(FilterFactory factory, Expression e1, Object nilReason) {
/* 37 */     super(factory, e1, (Expression)null);
/* 38 */     this.nilReason = nilReason;
/* 39 */     this.delegate = new IsNullImpl(factory, e1);
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 43 */     return this.delegate.evaluate(object);
/*    */   }
/*    */   
/*    */   public Expression getExpression() {
/* 55 */     return getExpression1();
/*    */   }
/*    */   
/*    */   public Object getNilReason() {
/* 59 */     return this.nilReason;
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 64 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\IsNilImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */