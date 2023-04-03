/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ 
/*    */ public class EntityByIdComparator implements Comparator<Entity> {
/*    */   public int compare(Entity o1, Entity o2) {
/* 23 */     long idDiff = o1.getId() - o2.getId();
/* 24 */     if (idDiff > 0L)
/* 25 */       return 1; 
/* 26 */     if (idDiff < 0L)
/* 27 */       return -1; 
/* 29 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\EntityByIdComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */