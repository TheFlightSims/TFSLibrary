/*      */ package scala.concurrent.forkjoin;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.concurrent.AbstractExecutorService;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.Future;
/*      */ import java.util.concurrent.RejectedExecutionException;
/*      */ import java.util.concurrent.RunnableFuture;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import scala.concurrent.util.Unsafe;
/*      */ import sun.misc.Unsafe;
/*      */ 
/*      */ public class ForkJoinPool extends AbstractExecutorService {
/*      */   public static final ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory;
/*      */   
/*      */   private static void checkPermission() {
/*  854 */     SecurityManager security = System.getSecurityManager();
/*  855 */     if (security != null)
/*  856 */       security.checkPermission(modifyThreadPermission); 
/*      */   }
/*      */   
/*      */   public static interface ForkJoinWorkerThreadFactory {
/*      */     ForkJoinWorkerThread newThread(ForkJoinPool param1ForkJoinPool);
/*      */   }
/*      */   
/*      */   static final class DefaultForkJoinWorkerThreadFactory implements ForkJoinWorkerThreadFactory {
/*      */     public final ForkJoinWorkerThread newThread(ForkJoinPool pool) {
/*  884 */       return new ForkJoinWorkerThread(pool);
/*      */     }
/*      */   }
/*      */   
/*      */   static final class Submitter {
/*      */     int seed;
/*      */     
/*      */     Submitter(int s) {
/*  905 */       this.seed = s;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class EmptyTask extends ForkJoinTask<Void> {
/*      */     private static final long serialVersionUID = -7721805057305804111L;
/*      */     
/*      */     public final Void getRawResult() {
/*  917 */       return null;
/*      */     }
/*      */     
/*      */     public final void setRawResult(Void x) {}
/*      */     
/*      */     public final boolean exec() {
/*  919 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class WorkQueue {
/*      */     static final int INITIAL_QUEUE_CAPACITY = 8192;
/*      */     
/*      */     static final int MAXIMUM_QUEUE_CAPACITY = 67108864;
/*      */     
/*      */     volatile long pad00;
/*      */     
/*      */     volatile long pad01;
/*      */     
/*      */     volatile long pad02;
/*      */     
/*      */     volatile long pad03;
/*      */     
/*      */     volatile long pad04;
/*      */     
/*      */     volatile long pad05;
/*      */     
/*      */     volatile long pad06;
/*      */     
/*      */     int seed;
/*      */     
/*      */     volatile int eventCount;
/*      */     
/*      */     int nextWait;
/*      */     
/*      */     int hint;
/*      */     
/*      */     int poolIndex;
/*      */     
/*      */     final int mode;
/*      */     
/*      */     int nsteals;
/*      */     
/*      */     volatile int qlock;
/*      */     
/*      */     volatile int base;
/*      */     
/*      */     int top;
/*      */     
/*      */     ForkJoinTask<?>[] array;
/*      */     
/*      */     final ForkJoinPool pool;
/*      */     
/*      */     final ForkJoinWorkerThread owner;
/*      */     
/*      */     volatile Thread parker;
/*      */     
/*      */     volatile ForkJoinTask<?> currentJoin;
/*      */     
/*      */     ForkJoinTask<?> currentSteal;
/*      */     
/*      */     volatile Object pad10;
/*      */     
/*      */     volatile Object pad11;
/*      */     
/*      */     volatile Object pad12;
/*      */     
/*      */     volatile Object pad13;
/*      */     
/*      */     volatile Object pad14;
/*      */     
/*      */     volatile Object pad15;
/*      */     
/*      */     volatile Object pad16;
/*      */     
/*      */     volatile Object pad17;
/*      */     
/*      */     volatile Object pad18;
/*      */     
/*      */     volatile Object pad19;
/*      */     
/*      */     volatile Object pad1a;
/*      */     
/*      */     volatile Object pad1b;
/*      */     
/*      */     volatile Object pad1c;
/*      */     
/*      */     volatile Object pad1d;
/*      */     
/*      */     private static final Unsafe U;
/*      */     
/*      */     private static final long QLOCK;
/*      */     
/*      */     private static final int ABASE;
/*      */     
/*      */     private static final int ASHIFT;
/*      */     
/*      */     WorkQueue(ForkJoinPool pool, ForkJoinWorkerThread owner, int mode, int seed) {
/* 1025 */       this.pool = pool;
/* 1026 */       this.owner = owner;
/* 1027 */       this.mode = mode;
/* 1028 */       this.seed = seed;
/* 1030 */       this.base = this.top = 4096;
/*      */     }
/*      */     
/*      */     final int queueSize() {
/* 1037 */       int n = this.base - this.top;
/* 1038 */       return (n >= 0) ? 0 : -n;
/*      */     }
/*      */     
/*      */     final boolean isEmpty() {
/* 1048 */       int s, n = this.base - (s = this.top);
/*      */       ForkJoinTask<?>[] a;
/*      */       int m;
/* 1049 */       return (n >= 0 || (n == -1 && ((a = this.array) == null || (m = a.length - 1) < 0 || U.getObject(a, ((m & s - 1) << ASHIFT) + ABASE) == null)));
/*      */     }
/*      */     
/*      */     final void push(ForkJoinTask<?> task) {
/* 1066 */       int s = this.top;
/*      */       ForkJoinTask<?>[] a;
/* 1067 */       if ((a = this.array) != null) {
/* 1068 */         int m, j = (((m = a.length - 1) & s) << ASHIFT) + ABASE;
/* 1069 */         U.putOrderedObject(a, j, task);
/*      */         int n;
/* 1070 */         if ((n = (this.top = s + 1) - this.base) <= 2) {
/*      */           ForkJoinPool p;
/* 1071 */           if ((p = this.pool) != null)
/* 1072 */             p.signalWork(this); 
/* 1074 */         } else if (n >= m) {
/* 1075 */           growArray();
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     final ForkJoinTask<?>[] growArray() {
/* 1085 */       ForkJoinTask<?>[] oldA = this.array;
/* 1086 */       int size = (oldA != null) ? (oldA.length << 1) : 8192;
/* 1087 */       if (size > 67108864)
/* 1088 */         throw new RejectedExecutionException("Queue capacity exceeded"); 
/* 1090 */       ForkJoinTask<?>[] a = this.array = (ForkJoinTask<?>[])new ForkJoinTask[size];
/*      */       int oldMask, t, b;
/* 1091 */       if (oldA != null && (oldMask = oldA.length - 1) >= 0 && (t = this.top) - (b = this.base) > 0) {
/* 1093 */         int mask = size - 1;
/*      */         do {
/* 1096 */           int oldj = ((b & oldMask) << ASHIFT) + ABASE;
/* 1097 */           int j = ((b & mask) << ASHIFT) + ABASE;
/* 1098 */           ForkJoinTask<?> x = (ForkJoinTask)U.getObjectVolatile(oldA, oldj);
/* 1099 */           if (x == null || !U.compareAndSwapObject(oldA, oldj, x, null))
/*      */             continue; 
/* 1101 */           U.putObjectVolatile(a, j, x);
/* 1102 */         } while (++b != t);
/*      */       } 
/* 1104 */       return a;
/*      */     }
/*      */     
/*      */     final ForkJoinTask<?> pop() {
/*      */       ForkJoinTask<?>[] a;
/*      */       int m;
/* 1113 */       if ((a = this.array) != null && (m = a.length - 1) >= 0) {
/* 1115 */         long j = (((m & s) << ASHIFT) + ABASE);
/*      */         ForkJoinTask<?> t;
/*      */         int s;
/* 1116 */         while ((s = this.top - 1) - this.base >= 0 && (t = (ForkJoinTask)U.getObject(a, j)) != null) {
/* 1118 */           if (U.compareAndSwapObject(a, j, t, null)) {
/* 1119 */             this.top = s;
/* 1120 */             return t;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1124 */       return null;
/*      */     }
/*      */     
/*      */     final ForkJoinTask<?> pollAt(int b) {
/* 1135 */       int j = ((a.length - 1 & b) << ASHIFT) + ABASE;
/*      */       ForkJoinTask<?> t, a[];
/* 1136 */       if ((a = this.array) != null && (t = (ForkJoinTask)U.getObjectVolatile(a, j)) != null && this.base == b && U.compareAndSwapObject(a, j, t, null)) {
/* 1139 */         this.base = b + 1;
/* 1140 */         return t;
/*      */       } 
/* 1143 */       return null;
/*      */     }
/*      */     
/*      */     final ForkJoinTask<?> poll() {
/*      */       ForkJoinTask<?>[] a;
/*      */       int b;
/* 1151 */       while ((b = this.base) - this.top < 0 && (a = this.array) != null) {
/* 1152 */         int j = ((a.length - 1 & b) << ASHIFT) + ABASE;
/* 1153 */         ForkJoinTask<?> t = (ForkJoinTask)U.getObjectVolatile(a, j);
/* 1154 */         if (t != null) {
/* 1155 */           if (this.base == b && U.compareAndSwapObject(a, j, t, null)) {
/* 1157 */             this.base = b + 1;
/* 1158 */             return t;
/*      */           } 
/*      */           continue;
/*      */         } 
/* 1161 */         if (this.base == b) {
/* 1162 */           if (b + 1 == this.top)
/*      */             break; 
/* 1164 */           Thread.yield();
/*      */         } 
/*      */       } 
/* 1167 */       return null;
/*      */     }
/*      */     
/*      */     final ForkJoinTask<?> nextLocalTask() {
/* 1174 */       return (this.mode == 0) ? pop() : poll();
/*      */     }
/*      */     
/*      */     final ForkJoinTask<?> peek() {
/* 1181 */       ForkJoinTask<?>[] a = this.array;
/*      */       int m;
/* 1182 */       if (a == null || (m = a.length - 1) < 0)
/* 1183 */         return null; 
/* 1184 */       int i = (this.mode == 0) ? (this.top - 1) : this.base;
/* 1185 */       int j = ((i & m) << ASHIFT) + ABASE;
/* 1186 */       return (ForkJoinTask)U.getObjectVolatile(a, j);
/*      */     }
/*      */     
/*      */     final boolean tryUnpush(ForkJoinTask<?> t) {
/*      */       ForkJoinTask<?>[] a;
/*      */       int s;
/* 1195 */       if ((a = this.array) != null && (s = this.top) != this.base && U.compareAndSwapObject(a, (((a.length - 1 & --s) << ASHIFT) + ABASE), t, null)) {
/* 1198 */         this.top = s;
/* 1199 */         return true;
/*      */       } 
/* 1201 */       return false;
/*      */     }
/*      */     
/*      */     final void cancelAll() {
/* 1208 */       ForkJoinTask.cancelIgnoringExceptions(this.currentJoin);
/* 1209 */       ForkJoinTask.cancelIgnoringExceptions(this.currentSteal);
/*      */       ForkJoinTask<?> t;
/* 1210 */       while ((t = poll()) != null)
/* 1211 */         ForkJoinTask.cancelIgnoringExceptions(t); 
/*      */     }
/*      */     
/*      */     final int nextSeed() {
/* 1222 */       int r = this.seed;
/* 1223 */       r ^= r << 13;
/* 1224 */       r ^= r >>> 17;
/* 1225 */       return this.seed = r ^= r << 5;
/*      */     }
/*      */     
/*      */     private void popAndExecAll() {
/*      */       ForkJoinTask<?>[] a;
/*      */       int m;
/*      */       int s;
/*      */       long j;
/*      */       ForkJoinTask<?> t;
/* 1240 */       while ((a = this.array) != null && (m = a.length - 1) >= 0 && (s = this.top - 1) - this.base >= 0 && (t = (ForkJoinTask)U.getObject(a, j = (((m & s) << ASHIFT) + ABASE))) != null) {
/* 1241 */         if (U.compareAndSwapObject(a, j, t, null)) {
/* 1242 */           this.top = s;
/* 1243 */           t.doExec();
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private void pollAndExecAll() {
/*      */       ForkJoinTask<?> t;
/* 1252 */       while ((t = poll()) != null)
/* 1253 */         t.doExec(); 
/*      */     }
/*      */     
/*      */     final boolean tryRemoveAndExec(ForkJoinTask<?> task) {
/* 1264 */       boolean stat = true, removed = false, empty = true;
/*      */       ForkJoinTask<?>[] a;
/*      */       int m, s, b, n;
/* 1266 */       if ((a = this.array) != null && (m = a.length - 1) >= 0 && (n = (s = this.top) - (b = this.base)) > 0)
/*      */         while (true) {
/* 1269 */           int j = ((--s & m) << ASHIFT) + ABASE;
/* 1270 */           ForkJoinTask<?> t = (ForkJoinTask)U.getObjectVolatile(a, j);
/* 1271 */           if (t == null)
/*      */             break; 
/* 1273 */           if (t == task) {
/* 1274 */             if (s + 1 == this.top) {
/* 1275 */               if (!U.compareAndSwapObject(a, j, task, null))
/*      */                 break; 
/* 1277 */               this.top = s;
/* 1278 */               removed = true;
/*      */               break;
/*      */             } 
/* 1280 */             if (this.base == b)
/* 1281 */               removed = U.compareAndSwapObject(a, j, task, new ForkJoinPool.EmptyTask()); 
/*      */             break;
/*      */           } 
/* 1285 */           if (t.status >= 0) {
/* 1286 */             empty = false;
/* 1287 */           } else if (s + 1 == this.top) {
/* 1288 */             if (U.compareAndSwapObject(a, j, t, null))
/* 1289 */               this.top = s; 
/*      */             break;
/*      */           } 
/* 1292 */           if (--n == 0) {
/* 1293 */             if (!empty && this.base == b)
/* 1294 */               stat = false; 
/*      */             break;
/*      */           } 
/*      */         }  
/* 1299 */       if (removed)
/* 1300 */         task.doExec(); 
/* 1301 */       return stat;
/*      */     }
/*      */     
/*      */     final boolean pollAndExecCC(ForkJoinTask<?> root) {
/* 1311 */       long j = (((a.length - 1 & b) << ASHIFT) + ABASE);
/*      */       ForkJoinTask<?>[] a;
/*      */       int b;
/*      */       Object o;
/* 1312 */       label22: while ((b = this.base) - this.top < 0 && (a = this.array) != null && (o = U.getObject(a, j)) != null && o instanceof CountedCompleter) {
/* 1315 */         CountedCompleter<?> t = (CountedCompleter)o, r = t;
/*      */         do {
/* 1316 */           if (r == root) {
/* 1317 */             if (this.base == b) {
/* 1317 */               if (U.compareAndSwapObject(a, j, t, null)) {
/* 1319 */                 this.base = b + 1;
/* 1320 */                 t.doExec();
/* 1321 */                 return true;
/*      */               } 
/*      */               continue label22;
/*      */             } 
/*      */             continue label22;
/*      */           } 
/* 1326 */         } while ((r = r.completer) != null);
/*      */         break;
/*      */       } 
/* 1330 */       return false;
/*      */     }
/*      */     
/*      */     final void runTask(ForkJoinTask<?> t) {
/* 1338 */       if (t != null) {
/* 1339 */         (this.currentSteal = t).doExec();
/* 1340 */         this.currentSteal = null;
/* 1341 */         this.nsteals++;
/* 1342 */         if (this.base - this.top < 0)
/* 1343 */           if (this.mode == 0) {
/* 1344 */             popAndExecAll();
/*      */           } else {
/* 1346 */             pollAndExecAll();
/*      */           }  
/*      */       } 
/*      */     }
/*      */     
/*      */     final void runSubtask(ForkJoinTask<?> t) {
/* 1355 */       if (t != null) {
/* 1356 */         ForkJoinTask<?> ps = this.currentSteal;
/* 1357 */         (this.currentSteal = t).doExec();
/* 1358 */         this.currentSteal = ps;
/*      */       } 
/*      */     }
/*      */     
/*      */     final boolean isApparentlyUnblocked() {
/*      */       Thread wt;
/*      */       Thread.State s;
/* 1367 */       return (this.eventCount >= 0 && (wt = this.owner) != null && (s = wt.getState()) != Thread.State.BLOCKED && s != Thread.State.WAITING && s != Thread.State.TIMED_WAITING);
/*      */     }
/*      */     
/*      */     static {
/*      */       try {
/* 1381 */         U = ForkJoinPool.getUnsafe();
/* 1382 */         Class<?> k = WorkQueue.class;
/* 1383 */         Class<?> ak = ForkJoinTask[].class;
/* 1384 */         QLOCK = U.objectFieldOffset(k.getDeclaredField("qlock"));
/* 1386 */         ABASE = U.arrayBaseOffset(ak);
/* 1387 */         int scale = U.arrayIndexScale(ak);
/* 1388 */         if ((scale & scale - 1) != 0)
/* 1389 */           throw new Error("data type scale not a power of two"); 
/* 1390 */         ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
/* 1391 */       } catch (Exception e) {
/* 1392 */         throw new Error(e);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static final synchronized int nextPoolId() {
/* 1444 */     return ++poolNumberSequence;
/*      */   }
/*      */   
/*      */   private int acquirePlock() {
/* 1607 */     int spins = 256, r = 0;
/*      */     while (true) {
/*      */       int ps;
/*      */       int nps;
/* 1609 */       if (((ps = this.plock) & 0x2) == 0 && U.compareAndSwapInt(this, PLOCK, ps, nps = ps + 2))
/* 1611 */         return nps; 
/* 1612 */       if (r == 0) {
/* 1613 */         Thread t = Thread.currentThread();
/*      */         WorkQueue w;
/* 1614 */         if (t instanceof ForkJoinWorkerThread && (w = ((ForkJoinWorkerThread)t).workQueue) != null) {
/* 1616 */           r = w.seed;
/*      */           continue;
/*      */         } 
/*      */         Submitter z;
/* 1617 */         if ((z = submitters.get()) != null) {
/* 1618 */           r = z.seed;
/*      */           continue;
/*      */         } 
/* 1620 */         r = 1;
/*      */         continue;
/*      */       } 
/* 1622 */       if (spins >= 0) {
/* 1623 */         r ^= r << 1;
/* 1623 */         r ^= r >>> 3;
/* 1623 */         r ^= r << 10;
/* 1624 */         if (r >= 0)
/* 1625 */           spins--; 
/*      */         continue;
/*      */       } 
/* 1627 */       if (U.compareAndSwapInt(this, PLOCK, ps, ps | 0x1))
/* 1628 */         synchronized (this) {
/* 1629 */           if ((this.plock & 0x1) != 0) {
/*      */             try {
/* 1631 */               wait();
/* 1632 */             } catch (InterruptedException ie) {
/*      */               try {
/* 1634 */                 Thread.currentThread().interrupt();
/* 1635 */               } catch (SecurityException ignore) {}
/*      */             } 
/*      */           } else {
/* 1640 */             notifyAll();
/*      */           } 
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   private void releasePlock(int ps) {
/* 1651 */     this.plock = ps;
/* 1652 */     synchronized (this) {
/* 1652 */       notifyAll();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void tryAddWorker() {
/*      */     long c;
/*      */     int u;
/* 1662 */     while ((u = (int)((c = this.ctl) >>> 32L)) < 0 && (u & 0x8000) != 0 && (int)c == 0) {
/* 1663 */       long nc = (u + 1 & 0xFFFF | u + 65536 & 0xFFFF0000) << 32L;
/* 1665 */       if (U.compareAndSwapLong(this, CTL, c, nc)) {
/* 1667 */         Throwable ex = null;
/* 1668 */         ForkJoinWorkerThread wt = null;
/*      */         try {
/*      */           ForkJoinWorkerThreadFactory fac;
/* 1670 */           if ((fac = this.factory) != null && (wt = fac.newThread(this)) != null) {
/* 1672 */             wt.start();
/*      */             break;
/*      */           } 
/* 1675 */         } catch (Throwable e) {
/* 1676 */           ex = e;
/*      */         } 
/* 1678 */         deregisterWorker(wt, ex);
/*      */         break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   final WorkQueue registerWorker(ForkJoinWorkerThread wt) {
/*      */     // Byte code:
/*      */     //   0: aload_1
/*      */     //   1: iconst_1
/*      */     //   2: invokevirtual setDaemon : (Z)V
/*      */     //   5: aload_0
/*      */     //   6: getfield ueh : Ljava/lang/Thread$UncaughtExceptionHandler;
/*      */     //   9: dup
/*      */     //   10: astore_2
/*      */     //   11: ifnull -> 19
/*      */     //   14: aload_1
/*      */     //   15: aload_2
/*      */     //   16: invokevirtual setUncaughtExceptionHandler : (Ljava/lang/Thread$UncaughtExceptionHandler;)V
/*      */     //   19: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   22: aload_0
/*      */     //   23: getstatic scala/concurrent/forkjoin/ForkJoinPool.INDEXSEED : J
/*      */     //   26: aload_0
/*      */     //   27: getfield indexSeed : I
/*      */     //   30: dup
/*      */     //   31: istore #4
/*      */     //   33: iload #4
/*      */     //   35: ldc 1640531527
/*      */     //   37: iadd
/*      */     //   38: istore #4
/*      */     //   40: iload #4
/*      */     //   42: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   45: ifeq -> 19
/*      */     //   48: iload #4
/*      */     //   50: ifeq -> 19
/*      */     //   53: new scala/concurrent/forkjoin/ForkJoinPool$WorkQueue
/*      */     //   56: dup
/*      */     //   57: aload_0
/*      */     //   58: aload_1
/*      */     //   59: aload_0
/*      */     //   60: getfield config : I
/*      */     //   63: bipush #16
/*      */     //   65: iushr
/*      */     //   66: iload #4
/*      */     //   68: invokespecial <init> : (Lscala/concurrent/forkjoin/ForkJoinPool;Lscala/concurrent/forkjoin/ForkJoinWorkerThread;II)V
/*      */     //   71: astore #6
/*      */     //   73: aload_0
/*      */     //   74: getfield plock : I
/*      */     //   77: dup
/*      */     //   78: istore #5
/*      */     //   80: iconst_2
/*      */     //   81: iand
/*      */     //   82: ifne -> 105
/*      */     //   85: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   88: aload_0
/*      */     //   89: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   92: iload #5
/*      */     //   94: iinc #5, 2
/*      */     //   97: iload #5
/*      */     //   99: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   102: ifne -> 111
/*      */     //   105: aload_0
/*      */     //   106: invokespecial acquirePlock : ()I
/*      */     //   109: istore #5
/*      */     //   111: iload #5
/*      */     //   113: ldc -2147483648
/*      */     //   115: iand
/*      */     //   116: iload #5
/*      */     //   118: iconst_2
/*      */     //   119: iadd
/*      */     //   120: ldc 2147483647
/*      */     //   122: iand
/*      */     //   123: ior
/*      */     //   124: istore #7
/*      */     //   126: aload_0
/*      */     //   127: getfield workQueues : [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   130: dup
/*      */     //   131: astore_3
/*      */     //   132: ifnull -> 267
/*      */     //   135: aload_3
/*      */     //   136: arraylength
/*      */     //   137: istore #8
/*      */     //   139: iload #8
/*      */     //   141: iconst_1
/*      */     //   142: isub
/*      */     //   143: istore #9
/*      */     //   145: iload #4
/*      */     //   147: iconst_1
/*      */     //   148: ishl
/*      */     //   149: iconst_1
/*      */     //   150: ior
/*      */     //   151: istore #10
/*      */     //   153: aload_3
/*      */     //   154: iload #10
/*      */     //   156: iload #9
/*      */     //   158: iand
/*      */     //   159: dup
/*      */     //   160: istore #10
/*      */     //   162: aaload
/*      */     //   163: ifnull -> 248
/*      */     //   166: iconst_0
/*      */     //   167: istore #11
/*      */     //   169: iload #8
/*      */     //   171: iconst_4
/*      */     //   172: if_icmpgt -> 179
/*      */     //   175: iconst_2
/*      */     //   176: goto -> 188
/*      */     //   179: iload #8
/*      */     //   181: iconst_1
/*      */     //   182: iushr
/*      */     //   183: ldc 65534
/*      */     //   185: iand
/*      */     //   186: iconst_2
/*      */     //   187: iadd
/*      */     //   188: istore #12
/*      */     //   190: aload_3
/*      */     //   191: iload #10
/*      */     //   193: iload #12
/*      */     //   195: iadd
/*      */     //   196: iload #9
/*      */     //   198: iand
/*      */     //   199: dup
/*      */     //   200: istore #10
/*      */     //   202: aaload
/*      */     //   203: ifnull -> 248
/*      */     //   206: iinc #11, 1
/*      */     //   209: iload #11
/*      */     //   211: iload #8
/*      */     //   213: if_icmplt -> 190
/*      */     //   216: aload_0
/*      */     //   217: aload_3
/*      */     //   218: iload #8
/*      */     //   220: iconst_1
/*      */     //   221: ishl
/*      */     //   222: dup
/*      */     //   223: istore #8
/*      */     //   225: invokestatic copyOf : ([Ljava/lang/Object;I)[Ljava/lang/Object;
/*      */     //   228: checkcast [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   231: dup
/*      */     //   232: astore_3
/*      */     //   233: putfield workQueues : [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   236: iload #8
/*      */     //   238: iconst_1
/*      */     //   239: isub
/*      */     //   240: istore #9
/*      */     //   242: iconst_0
/*      */     //   243: istore #11
/*      */     //   245: goto -> 190
/*      */     //   248: aload #6
/*      */     //   250: aload #6
/*      */     //   252: iload #10
/*      */     //   254: dup_x1
/*      */     //   255: putfield poolIndex : I
/*      */     //   258: putfield eventCount : I
/*      */     //   261: aload_3
/*      */     //   262: iload #10
/*      */     //   264: aload #6
/*      */     //   266: aastore
/*      */     //   267: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   270: aload_0
/*      */     //   271: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   274: iload #5
/*      */     //   276: iload #7
/*      */     //   278: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   281: ifne -> 321
/*      */     //   284: aload_0
/*      */     //   285: iload #7
/*      */     //   287: invokespecial releasePlock : (I)V
/*      */     //   290: goto -> 321
/*      */     //   293: astore #13
/*      */     //   295: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   298: aload_0
/*      */     //   299: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   302: iload #5
/*      */     //   304: iload #7
/*      */     //   306: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   309: ifne -> 318
/*      */     //   312: aload_0
/*      */     //   313: iload #7
/*      */     //   315: invokespecial releasePlock : (I)V
/*      */     //   318: aload #13
/*      */     //   320: athrow
/*      */     //   321: aload_1
/*      */     //   322: aload_0
/*      */     //   323: getfield workerNamePrefix : Ljava/lang/String;
/*      */     //   326: aload #6
/*      */     //   328: getfield poolIndex : I
/*      */     //   331: invokestatic toString : (I)Ljava/lang/String;
/*      */     //   334: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   337: invokevirtual setName : (Ljava/lang/String;)V
/*      */     //   340: aload #6
/*      */     //   342: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1698	-> 0
/*      */     //   #1699	-> 5
/*      */     //   #1700	-> 14
/*      */     //   #1703	-> 19
/*      */     //   #1704	-> 53
/*      */     //   #1705	-> 73
/*      */     //   #1707	-> 105
/*      */     //   #1708	-> 111
/*      */     //   #1710	-> 126
/*      */     //   #1711	-> 135
/*      */     //   #1712	-> 145
/*      */     //   #1713	-> 153
/*      */     //   #1714	-> 166
/*      */     //   #1715	-> 169
/*      */     //   #1716	-> 190
/*      */     //   #1717	-> 206
/*      */     //   #1718	-> 216
/*      */     //   #1719	-> 236
/*      */     //   #1720	-> 242
/*      */     //   #1724	-> 248
/*      */     //   #1725	-> 261
/*      */     //   #1728	-> 267
/*      */     //   #1729	-> 284
/*      */     //   #1728	-> 293
/*      */     //   #1729	-> 312
/*      */     //   #1731	-> 321
/*      */     //   #1732	-> 340
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   169	79	11	probes	I
/*      */     //   190	58	12	step	I
/*      */     //   139	128	8	n	I
/*      */     //   145	122	9	m	I
/*      */     //   153	114	10	r	I
/*      */     //   0	343	0	this	Lscala/concurrent/forkjoin/ForkJoinPool;
/*      */     //   0	343	1	wt	Lscala/concurrent/forkjoin/ForkJoinWorkerThread;
/*      */     //   11	332	2	handler	Ljava/lang/Thread$UncaughtExceptionHandler;
/*      */     //   132	211	3	ws	[Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   33	310	4	s	I
/*      */     //   80	263	5	ps	I
/*      */     //   73	270	6	w	Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   126	217	7	nps	I
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   126	267	293	finally
/*      */     //   293	295	293	finally
/*      */   }
/*      */   
/*      */   final void deregisterWorker(ForkJoinWorkerThread wt, Throwable ex) {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore_3
/*      */     //   2: aload_1
/*      */     //   3: ifnull -> 204
/*      */     //   6: aload_1
/*      */     //   7: getfield workQueue : Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   10: dup
/*      */     //   11: astore_3
/*      */     //   12: ifnull -> 204
/*      */     //   15: aload_3
/*      */     //   16: iconst_m1
/*      */     //   17: putfield qlock : I
/*      */     //   20: aload_3
/*      */     //   21: getfield nsteals : I
/*      */     //   24: i2l
/*      */     //   25: lstore #5
/*      */     //   27: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   30: aload_0
/*      */     //   31: getstatic scala/concurrent/forkjoin/ForkJoinPool.STEALCOUNT : J
/*      */     //   34: aload_0
/*      */     //   35: getfield stealCount : J
/*      */     //   38: dup2
/*      */     //   39: lstore #7
/*      */     //   41: lload #7
/*      */     //   43: lload #5
/*      */     //   45: ladd
/*      */     //   46: invokevirtual compareAndSwapLong : (Ljava/lang/Object;JJJ)Z
/*      */     //   49: ifeq -> 27
/*      */     //   52: aload_0
/*      */     //   53: getfield plock : I
/*      */     //   56: dup
/*      */     //   57: istore #4
/*      */     //   59: iconst_2
/*      */     //   60: iand
/*      */     //   61: ifne -> 84
/*      */     //   64: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   67: aload_0
/*      */     //   68: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   71: iload #4
/*      */     //   73: iinc #4, 2
/*      */     //   76: iload #4
/*      */     //   78: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   81: ifne -> 90
/*      */     //   84: aload_0
/*      */     //   85: invokespecial acquirePlock : ()I
/*      */     //   88: istore #4
/*      */     //   90: iload #4
/*      */     //   92: ldc -2147483648
/*      */     //   94: iand
/*      */     //   95: iload #4
/*      */     //   97: iconst_2
/*      */     //   98: iadd
/*      */     //   99: ldc 2147483647
/*      */     //   101: iand
/*      */     //   102: ior
/*      */     //   103: istore #9
/*      */     //   105: aload_3
/*      */     //   106: getfield poolIndex : I
/*      */     //   109: istore #10
/*      */     //   111: aload_0
/*      */     //   112: getfield workQueues : [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   115: astore #11
/*      */     //   117: aload #11
/*      */     //   119: ifnull -> 150
/*      */     //   122: iload #10
/*      */     //   124: iflt -> 150
/*      */     //   127: iload #10
/*      */     //   129: aload #11
/*      */     //   131: arraylength
/*      */     //   132: if_icmpge -> 150
/*      */     //   135: aload #11
/*      */     //   137: iload #10
/*      */     //   139: aaload
/*      */     //   140: aload_3
/*      */     //   141: if_acmpne -> 150
/*      */     //   144: aload #11
/*      */     //   146: iload #10
/*      */     //   148: aconst_null
/*      */     //   149: aastore
/*      */     //   150: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   153: aload_0
/*      */     //   154: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   157: iload #4
/*      */     //   159: iload #9
/*      */     //   161: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   164: ifne -> 204
/*      */     //   167: aload_0
/*      */     //   168: iload #9
/*      */     //   170: invokespecial releasePlock : (I)V
/*      */     //   173: goto -> 204
/*      */     //   176: astore #12
/*      */     //   178: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   181: aload_0
/*      */     //   182: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   185: iload #4
/*      */     //   187: iload #9
/*      */     //   189: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   192: ifne -> 201
/*      */     //   195: aload_0
/*      */     //   196: iload #9
/*      */     //   198: invokespecial releasePlock : (I)V
/*      */     //   201: aload #12
/*      */     //   203: athrow
/*      */     //   204: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   207: aload_0
/*      */     //   208: getstatic scala/concurrent/forkjoin/ForkJoinPool.CTL : J
/*      */     //   211: aload_0
/*      */     //   212: getfield ctl : J
/*      */     //   215: dup2
/*      */     //   216: lstore #4
/*      */     //   218: lload #4
/*      */     //   220: ldc2_w 281474976710656
/*      */     //   223: lsub
/*      */     //   224: ldc2_w -281474976710656
/*      */     //   227: land
/*      */     //   228: lload #4
/*      */     //   230: ldc2_w 4294967296
/*      */     //   233: lsub
/*      */     //   234: ldc2_w 281470681743360
/*      */     //   237: land
/*      */     //   238: lor
/*      */     //   239: lload #4
/*      */     //   241: ldc2_w 4294967295
/*      */     //   244: land
/*      */     //   245: lor
/*      */     //   246: invokevirtual compareAndSwapLong : (Ljava/lang/Object;JJJ)Z
/*      */     //   249: ifeq -> 204
/*      */     //   252: aload_0
/*      */     //   253: iconst_0
/*      */     //   254: iconst_0
/*      */     //   255: invokespecial tryTerminate : (ZZ)Z
/*      */     //   258: ifne -> 450
/*      */     //   261: aload_3
/*      */     //   262: ifnull -> 450
/*      */     //   265: aload_3
/*      */     //   266: getfield array : [Lscala/concurrent/forkjoin/ForkJoinTask;
/*      */     //   269: ifnull -> 450
/*      */     //   272: aload_3
/*      */     //   273: invokevirtual cancelAll : ()V
/*      */     //   276: aload_0
/*      */     //   277: getfield ctl : J
/*      */     //   280: dup2
/*      */     //   281: lstore #4
/*      */     //   283: bipush #32
/*      */     //   285: lushr
/*      */     //   286: l2i
/*      */     //   287: dup
/*      */     //   288: istore #9
/*      */     //   290: ifge -> 450
/*      */     //   293: lload #4
/*      */     //   295: l2i
/*      */     //   296: dup
/*      */     //   297: istore #11
/*      */     //   299: iflt -> 450
/*      */     //   302: iload #11
/*      */     //   304: ifle -> 437
/*      */     //   307: aload_0
/*      */     //   308: getfield workQueues : [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   311: dup
/*      */     //   312: astore #6
/*      */     //   314: ifnull -> 450
/*      */     //   317: iload #11
/*      */     //   319: ldc 65535
/*      */     //   321: iand
/*      */     //   322: dup
/*      */     //   323: istore #10
/*      */     //   325: aload #6
/*      */     //   327: arraylength
/*      */     //   328: if_icmpge -> 450
/*      */     //   331: aload #6
/*      */     //   333: iload #10
/*      */     //   335: aaload
/*      */     //   336: dup
/*      */     //   337: astore #7
/*      */     //   339: ifnonnull -> 345
/*      */     //   342: goto -> 450
/*      */     //   345: aload #7
/*      */     //   347: getfield nextWait : I
/*      */     //   350: ldc 2147483647
/*      */     //   352: iand
/*      */     //   353: i2l
/*      */     //   354: iload #9
/*      */     //   356: ldc 65536
/*      */     //   358: iadd
/*      */     //   359: i2l
/*      */     //   360: bipush #32
/*      */     //   362: lshl
/*      */     //   363: lor
/*      */     //   364: lstore #12
/*      */     //   366: aload #7
/*      */     //   368: getfield eventCount : I
/*      */     //   371: iload #11
/*      */     //   373: ldc -2147483648
/*      */     //   375: ior
/*      */     //   376: if_icmpeq -> 382
/*      */     //   379: goto -> 450
/*      */     //   382: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   385: aload_0
/*      */     //   386: getstatic scala/concurrent/forkjoin/ForkJoinPool.CTL : J
/*      */     //   389: lload #4
/*      */     //   391: lload #12
/*      */     //   393: invokevirtual compareAndSwapLong : (Ljava/lang/Object;JJJ)Z
/*      */     //   396: ifeq -> 434
/*      */     //   399: aload #7
/*      */     //   401: iload #11
/*      */     //   403: ldc 65536
/*      */     //   405: iadd
/*      */     //   406: ldc 2147483647
/*      */     //   408: iand
/*      */     //   409: putfield eventCount : I
/*      */     //   412: aload #7
/*      */     //   414: getfield parker : Ljava/lang/Thread;
/*      */     //   417: dup
/*      */     //   418: astore #8
/*      */     //   420: ifnull -> 450
/*      */     //   423: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   426: aload #8
/*      */     //   428: invokevirtual unpark : (Ljava/lang/Object;)V
/*      */     //   431: goto -> 450
/*      */     //   434: goto -> 276
/*      */     //   437: iload #9
/*      */     //   439: i2s
/*      */     //   440: ifge -> 450
/*      */     //   443: aload_0
/*      */     //   444: invokespecial tryAddWorker : ()V
/*      */     //   447: goto -> 450
/*      */     //   450: aload_2
/*      */     //   451: ifnonnull -> 460
/*      */     //   454: invokestatic helpExpungeStaleExceptions : ()V
/*      */     //   457: goto -> 464
/*      */     //   460: aload_2
/*      */     //   461: invokestatic rethrow : (Ljava/lang/Throwable;)V
/*      */     //   464: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1745	-> 0
/*      */     //   #1746	-> 2
/*      */     //   #1748	-> 15
/*      */     //   #1749	-> 20
/*      */     //   #1750	-> 27
/*      */     //   #1752	-> 52
/*      */     //   #1754	-> 84
/*      */     //   #1755	-> 90
/*      */     //   #1757	-> 105
/*      */     //   #1758	-> 111
/*      */     //   #1759	-> 117
/*      */     //   #1760	-> 144
/*      */     //   #1762	-> 150
/*      */     //   #1763	-> 167
/*      */     //   #1762	-> 176
/*      */     //   #1763	-> 195
/*      */     //   #1768	-> 204
/*      */     //   #1773	-> 252
/*      */     //   #1774	-> 272
/*      */     //   #1776	-> 276
/*      */     //   #1777	-> 302
/*      */     //   #1778	-> 307
/*      */     //   #1781	-> 342
/*      */     //   #1782	-> 345
/*      */     //   #1784	-> 366
/*      */     //   #1785	-> 379
/*      */     //   #1786	-> 382
/*      */     //   #1787	-> 399
/*      */     //   #1788	-> 412
/*      */     //   #1789	-> 423
/*      */     //   #1792	-> 434
/*      */     //   #1794	-> 437
/*      */     //   #1795	-> 443
/*      */     //   #1800	-> 450
/*      */     //   #1801	-> 454
/*      */     //   #1803	-> 460
/*      */     //   #1804	-> 464
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   111	39	10	idx	I
/*      */     //   117	33	11	ws	[Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   59	145	4	ps	I
/*      */     //   27	177	5	ns	J
/*      */     //   41	163	7	sc	J
/*      */     //   105	99	9	nps	I
/*      */     //   420	14	8	p	Ljava/lang/Thread;
/*      */     //   366	68	12	nc	J
/*      */     //   314	123	6	ws	[Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   339	98	7	v	Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   325	112	10	i	I
/*      */     //   290	160	9	u	I
/*      */     //   299	151	11	e	I
/*      */     //   0	465	0	this	Lscala/concurrent/forkjoin/ForkJoinPool;
/*      */     //   0	465	1	wt	Lscala/concurrent/forkjoin/ForkJoinWorkerThread;
/*      */     //   0	465	2	ex	Ljava/lang/Throwable;
/*      */     //   2	463	3	w	Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   218	247	4	c	J
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   105	150	176	finally
/*      */     //   176	178	176	finally
/*      */   }
/*      */   
/*      */   final void externalPush(ForkJoinTask<?> task) {
/*      */     WorkQueue[] ws;
/*      */     WorkQueue q;
/*      */     Submitter z;
/*      */     int m;
/* 1818 */     if ((z = submitters.get()) != null && this.plock > 0 && (ws = this.workQueues) != null && (m = ws.length - 1) >= 0 && (q = ws[m & z.seed & 0x7E]) != null && U.compareAndSwapInt(q, QLOCK, 0, 1)) {
/* 1822 */       int b = q.base, s = q.top;
/*      */       ForkJoinTask<?>[] a;
/*      */       int n, an;
/* 1823 */       if ((a = q.array) != null && (an = a.length) > (n = s + 1 - b)) {
/* 1824 */         int j = ((an - 1 & s) << ASHIFT) + ABASE;
/* 1825 */         U.putOrderedObject(a, j, task);
/* 1826 */         q.top = s + 1;
/* 1827 */         q.qlock = 0;
/* 1828 */         if (n <= 2)
/* 1829 */           signalWork(q); 
/*      */         return;
/*      */       } 
/* 1832 */       q.qlock = 0;
/*      */     } 
/* 1834 */     fullExternalPush(task);
/*      */   }
/*      */   
/*      */   private void fullExternalPush(ForkJoinTask<?> task) {
/*      */     // Byte code:
/*      */     //   0: iconst_0
/*      */     //   1: istore_2
/*      */     //   2: getstatic scala/concurrent/forkjoin/ForkJoinPool.submitters : Ljava/lang/ThreadLocal;
/*      */     //   5: invokevirtual get : ()Ljava/lang/Object;
/*      */     //   8: checkcast scala/concurrent/forkjoin/ForkJoinPool$Submitter
/*      */     //   11: astore_3
/*      */     //   12: aload_3
/*      */     //   13: ifnonnull -> 64
/*      */     //   16: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   19: aload_0
/*      */     //   20: getstatic scala/concurrent/forkjoin/ForkJoinPool.INDEXSEED : J
/*      */     //   23: aload_0
/*      */     //   24: getfield indexSeed : I
/*      */     //   27: dup
/*      */     //   28: istore_2
/*      */     //   29: iload_2
/*      */     //   30: ldc 1640531527
/*      */     //   32: iadd
/*      */     //   33: istore_2
/*      */     //   34: iload_2
/*      */     //   35: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   38: ifeq -> 666
/*      */     //   41: iload_2
/*      */     //   42: ifeq -> 666
/*      */     //   45: getstatic scala/concurrent/forkjoin/ForkJoinPool.submitters : Ljava/lang/ThreadLocal;
/*      */     //   48: new scala/concurrent/forkjoin/ForkJoinPool$Submitter
/*      */     //   51: dup
/*      */     //   52: iload_2
/*      */     //   53: invokespecial <init> : (I)V
/*      */     //   56: dup
/*      */     //   57: astore_3
/*      */     //   58: invokevirtual set : (Ljava/lang/Object;)V
/*      */     //   61: goto -> 666
/*      */     //   64: iload_2
/*      */     //   65: ifne -> 99
/*      */     //   68: aload_3
/*      */     //   69: getfield seed : I
/*      */     //   72: istore_2
/*      */     //   73: iload_2
/*      */     //   74: iload_2
/*      */     //   75: bipush #13
/*      */     //   77: ishl
/*      */     //   78: ixor
/*      */     //   79: istore_2
/*      */     //   80: iload_2
/*      */     //   81: iload_2
/*      */     //   82: bipush #17
/*      */     //   84: iushr
/*      */     //   85: ixor
/*      */     //   86: istore_2
/*      */     //   87: aload_3
/*      */     //   88: iload_2
/*      */     //   89: iload_2
/*      */     //   90: iconst_5
/*      */     //   91: ishl
/*      */     //   92: ixor
/*      */     //   93: putfield seed : I
/*      */     //   96: goto -> 666
/*      */     //   99: aload_0
/*      */     //   100: getfield plock : I
/*      */     //   103: dup
/*      */     //   104: istore #6
/*      */     //   106: ifge -> 117
/*      */     //   109: new java/util/concurrent/RejectedExecutionException
/*      */     //   112: dup
/*      */     //   113: invokespecial <init> : ()V
/*      */     //   116: athrow
/*      */     //   117: iload #6
/*      */     //   119: ifeq -> 143
/*      */     //   122: aload_0
/*      */     //   123: getfield workQueues : [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   126: dup
/*      */     //   127: astore #4
/*      */     //   129: ifnull -> 143
/*      */     //   132: aload #4
/*      */     //   134: arraylength
/*      */     //   135: iconst_1
/*      */     //   136: isub
/*      */     //   137: dup
/*      */     //   138: istore #7
/*      */     //   140: ifge -> 356
/*      */     //   143: aload_0
/*      */     //   144: getfield config : I
/*      */     //   147: ldc 65535
/*      */     //   149: iand
/*      */     //   150: istore #9
/*      */     //   152: iload #9
/*      */     //   154: iconst_1
/*      */     //   155: if_icmple -> 165
/*      */     //   158: iload #9
/*      */     //   160: iconst_1
/*      */     //   161: isub
/*      */     //   162: goto -> 166
/*      */     //   165: iconst_1
/*      */     //   166: istore #10
/*      */     //   168: iload #10
/*      */     //   170: iload #10
/*      */     //   172: iconst_1
/*      */     //   173: iushr
/*      */     //   174: ior
/*      */     //   175: istore #10
/*      */     //   177: iload #10
/*      */     //   179: iload #10
/*      */     //   181: iconst_2
/*      */     //   182: iushr
/*      */     //   183: ior
/*      */     //   184: istore #10
/*      */     //   186: iload #10
/*      */     //   188: iload #10
/*      */     //   190: iconst_4
/*      */     //   191: iushr
/*      */     //   192: ior
/*      */     //   193: istore #10
/*      */     //   195: iload #10
/*      */     //   197: iload #10
/*      */     //   199: bipush #8
/*      */     //   201: iushr
/*      */     //   202: ior
/*      */     //   203: istore #10
/*      */     //   205: iload #10
/*      */     //   207: iload #10
/*      */     //   209: bipush #16
/*      */     //   211: iushr
/*      */     //   212: ior
/*      */     //   213: istore #10
/*      */     //   215: iload #10
/*      */     //   217: iconst_1
/*      */     //   218: iadd
/*      */     //   219: iconst_1
/*      */     //   220: ishl
/*      */     //   221: istore #10
/*      */     //   223: aload_0
/*      */     //   224: getfield workQueues : [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   227: dup
/*      */     //   228: astore #4
/*      */     //   230: ifnull -> 239
/*      */     //   233: aload #4
/*      */     //   235: arraylength
/*      */     //   236: ifne -> 247
/*      */     //   239: iload #10
/*      */     //   241: anewarray scala/concurrent/forkjoin/ForkJoinPool$WorkQueue
/*      */     //   244: goto -> 248
/*      */     //   247: aconst_null
/*      */     //   248: astore #11
/*      */     //   250: aload_0
/*      */     //   251: getfield plock : I
/*      */     //   254: dup
/*      */     //   255: istore #6
/*      */     //   257: iconst_2
/*      */     //   258: iand
/*      */     //   259: ifne -> 282
/*      */     //   262: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   265: aload_0
/*      */     //   266: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   269: iload #6
/*      */     //   271: iinc #6, 2
/*      */     //   274: iload #6
/*      */     //   276: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   279: ifne -> 288
/*      */     //   282: aload_0
/*      */     //   283: invokespecial acquirePlock : ()I
/*      */     //   286: istore #6
/*      */     //   288: aload_0
/*      */     //   289: getfield workQueues : [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   292: dup
/*      */     //   293: astore #4
/*      */     //   295: ifnull -> 304
/*      */     //   298: aload #4
/*      */     //   300: arraylength
/*      */     //   301: ifne -> 315
/*      */     //   304: aload #11
/*      */     //   306: ifnull -> 315
/*      */     //   309: aload_0
/*      */     //   310: aload #11
/*      */     //   312: putfield workQueues : [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   315: iload #6
/*      */     //   317: ldc -2147483648
/*      */     //   319: iand
/*      */     //   320: iload #6
/*      */     //   322: iconst_2
/*      */     //   323: iadd
/*      */     //   324: ldc 2147483647
/*      */     //   326: iand
/*      */     //   327: ior
/*      */     //   328: istore #12
/*      */     //   330: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   333: aload_0
/*      */     //   334: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   337: iload #6
/*      */     //   339: iload #12
/*      */     //   341: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   344: ifne -> 353
/*      */     //   347: aload_0
/*      */     //   348: iload #12
/*      */     //   350: invokespecial releasePlock : (I)V
/*      */     //   353: goto -> 666
/*      */     //   356: aload #4
/*      */     //   358: iload_2
/*      */     //   359: iload #7
/*      */     //   361: iand
/*      */     //   362: bipush #126
/*      */     //   364: iand
/*      */     //   365: dup
/*      */     //   366: istore #8
/*      */     //   368: aaload
/*      */     //   369: dup
/*      */     //   370: astore #5
/*      */     //   372: ifnull -> 527
/*      */     //   375: aload #5
/*      */     //   377: getfield qlock : I
/*      */     //   380: ifne -> 522
/*      */     //   383: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   386: aload #5
/*      */     //   388: getstatic scala/concurrent/forkjoin/ForkJoinPool.QLOCK : J
/*      */     //   391: iconst_0
/*      */     //   392: iconst_1
/*      */     //   393: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   396: ifeq -> 522
/*      */     //   399: aload #5
/*      */     //   401: getfield array : [Lscala/concurrent/forkjoin/ForkJoinTask;
/*      */     //   404: astore #9
/*      */     //   406: aload #5
/*      */     //   408: getfield top : I
/*      */     //   411: istore #10
/*      */     //   413: iconst_0
/*      */     //   414: istore #11
/*      */     //   416: aload #9
/*      */     //   418: ifnull -> 437
/*      */     //   421: aload #9
/*      */     //   423: arraylength
/*      */     //   424: iload #10
/*      */     //   426: iconst_1
/*      */     //   427: iadd
/*      */     //   428: aload #5
/*      */     //   430: getfield base : I
/*      */     //   433: isub
/*      */     //   434: if_icmpgt -> 448
/*      */     //   437: aload #5
/*      */     //   439: invokevirtual growArray : ()[Lscala/concurrent/forkjoin/ForkJoinTask;
/*      */     //   442: dup
/*      */     //   443: astore #9
/*      */     //   445: ifnull -> 490
/*      */     //   448: aload #9
/*      */     //   450: arraylength
/*      */     //   451: iconst_1
/*      */     //   452: isub
/*      */     //   453: iload #10
/*      */     //   455: iand
/*      */     //   456: getstatic scala/concurrent/forkjoin/ForkJoinPool.ASHIFT : I
/*      */     //   459: ishl
/*      */     //   460: getstatic scala/concurrent/forkjoin/ForkJoinPool.ABASE : I
/*      */     //   463: iadd
/*      */     //   464: istore #12
/*      */     //   466: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   469: aload #9
/*      */     //   471: iload #12
/*      */     //   473: i2l
/*      */     //   474: aload_1
/*      */     //   475: invokevirtual putOrderedObject : (Ljava/lang/Object;JLjava/lang/Object;)V
/*      */     //   478: aload #5
/*      */     //   480: iload #10
/*      */     //   482: iconst_1
/*      */     //   483: iadd
/*      */     //   484: putfield top : I
/*      */     //   487: iconst_1
/*      */     //   488: istore #11
/*      */     //   490: aload #5
/*      */     //   492: iconst_0
/*      */     //   493: putfield qlock : I
/*      */     //   496: goto -> 510
/*      */     //   499: astore #13
/*      */     //   501: aload #5
/*      */     //   503: iconst_0
/*      */     //   504: putfield qlock : I
/*      */     //   507: aload #13
/*      */     //   509: athrow
/*      */     //   510: iload #11
/*      */     //   512: ifeq -> 522
/*      */     //   515: aload_0
/*      */     //   516: aload #5
/*      */     //   518: invokevirtual signalWork : (Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;)V
/*      */     //   521: return
/*      */     //   522: iconst_0
/*      */     //   523: istore_2
/*      */     //   524: goto -> 666
/*      */     //   527: aload_0
/*      */     //   528: getfield plock : I
/*      */     //   531: dup
/*      */     //   532: istore #6
/*      */     //   534: iconst_2
/*      */     //   535: iand
/*      */     //   536: ifne -> 664
/*      */     //   539: new scala/concurrent/forkjoin/ForkJoinPool$WorkQueue
/*      */     //   542: dup
/*      */     //   543: aload_0
/*      */     //   544: aconst_null
/*      */     //   545: iconst_m1
/*      */     //   546: iload_2
/*      */     //   547: invokespecial <init> : (Lscala/concurrent/forkjoin/ForkJoinPool;Lscala/concurrent/forkjoin/ForkJoinWorkerThread;II)V
/*      */     //   550: astore #5
/*      */     //   552: aload_0
/*      */     //   553: getfield plock : I
/*      */     //   556: dup
/*      */     //   557: istore #6
/*      */     //   559: iconst_2
/*      */     //   560: iand
/*      */     //   561: ifne -> 584
/*      */     //   564: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   567: aload_0
/*      */     //   568: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   571: iload #6
/*      */     //   573: iinc #6, 2
/*      */     //   576: iload #6
/*      */     //   578: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   581: ifne -> 590
/*      */     //   584: aload_0
/*      */     //   585: invokespecial acquirePlock : ()I
/*      */     //   588: istore #6
/*      */     //   590: aload_0
/*      */     //   591: getfield workQueues : [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   594: dup
/*      */     //   595: astore #4
/*      */     //   597: ifnull -> 623
/*      */     //   600: iload #8
/*      */     //   602: aload #4
/*      */     //   604: arraylength
/*      */     //   605: if_icmpge -> 623
/*      */     //   608: aload #4
/*      */     //   610: iload #8
/*      */     //   612: aaload
/*      */     //   613: ifnonnull -> 623
/*      */     //   616: aload #4
/*      */     //   618: iload #8
/*      */     //   620: aload #5
/*      */     //   622: aastore
/*      */     //   623: iload #6
/*      */     //   625: ldc -2147483648
/*      */     //   627: iand
/*      */     //   628: iload #6
/*      */     //   630: iconst_2
/*      */     //   631: iadd
/*      */     //   632: ldc 2147483647
/*      */     //   634: iand
/*      */     //   635: ior
/*      */     //   636: istore #9
/*      */     //   638: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   641: aload_0
/*      */     //   642: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   645: iload #6
/*      */     //   647: iload #9
/*      */     //   649: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   652: ifne -> 661
/*      */     //   655: aload_0
/*      */     //   656: iload #9
/*      */     //   658: invokespecial releasePlock : (I)V
/*      */     //   661: goto -> 666
/*      */     //   664: iconst_0
/*      */     //   665: istore_2
/*      */     //   666: goto -> 12
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1855	-> 0
/*      */     //   #1856	-> 2
/*      */     //   #1858	-> 12
/*      */     //   #1859	-> 16
/*      */     //   #1861	-> 45
/*      */     //   #1863	-> 64
/*      */     //   #1864	-> 68
/*      */     //   #1865	-> 73
/*      */     //   #1866	-> 80
/*      */     //   #1867	-> 87
/*      */     //   #1869	-> 99
/*      */     //   #1870	-> 109
/*      */     //   #1871	-> 117
/*      */     //   #1873	-> 143
/*      */     //   #1874	-> 152
/*      */     //   #1875	-> 168
/*      */     //   #1876	-> 195
/*      */     //   #1877	-> 223
/*      */     //   #1879	-> 250
/*      */     //   #1881	-> 282
/*      */     //   #1882	-> 288
/*      */     //   #1883	-> 309
/*      */     //   #1884	-> 315
/*      */     //   #1885	-> 330
/*      */     //   #1886	-> 347
/*      */     //   #1887	-> 353
/*      */     //   #1888	-> 356
/*      */     //   #1889	-> 375
/*      */     //   #1890	-> 399
/*      */     //   #1891	-> 406
/*      */     //   #1892	-> 413
/*      */     //   #1894	-> 416
/*      */     //   #1896	-> 448
/*      */     //   #1897	-> 466
/*      */     //   #1898	-> 478
/*      */     //   #1899	-> 487
/*      */     //   #1902	-> 490
/*      */     //   #1903	-> 496
/*      */     //   #1902	-> 499
/*      */     //   #1904	-> 510
/*      */     //   #1905	-> 515
/*      */     //   #1906	-> 521
/*      */     //   #1909	-> 522
/*      */     //   #1911	-> 527
/*      */     //   #1912	-> 539
/*      */     //   #1913	-> 552
/*      */     //   #1915	-> 584
/*      */     //   #1916	-> 590
/*      */     //   #1917	-> 616
/*      */     //   #1918	-> 623
/*      */     //   #1919	-> 638
/*      */     //   #1920	-> 655
/*      */     //   #1921	-> 661
/*      */     //   #1923	-> 664
/*      */     //   #1924	-> 666
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   129	14	4	ws	[Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   140	3	7	m	I
/*      */     //   152	201	9	p	I
/*      */     //   168	185	10	n	I
/*      */     //   250	103	11	nws	[Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   330	23	12	nps	I
/*      */     //   466	24	12	j	I
/*      */     //   406	116	9	a	[Lscala/concurrent/forkjoin/ForkJoinTask;
/*      */     //   413	109	10	s	I
/*      */     //   416	106	11	submitted	Z
/*      */     //   638	23	9	nps	I
/*      */     //   230	436	4	ws	[Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   372	294	5	q	Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   106	560	6	ps	I
/*      */     //   356	310	7	m	I
/*      */     //   368	298	8	k	I
/*      */     //   12	657	3	z	Lscala/concurrent/forkjoin/ForkJoinPool$Submitter;
/*      */     //   0	669	0	this	Lscala/concurrent/forkjoin/ForkJoinPool;
/*      */     //   0	669	1	task	Lscala/concurrent/forkjoin/ForkJoinTask;
/*      */     //   2	667	2	r	I
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   406	116	9	a	[Lscala/concurrent/forkjoin/ForkJoinTask<*>;
/*      */     //   0	669	1	task	Lscala/concurrent/forkjoin/ForkJoinTask<*>;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   416	490	499	finally
/*      */     //   499	501	499	finally
/*      */   }
/*      */   
/*      */   final void incrementActiveCount() {
/*      */     long c;
/*      */     do {
/*      */     
/* 1934 */     } while (!U.compareAndSwapLong(this, CTL, c = this.ctl, c + 281474976710656L));
/*      */   }
/*      */   
/*      */   final void signalWork(WorkQueue q) {
/* 1943 */     int hint = q.poolIndex;
/*      */     long c;
/*      */     int u;
/* 1945 */     while ((u = (int)((c = this.ctl) >>> 32L)) < 0) {
/*      */       int e;
/* 1946 */       if ((e = (int)c) > 0) {
/*      */         int i;
/*      */         WorkQueue[] ws;
/*      */         WorkQueue w;
/* 1947 */         if ((ws = this.workQueues) != null && ws.length > (i = e & 0xFFFF) && (w = ws[i]) != null && w.eventCount == (e | Integer.MIN_VALUE)) {
/* 1949 */           long nc = (w.nextWait & Integer.MAX_VALUE) | (u + 65536) << 32L;
/* 1951 */           if (U.compareAndSwapLong(this, CTL, c, nc)) {
/* 1952 */             w.hint = hint;
/* 1953 */             w.eventCount = e + 65536 & Integer.MAX_VALUE;
/*      */             Thread p;
/* 1954 */             if ((p = w.parker) != null)
/* 1955 */               U.unpark(p); 
/*      */             break;
/*      */           } 
/* 1958 */           if (q.top - q.base <= 0)
/*      */             break; 
/*      */           continue;
/*      */         } 
/*      */         break;
/*      */       } 
/* 1965 */       if ((short)u < 0)
/* 1966 */         tryAddWorker(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   final void runWorker(WorkQueue w) {
/* 1978 */     w.growArray();
/*      */     do {
/* 1979 */       w.runTask(scan(w));
/* 1979 */     } while (w.qlock >= 0);
/*      */   }
/*      */   
/*      */   private final ForkJoinTask<?> scan(WorkQueue w) {
/* 2021 */     int ps = this.plock;
/*      */     WorkQueue[] ws;
/*      */     int m;
/* 2022 */     if (w != null && (ws = this.workQueues) != null && (m = ws.length - 1) >= 0) {
/* 2023 */       int ec = w.eventCount;
/* 2024 */       int r = w.seed;
/* 2024 */       r ^= r << 13;
/* 2024 */       r ^= r >>> 17;
/* 2024 */       w.seed = r ^= r << 5;
/* 2025 */       w.hint = -1;
/* 2026 */       int j = (m + m + 1 | 0x1FF) & 0x1FFFF;
/*      */       do {
/*      */         WorkQueue q;
/*      */         ForkJoinTask<?>[] a;
/*      */         int b;
/* 2029 */         if ((q = ws[r + j & m]) == null || (b = q.base) - q.top >= 0 || (a = q.array) == null)
/*      */           continue; 
/* 2031 */         int i = ((a.length - 1 & b) << ASHIFT) + ABASE;
/* 2032 */         ForkJoinTask<?> t = (ForkJoinTask)U.getObjectVolatile(a, i);
/* 2034 */         if (q.base == b && ec >= 0 && t != null && U.compareAndSwapObject(a, i, t, null)) {
/* 2036 */           if ((q.base = b + 1) - q.top < 0)
/* 2037 */             signalWork(q); 
/* 2038 */           return t;
/*      */         } 
/* 2040 */         if ((ec < 0 || j < m) && (int)(this.ctl >> 48L) <= 0) {
/* 2041 */           w.hint = r + j & m;
/*      */           break;
/*      */         } 
/* 2045 */       } while (--j >= 0);
/*      */       int ns;
/* 2048 */       if ((ns = w.nsteals) != 0) {
/*      */         long sc;
/* 2049 */         if (U.compareAndSwapLong(this, STEALCOUNT, sc = this.stealCount, sc + ns))
/* 2051 */           w.nsteals = 0; 
/* 2053 */       } else if (this.plock == ps) {
/*      */         int e;
/*      */         long c;
/* 2055 */         if ((e = (int)(c = this.ctl)) < 0) {
/* 2056 */           w.qlock = -1;
/*      */         } else {
/*      */           int h;
/* 2058 */           if ((h = w.hint) < 0)
/* 2059 */             if (ec >= 0) {
/* 2060 */               long nc = ec | c - 281474976710656L & 0xFFFFFFFF00000000L;
/* 2062 */               w.nextWait = e;
/* 2063 */               w.eventCount = ec | Integer.MIN_VALUE;
/* 2064 */               if (this.ctl != c || !U.compareAndSwapLong(this, CTL, c, nc)) {
/* 2065 */                 w.eventCount = ec;
/* 2066 */               } else if ((int)(c >> 48L) == 1 - (this.config & 0xFFFF)) {
/* 2067 */                 idleAwaitWork(w, nc, c);
/*      */               } 
/* 2069 */             } else if (w.eventCount < 0 && this.ctl == c) {
/* 2070 */               Thread wt = Thread.currentThread();
/* 2071 */               Thread.interrupted();
/* 2072 */               U.putObject(wt, PARKBLOCKER, this);
/* 2073 */               w.parker = wt;
/* 2074 */               if (w.eventCount < 0)
/* 2075 */                 U.park(false, 0L); 
/* 2076 */               w.parker = null;
/* 2077 */               U.putObject(wt, PARKBLOCKER, null);
/*      */             }  
/*      */           WorkQueue q;
/* 2080 */           if ((h >= 0 || (h = w.hint) >= 0) && (ws = this.workQueues) != null && h < ws.length && (q = ws[h]) != null) {
/* 2084 */             int n = (this.config & 0xFFFF) - 1;
/*      */             do {
/* 2085 */               int idleCount = (w.eventCount < 0) ? 0 : -1;
/*      */               WorkQueue v;
/*      */               int u, i, s;
/* 2086 */               if (((s = idleCount - q.base + q.top) <= n && (n = s) <= 0) || (u = (int)((c = this.ctl) >>> 32L)) >= 0 || (e = (int)c) <= 0 || m < (i = e & 0xFFFF) || (v = ws[i]) == null)
/*      */                 break; 
/* 2092 */               long nc = (v.nextWait & Integer.MAX_VALUE) | (u + 65536) << 32L;
/* 2094 */               if (v.eventCount != (e | Integer.MIN_VALUE) || !U.compareAndSwapLong(this, CTL, c, nc))
/*      */                 break; 
/* 2097 */               v.hint = h;
/* 2098 */               v.eventCount = e + 65536 & Integer.MAX_VALUE;
/*      */               Thread p;
/* 2099 */               if ((p = v.parker) == null)
/*      */                 continue; 
/* 2100 */               U.unpark(p);
/* 2101 */             } while (--n > 0);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2107 */     return null;
/*      */   }
/*      */   
/*      */   private void idleAwaitWork(WorkQueue w, long currentCtl, long prevCtl) {
/* 2123 */     if (w != null && w.eventCount < 0 && !tryTerminate(false, false) && (int)prevCtl != 0 && this.ctl == currentCtl) {
/* 2126 */       int dc = -((short)(int)(currentCtl >>> 32L));
/* 2127 */       long parkTime = (dc < 0) ? 200000000L : ((dc + 1) * 2000000000L);
/* 2128 */       long deadline = System.nanoTime() + parkTime - 2000000L;
/* 2129 */       Thread wt = Thread.currentThread();
/* 2130 */       while (this.ctl == currentCtl) {
/* 2131 */         Thread.interrupted();
/* 2132 */         U.putObject(wt, PARKBLOCKER, this);
/* 2133 */         w.parker = wt;
/* 2134 */         if (this.ctl == currentCtl)
/* 2135 */           U.park(false, parkTime); 
/* 2136 */         w.parker = null;
/* 2137 */         U.putObject(wt, PARKBLOCKER, null);
/* 2138 */         if (this.ctl != currentCtl)
/*      */           break; 
/* 2140 */         if (deadline - System.nanoTime() <= 0L && U.compareAndSwapLong(this, CTL, currentCtl, prevCtl)) {
/* 2142 */           w.eventCount = w.eventCount + 65536 | Integer.MAX_VALUE;
/* 2143 */           w.hint = -1;
/* 2144 */           w.qlock = -1;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void helpSignal(ForkJoinTask<?> task, int origin) {
/*      */     WorkQueue[] ws;
/*      */     int m;
/*      */     int u;
/* 2161 */     if (task != null && task.status >= 0 && (u = (int)(this.ctl >>> 32L)) < 0 && u >> 16 < 0 && (ws = this.workQueues) != null && (m = ws.length - 1) >= 0) {
/*      */       int k;
/*      */       int j;
/* 2164 */       label46: for (k = origin, j = m; j >= 0; ) {
/* 2165 */         WorkQueue q = ws[k++ & m];
/* 2166 */         int n = m;
/* 2167 */         while (task.status >= 0) {
/* 2169 */           if (q != null) {
/*      */             int s;
/* 2169 */             if ((s = -q.base + q.top) <= n && (n = s) <= 0)
/*      */               continue label46; 
/*      */             WorkQueue w;
/*      */             long c;
/*      */             int e, i;
/* 2172 */             if ((u = (int)((c = this.ctl) >>> 32L)) >= 0 || (e = (int)c) <= 0 || m < (i = e & 0xFFFF) || (w = ws[i]) == null)
/*      */               break; 
/* 2176 */             long nc = (w.nextWait & Integer.MAX_VALUE) | (u + 65536) << 32L;
/* 2178 */             if (w.eventCount != (e | Integer.MIN_VALUE))
/*      */               break; 
/* 2180 */             if (U.compareAndSwapLong(this, CTL, c, nc)) {
/* 2181 */               w.eventCount = e + 65536 & Integer.MAX_VALUE;
/*      */               Thread p;
/* 2182 */               if ((p = w.parker) != null)
/* 2183 */                 U.unpark(p); 
/* 2184 */               if (--n <= 0)
/*      */                 j--; 
/*      */             } 
/*      */             continue;
/*      */           } 
/*      */           continue label46;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private int tryHelpStealer(WorkQueue joiner, ForkJoinTask<?> task) {
/* 2211 */     int stat = 0, steps = 0;
/* 2212 */     if (joiner != null && task != null)
/*      */       label75: while (true) {
/* 2214 */         ForkJoinTask<?> subtask = task;
/* 2215 */         WorkQueue j = joiner;
/*      */         while (true) {
/*      */           int s;
/* 2217 */           if ((s = task.status) < 0) {
/* 2218 */             stat = s;
/*      */             break;
/*      */           } 
/*      */           WorkQueue[] ws;
/*      */           int m;
/* 2221 */           if ((ws = this.workQueues) == null || (m = ws.length - 1) <= 0)
/*      */             break; 
/*      */           WorkQueue v;
/*      */           int h;
/* 2223 */           if ((v = ws[h = (j.hint | 0x1) & m]) == null || v.currentSteal != subtask) {
/* 2225 */             int origin = h;
/*      */             do {
/* 2226 */               if (((h = h + 2 & m) & 0xF) == 1)
/* 2226 */                 if (subtask.status >= 0) {
/* 2226 */                   if (j.currentJoin != subtask)
/*      */                     continue label75; 
/*      */                 } else {
/*      */                   continue label75;
/*      */                 }  
/* 2229 */               if ((v = ws[h]) != null && v.currentSteal == subtask) {
/* 2231 */                 j.hint = h;
/*      */                 continue label68;
/*      */               } 
/* 2234 */             } while (h != origin);
/*      */             break;
/*      */           } 
/* 2240 */           label68: while (subtask.status >= 0) {
/*      */             ForkJoinTask<?>[] arrayOfForkJoinTask;
/*      */             int b;
/* 2242 */             if ((b = v.base) - v.top < 0 && (arrayOfForkJoinTask = v.array) != null) {
/* 2243 */               int i = ((arrayOfForkJoinTask.length - 1 & b) << ASHIFT) + ABASE;
/* 2244 */               ForkJoinTask<?> t = (ForkJoinTask)U.getObjectVolatile(arrayOfForkJoinTask, i);
/* 2246 */               if (subtask.status < 0 || j.currentJoin != subtask || v.currentSteal != subtask)
/*      */                 break; 
/* 2249 */               stat = 1;
/* 2250 */               if (t != null && v.base == b && U.compareAndSwapObject(arrayOfForkJoinTask, i, t, null)) {
/* 2252 */                 v.base = b + 1;
/* 2253 */                 joiner.runSubtask(t);
/*      */                 continue;
/*      */               } 
/* 2255 */               if (v.base == b && ++steps == 64)
/*      */                 // Byte code: goto -> 429 
/*      */               continue;
/*      */             } 
/* 2259 */             ForkJoinTask<?> next = v.currentJoin;
/* 2260 */             if (subtask.status >= 0) {
/* 2260 */               if (j.currentJoin == subtask) {
/* 2260 */                 if (v.currentSteal != subtask)
/*      */                   continue label75; 
/* 2263 */                 if (next == null || ++steps == 64)
/*      */                   break; 
/* 2266 */                 subtask = next;
/* 2267 */                 j = v;
/*      */                 continue;
/*      */               } 
/*      */               continue label75;
/*      */             } 
/*      */             continue label75;
/*      */           } 
/*      */           continue label75;
/*      */         } 
/*      */         break;
/*      */       }  
/* 2275 */     return stat;
/*      */   }
/*      */   
/*      */   private int helpComplete(ForkJoinTask<?> task, int mode) {
/*      */     WorkQueue[] ws;
/*      */     int m;
/* 2288 */     if (task != null && (ws = this.workQueues) != null && (m = ws.length - 1) >= 0) {
/* 2290 */       int j = 1, origin = j;
/*      */       while (true) {
/*      */         int s;
/* 2291 */         if ((s = task.status) < 0)
/* 2292 */           return s; 
/*      */         WorkQueue q;
/* 2293 */         if ((q = ws[j & m]) != null && q.pollAndExecCC(task)) {
/* 2294 */           origin = j;
/*      */           int u;
/* 2295 */           if (mode == -1 && ((u = (int)(this.ctl >>> 32L)) >= 0 || u >> 16 >= 0))
/*      */             break; 
/*      */           continue;
/*      */         } 
/* 2299 */         if ((j = j + 2 & m) == origin)
/*      */           break; 
/*      */       } 
/*      */     } 
/* 2303 */     return 0;
/*      */   }
/*      */   
/*      */   final boolean tryCompensate() {
/* 2314 */     int pc = this.config & 0xFFFF;
/*      */     int e;
/*      */     long c;
/*      */     WorkQueue[] ws;
/* 2316 */     if ((ws = this.workQueues) != null && (e = (int)(c = this.ctl)) >= 0) {
/*      */       int i;
/*      */       WorkQueue w;
/* 2317 */       if (e != 0 && (i = e & 0xFFFF) < ws.length && (w = ws[i]) != null && w.eventCount == (e | Integer.MIN_VALUE)) {
/* 2319 */         long nc = (w.nextWait & Integer.MAX_VALUE) | c & 0xFFFFFFFF00000000L;
/* 2321 */         if (U.compareAndSwapLong(this, CTL, c, nc)) {
/* 2322 */           w.eventCount = e + 65536 & Integer.MAX_VALUE;
/*      */           Thread p;
/* 2323 */           if ((p = w.parker) != null)
/* 2324 */             U.unpark(p); 
/* 2325 */           return true;
/*      */         } 
/*      */       } else {
/*      */         int tc;
/* 2328 */         if ((tc = (short)(int)(c >>> 32L)) >= 0 && (int)(c >> 48L) + pc > 1) {
/* 2330 */           long nc = c - 281474976710656L & 0xFFFF000000000000L | c & 0xFFFFFFFFFFFFL;
/* 2331 */           if (U.compareAndSwapLong(this, CTL, c, nc))
/* 2332 */             return true; 
/* 2334 */         } else if (tc + pc < 32767) {
/* 2335 */           long nc = c + 4294967296L & 0xFFFF00000000L | c & 0xFFFF0000FFFFFFFFL;
/* 2336 */           if (U.compareAndSwapLong(this, CTL, c, nc)) {
/* 2338 */             Throwable ex = null;
/* 2339 */             ForkJoinWorkerThread wt = null;
/*      */             try {
/*      */               ForkJoinWorkerThreadFactory fac;
/* 2341 */               if ((fac = this.factory) != null && (wt = fac.newThread(this)) != null) {
/* 2343 */                 wt.start();
/* 2344 */                 return true;
/*      */               } 
/* 2346 */             } catch (Throwable rex) {
/* 2347 */               ex = rex;
/*      */             } 
/* 2349 */             deregisterWorker(wt, ex);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2353 */     return false;
/*      */   }
/*      */   
/*      */   final int awaitJoin(WorkQueue joiner, ForkJoinTask<?> task) {
/* 2364 */     int s = 0;
/* 2365 */     if (joiner != null && task != null && (s = task.status) >= 0) {
/* 2366 */       ForkJoinTask<?> prevJoin = joiner.currentJoin;
/* 2367 */       joiner.currentJoin = task;
/*      */       do {
/*      */       
/* 2368 */       } while ((s = task.status) >= 0 && !joiner.isEmpty() && joiner.tryRemoveAndExec(task));
/* 2371 */       helpSignal(task, joiner.poolIndex);
/* 2372 */       if (s >= 0 && (s = task.status) >= 0 && (s = task.status) >= 0 && task instanceof CountedCompleter)
/* 2374 */         s = helpComplete(task, 0); 
/* 2376 */       while (s >= 0 && (s = task.status) >= 0) {
/* 2377 */         if ((!joiner.isEmpty() || (s = tryHelpStealer(joiner, task)) == 0) && (s = task.status) >= 0) {
/* 2380 */           helpSignal(task, joiner.poolIndex);
/* 2381 */           if ((s = task.status) >= 0 && tryCompensate()) {
/* 2382 */             if (task.trySetSignal() && (s = task.status) >= 0)
/* 2383 */               synchronized (task) {
/* 2384 */                 if (task.status >= 0) {
/*      */                   try {
/* 2386 */                     task.wait();
/* 2387 */                   } catch (InterruptedException ie) {}
/*      */                 } else {
/* 2391 */                   task.notifyAll();
/*      */                 } 
/*      */               }  
/*      */             long c;
/*      */             do {
/*      */             
/* 2395 */             } while (!U.compareAndSwapLong(this, CTL, c = this.ctl, c + 281474976710656L));
/*      */           } 
/*      */         } 
/*      */       } 
/* 2400 */       joiner.currentJoin = prevJoin;
/*      */     } 
/* 2402 */     return s;
/*      */   }
/*      */   
/*      */   final void helpJoinOnce(WorkQueue joiner, ForkJoinTask<?> task) {
/*      */     int s;
/* 2415 */     if (joiner != null && task != null && (s = task.status) >= 0) {
/* 2416 */       ForkJoinTask<?> prevJoin = joiner.currentJoin;
/* 2417 */       joiner.currentJoin = task;
/*      */       do {
/*      */       
/* 2418 */       } while ((s = task.status) >= 0 && !joiner.isEmpty() && joiner.tryRemoveAndExec(task));
/* 2421 */       helpSignal(task, joiner.poolIndex);
/* 2422 */       if (s >= 0 && (s = task.status) >= 0 && (s = task.status) >= 0 && task instanceof CountedCompleter)
/* 2424 */         s = helpComplete(task, 0); 
/* 2426 */       if (s >= 0 && joiner.isEmpty())
/*      */         do {
/*      */         
/* 2428 */         } while (task.status >= 0 && tryHelpStealer(joiner, task) > 0); 
/* 2430 */       joiner.currentJoin = prevJoin;
/*      */     } 
/*      */   }
/*      */   
/*      */   private WorkQueue findNonEmptyStealQueue(int r) {
/*      */     while (true) {
/* 2442 */       int ps = this.plock;
/*      */       int m;
/*      */       WorkQueue[] ws;
/* 2443 */       if ((ws = this.workQueues) != null && (m = ws.length - 1) >= 0)
/* 2444 */         for (int j = m + 1 << 2; j >= 0; j--) {
/*      */           WorkQueue q;
/* 2445 */           if ((q = ws[(r + j << 1 | 0x1) & m]) != null && q.base - q.top < 0)
/* 2447 */             return q; 
/*      */         }  
/* 2450 */       if (this.plock == ps)
/* 2451 */         return null; 
/*      */     } 
/*      */   }
/*      */   
/*      */   final void helpQuiescePool(WorkQueue w) {
/* 2462 */     boolean active = true;
/*      */     while (true) {
/*      */       ForkJoinTask<?> t;
/* 2464 */       while ((t = w.nextLocalTask()) != null) {
/* 2465 */         if (w.base - w.top < 0)
/* 2466 */           signalWork(w); 
/* 2467 */         t.doExec();
/*      */       } 
/*      */       WorkQueue q;
/* 2469 */       if ((q = findNonEmptyStealQueue(w.nextSeed())) != null) {
/* 2470 */         if (!active) {
/* 2471 */           active = true;
/*      */           long l;
/*      */           do {
/*      */           
/* 2472 */           } while (!U.compareAndSwapLong(this, CTL, l = this.ctl, l + 281474976710656L));
/*      */         } 
/*      */         int b;
/* 2475 */         if ((b = q.base) - q.top < 0 && (t = q.pollAt(b)) != null) {
/* 2476 */           if (q.base - q.top < 0)
/* 2477 */             signalWork(q); 
/* 2478 */           w.runSubtask(t);
/*      */         } 
/*      */         continue;
/*      */       } 
/* 2481 */       if (active) {
/* 2482 */         long l1, nc = (l1 = this.ctl) - 281474976710656L;
/* 2483 */         if ((int)(nc >> 48L) + (this.config & 0xFFFF) == 0)
/*      */           return; 
/* 2485 */         if (U.compareAndSwapLong(this, CTL, l1, nc))
/* 2486 */           active = false; 
/*      */         continue;
/*      */       } 
/*      */       long c;
/* 2488 */       if ((int)((c = this.ctl) >> 48L) + (this.config & 0xFFFF) == 0 && U.compareAndSwapLong(this, CTL, c, c + 281474976710656L))
/*      */         break; 
/*      */     } 
/*      */   }
/*      */   
/*      */   final ForkJoinTask<?> nextTaskFor(WorkQueue w) {
/*      */     while (true) {
/*      */       ForkJoinTask<?> t;
/* 2502 */       if ((t = w.nextLocalTask()) != null)
/* 2503 */         return t; 
/*      */       WorkQueue q;
/* 2504 */       if ((q = findNonEmptyStealQueue(w.nextSeed())) == null)
/* 2505 */         return null; 
/*      */       int b;
/* 2506 */       if ((b = q.base) - q.top < 0 && (t = q.pollAt(b)) != null) {
/* 2507 */         if (q.base - q.top < 0)
/* 2508 */           signalWork(q); 
/* 2509 */         return t;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   static int getSurplusQueuedTaskCount() {
/*      */     Thread t;
/* 2562 */     if (t = Thread.currentThread() instanceof ForkJoinWorkerThread) {
/*      */       ForkJoinWorkerThread wt;
/*      */       ForkJoinPool pool;
/* 2563 */       int p = (pool = (wt = (ForkJoinWorkerThread)t).pool).config & 0xFFFF;
/*      */       WorkQueue q;
/* 2564 */       int n = (q = wt.workQueue).top - q.base;
/* 2565 */       int a = (int)(pool.ctl >> 48L) + p;
/* 2566 */       return n - ((a > (p >>>= 1)) ? 0 : ((a > (p >>>= 1)) ? 1 : ((a > (p >>>= 1)) ? 2 : ((a > (p >>>= 1)) ? 4 : 8))));
/*      */     } 
/* 2572 */     return 0;
/*      */   }
/*      */   
/*      */   private boolean tryTerminate(boolean now, boolean enable) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getstatic scala/concurrent/forkjoin/ForkJoinPool.common : Lscala/concurrent/forkjoin/ForkJoinPool;
/*      */     //   4: if_acmpne -> 9
/*      */     //   7: iconst_0
/*      */     //   8: ireturn
/*      */     //   9: aload_0
/*      */     //   10: getfield plock : I
/*      */     //   13: dup
/*      */     //   14: istore_3
/*      */     //   15: iflt -> 86
/*      */     //   18: iload_2
/*      */     //   19: ifne -> 24
/*      */     //   22: iconst_0
/*      */     //   23: ireturn
/*      */     //   24: iload_3
/*      */     //   25: iconst_2
/*      */     //   26: iand
/*      */     //   27: ifne -> 48
/*      */     //   30: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   33: aload_0
/*      */     //   34: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   37: iload_3
/*      */     //   38: iinc #3, 2
/*      */     //   41: iload_3
/*      */     //   42: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   45: ifne -> 53
/*      */     //   48: aload_0
/*      */     //   49: invokespecial acquirePlock : ()I
/*      */     //   52: istore_3
/*      */     //   53: iload_3
/*      */     //   54: iconst_2
/*      */     //   55: iadd
/*      */     //   56: ldc 2147483647
/*      */     //   58: iand
/*      */     //   59: ldc -2147483648
/*      */     //   61: ior
/*      */     //   62: istore #4
/*      */     //   64: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   67: aload_0
/*      */     //   68: getstatic scala/concurrent/forkjoin/ForkJoinPool.PLOCK : J
/*      */     //   71: iload_3
/*      */     //   72: iload #4
/*      */     //   74: invokevirtual compareAndSwapInt : (Ljava/lang/Object;JII)Z
/*      */     //   77: ifne -> 86
/*      */     //   80: aload_0
/*      */     //   81: iload #4
/*      */     //   83: invokespecial releasePlock : (I)V
/*      */     //   86: aload_0
/*      */     //   87: getfield ctl : J
/*      */     //   90: dup2
/*      */     //   91: lstore #4
/*      */     //   93: ldc2_w 2147483648
/*      */     //   96: land
/*      */     //   97: lconst_0
/*      */     //   98: lcmp
/*      */     //   99: ifeq -> 145
/*      */     //   102: lload #4
/*      */     //   104: bipush #32
/*      */     //   106: lushr
/*      */     //   107: l2i
/*      */     //   108: i2s
/*      */     //   109: aload_0
/*      */     //   110: getfield config : I
/*      */     //   113: ldc 65535
/*      */     //   115: iand
/*      */     //   116: ineg
/*      */     //   117: if_icmpne -> 143
/*      */     //   120: aload_0
/*      */     //   121: dup
/*      */     //   122: astore #6
/*      */     //   124: monitorenter
/*      */     //   125: aload_0
/*      */     //   126: invokevirtual notifyAll : ()V
/*      */     //   129: aload #6
/*      */     //   131: monitorexit
/*      */     //   132: goto -> 143
/*      */     //   135: astore #7
/*      */     //   137: aload #6
/*      */     //   139: monitorexit
/*      */     //   140: aload #7
/*      */     //   142: athrow
/*      */     //   143: iconst_1
/*      */     //   144: ireturn
/*      */     //   145: iload_1
/*      */     //   146: ifne -> 239
/*      */     //   149: lload #4
/*      */     //   151: bipush #48
/*      */     //   153: lshr
/*      */     //   154: l2i
/*      */     //   155: aload_0
/*      */     //   156: getfield config : I
/*      */     //   159: ldc 65535
/*      */     //   161: iand
/*      */     //   162: ineg
/*      */     //   163: if_icmpeq -> 168
/*      */     //   166: iconst_0
/*      */     //   167: ireturn
/*      */     //   168: aload_0
/*      */     //   169: getfield workQueues : [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   172: dup
/*      */     //   173: astore #6
/*      */     //   175: ifnull -> 239
/*      */     //   178: iconst_0
/*      */     //   179: istore #8
/*      */     //   181: iload #8
/*      */     //   183: aload #6
/*      */     //   185: arraylength
/*      */     //   186: if_icmpge -> 239
/*      */     //   189: aload #6
/*      */     //   191: iload #8
/*      */     //   193: aaload
/*      */     //   194: dup
/*      */     //   195: astore #7
/*      */     //   197: ifnull -> 233
/*      */     //   200: aload #7
/*      */     //   202: invokevirtual isEmpty : ()Z
/*      */     //   205: ifne -> 216
/*      */     //   208: aload_0
/*      */     //   209: aload #7
/*      */     //   211: invokevirtual signalWork : (Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;)V
/*      */     //   214: iconst_0
/*      */     //   215: ireturn
/*      */     //   216: iload #8
/*      */     //   218: iconst_1
/*      */     //   219: iand
/*      */     //   220: ifeq -> 233
/*      */     //   223: aload #7
/*      */     //   225: getfield eventCount : I
/*      */     //   228: iflt -> 233
/*      */     //   231: iconst_0
/*      */     //   232: ireturn
/*      */     //   233: iinc #8, 1
/*      */     //   236: goto -> 181
/*      */     //   239: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   242: aload_0
/*      */     //   243: getstatic scala/concurrent/forkjoin/ForkJoinPool.CTL : J
/*      */     //   246: lload #4
/*      */     //   248: lload #4
/*      */     //   250: ldc2_w 2147483648
/*      */     //   253: lor
/*      */     //   254: invokevirtual compareAndSwapLong : (Ljava/lang/Object;JJJ)Z
/*      */     //   257: ifeq -> 86
/*      */     //   260: iconst_0
/*      */     //   261: istore #6
/*      */     //   263: iload #6
/*      */     //   265: iconst_3
/*      */     //   266: if_icmpge -> 522
/*      */     //   269: aload_0
/*      */     //   270: getfield workQueues : [Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   273: dup
/*      */     //   274: astore #7
/*      */     //   276: ifnull -> 516
/*      */     //   279: aload #7
/*      */     //   281: arraylength
/*      */     //   282: istore #10
/*      */     //   284: iconst_0
/*      */     //   285: istore #11
/*      */     //   287: iload #11
/*      */     //   289: iload #10
/*      */     //   291: if_icmpge -> 370
/*      */     //   294: aload #7
/*      */     //   296: iload #11
/*      */     //   298: aaload
/*      */     //   299: dup
/*      */     //   300: astore #8
/*      */     //   302: ifnull -> 364
/*      */     //   305: aload #8
/*      */     //   307: iconst_m1
/*      */     //   308: putfield qlock : I
/*      */     //   311: iload #6
/*      */     //   313: ifle -> 364
/*      */     //   316: aload #8
/*      */     //   318: invokevirtual cancelAll : ()V
/*      */     //   321: iload #6
/*      */     //   323: iconst_1
/*      */     //   324: if_icmple -> 364
/*      */     //   327: aload #8
/*      */     //   329: getfield owner : Lscala/concurrent/forkjoin/ForkJoinWorkerThread;
/*      */     //   332: dup
/*      */     //   333: astore #9
/*      */     //   335: ifnull -> 364
/*      */     //   338: aload #9
/*      */     //   340: invokevirtual isInterrupted : ()Z
/*      */     //   343: ifne -> 356
/*      */     //   346: aload #9
/*      */     //   348: invokevirtual interrupt : ()V
/*      */     //   351: goto -> 356
/*      */     //   354: astore #12
/*      */     //   356: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   359: aload #9
/*      */     //   361: invokevirtual unpark : (Ljava/lang/Object;)V
/*      */     //   364: iinc #11, 1
/*      */     //   367: goto -> 287
/*      */     //   370: aload_0
/*      */     //   371: getfield ctl : J
/*      */     //   374: dup2
/*      */     //   375: lstore #13
/*      */     //   377: l2i
/*      */     //   378: ldc 2147483647
/*      */     //   380: iand
/*      */     //   381: dup
/*      */     //   382: istore #12
/*      */     //   384: ifeq -> 516
/*      */     //   387: iload #12
/*      */     //   389: ldc 65535
/*      */     //   391: iand
/*      */     //   392: dup
/*      */     //   393: istore #11
/*      */     //   395: iload #10
/*      */     //   397: if_icmpge -> 516
/*      */     //   400: iload #11
/*      */     //   402: iflt -> 516
/*      */     //   405: aload #7
/*      */     //   407: iload #11
/*      */     //   409: aaload
/*      */     //   410: dup
/*      */     //   411: astore #8
/*      */     //   413: ifnull -> 516
/*      */     //   416: aload #8
/*      */     //   418: getfield nextWait : I
/*      */     //   421: ldc 2147483647
/*      */     //   423: iand
/*      */     //   424: i2l
/*      */     //   425: lload #13
/*      */     //   427: ldc2_w 281474976710656
/*      */     //   430: ladd
/*      */     //   431: ldc2_w -281474976710656
/*      */     //   434: land
/*      */     //   435: lor
/*      */     //   436: lload #13
/*      */     //   438: ldc2_w 281472829227008
/*      */     //   441: land
/*      */     //   442: lor
/*      */     //   443: lstore #16
/*      */     //   445: aload #8
/*      */     //   447: getfield eventCount : I
/*      */     //   450: iload #12
/*      */     //   452: ldc -2147483648
/*      */     //   454: ior
/*      */     //   455: if_icmpne -> 513
/*      */     //   458: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   461: aload_0
/*      */     //   462: getstatic scala/concurrent/forkjoin/ForkJoinPool.CTL : J
/*      */     //   465: lload #13
/*      */     //   467: lload #16
/*      */     //   469: invokevirtual compareAndSwapLong : (Ljava/lang/Object;JJJ)Z
/*      */     //   472: ifeq -> 513
/*      */     //   475: aload #8
/*      */     //   477: iload #12
/*      */     //   479: ldc 65536
/*      */     //   481: iadd
/*      */     //   482: ldc 2147483647
/*      */     //   484: iand
/*      */     //   485: putfield eventCount : I
/*      */     //   488: aload #8
/*      */     //   490: iconst_m1
/*      */     //   491: putfield qlock : I
/*      */     //   494: aload #8
/*      */     //   496: getfield parker : Ljava/lang/Thread;
/*      */     //   499: dup
/*      */     //   500: astore #15
/*      */     //   502: ifnull -> 513
/*      */     //   505: getstatic scala/concurrent/forkjoin/ForkJoinPool.U : Lsun/misc/Unsafe;
/*      */     //   508: aload #15
/*      */     //   510: invokevirtual unpark : (Ljava/lang/Object;)V
/*      */     //   513: goto -> 370
/*      */     //   516: iinc #6, 1
/*      */     //   519: goto -> 263
/*      */     //   522: goto -> 86
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #2593	-> 0
/*      */     //   #2594	-> 7
/*      */     //   #2595	-> 9
/*      */     //   #2596	-> 18
/*      */     //   #2597	-> 22
/*      */     //   #2598	-> 24
/*      */     //   #2600	-> 48
/*      */     //   #2601	-> 53
/*      */     //   #2602	-> 64
/*      */     //   #2603	-> 80
/*      */     //   #2606	-> 86
/*      */     //   #2607	-> 102
/*      */     //   #2608	-> 120
/*      */     //   #2609	-> 125
/*      */     //   #2610	-> 129
/*      */     //   #2612	-> 143
/*      */     //   #2614	-> 145
/*      */     //   #2616	-> 149
/*      */     //   #2617	-> 166
/*      */     //   #2618	-> 168
/*      */     //   #2619	-> 178
/*      */     //   #2620	-> 189
/*      */     //   #2621	-> 200
/*      */     //   #2622	-> 208
/*      */     //   #2623	-> 214
/*      */     //   #2625	-> 216
/*      */     //   #2626	-> 231
/*      */     //   #2619	-> 233
/*      */     //   #2631	-> 239
/*      */     //   #2632	-> 260
/*      */     //   #2634	-> 269
/*      */     //   #2635	-> 279
/*      */     //   #2636	-> 284
/*      */     //   #2637	-> 294
/*      */     //   #2638	-> 305
/*      */     //   #2639	-> 311
/*      */     //   #2640	-> 316
/*      */     //   #2641	-> 321
/*      */     //   #2642	-> 338
/*      */     //   #2644	-> 346
/*      */     //   #2646	-> 351
/*      */     //   #2645	-> 354
/*      */     //   #2648	-> 356
/*      */     //   #2636	-> 364
/*      */     //   #2657	-> 370
/*      */     //   #2658	-> 416
/*      */     //   #2661	-> 445
/*      */     //   #2663	-> 475
/*      */     //   #2664	-> 488
/*      */     //   #2665	-> 494
/*      */     //   #2666	-> 505
/*      */     //   #2668	-> 513
/*      */     //   #2632	-> 516
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   64	22	4	nps	I
/*      */     //   197	42	7	w	Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   181	58	8	i	I
/*      */     //   175	64	6	ws	[Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   356	0	12	ignore	Ljava/lang/Throwable;
/*      */     //   335	29	9	wt	Ljava/lang/Thread;
/*      */     //   302	68	8	w	Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   287	83	11	i	I
/*      */     //   502	11	15	p	Ljava/lang/Thread;
/*      */     //   445	68	16	nc	J
/*      */     //   413	103	8	w	Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   284	232	10	n	I
/*      */     //   395	121	11	i	I
/*      */     //   384	132	12	e	I
/*      */     //   377	139	13	cc	J
/*      */     //   276	240	7	ws	[Lscala/concurrent/forkjoin/ForkJoinPool$WorkQueue;
/*      */     //   263	259	6	pass	I
/*      */     //   93	432	4	c	J
/*      */     //   0	525	0	this	Lscala/concurrent/forkjoin/ForkJoinPool;
/*      */     //   0	525	1	now	Z
/*      */     //   0	525	2	enable	Z
/*      */     //   15	510	3	ps	I
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   125	132	135	finally
/*      */     //   135	140	135	finally
/*      */     //   346	351	354	java/lang/Throwable
/*      */   }
/*      */   
/*      */   static WorkQueue commonSubmitterQueue() {
/*      */     ForkJoinPool p;
/*      */     WorkQueue[] ws;
/*      */     int m;
/*      */     Submitter z;
/* 2683 */     return ((z = submitters.get()) != null && (p = common) != null && (ws = p.workQueues) != null && (m = ws.length - 1) >= 0) ? ws[m & z.seed & 0x7E] : null;
/*      */   }
/*      */   
/*      */   static boolean tryExternalUnpush(ForkJoinTask<?> t) {
/*      */     ForkJoinPool p;
/*      */     WorkQueue[] ws;
/*      */     WorkQueue q;
/*      */     Submitter z;
/*      */     ForkJoinTask<?>[] a;
/*      */     int m;
/*      */     int s;
/* 2696 */     if (t != null && (z = submitters.get()) != null && (p = common) != null && (ws = p.workQueues) != null && (m = ws.length - 1) >= 0 && (q = ws[m & z.seed & 0x7E]) != null && (s = q.top) != q.base && (a = q.array) != null) {
/* 2704 */       long j = (((a.length - 1 & s - 1) << ASHIFT) + ABASE);
/* 2705 */       if (U.getObject(a, j) == t && U.compareAndSwapInt(q, QLOCK, 0, 1)) {
/* 2707 */         if (q.array == a && q.top == s && U.compareAndSwapObject(a, j, t, null)) {
/* 2709 */           q.top = s - 1;
/* 2710 */           q.qlock = 0;
/* 2711 */           return true;
/*      */         } 
/* 2713 */         q.qlock = 0;
/*      */       } 
/*      */     } 
/* 2716 */     return false;
/*      */   }
/*      */   
/*      */   private void externalHelpComplete(WorkQueue q, ForkJoinTask<?> root) {
/*      */     ForkJoinTask<?>[] a;
/*      */     int m;
/* 2726 */     if (q != null && (a = q.array) != null && (m = a.length - 1) >= 0 && root != null && root.status >= 0)
/*      */       while (true) {
/* 2729 */         CountedCompleter<?> task = null;
/* 2731 */         long j = (((m & s - 1) << ASHIFT) + ABASE);
/*      */         int s;
/*      */         Object o;
/* 2732 */         if ((s = q.top) - q.base > 0 && (o = U.getObject(a, j)) != null && o instanceof CountedCompleter) {
/* 2734 */           CountedCompleter<?> t = (CountedCompleter)o, r = t;
/*      */           do {
/* 2736 */             if (r == root) {
/* 2737 */               if (U.compareAndSwapInt(q, QLOCK, 0, 1)) {
/* 2738 */                 if (q.array == a && q.top == s && U.compareAndSwapObject(a, j, t, null)) {
/* 2740 */                   q.top = s - 1;
/* 2741 */                   task = t;
/*      */                 } 
/* 2743 */                 q.qlock = 0;
/*      */               } 
/*      */               break;
/*      */             } 
/* 2747 */           } while ((r = r.completer) != null);
/*      */         } 
/* 2750 */         if (task != null)
/* 2751 */           task.doExec(); 
/*      */         int u;
/* 2752 */         if (root.status < 0 || (u = (int)(this.ctl >>> 32L)) >= 0 || u >> 16 >= 0)
/*      */           break; 
/* 2755 */         if (task == null) {
/* 2756 */           helpSignal(root, q.poolIndex);
/* 2757 */           if (root.status >= 0)
/* 2758 */             helpComplete(root, -1); 
/*      */           break;
/*      */         } 
/*      */       }  
/*      */   }
/*      */   
/*      */   static void externalHelpJoin(ForkJoinTask<?> t) {
/*      */     ForkJoinPool p;
/*      */     WorkQueue[] ws;
/*      */     WorkQueue q;
/*      */     Submitter z;
/*      */     ForkJoinTask<?>[] a;
/*      */     int m;
/* 2773 */     if (t != null && (z = submitters.get()) != null && (p = common) != null && (ws = p.workQueues) != null && (m = ws.length - 1) >= 0 && (q = ws[m & z.seed & 0x7E]) != null && (a = q.array) != null) {
/* 2780 */       int am = a.length - 1;
/*      */       int s;
/* 2781 */       if ((s = q.top) != q.base) {
/* 2782 */         long j = (((am & s - 1) << ASHIFT) + ABASE);
/* 2783 */         if (U.getObject(a, j) == t && U.compareAndSwapInt(q, QLOCK, 0, 1))
/* 2785 */           if (q.array == a && q.top == s && U.compareAndSwapObject(a, j, t, null)) {
/* 2787 */             q.top = s - 1;
/* 2788 */             q.qlock = 0;
/* 2789 */             t.doExec();
/*      */           } else {
/* 2792 */             q.qlock = 0;
/*      */           }  
/*      */       } 
/* 2795 */       if (t.status >= 0)
/* 2796 */         if (t instanceof CountedCompleter) {
/* 2797 */           p.externalHelpComplete(q, t);
/*      */         } else {
/* 2799 */           p.helpSignal(t, q.poolIndex);
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public ForkJoinPool() {
/* 2820 */     this(Math.min(32767, Runtime.getRuntime().availableProcessors()), defaultForkJoinWorkerThreadFactory, (Thread.UncaughtExceptionHandler)null, false);
/*      */   }
/*      */   
/*      */   public ForkJoinPool(int parallelism) {
/* 2839 */     this(parallelism, defaultForkJoinWorkerThreadFactory, (Thread.UncaughtExceptionHandler)null, false);
/*      */   }
/*      */   
/*      */   public ForkJoinPool(int parallelism, ForkJoinWorkerThreadFactory factory, Thread.UncaughtExceptionHandler handler, boolean asyncMode) {
/* 2870 */     checkPermission();
/* 2871 */     if (factory == null)
/* 2872 */       throw new NullPointerException(); 
/* 2873 */     if (parallelism <= 0 || parallelism > 32767)
/* 2874 */       throw new IllegalArgumentException(); 
/* 2875 */     this.factory = factory;
/* 2876 */     this.ueh = handler;
/* 2877 */     this.config = parallelism | (asyncMode ? 65536 : 0);
/* 2878 */     long np = -parallelism;
/* 2879 */     this.ctl = np << 48L & 0xFFFF000000000000L | np << 32L & 0xFFFF00000000L;
/* 2880 */     int pn = nextPoolId();
/* 2881 */     StringBuilder sb = new StringBuilder("ForkJoinPool-");
/* 2882 */     sb.append(Integer.toString(pn));
/* 2883 */     sb.append("-worker-");
/* 2884 */     this.workerNamePrefix = sb.toString();
/*      */   }
/*      */   
/*      */   ForkJoinPool(int parallelism, long ctl, ForkJoinWorkerThreadFactory factory, Thread.UncaughtExceptionHandler handler) {
/* 2894 */     this.config = parallelism;
/* 2895 */     this.ctl = ctl;
/* 2896 */     this.factory = factory;
/* 2897 */     this.ueh = handler;
/* 2898 */     this.workerNamePrefix = "ForkJoinPool.commonPool-worker-";
/*      */   }
/*      */   
/*      */   public static ForkJoinPool commonPool() {
/* 2916 */     return common;
/*      */   }
/*      */   
/*      */   public <T> T invoke(ForkJoinTask<T> task) {
/* 2938 */     if (task == null)
/* 2939 */       throw new NullPointerException(); 
/* 2940 */     externalPush(task);
/* 2941 */     return task.join();
/*      */   }
/*      */   
/*      */   public void execute(ForkJoinTask<?> task) {
/* 2953 */     if (task == null)
/* 2954 */       throw new NullPointerException(); 
/* 2955 */     externalPush(task);
/*      */   }
/*      */   
/*      */   public void execute(Runnable task) {
/*      */     ForkJoinTask<?> job;
/* 2966 */     if (task == null)
/* 2967 */       throw new NullPointerException(); 
/* 2969 */     if (task instanceof ForkJoinTask) {
/* 2970 */       job = (ForkJoinTask)task;
/*      */     } else {
/* 2972 */       job = new ForkJoinTask.AdaptedRunnableAction(task);
/*      */     } 
/* 2973 */     externalPush(job);
/*      */   }
/*      */   
/*      */   public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task) {
/* 2986 */     if (task == null)
/* 2987 */       throw new NullPointerException(); 
/* 2988 */     externalPush(task);
/* 2989 */     return task;
/*      */   }
/*      */   
/*      */   public <T> ForkJoinTask<T> submit(Callable<T> task) {
/* 2998 */     ForkJoinTask<T> job = new ForkJoinTask.AdaptedCallable<T>(task);
/* 2999 */     externalPush(job);
/* 3000 */     return job;
/*      */   }
/*      */   
/*      */   public <T> ForkJoinTask<T> submit(Runnable task, T result) {
/* 3009 */     ForkJoinTask<T> job = new ForkJoinTask.AdaptedRunnable<T>(task, result);
/* 3010 */     externalPush(job);
/* 3011 */     return job;
/*      */   }
/*      */   
/*      */   public ForkJoinTask<?> submit(Runnable task) {
/*      */     ForkJoinTask<?> job;
/* 3020 */     if (task == null)
/* 3021 */       throw new NullPointerException(); 
/* 3023 */     if (task instanceof ForkJoinTask) {
/* 3024 */       job = (ForkJoinTask)task;
/*      */     } else {
/* 3026 */       job = new ForkJoinTask.AdaptedRunnableAction(task);
/*      */     } 
/* 3027 */     externalPush(job);
/* 3028 */     return job;
/*      */   }
/*      */   
/*      */   public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) {
/* 3039 */     ArrayList<Future<T>> futures = new ArrayList<Future<T>>(tasks.size());
/* 3041 */     boolean done = false;
/*      */     try {
/* 3043 */       for (Callable<T> t : tasks) {
/* 3044 */         ForkJoinTask<T> f = new ForkJoinTask.AdaptedCallable<T>(t);
/* 3045 */         futures.add(f);
/* 3046 */         externalPush(f);
/*      */       } 
/* 3048 */       for (int i = 0, size = futures.size(); i < size; i++)
/* 3049 */         ((ForkJoinTask)futures.get(i)).quietlyJoin(); 
/* 3050 */       done = true;
/* 3051 */       return futures;
/*      */     } finally {
/* 3053 */       if (!done)
/* 3054 */         for (int i = 0, size = futures.size(); i < size; i++)
/* 3055 */           ((Future)futures.get(i)).cancel(false);  
/*      */     } 
/*      */   }
/*      */   
/*      */   public ForkJoinWorkerThreadFactory getFactory() {
/* 3065 */     return this.factory;
/*      */   }
/*      */   
/*      */   public Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
/* 3075 */     return this.ueh;
/*      */   }
/*      */   
/*      */   public int getParallelism() {
/* 3084 */     return this.config & 0xFFFF;
/*      */   }
/*      */   
/*      */   public static int getCommonPoolParallelism() {
/* 3094 */     return commonParallelism;
/*      */   }
/*      */   
/*      */   public int getPoolSize() {
/* 3106 */     return (this.config & 0xFFFF) + (short)(int)(this.ctl >>> 32L);
/*      */   }
/*      */   
/*      */   public boolean getAsyncMode() {
/* 3116 */     return (this.config >>> 16 == 1);
/*      */   }
/*      */   
/*      */   public int getRunningThreadCount() {
/* 3128 */     int rc = 0;
/*      */     WorkQueue[] ws;
/* 3130 */     if ((ws = this.workQueues) != null)
/* 3131 */       for (int i = 1; i < ws.length; i += 2) {
/*      */         WorkQueue w;
/* 3132 */         if ((w = ws[i]) != null && w.isApparentlyUnblocked())
/* 3133 */           rc++; 
/*      */       }  
/* 3136 */     return rc;
/*      */   }
/*      */   
/*      */   public int getActiveThreadCount() {
/* 3147 */     int r = (this.config & 0xFFFF) + (int)(this.ctl >> 48L);
/* 3148 */     return (r <= 0) ? 0 : r;
/*      */   }
/*      */   
/*      */   public boolean isQuiescent() {
/* 3163 */     return ((int)(this.ctl >> 48L) + (this.config & 0xFFFF) == 0);
/*      */   }
/*      */   
/*      */   public long getStealCount() {
/* 3178 */     long count = this.stealCount;
/*      */     WorkQueue[] ws;
/* 3180 */     if ((ws = this.workQueues) != null)
/* 3181 */       for (int i = 1; i < ws.length; i += 2) {
/*      */         WorkQueue w;
/* 3182 */         if ((w = ws[i]) != null)
/* 3183 */           count += w.nsteals; 
/*      */       }  
/* 3186 */     return count;
/*      */   }
/*      */   
/*      */   public long getQueuedTaskCount() {
/* 3200 */     long count = 0L;
/*      */     WorkQueue[] ws;
/* 3202 */     if ((ws = this.workQueues) != null)
/* 3203 */       for (int i = 1; i < ws.length; i += 2) {
/*      */         WorkQueue w;
/* 3204 */         if ((w = ws[i]) != null)
/* 3205 */           count += w.queueSize(); 
/*      */       }  
/* 3208 */     return count;
/*      */   }
/*      */   
/*      */   public int getQueuedSubmissionCount() {
/* 3219 */     int count = 0;
/*      */     WorkQueue[] ws;
/* 3221 */     if ((ws = this.workQueues) != null)
/* 3222 */       for (int i = 0; i < ws.length; i += 2) {
/*      */         WorkQueue w;
/* 3223 */         if ((w = ws[i]) != null)
/* 3224 */           count += w.queueSize(); 
/*      */       }  
/* 3227 */     return count;
/*      */   }
/*      */   
/*      */   public boolean hasQueuedSubmissions() {
/*      */     WorkQueue[] ws;
/* 3238 */     if ((ws = this.workQueues) != null)
/* 3239 */       for (int i = 0; i < ws.length; i += 2) {
/*      */         WorkQueue w;
/* 3240 */         if ((w = ws[i]) != null && !w.isEmpty())
/* 3241 */           return true; 
/*      */       }  
/* 3244 */     return false;
/*      */   }
/*      */   
/*      */   protected ForkJoinTask<?> pollSubmission() {
/*      */     WorkQueue[] ws;
/* 3256 */     if ((ws = this.workQueues) != null)
/* 3257 */       for (int i = 0; i < ws.length; i += 2) {
/*      */         WorkQueue w;
/*      */         ForkJoinTask<?> t;
/* 3258 */         if ((w = ws[i]) != null && (t = w.poll()) != null)
/* 3259 */           return t; 
/*      */       }  
/* 3262 */     return null;
/*      */   }
/*      */   
/*      */   protected int drainTasksTo(Collection<? super ForkJoinTask<?>> c) {
/* 3283 */     int count = 0;
/*      */     WorkQueue[] ws;
/* 3285 */     if ((ws = this.workQueues) != null)
/* 3286 */       for (int i = 0; i < ws.length; i++) {
/*      */         WorkQueue w;
/* 3287 */         if ((w = ws[i]) != null) {
/*      */           ForkJoinTask<?> t;
/* 3288 */           while ((t = w.poll()) != null) {
/* 3289 */             c.add(t);
/* 3290 */             count++;
/*      */           } 
/*      */         } 
/*      */       }  
/* 3295 */     return count;
/*      */   }
/*      */   
/*      */   public String toString() {
/*      */     String level;
/* 3307 */     long qt = 0L, qs = 0L;
/* 3307 */     int rc = 0;
/* 3308 */     long st = this.stealCount;
/* 3309 */     long c = this.ctl;
/*      */     WorkQueue[] ws;
/* 3311 */     if ((ws = this.workQueues) != null)
/* 3312 */       for (int i = 0; i < ws.length; i++) {
/*      */         WorkQueue w;
/* 3313 */         if ((w = ws[i]) != null) {
/* 3314 */           int size = w.queueSize();
/* 3315 */           if ((i & 0x1) == 0) {
/* 3316 */             qs += size;
/*      */           } else {
/* 3318 */             qt += size;
/* 3319 */             st += w.nsteals;
/* 3320 */             if (w.isApparentlyUnblocked())
/* 3321 */               rc++; 
/*      */           } 
/*      */         } 
/*      */       }  
/* 3326 */     int pc = this.config & 0xFFFF;
/* 3327 */     int tc = pc + (short)(int)(c >>> 32L);
/* 3328 */     int ac = pc + (int)(c >> 48L);
/* 3329 */     if (ac < 0)
/* 3330 */       ac = 0; 
/* 3332 */     if ((c & 0x80000000L) != 0L) {
/* 3333 */       level = (tc == 0) ? "Terminated" : "Terminating";
/*      */     } else {
/* 3335 */       level = (this.plock < 0) ? "Shutting down" : "Running";
/*      */     } 
/* 3336 */     return super.toString() + "[" + level + ", parallelism = " + pc + ", size = " + tc + ", active = " + ac + ", running = " + rc + ", steals = " + st + ", tasks = " + qt + ", submissions = " + qs + "]";
/*      */   }
/*      */   
/*      */   public void shutdown() {
/* 3363 */     checkPermission();
/* 3364 */     tryTerminate(false, true);
/*      */   }
/*      */   
/*      */   public List<Runnable> shutdownNow() {
/* 3386 */     checkPermission();
/* 3387 */     tryTerminate(true, true);
/* 3388 */     return Collections.emptyList();
/*      */   }
/*      */   
/*      */   public boolean isTerminated() {
/* 3397 */     long c = this.ctl;
/* 3398 */     return ((c & 0x80000000L) != 0L && (short)(int)(c >>> 32L) == -(this.config & 0xFFFF));
/*      */   }
/*      */   
/*      */   public boolean isTerminating() {
/* 3416 */     long c = this.ctl;
/* 3417 */     return ((c & 0x80000000L) != 0L && (short)(int)(c >>> 32L) != -(this.config & 0xFFFF));
/*      */   }
/*      */   
/*      */   public boolean isShutdown() {
/* 3427 */     return (this.plock < 0);
/*      */   }
/*      */   
/*      */   public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
/* 3446 */     if (Thread.interrupted())
/* 3447 */       throw new InterruptedException(); 
/* 3448 */     if (this == common) {
/* 3449 */       awaitQuiescence(timeout, unit);
/* 3450 */       return false;
/*      */     } 
/* 3452 */     long nanos = unit.toNanos(timeout);
/* 3453 */     if (isTerminated())
/* 3454 */       return true; 
/* 3455 */     long startTime = System.nanoTime();
/* 3456 */     boolean terminated = false;
/* 3457 */     synchronized (this) {
/* 3458 */       long waitTime = nanos, millis = 0L;
/*      */       while (true) {
/* 3459 */         if (terminated = (isTerminated() || waitTime <= 0L || (millis = unit.toMillis(waitTime)) <= 0L))
/*      */           break; 
/* 3463 */         wait(millis);
/* 3464 */         waitTime = nanos - System.nanoTime() - startTime;
/*      */       } 
/*      */     } 
/* 3467 */     return terminated;
/*      */   }
/*      */   
/*      */   public boolean awaitQuiescence(long timeout, TimeUnit unit) {
/* 3482 */     long nanos = unit.toNanos(timeout);
/* 3484 */     Thread thread = Thread.currentThread();
/*      */     ForkJoinWorkerThread wt;
/* 3485 */     if (thread instanceof ForkJoinWorkerThread && (wt = (ForkJoinWorkerThread)thread).pool == this) {
/* 3487 */       helpQuiescePool(wt.workQueue);
/* 3488 */       return true;
/*      */     } 
/* 3490 */     long startTime = System.nanoTime();
/* 3492 */     int r = 0;
/* 3493 */     boolean found = true;
/*      */     WorkQueue[] ws;
/*      */     int m;
/* 3495 */     while (!isQuiescent() && (ws = this.workQueues) != null && (m = ws.length - 1) >= 0) {
/* 3496 */       if (!found) {
/* 3497 */         if (System.nanoTime() - startTime > nanos)
/* 3498 */           return false; 
/* 3499 */         Thread.yield();
/*      */       } 
/* 3501 */       found = false;
/* 3502 */       for (int j = m + 1 << 2; j >= 0; j--) {
/*      */         WorkQueue q;
/*      */         int b;
/* 3504 */         if ((q = ws[r++ & m]) != null && (b = q.base) - q.top < 0) {
/* 3505 */           found = true;
/*      */           ForkJoinTask<?> t;
/* 3506 */           if ((t = q.pollAt(b)) != null) {
/* 3507 */             if (q.base - q.top < 0)
/* 3508 */               signalWork(q); 
/* 3509 */             t.doExec();
/*      */           } 
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 3515 */     return true;
/*      */   }
/*      */   
/*      */   static void quiesceCommonPool() {
/* 3523 */     common.awaitQuiescence(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
/*      */   }
/*      */   
/*      */   public static void managedBlock(ManagedBlocker blocker) throws InterruptedException {
/* 3621 */     Thread t = Thread.currentThread();
/* 3622 */     if (t instanceof ForkJoinWorkerThread) {
/* 3623 */       ForkJoinPool p = ((ForkJoinWorkerThread)t).pool;
/* 3624 */       while (!blocker.isReleasable()) {
/*      */         WorkQueue[] ws;
/*      */         int m;
/* 3626 */         if ((ws = p.workQueues) != null && (m = ws.length - 1) >= 0)
/* 3627 */           for (int i = 0; i <= m; i++) {
/* 3628 */             if (blocker.isReleasable())
/*      */               return; 
/* 3631 */             p.signalWork(q);
/*      */             WorkQueue q;
/*      */             int u;
/* 3632 */             if ((q = ws[i]) != null && q.base - q.top < 0 && ((u = (int)(p.ctl >>> 32L)) >= 0 || u >> 16 >= 0))
/*      */               break; 
/*      */           }  
/* 3638 */         if (p.tryCompensate()) {
/*      */           try {
/*      */             do {
/*      */             
/* 3640 */             } while (!blocker.isReleasable() && !blocker.block());
/*      */           } finally {
/* 3643 */             p.incrementActiveCount();
/*      */           } 
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       do {
/*      */       
/* 3650 */       } while (!blocker.isReleasable() && !blocker.block());
/*      */     } 
/*      */   }
/*      */   
/*      */   protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
/* 3660 */     return new ForkJoinTask.AdaptedRunnable<T>(runnable, value);
/*      */   }
/*      */   
/*      */   protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
/* 3664 */     return new ForkJoinTask.AdaptedCallable<T>(callable);
/*      */   }
/*      */   
/*      */   static {
/*      */     try {
/* 3681 */       U = getUnsafe();
/* 3682 */       Class<?> k = ForkJoinPool.class;
/* 3683 */       CTL = U.objectFieldOffset(k.getDeclaredField("ctl"));
/* 3685 */       STEALCOUNT = U.objectFieldOffset(k.getDeclaredField("stealCount"));
/* 3687 */       PLOCK = U.objectFieldOffset(k.getDeclaredField("plock"));
/* 3689 */       INDEXSEED = U.objectFieldOffset(k.getDeclaredField("indexSeed"));
/* 3691 */       Class<?> tk = Thread.class;
/* 3692 */       PARKBLOCKER = U.objectFieldOffset(tk.getDeclaredField("parkBlocker"));
/* 3694 */       Class<?> wk = WorkQueue.class;
/* 3695 */       QLOCK = U.objectFieldOffset(wk.getDeclaredField("qlock"));
/* 3697 */       Class<?> ak = ForkJoinTask[].class;
/* 3698 */       ABASE = U.arrayBaseOffset(ak);
/* 3699 */       int scale = U.arrayIndexScale(ak);
/* 3700 */       if ((scale & scale - 1) != 0)
/* 3701 */         throw new Error("data type scale not a power of two"); 
/* 3702 */       ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
/* 3703 */     } catch (Exception e) {
/* 3704 */       throw new Error(e);
/*      */     } 
/*      */   }
/*      */   
/* 3707 */   static final ThreadLocal<Submitter> submitters = new ThreadLocal<Submitter>();
/*      */   
/*      */   static {
/* 3708 */     ForkJoinWorkerThreadFactory fac = defaultForkJoinWorkerThreadFactory = new DefaultForkJoinWorkerThreadFactory();
/*      */   }
/*      */   
/* 3710 */   private static final RuntimePermission modifyThreadPermission = new RuntimePermission("modifyThread");
/*      */   
/*      */   static final ForkJoinPool common;
/*      */   
/*      */   static final int commonParallelism;
/*      */   
/*      */   private static int poolNumberSequence;
/*      */   
/*      */   private static final long IDLE_TIMEOUT = 2000000000L;
/*      */   
/*      */   private static final long FAST_IDLE_TIMEOUT = 200000000L;
/*      */   
/*      */   private static final long TIMEOUT_SLOP = 2000000L;
/*      */   
/*      */   private static final int MAX_HELP = 64;
/*      */   
/*      */   private static final int SEED_INCREMENT = 1640531527;
/*      */   
/*      */   private static final int AC_SHIFT = 48;
/*      */   
/*      */   private static final int TC_SHIFT = 32;
/*      */   
/*      */   private static final int ST_SHIFT = 31;
/*      */   
/*      */   private static final int EC_SHIFT = 16;
/*      */   
/*      */   private static final int SMASK = 65535;
/*      */   
/*      */   private static final int MAX_CAP = 32767;
/*      */   
/*      */   private static final int EVENMASK = 65534;
/*      */   
/*      */   private static final int SQMASK = 126;
/*      */   
/*      */   private static final int SHORT_SIGN = 32768;
/*      */   
/*      */   private static final int INT_SIGN = -2147483648;
/*      */   
/*      */   private static final long STOP_BIT = 2147483648L;
/*      */   
/*      */   private static final long AC_MASK = -281474976710656L;
/*      */   
/*      */   private static final long TC_MASK = 281470681743360L;
/*      */   
/*      */   private static final long TC_UNIT = 4294967296L;
/*      */   
/*      */   private static final long AC_UNIT = 281474976710656L;
/*      */   
/*      */   private static final int UAC_SHIFT = 16;
/*      */   
/*      */   private static final int UTC_SHIFT = 0;
/*      */   
/*      */   private static final int UAC_MASK = -65536;
/*      */   
/*      */   private static final int UTC_MASK = 65535;
/*      */   
/*      */   private static final int UAC_UNIT = 65536;
/*      */   
/*      */   private static final int UTC_UNIT = 1;
/*      */   
/*      */   private static final int E_MASK = 2147483647;
/*      */   
/*      */   private static final int E_SEQ = 65536;
/*      */   
/*      */   private static final int SHUTDOWN = -2147483648;
/*      */   
/*      */   private static final int PL_LOCK = 2;
/*      */   
/*      */   private static final int PL_SIGNAL = 1;
/*      */   
/*      */   private static final int PL_SPINS = 256;
/*      */   
/*      */   static final int LIFO_QUEUE = 0;
/*      */   
/*      */   static final int FIFO_QUEUE = 1;
/*      */   
/*      */   static final int SHARED_QUEUE = -1;
/*      */   
/*      */   private static final int MIN_SCAN = 511;
/*      */   
/*      */   private static final int MAX_SCAN = 131071;
/*      */   
/*      */   volatile long pad00;
/*      */   
/*      */   volatile long pad01;
/*      */   
/*      */   volatile long pad02;
/*      */   
/*      */   volatile long pad03;
/*      */   
/*      */   volatile long pad04;
/*      */   
/*      */   volatile long pad05;
/*      */   
/*      */   volatile long pad06;
/*      */   
/*      */   volatile long stealCount;
/*      */   
/*      */   volatile long ctl;
/*      */   
/*      */   volatile int plock;
/*      */   
/*      */   volatile int indexSeed;
/*      */   
/*      */   final int config;
/*      */   
/*      */   WorkQueue[] workQueues;
/*      */   
/*      */   final ForkJoinWorkerThreadFactory factory;
/*      */   
/*      */   final Thread.UncaughtExceptionHandler ueh;
/*      */   
/*      */   final String workerNamePrefix;
/*      */   
/*      */   volatile Object pad10;
/*      */   
/*      */   volatile Object pad11;
/*      */   
/*      */   volatile Object pad12;
/*      */   
/*      */   volatile Object pad13;
/*      */   
/*      */   volatile Object pad14;
/*      */   
/*      */   volatile Object pad15;
/*      */   
/*      */   volatile Object pad16;
/*      */   
/*      */   volatile Object pad17;
/*      */   
/*      */   volatile Object pad18;
/*      */   
/*      */   volatile Object pad19;
/*      */   
/*      */   volatile Object pad1a;
/*      */   
/*      */   volatile Object pad1b;
/*      */   
/*      */   private static final Unsafe U;
/*      */   
/*      */   private static final long CTL;
/*      */   
/*      */   private static final long PARKBLOCKER;
/*      */   
/*      */   private static final int ABASE;
/*      */   
/*      */   private static final int ASHIFT;
/*      */   
/*      */   private static final long STEALCOUNT;
/*      */   
/*      */   private static final long PLOCK;
/*      */   
/*      */   private static final long INDEXSEED;
/*      */   
/*      */   private static final long QLOCK;
/*      */   
/*      */   static {
/* 3718 */     int par = 0;
/* 3719 */     Thread.UncaughtExceptionHandler handler = null;
/*      */     try {
/* 3721 */       String pp = System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism");
/* 3723 */       String hp = System.getProperty("java.util.concurrent.ForkJoinPool.common.exceptionHandler");
/* 3725 */       String fp = System.getProperty("java.util.concurrent.ForkJoinPool.common.threadFactory");
/* 3727 */       if (fp != null)
/* 3728 */         fac = (ForkJoinWorkerThreadFactory)ClassLoader.getSystemClassLoader().loadClass(fp).newInstance(); 
/* 3730 */       if (hp != null)
/* 3731 */         handler = (Thread.UncaughtExceptionHandler)ClassLoader.getSystemClassLoader().loadClass(hp).newInstance(); 
/* 3733 */       if (pp != null)
/* 3734 */         par = Integer.parseInt(pp); 
/* 3735 */     } catch (Exception ignore) {}
/* 3738 */     if (par <= 0)
/* 3739 */       par = Runtime.getRuntime().availableProcessors(); 
/* 3740 */     if (par > 32767)
/* 3741 */       par = 32767; 
/* 3742 */     commonParallelism = par;
/* 3743 */     long np = -par;
/* 3744 */     long ct = np << 48L & 0xFFFF000000000000L | np << 32L & 0xFFFF00000000L;
/* 3746 */     common = new ForkJoinPool(par, ct, fac, handler);
/*      */   }
/*      */   
/*      */   private static Unsafe getUnsafe() {
/* 3757 */     return Unsafe.instance;
/*      */   }
/*      */   
/*      */   public static interface ManagedBlocker {
/*      */     boolean block() throws InterruptedException;
/*      */     
/*      */     boolean isReleasable();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\forkjoin\ForkJoinPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */