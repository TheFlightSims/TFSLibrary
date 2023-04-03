/*     */ package akka.dispatch;
/*     */ 
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.BlockContext;
/*     */ import scala.concurrent.CanAwait;
/*     */ import scala.concurrent.forkjoin.ForkJoinPool;
/*     */ import scala.concurrent.forkjoin.ForkJoinWorkerThread;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t}s!B\001\003\021\0039\021\001G'p]&$xN]1cY\026$\006N]3bI\032\0137\r^8ss*\0211\001B\001\tI&\034\b/\031;dQ*\tQ!\001\003bW.\f7\001\001\t\003\021%i\021A\001\004\006\025\tA\ta\003\002\031\033>t\027\016^8sC\ndW\r\0265sK\006$g)Y2u_JL8cA\005\r%A\021Q\002E\007\002\035)\tq\"A\003tG\006d\027-\003\002\022\035\t1\021I\\=SK\032\004\"!D\n\n\005Qq!\001D*fe&\fG.\033>bE2,\007\"\002\f\n\t\0039\022A\002\037j]&$h\bF\001\b\021\035I\022B1A\005\002i\t\021\002Z8O_RD\027N\\4\026\003m\001\"\001\b\023\017\005u\021S\"\001\020\013\005}\001\023\001\0027b]\036T\021!I\001\005U\0064\030-\003\002$=\0051A\013\033:fC\022L!!\n\024\0031Us7-Y;hQR,\005pY3qi&|g\016S1oI2,'O\003\002$=!1\001&\003Q\001\nm\t!\002Z8O_RD\027N\\4!\r\025Q\023\002\001\003,\005a\t5n[1G_J\\'j\\5o/>\0248.\032:UQJ,\027\rZ\n\004S1\"\004CA\0273\033\005q#BA\0301\003!1wN]6k_&t'BA\031\017\003)\031wN\\2veJ,g\016^\005\003g9\022ACR8sW*{\027N\\,pe.,'\017\0265sK\006$\007CA\0337\033\005\001\024BA\0341\0051\021En\\2l\007>tG/\032=u\021!I\024F!A!\002\023Q\024!B0q_>d\007CA\027<\023\tadF\001\007G_J\\'j\\5o!>|G\016C\003\027S\021\005a\b\006\002@\003B\021\001)K\007\002\023!)\021(\020a\001u!)1)\013C!\t\0069!\r\\8dW>sWCA#J)\t1u\013\006\002H%B\021\001*\023\007\001\t\025Q%I1\001L\005\005!\026C\001'P!\tiQ*\003\002O\035\t9aj\034;iS:<\007CA\007Q\023\t\tfBA\002B]fDQa\025\"A\004Q\013!\002]3s[&\0348/[8o!\t)T+\003\002Wa\tA1)\0318Bo\006LG\017\003\004Y\005\022\005\r!W\001\006i\",hn\033\t\004\033i;\025BA.\017\005!a$-\0378b[\026t\004bB/\n\003\003%\tIX\001\006CB\004H.\037\013\f?\n5\"q\006B\031\005g\021)\004\005\002\tA\032!!B\001!b'\031\001'-\0327t%A\021QdY\005\003Iz\021aa\0242kK\016$\bC\0014k\033\0059'BA\031i\025\tI\007%\001\003vi&d\027BA6h\0055!\006N]3bI\032\0137\r^8ssB\021Q\016\035\b\003[9L!a\034\030\002\031\031{'o\033&pS:\004vn\0347\n\005E\024(a\007$pe.Tu.\0338X_J\\WM\035+ie\026\fGMR1di>\024\030P\003\002p]A\021Q\002^\005\003k:\021q\001\025:pIV\034G\017\003\005xA\nU\r\021\"\001y\003\021q\027-\\3\026\003e\004\"A_?\017\0055Y\030B\001?\017\003\031\001&/\0323fM&\021ap \002\007'R\024\030N\\4\013\005qt\001\"CA\002A\nE\t\025!\003z\003\025q\027-\\3!\021)\t9\001\031BK\002\023\005\021\021B\001\tI\006,Wn\0348jGV\021\0211\002\t\004\033\0055\021bAA\b\035\t9!i\\8mK\006t\007BCA\nA\nE\t\025!\003\002\f\005IA-Y3n_:L7\r\t\005\013\003/\001'Q3A\005\002\005e\021AE2p]R,\007\020^\"mCN\034Hj\\1eKJ,\"!a\007\021\0135\ti\"!\t\n\007\005}aB\001\004PaRLwN\034\t\004;\005\r\022bAA\023=\tY1\t\\1tg2{\027\rZ3s\021)\tI\003\031B\tB\003%\0211D\001\024G>tG/\032=u\0072\f7o\035'pC\022,'\017\t\005\n\003[\001'Q3A\005\002i\t\001#\032=dKB$\030n\0348IC:$G.\032:\t\023\005E\002M!E!\002\023Y\022!E3yG\026\004H/[8o\021\006tG\r\\3sA!Q\021Q\0071\003\006\004%\t\"a\016\002\017\r|WO\034;feV\021\021\021\b\t\005\003w\t\t%\004\002\002>)\031\021qH4\002\r\005$x.\\5d\023\021\t\031%!\020\003\025\005#x.\\5d\031>tw\r\003\006\002H\001\024\t\022)A\005\003s\t\001bY8v]R,'\017\t\005\007-\001$\t!a\023\025\027}\013i%a\024\002R\005M\023Q\013\005\007o\006%\003\031A=\t\021\005\035\021\021\na\001\003\027A\001\"a\006\002J\001\007\0211\004\005\n\003[\tI\005%AA\002mA!\"!\016\002JA\005\t\031AA\035\021\035\tI\006\031C\001\0037\n\021B\\3x)\"\024X-\0313\025\0071\ni\006C\004\002`\005]\003\031\001\036\002\tA|w\016\034\005\b\0033\002G\021AA2)\021\t)'a\033\021\007u\t9'C\002\002jy\021a\001\0265sK\006$\007\002CA7\003C\002\r!a\034\002\021I,hN\\1cY\026\0042!HA9\023\r\t\031H\b\002\t%Vtg.\0312mK\"9\021q\0171\005\002\005e\024\001C<ji\"t\025-\\3\025\007}\013Y\bC\004\002~\005U\004\031A=\002\0179,wOT1nK\"9\021\021\0211\005\022\005\r\025\001B<je\026,B!!\"\002\nR!\021qQAG!\rA\025\021\022\003\b\025\006}$\031AAF#\ra\025Q\r\005\t\003\037\013y\b1\001\002\b\006\tA\017C\005\002\024\002\f\t\021\"\001\002\026\006!1m\0349z)-y\026qSAM\0037\013i*a(\t\021]\f\t\n%AA\002eD!\"a\002\002\022B\005\t\031AA\006\021)\t9\"!%\021\002\003\007\0211\004\005\n\003[\t\t\n%AA\002mA!\"!\016\002\022B\005\t\031AA\035\021%\t\031\013YI\001\n\003\t)+\001\bd_BLH\005Z3gCVdG\017J\031\026\005\005\035&fA=\002*.\022\0211\026\t\005\003[\0139,\004\002\0020*!\021\021WAZ\003%)hn\0315fG.,GMC\002\0026:\t!\"\0318o_R\fG/[8o\023\021\tI,a,\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\005\002>\002\f\n\021\"\001\002@\006q1m\0349zI\021,g-Y;mi\022\022TCAAaU\021\tY!!+\t\023\005\025\007-%A\005\002\005\035\027AD2paf$C-\0324bk2$HeM\013\003\003\023TC!a\007\002*\"I\021Q\0321\022\002\023\005\021qZ\001\017G>\004\030\020\n3fM\006,H\016\036\0235+\t\t\tNK\002\034\003SC\021\"!6a#\003%\t!a6\002\035\r|\007/\037\023eK\032\fW\017\034;%kU\021\021\021\034\026\005\003s\tI\013C\005\002^\002\\\t\021\"\001\0028\005I1m\\;oi\026\024H%\r\005\n\003C\004\027\021!C!\003G\fQ\002\035:pIV\034G\017\025:fM&DXCAAs!\ri\022q]\005\003}zA\021\"a;a\003\003%\t!!<\002\031A\024x\016Z;di\006\023\030\016^=\026\005\005=\bcA\007\002r&\031\0211\037\b\003\007%sG\017C\005\002x\002\f\t\021\"\001\002z\006q\001O]8ek\016$X\t\\3nK:$HcA(\002|\"Q\021Q`A{\003\003\005\r!a<\002\007a$\023\007C\005\003\002\001\f\t\021\"\021\003\004\005y\001O]8ek\016$\030\n^3sCR|'/\006\002\003\006A)!q\001B\007\0376\021!\021\002\006\004\005\027q\021AC2pY2,7\r^5p]&!!q\002B\005\005!IE/\032:bi>\024\b\"\003B\nA\006\005I\021\001B\013\003!\031\027M\\#rk\006dG\003BA\006\005/A\021\"!@\003\022\005\005\t\031A(\t\023\tm\001-!A\005B\tu\021\001\0035bg\"\034u\016Z3\025\005\005=\b\"\003B\021A\006\005I\021\tB\022\003!!xn\025;sS:<GCAAs\021%\0219\003YA\001\n\003\022I#\001\004fcV\fGn\035\013\005\003\027\021Y\003C\005\002~\n\025\022\021!a\001\037\")q\017\030a\001s\"9\021q\001/A\002\005-\001bBA\f9\002\007\0211\004\005\t\003[a\006\023!a\0017!I\021Q\007/\021\002\003\007\021\021\b\005\n\005sI\021\021!CA\005w\tq!\0368baBd\027\020\006\003\003>\t\025\003#B\007\002\036\t}\002cC\007\003Be\fY!a\007\034\003sI1Aa\021\017\005\031!V\017\0357fk!I!q\tB\034\003\003\005\raX\001\004q\022\002\004\"\003B&\023E\005I\021AAh\003=\t\007\017\0357zI\021,g-Y;mi\022\"\004\"\003B(\023E\005I\021AAl\003=\t\007\017\0357zI\021,g-Y;mi\022*\004\"\003B*\023E\005I\021AAh\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%i!I!qK\005\022\002\023\005\021q[\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017J\033\t\023\tm\023\"!A\005\n\tu\023a\003:fC\022\024Vm]8mm\026$\022A\031")
/*     */ public class MonitorableThreadFactory implements ThreadFactory, ForkJoinPool.ForkJoinWorkerThreadFactory, Product, Serializable {
/*     */   private final String name;
/*     */   
/*     */   private final boolean daemonic;
/*     */   
/*     */   private final Option<ClassLoader> contextClassLoader;
/*     */   
/*     */   private final Thread.UncaughtExceptionHandler exceptionHandler;
/*     */   
/*     */   private final AtomicLong counter;
/*     */   
/*     */   public static class $anon$2 implements Thread.UncaughtExceptionHandler {
/*     */     public void uncaughtException(Thread thread, Throwable cause) {}
/*     */   }
/*     */   
/*     */   public static class AkkaForkJoinWorkerThread extends ForkJoinWorkerThread implements BlockContext {
/*     */     public AkkaForkJoinWorkerThread(ForkJoinPool _pool) {
/* 164 */       super(_pool);
/*     */     }
/*     */     
/*     */     public <T> T blockOn(Function0 thunk, CanAwait permission) {
/* 166 */       AtomicReference<None$> result = new AtomicReference<None$>(None$.MODULE$);
/* 167 */       ForkJoinPool.managedBlock(new MonitorableThreadFactory$AkkaForkJoinWorkerThread$$anon$3(this, thunk, result));
/* 174 */       return (T)((Option)result.get()).get();
/*     */     }
/*     */     
/*     */     public class MonitorableThreadFactory$AkkaForkJoinWorkerThread$$anon$3 implements ForkJoinPool.ManagedBlocker {
/*     */       private final Function0 thunk$1;
/*     */       
/*     */       private final AtomicReference result$1;
/*     */       
/*     */       public MonitorableThreadFactory$AkkaForkJoinWorkerThread$$anon$3(MonitorableThreadFactory.AkkaForkJoinWorkerThread $outer, Function0 thunk$1, AtomicReference result$1) {}
/*     */       
/*     */       public boolean block() {
/*     */         this.result$1.set(new Some(this.thunk$1.apply()));
/*     */         return true;
/*     */       }
/*     */       
/*     */       public boolean isReleasable() {
/*     */         return ((Option)this.result$1.get()).isDefined();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public String name() {
/* 179 */     return this.name;
/*     */   }
/*     */   
/*     */   public MonitorableThreadFactory copy(String name, boolean daemonic, Option<ClassLoader> contextClassLoader, Thread.UncaughtExceptionHandler exceptionHandler, AtomicLong counter) {
/* 179 */     return new MonitorableThreadFactory(name, 
/* 180 */         daemonic, 
/* 181 */         contextClassLoader, 
/* 182 */         exceptionHandler, 
/* 183 */         counter);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/*     */     return name();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "MonitorableThreadFactory";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 5;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 4:
/*     */       
/*     */       case 3:
/*     */       
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*     */     return name();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof MonitorableThreadFactory;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, Statics.anyHash(name()));
/*     */     i = Statics.mix(i, daemonic() ? 1231 : 1237);
/*     */     i = Statics.mix(i, Statics.anyHash(contextClassLoader()));
/*     */     i = Statics.mix(i, Statics.anyHash(exceptionHandler()));
/*     */     i = Statics.mix(i, Statics.anyHash(counter$1()));
/*     */     return Statics.finalizeHash(i, 5);
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 171
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/dispatch/MonitorableThreadFactory
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 175
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/MonitorableThreadFactory
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual name : ()Ljava/lang/String;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual name : ()Ljava/lang/String;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 167
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 167
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual daemonic : ()Z
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual daemonic : ()Z
/*     */     //   72: if_icmpne -> 167
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual contextClassLoader : ()Lscala/Option;
/*     */     //   79: aload #4
/*     */     //   81: invokevirtual contextClassLoader : ()Lscala/Option;
/*     */     //   84: astore #6
/*     */     //   86: dup
/*     */     //   87: ifnonnull -> 99
/*     */     //   90: pop
/*     */     //   91: aload #6
/*     */     //   93: ifnull -> 107
/*     */     //   96: goto -> 167
/*     */     //   99: aload #6
/*     */     //   101: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   104: ifeq -> 167
/*     */     //   107: aload_0
/*     */     //   108: invokevirtual exceptionHandler : ()Ljava/lang/Thread$UncaughtExceptionHandler;
/*     */     //   111: aload #4
/*     */     //   113: invokevirtual exceptionHandler : ()Ljava/lang/Thread$UncaughtExceptionHandler;
/*     */     //   116: astore #7
/*     */     //   118: dup
/*     */     //   119: ifnonnull -> 131
/*     */     //   122: pop
/*     */     //   123: aload #7
/*     */     //   125: ifnull -> 139
/*     */     //   128: goto -> 167
/*     */     //   131: aload #7
/*     */     //   133: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   136: ifeq -> 167
/*     */     //   139: aload_0
/*     */     //   140: invokevirtual counter$1 : ()Ljava/util/concurrent/atomic/AtomicLong;
/*     */     //   143: aload #4
/*     */     //   145: invokevirtual counter$1 : ()Ljava/util/concurrent/atomic/AtomicLong;
/*     */     //   148: invokestatic equalsNumNum : (Ljava/lang/Number;Ljava/lang/Number;)Z
/*     */     //   151: ifeq -> 167
/*     */     //   154: aload #4
/*     */     //   156: aload_0
/*     */     //   157: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   160: ifeq -> 167
/*     */     //   163: iconst_1
/*     */     //   164: goto -> 168
/*     */     //   167: iconst_0
/*     */     //   168: ifeq -> 175
/*     */     //   171: iconst_1
/*     */     //   172: goto -> 176
/*     */     //   175: iconst_0
/*     */     //   176: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #179	-> 0
/*     */     //   #63	-> 14
/*     */     //   #179	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	177	0	this	Lakka/dispatch/MonitorableThreadFactory;
/*     */     //   0	177	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public MonitorableThreadFactory(String name, boolean daemonic, Option<ClassLoader> contextClassLoader, Thread.UncaughtExceptionHandler exceptionHandler, AtomicLong counter) {
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public boolean daemonic() {
/*     */     return this.daemonic;
/*     */   }
/*     */   
/*     */   public boolean copy$default$2() {
/*     */     return daemonic();
/*     */   }
/*     */   
/*     */   public Option<ClassLoader> contextClassLoader() {
/*     */     return this.contextClassLoader;
/*     */   }
/*     */   
/*     */   public Option<ClassLoader> copy$default$3() {
/*     */     return contextClassLoader();
/*     */   }
/*     */   
/*     */   public Thread.UncaughtExceptionHandler exceptionHandler() {
/*     */     return this.exceptionHandler;
/*     */   }
/*     */   
/*     */   public Thread.UncaughtExceptionHandler copy$default$4() {
/*     */     return exceptionHandler();
/*     */   }
/*     */   
/*     */   public AtomicLong counter$1() {
/* 183 */     return this.counter;
/*     */   }
/*     */   
/*     */   public AtomicLong counter() {
/* 183 */     return this.counter;
/*     */   }
/*     */   
/*     */   public AtomicLong copy$default$5() {
/* 183 */     return counter();
/*     */   }
/*     */   
/*     */   public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
/* 187 */     AkkaForkJoinWorkerThread t = wire(new AkkaForkJoinWorkerThread(pool));
/* 189 */     t.setName((new StringBuilder()).append(name()).append("-").append(BoxesRunTime.boxToLong(counter().incrementAndGet())).toString());
/* 190 */     return t;
/*     */   }
/*     */   
/*     */   public Thread newThread(Runnable runnable) {
/* 193 */     return wire(new Thread(runnable, (new StringBuilder()).append(name()).append("-").append(BoxesRunTime.boxToLong(counter().incrementAndGet())).toString()));
/*     */   }
/*     */   
/*     */   public MonitorableThreadFactory withName(String newName) {
/* 195 */     return copy(newName, copy$default$2(), copy$default$3(), copy$default$4(), copy$default$5());
/*     */   }
/*     */   
/*     */   public <T extends Thread> T wire(Thread t) {
/* 198 */     t.setUncaughtExceptionHandler(exceptionHandler());
/* 199 */     t.setDaemon(daemonic());
/* 200 */     contextClassLoader().foreach((Function1)new MonitorableThreadFactory$$anonfun$wire$1(this, t));
/* 201 */     return (T)t;
/*     */   }
/*     */   
/*     */   public static AtomicLong $lessinit$greater$default$5() {
/*     */     return MonitorableThreadFactory$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static Thread.UncaughtExceptionHandler $lessinit$greater$default$4() {
/*     */     return MonitorableThreadFactory$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static AtomicLong apply$default$5() {
/*     */     return MonitorableThreadFactory$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static Thread.UncaughtExceptionHandler apply$default$4() {
/*     */     return MonitorableThreadFactory$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static Thread.UncaughtExceptionHandler doNothing() {
/*     */     return MonitorableThreadFactory$.MODULE$.doNothing();
/*     */   }
/*     */   
/*     */   public class MonitorableThreadFactory$$anonfun$wire$1 extends AbstractFunction1<ClassLoader, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Thread t$1;
/*     */     
/*     */     public final void apply(ClassLoader x$1) {
/*     */       this.t$1.setContextClassLoader(x$1);
/*     */     }
/*     */     
/*     */     public MonitorableThreadFactory$$anonfun$wire$1(MonitorableThreadFactory $outer, Thread t$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\MonitorableThreadFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */