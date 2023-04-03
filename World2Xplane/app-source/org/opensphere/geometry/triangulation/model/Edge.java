/*     */ package org.opensphere.geometry.triangulation.model;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Edge {
/*     */   private int id;
/*     */   
/*     */   private LineSegment geometry;
/*     */   
/*     */   private boolean border;
/*     */   
/*     */   private Vertex oV;
/*     */   
/*     */   private Vertex eV;
/*     */   
/*  54 */   private List<Triangle> triangles = new ArrayList<>();
/*     */   
/*  57 */   private List<Edge> incidentEdges = new ArrayList<>();
/*     */   
/*     */   public Edge() {}
/*     */   
/*     */   public Edge(int id) {
/*  74 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Edge(int id, LineSegment geometry) {
/*  86 */     this.id = id;
/*  87 */     this.geometry = geometry;
/*     */   }
/*     */   
/*     */   public Edge(int id, boolean border) {
/* 100 */     this.id = id;
/* 101 */     this.border = border;
/*     */   }
/*     */   
/*     */   public Edge(int id, LineSegment geometry, boolean border) {
/* 116 */     this.id = id;
/* 117 */     this.geometry = geometry;
/* 118 */     this.border = border;
/*     */   }
/*     */   
/*     */   public Edge(int id, LineSegment geometry, Vertex oV, Vertex eV, boolean border) {
/* 137 */     this.id = id;
/* 138 */     this.geometry = geometry;
/* 139 */     this.oV = oV;
/* 140 */     this.eV = eV;
/* 141 */     this.border = border;
/*     */   }
/*     */   
/*     */   public int getId() {
/* 152 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/* 162 */     this.id = id;
/*     */   }
/*     */   
/*     */   public LineSegment getGeometry() {
/* 172 */     return this.geometry;
/*     */   }
/*     */   
/*     */   public void setGeometry(LineSegment geometry) {
/* 182 */     this.geometry = geometry;
/*     */   }
/*     */   
/*     */   public boolean isBorder() {
/* 194 */     return this.border;
/*     */   }
/*     */   
/*     */   public void setBorder(boolean border) {
/* 206 */     this.border = border;
/*     */   }
/*     */   
/*     */   public Vertex getOV() {
/* 216 */     return this.oV;
/*     */   }
/*     */   
/*     */   public void setOV(Vertex oV) {
/* 226 */     this.oV = oV;
/*     */   }
/*     */   
/*     */   public Vertex getEV() {
/* 236 */     return this.eV;
/*     */   }
/*     */   
/*     */   public void setEV(Vertex eV) {
/* 246 */     this.eV = eV;
/*     */   }
/*     */   
/*     */   public List<Triangle> getTriangles() {
/* 256 */     return this.triangles;
/*     */   }
/*     */   
/*     */   public void setTriangles(List<Triangle> triangles) {
/* 266 */     this.triangles = triangles;
/*     */   }
/*     */   
/*     */   public List<Edge> getIncidentEdges() {
/* 276 */     return this.incidentEdges;
/*     */   }
/*     */   
/*     */   public void setIncidentEdges(List<Edge> edges) {
/* 286 */     this.incidentEdges = edges;
/*     */   }
/*     */   
/*     */   public boolean addTriangle(Triangle triangle) {
/* 296 */     return getTriangles().add(triangle);
/*     */   }
/*     */   
/*     */   public boolean addTriangles(List<Triangle> triangles) {
/* 306 */     return getTriangles().addAll(triangles);
/*     */   }
/*     */   
/*     */   public boolean removeTriangle(Triangle triangle) {
/* 316 */     return getTriangles().remove(triangle);
/*     */   }
/*     */   
/*     */   public boolean removeTriangles(List<Triangle> triangles) {
/* 326 */     return getTriangles().removeAll(triangles);
/*     */   }
/*     */   
/*     */   public boolean addIncidentEdge(Edge edge) {
/* 336 */     return getIncidentEdges().add(edge);
/*     */   }
/*     */   
/*     */   public boolean addIncidentEdges(List<Edge> edges) {
/* 346 */     return getIncidentEdges().addAll(edges);
/*     */   }
/*     */   
/*     */   public boolean removeIncidentEdge(Edge edge) {
/* 356 */     return getIncidentEdges().remove(edge);
/*     */   }
/*     */   
/*     */   public boolean removeAllIncidentEdges(List<Edge> edges) {
/* 366 */     return getIncidentEdges().removeAll(edges);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opensphere\geometry\triangulation\model\Edge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */