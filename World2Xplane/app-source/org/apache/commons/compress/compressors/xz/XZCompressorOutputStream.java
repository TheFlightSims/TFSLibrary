/*    */ package org.apache.commons.compress.compressors.xz;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.commons.compress.compressors.CompressorOutputStream;
/*    */ import org.tukaani.xz.FilterOptions;
/*    */ import org.tukaani.xz.LZMA2Options;
/*    */ import org.tukaani.xz.XZOutputStream;
/*    */ 
/*    */ public class XZCompressorOutputStream extends CompressorOutputStream {
/*    */   private final XZOutputStream out;
/*    */   
/*    */   public XZCompressorOutputStream(OutputStream outputStream) throws IOException {
/* 41 */     this.out = new XZOutputStream(outputStream, (FilterOptions)new LZMA2Options());
/*    */   }
/*    */   
/*    */   public XZCompressorOutputStream(OutputStream outputStream, int preset) throws IOException {
/* 59 */     this.out = new XZOutputStream(outputStream, (FilterOptions)new LZMA2Options(preset));
/*    */   }
/*    */   
/*    */   public void write(int b) throws IOException {
/* 65 */     this.out.write(b);
/*    */   }
/*    */   
/*    */   public void write(byte[] buf, int off, int len) throws IOException {
/* 71 */     this.out.write(buf, off, len);
/*    */   }
/*    */   
/*    */   public void flush() throws IOException {
/* 82 */     this.out.flush();
/*    */   }
/*    */   
/*    */   public void finish() throws IOException {
/* 90 */     this.out.finish();
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 96 */     this.out.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\xz\XZCompressorOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */