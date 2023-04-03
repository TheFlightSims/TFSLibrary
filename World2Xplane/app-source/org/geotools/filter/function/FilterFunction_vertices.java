/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateFilter;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.MultiPoint;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_vertices extends FunctionExpressionImpl {
/* 25 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("vertices", FunctionNameImpl.parameter("vertices", MultiPoint.class), new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class) });
/*    */   
/*    */   public FilterFunction_vertices() {
/* 30 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature, Class context) {
/* 34 */     Geometry g = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/* 35 */     if (g == null)
/* 36 */       return null; 
/* 38 */     MultiPointExtractor filter = new MultiPointExtractor();
/* 39 */     g.apply(filter);
/* 40 */     return filter.getMultiPoint();
/*    */   }
/*    */   
/*    */   static class MultiPointExtractor implements CoordinateFilter {
/* 44 */     List<Coordinate> coordinates = new ArrayList<Coordinate>();
/*    */     
/*    */     public void filter(Coordinate c) {
/* 47 */       this.coordinates.add(c);
/*    */     }
/*    */     
/*    */     MultiPoint getMultiPoint() {
/* 51 */       Coordinate[] coorArray = this.coordinates.<Coordinate>toArray(new Coordinate[this.coordinates.size()]);
/* 52 */       return (new GeometryFactory()).createMultiPoint(coorArray);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_vertices.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */