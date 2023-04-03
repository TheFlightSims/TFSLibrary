/*    */ package ch.qos.logback.core.net.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.ServerSocket;
/*    */ import java.net.Socket;
/*    */ 
/*    */ class RemoteReceiverServerListener extends ServerSocketListener<RemoteReceiverClient> {
/*    */   public RemoteReceiverServerListener(ServerSocket serverSocket) {
/* 35 */     super(serverSocket);
/*    */   }
/*    */   
/*    */   protected RemoteReceiverClient createClient(String id, Socket socket) throws IOException {
/* 44 */     return new RemoteReceiverStreamClient(id, socket);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\server\RemoteReceiverServerListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */