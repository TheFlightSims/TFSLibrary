/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ import org.postgresql.jdbc3.AbstractJdbc3Clob;
/*    */ 
/*    */ public abstract class AbstractJdbc4Clob extends AbstractJdbc3Clob {
/*    */   public AbstractJdbc4Clob(BaseConnection conn, long oid) throws SQLException {
/* 20 */     super(conn, oid);
/*    */   }
/*    */   
/*    */   public synchronized Reader getCharacterStream(long pos, long length) throws SQLException {
/* 25 */     checkFreed();
/* 26 */     throw Driver.notImplemented(getClass(), "getCharacterStream(long, long)");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\AbstractJdbc4Clob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */