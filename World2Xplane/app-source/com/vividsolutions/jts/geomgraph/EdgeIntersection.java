/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class EdgeIntersection implements Comparable {
/*     */   public Coordinate coord;
/*     */   
/*     */   public int segmentIndex;
/*     */   
/*     */   public double dist;
/*     */   
/*     */   public EdgeIntersection(Coordinate coord, int segmentIndex, double dist) {
/*  60 */     this.coord = new Coordinate(coord);
/*  61 */     this.segmentIndex = segmentIndex;
/*  62 */     this.dist = dist;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/*  65 */     return this.coord;
/*     */   }
/*     */   
/*     */   public int getSegmentIndex() {
/*  67 */     return this.segmentIndex;
/*     */   }
/*     */   
/*     */   public double getDistance() {
/*  69 */     return this.dist;
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/*  73 */     EdgeIntersection other = (EdgeIntersection)obj;
/*  74 */     return compare(other.segmentIndex, other.dist);
/*     */   }
/*     */   
/*     */   public int compare(int segmentIndex, double dist) {
/*  83 */     if (this.segmentIndex < segmentIndex)
/*  83 */       return -1; 
/*  84 */     if (this.segmentIndex > segmentIndex)
/*  84 */       return 1; 
/*  85 */     if (this.dist < dist)
/*  85 */       return -1; 
/*  86 */     if (this.dist > dist)
/*  86 */       return 1; 
/*  87 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isEndPoint(int maxSegmentIndex) {
/*  92 */     if (this.segmentIndex == 0 && this.dist == 0.0D)
/*  92 */       return true; 
/*  93 */     if (this.segmentIndex == maxSegmentIndex)
/*  93 */       return true; 
/*  94 */     return false;
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/*  99 */     out.print(this.coord);
/* 100 */     out.print(" seg # = " + this.segmentIndex);
/* 101 */     out.println(" dist = " + this.dist);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 105 */     return this.coord + " seg # = " + this.segmentIndex + " dist = " + this.dist;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\EdgeIntersection.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */