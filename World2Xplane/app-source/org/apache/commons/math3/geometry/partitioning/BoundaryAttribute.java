/*    */ package org.apache.commons.math3.geometry.partitioning;
/*    */ 
/*    */ import org.apache.commons.math3.geometry.Space;
/*    */ 
/*    */ public class BoundaryAttribute<S extends Space> {
/*    */   private final SubHyperplane<S> plusOutside;
/*    */   
/*    */   private final SubHyperplane<S> plusInside;
/*    */   
/*    */   public BoundaryAttribute(SubHyperplane<S> plusOutside, SubHyperplane<S> plusInside) {
/* 58 */     this.plusOutside = plusOutside;
/* 59 */     this.plusInside = plusInside;
/*    */   }
/*    */   
/*    */   public SubHyperplane<S> getPlusOutside() {
/* 70 */     return this.plusOutside;
/*    */   }
/*    */   
/*    */   public SubHyperplane<S> getPlusInside() {
/* 81 */     return this.plusInside;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\BoundaryAttribute.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */