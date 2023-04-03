/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_abs extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("abs", FunctionNameImpl.parameter("abs", Integer.class), new Parameter[] { FunctionNameImpl.parameter("int", Integer.class) });
/*    */   
/*    */   public FilterFunction_abs() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     int arg0;
/*    */     try {
/* 48 */       arg0 = ((Integer)getExpression(0).evaluate(feature, Integer.class)).intValue();
/* 49 */     } catch (Exception e) {
/* 51 */       throw new IllegalArgumentException("Filter Function problem for function abs argument #0 - expected type int");
/*    */     } 
/* 55 */     return new Integer(Math.abs(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_abs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */