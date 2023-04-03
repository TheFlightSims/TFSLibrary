/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Node;
/*    */ 
/*    */ public class NodeContainerFactory implements EntityContainerFactory<Node> {
/*    */   public EntityContainer createContainer(Node entity) {
/* 17 */     return new NodeContainer(entity);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\NodeContainerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */