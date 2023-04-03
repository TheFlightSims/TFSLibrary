/*    */ package org.postgresql.core.v3;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.copy.CopyIn;
/*    */ 
/*    */ public class CopyInImpl extends CopyOperationImpl implements CopyIn {
/*    */   public void writeToCopy(byte[] data, int off, int siz) throws SQLException {
/* 53 */     this.queryExecutor.writeToCopy(this, data, off, siz);
/*    */   }
/*    */   
/*    */   public void flushCopy() throws SQLException {
/* 57 */     this.queryExecutor.flushCopy(this);
/*    */   }
/*    */   
/*    */   public long endCopy() throws SQLException {
/* 61 */     return this.queryExecutor.endCopy(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\CopyInImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */