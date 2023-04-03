/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ 
/*    */ public class DbFeatureComparator<T extends Storeable> implements Comparator<DbFeature<T>> {
/*    */   public int compare(DbFeature<T> o1, DbFeature<T> o2) {
/* 24 */     long idDelta = o1.getEntityId() - o2.getEntityId();
/* 26 */     if (idDelta < 0L)
/* 27 */       return -1; 
/* 28 */     if (idDelta > 0L)
/* 29 */       return 1; 
/* 32 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DbFeatureComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */