/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.operation.distance3d.Distance3DOp;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_disjoint3D extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("disjoint3D", Double.class, new Parameter[] { FunctionNameImpl.parameter("geometry1", Geometry.class), FunctionNameImpl.parameter("geometry2", Geometry.class) });
/*    */   
/*    */   public FilterFunction_disjoint3D() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     Geometry arg1;
/*    */     try {
/* 50 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 51 */     } catch (Exception e) {
/* 53 */       throw new IllegalArgumentException("Filter Function problem for function disjoint3D argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 58 */       arg1 = (Geometry)getExpression(1).evaluate(feature, Geometry.class);
/* 59 */     } catch (Exception e) {
/* 61 */       throw new IllegalArgumentException("Filter Function problem for function disjoint3D argument #1 - expected type Geometry");
/*    */     } 
/* 69 */     return new Boolean((Distance3DOp.distance(arg0, arg1) > 0.0D));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_disjoint3D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */