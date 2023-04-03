/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_acos extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("acos", FunctionNameImpl.parameter("arc cosine", Double.class), new Parameter[] { FunctionNameImpl.parameter("value", Double.class) });
/*    */   
/*    */   public FilterFunction_acos() {
/* 42 */     super("acos");
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
/* 57 */       throw new IllegalArgumentException("Filter Function problem for function acos argument #0 - expected type double");
/*    */     } 
/* 61 */     return new Double(Math.acos(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_acos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */