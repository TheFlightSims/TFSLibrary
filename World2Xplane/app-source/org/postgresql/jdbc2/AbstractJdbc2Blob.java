/*    */ package org.postgresql.jdbc2;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ 
/*    */ public abstract class AbstractJdbc2Blob extends AbstractJdbc2BlobClob {
/*    */   public AbstractJdbc2Blob(BaseConnection conn, long oid) throws SQLException {
/* 21 */     super(conn, oid);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\AbstractJdbc2Blob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */