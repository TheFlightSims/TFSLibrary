/*    */ package ch.qos.logback.classic.net;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ import ch.qos.logback.core.net.AbstractSSLSocketAppender;
/*    */ import ch.qos.logback.core.spi.PreSerializationTransformer;
/*    */ import java.net.InetAddress;
/*    */ 
/*    */ public class SSLSocketAppender extends AbstractSSLSocketAppender<ILoggingEvent> {
/* 32 */   private final PreSerializationTransformer<ILoggingEvent> pst = new LoggingEventPreSerializationTransformer();
/*    */   
/*    */   private boolean includeCallerData;
/*    */   
/*    */   @Deprecated
/*    */   public SSLSocketAppender(String host, int port) {
/* 45 */     super(host, port);
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public SSLSocketAppender(InetAddress address, int port) {
/* 53 */     super(address.getHostAddress(), port);
/*    */   }
/*    */   
/*    */   protected void postProcessEvent(ILoggingEvent event) {
/* 58 */     if (this.includeCallerData)
/* 59 */       event.getCallerData(); 
/*    */   }
/*    */   
/*    */   public void setIncludeCallerData(boolean includeCallerData) {
/* 64 */     this.includeCallerData = includeCallerData;
/*    */   }
/*    */   
/*    */   public PreSerializationTransformer<ILoggingEvent> getPST() {
/* 68 */     return this.pst;
/*    */   }
/*    */   
/*    */   public SSLSocketAppender() {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\net\SSLSocketAppender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */