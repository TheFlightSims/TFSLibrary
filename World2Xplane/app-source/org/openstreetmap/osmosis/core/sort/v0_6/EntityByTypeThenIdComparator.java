/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ 
/*    */ public class EntityByTypeThenIdComparator implements Comparator<Entity> {
/*    */   private Comparator<Entity> comparator;
/*    */   
/*    */   public EntityByTypeThenIdComparator() {
/* 27 */     List<Comparator<Entity>> entityComparators = new ArrayList<Comparator<Entity>>();
/* 28 */     entityComparators.add(new EntityByTypeComparator());
/* 29 */     entityComparators.add(new EntityByIdComparator());
/* 32 */     this.comparator = new StackableComparator<Entity>(entityComparators);
/*    */   }
/*    */   
/*    */   public int compare(Entity o1, Entity o2) {
/* 40 */     return this.comparator.compare(o1, o2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\EntityByTypeThenIdComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */