/*     */ package com.vividsolutions.jts.operation.polygonize;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Polygonizer {
/*     */   private class LineStringAdder implements GeometryComponentFilter {
/*     */     private LineStringAdder() {}
/*     */     
/*     */     public void filter(Geometry g) {
/*  77 */       if (g instanceof LineString)
/*  78 */         Polygonizer.this.add((LineString)g); 
/*     */     }
/*     */   }
/*     */   
/*  83 */   private LineStringAdder lineStringAdder = new LineStringAdder();
/*     */   
/*     */   protected PolygonizeGraph graph;
/*     */   
/*  87 */   protected Collection dangles = new ArrayList();
/*     */   
/*  88 */   protected List cutEdges = new ArrayList();
/*     */   
/*  89 */   protected List invalidRingLines = new ArrayList();
/*     */   
/*  91 */   protected List holeList = null;
/*     */   
/*  92 */   protected List shellList = null;
/*     */   
/*  93 */   protected List polyList = null;
/*     */   
/*     */   private boolean isCheckingRingsValid = true;
/*     */   
/*     */   public void add(Collection geomList) {
/* 115 */     for (Iterator<Geometry> i = geomList.iterator(); i.hasNext(); ) {
/* 116 */       Geometry geometry = i.next();
/* 117 */       add(geometry);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(Geometry g) {
/* 131 */     g.apply(this.lineStringAdder);
/*     */   }
/*     */   
/*     */   private void add(LineString line) {
/* 142 */     if (this.graph == null)
/* 143 */       this.graph = new PolygonizeGraph(line.getFactory()); 
/* 144 */     this.graph.addEdge(line);
/*     */   }
/*     */   
/*     */   public void setCheckRingsValid(boolean isCheckingRingsValid) {
/* 157 */     this.isCheckingRingsValid = isCheckingRingsValid;
/*     */   }
/*     */   
/*     */   public Collection getPolygons() {
/* 166 */     polygonize();
/* 167 */     return this.polyList;
/*     */   }
/*     */   
/*     */   public Collection getDangles() {
/* 176 */     polygonize();
/* 177 */     return this.dangles;
/*     */   }
/*     */   
/*     */   public Collection getCutEdges() {
/* 186 */     polygonize();
/* 187 */     return this.cutEdges;
/*     */   }
/*     */   
/*     */   public Collection getInvalidRingLines() {
/* 196 */     polygonize();
/* 197 */     return this.invalidRingLines;
/*     */   }
/*     */   
/*     */   private void polygonize() {
/* 206 */     if (this.polyList != null)
/*     */       return; 
/* 207 */     this.polyList = new ArrayList();
/* 210 */     if (this.graph == null)
/*     */       return; 
/* 212 */     this.dangles = this.graph.deleteDangles();
/* 213 */     this.cutEdges = this.graph.deleteCutEdges();
/* 214 */     List edgeRingList = this.graph.getEdgeRings();
/* 218 */     List validEdgeRingList = new ArrayList();
/* 219 */     this.invalidRingLines = new ArrayList();
/* 220 */     if (this.isCheckingRingsValid) {
/* 221 */       findValidRings(edgeRingList, validEdgeRingList, this.invalidRingLines);
/*     */     } else {
/* 224 */       validEdgeRingList = edgeRingList;
/*     */     } 
/* 228 */     findShellsAndHoles(validEdgeRingList);
/* 229 */     assignHolesToShells(this.holeList, this.shellList);
/* 233 */     this.polyList = new ArrayList();
/* 234 */     for (Iterator<EdgeRing> i = this.shellList.iterator(); i.hasNext(); ) {
/* 235 */       EdgeRing er = i.next();
/* 236 */       this.polyList.add(er.getPolygon());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void findValidRings(List edgeRingList, List<EdgeRing> validEdgeRingList, List<LineString> invalidRingList) {
/* 242 */     for (Iterator<EdgeRing> i = edgeRingList.iterator(); i.hasNext(); ) {
/* 243 */       EdgeRing er = i.next();
/* 244 */       if (er.isValid()) {
/* 245 */         validEdgeRingList.add(er);
/*     */         continue;
/*     */       } 
/* 247 */       invalidRingList.add(er.getLineString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void findShellsAndHoles(List edgeRingList) {
/* 253 */     this.holeList = new ArrayList();
/* 254 */     this.shellList = new ArrayList();
/* 255 */     for (Iterator<EdgeRing> i = edgeRingList.iterator(); i.hasNext(); ) {
/* 256 */       EdgeRing er = i.next();
/* 257 */       if (er.isHole()) {
/* 258 */         this.holeList.add(er);
/*     */         continue;
/*     */       } 
/* 260 */       this.shellList.add(er);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void assignHolesToShells(List holeList, List shellList) {
/* 267 */     for (Iterator<EdgeRing> i = holeList.iterator(); i.hasNext(); ) {
/* 268 */       EdgeRing holeER = i.next();
/* 269 */       assignHoleToShell(holeER, shellList);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void assignHoleToShell(EdgeRing holeER, List shellList) {
/* 275 */     EdgeRing shell = EdgeRing.findEdgeRingContaining(holeER, shellList);
/* 276 */     if (shell != null)
/* 277 */       shell.addHole(holeER.getRing()); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\polygonize\Polygonizer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */