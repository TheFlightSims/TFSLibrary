/*     */ package math.geom2d.circulinear;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.conic.Circle2D;
/*     */ import math.geom2d.conic.CircularShape2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.curve.SmoothCurve2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ 
/*     */ public class CirculinearCurves2D {
/*     */   public static CirculinearCurve2D convert(Curve2D curve) {
/*  44 */     if (curve instanceof CirculinearCurve2D)
/*  45 */       return (CirculinearCurve2D)curve; 
/*  48 */     if (curve instanceof ContinuousCurve2D) {
/*  50 */       ContinuousCurve2D continuous = (ContinuousCurve2D)curve;
/*  51 */       Collection<? extends SmoothCurve2D> smoothPieces = 
/*  52 */         continuous.smoothPieces();
/*  55 */       ArrayList<CirculinearElement2D> elements = new ArrayList<CirculinearElement2D>(
/*  56 */           smoothPieces.size());
/*  59 */       for (SmoothCurve2D smooth : smoothPieces) {
/*  60 */         if (smooth instanceof CirculinearElement2D) {
/*  61 */           elements.add((CirculinearElement2D)smooth);
/*     */           continue;
/*     */         } 
/*  63 */         throw new NonCirculinearClassException(smooth);
/*     */       } 
/*  67 */       return new PolyCirculinearCurve2D<CirculinearElement2D>(elements);
/*     */     } 
/*  71 */     if (curve instanceof CurveSet2D) {
/*  73 */       CurveSet2D<?> set = (CurveSet2D)curve;
/*  74 */       Collection<? extends ContinuousCurve2D> continuousCurves = set
/*  75 */         .continuousCurves();
/*  78 */       ArrayList<CirculinearContinuousCurve2D> curves = 
/*  79 */         new ArrayList<CirculinearContinuousCurve2D>(continuousCurves.size());
/*  82 */       for (ContinuousCurve2D continuous : continuousCurves) {
/*  83 */         if (continuous instanceof CirculinearContinuousCurve2D) {
/*  84 */           curves.add((CirculinearContinuousCurve2D)continuous);
/*     */           continue;
/*     */         } 
/*  86 */         curves.add((CirculinearContinuousCurve2D)convert((Curve2D)continuous));
/*     */       } 
/*  90 */       return CirculinearCurveArray2D.create(curves);
/*     */     } 
/*  94 */     return null;
/*     */   }
/*     */   
/*     */   public static double getLength(CurveSet2D<? extends CirculinearCurve2D> curve, double pos) {
/* 105 */     double length = 0.0D;
/* 108 */     int index = curve.curveIndex(pos);
/* 109 */     for (int i = 0; i < index; i++)
/* 110 */       length += ((CirculinearCurve2D)curve.get(i)).length(); 
/* 113 */     if (index < curve.size()) {
/* 114 */       double pos2 = curve.localPosition(pos - (2 * index));
/* 115 */       length += ((CirculinearCurve2D)curve.get(index)).length(pos2);
/*     */     } 
/* 119 */     return length;
/*     */   }
/*     */   
/*     */   public static double getPosition(CurveSet2D<? extends CirculinearCurve2D> curveSet, double length) {
/* 131 */     double pos = 0.0D;
/* 134 */     int index = 0;
/* 137 */     double cumLength = getLength(curveSet, curveSet.t0());
/* 140 */     for (CirculinearCurve2D curve : curveSet.curves()) {
/* 142 */       double curveLength = curve.length();
/* 145 */       if (cumLength + curveLength < length) {
/* 146 */         cumLength += curveLength;
/* 147 */         index++;
/*     */         continue;
/*     */       } 
/* 150 */       double pos2 = curve.position(length - cumLength);
/* 151 */       pos = curveSet.globalPosition(index, pos2);
/*     */       break;
/*     */     } 
/* 157 */     return pos;
/*     */   }
/*     */   
/*     */   public static Collection<Point2D> findSelfIntersections(CirculinearCurve2D curve) {
/* 170 */     ArrayList<CirculinearElement2D> elements = new ArrayList<CirculinearElement2D>();
/* 173 */     for (CirculinearContinuousCurve2D cont : curve.continuousCurves())
/* 174 */       elements.addAll(cont.smoothPieces()); 
/* 177 */     ArrayList<Point2D> result = new ArrayList<Point2D>(0);
/* 180 */     int n = elements.size();
/* 181 */     for (int i = 0; i < n - 1; i++) {
/* 182 */       CirculinearElement2D elem1 = elements.get(i);
/* 183 */       for (int j = i; j < n; j++) {
/* 184 */         CirculinearElement2D elem2 = elements.get(j);
/* 186 */         for (Point2D inter : findIntersections(elem1, elem2)) {
/* 188 */           if (isCommonVertex(inter, elem1, elem2))
/*     */             continue; 
/* 191 */           result.add(inter);
/*     */         } 
/*     */       } 
/*     */     } 
/* 197 */     return result;
/*     */   }
/*     */   
/*     */   public static double[][] locateSelfIntersections(CurveSet2D<? extends CirculinearElement2D> curve) {
/* 204 */     ArrayList<Double> list1 = new ArrayList<Double>(0);
/* 205 */     ArrayList<Double> list2 = new ArrayList<Double>(0);
/* 209 */     int n = curve.size();
/* 210 */     for (int i = 0; i < n - 1; i++) {
/* 211 */       CirculinearElement2D elem1 = (CirculinearElement2D)curve.get(i);
/* 212 */       for (int k = i + 1; k < n; k++) {
/* 213 */         CirculinearElement2D elem2 = (CirculinearElement2D)curve.get(k);
/* 215 */         for (Point2D inter : findIntersections(elem1, elem2)) {
/* 217 */           if (isCommonVertex(inter, elem1, elem2))
/*     */             continue; 
/* 221 */           double dt = Curves2D.toUnitSegment(elem1.position(inter), 
/* 222 */               elem1.t0(), elem1.t1());
/* 223 */           list1.add(Double.valueOf((2 * i) + dt));
/* 225 */           dt = Curves2D.toUnitSegment(elem2.position(inter), 
/* 226 */               elem2.t0(), elem2.t1());
/* 227 */           list2.add(Double.valueOf((2 * k) + dt));
/*     */         } 
/*     */       } 
/*     */     } 
/* 233 */     int np = list1.size();
/* 234 */     double[][] result = new double[np][2];
/* 235 */     for (int j = 0; j < np; j++) {
/* 236 */       result[j][0] = ((Double)list1.get(j)).doubleValue();
/* 237 */       result[j][1] = ((Double)list2.get(j)).doubleValue();
/*     */     } 
/* 241 */     return result;
/*     */   }
/*     */   
/*     */   private static boolean isCommonVertex(Point2D inter, CirculinearCurve2D elem1, CirculinearCurve2D elem2) {
/* 250 */     double eps = 1.0E-12D;
/* 253 */     if (!Double.isInfinite(elem1.t1()) && 
/* 254 */       !Double.isInfinite(elem2.t0()) && 
/* 255 */       inter.almostEquals((GeometricObject2D)elem1.lastPoint(), eps) && 
/* 256 */       inter.almostEquals((GeometricObject2D)elem2.firstPoint(), eps))
/* 257 */       return true; 
/* 260 */     if (!Double.isInfinite(elem1.t0()) && 
/* 261 */       !Double.isInfinite(elem2.t1()) && 
/* 262 */       inter.almostEquals((GeometricObject2D)elem1.firstPoint(), eps) && 
/* 263 */       inter.almostEquals((GeometricObject2D)elem2.lastPoint(), eps))
/* 264 */       return true; 
/* 266 */     return false;
/*     */   }
/*     */   
/*     */   public static Collection<Point2D> findIntersections(CirculinearCurve2D curve1, CirculinearCurve2D curve2) {
/* 278 */     ArrayList<CirculinearElement2D> elements1 = new ArrayList<CirculinearElement2D>();
/* 279 */     ArrayList<CirculinearElement2D> elements2 = new ArrayList<CirculinearElement2D>();
/* 282 */     for (CirculinearContinuousCurve2D cont : curve1.continuousCurves())
/* 283 */       elements1.addAll(cont.smoothPieces()); 
/* 284 */     for (CirculinearContinuousCurve2D cont : curve2.continuousCurves())
/* 285 */       elements2.addAll(cont.smoothPieces()); 
/* 288 */     ArrayList<Point2D> result = new ArrayList<Point2D>(0);
/* 291 */     int n1 = elements1.size();
/* 292 */     int n2 = elements2.size();
/* 293 */     for (int i = 0; i < n1; i++) {
/* 294 */       CirculinearElement2D elem1 = elements1.get(i);
/* 295 */       for (int j = 0; j < n2; j++) {
/* 296 */         CirculinearElement2D elem2 = elements2.get(j);
/* 298 */         for (Point2D inter : findIntersections(elem1, elem2))
/* 300 */           result.add(inter); 
/*     */       } 
/*     */     } 
/* 306 */     return result;
/*     */   }
/*     */   
/*     */   public static double[][] locateIntersections(CirculinearCurve2D curve1, CirculinearCurve2D curve2) {
/* 319 */     ArrayList<Double> list1 = new ArrayList<Double>(0);
/* 320 */     ArrayList<Double> list2 = new ArrayList<Double>(0);
/* 323 */     ArrayList<CirculinearElement2D> elements1 = new ArrayList<CirculinearElement2D>();
/* 324 */     ArrayList<CirculinearElement2D> elements2 = new ArrayList<CirculinearElement2D>();
/* 327 */     for (CirculinearContinuousCurve2D cont : curve1.continuousCurves())
/* 328 */       elements1.addAll(cont.smoothPieces()); 
/* 329 */     for (CirculinearContinuousCurve2D cont : curve2.continuousCurves())
/* 330 */       elements2.addAll(cont.smoothPieces()); 
/* 333 */     int n1 = elements1.size();
/* 334 */     int n2 = elements2.size();
/* 335 */     for (int i = 0; i < n1; i++) {
/* 336 */       CirculinearElement2D elem1 = elements1.get(i);
/* 337 */       for (int k = 0; k < n2; k++) {
/* 338 */         CirculinearElement2D elem2 = elements2.get(k);
/* 340 */         for (Point2D inter : findIntersections(elem1, elem2)) {
/* 341 */           double pos1 = curve1.position(inter);
/* 342 */           double pos2 = curve2.position(inter);
/* 343 */           if (curve1.isSingular(pos1) && curve2.isSingular(pos2))
/*     */             continue; 
/* 346 */           list1.add(Double.valueOf(pos1));
/* 347 */           list2.add(Double.valueOf(pos2));
/*     */         } 
/*     */       } 
/*     */     } 
/* 353 */     int np = list1.size();
/* 354 */     double[][] result = new double[np][2];
/* 355 */     for (int j = 0; j < np; j++) {
/* 356 */       result[j][0] = ((Double)list1.get(j)).doubleValue();
/* 357 */       result[j][1] = ((Double)list2.get(j)).doubleValue();
/*     */     } 
/* 361 */     return result;
/*     */   }
/*     */   
/*     */   public static Collection<Point2D> findIntersections(CirculinearElement2D elem1, CirculinearElement2D elem2) {
/* 371 */     boolean b1 = elem1 instanceof LinearShape2D;
/* 372 */     boolean b2 = elem2 instanceof LinearShape2D;
/* 376 */     if (b1 && b2) {
/* 377 */       LinearShape2D line1 = (LinearShape2D)elem1;
/* 378 */       LinearShape2D line2 = (LinearShape2D)elem2;
/* 381 */       Vector2D v1 = line1.direction();
/* 382 */       Vector2D v2 = line2.direction();
/* 383 */       if (Vector2D.isColinear(v1, v2))
/* 384 */         return new ArrayList<Point2D>(0); 
/* 386 */       return line1.intersections(line2);
/*     */     } 
/* 390 */     if (elem1 instanceof LinearShape2D)
/* 391 */       return elem2.intersections((LinearShape2D)elem1); 
/* 393 */     if (elem2 instanceof LinearShape2D)
/* 394 */       return elem1.intersections((LinearShape2D)elem2); 
/* 399 */     Circle2D circ1 = ((CircularShape2D)elem1).supportingCircle();
/* 400 */     Circle2D circ2 = ((CircularShape2D)elem2).supportingCircle();
/* 403 */     ArrayList<Point2D> pts = new ArrayList<Point2D>(2);
/* 407 */     for (Point2D inter : Circle2D.circlesIntersections(circ1, circ2)) {
/* 408 */       if (elem1.contains(inter) && elem2.contains(inter))
/* 409 */         pts.add(inter); 
/*     */     } 
/* 413 */     return pts;
/*     */   }
/*     */   
/*     */   public static Collection<CirculinearContinuousCurve2D> splitContinuousCurve(CirculinearContinuousCurve2D curve) {
/* 430 */     ArrayList<CirculinearContinuousCurve2D> result = 
/* 431 */       new ArrayList<CirculinearContinuousCurve2D>();
/* 434 */     if (curve instanceof CirculinearElement2D) {
/* 435 */       result.add(curve);
/* 436 */       return result;
/*     */     } 
/* 441 */     PolyCirculinearCurve2D<CirculinearElement2D> polyCurve = createPolyCurve(
/* 442 */         curve.smoothPieces(), curve.isClosed());
/* 445 */     double[][] couples = locateSelfIntersections((CurveSet2D<? extends CirculinearElement2D>)polyCurve);
/* 448 */     if (couples.length == 0) {
/* 450 */       result.add(createPolyCurve(polyCurve.smoothPieces(), 
/* 451 */             curve.isClosed()));
/* 452 */       return result;
/*     */     } 
/* 456 */     TreeMap<Double, Double> twins = new TreeMap<Double, Double>();
/* 457 */     for (int i = 0; i < couples.length; i++) {
/* 458 */       double d1 = couples[i][0];
/* 459 */       double d2 = couples[i][1];
/* 460 */       twins.put(Double.valueOf(d1), Double.valueOf(d2));
/* 461 */       twins.put(Double.valueOf(d2), Double.valueOf(d1));
/*     */     } 
/* 470 */     ArrayList<CirculinearElement2D> elements = new ArrayList<CirculinearElement2D>();
/* 473 */     double pos1 = polyCurve.t0();
/* 474 */     double pos2 = ((Double)twins.firstKey()).doubleValue();
/* 475 */     double pos0 = pos2;
/* 478 */     addElements(elements, polyCurve.subCurve(pos1, pos2));
/*     */     while (true) {
/* 481 */       pos1 = ((Double)twins.remove(Double.valueOf(pos2))).doubleValue();
/* 484 */       if (twins.higherKey(Double.valueOf(pos1)) == null)
/*     */         break; 
/* 488 */       pos2 = ((Double)twins.higherKey(Double.valueOf(pos1))).doubleValue();
/* 491 */       addElements(elements, polyCurve.subCurve(pos1, pos2));
/*     */     } 
/* 495 */     pos2 = polyCurve.t1();
/* 496 */     addElements(elements, polyCurve.subCurve(pos1, pos2));
/* 499 */     result.add(createPolyCurve(elements, curve.isClosed()));
/* 502 */     while (!twins.isEmpty()) {
/* 504 */       elements = new ArrayList<CirculinearElement2D>();
/* 507 */       pos0 = ((Double)twins.firstKey()).doubleValue();
/* 508 */       pos1 = ((Double)twins.get(Double.valueOf(pos0))).doubleValue();
/* 509 */       pos2 = ((Double)twins.higherKey(Double.valueOf(pos1))).doubleValue();
/* 512 */       addElements(elements, polyCurve.subCurve(pos1, pos2));
/* 514 */       while (pos2 != pos0) {
/* 516 */         pos1 = ((Double)twins.remove(Double.valueOf(pos2))).doubleValue();
/* 519 */         if (twins.higherKey(Double.valueOf(pos1)) == null)
/*     */           break; 
/* 523 */         pos2 = ((Double)twins.higherKey(Double.valueOf(pos1))).doubleValue();
/* 526 */         addElements(elements, polyCurve.subCurve(pos1, pos2));
/*     */       } 
/* 529 */       pos1 = ((Double)twins.remove(Double.valueOf(pos2))).doubleValue();
/* 533 */       result.add(createPolyCurve(elements, true));
/*     */     } 
/* 536 */     return result;
/*     */   }
/*     */   
/*     */   private static PolyCirculinearCurve2D<CirculinearElement2D> createPolyCurve(Collection<? extends CirculinearElement2D> elements, boolean closed) {
/* 545 */     return new PolyCirculinearCurve2D<CirculinearElement2D>(elements, 
/* 546 */         closed);
/*     */   }
/*     */   
/*     */   public static Collection<CirculinearContour2D> splitIntersectingContours(CirculinearContour2D curve1, CirculinearContour2D curve2) {
/* 558 */     ArrayList<CirculinearContour2D> contours = new ArrayList<CirculinearContour2D>();
/* 561 */     double[][] couples = locateIntersections(curve1, curve2);
/* 564 */     if (couples.length == 0) {
/* 566 */       contours.add(curve1);
/* 567 */       contours.add(curve2);
/* 568 */       return contours;
/*     */     } 
/* 572 */     TreeMap<Double, Double> twins1 = new TreeMap<Double, Double>();
/* 573 */     TreeMap<Double, Double> twins2 = new TreeMap<Double, Double>();
/* 576 */     TreeSet<Double> positions1 = new TreeSet<Double>();
/* 577 */     TreeSet<Double> positions2 = new TreeSet<Double>();
/* 580 */     for (int i = 0; i < couples.length; i++) {
/* 581 */       double pos1 = couples[i][0];
/* 582 */       double pos2 = couples[i][1];
/* 583 */       twins1.put(Double.valueOf(pos1), Double.valueOf(pos2));
/* 584 */       twins2.put(Double.valueOf(pos2), Double.valueOf(pos1));
/* 585 */       positions1.add(Double.valueOf(pos1));
/* 586 */       positions2.add(Double.valueOf(pos2));
/*     */     } 
/* 593 */     while (!twins1.isEmpty()) {
/* 595 */       ArrayList<CirculinearElement2D> elements = new ArrayList<CirculinearElement2D>();
/* 598 */       double pos0 = ((Double)twins2.firstEntry().getValue()).doubleValue();
/* 599 */       double pos1 = pos0;
/*     */       do {
/* 602 */         double pos2 = nextValue(positions1, pos1);
/* 605 */         addElements(elements, curve1.subCurve(pos1, pos2));
/* 608 */         pos1 = ((Double)twins1.remove(Double.valueOf(pos2))).doubleValue();
/* 611 */         pos2 = nextValue(positions2, pos1);
/* 614 */         addElements(elements, curve2.subCurve(pos1, pos2));
/* 617 */         pos1 = ((Double)twins2.remove(Double.valueOf(pos2))).doubleValue();
/* 619 */       } while (pos1 != pos0);
/* 623 */       contours.add(BoundaryPolyCirculinearCurve2D.create(elements, true));
/*     */     } 
/* 626 */     return contours;
/*     */   }
/*     */   
/*     */   public static Collection<CirculinearContour2D> splitIntersectingContours(Collection<? extends CirculinearContour2D> curves) {
/* 637 */     double pos0 = 0.0D;
/* 643 */     CirculinearContour2D[] curveArray = 
/* 644 */       curves.<CirculinearContour2D>toArray(new CirculinearContour2D[0]);
/* 649 */     int nCurves = curves.size();
/* 650 */     ArrayList<TreeMap<Double, Integer>> twinIndices = 
/* 651 */       new ArrayList<TreeMap<Double, Integer>>(nCurves);
/* 652 */     ArrayList<TreeMap<Double, Double>> twinPositions = 
/* 653 */       new ArrayList<TreeMap<Double, Double>>(nCurves);
/* 656 */     for (int i = 0; i < nCurves; i++) {
/* 657 */       twinIndices.add(i, new TreeMap<Double, Integer>());
/* 658 */       twinPositions.add(i, new TreeMap<Double, Double>());
/*     */     } 
/* 663 */     ArrayList<TreeSet<Double>> positions = 
/* 664 */       new ArrayList<TreeSet<Double>>(nCurves);
/*     */     int j;
/* 667 */     for (j = 0; j < nCurves; j++)
/* 668 */       positions.add(j, new TreeSet<Double>()); 
/* 672 */     for (j = 0; j < nCurves - 1; j++) {
/* 673 */       CirculinearContour2D curve1 = curveArray[j];
/* 675 */       for (int m = j + 1; m < nCurves; m++) {
/* 676 */         CirculinearContour2D curve2 = curveArray[m];
/* 677 */         double[][] couples = locateIntersections(curve1, curve2);
/* 680 */         for (int n = 0; n < couples.length; n++) {
/* 682 */           double pos1 = couples[n][0];
/* 683 */           double pos2 = couples[n][1];
/* 686 */           ((TreeSet<Double>)positions.get(j)).add(Double.valueOf(pos1));
/* 687 */           ((TreeSet<Double>)positions.get(m)).add(Double.valueOf(pos2));
/* 690 */           ((TreeMap<Double, Integer>)twinIndices.get(j)).put(Double.valueOf(pos1), Integer.valueOf(m));
/* 691 */           ((TreeMap<Double, Integer>)twinIndices.get(m)).put(Double.valueOf(pos2), Integer.valueOf(j));
/* 695 */           ((TreeMap<Double, Double>)twinPositions.get(j)).put(Double.valueOf(pos1), Double.valueOf(pos2));
/* 696 */           ((TreeMap<Double, Double>)twinPositions.get(m)).put(Double.valueOf(pos2), Double.valueOf(pos1));
/*     */         } 
/*     */       } 
/*     */     } 
/* 702 */     ArrayList<CirculinearContour2D> contours = new ArrayList<CirculinearContour2D>();
/*     */     int k;
/* 705 */     for (k = 0; k < nCurves; k++) {
/* 707 */       if (((TreeMap)twinPositions.get(k)).isEmpty())
/* 708 */         contours.add(curveArray[k]); 
/*     */     } 
/* 713 */     for (k = 0; k < nCurves; k++) {
/* 715 */       if (!curveArray[k].isBounded())
/* 719 */         if (!((TreeMap)twinPositions.get(k)).isEmpty()) {
/* 724 */           pos0 = ((Double)((TreeMap)twinPositions.get(k)).firstEntry().getKey()).doubleValue();
/* 725 */           int ind0 = ((Integer)((TreeMap)twinIndices.get(k)).firstEntry().getValue()).intValue();
/* 728 */           ArrayList<CirculinearElement2D> elements = new ArrayList<CirculinearElement2D>();
/* 731 */           CirculinearContour2D curve0 = curveArray[k];
/* 732 */           addElements(elements, curve0.subCurve(curve0.t0(), pos0));
/* 735 */           double pos1 = ((Double)((TreeMap)twinPositions.get(k)).firstEntry().getValue()).doubleValue();
/* 736 */           int ind = ind0;
/*     */           do {
/* 740 */             CirculinearContour2D curve = curveArray[ind];
/* 743 */             double pos2 = nextValue(positions.get(ind), pos1);
/* 745 */             if (pos2 < pos1 && !curve.isBounded()) {
/* 749 */               addElements(elements, 
/* 750 */                   curve.subCurve(pos1, curve.t1()));
/*     */             } else {
/* 754 */               addElements(elements, curve.subCurve(pos1, pos2));
/* 757 */               pos1 = ((Double)((TreeMap)twinPositions.get(ind)).remove(Double.valueOf(pos2))).doubleValue();
/* 758 */               ind = ((Integer)((TreeMap)twinIndices.get(ind)).remove(Double.valueOf(pos2))).intValue();
/*     */             } 
/* 760 */           } while (ind != ind0);
/* 762 */           ((TreeMap)twinPositions.get(k)).remove(Double.valueOf(pos0));
/* 763 */           ((TreeMap)twinIndices.get(k)).remove(Double.valueOf(pos0));
/* 767 */           contours.add(BoundaryPolyCirculinearCurve2D.create(elements, true));
/*     */         }  
/*     */     } 
/* 771 */     while (!isAllEmpty(twinPositions)) {
/* 773 */       ArrayList<CirculinearElement2D> elements = new ArrayList<CirculinearElement2D>();
/* 776 */       int ind0 = 0;
/* 779 */       for (int m = 0; m < nCurves; ) {
/* 781 */         if (((TreeMap)twinPositions.get(m)).isEmpty()) {
/*     */           m++;
/*     */           continue;
/*     */         } 
/* 785 */         pos0 = ((Double)((TreeMap)twinPositions.get(m)).firstEntry().getValue()).doubleValue();
/* 786 */         ind0 = ((Integer)((TreeMap)twinIndices.get(m)).firstEntry().getValue()).intValue();
/*     */         break;
/*     */       } 
/* 790 */       if (ind0 == 0)
/* 791 */         System.out.println("No more intersections, but was not detected"); 
/* 794 */       double pos1 = pos0;
/* 795 */       int ind = ind0;
/*     */       do {
/* 798 */         double pos2 = nextValue(positions.get(ind), pos1);
/* 801 */         addElements(elements, curveArray[ind].subCurve(pos1, pos2));
/* 804 */         pos1 = ((Double)((TreeMap)twinPositions.get(ind)).remove(Double.valueOf(pos2))).doubleValue();
/* 805 */         ind = ((Integer)((TreeMap)twinIndices.get(ind)).remove(Double.valueOf(pos2))).intValue();
/* 806 */       } while (pos1 != pos0 || ind != ind0);
/* 810 */       contours.add(BoundaryPolyCirculinearCurve2D.create(elements, true));
/*     */     } 
/* 813 */     return contours;
/*     */   }
/*     */   
/*     */   private static void addElements(Collection<CirculinearElement2D> elements, CirculinearContinuousCurve2D curve) {
/* 822 */     elements.addAll(curve.smoothPieces());
/*     */   }
/*     */   
/*     */   private static boolean isAllEmpty(Collection<TreeMap<Double, Double>> coll) {
/* 826 */     for (TreeMap<?, ?> map : coll) {
/* 827 */       if (!map.isEmpty())
/* 828 */         return false; 
/*     */     } 
/* 830 */     return true;
/*     */   }
/*     */   
/*     */   private static double nextValue(TreeSet<Double> tree, double value) {
/* 838 */     if (tree.higher(Double.valueOf(value)) == null)
/* 839 */       return ((Double)tree.first()).doubleValue(); 
/* 841 */     return ((Double)tree.higher(Double.valueOf(value))).doubleValue();
/*     */   }
/*     */   
/*     */   public static double getDistanceCurvePoints(CirculinearCurve2D curve, Collection<? extends Point2D> points) {
/* 846 */     double minDist = Double.MAX_VALUE;
/* 847 */     for (Point2D point : points)
/* 848 */       minDist = Math.min(minDist, curve.distance(point)); 
/* 850 */     return minDist;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearCurves2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */