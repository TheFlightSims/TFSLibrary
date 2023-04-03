/*    */ package org.codehaus.stax2.io;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.Reader;
/*    */ 
/*    */ public class Stax2ByteArraySource extends Stax2BlockSource {
/*    */   private static final String DEFAULT_ENCODING = "UTF-8";
/*    */   
/*    */   final byte[] mBuffer;
/*    */   
/*    */   final int mStart;
/*    */   
/*    */   final int mLength;
/*    */   
/*    */   public Stax2ByteArraySource(byte[] buf, int start, int len) {
/* 27 */     this.mBuffer = buf;
/* 28 */     this.mStart = start;
/* 29 */     this.mLength = len;
/*    */   }
/*    */   
/*    */   public Reader constructReader() throws IOException {
/* 44 */     String enc = getEncoding();
/* 45 */     InputStream in = constructInputStream();
/* 46 */     if (enc == null || enc.length() == 0)
/* 50 */       enc = "UTF-8"; 
/* 52 */     return new InputStreamReader(in, enc);
/*    */   }
/*    */   
/*    */   public InputStream constructInputStream() throws IOException {
/* 58 */     return new ByteArrayInputStream(this.mBuffer, this.mStart, this.mLength);
/*    */   }
/*    */   
/*    */   public byte[] getBuffer() {
/* 68 */     return this.mBuffer;
/*    */   }
/*    */   
/*    */   public int getBufferStart() {
/* 72 */     return this.mStart;
/*    */   }
/*    */   
/*    */   public int getBufferLength() {
/* 76 */     return this.mLength;
/*    */   }
/*    */   
/*    */   public int getBufferEnd() {
/* 80 */     int end = this.mStart;
/* 81 */     if (this.mLength > 0)
/* 82 */       end += this.mLength; 
/* 84 */     return end;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\io\Stax2ByteArraySource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */