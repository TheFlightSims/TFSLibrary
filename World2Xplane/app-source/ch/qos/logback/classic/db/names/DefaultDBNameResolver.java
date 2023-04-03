/*    */ package ch.qos.logback.classic.db.names;
/*    */ 
/*    */ public class DefaultDBNameResolver implements DBNameResolver {
/*    */   public <N extends Enum<?>> String getTableName(N tableName) {
/* 27 */     return tableName.toString().toLowerCase();
/*    */   }
/*    */   
/*    */   public <N extends Enum<?>> String getColumnName(N columnName) {
/* 31 */     return columnName.toString().toLowerCase();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\db\names\DefaultDBNameResolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */