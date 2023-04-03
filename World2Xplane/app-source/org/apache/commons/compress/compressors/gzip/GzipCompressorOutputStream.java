/*    */ package org.apache.commons.compress.compressors.gzip;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.util.zip.GZIPOutputStream;
/*    */ import org.apache.commons.compress.compressors.CompressorOutputStream;
/*    */ 
/*    */ public class GzipCompressorOutputStream extends CompressorOutputStream {
/*    */   private final GZIPOutputStream out;
/*    */   
/*    */   public GzipCompressorOutputStream(OutputStream outputStream) throws IOException {
/* 32 */     this.out = new GZIPOutputStream(outputStream);
/*    */   }
/*    */   
/*    */   public void write(int b) throws IOException {
/* 38 */     this.out.write(b);
/*    */   }
/*    */   
/*    */   public void write(byte[] b) throws IOException {
/* 48 */     this.out.write(b);
/*    */   }
/*    */   
/*    */   public void write(byte[] b, int from, int length) throws IOException {
/* 58 */     this.out.write(b, from, length);
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 63 */     this.out.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\gzip\GzipCompressorOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */