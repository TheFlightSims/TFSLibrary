/*    */ package org.geotools.filter.expression;
/*    */ 
/*    */ import org.geotools.filter.Expression;
/*    */ import org.geotools.util.Converters;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public abstract class ExpressionAbstract implements Expression {
/*    */   public Object evaluate(Object object) {
/* 53 */     return null;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object object, Class context) {
/* 65 */     return Converters.convert(evaluate(object), context);
/*    */   }
/*    */   
/*    */   protected Object eval(Expression expression, SimpleFeature feature) {
/* 75 */     if (expression == null || feature == null)
/* 75 */       return null; 
/* 76 */     return expression.evaluate(feature);
/*    */   }
/*    */   
/*    */   protected Object eval(Expression expression, SimpleFeature feature) {
/* 88 */     if (expression == null || feature == null)
/* 89 */       return null; 
/* 90 */     return expression.evaluate(feature);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\ExpressionAbstract.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */