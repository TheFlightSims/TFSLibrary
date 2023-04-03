/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Node;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ 
/*    */ public class NodeContainerIterator implements ReleasableIterator<NodeContainer> {
/*    */   private ReleasableIterator<Node> source;
/*    */   
/*    */   public NodeContainerIterator(ReleasableIterator<Node> source) {
/* 23 */     this.source = source;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 32 */     return this.source.hasNext();
/*    */   }
/*    */   
/*    */   public NodeContainer next() {
/* 40 */     return new NodeContainer((Node)this.source.next());
/*    */   }
/*    */   
/*    */   public void remove() {
/* 48 */     this.source.remove();
/*    */   }
/*    */   
/*    */   public void release() {
/* 57 */     this.source.release();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\NodeContainerIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */