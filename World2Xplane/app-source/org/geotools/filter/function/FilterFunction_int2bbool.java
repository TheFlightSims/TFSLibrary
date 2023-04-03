/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_int2bbool extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("int2bbool", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("int", Integer.class) });
/*    */   
/*    */   public FilterFunction_int2bbool() {
/* 37 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     int arg0;
/*    */     try {
/* 44 */       arg0 = ((Integer)getExpression(0).evaluate(feature, Integer.class)).intValue();
/* 45 */     } catch (Exception e) {
/* 47 */       throw new IllegalArgumentException("Filter Function problem for function int2bbool argument #0 - expected type int");
/*    */     } 
/* 51 */     return new Boolean(StaticGeometry.int2bbool(Integer.valueOf(arg0)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_int2bbool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */