/*    */ package ch.qos.logback.core.db.dialect;
/*    */ 
/*    */ public class H2Dialect implements SQLDialect {
/*    */   public static final String SELECT_CURRVAL = "CALL IDENTITY()";
/*    */   
/*    */   public String getSelectInsertId() {
/* 25 */     return "CALL IDENTITY()";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\db\dialect\H2Dialect.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */