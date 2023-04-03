/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Bound;
/*    */ 
/*    */ public class BoundContainerFactory implements EntityContainerFactory<Bound> {
/*    */   public EntityContainer createContainer(Bound entity) {
/* 17 */     return new BoundContainer(entity);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\BoundContainerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */