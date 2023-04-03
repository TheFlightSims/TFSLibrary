/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.geotools.referencing.CRS;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ import org.opengis.referencing.FactoryException;
/*    */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*    */ 
/*    */ public class FilterFunction_setCRS extends FunctionExpressionImpl {
/* 32 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("setCRS", Geometry.class, new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class), FunctionNameImpl.parameter("CRS", String.class) });
/*    */   
/*    */   public FilterFunction_setCRS() {
/* 36 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Geometry geom;
/*    */     CoordinateReferenceSystem crs;
/*    */     try {
/* 44 */       geom = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 45 */     } catch (Exception e) {
/* 47 */       throw new IllegalArgumentException("Expected argument of type Geometry for argument #0");
/*    */     } 
/*    */     try {
/* 52 */       crs = (CoordinateReferenceSystem)getExpression(1).evaluate(feature, CoordinateReferenceSystem.class);
/* 53 */       if (crs == null) {
/* 54 */         String srs = (String)getExpression(1).evaluate(feature, String.class);
/*    */         try {
/* 56 */           crs = CRS.decode(srs);
/* 57 */         } catch (FactoryException e) {
/* 58 */           crs = CRS.parseWKT(srs);
/*    */         } 
/*    */       } 
/* 61 */     } catch (Exception e) {
/* 62 */       throw new IllegalArgumentException("Expected argument of type CoordinateReferenceSystem, WKT or valid EPSG code for argument #1");
/*    */     } 
/* 66 */     if (geom != null)
/* 67 */       geom.setUserData(crs); 
/* 70 */     return geom;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_setCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */