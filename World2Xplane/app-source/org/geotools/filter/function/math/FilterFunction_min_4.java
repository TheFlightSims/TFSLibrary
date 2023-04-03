/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_min_4 extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("min_4", FunctionNameImpl.parameter("minium", Integer.class), new Parameter[] { FunctionNameImpl.parameter("int", Number.class), FunctionNameImpl.parameter("int", Number.class) });
/*    */   
/*    */   public FilterFunction_min_4() {
/* 42 */     super("min_4");
/* 43 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 47 */     return 2;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     int arg0;
/*    */     int arg1;
/*    */     try {
/* 55 */       arg0 = ((Integer)getExpression(0).evaluate(feature, Integer.class)).intValue();
/* 56 */     } catch (Exception e) {
/* 58 */       throw new IllegalArgumentException("Filter Function problem for function min argument #0 - expected type double");
/*    */     } 
/*    */     try {
/* 63 */       arg1 = ((Integer)getExpression(1).evaluate(feature, Integer.class)).intValue();
/* 64 */     } catch (Exception e) {
/* 66 */       throw new IllegalArgumentException("Filter Function problem for function min argument #1 - expected type double");
/*    */     } 
/* 70 */     return new Integer(Math.min(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_min_4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */