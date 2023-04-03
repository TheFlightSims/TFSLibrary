/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_isWithinDistance extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("isWithinDistance", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("geometry1", Geometry.class), FunctionNameImpl.parameter("geometry2", Geometry.class), FunctionNameImpl.parameter("distance", Double.class) });
/*    */   
/*    */   public FilterFunction_isWithinDistance() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     Geometry arg1;
/*    */     double arg2;
/*    */     try {
/* 51 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 52 */     } catch (Exception e) {
/* 54 */       throw new IllegalArgumentException("Filter Function problem for function isWithinDistance argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 59 */       arg1 = (Geometry)getExpression(1).evaluate(feature, Geometry.class);
/* 60 */     } catch (Exception e) {
/* 62 */       throw new IllegalArgumentException("Filter Function problem for function isWithinDistance argument #1 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 67 */       arg2 = ((Double)getExpression(2).evaluate(feature, Double.class)).doubleValue();
/* 68 */     } catch (Exception e) {
/* 70 */       throw new IllegalArgumentException("Filter Function problem for function isWithinDistance argument #2 - expected type double");
/*    */     } 
/* 74 */     return new Boolean(StaticGeometry.isWithinDistance(arg0, arg1, Double.valueOf(arg2)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_isWithinDistance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */