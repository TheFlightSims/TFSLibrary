/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public final class ISOLatinReader extends BaseReader {
/*     */   boolean mXml11 = false;
/*     */   
/*  38 */   int mByteCount = 0;
/*     */   
/*     */   public ISOLatinReader(ReaderConfig cfg, InputStream in, byte[] buf, int ptr, int len, boolean recycleBuffer) {
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
/*  81 */       this.mByteCount += this.mByteBufferEnd;
/*  83 */       int count = readBytes();
/*  84 */       if (count <= 0) {
/*  85 */         if (count == 0)
/*  86 */           reportStrangeStream(); 
/*  91 */         freeBuffers();
/*  92 */         return -1;
/*     */       } 
/*  94 */       avail = count;
/*     */     } 
/* 101 */     if (len > avail)
/* 102 */       len = avail; 
/* 104 */     int i = this.mBytePtr;
/* 105 */     int last = i + len;
/* 107 */     if (this.mXml11) {
/* 108 */       while (i < last) {
/* 109 */         char c = (char)(this.mByteBuffer[i++] & 0xFF);
/* 110 */         if (c >= '' && 
/* 111 */           c <= '')
/* 112 */           if (c == '') {
/* 113 */             c = '\n';
/* 114 */           } else if (c >= '') {
/* 115 */             int pos = this.mByteCount + i;
/* 116 */             reportInvalidXml11(c, pos, pos);
/*     */           }  
/* 120 */         cbuf[start++] = c;
/*     */       } 
/*     */     } else {
/* 124 */       while (i < last)
/* 125 */         cbuf[start++] = (char)(this.mByteBuffer[i++] & 0xFF); 
/*     */     } 
/* 129 */     this.mBytePtr = last;
/* 130 */     return len;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\ISOLatinReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */