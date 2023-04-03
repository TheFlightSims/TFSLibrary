/*     */ package org.poly2tri.triangulation.sets;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.poly2tri.triangulation.TriangulationContext;
/*     */ import org.poly2tri.triangulation.TriangulationMode;
/*     */ import org.poly2tri.triangulation.TriangulationPoint;
/*     */ 
/*     */ public class ConstrainedPointSet extends PointSet {
/*     */   int[] _index;
/*     */   
/*  44 */   List<TriangulationPoint> _constrainedPointList = null;
/*     */   
/*     */   public ConstrainedPointSet(List<TriangulationPoint> points, int[] index) {
/*  48 */     super(points);
/*  49 */     this._index = index;
/*     */   }
/*     */   
/*     */   public ConstrainedPointSet(List<TriangulationPoint> points, List<TriangulationPoint> constraints) {
/*  59 */     super(points);
/*  60 */     this._constrainedPointList = new ArrayList<>();
/*  61 */     this._constrainedPointList.addAll(constraints);
/*     */   }
/*     */   
/*     */   public TriangulationMode getTriangulationMode() {
/*  67 */     return TriangulationMode.CONSTRAINED;
/*     */   }
/*     */   
/*     */   public int[] getEdgeIndex() {
/*  72 */     return this._index;
/*     */   }
/*     */   
/*     */   public void prepareTriangulation(TriangulationContext<?> tcx) {
/*  79 */     super.prepareTriangulation(tcx);
/*  80 */     if (this._constrainedPointList != null) {
/*  83 */       Iterator<TriangulationPoint> iterator = this._constrainedPointList.iterator();
/*  84 */       while (iterator.hasNext()) {
/*  86 */         TriangulationPoint p1 = iterator.next();
/*  87 */         TriangulationPoint p2 = iterator.next();
/*  88 */         tcx.newConstraint(p1, p2);
/*     */       } 
/*     */     } else {
/*  93 */       for (int i = 0; i < this._index.length; i += 2)
/*  96 */         tcx.newConstraint(this._points.get(this._index[i]), this._points.get(this._index[i + 1])); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 110 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\sets\ConstrainedPointSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */