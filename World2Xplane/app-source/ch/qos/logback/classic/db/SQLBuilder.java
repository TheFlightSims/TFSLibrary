/*    */ package ch.qos.logback.classic.db;
/*    */ 
/*    */ import ch.qos.logback.classic.db.names.ColumnName;
/*    */ import ch.qos.logback.classic.db.names.DBNameResolver;
/*    */ import ch.qos.logback.classic.db.names.TableName;
/*    */ 
/*    */ public class SQLBuilder {
/*    */   static String buildInsertPropertiesSQL(DBNameResolver dbNameResolver) {
/* 25 */     StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
/* 26 */     sqlBuilder.append(dbNameResolver.getTableName((Enum)TableName.LOGGING_EVENT_PROPERTY)).append(" (");
/* 27 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.EVENT_ID)).append(", ");
/* 28 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.MAPPED_KEY)).append(", ");
/* 29 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.MAPPED_VALUE)).append(") ");
/* 30 */     sqlBuilder.append("VALUES (?, ?, ?)");
/* 31 */     return sqlBuilder.toString();
/*    */   }
/*    */   
/*    */   static String buildInsertExceptionSQL(DBNameResolver dbNameResolver) {
/* 35 */     StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
/* 36 */     sqlBuilder.append(dbNameResolver.getTableName((Enum)TableName.LOGGING_EVENT_EXCEPTION)).append(" (");
/* 37 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.EVENT_ID)).append(", ");
/* 38 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.I)).append(", ");
/* 39 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.TRACE_LINE)).append(") ");
/* 40 */     sqlBuilder.append("VALUES (?, ?, ?)");
/* 41 */     return sqlBuilder.toString();
/*    */   }
/*    */   
/*    */   static String buildInsertSQL(DBNameResolver dbNameResolver) {
/* 45 */     StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
/* 46 */     sqlBuilder.append(dbNameResolver.getTableName((Enum)TableName.LOGGING_EVENT)).append(" (");
/* 47 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.TIMESTMP)).append(", ");
/* 48 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.FORMATTED_MESSAGE)).append(", ");
/* 49 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.LOGGER_NAME)).append(", ");
/* 50 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.LEVEL_STRING)).append(", ");
/* 51 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.THREAD_NAME)).append(", ");
/* 52 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.REFERENCE_FLAG)).append(", ");
/* 53 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.ARG0)).append(", ");
/* 54 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.ARG1)).append(", ");
/* 55 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.ARG2)).append(", ");
/* 56 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.ARG3)).append(", ");
/* 57 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.CALLER_FILENAME)).append(", ");
/* 58 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.CALLER_CLASS)).append(", ");
/* 59 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.CALLER_METHOD)).append(", ");
/* 60 */     sqlBuilder.append(dbNameResolver.getColumnName((Enum)ColumnName.CALLER_LINE)).append(") ");
/* 61 */     sqlBuilder.append("VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
/* 62 */     return sqlBuilder.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\db\SQLBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */