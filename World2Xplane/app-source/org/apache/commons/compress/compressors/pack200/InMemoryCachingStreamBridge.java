/*    */ package org.apache.commons.compress.compressors.pack200;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ class InMemoryCachingStreamBridge extends StreamBridge {
/*    */   InMemoryCachingStreamBridge() {
/* 34 */     super(new ByteArrayOutputStream());
/*    */   }
/*    */   
/*    */   InputStream getInputView() throws IOException {
/* 42 */     return new ByteArrayInputStream(((ByteArrayOutputStream)this.out).toByteArray());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\pack200\InMemoryCachingStreamBridge.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */