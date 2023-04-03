/*     */ package ch.qos.logback.core.net.server;
/*     */ 
/*     */ import ch.qos.logback.core.spi.ContextAwareBase;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.RejectedExecutionException;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ public abstract class ConcurrentServerRunner<T extends Client> extends ContextAwareBase implements Runnable, ServerRunner<T> {
/*  51 */   private final Lock clientsLock = new ReentrantLock();
/*     */   
/*  53 */   private final Collection<T> clients = new ArrayList<T>();
/*     */   
/*     */   private final ServerListener<T> listener;
/*     */   
/*     */   private final Executor executor;
/*     */   
/*     */   private boolean running;
/*     */   
/*     */   public ConcurrentServerRunner(ServerListener<T> listener, Executor executor) {
/*  70 */     this.listener = listener;
/*  71 */     this.executor = executor;
/*     */   }
/*     */   
/*     */   public boolean isRunning() {
/*  78 */     return this.running;
/*     */   }
/*     */   
/*     */   protected void setRunning(boolean running) {
/*  82 */     this.running = running;
/*     */   }
/*     */   
/*     */   public void stop() throws IOException {
/*  89 */     this.listener.close();
/*  90 */     accept(new ClientVisitor<T>() {
/*     */           public void visit(T client) {
/*  92 */             client.close();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void accept(ClientVisitor<T> visitor) {
/* 101 */     Collection<T> clients = copyClients();
/* 102 */     for (Client client : clients) {
/*     */       try {
/* 104 */         visitor.visit((T)client);
/* 106 */       } catch (RuntimeException ex) {
/* 107 */         addError(client + ": " + ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Collection<T> copyClients() {
/* 118 */     this.clientsLock.lock();
/*     */     try {
/* 120 */       Collection<T> copy = new ArrayList<T>(this.clients);
/* 121 */       return copy;
/*     */     } finally {
/* 124 */       this.clientsLock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void run() {
/* 132 */     setRunning(true);
/*     */     try {
/* 134 */       addInfo("listening on " + this.listener);
/* 135 */       while (!Thread.currentThread().isInterrupted()) {
/* 136 */         T client = this.listener.acceptClient();
/* 137 */         if (!configureClient(client)) {
/* 138 */           addError((new StringBuilder()).append(client).append(": connection dropped").toString());
/* 139 */           client.close();
/*     */           continue;
/*     */         } 
/*     */         try {
/* 143 */           this.executor.execute(new ClientWrapper(client));
/* 145 */         } catch (RejectedExecutionException ex) {
/* 146 */           addError((new StringBuilder()).append(client).append(": connection dropped").toString());
/* 147 */           client.close();
/*     */         } 
/*     */       } 
/* 151 */     } catch (InterruptedException ex) {
/*     */     
/* 154 */     } catch (Exception ex) {
/* 155 */       addError("listener: " + ex);
/*     */     } 
/* 158 */     setRunning(false);
/* 159 */     addInfo("shutting down");
/* 160 */     this.listener.close();
/*     */   }
/*     */   
/*     */   private void addClient(T client) {
/* 180 */     this.clientsLock.lock();
/*     */     try {
/* 182 */       this.clients.add(client);
/*     */     } finally {
/* 185 */       this.clientsLock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeClient(T client) {
/* 194 */     this.clientsLock.lock();
/*     */     try {
/* 196 */       this.clients.remove(client);
/*     */     } finally {
/* 199 */       this.clientsLock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract boolean configureClient(T paramT);
/*     */   
/*     */   private class ClientWrapper implements Client {
/*     */     private final T delegate;
/*     */     
/*     */     public ClientWrapper(T client) {
/* 212 */       this.delegate = client;
/*     */     }
/*     */     
/*     */     public void run() {
/* 216 */       ConcurrentServerRunner.this.addClient(this.delegate);
/*     */       try {
/* 218 */         this.delegate.run();
/*     */       } finally {
/* 221 */         ConcurrentServerRunner.this.removeClient(this.delegate);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void close() {
/* 226 */       this.delegate.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\server\ConcurrentServerRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */