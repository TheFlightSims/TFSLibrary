/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_abs_3 extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("abs_3", FunctionNameImpl.parameter("float", Float.class), new Parameter[] { FunctionNameImpl.parameter("number", Float.class) });
/*    */   
/*    */   public FilterFunction_abs_3() {
/* 41 */     super("abs_3");
/* 42 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 46 */     return 1;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     float arg0;
/*    */     try {
/* 53 */       arg0 = ((Float)getExpression(0).evaluate(feature, Float.class)).floatValue();
/* 54 */     } catch (Exception e) {
/* 56 */       throw new IllegalArgumentException("Filter Function problem for function abs argument #0 - expected type float");
/*    */     } 
/* 60 */     return new Float(Math.abs(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_abs_3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */