/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_isEmpty extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("isEmpty", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class) });
/*    */   
/*    */   public FilterFunction_isEmpty() {
/* 40 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     try {
/* 47 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 48 */     } catch (Exception e) {
/* 50 */       throw new IllegalArgumentException("Filter Function problem for function isEmpty argument #0 - expected type Geometry");
/*    */     } 
/* 54 */     return new Boolean(StaticGeometry.isEmpty(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_isEmpty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */