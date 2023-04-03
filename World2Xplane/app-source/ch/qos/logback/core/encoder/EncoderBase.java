/*    */ package ch.qos.logback.core.encoder;
/*    */ 
/*    */ import ch.qos.logback.core.spi.ContextAwareBase;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public abstract class EncoderBase<E> extends ContextAwareBase implements Encoder<E> {
/*    */   protected boolean started;
/*    */   
/*    */   protected OutputStream outputStream;
/*    */   
/*    */   public void init(OutputStream os) throws IOException {
/* 28 */     this.outputStream = os;
/*    */   }
/*    */   
/*    */   public boolean isStarted() {
/* 32 */     return this.started;
/*    */   }
/*    */   
/*    */   public void start() {
/* 36 */     this.started = true;
/*    */   }
/*    */   
/*    */   public void stop() {
/* 40 */     this.started = false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\encoder\EncoderBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */