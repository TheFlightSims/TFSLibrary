/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.ChangeContainer;
/*    */ 
/*    */ public class ChangeForStreamableApplierComparator implements Comparator<ChangeContainer> {
/* 31 */   private Comparator<ChangeContainer> comparator = new ChangeAsEntityComparator(new EntityContainerComparator(new EntityByTypeThenIdThenVersionComparator()));
/*    */   
/*    */   public int compare(ChangeContainer o1, ChangeContainer o2) {
/* 40 */     return this.comparator.compare(o1, o2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\ChangeForStreamableApplierComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */