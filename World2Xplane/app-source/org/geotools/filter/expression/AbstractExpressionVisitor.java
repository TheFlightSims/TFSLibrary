/*    */ package org.geotools.filter.expression;
/*    */ 
/*    */ import org.opengis.filter.expression.Add;
/*    */ import org.opengis.filter.expression.Divide;
/*    */ import org.opengis.filter.expression.ExpressionVisitor;
/*    */ import org.opengis.filter.expression.Function;
/*    */ import org.opengis.filter.expression.Literal;
/*    */ import org.opengis.filter.expression.Multiply;
/*    */ import org.opengis.filter.expression.NilExpression;
/*    */ import org.opengis.filter.expression.PropertyName;
/*    */ import org.opengis.filter.expression.Subtract;
/*    */ 
/*    */ public class AbstractExpressionVisitor implements ExpressionVisitor {
/*    */   public Object visit(NilExpression expr, Object extraData) {
/* 42 */     return expr;
/*    */   }
/*    */   
/*    */   public Object visit(Add expr, Object extraData) {
/* 46 */     return expr;
/*    */   }
/*    */   
/*    */   public Object visit(Divide expr, Object extraData) {
/* 50 */     return expr;
/*    */   }
/*    */   
/*    */   public Object visit(Function expr, Object extraData) {
/* 54 */     return expr;
/*    */   }
/*    */   
/*    */   public Object visit(Literal expr, Object extraData) {
/* 58 */     return expr;
/*    */   }
/*    */   
/*    */   public Object visit(Multiply expr, Object extraData) {
/* 62 */     return expr;
/*    */   }
/*    */   
/*    */   public Object visit(PropertyName expr, Object extraData) {
/* 66 */     return expr;
/*    */   }
/*    */   
/*    */   public Object visit(Subtract expr, Object extraData) {
/* 70 */     return expr;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\AbstractExpressionVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */