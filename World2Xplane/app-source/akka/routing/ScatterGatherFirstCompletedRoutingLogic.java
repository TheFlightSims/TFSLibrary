/*    */ package akka.routing;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\035c\001B\001\003\005\036\021qeU2biR,'oR1uQ\026\024h)\033:ti\016{W\016\0357fi\026$'k\\;uS:<Gj\\4jG*\0211\001B\001\be>,H/\0338h\025\005)\021\001B1lW\006\034\001aE\003\001\0219\021R\003\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\021ABU8vi&tw\rT8hS\016\004\"!C\n\n\005QQ!a\002)s_\022,8\r\036\t\003\023YI!a\006\006\003\031M+'/[1mSj\f'\r\\3\t\021e\001!Q3A\005\002i\taa^5uQ&tW#A\016\021\005q\tS\"A\017\013\005yy\022\001\0033ve\006$\030n\0348\013\005\001R\021AC2p]\016,(O]3oi&\021!%\b\002\017\r&t\027\016^3EkJ\fG/[8o\021!!\003A!E!\002\023Y\022aB<ji\"Lg\016\t\005\006M\001!\taJ\001\007y%t\027\016\036 \025\005!J\003CA\b\001\021\025IR\0051\001\034\021\025Y\003\001\"\021-\003\031\031X\r\\3diR\031Q\006M\033\021\005=q\023BA\030\003\005\031\021v.\036;fK\")\021G\013a\001e\0059Q.Z:tC\036,\007CA\0054\023\t!$BA\002B]fDQA\016\026A\002]\nqA]8vi\026,7\017E\0029{5j\021!\017\006\003um\n\021\"[7nkR\f'\r\\3\013\005qR\021AC2pY2,7\r^5p]&\021a(\017\002\013\023:$W\r_3e'\026\f\bb\002!\001\003\003%\t!Q\001\005G>\004\030\020\006\002)\005\"9\021d\020I\001\002\004Y\002b\002#\001#\003%\t!R\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\0051%FA\016HW\005A\005CA%O\033\005Q%BA&M\003%)hn\0315fG.,GM\003\002N\025\005Q\021M\0348pi\006$\030n\0348\n\005=S%!E;oG\",7m[3e-\006\024\030.\0318dK\"9\021\013AA\001\n\003\022\026!\0049s_\022,8\r\036)sK\032L\0070F\001T!\t!\026,D\001V\025\t1v+\001\003mC:<'\"\001-\002\t)\fg/Y\005\0035V\023aa\025;sS:<\007b\002/\001\003\003%\t!X\001\raJ|G-^2u\003JLG/_\013\002=B\021\021bX\005\003A*\0211!\0238u\021\035\021\007!!A\005\002\r\fa\002\035:pIV\034G/\0227f[\026tG\017\006\0023I\"9Q-YA\001\002\004q\026a\001=%c!9q\rAA\001\n\003B\027a\0049s_\022,8\r^%uKJ\fGo\034:\026\003%\0042A[63\033\005Y\024B\0017<\005!IE/\032:bi>\024\bb\0028\001\003\003%\ta\\\001\tG\006tW)];bYR\021\001o\035\t\003\023EL!A\035\006\003\017\t{w\016\\3b]\"9Q-\\A\001\002\004\021\004bB;\001\003\003%\tE^\001\tQ\006\034\bnQ8eKR\ta\fC\004y\001\005\005I\021I=\002\021Q|7\013\036:j]\036$\022a\025\005\bw\002\t\t\021\"\021}\003\031)\027/^1mgR\021\001/ \005\bKj\f\t\0211\0013Q\021\001q0!\002\021\007%\t\t!C\002\002\004)\021\001cU3sS\006dg+\032:tS>tW+\023#\037\003\0059\021\"!\003\003\003\003E\t!a\003\002OM\033\027\r\036;fe\036\013G\017[3s\r&\0248\017^\"p[BdW\r^3e%>,H/\0338h\031><\027n\031\t\004\037\0055a\001C\001\003\003\003E\t!a\004\024\013\0055\021\021C\013\021\r\005M\021\021D\016)\033\t\t)BC\002\002\030)\tqA];oi&lW-\003\003\002\034\005U!!E!cgR\024\030m\031;Gk:\034G/[8oc!9a%!\004\005\002\005}ACAA\006\021!A\030QBA\001\n\013J\bBCA\023\003\033\t\t\021\"!\002(\005)\021\r\0359msR\031\001&!\013\t\re\t\031\0031\001\034\021)\ti#!\004\002\002\023\005\025qF\001\bk:\f\007\017\0357z)\021\t\t$a\016\021\t%\t\031dG\005\004\003kQ!AB(qi&|g\016C\005\002:\005-\022\021!a\001Q\005\031\001\020\n\031\t\025\005u\022QBA\001\n\023\ty$A\006sK\006$'+Z:pYZ,GCAA!!\r!\0261I\005\004\003\013*&AB(cU\026\034G\017")
/*    */ public final class ScatterGatherFirstCompletedRoutingLogic implements RoutingLogic, Product, Serializable {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private final FiniteDuration within;
/*    */   
/*    */   public FiniteDuration within() {
/* 32 */     return this.within;
/*    */   }
/*    */   
/*    */   public ScatterGatherFirstCompletedRoutingLogic copy(FiniteDuration within) {
/* 32 */     return new ScatterGatherFirstCompletedRoutingLogic(within);
/*    */   }
/*    */   
/*    */   public FiniteDuration copy$default$1() {
/* 32 */     return within();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 32 */     return "ScatterGatherFirstCompletedRoutingLogic";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 32 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 32 */     int i = x$1;
/* 32 */     switch (i) {
/*    */       default:
/* 32 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 32 */     return within();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 32 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 32 */     return x$1 instanceof ScatterGatherFirstCompletedRoutingLogic;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 32 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 32 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 71
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/routing/ScatterGatherFirstCompletedRoutingLogic
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 75
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/routing/ScatterGatherFirstCompletedRoutingLogic
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual within : ()Lscala/concurrent/duration/FiniteDuration;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual within : ()Lscala/concurrent/duration/FiniteDuration;
/*    */     //   40: astore #5
/*    */     //   42: dup
/*    */     //   43: ifnonnull -> 55
/*    */     //   46: pop
/*    */     //   47: aload #5
/*    */     //   49: ifnull -> 63
/*    */     //   52: goto -> 67
/*    */     //   55: aload #5
/*    */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   60: ifeq -> 67
/*    */     //   63: iconst_1
/*    */     //   64: goto -> 68
/*    */     //   67: iconst_0
/*    */     //   68: ifeq -> 75
/*    */     //   71: iconst_1
/*    */     //   72: goto -> 76
/*    */     //   75: iconst_0
/*    */     //   76: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #32	-> 0
/*    */     //   #63	-> 14
/*    */     //   #32	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	77	0	this	Lakka/routing/ScatterGatherFirstCompletedRoutingLogic;
/*    */     //   0	77	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ScatterGatherFirstCompletedRoutingLogic(FiniteDuration within) {
/* 32 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Routee select(Object message, IndexedSeq<Routee> routees) {
/* 34 */     return routees.isEmpty() ? NoRoutee$.MODULE$ : 
/* 35 */       new ScatterGatherFirstCompletedRoutees(routees, within());
/*    */   }
/*    */   
/*    */   public static <A> Function1<FiniteDuration, A> andThen(Function1<ScatterGatherFirstCompletedRoutingLogic, A> paramFunction1) {
/*    */     return ScatterGatherFirstCompletedRoutingLogic$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, ScatterGatherFirstCompletedRoutingLogic> compose(Function1<A, FiniteDuration> paramFunction1) {
/*    */     return ScatterGatherFirstCompletedRoutingLogic$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ScatterGatherFirstCompletedRoutingLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */