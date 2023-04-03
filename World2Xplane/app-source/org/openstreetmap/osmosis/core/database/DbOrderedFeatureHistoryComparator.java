/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ 
/*    */ public class DbOrderedFeatureHistoryComparator<T extends Storeable> implements Comparator<DbFeatureHistory<DbOrderedFeature<T>>> {
/* 17 */   private DbFeatureComparator<T> featureComparator = new DbFeatureComparator<T>();
/*    */   
/*    */   public int compare(DbFeatureHistory<DbOrderedFeature<T>> o1, DbFeatureHistory<DbOrderedFeature<T>> o2) {
/* 29 */     int parentComparison = this.featureComparator.compare(o1.getFeature(), o2.getFeature());
/* 30 */     if (parentComparison != 0)
/* 31 */       return parentComparison; 
/* 34 */     int versionDelta = o1.getVersion() - o2.getVersion();
/* 35 */     if (versionDelta != 0)
/* 36 */       return versionDelta; 
/* 39 */     return ((DbOrderedFeature)o1.getFeature()).getSequenceId() - ((DbOrderedFeature)o2.getFeature()).getSequenceId();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DbOrderedFeatureHistoryComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */