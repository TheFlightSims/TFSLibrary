/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.geotools.util.Converters;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_Convert extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("convert", FunctionNameImpl.parameter("converted", Object.class), new Parameter[] { FunctionNameImpl.parameter("value", Object.class), FunctionNameImpl.parameter("class", Class.class) });
/*    */   
/*    */   public FilterFunction_Convert() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     try {
/* 47 */       Object arg = getExpression(0).evaluate(feature);
/* 48 */       Class target = (Class)getExpression(1).evaluate(feature, Class.class);
/* 49 */       return Converters.convert(arg, target);
/* 50 */     } catch (Exception e) {
/* 51 */       throw new IllegalArgumentException("Filter Function problem for function convert argument #1 - expected type Class");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_Convert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */