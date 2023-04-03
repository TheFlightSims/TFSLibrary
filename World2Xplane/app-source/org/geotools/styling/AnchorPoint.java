/*    */ package org.geotools.styling;
/*    */ 
/*    */ import org.geotools.filter.ConstantExpression;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.style.AnchorPoint;
/*    */ import org.opengis.style.StyleVisitor;
/*    */ 
/*    */ public interface AnchorPoint extends AnchorPoint {
/* 42 */   public static final AnchorPoint DEFAULT = new AnchorPoint() {
/*    */       private void cannotModifyConstant() {
/* 44 */         throw new UnsupportedOperationException("Constant Stroke may not be modified");
/*    */       }
/*    */       
/*    */       public void setAnchorPointX(Expression x) {
/* 48 */         cannotModifyConstant();
/*    */       }
/*    */       
/*    */       public void setAnchorPointY(Expression y) {
/* 52 */         cannotModifyConstant();
/*    */       }
/*    */       
/*    */       public void accept(StyleVisitor visitor) {
/* 56 */         cannotModifyConstant();
/*    */       }
/*    */       
/*    */       public Object accept(StyleVisitor visitor, Object data) {
/* 60 */         cannotModifyConstant();
/* 61 */         return null;
/*    */       }
/*    */       
/*    */       public Expression getAnchorPointX() {
/* 65 */         return (Expression)ConstantExpression.constant(0.5D);
/*    */       }
/*    */       
/*    */       public Expression getAnchorPointY() {
/* 69 */         return (Expression)ConstantExpression.constant(0.5D);
/*    */       }
/*    */     };
/*    */   
/*    */   Expression getAnchorPointX();
/*    */   
/*    */   void setAnchorPointX(Expression paramExpression);
/*    */   
/*    */   void setAnchorPointY(Expression paramExpression);
/*    */   
/*    */   void accept(StyleVisitor paramStyleVisitor);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\AnchorPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */