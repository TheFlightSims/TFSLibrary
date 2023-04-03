/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_within extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("within", FunctionNameImpl.parameter("within", Boolean.class), new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class), FunctionNameImpl.parameter("geometry", Geometry.class) });
/*    */   
/*    */   public FilterFunction_within() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     Geometry arg1;
/*    */     try {
/* 50 */       arg0 = (Geometry)getExpression(0).evaluate(feature);
/* 51 */     } catch (Exception e) {
/* 53 */       throw new IllegalArgumentException("Filter Function problem for function within argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 58 */       arg1 = (Geometry)getExpression(1).evaluate(feature);
/* 59 */     } catch (Exception e) {
/* 61 */       throw new IllegalArgumentException("Filter Function problem for function within argument #1 - expected type Geometry");
/*    */     } 
/* 65 */     return new Boolean(StaticGeometry.within(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_within.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */