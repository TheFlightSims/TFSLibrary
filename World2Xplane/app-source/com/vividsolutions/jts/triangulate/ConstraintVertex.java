/*     */ package com.vividsolutions.jts.triangulate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.Vertex;
/*     */ 
/*     */ public class ConstraintVertex extends Vertex {
/*     */   private boolean isOnConstraint;
/*     */   
/*  48 */   private Object constraint = null;
/*     */   
/*     */   public ConstraintVertex(Coordinate p) {
/*  56 */     super(p);
/*     */   }
/*     */   
/*     */   public void setOnConstraint(boolean isOnConstraint) {
/*  65 */     this.isOnConstraint = isOnConstraint;
/*     */   }
/*     */   
/*     */   public boolean isOnConstraint() {
/*  74 */     return this.isOnConstraint;
/*     */   }
/*     */   
/*     */   public void setConstraint(Object constraint) {
/*  83 */     this.isOnConstraint = true;
/*  84 */     this.constraint = constraint;
/*     */   }
/*     */   
/*     */   public Object getConstraint() {
/*  93 */     return this.constraint;
/*     */   }
/*     */   
/*     */   protected void merge(ConstraintVertex other) {
/* 104 */     if (other.isOnConstraint) {
/* 105 */       this.isOnConstraint = true;
/* 106 */       this.constraint = other.constraint;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\ConstraintVertex.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */