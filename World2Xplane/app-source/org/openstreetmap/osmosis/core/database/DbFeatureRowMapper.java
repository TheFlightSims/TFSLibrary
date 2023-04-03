/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ 
/*    */ public class DbFeatureRowMapper<T extends Storeable> implements RowMapperListener<T> {
/*    */   private RowMapperListener<DbFeature<T>> listener;
/*    */   
/*    */   public DbFeatureRowMapper(RowMapperListener<DbFeature<T>> listener) {
/* 27 */     this.listener = listener;
/*    */   }
/*    */   
/*    */   public void process(T data, ResultSet resultSet) throws SQLException {
/* 39 */     long id = resultSet.getLong("id");
/* 41 */     this.listener.process(new DbFeature<T>(id, data), resultSet);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DbFeatureRowMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */