/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_rint extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("rint", FunctionNameImpl.parameter("rounded", Double.class), new Parameter[] { FunctionNameImpl.parameter("double", Number.class) });
/*    */   
/*    */   public FilterFunction_rint() {
/* 42 */     super("rint");
/* 43 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 47 */     return 1;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     try {
/* 54 */       Number number = (Number)getExpression(0).evaluate(feature, Double.class);
/* 55 */       arg0 = number.doubleValue();
/* 56 */     } catch (Exception e) {
/* 58 */       throw new IllegalArgumentException("Filter Function problem for function rint argument #0 - expected type double");
/*    */     } 
/* 62 */     return new Double(Math.rint(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_rint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */