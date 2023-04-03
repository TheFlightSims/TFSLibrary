/*     */ package com.google.common.util.concurrent;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.base.Supplier;
/*     */ import com.google.common.base.Throwables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Queues;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.Delayed;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.RejectedExecutionException;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.ScheduledThreadPoolExecutor;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ public final class MoreExecutors {
/*     */   @Beta
/*     */   public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
/*  86 */     return (new Application()).getExitingExecutorService(executor, terminationTimeout, timeUnit);
/*     */   }
/*     */   
/*     */   @Beta
/*     */   public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
/* 109 */     return (new Application()).getExitingScheduledExecutorService(executor, terminationTimeout, timeUnit);
/*     */   }
/*     */   
/*     */   @Beta
/*     */   public static void addDelayedShutdownHook(ExecutorService service, long terminationTimeout, TimeUnit timeUnit) {
/* 127 */     (new Application()).addDelayedShutdownHook(service, terminationTimeout, timeUnit);
/*     */   }
/*     */   
/*     */   @Beta
/*     */   public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor) {
/* 148 */     return (new Application()).getExitingExecutorService(executor);
/*     */   }
/*     */   
/*     */   @Beta
/*     */   public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor) {
/* 169 */     return (new Application()).getExitingScheduledExecutorService(executor);
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   static class Application {
/*     */     final ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
/* 177 */       MoreExecutors.useDaemonThreadFactory(executor);
/* 178 */       ExecutorService service = Executors.unconfigurableExecutorService(executor);
/* 179 */       addDelayedShutdownHook(service, terminationTimeout, timeUnit);
/* 180 */       return service;
/*     */     }
/*     */     
/*     */     final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
/* 185 */       MoreExecutors.useDaemonThreadFactory(executor);
/* 186 */       ScheduledExecutorService service = Executors.unconfigurableScheduledExecutorService(executor);
/* 187 */       addDelayedShutdownHook(service, terminationTimeout, timeUnit);
/* 188 */       return service;
/*     */     }
/*     */     
/*     */     final void addDelayedShutdownHook(final ExecutorService service, final long terminationTimeout, final TimeUnit timeUnit) {
/* 193 */       Preconditions.checkNotNull(service);
/* 194 */       Preconditions.checkNotNull(timeUnit);
/* 195 */       addShutdownHook(MoreExecutors.newThread("DelayedShutdownHook-for-" + service, new Runnable() {
/*     */               public void run() {
/*     */                 try {
/* 204 */                   service.shutdown();
/* 205 */                   service.awaitTermination(terminationTimeout, timeUnit);
/* 206 */                 } catch (InterruptedException ignored) {}
/*     */               }
/*     */             }));
/*     */     }
/*     */     
/*     */     final ExecutorService getExitingExecutorService(ThreadPoolExecutor executor) {
/* 214 */       return getExitingExecutorService(executor, 120L, TimeUnit.SECONDS);
/*     */     }
/*     */     
/*     */     final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor) {
/* 219 */       return getExitingScheduledExecutorService(executor, 120L, TimeUnit.SECONDS);
/*     */     }
/*     */     
/*     */     @VisibleForTesting
/*     */     void addShutdownHook(Thread hook) {
/* 223 */       Runtime.getRuntime().addShutdownHook(hook);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void useDaemonThreadFactory(ThreadPoolExecutor executor) {
/* 228 */     executor.setThreadFactory((new ThreadFactoryBuilder()).setDaemon(true).setThreadFactory(executor.getThreadFactory()).build());
/*     */   }
/*     */   
/*     */   public static ListeningExecutorService sameThreadExecutor() {
/* 268 */     return new SameThreadExecutorService();
/*     */   }
/*     */   
/*     */   private static class SameThreadExecutorService extends AbstractListeningExecutorService {
/* 278 */     private final Lock lock = new ReentrantLock();
/*     */     
/* 281 */     private final Condition termination = this.lock.newCondition();
/*     */     
/* 290 */     private int runningTasks = 0;
/*     */     
/*     */     private boolean shutdown = false;
/*     */     
/*     */     public void execute(Runnable command) {
/* 295 */       startTask();
/*     */       try {
/* 297 */         command.run();
/*     */       } finally {
/* 299 */         endTask();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isShutdown() {
/* 305 */       this.lock.lock();
/*     */       try {
/* 307 */         return this.shutdown;
/*     */       } finally {
/* 309 */         this.lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void shutdown() {
/* 315 */       this.lock.lock();
/*     */       try {
/* 317 */         this.shutdown = true;
/*     */       } finally {
/* 319 */         this.lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public List<Runnable> shutdownNow() {
/* 326 */       shutdown();
/* 327 */       return Collections.emptyList();
/*     */     }
/*     */     
/*     */     public boolean isTerminated() {
/* 332 */       this.lock.lock();
/*     */       try {
/* 334 */         return (this.shutdown && this.runningTasks == 0);
/*     */       } finally {
/* 336 */         this.lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
/* 343 */       long nanos = unit.toNanos(timeout);
/* 344 */       this.lock.lock();
/*     */       try {
/*     */         while (true) {
/* 347 */           if (isTerminated())
/* 348 */             return true; 
/* 349 */           if (nanos <= 0L)
/* 350 */             return false; 
/* 352 */           nanos = this.termination.awaitNanos(nanos);
/*     */         } 
/*     */       } finally {
/* 356 */         this.lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     private void startTask() {
/* 368 */       this.lock.lock();
/*     */       try {
/* 370 */         if (isShutdown())
/* 371 */           throw new RejectedExecutionException("Executor already shutdown"); 
/* 373 */         this.runningTasks++;
/*     */       } finally {
/* 375 */         this.lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     private void endTask() {
/* 383 */       this.lock.lock();
/*     */       try {
/* 385 */         this.runningTasks--;
/* 386 */         if (isTerminated())
/* 387 */           this.termination.signalAll(); 
/*     */       } finally {
/* 390 */         this.lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     private SameThreadExecutorService() {}
/*     */   }
/*     */   
/*     */   public static ListeningExecutorService listeningDecorator(ExecutorService delegate) {
/* 415 */     return (delegate instanceof ListeningExecutorService) ? (ListeningExecutorService)delegate : ((delegate instanceof ScheduledExecutorService) ? new ScheduledListeningDecorator((ScheduledExecutorService)delegate) : new ListeningDecorator(delegate));
/*     */   }
/*     */   
/*     */   public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService delegate) {
/* 443 */     return (delegate instanceof ListeningScheduledExecutorService) ? (ListeningScheduledExecutorService)delegate : new ScheduledListeningDecorator(delegate);
/*     */   }
/*     */   
/*     */   private static class ListeningDecorator extends AbstractListeningExecutorService {
/*     */     private final ExecutorService delegate;
/*     */     
/*     */     ListeningDecorator(ExecutorService delegate) {
/* 453 */       this.delegate = (ExecutorService)Preconditions.checkNotNull(delegate);
/*     */     }
/*     */     
/*     */     public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
/* 459 */       return this.delegate.awaitTermination(timeout, unit);
/*     */     }
/*     */     
/*     */     public boolean isShutdown() {
/* 464 */       return this.delegate.isShutdown();
/*     */     }
/*     */     
/*     */     public boolean isTerminated() {
/* 469 */       return this.delegate.isTerminated();
/*     */     }
/*     */     
/*     */     public void shutdown() {
/* 474 */       this.delegate.shutdown();
/*     */     }
/*     */     
/*     */     public List<Runnable> shutdownNow() {
/* 479 */       return this.delegate.shutdownNow();
/*     */     }
/*     */     
/*     */     public void execute(Runnable command) {
/* 484 */       this.delegate.execute(command);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class ScheduledListeningDecorator extends ListeningDecorator implements ListeningScheduledExecutorService {
/*     */     final ScheduledExecutorService delegate;
/*     */     
/*     */     ScheduledListeningDecorator(ScheduledExecutorService delegate) {
/* 494 */       super(delegate);
/* 495 */       this.delegate = (ScheduledExecutorService)Preconditions.checkNotNull(delegate);
/*     */     }
/*     */     
/*     */     public ListenableScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
/* 501 */       ListenableFutureTask<Void> task = ListenableFutureTask.create(command, null);
/* 503 */       ScheduledFuture<?> scheduled = this.delegate.schedule(task, delay, unit);
/* 504 */       return new ListenableScheduledTask(task, scheduled);
/*     */     }
/*     */     
/*     */     public <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
/* 510 */       ListenableFutureTask<V> task = ListenableFutureTask.create(callable);
/* 511 */       ScheduledFuture<?> scheduled = this.delegate.schedule(task, delay, unit);
/* 512 */       return new ListenableScheduledTask<V>(task, scheduled);
/*     */     }
/*     */     
/*     */     public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
/* 518 */       NeverSuccessfulListenableFutureTask task = new NeverSuccessfulListenableFutureTask(command);
/* 520 */       ScheduledFuture<?> scheduled = this.delegate.scheduleAtFixedRate(task, initialDelay, period, unit);
/* 522 */       return new ListenableScheduledTask(task, scheduled);
/*     */     }
/*     */     
/*     */     public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
/* 528 */       NeverSuccessfulListenableFutureTask task = new NeverSuccessfulListenableFutureTask(command);
/* 530 */       ScheduledFuture<?> scheduled = this.delegate.scheduleWithFixedDelay(task, initialDelay, delay, unit);
/* 532 */       return new ListenableScheduledTask(task, scheduled);
/*     */     }
/*     */     
/*     */     private static final class ListenableScheduledTask<V> extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V> implements ListenableScheduledFuture<V> {
/*     */       private final ScheduledFuture<?> scheduledDelegate;
/*     */       
/*     */       public ListenableScheduledTask(ListenableFuture<V> listenableDelegate, ScheduledFuture<?> scheduledDelegate) {
/* 544 */         super(listenableDelegate);
/* 545 */         this.scheduledDelegate = scheduledDelegate;
/*     */       }
/*     */       
/*     */       public boolean cancel(boolean mayInterruptIfRunning) {
/* 550 */         boolean cancelled = super.cancel(mayInterruptIfRunning);
/* 551 */         if (cancelled)
/* 553 */           this.scheduledDelegate.cancel(mayInterruptIfRunning); 
/* 557 */         return cancelled;
/*     */       }
/*     */       
/*     */       public long getDelay(TimeUnit unit) {
/* 562 */         return this.scheduledDelegate.getDelay(unit);
/*     */       }
/*     */       
/*     */       public int compareTo(Delayed other) {
/* 567 */         return this.scheduledDelegate.compareTo(other);
/*     */       }
/*     */     }
/*     */     
/*     */     private static final class NeverSuccessfulListenableFutureTask extends AbstractFuture<Void> implements Runnable {
/*     */       private final Runnable delegate;
/*     */       
/*     */       public NeverSuccessfulListenableFutureTask(Runnable delegate) {
/* 577 */         this.delegate = (Runnable)Preconditions.checkNotNull(delegate);
/*     */       }
/*     */       
/*     */       public void run() {
/*     */         try {
/* 582 */           this.delegate.run();
/* 583 */         } catch (Throwable t) {
/* 584 */           setException(t);
/* 585 */           throw Throwables.propagate(t);
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   static <T> T invokeAnyImpl(ListeningExecutorService executorService, Collection<? extends Callable<T>> tasks, boolean timed, long nanos) throws InterruptedException, ExecutionException, TimeoutException {
/* 609 */     Preconditions.checkNotNull(executorService);
/* 610 */     int ntasks = tasks.size();
/* 611 */     Preconditions.checkArgument((ntasks > 0));
/* 612 */     List<Future<T>> futures = Lists.newArrayListWithCapacity(ntasks);
/* 613 */     BlockingQueue<Future<T>> futureQueue = Queues.newLinkedBlockingQueue();
/*     */   }
/*     */   
/*     */   private static <T> ListenableFuture<T> submitAndAddQueueListener(ListeningExecutorService executorService, Callable<T> task, final BlockingQueue<Future<T>> queue) {
/* 682 */     final ListenableFuture<T> future = executorService.submit(task);
/* 683 */     future.addListener(new Runnable() {
/*     */           public void run() {
/* 685 */             queue.add(future);
/*     */           }
/*     */         },  sameThreadExecutor());
/* 688 */     return future;
/*     */   }
/*     */   
/*     */   @Beta
/*     */   public static ThreadFactory platformThreadFactory() {
/* 701 */     if (!isAppEngine())
/* 702 */       return Executors.defaultThreadFactory(); 
/*     */     try {
/* 705 */       return (ThreadFactory)Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", new Class[0]).invoke(null, new Object[0]);
/* 708 */     } catch (IllegalAccessException e) {
/* 709 */       throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e);
/* 710 */     } catch (ClassNotFoundException e) {
/* 711 */       throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e);
/* 712 */     } catch (NoSuchMethodException e) {
/* 713 */       throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e);
/* 714 */     } catch (InvocationTargetException e) {
/* 715 */       throw Throwables.propagate(e.getCause());
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean isAppEngine() {
/* 720 */     if (System.getProperty("com.google.appengine.runtime.environment") == null)
/* 721 */       return false; 
/*     */     try {
/* 725 */       return (Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment", new Class[0]).invoke(null, new Object[0]) != null);
/* 728 */     } catch (ClassNotFoundException e) {
/* 730 */       return false;
/* 731 */     } catch (InvocationTargetException e) {
/* 733 */       return false;
/* 734 */     } catch (IllegalAccessException e) {
/* 736 */       return false;
/* 737 */     } catch (NoSuchMethodException e) {
/* 739 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   static Thread newThread(String name, Runnable runnable) {
/* 748 */     Preconditions.checkNotNull(name);
/* 749 */     Preconditions.checkNotNull(runnable);
/* 750 */     Thread result = platformThreadFactory().newThread(runnable);
/*     */     try {
/* 752 */       result.setName(name);
/* 753 */     } catch (SecurityException e) {}
/* 756 */     return result;
/*     */   }
/*     */   
/*     */   static Executor renamingDecorator(final Executor executor, final Supplier<String> nameSupplier) {
/* 775 */     Preconditions.checkNotNull(executor);
/* 776 */     Preconditions.checkNotNull(nameSupplier);
/* 777 */     if (isAppEngine())
/* 779 */       return executor; 
/* 781 */     return new Executor() {
/*     */         public void execute(Runnable command) {
/* 783 */           executor.execute(Callables.threadRenaming(command, nameSupplier));
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   static ExecutorService renamingDecorator(ExecutorService service, final Supplier<String> nameSupplier) {
/* 802 */     Preconditions.checkNotNull(service);
/* 803 */     Preconditions.checkNotNull(nameSupplier);
/* 804 */     if (isAppEngine())
/* 806 */       return service; 
/* 808 */     return new WrappingExecutorService(service) {
/*     */         protected <T> Callable<T> wrapTask(Callable<T> callable) {
/* 810 */           return Callables.threadRenaming(callable, nameSupplier);
/*     */         }
/*     */         
/*     */         protected Runnable wrapTask(Runnable command) {
/* 813 */           return Callables.threadRenaming(command, nameSupplier);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   static ScheduledExecutorService renamingDecorator(ScheduledExecutorService service, final Supplier<String> nameSupplier) {
/* 832 */     Preconditions.checkNotNull(service);
/* 833 */     Preconditions.checkNotNull(nameSupplier);
/* 834 */     if (isAppEngine())
/* 836 */       return service; 
/* 838 */     return new WrappingScheduledExecutorService(service) {
/*     */         protected <T> Callable<T> wrapTask(Callable<T> callable) {
/* 840 */           return Callables.threadRenaming(callable, nameSupplier);
/*     */         }
/*     */         
/*     */         protected Runnable wrapTask(Runnable command) {
/* 843 */           return Callables.threadRenaming(command, nameSupplier);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   @Beta
/*     */   public static boolean shutdownAndAwaitTermination(ExecutorService service, long timeout, TimeUnit unit) {
/* 875 */     Preconditions.checkNotNull(unit);
/* 877 */     service.shutdown();
/*     */     try {
/* 879 */       long halfTimeoutNanos = TimeUnit.NANOSECONDS.convert(timeout, unit) / 2L;
/* 881 */       if (!service.awaitTermination(halfTimeoutNanos, TimeUnit.NANOSECONDS)) {
/* 883 */         service.shutdownNow();
/* 885 */         service.awaitTermination(halfTimeoutNanos, TimeUnit.NANOSECONDS);
/*     */       } 
/* 887 */     } catch (InterruptedException ie) {
/* 889 */       Thread.currentThread().interrupt();
/* 891 */       service.shutdownNow();
/*     */     } 
/* 893 */     return service.isTerminated();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\commo\\util\concurrent\MoreExecutors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */