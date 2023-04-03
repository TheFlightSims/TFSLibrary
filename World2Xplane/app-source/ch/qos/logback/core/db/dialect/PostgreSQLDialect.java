/*    */ package ch.qos.logback.core.db.dialect;
/*    */ 
/*    */ public class PostgreSQLDialect implements SQLDialect {
/*    */   public static final String SELECT_CURRVAL = "SELECT currval('logging_event_id_seq')";
/*    */   
/*    */   public String getSelectInsertId() {
/* 27 */     return "SELECT currval('logging_event_id_seq')";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\db\dialect\PostgreSQLDialect.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */