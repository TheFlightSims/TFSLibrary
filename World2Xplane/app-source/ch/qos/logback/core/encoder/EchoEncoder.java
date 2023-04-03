/*    */ package ch.qos.logback.core.encoder;
/*    */ 
/*    */ import ch.qos.logback.core.CoreConstants;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public class EchoEncoder<E> extends EncoderBase<E> {
/*    */   String fileHeader;
/*    */   
/*    */   String fileFooter;
/*    */   
/*    */   public void doEncode(E event) throws IOException {
/* 27 */     String val = (new StringBuilder()).append(event).append(CoreConstants.LINE_SEPARATOR).toString();
/* 28 */     this.outputStream.write(val.getBytes());
/* 30 */     this.outputStream.flush();
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 34 */     if (this.fileFooter == null)
/*    */       return; 
/* 37 */     this.outputStream.write(this.fileFooter.getBytes());
/*    */   }
/*    */   
/*    */   public void init(OutputStream os) throws IOException {
/* 41 */     super.init(os);
/* 42 */     if (this.fileHeader == null)
/*    */       return; 
/* 45 */     this.outputStream.write(this.fileHeader.getBytes());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\encoder\EchoEncoder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */