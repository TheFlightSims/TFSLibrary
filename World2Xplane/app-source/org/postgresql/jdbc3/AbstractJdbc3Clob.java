/*     */ package org.postgresql.jdbc3;
/*     */ 
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.Driver;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.jdbc2.AbstractJdbc2Clob;
/*     */ 
/*     */ public abstract class AbstractJdbc3Clob extends AbstractJdbc2Clob {
/*     */   public AbstractJdbc3Clob(BaseConnection conn, long oid) throws SQLException {
/*  20 */     super(conn, oid);
/*     */   }
/*     */   
/*     */   public synchronized int setString(long pos, String str) throws SQLException {
/*  40 */     checkFreed();
/*  41 */     throw Driver.notImplemented(getClass(), "setString(long,str)");
/*     */   }
/*     */   
/*     */   public synchronized int setString(long pos, String str, int offset, int len) throws SQLException {
/*  64 */     checkFreed();
/*  65 */     throw Driver.notImplemented(getClass(), "setString(long,String,int,int)");
/*     */   }
/*     */   
/*     */   public synchronized OutputStream setAsciiStream(long pos) throws SQLException {
/*  84 */     checkFreed();
/*  85 */     throw Driver.notImplemented(getClass(), "setAsciiStream(long)");
/*     */   }
/*     */   
/*     */   public synchronized Writer setCharacterStream(long pos) throws SQLException {
/* 105 */     checkFreed();
/* 106 */     throw Driver.notImplemented(getClass(), "setCharacteStream(long)");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc3\AbstractJdbc3Clob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */