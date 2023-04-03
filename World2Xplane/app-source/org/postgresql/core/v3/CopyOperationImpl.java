/*    */ package org.postgresql.core.v3;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.copy.CopyOperation;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class CopyOperationImpl implements CopyOperation {
/*    */   QueryExecutorImpl queryExecutor;
/*    */   
/*    */   int rowFormat;
/*    */   
/*    */   int[] fieldFormats;
/*    */   
/* 23 */   long handledRowCount = -1L;
/*    */   
/*    */   void init(QueryExecutorImpl q, int fmt, int[] fmts) {
/* 26 */     this.queryExecutor = q;
/* 27 */     this.rowFormat = fmt;
/* 28 */     this.fieldFormats = fmts;
/*    */   }
/*    */   
/*    */   public void cancelCopy() throws SQLException {
/* 32 */     this.queryExecutor.cancelCopy(this);
/*    */   }
/*    */   
/*    */   public int getFieldCount() {
/* 36 */     return this.fieldFormats.length;
/*    */   }
/*    */   
/*    */   public int getFieldFormat(int field) {
/* 40 */     return this.fieldFormats[field];
/*    */   }
/*    */   
/*    */   public int getFormat() {
/* 44 */     return this.rowFormat;
/*    */   }
/*    */   
/*    */   public boolean isActive() {
/* 48 */     synchronized (this.queryExecutor) {
/* 49 */       return this.queryExecutor.hasLock(this);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void handleCommandStatus(String status) throws PSQLException {
/* 54 */     if (status.startsWith("COPY")) {
/* 55 */       int i = status.lastIndexOf(' ');
/* 56 */       this.handledRowCount = (i > 3) ? Long.parseLong(status.substring(i + 1)) : -1L;
/*    */     } else {
/* 58 */       throw new PSQLException(GT.tr("CommandComplete expected COPY but got: " + status), PSQLState.COMMUNICATION_ERROR);
/*    */     } 
/*    */   }
/*    */   
/*    */   public long getHandledRowCount() {
/* 63 */     return this.handledRowCount;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\CopyOperationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */