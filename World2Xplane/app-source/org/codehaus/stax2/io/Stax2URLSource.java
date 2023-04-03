/*    */ package org.codehaus.stax2.io;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.Reader;
/*    */ import java.net.URL;
/*    */ 
/*    */ public class Stax2URLSource extends Stax2ReferentialSource {
/*    */   final URL mURL;
/*    */   
/*    */   public Stax2URLSource(URL url) {
/* 16 */     this.mURL = url;
/*    */   }
/*    */   
/*    */   public URL getReference() {
/* 32 */     return this.mURL;
/*    */   }
/*    */   
/*    */   public Reader constructReader() throws IOException {
/* 38 */     String enc = getEncoding();
/* 39 */     if (enc != null && enc.length() > 0)
/* 40 */       return new InputStreamReader(constructInputStream(), enc); 
/* 43 */     return new InputStreamReader(constructInputStream());
/*    */   }
/*    */   
/*    */   public InputStream constructInputStream() throws IOException {
/* 52 */     if ("file".equals(this.mURL.getProtocol()))
/* 53 */       return new FileInputStream(this.mURL.getPath()); 
/* 55 */     return this.mURL.openStream();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\io\Stax2URLSource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */