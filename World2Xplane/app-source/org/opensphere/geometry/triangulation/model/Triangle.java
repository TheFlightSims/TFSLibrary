/*     */ package org.opensphere.geometry.triangulation.model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Triangle {
/*     */   private int id;
/*     */   
/*     */   private boolean border;
/*     */   
/*  43 */   private List<Edge> edges = new ArrayList<>();
/*     */   
/*  46 */   private List<Triangle> neighbours = new ArrayList<>();
/*     */   
/*     */   public Triangle() {}
/*     */   
/*     */   public Triangle(int id) {
/*  64 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Triangle(int id, boolean border) {
/*  77 */     this.id = id;
/*  78 */     this.border = border;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  88 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  98 */     this.id = id;
/*     */   }
/*     */   
/*     */   public boolean isBorder() {
/* 110 */     return this.border;
/*     */   }
/*     */   
/*     */   public void setBorder(boolean border) {
/* 122 */     this.border = border;
/*     */   }
/*     */   
/*     */   public List<Edge> getEdges() {
/* 132 */     return this.edges;
/*     */   }
/*     */   
/*     */   public void setEdges(List<Edge> edges) {
/* 142 */     this.edges = edges;
/*     */   }
/*     */   
/*     */   public List<Triangle> getNeighbours() {
/* 152 */     return this.neighbours;
/*     */   }
/*     */   
/*     */   public void setNeighbours(List<Triangle> neighbours) {
/* 162 */     this.neighbours = neighbours;
/*     */   }
/*     */   
/*     */   public boolean addEdge(Edge edge) {
/* 173 */     return getEdges().add(edge);
/*     */   }
/*     */   
/*     */   public boolean addEdges(List<Edge> edges) {
/* 183 */     return getEdges().addAll(edges);
/*     */   }
/*     */   
/*     */   public boolean removeEdge(Edge edge) {
/* 193 */     return getEdges().remove(edge);
/*     */   }
/*     */   
/*     */   public boolean removeEdges(List<Edge> edges) {
/* 203 */     return getEdges().removeAll(edges);
/*     */   }
/*     */   
/*     */   public boolean addNeighbour(Triangle triangle) {
/* 214 */     return getNeighbours().add(triangle);
/*     */   }
/*     */   
/*     */   public boolean addNeighbours(List<Triangle> triangles) {
/* 224 */     return getNeighbours().addAll(triangles);
/*     */   }
/*     */   
/*     */   public boolean removeNeighbour(Triangle triangle) {
/* 234 */     return getNeighbours().remove(triangle);
/*     */   }
/*     */   
/*     */   public boolean removeNeighbours(List<Triangle> triangles) {
/* 244 */     return getNeighbours().removeAll(triangles);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opensphere\geometry\triangulation\model\Triangle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */