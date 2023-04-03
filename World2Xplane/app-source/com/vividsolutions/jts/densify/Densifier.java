/*     */ package com.vividsolutions.jts.densify;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.geom.util.GeometryTransformer;
/*     */ 
/*     */ public class Densifier {
/*     */   private Geometry inputGeom;
/*     */   
/*     */   private double distanceTolerance;
/*     */   
/*     */   public static Geometry densify(Geometry geom, double distanceTolerance) {
/*  62 */     Densifier densifier = new Densifier(geom);
/*  63 */     densifier.setDistanceTolerance(distanceTolerance);
/*  64 */     return densifier.getResultGeometry();
/*     */   }
/*     */   
/*     */   private static Coordinate[] densifyPoints(Coordinate[] pts, double distanceTolerance, PrecisionModel precModel) {
/*  76 */     LineSegment seg = new LineSegment();
/*  77 */     CoordinateList coordList = new CoordinateList();
/*  78 */     for (int i = 0; i < pts.length - 1; i++) {
/*  79 */       seg.p0 = pts[i];
/*  80 */       seg.p1 = pts[i + 1];
/*  81 */       coordList.add(seg.p0, false);
/*  82 */       double len = seg.getLength();
/*  83 */       int densifiedSegCount = (int)(len / distanceTolerance) + 1;
/*  84 */       if (densifiedSegCount > 1) {
/*  85 */         double densifiedSegLen = len / densifiedSegCount;
/*  86 */         for (int j = 1; j < densifiedSegCount; j++) {
/*  87 */           double segFract = j * densifiedSegLen / len;
/*  88 */           Coordinate p = seg.pointAlong(segFract);
/*  89 */           precModel.makePrecise(p);
/*  90 */           coordList.add(p, false);
/*     */         } 
/*     */       } 
/*     */     } 
/*  94 */     coordList.add(pts[pts.length - 1], false);
/*  95 */     return coordList.toCoordinateArray();
/*     */   }
/*     */   
/*     */   public Densifier(Geometry inputGeom) {
/* 108 */     this.inputGeom = inputGeom;
/*     */   }
/*     */   
/*     */   public void setDistanceTolerance(double distanceTolerance) {
/* 121 */     if (distanceTolerance <= 0.0D)
/* 122 */       throw new IllegalArgumentException("Tolerance must be positive"); 
/* 123 */     this.distanceTolerance = distanceTolerance;
/*     */   }
/*     */   
/*     */   public Geometry getResultGeometry() {
/* 132 */     return (new DensifyTransformer()).transform(this.inputGeom);
/*     */   }
/*     */   
/*     */   class DensifyTransformer extends GeometryTransformer {
/*     */     protected CoordinateSequence transformCoordinates(CoordinateSequence coords, Geometry parent) {
/* 138 */       Coordinate[] inputPts = coords.toCoordinateArray();
/* 139 */       Coordinate[] newPts = Densifier.densifyPoints(inputPts, Densifier.this.distanceTolerance, parent.getPrecisionModel());
/* 142 */       if (parent instanceof com.vividsolutions.jts.geom.LineString && newPts.length == 1)
/* 143 */         newPts = new Coordinate[0]; 
/* 145 */       return this.factory.getCoordinateSequenceFactory().create(newPts);
/*     */     }
/*     */     
/*     */     protected Geometry transformPolygon(Polygon geom, Geometry parent) {
/* 149 */       Geometry roughGeom = super.transformPolygon(geom, parent);
/* 151 */       if (parent instanceof MultiPolygon)
/* 152 */         return roughGeom; 
/* 154 */       return createValidArea(roughGeom);
/*     */     }
/*     */     
/*     */     protected Geometry transformMultiPolygon(MultiPolygon geom, Geometry parent) {
/* 158 */       Geometry roughGeom = super.transformMultiPolygon(geom, parent);
/* 159 */       return createValidArea(roughGeom);
/*     */     }
/*     */     
/*     */     private Geometry createValidArea(Geometry roughAreaGeom) {
/* 175 */       return roughAreaGeom.buffer(0.0D);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\densify\Densifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */