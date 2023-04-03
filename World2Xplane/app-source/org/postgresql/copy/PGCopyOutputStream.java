/*     */ package org.postgresql.copy;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.PGConnection;
/*     */ import org.postgresql.util.GT;
/*     */ 
/*     */ public class PGCopyOutputStream extends OutputStream implements CopyIn {
/*     */   private CopyIn op;
/*     */   
/*     */   private final byte[] copyBuffer;
/*     */   
/*  25 */   private final byte[] singleByteBuffer = new byte[1];
/*     */   
/*  26 */   private int at = 0;
/*     */   
/*     */   public PGCopyOutputStream(PGConnection connection, String sql) throws SQLException {
/*  35 */     this(connection, sql, 65536);
/*     */   }
/*     */   
/*     */   public PGCopyOutputStream(PGConnection connection, String sql, int bufferSize) throws SQLException {
/*  46 */     this(connection.getCopyAPI().copyIn(sql), bufferSize);
/*     */   }
/*     */   
/*     */   public PGCopyOutputStream(CopyIn op) {
/*  54 */     this(op, 65536);
/*     */   }
/*     */   
/*     */   public PGCopyOutputStream(CopyIn op, int bufferSize) {
/*  63 */     this.op = op;
/*  64 */     this.copyBuffer = new byte[bufferSize];
/*     */   }
/*     */   
/*     */   public void write(int b) throws IOException {
/*  68 */     checkClosed();
/*  69 */     if (b < 0 || b > 255)
/*  70 */       throw new IOException(GT.tr("Cannot write to copy a byte of value {0}", new Integer(b))); 
/*  71 */     this.singleByteBuffer[0] = (byte)b;
/*  72 */     write(this.singleByteBuffer, 0, 1);
/*     */   }
/*     */   
/*     */   public void write(byte[] buf) throws IOException {
/*  76 */     write(buf, 0, buf.length);
/*     */   }
/*     */   
/*     */   public void write(byte[] buf, int off, int siz) throws IOException {
/*  80 */     checkClosed();
/*     */     try {
/*  82 */       writeToCopy(buf, off, siz);
/*  83 */     } catch (SQLException se) {
/*  84 */       IOException ioe = new IOException("Write to copy failed.");
/*  85 */       ioe.initCause(se);
/*  86 */       throw ioe;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkClosed() throws IOException {
/*  91 */     if (this.op == null)
/*  92 */       throw new IOException(GT.tr("This copy stream is closed.")); 
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  98 */     if (this.op == null)
/*     */       return; 
/*     */     try {
/* 102 */       endCopy();
/* 103 */     } catch (SQLException se) {
/* 104 */       IOException ioe = new IOException("Ending write to copy failed.");
/* 105 */       ioe.initCause(se);
/* 106 */       throw ioe;
/*     */     } 
/* 108 */     this.op = null;
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/*     */     try {
/* 113 */       this.op.writeToCopy(this.copyBuffer, 0, this.at);
/* 114 */       this.at = 0;
/* 115 */       this.op.flushCopy();
/* 116 */     } catch (SQLException e) {
/* 117 */       IOException ioe = new IOException("Unable to flush stream");
/* 118 */       ioe.initCause(e);
/* 119 */       throw ioe;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeToCopy(byte[] buf, int off, int siz) throws SQLException {
/* 124 */     if (this.at > 0 && siz > this.copyBuffer.length - this.at) {
/* 125 */       this.op.writeToCopy(this.copyBuffer, 0, this.at);
/* 126 */       this.at = 0;
/*     */     } 
/* 128 */     if (siz > this.copyBuffer.length) {
/* 129 */       this.op.writeToCopy(buf, off, siz);
/*     */     } else {
/* 131 */       System.arraycopy(buf, off, this.copyBuffer, this.at, siz);
/* 132 */       this.at += siz;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getFormat() {
/* 137 */     return this.op.getFormat();
/*     */   }
/*     */   
/*     */   public int getFieldFormat(int field) {
/* 141 */     return this.op.getFieldFormat(field);
/*     */   }
/*     */   
/*     */   public void cancelCopy() throws SQLException {
/* 145 */     this.op.cancelCopy();
/*     */   }
/*     */   
/*     */   public int getFieldCount() {
/* 149 */     return this.op.getFieldCount();
/*     */   }
/*     */   
/*     */   public boolean isActive() {
/* 153 */     return this.op.isActive();
/*     */   }
/*     */   
/*     */   public void flushCopy() throws SQLException {
/* 157 */     this.op.flushCopy();
/*     */   }
/*     */   
/*     */   public long endCopy() throws SQLException {
/* 161 */     if (this.at > 0)
/* 162 */       this.op.writeToCopy(this.copyBuffer, 0, this.at); 
/* 164 */     this.op.endCopy();
/* 165 */     return getHandledRowCount();
/*     */   }
/*     */   
/*     */   public long getHandledRowCount() {
/* 169 */     return this.op.getHandledRowCount();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\copy\PGCopyOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */