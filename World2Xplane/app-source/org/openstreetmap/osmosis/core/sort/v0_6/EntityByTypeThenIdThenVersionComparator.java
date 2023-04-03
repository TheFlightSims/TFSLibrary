/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ 
/*    */ public class EntityByTypeThenIdThenVersionComparator implements Comparator<Entity> {
/*    */   private Comparator<Entity> comparator;
/*    */   
/*    */   public EntityByTypeThenIdThenVersionComparator() {
/* 28 */     List<Comparator<Entity>> entityComparators = new ArrayList<Comparator<Entity>>();
/* 29 */     entityComparators.add(new EntityByTypeComparator());
/* 30 */     entityComparators.add(new EntityByIdComparator());
/* 31 */     entityComparators.add(new EntityByVersionComparator());
/* 34 */     this.comparator = new StackableComparator<Entity>(entityComparators);
/*    */   }
/*    */   
/*    */   public int compare(Entity o1, Entity o2) {
/* 42 */     return this.comparator.compare(o1, o2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\EntityByTypeThenIdThenVersionComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */