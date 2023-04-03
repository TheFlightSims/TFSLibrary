/*    */ package ch.qos.logback.core.db.dialect;
/*    */ 
/*    */ public class OracleDialect implements SQLDialect {
/*    */   public static final String SELECT_CURRVAL = "SELECT logging_event_id_seq.currval from dual";
/*    */   
/*    */   public String getSelectInsertId() {
/* 26 */     return "SELECT logging_event_id_seq.currval from dual";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\db\dialect\OracleDialect.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */