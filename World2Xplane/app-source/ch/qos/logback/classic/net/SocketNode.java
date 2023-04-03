/*     */ package ch.qos.logback.classic.net;
/*     */ 
/*     */ import ch.qos.logback.classic.Logger;
/*     */ import ch.qos.logback.classic.LoggerContext;
/*     */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketAddress;
/*     */ import java.net.SocketException;
/*     */ 
/*     */ public class SocketNode implements Runnable {
/*     */   Socket socket;
/*     */   
/*     */   LoggerContext context;
/*     */   
/*     */   ObjectInputStream ois;
/*     */   
/*     */   SocketAddress remoteSocketAddress;
/*     */   
/*     */   Logger logger;
/*     */   
/*     */   boolean closed = false;
/*     */   
/*     */   SimpleSocketServer socketServer;
/*     */   
/*     */   public SocketNode(SimpleSocketServer socketServer, Socket socket, LoggerContext context) {
/*  55 */     this.socketServer = socketServer;
/*  56 */     this.socket = socket;
/*  57 */     this.remoteSocketAddress = socket.getRemoteSocketAddress();
/*  58 */     this.context = context;
/*  59 */     this.logger = context.getLogger(SocketNode.class);
/*     */     try {
/*  62 */       this.ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
/*  64 */     } catch (Exception e) {
/*  65 */       this.logger.error("Could not open ObjectInputStream to " + socket, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void run() {
/*     */     try {
/*  80 */       while (!this.closed) {
/*  82 */         ILoggingEvent event = (ILoggingEvent)this.ois.readObject();
/*  85 */         Logger remoteLogger = this.context.getLogger(event.getLoggerName());
/*  87 */         if (remoteLogger.isEnabledFor(event.getLevel()))
/*  89 */           remoteLogger.callAppenders(event); 
/*     */       } 
/*  92 */     } catch (EOFException e) {
/*  93 */       this.logger.info("Caught java.io.EOFException closing connection.");
/*  94 */     } catch (SocketException e) {
/*  95 */       this.logger.info("Caught java.net.SocketException closing connection.");
/*  96 */     } catch (IOException e) {
/*  97 */       this.logger.info("Caught java.io.IOException: " + e);
/*  98 */       this.logger.info("Closing connection.");
/*  99 */     } catch (Exception e) {
/* 100 */       this.logger.error("Unexpected exception. Closing connection.", e);
/*     */     } 
/* 103 */     this.socketServer.socketNodeClosing(this);
/* 104 */     close();
/*     */   }
/*     */   
/*     */   void close() {
/* 108 */     if (this.closed)
/*     */       return; 
/* 111 */     this.closed = true;
/* 112 */     if (this.ois != null)
/*     */       try {
/* 114 */         this.ois.close();
/* 115 */       } catch (IOException e) {
/* 116 */         this.logger.warn("Could not close connection.", e);
/*     */       } finally {
/* 118 */         this.ois = null;
/*     */       }  
/*     */   }
/*     */   
/*     */   public String toString() {
/* 125 */     return getClass().getName() + this.remoteSocketAddress.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\net\SocketNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */