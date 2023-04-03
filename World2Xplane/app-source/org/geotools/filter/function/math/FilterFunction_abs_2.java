/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_abs_2 extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("abs_2", FunctionNameImpl.parameter("long", Long.class), new Parameter[] { FunctionNameImpl.parameter("number", Long.class) });
/*    */   
/*    */   public FilterFunction_abs_2() {
/* 41 */     super("abs_2");
/* 42 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 46 */     return 1;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     long arg0;
/*    */     try {
/* 53 */       arg0 = ((Long)getExpression(0).evaluate(feature, Long.class)).longValue();
/* 54 */     } catch (Exception e) {
/* 56 */       throw new IllegalArgumentException("Filter Function problem for function abs argument #0 - expected type long");
/*    */     } 
/* 60 */     return new Long(Math.abs(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_abs_2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */