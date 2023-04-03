/*    */ package org.apache.commons.compress.compressors.pack200;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ class TempFileCachingStreamBridge extends StreamBridge {
/*    */   private final File f;
/*    */   
/*    */   TempFileCachingStreamBridge() throws IOException {
/* 37 */     this.f = File.createTempFile("commons-compress", "packtemp");
/* 38 */     this.f.deleteOnExit();
/* 39 */     this.out = new FileOutputStream(this.f);
/*    */   }
/*    */   
/*    */   InputStream getInputView() throws IOException {
/* 47 */     this.out.close();
/* 48 */     return new FileInputStream(this.f) {
/*    */         public void close() throws IOException {
/* 51 */           super.close();
/* 52 */           TempFileCachingStreamBridge.this.f.delete();
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\pack200\TempFileCachingStreamBridge.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */