/*    */ package org.poly2tri.triangulation.sets;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.poly2tri.triangulation.Triangulatable;
/*    */ import org.poly2tri.triangulation.TriangulationContext;
/*    */ import org.poly2tri.triangulation.TriangulationMode;
/*    */ import org.poly2tri.triangulation.TriangulationPoint;
/*    */ import org.poly2tri.triangulation.delaunay.DelaunayTriangle;
/*    */ 
/*    */ public class PointSet implements Triangulatable {
/*    */   List<TriangulationPoint> _points;
/*    */   
/*    */   List<DelaunayTriangle> _triangles;
/*    */   
/*    */   public PointSet(List<TriangulationPoint> points) {
/* 40 */     this._points = new ArrayList<>();
/* 41 */     this._points.addAll(points);
/*    */   }
/*    */   
/*    */   public TriangulationMode getTriangulationMode() {
/* 46 */     return TriangulationMode.UNCONSTRAINED;
/*    */   }
/*    */   
/*    */   public List<TriangulationPoint> getPoints() {
/* 51 */     return this._points;
/*    */   }
/*    */   
/*    */   public List<DelaunayTriangle> getTriangles() {
/* 56 */     return this._triangles;
/*    */   }
/*    */   
/*    */   public void addTriangle(DelaunayTriangle t) {
/* 61 */     this._triangles.add(t);
/*    */   }
/*    */   
/*    */   public void addTriangles(List<DelaunayTriangle> list) {
/* 66 */     this._triangles.addAll(list);
/*    */   }
/*    */   
/*    */   public void clearTriangulation() {
/* 71 */     this._triangles.clear();
/*    */   }
/*    */   
/*    */   public void prepareTriangulation(TriangulationContext<?> tcx) {
/* 76 */     if (this._triangles == null) {
/* 78 */       this._triangles = new ArrayList<>(this._points.size());
/*    */     } else {
/* 82 */       this._triangles.clear();
/*    */     } 
/* 84 */     tcx.addPoints(this._points);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\sets\PointSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */