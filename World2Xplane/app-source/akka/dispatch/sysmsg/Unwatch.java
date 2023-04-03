/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}b!B\001\003\001\032A!aB+oo\006$8\r\033\006\003\007\021\taa]=t[N<'BA\003\007\003!!\027n\0359bi\016D'\"A\004\002\t\005\\7.Y\n\006\001%y1C\006\t\003\0255i\021a\003\006\002\031\005)1oY1mC&\021ab\003\002\007\003:L(+\0324\021\005A\tR\"\001\002\n\005I\021!!D*zgR,W.T3tg\006<W\r\005\002\013)%\021Qc\003\002\b!J|G-^2u!\tQq#\003\002\031\027\ta1+\032:jC2L'0\0312mK\"A!\004\001BK\002\023\005A$A\004xCR\034\007.Z3\004\001U\tQ\004\005\002\037C5\tqD\003\002!\r\005)\021m\031;pe&\021!e\b\002\t\003\016$xN\035*fM\"AA\005\001B\tB\003%Q$\001\005xCR\034\007.Z3!\021!1\003A!f\001\n\003a\022aB<bi\016DWM\035\005\tQ\001\021\t\022)A\005;\005Aq/\031;dQ\026\024\b\005C\003+\001\021\0051&\001\004=S:LGO\020\013\004Y5r\003C\001\t\001\021\025Q\022\0061\001\036\021\0251\023\0061\001\036\021\035\001\004!!A\005\002E\nAaY8qsR\031AFM\032\t\017iy\003\023!a\001;!9ae\fI\001\002\004i\002bB\033\001#\003%\tAN\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\0059$FA\0179W\005I\004C\001\036@\033\005Y$B\001\037>\003%)hn\0315fG.,GM\003\002?\027\005Q\021M\0348pi\006$\030n\0348\n\005\001[$!E;oG\",7m[3e-\006\024\030.\0318dK\"9!\tAI\001\n\0031\024AD2paf$C-\0324bk2$HE\r\005\b\t\002\t\t\021\"\021F\0035\001(o\0343vGR\004&/\0324jqV\ta\t\005\002H\0316\t\001J\003\002J\025\006!A.\0318h\025\005Y\025\001\0026bm\006L!!\024%\003\rM#(/\0338h\021\035y\005!!A\005\002A\013A\002\035:pIV\034G/\021:jif,\022!\025\t\003\025IK!aU\006\003\007%sG\017C\004V\001\005\005I\021\001,\002\035A\024x\016Z;di\026cW-\\3oiR\021qK\027\t\003\025aK!!W\006\003\007\005s\027\020C\004\\)\006\005\t\031A)\002\007a$\023\007C\004^\001\005\005I\021\t0\002\037A\024x\016Z;di&#XM]1u_J,\022a\030\t\004A\016<V\"A1\013\005\t\\\021AC2pY2,7\r^5p]&\021A-\031\002\t\023R,'/\031;pe\"9a\rAA\001\n\0039\027\001C2b]\026\013X/\0317\025\005!\\\007C\001\006j\023\tQ7BA\004C_>dW-\0318\t\017m+\027\021!a\001/\"9Q\016AA\001\n\003r\027\001\0035bg\"\034u\016Z3\025\003ECq\001\035\001\002\002\023\005\023/\001\005u_N#(/\0338h)\0051\005bB:\001\003\003%\t\005^\001\007KF,\030\r\\:\025\005!,\bbB.s\003\003\005\ra\026\025\004\001]T\bC\001\006y\023\tI8B\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021a\002\005}\005\005\005\t\022\001\004~\003\035)fn^1uG\"\004\"\001\005@\007\021\005\021\021\021!E\001\r}\034BA`A\001-A9\0211AA\005;uaSBAA\003\025\r\t9aC\001\beVtG/[7f\023\021\tY!!\002\003#\005\0237\017\036:bGR4UO\\2uS>t'\007\003\004+}\022\005\021q\002\013\002{\"9\001O`A\001\n\013\n\b\"CA\013}\006\005I\021QA\f\003\025\t\007\017\0357z)\025a\023\021DA\016\021\031Q\0221\003a\001;!1a%a\005A\002uA\021\"a\b\003\003%\t)!\t\002\017Ut\027\r\0359msR!\0211EA\030!\025Q\021QEA\025\023\r\t9c\003\002\007\037B$\030n\0348\021\013)\tY#H\017\n\007\00552B\001\004UkBdWM\r\005\n\003c\ti\"!AA\0021\n1\001\037\0231\021%\t)D`A\001\n\023\t9$A\006sK\006$'+Z:pYZ,GCAA\035!\r9\0251H\005\004\003{A%AB(cU\026\034G\017")
/*     */ public class Unwatch implements SystemMessage, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ActorRef watchee;
/*     */   
/*     */   private final ActorRef watcher;
/*     */   
/*     */   private transient SystemMessage next;
/*     */   
/*     */   public static Function1<Tuple2<ActorRef, ActorRef>, Unwatch> tupled() {
/*     */     return Unwatch$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<ActorRef, Function1<ActorRef, Unwatch>> curried() {
/*     */     return Unwatch$.MODULE$.curried();
/*     */   }
/*     */   
/*     */   public SystemMessage next() {
/* 245 */     return this.next;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void next_$eq(SystemMessage x$1) {
/* 245 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public void unlink() {
/* 245 */     SystemMessage$class.unlink(this);
/*     */   }
/*     */   
/*     */   public boolean unlinked() {
/* 245 */     return SystemMessage$class.unlinked(this);
/*     */   }
/*     */   
/*     */   public ActorRef watchee() {
/* 245 */     return this.watchee;
/*     */   }
/*     */   
/*     */   public ActorRef watcher() {
/* 245 */     return this.watcher;
/*     */   }
/*     */   
/*     */   public Unwatch copy(ActorRef watchee, ActorRef watcher) {
/* 245 */     return new Unwatch(watchee, watcher);
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$1() {
/* 245 */     return watchee();
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$2() {
/* 245 */     return watcher();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 245 */     return "Unwatch";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 245 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 245 */     int i = x$1;
/* 245 */     switch (i) {
/*     */       default:
/* 245 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 245 */     return watchee();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 245 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 245 */     return x$1 instanceof Unwatch;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 245 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 245 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 112
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/dispatch/sysmsg/Unwatch
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 116
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/sysmsg/Unwatch
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual watchee : ()Lakka/actor/ActorRef;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual watchee : ()Lakka/actor/ActorRef;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 108
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 108
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual watcher : ()Lakka/actor/ActorRef;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual watcher : ()Lakka/actor/ActorRef;
/*     */     //   72: astore #6
/*     */     //   74: dup
/*     */     //   75: ifnonnull -> 87
/*     */     //   78: pop
/*     */     //   79: aload #6
/*     */     //   81: ifnull -> 95
/*     */     //   84: goto -> 108
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 108
/*     */     //   95: aload #4
/*     */     //   97: aload_0
/*     */     //   98: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   101: ifeq -> 108
/*     */     //   104: iconst_1
/*     */     //   105: goto -> 109
/*     */     //   108: iconst_0
/*     */     //   109: ifeq -> 116
/*     */     //   112: iconst_1
/*     */     //   113: goto -> 117
/*     */     //   116: iconst_0
/*     */     //   117: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #245	-> 0
/*     */     //   #63	-> 14
/*     */     //   #245	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	118	0	this	Lakka/dispatch/sysmsg/Unwatch;
/*     */     //   0	118	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Unwatch(ActorRef watchee, ActorRef watcher) {
/* 245 */     SystemMessage$class.$init$(this);
/* 245 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Unwatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */