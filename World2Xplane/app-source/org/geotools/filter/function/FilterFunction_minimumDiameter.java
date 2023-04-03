/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_minimumDiameter extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("minimumdiameter", Geometry.class, new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class) });
/*    */   
/*    */   public FilterFunction_minimumDiameter() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     try {
/* 56 */       arg0 = (Geometry)getExpression(0).evaluate(feature);
/* 57 */     } catch (Exception e) {
/* 59 */       throw new IllegalArgumentException("Filter Function problem for function minimum diameter argument #0 - expected type Geometry");
/*    */     } 
/* 63 */     return StaticGeometry.minimumDiameter(arg0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_minimumDiameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */