/*      */ package com.google.common.util.concurrent;
/*      */ 
/*      */ import com.google.common.annotations.Beta;
/*      */ import com.google.common.base.Function;
/*      */ import com.google.common.base.Optional;
/*      */ import com.google.common.base.Preconditions;
/*      */ import com.google.common.collect.ImmutableCollection;
/*      */ import com.google.common.collect.ImmutableList;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Ordering;
/*      */ import com.google.common.collect.Queues;
/*      */ import com.google.common.collect.Sets;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.UndeclaredThrowableException;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.CancellationException;
/*      */ import java.util.concurrent.ConcurrentLinkedQueue;
/*      */ import java.util.concurrent.CountDownLatch;
/*      */ import java.util.concurrent.ExecutionException;
/*      */ import java.util.concurrent.Executor;
/*      */ import java.util.concurrent.Future;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.concurrent.TimeoutException;
/*      */ import java.util.concurrent.atomic.AtomicInteger;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.annotation.Nullable;
/*      */ 
/*      */ @Beta
/*      */ public final class Futures {
/*      */   public static <V, X extends Exception> CheckedFuture<V, X> makeChecked(ListenableFuture<V> future, Function<Exception, X> mapper) {
/*   90 */     return new MappingCheckedFuture<V, X>((ListenableFuture<V>)Preconditions.checkNotNull(future), mapper);
/*      */   }
/*      */   
/*      */   private static abstract class ImmediateFuture<V> implements ListenableFuture<V> {
/*      */     private ImmediateFuture() {}
/*      */     
/*   96 */     private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());
/*      */     
/*      */     public void addListener(Runnable listener, Executor executor) {
/*  101 */       Preconditions.checkNotNull(listener, "Runnable was null.");
/*  102 */       Preconditions.checkNotNull(executor, "Executor was null.");
/*      */       try {
/*  104 */         executor.execute(listener);
/*  105 */       } catch (RuntimeException e) {
/*  108 */         log.log(Level.SEVERE, "RuntimeException while executing runnable " + listener + " with executor " + executor, e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean cancel(boolean mayInterruptIfRunning) {
/*  115 */       return false;
/*      */     }
/*      */     
/*      */     public V get(long timeout, TimeUnit unit) throws ExecutionException {
/*  123 */       Preconditions.checkNotNull(unit);
/*  124 */       return get();
/*      */     }
/*      */     
/*      */     public boolean isCancelled() {
/*  129 */       return false;
/*      */     }
/*      */     
/*      */     public boolean isDone() {
/*  134 */       return true;
/*      */     }
/*      */     
/*      */     public abstract V get() throws ExecutionException;
/*      */   }
/*      */   
/*      */   private static class ImmediateSuccessfulFuture<V> extends ImmediateFuture<V> {
/*      */     @Nullable
/*      */     private final V value;
/*      */     
/*      */     ImmediateSuccessfulFuture(@Nullable V value) {
/*  143 */       this.value = value;
/*      */     }
/*      */     
/*      */     public V get() {
/*  148 */       return this.value;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ImmediateSuccessfulCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
/*      */     @Nullable
/*      */     private final V value;
/*      */     
/*      */     ImmediateSuccessfulCheckedFuture(@Nullable V value) {
/*  158 */       this.value = value;
/*      */     }
/*      */     
/*      */     public V get() {
/*  163 */       return this.value;
/*      */     }
/*      */     
/*      */     public V checkedGet() {
/*  168 */       return this.value;
/*      */     }
/*      */     
/*      */     public V checkedGet(long timeout, TimeUnit unit) {
/*  173 */       Preconditions.checkNotNull(unit);
/*  174 */       return this.value;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ImmediateFailedFuture<V> extends ImmediateFuture<V> {
/*      */     private final Throwable thrown;
/*      */     
/*      */     ImmediateFailedFuture(Throwable thrown) {
/*  183 */       this.thrown = thrown;
/*      */     }
/*      */     
/*      */     public V get() throws ExecutionException {
/*  188 */       throw new ExecutionException(this.thrown);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ImmediateCancelledFuture<V> extends ImmediateFuture<V> {
/*      */     private final CancellationException thrown;
/*      */     
/*      */     ImmediateCancelledFuture() {
/*  197 */       this.thrown = new CancellationException("Immediate cancelled future.");
/*      */     }
/*      */     
/*      */     public boolean isCancelled() {
/*  202 */       return true;
/*      */     }
/*      */     
/*      */     public V get() {
/*  207 */       throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", this.thrown);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ImmediateFailedCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
/*      */     private final X thrown;
/*      */     
/*      */     ImmediateFailedCheckedFuture(X thrown) {
/*  218 */       this.thrown = thrown;
/*      */     }
/*      */     
/*      */     public V get() throws ExecutionException {
/*  223 */       throw new ExecutionException(this.thrown);
/*      */     }
/*      */     
/*      */     public V checkedGet() throws X {
/*  228 */       throw this.thrown;
/*      */     }
/*      */     
/*      */     public V checkedGet(long timeout, TimeUnit unit) throws X {
/*  233 */       Preconditions.checkNotNull(unit);
/*  234 */       throw this.thrown;
/*      */     }
/*      */   }
/*      */   
/*      */   public static <V> ListenableFuture<V> immediateFuture(@Nullable V value) {
/*  245 */     return new ImmediateSuccessfulFuture<V>(value);
/*      */   }
/*      */   
/*      */   public static <V, X extends Exception> CheckedFuture<V, X> immediateCheckedFuture(@Nullable V value) {
/*  258 */     return new ImmediateSuccessfulCheckedFuture<V, X>(value);
/*      */   }
/*      */   
/*      */   public static <V> ListenableFuture<V> immediateFailedFuture(Throwable throwable) {
/*  272 */     Preconditions.checkNotNull(throwable);
/*  273 */     return new ImmediateFailedFuture<V>(throwable);
/*      */   }
/*      */   
/*      */   public static <V> ListenableFuture<V> immediateCancelledFuture() {
/*  283 */     return new ImmediateCancelledFuture<V>();
/*      */   }
/*      */   
/*      */   public static <V, X extends Exception> CheckedFuture<V, X> immediateFailedCheckedFuture(X exception) {
/*  298 */     Preconditions.checkNotNull(exception);
/*  299 */     return new ImmediateFailedCheckedFuture<V, X>(exception);
/*      */   }
/*      */   
/*      */   public static <V> ListenableFuture<V> withFallback(ListenableFuture<? extends V> input, FutureFallback<? extends V> fallback) {
/*  377 */     return withFallback(input, fallback, MoreExecutors.sameThreadExecutor());
/*      */   }
/*      */   
/*      */   public static <V> ListenableFuture<V> withFallback(ListenableFuture<? extends V> input, FutureFallback<? extends V> fallback, Executor executor) {
/*  441 */     Preconditions.checkNotNull(fallback);
/*  442 */     return new FallbackFuture<V>(input, fallback, executor);
/*      */   }
/*      */   
/*      */   private static class FallbackFuture<V> extends AbstractFuture<V> {
/*      */     private volatile ListenableFuture<? extends V> running;
/*      */     
/*      */     FallbackFuture(ListenableFuture<? extends V> input, final FutureFallback<? extends V> fallback, Executor executor) {
/*  456 */       this.running = input;
/*  457 */       Futures.addCallback(this.running, new FutureCallback<V>() {
/*      */             public void onSuccess(V value) {
/*  460 */               Futures.FallbackFuture.this.set(value);
/*      */             }
/*      */             
/*      */             public void onFailure(Throwable t) {
/*  465 */               if (Futures.FallbackFuture.this.isCancelled())
/*      */                 return; 
/*      */               try {
/*  469 */                 Futures.FallbackFuture.this.running = fallback.create(t);
/*  470 */                 if (Futures.FallbackFuture.this.isCancelled()) {
/*  471 */                   Futures.FallbackFuture.this.running.cancel(Futures.FallbackFuture.this.wasInterrupted());
/*      */                   return;
/*      */                 } 
/*  474 */                 Futures.addCallback(Futures.FallbackFuture.this.running, new FutureCallback<V>() {
/*      */                       public void onSuccess(V value) {
/*  477 */                         Futures.FallbackFuture.this.set(value);
/*      */                       }
/*      */                       
/*      */                       public void onFailure(Throwable t) {
/*  482 */                         if (Futures.FallbackFuture.this.running.isCancelled()) {
/*  483 */                           Futures.FallbackFuture.this.cancel(false);
/*      */                         } else {
/*  485 */                           Futures.FallbackFuture.this.setException(t);
/*      */                         } 
/*      */                       }
/*      */                     },  MoreExecutors.sameThreadExecutor());
/*  489 */               } catch (Throwable e) {
/*  490 */                 Futures.FallbackFuture.this.setException(e);
/*      */               } 
/*      */             }
/*      */           }executor);
/*      */     }
/*      */     
/*      */     public boolean cancel(boolean mayInterruptIfRunning) {
/*  498 */       if (super.cancel(mayInterruptIfRunning)) {
/*  499 */         this.running.cancel(mayInterruptIfRunning);
/*  500 */         return true;
/*      */       } 
/*  502 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> input, AsyncFunction<? super I, ? extends O> function) {
/*  563 */     return transform(input, function, MoreExecutors.sameThreadExecutor());
/*      */   }
/*      */   
/*      */   public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> input, AsyncFunction<? super I, ? extends O> function, Executor executor) {
/*  608 */     ChainingListenableFuture<I, O> output = new ChainingListenableFuture<I, O>(function, input);
/*  610 */     input.addListener(output, executor);
/*  611 */     return output;
/*      */   }
/*      */   
/*      */   public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> input, Function<? super I, ? extends O> function) {
/*  669 */     return transform(input, function, MoreExecutors.sameThreadExecutor());
/*      */   }
/*      */   
/*      */   public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> input, final Function<? super I, ? extends O> function, Executor executor) {
/*  711 */     Preconditions.checkNotNull(function);
/*  712 */     AsyncFunction<I, O> wrapperFunction = new AsyncFunction<I, O>() {
/*      */         public ListenableFuture<O> apply(I input) {
/*  715 */           O output = (O)function.apply(input);
/*  716 */           return Futures.immediateFuture(output);
/*      */         }
/*      */       };
/*  719 */     return transform(input, wrapperFunction, executor);
/*      */   }
/*      */   
/*      */   public static <I, O> Future<O> lazyTransform(final Future<I> input, final Function<? super I, ? extends O> function) {
/*  747 */     Preconditions.checkNotNull(input);
/*  748 */     Preconditions.checkNotNull(function);
/*  749 */     return new Future<O>() {
/*      */         public boolean cancel(boolean mayInterruptIfRunning) {
/*  753 */           return input.cancel(mayInterruptIfRunning);
/*      */         }
/*      */         
/*      */         public boolean isCancelled() {
/*  758 */           return input.isCancelled();
/*      */         }
/*      */         
/*      */         public boolean isDone() {
/*  763 */           return input.isDone();
/*      */         }
/*      */         
/*      */         public O get() throws InterruptedException, ExecutionException {
/*  768 */           return applyTransformation(input.get());
/*      */         }
/*      */         
/*      */         public O get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
/*  774 */           return applyTransformation(input.get(timeout, unit));
/*      */         }
/*      */         
/*      */         private O applyTransformation(I input) throws ExecutionException {
/*      */           try {
/*  779 */             return (O)function.apply(input);
/*  780 */           } catch (Throwable t) {
/*  781 */             throw new ExecutionException(t);
/*      */           } 
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   private static class ChainingListenableFuture<I, O> extends AbstractFuture<O> implements Runnable {
/*      */     private AsyncFunction<? super I, ? extends O> function;
/*      */     
/*      */     private ListenableFuture<? extends I> inputFuture;
/*      */     
/*      */     private volatile ListenableFuture<? extends O> outputFuture;
/*      */     
/*  813 */     private final CountDownLatch outputCreated = new CountDownLatch(1);
/*      */     
/*      */     private ChainingListenableFuture(AsyncFunction<? super I, ? extends O> function, ListenableFuture<? extends I> inputFuture) {
/*  818 */       this.function = (AsyncFunction<? super I, ? extends O>)Preconditions.checkNotNull(function);
/*  819 */       this.inputFuture = (ListenableFuture<? extends I>)Preconditions.checkNotNull(inputFuture);
/*      */     }
/*      */     
/*      */     public boolean cancel(boolean mayInterruptIfRunning) {
/*  828 */       if (super.cancel(mayInterruptIfRunning)) {
/*  831 */         cancel(this.inputFuture, mayInterruptIfRunning);
/*  832 */         cancel(this.outputFuture, mayInterruptIfRunning);
/*  833 */         return true;
/*      */       } 
/*  835 */       return false;
/*      */     }
/*      */     
/*      */     private void cancel(@Nullable Future<?> future, boolean mayInterruptIfRunning) {
/*  840 */       if (future != null)
/*  841 */         future.cancel(mayInterruptIfRunning); 
/*      */     }
/*      */     
/*      */     public void run() {
/*      */       try {
/*      */         I sourceResult;
/*      */         try {
/*  850 */           sourceResult = Uninterruptibles.getUninterruptibly((Future)this.inputFuture);
/*  851 */         } catch (CancellationException e) {
/*  855 */           cancel(false);
/*      */           return;
/*  857 */         } catch (ExecutionException e) {
/*  859 */           setException(e.getCause());
/*      */           return;
/*      */         } 
/*  863 */         final ListenableFuture<? extends O> outputFuture = this.outputFuture = (ListenableFuture<? extends O>)Preconditions.checkNotNull(this.function.apply(sourceResult), "AsyncFunction may not return null.");
/*  866 */         if (isCancelled()) {
/*  867 */           outputFuture.cancel(wasInterrupted());
/*  868 */           this.outputFuture = null;
/*      */           return;
/*      */         } 
/*  871 */         outputFuture.addListener(new Runnable() {
/*      */               public void run() {
/*      */                 try {
/*  875 */                   Futures.ChainingListenableFuture.this.set(Uninterruptibles.getUninterruptibly(outputFuture));
/*  876 */                 } catch (CancellationException e) {
/*  880 */                   Futures.ChainingListenableFuture.this.cancel(false);
/*      */                   return;
/*  882 */                 } catch (ExecutionException e) {
/*  884 */                   Futures.ChainingListenableFuture.this.setException(e.getCause());
/*      */                 } finally {
/*  887 */                   Futures.ChainingListenableFuture.this.outputFuture = null;
/*      */                 } 
/*      */               }
/*      */             },  MoreExecutors.sameThreadExecutor());
/*  891 */       } catch (UndeclaredThrowableException e) {
/*  893 */         setException(e.getCause());
/*  894 */       } catch (Throwable t) {
/*  897 */         setException(t);
/*      */       } finally {
/*  900 */         this.function = null;
/*  901 */         this.inputFuture = null;
/*  903 */         this.outputCreated.countDown();
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static <V> ListenableFuture<V> dereference(ListenableFuture<? extends ListenableFuture<? extends V>> nested) {
/*  932 */     return transform(nested, (AsyncFunction)DEREFERENCER);
/*      */   }
/*      */   
/*  938 */   private static final AsyncFunction<ListenableFuture<Object>, Object> DEREFERENCER = new AsyncFunction<ListenableFuture<Object>, Object>() {
/*      */       public ListenableFuture<Object> apply(ListenableFuture<Object> input) {
/*  941 */         return input;
/*      */       }
/*      */     };
/*      */   
/*      */   @Beta
/*      */   public static <V> ListenableFuture<List<V>> allAsList(ListenableFuture<? extends V>... futures) {
/*  964 */     return listFuture(ImmutableList.copyOf((Object[])futures), true, MoreExecutors.sameThreadExecutor());
/*      */   }
/*      */   
/*      */   @Beta
/*      */   public static <V> ListenableFuture<List<V>> allAsList(Iterable<? extends ListenableFuture<? extends V>> futures) {
/*  987 */     return listFuture(ImmutableList.copyOf(futures), true, MoreExecutors.sameThreadExecutor());
/*      */   }
/*      */   
/*      */   public static <V> ListenableFuture<V> nonCancellationPropagating(ListenableFuture<V> future) {
/* 1001 */     return new NonCancellationPropagatingFuture<V>(future);
/*      */   }
/*      */   
/*      */   private static class NonCancellationPropagatingFuture<V> extends AbstractFuture<V> {
/*      */     NonCancellationPropagatingFuture(final ListenableFuture<V> delegate) {
/* 1010 */       Preconditions.checkNotNull(delegate);
/* 1011 */       Futures.addCallback(delegate, new FutureCallback<V>() {
/*      */             public void onSuccess(V result) {
/* 1014 */               Futures.NonCancellationPropagatingFuture.this.set(result);
/*      */             }
/*      */             
/*      */             public void onFailure(Throwable t) {
/* 1019 */               if (delegate.isCancelled()) {
/* 1020 */                 Futures.NonCancellationPropagatingFuture.this.cancel(false);
/*      */               } else {
/* 1022 */                 Futures.NonCancellationPropagatingFuture.this.setException(t);
/*      */               } 
/*      */             }
/*      */           },  MoreExecutors.sameThreadExecutor());
/*      */     }
/*      */   }
/*      */   
/*      */   @Beta
/*      */   public static <V> ListenableFuture<List<V>> successfulAsList(ListenableFuture<? extends V>... futures) {
/* 1047 */     return listFuture(ImmutableList.copyOf((Object[])futures), false, MoreExecutors.sameThreadExecutor());
/*      */   }
/*      */   
/*      */   @Beta
/*      */   public static <V> ListenableFuture<List<V>> successfulAsList(Iterable<? extends ListenableFuture<? extends V>> futures) {
/* 1069 */     return listFuture(ImmutableList.copyOf(futures), false, MoreExecutors.sameThreadExecutor());
/*      */   }
/*      */   
/*      */   @Beta
/*      */   public static <T> ImmutableList<ListenableFuture<T>> inCompletionOrder(Iterable<? extends ListenableFuture<? extends T>> futures) {
/* 1091 */     final ConcurrentLinkedQueue<AsyncSettableFuture<T>> delegates = Queues.newConcurrentLinkedQueue();
/* 1093 */     ImmutableList.Builder<ListenableFuture<T>> listBuilder = ImmutableList.builder();
/* 1104 */     SerializingExecutor executor = new SerializingExecutor(MoreExecutors.sameThreadExecutor());
/* 1105 */     for (ListenableFuture<? extends T> future : futures) {
/* 1106 */       AsyncSettableFuture<T> delegate = AsyncSettableFuture.create();
/* 1108 */       delegates.add(delegate);
/* 1109 */       future.addListener(new Runnable() {
/*      */             public void run() {
/* 1111 */               ((AsyncSettableFuture)delegates.remove()).setFuture(future);
/*      */             }
/*      */           },  executor);
/* 1114 */       listBuilder.add(delegate);
/*      */     } 
/* 1116 */     return listBuilder.build();
/*      */   }
/*      */   
/*      */   public static <V> void addCallback(ListenableFuture<V> future, FutureCallback<? super V> callback) {
/* 1170 */     addCallback(future, callback, MoreExecutors.sameThreadExecutor());
/*      */   }
/*      */   
/*      */   public static <V> void addCallback(final ListenableFuture<V> future, final FutureCallback<? super V> callback, Executor executor) {
/* 1212 */     Preconditions.checkNotNull(callback);
/* 1213 */     Runnable callbackListener = new Runnable() {
/*      */         public void run() {
/*      */           V value;
/*      */           try {
/* 1220 */             value = Uninterruptibles.getUninterruptibly(future);
/* 1221 */           } catch (ExecutionException e) {
/* 1222 */             callback.onFailure(e.getCause());
/*      */             return;
/* 1224 */           } catch (RuntimeException e) {
/* 1225 */             callback.onFailure(e);
/*      */             return;
/* 1227 */           } catch (Error e) {
/* 1228 */             callback.onFailure(e);
/*      */             return;
/*      */           } 
/* 1231 */           callback.onSuccess(value);
/*      */         }
/*      */       };
/* 1234 */     future.addListener(callbackListener, executor);
/*      */   }
/*      */   
/*      */   public static <V, X extends Exception> V get(Future<V> future, Class<X> exceptionClass) throws X {
/* 1286 */     Preconditions.checkNotNull(future);
/* 1287 */     Preconditions.checkArgument(!RuntimeException.class.isAssignableFrom(exceptionClass), "Futures.get exception type (%s) must not be a RuntimeException", new Object[] { exceptionClass });
/*      */     try {
/* 1291 */       return future.get();
/* 1292 */     } catch (InterruptedException e) {
/* 1293 */       Thread.currentThread().interrupt();
/* 1294 */       throw newWithCause(exceptionClass, e);
/* 1295 */     } catch (ExecutionException e) {
/* 1296 */       wrapAndThrowExceptionOrError(e.getCause(), exceptionClass);
/* 1297 */       throw (X)new AssertionError();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static <V, X extends Exception> V get(Future<V> future, long timeout, TimeUnit unit, Class<X> exceptionClass) throws X {
/* 1352 */     Preconditions.checkNotNull(future);
/* 1353 */     Preconditions.checkNotNull(unit);
/* 1354 */     Preconditions.checkArgument(!RuntimeException.class.isAssignableFrom(exceptionClass), "Futures.get exception type (%s) must not be a RuntimeException", new Object[] { exceptionClass });
/*      */     try {
/* 1358 */       return future.get(timeout, unit);
/* 1359 */     } catch (InterruptedException e) {
/* 1360 */       Thread.currentThread().interrupt();
/* 1361 */       throw newWithCause(exceptionClass, e);
/* 1362 */     } catch (TimeoutException e) {
/* 1363 */       throw newWithCause(exceptionClass, e);
/* 1364 */     } catch (ExecutionException e) {
/* 1365 */       wrapAndThrowExceptionOrError(e.getCause(), exceptionClass);
/* 1366 */       throw (X)new AssertionError();
/*      */     } 
/*      */   }
/*      */   
/*      */   private static <X extends Exception> void wrapAndThrowExceptionOrError(Throwable cause, Class<X> exceptionClass) throws X {
/* 1372 */     if (cause instanceof Error)
/* 1373 */       throw (X)new ExecutionError((Error)cause); 
/* 1375 */     if (cause instanceof RuntimeException)
/* 1376 */       throw (X)new UncheckedExecutionException(cause); 
/* 1378 */     throw newWithCause(exceptionClass, cause);
/*      */   }
/*      */   
/*      */   public static <V> V getUnchecked(Future<V> future) {
/* 1419 */     Preconditions.checkNotNull(future);
/*      */     try {
/* 1421 */       return Uninterruptibles.getUninterruptibly(future);
/* 1422 */     } catch (ExecutionException e) {
/* 1423 */       wrapAndThrowUnchecked(e.getCause());
/* 1424 */       throw new AssertionError();
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void wrapAndThrowUnchecked(Throwable cause) {
/* 1429 */     if (cause instanceof Error)
/* 1430 */       throw new ExecutionError((Error)cause); 
/* 1437 */     throw new UncheckedExecutionException(cause);
/*      */   }
/*      */   
/*      */   private static <X extends Exception> X newWithCause(Class<X> exceptionClass, Throwable cause) {
/* 1461 */     List<Constructor<X>> constructors = (List)Arrays.asList(exceptionClass.getConstructors());
/* 1463 */     for (Constructor<X> constructor : preferringStrings(constructors)) {
/* 1464 */       Exception exception = newFromConstructor(constructor, cause);
/* 1465 */       if (exception != null) {
/* 1466 */         if (exception.getCause() == null)
/* 1467 */           exception.initCause(cause); 
/* 1469 */         return (X)exception;
/*      */       } 
/*      */     } 
/* 1472 */     throw new IllegalArgumentException("No appropriate constructor for exception of type " + exceptionClass + " in response to chained exception", cause);
/*      */   }
/*      */   
/*      */   private static <X extends Exception> List<Constructor<X>> preferringStrings(List<Constructor<X>> constructors) {
/* 1479 */     return WITH_STRING_PARAM_FIRST.sortedCopy(constructors);
/*      */   }
/*      */   
/* 1482 */   private static final Ordering<Constructor<?>> WITH_STRING_PARAM_FIRST = Ordering.natural().onResultOf(new Function<Constructor<?>, Boolean>() {
/*      */         public Boolean apply(Constructor<?> input) {
/* 1485 */           return Boolean.valueOf(Arrays.<Class<?>>asList(input.getParameterTypes()).contains(String.class));
/*      */         }
/*      */       }).reverse();
/*      */   
/*      */   @Nullable
/*      */   private static <X> X newFromConstructor(Constructor<X> constructor, Throwable cause) {
/* 1491 */     Class<?>[] paramTypes = constructor.getParameterTypes();
/* 1492 */     Object[] params = new Object[paramTypes.length];
/* 1493 */     for (int i = 0; i < paramTypes.length; i++) {
/* 1494 */       Class<?> paramType = paramTypes[i];
/* 1495 */       if (paramType.equals(String.class)) {
/* 1496 */         params[i] = cause.toString();
/* 1497 */       } else if (paramType.equals(Throwable.class)) {
/* 1498 */         params[i] = cause;
/*      */       } else {
/* 1500 */         return null;
/*      */       } 
/*      */     } 
/*      */     try {
/* 1504 */       return constructor.newInstance(params);
/* 1505 */     } catch (IllegalArgumentException e) {
/* 1506 */       return null;
/* 1507 */     } catch (InstantiationException e) {
/* 1508 */       return null;
/* 1509 */     } catch (IllegalAccessException e) {
/* 1510 */       return null;
/* 1511 */     } catch (InvocationTargetException e) {
/* 1512 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static class CombinedFuture<V, C> extends AbstractFuture<C> {
/* 1521 */     private static final Logger logger = Logger.getLogger(CombinedFuture.class.getName());
/*      */     
/*      */     ImmutableCollection<? extends ListenableFuture<? extends V>> futures;
/*      */     
/*      */     final boolean allMustSucceed;
/*      */     
/*      */     final AtomicInteger remaining;
/*      */     
/*      */     Futures.FutureCombiner<V, C> combiner;
/*      */     
/*      */     List<Optional<V>> values;
/*      */     
/* 1529 */     final Object seenExceptionsLock = new Object();
/*      */     
/*      */     Set<Throwable> seenExceptions;
/*      */     
/*      */     CombinedFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed, Executor listenerExecutor, Futures.FutureCombiner<V, C> combiner) {
/* 1536 */       this.futures = futures;
/* 1537 */       this.allMustSucceed = allMustSucceed;
/* 1538 */       this.remaining = new AtomicInteger(futures.size());
/* 1539 */       this.combiner = combiner;
/* 1540 */       this.values = Lists.newArrayListWithCapacity(futures.size());
/* 1541 */       init(listenerExecutor);
/*      */     }
/*      */     
/*      */     protected void init(Executor listenerExecutor) {
/* 1549 */       addListener(new Runnable() {
/*      */             public void run() {
/* 1553 */               if (Futures.CombinedFuture.this.isCancelled())
/* 1554 */                 for (ListenableFuture<?> future : (Iterable<ListenableFuture<?>>)Futures.CombinedFuture.this.futures)
/* 1555 */                   future.cancel(Futures.CombinedFuture.this.wasInterrupted());  
/* 1560 */               Futures.CombinedFuture.this.futures = null;
/* 1564 */               Futures.CombinedFuture.this.values = null;
/* 1567 */               Futures.CombinedFuture.this.combiner = null;
/*      */             }
/*      */           },  MoreExecutors.sameThreadExecutor());
/* 1574 */       if (this.futures.isEmpty()) {
/* 1575 */         set(this.combiner.combine((List<Optional<V>>)ImmutableList.of()));
/*      */         return;
/*      */       } 
/*      */       int i;
/* 1580 */       for (i = 0; i < this.futures.size(); i++)
/* 1581 */         this.values.add(null); 
/* 1592 */       i = 0;
/* 1593 */       for (ListenableFuture<? extends V> listenable : this.futures) {
/* 1594 */         final int index = i++;
/* 1595 */         listenable.addListener(new Runnable() {
/*      */               public void run() {
/* 1598 */                 Futures.CombinedFuture.this.setOneValue(index, listenable);
/*      */               }
/*      */             }listenerExecutor);
/*      */       } 
/*      */     }
/*      */     
/*      */     private void setExceptionAndMaybeLog(Throwable throwable) {
/* 1611 */       boolean visibleFromOutputFuture = false;
/* 1612 */       boolean firstTimeSeeingThisException = true;
/* 1613 */       if (this.allMustSucceed) {
/* 1616 */         visibleFromOutputFuture = setException(throwable);
/* 1618 */         synchronized (this.seenExceptionsLock) {
/* 1619 */           if (this.seenExceptions == null)
/* 1620 */             this.seenExceptions = Sets.newHashSet(); 
/* 1622 */           firstTimeSeeingThisException = this.seenExceptions.add(throwable);
/*      */         } 
/*      */       } 
/* 1626 */       if (throwable instanceof Error || (this.allMustSucceed && !visibleFromOutputFuture && firstTimeSeeingThisException))
/* 1628 */         logger.log(Level.SEVERE, "input future failed.", throwable); 
/*      */     }
/*      */     
/*      */     private void setOneValue(int index, Future<? extends V> future) {
/* 1636 */       List<Optional<V>> localValues = this.values;
/* 1644 */       if (isDone() || localValues == null)
/* 1649 */         Preconditions.checkState((this.allMustSucceed || isCancelled()), "Future was done before all dependencies completed"); 
/*      */       try {
/* 1654 */         Preconditions.checkState(future.isDone(), "Tried to set value from future which is not done");
/* 1656 */         V returnValue = Uninterruptibles.getUninterruptibly((Future)future);
/* 1657 */         if (localValues != null)
/* 1658 */           localValues.set(index, Optional.fromNullable(returnValue)); 
/* 1660 */       } catch (CancellationException e) {
/* 1661 */         if (this.allMustSucceed)
/* 1664 */           cancel(false); 
/* 1666 */       } catch (ExecutionException e) {
/* 1667 */         setExceptionAndMaybeLog(e.getCause());
/* 1668 */       } catch (Throwable t) {
/* 1669 */         setExceptionAndMaybeLog(t);
/*      */       } finally {
/* 1671 */         int newRemaining = this.remaining.decrementAndGet();
/* 1672 */         Preconditions.checkState((newRemaining >= 0), "Less than 0 remaining futures");
/* 1673 */         if (newRemaining == 0) {
/* 1674 */           Futures.FutureCombiner<V, C> localCombiner = this.combiner;
/* 1675 */           if (localCombiner != null && localValues != null) {
/* 1676 */             set(localCombiner.combine(localValues));
/*      */           } else {
/* 1678 */             Preconditions.checkState(isDone());
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static <V> ListenableFuture<List<V>> listFuture(ImmutableList<ListenableFuture<? extends V>> futures, boolean allMustSucceed, Executor listenerExecutor) {
/* 1690 */     return new CombinedFuture<V, List<V>>((ImmutableCollection<? extends ListenableFuture<? extends V>>)futures, allMustSucceed, listenerExecutor, new FutureCombiner<V, List<V>>() {
/*      */           public List<V> combine(List<Optional<V>> values) {
/* 1695 */             List<V> result = Lists.newArrayList();
/* 1696 */             for (Optional<V> element : values)
/* 1697 */               result.add((element != null) ? (V)element.orNull() : null); 
/* 1699 */             return Collections.unmodifiableList(result);
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   private static class MappingCheckedFuture<V, X extends Exception> extends AbstractCheckedFuture<V, X> {
/*      */     final Function<Exception, X> mapper;
/*      */     
/*      */     MappingCheckedFuture(ListenableFuture<V> delegate, Function<Exception, X> mapper) {
/* 1715 */       super(delegate);
/* 1717 */       this.mapper = (Function<Exception, X>)Preconditions.checkNotNull(mapper);
/*      */     }
/*      */     
/*      */     protected X mapException(Exception e) {
/* 1722 */       return (X)this.mapper.apply(e);
/*      */     }
/*      */   }
/*      */   
/*      */   private static interface FutureCombiner<V, C> {
/*      */     C combine(List<Optional<V>> param1List);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\commo\\util\concurrent\Futures.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */