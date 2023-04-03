/*     */ package math.geom2d.circulinear;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class CirculinearDomains2D {
/*     */   public static final CirculinearDomain2D computeBuffer(CirculinearDomain2D domain, double dist) {
/*  27 */     ArrayList<CirculinearContour2D> rings = 
/*  28 */       new ArrayList<CirculinearContour2D>();
/*  31 */     for (CirculinearContour2D contour : domain.contours()) {
/*  34 */       Iterator<CirculinearContinuousCurve2D> iterator = CirculinearCurves2D.splitContinuousCurve(contour).iterator();
/*     */       while (iterator.hasNext()) {
/*  34 */         CirculinearContinuousCurve2D simpleCurve = iterator.next();
/*  35 */         CirculinearContour2D<CirculinearElement2D> boundary = 
/*  36 */           new BoundaryPolyCirculinearCurve2D<CirculinearElement2D>(
/*  37 */             simpleCurve.smoothPieces(), contour.isClosed());
/*  39 */         rings.addAll(computeBufferSimpleRing(boundary, dist));
/*     */       } 
/*     */     } 
/*  45 */     return new GenericCirculinearDomain2D(
/*  46 */         new CirculinearContourArray2D<CirculinearContour2D>(rings));
/*     */   }
/*     */   
/*     */   public static final Collection<CirculinearContour2D> computeBufferSimpleRing(CirculinearContour2D curve, double d) {
/*  57 */     ArrayList<CirculinearContour2D> rings = 
/*  58 */       new ArrayList<CirculinearContour2D>();
/*  61 */     CirculinearContinuousCurve2D parallel1 = curve.parallel(d);
/*  64 */     CirculinearCurveArray2D<CirculinearContinuousCurve2D> curves = 
/*  65 */       new CirculinearCurveArray2D<CirculinearContinuousCurve2D>();
/*  69 */     null = CirculinearCurves2D.splitContinuousCurve(parallel1).iterator();
/*     */     while (null.hasNext()) {
/*  69 */       CirculinearContinuousCurve2D split = null.next();
/*  70 */       if (CirculinearCurves2D.findIntersections(curve, split).size() == 0)
/*  71 */         curves.add(split); 
/*     */     } 
/*  75 */     for (CirculinearContinuousCurve2D split : curves)
/*  76 */       rings.add(
/*  77 */           new BoundaryPolyCirculinearCurve2D<CirculinearElement2D>(
/*  78 */             split.smoothPieces(), split.isClosed())); 
/*  82 */     ArrayList<CirculinearContour2D> rings2 = 
/*  83 */       new ArrayList<CirculinearContour2D>();
/*  86 */     for (CirculinearContour2D ring : rings) {
/*  89 */       Iterator<CirculinearContinuousCurve2D> iterator = CirculinearCurves2D.splitContinuousCurve(ring).iterator();
/*     */       while (iterator.hasNext()) {
/*  89 */         CirculinearContinuousCurve2D split = iterator.next();
/*  94 */         double dist = CirculinearCurves2D.getDistanceCurvePoints(
/*  95 */             curve, split.singularPoints());
/*  98 */         if (dist - d < -1.0E-12D)
/*     */           continue; 
/* 102 */         rings2.add(
/* 103 */             new BoundaryPolyCirculinearCurve2D<CirculinearElement2D>(
/* 104 */               split.smoothPieces(), split.isClosed()));
/*     */       } 
/*     */     } 
/* 108 */     return rings2;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearDomains2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */