/*    */ package org.apache.commons.math3.geometry.partitioning;
/*    */ 
/*    */ import org.apache.commons.math3.geometry.Space;
/*    */ 
/*    */ class BoundarySizeVisitor<S extends Space> implements BSPTreeVisitor<S> {
/* 34 */   private double boundarySize = 0.0D;
/*    */   
/*    */   public BSPTreeVisitor.Order visitOrder(BSPTree<S> node) {
/* 39 */     return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
/*    */   }
/*    */   
/*    */   public void visitInternalNode(BSPTree<S> node) {
/* 45 */     BoundaryAttribute<S> attribute = (BoundaryAttribute<S>)node.getAttribute();
/* 47 */     if (attribute.getPlusOutside() != null)
/* 48 */       this.boundarySize += attribute.getPlusOutside().getSize(); 
/* 50 */     if (attribute.getPlusInside() != null)
/* 51 */       this.boundarySize += attribute.getPlusInside().getSize(); 
/*    */   }
/*    */   
/*    */   public void visitLeafNode(BSPTree<S> node) {}
/*    */   
/*    */   public double getSize() {
/* 63 */     return this.boundarySize;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\BoundarySizeVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */