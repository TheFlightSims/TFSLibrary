/*    */ package org.apache.commons.math3.geometry.partitioning;
/*    */ 
/*    */ public interface BSPTreeVisitor<S extends org.apache.commons.math3.geometry.Space> {
/*    */   Order visitOrder(BSPTree<S> paramBSPTree);
/*    */   
/*    */   void visitInternalNode(BSPTree<S> paramBSPTree);
/*    */   
/*    */   void visitLeafNode(BSPTree<S> paramBSPTree);
/*    */   
/*    */   public enum Order {
/* 58 */     PLUS_MINUS_SUB, PLUS_SUB_MINUS, MINUS_PLUS_SUB, MINUS_SUB_PLUS, SUB_PLUS_MINUS, SUB_MINUS_PLUS;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\BSPTreeVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */