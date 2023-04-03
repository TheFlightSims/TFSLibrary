/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_isNull extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("isNull", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("object", Object.class) });
/*    */   
/*    */   public FilterFunction_isNull() {
/* 38 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Object arg0;
/*    */     try {
/* 45 */       arg0 = getExpression(0).evaluate(feature);
/* 46 */     } catch (Exception e) {
/* 48 */       throw new IllegalArgumentException("Filter Function problem for function isNull argument #0 - expected type Object");
/*    */     } 
/* 52 */     return new Boolean(StaticGeometry.isNull(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_isNull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */