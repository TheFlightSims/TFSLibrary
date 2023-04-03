/*    */ package org.opengis.filter.expression;
/*    */ 
/*    */ import java.io.ObjectStreamException;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public final class NilExpression implements Expression, Serializable {
/*    */   private static final long serialVersionUID = 4999313240542653655L;
/*    */   
/*    */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 44 */     return visitor.visit(this, extraData);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object object) {
/* 51 */     return null;
/*    */   }
/*    */   
/*    */   public <T> T evaluate(Object object, Class<T> context) {
/* 58 */     return null;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 66 */     return "Expression.NIL";
/*    */   }
/*    */   
/*    */   private Object readResolve() throws ObjectStreamException {
/* 73 */     return Expression.NIL;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\expression\NilExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */