/*    */ package org.geotools.filter.visitor;
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
/*    */ public class NullExpressionVisitor implements ExpressionVisitor {
/*    */   public Object visit(NilExpression expression, Object extraData) {
/* 41 */     return null;
/*    */   }
/*    */   
/*    */   public Object visit(Add expression, Object extraData) {
/* 45 */     return null;
/*    */   }
/*    */   
/*    */   public Object visit(Divide expression, Object extraData) {
/* 49 */     return null;
/*    */   }
/*    */   
/*    */   public Object visit(Function expression, Object extraData) {
/* 53 */     return null;
/*    */   }
/*    */   
/*    */   public Object visit(Literal expression, Object extraData) {
/* 57 */     return null;
/*    */   }
/*    */   
/*    */   public Object visit(Multiply expression, Object extraData) {
/* 61 */     return null;
/*    */   }
/*    */   
/*    */   public Object visit(PropertyName expression, Object extraData) {
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   public Object visit(Subtract expression, Object extraData) {
/* 69 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\NullExpressionVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */