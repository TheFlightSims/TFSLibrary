/*     */ package org.apache.commons.math3.geometry.euclidean.oned;
/*     */ 
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.geometry.partitioning.Hyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.Region;
/*     */ import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
/*     */ 
/*     */ public class OrientedPoint implements Hyperplane<Euclidean1D> {
/*     */   private Vector1D location;
/*     */   
/*     */   private boolean direct;
/*     */   
/*     */   public OrientedPoint(Vector1D location, boolean direct) {
/*  43 */     this.location = location;
/*  44 */     this.direct = direct;
/*     */   }
/*     */   
/*     */   public OrientedPoint copySelf() {
/*  53 */     return this;
/*     */   }
/*     */   
/*     */   public double getOffset(Vector<Euclidean1D> point) {
/*  58 */     double delta = ((Vector1D)point).getX() - this.location.getX();
/*  59 */     return this.direct ? delta : -delta;
/*     */   }
/*     */   
/*     */   public SubOrientedPoint wholeHyperplane() {
/*  75 */     return new SubOrientedPoint(this, null);
/*     */   }
/*     */   
/*     */   public IntervalsSet wholeSpace() {
/*  83 */     return new IntervalsSet();
/*     */   }
/*     */   
/*     */   public boolean sameOrientationAs(Hyperplane<Euclidean1D> other) {
/*  88 */     return ((this.direct ^ ((OrientedPoint)other).direct) == 0);
/*     */   }
/*     */   
/*     */   public Vector1D getLocation() {
/*  95 */     return this.location;
/*     */   }
/*     */   
/*     */   public boolean isDirect() {
/* 103 */     return this.direct;
/*     */   }
/*     */   
/*     */   public void revertSelf() {
/* 109 */     this.direct = !this.direct;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\oned\OrientedPoint.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */