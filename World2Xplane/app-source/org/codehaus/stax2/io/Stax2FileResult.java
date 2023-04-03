/*    */ package org.codehaus.stax2.io;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.Writer;
/*    */ 
/*    */ public class Stax2FileResult extends Stax2ReferentialResult {
/*    */   final File mFile;
/*    */   
/*    */   public Stax2FileResult(File f) {
/* 15 */     this.mFile = f;
/*    */   }
/*    */   
/*    */   public Writer constructWriter() throws IOException {
/* 27 */     String enc = getEncoding();
/* 28 */     if (enc != null && enc.length() > 0)
/* 29 */       return new OutputStreamWriter(constructOutputStream(), enc); 
/* 32 */     return new FileWriter(this.mFile);
/*    */   }
/*    */   
/*    */   public OutputStream constructOutputStream() throws IOException {
/* 38 */     return new FileOutputStream(this.mFile);
/*    */   }
/*    */   
/*    */   public File getFile() {
/* 48 */     return this.mFile;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\io\Stax2FileResult.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */