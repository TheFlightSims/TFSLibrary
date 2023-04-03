/*     */ package org.apache.commons.math3.geometry.partitioning;
/*     */ 
/*     */ import org.apache.commons.math3.geometry.Space;
/*     */ 
/*     */ public interface SubHyperplane<S extends Space> {
/*     */   SubHyperplane<S> copySelf();
/*     */   
/*     */   Hyperplane<S> getHyperplane();
/*     */   
/*     */   boolean isEmpty();
/*     */   
/*     */   double getSize();
/*     */   
/*     */   Side side(Hyperplane<S> paramHyperplane);
/*     */   
/*     */   SplitSubHyperplane<S> split(Hyperplane<S> paramHyperplane);
/*     */   
/*     */   SubHyperplane<S> reunite(SubHyperplane<S> paramSubHyperplane);
/*     */   
/*     */   public static class SplitSubHyperplane<U extends Space> {
/*     */     private final SubHyperplane<U> plus;
/*     */     
/*     */     private final SubHyperplane<U> minus;
/*     */     
/*     */     public SplitSubHyperplane(SubHyperplane<U> plus, SubHyperplane<U> minus) {
/* 105 */       this.plus = plus;
/* 106 */       this.minus = minus;
/*     */     }
/*     */     
/*     */     public SubHyperplane<U> getPlus() {
/* 113 */       return this.plus;
/*     */     }
/*     */     
/*     */     public SubHyperplane<U> getMinus() {
/* 120 */       return this.minus;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\SubHyperplane.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */