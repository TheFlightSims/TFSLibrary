/*     */ package com.vividsolutions.jts.operation.overlay.snap;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.util.GeometryTransformer;
/*     */ 
/*     */ class SnapTransformer extends GeometryTransformer {
/*     */   private double snapTolerance;
/*     */   
/*     */   private Coordinate[] snapPts;
/*     */   
/*     */   private boolean isSelfSnap = false;
/*     */   
/*     */   SnapTransformer(double snapTolerance, Coordinate[] snapPts) {
/* 250 */     this.snapTolerance = snapTolerance;
/* 251 */     this.snapPts = snapPts;
/*     */   }
/*     */   
/*     */   SnapTransformer(double snapTolerance, Coordinate[] snapPts, boolean isSelfSnap) {
/* 256 */     this.snapTolerance = snapTolerance;
/* 257 */     this.snapPts = snapPts;
/* 258 */     this.isSelfSnap = isSelfSnap;
/*     */   }
/*     */   
/*     */   protected CoordinateSequence transformCoordinates(CoordinateSequence coords, Geometry parent) {
/* 263 */     Coordinate[] srcPts = coords.toCoordinateArray();
/* 264 */     Coordinate[] newPts = snapLine(srcPts, this.snapPts);
/* 265 */     return this.factory.getCoordinateSequenceFactory().create(newPts);
/*     */   }
/*     */   
/*     */   private Coordinate[] snapLine(Coordinate[] srcPts, Coordinate[] snapPts) {
/* 270 */     LineStringSnapper snapper = new LineStringSnapper(srcPts, this.snapTolerance);
/* 271 */     snapper.setAllowSnappingToSourceVertices(this.isSelfSnap);
/* 272 */     return snapper.snapTo(snapPts);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\snap\SnapTransformer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */