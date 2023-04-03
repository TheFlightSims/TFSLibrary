/*     */ package org.poly2tri.triangulation.delaunay.sweep;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Collections;
/*     */ import org.poly2tri.triangulation.Triangulatable;
/*     */ import org.poly2tri.triangulation.TriangulationAlgorithm;
/*     */ import org.poly2tri.triangulation.TriangulationConstraint;
/*     */ import org.poly2tri.triangulation.TriangulationContext;
/*     */ import org.poly2tri.triangulation.TriangulationPoint;
/*     */ import org.poly2tri.triangulation.delaunay.DelaunayTriangle;
/*     */ import org.poly2tri.triangulation.point.TPoint;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class DTSweepContext extends TriangulationContext<DTSweepDebugContext> {
/*  44 */   private static final Logger logger = LoggerFactory.getLogger(DTSweepContext.class);
/*     */   
/*  48 */   private final float ALPHA = 0.3F;
/*     */   
/*     */   protected AdvancingFront aFront;
/*     */   
/*     */   private TriangulationPoint _head;
/*     */   
/*     */   private TriangulationPoint _tail;
/*     */   
/*  56 */   protected Basin basin = new Basin();
/*     */   
/*  57 */   protected EdgeEvent edgeEvent = new EdgeEvent();
/*     */   
/*  59 */   private DTSweepPointComparator _comparator = new DTSweepPointComparator();
/*     */   
/*     */   public DTSweepContext() {
/*  63 */     clear();
/*     */   }
/*     */   
/*     */   public void isDebugEnabled(boolean b) {
/*  68 */     if (b)
/*  70 */       if (this._debug == null)
/*  72 */         this._debug = new DTSweepDebugContext(this);  
/*  75 */     this._debugEnabled = b;
/*     */   }
/*     */   
/*     */   public void removeFromList(DelaunayTriangle triangle) {
/*  80 */     this._triList.remove(triangle);
/*     */   }
/*     */   
/*     */   protected void meshClean(DelaunayTriangle triangle) {
/*  95 */     if (triangle != null) {
/*  97 */       ArrayDeque<DelaunayTriangle> deque = new ArrayDeque<>();
/*  98 */       deque.addFirst(triangle);
/*  99 */       triangle.isInterior(true);
/* 101 */       while (!deque.isEmpty()) {
/* 103 */         DelaunayTriangle t1 = deque.removeFirst();
/* 104 */         this._triUnit.addTriangle(t1);
/* 105 */         for (int i = 0; i < 3; i++) {
/* 107 */           if (!t1.cEdge[i]) {
/* 109 */             DelaunayTriangle t2 = t1.neighbors[i];
/* 110 */             if (t2 != null && !t2.isInterior()) {
/* 112 */               t2.isInterior(true);
/* 113 */               deque.addLast(t2);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 123 */     super.clear();
/* 124 */     this._triList.clear();
/*     */   }
/*     */   
/*     */   public AdvancingFront getAdvancingFront() {
/* 129 */     return this.aFront;
/*     */   }
/*     */   
/*     */   public void setHead(TriangulationPoint p1) {
/* 132 */     this._head = p1;
/*     */   }
/*     */   
/*     */   public TriangulationPoint getHead() {
/* 133 */     return this._head;
/*     */   }
/*     */   
/*     */   public void setTail(TriangulationPoint p1) {
/* 135 */     this._tail = p1;
/*     */   }
/*     */   
/*     */   public TriangulationPoint getTail() {
/* 136 */     return this._tail;
/*     */   }
/*     */   
/*     */   public void addNode(AdvancingFrontNode node) {
/* 142 */     this.aFront.addNode(node);
/*     */   }
/*     */   
/*     */   public void removeNode(AdvancingFrontNode node) {
/* 149 */     this.aFront.removeNode(node);
/*     */   }
/*     */   
/*     */   public AdvancingFrontNode locateNode(TriangulationPoint point) {
/* 154 */     return this.aFront.locateNode(point);
/*     */   }
/*     */   
/*     */   public void createAdvancingFront() {
/* 161 */     DelaunayTriangle iTriangle = new DelaunayTriangle(this._points.get(0), getTail(), getHead());
/* 164 */     addToList(iTriangle);
/* 166 */     AdvancingFrontNode head = new AdvancingFrontNode(iTriangle.points[1]);
/* 167 */     head.triangle = iTriangle;
/* 168 */     AdvancingFrontNode middle = new AdvancingFrontNode(iTriangle.points[0]);
/* 169 */     middle.triangle = iTriangle;
/* 170 */     AdvancingFrontNode tail = new AdvancingFrontNode(iTriangle.points[2]);
/* 172 */     this.aFront = new AdvancingFront(head, tail);
/* 173 */     this.aFront.addNode(middle);
/* 177 */     this.aFront.head.next = middle;
/* 178 */     middle.next = this.aFront.tail;
/* 179 */     middle.prev = this.aFront.head;
/* 180 */     this.aFront.tail.prev = middle;
/*     */   }
/*     */   
/*     */   class Basin {
/*     */     AdvancingFrontNode leftNode;
/*     */     
/*     */     AdvancingFrontNode bottomNode;
/*     */     
/*     */     AdvancingFrontNode rightNode;
/*     */     
/*     */     public double width;
/*     */     
/*     */     public boolean leftHighest;
/*     */   }
/*     */   
/*     */   class EdgeEvent {
/*     */     DTSweepConstraint constrainedEdge;
/*     */     
/*     */     public boolean right;
/*     */   }
/*     */   
/*     */   public void mapTriangleToNodes(DelaunayTriangle t) {
/* 207 */     for (int i = 0; i < 3; i++) {
/* 209 */       if (t.neighbors[i] == null) {
/* 211 */         AdvancingFrontNode n = this.aFront.locatePoint(t.pointCW(t.points[i]));
/* 212 */         if (n != null)
/* 214 */           n.triangle = t; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void prepareTriangulation(Triangulatable t) {
/* 223 */     super.prepareTriangulation(t);
/* 228 */     double xmin = ((TriangulationPoint)this._points.get(0)).getX(), xmax = xmin;
/* 229 */     double ymin = ((TriangulationPoint)this._points.get(0)).getY(), ymax = ymin;
/* 231 */     for (TriangulationPoint p : this._points) {
/* 233 */       if (p.getX() > xmax)
/* 234 */         xmax = p.getX(); 
/* 235 */       if (p.getX() < xmin)
/* 236 */         xmin = p.getX(); 
/* 237 */       if (p.getY() > ymax)
/* 238 */         ymax = p.getY(); 
/* 239 */       if (p.getY() < ymin)
/* 240 */         ymin = p.getY(); 
/*     */     } 
/* 243 */     double deltaX = 0.30000001192092896D * (xmax - xmin);
/* 244 */     double deltaY = 0.30000001192092896D * (ymax - ymin);
/* 245 */     TPoint p1 = new TPoint(xmax + deltaX, ymin - deltaY);
/* 246 */     TPoint p2 = new TPoint(xmin - deltaX, ymin - deltaY);
/* 248 */     setHead((TriangulationPoint)p1);
/* 249 */     setTail((TriangulationPoint)p2);
/* 253 */     Collections.sort(this._points, this._comparator);
/*     */   }
/*     */   
/*     */   public void finalizeTriangulation() {
/* 260 */     this._triUnit.addTriangles(this._triList);
/* 261 */     this._triList.clear();
/*     */   }
/*     */   
/*     */   public TriangulationConstraint newConstraint(TriangulationPoint a, TriangulationPoint b) {
/* 267 */     return new DTSweepConstraint(a, b);
/*     */   }
/*     */   
/*     */   public TriangulationAlgorithm algorithm() {
/* 273 */     return TriangulationAlgorithm.DTSweep;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\delaunay\sweep\DTSweepContext.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */