/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_max_2 extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("max_2", FunctionNameImpl.parameter("maximum", Long.class), new Parameter[] { FunctionNameImpl.parameter("long", Number.class), FunctionNameImpl.parameter("long", Number.class) });
/*    */   
/*    */   public FilterFunction_max_2() {
/* 43 */     super("max_2");
/* 44 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 48 */     return 2;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     long arg0;
/*    */     long arg1;
/*    */     try {
/* 56 */       arg0 = ((Long)getExpression(0).evaluate(feature, Long.class)).longValue();
/* 57 */     } catch (Exception e) {
/* 59 */       throw new IllegalArgumentException("Filter Function problem for function max argument #0 - expected type long");
/*    */     } 
/*    */     try {
/* 64 */       arg1 = ((Long)getExpression(1).evaluate(feature, Long.class)).longValue();
/* 65 */     } catch (Exception e) {
/* 67 */       throw new IllegalArgumentException("Filter Function problem for function max argument #1 - expected type long");
/*    */     } 
/* 71 */     return new Long(Math.max(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_max_2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */