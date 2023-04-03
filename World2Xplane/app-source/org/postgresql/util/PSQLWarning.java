/*    */ package org.postgresql.util;
/*    */ 
/*    */ import java.sql.SQLWarning;
/*    */ 
/*    */ public class PSQLWarning extends SQLWarning {
/*    */   private ServerErrorMessage serverError;
/*    */   
/*    */   public PSQLWarning(ServerErrorMessage err) {
/* 21 */     this.serverError = err;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 26 */     return this.serverError.toString();
/*    */   }
/*    */   
/*    */   public String getSQLState() {
/* 31 */     return this.serverError.getSQLState();
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 36 */     return this.serverError.getMessage();
/*    */   }
/*    */   
/*    */   public ServerErrorMessage getServerErrorMessage() {
/* 41 */     return this.serverError;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\PSQLWarning.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */