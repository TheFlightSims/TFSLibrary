/*     */ package org.poly2tri.triangulation;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.poly2tri.geometry.primitives.Point;
/*     */ import org.poly2tri.triangulation.delaunay.sweep.DTSweepConstraint;
/*     */ 
/*     */ public abstract class TriangulationPoint extends Point {
/*     */   private ArrayList<DTSweepConstraint> edges;
/*     */   
/*     */   public String toString() {
/*  38 */     return "[" + getX() + "," + getY() + "]";
/*     */   }
/*     */   
/*     */   public abstract double getX();
/*     */   
/*     */   public abstract double getY();
/*     */   
/*     */   public abstract double getZ();
/*     */   
/*     */   public abstract float getXf();
/*     */   
/*     */   public abstract float getYf();
/*     */   
/*     */   public abstract float getZf();
/*     */   
/*     */   public abstract void set(double paramDouble1, double paramDouble2, double paramDouble3);
/*     */   
/*     */   public ArrayList<DTSweepConstraint> getEdges() {
/*  53 */     return this.edges;
/*     */   }
/*     */   
/*     */   public void addEdge(DTSweepConstraint e) {
/*  58 */     if (this.edges == null)
/*  60 */       this.edges = new ArrayList<>(); 
/*  62 */     this.edges.add(e);
/*     */   }
/*     */   
/*     */   public boolean hasEdges() {
/*  67 */     return (this.edges != null);
/*     */   }
/*     */   
/*     */   public DTSweepConstraint getEdge(TriangulationPoint p) {
/*  76 */     for (DTSweepConstraint c : this.edges) {
/*  78 */       if (c.p == p)
/*  80 */         return c; 
/*     */     } 
/*  83 */     return null;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  88 */     if (obj instanceof TriangulationPoint) {
/*  90 */       TriangulationPoint p = (TriangulationPoint)obj;
/*  91 */       return (getX() == p.getX() && getY() == p.getY());
/*     */     } 
/*  93 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  98 */     long bits = Double.doubleToLongBits(getX());
/*  99 */     bits ^= Double.doubleToLongBits(getY()) * 31L;
/* 100 */     return (int)bits ^ (int)(bits >> 32L);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\TriangulationPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */