/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Node;
/*    */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*    */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*    */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*    */ 
/*    */ public class NodeContainer extends EntityContainer {
/*    */   private Node node;
/*    */   
/*    */   public NodeContainer(Node node) {
/* 27 */     this.node = node;
/*    */   }
/*    */   
/*    */   public NodeContainer(StoreReader sr, StoreClassRegister scr) {
/* 41 */     this.node = new Node(sr, scr);
/*    */   }
/*    */   
/*    */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 49 */     this.node.store(sw, scr);
/*    */   }
/*    */   
/*    */   public void process(EntityProcessor processor) {
/* 58 */     processor.process(this);
/*    */   }
/*    */   
/*    */   public Node getEntity() {
/* 67 */     return this.node;
/*    */   }
/*    */   
/*    */   public NodeContainer getWriteableInstance() {
/* 76 */     if (this.node.isReadOnly())
/* 77 */       return new NodeContainer(this.node.getWriteableInstance()); 
/* 79 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\NodeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */