/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ 
/*    */ public class EntityByTypeComparator implements Comparator<Entity> {
/*    */   public int compare(Entity o1, Entity o2) {
/* 21 */     return o1.getType().compareTo((Enum)o2.getType());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\EntityByTypeComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */