/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Way;
/*    */ 
/*    */ public class WayContainerFactory implements EntityContainerFactory<Way> {
/*    */   public EntityContainer createContainer(Way entity) {
/* 17 */     return new WayContainer(entity);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\WayContainerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */