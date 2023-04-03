/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class BufferedRandomAccessFileInputStream extends InputStream {
/*     */   private static final int DEFAULT_BUFFER_COUNT = 4;
/*     */   
/*     */   private static final int DEFAULT_INITIAL_BUFFER_SIZE = 16;
/*     */   
/*     */   private static final int DEFAULT_MAXIMUM_BUFFER_SIZE = 4096;
/*     */   
/*     */   private static final float DEFAULT_BUFFER_INCREASE_FACTOR = 2.0F;
/*     */   
/*     */   private RandomAccessFile randomFile;
/*     */   
/*     */   private int bufferCount;
/*     */   
/*     */   private List<BufferedReader> readerList;
/*     */   
/*     */   public BufferedRandomAccessFileInputStream(File file) throws FileNotFoundException {
/*  42 */     this(file, 4, 16, 4096, 2.0F);
/*     */   }
/*     */   
/*     */   public BufferedRandomAccessFileInputStream(File file, int bufferCount, int initialBufferSize, int maxBufferSize, float bufferIncreaseFactor) throws FileNotFoundException {
/*  77 */     this.bufferCount = bufferCount;
/*  79 */     this.randomFile = new RandomAccessFile(file, "r");
/*  81 */     this.readerList = new LinkedList<BufferedReader>();
/*  82 */     for (int i = 0; i < bufferCount; i++)
/*  83 */       this.readerList.add(new BufferedReader(this.randomFile, initialBufferSize, maxBufferSize, bufferIncreaseFactor)); 
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/*  93 */     return ((BufferedReader)this.readerList.get(0)).read();
/*     */   }
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 102 */     return read(b, 0, b.length);
/*     */   }
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 111 */     return ((BufferedReader)this.readerList.get(0)).read(b, off, len);
/*     */   }
/*     */   
/*     */   public void seek(long pos) throws IOException {
/* 126 */     BufferedReader reader = null;
/* 127 */     for (int i = 0; i < this.bufferCount; i++) {
/* 130 */       BufferedReader tmpReader = this.readerList.get(i);
/* 132 */       if (tmpReader.containsPosition(pos)) {
/* 133 */         reader = tmpReader;
/* 135 */         if (i > 0) {
/* 136 */           this.readerList.remove(i);
/* 137 */           this.readerList.add(0, reader);
/*     */         } 
/*     */         break;
/*     */       } 
/*     */     } 
/* 144 */     if (reader == null) {
/* 145 */       reader = this.readerList.remove(this.bufferCount - 1);
/* 146 */       this.readerList.add(0, reader);
/*     */     } 
/* 149 */     reader.seek(pos);
/*     */   }
/*     */   
/*     */   public long length() throws IOException {
/* 161 */     return this.randomFile.length();
/*     */   }
/*     */   
/*     */   public long position() throws IOException {
/* 173 */     return ((BufferedReader)this.readerList.get(0)).position();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 182 */     this.randomFile.close();
/*     */   }
/*     */   
/*     */   private static class BufferedReader {
/*     */     private RandomAccessFile randomFile;
/*     */     
/*     */     private int initialBufferSize;
/*     */     
/*     */     private int maxBufferSize;
/*     */     
/*     */     private float bufferIncreaseFactor;
/*     */     
/*     */     private byte[] buffer;
/*     */     
/*     */     private long bufferFilePosition;
/*     */     
/*     */     private int currentBufferSize;
/*     */     
/*     */     private int currentBufferByteCount;
/*     */     
/*     */     private int currentBufferOffset;
/*     */     
/*     */     public BufferedReader(RandomAccessFile randomFile, int initialBufferSize, int maxBufferSize, float bufferIncreaseFactor) {
/* 221 */       this.randomFile = randomFile;
/* 222 */       this.initialBufferSize = initialBufferSize;
/* 223 */       this.maxBufferSize = maxBufferSize;
/* 224 */       this.bufferIncreaseFactor = bufferIncreaseFactor;
/* 226 */       this.buffer = new byte[maxBufferSize];
/* 228 */       this.bufferFilePosition = 0L;
/* 229 */       this.currentBufferSize = 0;
/* 230 */       this.currentBufferByteCount = 0;
/* 231 */       this.currentBufferOffset = 0;
/*     */     }
/*     */     
/*     */     public long position() throws IOException {
/* 243 */       return this.bufferFilePosition + this.currentBufferOffset;
/*     */     }
/*     */     
/*     */     public boolean containsPosition(long position) {
/* 259 */       return (position >= this.bufferFilePosition && position < this.bufferFilePosition + this.maxBufferSize);
/*     */     }
/*     */     
/*     */     public void seek(long pos) throws IOException {
/* 275 */       long bufferBeginFileOffset = this.bufferFilePosition;
/* 276 */       long bufferEndFileOffset = this.bufferFilePosition + this.currentBufferByteCount;
/* 280 */       if (pos >= bufferBeginFileOffset && pos <= bufferEndFileOffset) {
/* 283 */         this.currentBufferOffset = (int)(pos - bufferBeginFileOffset);
/* 284 */       } else if (pos < bufferBeginFileOffset && bufferBeginFileOffset - pos <= this.maxBufferSize) {
/* 288 */         this.randomFile.seek(pos);
/* 289 */         this.bufferFilePosition = pos;
/* 292 */         this.currentBufferByteCount = 0;
/* 293 */       } else if (pos > bufferEndFileOffset && pos - bufferEndFileOffset <= this.maxBufferSize) {
/* 297 */         this.randomFile.seek(pos);
/* 298 */         this.bufferFilePosition = pos;
/* 301 */         this.currentBufferByteCount = 0;
/*     */       } else {
/* 305 */         this.randomFile.seek(pos);
/* 306 */         this.bufferFilePosition = pos;
/* 307 */         this.currentBufferSize = 0;
/* 308 */         this.currentBufferByteCount = 0;
/* 309 */         this.currentBufferOffset = 0;
/*     */       } 
/*     */     }
/*     */     
/*     */     private boolean populateBuffer() throws IOException {
/* 321 */       if (this.currentBufferOffset >= this.currentBufferByteCount) {
/* 324 */         if (this.randomFile.getFilePointer() != this.bufferFilePosition + this.currentBufferByteCount)
/* 325 */           this.randomFile.seek(this.bufferFilePosition + this.currentBufferByteCount); 
/* 330 */         this.bufferFilePosition += this.currentBufferByteCount;
/* 331 */         this.currentBufferOffset = 0;
/* 333 */         if (this.currentBufferSize == 0) {
/* 334 */           this.currentBufferSize = this.initialBufferSize;
/* 335 */         } else if (this.currentBufferSize < this.maxBufferSize) {
/* 336 */           this.currentBufferSize = (int)(this.currentBufferSize * this.bufferIncreaseFactor);
/* 337 */           if (this.currentBufferSize > this.maxBufferSize)
/* 338 */             this.currentBufferSize = this.maxBufferSize; 
/*     */         } 
/* 343 */         this.currentBufferByteCount = this.randomFile.read(this.buffer, 0, this.currentBufferSize);
/* 345 */         if (this.currentBufferByteCount < 0)
/* 346 */           return false; 
/*     */       } 
/* 350 */       return true;
/*     */     }
/*     */     
/*     */     public int read() throws IOException {
/* 362 */       if (populateBuffer())
/* 363 */         return this.buffer[this.currentBufferOffset++] & 0xFF; 
/* 365 */       return -1;
/*     */     }
/*     */     
/*     */     public int read(byte[] b, int off, int len) throws IOException {
/* 384 */       if (populateBuffer()) {
/* 388 */         int readLength = this.currentBufferByteCount - this.currentBufferOffset;
/* 389 */         if (readLength > len)
/* 390 */           readLength = len; 
/* 394 */         System.arraycopy(this.buffer, this.currentBufferOffset, b, off, readLength);
/* 395 */         this.currentBufferOffset += readLength;
/* 397 */         return readLength;
/*     */       } 
/* 400 */       return -1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\BufferedRandomAccessFileInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */