/*     */ package org.postgresql.core;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public class VisibleBufferedInputStream extends InputStream {
/*     */   private static final int MINIMUM_READ = 1024;
/*     */   
/*     */   private static final int STRING_SCAN_SPAN = 1024;
/*     */   
/*     */   private final InputStream wrapped;
/*     */   
/*     */   private byte[] buffer;
/*     */   
/*     */   private int index;
/*     */   
/*     */   private int endIndex;
/*     */   
/*     */   public VisibleBufferedInputStream(InputStream in, int bufferSize) {
/*  64 */     this.wrapped = in;
/*  65 */     this.buffer = new byte[(bufferSize < 1024) ? 1024 : bufferSize];
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/*  73 */     if (ensureBytes(1))
/*  74 */       return this.buffer[this.index++] & 0xFF; 
/*  76 */     return -1;
/*     */   }
/*     */   
/*     */   public int peek() throws IOException {
/*  83 */     if (ensureBytes(1))
/*  84 */       return this.buffer[this.index] & 0xFF; 
/*  86 */     return -1;
/*     */   }
/*     */   
/*     */   public byte readRaw() {
/* 100 */     return this.buffer[this.index++];
/*     */   }
/*     */   
/*     */   public boolean ensureBytes(int n) throws IOException {
/* 112 */     int required = n - this.endIndex + this.index;
/* 113 */     while (required > 0) {
/* 114 */       if (!readMore(required))
/* 115 */         return false; 
/* 117 */       required = n - this.endIndex + this.index;
/*     */     } 
/* 119 */     return true;
/*     */   }
/*     */   
/*     */   private boolean readMore(int wanted) throws IOException {
/* 130 */     if (this.endIndex == this.index) {
/* 131 */       this.index = 0;
/* 132 */       this.endIndex = 0;
/*     */     } 
/* 134 */     int canFit = this.buffer.length - this.endIndex;
/* 135 */     if (canFit < wanted) {
/* 138 */       if (this.index + canFit > wanted + 1024) {
/* 139 */         compact();
/*     */       } else {
/* 141 */         doubleBuffer();
/*     */       } 
/* 143 */       canFit = this.buffer.length - this.endIndex;
/*     */     } 
/* 145 */     int read = this.wrapped.read(this.buffer, this.endIndex, canFit);
/* 146 */     if (read < 0)
/* 147 */       return false; 
/* 149 */     this.endIndex += read;
/* 150 */     return true;
/*     */   }
/*     */   
/*     */   private void doubleBuffer() {
/* 157 */     byte[] buf = new byte[this.buffer.length * 2];
/* 158 */     moveBufferTo(buf);
/* 159 */     this.buffer = buf;
/*     */   }
/*     */   
/*     */   private void compact() {
/* 166 */     moveBufferTo(this.buffer);
/*     */   }
/*     */   
/*     */   private void moveBufferTo(byte[] dest) {
/* 176 */     int size = this.endIndex - this.index;
/* 177 */     System.arraycopy(this.buffer, this.index, dest, 0, size);
/* 178 */     this.index = 0;
/* 179 */     this.endIndex = size;
/*     */   }
/*     */   
/*     */   public int read(byte[] to, int off, int len) throws IOException {
/* 186 */     if ((off | len | off + len | to.length - off + len) < 0)
/* 187 */       throw new IndexOutOfBoundsException(); 
/* 188 */     if (len == 0)
/* 189 */       return 0; 
/* 194 */     int avail = this.endIndex - this.index;
/* 195 */     if (len - avail < 1024) {
/* 196 */       ensureBytes(len);
/* 197 */       avail = this.endIndex - this.index;
/*     */     } 
/* 201 */     if (avail > 0) {
/* 202 */       if (len <= avail) {
/* 203 */         System.arraycopy(this.buffer, this.index, to, off, len);
/* 204 */         this.index += len;
/* 205 */         return len;
/*     */       } 
/* 207 */       System.arraycopy(this.buffer, this.index, to, off, avail);
/* 208 */       len -= avail;
/* 209 */       off += avail;
/*     */     } 
/* 211 */     int read = avail;
/* 214 */     this.index = 0;
/* 215 */     this.endIndex = 0;
/*     */     do {
/* 219 */       int r = this.wrapped.read(to, off, len);
/* 220 */       if (r <= 0)
/* 221 */         return (read == 0) ? r : read; 
/* 223 */       read += r;
/* 224 */       off += r;
/* 225 */       len -= r;
/* 226 */     } while (len > 0);
/* 228 */     return read;
/*     */   }
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 235 */     int avail = this.endIndex - this.index;
/* 236 */     if (avail >= n) {
/* 237 */       this.index = (int)(this.index + n);
/* 238 */       return n;
/*     */     } 
/* 240 */     n -= avail;
/* 241 */     this.index = 0;
/* 242 */     this.endIndex = 0;
/* 243 */     return avail + this.wrapped.skip(n);
/*     */   }
/*     */   
/*     */   public int available() throws IOException {
/* 250 */     int avail = this.endIndex - this.index;
/* 251 */     return (avail > 0) ? avail : this.wrapped.available();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 258 */     this.wrapped.close();
/*     */   }
/*     */   
/*     */   public byte[] getBuffer() {
/* 269 */     return this.buffer;
/*     */   }
/*     */   
/*     */   public int getIndex() {
/* 278 */     return this.index;
/*     */   }
/*     */   
/*     */   public int scanCStringLength() throws IOException {
/* 290 */     int pos = this.index;
/*     */     while (true) {
/* 292 */       while (pos < this.endIndex) {
/* 293 */         if (this.buffer[pos++] == 0)
/* 294 */           return pos - this.index; 
/*     */       } 
/* 297 */       if (!readMore(1024))
/* 298 */         throw new EOFException(); 
/* 300 */       pos = this.index;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\VisibleBufferedInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */