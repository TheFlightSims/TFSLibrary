/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_log extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("log", FunctionNameImpl.parameter("logarithm", Double.class), new Parameter[] { FunctionNameImpl.parameter("value", Number.class) });
/*    */   
/*    */   public FilterFunction_log() {
/* 42 */     super("log");
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
/* 54 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 55 */     } catch (Exception e) {
/* 57 */       throw new IllegalArgumentException("Filter Function problem for function log argument #0 - expected type double");
/*    */     } 
/* 61 */     return new Double(Math.log(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_log.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */