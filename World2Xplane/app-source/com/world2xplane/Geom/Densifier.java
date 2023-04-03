/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Densifier {
/*     */   private Geometry geom;
/*     */   
/*     */   private GeometryFactory factory;
/*     */   
/*  45 */   private double maxLength = 1.0D;
/*     */   
/*     */   public Densifier(Geometry geom, double maxLength) {
/*  48 */     this.geom = geom;
/*  49 */     this.factory = geom.getFactory();
/*  50 */     this.maxLength = maxLength;
/*     */   }
/*     */   
/*     */   public static Geometry densify(Geometry g, double maxLength) {
/*  54 */     Densifier densifier = new Densifier(g, maxLength);
/*  55 */     return densifier.densify();
/*     */   }
/*     */   
/*     */   private Geometry densify() {
/*  59 */     if (this.geom.isEmpty() || this.geom.getDimension() == 0)
/*  60 */       return this.geom; 
/*  62 */     List<Geometry> list = new ArrayList<>();
/*  63 */     for (int i = 0; i < this.geom.getNumGeometries(); i++)
/*  64 */       list.add(densify(this.geom.getGeometryN(i))); 
/*  66 */     return this.factory.buildGeometry(list);
/*     */   }
/*     */   
/*     */   private Geometry densify(Geometry geometry) {
/*  70 */     if (geometry.getDimension() == 1)
/*  71 */       return (Geometry)densify((LineString)geometry); 
/*  72 */     if (geometry.getDimension() == 2)
/*  73 */       return (Geometry)densify((Polygon)geometry); 
/*  75 */     return geometry;
/*     */   }
/*     */   
/*     */   private Polygon densify(Polygon polygon) {
/*  80 */     LinearRing exteriorRing = densify((LinearRing)polygon.getExteriorRing());
/*  81 */     LinearRing[] holes = new LinearRing[polygon.getNumInteriorRing()];
/*  82 */     for (int i = 0; i < holes.length; i++)
/*  83 */       holes[i] = densify((LinearRing)polygon.getInteriorRingN(i)); 
/*  85 */     return this.factory.createPolygon(exteriorRing, holes);
/*     */   }
/*     */   
/*     */   private LineString densify(LineString line) {
/*  89 */     CoordinateSequence sequence = line.getCoordinateSequence();
/*  90 */     CoordinateList list = new CoordinateList();
/*  91 */     for (int i = 0; i < sequence.size(); i++)
/*  92 */       densify(sequence, i, list); 
/*  94 */     list.add(sequence.getCoordinate(sequence.size() - 1));
/*  95 */     return this.factory.createLineString(list.toCoordinateArray());
/*     */   }
/*     */   
/*     */   private LinearRing densify(LinearRing line) {
/*  99 */     CoordinateSequence sequence = line.getCoordinateSequence();
/* 100 */     CoordinateList list = new CoordinateList();
/* 101 */     for (int i = 0; i < sequence.size(); i++)
/* 102 */       densify(sequence, i, list); 
/* 104 */     list.add(sequence.getCoordinate(sequence.size() - 1));
/* 105 */     return this.factory.createLinearRing(list.toCoordinateArray());
/*     */   }
/*     */   
/*     */   private void densify(CoordinateSequence seq, int index, CoordinateList coordinateList) {
/* 110 */     if (index == 0)
/*     */       return; 
/* 112 */     Coordinate p0 = seq.getCoordinate(index - 1);
/* 113 */     Coordinate p1 = seq.getCoordinate(index);
/* 115 */     double dx = p1.x - p0.x;
/* 116 */     double dy = p1.y - p0.y;
/* 117 */     double dz = p1.z - p0.z;
/* 118 */     double frac = Math.sqrt(dx * dx + dy * dy) / this.maxLength;
/* 119 */     dx /= frac;
/* 120 */     dy /= frac;
/* 121 */     dz /= frac;
/* 123 */     int nbSegments = (int)(frac + 0.9999D);
/* 125 */     for (int i = 0; i < nbSegments; i++) {
/* 126 */       double x = p0.x + i * dx;
/* 127 */       double y = p0.y + i * dy;
/* 128 */       double z = p0.z + i * dz;
/* 129 */       Coordinate pt = new Coordinate(x, y, z);
/* 130 */       coordinateList.add(pt);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\Densifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */