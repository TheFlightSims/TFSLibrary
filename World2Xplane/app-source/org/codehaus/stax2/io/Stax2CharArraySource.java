/*    */ package org.codehaus.stax2.io;
/*    */ 
/*    */ import java.io.CharArrayReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.Reader;
/*    */ 
/*    */ public class Stax2CharArraySource extends Stax2BlockSource {
/*    */   final char[] mBuffer;
/*    */   
/*    */   final int mStart;
/*    */   
/*    */   final int mLength;
/*    */   
/*    */   public Stax2CharArraySource(char[] buf, int start, int len) {
/* 25 */     this.mBuffer = buf;
/* 26 */     this.mStart = start;
/* 27 */     this.mLength = len;
/*    */   }
/*    */   
/*    */   public Reader constructReader() throws IOException {
/* 39 */     return new CharArrayReader(this.mBuffer, this.mStart, this.mLength);
/*    */   }
/*    */   
/*    */   public InputStream constructInputStream() throws IOException {
/* 48 */     return null;
/*    */   }
/*    */   
/*    */   public char[] getBuffer() {
/* 58 */     return this.mBuffer;
/*    */   }
/*    */   
/*    */   public int getBufferStart() {
/* 62 */     return this.mStart;
/*    */   }
/*    */   
/*    */   public int getBufferLength() {
/* 66 */     return this.mLength;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\io\Stax2CharArraySource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */