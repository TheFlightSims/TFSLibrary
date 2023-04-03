/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ 
/*    */ public class DbOrderedFeatureRowMapper<T extends Storeable> implements RowMapperListener<DbFeature<T>> {
/*    */   private RowMapperListener<DbOrderedFeature<T>> listener;
/*    */   
/*    */   public DbOrderedFeatureRowMapper(RowMapperListener<DbOrderedFeature<T>> listener) {
/* 27 */     this.listener = listener;
/*    */   }
/*    */   
/*    */   public void process(DbFeature<T> data, ResultSet resultSet) throws SQLException {
/* 39 */     int sequence = resultSet.getInt("sequence_id");
/* 41 */     this.listener.process(new DbOrderedFeature<T>(data.getEntityId(), data.getFeature(), sequence), resultSet);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DbOrderedFeatureRowMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */