/*    */ package ch.qos.logback.classic.net;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ import ch.qos.logback.core.net.AbstractSocketAppender;
/*    */ import ch.qos.logback.core.spi.PreSerializationTransformer;
/*    */ import java.net.InetAddress;
/*    */ 
/*    */ public class SocketAppender extends AbstractSocketAppender<ILoggingEvent> {
/* 36 */   private static final PreSerializationTransformer<ILoggingEvent> pst = new LoggingEventPreSerializationTransformer();
/*    */   
/*    */   private boolean includeCallerData = false;
/*    */   
/*    */   @Deprecated
/*    */   public SocketAppender(String host, int port) {
/* 49 */     super(host, port);
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public SocketAppender(InetAddress address, int port) {
/* 57 */     super(address.getHostAddress(), port);
/*    */   }
/*    */   
/*    */   protected void postProcessEvent(ILoggingEvent event) {
/* 62 */     if (this.includeCallerData)
/* 63 */       event.getCallerData(); 
/*    */   }
/*    */   
/*    */   public void setIncludeCallerData(boolean includeCallerData) {
/* 68 */     this.includeCallerData = includeCallerData;
/*    */   }
/*    */   
/*    */   public PreSerializationTransformer<ILoggingEvent> getPST() {
/* 72 */     return pst;
/*    */   }
/*    */   
/*    */   public SocketAppender() {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\net\SocketAppender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */