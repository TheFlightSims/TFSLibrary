/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_relatePattern extends FunctionExpressionImpl {
/* 35 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("relatePattern", FunctionNameImpl.parameter("related", Boolean.class), new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class), FunctionNameImpl.parameter("geometry", Geometry.class), FunctionNameImpl.parameter("pattern", String.class) });
/*    */   
/*    */   public FilterFunction_relatePattern() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     Geometry arg1;
/*    */     String arg2;
/*    */     try {
/* 51 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 52 */     } catch (Exception e) {
/* 54 */       throw new IllegalArgumentException("Filter Function problem for function relatePattern argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 59 */       arg1 = (Geometry)getExpression(1).evaluate(feature, Geometry.class);
/* 60 */     } catch (Exception e) {
/* 62 */       throw new IllegalArgumentException("Filter Function problem for function relatePattern argument #1 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 67 */       arg2 = (String)getExpression(2).evaluate(feature, String.class);
/* 71 */     } catch (Exception e) {
/* 73 */       throw new IllegalArgumentException("Filter Function problem for function relatePattern argument #2 - expected type String");
/*    */     } 
/* 77 */     return new Boolean(StaticGeometry.relatePattern(arg0, arg1, arg2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_relatePattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */