/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.Initializable;
/*     */ 
/*     */ public class DataPostbox<T> implements Initializable {
/*     */   private int bufferCapacity;
/*     */   
/*     */   private int chunkSize;
/*     */   
/*     */   private Lock lock;
/*     */   
/*     */   private Condition dataWaitCondition;
/*     */   
/*     */   private Map<String, Object> processingMetaData;
/*     */   
/*     */   private Collection<T> centralQueue;
/*     */   
/*     */   private Collection<T> inboundQueue;
/*     */   
/*     */   private Queue<T> outboundQueue;
/*     */   
/*     */   private boolean inputInitialized;
/*     */   
/*     */   private boolean outputInitialized;
/*     */   
/*     */   private boolean inputComplete;
/*     */   
/*     */   private boolean outputComplete;
/*     */   
/*     */   private boolean inputReleased;
/*     */   
/*     */   private boolean outputReleased;
/*     */   
/*     */   private boolean inputExit;
/*     */   
/*     */   private boolean outputExit;
/*     */   
/*     */   private boolean inputOkay;
/*     */   
/*     */   private boolean outputOkay;
/*     */   
/*     */   public DataPostbox(int capacity) {
/* 108 */     if (capacity <= 0)
/* 109 */       throw new OsmosisRuntimeException("A capacity of " + capacity + " is invalid, must be greater than 0."); 
/* 112 */     this.bufferCapacity = capacity;
/* 116 */     this.chunkSize = this.bufferCapacity / 4;
/* 117 */     if (this.chunkSize <= 0)
/* 118 */       this.chunkSize = 1; 
/* 122 */     this.lock = new ReentrantLock();
/* 123 */     this.dataWaitCondition = this.lock.newCondition();
/* 129 */     this.inputInitialized = false;
/* 130 */     this.outputInitialized = false;
/* 131 */     this.inputComplete = false;
/* 132 */     this.outputComplete = false;
/* 133 */     this.inputReleased = false;
/* 134 */     this.outputReleased = false;
/* 135 */     this.inputExit = true;
/* 136 */     this.outputExit = true;
/* 137 */     this.inputOkay = true;
/* 138 */     this.outputOkay = true;
/* 141 */     initializeQueues();
/*     */   }
/*     */   
/*     */   private void initializeQueues() {
/* 147 */     this.centralQueue = new ArrayList<T>();
/* 148 */     this.inboundQueue = new ArrayList<T>();
/* 149 */     this.outboundQueue = new ArrayDeque<T>();
/*     */   }
/*     */   
/*     */   private void checkForOutputErrors() {
/* 159 */     if (!this.outputOkay)
/* 160 */       throw new OsmosisRuntimeException("An output error has occurred, aborting."); 
/*     */   }
/*     */   
/*     */   private void checkForInputErrors() {
/* 171 */     if (!this.inputOkay)
/* 172 */       throw new OsmosisRuntimeException("An input error has occurred, aborting."); 
/*     */   }
/*     */   
/*     */   private void waitForUpdate() {
/*     */     try {
/* 183 */       this.dataWaitCondition.await();
/* 185 */     } catch (InterruptedException e) {
/* 186 */       throw new OsmosisRuntimeException("Thread was interrupted.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void signalUpdate() {
/* 196 */     this.dataWaitCondition.signal();
/*     */   }
/*     */   
/*     */   private void populateCentralQueue() {
/* 208 */     this.lock.lock();
/*     */     try {
/* 211 */       checkForOutputErrors();
/* 214 */       while (this.centralQueue.size() >= this.bufferCapacity) {
/* 215 */         waitForUpdate();
/* 216 */         checkForOutputErrors();
/*     */       } 
/* 220 */       this.centralQueue.addAll(this.inboundQueue);
/* 221 */       this.inboundQueue.clear();
/* 222 */       signalUpdate();
/*     */     } finally {
/* 225 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void consumeCentralQueue() {
/* 234 */     this.lock.lock();
/*     */     try {
/* 237 */       checkForInputErrors();
/* 240 */       while (this.centralQueue.size() <= 0 && !this.inputComplete) {
/* 241 */         waitForUpdate();
/* 242 */         checkForInputErrors();
/*     */       } 
/* 245 */       this.outboundQueue.addAll(this.centralQueue);
/* 246 */       this.centralQueue.clear();
/* 248 */       signalUpdate();
/*     */     } finally {
/* 251 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initialize(Map<String, Object> metaData) {
/* 261 */     if (this.inputInitialized)
/* 262 */       throw new OsmosisRuntimeException("initialize has already been called"); 
/* 265 */     this.lock.lock();
/*     */     try {
/* 268 */       checkForOutputErrors();
/* 271 */       this.processingMetaData = metaData;
/* 272 */       this.inputInitialized = true;
/* 274 */       signalUpdate();
/* 278 */       while (!this.outputInitialized) {
/* 279 */         waitForUpdate();
/* 280 */         checkForOutputErrors();
/*     */       } 
/*     */     } finally {
/* 284 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void put(T o) {
/* 296 */     if (!this.inputInitialized)
/* 297 */       throw new OsmosisRuntimeException("initialize has not been called"); 
/* 300 */     this.inboundQueue.add(o);
/* 302 */     if (this.inboundQueue.size() >= this.chunkSize)
/* 303 */       populateCentralQueue(); 
/*     */   }
/*     */   
/*     */   public void complete() {
/* 313 */     if (!this.inputInitialized)
/* 314 */       throw new OsmosisRuntimeException("initialize has not been called"); 
/* 317 */     this.lock.lock();
/*     */     try {
/* 320 */       populateCentralQueue();
/* 322 */       this.inputComplete = true;
/* 324 */       signalUpdate();
/* 328 */       while (!this.outputComplete) {
/* 329 */         waitForUpdate();
/* 330 */         checkForOutputErrors();
/*     */       } 
/*     */     } finally {
/* 334 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void release() {
/* 348 */     this.lock.lock();
/*     */     try {
/* 353 */       if (!this.inputComplete)
/* 354 */         this.inputOkay = false; 
/* 357 */       this.inputReleased = true;
/* 358 */       this.inputExit = false;
/* 359 */       signalUpdate();
/* 362 */       while (!this.outputReleased)
/* 363 */         waitForUpdate(); 
/* 368 */       initializeQueues();
/* 369 */       this.inputInitialized = false;
/* 370 */       this.inputComplete = false;
/* 371 */       this.inputReleased = false;
/* 372 */       this.inputExit = true;
/* 373 */       this.inputOkay = true;
/* 374 */       signalUpdate();
/* 377 */       while (!this.outputExit)
/* 378 */         waitForUpdate(); 
/*     */     } finally {
/* 382 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map<String, Object> outputInitialize() {
/* 395 */     if (this.outputInitialized)
/* 396 */       throw new OsmosisRuntimeException("outputInitialize has already been called"); 
/* 399 */     this.lock.lock();
/*     */     try {
/* 402 */       checkForInputErrors();
/* 406 */       while (!this.inputInitialized) {
/* 407 */         waitForUpdate();
/* 408 */         checkForInputErrors();
/*     */       } 
/* 411 */       this.outputInitialized = true;
/* 412 */       signalUpdate();
/* 414 */       return this.processingMetaData;
/*     */     } finally {
/* 417 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 432 */     if (!this.outputInitialized)
/* 433 */       throw new OsmosisRuntimeException("outputInitialize has not been called"); 
/* 436 */     int queueSize = this.outboundQueue.size();
/* 438 */     if (queueSize <= 0) {
/* 439 */       consumeCentralQueue();
/* 440 */       queueSize = this.outboundQueue.size();
/*     */     } 
/* 443 */     return (queueSize > 0);
/*     */   }
/*     */   
/*     */   public T getNext() {
/* 454 */     if (hasNext()) {
/* 457 */       T result = this.outboundQueue.remove();
/* 459 */       return result;
/*     */     } 
/* 462 */     throw new OsmosisRuntimeException("No data is available, should call hasNext first.");
/*     */   }
/*     */   
/*     */   public void outputComplete() {
/* 473 */     if (!this.outputInitialized)
/* 474 */       throw new OsmosisRuntimeException("outputInitialize has not been called"); 
/* 477 */     this.lock.lock();
/*     */     try {
/* 480 */       checkForInputErrors();
/* 484 */       while (!this.inputComplete) {
/* 485 */         waitForUpdate();
/* 486 */         checkForInputErrors();
/*     */       } 
/* 489 */       this.outputComplete = true;
/* 490 */       signalUpdate();
/*     */     } finally {
/* 493 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void outputRelease() {
/* 503 */     this.lock.lock();
/*     */     try {
/* 508 */       if (!this.outputComplete) {
/* 509 */         this.outputOkay = false;
/* 510 */         signalUpdate();
/*     */       } 
/* 514 */       while (!this.inputReleased)
/* 515 */         waitForUpdate(); 
/* 520 */       this.outputInitialized = false;
/* 521 */       this.outputComplete = false;
/* 522 */       this.outputReleased = true;
/* 523 */       this.outputExit = false;
/* 524 */       this.outputOkay = true;
/* 525 */       signalUpdate();
/* 528 */       while (!this.inputExit)
/* 529 */         waitForUpdate(); 
/* 536 */       this.outputReleased = false;
/* 537 */       this.outputExit = true;
/* 538 */       signalUpdate();
/*     */     } finally {
/* 541 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\DataPostbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */