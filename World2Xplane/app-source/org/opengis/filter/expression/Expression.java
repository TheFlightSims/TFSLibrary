/*    */ package org.opengis.filter.expression;
/*    */ 
/*    */ import org.opengis.annotation.XmlElement;
/*    */ 
/*    */ @XmlElement("expression")
/*    */ public interface Expression {
/* 41 */   public static final Expression NIL = new NilExpression();
/*    */   
/*    */   Object evaluate(Object paramObject);
/*    */   
/*    */   <T> T evaluate(Object paramObject, Class<T> paramClass);
/*    */   
/*    */   Object accept(ExpressionVisitor paramExpressionVisitor, Object paramObject);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\expression\Expression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */