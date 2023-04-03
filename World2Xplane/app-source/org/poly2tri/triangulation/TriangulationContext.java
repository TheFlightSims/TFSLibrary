/*     */ package org.poly2tri.triangulation;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.poly2tri.triangulation.delaunay.DelaunayTriangle;
/*     */ 
/*     */ public abstract class TriangulationContext<A extends TriangulationDebugContext> {
/*     */   protected A _debug;
/*     */   
/*     */   protected boolean _debugEnabled = false;
/*     */   
/*  34 */   protected ArrayList<DelaunayTriangle> _triList = new ArrayList<>();
/*     */   
/*  36 */   protected ArrayList<TriangulationPoint> _points = new ArrayList<>(200);
/*     */   
/*     */   protected TriangulationMode _triangulationMode;
/*     */   
/*     */   protected Triangulatable _triUnit;
/*     */   
/*     */   private boolean _terminated = false;
/*     */   
/*     */   private boolean _waitUntilNotified;
/*     */   
/*  43 */   private int _stepTime = -1;
/*     */   
/*  44 */   private int _stepCount = 0;
/*     */   
/*     */   public int getStepCount() {
/*  45 */     return this._stepCount;
/*     */   }
/*     */   
/*     */   public void done() {
/*  49 */     this._stepCount++;
/*     */   }
/*     */   
/*     */   public abstract TriangulationAlgorithm algorithm();
/*     */   
/*     */   public void prepareTriangulation(Triangulatable t) {
/*  56 */     this._triUnit = t;
/*  57 */     this._triangulationMode = t.getTriangulationMode();
/*  58 */     t.prepareTriangulation(this);
/*     */   }
/*     */   
/*     */   public abstract TriangulationConstraint newConstraint(TriangulationPoint paramTriangulationPoint1, TriangulationPoint paramTriangulationPoint2);
/*     */   
/*     */   public void addToList(DelaunayTriangle triangle) {
/*  65 */     this._triList.add(triangle);
/*     */   }
/*     */   
/*     */   public List<DelaunayTriangle> getTriangles() {
/*  70 */     return this._triList;
/*     */   }
/*     */   
/*     */   public Triangulatable getTriangulatable() {
/*  75 */     return this._triUnit;
/*     */   }
/*     */   
/*     */   public List<TriangulationPoint> getPoints() {
/*  80 */     return this._points;
/*     */   }
/*     */   
/*     */   public synchronized void update(String message) {
/*  85 */     if (this._debugEnabled)
/*     */       try {
/*  89 */         synchronized (this) {
/*  91 */           this._stepCount++;
/*  92 */           if (this._stepTime > 0) {
/*  94 */             wait(this._stepTime);
/*  96 */             if (this._waitUntilNotified)
/*  98 */               wait(); 
/*     */           } else {
/* 103 */             wait();
/*     */           } 
/* 106 */           this._waitUntilNotified = false;
/*     */         } 
/* 109 */       } catch (InterruptedException e) {
/* 111 */         update("Triangulation was interrupted");
/*     */       }  
/* 114 */     if (this._terminated)
/* 116 */       throw new RuntimeException("Triangulation process terminated before completion"); 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 122 */     this._points.clear();
/* 123 */     this._terminated = false;
/* 124 */     if (this._debug != null)
/* 126 */       this._debug.clear(); 
/* 128 */     this._stepCount = 0;
/*     */   }
/*     */   
/*     */   public TriangulationMode getTriangulationMode() {
/* 133 */     return this._triangulationMode;
/*     */   }
/*     */   
/*     */   public synchronized void waitUntilNotified(boolean b) {
/* 138 */     this._waitUntilNotified = b;
/*     */   }
/*     */   
/*     */   public void terminateTriangulation() {
/* 143 */     this._terminated = true;
/*     */   }
/*     */   
/*     */   public boolean isDebugEnabled() {
/* 148 */     return this._debugEnabled;
/*     */   }
/*     */   
/*     */   public abstract void isDebugEnabled(boolean paramBoolean);
/*     */   
/*     */   public A getDebugContext() {
/* 155 */     return this._debug;
/*     */   }
/*     */   
/*     */   public void addPoints(List<TriangulationPoint> points) {
/* 160 */     this._points.addAll(points);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\TriangulationContext.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */