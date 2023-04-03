/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import com.vividsolutions.jts.geom.Point;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_endPoint extends FunctionExpressionImpl {
/* 38 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("endPoint", Point.class, new Parameter[] { FunctionNameImpl.parameter("linestring", LineString.class) });
/*    */   
/*    */   public FilterFunction_endPoint() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     try {
/* 49 */       arg0 = (Geometry)getExpression(0).evaluate(feature, LineString.class);
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function endPoint argument #0 - expected type Geometry");
/*    */     } 
/* 56 */     return StaticGeometry.endPoint(arg0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_endPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */