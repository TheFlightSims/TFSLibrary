/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.filter.expression.ExpressionVisitor;
/*    */ import org.opengis.filter.expression.Literal;
/*    */ 
/*    */ public class MapScaleDenominatorImpl extends DefaultExpression implements MapScaleDenominator, Literal {
/*    */   public Object evaluate(Object f) {
/* 42 */     return getValue();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 46 */     return new Double(1.0D);
/*    */   }
/*    */   
/*    */   public void setValue(Object constant) {
/* 50 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 54 */     return visitor.visit(this, extraData);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 58 */     return "sld:MapScaleDenominator";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\MapScaleDenominatorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */