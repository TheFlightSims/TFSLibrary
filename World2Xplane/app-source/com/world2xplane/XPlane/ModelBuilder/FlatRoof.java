/*    */ package com.world2xplane.XPlane.ModelBuilder;
/*    */ 
/*    */ import com.world2xplane.Geom.GeomUtils;
/*    */ import com.world2xplane.Geom.GeometryClipper;
/*    */ import com.world2xplane.Geom.Vector3D;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.polygon.LinearRing2D;
/*    */ 
/*    */ public class FlatRoof {
/*    */   public void makeRoof(Set<ModelBuilder.ObjPoint> vertexes, List<ModelBuilder.ObjTriangle> tris, LinearRing2D building, Point2D centroid, double minHeight, double maxHeight) {
/* 47 */     building = GeomUtils.setClockwise(building);
/* 53 */     List<LinearRing2D> triangles = GeometryClipper.triangulate(building, null);
/* 54 */     if (triangles == null)
/*    */       return; 
/* 57 */     for (LinearRing2D triangle : triangles) {
/* 58 */       Point2D p1 = triangle.vertex(0);
/* 59 */       Point2D p2 = triangle.vertex(1);
/* 60 */       Point2D p3 = triangle.vertex(2);
/* 62 */       Vector3D t1 = ModelBuilder.get3DPoint(centroid, p1, minHeight + maxHeight);
/* 63 */       Vector3D t2 = ModelBuilder.get3DPoint(centroid, p2, minHeight + maxHeight);
/* 64 */       Vector3D t3 = ModelBuilder.get3DPoint(centroid, p3, minHeight + maxHeight);
/* 65 */       Vector3D normal = ModelBuilder.calculateNormal(t1, t2, t3);
/* 66 */       Point2D uv1 = new Point2D(0.0D, 0.0D);
/* 67 */       Point2D uv2 = new Point2D(0.0D, 0.0D);
/* 68 */       Point2D uv3 = new Point2D(0.0D, 0.0D);
/* 70 */       ModelBuilder.ObjPoint v1 = new ModelBuilder.ObjPoint(t1.x(), t1.y(), t1.z(), normal.x(), normal.y(), normal.z(), uv1.x(), uv1.y());
/* 71 */       ModelBuilder.ObjPoint v2 = new ModelBuilder.ObjPoint(t2.x(), t2.y(), t2.z(), normal.x(), normal.y(), normal.z(), uv2.x(), uv2.y());
/* 72 */       ModelBuilder.ObjPoint v3 = new ModelBuilder.ObjPoint(t3.x(), t3.y(), t3.z(), normal.x(), normal.y(), normal.z(), uv3.x(), uv3.y());
/* 73 */       vertexes.add(v1);
/* 74 */       vertexes.add(v2);
/* 75 */       vertexes.add(v3);
/* 76 */       ModelBuilder.ObjTriangle tri = new ModelBuilder.ObjTriangle(v1, v2, v3);
/* 77 */       tris.add(tri);
/* 78 */       tri = new ModelBuilder.ObjTriangle(v3, v2, v1);
/* 79 */       tris.add(tri);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\ModelBuilder\FlatRoof.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */