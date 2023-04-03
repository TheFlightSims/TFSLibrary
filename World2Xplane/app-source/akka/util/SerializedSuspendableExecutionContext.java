/*    */ package akka.util;
/*    */ 
/*    */ import akka.dispatch.AbstractNodeQueue;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ import scala.Function0;
/*    */ import scala.MatchError;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.StringContext;
/*    */ import scala.collection.Seq;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005tAB\001\003\021\003!a!A\023TKJL\027\r\\5{K\022\034Vo\0359f]\022\f'\r\\3Fq\026\034W\017^5p]\016{g\016^3yi*\0211\001B\001\005kRLGNC\001\006\003\021\t7n[1\021\005\035AQ\"\001\002\007\r%\021\001\022\001\003\013\005\025\032VM]5bY&TX\rZ*vgB,g\016Z1cY\026,\0050Z2vi&|gnQ8oi\026DHoE\002\t\027E\001\"\001D\b\016\0035Q\021AD\001\006g\016\fG.Y\005\003!5\021a!\0218z%\0264\007C\001\007\023\023\t\031RB\001\007TKJL\027\r\\5{C\ndW\rC\003\026\021\021\005q#\001\004=S:LGOP\002\001)\0051\001bB\r\t\005\004%)AG\001\004\037\0324W#A\016\020\003qi\022\001\001\005\007=!\001\013QB\016\002\t=3g\r\t\005\bA!\021\r\021\"\002\"\003\tye.F\001#\037\005\031S$A\001\t\r\025B\001\025!\004#\003\rye\016\t\005\bO!\021\r\021\"\002)\003%\031Vo\0359f]\022,G-F\001*\037\005QS$\001\002\t\r1B\001\025!\004*\003)\031Vo\0359f]\022,G\r\t\005\006]!!\taL\001\006CB\004H.\037\013\004a\005MCcA\031\002RA\021qA\r\004\006\023\t\021AaM\n\005eQR$\tE\0026qij\021A\016\006\003o\021\t\001\002Z5ta\006$8\r[\005\003sY\022\021#\0212tiJ\f7\r\036(pI\026\fV/Z;f!\tY\004)D\001=\025\tid(\001\003mC:<'\"A \002\t)\fg/Y\005\003\003r\022\001BU;o]\006\024G.\032\t\003\007\032k\021\001\022\006\003\0136\t!bY8oGV\024(/\0328u\023\t9EI\001\tFq\026\034W\017^5p]\016{g\016^3yi\"A\021J\rB\001B\003%!*\001\006uQJ|Wo\0325qkR\004\"\001D&\n\0051k!aA%oi\"AaJ\rBC\002\023\005q*A\004d_:$X\r\037;\026\003\tC\001\"\025\032\003\002\003\006IAQ\001\tG>tG/\032=uA!)QC\rC\001'R\021AK\026\013\003cUCQA\024*A\002\tCQ!\023*A\002)Cq\001\027\032C\002\0235\021,A\003ti\006$X-F\001[!\tY\006-D\001]\025\tif,\001\004bi>l\027n\031\006\003\013~S!a\001 \n\005\005d&!D!u_6L7-\0238uK\036,'\017\003\004de\001\006iAW\001\007gR\fG/\032\021\t\013\025\024DQ\0024\002\021\005$Gm\025;bi\026$\"a\0326\021\0051A\027BA5\016\005\035\021un\0347fC:DQa\0333A\002)\013\001B\\3x'R\fG/\032\025\003I6\004\"A\\9\016\003=T!\001]\007\002\025\005tgn\034;bi&|g.\003\002s_\n9A/Y5me\026\034\007\"\002;3\t\033)\030\001\003:f[N#\030\r^3\025\005YL\bC\001\007x\023\tAXB\001\003V]&$\b\"\002>t\001\004Q\025\001C8mIN#\030\r^3)\005Ml\007\"B?3\t\013q\030A\002:fgVlW\rF\001w\021\031\t\tA\rC\003}\00691/^:qK:$\007BBA\003e\021\025a0A\002sk:Da!!\0033\t\013q\030AB1ui\006\034\007\016C\004\002\016I\")%a\004\002\017\025DXmY;uKR\031a/!\005\t\017\005M\0211\002a\001u\005!A/Y:l\021\035\t9B\rC#\0033\tQB]3q_J$h)Y5mkJ,Gc\001<\002\034!A\021QDA\013\001\004\ty\"A\001u!\021\t\t#!\r\017\t\005\r\022Q\006\b\005\003K\tY#\004\002\002()\031\021\021\006\f\002\rq\022xn\034;?\023\005q\021bAA\030\033\0059\001/Y2lC\036,\027\002BA\032\003k\021\021\002\0265s_^\f'\r\\3\013\007\005=R\002C\004\002:I\")!a\017\002\tML'0\032\013\002\025\"9\021q\b\032\005F\005\005\023\001\003;p'R\024\030N\\4\025\005\005\r\003\003BA#\003\027r1\001DA$\023\r\tI%D\001\007!J,G-\0324\n\t\0055\023q\n\002\007'R\024\030N\\4\013\007\005%S\002C\003O[\001\017!\tC\003J[\001\007!\nC\005\002X!\t\t\021\"\003\002Z\005Y!/Z1e%\026\034x\016\034<f)\t\tY\006E\002<\003;J1!a\030=\005\031y%M[3di\002")
/*    */ public final class SerializedSuspendableExecutionContext extends AbstractNodeQueue<Runnable> implements Runnable, ExecutionContext {
/*    */   public final int akka$util$SerializedSuspendableExecutionContext$$throughput;
/*    */   
/*    */   private final ExecutionContext context;
/*    */   
/*    */   private final AtomicInteger state;
/*    */   
/*    */   public ExecutionContext prepare() {
/* 34 */     return ExecutionContext.class.prepare(this);
/*    */   }
/*    */   
/*    */   public ExecutionContext context() {
/* 34 */     return this.context;
/*    */   }
/*    */   
/*    */   public SerializedSuspendableExecutionContext(int throughput, ExecutionContext context) {
/* 34 */     ExecutionContext.class.$init$(this);
/* 37 */     Predef$.MODULE$.require((throughput > 0), (Function0)new SerializedSuspendableExecutionContext$$anonfun$1(this));
/* 39 */     this.state = new AtomicInteger(0);
/*    */   }
/*    */   
/*    */   public class SerializedSuspendableExecutionContext$$anonfun$1 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/*    */       (new String[2])[0] = "SerializedSuspendableExecutionContext.throughput must be greater than 0 but was ";
/*    */       (new String[2])[1] = "";
/*    */       return (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(this.$outer.akka$util$SerializedSuspendableExecutionContext$$throughput) }));
/*    */     }
/*    */     
/*    */     public SerializedSuspendableExecutionContext$$anonfun$1(SerializedSuspendableExecutionContext $outer) {}
/*    */   }
/*    */   
/*    */   private final AtomicInteger state() {
/* 39 */     return this.state;
/*    */   }
/*    */   
/*    */   private final boolean addState(int newState) {
/*    */     while (true) {
/* 41 */       int c = state().get();
/* 42 */       if (state().compareAndSet(c, c | newState))
/*    */         return true; 
/* 42 */       newState = newState;
/*    */     } 
/*    */   }
/*    */   
/*    */   private final void remState(int oldState) {
/*    */     while (true) {
/* 45 */       int c = state().get();
/* 46 */       if (state().compareAndSet(c, c & (oldState ^ 0xFFFFFFFF))) {
/* 46 */         attach();
/*    */         return;
/*    */       } 
/* 46 */       oldState = oldState;
/*    */     } 
/*    */   }
/*    */   
/*    */   public final void resume() {
/* 54 */     remState(2);
/*    */   }
/*    */   
/*    */   public final void suspend() {
/* 60 */     addState(2);
/*    */   }
/*    */   
/*    */   private final void run$1(int done) {
/*    */     // Byte code:
/*    */     //   0: iload_1
/*    */     //   1: aload_0
/*    */     //   2: getfield akka$util$SerializedSuspendableExecutionContext$$throughput : I
/*    */     //   5: if_icmplt -> 71
/*    */     //   8: goto -> 114
/*    */     //   11: astore #5
/*    */     //   13: aload #5
/*    */     //   15: astore #6
/*    */     //   17: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*    */     //   20: aload #6
/*    */     //   22: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*    */     //   25: astore #7
/*    */     //   27: aload #7
/*    */     //   29: invokevirtual isEmpty : ()Z
/*    */     //   32: ifeq -> 38
/*    */     //   35: aload #5
/*    */     //   37: athrow
/*    */     //   38: aload #7
/*    */     //   40: invokevirtual get : ()Ljava/lang/Object;
/*    */     //   43: checkcast java/lang/Throwable
/*    */     //   46: astore #8
/*    */     //   48: aload_0
/*    */     //   49: invokevirtual context : ()Lscala/concurrent/ExecutionContext;
/*    */     //   52: aload #8
/*    */     //   54: invokeinterface reportFailure : (Ljava/lang/Throwable;)V
/*    */     //   59: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */     //   62: astore #9
/*    */     //   64: iload_1
/*    */     //   65: iconst_1
/*    */     //   66: iadd
/*    */     //   67: istore_1
/*    */     //   68: goto -> 0
/*    */     //   71: aload_0
/*    */     //   72: invokespecial state : ()Ljava/util/concurrent/atomic/AtomicInteger;
/*    */     //   75: invokevirtual get : ()I
/*    */     //   78: iconst_1
/*    */     //   79: if_icmpne -> 114
/*    */     //   82: aload_0
/*    */     //   83: invokevirtual poll : ()Ljava/lang/Object;
/*    */     //   86: checkcast java/lang/Runnable
/*    */     //   89: astore_3
/*    */     //   90: aload_3
/*    */     //   91: ifnonnull -> 105
/*    */     //   94: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */     //   97: astore #4
/*    */     //   99: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */     //   102: goto -> 117
/*    */     //   105: aload_3
/*    */     //   106: invokeinterface run : ()V
/*    */     //   111: goto -> 64
/*    */     //   114: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */     //   117: pop
/*    */     //   118: return
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #64	-> 0
/*    */     //   #68	-> 11
/*    */     //   #65	-> 38
/*    */     //   #68	-> 40
/*    */     //   #69	-> 64
/*    */     //   #64	-> 71
/*    */     //   #65	-> 82
/*    */     //   #66	-> 90
/*    */     //   #65	-> 99
/*    */     //   #68	-> 105
/*    */     //   #64	-> 114
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	119	0	this	Lakka/util/SerializedSuspendableExecutionContext;
/*    */     //   0	119	1	done	I
/*    */     //   48	71	8	t	Ljava/lang/Throwable;
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   105	114	11	finally
/*    */   }
/*    */   
/*    */   public final void run() {
/*    */     try {
/* 72 */       run$1(0);
/*    */       return;
/*    */     } finally {
/* 72 */       remState(1);
/*    */     } 
/*    */   }
/*    */   
/*    */   public final void attach() {
/* 75 */     if (!isEmpty() && state().compareAndSet(0, 1))
/* 75 */       context().execute(this); 
/*    */   }
/*    */   
/*    */   public final void execute(Runnable task) {
/*    */     try {
/* 76 */       add(task);
/*    */       return;
/*    */     } finally {
/* 76 */       attach();
/*    */     } 
/*    */   }
/*    */   
/*    */   public final void reportFailure(Throwable t) {
/* 77 */     context().reportFailure(t);
/*    */   }
/*    */   
/*    */   public final int size() {
/* 83 */     return count();
/*    */   }
/*    */   
/*    */   public final String toString() {
/* 85 */     int i = state().get();
/* 85 */     switch (i) {
/*    */       default:
/* 85 */         throw new MatchError(BoxesRunTime.boxToInteger(i));
/*    */       case 3:
/*    */       
/*    */       case 2:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 85 */     return 
/* 86 */       "Off";
/*    */   }
/*    */   
/*    */   public static SerializedSuspendableExecutionContext apply(int paramInt, ExecutionContext paramExecutionContext) {
/*    */     return SerializedSuspendableExecutionContext$.MODULE$.apply(paramInt, paramExecutionContext);
/*    */   }
/*    */   
/*    */   public static int Suspended() {
/*    */     return SerializedSuspendableExecutionContext$.MODULE$.Suspended();
/*    */   }
/*    */   
/*    */   public static int On() {
/*    */     return SerializedSuspendableExecutionContext$.MODULE$.On();
/*    */   }
/*    */   
/*    */   public static int Off() {
/*    */     return SerializedSuspendableExecutionContext$.MODULE$.Off();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\SerializedSuspendableExecutionContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */