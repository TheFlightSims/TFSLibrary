/*     */ package org.opensphere.geometry.triangulation.model;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class Vertex {
/*     */   private int id;
/*     */   
/*     */   private Coordinate coordinate;
/*     */   
/*     */   private boolean border;
/*     */   
/*     */   public Vertex() {}
/*     */   
/*     */   public Vertex(int id) {
/*  58 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Vertex(int id, Coordinate coordinate) {
/*  70 */     this.id = id;
/*  71 */     setCoordinate(coordinate);
/*     */   }
/*     */   
/*     */   public Vertex(int id, boolean border) {
/*  84 */     this.id = id;
/*  85 */     this.border = border;
/*     */   }
/*     */   
/*     */   public Vertex(int id, Coordinate coordinate, boolean border) {
/* 100 */     this.id = id;
/* 101 */     this.border = border;
/* 102 */     setCoordinate(coordinate);
/*     */   }
/*     */   
/*     */   public int getId() {
/* 112 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/* 122 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/* 132 */     return this.coordinate;
/*     */   }
/*     */   
/*     */   public void setCoordinate(Coordinate c) {
/* 142 */     this.coordinate = c;
/*     */   }
/*     */   
/*     */   public boolean isBorder() {
/* 154 */     return this.border;
/*     */   }
/*     */   
/*     */   public void setBorder(boolean border) {
/* 166 */     this.border = border;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opensphere\geometry\triangulation\model\Vertex.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */