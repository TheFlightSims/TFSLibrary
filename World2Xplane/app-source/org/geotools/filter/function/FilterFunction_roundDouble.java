/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_roundDouble extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("roundDouble", FunctionNameImpl.parameter("rounded", Double.class), new Parameter[] { FunctionNameImpl.parameter("number", Number.class) });
/*    */   
/*    */   public FilterFunction_roundDouble() {
/* 39 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     try {
/* 46 */       Number number = (Number)getExpression(0).evaluate(feature, Double.class);
/* 47 */       arg0 = number.doubleValue();
/* 48 */     } catch (Exception e) {
/* 50 */       throw new IllegalArgumentException("Filter Function problem for function roundDouble argument #0 - expected type double");
/*    */     } 
/* 54 */     return new Integer(StaticGeometry.roundDouble(Double.valueOf(arg0)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_roundDouble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */