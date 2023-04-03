/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_boundary extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("boundary", FunctionNameImpl.parameter("boundary", Geometry.class), new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class) });
/*    */   
/*    */   public FilterFunction_boundary() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     try {
/* 48 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 49 */     } catch (Exception e) {
/* 51 */       throw new IllegalArgumentException("Filter Function problem for function boundary argument #0 - expected type Geometry");
/*    */     } 
/* 55 */     return StaticGeometry.boundary(arg0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_boundary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */