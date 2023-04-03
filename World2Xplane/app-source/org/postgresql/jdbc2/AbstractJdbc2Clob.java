/*    */ package org.postgresql.jdbc2;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.Reader;
/*    */ import java.sql.Clob;
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ 
/*    */ public abstract class AbstractJdbc2Clob extends AbstractJdbc2BlobClob {
/*    */   public AbstractJdbc2Clob(BaseConnection conn, long oid) throws SQLException {
/* 25 */     super(conn, oid);
/*    */   }
/*    */   
/*    */   public synchronized InputStream getAsciiStream() throws SQLException {
/* 30 */     return getBinaryStream();
/*    */   }
/*    */   
/*    */   public synchronized Reader getCharacterStream() throws SQLException {
/* 35 */     return new InputStreamReader(getBinaryStream());
/*    */   }
/*    */   
/*    */   public synchronized String getSubString(long i, int j) throws SQLException {
/* 40 */     assertPosition(i, j);
/* 41 */     this.lo.seek((int)i - 1);
/* 42 */     return new String(this.lo.read(j));
/*    */   }
/*    */   
/*    */   public synchronized long position(String pattern, long start) throws SQLException {
/* 50 */     checkFreed();
/* 51 */     throw Driver.notImplemented(getClass(), "position(String,long)");
/*    */   }
/*    */   
/*    */   public synchronized long position(Clob pattern, long start) throws SQLException {
/* 59 */     checkFreed();
/* 60 */     throw Driver.notImplemented(getClass(), "position(Clob,start)");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\AbstractJdbc2Clob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */