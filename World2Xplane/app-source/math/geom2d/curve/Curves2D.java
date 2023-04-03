/*     */ package math.geom2d.curve;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import math.geom2d.Angle2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ 
/*     */ public abstract class Curves2D {
/*     */   public static double toUnitSegment(double t, double t0, double t1) {
/*  49 */     if (t <= t0)
/*  50 */       return 0.0D; 
/*  51 */     if (t >= t1)
/*  52 */       return 1.0D; 
/*  54 */     if (t0 == Double.NEGATIVE_INFINITY && t1 == Double.POSITIVE_INFINITY)
/*  55 */       return Math.atan(t) / Math.PI + 0.5D; 
/*  57 */     if (t0 == Double.NEGATIVE_INFINITY)
/*  58 */       return Math.atan(t - t1) * 2.0D / Math.PI + 1.0D; 
/*  60 */     if (t1 == Double.POSITIVE_INFINITY)
/*  61 */       return Math.atan(t - t0) * 2.0D / Math.PI; 
/*  64 */     return (t - t0) / (t1 - t0);
/*     */   }
/*     */   
/*     */   public static double fromUnitSegment(double t, double t0, double t1) {
/*  80 */     if (t <= 0.0D)
/*  81 */       return t0; 
/*  82 */     if (t >= 1.0D)
/*  83 */       return t1; 
/*  85 */     if (t0 == Double.NEGATIVE_INFINITY && t1 == Double.POSITIVE_INFINITY)
/*  86 */       return Math.tan((t - 0.5D) * Math.PI); 
/*  88 */     if (t0 == Double.NEGATIVE_INFINITY)
/*  89 */       return Math.tan((t - 1.0D) * Math.PI / 2.0D) + t1; 
/*  91 */     if (t1 == Double.POSITIVE_INFINITY)
/*  92 */       return Math.tan(t * Math.PI / 2.0D) + t0; 
/*  95 */     return t * (t1 - t0) + t0;
/*     */   }
/*     */   
/*     */   public static CurveSet2D<? extends Curve2D> clipCurve(Curve2D curve, Box2D box) {
/* 108 */     if (curve instanceof ContinuousCurve2D)
/* 109 */       return (CurveSet2D)clipContinuousCurve((ContinuousCurve2D)curve, box); 
/* 112 */     if (curve instanceof CurveSet2D)
/* 113 */       return clipCurveSet((CurveSet2D)curve, box); 
/* 116 */     System.err.println("Unknown curve class in Box2D.clipCurve()");
/* 117 */     return new CurveArray2D<Curve2D>();
/*     */   }
/*     */   
/*     */   public static CurveSet2D<? extends Curve2D> clipCurveSet(CurveSet2D<?> curveSet, Box2D box) {
/* 126 */     CurveArray2D<Curve2D> result = new CurveArray2D<Curve2D>();
/* 130 */     for (Curve2D curve : curveSet) {
/* 131 */       CurveSet2D<?> clipped = clipCurve(curve, box);
/* 132 */       for (Curve2D clippedPart : clipped)
/* 133 */         result.add(clippedPart); 
/*     */     } 
/* 137 */     return result;
/*     */   }
/*     */   
/*     */   public static CurveSet2D<ContinuousCurve2D> clipContinuousCurve(ContinuousCurve2D curve, Box2D box) {
/* 166 */     CurveArray2D<ContinuousCurve2D> res = new CurveArray2D<ContinuousCurve2D>();
/* 171 */     ArrayList<Point2D> points = new ArrayList<Point2D>();
/* 174 */     for (LinearShape2D edge : box.edges())
/* 175 */       points.addAll(curve.intersections(edge)); 
/* 179 */     SortedSet<Double> set = new TreeSet<Double>();
/* 180 */     for (Point2D p : points)
/* 181 */       set.add(new Double(curve.position(p))); 
/* 184 */     Iterator<Double> iter = set.iterator();
/* 189 */     int nInter = set.size();
/* 190 */     double[] positions = new double[nInter + 2];
/* 191 */     double[] between = new double[nInter + 1];
/* 194 */     positions[0] = curve.t0();
/*     */     int i;
/* 195 */     for (i = 0; i < nInter; i++)
/* 196 */       positions[i + 1] = ((Double)iter.next()).doubleValue(); 
/* 197 */     positions[nInter + 1] = curve.t1();
/* 200 */     for (i = 0; i < nInter + 1; i++)
/* 201 */       between[i] = choosePosition(positions[i], positions[i + 1]); 
/* 204 */     ArrayList<Double> toRemove = new ArrayList<Double>();
/* 208 */     for (int j = 0; j < nInter; j++) {
/* 209 */       Point2D p1 = curve.point(between[j]);
/* 210 */       Point2D p2 = curve.point(between[j + 1]);
/* 211 */       boolean b1 = box.contains(p1);
/* 212 */       boolean b2 = box.contains(p2);
/* 213 */       if (b1 == b2)
/* 214 */         toRemove.add(Double.valueOf(positions[j + 1])); 
/*     */     } 
/* 218 */     set.removeAll(toRemove);
/* 221 */     iter = set.iterator();
/* 227 */     if (set.size() == 0) {
/*     */       Point2D point;
/* 230 */       if (curve.isBounded()) {
/* 231 */         point = curve.firstPoint();
/*     */       } else {
/* 233 */         double pos = choosePosition(curve.t0(), curve.t1());
/* 234 */         point = curve.point(pos);
/*     */       } 
/* 238 */       if (box.contains(point))
/* 239 */         res.add(curve); 
/* 240 */       return res;
/*     */     } 
/* 246 */     boolean inside = false;
/* 247 */     boolean touch = false;
/* 250 */     double t0 = curve.t0();
/* 251 */     if (isLeftInfinite(curve)) {
/* 253 */       double pos = choosePosition(t0, ((Double)set.iterator().next()).doubleValue());
/* 254 */       inside = box.contains(curve.point(pos));
/*     */     } else {
/* 257 */       Point2D point = curve.firstPoint();
/* 258 */       inside = box.contains(point);
/* 262 */       if (box.boundary().contains(point)) {
/* 263 */         touch = true;
/* 265 */         double pos = choosePosition(t0, ((Double)iter.next()).doubleValue());
/* 266 */         while (Math.abs(pos - t0) < 1.0E-12D && iter.hasNext())
/* 267 */           pos = choosePosition(t0, ((Double)iter.next()).doubleValue()); 
/* 268 */         if (Math.abs(pos - t0) < 1.0E-12D)
/* 269 */           pos = choosePosition(t0, curve.t1()); 
/* 270 */         point = curve.point(pos);
/* 273 */         set.remove(Double.valueOf(t0));
/* 277 */         if (box.contains(point)) {
/* 278 */           pos = ((Double)set.iterator().next()).doubleValue();
/* 279 */           res.add(curve.subCurve(t0, pos));
/* 280 */           set.remove(Double.valueOf(pos));
/*     */         } 
/* 284 */         iter = set.iterator();
/* 286 */         inside = false;
/*     */       } 
/*     */     } 
/* 291 */     double pos0 = Double.NaN;
/* 292 */     if (inside && !touch)
/* 293 */       if (curve.isClosed()) {
/* 294 */         pos0 = ((Double)iter.next()).doubleValue();
/*     */       } else {
/* 296 */         res.add(curve.subCurve(curve.t0(), ((Double)iter.next()).doubleValue()));
/*     */       }  
/* 301 */     while (iter.hasNext()) {
/* 302 */       double pos2, pos1 = ((Double)iter.next()).doubleValue();
/* 303 */       if (iter.hasNext()) {
/* 304 */         pos2 = ((Double)iter.next()).doubleValue();
/*     */       } else {
/* 306 */         pos2 = (curve.isClosed() && !touch) ? pos0 : curve.t1();
/*     */       } 
/* 307 */       res.add(curve.subCurve(pos1, pos2));
/*     */     } 
/* 310 */     return res;
/*     */   }
/*     */   
/*     */   public static CurveSet2D<SmoothCurve2D> clipSmoothCurve(SmoothCurve2D curve, Box2D box) {
/* 319 */     CurveArray2D<SmoothCurve2D> result = new CurveArray2D<SmoothCurve2D>();
/* 320 */     for (ContinuousCurve2D cont : clipContinuousCurve(curve, 
/* 321 */         box)) {
/* 322 */       if (cont instanceof SmoothCurve2D)
/* 323 */         result.add((SmoothCurve2D)cont); 
/*     */     } 
/* 325 */     return result;
/*     */   }
/*     */   
/*     */   public static CurveSet2D<SmoothCurve2D> clipSmoothCurve(SmoothCurve2D curve, StraightLine2D line) {
/*     */     Point2D point1;
/* 336 */     ArrayList<Point2D> list = new ArrayList<Point2D>();
/* 337 */     list.addAll(curve.intersections((LinearShape2D)line));
/* 342 */     SortedSet<Double> set = new TreeSet<Double>();
/* 344 */     Vector2D vector = line.direction();
/* 345 */     for (Point2D point : list) {
/* 348 */       double position = curve.project(point);
/* 351 */       Vector2D tangent = curve.tangent(position);
/* 352 */       if (Vector2D.isColinear(tangent, vector)) {
/* 354 */         double curv = curve.curvature(position);
/* 355 */         if (Math.abs(curv) > 1.0E-12D)
/*     */           continue; 
/*     */       } 
/* 358 */       set.add(new Double(position));
/*     */     } 
/* 362 */     CurveArray2D<SmoothCurve2D> res = new CurveArray2D<SmoothCurve2D>();
/* 366 */     if (Double.isInfinite(curve.t0())) {
/* 367 */       point1 = curve.point(-1000.0D);
/*     */     } else {
/* 369 */       point1 = curve.firstPoint();
/*     */     } 
/* 373 */     Iterator<Double> iter = set.iterator();
/* 377 */     if (!iter.hasNext()) {
/* 380 */       double t0 = curve.t0();
/* 381 */       if (t0 == Double.NEGATIVE_INFINITY)
/* 382 */         t0 = -100.0D; 
/* 383 */       while (line.contains(point1)) {
/* 384 */         double t1 = curve.t1();
/* 385 */         if (t1 == Double.POSITIVE_INFINITY)
/* 386 */           t1 = 100.0D; 
/* 387 */         t0 = (t0 + t1) / 2.0D;
/* 388 */         point1 = curve.point(t0);
/*     */       } 
/* 390 */       if (line.signedDistance(point1) < 0.0D)
/* 391 */         res.add(curve); 
/* 392 */       return res;
/*     */     } 
/* 396 */     if (line.signedDistance(point1) < 0.0D && !line.contains(point1)) {
/* 397 */       double pos1 = ((Double)iter.next()).doubleValue();
/* 398 */       res.add(curve.subCurve(curve.t0(), pos1));
/*     */     } 
/* 402 */     while (iter.hasNext()) {
/* 403 */       double pos2, pos1 = ((Double)iter.next()).doubleValue();
/* 404 */       if (iter.hasNext()) {
/* 405 */         pos2 = ((Double)iter.next()).doubleValue();
/*     */       } else {
/* 407 */         pos2 = curve.t1();
/*     */       } 
/* 408 */       res.add(curve.subCurve(pos1, pos2));
/*     */     } 
/* 411 */     return res;
/*     */   }
/*     */   
/*     */   public static int findNextCurveIndex(double[] positions, double pos) {
/* 415 */     int ind = -1;
/* 416 */     double posMin = Double.MAX_VALUE;
/*     */     int i;
/* 417 */     for (i = 0; i < positions.length; i++) {
/* 419 */       if (!Double.isNaN(positions[i]))
/* 422 */         if (positions[i] - pos >= 1.0E-12D)
/* 426 */           if (positions[i] < posMin) {
/* 427 */             ind = i;
/* 428 */             posMin = positions[i];
/*     */           }   
/*     */     } 
/* 432 */     if (ind != -1)
/* 433 */       return ind; 
/* 437 */     for (i = 0; i < positions.length; i++) {
/* 438 */       if (!Double.isNaN(positions[i]))
/* 440 */         if (positions[i] - posMin < 1.0E-12D) {
/* 441 */           ind = i;
/* 442 */           posMin = positions[i];
/*     */         }  
/*     */     } 
/* 445 */     return ind;
/*     */   }
/*     */   
/*     */   public static double choosePosition(double t0, double t1) {
/* 459 */     if (Double.isInfinite(t0)) {
/* 460 */       if (Double.isInfinite(t1))
/* 461 */         return 0.0D; 
/* 462 */       return t1 - 10.0D;
/*     */     } 
/* 465 */     if (Double.isInfinite(t1))
/* 466 */       return t0 + 10.0D; 
/* 468 */     return (t0 + t1) / 2.0D;
/*     */   }
/*     */   
/*     */   public static boolean isLeftInfinite(Curve2D curve) {
/* 473 */     if (curve.isBounded())
/* 474 */       return false; 
/* 477 */     ContinuousCurve2D cont = 
/* 478 */       curve.continuousCurves().iterator().next();
/* 479 */     SmoothCurve2D smooth = 
/* 480 */       cont.smoothPieces().iterator().next();
/* 483 */     return Double.isInfinite(smooth.t0());
/*     */   }
/*     */   
/*     */   public static boolean isRightInfinite(Curve2D curve) {
/* 488 */     if (curve.isBounded())
/* 489 */       return false; 
/* 492 */     SmoothCurve2D lastCurve = null;
/* 493 */     for (ContinuousCurve2D cont : curve.continuousCurves()) {
/* 494 */       for (SmoothCurve2D smooth : cont.smoothPieces())
/* 495 */         lastCurve = smooth; 
/*     */     } 
/* 498 */     return Double.isInfinite(lastCurve.t1());
/*     */   }
/*     */   
/*     */   public static ContinuousCurve2D getFirstContinuousCurve(Curve2D curve) {
/* 502 */     if (curve instanceof ContinuousCurve2D)
/* 503 */       return (ContinuousCurve2D)curve; 
/* 505 */     Collection<? extends ContinuousCurve2D> curves = 
/* 506 */       curve.continuousCurves();
/* 507 */     if (curves.size() == 0)
/* 508 */       return null; 
/* 510 */     return curves.iterator().next();
/*     */   }
/*     */   
/*     */   public static ContinuousCurve2D getLastContinuousCurve(Curve2D curve) {
/* 514 */     if (curve instanceof ContinuousCurve2D)
/* 515 */       return (ContinuousCurve2D)curve; 
/* 516 */     ContinuousCurve2D res = null;
/* 517 */     for (ContinuousCurve2D continuous : curve.continuousCurves())
/* 518 */       res = continuous; 
/* 519 */     return res;
/*     */   }
/*     */   
/*     */   public static SmoothCurve2D getFirstSmoothCurve(Curve2D curve) {
/* 523 */     if (curve instanceof SmoothCurve2D)
/* 524 */       return (SmoothCurve2D)curve; 
/* 527 */     ContinuousCurve2D continuous = getFirstContinuousCurve(curve);
/* 528 */     if (continuous == null)
/* 529 */       return null; 
/* 531 */     Collection<? extends SmoothCurve2D> curves = 
/* 532 */       continuous.smoothPieces();
/* 533 */     if (curves.size() == 0)
/* 534 */       return null; 
/* 536 */     return curves.iterator().next();
/*     */   }
/*     */   
/*     */   public static SmoothCurve2D getLastSmoothCurve(Curve2D curve) {
/* 540 */     if (curve instanceof SmoothCurve2D)
/* 541 */       return (SmoothCurve2D)curve; 
/* 544 */     ContinuousCurve2D continuous = getLastContinuousCurve(curve);
/* 545 */     SmoothCurve2D res = null;
/* 546 */     for (SmoothCurve2D smooth : continuous.smoothPieces())
/* 547 */       res = smooth; 
/* 548 */     return res;
/*     */   }
/*     */   
/*     */   public enum JunctionType {
/* 552 */     SALIENT, REENTRANT, FLAT;
/*     */   }
/*     */   
/*     */   public static JunctionType getJunctionType(Curve2D prev, Curve2D next) {
/* 562 */     SmoothCurve2D smoothPrev = getLastSmoothCurve(prev);
/* 563 */     SmoothCurve2D smoothNext = getFirstSmoothCurve(next);
/* 566 */     Vector2D v1 = computeTangent(smoothPrev, smoothPrev.t1());
/* 567 */     Vector2D v2 = computeTangent(smoothNext, smoothNext.t0());
/* 570 */     double diff = Angle2D.angle(v1, v2);
/* 571 */     double eps = 1.0E-12D;
/* 572 */     if (diff < eps || diff > 6.283185307179586D - eps)
/* 573 */       return JunctionType.FLAT; 
/* 576 */     if (diff < Math.PI - eps)
/* 578 */       return JunctionType.SALIENT; 
/* 581 */     if (diff > Math.PI + eps)
/* 583 */       return JunctionType.REENTRANT; 
/* 587 */     double kappaPrev = smoothPrev.curvature(smoothPrev.t1());
/* 588 */     double kappaNext = smoothNext.curvature(smoothNext.t0());
/* 591 */     double sp = Math.signum(kappaPrev);
/* 592 */     double sn = Math.signum(kappaNext);
/* 595 */     if (sn * sp > 0.0D) {
/* 596 */       if (sn > 0.0D)
/* 597 */         return JunctionType.REENTRANT; 
/* 599 */       return JunctionType.SALIENT;
/*     */     } 
/* 603 */     if (sp == 0.0D) {
/* 604 */       if (sn < 0.0D)
/* 605 */         return JunctionType.SALIENT; 
/* 606 */       if (sn > 0.0D)
/* 607 */         return JunctionType.REENTRANT; 
/* 610 */       throw new IllegalArgumentException("colinear lines...");
/*     */     } 
/* 612 */     if (sn == 0.0D) {
/* 613 */       if (sp < 0.0D)
/* 614 */         return JunctionType.SALIENT; 
/* 615 */       if (sp > 0.0D)
/* 616 */         return JunctionType.REENTRANT; 
/*     */     } 
/* 622 */     if (sp == 1.0D && sn == -1.0D)
/* 623 */       return (Math.abs(kappaPrev) < Math.abs(kappaNext)) ? 
/* 624 */         JunctionType.SALIENT : JunctionType.REENTRANT; 
/* 625 */     if (sp == -1.0D && sn == 1.0D)
/* 626 */       return (Math.abs(kappaPrev) > Math.abs(kappaNext)) ? 
/* 627 */         JunctionType.SALIENT : JunctionType.REENTRANT; 
/* 630 */     throw new RuntimeException("Could not determine junction type");
/*     */   }
/*     */   
/*     */   private static Vector2D computeTangent(ContinuousCurve2D curve, double pos) {
/* 638 */     if (curve instanceof SmoothCurve2D)
/* 639 */       return ((SmoothCurve2D)curve).tangent(pos); 
/* 642 */     if (curve instanceof CurveSet2D) {
/* 643 */       CurveSet2D<?> curveSet = (CurveSet2D)curve;
/* 644 */       double pos2 = curveSet.localPosition(pos);
/* 645 */       Curve2D subCurve = (Curve2D)curveSet.childCurve(pos);
/* 646 */       return computeTangent((ContinuousCurve2D)subCurve, pos2);
/*     */     } 
/* 649 */     throw new IllegalArgumentException(
/* 650 */         "Unknown type of curve: should be either continuous or curveset");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\curve\Curves2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */