/*     */ package ch.qos.logback.core.net;
/*     */ 
/*     */ import ch.qos.logback.core.AppenderBase;
/*     */ import ch.qos.logback.core.spi.PreSerializationTransformer;
/*     */ import ch.qos.logback.core.util.CloseUtil;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.net.InetAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.concurrent.ArrayBlockingQueue;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.RejectedExecutionException;
/*     */ import java.util.concurrent.SynchronousQueue;
/*     */ import javax.net.SocketFactory;
/*     */ 
/*     */ public abstract class AbstractSocketAppender<E> extends AppenderBase<E> implements Runnable, SocketConnector.ExceptionHandler {
/*     */   public static final int DEFAULT_PORT = 4560;
/*     */   
/*     */   public static final int DEFAULT_RECONNECTION_DELAY = 30000;
/*     */   
/*     */   public static final int DEFAULT_QUEUE_SIZE = 0;
/*     */   
/*     */   private static final int DEFAULT_ACCEPT_CONNECTION_DELAY = 5000;
/*     */   
/*     */   private String remoteHost;
/*     */   
/*  74 */   private int port = 4560;
/*     */   
/*     */   private InetAddress address;
/*     */   
/*  76 */   private int reconnectionDelay = 30000;
/*     */   
/*  77 */   private int queueSize = 0;
/*     */   
/*  78 */   private int acceptConnectionTimeout = 5000;
/*     */   
/*     */   private BlockingQueue<E> queue;
/*     */   
/*     */   private String peerId;
/*     */   
/*     */   private Future<?> task;
/*     */   
/*     */   private Future<Socket> connectorTask;
/*     */   
/*     */   private volatile Socket socket;
/*     */   
/*     */   protected AbstractSocketAppender() {}
/*     */   
/*     */   @Deprecated
/*     */   protected AbstractSocketAppender(String remoteHost, int port) {
/* 107 */     this.remoteHost = remoteHost;
/* 108 */     this.port = port;
/*     */   }
/*     */   
/*     */   public void start() {
/* 115 */     if (isStarted())
/*     */       return; 
/* 116 */     int errorCount = 0;
/* 117 */     if (this.port <= 0) {
/* 118 */       errorCount++;
/* 119 */       addError("No port was configured for appender" + this.name + " For more information, please visit http://logback.qos.ch/codes.html#socket_no_port");
/*     */     } 
/* 124 */     if (this.remoteHost == null) {
/* 125 */       errorCount++;
/* 126 */       addError("No remote host was configured for appender" + this.name + " For more information, please visit http://logback.qos.ch/codes.html#socket_no_host");
/*     */     } 
/* 131 */     if (this.queueSize < 0) {
/* 132 */       errorCount++;
/* 133 */       addError("Queue size must be non-negative");
/*     */     } 
/* 136 */     if (errorCount == 0)
/*     */       try {
/* 138 */         this.address = InetAddress.getByName(this.remoteHost);
/* 139 */       } catch (UnknownHostException ex) {
/* 140 */         addError("unknown host: " + this.remoteHost);
/* 141 */         errorCount++;
/*     */       }  
/* 145 */     if (errorCount == 0) {
/* 146 */       this.queue = newBlockingQueue(this.queueSize);
/* 147 */       this.peerId = "remote peer " + this.remoteHost + ":" + this.port + ": ";
/* 148 */       this.task = getContext().getExecutorService().submit(this);
/* 149 */       super.start();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void stop() {
/* 158 */     if (!isStarted())
/*     */       return; 
/* 159 */     CloseUtil.closeQuietly(this.socket);
/* 160 */     this.task.cancel(true);
/* 161 */     if (this.connectorTask != null)
/* 162 */       this.connectorTask.cancel(true); 
/* 163 */     super.stop();
/*     */   }
/*     */   
/*     */   protected void append(E event) {
/* 171 */     if (event == null || !isStarted())
/*     */       return; 
/* 172 */     this.queue.offer(event);
/*     */   }
/*     */   
/*     */   public final void run() {
/*     */     try {
/* 180 */       while (!Thread.currentThread().isInterrupted()) {
/* 181 */         SocketConnector connector = createConnector(this.address, this.port, 0, this.reconnectionDelay);
/* 184 */         this.connectorTask = activateConnector(connector);
/* 185 */         if (this.connectorTask == null)
/*     */           break; 
/* 188 */         this.socket = waitForConnectorToReturnASocket();
/* 189 */         if (this.socket == null)
/*     */           break; 
/* 191 */         dispatchEvents();
/*     */       } 
/* 193 */     } catch (InterruptedException ex) {}
/* 196 */     addInfo("shutting down");
/*     */   }
/*     */   
/*     */   private SocketConnector createConnector(InetAddress address, int port, int initialDelay, int retryDelay) {
/* 201 */     SocketConnector connector = newConnector(address, port, initialDelay, retryDelay);
/* 203 */     connector.setExceptionHandler(this);
/* 204 */     connector.setSocketFactory(getSocketFactory());
/* 205 */     return connector;
/*     */   }
/*     */   
/*     */   private Future<Socket> activateConnector(SocketConnector connector) {
/*     */     try {
/* 210 */       return getContext().getExecutorService().submit(connector);
/* 211 */     } catch (RejectedExecutionException ex) {
/* 212 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Socket waitForConnectorToReturnASocket() throws InterruptedException {
/*     */     try {
/* 218 */       Socket s = this.connectorTask.get();
/* 219 */       this.connectorTask = null;
/* 220 */       return s;
/* 221 */     } catch (ExecutionException e) {
/* 222 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void dispatchEvents() throws InterruptedException {
/*     */     try {
/* 228 */       this.socket.setSoTimeout(this.acceptConnectionTimeout);
/* 229 */       ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
/* 230 */       this.socket.setSoTimeout(0);
/* 231 */       addInfo(this.peerId + "connection established");
/* 232 */       int counter = 0;
/*     */       while (true) {
/* 234 */         E event = this.queue.take();
/* 235 */         postProcessEvent(event);
/* 236 */         Serializable serEvent = getPST().transform(event);
/* 237 */         oos.writeObject(serEvent);
/* 238 */         oos.flush();
/* 239 */         if (++counter >= 70) {
/* 242 */           oos.reset();
/* 243 */           counter = 0;
/*     */         } 
/*     */       } 
/* 246 */     } catch (IOException ex) {
/* 247 */       addInfo(this.peerId + "connection failed: " + ex);
/*     */     } finally {
/* 249 */       CloseUtil.closeQuietly(this.socket);
/* 250 */       this.socket = null;
/* 251 */       addInfo(this.peerId + "connection closed");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void connectionFailed(SocketConnector connector, Exception ex) {
/* 259 */     if (ex instanceof InterruptedException) {
/* 260 */       addInfo("connector interrupted");
/* 261 */     } else if (ex instanceof java.net.ConnectException) {
/* 262 */       addInfo(this.peerId + "connection refused");
/*     */     } else {
/* 264 */       addInfo(this.peerId + ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected SocketConnector newConnector(InetAddress address, int port, int initialDelay, int retryDelay) {
/* 285 */     return new DefaultSocketConnector(address, port, initialDelay, retryDelay);
/*     */   }
/*     */   
/*     */   protected SocketFactory getSocketFactory() {
/* 294 */     return SocketFactory.getDefault();
/*     */   }
/*     */   
/*     */   BlockingQueue<E> newBlockingQueue(int queueSize) {
/* 311 */     return (queueSize <= 0) ? new SynchronousQueue<E>() : new ArrayBlockingQueue<E>(queueSize);
/*     */   }
/*     */   
/*     */   protected abstract void postProcessEvent(E paramE);
/*     */   
/*     */   protected abstract PreSerializationTransformer<E> getPST();
/*     */   
/*     */   @Deprecated
/*     */   protected static InetAddress getAddressByName(String host) {
/*     */     try {
/* 337 */       return InetAddress.getByName(host);
/* 338 */     } catch (Exception e) {
/* 340 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setRemoteHost(String host) {
/* 348 */     this.remoteHost = host;
/*     */   }
/*     */   
/*     */   public String getRemoteHost() {
/* 355 */     return this.remoteHost;
/*     */   }
/*     */   
/*     */   public void setPort(int port) {
/* 363 */     this.port = port;
/*     */   }
/*     */   
/*     */   public int getPort() {
/* 370 */     return this.port;
/*     */   }
/*     */   
/*     */   public void setReconnectionDelay(int delay) {
/* 383 */     this.reconnectionDelay = delay;
/*     */   }
/*     */   
/*     */   public int getReconnectionDelay() {
/* 390 */     return this.reconnectionDelay;
/*     */   }
/*     */   
/*     */   public void setQueueSize(int queueSize) {
/* 408 */     this.queueSize = queueSize;
/*     */   }
/*     */   
/*     */   public int getQueueSize() {
/* 415 */     return this.queueSize;
/*     */   }
/*     */   
/*     */   void setAcceptConnectionTimeout(int acceptConnectionTimeout) {
/* 428 */     this.acceptConnectionTimeout = acceptConnectionTimeout;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\AbstractSocketAppender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */