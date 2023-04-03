/*     */ package com.vividsolutions.jts.operation.overlay.snap;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public class GeometrySnapper {
/*     */   private static final double SNAP_PRECISION_FACTOR = 1.0E-9D;
/*     */   
/*     */   private Geometry srcGeom;
/*     */   
/*     */   public static double computeOverlaySnapTolerance(Geometry g) {
/*  71 */     double snapTolerance = computeSizeBasedSnapTolerance(g);
/*  82 */     PrecisionModel pm = g.getPrecisionModel();
/*  83 */     if (pm.getType() == PrecisionModel.FIXED) {
/*  84 */       double fixedSnapTol = 1.0D / pm.getScale() * 2.0D / 1.415D;
/*  85 */       if (fixedSnapTol > snapTolerance)
/*  86 */         snapTolerance = fixedSnapTol; 
/*     */     } 
/*  88 */     return snapTolerance;
/*     */   }
/*     */   
/*     */   public static double computeSizeBasedSnapTolerance(Geometry g) {
/*  93 */     Envelope env = g.getEnvelopeInternal();
/*  94 */     double minDimension = Math.min(env.getHeight(), env.getWidth());
/*  95 */     double snapTol = minDimension * 1.0E-9D;
/*  96 */     return snapTol;
/*     */   }
/*     */   
/*     */   public static double computeOverlaySnapTolerance(Geometry g0, Geometry g1) {
/* 101 */     return Math.min(computeOverlaySnapTolerance(g0), computeOverlaySnapTolerance(g1));
/*     */   }
/*     */   
/*     */   public static Geometry[] snap(Geometry g0, Geometry g1, double snapTolerance) {
/* 114 */     Geometry[] snapGeom = new Geometry[2];
/* 115 */     GeometrySnapper snapper0 = new GeometrySnapper(g0);
/* 116 */     snapGeom[0] = snapper0.snapTo(g1, snapTolerance);
/* 122 */     GeometrySnapper snapper1 = new GeometrySnapper(g1);
/* 123 */     snapGeom[1] = snapper1.snapTo(snapGeom[0], snapTolerance);
/* 127 */     return snapGeom;
/*     */   }
/*     */   
/*     */   public static Geometry snapToSelf(Geometry geom, double snapTolerance, boolean cleanResult) {
/* 144 */     GeometrySnapper snapper0 = new GeometrySnapper(geom);
/* 145 */     return snapper0.snapToSelf(snapTolerance, cleanResult);
/*     */   }
/*     */   
/*     */   public GeometrySnapper(Geometry srcGeom) {
/* 157 */     this.srcGeom = srcGeom;
/*     */   }
/*     */   
/*     */   public Geometry snapTo(Geometry snapGeom, double snapTolerance) {
/* 171 */     Coordinate[] snapPts = extractTargetCoordinates(snapGeom);
/* 173 */     SnapTransformer snapTrans = new SnapTransformer(snapTolerance, snapPts);
/* 174 */     return snapTrans.transform(this.srcGeom);
/*     */   }
/*     */   
/*     */   public Geometry snapToSelf(double snapTolerance, boolean cleanResult) {
/* 191 */     Coordinate[] snapPts = extractTargetCoordinates(this.srcGeom);
/* 193 */     SnapTransformer snapTrans = new SnapTransformer(snapTolerance, snapPts, true);
/* 194 */     Geometry snappedGeom = snapTrans.transform(this.srcGeom);
/* 195 */     Geometry result = snappedGeom;
/* 196 */     if (cleanResult && result instanceof com.vividsolutions.jts.geom.Polygonal)
/* 198 */       result = snappedGeom.buffer(0.0D); 
/* 200 */     return result;
/*     */   }
/*     */   
/*     */   private Coordinate[] extractTargetCoordinates(Geometry g) {
/* 206 */     Set<Coordinate> ptSet = new TreeSet();
/* 207 */     Coordinate[] pts = g.getCoordinates();
/* 208 */     for (int i = 0; i < pts.length; i++)
/* 209 */       ptSet.add(pts[i]); 
/* 211 */     return ptSet.<Coordinate>toArray(new Coordinate[0]);
/*     */   }
/*     */   
/*     */   private double computeSnapTolerance(Coordinate[] ringPts) {
/* 222 */     double minSegLen = computeMinimumSegmentLength(ringPts);
/* 224 */     double snapTol = minSegLen / 10.0D;
/* 225 */     return snapTol;
/*     */   }
/*     */   
/*     */   private double computeMinimumSegmentLength(Coordinate[] pts) {
/* 230 */     double minSegLen = Double.MAX_VALUE;
/* 231 */     for (int i = 0; i < pts.length - 1; i++) {
/* 232 */       double segLen = pts[i].distance(pts[i + 1]);
/* 233 */       if (segLen < minSegLen)
/* 234 */         minSegLen = segLen; 
/*     */     } 
/* 236 */     return minSegLen;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\snap\GeometrySnapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */