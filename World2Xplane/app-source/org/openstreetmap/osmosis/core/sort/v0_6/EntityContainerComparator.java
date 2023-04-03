/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ 
/*    */ public class EntityContainerComparator implements Comparator<EntityContainer> {
/*    */   private Comparator<Entity> entityComparator;
/*    */   
/*    */   public EntityContainerComparator(Comparator<Entity> entityComparator) {
/* 28 */     this.entityComparator = entityComparator;
/*    */   }
/*    */   
/*    */   public int compare(EntityContainer o1, EntityContainer o2) {
/* 37 */     return this.entityComparator.compare(o1.getEntity(), o2.getEntity());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\EntityContainerComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */