/*     */ package org.apache.commons.compress.archivers.dump;
/*     */ 
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.Inflater;
/*     */ 
/*     */ class TapeInputStream extends FilterInputStream {
/*  37 */   private byte[] blockBuffer = new byte[1024];
/*     */   
/*  38 */   private int currBlkIdx = -1;
/*     */   
/*  39 */   private int blockSize = 1024;
/*     */   
/*  40 */   private int recordSize = 1024;
/*     */   
/*  41 */   private int readOffset = 1024;
/*     */   
/*     */   private boolean isCompressed = false;
/*     */   
/*  43 */   private long bytesRead = 0L;
/*     */   
/*     */   public TapeInputStream(InputStream in) {
/*  49 */     super(in);
/*     */   }
/*     */   
/*     */   public void resetBlockSize(int recsPerBlock, boolean isCompressed) throws IOException {
/*  68 */     this.isCompressed = isCompressed;
/*  70 */     this.blockSize = this.recordSize * recsPerBlock;
/*  73 */     byte[] oldBuffer = this.blockBuffer;
/*  76 */     this.blockBuffer = new byte[this.blockSize];
/*  77 */     System.arraycopy(oldBuffer, 0, this.blockBuffer, 0, this.recordSize);
/*  78 */     readFully(this.blockBuffer, this.recordSize, this.blockSize - this.recordSize);
/*  80 */     this.currBlkIdx = 0;
/*  81 */     this.readOffset = this.recordSize;
/*     */   }
/*     */   
/*     */   public int available() throws IOException {
/*  89 */     if (this.readOffset < this.blockSize)
/*  90 */       return this.blockSize - this.readOffset; 
/*  93 */     return this.in.available();
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/* 101 */     throw new IllegalArgumentException("all reads must be multiple of record size (" + this.recordSize + " bytes.");
/*     */   }
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 116 */     if (len % this.recordSize != 0)
/* 117 */       throw new IllegalArgumentException("all reads must be multiple of record size (" + this.recordSize + " bytes."); 
/* 122 */     int bytes = 0;
/* 124 */     while (bytes < len) {
/* 128 */       if (this.readOffset == this.blockSize && !readBlock(true))
/* 129 */         return -1; 
/* 132 */       int n = 0;
/* 134 */       if (this.readOffset + len - bytes <= this.blockSize) {
/* 136 */         n = len - bytes;
/*     */       } else {
/* 139 */         n = this.blockSize - this.readOffset;
/*     */       } 
/* 143 */       System.arraycopy(this.blockBuffer, this.readOffset, b, off, n);
/* 144 */       this.readOffset += n;
/* 145 */       bytes += n;
/* 146 */       off += n;
/*     */     } 
/* 149 */     return bytes;
/*     */   }
/*     */   
/*     */   public long skip(long len) throws IOException {
/* 162 */     if (len % this.recordSize != 0L)
/* 163 */       throw new IllegalArgumentException("all reads must be multiple of record size (" + this.recordSize + " bytes."); 
/* 168 */     long bytes = 0L;
/* 170 */     while (bytes < len) {
/* 175 */       if (this.readOffset == this.blockSize && !readBlock((len - bytes < this.blockSize)))
/* 177 */         return -1L; 
/* 180 */       long n = 0L;
/* 182 */       if (this.readOffset + len - bytes <= this.blockSize) {
/* 184 */         n = len - bytes;
/*     */       } else {
/* 187 */         n = (this.blockSize - this.readOffset);
/*     */       } 
/* 191 */       this.readOffset = (int)(this.readOffset + n);
/* 192 */       bytes += n;
/*     */     } 
/* 195 */     return bytes;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 205 */     if (this.in != null && this.in != System.in)
/* 206 */       this.in.close(); 
/*     */   }
/*     */   
/*     */   public byte[] peek() throws IOException {
/* 220 */     if (this.readOffset == this.blockSize && !readBlock(true))
/* 221 */       return null; 
/* 225 */     byte[] b = new byte[this.recordSize];
/* 226 */     System.arraycopy(this.blockBuffer, this.readOffset, b, 0, b.length);
/* 228 */     return b;
/*     */   }
/*     */   
/*     */   public byte[] readRecord() throws IOException {
/* 238 */     byte[] result = new byte[this.recordSize];
/* 240 */     if (-1 == read(result, 0, result.length))
/* 241 */       throw new ShortFileException(); 
/* 244 */     return result;
/*     */   }
/*     */   
/*     */   private boolean readBlock(boolean decompress) throws IOException {
/* 255 */     boolean success = true;
/* 257 */     if (this.in == null)
/* 258 */       throw new IOException("input buffer is closed"); 
/* 261 */     if (!this.isCompressed || this.currBlkIdx == -1) {
/* 263 */       success = readFully(this.blockBuffer, 0, this.blockSize);
/* 264 */       this.bytesRead += this.blockSize;
/*     */     } else {
/* 266 */       if (!readFully(this.blockBuffer, 0, 4))
/* 267 */         return false; 
/* 269 */       this.bytesRead += 4L;
/* 271 */       int h = DumpArchiveUtil.convert32(this.blockBuffer, 0);
/* 272 */       boolean compressed = ((h & 0x1) == 1);
/* 274 */       if (!compressed) {
/* 276 */         success = readFully(this.blockBuffer, 0, this.blockSize);
/* 277 */         this.bytesRead += this.blockSize;
/*     */       } else {
/* 280 */         int flags = h >> 1 & 0x7;
/* 281 */         int length = h >> 4 & 0xFFFFFFF;
/* 282 */         byte[] compBuffer = new byte[length];
/* 283 */         success = readFully(compBuffer, 0, length);
/* 284 */         this.bytesRead += length;
/* 286 */         if (!decompress) {
/* 288 */           Arrays.fill(this.blockBuffer, (byte)0);
/*     */         } else {
/* 290 */           switch (DumpArchiveConstants.COMPRESSION_TYPE.find(flags & 0x3)) {
/*     */             case ZLIB:
/*     */               try {
/* 295 */                 Inflater inflator = new Inflater();
/* 296 */                 inflator.setInput(compBuffer, 0, compBuffer.length);
/* 297 */                 length = inflator.inflate(this.blockBuffer);
/* 299 */                 if (length != this.blockSize)
/* 300 */                   throw new ShortFileException(); 
/* 303 */                 inflator.end();
/* 304 */               } catch (DataFormatException e) {
/* 305 */                 throw new DumpArchiveException("bad data", e);
/*     */               } 
/* 325 */               this.currBlkIdx++;
/* 326 */               this.readOffset = 0;
/* 328 */               return success;
/*     */             case BZLIB:
/*     */               throw new UnsupportedCompressionAlgorithmException("BZLIB2");
/*     */             case LZO:
/*     */               throw new UnsupportedCompressionAlgorithmException("LZO");
/*     */           } 
/*     */           throw new UnsupportedCompressionAlgorithmException();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     this.currBlkIdx++;
/*     */     this.readOffset = 0;
/* 328 */     return success;
/*     */   }
/*     */   
/*     */   private boolean readFully(byte[] b, int off, int len) throws IOException {
/* 336 */     int count = 0;
/* 338 */     while (count < len) {
/* 339 */       int n = this.in.read(b, off + count, len - count);
/* 341 */       if (n == -1)
/* 342 */         throw new ShortFileException(); 
/* 345 */       count += n;
/*     */     } 
/* 348 */     return true;
/*     */   }
/*     */   
/*     */   public long getBytesRead() {
/* 355 */     return this.bytesRead;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\dump\TapeInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */