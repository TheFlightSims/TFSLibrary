/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_max_4 extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("max_4", FunctionNameImpl.parameter("maximum", Integer.class), new Parameter[] { FunctionNameImpl.parameter("int", Number.class), FunctionNameImpl.parameter("int", Number.class) });
/*    */   
/*    */   public FilterFunction_max_4() {
/* 43 */     super("max_4");
/* 44 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 48 */     return 2;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     int arg0;
/*    */     int arg1;
/*    */     try {
/* 56 */       arg0 = ((Integer)getExpression(0).evaluate(feature, Integer.class)).intValue();
/* 57 */     } catch (Exception e) {
/* 59 */       throw new IllegalArgumentException("Filter Function problem for function max argument #0 - expected type double");
/*    */     } 
/*    */     try {
/* 64 */       arg1 = ((Integer)getExpression(1).evaluate(feature, Integer.class)).intValue();
/* 65 */     } catch (Exception e) {
/* 67 */       throw new IllegalArgumentException("Filter Function problem for function max argument #1 - expected type double");
/*    */     } 
/* 71 */     return new Integer(Math.max(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_max_4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */