/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import org.openstreetmap.osmosis.core.sort.common.FileBasedSort;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ 
/*    */ public class SortingStoreRowMapperListener<T extends Storeable> implements RowMapperListener<T> {
/*    */   private FileBasedSort<T> sortingStore;
/*    */   
/*    */   public SortingStoreRowMapperListener(FileBasedSort<T> sortingStore) {
/* 29 */     this.sortingStore = sortingStore;
/*    */   }
/*    */   
/*    */   public void process(T data, ResultSet resultSet) throws SQLException {
/* 38 */     this.sortingStore.add((Storeable)data);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\SortingStoreRowMapperListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */