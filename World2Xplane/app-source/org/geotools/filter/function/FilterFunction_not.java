/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_not extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("not", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("logicValue", Boolean.class) });
/*    */   
/*    */   public FilterFunction_not() {
/* 40 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     boolean arg0;
/*    */     try {
/* 47 */       arg0 = ((Boolean)getExpression(0).evaluate(feature, Boolean.class)).booleanValue();
/* 49 */     } catch (Exception e) {
/* 51 */       throw new IllegalArgumentException("Filter Function problem for function not argument #0 - expected type boolean");
/*    */     } 
/* 55 */     return new Boolean(StaticGeometry.not(Boolean.valueOf(arg0)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_not.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */