/*     */ package com.vividsolutions.jts.dissolve;
/*     */ 
/*     */ import com.vividsolutions.jts.edgegraph.HalfEdge;
/*     */ import com.vividsolutions.jts.edgegraph.MarkHalfEdge;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Stack;
/*     */ 
/*     */ public class LineDissolver {
/*     */   private Geometry result;
/*     */   
/*     */   private GeometryFactory factory;
/*     */   
/*     */   private DissolveEdgeGraph graph;
/*     */   
/*     */   public static Geometry dissolve(Geometry g) {
/*  52 */     LineDissolver d = new LineDissolver();
/*  53 */     d.add(g);
/*  54 */     return d.getResult();
/*     */   }
/*     */   
/*  60 */   private List lines = new ArrayList();
/*     */   
/*     */   private Stack nodeEdgeStack;
/*     */   
/*     */   private DissolveHalfEdge ringStartEdge;
/*     */   
/*     */   public void add(Geometry geometry) {
/*  76 */     geometry.apply(new GeometryComponentFilter() {
/*     */           public void filter(Geometry component) {
/*  78 */             if (component instanceof LineString)
/*  79 */               LineDissolver.this.add((LineString)component); 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void add(Collection geometries) {
/*  93 */     for (Iterator<Geometry> i = geometries.iterator(); i.hasNext(); ) {
/*  94 */       Geometry geometry = i.next();
/*  95 */       add(geometry);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void add(LineString lineString) {
/* 100 */     if (this.factory == null)
/* 101 */       this.factory = lineString.getFactory(); 
/* 103 */     CoordinateSequence seq = lineString.getCoordinateSequence();
/* 104 */     for (int i = 1; i < seq.size(); i++) {
/* 105 */       DissolveHalfEdge e = (DissolveHalfEdge)this.graph.addEdge(seq.getCoordinate(i - 1), seq.getCoordinate(i));
/* 110 */       if (i == 1)
/* 111 */         e.setStart(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Geometry getResult() {
/* 122 */     if (this.result == null)
/* 123 */       computeResult(); 
/* 124 */     return this.result;
/*     */   }
/*     */   
/*     */   private void computeResult() {
/* 128 */     Collection edges = this.graph.getVertexEdges();
/* 129 */     for (Iterator<HalfEdge> i = edges.iterator(); i.hasNext(); ) {
/* 130 */       HalfEdge e = i.next();
/* 131 */       if (MarkHalfEdge.isMarked(e))
/*     */         continue; 
/* 132 */       process(e);
/*     */     } 
/* 134 */     this.result = this.factory.buildGeometry(this.lines);
/*     */   }
/*     */   
/*     */   public LineDissolver() {
/* 137 */     this.nodeEdgeStack = new Stack();
/*     */     this.graph = new DissolveEdgeGraph();
/*     */   }
/*     */   
/*     */   private void process(HalfEdge e) {
/* 140 */     HalfEdge eNode = e.prevNode();
/* 142 */     if (eNode == null)
/* 143 */       eNode = e; 
/* 144 */     stackEdges(eNode);
/* 146 */     buildLines();
/*     */   }
/*     */   
/*     */   private void buildLines() {
/* 155 */     while (!this.nodeEdgeStack.empty()) {
/* 156 */       HalfEdge e = this.nodeEdgeStack.pop();
/* 157 */       if (MarkHalfEdge.isMarked(e))
/*     */         continue; 
/* 159 */       buildLine(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateRingStartEdge(DissolveHalfEdge e) {
/* 182 */     if (!e.isStart()) {
/* 183 */       e = (DissolveHalfEdge)e.sym();
/* 184 */       if (!e.isStart())
/*     */         return; 
/*     */     } 
/* 187 */     if (this.ringStartEdge == null) {
/* 188 */       this.ringStartEdge = e;
/*     */       return;
/*     */     } 
/* 191 */     if (e.orig().compareTo(this.ringStartEdge.orig()) < 0)
/* 192 */       this.ringStartEdge = e; 
/*     */   }
/*     */   
/*     */   private void buildLine(HalfEdge eStart) {
/* 210 */     CoordinateList line = new CoordinateList();
/* 211 */     DissolveHalfEdge e = (DissolveHalfEdge)eStart;
/* 212 */     this.ringStartEdge = null;
/* 214 */     MarkHalfEdge.markBoth((HalfEdge)e);
/* 215 */     line.add(e.orig().clone(), false);
/* 217 */     while (e.sym().degree() == 2) {
/* 218 */       updateRingStartEdge(e);
/* 219 */       DissolveHalfEdge eNext = (DissolveHalfEdge)e.next();
/* 221 */       if (eNext == eStart) {
/* 222 */         buildRing((HalfEdge)this.ringStartEdge);
/*     */         return;
/*     */       } 
/* 226 */       line.add(eNext.orig().clone(), false);
/* 227 */       e = eNext;
/* 228 */       MarkHalfEdge.markBoth((HalfEdge)e);
/*     */     } 
/* 231 */     line.add(e.dest().clone(), false);
/* 234 */     stackEdges(e.sym());
/* 236 */     addLine(line);
/*     */   }
/*     */   
/*     */   private void buildRing(HalfEdge eStartRing) {
/* 240 */     CoordinateList line = new CoordinateList();
/* 241 */     HalfEdge e = eStartRing;
/* 243 */     line.add(e.orig().clone(), false);
/* 245 */     while (e.sym().degree() == 2) {
/* 246 */       HalfEdge eNext = e.next();
/* 248 */       if (eNext == eStartRing)
/*     */         break; 
/* 252 */       line.add(eNext.orig().clone(), false);
/* 253 */       e = eNext;
/*     */     } 
/* 256 */     line.add(e.dest().clone(), false);
/* 259 */     addLine(line);
/*     */   }
/*     */   
/*     */   private void addLine(CoordinateList line) {
/* 263 */     this.lines.add(this.factory.createLineString(line.toCoordinateArray()));
/*     */   }
/*     */   
/*     */   private void stackEdges(HalfEdge node) {
/* 272 */     HalfEdge e = node;
/*     */     do {
/* 274 */       if (!MarkHalfEdge.isMarked(e))
/* 275 */         this.nodeEdgeStack.add(e); 
/* 276 */       e = e.oNext();
/* 277 */     } while (e != node);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\dissolve\LineDissolver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */