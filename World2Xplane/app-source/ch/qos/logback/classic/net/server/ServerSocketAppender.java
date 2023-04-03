/*    */ package ch.qos.logback.classic.net.server;
/*    */ 
/*    */ import ch.qos.logback.classic.net.LoggingEventPreSerializationTransformer;
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ import ch.qos.logback.core.net.server.AbstractServerSocketAppender;
/*    */ import ch.qos.logback.core.spi.PreSerializationTransformer;
/*    */ 
/*    */ public class ServerSocketAppender extends AbstractServerSocketAppender<ILoggingEvent> {
/* 31 */   private static final PreSerializationTransformer<ILoggingEvent> pst = (PreSerializationTransformer<ILoggingEvent>)new LoggingEventPreSerializationTransformer();
/*    */   
/*    */   private boolean includeCallerData;
/*    */   
/*    */   protected void postProcessEvent(ILoggingEvent event) {
/* 38 */     if (isIncludeCallerData())
/* 39 */       event.getCallerData(); 
/*    */   }
/*    */   
/*    */   protected PreSerializationTransformer<ILoggingEvent> getPST() {
/* 45 */     return pst;
/*    */   }
/*    */   
/*    */   public boolean isIncludeCallerData() {
/* 49 */     return this.includeCallerData;
/*    */   }
/*    */   
/*    */   public void setIncludeCallerData(boolean includeCallerData) {
/* 53 */     this.includeCallerData = includeCallerData;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\net\server\ServerSocketAppender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */