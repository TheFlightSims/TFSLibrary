/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_numPoints extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("numPoints", Integer.class, new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class) });
/*    */   
/*    */   public FilterFunction_numPoints() {
/* 40 */     super(NAME);
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 44 */     return 1;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     try {
/* 51 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 52 */     } catch (Exception e) {
/* 54 */       throw new IllegalArgumentException("Filter Function problem for function numPoints argument #0 - expected type Geometry");
/*    */     } 
/* 58 */     return new Integer(StaticGeometry.numPoints(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_numPoints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */