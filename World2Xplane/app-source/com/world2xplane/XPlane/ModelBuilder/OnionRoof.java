/*    */ package com.world2xplane.XPlane.ModelBuilder;
/*    */ 
/*    */ import com.world2xplane.Geom.GeomUtils;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.polygon.LinearRing2D;
/*    */ 
/*    */ public class OnionRoof extends SpindleRoof {
/*    */   public void makeRoof(Set<ModelBuilder.ObjPoint> vertexes, List<ModelBuilder.ObjTriangle> tris, LinearRing2D building, Point2D centroid, double minHeight, double maxHeight) {
/* 46 */     if (maxHeight == 0.0D)
/* 47 */       maxHeight = minHeight + 3.0D; 
/* 50 */     building = GeomUtils.setClockwise(building);
/* 53 */     double roofY = minHeight;
/* 54 */     double roofHeight = maxHeight;
/* 56 */     renderSpindle(building, centroid, roofY, roofY + 0.15D * roofHeight, 1.0D, 0.8D, vertexes, tris);
/* 57 */     renderSpindle(building, centroid, roofY + 0.15D * roofHeight, roofY + 0.52D * roofHeight, 0.8D, 1.0D, vertexes, tris);
/* 58 */     renderSpindle(building, centroid, roofY + 0.52D * roofHeight, roofY + 0.72D * roofHeight, 1.0D, 0.7D, vertexes, tris);
/* 59 */     renderSpindle(building, centroid, roofY + 0.72D * roofHeight, roofY + 0.82D * roofHeight, 0.7D, 0.15D, vertexes, tris);
/* 60 */     renderSpindle(building, centroid, roofY + 0.82D * roofHeight, roofY + 1.0D * roofHeight, 0.15D, 0.0D, vertexes, tris);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\ModelBuilder\OnionRoof.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */