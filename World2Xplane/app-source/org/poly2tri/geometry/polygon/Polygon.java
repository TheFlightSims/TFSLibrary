/*     */ package org.poly2tri.geometry.polygon;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.poly2tri.triangulation.Triangulatable;
/*     */ import org.poly2tri.triangulation.TriangulationContext;
/*     */ import org.poly2tri.triangulation.TriangulationMode;
/*     */ import org.poly2tri.triangulation.TriangulationPoint;
/*     */ import org.poly2tri.triangulation.delaunay.DelaunayTriangle;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class Polygon implements Triangulatable {
/*  39 */   private static final Logger logger = LoggerFactory.getLogger(Polygon.class);
/*     */   
/*  41 */   protected ArrayList<TriangulationPoint> _points = new ArrayList<>();
/*     */   
/*     */   protected ArrayList<TriangulationPoint> _steinerPoints;
/*     */   
/*     */   protected ArrayList<Polygon> _holes;
/*     */   
/*     */   protected List<DelaunayTriangle> m_triangles;
/*     */   
/*     */   protected PolygonPoint _last;
/*     */   
/*     */   public Polygon(PolygonPoint p1, PolygonPoint p2, PolygonPoint p3) {
/*  58 */     p1._next = p2;
/*  59 */     p2._next = p3;
/*  60 */     p3._next = p1;
/*  61 */     p1._previous = p3;
/*  62 */     p2._previous = p1;
/*  63 */     p3._previous = p2;
/*  64 */     this._points.add(p1);
/*  65 */     this._points.add(p2);
/*  66 */     this._points.add(p3);
/*     */   }
/*     */   
/*     */   public Polygon(List<PolygonPoint> points) {
/*  78 */     if (((PolygonPoint)points.get(0)).equals(points.get(points.size() - 1)))
/*  80 */       points.remove(points.size() - 1); 
/*  82 */     this._points.addAll(points);
/*     */   }
/*     */   
/*     */   public Polygon(PolygonPoint[] points) {
/*  92 */     this(Arrays.asList(points));
/*     */   }
/*     */   
/*     */   public TriangulationMode getTriangulationMode() {
/*  97 */     return TriangulationMode.POLYGON;
/*     */   }
/*     */   
/*     */   public int pointCount() {
/* 102 */     int count = this._points.size();
/* 103 */     if (this._steinerPoints != null)
/* 105 */       count += this._steinerPoints.size(); 
/* 107 */     return count;
/*     */   }
/*     */   
/*     */   public void addSteinerPoint(TriangulationPoint point) {
/* 112 */     if (this._steinerPoints == null)
/* 114 */       this._steinerPoints = new ArrayList<>(); 
/* 116 */     this._steinerPoints.add(point);
/*     */   }
/*     */   
/*     */   public void addSteinerPoints(List<TriangulationPoint> points) {
/* 121 */     if (this._steinerPoints == null)
/* 123 */       this._steinerPoints = new ArrayList<>(); 
/* 125 */     this._steinerPoints.addAll(points);
/*     */   }
/*     */   
/*     */   public void clearSteinerPoints() {
/* 130 */     if (this._steinerPoints != null)
/* 132 */       this._steinerPoints.clear(); 
/*     */   }
/*     */   
/*     */   public void addHole(Polygon poly) {
/* 142 */     if (this._holes == null)
/* 144 */       this._holes = new ArrayList<>(); 
/* 146 */     this._holes.add(poly);
/*     */   }
/*     */   
/*     */   public void insertPointAfter(PolygonPoint a, PolygonPoint newPoint) {
/* 161 */     int index = this._points.indexOf(a);
/* 162 */     if (index != -1) {
/* 164 */       newPoint.setNext(a.getNext());
/* 165 */       newPoint.setPrevious(a);
/* 166 */       a.getNext().setPrevious(newPoint);
/* 167 */       a.setNext(newPoint);
/* 168 */       this._points.add(index + 1, newPoint);
/*     */     } else {
/* 172 */       throw new RuntimeException("Tried to insert a point into a Polygon after a point not belonging to the Polygon");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addPoints(List<PolygonPoint> list) {
/* 179 */     for (PolygonPoint p : list) {
/* 181 */       p.setPrevious(this._last);
/* 182 */       if (this._last != null) {
/* 184 */         p.setNext(this._last.getNext());
/* 185 */         this._last.setNext(p);
/*     */       } 
/* 187 */       this._last = p;
/* 188 */       this._points.add(p);
/*     */     } 
/* 190 */     PolygonPoint first = (PolygonPoint)this._points.get(0);
/* 191 */     this._last.setNext(first);
/* 192 */     first.setPrevious(this._last);
/*     */   }
/*     */   
/*     */   public void addPoint(PolygonPoint p) {
/* 202 */     p.setPrevious(this._last);
/* 203 */     p.setNext(this._last.getNext());
/* 204 */     this._last.setNext(p);
/* 205 */     this._points.add(p);
/*     */   }
/*     */   
/*     */   public void removePoint(PolygonPoint p) {
/* 212 */     PolygonPoint next = p.getNext();
/* 213 */     PolygonPoint prev = p.getPrevious();
/* 214 */     prev.setNext(next);
/* 215 */     next.setPrevious(prev);
/* 216 */     this._points.remove(p);
/*     */   }
/*     */   
/*     */   public PolygonPoint getPoint() {
/* 221 */     return this._last;
/*     */   }
/*     */   
/*     */   public List<TriangulationPoint> getPoints() {
/* 226 */     return this._points;
/*     */   }
/*     */   
/*     */   public List<DelaunayTriangle> getTriangles() {
/* 231 */     return this.m_triangles;
/*     */   }
/*     */   
/*     */   public void addTriangle(DelaunayTriangle t) {
/* 236 */     this.m_triangles.add(t);
/*     */   }
/*     */   
/*     */   public void addTriangles(List<DelaunayTriangle> list) {
/* 241 */     this.m_triangles.addAll(list);
/*     */   }
/*     */   
/*     */   public void clearTriangulation() {
/* 246 */     if (this.m_triangles != null)
/* 248 */       this.m_triangles.clear(); 
/*     */   }
/*     */   
/*     */   public void prepareTriangulation(TriangulationContext<?> tcx) {
/* 257 */     if (this.m_triangles == null) {
/* 259 */       this.m_triangles = new ArrayList<>(this._points.size());
/*     */     } else {
/* 263 */       this.m_triangles.clear();
/*     */     } 
/* 267 */     for (int i = 0; i < this._points.size() - 1; i++)
/* 269 */       tcx.newConstraint(this._points.get(i), this._points.get(i + 1)); 
/* 271 */     tcx.newConstraint(this._points.get(0), this._points.get(this._points.size() - 1));
/* 272 */     tcx.addPoints(this._points);
/* 275 */     if (this._holes != null)
/* 277 */       for (Polygon p : this._holes) {
/* 279 */         for (int j = 0; j < p._points.size() - 1; j++)
/* 281 */           tcx.newConstraint(p._points.get(j), p._points.get(j + 1)); 
/* 283 */         tcx.newConstraint(p._points.get(0), p._points.get(p._points.size() - 1));
/* 284 */         tcx.addPoints(p._points);
/*     */       }  
/* 288 */     if (this._steinerPoints != null)
/* 290 */       tcx.addPoints(this._steinerPoints); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\geometry\polygon\Polygon.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */