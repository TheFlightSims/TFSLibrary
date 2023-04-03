/*    */ package org.geotools.data.sort;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ 
/*    */ class FidComparator implements Comparator<SimpleFeature> {
/*    */   boolean ascending;
/*    */   
/*    */   public FidComparator(boolean ascending) {
/* 38 */     this.ascending = ascending;
/*    */   }
/*    */   
/*    */   public int compare(SimpleFeature f1, SimpleFeature f2) {
/* 42 */     int result = compareAscending(f1, f2);
/* 43 */     if (this.ascending)
/* 44 */       return result; 
/* 46 */     return result * -1;
/*    */   }
/*    */   
/*    */   private int compareAscending(SimpleFeature f1, SimpleFeature f2) {
/* 51 */     String id1 = f1.getID();
/* 52 */     String id2 = f2.getID();
/* 54 */     if (id1 == null) {
/* 55 */       if (id2 == null)
/* 56 */         return 0; 
/* 58 */       return -1;
/*    */     } 
/* 60 */     if (id2 == null)
/* 61 */       return 1; 
/* 63 */     return id1.compareTo(id2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\sort\FidComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */