/*     */ package org.apache.commons.compress.archivers.tar;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ class TarBuffer {
/*     */   public static final int DEFAULT_RCDSIZE = 512;
/*     */   
/*     */   public static final int DEFAULT_BLKSIZE = 10240;
/*     */   
/*     */   private InputStream inStream;
/*     */   
/*     */   private OutputStream outStream;
/*     */   
/*     */   private final int blockSize;
/*     */   
/*     */   private final int recordSize;
/*     */   
/*     */   private final int recsPerBlock;
/*     */   
/*     */   private final byte[] blockBuffer;
/*     */   
/*     */   private int currBlkIdx;
/*     */   
/*     */   private int currRecIdx;
/*     */   
/*     */   public TarBuffer(InputStream inStream) {
/*  63 */     this(inStream, 10240);
/*     */   }
/*     */   
/*     */   public TarBuffer(InputStream inStream, int blockSize) {
/*  72 */     this(inStream, blockSize, 512);
/*     */   }
/*     */   
/*     */   public TarBuffer(InputStream inStream, int blockSize, int recordSize) {
/*  82 */     this(inStream, null, blockSize, recordSize);
/*     */   }
/*     */   
/*     */   public TarBuffer(OutputStream outStream) {
/*  90 */     this(outStream, 10240);
/*     */   }
/*     */   
/*     */   public TarBuffer(OutputStream outStream, int blockSize) {
/*  99 */     this(outStream, blockSize, 512);
/*     */   }
/*     */   
/*     */   public TarBuffer(OutputStream outStream, int blockSize, int recordSize) {
/* 109 */     this(null, outStream, blockSize, recordSize);
/*     */   }
/*     */   
/*     */   private TarBuffer(InputStream inStream, OutputStream outStream, int blockSize, int recordSize) {
/* 116 */     this.inStream = inStream;
/* 117 */     this.outStream = outStream;
/* 118 */     this.blockSize = blockSize;
/* 119 */     this.recordSize = recordSize;
/* 120 */     this.recsPerBlock = this.blockSize / this.recordSize;
/* 121 */     this.blockBuffer = new byte[this.blockSize];
/* 123 */     if (this.inStream != null) {
/* 124 */       this.currBlkIdx = -1;
/* 125 */       this.currRecIdx = this.recsPerBlock;
/*     */     } else {
/* 127 */       this.currBlkIdx = 0;
/* 128 */       this.currRecIdx = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getBlockSize() {
/* 137 */     return this.blockSize;
/*     */   }
/*     */   
/*     */   public int getRecordSize() {
/* 145 */     return this.recordSize;
/*     */   }
/*     */   
/*     */   public boolean isEOFRecord(byte[] record) {
/* 156 */     for (int i = 0, sz = getRecordSize(); i < sz; i++) {
/* 157 */       if (record[i] != 0)
/* 158 */         return false; 
/*     */     } 
/* 162 */     return true;
/*     */   }
/*     */   
/*     */   public void skipRecord() throws IOException {
/* 170 */     if (this.inStream == null)
/* 171 */       throw new IOException("reading (via skip) from an output buffer"); 
/* 174 */     if (this.currRecIdx >= this.recsPerBlock && !readBlock())
/*     */       return; 
/* 178 */     this.currRecIdx++;
/*     */   }
/*     */   
/*     */   public byte[] readRecord() throws IOException {
/* 188 */     if (this.inStream == null) {
/* 189 */       if (this.outStream == null)
/* 190 */         throw new IOException("input buffer is closed"); 
/* 192 */       throw new IOException("reading from an output buffer");
/*     */     } 
/* 195 */     if (this.currRecIdx >= this.recsPerBlock && !readBlock())
/* 196 */       return null; 
/* 199 */     byte[] result = new byte[this.recordSize];
/* 201 */     System.arraycopy(this.blockBuffer, this.currRecIdx * this.recordSize, result, 0, this.recordSize);
/* 205 */     this.currRecIdx++;
/* 207 */     return result;
/*     */   }
/*     */   
/*     */   private boolean readBlock() throws IOException {
/* 214 */     if (this.inStream == null)
/* 215 */       throw new IOException("reading from an output buffer"); 
/* 218 */     this.currRecIdx = 0;
/* 220 */     int offset = 0;
/* 221 */     int bytesNeeded = this.blockSize;
/* 223 */     while (bytesNeeded > 0) {
/* 224 */       long numBytes = this.inStream.read(this.blockBuffer, offset, bytesNeeded);
/* 240 */       if (numBytes == -1L) {
/* 241 */         if (offset == 0)
/* 245 */           return false; 
/* 253 */         Arrays.fill(this.blockBuffer, offset, offset + bytesNeeded, (byte)0);
/*     */         break;
/*     */       } 
/* 258 */       offset = (int)(offset + numBytes);
/* 259 */       bytesNeeded = (int)(bytesNeeded - numBytes);
/* 261 */       if (numBytes != this.blockSize);
/*     */     } 
/* 266 */     this.currBlkIdx++;
/* 268 */     return true;
/*     */   }
/*     */   
/*     */   public int getCurrentBlockNum() {
/* 277 */     return this.currBlkIdx;
/*     */   }
/*     */   
/*     */   public int getCurrentRecordNum() {
/* 287 */     return this.currRecIdx - 1;
/*     */   }
/*     */   
/*     */   public void writeRecord(byte[] record) throws IOException {
/* 297 */     if (this.outStream == null) {
/* 298 */       if (this.inStream == null)
/* 299 */         throw new IOException("Output buffer is closed"); 
/* 301 */       throw new IOException("writing to an input buffer");
/*     */     } 
/* 304 */     if (record.length != this.recordSize)
/* 305 */       throw new IOException("record to write has length '" + record.length + "' which is not the record size of '" + this.recordSize + "'"); 
/* 311 */     if (this.currRecIdx >= this.recsPerBlock)
/* 312 */       writeBlock(); 
/* 315 */     System.arraycopy(record, 0, this.blockBuffer, this.currRecIdx * this.recordSize, this.recordSize);
/* 319 */     this.currRecIdx++;
/*     */   }
/*     */   
/*     */   public void writeRecord(byte[] buf, int offset) throws IOException {
/* 332 */     if (this.outStream == null) {
/* 333 */       if (this.inStream == null)
/* 334 */         throw new IOException("Output buffer is closed"); 
/* 336 */       throw new IOException("writing to an input buffer");
/*     */     } 
/* 339 */     if (offset + this.recordSize > buf.length)
/* 340 */       throw new IOException("record has length '" + buf.length + "' with offset '" + offset + "' which is less than the record size of '" + this.recordSize + "'"); 
/* 346 */     if (this.currRecIdx >= this.recsPerBlock)
/* 347 */       writeBlock(); 
/* 350 */     System.arraycopy(buf, offset, this.blockBuffer, this.currRecIdx * this.recordSize, this.recordSize);
/* 354 */     this.currRecIdx++;
/*     */   }
/*     */   
/*     */   private void writeBlock() throws IOException {
/* 361 */     if (this.outStream == null)
/* 362 */       throw new IOException("writing to an input buffer"); 
/* 365 */     this.outStream.write(this.blockBuffer, 0, this.blockSize);
/* 366 */     this.outStream.flush();
/* 368 */     this.currRecIdx = 0;
/* 369 */     this.currBlkIdx++;
/* 370 */     Arrays.fill(this.blockBuffer, (byte)0);
/*     */   }
/*     */   
/*     */   void flushBlock() throws IOException {
/* 377 */     if (this.outStream == null)
/* 378 */       throw new IOException("writing to an input buffer"); 
/* 381 */     if (this.currRecIdx > 0)
/* 382 */       writeBlock(); 
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 392 */     if (this.outStream != null) {
/* 393 */       flushBlock();
/* 395 */       if (this.outStream != System.out && this.outStream != System.err) {
/* 397 */         this.outStream.close();
/* 399 */         this.outStream = null;
/*     */       } 
/* 401 */     } else if (this.inStream != null) {
/* 402 */       if (this.inStream != System.in)
/* 403 */         this.inStream.close(); 
/* 405 */       this.inStream = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\tar\TarBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */