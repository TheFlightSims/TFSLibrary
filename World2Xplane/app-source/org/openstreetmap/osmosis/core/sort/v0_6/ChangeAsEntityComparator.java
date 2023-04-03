/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.ChangeContainer;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*    */ 
/*    */ public class ChangeAsEntityComparator implements Comparator<ChangeContainer> {
/*    */   private Comparator<EntityContainer> entityComparator;
/*    */   
/*    */   public ChangeAsEntityComparator(Comparator<EntityContainer> entityComparator) {
/* 26 */     this.entityComparator = entityComparator;
/*    */   }
/*    */   
/*    */   public int compare(ChangeContainer o1, ChangeContainer o2) {
/* 35 */     return this.entityComparator.compare(o1.getEntityContainer(), o2.getEntityContainer());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\ChangeAsEntityComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */