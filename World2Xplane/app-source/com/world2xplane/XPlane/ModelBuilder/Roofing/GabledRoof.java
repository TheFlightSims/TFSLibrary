/*    */ package com.world2xplane.XPlane.ModelBuilder.Roofing;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import com.world2xplane.Geom.GeomUtils;
/*    */ import com.world2xplane.XPlane.ModelBuilder.ModelBuilder;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.line.LineSegment2D;
/*    */ import math.geom2d.polygon.LinearRing2D;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class GabledRoof {
/*    */   public void makeRoof(Set<ModelBuilder.ObjPoint> vertexes, List<ModelBuilder.ObjTriangle> tris, LinearRing2D building, Point2D centroid, double minHeight, double maxHeight) throws Exception {
/* 46 */     LinearRing2D simplified = GeomUtils.getMinimumRectangle(building);
/* 49 */     LineSegment2D longestEdge = GeomUtils.getLongestLine(simplified);
/* 52 */     Point2D ridgeDirection = fromAngle(GeomUtils.getBearing(longestEdge.firstPoint(), longestEdge.lastPoint()));
/* 54 */     Point2D min = mult(ridgeDirection, -1000.0D);
/* 55 */     Point2D max = mult(ridgeDirection, 1000.0D);
/* 57 */     Geometry shape = GeomUtils.linearRingToJTSPolygon(building);
/* 58 */     GeometryFactory gf = new GeometryFactory();
/* 59 */     LineString ls = gf.createLineString(new Coordinate[] { new Coordinate(min.x(), min.y()), new Coordinate(min.x(), min.y()) });
/* 61 */     Geometry intersections = shape.intersection((Geometry)ls);
/*    */   }
/*    */   
/*    */   public static Point2D mult(Point2D point, double mult) {
/* 68 */     return new Point2D(point.x() * mult, point.y() * mult);
/*    */   }
/*    */   
/*    */   public static Point2D fromAngle(double angle) {
/* 71 */     return new Point2D(FastMath.sin(FastMath.toRadians(angle)), FastMath.cos(FastMath.toRadians(angle)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\ModelBuilder\Roofing\GabledRoof.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */