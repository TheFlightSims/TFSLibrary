/*     */ package ch.qos.logback.core;
/*     */ 
/*     */ import ch.qos.logback.core.helpers.CyclicBuffer;
/*     */ import ch.qos.logback.core.spi.LogbackLock;
/*     */ import ch.qos.logback.core.status.Status;
/*     */ import ch.qos.logback.core.status.StatusListener;
/*     */ import ch.qos.logback.core.status.StatusManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class BasicStatusManager implements StatusManager {
/*     */   public static final int MAX_HEADER_COUNT = 150;
/*     */   
/*     */   public static final int TAIL_SIZE = 150;
/*     */   
/*  30 */   int count = 0;
/*     */   
/*  33 */   protected final List<Status> statusList = new ArrayList<Status>();
/*     */   
/*  34 */   protected final CyclicBuffer<Status> tailBuffer = new CyclicBuffer(150);
/*     */   
/*  36 */   protected final LogbackLock statusListLock = new LogbackLock();
/*     */   
/*  38 */   int level = 0;
/*     */   
/*  41 */   protected final List<StatusListener> statusListenerList = new ArrayList<StatusListener>();
/*     */   
/*  42 */   protected final LogbackLock statusListenerListLock = new LogbackLock();
/*     */   
/*     */   public void add(Status newStatus) {
/*  59 */     fireStatusAddEvent(newStatus);
/*  61 */     this.count++;
/*  62 */     if (newStatus.getLevel() > this.level)
/*  63 */       this.level = newStatus.getLevel(); 
/*  66 */     synchronized (this.statusListLock) {
/*  67 */       if (this.statusList.size() < 150) {
/*  68 */         this.statusList.add(newStatus);
/*     */       } else {
/*  70 */         this.tailBuffer.add(newStatus);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<Status> getCopyOfStatusList() {
/*  77 */     synchronized (this.statusListLock) {
/*  78 */       List<Status> tList = new ArrayList<Status>(this.statusList);
/*  79 */       tList.addAll(this.tailBuffer.asList());
/*  80 */       return tList;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fireStatusAddEvent(Status status) {
/*  85 */     synchronized (this.statusListenerListLock) {
/*  86 */       for (StatusListener sl : this.statusListenerList)
/*  87 */         sl.addStatusEvent(status); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/*  93 */     synchronized (this.statusListLock) {
/*  94 */       this.count = 0;
/*  95 */       this.statusList.clear();
/*  96 */       this.tailBuffer.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getLevel() {
/* 101 */     return this.level;
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 105 */     return this.count;
/*     */   }
/*     */   
/*     */   public void add(StatusListener listener) {
/* 109 */     synchronized (this.statusListenerListLock) {
/* 110 */       this.statusListenerList.add(listener);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove(StatusListener listener) {
/* 115 */     synchronized (this.statusListenerListLock) {
/* 116 */       this.statusListenerList.remove(listener);
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<StatusListener> getCopyOfStatusListenerList() {
/* 121 */     synchronized (this.statusListenerListLock) {
/* 122 */       return new ArrayList<StatusListener>(this.statusListenerList);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\BasicStatusManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */