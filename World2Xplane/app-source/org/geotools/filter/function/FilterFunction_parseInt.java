/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_parseInt extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("parseInt", Integer.class, new Parameter[] { FunctionNameImpl.parameter("string", String.class) });
/*    */   
/*    */   public FilterFunction_parseInt() {
/* 38 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String arg0;
/*    */     try {
/* 45 */       arg0 = (String)getExpression(0).evaluate(feature, String.class);
/* 49 */     } catch (Exception e) {
/* 51 */       throw new IllegalArgumentException("Filter Function problem for function parseInt argument #0 - expected type String");
/*    */     } 
/* 55 */     return new Integer(StaticGeometry.parseInt(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_parseInt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */