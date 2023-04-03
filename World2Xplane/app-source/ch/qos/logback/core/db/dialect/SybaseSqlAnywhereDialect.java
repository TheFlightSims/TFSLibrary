/*    */ package ch.qos.logback.core.db.dialect;
/*    */ 
/*    */ public class SybaseSqlAnywhereDialect implements SQLDialect {
/*    */   public static final String SELECT_CURRVAL = "SELECT @@identity id";
/*    */   
/*    */   public String getSelectInsertId() {
/* 30 */     return "SELECT @@identity id";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\db\dialect\SybaseSqlAnywhereDialect.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */