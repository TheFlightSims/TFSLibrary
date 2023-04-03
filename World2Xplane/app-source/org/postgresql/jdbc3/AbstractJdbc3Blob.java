/*    */ package org.postgresql.jdbc3;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ import org.postgresql.jdbc2.AbstractJdbc2Blob;
/*    */ 
/*    */ public abstract class AbstractJdbc3Blob extends AbstractJdbc2Blob {
/*    */   public AbstractJdbc3Blob(BaseConnection conn, long oid) throws SQLException {
/* 19 */     super(conn, oid);
/*    */   }
/*    */   
/*    */   public synchronized int setBytes(long pos, byte[] bytes) throws SQLException {
/* 39 */     return setBytes(pos, bytes, 0, bytes.length);
/*    */   }
/*    */   
/*    */   public synchronized int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException {
/* 67 */     assertPosition(pos);
/* 68 */     this.lo.seek((int)(pos - 1L));
/* 69 */     this.lo.write(bytes, offset, len);
/* 70 */     return len;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc3\AbstractJdbc3Blob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */