/*    */ package org.geotools.styling;
/*    */ 
/*    */ import org.geotools.filter.ConstantExpression;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.style.Displacement;
/*    */ import org.opengis.style.StyleVisitor;
/*    */ 
/*    */ public interface Displacement extends Displacement {
/* 39 */   public static final Displacement DEFAULT = new ConstantDisplacement() {
/*    */       private void cannotModifyConstant() {
/* 41 */         throw new UnsupportedOperationException("Constant Stroke may not be modified");
/*    */       }
/*    */       
/*    */       public Expression getDisplacementX() {
/* 45 */         return (Expression)ConstantExpression.ZERO;
/*    */       }
/*    */       
/*    */       public Expression getDisplacementY() {
/* 49 */         return (Expression)ConstantExpression.ZERO;
/*    */       }
/*    */       
/*    */       public Object accept(StyleVisitor visitor, Object extraData) {
/* 53 */         cannotModifyConstant();
/* 54 */         return null;
/*    */       }
/*    */     };
/*    */   
/* 62 */   public static final Displacement NULL = new ConstantDisplacement() {
/*    */       private void cannotModifyConstant() {
/* 64 */         throw new UnsupportedOperationException("Constant Stroke may not be modified");
/*    */       }
/*    */       
/*    */       public Expression getDisplacementX() {
/* 68 */         return (Expression)ConstantExpression.NULL;
/*    */       }
/*    */       
/*    */       public Expression getDisplacementY() {
/* 72 */         return (Expression)ConstantExpression.NULL;
/*    */       }
/*    */       
/*    */       public Object accept(StyleVisitor visitor, Object extraData) {
/* 76 */         cannotModifyConstant();
/* 77 */         return null;
/*    */       }
/*    */     };
/*    */   
/*    */   Expression getDisplacementX();
/*    */   
/*    */   void setDisplacementX(Expression paramExpression);
/*    */   
/*    */   void setDisplacementY(Expression paramExpression);
/*    */   
/*    */   void accept(StyleVisitor paramStyleVisitor);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Displacement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */