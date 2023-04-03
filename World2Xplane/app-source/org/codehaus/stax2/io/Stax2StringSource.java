/*    */ package org.codehaus.stax2.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.Reader;
/*    */ import java.io.StringReader;
/*    */ 
/*    */ public class Stax2StringSource extends Stax2BlockSource {
/*    */   final String mText;
/*    */   
/*    */   public Stax2StringSource(String text) {
/* 15 */     this.mText = text;
/*    */   }
/*    */   
/*    */   public Reader constructReader() throws IOException {
/* 27 */     return new StringReader(this.mText);
/*    */   }
/*    */   
/*    */   public InputStream constructInputStream() throws IOException {
/* 36 */     return null;
/*    */   }
/*    */   
/*    */   public String getText() {
/* 46 */     return this.mText;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\io\Stax2StringSource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */