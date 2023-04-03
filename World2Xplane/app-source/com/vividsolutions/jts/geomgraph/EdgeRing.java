/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class EdgeRing {
/*     */   protected DirectedEdge startDe;
/*     */   
/*  55 */   private int maxNodeDegree = -1;
/*     */   
/*  56 */   private List edges = new ArrayList();
/*     */   
/*  57 */   private List pts = new ArrayList();
/*     */   
/*  58 */   private Label label = new Label(-1);
/*     */   
/*     */   private LinearRing ring;
/*     */   
/*     */   private boolean isHole;
/*     */   
/*     */   private EdgeRing shell;
/*     */   
/*  62 */   private ArrayList holes = new ArrayList();
/*     */   
/*     */   protected GeometryFactory geometryFactory;
/*     */   
/*     */   public EdgeRing(DirectedEdge start, GeometryFactory geometryFactory) {
/*  67 */     this.geometryFactory = geometryFactory;
/*  68 */     computePoints(start);
/*  69 */     computeRing();
/*     */   }
/*     */   
/*     */   public boolean isIsolated() {
/*  74 */     return (this.label.getGeometryCount() == 1);
/*     */   }
/*     */   
/*     */   public boolean isHole() {
/*  79 */     return this.isHole;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/*  82 */     return this.pts.get(i);
/*     */   }
/*     */   
/*     */   public LinearRing getLinearRing() {
/*  83 */     return this.ring;
/*     */   }
/*     */   
/*     */   public Label getLabel() {
/*  84 */     return this.label;
/*     */   }
/*     */   
/*     */   public boolean isShell() {
/*  85 */     return (this.shell == null);
/*     */   }
/*     */   
/*     */   public EdgeRing getShell() {
/*  86 */     return this.shell;
/*     */   }
/*     */   
/*     */   public void setShell(EdgeRing shell) {
/*  89 */     this.shell = shell;
/*  90 */     if (shell != null)
/*  90 */       shell.addHole(this); 
/*     */   }
/*     */   
/*     */   public void addHole(EdgeRing ring) {
/*  92 */     this.holes.add(ring);
/*     */   }
/*     */   
/*     */   public Polygon toPolygon(GeometryFactory geometryFactory) {
/*  96 */     LinearRing[] holeLR = new LinearRing[this.holes.size()];
/*  97 */     for (int i = 0; i < this.holes.size(); i++)
/*  98 */       holeLR[i] = ((EdgeRing)this.holes.get(i)).getLinearRing(); 
/* 100 */     Polygon poly = geometryFactory.createPolygon(getLinearRing(), holeLR);
/* 101 */     return poly;
/*     */   }
/*     */   
/*     */   public void computeRing() {
/* 110 */     if (this.ring != null)
/*     */       return; 
/* 111 */     Coordinate[] coord = new Coordinate[this.pts.size()];
/* 112 */     for (int i = 0; i < this.pts.size(); i++)
/* 113 */       coord[i] = this.pts.get(i); 
/* 115 */     this.ring = this.geometryFactory.createLinearRing(coord);
/* 116 */     this.isHole = CGAlgorithms.isCCW(this.ring.getCoordinates());
/*     */   }
/*     */   
/*     */   public abstract DirectedEdge getNext(DirectedEdge paramDirectedEdge);
/*     */   
/*     */   public abstract void setEdgeRing(DirectedEdge paramDirectedEdge, EdgeRing paramEdgeRing);
/*     */   
/*     */   public List getEdges() {
/* 125 */     return this.edges;
/*     */   }
/*     */   
/*     */   protected void computePoints(DirectedEdge start) {
/* 133 */     this.startDe = start;
/* 134 */     DirectedEdge de = start;
/* 135 */     boolean isFirstEdge = true;
/*     */     do {
/* 138 */       if (de == null)
/* 139 */         throw new TopologyException("Found null DirectedEdge"); 
/* 140 */       if (de.getEdgeRing() == this)
/* 141 */         throw new TopologyException("Directed Edge visited twice during ring-building at " + de.getCoordinate()); 
/* 143 */       this.edges.add(de);
/* 146 */       Label label = de.getLabel();
/* 147 */       Assert.isTrue(label.isArea());
/* 148 */       mergeLabel(label);
/* 149 */       addPoints(de.getEdge(), de.isForward(), isFirstEdge);
/* 150 */       isFirstEdge = false;
/* 151 */       setEdgeRing(de, this);
/* 152 */       de = getNext(de);
/* 153 */     } while (de != this.startDe);
/*     */   }
/*     */   
/*     */   public int getMaxNodeDegree() {
/* 158 */     if (this.maxNodeDegree < 0)
/* 158 */       computeMaxNodeDegree(); 
/* 159 */     return this.maxNodeDegree;
/*     */   }
/*     */   
/*     */   private void computeMaxNodeDegree() {
/* 164 */     this.maxNodeDegree = 0;
/* 165 */     DirectedEdge de = this.startDe;
/*     */     while (true) {
/* 167 */       Node node = de.getNode();
/* 168 */       int degree = ((DirectedEdgeStar)node.getEdges()).getOutgoingDegree(this);
/* 169 */       if (degree > this.maxNodeDegree)
/* 169 */         this.maxNodeDegree = degree; 
/* 170 */       de = getNext(de);
/* 171 */       if (de == this.startDe) {
/* 172 */         this.maxNodeDegree *= 2;
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setInResult() {
/* 178 */     DirectedEdge de = this.startDe;
/*     */     do {
/* 180 */       de.getEdge().setInResult(true);
/* 181 */       de = de.getNext();
/* 182 */     } while (de != this.startDe);
/*     */   }
/*     */   
/*     */   protected void mergeLabel(Label deLabel) {
/* 187 */     mergeLabel(deLabel, 0);
/* 188 */     mergeLabel(deLabel, 1);
/*     */   }
/*     */   
/*     */   protected void mergeLabel(Label deLabel, int geomIndex) {
/* 199 */     int loc = deLabel.getLocation(geomIndex, 2);
/* 201 */     if (loc == -1)
/*     */       return; 
/* 203 */     if (this.label.getLocation(geomIndex) == -1) {
/* 204 */       this.label.setLocation(geomIndex, loc);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void addPoints(Edge edge, boolean isForward, boolean isFirstEdge) {
/* 210 */     Coordinate[] edgePts = edge.getCoordinates();
/* 211 */     if (isForward) {
/* 212 */       int startIndex = 1;
/* 213 */       if (isFirstEdge)
/* 213 */         startIndex = 0; 
/* 214 */       for (int i = startIndex; i < edgePts.length; i++)
/* 215 */         this.pts.add(edgePts[i]); 
/*     */     } else {
/* 219 */       int startIndex = edgePts.length - 2;
/* 220 */       if (isFirstEdge)
/* 220 */         startIndex = edgePts.length - 1; 
/* 221 */       for (int i = startIndex; i >= 0; i--)
/* 222 */         this.pts.add(edgePts[i]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsPoint(Coordinate p) {
/* 233 */     LinearRing shell = getLinearRing();
/* 234 */     Envelope env = shell.getEnvelopeInternal();
/* 235 */     if (!env.contains(p))
/* 235 */       return false; 
/* 236 */     if (!CGAlgorithms.isPointInRing(p, shell.getCoordinates()))
/* 236 */       return false; 
/* 238 */     for (Iterator<EdgeRing> i = this.holes.iterator(); i.hasNext(); ) {
/* 239 */       EdgeRing hole = i.next();
/* 240 */       if (hole.containsPoint(p))
/* 241 */         return false; 
/*     */     } 
/* 243 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\EdgeRing.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */