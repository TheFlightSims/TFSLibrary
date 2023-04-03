/*    */ package org.postgresql.util;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ public class PSQLException extends SQLException {
/*    */   private ServerErrorMessage _serverError;
/*    */   
/*    */   public PSQLException(String msg, PSQLState state, Throwable cause) {
/* 21 */     super(msg, (state == null) ? null : state.getState());
/* 22 */     initCause(cause);
/*    */   }
/*    */   
/*    */   public PSQLException(String msg, PSQLState state) {
/* 27 */     this(msg, state, (Throwable)null);
/*    */   }
/*    */   
/*    */   public PSQLException(ServerErrorMessage serverError) {
/* 32 */     this(serverError.toString(), new PSQLState(serverError.getSQLState()));
/* 33 */     this._serverError = serverError;
/*    */   }
/*    */   
/*    */   public ServerErrorMessage getServerErrorMessage() {
/* 38 */     return this._serverError;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\PSQLException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */