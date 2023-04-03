/*     */ package org.poly2tri;
/*     */ 
/*     */ import org.poly2tri.geometry.polygon.Polygon;
/*     */ import org.poly2tri.geometry.polygon.PolygonSet;
/*     */ import org.poly2tri.triangulation.Triangulatable;
/*     */ import org.poly2tri.triangulation.TriangulationAlgorithm;
/*     */ import org.poly2tri.triangulation.TriangulationContext;
/*     */ import org.poly2tri.triangulation.TriangulationProcess;
/*     */ import org.poly2tri.triangulation.delaunay.sweep.DTSweep;
/*     */ import org.poly2tri.triangulation.delaunay.sweep.DTSweepContext;
/*     */ import org.poly2tri.triangulation.sets.ConstrainedPointSet;
/*     */ import org.poly2tri.triangulation.sets.PointSet;
/*     */ import org.poly2tri.triangulation.util.PolygonGenerator;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class Poly2Tri {
/*  41 */   private static final Logger logger = LoggerFactory.getLogger(Poly2Tri.class);
/*     */   
/*  43 */   private static final TriangulationAlgorithm _defaultAlgorithm = TriangulationAlgorithm.DTSweep;
/*     */   
/*     */   public static void triangulate(PolygonSet ps) throws Exception {
/*  46 */     TriangulationContext<?> tcx = createContext(_defaultAlgorithm);
/*  47 */     for (Polygon p : ps.getPolygons()) {
/*  49 */       tcx.prepareTriangulation((Triangulatable)p);
/*  50 */       triangulate(tcx);
/*  51 */       tcx.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void triangulate(Polygon p) throws Exception {
/*  56 */     triangulate(_defaultAlgorithm, (Triangulatable)p);
/*     */   }
/*     */   
/*     */   public static void triangulate(ConstrainedPointSet cps) throws Exception {
/*  60 */     triangulate(_defaultAlgorithm, (Triangulatable)cps);
/*     */   }
/*     */   
/*     */   public static void triangulate(PointSet ps) throws Exception {
/*  64 */     triangulate(_defaultAlgorithm, (Triangulatable)ps);
/*     */   }
/*     */   
/*     */   public static TriangulationContext<?> createContext(TriangulationAlgorithm algorithm) {
/*  69 */     switch (algorithm) {
/*     */     
/*     */     } 
/*  73 */     return (TriangulationContext<?>)new DTSweepContext();
/*     */   }
/*     */   
/*     */   public static void triangulate(TriangulationAlgorithm algorithm, Triangulatable t) throws Exception {
/*  82 */     TriangulationContext<?> tcx = createContext(algorithm);
/*  83 */     tcx.prepareTriangulation(t);
/*  84 */     triangulate(tcx);
/*     */   }
/*     */   
/*     */   public static void triangulate(TriangulationContext<?> tcx) throws Exception {
/*  89 */     switch (tcx.algorithm()) {
/*     */     
/*     */     } 
/*  93 */     DTSweep.triangulate((DTSweepContext)tcx);
/*     */   }
/*     */   
/*     */   public static void warmup() {
/* 108 */     Polygon poly = PolygonGenerator.RandomCircleSweep2(50.0D, 50000);
/* 109 */     TriangulationProcess process = new TriangulationProcess();
/* 110 */     process.triangulate(poly);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\Poly2Tri.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */