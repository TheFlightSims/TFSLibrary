/*     */ package com.vividsolutions.jts.triangulate.quadedge;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class QuadEdgeTriangle {
/*     */   private QuadEdge[] edge;
/*     */   
/*     */   private Object data;
/*     */   
/*     */   public static List createOn(QuadEdgeSubdivision subdiv) {
/*  78 */     QuadEdgeTriangleBuilderVisitor visitor = new QuadEdgeTriangleBuilderVisitor();
/*  79 */     subdiv.visitTriangles(visitor, false);
/*  80 */     return visitor.getTriangles();
/*     */   }
/*     */   
/*     */   public static boolean contains(Vertex[] tri, Coordinate pt) {
/*  94 */     Coordinate[] ring = { tri[0].getCoordinate(), tri[1].getCoordinate(), tri[2].getCoordinate(), tri[0].getCoordinate() };
/*  96 */     return CGAlgorithms.isPointInRing(pt, ring);
/*     */   }
/*     */   
/*     */   public static boolean contains(QuadEdge[] tri, Coordinate pt) {
/* 110 */     Coordinate[] ring = { tri[0].orig().getCoordinate(), tri[1].orig().getCoordinate(), tri[2].orig().getCoordinate(), tri[0].orig().getCoordinate() };
/* 113 */     return CGAlgorithms.isPointInRing(pt, ring);
/*     */   }
/*     */   
/*     */   public static Geometry toPolygon(Vertex[] v) {
/* 117 */     Coordinate[] ringPts = { v[0].getCoordinate(), v[1].getCoordinate(), v[2].getCoordinate(), v[0].getCoordinate() };
/* 119 */     GeometryFactory fact = new GeometryFactory();
/* 120 */     LinearRing ring = fact.createLinearRing(ringPts);
/* 121 */     Polygon tri = fact.createPolygon(ring, null);
/* 122 */     return (Geometry)tri;
/*     */   }
/*     */   
/*     */   public static Geometry toPolygon(QuadEdge[] e) {
/* 126 */     Coordinate[] ringPts = { e[0].orig().getCoordinate(), e[1].orig().getCoordinate(), e[2].orig().getCoordinate(), e[0].orig().getCoordinate() };
/* 129 */     GeometryFactory fact = new GeometryFactory();
/* 130 */     LinearRing ring = fact.createLinearRing(ringPts);
/* 131 */     Polygon tri = fact.createPolygon(ring, null);
/* 132 */     return (Geometry)tri;
/*     */   }
/*     */   
/*     */   public static int nextIndex(int index) {
/* 143 */     return index = (index + 1) % 3;
/*     */   }
/*     */   
/*     */   public QuadEdgeTriangle(QuadEdge[] edge) {
/* 155 */     this.edge = (QuadEdge[])edge.clone();
/* 157 */     for (int i = 0; i < 3; i++)
/* 158 */       edge[i].setData(this); 
/*     */   }
/*     */   
/*     */   public void setData(Object data) {
/* 168 */     this.data = data;
/*     */   }
/*     */   
/*     */   public Object getData() {
/* 177 */     return this.data;
/*     */   }
/*     */   
/*     */   public void kill() {
/* 181 */     this.edge = null;
/*     */   }
/*     */   
/*     */   public boolean isLive() {
/* 185 */     return (this.edge != null);
/*     */   }
/*     */   
/*     */   public QuadEdge[] getEdges() {
/* 189 */     return this.edge;
/*     */   }
/*     */   
/*     */   public QuadEdge getEdge(int i) {
/* 193 */     return this.edge[i];
/*     */   }
/*     */   
/*     */   public Vertex getVertex(int i) {
/* 197 */     return this.edge[i].orig();
/*     */   }
/*     */   
/*     */   public Vertex[] getVertices() {
/* 206 */     Vertex[] vert = new Vertex[3];
/* 207 */     for (int i = 0; i < 3; i++)
/* 208 */       vert[i] = getVertex(i); 
/* 210 */     return vert;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/* 214 */     return this.edge[i].orig().getCoordinate();
/*     */   }
/*     */   
/*     */   public int getEdgeIndex(QuadEdge e) {
/* 226 */     for (int i = 0; i < 3; i++) {
/* 227 */       if (this.edge[i] == e)
/* 228 */         return i; 
/*     */     } 
/* 230 */     return -1;
/*     */   }
/*     */   
/*     */   public int getEdgeIndex(Vertex v) {
/* 242 */     for (int i = 0; i < 3; i++) {
/* 243 */       if (this.edge[i].orig() == v)
/* 244 */         return i; 
/*     */     } 
/* 246 */     return -1;
/*     */   }
/*     */   
/*     */   public void getEdgeSegment(int i, LineSegment seg) {
/* 250 */     seg.p0 = this.edge[i].orig().getCoordinate();
/* 251 */     int nexti = (i + 1) % 3;
/* 252 */     seg.p1 = this.edge[nexti].orig().getCoordinate();
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/* 256 */     Coordinate[] pts = new Coordinate[4];
/* 257 */     for (int i = 0; i < 3; i++)
/* 258 */       pts[i] = this.edge[i].orig().getCoordinate(); 
/* 260 */     pts[3] = new Coordinate(pts[0]);
/* 261 */     return pts;
/*     */   }
/*     */   
/*     */   public boolean contains(Coordinate pt) {
/* 265 */     Coordinate[] ring = getCoordinates();
/* 266 */     return CGAlgorithms.isPointInRing(pt, ring);
/*     */   }
/*     */   
/*     */   public Polygon getGeometry(GeometryFactory fact) {
/* 270 */     LinearRing ring = fact.createLinearRing(getCoordinates());
/* 271 */     Polygon tri = fact.createPolygon(ring, null);
/* 272 */     return tri;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 276 */     return getGeometry(new GeometryFactory()).toString();
/*     */   }
/*     */   
/*     */   public boolean isBorder() {
/* 285 */     for (int i = 0; i < 3; i++) {
/* 286 */       if (getAdjacentTriangleAcrossEdge(i) == null)
/* 287 */         return true; 
/*     */     } 
/* 289 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isBorder(int i) {
/* 293 */     return (getAdjacentTriangleAcrossEdge(i) == null);
/*     */   }
/*     */   
/*     */   public QuadEdgeTriangle getAdjacentTriangleAcrossEdge(int edgeIndex) {
/* 297 */     return (QuadEdgeTriangle)getEdge(edgeIndex).sym().getData();
/*     */   }
/*     */   
/*     */   public int getAdjacentTriangleEdgeIndex(int i) {
/* 301 */     return getAdjacentTriangleAcrossEdge(i).getEdgeIndex(getEdge(i).sym());
/*     */   }
/*     */   
/*     */   public List getTrianglesAdjacentToVertex(int vertexIndex) {
/* 313 */     List<QuadEdgeTriangle> adjTris = new ArrayList();
/* 315 */     QuadEdge start = getEdge(vertexIndex);
/* 316 */     QuadEdge qe = start;
/*     */     do {
/* 318 */       QuadEdgeTriangle adjTri = (QuadEdgeTriangle)qe.getData();
/* 319 */       if (adjTri != null)
/* 320 */         adjTris.add(adjTri); 
/* 322 */       qe = qe.oNext();
/* 323 */     } while (qe != start);
/* 325 */     return adjTris;
/*     */   }
/*     */   
/*     */   public QuadEdgeTriangle[] getNeighbours() {
/* 336 */     QuadEdgeTriangle[] neigh = new QuadEdgeTriangle[3];
/* 337 */     for (int i = 0; i < 3; i++)
/* 338 */       neigh[i] = (QuadEdgeTriangle)getEdge(i).sym().getData(); 
/* 340 */     return neigh;
/*     */   }
/*     */   
/*     */   private static class QuadEdgeTriangleBuilderVisitor implements TriangleVisitor {
/* 344 */     private List triangles = new ArrayList();
/*     */     
/*     */     public void visit(QuadEdge[] edges) {
/* 350 */       this.triangles.add(new QuadEdgeTriangle(edges));
/*     */     }
/*     */     
/*     */     public List getTriangles() {
/* 354 */       return this.triangles;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\quadedge\QuadEdgeTriangle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */