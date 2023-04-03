/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import java.io.CharConversionException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ 
/*     */ abstract class BaseReader extends Reader {
/*     */   protected static final char NULL_CHAR = '\000';
/*     */   
/*     */   protected static final char NULL_BYTE = '\000';
/*     */   
/*     */   protected static final char CONVERT_NEL_TO = '\n';
/*     */   
/*     */   protected static final char CONVERT_LSEP_TO = '\n';
/*     */   
/*     */   static final char CHAR_DEL = '';
/*     */   
/*     */   protected final ReaderConfig mConfig;
/*     */   
/*     */   private InputStream mIn;
/*     */   
/*     */   protected byte[] mByteBuffer;
/*     */   
/*     */   protected int mBytePtr;
/*     */   
/*     */   protected int mByteBufferEnd;
/*     */   
/*     */   private final boolean mRecycleBuffer;
/*     */   
/*     */   char[] mTmpBuf;
/*     */   
/*     */   protected BaseReader(ReaderConfig cfg, InputStream in, byte[] buf, int ptr, int len, boolean recycleBuffer) {
/* 118 */     this.mTmpBuf = null;
/*     */     this.mConfig = cfg;
/*     */     this.mIn = in;
/*     */     this.mByteBuffer = buf;
/*     */     this.mBytePtr = ptr;
/*     */     this.mByteBufferEnd = len;
/*     */     this.mRecycleBuffer = recycleBuffer;
/*     */   }
/*     */   
/*     */   public abstract void setXmlCompliancy(int paramInt);
/*     */   
/*     */   protected final boolean canModifyBuffer() {
/*     */     return this.mRecycleBuffer;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*     */     InputStream in = this.mIn;
/*     */     if (in != null) {
/*     */       this.mIn = null;
/*     */       freeBuffers();
/*     */       in.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/* 128 */     if (this.mTmpBuf == null)
/* 129 */       this.mTmpBuf = new char[1]; 
/* 131 */     if (read(this.mTmpBuf, 0, 1) < 1)
/* 132 */       return -1; 
/* 134 */     return this.mTmpBuf[0];
/*     */   }
/*     */   
/*     */   protected final InputStream getStream() {
/* 143 */     return this.mIn;
/*     */   }
/*     */   
/*     */   protected final int readBytes() throws IOException {
/* 152 */     this.mBytePtr = 0;
/* 153 */     this.mByteBufferEnd = 0;
/* 154 */     if (this.mIn != null) {
/* 155 */       int count = this.mIn.read(this.mByteBuffer, 0, this.mByteBuffer.length);
/* 156 */       if (count > 0)
/* 157 */         this.mByteBufferEnd = count; 
/* 159 */       return count;
/*     */     } 
/* 161 */     return -1;
/*     */   }
/*     */   
/*     */   protected final int readBytesAt(int offset) throws IOException {
/* 175 */     if (this.mIn != null) {
/* 176 */       int count = this.mIn.read(this.mByteBuffer, offset, this.mByteBuffer.length - offset);
/* 177 */       if (count > 0)
/* 178 */         this.mByteBufferEnd += count; 
/* 180 */       return count;
/*     */     } 
/* 182 */     return -1;
/*     */   }
/*     */   
/*     */   public final void freeBuffers() {
/* 196 */     if (this.mRecycleBuffer) {
/* 197 */       byte[] buf = this.mByteBuffer;
/* 198 */       if (buf != null) {
/* 199 */         this.mByteBuffer = null;
/* 200 */         if (this.mConfig != null)
/* 201 */           this.mConfig.freeFullBBuffer(buf); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void reportBounds(char[] cbuf, int start, int len) throws IOException {
/* 210 */     throw new ArrayIndexOutOfBoundsException("read(buf," + start + "," + len + "), cbuf[" + cbuf.length + "]");
/*     */   }
/*     */   
/*     */   protected void reportStrangeStream() throws IOException {
/* 216 */     throw new IOException("Strange I/O stream, returned 0 bytes on read");
/*     */   }
/*     */   
/*     */   protected void reportInvalidXml11(int value, int bytePos, int charPos) throws IOException {
/* 222 */     throw new CharConversionException("Invalid character 0x" + Integer.toHexString(value) + ", can only be included in xml 1.1 using character entities (at char #" + charPos + ", byte #" + bytePos + ")");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\BaseReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */