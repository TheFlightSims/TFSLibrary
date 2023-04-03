/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ 
/*    */ public class DbFeatureHistoryComparator<T extends Storeable> implements Comparator<DbFeatureHistory<DbFeature<T>>> {
/* 17 */   private DbFeatureComparator<T> featureComparator = new DbFeatureComparator<T>();
/*    */   
/*    */   public int compare(DbFeatureHistory<DbFeature<T>> o1, DbFeatureHistory<DbFeature<T>> o2) {
/* 26 */     int parentComparison = this.featureComparator.compare(o1.getFeature(), o2.getFeature());
/* 27 */     if (parentComparison != 0)
/* 28 */       return parentComparison; 
/* 31 */     return o1.getVersion() - o2.getVersion();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DbFeatureHistoryComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */