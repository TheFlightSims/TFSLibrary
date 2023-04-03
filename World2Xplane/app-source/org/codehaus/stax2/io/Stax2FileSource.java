/*    */ package org.codehaus.stax2.io;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.Reader;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ 
/*    */ public class Stax2FileSource extends Stax2ReferentialSource {
/*    */   final File mFile;
/*    */   
/*    */   public Stax2FileSource(File f) {
/* 16 */     this.mFile = f;
/*    */   }
/*    */   
/*    */   public URL getReference() {
/*    */     try {
/* 37 */       return this.mFile.toURL();
/* 38 */     } catch (MalformedURLException e) {
/* 43 */       throw new IllegalArgumentException("(was " + e.getClass() + ") Could not convert File '" + this.mFile.getPath() + "' to URL: " + e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public Reader constructReader() throws IOException {
/* 50 */     String enc = getEncoding();
/* 51 */     if (enc != null && enc.length() > 0)
/* 52 */       return new InputStreamReader(constructInputStream(), enc); 
/* 55 */     return new FileReader(this.mFile);
/*    */   }
/*    */   
/*    */   public InputStream constructInputStream() throws IOException {
/* 61 */     return new FileInputStream(this.mFile);
/*    */   }
/*    */   
/*    */   public File getFile() {
/* 71 */     return this.mFile;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\io\Stax2FileSource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */