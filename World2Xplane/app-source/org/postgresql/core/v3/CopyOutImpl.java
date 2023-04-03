/*    */ package org.postgresql.core.v3;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.copy.CopyOut;
/*    */ 
/*    */ public class CopyOutImpl extends CopyOperationImpl implements CopyOut {
/*    */   private byte[] currentDataRow;
/*    */   
/*    */   public byte[] readFromCopy() throws SQLException {
/* 46 */     this.currentDataRow = null;
/* 47 */     this.queryExecutor.readFromCopy(this);
/* 48 */     return this.currentDataRow;
/*    */   }
/*    */   
/*    */   void handleCopydata(byte[] data) {
/* 52 */     this.currentDataRow = data;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\CopyOutImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */