/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_round extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("round", FunctionNameImpl.parameter("float", Float.class), new Parameter[] { FunctionNameImpl.parameter("number", Number.class) });
/*    */   
/*    */   public FilterFunction_round() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     float arg0;
/*    */     try {
/* 49 */       Number number = (Number)getExpression(0).evaluate(feature, Float.class);
/* 50 */       arg0 = number.floatValue();
/* 51 */     } catch (Exception e) {
/* 53 */       throw new IllegalArgumentException("Filter Function problem for function round argument #0 - expected type float");
/*    */     } 
/* 57 */     return new Integer(Math.round(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_round.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */