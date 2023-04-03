/*     */ package math.geom2d.curve;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ 
/*     */ public class CurveArray2D<T extends Curve2D> implements CurveSet2D<T>, Iterable<T>, Cloneable {
/*     */   protected ArrayList<T> curves;
/*     */   
/*     */   public static <T extends Curve2D> CurveArray2D<T> create(Collection<T> curves) {
/*  62 */     return new CurveArray2D<T>(curves);
/*     */   }
/*     */   
/*     */   public static <T extends Curve2D> CurveArray2D<T> create(Curve2D... curves) {
/*  72 */     return new CurveArray2D<T>((T[])curves);
/*     */   }
/*     */   
/*     */   public CurveArray2D() {
/*  90 */     this.curves = new ArrayList<T>();
/*     */   }
/*     */   
/*     */   public CurveArray2D(int n) {
/*  98 */     this();
/*  99 */     this.curves = new ArrayList<T>(n);
/*     */   }
/*     */   
/*     */   public CurveArray2D(Curve2D... curves) {
/* 108 */     this();
/* 109 */     this.curves = new ArrayList<T>(curves.length);
/*     */     byte b;
/*     */     int i;
/*     */     Curve2D[] arrayOfCurve2D;
/* 110 */     for (i = (arrayOfCurve2D = curves).length, b = 0; b < i; ) {
/* 110 */       Curve2D curve2D = arrayOfCurve2D[b];
/* 111 */       add((T)curve2D);
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public CurveArray2D(Collection<? extends T> curves) {
/* 121 */     this();
/* 122 */     this.curves = new ArrayList<T>(curves.size());
/* 123 */     this.curves.addAll(curves);
/*     */   }
/*     */   
/*     */   public double localPosition(double t) {
/* 142 */     int i = curveIndex(t);
/* 143 */     Curve2D curve2D = (Curve2D)this.curves.get(i);
/* 144 */     double t0 = curve2D.t0();
/* 145 */     double t1 = curve2D.t1();
/* 146 */     return Curves2D.fromUnitSegment(t - (2 * i), t0, t1);
/*     */   }
/*     */   
/*     */   public double globalPosition(int i, double t) {
/* 160 */     Curve2D curve2D = (Curve2D)this.curves.get(i);
/* 161 */     double t0 = curve2D.t0();
/* 162 */     double t1 = curve2D.t1();
/* 163 */     return Curves2D.toUnitSegment(t, t0, t1) + (i * 2);
/*     */   }
/*     */   
/*     */   public int curveIndex(double t) {
/* 176 */     if (this.curves.size() == 0)
/* 177 */       return 0; 
/* 178 */     if (t > (this.curves.size() * 2 - 1))
/* 179 */       return this.curves.size() - 1; 
/* 182 */     int nc = (int)Math.floor(t);
/* 185 */     int indc = (int)Math.floor((nc / 2));
/* 186 */     if (indc * 2 == nc)
/* 187 */       return indc; 
/* 189 */     return (t - nc < 0.5D) ? indc : (indc + 1);
/*     */   }
/*     */   
/*     */   public boolean add(T curve) {
/* 202 */     if (this.curves.contains(curve))
/* 203 */       return false; 
/* 204 */     return this.curves.add(curve);
/*     */   }
/*     */   
/*     */   public void add(int index, T curve) {
/* 208 */     this.curves.add(index, curve);
/*     */   }
/*     */   
/*     */   public boolean remove(T curve) {
/* 217 */     return this.curves.remove(curve);
/*     */   }
/*     */   
/*     */   public T remove(int index) {
/* 221 */     return this.curves.remove(index);
/*     */   }
/*     */   
/*     */   public boolean contains(T curve) {
/* 228 */     return this.curves.contains(curve);
/*     */   }
/*     */   
/*     */   public int indexOf(T curve) {
/* 235 */     return this.curves.indexOf(curve);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 242 */     this.curves.clear();
/*     */   }
/*     */   
/*     */   public Collection<T> curves() {
/* 251 */     return this.curves;
/*     */   }
/*     */   
/*     */   public T get(int index) {
/* 262 */     return this.curves.get(index);
/*     */   }
/*     */   
/*     */   public T childCurve(double t) {
/* 274 */     if (this.curves.size() == 0)
/* 275 */       return null; 
/* 276 */     return this.curves.get(curveIndex(t));
/*     */   }
/*     */   
/*     */   public T firstCurve() {
/* 285 */     if (this.curves.size() == 0)
/* 286 */       return null; 
/* 287 */     return this.curves.get(0);
/*     */   }
/*     */   
/*     */   public T lastCurve() {
/* 296 */     if (this.curves.size() == 0)
/* 297 */       return null; 
/* 298 */     return this.curves.get(this.curves.size() - 1);
/*     */   }
/*     */   
/*     */   public int size() {
/* 307 */     return this.curves.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 314 */     return (this.curves.size() == 0);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 321 */     ArrayList<Point2D> intersect = new ArrayList<Point2D>();
/* 324 */     for (Curve2D curve : this.curves)
/* 325 */       intersect.addAll(curve.intersections(line)); 
/* 327 */     return intersect;
/*     */   }
/*     */   
/*     */   public double t0() {
/* 334 */     return 0.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 342 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 346 */     return Math.max(this.curves.size() * 2 - 1, 0);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 354 */     return t1();
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 363 */     if (this.curves.size() == 0)
/* 364 */       return null; 
/* 365 */     if (t < t0())
/* 366 */       return firstCurve().firstPoint(); 
/* 367 */     if (t > t1())
/* 368 */       return lastCurve().lastPoint(); 
/* 371 */     int nc = (int)Math.floor(t);
/* 374 */     int indc = (int)Math.floor((nc / 2));
/* 375 */     if (indc * 2 == nc) {
/* 376 */       Curve2D curve = (Curve2D)this.curves.get(indc);
/* 377 */       double pos = Curves2D.fromUnitSegment(t - nc, 
/* 378 */           curve.t0(), curve.t1());
/* 379 */       return curve.point(pos);
/*     */     } 
/* 383 */     if (t - nc < 0.5D)
/* 384 */       return ((Curve2D)this.curves.get(indc)).lastPoint(); 
/* 386 */     return ((Curve2D)this.curves.get(indc + 1)).firstPoint();
/*     */   }
/*     */   
/*     */   public Point2D firstPoint() {
/* 396 */     if (this.curves.size() == 0)
/* 397 */       return null; 
/* 398 */     return firstCurve().firstPoint();
/*     */   }
/*     */   
/*     */   public Point2D lastPoint() {
/* 407 */     if (this.curves.size() == 0)
/* 408 */       return null; 
/* 409 */     return lastCurve().lastPoint();
/*     */   }
/*     */   
/*     */   public Collection<Point2D> singularPoints() {
/* 420 */     ArrayList<Point2D> points = new ArrayList<Point2D>();
/* 421 */     double eps = 1.0E-12D;
/* 424 */     for (Curve2D curve : this.curves) {
/* 426 */       for (Point2D point : curve.singularPoints())
/* 427 */         addPointWithGuardDistance(points, point, eps); 
/* 430 */       if (!Curves2D.isLeftInfinite(curve))
/* 431 */         addPointWithGuardDistance(points, curve.firstPoint(), eps); 
/* 434 */       if (!Curves2D.isRightInfinite(curve))
/* 435 */         addPointWithGuardDistance(points, curve.lastPoint(), eps); 
/*     */     } 
/* 438 */     return points;
/*     */   }
/*     */   
/*     */   private void addPointWithGuardDistance(Collection<Point2D> pointSet, Point2D point, double eps) {
/* 450 */     for (Point2D p0 : pointSet) {
/* 451 */       if (p0.almostEquals((GeometricObject2D)point, eps))
/*     */         return; 
/*     */     } 
/* 454 */     pointSet.add(point);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> vertices() {
/* 463 */     return singularPoints();
/*     */   }
/*     */   
/*     */   public boolean isSingular(double pos) {
/* 467 */     if (Math.abs(pos - Math.round(pos)) < 1.0E-12D)
/* 468 */       return true; 
/* 470 */     int nc = curveIndex(pos);
/* 472 */     if (nc - Math.floor(pos / 2.0D) > 0.0D)
/* 473 */       return true; 
/* 476 */     Curve2D curve = (Curve2D)this.curves.get(nc);
/* 479 */     return curve.isSingular(localPosition(pos));
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 483 */     double minDist = Double.MAX_VALUE, dist = minDist;
/* 484 */     double x = point.x(), y = point.y();
/* 485 */     double pos = 0.0D;
/* 487 */     int i = 0;
/* 488 */     for (Curve2D curve : this.curves) {
/* 489 */       dist = curve.distance(x, y);
/* 490 */       if (dist < minDist) {
/* 491 */         minDist = dist;
/* 492 */         pos = curve.position(point);
/* 494 */         double t0 = curve.t0();
/* 495 */         double t1 = curve.t1();
/* 496 */         pos = Curves2D.toUnitSegment(pos, t0, t1) + (i * 2);
/*     */       } 
/* 498 */       i++;
/*     */     } 
/* 500 */     return pos;
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 504 */     double minDist = Double.MAX_VALUE, dist = minDist;
/* 505 */     double x = point.x(), y = point.y();
/* 506 */     double pos = 0.0D;
/* 508 */     int i = 0;
/* 509 */     for (Curve2D curve : this.curves) {
/* 510 */       dist = curve.distance(x, y);
/* 511 */       if (dist < minDist) {
/* 512 */         minDist = dist;
/* 513 */         pos = curve.project(point);
/* 515 */         double t0 = curve.t0();
/* 516 */         double t1 = curve.t1();
/* 517 */         pos = Curves2D.toUnitSegment(pos, t0, t1) + (i * 2);
/*     */       } 
/* 519 */       i++;
/*     */     } 
/* 521 */     return pos;
/*     */   }
/*     */   
/*     */   public Curve2D reverse() {
/* 526 */     int n = this.curves.size();
/* 527 */     Curve2D[] curves2 = new Curve2D[n];
/* 530 */     for (int i = 0; i < n; i++)
/* 531 */       curves2[i] = ((Curve2D)this.curves.get(n - 1 - i)).reverse(); 
/* 534 */     return new CurveArray2D((T[])curves2);
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends Curve2D> subCurve(double t0, double t1) {
/* 542 */     int nc = this.curves.size();
/* 545 */     CurveArray2D<Curve2D> res = new CurveArray2D();
/* 549 */     t0 = Math.min(Math.max(t0, 0.0D), (nc * 2) - 0.6D);
/* 550 */     t1 = Math.min(Math.max(t1, 0.0D), (nc * 2) - 0.6D);
/* 553 */     double t0f = Math.floor(t0);
/* 554 */     double t1f = Math.floor(t1);
/* 557 */     int ind0 = (int)Math.floor(t0f / 2.0D);
/* 558 */     int ind1 = (int)Math.floor(t1f / 2.0D);
/* 561 */     if (t0 - (2 * ind0) > 1.5D)
/* 562 */       ind0++; 
/* 563 */     if (t1 - (2 * ind1) > 1.5D)
/* 564 */       ind1++; 
/* 567 */     t0f = (2 * ind0);
/* 568 */     t1f = (2 * ind1);
/* 573 */     if (ind0 == ind1 && t0 < t1) {
/* 574 */       Curve2D curve2D = (Curve2D)this.curves.get(ind0);
/* 575 */       double d1 = Curves2D.fromUnitSegment(t0 - t0f, curve2D.t0(), curve2D.t1());
/* 576 */       double d2 = Curves2D.fromUnitSegment(t1 - t1f, curve2D.t0(), curve2D.t1());
/* 577 */       res.add(curve2D.subCurve(d1, d2));
/* 578 */       return res;
/*     */     } 
/* 582 */     Curve2D curve = (Curve2D)this.curves.get(ind0);
/* 583 */     double pos0 = Curves2D.fromUnitSegment(t0 - t0f, curve.t0(), curve.t1());
/* 584 */     res.add(curve.subCurve(pos0, curve.t1()));
/* 586 */     if (ind1 > ind0) {
/* 588 */       for (int n = ind0 + 1; n < ind1; n++)
/* 589 */         res.add((Curve2D)this.curves.get(n)); 
/*     */     } else {
/*     */       int n;
/* 592 */       for (n = ind0 + 1; n < nc; n++)
/* 593 */         res.add((Curve2D)this.curves.get(n)); 
/* 596 */       for (n = 0; n < ind1; n++)
/* 597 */         res.add((Curve2D)this.curves.get(n)); 
/*     */     } 
/* 601 */     curve = (Curve2D)this.curves.get(ind1);
/* 602 */     double pos1 = Curves2D.fromUnitSegment(t1 - t1f, curve.t0(), curve.t1());
/* 603 */     res.add(curve.subCurve(curve.t0(), pos1));
/* 606 */     return res;
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 613 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 617 */     double dist = Double.POSITIVE_INFINITY;
/* 618 */     for (Curve2D curve : this.curves)
/* 619 */       dist = Math.min(dist, curve.distance(x, y)); 
/* 620 */     return dist;
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 627 */     for (Curve2D curve : this.curves) {
/* 628 */       if (!curve.isBounded())
/* 629 */         return false; 
/*     */     } 
/* 630 */     return true;
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends Curve2D> clip(Box2D box) {
/* 641 */     return Curves2D.clipCurveSet(this, box);
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 648 */     double xmin = Double.MAX_VALUE;
/* 649 */     double ymin = Double.MAX_VALUE;
/* 650 */     double xmax = Double.MIN_VALUE;
/* 651 */     double ymax = Double.MIN_VALUE;
/* 654 */     for (Curve2D curve : this.curves) {
/* 655 */       Box2D box = curve.boundingBox();
/* 656 */       xmin = Math.min(xmin, box.getMinX());
/* 657 */       ymin = Math.min(ymin, box.getMinY());
/* 658 */       xmax = Math.max(xmax, box.getMaxX());
/* 659 */       ymax = Math.max(ymax, box.getMaxY());
/*     */     } 
/* 662 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   public CurveArray2D<? extends Curve2D> transform(AffineTransform2D trans) {
/* 671 */     CurveArray2D<Curve2D> result = new CurveArray2D(this.curves.size());
/* 674 */     for (Curve2D curve : this.curves)
/* 675 */       result.add(curve.transform(trans)); 
/* 676 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<? extends ContinuousCurve2D> continuousCurves() {
/* 681 */     ArrayList<ContinuousCurve2D> continuousCurves = 
/* 682 */       new ArrayList<ContinuousCurve2D>();
/* 686 */     for (Curve2D curve : this.curves) {
/* 687 */       if (curve instanceof ContinuousCurve2D) {
/* 688 */         continuousCurves.add((ContinuousCurve2D)curve);
/*     */         continue;
/*     */       } 
/* 690 */       continuousCurves.addAll(curve.continuousCurves());
/*     */     } 
/* 694 */     return continuousCurves;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 702 */     return contains(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 707 */     for (Curve2D curve : this.curves) {
/* 708 */       if (curve.contains(x, y))
/* 709 */         return true; 
/*     */     } 
/* 711 */     return false;
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 716 */     GeneralPath path = new GeneralPath();
/* 719 */     if (this.curves.size() == 0)
/* 720 */       return path; 
/* 724 */     for (ContinuousCurve2D curve : continuousCurves()) {
/* 725 */       Point2D point = curve.firstPoint();
/* 726 */       path.moveTo((float)point.x(), (float)point.y());
/* 727 */       path = curve.appendPath(path);
/*     */     } 
/* 731 */     return path;
/*     */   }
/*     */   
/*     */   public Shape asAwtShape() {
/* 738 */     return getGeneralPath();
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 742 */     for (Curve2D curve : this.curves)
/* 743 */       curve.draw(g2); 
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 753 */     if (this == obj)
/* 754 */       return true; 
/* 757 */     if (!(obj instanceof CurveArray2D))
/* 758 */       return false; 
/* 759 */     CurveArray2D<?> shapeSet = (CurveArray2D)obj;
/* 762 */     if (this.curves.size() != shapeSet.curves.size())
/* 763 */       return false; 
/* 766 */     for (int i = 0; i < this.curves.size(); i++) {
/* 767 */       if (!((Curve2D)this.curves.get(i)).almostEquals((GeometricObject2D)shapeSet.curves.get(i), eps))
/* 768 */         return false; 
/*     */     } 
/* 771 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 785 */     if (!(obj instanceof CurveArray2D))
/* 786 */       return false; 
/* 787 */     CurveArray2D<?> curveSet = (CurveArray2D)obj;
/* 790 */     if (size() != curveSet.size())
/* 791 */       return false; 
/* 794 */     for (int i = 0; i < this.curves.size(); i++) {
/* 795 */       if (!((Curve2D)this.curves.get(i)).equals(curveSet.curves.get(i)))
/* 796 */         return false; 
/*     */     } 
/* 799 */     return true;
/*     */   }
/*     */   
/*     */   public CurveArray2D<? extends Curve2D> clone() {
/* 803 */     ArrayList<Curve2D> array = new ArrayList<Curve2D>(this.curves.size());
/* 804 */     for (Curve2D curve2D : this.curves)
/* 805 */       array.add(curve2D); 
/* 806 */     return new CurveArray2D((Collection)array);
/*     */   }
/*     */   
/*     */   public Iterator<T> iterator() {
/* 818 */     return this.curves.iterator();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\curve\CurveArray2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */