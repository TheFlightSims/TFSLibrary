/*    */ package javax.media.jai;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ final class ProductOperationGraph extends OperationGraph implements Serializable {
/*    */   ProductOperationGraph() {
/* 40 */     super(true);
/*    */   }
/*    */   
/*    */   void addProduct(String productName) {
/* 49 */     addOp(new PartialOrderNode(new OperationGraph(), productName));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ProductOperationGraph.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */