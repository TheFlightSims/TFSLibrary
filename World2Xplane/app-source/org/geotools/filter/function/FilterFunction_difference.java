/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_difference extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("difference", FunctionNameImpl.parameter("difference", Geometry.class), new Parameter[] { FunctionNameImpl.parameter("geometry1", Geometry.class), FunctionNameImpl.parameter("geometry2", Geometry.class) });
/*    */   
/*    */   public FilterFunction_difference() {
/* 43 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     Geometry arg1;
/*    */     try {
/* 51 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 52 */     } catch (Exception e) {
/* 54 */       throw new IllegalArgumentException("Filter Function problem for function difference argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 59 */       arg1 = (Geometry)getExpression(1).evaluate(feature, Geometry.class);
/* 60 */     } catch (Exception e) {
/* 62 */       throw new IllegalArgumentException("Filter Function problem for function difference argument #1 - expected type Geometry");
/*    */     } 
/* 66 */     return StaticGeometry.difference(arg0, arg1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_difference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */