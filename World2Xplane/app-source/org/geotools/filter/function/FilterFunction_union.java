/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpression;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_union extends FunctionExpressionImpl implements FunctionExpression {
/* 38 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("union", FunctionNameImpl.parameter("union", Geometry.class), new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class), FunctionNameImpl.parameter("geometry", Geometry.class) });
/*    */   
/*    */   public FilterFunction_union() {
/* 44 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     Geometry arg1;
/*    */     try {
/* 52 */       arg0 = (Geometry)getExpression(0).evaluate(feature);
/* 53 */     } catch (Exception e) {
/* 55 */       throw new IllegalArgumentException("Filter Function problem for function union argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 60 */       arg1 = (Geometry)getExpression(1).evaluate(feature);
/* 61 */     } catch (Exception e) {
/* 63 */       throw new IllegalArgumentException("Filter Function problem for function union argument #1 - expected type Geometry");
/*    */     } 
/* 67 */     return StaticGeometry.union(arg0, arg1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_union.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */