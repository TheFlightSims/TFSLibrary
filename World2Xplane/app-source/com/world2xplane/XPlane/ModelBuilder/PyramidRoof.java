/*    */ package com.world2xplane.XPlane.ModelBuilder;
/*    */ 
/*    */ import com.world2xplane.Geom.GeomUtils;
/*    */ import com.world2xplane.Geom.Vector3D;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.polygon.LinearRing2D;
/*    */ 
/*    */ public class PyramidRoof {
/*    */   public void makeRoof(Set<ModelBuilder.ObjPoint> vertexes, List<ModelBuilder.ObjTriangle> tris, LinearRing2D building, Point2D centroid, double minHeight, double maxHeight) {
/* 49 */     double height = maxHeight - minHeight;
/* 50 */     double textureHeight = 40.0D;
/* 52 */     building = GeomUtils.setClockwise(building);
/* 54 */     Vector3D pyramidCentre = ModelBuilder.get3DPoint(centroid, centroid, minHeight + maxHeight);
/* 56 */     for (int x = 0; x < building.vertexNumber() - 1; x++) {
/* 57 */       Point2D vertex1 = building.vertex(x);
/* 58 */       Point2D vertex2 = building.vertex(x + 1);
/* 61 */       Vector3D point1 = ModelBuilder.get3DPoint(centroid, vertex1, minHeight);
/* 62 */       Vector3D point2 = ModelBuilder.get3DPoint(centroid, vertex2, minHeight);
/* 63 */       Vector3D normal = ModelBuilder.calculateNormal(point1, pyramidCentre, point2);
/* 65 */       double distance = ModelBuilder.distance(vertex1, vertex2).doubleValue();
/* 66 */       Point2D uv1 = new Point2D(0.0D, 0.0D);
/* 67 */       Point2D uv2 = new Point2D(0.0D, height / textureHeight);
/* 68 */       Point2D uv3 = new Point2D(distance / textureHeight, 0.0D);
/* 71 */       ModelBuilder.ObjPoint objPoint1 = new ModelBuilder.ObjPoint(point1.x(), point1.y(), point1.z(), normal.x(), normal.y(), normal.z(), uv1.x(), uv1.y());
/* 72 */       ModelBuilder.ObjPoint objPoint2 = new ModelBuilder.ObjPoint(point2.x(), point2.y(), point2.z(), normal.x(), normal.y(), normal.z(), uv3.x(), uv3.y());
/* 73 */       ModelBuilder.ObjPoint objPoint3 = new ModelBuilder.ObjPoint(pyramidCentre.x(), pyramidCentre.y(), pyramidCentre.z(), normal.x(), normal.y(), normal.z(), uv2.x(), uv2.y());
/* 75 */       vertexes.add(objPoint1);
/* 76 */       vertexes.add(objPoint2);
/* 77 */       vertexes.add(objPoint3);
/* 78 */       ModelBuilder.ObjTriangle tri1 = new ModelBuilder.ObjTriangle(objPoint1, objPoint2, objPoint3);
/* 79 */       tris.add(tri1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\ModelBuilder\PyramidRoof.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */