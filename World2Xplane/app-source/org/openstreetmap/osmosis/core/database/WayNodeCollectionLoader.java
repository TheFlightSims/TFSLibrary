/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Way;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.WayNode;
/*    */ 
/*    */ public class WayNodeCollectionLoader implements FeatureCollectionLoader<Way, WayNode> {
/*    */   public Collection<WayNode> getFeatureCollection(Way entity) {
/* 20 */     return entity.getWayNodes();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\WayNodeCollectionLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */