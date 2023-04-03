/*     */ package org.postgresql.copy;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.core.Encoding;
/*     */ import org.postgresql.core.QueryExecutor;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class CopyManager {
/*     */   static final int DEFAULT_BUFFER_SIZE = 65536;
/*     */   
/*     */   private final Encoding encoding;
/*     */   
/*     */   private final QueryExecutor queryExecutor;
/*     */   
/*     */   private final BaseConnection connection;
/*     */   
/*     */   public CopyManager(BaseConnection connection) throws SQLException {
/*  46 */     this.encoding = connection.getEncoding();
/*  47 */     this.queryExecutor = connection.getQueryExecutor();
/*  48 */     this.connection = connection;
/*     */   }
/*     */   
/*     */   public CopyIn copyIn(String sql) throws SQLException {
/*  52 */     CopyOperation op = null;
/*     */     try {
/*  54 */       op = this.queryExecutor.startCopy(sql, this.connection.getAutoCommit());
/*  55 */       return (CopyIn)op;
/*  56 */     } catch (ClassCastException cce) {
/*  57 */       op.cancelCopy();
/*  58 */       throw new PSQLException(GT.tr("Requested CopyIn but got {0}", op.getClass().getName()), PSQLState.WRONG_OBJECT_TYPE, cce);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CopyOut copyOut(String sql) throws SQLException {
/*  63 */     CopyOperation op = null;
/*     */     try {
/*  65 */       op = this.queryExecutor.startCopy(sql, this.connection.getAutoCommit());
/*  66 */       return (CopyOut)op;
/*  67 */     } catch (ClassCastException cce) {
/*  68 */       op.cancelCopy();
/*  69 */       throw new PSQLException(GT.tr("Requested CopyOut but got {0}", op.getClass().getName()), PSQLState.WRONG_OBJECT_TYPE, cce);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long copyOut(String sql, Writer to) throws SQLException, IOException {
/*  83 */     CopyOut cp = copyOut(sql);
/*     */     try {
/*     */       byte[] buf;
/*  85 */       while ((buf = cp.readFromCopy()) != null)
/*  86 */         to.write(this.encoding.decode(buf)); 
/*  88 */       return cp.getHandledRowCount();
/*     */     } finally {
/*  90 */       if (cp.isActive())
/*  91 */         cp.cancelCopy(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public long copyOut(String sql, OutputStream to) throws SQLException, IOException {
/* 105 */     CopyOut cp = copyOut(sql);
/*     */     try {
/*     */       byte[] buf;
/* 107 */       while ((buf = cp.readFromCopy()) != null)
/* 108 */         to.write(buf); 
/* 110 */       return cp.getHandledRowCount();
/*     */     } finally {
/* 112 */       if (cp.isActive())
/* 113 */         cp.cancelCopy(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public long copyIn(String sql, Reader from) throws SQLException, IOException {
/* 126 */     return copyIn(sql, from, 65536);
/*     */   }
/*     */   
/*     */   public long copyIn(String sql, Reader from, int bufferSize) throws SQLException, IOException {
/* 139 */     char[] cbuf = new char[bufferSize];
/* 141 */     CopyIn cp = copyIn(sql);
/*     */     try {
/*     */       int len;
/* 143 */       while ((len = from.read(cbuf)) > 0) {
/* 144 */         byte[] buf = this.encoding.encode(new String(cbuf, 0, len));
/* 145 */         cp.writeToCopy(buf, 0, buf.length);
/*     */       } 
/* 147 */       return cp.endCopy();
/*     */     } finally {
/* 149 */       if (cp.isActive())
/* 150 */         cp.cancelCopy(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public long copyIn(String sql, InputStream from) throws SQLException, IOException {
/* 163 */     return copyIn(sql, from, 65536);
/*     */   }
/*     */   
/*     */   public long copyIn(String sql, InputStream from, int bufferSize) throws SQLException, IOException {
/* 176 */     byte[] buf = new byte[bufferSize];
/* 178 */     CopyIn cp = copyIn(sql);
/*     */     try {
/*     */       int len;
/* 180 */       while ((len = from.read(buf)) > 0)
/* 181 */         cp.writeToCopy(buf, 0, len); 
/* 183 */       return cp.endCopy();
/*     */     } finally {
/* 185 */       if (cp.isActive())
/* 186 */         cp.cancelCopy(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\copy\CopyManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */