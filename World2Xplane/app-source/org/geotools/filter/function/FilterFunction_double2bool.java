/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_double2bool extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("double2bool", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("double", Double.class) });
/*    */   
/*    */   public FilterFunction_double2bool() {
/* 38 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     try {
/* 45 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 46 */     } catch (Exception e) {
/* 48 */       throw new IllegalArgumentException("Filter Function problem for function double2bool argument #0 - expected type double");
/*    */     } 
/* 52 */     return new Boolean(StaticGeometry.double2bool(Double.valueOf(arg0)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_double2bool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */