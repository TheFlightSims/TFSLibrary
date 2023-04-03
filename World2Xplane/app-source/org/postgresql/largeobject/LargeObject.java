/*     */ package org.postgresql.largeobject;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.fastpath.Fastpath;
/*     */ import org.postgresql.fastpath.FastpathArg;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class LargeObject {
/*     */   public static final int SEEK_SET = 0;
/*     */   
/*     */   public static final int SEEK_CUR = 1;
/*     */   
/*     */   public static final int SEEK_END = 2;
/*     */   
/*     */   private final Fastpath fp;
/*     */   
/*     */   private final long oid;
/*     */   
/*     */   private final int mode;
/*     */   
/*     */   private final int fd;
/*     */   
/*     */   private BlobOutputStream os;
/*     */   
/*     */   private boolean closed = false;
/*     */   
/*     */   protected LargeObject(Fastpath fp, long oid, int mode) throws SQLException {
/*  86 */     this.fp = fp;
/*  87 */     this.oid = oid;
/*  88 */     this.mode = mode;
/*  90 */     FastpathArg[] args = new FastpathArg[2];
/*  91 */     args[0] = Fastpath.createOIDArg(oid);
/*  92 */     args[1] = new FastpathArg(mode);
/*  93 */     this.fd = fp.getInteger("lo_open", args);
/*     */   }
/*     */   
/*     */   public LargeObject copy() throws SQLException {
/*  98 */     return new LargeObject(this.fp, this.oid, this.mode);
/*     */   }
/*     */   
/*     */   public int getOID() {
/* 121 */     return (int)this.oid;
/*     */   }
/*     */   
/*     */   public long getLongOID() {
/* 129 */     return this.oid;
/*     */   }
/*     */   
/*     */   public void close() throws SQLException {
/* 139 */     if (!this.closed) {
/* 142 */       if (this.os != null)
/*     */         try {
/* 147 */           this.os.flush();
/* 149 */         } catch (IOException ioe) {
/* 151 */           throw new PSQLException("Exception flushing output stream", PSQLState.DATA_ERROR, ioe);
/*     */         } finally {
/* 156 */           this.os = null;
/*     */         }  
/* 161 */       FastpathArg[] args = new FastpathArg[1];
/* 162 */       args[0] = new FastpathArg(this.fd);
/* 163 */       this.fp.fastpath("lo_close", false, args);
/* 164 */       this.closed = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] read(int len) throws SQLException {
/* 179 */     FastpathArg[] args = new FastpathArg[2];
/* 180 */     args[0] = new FastpathArg(this.fd);
/* 181 */     args[1] = new FastpathArg(len);
/* 182 */     return this.fp.getData("loread", args);
/*     */   }
/*     */   
/*     */   public int read(byte[] buf, int off, int len) throws SQLException {
/* 196 */     byte[] b = read(len);
/* 197 */     if (b.length < len)
/* 198 */       len = b.length; 
/* 199 */     System.arraycopy(b, 0, buf, off, len);
/* 200 */     return len;
/*     */   }
/*     */   
/*     */   public void write(byte[] buf) throws SQLException {
/* 211 */     FastpathArg[] args = new FastpathArg[2];
/* 212 */     args[0] = new FastpathArg(this.fd);
/* 213 */     args[1] = new FastpathArg(buf);
/* 214 */     this.fp.fastpath("lowrite", false, args);
/*     */   }
/*     */   
/*     */   public void write(byte[] buf, int off, int len) throws SQLException {
/* 227 */     FastpathArg[] args = new FastpathArg[2];
/* 228 */     args[0] = new FastpathArg(this.fd);
/* 229 */     args[1] = new FastpathArg(buf, off, len);
/* 230 */     this.fp.fastpath("lowrite", false, args);
/*     */   }
/*     */   
/*     */   public void seek(int pos, int ref) throws SQLException {
/* 245 */     FastpathArg[] args = new FastpathArg[3];
/* 246 */     args[0] = new FastpathArg(this.fd);
/* 247 */     args[1] = new FastpathArg(pos);
/* 248 */     args[2] = new FastpathArg(ref);
/* 249 */     this.fp.fastpath("lo_lseek", false, args);
/*     */   }
/*     */   
/*     */   public void seek(int pos) throws SQLException {
/* 263 */     seek(pos, 0);
/*     */   }
/*     */   
/*     */   public int tell() throws SQLException {
/* 272 */     FastpathArg[] args = new FastpathArg[1];
/* 273 */     args[0] = new FastpathArg(this.fd);
/* 274 */     return this.fp.getInteger("lo_tell", args);
/*     */   }
/*     */   
/*     */   public int size() throws SQLException {
/* 289 */     int cp = tell();
/* 290 */     seek(0, 2);
/* 291 */     int sz = tell();
/* 292 */     seek(cp, 0);
/* 293 */     return sz;
/*     */   }
/*     */   
/*     */   public void truncate(int len) throws SQLException {
/* 304 */     FastpathArg[] args = new FastpathArg[2];
/* 305 */     args[0] = new FastpathArg(this.fd);
/* 306 */     args[1] = new FastpathArg(len);
/* 307 */     this.fp.getInteger("lo_truncate", args);
/*     */   }
/*     */   
/*     */   public InputStream getInputStream() throws SQLException {
/* 320 */     return new BlobInputStream(this, 4096);
/*     */   }
/*     */   
/*     */   public OutputStream getOutputStream() throws SQLException {
/* 333 */     if (this.os == null)
/* 334 */       this.os = new BlobOutputStream(this, 4096); 
/* 335 */     return this.os;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\largeobject\LargeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */