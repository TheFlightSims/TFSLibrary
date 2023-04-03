/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ public final class TextBuilder {
/*     */   private static final int MIN_LEN = 60;
/*     */   
/*     */   private static final int MAX_LEN = 120;
/*     */   
/*     */   private char[] mBuffer;
/*     */   
/*     */   private int mBufferLen;
/*     */   
/*     */   private String mResultString;
/*     */   
/*     */   public TextBuilder(int initialSize) {
/*  28 */     int charSize = initialSize << 4;
/*  29 */     if (charSize < 60) {
/*  30 */       charSize = 60;
/*  31 */     } else if (charSize > 120) {
/*  32 */       charSize = 120;
/*     */     } 
/*  34 */     this.mBuffer = new char[charSize];
/*     */   }
/*     */   
/*     */   public void reset() {
/*  42 */     this.mBufferLen = 0;
/*  43 */     this.mResultString = null;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  53 */     return (this.mBufferLen == 0);
/*     */   }
/*     */   
/*     */   public String getAllValues() {
/*  58 */     if (this.mResultString == null)
/*  59 */       this.mResultString = new String(this.mBuffer, 0, this.mBufferLen); 
/*  61 */     return this.mResultString;
/*     */   }
/*     */   
/*     */   public char[] getCharBuffer() {
/*  68 */     return this.mBuffer;
/*     */   }
/*     */   
/*     */   public int getCharSize() {
/*  72 */     return this.mBufferLen;
/*     */   }
/*     */   
/*     */   public void append(char c) {
/*  82 */     if (this.mBuffer.length == this.mBufferLen)
/*  83 */       resize(1); 
/*  85 */     this.mBuffer[this.mBufferLen++] = c;
/*     */   }
/*     */   
/*     */   public void append(char[] src, int start, int len) {
/*  89 */     if (len > this.mBuffer.length - this.mBufferLen)
/*  90 */       resize(len); 
/*  92 */     System.arraycopy(src, start, this.mBuffer, this.mBufferLen, len);
/*  93 */     this.mBufferLen += len;
/*     */   }
/*     */   
/*     */   public void setBufferSize(int newSize) {
/*  97 */     this.mBufferLen = newSize;
/*     */   }
/*     */   
/*     */   public char[] bufferFull(int needSpaceFor) {
/* 101 */     this.mBufferLen = this.mBuffer.length;
/* 102 */     resize(1);
/* 103 */     return this.mBuffer;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 113 */     return new String(this.mBuffer, 0, this.mBufferLen);
/*     */   }
/*     */   
/*     */   private void resize(int needSpaceFor) {
/* 123 */     char[] old = this.mBuffer;
/* 124 */     int oldLen = old.length;
/* 125 */     int addition = oldLen >> 1;
/* 126 */     needSpaceFor -= oldLen - this.mBufferLen;
/* 127 */     if (addition < needSpaceFor)
/* 128 */       addition = needSpaceFor; 
/* 130 */     this.mBuffer = new char[oldLen + addition];
/* 131 */     System.arraycopy(old, 0, this.mBuffer, 0, this.mBufferLen);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\TextBuilder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */