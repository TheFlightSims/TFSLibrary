/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public final class SynchronizedPriorityQueue implements PriorityQueue {
/*     */   protected final PriorityQueue m_priorityQueue;
/*     */   
/*     */   public SynchronizedPriorityQueue(PriorityQueue priorityQueue) {
/*  46 */     this.m_priorityQueue = priorityQueue;
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/*  53 */     this.m_priorityQueue.clear();
/*     */   }
/*     */   
/*     */   public synchronized boolean isEmpty() {
/*  62 */     return this.m_priorityQueue.isEmpty();
/*     */   }
/*     */   
/*     */   public synchronized void insert(Object element) {
/*  71 */     this.m_priorityQueue.insert(element);
/*     */   }
/*     */   
/*     */   public synchronized Object peek() throws NoSuchElementException {
/*  81 */     return this.m_priorityQueue.peek();
/*     */   }
/*     */   
/*     */   public synchronized Object pop() throws NoSuchElementException {
/*  91 */     return this.m_priorityQueue.pop();
/*     */   }
/*     */   
/*     */   public synchronized String toString() {
/* 100 */     return this.m_priorityQueue.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\SynchronizedPriorityQueue.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */