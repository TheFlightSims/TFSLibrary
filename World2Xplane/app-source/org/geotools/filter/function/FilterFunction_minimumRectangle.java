/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpression;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_minimumRectangle extends FunctionExpressionImpl implements FunctionExpression {
/* 38 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("minrectangle", Geometry.class, new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class) });
/*    */   
/*    */   public FilterFunction_minimumRectangle() {
/* 45 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     try {
/* 59 */       arg0 = (Geometry)getExpression(0).evaluate(feature);
/* 60 */     } catch (Exception e) {
/* 62 */       throw new IllegalArgumentException("Filter Function problem for function minimum rectangle argument #0 - expected type Geometry");
/*    */     } 
/* 66 */     return StaticGeometry.minimumRectangle(arg0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_minimumRectangle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */