/*    */ package com.world2xplane.XPlane.ModelBuilder;
/*    */ 
/*    */ import com.world2xplane.Geom.Vector3D;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.polygon.LinearRing2D;
/*    */ 
/*    */ public class SpindleRoof {
/*    */   protected void renderSpindle(LinearRing2D polygon, Point2D centroid, double prevHeight, double height, double prevScale, double scale, Set<ModelBuilder.ObjPoint> vertexes, List<ModelBuilder.ObjTriangle> tris) {
/* 48 */     double textureWidth = 8.0D;
/* 50 */     for (int x = 0; x < polygon.vertexNumber() - 1; x++) {
/* 51 */       Point2D p1 = polygon.vertex(x);
/* 52 */       Point2D p2 = polygon.vertex((x < polygon.vertexNumber() - 1) ? (x + 1) : 0);
/* 54 */       Vector3D a = ModelBuilder.get3DPoint(centroid, p1, prevHeight, prevScale);
/* 55 */       Vector3D b = ModelBuilder.get3DPoint(centroid, p2, prevHeight, prevScale);
/* 56 */       Vector3D c = ModelBuilder.get3DPoint(centroid, p1, height, scale);
/* 57 */       Vector3D normal = ModelBuilder.calculateNormal(a, b, c);
/* 59 */       double distance = ModelBuilder.distance(p1, p2).doubleValue();
/* 60 */       Point2D uv1 = new Point2D(0.0D, 0.0D);
/* 61 */       Point2D uv2 = new Point2D(distance / textureWidth, 0.0D);
/* 62 */       Point2D uv3 = new Point2D(0.0D, height / textureWidth);
/* 65 */       ModelBuilder.ObjPoint v1 = new ModelBuilder.ObjPoint(a.x(), a.y(), a.z(), normal.x(), normal.y(), normal.z(), uv1.x(), uv1.y());
/* 66 */       ModelBuilder.ObjPoint v2 = new ModelBuilder.ObjPoint(b.x(), b.y(), b.z(), normal.x(), normal.y(), normal.z(), uv2.x(), uv2.y());
/* 67 */       ModelBuilder.ObjPoint v3 = new ModelBuilder.ObjPoint(c.x(), c.y(), c.z(), normal.x(), normal.y(), normal.z(), uv3.x(), uv3.y());
/* 68 */       vertexes.add(v1);
/* 69 */       vertexes.add(v2);
/* 70 */       vertexes.add(v3);
/* 71 */       ModelBuilder.ObjTriangle tri = new ModelBuilder.ObjTriangle(v1, v2, v3);
/* 72 */       tris.add(tri);
/* 74 */       a = ModelBuilder.get3DPoint(centroid, p2, prevHeight, prevScale);
/* 75 */       b = ModelBuilder.get3DPoint(centroid, p2, height, scale);
/* 76 */       c = ModelBuilder.get3DPoint(centroid, p1, height, scale);
/* 77 */       normal = ModelBuilder.calculateNormal(a, b, c);
/* 78 */       uv1 = new Point2D(distance / textureWidth, 0.0D);
/* 79 */       uv2 = new Point2D(distance / textureWidth, height / textureWidth);
/* 80 */       uv3 = new Point2D(0.0D, height / textureWidth);
/* 83 */       v1 = new ModelBuilder.ObjPoint(a.x(), a.y(), a.z(), normal.x(), normal.y(), normal.z(), uv1.x(), uv1.y());
/* 84 */       v2 = new ModelBuilder.ObjPoint(b.x(), b.y(), b.z(), normal.x(), normal.y(), normal.z(), uv2.x(), uv2.y());
/* 85 */       v3 = new ModelBuilder.ObjPoint(c.x(), c.y(), c.z(), normal.x(), normal.y(), normal.z(), uv3.x(), uv3.y());
/* 86 */       vertexes.add(v1);
/* 87 */       vertexes.add(v2);
/* 88 */       vertexes.add(v3);
/* 89 */       tri = new ModelBuilder.ObjTriangle(v1, v2, v3);
/* 90 */       tris.add(tri);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\ModelBuilder\SpindleRoof.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */