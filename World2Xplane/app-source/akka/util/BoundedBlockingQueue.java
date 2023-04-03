/*     */ package akka.util;
/*     */ 
/*     */ import java.util.AbstractQueue;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\035g\001B\001\003\001\035\021ACQ8v]\022,GM\0217pG.LgnZ)vKV,'BA\002\005\003\021)H/\0337\013\003\025\tA!Y6lC\016\001QC\001\005\023'\r\001\021B\b\t\004\0259\001R\"A\006\013\005\ra!\"A\007\002\t)\fg/Y\005\003\037-\021Q\"\0212tiJ\f7\r^)vKV,\007CA\t\023\031\001!Qa\005\001C\002Q\021\021!R\t\003+m\001\"AF\r\016\003]Q\021\001G\001\006g\016\fG.Y\005\0035]\021qAT8uQ&tw\r\005\002\0279%\021Qd\006\002\007\003:L(+\0324\021\007}\021\003#D\001!\025\t\t3\"\001\006d_:\034WO\035:f]RL!a\t\021\003\033\tcwnY6j]\036\fV/Z;f\021!)\003A!b\001\n\0031\023aC7bq\016\013\007/Y2jif,\022a\n\t\003-!J!!K\f\003\007%sG\017\003\005,\001\t\005\t\025!\003(\0031i\027\r_\"ba\006\034\027\016^=!\021!i\003A!b\001\n\023q\023a\0022bG.LgnZ\013\002_A\031!\002\r\t\n\005EZ!!B)vKV,\007\002C\032\001\005\003\005\013\021B\030\002\021\t\f7m[5oO\002BQ!\016\001\005\002Y\na\001P5oSRtDcA\034:uA\031\001\b\001\t\016\003\tAQ!\n\033A\002\035BQ!\f\033A\002=Bq\001\020\001C\002\023EQ(\001\003m_\016\\W#\001 \021\005}\022U\"\001!\013\005\005\003\023!\0027pG.\034\030BA\"A\0055\021V-\0328ue\006tG\017T8dW\"1Q\t\001Q\001\ny\nQ\001\\8dW\002Bqa\022\001C\002\023%\001*\001\005o_R,U\016\035;z+\005I\005CA K\023\tY\005IA\005D_:$\027\016^5p]\"1Q\n\001Q\001\n%\013\021B\\8u\0136\004H/\037\021\t\017=\003!\031!C\005\021\0069an\034;Gk2d\007BB)\001A\003%\021*\001\005o_R4U\017\0347!\021\025\031\006\001\"\001U\003\r\001X\017\036\013\003+b\003\"A\006,\n\005];\"\001B+oSRDQ!\027*A\002A\t\021!\032\005\0067\002!\t\001X\001\005i\006\\W\rF\001\021\021\025q\006\001\"\001`\003\025ygMZ3s)\t\0017\r\005\002\027C&\021!m\006\002\b\005>|G.Z1o\021\025IV\f1\001\021\021\025q\006\001\"\001f)\021\001gm\0327\t\013e#\007\031\001\t\t\013!$\007\031A5\002\017QLW.Z8viB\021aC[\005\003W^\021A\001T8oO\")Q\016\032a\001]\006!QO\\5u!\tyr.\003\002qA\tAA+[7f+:LG\017C\003s\001\021\0051/\001\003q_2dGc\001\tuk\")\001.\035a\001S\")Q.\035a\001]\")!\017\001C\0019\")\001\020\001C!s\0061!/Z7pm\026$\"\001\031>\t\013e;\b\031A\016\t\013q\004A\021I?\002\021\r|g\016^1j]N$\"\001\031@\t\013e[\b\031A\016\t\017\005\005\001\001\"\021\002\004\005)1\r\\3beR\tQ\013C\004\002\b\001!\t!!\003\002#I,W.Y5oS:<7)\0319bG&$\030\020F\001(\021\035\ti\001\001C\001\003\023\tAa]5{K\"1\021\021\003\001\005\002q\013A\001]3fW\"9\021Q\003\001\005\002\005]\021a\0023sC&tGk\034\013\004O\005e\001\002CA\016\003'\001\r!!\b\002\003\r\004D!a\b\002(A)!\"!\t\002&%\031\0211E\006\003\025\r{G\016\\3di&|g\016E\002\022\003O!A\"!\013\002\032\005\005\t\021!B\001\003W\0211a\030\0232#\r\001\022Q\006\t\004-\005=\022bAA\031/\t\031\021I\\=\t\017\005U\001\001\"\001\0026Q)q%a\016\002D!A\0211DA\032\001\004\tI\004\r\003\002<\005}\002#\002\006\002\"\005u\002cA\t\002@\021a\021\021IA\034\003\003\005\tQ!\001\002,\t\031q\f\n\032\t\017\005\025\0231\007a\001O\005YQ.\031=FY\026lWM\034;t\021\035\tI\005\001C!\003\027\n1bY8oi\006Lgn]!mYR\031\001-!\024\t\021\005m\021q\ta\001\003\037\002D!!\025\002VA)!\"!\t\002TA\031\021#!\026\005\031\005]\023QJA\001\002\003\025\t!!\027\003\007}#3'E\002\026\003[Aq!!\030\001\t\003\ny&A\005sK6|g/Z!mYR\031\001-!\031\t\021\005m\0211\fa\001\003G\002D!!\032\002jA)!\"!\t\002hA\031\021#!\033\005\031\005-\024\021MA\001\002\003\025\t!!\027\003\007}#C\007C\004\002p\001!\t%!\035\002\023I,G/Y5o\0032dGc\0011\002t!A\0211DA7\001\004\t)\b\r\003\002x\005m\004#\002\006\002\"\005e\004cA\t\002|\021a\021QPA:\003\003\005\tQ!\001\002Z\t\031q\fJ\033\t\017\005\005\005\001\"\001\002\004\006A\021\016^3sCR|'\017\006\002\002\006B!!\"a\"\021\023\r\tIi\003\002\t\023R,'/\031;pe\"9\021Q\022\001\005B\005=\025a\002;p\003J\024\030-\037\013\003\003#\003BAFAJ7%\031\021QS\f\003\013\005\023(/Y=\t\017\005e\005\001\"\021\002\034\0069\021n]#naRLH#\0011\t\017\0055\005\001\"\021\002 V!\021\021UAW)\021\t\031+!0\021\013Y\t\031*!*\023\r\005\035\0261VAY\r\031\tI\013\001\001\002&\naAH]3gS:,W.\0328u}A\031\021#!,\005\021\005=\026Q\024b\001\0033\022\021\001\027\t\005\003g\013I,\004\002\0026*\031\021q\027\007\002\t1\fgnZ\005\005\003w\013)L\001\004PE*,7\r\036\005\t\003\013i\n1\001\002B\006\t\021\rE\003\027\003'\013\031ME\003\002F\006-6D\002\004\002*\002\001\0211\031")
/*     */ public class BoundedBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E> {
/*     */   private final int maxCapacity;
/*     */   
/*     */   private final Queue<E> akka$util$BoundedBlockingQueue$$backing;
/*     */   
/*     */   private final ReentrantLock lock;
/*     */   
/*     */   private final Condition notEmpty;
/*     */   
/*     */   private final Condition akka$util$BoundedBlockingQueue$$notFull;
/*     */   
/*     */   public int maxCapacity() {
/*  19 */     return this.maxCapacity;
/*     */   }
/*     */   
/*     */   public Queue<E> akka$util$BoundedBlockingQueue$$backing() {
/*  19 */     return this.akka$util$BoundedBlockingQueue$$backing;
/*     */   }
/*     */   
/*     */   public BoundedBlockingQueue(int maxCapacity, Queue<E> backing) {
/*  21 */     Queue<E> queue = backing;
/*  22 */     if (queue == null)
/*  22 */       throw new IllegalArgumentException("Backing Queue may not be null"); 
/*  23 */     if (queue instanceof BlockingQueue) {
/*  23 */       BlockingQueue blockingQueue = (BlockingQueue)queue;
/*  24 */       Predef$.MODULE$.require((maxCapacity > 0));
/*  25 */       Predef$.MODULE$.require((blockingQueue.size() == 0));
/*  26 */       Predef$.MODULE$.require((blockingQueue.remainingCapacity() >= maxCapacity));
/*  26 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*  27 */     } else if (queue != null) {
/*  27 */       Queue<E> queue1 = queue;
/*  28 */       Predef$.MODULE$.require((queue1.size() == 0));
/*  29 */       Predef$.MODULE$.require((maxCapacity > 0));
/*  29 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/*     */       throw new MatchError(queue);
/*     */     } 
/*  32 */     this.lock = new ReentrantLock(false);
/*  34 */     this.notEmpty = lock().newCondition();
/*  35 */     this.akka$util$BoundedBlockingQueue$$notFull = lock().newCondition();
/*     */   }
/*     */   
/*     */   public ReentrantLock lock() {
/*     */     return this.lock;
/*     */   }
/*     */   
/*     */   private Condition notEmpty() {
/*     */     return this.notEmpty;
/*     */   }
/*     */   
/*     */   public Condition akka$util$BoundedBlockingQueue$$notFull() {
/*  35 */     return this.akka$util$BoundedBlockingQueue$$notFull;
/*     */   }
/*     */   
/*     */   public void put(Object e) {
/*  38 */     if (e == null)
/*  38 */       throw new NullPointerException(); 
/*  39 */     lock().lockInterruptibly();
/*     */     try {
/*  50 */       putElement$1(e);
/*     */       return;
/*     */     } finally {
/*  51 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void putElement$1(Object e$1) {
/*     */     while (true) {
/*     */       if (akka$util$BoundedBlockingQueue$$backing().size() < maxCapacity()) {
/*     */         Predef$.MODULE$.require(akka$util$BoundedBlockingQueue$$backing().offer((E)e$1));
/*     */         notEmpty().signal();
/*     */         return;
/*     */       } 
/*     */       akka$util$BoundedBlockingQueue$$notFull().await();
/*     */     } 
/*     */   }
/*     */   
/*     */   public E take() {
/*  55 */     lock().lockInterruptibly();
/*     */     try {
/*  68 */       return (E)takeElement$1();
/*     */     } finally {
/*  69 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private final Object takeElement$1() {
/*     */     while (akka$util$BoundedBlockingQueue$$backing().isEmpty())
/*     */       notEmpty().await(); 
/*     */     Object e = akka$util$BoundedBlockingQueue$$backing().poll();
/*     */     Predef$.MODULE$.require((e != null));
/*     */     akka$util$BoundedBlockingQueue$$notFull().signal();
/*     */     return e;
/*     */   }
/*     */   
/*     */   public boolean offer(Object e) {
/*  73 */     if (e == null)
/*  73 */       throw new NullPointerException(); 
/*  74 */     lock().lock();
/*     */     try {
/*  78 */       Predef$.MODULE$.require(akka$util$BoundedBlockingQueue$$backing().offer((E)e));
/*  79 */       notEmpty().signal();
/*     */       return !(akka$util$BoundedBlockingQueue$$backing().size() == maxCapacity());
/*     */     } finally {
/*  82 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean offer(Object e, long timeout, TimeUnit unit) {
/*  86 */     if (e == null)
/*  86 */       throw new NullPointerException(); 
/*  87 */     lock().lockInterruptibly();
/*     */     try {
/*  97 */       return offerElement$1(unit.toNanos(timeout), e);
/*     */     } finally {
/*  98 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private final boolean offerElement$1(long remainingNanos, Object e$2) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual akka$util$BoundedBlockingQueue$$backing : ()Ljava/util/Queue;
/*     */     //   4: invokeinterface size : ()I
/*     */     //   9: aload_0
/*     */     //   10: invokevirtual maxCapacity : ()I
/*     */     //   13: if_icmpge -> 45
/*     */     //   16: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   19: aload_0
/*     */     //   20: invokevirtual akka$util$BoundedBlockingQueue$$backing : ()Ljava/util/Queue;
/*     */     //   23: aload_3
/*     */     //   24: invokeinterface offer : (Ljava/lang/Object;)Z
/*     */     //   29: invokevirtual require : (Z)V
/*     */     //   32: aload_0
/*     */     //   33: invokespecial notEmpty : ()Ljava/util/concurrent/locks/Condition;
/*     */     //   36: invokeinterface signal : ()V
/*     */     //   41: iconst_1
/*     */     //   42: goto -> 52
/*     */     //   45: lload_1
/*     */     //   46: lconst_0
/*     */     //   47: lcmp
/*     */     //   48: ifgt -> 53
/*     */     //   51: iconst_0
/*     */     //   52: ireturn
/*     */     //   53: aload_0
/*     */     //   54: invokevirtual akka$util$BoundedBlockingQueue$$notFull : ()Ljava/util/concurrent/locks/Condition;
/*     */     //   57: lload_1
/*     */     //   58: invokeinterface awaitNanos : (J)J
/*     */     //   63: lstore_1
/*     */     //   64: goto -> 0
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #90	-> 0
/*     */     //   #91	-> 16
/*     */     //   #92	-> 32
/*     */     //   #93	-> 41
/*     */     //   #94	-> 45
/*     */     //   #89	-> 52
/*     */     //   #95	-> 53
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	67	0	this	Lakka/util/BoundedBlockingQueue;
/*     */     //   0	67	1	remainingNanos	J
/*     */     //   0	67	3	e$2	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public E poll(long timeout, TimeUnit unit) {
/* 102 */     lock().lockInterruptibly();
/*     */     try {
/* 114 */       return (E)pollElement$1(unit.toNanos(timeout));
/*     */     } finally {
/* 115 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private final Object pollElement$1(long remainingNanos) {
/*     */     E e;
/*     */     while (true) {
/*     */       boolean bool = false;
/*     */       null;
/*     */       Object object = null;
/*     */       E e1 = akka$util$BoundedBlockingQueue$$backing().poll();
/*     */       if (e1 == null) {
/*     */         bool = true;
/*     */         E e2 = e1;
/*     */         if (remainingNanos <= 0L) {
/*     */           null;
/*     */           Object object1 = null;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       if (bool) {
/*     */         remainingNanos = notEmpty().awaitNanos(remainingNanos);
/*     */         continue;
/*     */       } 
/*     */       akka$util$BoundedBlockingQueue$$notFull().signal();
/*     */       e = e1;
/*     */       break;
/*     */     } 
/*     */     return e;
/*     */   }
/*     */   
/*     */   public E poll() {
/* 119 */     lock().lock();
/*     */     try {
/* 121 */       E e2, e1 = akka$util$BoundedBlockingQueue$$backing().poll();
/* 122 */       if (e1 == null) {
/* 122 */         null;
/* 122 */         Object object = null;
/*     */       } else {
/* 124 */         akka$util$BoundedBlockingQueue$$notFull().signal();
/* 125 */         e2 = e1;
/*     */       } 
/*     */       return e2;
/*     */     } finally {
/* 127 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean remove(Object e) {
/* 131 */     if (e == null)
/* 131 */       throw new NullPointerException(); 
/* 132 */     lock().lock();
/*     */     try {
/* 135 */       akka$util$BoundedBlockingQueue$$notFull().signal();
/*     */       return akka$util$BoundedBlockingQueue$$backing().remove(e);
/*     */     } finally {
/* 138 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(Object e) {
/* 142 */     if (e == null)
/* 142 */       throw new NullPointerException(); 
/* 143 */     lock().lock();
/*     */     try {
/* 144 */       return akka$util$BoundedBlockingQueue$$backing().contains(e);
/*     */     } finally {
/* 144 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 148 */     lock().lock();
/*     */     try {
/* 150 */       akka$util$BoundedBlockingQueue$$backing().clear();
/* 151 */       akka$util$BoundedBlockingQueue$$notFull().signalAll();
/*     */       return;
/*     */     } finally {
/* 152 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int remainingCapacity() {
/* 156 */     lock().lock();
/*     */     try {
/* 158 */       return maxCapacity() - akka$util$BoundedBlockingQueue$$backing().size();
/*     */     } finally {
/* 159 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 163 */     lock().lock();
/*     */     try {
/* 164 */       return akka$util$BoundedBlockingQueue$$backing().size();
/*     */     } finally {
/* 164 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public E peek() {
/* 168 */     lock().lock();
/*     */     try {
/* 169 */       return akka$util$BoundedBlockingQueue$$backing().peek();
/*     */     } finally {
/* 169 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int drainTo(Collection<? super E> c) {
/* 172 */     return drainTo(c, 2147483647);
/*     */   }
/*     */   
/*     */   public int drainTo(Collection c, int maxElements) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ifnonnull -> 12
/*     */     //   4: new java/lang/NullPointerException
/*     */     //   7: dup
/*     */     //   8: invokespecial <init> : ()V
/*     */     //   11: athrow
/*     */     //   12: aload_1
/*     */     //   13: aload_0
/*     */     //   14: if_acmpne -> 25
/*     */     //   17: new java/lang/IllegalArgumentException
/*     */     //   20: dup
/*     */     //   21: invokespecial <init> : ()V
/*     */     //   24: athrow
/*     */     //   25: aload_1
/*     */     //   26: aload_0
/*     */     //   27: invokevirtual akka$util$BoundedBlockingQueue$$backing : ()Ljava/util/Queue;
/*     */     //   30: if_acmpne -> 41
/*     */     //   33: new java/lang/IllegalArgumentException
/*     */     //   36: dup
/*     */     //   37: invokespecial <init> : ()V
/*     */     //   40: athrow
/*     */     //   41: iload_2
/*     */     //   42: iconst_0
/*     */     //   43: if_icmpgt -> 50
/*     */     //   46: iconst_0
/*     */     //   47: goto -> 93
/*     */     //   50: aload_0
/*     */     //   51: invokevirtual lock : ()Ljava/util/concurrent/locks/ReentrantLock;
/*     */     //   54: invokevirtual lock : ()V
/*     */     //   57: aload_0
/*     */     //   58: aload_0
/*     */     //   59: invokespecial drainOne$default$1$1 : ()I
/*     */     //   62: aload_1
/*     */     //   63: iload_2
/*     */     //   64: invokespecial drainOne$1 : (ILjava/util/Collection;I)I
/*     */     //   67: istore #4
/*     */     //   69: iload #4
/*     */     //   71: iconst_0
/*     */     //   72: if_icmple -> 84
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual akka$util$BoundedBlockingQueue$$notFull : ()Ljava/util/concurrent/locks/Condition;
/*     */     //   79: invokeinterface signalAll : ()V
/*     */     //   84: iload #4
/*     */     //   86: aload_0
/*     */     //   87: invokevirtual lock : ()Ljava/util/concurrent/locks/ReentrantLock;
/*     */     //   90: invokevirtual unlock : ()V
/*     */     //   93: ireturn
/*     */     //   94: astore_3
/*     */     //   95: aload_0
/*     */     //   96: invokevirtual lock : ()Ljava/util/concurrent/locks/ReentrantLock;
/*     */     //   99: invokevirtual unlock : ()V
/*     */     //   102: aload_3
/*     */     //   103: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #175	-> 0
/*     */     //   #176	-> 12
/*     */     //   #177	-> 25
/*     */     //   #178	-> 41
/*     */     //   #180	-> 50
/*     */     //   #190	-> 57
/*     */     //   #191	-> 69
/*     */     //   #192	-> 84
/*     */     //   #193	-> 86
/*     */     //   #174	-> 93
/*     */     //   #193	-> 94
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	104	0	this	Lakka/util/BoundedBlockingQueue;
/*     */     //   0	104	1	c	Ljava/util/Collection;
/*     */     //   0	104	2	maxElements	I
/*     */     //   69	17	4	n	I
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   57	86	94	finally
/*     */   }
/*     */   
/*     */   private final int drainOne$default$1$1() {
/* 182 */     return 0;
/*     */   }
/*     */   
/*     */   private final int drainOne$1(int n, Collection<E> c$1, int maxElements$1) {
/*     */     // Byte code:
/*     */     //   0: iload_1
/*     */     //   1: iload_3
/*     */     //   2: if_icmpge -> 45
/*     */     //   5: aload_0
/*     */     //   6: invokevirtual akka$util$BoundedBlockingQueue$$backing : ()Ljava/util/Queue;
/*     */     //   9: invokeinterface poll : ()Ljava/lang/Object;
/*     */     //   14: astore #5
/*     */     //   16: aload #5
/*     */     //   18: ifnonnull -> 29
/*     */     //   21: iload_1
/*     */     //   22: istore #6
/*     */     //   24: iload #6
/*     */     //   26: goto -> 46
/*     */     //   29: aload_2
/*     */     //   30: aload #5
/*     */     //   32: invokeinterface add : (Ljava/lang/Object;)Z
/*     */     //   37: pop
/*     */     //   38: iload_1
/*     */     //   39: iconst_1
/*     */     //   40: iadd
/*     */     //   41: istore_1
/*     */     //   42: goto -> 0
/*     */     //   45: iload_1
/*     */     //   46: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #183	-> 0
/*     */     //   #184	-> 5
/*     */     //   #185	-> 16
/*     */     //   #184	-> 24
/*     */     //   #186	-> 29
/*     */     //   #188	-> 45
/*     */     //   #182	-> 46
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	47	0	this	Lakka/util/BoundedBlockingQueue;
/*     */     //   0	47	1	n	I
/*     */     //   0	47	2	c$1	Ljava/util/Collection;
/*     */     //   0	47	3	maxElements$1	I
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 198 */     lock().lock();
/*     */     try {
/* 199 */       return akka$util$BoundedBlockingQueue$$backing().containsAll(c);
/*     */     } finally {
/* 199 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 203 */     lock().lock();
/*     */     try {
/* 206 */       int sz = akka$util$BoundedBlockingQueue$$backing().size();
/* 207 */       if (sz < maxCapacity())
/* 207 */         akka$util$BoundedBlockingQueue$$notFull().signal(); 
/* 208 */       if (sz > 0)
/* 208 */         notEmpty().signal(); 
/*     */       return akka$util$BoundedBlockingQueue$$backing().removeAll(c);
/*     */     } finally {
/* 211 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 215 */     lock().lock();
/*     */     try {
/* 218 */       int sz = akka$util$BoundedBlockingQueue$$backing().size();
/* 219 */       if (sz < maxCapacity())
/* 219 */         akka$util$BoundedBlockingQueue$$notFull().signal(); 
/* 220 */       if (sz > 0)
/* 220 */         notEmpty().signal(); 
/*     */       return akka$util$BoundedBlockingQueue$$backing().retainAll(c);
/*     */     } finally {
/* 223 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/* 227 */     lock().lock();
/*     */     try {
/* 229 */       Object[] elements = akka$util$BoundedBlockingQueue$$backing().toArray();
/* 230 */       return new BoundedBlockingQueue$$anon$1(this, (BoundedBlockingQueue<E>)elements);
/*     */     } finally {
/* 261 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public class BoundedBlockingQueue$$anon$1 implements Iterator<E> {
/*     */     private int at = 0;
/*     */     
/*     */     private int at() {
/*     */       return this.at;
/*     */     }
/*     */     
/*     */     private void at_$eq(int x$1) {
/*     */       this.at = x$1;
/*     */     }
/*     */     
/*     */     private int last = -1;
/*     */     
/*     */     private final Object[] elements$1;
/*     */     
/*     */     private int last() {
/*     */       return this.last;
/*     */     }
/*     */     
/*     */     private void last_$eq(int x$1) {
/*     */       this.last = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*     */       return (at() < this.elements$1.length);
/*     */     }
/*     */     
/*     */     public E next() {
/*     */       if (at() >= this.elements$1.length)
/*     */         throw new NoSuchElementException(); 
/*     */       last_$eq(at());
/*     */       at_$eq(at() + 1);
/*     */       return (E)this.elements$1[last()];
/*     */     }
/*     */     
/*     */     public void remove() {
/*     */       if (last() < 0)
/*     */         throw new IllegalStateException(); 
/*     */       Object target = this.elements$1[last()];
/*     */       last_$eq(-1);
/*     */       this.$outer.lock().lock();
/*     */       try {
/*     */         removeTarget$1(removeTarget$default$1$1(), target);
/*     */         return;
/*     */       } finally {
/*     */         this.$outer.lock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     private final Iterator removeTarget$default$1$1() {
/*     */       return this.$outer.akka$util$BoundedBlockingQueue$$backing().iterator();
/*     */     }
/*     */     
/*     */     private final void removeTarget$1(Iterator i, Object target$1) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: invokeinterface hasNext : ()Z
/*     */       //   6: ifeq -> 48
/*     */       //   9: aload_1
/*     */       //   10: invokeinterface next : ()Ljava/lang/Object;
/*     */       //   15: aload_2
/*     */       //   16: if_acmpne -> 43
/*     */       //   19: aload_1
/*     */       //   20: invokeinterface remove : ()V
/*     */       //   25: aload_0
/*     */       //   26: getfield $outer : Lakka/util/BoundedBlockingQueue;
/*     */       //   29: invokevirtual akka$util$BoundedBlockingQueue$$notFull : ()Ljava/util/concurrent/locks/Condition;
/*     */       //   32: invokeinterface signal : ()V
/*     */       //   37: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   40: goto -> 51
/*     */       //   43: aload_1
/*     */       //   44: astore_1
/*     */       //   45: goto -> 0
/*     */       //   48: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   51: pop
/*     */       //   52: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #250	-> 0
/*     */       //   #251	-> 9
/*     */       //   #252	-> 19
/*     */       //   #253	-> 25
/*     */       //   #254	-> 43
/*     */       //   #250	-> 48
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	53	0	this	Lakka/util/BoundedBlockingQueue$$anon$1;
/*     */       //   0	53	1	i	Ljava/util/Iterator;
/*     */       //   0	53	2	target$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public BoundedBlockingQueue$$anon$1(BoundedBlockingQueue $outer, Object[] elements$1) {}
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 265 */     lock().lock();
/*     */     try {
/* 266 */       return akka$util$BoundedBlockingQueue$$backing().toArray();
/*     */     } finally {
/* 266 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 270 */     lock().lock();
/*     */     try {
/* 271 */       return akka$util$BoundedBlockingQueue$$backing().isEmpty();
/*     */     } finally {
/* 271 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <X> X[] toArray(Object[] a) {
/* 275 */     lock().lock();
/*     */     try {
/* 276 */       return (X[])akka$util$BoundedBlockingQueue$$backing().toArray(a);
/*     */     } finally {
/* 276 */       lock().unlock();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\BoundedBlockingQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */