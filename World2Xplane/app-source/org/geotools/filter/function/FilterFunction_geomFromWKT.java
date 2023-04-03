/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_geomFromWKT extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("geomFromWKT", Geometry.class, new Parameter[] { FunctionNameImpl.parameter("geometry", String.class) });
/*    */   
/*    */   public FilterFunction_geomFromWKT() {
/* 40 */     super("geomFromWKT");
/* 41 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 45 */     return 1;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String arg0;
/*    */     try {
/* 52 */       arg0 = (String)getExpression(0).evaluate(feature, String.class);
/* 56 */     } catch (Exception e) {
/* 58 */       throw new IllegalArgumentException("Filter Function problem for function geomFromWKT argument #0 - expected type String");
/*    */     } 
/* 62 */     return StaticGeometry.geomFromWKT(arg0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_geomFromWKT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */