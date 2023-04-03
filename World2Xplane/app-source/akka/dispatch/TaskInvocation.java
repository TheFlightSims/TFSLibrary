/*    */ package akka.dispatch;
/*    */ 
/*    */ import akka.event.EventStream;
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\035d\001B\001\003\005\036\021a\002V1tW&sgo\\2bi&|gN\003\002\004\t\005AA-[:qCR\034\007NC\001\006\003\021\t7n[1\004\001M)\001\001\003\t\0255A\021\021BD\007\002\025)\0211\002D\001\005Y\006twMC\001\016\003\021Q\027M^1\n\005=Q!AB(cU\026\034G\017\005\002\022%5\t!!\003\002\024\005\tI!)\031;dQ\006\024G.\032\t\003+ai\021A\006\006\002/\005)1oY1mC&\021\021D\006\002\b!J|G-^2u!\t)2$\003\002\035-\ta1+\032:jC2L'0\0312mK\"Aa\004\001BK\002\023\005q$A\006fm\026tGo\025;sK\006lW#\001\021\021\005\005\"S\"\001\022\013\005\r\"\021!B3wK:$\030BA\023#\005-)e/\0328u'R\024X-Y7\t\021\035\002!\021#Q\001\n\001\nA\"\032<f]R\034FO]3b[\002B\001\"\013\001\003\026\004%\tAK\001\teVtg.\0312mKV\t1\006\005\002\nY%\021QF\003\002\t%Vtg.\0312mK\"Aq\006\001B\tB\003%1&A\005sk:t\027M\0317fA!A\021\007\001BK\002\023\005!'A\004dY\026\fg.\0369\026\003M\0022!\006\0337\023\t)dCA\005Gk:\034G/[8oaA\021QcN\005\003qY\021A!\0268ji\"A!\b\001B\tB\003%1'\001\005dY\026\fg.\0369!\021\025a\004\001\"\001>\003\031a\024N\\5u}Q!ah\020!B!\t\t\002\001C\003\037w\001\007\001\005C\003*w\001\0071\006C\0032w\001\0071\007C\003D\001\021\025C)A\006jg\n\013Go\0315bE2,W#A#\021\005U1\025BA$\027\005\035\021un\0347fC:DQ!\023\001\005\002)\0131A];o)\0051\004b\002'\001\003\003%\t!T\001\005G>\004\030\020\006\003?\035>\003\006b\002\020L!\003\005\r\001\t\005\bS-\003\n\0211\001,\021\035\t4\n%AA\002MBqA\025\001\022\002\023\0051+\001\bd_BLH\005Z3gCVdG\017J\031\026\003QS#\001I+,\003Y\003\"a\026/\016\003aS!!\027.\002\023Ut7\r[3dW\026$'BA.\027\003)\tgN\\8uCRLwN\\\005\003;b\023\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035y\006!%A\005\002\001\fabY8qs\022\"WMZ1vYR$#'F\001bU\tYS\013C\004d\001E\005I\021\0013\002\035\r|\007/\037\023eK\032\fW\017\034;%gU\tQM\013\0024+\"9q\rAA\001\n\003B\027!\0049s_\022,8\r\036)sK\032L\0070F\001j!\tI!.\003\002l\025\t11\013\036:j]\036Dq!\034\001\002\002\023\005a.\001\007qe>$Wo\031;Be&$\0300F\001p!\t)\002/\003\002r-\t\031\021J\034;\t\017M\004\021\021!C\001i\006q\001O]8ek\016$X\t\\3nK:$HCA;y!\t)b/\003\002x-\t\031\021I\\=\t\017e\024\030\021!a\001_\006\031\001\020J\031\t\017m\004\021\021!C!y\006y\001O]8ek\016$\030\n^3sCR|'/F\001~!\021q\0301A;\016\003}T1!!\001\027\003)\031w\016\0347fGRLwN\\\005\004\003\013y(\001C%uKJ\fGo\034:\t\023\005%\001!!A\005\002\005-\021\001C2b]\026\013X/\0317\025\007\025\013i\001\003\005z\003\017\t\t\0211\001v\021%\t\t\002AA\001\n\003\n\031\"\001\005iCND7i\0343f)\005y\007\"CA\f\001\005\005I\021IA\r\003!!xn\025;sS:<G#A5\t\023\005u\001!!A\005B\005}\021AB3rk\006d7\017F\002F\003CA\001\"_A\016\003\003\005\r!^\004\n\003K\021\021\021!E\001\003O\ta\002V1tW&sgo\\2bi&|g\016E\002\022\003S1\001\"\001\002\002\002#\005\0211F\n\006\003S\tiC\007\t\t\003_\t)\004I\0264}5\021\021\021\007\006\004\003g1\022a\002:v]RLW.Z\005\005\003o\t\tDA\tBEN$(/Y2u\rVt7\r^5p]NBq\001PA\025\t\003\tY\004\006\002\002(!Q\021qCA\025\003\003%)%!\007\t\025\005\005\023\021FA\001\n\003\013\031%A\003baBd\027\020F\004?\003\013\n9%!\023\t\ry\ty\0041\001!\021\031I\023q\ba\001W!1\021'a\020A\002MB!\"!\024\002*\005\005I\021QA(\003\035)h.\0319qYf$B!!\025\002^A)Q#a\025\002X%\031\021Q\013\f\003\r=\003H/[8o!\031)\022\021\f\021,g%\031\0211\f\f\003\rQ+\b\017\\34\021%\ty&a\023\002\002\003\007a(A\002yIAB!\"a\031\002*\005\005I\021BA3\003-\021X-\0313SKN|GN^3\025\003!\001")
/*    */ public final class TaskInvocation implements Batchable, Product, Serializable {
/*    */   private final EventStream eventStream;
/*    */   
/*    */   private final Runnable runnable;
/*    */   
/*    */   private final Function0<BoxedUnit> cleanup;
/*    */   
/*    */   public EventStream eventStream() {
/* 33 */     return this.eventStream;
/*    */   }
/*    */   
/*    */   public Runnable runnable() {
/* 33 */     return this.runnable;
/*    */   }
/*    */   
/*    */   public Function0<BoxedUnit> cleanup() {
/* 33 */     return this.cleanup;
/*    */   }
/*    */   
/*    */   public TaskInvocation copy(EventStream eventStream, Runnable runnable, Function0<BoxedUnit> cleanup) {
/* 33 */     return new TaskInvocation(eventStream, runnable, cleanup);
/*    */   }
/*    */   
/*    */   public EventStream copy$default$1() {
/* 33 */     return eventStream();
/*    */   }
/*    */   
/*    */   public Runnable copy$default$2() {
/* 33 */     return runnable();
/*    */   }
/*    */   
/*    */   public Function0<BoxedUnit> copy$default$3() {
/* 33 */     return cleanup();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 33 */     return "TaskInvocation";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 33 */     return 3;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 33 */     int i = x$1;
/* 33 */     switch (i) {
/*    */       default:
/* 33 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 2:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 33 */     return eventStream();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 33 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 33 */     return x$1 instanceof TaskInvocation;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 33 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 33 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 135
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/dispatch/TaskInvocation
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 139
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/dispatch/TaskInvocation
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual eventStream : ()Lakka/event/EventStream;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual eventStream : ()Lakka/event/EventStream;
/*    */     //   40: astore #5
/*    */     //   42: dup
/*    */     //   43: ifnonnull -> 55
/*    */     //   46: pop
/*    */     //   47: aload #5
/*    */     //   49: ifnull -> 63
/*    */     //   52: goto -> 131
/*    */     //   55: aload #5
/*    */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   60: ifeq -> 131
/*    */     //   63: aload_0
/*    */     //   64: invokevirtual runnable : ()Ljava/lang/Runnable;
/*    */     //   67: aload #4
/*    */     //   69: invokevirtual runnable : ()Ljava/lang/Runnable;
/*    */     //   72: astore #6
/*    */     //   74: dup
/*    */     //   75: ifnonnull -> 87
/*    */     //   78: pop
/*    */     //   79: aload #6
/*    */     //   81: ifnull -> 95
/*    */     //   84: goto -> 131
/*    */     //   87: aload #6
/*    */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   92: ifeq -> 131
/*    */     //   95: aload_0
/*    */     //   96: invokevirtual cleanup : ()Lscala/Function0;
/*    */     //   99: aload #4
/*    */     //   101: invokevirtual cleanup : ()Lscala/Function0;
/*    */     //   104: astore #7
/*    */     //   106: dup
/*    */     //   107: ifnonnull -> 119
/*    */     //   110: pop
/*    */     //   111: aload #7
/*    */     //   113: ifnull -> 127
/*    */     //   116: goto -> 131
/*    */     //   119: aload #7
/*    */     //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   124: ifeq -> 131
/*    */     //   127: iconst_1
/*    */     //   128: goto -> 132
/*    */     //   131: iconst_0
/*    */     //   132: ifeq -> 139
/*    */     //   135: iconst_1
/*    */     //   136: goto -> 140
/*    */     //   139: iconst_0
/*    */     //   140: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #33	-> 0
/*    */     //   #63	-> 14
/*    */     //   #33	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	141	0	this	Lakka/dispatch/TaskInvocation;
/*    */     //   0	141	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public TaskInvocation(EventStream eventStream, Runnable runnable, Function0<BoxedUnit> cleanup) {
/* 33 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public final boolean isBatchable() {
/*    */     boolean bool;
/* 34 */     Runnable runnable = runnable();
/* 35 */     if (runnable instanceof Batchable) {
/* 35 */       Batchable batchable = (Batchable)runnable;
/* 35 */       bool = batchable.isBatchable();
/* 36 */     } else if (runnable instanceof scala.concurrent.OnCompleteRunnable) {
/* 36 */       bool = true;
/*    */     } else {
/* 37 */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public void run() {
/*    */     try {
/* 41 */       runnable().run();
/*    */     } finally {
/* 43 */       cleanup().apply$mcV$sp();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Function1<Tuple3<EventStream, Runnable, Function0<BoxedUnit>>, TaskInvocation> tupled() {
/*    */     return TaskInvocation$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<EventStream, Function1<Runnable, Function1<Function0<BoxedUnit>, TaskInvocation>>> curried() {
/*    */     return TaskInvocation$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\TaskInvocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */