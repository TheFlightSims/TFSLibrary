/*    */ package org.apache.commons.compress.compressors.pack200;
/*    */ 
/*    */ import java.io.FilterOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ abstract class StreamBridge extends FilterOutputStream {
/*    */   private InputStream input;
/*    */   
/* 36 */   private final Object INPUT_LOCK = new Object();
/*    */   
/*    */   protected StreamBridge(OutputStream out) {
/* 39 */     super(out);
/*    */   }
/*    */   
/*    */   protected StreamBridge() {
/* 43 */     this(null);
/*    */   }
/*    */   
/*    */   InputStream getInput() throws IOException {
/* 50 */     synchronized (this.INPUT_LOCK) {
/* 51 */       if (this.input == null)
/* 52 */         this.input = getInputView(); 
/*    */     } 
/* 55 */     return this.input;
/*    */   }
/*    */   
/*    */   abstract InputStream getInputView() throws IOException;
/*    */   
/*    */   void stop() throws IOException {
/* 67 */     close();
/* 68 */     synchronized (this.INPUT_LOCK) {
/* 69 */       if (this.input != null) {
/* 70 */         this.input.close();
/* 71 */         this.input = null;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\pack200\StreamBridge.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */