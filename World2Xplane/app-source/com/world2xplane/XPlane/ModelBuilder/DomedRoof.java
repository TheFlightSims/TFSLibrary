/*    */ package com.world2xplane.XPlane.ModelBuilder;
/*    */ 
/*    */ import com.world2xplane.Geom.GeomUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import math.geom2d.Point2D;
/*    */ import math.geom2d.polygon.LinearRing2D;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class DomedRoof extends SpindleRoof {
/*    */   public void makeRoof(Set<ModelBuilder.ObjPoint> vertexes, List<ModelBuilder.ObjTriangle> tris, LinearRing2D building, Point2D centroid, double minHeight, double maxHeight) {
/* 73 */     building = GeomUtils.setClockwise(building);
/* 75 */     if (maxHeight == 0.0D)
/* 76 */       maxHeight = minHeight + 3.0D; 
/* 79 */     List<Double> heights = new ArrayList<>();
/* 80 */     List<Double> scales = new ArrayList<>();
/*    */     int x;
/* 83 */     for (x = 0; x < 10; x++) {
/* 84 */       double relativeHeight = x / 9.0D;
/* 85 */       heights.add(Double.valueOf(relativeHeight * maxHeight));
/* 86 */       scales.add(Double.valueOf(FastMath.sqrt(1.0D - relativeHeight * relativeHeight)));
/*    */     } 
/* 89 */     for (x = 1; x < 10; x++)
/* 90 */       renderSpindle(building, centroid, minHeight + ((Double)heights.get(x - 1)).doubleValue(), minHeight + ((Double)heights.get(x)).doubleValue(), ((Double)scales.get(x - 1)).doubleValue(), ((Double)scales.get(x)).doubleValue(), vertexes, tris); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\ModelBuilder\DomedRoof.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */