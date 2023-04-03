/*      */ package scala.concurrent.forkjoin;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.lang.ref.ReferenceQueue;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.Collection;
/*      */ import java.util.List;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.CancellationException;
/*      */ import java.util.concurrent.ExecutionException;
/*      */ import java.util.concurrent.Future;
/*      */ import java.util.concurrent.RunnableFuture;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.concurrent.TimeoutException;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import scala.concurrent.util.Unsafe;
/*      */ import sun.misc.Unsafe;
/*      */ 
/*      */ public abstract class ForkJoinTask<V> implements Future<V>, Serializable {
/*      */   volatile int status;
/*      */   
/*      */   static final int DONE_MASK = -268435456;
/*      */   
/*      */   static final int NORMAL = -268435456;
/*      */   
/*      */   static final int CANCELLED = -1073741824;
/*      */   
/*      */   static final int EXCEPTIONAL = -2147483648;
/*      */   
/*      */   static final int SIGNAL = 65536;
/*      */   
/*      */   static final int SMASK = 65535;
/*      */   
/*      */   private static final ExceptionNode[] exceptionTable;
/*      */   
/*      */   private int setCompletion(int completion) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield status : I
/*      */     //   4: dup
/*      */     //   5: istore_2
/*      */     //   6: ifge -> 11
/*      */     //   9: iload_2
/*      */     //   10: ireturn
/*      */     //   11: getstatic scala/concurrent/forkjoin/ForkJoinTask.U : Lsun/misc/Unsafe;
/*      */     //   14: aload_0
/*      */     //   15: getstatic scala/concurrent/forkjoin/ForkJoinTask.STATUS : J
/*      */     //   18: iload_2
/*      */     //   19: iload_2
/*      */     //   20: iload_1
/*      */     //   21: ior
/*      */     //   22: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   25: ifeq -> 0
/*      */     //   28: iload_2
/*      */     //   29: bipush #16
/*      */     //   31: iushr
/*      */     //   32: ifeq -> 55
/*      */     //   35: aload_0
/*      */     //   36: dup
/*      */     //   37: astore_3
/*      */     //   38: monitorenter
/*      */     //   39: aload_0
/*      */     //   40: invokevirtual notifyAll : ()V
/*      */     //   43: aload_3
/*      */     //   44: monitorexit
/*      */     //   45: goto -> 55
/*      */     //   48: astore #4
/*      */     //   50: aload_3
/*      */     //   51: monitorexit
/*      */     //   52: aload #4
/*      */     //   54: athrow
/*      */     //   55: iload_1
/*      */     //   56: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #239	-> 0
/*      */     //   #240	-> 9
/*      */     //   #241	-> 11
/*      */     //   #242	-> 28
/*      */     //   #243	-> 35
/*      */     //   #244	-> 55
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   6	51	2	s	I
/*      */     //   0	57	0	this	Lscala/concurrent/forkjoin/ForkJoinTask;
/*      */     //   0	57	1	completion	I
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	57	0	this	Lscala/concurrent/forkjoin/ForkJoinTask<TV;>;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   39	45	48	finally
/*      */     //   48	52	48	finally
/*      */   }
/*      */   
/*      */   final int doExec() {
/*      */     int s;
/*  258 */     if ((s = this.status) >= 0) {
/*      */       boolean completed;
/*      */       try {
/*  260 */         completed = exec();
/*  261 */       } catch (Throwable rex) {
/*  262 */         return setExceptionalCompletion(rex);
/*      */       } 
/*  264 */       if (completed)
/*  265 */         s = setCompletion(-268435456); 
/*      */     } 
/*  267 */     return s;
/*      */   }
/*      */   
/*      */   final boolean trySetSignal() {
/*  278 */     int s = this.status;
/*  279 */     return (s >= 0 && U.compareAndSwapInt(this, STATUS, s, s | 0x10000));
/*      */   }
/*      */   
/*      */   private int externalAwaitDone() {
/*  288 */     ForkJoinPool.externalHelpJoin(this);
/*  289 */     boolean interrupted = false;
/*      */     int s;
/*  290 */     while ((s = this.status) >= 0) {
/*  291 */       if (U.compareAndSwapInt(this, STATUS, s, s | 0x10000))
/*  292 */         synchronized (this) {
/*  293 */           if (this.status >= 0) {
/*      */             try {
/*  295 */               wait();
/*  296 */             } catch (InterruptedException ie) {
/*  297 */               interrupted = true;
/*      */             } 
/*      */           } else {
/*  301 */             notifyAll();
/*      */           } 
/*      */         }  
/*      */     } 
/*  305 */     if (interrupted)
/*  306 */       Thread.currentThread().interrupt(); 
/*  307 */     return s;
/*      */   }
/*      */   
/*      */   private int externalInterruptibleAwaitDone() throws InterruptedException {
/*  315 */     if (Thread.interrupted())
/*  316 */       throw new InterruptedException(); 
/*  317 */     ForkJoinPool.externalHelpJoin(this);
/*      */     int s;
/*  318 */     while ((s = this.status) >= 0) {
/*  319 */       if (U.compareAndSwapInt(this, STATUS, s, s | 0x10000))
/*  320 */         synchronized (this) {
/*  321 */           if (this.status >= 0) {
/*  322 */             wait();
/*      */           } else {
/*  324 */             notifyAll();
/*      */           } 
/*      */         }  
/*      */     } 
/*  328 */     return s;
/*      */   }
/*      */   
/*      */   private int doJoin() {
/*      */     int s;
/*      */     Thread t;
/*      */     ForkJoinWorkerThread wt;
/*      */     ForkJoinPool.WorkQueue w;
/*  341 */     return ((s = this.status) < 0) ? s : ((t = Thread.currentThread() instanceof ForkJoinWorkerThread) ? (((w = (wt = (ForkJoinWorkerThread)t).workQueue).tryUnpush(this) && (s = doExec()) < 0) ? s : wt.pool.awaitJoin(w, this)) : externalAwaitDone());
/*      */   }
/*      */   
/*      */   private int doInvoke() {
/*      */     int s;
/*      */     Thread t;
/*      */     ForkJoinWorkerThread wt;
/*  356 */     return ((s = doExec()) < 0) ? s : ((t = Thread.currentThread() instanceof ForkJoinWorkerThread) ? (wt = (ForkJoinWorkerThread)t).pool.awaitJoin(wt.workQueue, this) : externalAwaitDone());
/*      */   }
/*      */   
/*      */   static final class ExceptionNode extends WeakReference<ForkJoinTask<?>> {
/*      */     final Throwable ex;
/*      */     
/*      */     ExceptionNode next;
/*      */     
/*      */     final long thrower;
/*      */     
/*      */     ExceptionNode(ForkJoinTask<?> task, Throwable ex, ExceptionNode next) {
/*  399 */       super(task, (ReferenceQueue)ForkJoinTask.exceptionTableRefQueue);
/*  400 */       this.ex = ex;
/*  401 */       this.next = next;
/*  402 */       this.thrower = Thread.currentThread().getId();
/*      */     }
/*      */   }
/*      */   
/*      */   final int recordExceptionalCompletion(Throwable ex) {
/*      */     int s;
/*  413 */     if ((s = this.status) >= 0) {
/*  414 */       int h = System.identityHashCode(this);
/*  415 */       ReentrantLock lock = exceptionTableLock;
/*  416 */       lock.lock();
/*      */       try {
/*  418 */         expungeStaleExceptions();
/*  419 */         ExceptionNode[] t = exceptionTable;
/*  420 */         int i = h & t.length - 1;
/*  421 */         for (ExceptionNode e = t[i];; e = e.next) {
/*  422 */           if (e == null) {
/*  423 */             t[i] = new ExceptionNode(this, ex, t[i]);
/*      */             break;
/*      */           } 
/*  426 */           if (e.get() == this)
/*      */             break; 
/*      */         } 
/*      */       } finally {
/*  430 */         lock.unlock();
/*      */       } 
/*  432 */       s = setCompletion(-2147483648);
/*      */     } 
/*  434 */     return s;
/*      */   }
/*      */   
/*      */   private int setExceptionalCompletion(Throwable ex) {
/*  443 */     int s = recordExceptionalCompletion(ex);
/*  444 */     if ((s & 0xF0000000) == Integer.MIN_VALUE)
/*  445 */       internalPropagateException(ex); 
/*  446 */     return s;
/*      */   }
/*      */   
/*      */   void internalPropagateException(Throwable ex) {}
/*      */   
/*      */   static final void cancelIgnoringExceptions(ForkJoinTask<?> t) {
/*  462 */     if (t != null && t.status >= 0)
/*      */       try {
/*  464 */         t.cancel(false);
/*  465 */       } catch (Throwable ignore) {} 
/*      */   }
/*      */   
/*      */   private void clearExceptionalCompletion() {
/*  474 */     int h = System.identityHashCode(this);
/*  475 */     ReentrantLock lock = exceptionTableLock;
/*  476 */     lock.lock();
/*      */     try {
/*  478 */       ExceptionNode[] t = exceptionTable;
/*  479 */       int i = h & t.length - 1;
/*  480 */       ExceptionNode e = t[i];
/*  481 */       ExceptionNode pred = null;
/*  482 */       while (e != null) {
/*  483 */         ExceptionNode next = e.next;
/*  484 */         if (e.get() == this) {
/*  485 */           if (pred == null) {
/*  486 */             t[i] = next;
/*      */             break;
/*      */           } 
/*  488 */           pred.next = next;
/*      */           break;
/*      */         } 
/*  491 */         pred = e;
/*  492 */         e = next;
/*      */       } 
/*  494 */       expungeStaleExceptions();
/*  495 */       this.status = 0;
/*      */     } finally {
/*  497 */       lock.unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   private Throwable getThrowableException() {
/*      */     ExceptionNode e;
/*  516 */     if ((this.status & 0xF0000000) != Integer.MIN_VALUE)
/*  517 */       return null; 
/*  518 */     int h = System.identityHashCode(this);
/*  520 */     ReentrantLock lock = exceptionTableLock;
/*  521 */     lock.lock();
/*      */     try {
/*  523 */       expungeStaleExceptions();
/*  524 */       ExceptionNode[] t = exceptionTable;
/*  525 */       e = t[h & t.length - 1];
/*  526 */       while (e != null && e.get() != this)
/*  527 */         e = e.next; 
/*      */     } finally {
/*  529 */       lock.unlock();
/*      */     } 
/*      */     Throwable ex;
/*  532 */     if (e == null || (ex = e.ex) == null)
/*  533 */       return null; 
/*  555 */     return ex;
/*      */   }
/*      */   
/*      */   private static void expungeStaleExceptions() {
/*      */     Object<?> x;
/*  562 */     while ((x = (Object<?>)exceptionTableRefQueue.poll()) != null) {
/*  563 */       if (x instanceof ExceptionNode) {
/*  564 */         ForkJoinTask<?> key = ((ExceptionNode)x).get();
/*  565 */         ExceptionNode[] t = exceptionTable;
/*  566 */         int i = System.identityHashCode(key) & t.length - 1;
/*  567 */         ExceptionNode e = t[i];
/*  568 */         ExceptionNode pred = null;
/*  569 */         while (e != null) {
/*  570 */           ExceptionNode next = e.next;
/*  571 */           if (e == x) {
/*  572 */             if (pred == null) {
/*  573 */               t[i] = next;
/*      */               break;
/*      */             } 
/*  575 */             pred.next = next;
/*      */             break;
/*      */           } 
/*  578 */           pred = e;
/*  579 */           e = next;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   static final void helpExpungeStaleExceptions() {
/*  590 */     ReentrantLock lock = exceptionTableLock;
/*  591 */     if (lock.tryLock())
/*      */       try {
/*  593 */         expungeStaleExceptions();
/*      */       } finally {
/*  595 */         lock.unlock();
/*      */       }  
/*      */   }
/*      */   
/*      */   static void rethrow(Throwable ex) {
/*  604 */     if (ex != null) {
/*  605 */       if (ex instanceof Error)
/*  606 */         throw (Error)ex; 
/*  607 */       if (ex instanceof RuntimeException)
/*  608 */         throw (RuntimeException)ex; 
/*  609 */       uncheckedThrow(ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   static <T extends Throwable> void uncheckedThrow(Throwable t) throws T {
/*  620 */     if (t != null)
/*  621 */       throw (T)t; 
/*      */   }
/*      */   
/*      */   private void reportException(int s) {
/*  628 */     if (s == -1073741824)
/*  629 */       throw new CancellationException(); 
/*  630 */     if (s == Integer.MIN_VALUE)
/*  631 */       rethrow(getThrowableException()); 
/*      */   }
/*      */   
/*      */   public final ForkJoinTask<V> fork() {
/*      */     Thread t;
/*  653 */     if (t = Thread.currentThread() instanceof ForkJoinWorkerThread) {
/*  654 */       ((ForkJoinWorkerThread)t).workQueue.push(this);
/*      */     } else {
/*  656 */       ForkJoinPool.common.externalPush(this);
/*      */     } 
/*  657 */     return this;
/*      */   }
/*      */   
/*      */   public final V join() {
/*      */     int s;
/*  673 */     if ((s = doJoin() & 0xF0000000) != -268435456)
/*  674 */       reportException(s); 
/*  675 */     return getRawResult();
/*      */   }
/*      */   
/*      */   public final V invoke() {
/*      */     int s;
/*  688 */     if ((s = doInvoke() & 0xF0000000) != -268435456)
/*  689 */       reportException(s); 
/*  690 */     return getRawResult();
/*      */   }
/*      */   
/*      */   public static void invokeAll(ForkJoinTask<?> t1, ForkJoinTask<?> t2) {
/*  712 */     t2.fork();
/*      */     int s1;
/*  713 */     if ((s1 = t1.doInvoke() & 0xF0000000) != -268435456)
/*  714 */       t1.reportException(s1); 
/*      */     int s2;
/*  715 */     if ((s2 = t2.doJoin() & 0xF0000000) != -268435456)
/*  716 */       t2.reportException(s2); 
/*      */   }
/*      */   
/*      */   public static void invokeAll(ForkJoinTask<?>... tasks) {
/*  735 */     Throwable ex = null;
/*  736 */     int last = tasks.length - 1;
/*      */     int i;
/*  737 */     for (i = last; i >= 0; i--) {
/*  738 */       ForkJoinTask<?> t = tasks[i];
/*  739 */       if (t == null) {
/*  740 */         if (ex == null)
/*  741 */           ex = new NullPointerException(); 
/*  743 */       } else if (i != 0) {
/*  744 */         t.fork();
/*  745 */       } else if (t.doInvoke() < -268435456 && ex == null) {
/*  746 */         ex = t.getException();
/*      */       } 
/*      */     } 
/*  748 */     for (i = 1; i <= last; i++) {
/*  749 */       ForkJoinTask<?> t = tasks[i];
/*  750 */       if (t != null)
/*  751 */         if (ex != null) {
/*  752 */           t.cancel(false);
/*  753 */         } else if (t.doJoin() < -268435456) {
/*  754 */           ex = t.getException();
/*      */         }  
/*      */     } 
/*  757 */     if (ex != null)
/*  758 */       rethrow(ex); 
/*      */   }
/*      */   
/*      */   public static <T extends ForkJoinTask<?>> Collection<T> invokeAll(Collection<T> tasks) {
/*  779 */     if (!(tasks instanceof java.util.RandomAccess) || !(tasks instanceof List)) {
/*  780 */       invokeAll((ForkJoinTask<?>[])tasks.<ForkJoinTask>toArray(new ForkJoinTask[tasks.size()]));
/*  781 */       return tasks;
/*      */     } 
/*  784 */     List<? extends ForkJoinTask<?>> ts = (List<? extends ForkJoinTask<?>>)tasks;
/*  786 */     Throwable ex = null;
/*  787 */     int last = ts.size() - 1;
/*      */     int i;
/*  788 */     for (i = last; i >= 0; i--) {
/*  789 */       ForkJoinTask<?> t = ts.get(i);
/*  790 */       if (t == null) {
/*  791 */         if (ex == null)
/*  792 */           ex = new NullPointerException(); 
/*  794 */       } else if (i != 0) {
/*  795 */         t.fork();
/*  796 */       } else if (t.doInvoke() < -268435456 && ex == null) {
/*  797 */         ex = t.getException();
/*      */       } 
/*      */     } 
/*  799 */     for (i = 1; i <= last; i++) {
/*  800 */       ForkJoinTask<?> t = ts.get(i);
/*  801 */       if (t != null)
/*  802 */         if (ex != null) {
/*  803 */           t.cancel(false);
/*  804 */         } else if (t.doJoin() < -268435456) {
/*  805 */           ex = t.getException();
/*      */         }  
/*      */     } 
/*  808 */     if (ex != null)
/*  809 */       rethrow(ex); 
/*  810 */     return tasks;
/*      */   }
/*      */   
/*      */   public boolean cancel(boolean mayInterruptIfRunning) {
/*  841 */     return ((setCompletion(-1073741824) & 0xF0000000) == -1073741824);
/*      */   }
/*      */   
/*      */   public final boolean isDone() {
/*  845 */     return (this.status < 0);
/*      */   }
/*      */   
/*      */   public final boolean isCancelled() {
/*  849 */     return ((this.status & 0xF0000000) == -1073741824);
/*      */   }
/*      */   
/*      */   public final boolean isCompletedAbnormally() {
/*  858 */     return (this.status < -268435456);
/*      */   }
/*      */   
/*      */   public final boolean isCompletedNormally() {
/*  869 */     return ((this.status & 0xF0000000) == -268435456);
/*      */   }
/*      */   
/*      */   public final Throwable getException() {
/*  880 */     int s = this.status & 0xF0000000;
/*  881 */     return (s >= -268435456) ? null : ((s == -1073741824) ? new CancellationException() : getThrowableException());
/*      */   }
/*      */   
/*      */   public void completeExceptionally(Throwable ex) {
/*  901 */     setExceptionalCompletion((ex instanceof RuntimeException || ex instanceof Error) ? ex : new RuntimeException(ex));
/*      */   }
/*      */   
/*      */   public void complete(V value) {
/*      */     try {
/*  921 */       setRawResult(value);
/*  922 */     } catch (Throwable rex) {
/*  923 */       setExceptionalCompletion(rex);
/*      */       return;
/*      */     } 
/*  926 */     setCompletion(-268435456);
/*      */   }
/*      */   
/*      */   public final void quietlyComplete() {
/*  938 */     setCompletion(-268435456);
/*      */   }
/*      */   
/*      */   public final V get() throws InterruptedException, ExecutionException {
/*  953 */     int s = (Thread.currentThread() instanceof ForkJoinWorkerThread) ? doJoin() : externalInterruptibleAwaitDone();
/*  956 */     if ((s &= 0xF0000000) == -1073741824)
/*  957 */       throw new CancellationException(); 
/*      */     Throwable ex;
/*  958 */     if (s == Integer.MIN_VALUE && (ex = getThrowableException()) != null)
/*  959 */       throw new ExecutionException(ex); 
/*  960 */     return getRawResult();
/*      */   }
/*      */   
/*      */   public final V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
/*  979 */     if (Thread.interrupted())
/*  980 */       throw new InterruptedException(); 
/*  983 */     long ns = unit.toNanos(timeout);
/*      */     int s;
/*  984 */     if ((s = this.status) >= 0 && ns > 0L) {
/*  985 */       long deadline = System.nanoTime() + ns;
/*  986 */       ForkJoinPool p = null;
/*  987 */       ForkJoinPool.WorkQueue w = null;
/*  988 */       Thread t = Thread.currentThread();
/*  989 */       if (t instanceof ForkJoinWorkerThread) {
/*  990 */         ForkJoinWorkerThread wt = (ForkJoinWorkerThread)t;
/*  991 */         p = wt.pool;
/*  992 */         w = wt.workQueue;
/*  993 */         p.helpJoinOnce(w, this);
/*      */       } else {
/*  996 */         ForkJoinPool.externalHelpJoin(this);
/*      */       } 
/*  997 */       boolean canBlock = false;
/*  998 */       boolean interrupted = false;
/*      */       try {
/* 1000 */         while ((s = this.status) >= 0) {
/* 1001 */           if (w != null && w.qlock < 0) {
/* 1002 */             cancelIgnoringExceptions(this);
/*      */             continue;
/*      */           } 
/* 1003 */           if (!canBlock) {
/* 1004 */             if (p == null || p.tryCompensate())
/* 1005 */               canBlock = true; 
/*      */             continue;
/*      */           } 
/*      */           long ms;
/* 1008 */           if ((ms = TimeUnit.NANOSECONDS.toMillis(ns)) > 0L && U.compareAndSwapInt(this, STATUS, s, s | 0x10000))
/* 1010 */             synchronized (this) {
/* 1011 */               if (this.status >= 0) {
/*      */                 try {
/* 1013 */                   wait(ms);
/* 1014 */                 } catch (InterruptedException ie) {
/* 1015 */                   if (p == null)
/* 1016 */                     interrupted = true; 
/*      */                 } 
/*      */               } else {
/* 1020 */                 notifyAll();
/*      */               } 
/*      */             }  
/* 1023 */           if ((s = this.status) < 0 || interrupted || (ns = deadline - System.nanoTime()) <= 0L)
/*      */             break; 
/*      */         } 
/*      */       } finally {
/* 1029 */         if (p != null && canBlock)
/* 1030 */           p.incrementActiveCount(); 
/*      */       } 
/* 1032 */       if (interrupted)
/* 1033 */         throw new InterruptedException(); 
/*      */     } 
/* 1035 */     if ((s &= 0xF0000000) != -268435456) {
/* 1037 */       if (s == -1073741824)
/* 1038 */         throw new CancellationException(); 
/* 1039 */       if (s != Integer.MIN_VALUE)
/* 1040 */         throw new TimeoutException(); 
/*      */       Throwable ex;
/* 1041 */       if ((ex = getThrowableException()) != null)
/* 1042 */         throw new ExecutionException(ex); 
/*      */     } 
/* 1044 */     return getRawResult();
/*      */   }
/*      */   
/*      */   public final void quietlyJoin() {
/* 1054 */     doJoin();
/*      */   }
/*      */   
/*      */   public final void quietlyInvoke() {
/* 1063 */     doInvoke();
/*      */   }
/*      */   
/*      */   public static void helpQuiesce() {
/*      */     Thread t;
/* 1075 */     if (t = Thread.currentThread() instanceof ForkJoinWorkerThread) {
/* 1076 */       ForkJoinWorkerThread wt = (ForkJoinWorkerThread)t;
/* 1077 */       wt.pool.helpQuiescePool(wt.workQueue);
/*      */     } else {
/* 1080 */       ForkJoinPool.quiesceCommonPool();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void reinitialize() {
/* 1100 */     if ((this.status & 0xF0000000) == Integer.MIN_VALUE) {
/* 1101 */       clearExceptionalCompletion();
/*      */     } else {
/* 1103 */       this.status = 0;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static ForkJoinPool getPool() {
/* 1114 */     Thread t = Thread.currentThread();
/* 1115 */     return (t instanceof ForkJoinWorkerThread) ? ((ForkJoinWorkerThread)t).pool : null;
/*      */   }
/*      */   
/*      */   public static boolean inForkJoinPool() {
/* 1128 */     return Thread.currentThread() instanceof ForkJoinWorkerThread;
/*      */   }
/*      */   
/*      */   public boolean tryUnfork() {
/*      */     Thread t;
/* 1143 */     return (t = Thread.currentThread() instanceof ForkJoinWorkerThread) ? ((ForkJoinWorkerThread)t).workQueue.tryUnpush(this) : ForkJoinPool.tryExternalUnpush(this);
/*      */   }
/*      */   
/*      */   public static int getQueuedTaskCount() {
/*      */     ForkJoinPool.WorkQueue q;
/*      */     Thread t;
/* 1158 */     if (t = Thread.currentThread() instanceof ForkJoinWorkerThread) {
/* 1159 */       q = ((ForkJoinWorkerThread)t).workQueue;
/*      */     } else {
/* 1161 */       q = ForkJoinPool.commonSubmitterQueue();
/*      */     } 
/* 1162 */     return (q == null) ? 0 : q.queueSize();
/*      */   }
/*      */   
/*      */   public static int getSurplusQueuedTaskCount() {
/* 1179 */     return ForkJoinPool.getSurplusQueuedTaskCount();
/*      */   }
/*      */   
/*      */   protected static ForkJoinTask<?> peekNextLocalTask() {
/*      */     ForkJoinPool.WorkQueue q;
/*      */     Thread t;
/* 1234 */     if (t = Thread.currentThread() instanceof ForkJoinWorkerThread) {
/* 1235 */       q = ((ForkJoinWorkerThread)t).workQueue;
/*      */     } else {
/* 1237 */       q = ForkJoinPool.commonSubmitterQueue();
/*      */     } 
/* 1238 */     return (q == null) ? null : q.peek();
/*      */   }
/*      */   
/*      */   protected static ForkJoinTask<?> pollNextLocalTask() {
/*      */     Thread t;
/* 1252 */     return (t = Thread.currentThread() instanceof ForkJoinWorkerThread) ? ((ForkJoinWorkerThread)t).workQueue.nextLocalTask() : null;
/*      */   }
/*      */   
/*      */   protected static ForkJoinTask<?> pollTask() {
/*      */     Thread t;
/*      */     ForkJoinWorkerThread wt;
/* 1272 */     return (t = Thread.currentThread() instanceof ForkJoinWorkerThread) ? (wt = (ForkJoinWorkerThread)t).pool.nextTaskFor(wt.workQueue) : null;
/*      */   }
/*      */   
/*      */   public final short getForkJoinTaskTag() {
/* 1286 */     return (short)this.status;
/*      */   }
/*      */   
/*      */   public final short setForkJoinTaskTag(short tag) {
/*      */     int s;
/*      */     do {
/*      */     
/* 1298 */     } while (!U.compareAndSwapInt(this, STATUS, s = this.status, s & 0xFFFF0000 | tag & 0xFFFF));
/* 1300 */     return (short)s;
/*      */   }
/*      */   
/*      */   public final boolean compareAndSetForkJoinTaskTag(short e, short tag) {
/*      */     int s;
/*      */     do {
/* 1320 */       if ((short)(s = this.status) != e)
/* 1321 */         return false; 
/* 1322 */     } while (!U.compareAndSwapInt(this, STATUS, s, s & 0xFFFF0000 | tag & 0xFFFF));
/* 1324 */     return true;
/*      */   }
/*      */   
/*      */   static final class AdaptedRunnable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
/*      */     final Runnable runnable;
/*      */     
/*      */     T result;
/*      */     
/*      */     private static final long serialVersionUID = 5232453952276885070L;
/*      */     
/*      */     AdaptedRunnable(Runnable runnable, T result) {
/* 1338 */       if (runnable == null)
/* 1338 */         throw new NullPointerException(); 
/* 1339 */       this.runnable = runnable;
/* 1340 */       this.result = result;
/*      */     }
/*      */     
/*      */     public final T getRawResult() {
/* 1342 */       return this.result;
/*      */     }
/*      */     
/*      */     public final void setRawResult(T v) {
/* 1343 */       this.result = v;
/*      */     }
/*      */     
/*      */     public final boolean exec() {
/* 1344 */       this.runnable.run();
/* 1344 */       return true;
/*      */     }
/*      */     
/*      */     public final void run() {
/* 1345 */       invoke();
/*      */     }
/*      */   }
/*      */   
/*      */   static final class AdaptedRunnableAction extends ForkJoinTask<Void> implements RunnableFuture<Void> {
/*      */     final Runnable runnable;
/*      */     
/*      */     private static final long serialVersionUID = 5232453952276885070L;
/*      */     
/*      */     AdaptedRunnableAction(Runnable runnable) {
/* 1356 */       if (runnable == null)
/* 1356 */         throw new NullPointerException(); 
/* 1357 */       this.runnable = runnable;
/*      */     }
/*      */     
/*      */     public final Void getRawResult() {
/* 1359 */       return null;
/*      */     }
/*      */     
/*      */     public final void setRawResult(Void v) {}
/*      */     
/*      */     public final boolean exec() {
/* 1361 */       this.runnable.run();
/* 1361 */       return true;
/*      */     }
/*      */     
/*      */     public final void run() {
/* 1362 */       invoke();
/*      */     }
/*      */   }
/*      */   
/*      */   static final class AdaptedCallable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
/*      */     final Callable<? extends T> callable;
/*      */     
/*      */     T result;
/*      */     
/*      */     private static final long serialVersionUID = 2838392045355241008L;
/*      */     
/*      */     AdaptedCallable(Callable<? extends T> callable) {
/* 1374 */       if (callable == null)
/* 1374 */         throw new NullPointerException(); 
/* 1375 */       this.callable = callable;
/*      */     }
/*      */     
/*      */     public final T getRawResult() {
/* 1377 */       return this.result;
/*      */     }
/*      */     
/*      */     public final void setRawResult(T v) {
/* 1378 */       this.result = v;
/*      */     }
/*      */     
/*      */     public final boolean exec() {
/*      */       try {
/* 1381 */         this.result = this.callable.call();
/* 1382 */         return true;
/* 1383 */       } catch (Error err) {
/* 1384 */         throw err;
/* 1385 */       } catch (RuntimeException rex) {
/* 1386 */         throw rex;
/* 1387 */       } catch (Exception ex) {
/* 1388 */         throw new RuntimeException(ex);
/*      */       } 
/*      */     }
/*      */     
/*      */     public final void run() {
/* 1391 */       invoke();
/*      */     }
/*      */   }
/*      */   
/*      */   public static ForkJoinTask<?> adapt(Runnable runnable) {
/* 1404 */     return new AdaptedRunnableAction(runnable);
/*      */   }
/*      */   
/*      */   public static <T> ForkJoinTask<T> adapt(Runnable runnable, T result) {
/* 1417 */     return new AdaptedRunnable<T>(runnable, result);
/*      */   }
/*      */   
/*      */   public static <T> ForkJoinTask<T> adapt(Callable<? extends T> callable) {
/* 1430 */     return new AdaptedCallable<T>(callable);
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1445 */     s.defaultWriteObject();
/* 1446 */     s.writeObject(getException());
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1454 */     s.defaultReadObject();
/* 1455 */     Object ex = s.readObject();
/* 1456 */     if (ex != null)
/* 1457 */       setExceptionalCompletion((Throwable)ex); 
/*      */   }
/*      */   
/* 1465 */   private static final ReentrantLock exceptionTableLock = new ReentrantLock();
/*      */   
/* 1466 */   private static final ReferenceQueue<Object> exceptionTableRefQueue = new ReferenceQueue();
/*      */   
/*      */   private static final int EXCEPTION_MAP_CAPACITY = 32;
/*      */   
/*      */   private static final long serialVersionUID = -7721805057305804111L;
/*      */   
/*      */   private static final Unsafe U;
/*      */   
/*      */   private static final long STATUS;
/*      */   
/*      */   static {
/* 1467 */     exceptionTable = new ExceptionNode[32];
/*      */     try {
/* 1469 */       U = getUnsafe();
/* 1470 */       Class<?> k = ForkJoinTask.class;
/* 1471 */       STATUS = U.objectFieldOffset(k.getDeclaredField("status"));
/* 1473 */     } catch (Exception e) {
/* 1474 */       throw new Error(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Unsafe getUnsafe() {
/* 1486 */     return Unsafe.instance;
/*      */   }
/*      */   
/*      */   public abstract V getRawResult();
/*      */   
/*      */   protected abstract void setRawResult(V paramV);
/*      */   
/*      */   protected abstract boolean exec();
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\forkjoin\ForkJoinTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */