/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Cell;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.event.Logging;
/*     */ import akka.event.Logging$;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.RejectedExecutionException;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005ee\001B\001\003\001\035\021!\002R5ta\006$8\r[3s\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001a\005\002\001\021A\021\021BC\007\002\005%\0211B\001\002\022\033\026\0348/Y4f\t&\034\b/\031;dQ\026\024\b\"C\007\001\005\003\005\013\021\002\b\022\0035y6m\0348gS\036,(/\031;peB\021\021bD\005\003!\t\021Q$T3tg\006<W\rR5ta\006$8\r[3s\007>tg-[4ve\006$xN]\005\003%)\tAbY8oM&<WO]1u_JD\001\002\006\001\003\006\004%\t!F\001\003S\022,\022A\006\t\003/uq!\001G\016\016\003eQ\021AG\001\006g\016\fG.Y\005\0039e\ta\001\025:fI\0264\027B\001\020 \005\031\031FO]5oO*\021A$\007\005\tC\001\021\t\021)A\005-\005\031\021\016\032\021\t\021\r\002!Q1A\005\002\021\n!\002\0365s_V<\007\016];u+\005)\003C\001\r'\023\t9\023DA\002J]RD\001\"\013\001\003\002\003\006I!J\001\fi\"\024x.^4iaV$\b\005\003\005,\001\t\025\r\021\"\001-\003Y!\bN]8vO\"\004X\017\036#fC\022d\027N\\3US6,W#A\027\021\0059\032T\"A\030\013\005A\n\024\001\0033ve\006$\030n\0348\013\005IJ\022AC2p]\016,(O]3oi&\021Ag\f\002\t\tV\024\030\r^5p]\"Aa\007\001B\001B\003%Q&A\fuQJ|Wo\0325qkR$U-\0313mS:,G+[7fA!A\001\b\001B\001B\003%\021(\001\020fq\026\034W\017^8s'\026\024h/[2f\r\006\034Go\034:z!J|g/\0333feB\021\021BO\005\003w\t\021a$\022=fGV$xN]*feZL7-\032$bGR|'/\037)s_ZLG-\032:\t\021u\002!Q1A\005\002y\nqb\0355vi\022|wO\034+j[\026|W\017^\013\002A\021a\006Q\005\003\003>\022aBR5oSR,G)\036:bi&|g\016\003\005D\001\t\005\t\025!\003@\003A\031\b.\036;e_^tG+[7f_V$\b\005C\003F\001\021\005a)\001\004=S:LGO\020\013\b\017\"K%j\023'N!\tI\001\001C\003\016\t\002\007a\002C\003\025\t\002\007a\003C\003$\t\002\007Q\005C\003,\t\002\007Q\006C\0039\t\002\007\021\bC\003>\t\002\007qH\002\003P\001\021\001&a\007'buf,\0050Z2vi>\0248+\032:wS\016,G)\0327fO\006$XmE\002O#f\003\"AU,\016\003MS!\001V+\002\t1\fgn\032\006\002-\006!!.\031<b\023\tA6K\001\004PE*,7\r\036\t\003\023iK!a\027\002\003/\025CXmY;u_J\034VM\035<jG\026$U\r\\3hCR,\007\002C/O\005\003\005\013\021\0020\002\017\031\f7\r^8ssB\021\021bX\005\003A\n\021a#\022=fGV$xN]*feZL7-\032$bGR|'/\037\005\006\013:#\tA\031\013\003G\026\004\"\001\032(\016\003\001AQ!X1A\002yC\001b\032(\t\006\004%\t\001[\001\tKb,7-\036;peV\t\021\016\005\002k]6\t1N\003\0023Y*\021Q.V\001\005kRLG.\003\002pW\nyQ\t_3dkR|'oU3sm&\034W\r\003\005r\035\"\005\t\025)\003j\003%)\0070Z2vi>\024\b\005C\003t\035\022\005A/\001\003d_BLH#A2\t\017Y\004\001\031!C\005o\0069R\r_3dkR|'oU3sm&\034W\rR3mK\036\fG/Z\013\002G\"9\021\020\001a\001\n\023Q\030aG3yK\016,Ho\034:TKJ4\030nY3EK2,w-\031;f?\022*\027\017\006\002|}B\021\001\004`\005\003{f\021A!\0268ji\"9q\020_A\001\002\004\031\027a\001=%c!9\0211\001\001!B\023\031\027\001G3yK\016,Ho\034:TKJ4\030nY3EK2,w-\031;fA!\"\021\021AA\004!\rA\022\021B\005\004\003\027I\"\001\003<pY\006$\030\016\\3\t\017\005=\001\001\"\006\002\022\005yQ\r_3dkR|'oU3sm&\034W-F\001Z\021\035\031\001\001\"\005\005\003+!Ra_A\f\003OA\001\"!\007\002\024\001\007\0211D\001\te\026\034W-\033<feB!\021QDA\022\033\t\tyBC\002\002\"\021\tQ!Y2u_JLA!!\n\002 \tI\021i\031;pe\016+G\016\034\005\t\003S\t\031\0021\001\002,\005Q\021N\034<pG\006$\030n\0348\021\007%\ti#C\002\0020\t\021\001\"\0228wK2|\007/\032\005\t\003g\001A\021\003\003\0026\005q1/_:uK6$\025n\0359bi\016DG#B>\0028\005e\002\002CA\r\003c\001\r!a\007\t\021\005%\022\021\007a\001\003w\001B!!\020\002D5\021\021q\b\006\004\003\003\022\021AB:zg6\034x-\003\003\002F\005}\"!D*zgR,W.T3tg\006<W\r\003\005\002J\001!\t\002BA&\003-)\0070Z2vi\026$\026m]6\025\007m\fi\005\003\005\002*\005\035\003\031AA(!\rI\021\021K\005\004\003'\022!A\004+bg.LeN^8dCRLwN\034\005\t\003/\002A\021\003\003\002Z\005i1M]3bi\026l\025-\0337c_b$b!a\027\002b\005%\004cA\005\002^%\031\021q\f\002\003\0175\013\027\016\0342pq\"A\021\021EA+\001\004\t\031\007\005\003\002\036\005\025\024\002BA4\003?\021AaQ3mY\"A\0211NA+\001\004\ti'A\006nC&d'm\034=UsB,\007cA\005\002p%\031\021\021\017\002\003\0275\013\027\016\0342pqRK\b/\032\005\t\003k\002A\021\003\003\002x\005A1\017[;uI><h\016F\001|\021!\tY\b\001C)\t\005u\024\001\006:fO&\034H/\032:G_J,\0050Z2vi&|g\016\006\005\002\000\005\025\025\021RAG!\rA\022\021Q\005\004\003\007K\"a\002\"p_2,\027M\034\005\t\003\017\013I\b1\001\002\\\005!QNY8y\021!\tY)!\037A\002\005}\024A\0045bg6+7o]1hK\"Kg\016\036\005\t\003\037\013I\b1\001\002\000\005!\002.Y:TsN$X-\\'fgN\fw-\032%j]RD\001\"a%\001\005\004%\t%F\001\ti>\034FO]5oO\"9\021q\023\001!\002\0231\022!\003;p'R\024\030N\\4!\001")
/*     */ public class Dispatcher extends MessageDispatcher {
/*     */   private final String id;
/*     */   
/*     */   private final int throughput;
/*     */   
/*     */   private final Duration throughputDeadlineTime;
/*     */   
/*     */   private final FiniteDuration shutdownTimeout;
/*     */   
/*     */   private volatile LazyExecutorServiceDelegate executorServiceDelegate;
/*     */   
/*     */   private final String toString;
/*     */   
/*     */   public String id() {
/*  34 */     return this.id;
/*     */   }
/*     */   
/*     */   public Dispatcher(MessageDispatcherConfigurator _configurator, String id, int throughput, Duration throughputDeadlineTime, ExecutorServiceFactoryProvider executorServiceFactoryProvider, FiniteDuration shutdownTimeout) {
/*  39 */     super(_configurator);
/*  48 */     this.executorServiceDelegate = 
/*  49 */       new LazyExecutorServiceDelegate(this, executorServiceFactoryProvider.createExecutorServiceFactory(id, configurator().prerequisites().threadFactory()));
/* 136 */     this.toString = (new StringBuilder()).append(Logging$.MODULE$.simpleName(this)).append("[").append(id).append("]").toString();
/*     */   }
/*     */   
/*     */   public int throughput() {
/*     */     return this.throughput;
/*     */   }
/*     */   
/*     */   public Duration throughputDeadlineTime() {
/*     */     return this.throughputDeadlineTime;
/*     */   }
/*     */   
/*     */   public FiniteDuration shutdownTimeout() {
/*     */     return this.shutdownTimeout;
/*     */   }
/*     */   
/*     */   public class LazyExecutorServiceDelegate implements ExecutorServiceDelegate {
/*     */     private final ExecutorServiceFactory factory;
/*     */     
/*     */     private ExecutorService executor;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public void execute(Runnable command) {
/*     */       ExecutorServiceDelegate$class.execute(this, command);
/*     */     }
/*     */     
/*     */     public void shutdown() {
/*     */       ExecutorServiceDelegate$class.shutdown(this);
/*     */     }
/*     */     
/*     */     public List<Runnable> shutdownNow() {
/*     */       return ExecutorServiceDelegate$class.shutdownNow(this);
/*     */     }
/*     */     
/*     */     public boolean isShutdown() {
/*     */       return ExecutorServiceDelegate$class.isShutdown(this);
/*     */     }
/*     */     
/*     */     public boolean isTerminated() {
/*     */       return ExecutorServiceDelegate$class.isTerminated(this);
/*     */     }
/*     */     
/*     */     public boolean awaitTermination(long l, TimeUnit timeUnit) {
/*     */       return ExecutorServiceDelegate$class.awaitTermination(this, l, timeUnit);
/*     */     }
/*     */     
/*     */     public <T> Future<T> submit(Callable callable) {
/*     */       return ExecutorServiceDelegate$class.submit(this, callable);
/*     */     }
/*     */     
/*     */     public <T> Future<T> submit(Runnable runnable, Object t) {
/*     */       return ExecutorServiceDelegate$class.submit(this, runnable, t);
/*     */     }
/*     */     
/*     */     public Future<?> submit(Runnable runnable) {
/*     */       return ExecutorServiceDelegate$class.submit(this, runnable);
/*     */     }
/*     */     
/*     */     public <T> List<Future<T>> invokeAll(Collection callables) {
/*     */       return ExecutorServiceDelegate$class.invokeAll(this, callables);
/*     */     }
/*     */     
/*     */     public <T> List<Future<T>> invokeAll(Collection callables, long l, TimeUnit timeUnit) {
/*     */       return ExecutorServiceDelegate$class.invokeAll(this, callables, l, timeUnit);
/*     */     }
/*     */     
/*     */     public <T> T invokeAny(Collection callables) {
/*     */       return (T)ExecutorServiceDelegate$class.invokeAny(this, callables);
/*     */     }
/*     */     
/*     */     public <T> T invokeAny(Collection callables, long l, TimeUnit timeUnit) {
/*     */       return (T)ExecutorServiceDelegate$class.invokeAny(this, callables, l, timeUnit);
/*     */     }
/*     */     
/*     */     public LazyExecutorServiceDelegate(Dispatcher $outer, ExecutorServiceFactory factory) {
/*     */       ExecutorServiceDelegate$class.$init$(this);
/*     */     }
/*     */     
/*     */     private ExecutorService executor$lzycompute() {
/*     */       synchronized (this) {
/*     */         if (!this.bitmap$0) {
/*     */           this.executor = this.factory.createExecutorService();
/*     */           this.bitmap$0 = true;
/*     */         } 
/*     */         return this.executor;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ExecutorService executor() {
/*     */       return this.bitmap$0 ? this.executor : executor$lzycompute();
/*     */     }
/*     */     
/*     */     public LazyExecutorServiceDelegate copy() {
/*     */       return new LazyExecutorServiceDelegate(akka$dispatch$Dispatcher$LazyExecutorServiceDelegate$$$outer(), this.factory);
/*     */     }
/*     */   }
/*     */   
/*     */   private LazyExecutorServiceDelegate executorServiceDelegate() {
/*     */     return this.executorServiceDelegate;
/*     */   }
/*     */   
/*     */   private void executorServiceDelegate_$eq(LazyExecutorServiceDelegate x$1) {
/*     */     this.executorServiceDelegate = x$1;
/*     */   }
/*     */   
/*     */   public final ExecutorServiceDelegate executorService() {
/*     */     return executorServiceDelegate();
/*     */   }
/*     */   
/*     */   public void dispatch(ActorCell receiver, Envelope invocation) {
/*     */     Mailbox mbox = receiver.mailbox();
/*     */     mbox.enqueue((ActorRef)receiver.self(), invocation);
/*     */     registerForExecution(mbox, true, false);
/*     */   }
/*     */   
/*     */   public void systemDispatch(ActorCell receiver, SystemMessage invocation) {
/*     */     Mailbox mbox = receiver.mailbox();
/*     */     mbox.systemEnqueue((ActorRef)receiver.self(), invocation);
/*     */     registerForExecution(mbox, false, true);
/*     */   }
/*     */   
/*     */   public void executeTask(TaskInvocation invocation) {
/*     */     try {
/*     */       executorService().execute(invocation);
/*     */     } catch (RejectedExecutionException rejectedExecutionException) {
/*     */       try {
/*     */         executorService().execute(invocation);
/*     */         return;
/*     */       } catch (RejectedExecutionException rejectedExecutionException1) {
/*     */         eventStream().publish(new Logging.Error(rejectedExecutionException, getClass().getName(), getClass(), "executeTask was rejected twice!"));
/*     */         throw rejectedExecutionException1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Mailbox createMailbox(Cell actor, MailboxType mailboxType) {
/*     */     return new Dispatcher$$anon$1(this, actor, mailboxType);
/*     */   }
/*     */   
/*     */   public class Dispatcher$$anon$1 extends Mailbox implements DefaultSystemMessageQueue {
/*     */     public final void systemEnqueue(ActorRef receiver, SystemMessage message) {
/*     */       DefaultSystemMessageQueue$class.systemEnqueue(this, receiver, message);
/*     */     }
/*     */     
/*     */     public final SystemMessage systemDrain(SystemMessage newContents) {
/*     */       return DefaultSystemMessageQueue$class.systemDrain(this, newContents);
/*     */     }
/*     */     
/*     */     public boolean hasSystemMessages() {
/*     */       return DefaultSystemMessageQueue$class.hasSystemMessages(this);
/*     */     }
/*     */     
/*     */     public Dispatcher$$anon$1(Dispatcher $outer, Cell actor$1, MailboxType mailboxType$1) {
/*     */       super(mailboxType$1.create((Option<ActorRef>)new Some(actor$1.self()), (Option<ActorSystem>)new Some(actor$1.system())));
/*     */       DefaultSystemMessageQueue$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public void shutdown() {
/*     */     LazyExecutorServiceDelegate newDelegate = executorServiceDelegate().copy();
/*     */     synchronized (this) {
/*     */       LazyExecutorServiceDelegate service = executorServiceDelegate();
/*     */       executorServiceDelegate_$eq(newDelegate);
/*     */       LazyExecutorServiceDelegate lazyExecutorServiceDelegate1 = service;
/*     */       LazyExecutorServiceDelegate es = lazyExecutorServiceDelegate1;
/*     */       es.shutdown();
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean registerForExecution(Mailbox mbox, boolean hasMessageHint, boolean hasSystemMessageHint) {
/*     */     if (mbox.canBeScheduledForExecution(hasMessageHint, hasSystemMessageHint)) {
/*     */       if (mbox.setAsScheduled()) {
/*     */         try {
/*     */           executorService().execute(mbox);
/*     */         } catch (RejectedExecutionException rejectedExecutionException) {
/*     */           try {
/*     */             executorService().execute(mbox);
/*     */             return true;
/*     */           } catch (RejectedExecutionException rejectedExecutionException1) {
/*     */             mbox.setAsIdle();
/*     */             eventStream().publish(new Logging.Error(rejectedExecutionException1, getClass().getName(), getClass(), "registerForExecution was rejected twice!"));
/*     */             throw rejectedExecutionException1;
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */       
/*     */       } 
/*     */     } else {
/*     */     
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 136 */     return this.toString;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Dispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */