/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_startAngle extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("startAngle", FunctionNameImpl.parameter("degrees", Double.class), new Parameter[] { FunctionNameImpl.parameter("linestring", LineString.class) });
/*    */   
/*    */   public FilterFunction_startAngle() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     LineString ls;
/*    */     try {
/* 48 */       ls = (LineString)getExpression(0).evaluate(feature, LineString.class);
/* 49 */     } catch (Exception e) {
/* 51 */       throw new IllegalArgumentException("Filter Function problem for function startAngle argument #0 - expected type Geometry");
/*    */     } 
/* 54 */     if (ls == null || ls.getNumPoints() == 1)
/* 55 */       return null; 
/* 58 */     CoordinateSequence cs = ls.getCoordinateSequence();
/* 60 */     double dx = cs.getX(1) - cs.getX(0);
/* 61 */     double dy = cs.getY(1) - cs.getY(0);
/* 62 */     return Double.valueOf(-Math.toDegrees(Math.atan2(dy, dx)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_startAngle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */