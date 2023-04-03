/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.IntersectionMatrix;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ public abstract class GraphComponent {
/*     */   protected Label label;
/*     */   
/*     */   private boolean isInResult = false;
/*     */   
/*     */   private boolean isCovered = false;
/*     */   
/*     */   private boolean isCoveredSet = false;
/*     */   
/*     */   private boolean isVisited = false;
/*     */   
/*     */   public GraphComponent() {}
/*     */   
/*     */   public GraphComponent(Label label) {
/*  65 */     this.label = label;
/*     */   }
/*     */   
/*     */   public Label getLabel() {
/*  68 */     return this.label;
/*     */   }
/*     */   
/*     */   public void setLabel(Label label) {
/*  69 */     this.label = label;
/*     */   }
/*     */   
/*     */   public void setInResult(boolean isInResult) {
/*  70 */     this.isInResult = isInResult;
/*     */   }
/*     */   
/*     */   public boolean isInResult() {
/*  71 */     return this.isInResult;
/*     */   }
/*     */   
/*     */   public void setCovered(boolean isCovered) {
/*  74 */     this.isCovered = isCovered;
/*  75 */     this.isCoveredSet = true;
/*     */   }
/*     */   
/*     */   public boolean isCovered() {
/*  77 */     return this.isCovered;
/*     */   }
/*     */   
/*     */   public boolean isCoveredSet() {
/*  78 */     return this.isCoveredSet;
/*     */   }
/*     */   
/*     */   public boolean isVisited() {
/*  79 */     return this.isVisited;
/*     */   }
/*     */   
/*     */   public void setVisited(boolean isVisited) {
/*  80 */     this.isVisited = isVisited;
/*     */   }
/*     */   
/*     */   public abstract Coordinate getCoordinate();
/*     */   
/*     */   protected abstract void computeIM(IntersectionMatrix paramIntersectionMatrix);
/*     */   
/*     */   public abstract boolean isIsolated();
/*     */   
/*     */   public void updateIM(IntersectionMatrix im) {
/* 103 */     Assert.isTrue((this.label.getGeometryCount() >= 2), "found partial label");
/* 104 */     computeIM(im);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\GraphComponent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */