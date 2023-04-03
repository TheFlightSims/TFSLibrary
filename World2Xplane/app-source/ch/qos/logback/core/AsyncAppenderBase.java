/*     */ package ch.qos.logback.core;
/*     */ 
/*     */ import ch.qos.logback.core.spi.AppenderAttachable;
/*     */ import ch.qos.logback.core.spi.AppenderAttachableImpl;
/*     */ import java.util.Iterator;
/*     */ import java.util.concurrent.ArrayBlockingQueue;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ 
/*     */ public class AsyncAppenderBase<E> extends UnsynchronizedAppenderBase<E> implements AppenderAttachable<E> {
/*  41 */   AppenderAttachableImpl<E> aai = new AppenderAttachableImpl();
/*     */   
/*     */   BlockingQueue<E> blockingQueue;
/*     */   
/*     */   public static final int DEFAULT_QUEUE_SIZE = 256;
/*     */   
/*  48 */   int queueSize = 256;
/*     */   
/*  50 */   int appenderCount = 0;
/*     */   
/*     */   static final int UNDEFINED = -1;
/*     */   
/*  53 */   int discardingThreshold = -1;
/*     */   
/*  55 */   Worker worker = new Worker();
/*     */   
/*     */   protected boolean isDiscardable(E eventObject) {
/*  68 */     return false;
/*     */   }
/*     */   
/*     */   protected void preprocess(E eventObject) {}
/*     */   
/*     */   public void start() {
/*  84 */     if (this.appenderCount == 0) {
/*  85 */       addError("No attached appenders found.");
/*     */       return;
/*     */     } 
/*  88 */     if (this.queueSize < 1) {
/*  89 */       addError("Invalid queue size [" + this.queueSize + "]");
/*     */       return;
/*     */     } 
/*  92 */     this.blockingQueue = new ArrayBlockingQueue<E>(this.queueSize);
/*  94 */     if (this.discardingThreshold == -1)
/*  95 */       this.discardingThreshold = this.queueSize / 5; 
/*  96 */     addInfo("Setting discardingThreshold to " + this.discardingThreshold);
/*  97 */     this.worker.setDaemon(true);
/*  98 */     this.worker.setName("AsyncAppender-Worker-" + this.worker.getName());
/* 100 */     super.start();
/* 101 */     this.worker.start();
/*     */   }
/*     */   
/*     */   public void stop() {
/* 106 */     if (!isStarted())
/*     */       return; 
/* 111 */     super.stop();
/* 115 */     this.worker.interrupt();
/*     */     try {
/* 117 */       this.worker.join(1000L);
/* 118 */     } catch (InterruptedException e) {
/* 119 */       addError("Failed to join worker thread", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void append(E eventObject) {
/* 126 */     if (isQueueBelowDiscardingThreshold() && isDiscardable(eventObject))
/*     */       return; 
/* 129 */     preprocess(eventObject);
/* 130 */     put(eventObject);
/*     */   }
/*     */   
/*     */   private boolean isQueueBelowDiscardingThreshold() {
/* 134 */     return (this.blockingQueue.remainingCapacity() < this.discardingThreshold);
/*     */   }
/*     */   
/*     */   private void put(E eventObject) {
/*     */     try {
/* 139 */       this.blockingQueue.put(eventObject);
/* 140 */     } catch (InterruptedException e) {}
/*     */   }
/*     */   
/*     */   public int getQueueSize() {
/* 145 */     return this.queueSize;
/*     */   }
/*     */   
/*     */   public void setQueueSize(int queueSize) {
/* 149 */     this.queueSize = queueSize;
/*     */   }
/*     */   
/*     */   public int getDiscardingThreshold() {
/* 153 */     return this.discardingThreshold;
/*     */   }
/*     */   
/*     */   public void setDiscardingThreshold(int discardingThreshold) {
/* 157 */     this.discardingThreshold = discardingThreshold;
/*     */   }
/*     */   
/*     */   public int getNumberOfElementsInQueue() {
/* 166 */     return this.blockingQueue.size();
/*     */   }
/*     */   
/*     */   public int getRemainingCapacity() {
/* 176 */     return this.blockingQueue.remainingCapacity();
/*     */   }
/*     */   
/*     */   public void addAppender(Appender<E> newAppender) {
/* 182 */     if (this.appenderCount == 0) {
/* 183 */       this.appenderCount++;
/* 184 */       addInfo("Attaching appender named [" + newAppender.getName() + "] to AsyncAppender.");
/* 185 */       this.aai.addAppender(newAppender);
/*     */     } else {
/* 187 */       addWarn("One and only one appender may be attached to AsyncAppender.");
/* 188 */       addWarn("Ignoring additional appender named [" + newAppender.getName() + "]");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator<Appender<E>> iteratorForAppenders() {
/* 193 */     return this.aai.iteratorForAppenders();
/*     */   }
/*     */   
/*     */   public Appender<E> getAppender(String name) {
/* 197 */     return this.aai.getAppender(name);
/*     */   }
/*     */   
/*     */   public boolean isAttached(Appender<E> eAppender) {
/* 201 */     return this.aai.isAttached(eAppender);
/*     */   }
/*     */   
/*     */   public void detachAndStopAllAppenders() {
/* 205 */     this.aai.detachAndStopAllAppenders();
/*     */   }
/*     */   
/*     */   public boolean detachAppender(Appender<E> eAppender) {
/* 209 */     return this.aai.detachAppender(eAppender);
/*     */   }
/*     */   
/*     */   public boolean detachAppender(String name) {
/* 213 */     return this.aai.detachAppender(name);
/*     */   }
/*     */   
/*     */   class Worker extends Thread {
/*     */     public void run() {
/* 219 */       AsyncAppenderBase<E> parent = AsyncAppenderBase.this;
/* 220 */       AppenderAttachableImpl<E> aai = parent.aai;
/* 223 */       while (parent.isStarted()) {
/*     */         try {
/* 225 */           E e = parent.blockingQueue.take();
/* 226 */           aai.appendLoopOnAppenders(e);
/* 227 */         } catch (InterruptedException ie) {
/*     */           break;
/*     */         } 
/*     */       } 
/* 232 */       AsyncAppenderBase.this.addInfo("Worker thread will flush remaining events before exiting. ");
/* 233 */       for (E e : parent.blockingQueue)
/* 234 */         aai.appendLoopOnAppenders(e); 
/* 237 */       aai.detachAndStopAllAppenders();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\AsyncAppenderBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */