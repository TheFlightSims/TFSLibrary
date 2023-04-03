/*     */ package com.vividsolutions.jts.operation.buffer;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ 
/*     */ public class OffsetCurveBuilder {
/*  55 */   private double distance = 0.0D;
/*     */   
/*     */   private PrecisionModel precisionModel;
/*     */   
/*     */   private BufferParameters bufParams;
/*     */   
/*     */   private static final double SIMPLIFY_FACTOR = 100.0D;
/*     */   
/*     */   public OffsetCurveBuilder(PrecisionModel precisionModel, BufferParameters bufParams) {
/*  64 */     this.precisionModel = precisionModel;
/*  65 */     this.bufParams = bufParams;
/*     */   }
/*     */   
/*     */   public BufferParameters getBufferParameters() {
/*  75 */     return this.bufParams;
/*     */   }
/*     */   
/*     */   public Coordinate[] getLineCurve(Coordinate[] inputPts, double distance) {
/*  91 */     this.distance = distance;
/*  94 */     if (distance < 0.0D && !this.bufParams.isSingleSided())
/*  94 */       return null; 
/*  95 */     if (distance == 0.0D)
/*  95 */       return null; 
/*  97 */     double posDistance = Math.abs(distance);
/*  98 */     OffsetSegmentGenerator segGen = getSegGen(posDistance);
/*  99 */     if (inputPts.length <= 1) {
/* 100 */       computePointCurve(inputPts[0], segGen);
/* 103 */     } else if (this.bufParams.isSingleSided()) {
/* 104 */       boolean isRightSide = (distance < 0.0D);
/* 105 */       computeSingleSidedBufferCurve(inputPts, isRightSide, segGen);
/*     */     } else {
/* 108 */       computeLineBufferCurve(inputPts, segGen);
/*     */     } 
/* 111 */     Coordinate[] lineCoord = segGen.getCoordinates();
/* 112 */     return lineCoord;
/*     */   }
/*     */   
/*     */   public Coordinate[] getRingCurve(Coordinate[] inputPts, int side, double distance) {
/* 124 */     this.distance = distance;
/* 125 */     if (inputPts.length <= 2)
/* 126 */       return getLineCurve(inputPts, distance); 
/* 129 */     if (distance == 0.0D)
/* 130 */       return copyCoordinates(inputPts); 
/* 132 */     OffsetSegmentGenerator segGen = getSegGen(distance);
/* 133 */     computeRingBufferCurve(inputPts, side, segGen);
/* 134 */     return segGen.getCoordinates();
/*     */   }
/*     */   
/*     */   public Coordinate[] getOffsetCurve(Coordinate[] inputPts, double distance) {
/* 139 */     this.distance = distance;
/* 142 */     if (distance == 0.0D)
/* 142 */       return null; 
/* 144 */     boolean isRightSide = (distance < 0.0D);
/* 145 */     double posDistance = Math.abs(distance);
/* 146 */     OffsetSegmentGenerator segGen = getSegGen(posDistance);
/* 147 */     if (inputPts.length <= 1) {
/* 148 */       computePointCurve(inputPts[0], segGen);
/*     */     } else {
/* 151 */       computeOffsetCurve(inputPts, isRightSide, segGen);
/*     */     } 
/* 153 */     Coordinate[] curvePts = segGen.getCoordinates();
/* 155 */     if (isRightSide)
/* 156 */       CoordinateArrays.reverse(curvePts); 
/* 157 */     return curvePts;
/*     */   }
/*     */   
/*     */   private static Coordinate[] copyCoordinates(Coordinate[] pts) {
/* 162 */     Coordinate[] copy = new Coordinate[pts.length];
/* 163 */     for (int i = 0; i < copy.length; i++)
/* 164 */       copy[i] = new Coordinate(pts[i]); 
/* 166 */     return copy;
/*     */   }
/*     */   
/*     */   private OffsetSegmentGenerator getSegGen(double distance) {
/* 171 */     return new OffsetSegmentGenerator(this.precisionModel, this.bufParams, distance);
/*     */   }
/*     */   
/*     */   private static double simplifyTolerance(double bufDistance) {
/* 192 */     return bufDistance / 100.0D;
/*     */   }
/*     */   
/*     */   private void computePointCurve(Coordinate pt, OffsetSegmentGenerator segGen) {
/* 196 */     switch (this.bufParams.getEndCapStyle()) {
/*     */       case 1:
/* 198 */         segGen.createCircle(pt);
/*     */         break;
/*     */       case 3:
/* 201 */         segGen.createSquare(pt);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeLineBufferCurve(Coordinate[] inputPts, OffsetSegmentGenerator segGen) {
/* 209 */     double distTol = simplifyTolerance(this.distance);
/* 213 */     Coordinate[] simp1 = BufferInputLineSimplifier.simplify(inputPts, distTol);
/* 217 */     int n1 = simp1.length - 1;
/* 218 */     segGen.initSideSegments(simp1[0], simp1[1], 1);
/* 219 */     for (int i = 2; i <= n1; i++)
/* 220 */       segGen.addNextSegment(simp1[i], true); 
/* 222 */     segGen.addLastSegment();
/* 224 */     segGen.addLineEndCap(simp1[n1 - 1], simp1[n1]);
/* 228 */     Coordinate[] simp2 = BufferInputLineSimplifier.simplify(inputPts, -distTol);
/* 231 */     int n2 = simp2.length - 1;
/* 234 */     segGen.initSideSegments(simp2[n2], simp2[n2 - 1], 1);
/* 235 */     for (int j = n2 - 2; j >= 0; j--)
/* 236 */       segGen.addNextSegment(simp2[j], true); 
/* 238 */     segGen.addLastSegment();
/* 240 */     segGen.addLineEndCap(simp2[1], simp2[0]);
/* 242 */     segGen.closeRing();
/*     */   }
/*     */   
/*     */   private void computeSingleSidedBufferCurve(Coordinate[] inputPts, boolean isRightSide, OffsetSegmentGenerator segGen) {
/* 274 */     double distTol = simplifyTolerance(this.distance);
/* 276 */     if (isRightSide) {
/* 278 */       segGen.addSegments(inputPts, true);
/* 282 */       Coordinate[] simp2 = BufferInputLineSimplifier.simplify(inputPts, -distTol);
/* 285 */       int n2 = simp2.length - 1;
/* 288 */       segGen.initSideSegments(simp2[n2], simp2[n2 - 1], 1);
/* 289 */       segGen.addFirstSegment();
/* 290 */       for (int i = n2 - 2; i >= 0; i--)
/* 291 */         segGen.addNextSegment(simp2[i], true); 
/*     */     } else {
/* 296 */       segGen.addSegments(inputPts, false);
/* 300 */       Coordinate[] simp1 = BufferInputLineSimplifier.simplify(inputPts, distTol);
/* 304 */       int n1 = simp1.length - 1;
/* 305 */       segGen.initSideSegments(simp1[0], simp1[1], 1);
/* 306 */       segGen.addFirstSegment();
/* 307 */       for (int i = 2; i <= n1; i++)
/* 308 */         segGen.addNextSegment(simp1[i], true); 
/*     */     } 
/* 311 */     segGen.addLastSegment();
/* 312 */     segGen.closeRing();
/*     */   }
/*     */   
/*     */   private void computeOffsetCurve(Coordinate[] inputPts, boolean isRightSide, OffsetSegmentGenerator segGen) {
/* 317 */     double distTol = simplifyTolerance(this.distance);
/* 319 */     if (isRightSide) {
/* 322 */       Coordinate[] simp2 = BufferInputLineSimplifier.simplify(inputPts, -distTol);
/* 325 */       int n2 = simp2.length - 1;
/* 328 */       segGen.initSideSegments(simp2[n2], simp2[n2 - 1], 1);
/* 329 */       segGen.addFirstSegment();
/* 330 */       for (int i = n2 - 2; i >= 0; i--)
/* 331 */         segGen.addNextSegment(simp2[i], true); 
/*     */     } else {
/* 337 */       Coordinate[] simp1 = BufferInputLineSimplifier.simplify(inputPts, distTol);
/* 341 */       int n1 = simp1.length - 1;
/* 342 */       segGen.initSideSegments(simp1[0], simp1[1], 1);
/* 343 */       segGen.addFirstSegment();
/* 344 */       for (int i = 2; i <= n1; i++)
/* 345 */         segGen.addNextSegment(simp1[i], true); 
/*     */     } 
/* 348 */     segGen.addLastSegment();
/*     */   }
/*     */   
/*     */   private void computeRingBufferCurve(Coordinate[] inputPts, int side, OffsetSegmentGenerator segGen) {
/* 354 */     double distTol = simplifyTolerance(this.distance);
/* 356 */     if (side == 2)
/* 357 */       distTol = -distTol; 
/* 358 */     Coordinate[] simp = BufferInputLineSimplifier.simplify(inputPts, distTol);
/* 361 */     int n = simp.length - 1;
/* 362 */     segGen.initSideSegments(simp[n - 1], simp[0], side);
/* 363 */     for (int i = 1; i <= n; i++) {
/* 364 */       boolean addStartPoint = (i != 1);
/* 365 */       segGen.addNextSegment(simp[i], addStartPoint);
/*     */     } 
/* 367 */     segGen.closeRing();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\OffsetCurveBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */