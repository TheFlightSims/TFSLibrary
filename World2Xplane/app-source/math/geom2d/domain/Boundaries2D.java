/*     */ package math.geom2d.domain;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.UnboundedBox2DException;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveArray2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.polygon.Polyline2D;
/*     */ 
/*     */ public abstract class Boundaries2D {
/*     */   public static final CurveSet2D<ContinuousOrientedCurve2D> clipContinuousOrientedCurve(ContinuousOrientedCurve2D curve, Box2D box) {
/*  38 */     CurveArray2D<ContinuousOrientedCurve2D> result = 
/*  39 */       new CurveArray2D();
/*  42 */     for (ContinuousCurve2D cont : Curves2D.clipContinuousCurve(curve, box)) {
/*  43 */       if (cont instanceof ContinuousOrientedCurve2D)
/*  44 */         result.add((ContinuousOrientedCurve2D)cont); 
/*     */     } 
/*  46 */     return (CurveSet2D<ContinuousOrientedCurve2D>)result;
/*     */   }
/*     */   
/*     */   public static final ContourArray2D<Contour2D> clipBoundary(Boundary2D boundary, Box2D box) {
/* 111 */     if (!box.isBounded())
/* 112 */       throw new UnboundedBox2DException(box); 
/* 120 */     ContourArray2D<Contour2D> res = new ContourArray2D<Contour2D>();
/* 126 */     CurveArray2D<ContinuousOrientedCurve2D> curveSet = 
/* 127 */       new CurveArray2D();
/* 131 */     for (Contour2D contour : boundary.continuousCurves()) {
/* 132 */       CurveSet2D<ContinuousOrientedCurve2D> clipped = clipContinuousOrientedCurve(contour, box);
/* 134 */       for (ContinuousOrientedCurve2D clip : clipped)
/* 135 */         curveSet.add(clip); 
/*     */     } 
/* 139 */     int nc = curveSet.size();
/* 140 */     double[] startPositions = new double[nc];
/* 141 */     double[] endPositions = new double[nc];
/* 144 */     boolean intersect = false;
/* 147 */     ContinuousOrientedCurve2D[] curves = new ContinuousOrientedCurve2D[nc];
/* 150 */     Curve2D boxBoundary = box.boundary();
/* 153 */     Iterator<ContinuousOrientedCurve2D> iter = curveSet.curves().iterator();
/* 155 */     for (int i = 0; i < nc; i++) {
/* 157 */       ContinuousOrientedCurve2D curve = iter.next();
/* 158 */       curves[i] = curve;
/* 160 */       if (curve.isClosed()) {
/* 161 */         startPositions[i] = Double.NaN;
/* 162 */         endPositions[i] = Double.NaN;
/*     */       } else {
/* 167 */         startPositions[i] = boxBoundary.position(curve.firstPoint());
/* 168 */         endPositions[i] = boxBoundary.position(curve.lastPoint());
/* 171 */         intersect = true;
/*     */       } 
/*     */     } 
/* 177 */     int nb = nc;
/* 180 */     int c = 0;
/* 183 */     while (c < nb) {
/* 184 */       int ind = c;
/* 186 */       while (curves[ind] == null)
/* 187 */         ind++; 
/* 190 */       ContinuousOrientedCurve2D curve = curves[ind];
/* 193 */       if (curve.isClosed()) {
/* 195 */         if (curve instanceof Contour2D) {
/* 196 */           res.add(curve);
/*     */         } else {
/* 198 */           BoundaryPolyCurve2D<ContinuousOrientedCurve2D> bnd = 
/* 199 */             new BoundaryPolyCurve2D<ContinuousOrientedCurve2D>();
/* 200 */           bnd.add(curve);
/* 201 */           res.add(bnd);
/*     */         } 
/* 203 */         curves[ind] = null;
/* 206 */         c++;
/*     */         continue;
/*     */       } 
/* 211 */       BoundaryPolyCurve2D<ContinuousOrientedCurve2D> boundary0 = 
/* 212 */         new BoundaryPolyCurve2D<ContinuousOrientedCurve2D>();
/* 215 */       boundary0.add(curve);
/* 218 */       Point2D p0 = curve.firstPoint();
/* 219 */       Point2D p1 = curve.lastPoint();
/* 222 */       int ind0 = ind;
/* 225 */       ArrayList<Integer> indices = new ArrayList<Integer>();
/* 226 */       indices.add(new Integer(ind));
/* 229 */       ind = findNextCurveIndex(startPositions, endPositions[ind0]);
/* 232 */       while (ind != ind0) {
/* 235 */         curve = curves[ind];
/* 238 */         Point2D p0i = curve.firstPoint();
/* 239 */         boundary0.add((Curve2D)getBoundaryPortion(box, p1, p0i));
/* 242 */         boundary0.add(curve);
/* 244 */         indices.add(new Integer(ind));
/* 247 */         ind = findNextCurveIndex(startPositions, endPositions[ind]);
/* 250 */         p1 = curve.lastPoint();
/* 253 */         nb--;
/*     */       } 
/* 257 */       boundary0.add((Curve2D)getBoundaryPortion(box, p1, p0));
/* 260 */       res.add(boundary0);
/* 263 */       Iterator<Integer> iter2 = indices.iterator();
/* 264 */       while (iter2.hasNext())
/* 265 */         curves[((Integer)iter2.next()).intValue()] = null; 
/* 268 */       c++;
/*     */     } 
/* 274 */     if (!intersect) {
/* 275 */       Point2D vertex = box.vertices().iterator().next();
/* 276 */       if (boundary.isInside(vertex))
/* 277 */         res.add(box.asRectangle().boundary().firstCurve()); 
/*     */     } 
/* 281 */     return res;
/*     */   }
/*     */   
/*     */   public static final int findNextCurveIndex(double[] positions, double pos) {
/* 285 */     int ind = -1;
/* 286 */     double posMin = Double.MAX_VALUE;
/*     */     int i;
/* 287 */     for (i = 0; i < positions.length; i++) {
/* 289 */       if (!Double.isNaN(positions[i]))
/* 292 */         if (positions[i] - pos >= 1.0E-12D)
/* 296 */           if (positions[i] < posMin) {
/* 297 */             ind = i;
/* 298 */             posMin = positions[i];
/*     */           }   
/*     */     } 
/* 302 */     if (ind != -1)
/* 303 */       return ind; 
/* 307 */     for (i = 0; i < positions.length; i++) {
/* 308 */       if (!Double.isNaN(positions[i]))
/* 310 */         if (positions[i] - posMin < 1.0E-12D) {
/* 311 */           ind = i;
/* 312 */           posMin = positions[i];
/*     */         }  
/*     */     } 
/* 315 */     return ind;
/*     */   }
/*     */   
/*     */   public static final Polyline2D getBoundaryPortion(Box2D box, Point2D p0, Point2D p1) {
/* 328 */     Boundary2D boundary = box.boundary();
/* 331 */     double t0 = boundary.position(p0);
/* 332 */     double t1 = boundary.position(p1);
/* 335 */     int ind0 = (int)Math.floor(t0);
/* 336 */     int ind1 = (int)Math.floor(t1);
/* 339 */     if (ind0 == ind1 && t0 < t1)
/* 340 */       return new Polyline2D(new Point2D[] { p0, p1 }); 
/* 345 */     ArrayList<Point2D> vertices = new ArrayList<Point2D>(6);
/* 348 */     vertices.add(p0);
/* 351 */     int ind = (ind0 + 1) % 4;
/* 354 */     while (ind != ind1) {
/* 355 */       vertices.add(boundary.point(ind));
/* 356 */       ind = (ind + 1) % 4;
/*     */     } 
/* 358 */     vertices.add(boundary.point(ind));
/* 361 */     vertices.add(p1);
/* 363 */     return new Polyline2D(vertices);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\Boundaries2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */