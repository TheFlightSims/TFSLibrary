/*    */ package org.apache.commons.math3.geometry.partitioning;
/*    */ 
/*    */ import org.apache.commons.math3.geometry.Vector;
/*    */ 
/*    */ public interface Region<S extends org.apache.commons.math3.geometry.Space> {
/*    */   Region<S> buildNew(BSPTree<S> paramBSPTree);
/*    */   
/*    */   Region<S> copySelf();
/*    */   
/*    */   boolean isEmpty();
/*    */   
/*    */   boolean isEmpty(BSPTree<S> paramBSPTree);
/*    */   
/*    */   boolean contains(Region<S> paramRegion);
/*    */   
/*    */   Location checkPoint(Vector<S> paramVector);
/*    */   
/*    */   BSPTree<S> getTree(boolean paramBoolean);
/*    */   
/*    */   double getBoundarySize();
/*    */   
/*    */   double getSize();
/*    */   
/*    */   Vector<S> getBarycenter();
/*    */   
/*    */   Side side(Hyperplane<S> paramHyperplane);
/*    */   
/*    */   SubHyperplane<S> intersection(SubHyperplane<S> paramSubHyperplane);
/*    */   
/*    */   public enum Location {
/* 52 */     INSIDE, OUTSIDE, BOUNDARY;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\Region.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */