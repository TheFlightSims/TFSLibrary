/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_int2ddouble extends FunctionExpressionImpl {
/* 35 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("int2ddouble", Double.class, new Parameter[] { FunctionNameImpl.parameter("int", Integer.class) });
/*    */   
/*    */   public FilterFunction_int2ddouble() {
/* 38 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     int arg0;
/*    */     try {
/* 45 */       arg0 = ((Integer)getExpression(0).evaluate(feature, Integer.class)).intValue();
/* 46 */     } catch (Exception e) {
/* 48 */       throw new IllegalArgumentException("Filter Function problem for function int2ddouble argument #0 - expected type int");
/*    */     } 
/* 52 */     return new Double(StaticGeometry.int2ddouble(Integer.valueOf(arg0)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_int2ddouble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */