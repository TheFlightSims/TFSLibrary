/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_round_2 extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("round_2", FunctionNameImpl.parameter("long", Long.class), new Parameter[] { FunctionNameImpl.parameter("number", Number.class) });
/*    */   
/*    */   public FilterFunction_round_2() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     try {
/* 48 */       Number number = (Number)getExpression(0).evaluate(feature, Double.class);
/* 49 */       arg0 = number.doubleValue();
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function round argument #0 - expected type double");
/*    */     } 
/* 56 */     return new Long(Math.round(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_round_2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */