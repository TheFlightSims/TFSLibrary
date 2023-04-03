/*     */ package com.vividsolutions.jts.awt;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ShapeReader {
/*  63 */   private static final AffineTransform INVERT_Y = AffineTransform.getScaleInstance(1.0D, -1.0D);
/*     */   
/*     */   private GeometryFactory geometryFactory;
/*     */   
/*     */   public static Geometry read(PathIterator pathIt, GeometryFactory geomFact) {
/*  74 */     ShapeReader pc = new ShapeReader(geomFact);
/*  75 */     return pc.read(pathIt);
/*     */   }
/*     */   
/*     */   public static Geometry read(Shape shp, double flatness, GeometryFactory geomFact) {
/*  88 */     PathIterator pathIt = shp.getPathIterator(INVERT_Y, flatness);
/*  89 */     return read(pathIt, geomFact);
/*     */   }
/*     */   
/*     */   public ShapeReader(GeometryFactory geometryFactory) {
/*  95 */     this.geometryFactory = geometryFactory;
/*     */   }
/*     */   
/*     */   public Geometry read(PathIterator pathIt) {
/* 106 */     List<Coordinate[]> pathPtSeq = toCoordinates(pathIt);
/* 108 */     List<Polygon> polys = new ArrayList();
/* 109 */     int seqIndex = 0;
/* 110 */     while (seqIndex < pathPtSeq.size()) {
/* 113 */       Coordinate[] pts = pathPtSeq.get(seqIndex);
/* 114 */       LinearRing shell = this.geometryFactory.createLinearRing(pts);
/* 115 */       seqIndex++;
/* 117 */       List<LinearRing> holes = new ArrayList();
/* 119 */       while (seqIndex < pathPtSeq.size() && isHole(pathPtSeq.get(seqIndex))) {
/* 120 */         Coordinate[] holePts = pathPtSeq.get(seqIndex);
/* 121 */         LinearRing hole = this.geometryFactory.createLinearRing(holePts);
/* 122 */         holes.add(hole);
/* 123 */         seqIndex++;
/*     */       } 
/* 125 */       LinearRing[] holeArray = GeometryFactory.toLinearRingArray(holes);
/* 126 */       polys.add(this.geometryFactory.createPolygon(shell, holeArray));
/*     */     } 
/* 128 */     return this.geometryFactory.buildGeometry(polys);
/*     */   }
/*     */   
/*     */   private boolean isHole(Coordinate[] pts) {
/* 133 */     return CGAlgorithms.isCCW(pts);
/*     */   }
/*     */   
/*     */   public static List toCoordinates(PathIterator pathIt) {
/* 146 */     List<Coordinate[]> coordArrays = new ArrayList();
/* 147 */     while (!pathIt.isDone()) {
/* 148 */       Coordinate[] pts = nextCoordinateArray(pathIt);
/* 149 */       if (pts == null)
/*     */         break; 
/* 151 */       coordArrays.add(pts);
/*     */     } 
/* 153 */     return coordArrays;
/*     */   }
/*     */   
/*     */   private static Coordinate[] nextCoordinateArray(PathIterator pathIt) {
/* 158 */     double[] pathPt = new double[6];
/* 159 */     CoordinateList coordList = null;
/* 160 */     boolean isDone = false;
/* 161 */     while (!pathIt.isDone()) {
/* 162 */       int segType = pathIt.currentSegment(pathPt);
/* 163 */       switch (segType) {
/*     */         case 0:
/* 165 */           if (coordList != null) {
/* 167 */             isDone = true;
/*     */             break;
/*     */           } 
/* 170 */           coordList = new CoordinateList();
/* 171 */           coordList.add(new Coordinate(pathPt[0], pathPt[1]));
/* 172 */           pathIt.next();
/*     */           break;
/*     */         case 1:
/* 176 */           coordList.add(new Coordinate(pathPt[0], pathPt[1]));
/* 177 */           pathIt.next();
/*     */           break;
/*     */         case 4:
/* 180 */           coordList.closeRing();
/* 181 */           pathIt.next();
/* 182 */           isDone = true;
/*     */           break;
/*     */         default:
/* 185 */           throw new IllegalArgumentException("unhandled (non-linear) segment type encountered");
/*     */       } 
/* 187 */       if (isDone)
/*     */         break; 
/*     */     } 
/* 190 */     return coordList.toCoordinateArray();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\awt\ShapeReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */