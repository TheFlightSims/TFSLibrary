/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.operation.distance3d.Distance3DOp;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_isWithinDistance3D extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("isWithinDistance3D", Double.class, new Parameter[] { FunctionNameImpl.parameter("geometry1", Geometry.class), FunctionNameImpl.parameter("geometry2", Geometry.class), FunctionNameImpl.parameter("distance", Double.class) });
/*    */   
/*    */   public FilterFunction_isWithinDistance3D() {
/* 43 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     Geometry arg1;
/*    */     double arg2;
/*    */     try {
/* 52 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 53 */     } catch (Exception e) {
/* 55 */       throw new IllegalArgumentException("Filter Function problem for function isWithinDisatnce3D argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 60 */       arg1 = (Geometry)getExpression(1).evaluate(feature, Geometry.class);
/* 61 */     } catch (Exception e) {
/* 63 */       throw new IllegalArgumentException("Filter Function problem for function isWithinDisatnce3D argument #1 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 68 */       arg2 = ((Double)getExpression(2).evaluate(feature, Double.class)).doubleValue();
/* 69 */     } catch (Exception e) {
/* 71 */       throw new IllegalArgumentException("Filter Function problem for function isWithinDisatnce3D argument #2 - expected type double");
/*    */     } 
/* 75 */     return new Boolean(Distance3DOp.isWithinDistance(arg0, arg1, arg2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_isWithinDistance3D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */