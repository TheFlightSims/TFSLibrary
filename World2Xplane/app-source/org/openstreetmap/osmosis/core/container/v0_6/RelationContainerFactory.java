/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
/*    */ 
/*    */ public class RelationContainerFactory implements EntityContainerFactory<Relation> {
/*    */   public EntityContainer createContainer(Relation entity) {
/* 17 */     return new RelationContainer(entity);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\RelationContainerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */