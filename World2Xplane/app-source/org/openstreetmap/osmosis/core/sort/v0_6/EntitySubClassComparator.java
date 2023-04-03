/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ 
/*    */ public class EntitySubClassComparator<T extends Entity> implements Comparator<T> {
/*    */   private Comparator<Entity> comparator;
/*    */   
/*    */   public EntitySubClassComparator(Comparator<Entity> comparator) {
/* 29 */     this.comparator = comparator;
/*    */   }
/*    */   
/*    */   public int compare(T o1, T o2) {
/* 38 */     return this.comparator.compare((Entity)o1, (Entity)o2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\EntitySubClassComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */