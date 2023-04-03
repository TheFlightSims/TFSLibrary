/*    */ package ch.qos.logback.core.net.server;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.concurrent.ArrayBlockingQueue;
/*    */ import java.util.concurrent.Executor;
/*    */ 
/*    */ class RemoteReceiverServerRunner extends ConcurrentServerRunner<RemoteReceiverClient> {
/*    */   private final int clientQueueSize;
/*    */   
/*    */   public RemoteReceiverServerRunner(ServerListener<RemoteReceiverClient> listener, Executor executor, int clientQueueSize) {
/* 43 */     super(listener, executor);
/* 44 */     this.clientQueueSize = clientQueueSize;
/*    */   }
/*    */   
/*    */   protected boolean configureClient(RemoteReceiverClient client) {
/* 52 */     client.setContext(getContext());
/* 53 */     client.setQueue(new ArrayBlockingQueue<Serializable>(this.clientQueueSize));
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\server\RemoteReceiverServerRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */