/*     */ package org.postgresql.copy;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.PGConnection;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class PGCopyInputStream extends InputStream implements CopyOut {
/*     */   private CopyOut op;
/*     */   
/*     */   private byte[] buf;
/*     */   
/*     */   private int at;
/*     */   
/*     */   private int len;
/*     */   
/*     */   public PGCopyInputStream(PGConnection connection, String sql) throws SQLException {
/*  36 */     this(connection.getCopyAPI().copyOut(sql));
/*     */   }
/*     */   
/*     */   public PGCopyInputStream(CopyOut op) {
/*  45 */     this.op = op;
/*     */   }
/*     */   
/*     */   private boolean gotBuf() throws IOException {
/*  49 */     if (this.at >= this.len) {
/*     */       try {
/*  51 */         this.buf = this.op.readFromCopy();
/*  52 */       } catch (SQLException sqle) {
/*  53 */         throw new IOException(GT.tr("Copying from database failed: {0}", sqle));
/*     */       } 
/*  55 */       if (this.buf == null) {
/*  56 */         this.at = -1;
/*  57 */         return false;
/*     */       } 
/*  59 */       this.at = 0;
/*  60 */       this.len = this.buf.length;
/*  61 */       return true;
/*     */     } 
/*  64 */     return (this.buf != null);
/*     */   }
/*     */   
/*     */   private void checkClosed() throws IOException {
/*  68 */     if (this.op == null)
/*  69 */       throw new IOException(GT.tr("This copy stream is closed.")); 
/*     */   }
/*     */   
/*     */   public int available() throws IOException {
/*  75 */     checkClosed();
/*  76 */     return (this.buf != null) ? (this.len - this.at) : 0;
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/*  80 */     checkClosed();
/*  81 */     return gotBuf() ? this.buf[this.at++] : -1;
/*     */   }
/*     */   
/*     */   public int read(byte[] buf) throws IOException {
/*  85 */     return read(buf, 0, buf.length);
/*     */   }
/*     */   
/*     */   public int read(byte[] buf, int off, int siz) throws IOException {
/*  89 */     checkClosed();
/*  90 */     int got = 0;
/*  91 */     while (got < siz && gotBuf())
/*  92 */       buf[off + got++] = this.buf[this.at++]; 
/*  94 */     return got;
/*     */   }
/*     */   
/*     */   public byte[] readFromCopy() throws SQLException {
/*  98 */     byte[] result = this.buf;
/*     */     try {
/* 100 */       if (gotBuf()) {
/* 101 */         if (this.at > 0 || this.len < this.buf.length) {
/* 102 */           byte[] ba = new byte[this.len - this.at];
/* 103 */           for (int i = this.at; i < this.len; i++)
/* 104 */             ba[i - this.at] = this.buf[i]; 
/* 105 */           result = ba;
/*     */         } 
/* 107 */         this.at = this.len;
/*     */       } 
/* 109 */     } catch (IOException ioe) {
/* 110 */       throw new PSQLException(GT.tr("Read from copy failed."), PSQLState.CONNECTION_FAILURE);
/*     */     } 
/* 112 */     return result;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 117 */     if (this.op == null)
/*     */       return; 
/*     */     try {
/* 121 */       this.op.cancelCopy();
/* 122 */     } catch (SQLException se) {
/* 123 */       IOException ioe = new IOException("Failed to close copy reader.");
/* 124 */       ioe.initCause(se);
/* 125 */       throw ioe;
/*     */     } 
/* 127 */     this.op = null;
/*     */   }
/*     */   
/*     */   public void cancelCopy() throws SQLException {
/* 131 */     this.op.cancelCopy();
/*     */   }
/*     */   
/*     */   public int getFormat() {
/* 135 */     return this.op.getFormat();
/*     */   }
/*     */   
/*     */   public int getFieldFormat(int field) {
/* 139 */     return this.op.getFieldFormat(field);
/*     */   }
/*     */   
/*     */   public int getFieldCount() {
/* 143 */     return this.op.getFieldCount();
/*     */   }
/*     */   
/*     */   public boolean isActive() {
/* 147 */     return this.op.isActive();
/*     */   }
/*     */   
/*     */   public long getHandledRowCount() {
/* 151 */     return this.op.getHandledRowCount();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\copy\PGCopyInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */