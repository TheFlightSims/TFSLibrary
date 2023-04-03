/*    */ package com.world2xplane.Geom;
/*    */ 
/*    */ import com.vividsolutions.jts.algorithm.MinimumDiameter;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.operation.buffer.BufferOp;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.polygon.LinearRing2D;
/*    */ 
/*    */ public class MinimumBoundingBox {
/*    */   public static LinearRing2D BoundingBox(LinearRing2D record) {
/* 43 */     LinearRing2D mercated = new LinearRing2D();
/* 44 */     for (int x = 0; x < record.vertexNumber(); x++) {
/* 45 */       Point2D item = record.vertex(x);
/* 46 */       mercated.addVertex(new Point2D(GeomUtils.merc_x(item.x()), GeomUtils.merc_y(item.y())));
/*    */     } 
/* 49 */     Geometry geometry = GeomUtils.linearRingToJTSPolygon(mercated);
/* 50 */     MinimumDiameter minimumDiameter = new MinimumDiameter(geometry);
/* 51 */     Geometry out = minimumDiameter.getMinimumRectangle();
/* 52 */     LinearRing2D outRing = GeomUtils.polygonToLinearRing2D(out);
/* 54 */     LinearRing2D minimumBox = new LinearRing2D();
/* 55 */     for (int i = 0; i < outRing.vertexNumber(); i++) {
/* 56 */       Point2D item = outRing.vertex(i);
/* 58 */       minimumBox.addVertex(new Point2D(GeomUtils.merc_lon(item.x()), GeomUtils.merc_lat(item.y())));
/*    */     } 
/* 60 */     return minimumBox;
/*    */   }
/*    */   
/*    */   public static LinearRing2D Boundary(LinearRing2D record) {
/* 65 */     LinearRing2D mercated = new LinearRing2D();
/* 66 */     for (int x = 0; x < record.vertexNumber(); x++) {
/* 67 */       Point2D item = record.vertex(x);
/* 68 */       mercated.addVertex(new Point2D(GeomUtils.merc_x(item.x()), GeomUtils.merc_y(item.y())));
/*    */     } 
/* 71 */     Geometry geometry = GeomUtils.linearRingToJTSPolygon(mercated);
/* 72 */     BufferOp minimumDiameter = new BufferOp(geometry);
/* 73 */     minimumDiameter.setQuadrantSegments(4);
/* 74 */     Geometry out = minimumDiameter.getResultGeometry(1.0D);
/* 75 */     LinearRing2D outRing = GeomUtils.polygonToLinearRing2D(out);
/* 77 */     LinearRing2D minimumBox = new LinearRing2D();
/* 78 */     for (int i = 0; i < outRing.vertexNumber(); i++) {
/* 79 */       Point2D item = outRing.vertex(i);
/* 81 */       minimumBox.addVertex(new Point2D(GeomUtils.merc_lon(item.x()), GeomUtils.merc_lat(item.y())));
/*    */     } 
/* 83 */     return minimumBox;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\MinimumBoundingBox.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */