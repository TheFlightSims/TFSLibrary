/*     */ package com.vividsolutions.jts.operation.buffer;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.Triangle;
/*     */ import com.vividsolutions.jts.geomgraph.Label;
/*     */ import com.vividsolutions.jts.geomgraph.Position;
/*     */ import com.vividsolutions.jts.noding.NodedSegmentString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class OffsetCurveSetBuilder {
/*     */   private Geometry inputGeom;
/*     */   
/*     */   private double distance;
/*     */   
/*     */   private OffsetCurveBuilder curveBuilder;
/*     */   
/*  60 */   private List curveList = new ArrayList();
/*     */   
/*     */   public OffsetCurveSetBuilder(Geometry inputGeom, double distance, OffsetCurveBuilder curveBuilder) {
/*  67 */     this.inputGeom = inputGeom;
/*  68 */     this.distance = distance;
/*  69 */     this.curveBuilder = curveBuilder;
/*     */   }
/*     */   
/*     */   public List getCurves() {
/*  81 */     add(this.inputGeom);
/*  82 */     return this.curveList;
/*     */   }
/*     */   
/*     */   private void addCurve(Coordinate[] coord, int leftLoc, int rightLoc) {
/*  97 */     if (coord == null || coord.length < 2)
/*     */       return; 
/*  99 */     NodedSegmentString nodedSegmentString = new NodedSegmentString(coord, new Label(0, 1, leftLoc, rightLoc));
/* 101 */     this.curveList.add(nodedSegmentString);
/*     */   }
/*     */   
/*     */   private void add(Geometry g) {
/* 107 */     if (g.isEmpty())
/*     */       return; 
/* 109 */     if (g instanceof Polygon) {
/* 109 */       addPolygon((Polygon)g);
/* 111 */     } else if (g instanceof LineString) {
/* 111 */       addLineString((LineString)g);
/* 112 */     } else if (g instanceof Point) {
/* 112 */       addPoint((Point)g);
/* 113 */     } else if (g instanceof com.vividsolutions.jts.geom.MultiPoint) {
/* 113 */       addCollection((GeometryCollection)g);
/* 114 */     } else if (g instanceof com.vividsolutions.jts.geom.MultiLineString) {
/* 114 */       addCollection((GeometryCollection)g);
/* 115 */     } else if (g instanceof com.vividsolutions.jts.geom.MultiPolygon) {
/* 115 */       addCollection((GeometryCollection)g);
/* 116 */     } else if (g instanceof GeometryCollection) {
/* 116 */       addCollection((GeometryCollection)g);
/*     */     } else {
/* 117 */       throw new UnsupportedOperationException(g.getClass().getName());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addCollection(GeometryCollection gc) {
/* 121 */     for (int i = 0; i < gc.getNumGeometries(); i++) {
/* 122 */       Geometry g = gc.getGeometryN(i);
/* 123 */       add(g);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addPoint(Point p) {
/* 132 */     if (this.distance <= 0.0D)
/*     */       return; 
/* 134 */     Coordinate[] coord = p.getCoordinates();
/* 135 */     Coordinate[] curve = this.curveBuilder.getLineCurve(coord, this.distance);
/* 136 */     addCurve(curve, 2, 0);
/*     */   }
/*     */   
/*     */   private void addLineString(LineString line) {
/* 142 */     if (this.distance <= 0.0D && !this.curveBuilder.getBufferParameters().isSingleSided())
/*     */       return; 
/* 144 */     Coordinate[] coord = CoordinateArrays.removeRepeatedPoints(line.getCoordinates());
/* 145 */     Coordinate[] curve = this.curveBuilder.getLineCurve(coord, this.distance);
/* 146 */     addCurve(curve, 2, 0);
/*     */   }
/*     */   
/*     */   private void addPolygon(Polygon p) {
/* 155 */     double offsetDistance = this.distance;
/* 156 */     int offsetSide = 1;
/* 157 */     if (this.distance < 0.0D) {
/* 158 */       offsetDistance = -this.distance;
/* 159 */       offsetSide = 2;
/*     */     } 
/* 162 */     LinearRing shell = (LinearRing)p.getExteriorRing();
/* 163 */     Coordinate[] shellCoord = CoordinateArrays.removeRepeatedPoints(shell.getCoordinates());
/* 166 */     if (this.distance < 0.0D && isErodedCompletely(shell, this.distance))
/*     */       return; 
/* 169 */     if (this.distance <= 0.0D && shellCoord.length < 3)
/*     */       return; 
/* 172 */     addPolygonRing(shellCoord, offsetDistance, offsetSide, 2, 0);
/* 179 */     for (int i = 0; i < p.getNumInteriorRing(); i++) {
/* 181 */       LinearRing hole = (LinearRing)p.getInteriorRingN(i);
/* 182 */       Coordinate[] holeCoord = CoordinateArrays.removeRepeatedPoints(hole.getCoordinates());
/* 186 */       if (this.distance <= 0.0D || !isErodedCompletely(hole, -this.distance))
/* 192 */         addPolygonRing(holeCoord, offsetDistance, Position.opposite(offsetSide), 0, 2); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addPolygonRing(Coordinate[] coord, double offsetDistance, int side, int cwLeftLoc, int cwRightLoc) {
/* 217 */     if (offsetDistance == 0.0D && coord.length < 4)
/*     */       return; 
/* 220 */     int leftLoc = cwLeftLoc;
/* 221 */     int rightLoc = cwRightLoc;
/* 222 */     if (coord.length >= 4 && CGAlgorithms.isCCW(coord)) {
/* 224 */       leftLoc = cwRightLoc;
/* 225 */       rightLoc = cwLeftLoc;
/* 226 */       side = Position.opposite(side);
/*     */     } 
/* 228 */     Coordinate[] curve = this.curveBuilder.getRingCurve(coord, side, offsetDistance);
/* 229 */     addCurve(curve, leftLoc, rightLoc);
/*     */   }
/*     */   
/*     */   private boolean isErodedCompletely(LinearRing ring, double bufferDistance) {
/* 243 */     Coordinate[] ringCoord = ring.getCoordinates();
/* 244 */     double minDiam = 0.0D;
/* 246 */     if (ringCoord.length < 4)
/* 247 */       return (bufferDistance < 0.0D); 
/* 251 */     if (ringCoord.length == 4)
/* 252 */       return isTriangleErodedCompletely(ringCoord, bufferDistance); 
/* 255 */     Envelope env = ring.getEnvelopeInternal();
/* 256 */     double envMinDimension = Math.min(env.getHeight(), env.getWidth());
/* 257 */     if (bufferDistance < 0.0D && 2.0D * Math.abs(bufferDistance) > envMinDimension)
/* 259 */       return true; 
/* 261 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isTriangleErodedCompletely(Coordinate[] triangleCoord, double bufferDistance) {
/* 303 */     Triangle tri = new Triangle(triangleCoord[0], triangleCoord[1], triangleCoord[2]);
/* 304 */     Coordinate inCentre = tri.inCentre();
/* 305 */     double distToCentre = CGAlgorithms.distancePointLine(inCentre, tri.p0, tri.p1);
/* 306 */     return (distToCentre < Math.abs(bufferDistance));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\OffsetCurveSetBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */