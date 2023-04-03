/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ 
/*     */ public class IterationManager {
/*     */   private final Incrementor iterations;
/*     */   
/*     */   private final Collection<IterationListener> listeners;
/*     */   
/*     */   public IterationManager(int maxIterations) {
/*  46 */     this.iterations = new Incrementor();
/*  47 */     this.iterations.setMaximalCount(maxIterations);
/*  48 */     this.listeners = new CopyOnWriteArrayList<IterationListener>();
/*     */   }
/*     */   
/*     */   public void addIterationListener(IterationListener listener) {
/*  57 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */   public void fireInitializationEvent(IterationEvent e) {
/*  67 */     for (IterationListener l : this.listeners)
/*  68 */       l.initializationPerformed(e); 
/*     */   }
/*     */   
/*     */   public void fireIterationPerformedEvent(IterationEvent e) {
/*  79 */     for (IterationListener l : this.listeners)
/*  80 */       l.iterationPerformed(e); 
/*     */   }
/*     */   
/*     */   public void fireIterationStartedEvent(IterationEvent e) {
/*  91 */     for (IterationListener l : this.listeners)
/*  92 */       l.iterationStarted(e); 
/*     */   }
/*     */   
/*     */   public void fireTerminationEvent(IterationEvent e) {
/* 103 */     for (IterationListener l : this.listeners)
/* 104 */       l.terminationPerformed(e); 
/*     */   }
/*     */   
/*     */   public int getIterations() {
/* 115 */     return this.iterations.getCount();
/*     */   }
/*     */   
/*     */   public int getMaxIterations() {
/* 124 */     return this.iterations.getMaximalCount();
/*     */   }
/*     */   
/*     */   public void incrementIterationCount() throws MaxCountExceededException {
/* 137 */     this.iterations.incrementCount();
/*     */   }
/*     */   
/*     */   public void removeIterationListener(IterationListener listener) {
/* 149 */     this.listeners.remove(listener);
/*     */   }
/*     */   
/*     */   public void resetIterationCount() {
/* 157 */     this.iterations.resetCount();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\IterationManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */