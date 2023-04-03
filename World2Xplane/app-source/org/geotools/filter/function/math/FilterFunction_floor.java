/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_floor extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("floor", FunctionNameImpl.parameter("floor", Double.class), new Parameter[] { FunctionNameImpl.parameter("double", Double.class) });
/*    */   
/*    */   public FilterFunction_floor() {
/* 41 */     super("floor");
/* 42 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 46 */     return 1;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     try {
/* 53 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 54 */     } catch (Exception e) {
/* 56 */       throw new IllegalArgumentException("Filter Function problem for function floor argument #0 - expected type double");
/*    */     } 
/* 59 */     return new Double(Math.floor(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_floor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */