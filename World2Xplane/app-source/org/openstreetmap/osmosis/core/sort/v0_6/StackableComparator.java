/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class StackableComparator<T> implements Comparator<T> {
/*    */   private List<Comparator<T>> comparators;
/*    */   
/*    */   public StackableComparator(List<Comparator<T>> comparators) {
/* 29 */     this.comparators = new ArrayList<Comparator<T>>(comparators);
/*    */   }
/*    */   
/*    */   public int compare(T o1, T o2) {
/* 40 */     for (Comparator<T> comparator : this.comparators) {
/* 43 */       int result = comparator.compare(o1, o2);
/* 45 */       if (result != 0)
/* 46 */         return result; 
/*    */     } 
/* 50 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\StackableComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */