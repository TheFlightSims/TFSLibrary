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
/*    */ public class FilterFunction_startPoint extends FunctionExpressionImpl {
/* 38 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("startPoint", FunctionNameImpl.parameter("point", Point.class), new Parameter[] { FunctionNameImpl.parameter("linestring", LineString.class) });
/*    */   
/*    */   public FilterFunction_startPoint() {
/* 43 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Point evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     try {
/* 50 */       arg0 = (Geometry)getExpression(0).evaluate(feature, LineString.class);
/* 51 */     } catch (Exception e) {
/* 53 */       throw new IllegalArgumentException("Filter Function problem for function startPoint argument #0 - expected type Geometry");
/*    */     } 
/* 57 */     return StaticGeometry.startPoint(arg0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_startPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */