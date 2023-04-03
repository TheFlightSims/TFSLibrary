/*     */ package math.geom2d.circulinear.buffer;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.circulinear.BoundaryPolyCirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearBoundary2D;
/*     */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearContour2D;
/*     */ import math.geom2d.circulinear.CirculinearContourArray2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearCurveArray2D;
/*     */ import math.geom2d.circulinear.CirculinearCurves2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearElement2D;
/*     */ import math.geom2d.circulinear.GenericCirculinearDomain2D;
/*     */ import math.geom2d.circulinear.GenericCirculinearRing2D;
/*     */ import math.geom2d.circulinear.PolyCirculinearCurve2D;
/*     */ import math.geom2d.conic.Circle2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ import math.geom2d.point.PointSet2D;
/*     */ 
/*     */ public class BufferCalculator {
/*  40 */   private static BufferCalculator defaultInstance = null;
/*     */   
/*     */   private JoinFactory joinFactory;
/*     */   
/*     */   private CapFactory capFactory;
/*     */   
/*     */   public static BufferCalculator getDefaultInstance() {
/*  46 */     if (defaultInstance == null)
/*  47 */       defaultInstance = new BufferCalculator(); 
/*  48 */     return defaultInstance;
/*     */   }
/*     */   
/*     */   public BufferCalculator() {
/*  64 */     this.joinFactory = new RoundJoinFactory();
/*  65 */     this.capFactory = new RoundCapFactory();
/*     */   }
/*     */   
/*     */   public BufferCalculator(JoinFactory joinFactory, CapFactory capFactory) {
/*  72 */     this.joinFactory = joinFactory;
/*  73 */     this.capFactory = capFactory;
/*     */   }
/*     */   
/*     */   public CirculinearCurve2D createParallel(CirculinearCurve2D curve, double dist) {
/*  89 */     if (curve instanceof CirculinearContinuousCurve2D)
/*  90 */       return (CirculinearCurve2D)createContinuousParallel(
/*  91 */           (CirculinearContinuousCurve2D)curve, dist); 
/*  95 */     CirculinearCurveArray2D<CirculinearContinuousCurve2D> parallels = 
/*  96 */       new CirculinearCurveArray2D();
/* 100 */     Iterator<CirculinearContinuousCurve2D> iterator = curve.continuousCurves().iterator();
/*     */     while (iterator.hasNext()) {
/* 100 */       CirculinearContinuousCurve2D continuous = iterator.next();
/* 101 */       CirculinearContinuousCurve2D contParallel = 
/* 102 */         createContinuousParallel(continuous, dist);
/* 103 */       if (contParallel != null)
/* 104 */         parallels.add((Curve2D)contParallel); 
/*     */     } 
/* 108 */     return (CirculinearCurve2D)parallels;
/*     */   }
/*     */   
/*     */   public CirculinearBoundary2D createParallelBoundary(CirculinearBoundary2D boundary, double dist) {
/* 115 */     if (boundary instanceof CirculinearContour2D)
/* 116 */       return (CirculinearBoundary2D)createParallelContour((CirculinearContour2D)boundary, dist); 
/* 119 */     Collection<? extends CirculinearContour2D> contours = 
/* 120 */       boundary.continuousCurves();
/* 123 */     Collection<CirculinearContour2D> parallelContours = 
/* 124 */       new ArrayList<CirculinearContour2D>(contours.size());
/* 127 */     for (CirculinearContour2D contour : contours)
/* 128 */       parallelContours.add(contour.parallel(dist)); 
/* 131 */     return (CirculinearBoundary2D)CirculinearContourArray2D.create(parallelContours);
/*     */   }
/*     */   
/*     */   public CirculinearContour2D createParallelContour(CirculinearContour2D contour, double dist) {
/* 138 */     if (contour instanceof StraightLine2D)
/* 139 */       return (CirculinearContour2D)((StraightLine2D)contour).parallel(dist); 
/* 142 */     if (contour instanceof Circle2D)
/* 143 */       return (CirculinearContour2D)((Circle2D)contour).parallel(dist); 
/* 147 */     Collection<CirculinearContinuousCurve2D> parallelCurves = 
/* 148 */       getParallelElements((CirculinearContinuousCurve2D)contour, dist);
/* 151 */     return (CirculinearContour2D)BoundaryPolyCirculinearCurve2D.create(parallelCurves, 
/* 152 */         contour.isClosed());
/*     */   }
/*     */   
/*     */   public CirculinearContinuousCurve2D createContinuousParallel(CirculinearContinuousCurve2D curve, double dist) {
/* 163 */     if (curve instanceof CirculinearElement2D)
/* 164 */       return (CirculinearContinuousCurve2D)((CirculinearElement2D)curve).parallel(dist); 
/* 168 */     Collection<CirculinearContinuousCurve2D> parallelCurves = 
/* 169 */       getParallelElements(curve, dist);
/* 173 */     return (CirculinearContinuousCurve2D)PolyCirculinearCurve2D.create(parallelCurves, curve.isClosed());
/*     */   }
/*     */   
/*     */   private Collection<CirculinearContinuousCurve2D> getParallelElements(CirculinearContinuousCurve2D curve, double dist) {
/* 180 */     Collection<? extends CirculinearElement2D> elements = 
/* 181 */       curve.smoothPieces();
/* 183 */     Iterator<? extends CirculinearElement2D> iterator = 
/* 184 */       elements.iterator();
/* 187 */     CirculinearElement2D previous = null;
/* 188 */     CirculinearElement2D current = null;
/* 191 */     ArrayList<CirculinearContinuousCurve2D> parallelCurves = 
/* 192 */       new ArrayList<CirculinearContinuousCurve2D>();
/* 195 */     if (!iterator.hasNext())
/* 196 */       return parallelCurves; 
/* 199 */     current = iterator.next();
/* 200 */     CirculinearElement2D parallel = current.parallel(dist);
/* 201 */     parallelCurves.add(parallel);
/* 205 */     while (iterator.hasNext()) {
/* 207 */       previous = current;
/* 208 */       current = iterator.next();
/* 211 */       CirculinearContinuousCurve2D join = this.joinFactory.createJoin(previous, current, dist);
/* 212 */       if (join.length() > 0.0D)
/* 213 */         parallelCurves.add(join); 
/* 216 */       parallelCurves.add(current.parallel(dist));
/*     */     } 
/* 220 */     if (curve.isClosed()) {
/* 221 */       previous = current;
/* 222 */       current = elements.iterator().next();
/* 224 */       CirculinearContinuousCurve2D join = this.joinFactory.createJoin(previous, current, dist);
/* 225 */       if (join.length() > 0.0D)
/* 226 */         parallelCurves.add(join); 
/*     */     } 
/* 229 */     return parallelCurves;
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D computeBuffer(CirculinearCurve2D curve, double dist) {
/* 247 */     ArrayList<CirculinearContour2D> contours = 
/* 248 */       new ArrayList<CirculinearContour2D>();
/* 251 */     for (CirculinearContinuousCurve2D cont : curve.continuousCurves()) {
/* 254 */       Iterator<CirculinearContinuousCurve2D> iterator = CirculinearCurves2D.splitContinuousCurve(cont).iterator();
/*     */       while (iterator.hasNext()) {
/* 254 */         CirculinearContinuousCurve2D splitted = iterator.next();
/* 256 */         contours.addAll(computeBufferSimpleCurve(splitted, dist));
/*     */       } 
/*     */     } 
/* 261 */     contours = new ArrayList<CirculinearContour2D>(
/* 262 */         CirculinearCurves2D.splitIntersectingContours(contours));
/* 265 */     ArrayList<CirculinearContour2D> contours2 = 
/* 266 */       new ArrayList<CirculinearContour2D>(contours.size());
/* 270 */     for (CirculinearContour2D contour : contours) {
/* 273 */       Collection<Point2D> intersects = CirculinearCurves2D.findIntersections(curve, (CirculinearCurve2D)contour);
/* 276 */       Collection<Point2D> vertices = curve.singularPoints();
/* 277 */       vertices = curve.vertices();
/* 278 */       intersects.removeAll(vertices);
/* 280 */       if (intersects.size() > 0)
/*     */         continue; 
/* 285 */       double distCurves = 
/* 286 */         getDistanceCurveSingularPoints(curve, (CirculinearCurve2D)contour);
/* 287 */       if (distCurves < dist - 1.0E-12D)
/*     */         continue; 
/* 291 */       contours2.add(contour);
/*     */     } 
/* 296 */     return (CirculinearDomain2D)new GenericCirculinearDomain2D(
/* 297 */         (CirculinearBoundary2D)CirculinearContourArray2D.create(contours2));
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D computeBuffer(PointSet2D set, double dist) {
/* 306 */     Collection<CirculinearContour2D> contours = 
/* 307 */       new ArrayList<CirculinearContour2D>(set.size());
/* 310 */     for (Point2D point : set)
/* 311 */       contours.add(new Circle2D(point, Math.abs(dist), (dist > 0.0D))); 
/* 315 */     contours = CirculinearCurves2D.splitIntersectingContours(contours);
/* 318 */     ArrayList<CirculinearContour2D> contours2 = 
/* 319 */       new ArrayList<CirculinearContour2D>(contours.size());
/* 320 */     for (CirculinearContour2D ring : contours) {
/* 324 */       double minDist = CirculinearCurves2D.getDistanceCurvePoints(
/* 325 */           (CirculinearCurve2D)ring, set.points());
/* 326 */       if (minDist < dist - 1.0E-12D)
/*     */         continue; 
/* 330 */       contours2.add(ring);
/*     */     } 
/* 333 */     return (CirculinearDomain2D)new GenericCirculinearDomain2D(
/* 334 */         (CirculinearBoundary2D)CirculinearContourArray2D.create(contours2));
/*     */   }
/*     */   
/*     */   private Collection<? extends CirculinearContour2D> computeBufferSimpleCurve(CirculinearContinuousCurve2D curve, double d) {
/* 344 */     Collection<CirculinearContour2D> contours = 
/* 345 */       new ArrayList<CirculinearContour2D>(2);
/* 349 */     CirculinearContinuousCurve2D parallel1 = createContinuousParallel(curve, d);
/* 350 */     CirculinearContinuousCurve2D parallel2 = createContinuousParallel(curve, -d).reverse();
/* 352 */     if (curve.isClosed()) {
/* 354 */       contours.add(convertCurveToBoundary(parallel1));
/* 355 */       contours.add(convertCurveToBoundary(parallel2));
/*     */     } else {
/* 358 */       contours.addAll(createSingleContourFromTwoParallels(parallel1, parallel2));
/*     */     } 
/* 362 */     Collection<CirculinearContour2D> contours2 = 
/* 363 */       removeIntersectingContours(contours, (CirculinearCurve2D)curve, d);
/* 366 */     return contours2;
/*     */   }
/*     */   
/*     */   private Collection<CirculinearContour2D> createSingleContourFromTwoParallels(CirculinearContinuousCurve2D curve1, CirculinearContinuousCurve2D curve2) {
/* 379 */     ArrayList<CirculinearContour2D> contours = 
/* 380 */       new ArrayList<CirculinearContour2D>();
/* 385 */     if (curve1 != null && curve2 != null) {
/* 387 */       ArrayList<CirculinearElement2D> elements = 
/* 388 */         new ArrayList<CirculinearElement2D>();
/* 391 */       boolean b0 = !Curves2D.isLeftInfinite((Curve2D)curve1);
/* 392 */       boolean b1 = !Curves2D.isRightInfinite((Curve2D)curve1);
/* 394 */       if (b0 && b1) {
/* 398 */         Point2D p11 = curve1.firstPoint();
/* 399 */         Point2D p12 = curve1.lastPoint();
/* 400 */         Point2D p21 = curve2.firstPoint();
/* 401 */         Point2D p22 = curve2.lastPoint();
/* 404 */         elements.addAll(curve1.smoothPieces());
/* 405 */         CirculinearContinuousCurve2D cap = this.capFactory.createCap(p12, p21);
/* 406 */         elements.addAll(cap.smoothPieces());
/* 407 */         elements.addAll(curve2.smoothPieces());
/* 408 */         cap = this.capFactory.createCap(p22, p11);
/* 409 */         elements.addAll(cap.smoothPieces());
/* 412 */         contours.add(new GenericCirculinearRing2D(elements));
/* 414 */       } else if (!b0 && !b1) {
/* 418 */         contours.add(convertCurveToBoundary(curve1));
/* 419 */         contours.add(convertCurveToBoundary(curve2));
/* 421 */       } else if (b0 && !b1) {
/* 426 */         Point2D p11 = curve1.firstPoint();
/* 427 */         Point2D p22 = curve2.lastPoint();
/* 430 */         elements.addAll(curve2.smoothPieces());
/* 431 */         CirculinearContinuousCurve2D cap = this.capFactory.createCap(p22, p11);
/* 432 */         elements.addAll(cap.smoothPieces());
/* 433 */         elements.addAll(curve1.smoothPieces());
/* 436 */         contours.add(new GenericCirculinearRing2D(elements));
/* 438 */       } else if (b1 && !b0) {
/* 443 */         Point2D p12 = curve1.lastPoint();
/* 444 */         Point2D p21 = curve2.firstPoint();
/* 447 */         elements.addAll(curve1.smoothPieces());
/* 448 */         CirculinearContinuousCurve2D cap = this.capFactory.createCap(p12, p21);
/* 449 */         elements.addAll(cap.smoothPieces());
/* 450 */         elements.addAll(curve2.smoothPieces());
/* 453 */         contours.add(new GenericCirculinearRing2D(elements));
/*     */       } 
/*     */     } 
/* 458 */     return contours;
/*     */   }
/*     */   
/*     */   private Collection<CirculinearContour2D> removeIntersectingContours(Collection<CirculinearContour2D> contours, CirculinearCurve2D curve, double d) {
/* 465 */     ArrayList<CirculinearContour2D> contours2 = 
/* 466 */       new ArrayList<CirculinearContour2D>();
/* 469 */     for (CirculinearContour2D contour : contours) {
/* 472 */       Iterator<CirculinearContinuousCurve2D> iterator = CirculinearCurves2D.splitContinuousCurve((CirculinearContinuousCurve2D)contour).iterator();
/*     */       while (iterator.hasNext()) {
/* 472 */         CirculinearContinuousCurve2D splitted = iterator.next();
/* 477 */         double dist = CirculinearCurves2D.getDistanceCurvePoints(
/* 478 */             curve, splitted.singularPoints());
/* 481 */         if (dist - d < -1.0E-12D)
/*     */           continue; 
/* 485 */         contours2.add(convertCurveToBoundary(splitted));
/*     */       } 
/*     */     } 
/* 489 */     return contours2;
/*     */   }
/*     */   
/*     */   private CirculinearContour2D convertCurveToBoundary(CirculinearContinuousCurve2D curve) {
/* 501 */     if (curve instanceof CirculinearContour2D)
/* 502 */       return (CirculinearContour2D)curve; 
/* 505 */     if (curve.isClosed())
/* 506 */       return (CirculinearContour2D)GenericCirculinearRing2D.create(curve.smoothPieces()); 
/* 508 */     return (CirculinearContour2D)BoundaryPolyCirculinearCurve2D.create(curve.smoothPieces());
/*     */   }
/*     */   
/*     */   private double getDistanceCurveSingularPoints(CirculinearCurve2D ref, CirculinearCurve2D curve) {
/* 514 */     Collection<Point2D> points = curve.singularPoints();
/* 517 */     if (points.isEmpty()) {
/* 518 */       points = new ArrayList<Point2D>();
/* 519 */       double t = Curves2D.choosePosition(curve.t0(), curve.t1());
/* 520 */       points.add(curve.point(t));
/*     */     } 
/* 524 */     double minDist = Double.MAX_VALUE;
/* 525 */     for (Point2D point : points)
/* 526 */       minDist = Math.min(minDist, ref.distance(point)); 
/* 528 */     return minDist;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\buffer\BufferCalculator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */