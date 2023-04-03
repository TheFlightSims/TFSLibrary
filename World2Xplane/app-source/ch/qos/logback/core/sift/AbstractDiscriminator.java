/*    */ package ch.qos.logback.core.sift;
/*    */ 
/*    */ import ch.qos.logback.core.spi.ContextAwareBase;
/*    */ 
/*    */ public abstract class AbstractDiscriminator<E> extends ContextAwareBase implements Discriminator<E> {
/*    */   protected boolean started;
/*    */   
/*    */   public void start() {
/* 16 */     this.started = true;
/*    */   }
/*    */   
/*    */   public void stop() {
/* 20 */     this.started = false;
/*    */   }
/*    */   
/*    */   public boolean isStarted() {
/* 24 */     return this.started;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\sift\AbstractDiscriminator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */