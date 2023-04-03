/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.RelationMember;
/*    */ 
/*    */ public class RelationMemberCollectionLoader implements FeatureCollectionLoader<Relation, RelationMember> {
/*    */   public Collection<RelationMember> getFeatureCollection(Relation entity) {
/* 20 */     return entity.getMembers();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\RelationMemberCollectionLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */