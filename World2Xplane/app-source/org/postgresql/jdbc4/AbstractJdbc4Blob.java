/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ import org.postgresql.jdbc3.AbstractJdbc3Blob;
/*    */ 
/*    */ public abstract class AbstractJdbc4Blob extends AbstractJdbc3Blob {
/*    */   public AbstractJdbc4Blob(BaseConnection conn, long oid) throws SQLException {
/* 20 */     super(conn, oid);
/*    */   }
/*    */   
/*    */   public synchronized InputStream getBinaryStream(long pos, long length) throws SQLException {
/* 25 */     checkFreed();
/* 26 */     throw Driver.notImplemented(getClass(), "getBinaryStream(long, long)");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\AbstractJdbc4Blob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */