/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_getGeometryN extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("getGeometryN", Geometry.class, new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class), FunctionNameImpl.parameter("index", Integer.class) });
/*    */   
/*    */   public FilterFunction_getGeometryN() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     int arg1;
/*    */     try {
/* 49 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function getGeometryN argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 57 */       arg1 = ((Integer)getExpression(1).evaluate(feature, Integer.class)).intValue();
/* 58 */     } catch (Exception e) {
/* 60 */       throw new IllegalArgumentException("Filter Function problem for function getGeometryN argument #1 - expected type int");
/*    */     } 
/* 64 */     return StaticGeometry.getGeometryN(arg0, Integer.valueOf(arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_getGeometryN.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */