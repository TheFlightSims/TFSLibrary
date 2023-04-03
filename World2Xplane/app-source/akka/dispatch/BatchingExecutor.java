/*     */ package akka.dispatch;
/*     */ 
/*     */ import java.util.concurrent.Executor;
/*     */ import scala.Function0;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.concurrent.BlockContext;
/*     */ import scala.concurrent.BlockContext$;
/*     */ import scala.concurrent.CanAwait;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025a\001C\001\003!\003\r\t\001\002\004\003!\t\013Go\0315j]\036,\0050Z2vi>\024(BA\002\005\003!!\027n\0359bi\016D'\"A\003\002\t\005\\7.Y\n\004\001\035y\001C\001\005\016\033\005I!B\001\006\f\003\021a\027M\\4\013\0031\tAA[1wC&\021a\"\003\002\007\037\nTWm\031;\021\005A)R\"A\t\013\005I\031\022AC2p]\016,(O]3oi*\021AcC\001\005kRLG.\003\002\027#\tAQ\t_3dkR|'\017C\003\031\001\021\005!$\001\004%S:LG\017J\002\001)\005Y\002C\001\017 \033\005i\"\"\001\020\002\013M\034\027\r\\1\n\005\001j\"\001B+oSRDqA\t\001C\002\023%1%A\006`i\006\0348n\035'pG\006dW#\001\023\021\007!)s%\003\002'\023\tYA\013\033:fC\022dunY1m!\rA\003g\r\b\003S9r!AK\027\016\003-R!\001L\r\002\rq\022xn\034;?\023\005q\022BA\030\036\003\035\001\030mY6bO\026L!!\r\032\003\t1K7\017\036\006\003_u\001\"\001\003\033\n\005UJ!\001\003*v]:\f'\r\\3\t\r]\002\001\025!\003%\0031yF/Y:lg2{7-\0317!\r\021I\004\001\002\036\003\013\t\013Go\0315\024\ta:1g\017\t\003yyj\021!\020\006\003%uI!aP\037\003\031\tcwnY6D_:$X\r\037;\t\021\005C$Q1A\005\002\t\013q!\0338ji&\fG.F\001(\021!!\005H!A!\002\0239\023\001C5oSRL\027\r\034\021\t\013\031CD\021A$\002\rqJg.\033;?)\tA%\n\005\002Jq5\t\001\001C\003B\013\002\007q\005C\005Mq\001\007\t\031!C\005\033\006\021\002/\031:f]R\024En\\2l\007>tG/\032=u+\005Y\004\"C(9\001\004\005\r\021\"\003Q\003Y\001\030M]3oi\ncwnY6D_:$X\r\037;`I\025\fHCA\016R\021\035\021f*!AA\002m\n1\001\037\0232\021\031!\006\b)Q\005w\005\031\002/\031:f]R\024En\\2l\007>tG/\032=uA!)a\013\017C!5\005\031!/\0368\t\013aCD\021I-\002\017\tdwnY6P]V\021!L\030\013\00372$\"\001X4\021\005usF\002\001\003\006?^\023\r\001\031\002\002)F\021\021\r\032\t\0039\tL!aY\017\003\0179{G\017[5oOB\021A$Z\005\003Mv\0211!\0218z\021\025Aw\013q\001j\003)\001XM]7jgNLwN\034\t\003y)L!a[\037\003\021\r\013g.Q<bSRDa!\\,\005\002\004q\027!\002;ik:\\\007c\001\017p9&\021\001/\b\002\ty\tLh.Y7f}!)!\017\001D\tg\006\001RO\0342bi\016DW\rZ#yK\016,H/\032\013\0037QDQ!^9A\002M\n\021A\035\005\006o\002!\t\005_\001\bKb,7-\036;f)\tY\022\020C\003{m\002\0071'\001\005sk:t\027M\0317f\021\025a\b\001\"\001~\003%\021\027\r^2iC\ndW\rF\002\003\007\001\"\001H@\n\007\005\005QDA\004C_>dW-\0318\t\013i\\\b\031A\032")
/*     */ public interface BatchingExecutor extends Executor {
/*     */   void akka$dispatch$BatchingExecutor$_setter_$akka$dispatch$BatchingExecutor$$_tasksLocal_$eq(ThreadLocal paramThreadLocal);
/*     */   
/*     */   ThreadLocal<List<Runnable>> akka$dispatch$BatchingExecutor$$_tasksLocal();
/*     */   
/*     */   void unbatchedExecute(Runnable paramRunnable);
/*     */   
/*     */   void execute(Runnable paramRunnable);
/*     */   
/*     */   boolean batchable(Runnable paramRunnable);
/*     */   
/*     */   public class Batch implements Runnable, BlockContext {
/*     */     private final List<Runnable> initial;
/*     */     
/*     */     private BlockContext akka$dispatch$BatchingExecutor$Batch$$parentBlockContext;
/*     */     
/*     */     public List<Runnable> initial() {
/*  51 */       return this.initial;
/*     */     }
/*     */     
/*     */     public Batch(BatchingExecutor $outer, List<Runnable> initial) {}
/*     */     
/*     */     private BlockContext akka$dispatch$BatchingExecutor$Batch$$parentBlockContext() {
/*  52 */       return this.akka$dispatch$BatchingExecutor$Batch$$parentBlockContext;
/*     */     }
/*     */     
/*     */     public void akka$dispatch$BatchingExecutor$Batch$$parentBlockContext_$eq(BlockContext x$1) {
/*  52 */       this.akka$dispatch$BatchingExecutor$Batch$$parentBlockContext = x$1;
/*     */     }
/*     */     
/*     */     public void run() {
/*  55 */       Predef$.MODULE$.require((akka$dispatch$BatchingExecutor$Batch$$$outer().akka$dispatch$BatchingExecutor$$_tasksLocal().get() == null));
/*  57 */       BlockContext prevBlockContext = BlockContext$.MODULE$.current();
/*  58 */       BlockContext$.MODULE$.withBlockContext(this, 
/*  59 */           (Function0)new BatchingExecutor$Batch$$anonfun$run$1(this, prevBlockContext));
/*     */     }
/*     */     
/*     */     public class BatchingExecutor$Batch$$anonfun$run$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final BlockContext prevBlockContext$1;
/*     */       
/*     */       public final void apply() {
/*  59 */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public BatchingExecutor$Batch$$anonfun$run$1(BatchingExecutor.Batch $outer, BlockContext prevBlockContext$1) {}
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         try {
/*  60 */           this.$outer.akka$dispatch$BatchingExecutor$Batch$$parentBlockContext_$eq(this.prevBlockContext$1);
/*  82 */           processBatch$1(this.$outer.initial());
/*     */           return;
/*     */         } finally {
/*  84 */           this.$outer.akka$dispatch$BatchingExecutor$Batch$$$outer().akka$dispatch$BatchingExecutor$$_tasksLocal().remove();
/*  85 */           null;
/*  85 */           this.$outer.akka$dispatch$BatchingExecutor$Batch$$parentBlockContext_$eq(null);
/*     */         } 
/*     */       }
/*     */       
/*     */       private final void processBatch$1(List batch) {
/*     */         // Byte code:
/*     */         //   0: aload_1
/*     */         //   1: astore_3
/*     */         //   2: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */         //   5: aload_3
/*     */         //   6: astore #4
/*     */         //   8: dup
/*     */         //   9: ifnonnull -> 21
/*     */         //   12: pop
/*     */         //   13: aload #4
/*     */         //   15: ifnull -> 29
/*     */         //   18: goto -> 39
/*     */         //   21: aload #4
/*     */         //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   26: ifeq -> 39
/*     */         //   29: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   32: astore #5
/*     */         //   34: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   37: pop
/*     */         //   38: return
/*     */         //   39: aload_3
/*     */         //   40: instanceof scala/collection/immutable/$colon$colon
/*     */         //   43: ifeq -> 115
/*     */         //   46: aload_3
/*     */         //   47: checkcast scala/collection/immutable/$colon$colon
/*     */         //   50: astore #6
/*     */         //   52: aload #6
/*     */         //   54: invokevirtual hd$1 : ()Ljava/lang/Object;
/*     */         //   57: checkcast java/lang/Runnable
/*     */         //   60: astore #7
/*     */         //   62: aload #6
/*     */         //   64: invokevirtual tl$1 : ()Lscala/collection/immutable/List;
/*     */         //   67: astore #8
/*     */         //   69: aload_0
/*     */         //   70: getfield $outer : Lakka/dispatch/BatchingExecutor$Batch;
/*     */         //   73: invokevirtual akka$dispatch$BatchingExecutor$Batch$$$outer : ()Lakka/dispatch/BatchingExecutor;
/*     */         //   76: invokeinterface akka$dispatch$BatchingExecutor$$_tasksLocal : ()Ljava/lang/ThreadLocal;
/*     */         //   81: aload #8
/*     */         //   83: invokevirtual set : (Ljava/lang/Object;)V
/*     */         //   86: aload #7
/*     */         //   88: invokeinterface run : ()V
/*     */         //   93: aload_0
/*     */         //   94: getfield $outer : Lakka/dispatch/BatchingExecutor$Batch;
/*     */         //   97: invokevirtual akka$dispatch$BatchingExecutor$Batch$$$outer : ()Lakka/dispatch/BatchingExecutor;
/*     */         //   100: invokeinterface akka$dispatch$BatchingExecutor$$_tasksLocal : ()Ljava/lang/ThreadLocal;
/*     */         //   105: invokevirtual get : ()Ljava/lang/Object;
/*     */         //   108: checkcast scala/collection/immutable/List
/*     */         //   111: astore_1
/*     */         //   112: goto -> 0
/*     */         //   115: new scala/MatchError
/*     */         //   118: dup
/*     */         //   119: aload_3
/*     */         //   120: invokespecial <init> : (Ljava/lang/Object;)V
/*     */         //   123: athrow
/*     */         //   124: astore #9
/*     */         //   126: aload_0
/*     */         //   127: getfield $outer : Lakka/dispatch/BatchingExecutor$Batch;
/*     */         //   130: invokevirtual akka$dispatch$BatchingExecutor$Batch$$$outer : ()Lakka/dispatch/BatchingExecutor;
/*     */         //   133: invokeinterface akka$dispatch$BatchingExecutor$$_tasksLocal : ()Ljava/lang/ThreadLocal;
/*     */         //   138: invokevirtual get : ()Ljava/lang/Object;
/*     */         //   141: checkcast scala/collection/immutable/List
/*     */         //   144: astore #10
/*     */         //   146: aload_0
/*     */         //   147: getfield $outer : Lakka/dispatch/BatchingExecutor$Batch;
/*     */         //   150: invokevirtual akka$dispatch$BatchingExecutor$Batch$$$outer : ()Lakka/dispatch/BatchingExecutor;
/*     */         //   153: invokeinterface akka$dispatch$BatchingExecutor$$_tasksLocal : ()Ljava/lang/ThreadLocal;
/*     */         //   158: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */         //   161: invokevirtual set : (Ljava/lang/Object;)V
/*     */         //   164: aload_0
/*     */         //   165: getfield $outer : Lakka/dispatch/BatchingExecutor$Batch;
/*     */         //   168: invokevirtual akka$dispatch$BatchingExecutor$Batch$$$outer : ()Lakka/dispatch/BatchingExecutor;
/*     */         //   171: new akka/dispatch/BatchingExecutor$Batch
/*     */         //   174: dup
/*     */         //   175: aload_0
/*     */         //   176: getfield $outer : Lakka/dispatch/BatchingExecutor$Batch;
/*     */         //   179: invokevirtual akka$dispatch$BatchingExecutor$Batch$$$outer : ()Lakka/dispatch/BatchingExecutor;
/*     */         //   182: aload #10
/*     */         //   184: invokespecial <init> : (Lakka/dispatch/BatchingExecutor;Lscala/collection/immutable/List;)V
/*     */         //   187: invokeinterface unbatchedExecute : (Ljava/lang/Runnable;)V
/*     */         //   192: aload #9
/*     */         //   194: athrow
/*     */         // Line number table:
/*     */         //   Java source line number -> byte code offset
/*     */         //   #62	-> 0
/*     */         //   #63	-> 2
/*     */         //   #62	-> 34
/*     */         //   #64	-> 39
/*     */         //   #65	-> 69
/*     */         //   #67	-> 86
/*     */         //   #79	-> 93
/*     */         //   #62	-> 115
/*     */         //   #69	-> 124
/*     */         //   #66	-> 124
/*     */         //   #74	-> 126
/*     */         //   #75	-> 146
/*     */         //   #76	-> 164
/*     */         //   #77	-> 192
/*     */         // Local variable table:
/*     */         //   start	length	slot	name	descriptor
/*     */         //   0	195	0	this	Lakka/dispatch/BatchingExecutor$Batch$$anonfun$run$1;
/*     */         //   0	195	1	batch	Lscala/collection/immutable/List;
/*     */         //   62	133	7	head	Ljava/lang/Runnable;
/*     */         //   69	126	8	tail	Lscala/collection/immutable/List;
/*     */         //   146	49	10	remaining	Lscala/collection/immutable/List;
/*     */         // Exception table:
/*     */         //   from	to	target	type
/*     */         //   86	93	124	finally
/*     */       }
/*     */     }
/*     */     
/*     */     public <T> T blockOn(Function0 thunk, CanAwait permission) {
/*  93 */       List<Runnable> tasks = akka$dispatch$BatchingExecutor$Batch$$$outer().akka$dispatch$BatchingExecutor$$_tasksLocal().get();
/*  94 */       akka$dispatch$BatchingExecutor$Batch$$$outer().akka$dispatch$BatchingExecutor$$_tasksLocal().set(Nil$.MODULE$);
/*  95 */       if (tasks != null && tasks.nonEmpty())
/*  96 */         akka$dispatch$BatchingExecutor$Batch$$$outer().unbatchedExecute(new Batch(akka$dispatch$BatchingExecutor$Batch$$$outer(), tasks)); 
/* 100 */       Predef$.MODULE$.require((akka$dispatch$BatchingExecutor$Batch$$parentBlockContext() != null));
/* 101 */       return (T)akka$dispatch$BatchingExecutor$Batch$$parentBlockContext().blockOn(thunk, permission);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BatchingExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */