/*    */ package ch.qos.logback.classic.net.server;
/*    */ 
/*    */ import ch.qos.logback.classic.LoggerContext;
/*    */ import ch.qos.logback.core.net.server.Client;
/*    */ import ch.qos.logback.core.net.server.ConcurrentServerRunner;
/*    */ import ch.qos.logback.core.net.server.ServerListener;
/*    */ import java.util.concurrent.Executor;
/*    */ 
/*    */ class RemoteAppenderServerRunner extends ConcurrentServerRunner<RemoteAppenderClient> {
/*    */   public RemoteAppenderServerRunner(ServerListener<RemoteAppenderClient> listener, Executor executor) {
/* 41 */     super(listener, executor);
/*    */   }
/*    */   
/*    */   protected boolean configureClient(RemoteAppenderClient client) {
/* 49 */     client.setLoggerContext((LoggerContext)getContext());
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\net\server\RemoteAppenderServerRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */