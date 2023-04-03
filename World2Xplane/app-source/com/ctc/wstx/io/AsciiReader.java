/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import java.io.CharConversionException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public final class AsciiReader extends BaseReader {
/*     */   boolean mXml11 = false;
/*     */   
/*  38 */   int mCharCount = 0;
/*     */   
/*     */   public AsciiReader(ReaderConfig cfg, InputStream in, byte[] buf, int ptr, int len, boolean recycleBuffer) {
/*  49 */     super(cfg, in, buf, ptr, len, recycleBuffer);
/*     */   }
/*     */   
/*     */   public void setXmlCompliancy(int xmlVersion) {
/*  54 */     this.mXml11 = (xmlVersion == 272);
/*     */   }
/*     */   
/*     */   public int read(char[] cbuf, int start, int len) throws IOException {
/*  67 */     if (start < 0 || start + len > cbuf.length)
/*  68 */       reportBounds(cbuf, start, len); 
/*  71 */     if (this.mByteBuffer == null)
/*  72 */       return -1; 
/*  74 */     if (len < 1)
/*  75 */       return 0; 
/*  79 */     int avail = this.mByteBufferEnd - this.mBytePtr;
/*  80 */     if (avail <= 0) {
/*  81 */       this.mCharCount += this.mByteBufferEnd;
/*  83 */       int count = readBytes();
/*  84 */       if (count <= 0) {
/*  85 */         if (count == 0)
/*  86 */           reportStrangeStream(); 
/*  91 */         freeBuffers();
/*  92 */         return -1;
/*     */       } 
/*  94 */       avail = count;
/*     */     } 
/*  99 */     if (len > avail)
/* 100 */       len = avail; 
/* 102 */     int i = this.mBytePtr;
/* 103 */     int last = i + len;
/* 105 */     while (i < last) {
/* 106 */       char c = (char)this.mByteBuffer[i++];
/* 107 */       if (c >= '')
/* 108 */         if (c > '') {
/* 109 */           reportInvalidAscii(c);
/* 111 */         } else if (this.mXml11) {
/* 112 */           int pos = this.mCharCount + this.mBytePtr;
/* 113 */           reportInvalidXml11(c, pos, pos);
/*     */         }  
/* 117 */       cbuf[start++] = c;
/*     */     } 
/* 120 */     this.mBytePtr = last;
/* 121 */     return len;
/*     */   }
/*     */   
/*     */   private void reportInvalidAscii(char c) throws IOException {
/* 133 */     throw new CharConversionException("Invalid ascii byte; value above 7-bit ascii range (" + c + "; at pos #" + (this.mCharCount + this.mBytePtr) + ")");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\AsciiReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */