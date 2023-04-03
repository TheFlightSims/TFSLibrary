/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ 
/*    */ public class DbFeatureHistoryRowMapper<T extends Storeable> implements RowMapperListener<T> {
/*    */   private RowMapperListener<DbFeatureHistory<T>> listener;
/*    */   
/*    */   public DbFeatureHistoryRowMapper(RowMapperListener<DbFeatureHistory<T>> listener) {
/* 27 */     this.listener = listener;
/*    */   }
/*    */   
/*    */   public void process(T data, ResultSet resultSet) throws SQLException {
/* 39 */     int version = resultSet.getInt("version");
/* 41 */     this.listener.process(new DbFeatureHistory<T>(data, version), resultSet);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DbFeatureHistoryRowMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */