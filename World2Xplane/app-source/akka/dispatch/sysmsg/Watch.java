/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import akka.actor.InternalActorRef;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}b!B\001\003\001\032A!!B,bi\016D'BA\002\005\003\031\031\030p]7tO*\021QAB\001\tI&\034\b/\031;dQ*\tq!\001\003bW.\f7#\002\001\n\037M1\002C\001\006\016\033\005Y!\"\001\007\002\013M\034\027\r\\1\n\0059Y!AB!osJ+g\r\005\002\021#5\t!!\003\002\023\005\ti1+_:uK6lUm]:bO\026\004\"A\003\013\n\005UY!a\002)s_\022,8\r\036\t\003\025]I!\001G\006\003\031M+'/[1mSj\f'\r\\3\t\021i\001!Q3A\005\002q\tqa^1uG\",Wm\001\001\026\003u\001\"AH\021\016\003}Q!\001\t\004\002\013\005\034Go\034:\n\005\tz\"\001E%oi\026\024h.\0317BGR|'OU3g\021!!\003A!E!\002\023i\022\001C<bi\016DW-\032\021\t\021\031\002!Q3A\005\002q\tqa^1uG\",'\017\003\005)\001\tE\t\025!\003\036\003!9\030\r^2iKJ\004\003\"\002\026\001\t\003Y\023A\002\037j]&$h\bF\002-[9\002\"\001\005\001\t\013iI\003\031A\017\t\013\031J\003\031A\017\t\017A\002\021\021!C\001c\005!1m\0349z)\ra#g\r\005\b5=\002\n\0211\001\036\021\0351s\006%AA\002uAq!\016\001\022\002\023\005a'\001\bd_BLH\005Z3gCVdG\017J\031\026\003]R#!\b\035,\003e\002\"AO \016\003mR!\001P\037\002\023Ut7\r[3dW\026$'B\001 \f\003)\tgN\\8uCRLwN\\\005\003\001n\022\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035\021\005!%A\005\002Y\nabY8qs\022\"WMZ1vYR$#\007C\004E\001\005\005I\021I#\002\033A\024x\016Z;diB\023XMZ5y+\0051\005CA$M\033\005A%BA%K\003\021a\027M\\4\013\003-\013AA[1wC&\021Q\n\023\002\007'R\024\030N\\4\t\017=\003\021\021!C\001!\006a\001O]8ek\016$\030I]5usV\t\021\013\005\002\013%&\0211k\003\002\004\023:$\bbB+\001\003\003%\tAV\001\017aJ|G-^2u\0132,W.\0328u)\t9&\f\005\002\0131&\021\021l\003\002\004\003:L\bbB.U\003\003\005\r!U\001\004q\022\n\004bB/\001\003\003%\tEX\001\020aJ|G-^2u\023R,'/\031;peV\tq\fE\002aG^k\021!\031\006\003E.\t!bY8mY\026\034G/[8o\023\t!\027M\001\005Ji\026\024\030\r^8s\021\0351\007!!A\005\002\035\f\001bY1o\013F,\030\r\034\013\003Q.\004\"AC5\n\005)\\!a\002\"p_2,\027M\034\005\b7\026\f\t\0211\001X\021\035i\007!!A\005B9\f\001\002[1tQ\016{G-\032\013\002#\"9\001\017AA\001\n\003\n\030\001\003;p'R\024\030N\\4\025\003\031Cqa\035\001\002\002\023\005C/\001\004fcV\fGn\035\013\003QVDqa\027:\002\002\003\007q\013K\002\001oj\004\"A\003=\n\005e\\!\001E*fe&\fGNV3sg&|g.V%E=\005\tq\001\003?\003\003\003E\tAB?\002\013]\013Go\0315\021\005Aqh\001C\001\003\003\003E\tAB@\024\ty\f\tA\006\t\b\003\007\tI!H\017-\033\t\t)AC\002\002\b-\tqA];oi&lW-\003\003\002\f\005\025!!E!cgR\024\030m\031;Gk:\034G/[8oe!1!F C\001\003\037!\022! \005\baz\f\t\021\"\022r\021%\t)B`A\001\n\003\0139\"A\003baBd\027\020F\003-\0033\tY\002\003\004\033\003'\001\r!\b\005\007M\005M\001\031A\017\t\023\005}a0!A\005\002\006\005\022aB;oCB\004H.\037\013\005\003G\ty\003E\003\013\003K\tI#C\002\002(-\021aa\0249uS>t\007#\002\006\002,ui\022bAA\027\027\t1A+\0369mKJB\021\"!\r\002\036\005\005\t\031\001\027\002\007a$\003\007C\005\0026y\f\t\021\"\003\0028\005Y!/Z1e%\026\034x\016\034<f)\t\tI\004E\002H\003wI1!!\020I\005\031y%M[3di\002")
/*     */ public class Watch implements SystemMessage, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final InternalActorRef watchee;
/*     */   
/*     */   private final InternalActorRef watcher;
/*     */   
/*     */   private transient SystemMessage next;
/*     */   
/*     */   public static Function1<Tuple2<InternalActorRef, InternalActorRef>, Watch> tupled() {
/*     */     return Watch$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<InternalActorRef, Function1<InternalActorRef, Watch>> curried() {
/*     */     return Watch$.MODULE$.curried();
/*     */   }
/*     */   
/*     */   public SystemMessage next() {
/* 240 */     return this.next;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void next_$eq(SystemMessage x$1) {
/* 240 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public void unlink() {
/* 240 */     SystemMessage$class.unlink(this);
/*     */   }
/*     */   
/*     */   public boolean unlinked() {
/* 240 */     return SystemMessage$class.unlinked(this);
/*     */   }
/*     */   
/*     */   public InternalActorRef watchee() {
/* 240 */     return this.watchee;
/*     */   }
/*     */   
/*     */   public InternalActorRef watcher() {
/* 240 */     return this.watcher;
/*     */   }
/*     */   
/*     */   public Watch copy(InternalActorRef watchee, InternalActorRef watcher) {
/* 240 */     return new Watch(watchee, watcher);
/*     */   }
/*     */   
/*     */   public InternalActorRef copy$default$1() {
/* 240 */     return watchee();
/*     */   }
/*     */   
/*     */   public InternalActorRef copy$default$2() {
/* 240 */     return watcher();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 240 */     return "Watch";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 240 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 240 */     int i = x$1;
/* 240 */     switch (i) {
/*     */       default:
/* 240 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 240 */     return watchee();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 240 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 240 */     return x$1 instanceof Watch;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 240 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 240 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*     */     //   8: instanceof akka/dispatch/sysmsg/Watch
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 116
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/sysmsg/Watch
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual watchee : ()Lakka/actor/InternalActorRef;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual watchee : ()Lakka/actor/InternalActorRef;
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
/*     */     //   64: invokevirtual watcher : ()Lakka/actor/InternalActorRef;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual watcher : ()Lakka/actor/InternalActorRef;
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
/*     */     //   #240	-> 0
/*     */     //   #63	-> 14
/*     */     //   #240	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	118	0	this	Lakka/dispatch/sysmsg/Watch;
/*     */     //   0	118	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Watch(InternalActorRef watchee, InternalActorRef watcher) {
/* 240 */     SystemMessage$class.$init$(this);
/* 240 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Watch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */