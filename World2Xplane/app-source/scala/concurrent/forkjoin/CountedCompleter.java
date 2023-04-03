/*     */ package scala.concurrent.forkjoin;
/*     */ 
/*     */ import scala.concurrent.util.Unsafe;
/*     */ import sun.misc.Unsafe;
/*     */ 
/*     */ abstract class CountedCompleter<T> extends ForkJoinTask<T> {
/*     */   private static final long serialVersionUID = 5232453752276485070L;
/*     */   
/*     */   final CountedCompleter<?> completer;
/*     */   
/*     */   volatile int pending;
/*     */   
/*     */   private static final Unsafe U;
/*     */   
/*     */   private static final long PENDING;
/*     */   
/*     */   protected CountedCompleter(CountedCompleter<?> completer, int initialPendingCount) {
/*  43 */     this.completer = completer;
/*  44 */     this.pending = initialPendingCount;
/*     */   }
/*     */   
/*     */   protected CountedCompleter(CountedCompleter<?> completer) {
/*  54 */     this.completer = completer;
/*     */   }
/*     */   
/*     */   protected CountedCompleter() {
/*  62 */     this.completer = null;
/*     */   }
/*     */   
/*     */   public void onCompletion(CountedCompleter<?> caller) {}
/*     */   
/*     */   public boolean onExceptionalCompletion(Throwable ex, CountedCompleter<?> caller) {
/* 103 */     return true;
/*     */   }
/*     */   
/*     */   public final CountedCompleter<?> getCompleter() {
/* 113 */     return this.completer;
/*     */   }
/*     */   
/*     */   public final int getPendingCount() {
/* 122 */     return this.pending;
/*     */   }
/*     */   
/*     */   public final void setPendingCount(int count) {
/* 131 */     this.pending = count;
/*     */   }
/*     */   
/*     */   public final void addToPendingCount(int delta) {
/*     */     int c;
/*     */     do {
/*     */     
/* 141 */     } while (!U.compareAndSwapInt(this, PENDING, c = this.pending, c + delta));
/*     */   }
/*     */   
/*     */   public final boolean compareAndSetPendingCount(int expected, int count) {
/* 153 */     return U.compareAndSwapInt(this, PENDING, expected, count);
/*     */   }
/*     */   
/*     */   public final int decrementPendingCountUnlessZero() {
/*     */     int c;
/*     */     do {
/*     */     
/* 164 */     } while ((c = this.pending) != 0 && !U.compareAndSwapInt(this, PENDING, c, c - 1));
/* 166 */     return c;
/*     */   }
/*     */   
/*     */   public final CountedCompleter<?> getRoot() {
/* 176 */     CountedCompleter<?> a = this;
/*     */     CountedCompleter<?> p;
/* 177 */     while ((p = a.completer) != null)
/* 178 */       a = p; 
/* 179 */     return a;
/*     */   }
/*     */   
/*     */   public final void tryComplete() {
/*     */     int c;
/* 189 */     CountedCompleter<?> a = this, s = a;
/*     */     do {
/* 191 */       while ((c = a.pending) == 0) {
/* 192 */         a.onCompletion(s);
/* 193 */         if ((a = (s = a).completer) == null) {
/* 194 */           s.quietlyComplete();
/*     */           return;
/*     */         } 
/*     */       } 
/* 198 */     } while (!U.compareAndSwapInt(a, PENDING, c, c - 1));
/*     */   }
/*     */   
/*     */   public final void propagateCompletion() {
/*     */     int c;
/* 213 */     CountedCompleter<?> a = this, s = a;
/*     */     do {
/* 215 */       while ((c = a.pending) == 0) {
/* 216 */         if ((a = (s = a).completer) == null) {
/* 217 */           s.quietlyComplete();
/*     */           return;
/*     */         } 
/*     */       } 
/* 221 */     } while (!U.compareAndSwapInt(a, PENDING, c, c - 1));
/*     */   }
/*     */   
/*     */   public void complete(T rawResult) {
/* 245 */     setRawResult(rawResult);
/* 246 */     onCompletion(this);
/* 247 */     quietlyComplete();
/*     */     CountedCompleter<?> p;
/* 248 */     if ((p = this.completer) != null)
/* 249 */       p.tryComplete(); 
/*     */   }
/*     */   
/*     */   public final CountedCompleter<?> firstComplete() {
/*     */     while (true) {
/*     */       int c;
/* 263 */       if ((c = this.pending) == 0)
/* 264 */         return this; 
/* 265 */       if (U.compareAndSwapInt(this, PENDING, c, c - 1))
/* 266 */         return null; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final CountedCompleter<?> nextComplete() {
/*     */     CountedCompleter<?> p;
/* 289 */     if ((p = this.completer) != null)
/* 290 */       return p.firstComplete(); 
/* 292 */     quietlyComplete();
/* 293 */     return null;
/*     */   }
/*     */   
/*     */   public final void quietlyCompleteRoot() {
/* 301 */     CountedCompleter<?> a = this;
/*     */     while (true) {
/*     */       CountedCompleter<?> p;
/* 302 */       if ((p = a.completer) == null) {
/* 303 */         a.quietlyComplete();
/*     */         return;
/*     */       } 
/* 306 */       a = p;
/*     */     } 
/*     */   }
/*     */   
/*     */   void internalPropagateException(Throwable ex) {
/* 314 */     CountedCompleter<?> a = this, s = a;
/* 316 */     while (a.onExceptionalCompletion(ex, s) && (a = (s = a).completer) != null && a.status >= 0)
/* 317 */       a.recordExceptionalCompletion(ex); 
/*     */   }
/*     */   
/*     */   protected final boolean exec() {
/* 324 */     compute();
/* 325 */     return false;
/*     */   }
/*     */   
/*     */   public T getRawResult() {
/* 337 */     return null;
/*     */   }
/*     */   
/*     */   protected void setRawResult(T t) {}
/*     */   
/*     */   static {
/*     */     try {
/* 353 */       U = getUnsafe();
/* 354 */       PENDING = U.objectFieldOffset(CountedCompleter.class.getDeclaredField("pending"));
/* 356 */     } catch (Exception e) {
/* 357 */       throw new Error(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Unsafe getUnsafe() {
/* 369 */     return Unsafe.instance;
/*     */   }
/*     */   
/*     */   public abstract void compute();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\forkjoin\CountedCompleter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */