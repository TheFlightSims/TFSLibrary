/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.LinearRing;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_exteriorRing extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("exteriorRing", LinearRing.class, new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class) });
/*    */   
/*    */   public FilterFunction_exteriorRing() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 45 */     return 1;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry arg0;
/*    */     try {
/* 52 */       arg0 = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 53 */     } catch (Exception e) {
/* 55 */       throw new IllegalArgumentException("Filter Function problem for function exteriorRing argument #0 - expected type Geometry");
/*    */     } 
/* 59 */     return StaticGeometry.exteriorRing(arg0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_exteriorRing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */