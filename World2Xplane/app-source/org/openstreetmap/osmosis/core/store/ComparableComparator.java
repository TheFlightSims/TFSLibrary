/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class ComparableComparator<T extends Comparable<T>> implements Comparator<T> {
/*    */   public int compare(T o1, T o2) {
/* 22 */     return o1.compareTo(o2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\ComparableComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */