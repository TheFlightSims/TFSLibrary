/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_bufferWithSegments extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("bufferWithSegments", FunctionNameImpl.parameter("buffer", Geometry.class), new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class), FunctionNameImpl.parameter("distance", Number.class), FunctionNameImpl.parameter("numberOfSegments", Number.class) });
/*    */   
/*    */   public FilterFunction_bufferWithSegments() {
/* 44 */     super("bufferWithSegments");
/* 45 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 49 */     return 3;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     double arg1;
/*    */     int arg2;
/*    */     try {
/* 58 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 59 */     } catch (Exception e) {
/* 61 */       throw new IllegalArgumentException("Filter Function problem for function bufferWithSegments argument #0 - expected type Geometry");
/*    */     } 
/*    */     try {
/* 66 */       arg1 = ((Double)getExpression(1).evaluate(feature, Double.class)).doubleValue();
/* 67 */     } catch (Exception e) {
/* 69 */       throw new IllegalArgumentException("Filter Function problem for function bufferWithSegments argument #1 - expected type double");
/*    */     } 
/*    */     try {
/* 74 */       arg2 = ((Integer)getExpression(2).evaluate(feature, Integer.class)).intValue();
/* 75 */     } catch (Exception e) {
/* 77 */       throw new IllegalArgumentException("Filter Function problem for function bufferWithSegments argument #2 - expected type int");
/*    */     } 
/* 81 */     return StaticGeometry.bufferWithSegments(arg0, Double.valueOf(arg1), Integer.valueOf(arg2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_bufferWithSegments.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */