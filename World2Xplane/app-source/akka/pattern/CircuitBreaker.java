/*     */ package akka.pattern;
/*     */ 
/*     */ import akka.actor.Scheduler;
/*     */ import akka.util.Unsafe;
/*     */ import java.util.Iterator;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import scala.Function0;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.Awaitable;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Future;
/*     */ import scala.concurrent.duration.Deadline;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.concurrent.duration.package;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Try;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tUw!B\001\003\021\0039\021AD\"je\016,\030\016\036\"sK\006\\WM\035\006\003\007\021\tq\001]1ui\026\024hNC\001\006\003\021\t7n[1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\tq1)\033:dk&$(I]3bW\026\0248CA\005\r!\ti\001#D\001\017\025\005y\021!B:dC2\f\027BA\t\017\005\031\te.\037*fM\")1#\003C\001)\0051A(\0338jiz\"\022a\002\005\006-%!\taF\001\006CB\004H.\037\013\n1\t}&\021\031Bb\005\013\004\"\001C\r\007\t)\021\001AG\n\0033m\001\"\001\003\017\n\005u\021!AF!cgR\024\030m\031;DSJ\034W/\033;Ce\026\f7.\032:\t\021}I\"\021!Q\001\n\001\n\021b]2iK\022,H.\032:\021\005\005\"S\"\001\022\013\005\r\"\021!B1di>\024\030BA\023#\005%\0316\r[3ek2,'\017\003\005(3\t\005\t\025!\003)\003-i\027\r\037$bS2,(/Z:\021\0055I\023B\001\026\017\005\rIe\016\036\005\tYe\021\t\021)A\005[\005Y1-\0317m)&lWm\\;u!\tq3'D\0010\025\t\001\024'\001\005ekJ\fG/[8o\025\t\021d\"\001\006d_:\034WO\035:f]RL!\001N\030\003\035\031Kg.\033;f\tV\024\030\r^5p]\"Aa'\007B\001B\003%Q&\001\007sKN,G\017V5nK>,H\017\003\00593\t\005\t\025a\003:\003!)\0070Z2vi>\024\bC\001\036<\033\005\t\024B\001\0372\005A)\0050Z2vi&|gnQ8oi\026DH\017C\003\0243\021\005a\bF\003@\003\n\033E\t\006\002\031\001\")\001(\020a\002s!)q$\020a\001A!)q%\020a\001Q!)A&\020a\001[!)a'\020a\001[!)1#\007C\001\rR1\001d\022%J\025.CQ\001O#A\002eBQaH#A\002\001BQaJ#A\002!BQ\001L#A\0025BQAN#A\0025Ba!T\r!B\023q\025\001I0dkJ\024XM\034;Ti\006$X\rR8O_R\034\025\r\0347NK\022K'/Z2uYf\004\"a\024)\016\003e1q!U\r\021\002\007%\"KA\003Ti\006$Xm\005\002Q\031!)A\013\025C\001+\0061A%\0338ji\022\"\022A\026\t\003\033]K!\001\027\b\003\tUs\027\016\036\005\b5B\023\r\021\"\003\\\003%a\027n\035;f]\026\0248/F\001]!\ri6-Z\007\002=*\021!g\030\006\003A\006\fA!\036;jY*\t!-\001\003kCZ\f\027B\0013_\005Q\031u\016]=P]^\023\030\016^3BeJ\f\027\020T5tiB\021a-[\007\002O*\021\001.Y\001\005Y\006tw-\003\002kO\nA!+\0368oC\ndW\r\003\004m!\002\006I\001X\001\013Y&\034H/\0328feN\004\003\"\0028Q\t\003y\027aC1eI2K7\017^3oKJ$\"A\0269\t\013El\007\031A3\002\0211L7\017^3oKJDQa\035)\005\nQ\fA\002[1t\031&\034H/\0328feN,\022!\036\t\003\033YL!a\036\b\003\017\t{w\016\\3b]\")\021\020\025C\t+\006Ibn\034;jMf$&/\0318tSRLwN\034'jgR,g.\032:t\021\025Y\b\013\"\001}\003-\031\027\r\0347UQJ|Wo\0325\026\007u\f9\001F\002\0033\001BAO@\002\004%\031\021\021A\031\003\r\031+H/\036:f!\021\t)!a\002\r\001\0219\021\021\002>C\002\005-!!\001+\022\t\0055\0211\003\t\004\033\005=\021bAA\t\035\t9aj\034;iS:<\007cA\007\002\026%\031\021q\003\b\003\007\005s\027\020\003\005\002\034i$\t\031AA\017\003\021\021w\016Z=\021\t5\tyB`\005\004\003Cq!\001\003\037cs:\fW.\032 \t\017\005\025\002K\"\001\002(\0051\021N\034<pW\026,B!!\013\0020Q!\0211FA\031!\021Qt0!\f\021\t\005\025\021q\006\003\t\003\023\t\031C1\001\002\f!I\0211DA\022\t\003\007\0211\007\t\006\033\005}\0211\006\005\007\003o\001f\021A+\002\031\r\fG\016\\*vG\016,W\rZ:\t\r\005m\002K\"\001V\003%\031\027\r\0347GC&d7\017\003\004\002@A#)!V\001\006K:$XM\035\005\007\003\007\002f\021A+\002\r}+g\016^3sS\035\001\026qIAK\003\0034q!!\023\032\021\023\tYE\001\004DY>\034X\rZ\n\006\003\017\niE\024\t\005\003\037\n)&\004\002\002R)\031\0211\0130\002\r\005$x.\\5d\023\021\t9&!\025\003\033\005#x.\\5d\023:$XmZ3s\021\035\031\022q\tC\001\0037\"\"!!\030\021\007=\0139\005\003\005\002&\005\035C\021IA1+\021\t\031'!\033\025\t\005\025\0241\016\t\005u}\f9\007\005\003\002\006\005%D\001CA\005\003?\022\r!a\003\t\023\005m\021q\fCA\002\0055\004#B\007\002 \005\025\004bBA\034\003\017\"\t%\026\005\b\003w\t9\005\"\021V\021\035\t\031%a\022\005BUC\001\"a\036\002H\021\005\023\021P\001\ti>\034FO]5oOR\021\0211\020\t\005\003{\n\031ID\002\016\003J1!!!\017\003\031\001&/\0323fM&!\021QQAD\005\031\031FO]5oO*\031\021\021\021\b\t\025\005-\025qIA\001\n\023\ti)A\006sK\006$'+Z:pYZ,GCAAH!\r1\027\021S\005\004\003';'AB(cU\026\034GOB\004\002\030fAI!!'\003\021!\013GNZ(qK:\034R!!&\002\034:\003B!a\024\002\036&!\021qTA)\0055\tEo\\7jG\n{w\016\\3b]\"91#!&\005\002\005\rFCAAS!\ry\025Q\023\005\t\003K\t)\n\"\021\002*V!\0211VAY)\021\ti+a-\021\tiz\030q\026\t\005\003\013\t\t\f\002\005\002\n\005\035&\031AA\006\021%\tY\"a*\005\002\004\t)\fE\003\016\003?\ti\013C\004\0028\005UE\021I+\t\017\005m\022Q\023C!+\"9\0211IAK\t\003*\006\002CA<\003+#\t%!\037\t\025\005-\025QSA\001\n\023\tiIB\004\002DfAI!!2\003\t=\003XM\\\n\006\003\003\f9M\024\t\005\003\037\nI-\003\003\002L\006E#AC!u_6L7\rT8oO\"91#!1\005\002\005=GCAAi!\ry\025\021\031\005\t\003K\t\t\r\"\021\002VV!\021q[Ao)\021\tI.a8\021\tiz\0301\034\t\005\003\013\ti\016\002\005\002\n\005M'\031AA\006\021%\tY\"a5\005\002\004\t\t\017E\003\016\003?\tI\016\003\005\002f\006\005G\021BAt\003E\021X-\\1j]&tw\rR;sCRLwN\034\013\002[!9\021qGAa\t\003*\006bBA\036\003\003$\t%\026\005\b\003\007\n\t\r\"\021V\021!\t9(!1\005B\005e\004BCAF\003\003\f\t\021\"\003\002\016\"\032A*!>\021\0075\t90C\002\002z:\021\001B^8mCRLG.\032\005\t\003{L\002\025\"\003\002\000\006I1o^1q'R\fG/\032\013\006k\n\005!Q\001\005\b\005\007\tY\0201\001O\003!yG\016Z*uCR,\007b\002B\004\003w\004\rAT\001\t]\026<8\013^1uK\"\"\0211 B\006!\ri!QB\005\004\005\037q!AB5oY&tW\r\003\005\003\024e\001K\021\002B\013\0031\031WO\035:f]R\034F/\031;f+\005q\005\006\002B\t\005\027AqAa\007\032\t\003\021i\"\001\nxSRD7)\033:dk&$(I]3bW\026\024X\003\002B\020\005K!BA!\t\003(A!!h B\022!\021\t)A!\n\005\021\005%!\021\004b\001\003\027A\021\"a\007\003\032\021\005\rA!\013\021\0135\tyB!\t\t\017\t5\022\004\"\001\0030\00512-\0317m/&$\bnQ5sGVLGO\021:fC.,'/\006\003\0032\t]B\003\002B\032\005s\001BAO@\0036A!\021Q\001B\034\t!\tIAa\013C\002\005-\001\002CA\016\005W\001\rAa\017\021\013u\023iDa\r\n\007\t}bL\001\005DC2d\027M\0317f\021\035\021\031%\007C\001\005\013\nac^5uQNKhnY\"je\016,\030\016\036\"sK\006\\WM]\013\005\005\017\022Y\005\006\003\003J\t5\003\003BA\003\005\027\"\001\"!\003\003B\t\007\0211\002\005\n\0037\021\t\005\"a\001\005\037\002R!DA\020\005\023BqAa\025\032\t\003\021)&\001\016dC2dw+\033;i'ft7mQ5sGVLGO\021:fC.,'/\006\003\003X\tmC\003\002B-\005;\002B!!\002\003\\\021A\021\021\002B)\005\004\tY\001\003\005\002\034\tE\003\031\001B0!\025i&Q\bB-\021\035\021\031'\007C\001\005K\naa\0348Pa\026tGc\001\r\003h!I!\021\016B1\t\003\007!1N\001\tG\006dGNY1dWB!Q\"a\bW\021\035\021\031'\007C\001\005_\"2\001\007B9\021\035\021IG!\034A\002\025DqA!\036\032\t\003\0219(\001\006p]\"\013GNZ(qK:$2\001\007B=\021%\021IGa\035\005\002\004\021Y\007C\004\003ve!\tA! \025\007a\021y\bC\004\003j\tm\004\031A3\t\017\t\r\025\004\"\001\003\006\0069qN\\\"m_N,Gc\001\r\003\b\"I!\021\016BA\t\003\007!1\016\005\b\005\007KB\021\001BF)\rA\"Q\022\005\b\005S\022I\t1\001f\021!\021\t*\007C\001\t\tM\025aE2veJ,g\016\036$bS2,(/Z\"pk:$X#\001\025\t\017\t]\025\004\"\003\003\032\006QAO]1og&$\030n\0348\025\013Y\023YJa(\t\017\tu%Q\023a\001\035\006IaM]8n'R\fG/\032\005\b\005C\023)\n1\001O\003\035!xn\025;bi\026DqA!*\032\t\023\0219+A\006ue&\004(I]3bW\026\024Hc\001,\003*\"9!Q\024BR\001\004q\005B\002BW3\021%Q+\001\007sKN,GO\021:fC.,'\017\003\004\0032f!I!V\001\rCR$X-\0349u%\026\034X\r^\004\b\005kK\002\022BA/\003\031\031En\\:fI\0369!\021X\r\t\n\005\025\026\001\003%bY\032|\005/\0328\b\017\tu\026\004#\003\002R\006!q\n]3o\021\025yR\0031\001!\021\0259S\0031\001)\021\025aS\0031\001.\021\0251T\0031\001.\021\035\021I-\003C\001\005\027\faa\031:fCR,G#\003\r\003N\n='\021\033Bj\021\031y\"q\031a\001A!1qEa2A\002!Ba\001\fBd\001\004i\003B\002\034\003H\002\007Q\006")
/*     */ public class CircuitBreaker extends AbstractCircuitBreaker {
/*     */   public final Scheduler akka$pattern$CircuitBreaker$$scheduler;
/*     */   
/*     */   public final int akka$pattern$CircuitBreaker$$maxFailures;
/*     */   
/*     */   public final FiniteDuration akka$pattern$CircuitBreaker$$callTimeout;
/*     */   
/*     */   public final FiniteDuration akka$pattern$CircuitBreaker$$resetTimeout;
/*     */   
/*     */   public final ExecutionContext akka$pattern$CircuitBreaker$$executor;
/*     */   
/*     */   public CircuitBreaker(ExecutionContext executor, Scheduler scheduler, int maxFailures, FiniteDuration callTimeout, FiniteDuration resetTimeout) {
/*  77 */     this(scheduler, maxFailures, callTimeout, resetTimeout, executor);
/*     */   }
/*     */   
/*  84 */   private volatile State _currentStateDoNotCallMeDirectly = akka$pattern$CircuitBreaker$$Closed();
/*     */   
/*     */   private volatile Closed$ Closed$module;
/*     */   
/*     */   private volatile HalfOpen$ HalfOpen$module;
/*     */   
/*     */   private volatile Open$ Open$module;
/*     */   
/*     */   private boolean swapState(State oldState, State newState) {
/*  95 */     return Unsafe.instance.compareAndSwapObject(this, AbstractCircuitBreaker.stateOffset, oldState, newState);
/*     */   }
/*     */   
/*     */   private State currentState() {
/* 104 */     return (State)Unsafe.instance.getObjectVolatile(this, AbstractCircuitBreaker.stateOffset);
/*     */   }
/*     */   
/*     */   public <T> Future<T> withCircuitBreaker(Function0<Future<T>> body) {
/* 113 */     return currentState().invoke(body);
/*     */   }
/*     */   
/*     */   public <T> Future<T> callWithCircuitBreaker(Callable body) {
/* 122 */     return withCircuitBreaker((Function0<Future<T>>)new CircuitBreaker$$anonfun$callWithCircuitBreaker$1(this, body));
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anonfun$callWithCircuitBreaker$1 extends AbstractFunction0<Future<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Callable body$1;
/*     */     
/*     */     public final Future<T> apply() {
/* 122 */       return this.body$1.call();
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anonfun$callWithCircuitBreaker$1(CircuitBreaker $outer, Callable body$1) {}
/*     */   }
/*     */   
/*     */   public <T> T withSyncCircuitBreaker(Function0 body) {
/* 134 */     return (T)scala.concurrent.Await$.MODULE$.result(
/* 135 */         (Awaitable)withCircuitBreaker((Function0<Future<?>>)new CircuitBreaker$$anonfun$withSyncCircuitBreaker$1(this, body)), 
/* 136 */         (Duration)this.akka$pattern$CircuitBreaker$$callTimeout);
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anonfun$withSyncCircuitBreaker$1 extends AbstractFunction0<Future<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function0 body$2;
/*     */     
/*     */     public final Future<T> apply() {
/*     */       try {
/*     */       
/*     */       } finally {
/*     */         Future<T> future;
/*     */         Exception exception1 = null, exception2 = exception1;
/*     */         Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/*     */         if (option.isEmpty())
/*     */           throw exception1; 
/*     */         Throwable t = (Throwable)option.get();
/*     */       } 
/*     */       return future;
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anonfun$withSyncCircuitBreaker$1(CircuitBreaker $outer, Function0 body$2) {}
/*     */   }
/*     */   
/*     */   public <T> T callWithSyncCircuitBreaker(Callable body) {
/* 146 */     return withSyncCircuitBreaker((Function0<T>)new CircuitBreaker$$anonfun$callWithSyncCircuitBreaker$1(this, body));
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anonfun$callWithSyncCircuitBreaker$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Callable body$3;
/*     */     
/*     */     public final T apply() {
/* 146 */       return this.body$3.call();
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anonfun$callWithSyncCircuitBreaker$1(CircuitBreaker $outer, Callable body$3) {}
/*     */   }
/*     */   
/*     */   public CircuitBreaker onOpen(Function0 callback) {
/* 156 */     return onOpen(new CircuitBreaker$$anon$1(this, callback));
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anon$1 implements Runnable {
/*     */     private final Function0 callback$1;
/*     */     
/*     */     public void run() {
/* 156 */       this.callback$1.apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anon$1(CircuitBreaker $outer, Function0 callback$1) {}
/*     */   }
/*     */   
/*     */   public CircuitBreaker onOpen(Runnable callback) {
/* 165 */     akka$pattern$CircuitBreaker$$Open().addListener(callback);
/* 166 */     return this;
/*     */   }
/*     */   
/*     */   public CircuitBreaker onHalfOpen(Function0 callback) {
/* 177 */     return onHalfOpen(new CircuitBreaker$$anon$2(this, callback));
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anon$2 implements Runnable {
/*     */     private final Function0 callback$2;
/*     */     
/*     */     public void run() {
/* 177 */       this.callback$2.apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anon$2(CircuitBreaker $outer, Function0 callback$2) {}
/*     */   }
/*     */   
/*     */   public CircuitBreaker onHalfOpen(Runnable callback) {
/* 186 */     akka$pattern$CircuitBreaker$$HalfOpen().addListener(callback);
/* 187 */     return this;
/*     */   }
/*     */   
/*     */   public CircuitBreaker onClose(Function0 callback) {
/* 198 */     return onClose(new CircuitBreaker$$anon$3(this, callback));
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anon$3 implements Runnable {
/*     */     private final Function0 callback$3;
/*     */     
/*     */     public void run() {
/* 198 */       this.callback$3.apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anon$3(CircuitBreaker $outer, Function0 callback$3) {}
/*     */   }
/*     */   
/*     */   public CircuitBreaker onClose(Runnable callback) {
/* 207 */     akka$pattern$CircuitBreaker$$Closed().addListener(callback);
/* 208 */     return this;
/*     */   }
/*     */   
/*     */   public int currentFailureCount() {
/* 216 */     return akka$pattern$CircuitBreaker$$Closed().get();
/*     */   }
/*     */   
/*     */   private void transition(State fromState, State toState) {
/* 226 */     if (swapState(fromState, toState)) {
/* 227 */       toState.enter();
/*     */       return;
/*     */     } 
/* 229 */     throw new IllegalStateException((new StringBuilder()).append("Illegal transition attempted from: ").append(fromState).append(" to ").append(toState).toString());
/*     */   }
/*     */   
/*     */   public void akka$pattern$CircuitBreaker$$tripBreaker(State fromState) {
/* 236 */     transition(fromState, akka$pattern$CircuitBreaker$$Open());
/*     */   }
/*     */   
/*     */   public void akka$pattern$CircuitBreaker$$resetBreaker() {
/* 242 */     transition(akka$pattern$CircuitBreaker$$HalfOpen(), akka$pattern$CircuitBreaker$$Closed());
/*     */   }
/*     */   
/*     */   public void akka$pattern$CircuitBreaker$$attemptReset() {
/* 248 */     transition(akka$pattern$CircuitBreaker$$Open(), akka$pattern$CircuitBreaker$$HalfOpen());
/*     */   }
/*     */   
/*     */   public abstract class State$class {
/*     */     public static void $init$(CircuitBreaker.State $this) {
/* 254 */       $this.akka$pattern$CircuitBreaker$State$_setter_$akka$pattern$CircuitBreaker$State$$listeners_$eq(new CopyOnWriteArrayList());
/*     */     }
/*     */     
/*     */     public static void addListener(CircuitBreaker.State $this, Runnable listener) {
/* 262 */       $this.akka$pattern$CircuitBreaker$State$$listeners().add(listener);
/*     */     }
/*     */     
/*     */     private static boolean hasListeners(CircuitBreaker.State $this) {
/* 269 */       return !$this.akka$pattern$CircuitBreaker$State$$listeners().isEmpty();
/*     */     }
/*     */     
/*     */     public static void notifyTransitionListeners(CircuitBreaker.State $this) {
/* 277 */       if (hasListeners($this)) {
/* 278 */         Iterator<Runnable> iterator = $this.akka$pattern$CircuitBreaker$State$$listeners().iterator();
/* 279 */         while (iterator.hasNext()) {
/* 280 */           Runnable listener = iterator.next();
/* 281 */           ($this.akka$pattern$CircuitBreaker$State$$$outer()).akka$pattern$CircuitBreaker$$executor.execute(listener);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public static Future callThrough(CircuitBreaker.State $this, Function0 body) {
/*     */       Future bodyFuture;
/* 295 */       Deadline deadline = ($this.akka$pattern$CircuitBreaker$State$$$outer()).akka$pattern$CircuitBreaker$$callTimeout.fromNow();
/*     */     }
/*     */     
/*     */     public static final void enter(CircuitBreaker.State $this) {
/* 331 */       $this._enter();
/* 332 */       $this.notifyTransitionListeners();
/*     */     }
/*     */   }
/*     */   
/*     */   public interface State {
/*     */     void akka$pattern$CircuitBreaker$State$_setter_$akka$pattern$CircuitBreaker$State$$listeners_$eq(CopyOnWriteArrayList param1CopyOnWriteArrayList);
/*     */     
/*     */     CopyOnWriteArrayList<Runnable> akka$pattern$CircuitBreaker$State$$listeners();
/*     */     
/*     */     void addListener(Runnable param1Runnable);
/*     */     
/*     */     void notifyTransitionListeners();
/*     */     
/*     */     <T> Future<T> callThrough(Function0<Future<T>> param1Function0);
/*     */     
/*     */     <T> Future<T> invoke(Function0<Future<T>> param1Function0);
/*     */     
/*     */     void callSucceeds();
/*     */     
/*     */     void callFails();
/*     */     
/*     */     void enter();
/*     */     
/*     */     void _enter();
/*     */     
/*     */     public class CircuitBreaker$State$$anonfun$callThrough$1 extends AbstractFunction1<Try<T>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Deadline deadline$1;
/*     */       
/*     */       public final void apply(Try x0$1) {
/*     */         Try try_ = x0$1;
/*     */         if (try_ instanceof scala.util.Success && !this.deadline$1.isOverdue()) {
/*     */           this.$outer.callSucceeds();
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } else {
/*     */           this.$outer.callFails();
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } 
/*     */       }
/*     */       
/*     */       public CircuitBreaker$State$$anonfun$callThrough$1(CircuitBreaker.State $outer, Deadline deadline$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   private Closed$ akka$pattern$CircuitBreaker$$Closed$lzycompute() {
/* 345 */     synchronized (this) {
/* 345 */       if (this.Closed$module == null)
/* 345 */         this.Closed$module = new Closed$(this); 
/* 345 */       null;
/* 345 */       return this.Closed$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Closed$ akka$pattern$CircuitBreaker$$Closed() {
/* 345 */     return (this.Closed$module == null) ? akka$pattern$CircuitBreaker$$Closed$lzycompute() : this.Closed$module;
/*     */   }
/*     */   
/*     */   public class Closed$ extends AtomicInteger implements State {
/*     */     private final CopyOnWriteArrayList<Runnable> akka$pattern$CircuitBreaker$State$$listeners;
/*     */     
/*     */     public CopyOnWriteArrayList<Runnable> akka$pattern$CircuitBreaker$State$$listeners() {
/* 345 */       return this.akka$pattern$CircuitBreaker$State$$listeners;
/*     */     }
/*     */     
/*     */     public void akka$pattern$CircuitBreaker$State$_setter_$akka$pattern$CircuitBreaker$State$$listeners_$eq(CopyOnWriteArrayList<Runnable> x$1) {
/* 345 */       this.akka$pattern$CircuitBreaker$State$$listeners = x$1;
/*     */     }
/*     */     
/*     */     public void addListener(Runnable listener) {
/* 345 */       CircuitBreaker.State$class.addListener(this, listener);
/*     */     }
/*     */     
/*     */     public void notifyTransitionListeners() {
/* 345 */       CircuitBreaker.State$class.notifyTransitionListeners(this);
/*     */     }
/*     */     
/*     */     public <T> Future<T> callThrough(Function0 body) {
/* 345 */       return CircuitBreaker.State$class.callThrough(this, body);
/*     */     }
/*     */     
/*     */     public final void enter() {
/* 345 */       CircuitBreaker.State$class.enter(this);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 345 */       return this.$outer.akka$pattern$CircuitBreaker$$Closed();
/*     */     }
/*     */     
/*     */     public Closed$(CircuitBreaker $outer) {
/* 345 */       CircuitBreaker.State$class.$init$(this);
/*     */     }
/*     */     
/*     */     public <T> Future<T> invoke(Function0<Future<T>> body) {
/* 354 */       return callThrough(body);
/*     */     }
/*     */     
/*     */     public void callSucceeds() {
/* 361 */       set(0);
/*     */     }
/*     */     
/*     */     public void callFails() {
/* 369 */       if (incrementAndGet() == this.$outer.akka$pattern$CircuitBreaker$$maxFailures)
/* 369 */         this.$outer.akka$pattern$CircuitBreaker$$tripBreaker(this.$outer.akka$pattern$CircuitBreaker$$Closed()); 
/*     */     }
/*     */     
/*     */     public void _enter() {
/* 376 */       set(0);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 383 */       return (new StringBuilder()).append("Closed with failure count = ").append(BoxesRunTime.boxToInteger(get())).toString();
/*     */     }
/*     */   }
/*     */   
/*     */   private HalfOpen$ akka$pattern$CircuitBreaker$$HalfOpen$lzycompute() {
/* 389 */     synchronized (this) {
/* 389 */       if (this.HalfOpen$module == null)
/* 389 */         this.HalfOpen$module = new HalfOpen$(this); 
/* 389 */       null;
/* 389 */       return this.HalfOpen$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public HalfOpen$ akka$pattern$CircuitBreaker$$HalfOpen() {
/* 389 */     return (this.HalfOpen$module == null) ? akka$pattern$CircuitBreaker$$HalfOpen$lzycompute() : this.HalfOpen$module;
/*     */   }
/*     */   
/*     */   public class HalfOpen$ extends AtomicBoolean implements State {
/*     */     private final CopyOnWriteArrayList<Runnable> akka$pattern$CircuitBreaker$State$$listeners;
/*     */     
/*     */     public CopyOnWriteArrayList<Runnable> akka$pattern$CircuitBreaker$State$$listeners() {
/* 389 */       return this.akka$pattern$CircuitBreaker$State$$listeners;
/*     */     }
/*     */     
/*     */     public void akka$pattern$CircuitBreaker$State$_setter_$akka$pattern$CircuitBreaker$State$$listeners_$eq(CopyOnWriteArrayList<Runnable> x$1) {
/* 389 */       this.akka$pattern$CircuitBreaker$State$$listeners = x$1;
/*     */     }
/*     */     
/*     */     public void addListener(Runnable listener) {
/* 389 */       CircuitBreaker.State$class.addListener(this, listener);
/*     */     }
/*     */     
/*     */     public void notifyTransitionListeners() {
/* 389 */       CircuitBreaker.State$class.notifyTransitionListeners(this);
/*     */     }
/*     */     
/*     */     public <T> Future<T> callThrough(Function0 body) {
/* 389 */       return CircuitBreaker.State$class.callThrough(this, body);
/*     */     }
/*     */     
/*     */     public final void enter() {
/* 389 */       CircuitBreaker.State$class.enter(this);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 389 */       return this.$outer.akka$pattern$CircuitBreaker$$HalfOpen();
/*     */     }
/*     */     
/*     */     public HalfOpen$(CircuitBreaker $outer) {
/* 389 */       super(true);
/* 389 */       CircuitBreaker.State$class.$init$(this);
/*     */     }
/*     */     
/*     */     public <T> Future<T> invoke(Function0<Future<T>> body) {
/* 400 */       return compareAndSet(true, false) ? callThrough(body) : scala.concurrent.Promise$.MODULE$.failed((Throwable)new CircuitBreakerOpenException((new package.DurationInt(scala.concurrent.duration.package$.MODULE$.DurationInt(0))).seconds(), CircuitBreakerOpenException$.MODULE$.$lessinit$greater$default$2())).future();
/*     */     }
/*     */     
/*     */     public void callSucceeds() {
/* 407 */       this.$outer.akka$pattern$CircuitBreaker$$resetBreaker();
/*     */     }
/*     */     
/*     */     public void callFails() {
/* 414 */       this.$outer.akka$pattern$CircuitBreaker$$tripBreaker(this.$outer.akka$pattern$CircuitBreaker$$HalfOpen());
/*     */     }
/*     */     
/*     */     public void _enter() {
/* 421 */       set(true);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 428 */       return (new StringBuilder()).append("Half-Open currently testing call for success = ").append(BoxesRunTime.boxToBoolean(get())).toString();
/*     */     }
/*     */   }
/*     */   
/*     */   private Open$ akka$pattern$CircuitBreaker$$Open$lzycompute() {
/* 434 */     synchronized (this) {
/* 434 */       if (this.Open$module == null)
/* 434 */         this.Open$module = new Open$(this); 
/* 434 */       null;
/* 434 */       return this.Open$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Open$ akka$pattern$CircuitBreaker$$Open() {
/* 434 */     return (this.Open$module == null) ? akka$pattern$CircuitBreaker$$Open$lzycompute() : this.Open$module;
/*     */   }
/*     */   
/*     */   public static CircuitBreaker create(Scheduler paramScheduler, int paramInt, FiniteDuration paramFiniteDuration1, FiniteDuration paramFiniteDuration2) {
/*     */     return CircuitBreaker$.MODULE$.create(paramScheduler, paramInt, paramFiniteDuration1, paramFiniteDuration2);
/*     */   }
/*     */   
/*     */   public static CircuitBreaker apply(Scheduler paramScheduler, int paramInt, FiniteDuration paramFiniteDuration1, FiniteDuration paramFiniteDuration2) {
/*     */     return CircuitBreaker$.MODULE$.apply(paramScheduler, paramInt, paramFiniteDuration1, paramFiniteDuration2);
/*     */   }
/*     */   
/*     */   public CircuitBreaker(Scheduler scheduler, int maxFailures, FiniteDuration callTimeout, FiniteDuration resetTimeout, ExecutionContext executor) {}
/*     */   
/*     */   public class Open$ extends AtomicLong implements State {
/*     */     private final CopyOnWriteArrayList<Runnable> akka$pattern$CircuitBreaker$State$$listeners;
/*     */     
/*     */     public CopyOnWriteArrayList<Runnable> akka$pattern$CircuitBreaker$State$$listeners() {
/* 434 */       return this.akka$pattern$CircuitBreaker$State$$listeners;
/*     */     }
/*     */     
/*     */     public void akka$pattern$CircuitBreaker$State$_setter_$akka$pattern$CircuitBreaker$State$$listeners_$eq(CopyOnWriteArrayList<Runnable> x$1) {
/* 434 */       this.akka$pattern$CircuitBreaker$State$$listeners = x$1;
/*     */     }
/*     */     
/*     */     public void addListener(Runnable listener) {
/* 434 */       CircuitBreaker.State$class.addListener(this, listener);
/*     */     }
/*     */     
/*     */     public void notifyTransitionListeners() {
/* 434 */       CircuitBreaker.State$class.notifyTransitionListeners(this);
/*     */     }
/*     */     
/*     */     public <T> Future<T> callThrough(Function0 body) {
/* 434 */       return CircuitBreaker.State$class.callThrough(this, body);
/*     */     }
/*     */     
/*     */     public final void enter() {
/* 434 */       CircuitBreaker.State$class.enter(this);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 434 */       return this.$outer.akka$pattern$CircuitBreaker$$Open();
/*     */     }
/*     */     
/*     */     public Open$(CircuitBreaker $outer) {
/* 434 */       CircuitBreaker.State$class.$init$(this);
/*     */     }
/*     */     
/*     */     public <T> Future<T> invoke(Function0 body) {
/* 444 */       return scala.concurrent.Promise$.MODULE$.failed((Throwable)new CircuitBreakerOpenException(remainingDuration(), CircuitBreakerOpenException$.MODULE$.$lessinit$greater$default$2())).future();
/*     */     }
/*     */     
/*     */     private FiniteDuration remainingDuration() {
/* 452 */       long diff = System.nanoTime() - get();
/* 453 */       return (diff <= 0L) ? scala.concurrent.duration.Duration$.MODULE$.Zero() : (
/* 454 */         new package.DurationLong(scala.concurrent.duration.package$.MODULE$.DurationLong(diff))).nanos();
/*     */     }
/*     */     
/*     */     public void callSucceeds() {}
/*     */     
/*     */     public void callFails() {}
/*     */     
/*     */     public void _enter() {
/* 478 */       set(System.nanoTime());
/* 479 */       this.$outer.akka$pattern$CircuitBreaker$$scheduler.scheduleOnce(this.$outer.akka$pattern$CircuitBreaker$$resetTimeout, 
/* 480 */           (Function0)new CircuitBreaker$Open$$anonfun$_enter$1(this), this.$outer.akka$pattern$CircuitBreaker$$executor);
/*     */     }
/*     */     
/*     */     public static class CircuitBreaker$Open$$anonfun$_enter$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply() {
/* 480 */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/* 480 */         this.$outer.akka$pattern$CircuitBreaker$Open$$$outer().akka$pattern$CircuitBreaker$$attemptReset();
/*     */       }
/*     */       
/*     */       public CircuitBreaker$Open$$anonfun$_enter$1(CircuitBreaker.Open$ $outer) {}
/*     */     }
/*     */     
/*     */     public String toString() {
/* 489 */       return "Open";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\CircuitBreaker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */