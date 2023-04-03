/*    */ package ch.qos.logback.core.db.dialect;
/*    */ 
/*    */ public class SQLiteDialect implements SQLDialect {
/*    */   public static final String SELECT_CURRVAL = "SELECT last_insert_rowid();";
/*    */   
/*    */   public String getSelectInsertId() {
/* 28 */     return "SELECT last_insert_rowid();";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\db\dialect\SQLiteDialect.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */