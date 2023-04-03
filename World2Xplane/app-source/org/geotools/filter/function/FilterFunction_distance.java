/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_distance extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("distance", Double.class, new Parameter[] { FunctionNameImpl.parameter("geometry1", Geometry.class), FunctionNameImpl.parameter("geometry2", Geometry.class) });
/*    */   
/*    */   public FilterFunction_distance() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     Geometry arg1;
/*    */     try {
/* 49 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function distance argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 57 */       arg1 = (Geometry)getExpression(1).evaluate(feature, Geometry.class);
/* 58 */     } catch (Exception e) {
/* 60 */       throw new IllegalArgumentException("Filter Function problem for function distance argument #1 - expected type Geometry");
/*    */     } 
/* 64 */     return new Double(StaticGeometry.distance(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_distance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */