/*     */ package ch.qos.logback.classic.net;
/*     */ 
/*     */ import ch.qos.logback.classic.LoggerContext;
/*     */ import ch.qos.logback.classic.joran.JoranConfigurator;
/*     */ import ch.qos.logback.core.Context;
/*     */ import ch.qos.logback.core.joran.spi.JoranException;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import javax.net.ServerSocketFactory;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class SimpleSocketServer extends Thread {
/*  53 */   Logger logger = LoggerFactory.getLogger(SimpleSocketServer.class);
/*     */   
/*     */   private final int port;
/*     */   
/*     */   private final LoggerContext lc;
/*     */   
/*     */   private boolean closed = false;
/*     */   
/*     */   private ServerSocket serverSocket;
/*     */   
/*  59 */   private List<SocketNode> socketNodeList = new ArrayList<SocketNode>();
/*     */   
/*     */   private CountDownLatch latch;
/*     */   
/*     */   public static void main(String[] argv) throws Exception {
/*  65 */     doMain(SimpleSocketServer.class, argv);
/*     */   }
/*     */   
/*     */   protected static void doMain(Class<? extends SimpleSocketServer> serverClass, String[] argv) throws Exception {
/*  70 */     int port = -1;
/*  71 */     if (argv.length == 2) {
/*  72 */       port = parsePortNumber(argv[0]);
/*     */     } else {
/*  74 */       usage("Wrong number of arguments.");
/*     */     } 
/*  77 */     String configFile = argv[1];
/*  78 */     LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
/*  79 */     configureLC(lc, configFile);
/*  81 */     SimpleSocketServer sss = createServer(serverClass, lc, port);
/*  82 */     sss.start();
/*     */   }
/*     */   
/*     */   private static SimpleSocketServer createServer(Class<? extends SimpleSocketServer> serverClass, LoggerContext lc, int port) throws Exception {
/*  89 */     Constructor<? extends SimpleSocketServer> constructor = serverClass.getConstructor(new Class[] { LoggerContext.class, int.class });
/*  92 */     return constructor.newInstance(new Object[] { lc, Integer.valueOf(port) });
/*     */   }
/*     */   
/*     */   public SimpleSocketServer(LoggerContext lc, int port) {
/*  96 */     this.lc = lc;
/*  97 */     this.port = port;
/*     */   }
/*     */   
/*     */   public void run() {
/*     */     try {
/* 103 */       this.logger.info("Listening on port " + this.port);
/* 104 */       this.serverSocket = getServerSocketFactory().createServerSocket(this.port);
/* 105 */       while (!this.closed) {
/* 106 */         this.logger.info("Waiting to accept a new client.");
/* 107 */         signalAlmostReadiness();
/* 108 */         Socket socket = this.serverSocket.accept();
/* 109 */         this.logger.info("Connected to client at " + socket.getInetAddress());
/* 110 */         this.logger.info("Starting new socket node.");
/* 111 */         SocketNode newSocketNode = new SocketNode(this, socket, this.lc);
/* 112 */         synchronized (this.socketNodeList) {
/* 113 */           this.socketNodeList.add(newSocketNode);
/*     */         } 
/* 115 */         (new Thread(newSocketNode)).start();
/*     */       } 
/* 117 */     } catch (Exception e) {
/* 118 */       if (this.closed) {
/* 119 */         this.logger.info("Exception in run method for a closed server. This is normal.");
/*     */       } else {
/* 121 */         this.logger.error("Unexpected failure in run method", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ServerSocketFactory getServerSocketFactory() {
/* 132 */     return ServerSocketFactory.getDefault();
/*     */   }
/*     */   
/*     */   void signalAlmostReadiness() {
/* 140 */     if (this.latch != null && this.latch.getCount() != 0L)
/* 142 */       this.latch.countDown(); 
/*     */   }
/*     */   
/*     */   void setLatch(CountDownLatch latch) {
/* 151 */     this.latch = latch;
/*     */   }
/*     */   
/*     */   public CountDownLatch getLatch() {
/* 157 */     return this.latch;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 160 */     return this.closed;
/*     */   }
/*     */   
/*     */   public void close() {
/* 164 */     this.closed = true;
/* 165 */     if (this.serverSocket != null)
/*     */       try {
/* 167 */         this.serverSocket.close();
/* 168 */       } catch (IOException e) {
/* 169 */         this.logger.error("Failed to close serverSocket", e);
/*     */       } finally {
/* 171 */         this.serverSocket = null;
/*     */       }  
/* 175 */     this.logger.info("closing this server");
/* 176 */     synchronized (this.socketNodeList) {
/* 177 */       for (SocketNode sn : this.socketNodeList)
/* 178 */         sn.close(); 
/*     */     } 
/* 181 */     if (this.socketNodeList.size() != 0)
/* 182 */       this.logger.warn("Was expecting a 0-sized socketNodeList after server shutdown"); 
/*     */   }
/*     */   
/*     */   public void socketNodeClosing(SocketNode sn) {
/* 188 */     this.logger.debug("Removing {}", sn);
/* 193 */     synchronized (this.socketNodeList) {
/* 194 */       this.socketNodeList.remove(sn);
/*     */     } 
/*     */   }
/*     */   
/*     */   static void usage(String msg) {
/* 199 */     System.err.println(msg);
/* 200 */     System.err.println("Usage: java " + SimpleSocketServer.class.getName() + " port configFile");
/* 202 */     System.exit(1);
/*     */   }
/*     */   
/*     */   static int parsePortNumber(String portStr) {
/*     */     try {
/* 207 */       return Integer.parseInt(portStr);
/* 208 */     } catch (NumberFormatException e) {
/* 209 */       e.printStackTrace();
/* 210 */       usage("Could not interpret port number [" + portStr + "].");
/* 212 */       return -1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void configureLC(LoggerContext lc, String configFile) throws JoranException {
/* 218 */     JoranConfigurator configurator = new JoranConfigurator();
/* 219 */     lc.reset();
/* 220 */     configurator.setContext((Context)lc);
/* 221 */     configurator.doConfigure(configFile);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\net\SimpleSocketServer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */