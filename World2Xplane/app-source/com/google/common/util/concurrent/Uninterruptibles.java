/*     */ package com.google.common.util.concurrent;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ 
/*     */ @Beta
/*     */ public final class Uninterruptibles {
/*     */   public static void awaitUninterruptibly(CountDownLatch latch) {
/*  51 */     boolean interrupted = false;
/*     */     while (true) {
/*     */       try {
/*  55 */         latch.await();
/*     */         return;
/*  57 */       } catch (InterruptedException e) {
/*     */       
/*     */       } finally {
/*  62 */         if (interrupted)
/*  63 */           Thread.currentThread().interrupt(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean awaitUninterruptibly(CountDownLatch latch, long timeout, TimeUnit unit) {
/*  75 */     boolean interrupted = false;
/*     */     try {
/*  77 */       long remainingNanos = unit.toNanos(timeout);
/*  78 */       long end = System.nanoTime() + remainingNanos;
/*     */       while (true) {
/*     */         try {
/*  83 */           return latch.await(remainingNanos, TimeUnit.NANOSECONDS);
/*  84 */         } catch (InterruptedException e) {
/*  85 */           interrupted = true;
/*  86 */           remainingNanos = end - System.nanoTime();
/*     */         } 
/*     */       } 
/*     */     } finally {
/*  90 */       if (interrupted)
/*  91 */         Thread.currentThread().interrupt(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void joinUninterruptibly(Thread toJoin) {
/* 100 */     boolean interrupted = false;
/*     */     while (true) {
/*     */       try {
/* 104 */         toJoin.join();
/*     */         return;
/* 106 */       } catch (InterruptedException e) {
/*     */       
/*     */       } finally {
/* 111 */         if (interrupted)
/* 112 */           Thread.currentThread().interrupt(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
/* 131 */     boolean interrupted = false;
/*     */     while (true) {
/*     */       try {
/* 135 */         return future.get();
/* 136 */       } catch (InterruptedException e) {
/*     */       
/*     */       } finally {
/* 141 */         if (interrupted)
/* 142 */           Thread.currentThread().interrupt(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <V> V getUninterruptibly(Future<V> future, long timeout, TimeUnit unit) throws ExecutionException, TimeoutException {
/* 163 */     boolean interrupted = false;
/*     */     try {
/* 165 */       long remainingNanos = unit.toNanos(timeout);
/* 166 */       long end = System.nanoTime() + remainingNanos;
/*     */       while (true) {
/*     */         try {
/* 171 */           return future.get(remainingNanos, TimeUnit.NANOSECONDS);
/* 172 */         } catch (InterruptedException e) {
/* 173 */           interrupted = true;
/* 174 */           remainingNanos = end - System.nanoTime();
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 178 */       if (interrupted)
/* 179 */         Thread.currentThread().interrupt(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void joinUninterruptibly(Thread toJoin, long timeout, TimeUnit unit) {
/* 191 */     Preconditions.checkNotNull(toJoin);
/* 192 */     boolean interrupted = false;
/*     */     try {
/* 194 */       long remainingNanos = unit.toNanos(timeout);
/* 195 */       long end = System.nanoTime() + remainingNanos;
/*     */       while (true) {
/*     */         try {
/* 199 */           TimeUnit.NANOSECONDS.timedJoin(toJoin, remainingNanos);
/*     */           return;
/* 201 */         } catch (InterruptedException e) {
/* 202 */           interrupted = true;
/* 203 */           remainingNanos = end - System.nanoTime();
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 207 */       if (interrupted)
/* 208 */         Thread.currentThread().interrupt(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <E> E takeUninterruptibly(BlockingQueue<E> queue) {
/* 217 */     boolean interrupted = false;
/*     */     while (true) {
/*     */       try {
/* 221 */         return queue.take();
/* 222 */       } catch (InterruptedException e) {
/*     */       
/*     */       } finally {
/* 227 */         if (interrupted)
/* 228 */           Thread.currentThread().interrupt(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <E> void putUninterruptibly(BlockingQueue<E> queue, E element) {
/* 243 */     boolean interrupted = false;
/*     */     while (true) {
/*     */       try {
/* 247 */         queue.put(element);
/*     */         return;
/* 249 */       } catch (InterruptedException e) {
/*     */       
/*     */       } finally {
/* 254 */         if (interrupted)
/* 255 */           Thread.currentThread().interrupt(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sleepUninterruptibly(long sleepFor, TimeUnit unit) {
/* 266 */     boolean interrupted = false;
/*     */     try {
/* 268 */       long remainingNanos = unit.toNanos(sleepFor);
/* 269 */       long end = System.nanoTime() + remainingNanos;
/*     */       while (true) {
/*     */         try {
/* 273 */           TimeUnit.NANOSECONDS.sleep(remainingNanos);
/*     */           return;
/* 275 */         } catch (InterruptedException e) {
/* 276 */           interrupted = true;
/* 277 */           remainingNanos = end - System.nanoTime();
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 281 */       if (interrupted)
/* 282 */         Thread.currentThread().interrupt(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\commo\\util\concurrent\Uninterruptibles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */