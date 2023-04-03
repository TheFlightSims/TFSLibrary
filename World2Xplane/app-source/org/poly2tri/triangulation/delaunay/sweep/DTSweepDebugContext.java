/*     */ package org.poly2tri.triangulation.delaunay.sweep;
/*     */ 
/*     */ import org.poly2tri.triangulation.TriangulationDebugContext;
/*     */ import org.poly2tri.triangulation.TriangulationPoint;
/*     */ import org.poly2tri.triangulation.delaunay.DelaunayTriangle;
/*     */ 
/*     */ public class DTSweepDebugContext extends TriangulationDebugContext {
/*     */   protected DelaunayTriangle _primaryTriangle;
/*     */   
/*     */   protected DelaunayTriangle _secondaryTriangle;
/*     */   
/*     */   protected TriangulationPoint _activePoint;
/*     */   
/*     */   protected AdvancingFrontNode _activeNode;
/*     */   
/*     */   protected DTSweepConstraint _activeConstraint;
/*     */   
/*     */   public DTSweepDebugContext(DTSweepContext tcx) {
/*  43 */     super(tcx);
/*     */   }
/*     */   
/*     */   public boolean isDebugContext() {
/*  48 */     return true;
/*     */   }
/*     */   
/*     */   public DelaunayTriangle getPrimaryTriangle() {
/*  55 */     return this._primaryTriangle;
/*     */   }
/*     */   
/*     */   public DelaunayTriangle getSecondaryTriangle() {
/*  60 */     return this._secondaryTriangle;
/*     */   }
/*     */   
/*     */   public AdvancingFrontNode getActiveNode() {
/*  65 */     return this._activeNode;
/*     */   }
/*     */   
/*     */   public DTSweepConstraint getActiveConstraint() {
/*  70 */     return this._activeConstraint;
/*     */   }
/*     */   
/*     */   public TriangulationPoint getActivePoint() {
/*  75 */     return this._activePoint;
/*     */   }
/*     */   
/*     */   public void setPrimaryTriangle(DelaunayTriangle triangle) {
/*  80 */     this._primaryTriangle = triangle;
/*  81 */     this._tcx.update("setPrimaryTriangle");
/*     */   }
/*     */   
/*     */   public void setSecondaryTriangle(DelaunayTriangle triangle) {
/*  86 */     this._secondaryTriangle = triangle;
/*  87 */     this._tcx.update("setSecondaryTriangle");
/*     */   }
/*     */   
/*     */   public void setActivePoint(TriangulationPoint point) {
/*  92 */     this._activePoint = point;
/*     */   }
/*     */   
/*     */   public void setActiveConstraint(DTSweepConstraint e) {
/*  97 */     this._activeConstraint = e;
/*  98 */     this._tcx.update("setWorkingSegment");
/*     */   }
/*     */   
/*     */   public void setActiveNode(AdvancingFrontNode node) {
/* 103 */     this._activeNode = node;
/* 104 */     this._tcx.update("setWorkingNode");
/*     */   }
/*     */   
/*     */   public void clear() {
/* 110 */     this._primaryTriangle = null;
/* 111 */     this._secondaryTriangle = null;
/* 112 */     this._activePoint = null;
/* 113 */     this._activeNode = null;
/* 114 */     this._activeConstraint = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\delaunay\sweep\DTSweepDebugContext.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */