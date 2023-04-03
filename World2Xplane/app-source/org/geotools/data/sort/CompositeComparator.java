/*    */ package org.geotools.data.sort;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ 
/*    */ class CompositeComparator implements Comparator<SimpleFeature> {
/*    */   List<Comparator<SimpleFeature>> comparators;
/*    */   
/*    */   public CompositeComparator(List<Comparator<SimpleFeature>> comparators) {
/* 36 */     this.comparators = comparators;
/*    */   }
/*    */   
/*    */   public int compare(SimpleFeature f1, SimpleFeature f2) {
/* 40 */     for (Comparator<SimpleFeature> comp : this.comparators) {
/* 41 */       int result = comp.compare(f1, f2);
/* 42 */       if (result != 0)
/* 43 */         return result; 
/*    */     } 
/* 46 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\sort\CompositeComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */