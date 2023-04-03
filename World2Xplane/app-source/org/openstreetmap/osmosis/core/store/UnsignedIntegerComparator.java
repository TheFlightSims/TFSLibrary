/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class UnsignedIntegerComparator implements Comparator<Integer> {
/*    */   public int compare(Integer o1, Integer o2) {
/* 25 */     long value1 = o1.intValue() & 0xFFFFFFFFL;
/* 26 */     long value2 = o2.intValue() & 0xFFFFFFFFL;
/* 28 */     long comparison = value1 - value2;
/* 30 */     if (comparison == 0L)
/* 31 */       return 0; 
/* 32 */     if (comparison > 0L)
/* 33 */       return 1; 
/* 35 */     return -1;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\UnsignedIntegerComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */