/*     */ package com.vividsolutions.jts.operation.distance3d;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.RayCrossingCounter;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.math.Plane3D;
/*     */ import com.vividsolutions.jts.math.Vector3D;
/*     */ 
/*     */ public class PlanarPolygon3D {
/*     */   private Plane3D plane;
/*     */   
/*     */   private Polygon poly;
/*     */   
/*  61 */   private int facingPlane = -1;
/*     */   
/*     */   public PlanarPolygon3D(Polygon poly) {
/*  64 */     this.poly = poly;
/*  65 */     this.plane = findBestFitPlane(poly);
/*  66 */     this.facingPlane = this.plane.closestAxisPlane();
/*     */   }
/*     */   
/*     */   private Plane3D findBestFitPlane(Polygon poly) {
/*  83 */     CoordinateSequence seq = poly.getExteriorRing().getCoordinateSequence();
/*  84 */     Coordinate basePt = averagePoint(seq);
/*  85 */     Vector3D normal = averageNormal(seq);
/*  86 */     return new Plane3D(normal, basePt);
/*     */   }
/*     */   
/*     */   private Vector3D averageNormal(CoordinateSequence seq) {
/* 101 */     int n = seq.size();
/* 102 */     Coordinate sum = new Coordinate(0.0D, 0.0D, 0.0D);
/* 103 */     Coordinate p1 = new Coordinate(0.0D, 0.0D, 0.0D);
/* 104 */     Coordinate p2 = new Coordinate(0.0D, 0.0D, 0.0D);
/* 105 */     for (int i = 0; i < n - 1; i++) {
/* 106 */       seq.getCoordinate(i, p1);
/* 107 */       seq.getCoordinate(i + 1, p2);
/* 108 */       sum.x += (p1.y - p2.y) * (p1.z + p2.z);
/* 109 */       sum.y += (p1.z - p2.z) * (p1.x + p2.x);
/* 110 */       sum.z += (p1.x - p2.x) * (p1.y + p2.y);
/*     */     } 
/* 112 */     sum.x /= n;
/* 113 */     sum.y /= n;
/* 114 */     sum.z /= n;
/* 115 */     Vector3D norm = Vector3D.create(sum).normalize();
/* 116 */     return norm;
/*     */   }
/*     */   
/*     */   private Coordinate averagePoint(CoordinateSequence seq) {
/* 129 */     Coordinate a = new Coordinate(0.0D, 0.0D, 0.0D);
/* 130 */     int n = seq.size();
/* 131 */     for (int i = 0; i < n; i++) {
/* 132 */       a.x += seq.getOrdinate(i, 0);
/* 133 */       a.y += seq.getOrdinate(i, 1);
/* 134 */       a.z += seq.getOrdinate(i, 2);
/*     */     } 
/* 136 */     a.x /= n;
/* 137 */     a.y /= n;
/* 138 */     a.z /= n;
/* 139 */     return a;
/*     */   }
/*     */   
/*     */   public Plane3D getPlane() {
/* 143 */     return this.plane;
/*     */   }
/*     */   
/*     */   public Polygon getPolygon() {
/* 147 */     return this.poly;
/*     */   }
/*     */   
/*     */   public boolean intersects(Coordinate intPt) {
/* 151 */     if (2 == locate(intPt, this.poly.getExteriorRing()))
/* 152 */       return false; 
/* 154 */     for (int i = 0; i < this.poly.getNumInteriorRing(); i++) {
/* 155 */       if (0 == locate(intPt, this.poly.getInteriorRingN(i)))
/* 156 */         return false; 
/*     */     } 
/* 158 */     return true;
/*     */   }
/*     */   
/*     */   private int locate(Coordinate pt, LineString ring) {
/* 162 */     CoordinateSequence seq = ring.getCoordinateSequence();
/* 163 */     CoordinateSequence seqProj = project(seq, this.facingPlane);
/* 164 */     Coordinate ptProj = project(pt, this.facingPlane);
/* 165 */     return RayCrossingCounter.locatePointInRing(ptProj, seqProj);
/*     */   }
/*     */   
/*     */   public boolean intersects(Coordinate pt, LineString ring) {
/* 169 */     CoordinateSequence seq = ring.getCoordinateSequence();
/* 170 */     CoordinateSequence seqProj = project(seq, this.facingPlane);
/* 171 */     Coordinate ptProj = project(pt, this.facingPlane);
/* 172 */     return (2 != RayCrossingCounter.locatePointInRing(ptProj, seqProj));
/*     */   }
/*     */   
/*     */   private static CoordinateSequence project(CoordinateSequence seq, int facingPlane) {
/* 177 */     switch (facingPlane) {
/*     */       case 1:
/* 178 */         return AxisPlaneCoordinateSequence.projectToXY(seq);
/*     */       case 3:
/* 179 */         return AxisPlaneCoordinateSequence.projectToXZ(seq);
/*     */     } 
/* 180 */     return AxisPlaneCoordinateSequence.projectToYZ(seq);
/*     */   }
/*     */   
/*     */   private static Coordinate project(Coordinate p, int facingPlane) {
/* 186 */     switch (facingPlane) {
/*     */       case 1:
/* 187 */         return new Coordinate(p.x, p.y);
/*     */       case 3:
/* 188 */         return new Coordinate(p.x, p.z);
/*     */     } 
/* 190 */     return new Coordinate(p.y, p.z);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\distance3d\PlanarPolygon3D.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */