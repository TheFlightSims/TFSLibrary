/*    */ package org.apache.commons.math3.geometry.euclidean.oned;
/*    */ 
/*    */ import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
/*    */ import org.apache.commons.math3.geometry.partitioning.Hyperplane;
/*    */ import org.apache.commons.math3.geometry.partitioning.Region;
/*    */ import org.apache.commons.math3.geometry.partitioning.Side;
/*    */ import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
/*    */ 
/*    */ public class SubOrientedPoint extends AbstractSubHyperplane<Euclidean1D, Euclidean1D> {
/*    */   public SubOrientedPoint(Hyperplane<Euclidean1D> hyperplane, Region<Euclidean1D> remainingRegion) {
/* 39 */     super(hyperplane, remainingRegion);
/*    */   }
/*    */   
/*    */   public double getSize() {
/* 45 */     return 0.0D;
/*    */   }
/*    */   
/*    */   protected AbstractSubHyperplane<Euclidean1D, Euclidean1D> buildNew(Hyperplane<Euclidean1D> hyperplane, Region<Euclidean1D> remainingRegion) {
/* 52 */     return new SubOrientedPoint(hyperplane, remainingRegion);
/*    */   }
/*    */   
/*    */   public Side side(Hyperplane<Euclidean1D> hyperplane) {
/* 58 */     double global = hyperplane.getOffset(((OrientedPoint)getHyperplane()).getLocation());
/* 59 */     return (global < -1.0E-10D) ? Side.MINUS : ((global > 1.0E-10D) ? Side.PLUS : Side.HYPER);
/*    */   }
/*    */   
/*    */   public SubHyperplane.SplitSubHyperplane<Euclidean1D> split(Hyperplane<Euclidean1D> hyperplane) {
/* 65 */     double global = hyperplane.getOffset(((OrientedPoint)getHyperplane()).getLocation());
/* 66 */     return (global < -1.0E-10D) ? new SubHyperplane.SplitSubHyperplane(null, (SubHyperplane)this) : new SubHyperplane.SplitSubHyperplane((SubHyperplane)this, null);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\oned\SubOrientedPoint.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */