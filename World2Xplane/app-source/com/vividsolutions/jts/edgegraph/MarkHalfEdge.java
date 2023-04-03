/*     */ package com.vividsolutions.jts.edgegraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class MarkHalfEdge extends HalfEdge {
/*     */   public static boolean isMarked(HalfEdge e) {
/*  23 */     return ((MarkHalfEdge)e).isMarked();
/*     */   }
/*     */   
/*     */   public static void mark(HalfEdge e) {
/*  33 */     ((MarkHalfEdge)e).mark();
/*     */   }
/*     */   
/*     */   public static void setMark(HalfEdge e, boolean isMarked) {
/*  44 */     ((MarkHalfEdge)e).setMark(isMarked);
/*     */   }
/*     */   
/*     */   public static void setMarkBoth(HalfEdge e, boolean isMarked) {
/*  55 */     ((MarkHalfEdge)e).setMark(isMarked);
/*  56 */     ((MarkHalfEdge)e.sym()).setMark(isMarked);
/*     */   }
/*     */   
/*     */   public static void markBoth(HalfEdge e) {
/*  65 */     ((MarkHalfEdge)e).mark();
/*  66 */     ((MarkHalfEdge)e.sym()).mark();
/*     */   }
/*     */   
/*     */   private boolean isMarked = false;
/*     */   
/*     */   public MarkHalfEdge(Coordinate orig) {
/*  77 */     super(orig);
/*     */   }
/*     */   
/*     */   public boolean isMarked() {
/*  87 */     return this.isMarked;
/*     */   }
/*     */   
/*     */   public void mark() {
/*  96 */     this.isMarked = true;
/*     */   }
/*     */   
/*     */   public void setMark(boolean isMarked) {
/* 106 */     this.isMarked = isMarked;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\edgegraph\MarkHalfEdge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */