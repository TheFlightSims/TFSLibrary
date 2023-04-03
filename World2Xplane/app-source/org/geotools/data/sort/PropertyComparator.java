/*    */ package org.geotools.data.sort;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ 
/*    */ class PropertyComparator implements Comparator<SimpleFeature> {
/*    */   String propertyName;
/*    */   
/*    */   boolean ascending;
/*    */   
/*    */   public PropertyComparator(String propertyName, boolean ascending) {
/* 41 */     this.propertyName = propertyName;
/* 42 */     this.ascending = ascending;
/*    */   }
/*    */   
/*    */   public int compare(SimpleFeature f1, SimpleFeature f2) {
/* 46 */     int result = compareAscending(f1, f2);
/* 47 */     if (this.ascending)
/* 48 */       return result; 
/* 50 */     return result * -1;
/*    */   }
/*    */   
/*    */   private int compareAscending(SimpleFeature f1, SimpleFeature f2) {
/* 55 */     Comparable<Comparable> o1 = (Comparable)f1.getAttribute(this.propertyName);
/* 56 */     Comparable o2 = (Comparable)f2.getAttribute(this.propertyName);
/* 58 */     if (o1 == null) {
/* 59 */       if (o2 == null)
/* 60 */         return 0; 
/* 62 */       return -1;
/*    */     } 
/* 64 */     if (o2 == null)
/* 65 */       return 1; 
/* 67 */     return o1.compareTo(o2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\sort\PropertyComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */