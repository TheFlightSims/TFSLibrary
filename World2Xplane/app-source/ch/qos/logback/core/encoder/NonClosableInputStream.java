/*    */ package ch.qos.logback.core.encoder;
/*    */ 
/*    */ import java.io.FilterInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class NonClosableInputStream extends FilterInputStream {
/*    */   NonClosableInputStream(InputStream is) {
/* 23 */     super(is);
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */   
/*    */   public void realClose() throws IOException {
/* 35 */     super.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\encoder\NonClosableInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */