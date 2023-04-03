/*     */ package org.postgresql.jdbc2;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.sql.Blob;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.largeobject.LargeObject;
/*     */ import org.postgresql.largeobject.LargeObjectManager;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public abstract class AbstractJdbc2BlobClob {
/*     */   protected BaseConnection conn;
/*     */   
/*     */   protected LargeObject lo;
/*     */   
/*     */   private ArrayList subLOs;
/*     */   
/*     */   public AbstractJdbc2BlobClob(BaseConnection conn, long oid) throws SQLException {
/*  45 */     this.conn = conn;
/*  46 */     LargeObjectManager lom = conn.getLargeObjectAPI();
/*  47 */     this.lo = lom.open(oid);
/*  48 */     this.subLOs = new ArrayList();
/*     */   }
/*     */   
/*     */   public synchronized void free() throws SQLException {
/*  53 */     if (this.lo != null) {
/*  54 */       this.lo.close();
/*  55 */       this.lo = null;
/*     */     } 
/*  57 */     Iterator<LargeObject> i = this.subLOs.iterator();
/*  58 */     while (i.hasNext()) {
/*  59 */       LargeObject subLO = i.next();
/*  60 */       subLO.close();
/*     */     } 
/*  62 */     this.subLOs = null;
/*     */   }
/*     */   
/*     */   public synchronized void truncate(long len) throws SQLException {
/*  73 */     checkFreed();
/*  74 */     if (!this.conn.haveMinimumServerVersion("8.3"))
/*  75 */       throw new PSQLException(GT.tr("Truncation of large objects is only implemented in 8.3 and later servers."), PSQLState.NOT_IMPLEMENTED); 
/*  77 */     if (len < 0L)
/*  79 */       throw new PSQLException(GT.tr("Cannot truncate LOB to a negative length."), PSQLState.INVALID_PARAMETER_VALUE); 
/*  81 */     if (len > 2147483647L)
/*  83 */       throw new PSQLException(GT.tr("PostgreSQL LOBs can only index to: {0}", new Integer(2147483647)), PSQLState.INVALID_PARAMETER_VALUE); 
/*  86 */     this.lo.truncate((int)len);
/*     */   }
/*     */   
/*     */   public synchronized long length() throws SQLException {
/*  91 */     checkFreed();
/*  92 */     return this.lo.size();
/*     */   }
/*     */   
/*     */   public synchronized byte[] getBytes(long pos, int length) throws SQLException {
/*  97 */     assertPosition(pos);
/*  98 */     this.lo.seek((int)(pos - 1L), 0);
/*  99 */     return this.lo.read(length);
/*     */   }
/*     */   
/*     */   public synchronized InputStream getBinaryStream() throws SQLException {
/* 105 */     checkFreed();
/* 106 */     LargeObject subLO = this.lo.copy();
/* 107 */     this.subLOs.add(subLO);
/* 108 */     subLO.seek(0, 0);
/* 109 */     return subLO.getInputStream();
/*     */   }
/*     */   
/*     */   public synchronized OutputStream setBinaryStream(long pos) throws SQLException {
/* 114 */     assertPosition(pos);
/* 115 */     LargeObject subLO = this.lo.copy();
/* 116 */     this.subLOs.add(subLO);
/* 117 */     subLO.seek((int)(pos - 1L));
/* 118 */     return subLO.getOutputStream();
/*     */   }
/*     */   
/*     */   public synchronized long position(byte[] pattern, long start) throws SQLException {
/* 129 */     assertPosition(start, pattern.length);
/* 131 */     int position = 1;
/* 132 */     int patternIdx = 0;
/* 133 */     long result = -1L;
/* 134 */     int tmpPosition = 1;
/* 136 */     for (LOIterator i = new LOIterator(start - 1L); i.hasNext(); position++) {
/* 138 */       byte b = i.next();
/* 139 */       if (b == pattern[patternIdx]) {
/* 141 */         if (patternIdx == 0)
/* 143 */           tmpPosition = position; 
/* 145 */         patternIdx++;
/* 146 */         if (patternIdx == pattern.length) {
/* 148 */           result = tmpPosition;
/*     */           break;
/*     */         } 
/*     */       } else {
/* 154 */         patternIdx = 0;
/*     */       } 
/*     */     } 
/* 158 */     return result;
/*     */   }
/*     */   
/*     */   private class LOIterator {
/*     */     private static final int BUFFER_SIZE = 8096;
/*     */     
/* 170 */     private byte[] buffer = new byte[8096];
/*     */     
/* 171 */     private int idx = 8096;
/*     */     
/* 172 */     private int numBytes = 8096;
/*     */     
/*     */     public LOIterator(long start) throws SQLException {
/* 176 */       AbstractJdbc2BlobClob.this.lo.seek((int)start);
/*     */     }
/*     */     
/*     */     public boolean hasNext() throws SQLException {
/* 181 */       boolean result = false;
/* 182 */       if (this.idx < this.numBytes) {
/* 184 */         result = true;
/*     */       } else {
/* 188 */         this.numBytes = AbstractJdbc2BlobClob.this.lo.read(this.buffer, 0, 8096);
/* 189 */         this.idx = 0;
/* 190 */         result = (this.numBytes > 0);
/*     */       } 
/* 192 */       return result;
/*     */     }
/*     */     
/*     */     private byte next() {
/* 197 */       return this.buffer[this.idx++];
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized long position(Blob pattern, long start) throws SQLException {
/* 207 */     return position(pattern.getBytes(1L, (int)pattern.length()), start);
/*     */   }
/*     */   
/*     */   protected void assertPosition(long pos) throws SQLException {
/* 218 */     assertPosition(pos, 0L);
/*     */   }
/*     */   
/*     */   protected void assertPosition(long pos, long len) throws SQLException {
/* 230 */     checkFreed();
/* 231 */     if (pos < 1L)
/* 233 */       throw new PSQLException(GT.tr("LOB positioning offsets start at 1."), PSQLState.INVALID_PARAMETER_VALUE); 
/* 235 */     if (pos + len - 1L > 2147483647L)
/* 237 */       throw new PSQLException(GT.tr("PostgreSQL LOBs can only index to: {0}", new Integer(2147483647)), PSQLState.INVALID_PARAMETER_VALUE); 
/*     */   }
/*     */   
/*     */   protected void checkFreed() throws SQLException {
/* 247 */     if (this.lo == null)
/* 248 */       throw new PSQLException(GT.tr("free() was called on this LOB previously"), PSQLState.OBJECT_NOT_IN_STATE); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\AbstractJdbc2BlobClob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */