/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.LinearRing;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_interiorRingN extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("interiorRingN", LinearRing.class, new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class), FunctionNameImpl.parameter("n", Integer.class) });
/*    */   
/*    */   public FilterFunction_interiorRingN() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     int arg1;
/*    */     try {
/* 50 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 51 */     } catch (Exception e) {
/* 53 */       throw new IllegalArgumentException("Filter Function problem for function interiorRingN argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 58 */       arg1 = ((Integer)getExpression(1).evaluate(feature, Integer.class)).intValue();
/* 59 */     } catch (Exception e) {
/* 61 */       throw new IllegalArgumentException("Filter Function problem for function interiorRingN argument #1 - expected type int");
/*    */     } 
/* 65 */     return StaticGeometry.interiorRingN(arg0, Integer.valueOf(arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_interiorRingN.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */