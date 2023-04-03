/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import akka.actor.ActorInitializationException;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%b!B\001\003\001\032A!AB\"sK\006$XM\003\002\004\t\00511/_:ng\036T!!\002\004\002\021\021L7\017]1uG\"T\021aB\001\005C.\\\027mE\003\001\023=\031b\003\005\002\013\0335\t1BC\001\r\003\025\0318-\0317b\023\tq1B\001\004B]f\024VM\032\t\003!Ei\021AA\005\003%\t\021QbU=ti\026lW*Z:tC\036,\007C\001\006\025\023\t)2BA\004Qe>$Wo\031;\021\005)9\022B\001\r\f\0051\031VM]5bY&T\030M\0317f\021!Q\002A!f\001\n\003a\022a\0024bS2,(/Z\002\001+\005i\002c\001\006\037A%\021qd\003\002\007\037B$\030n\0348\021\005\005\"S\"\001\022\013\005\r2\021!B1di>\024\030BA\023#\005q\t5\r^8s\023:LG/[1mSj\fG/[8o\013b\034W\r\035;j_:D\001b\n\001\003\022\003\006I!H\001\tM\006LG.\036:fA!)\021\006\001C\001U\0051A(\0338jiz\"\"a\013\027\021\005A\001\001\"\002\016)\001\004i\002b\002\030\001\003\003%\taL\001\005G>\004\030\020\006\002,a!9!$\fI\001\002\004i\002b\002\032\001#\003%\taM\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005!$FA\0176W\0051\004CA\034=\033\005A$BA\035;\003%)hn\0315fG.,GM\003\002<\027\005Q\021M\0348pi\006$\030n\0348\n\005uB$!E;oG\",7m[3e-\006\024\030.\0318dK\"9q\bAA\001\n\003\002\025!\0049s_\022,8\r\036)sK\032L\0070F\001B!\t\021u)D\001D\025\t!U)\001\003mC:<'\"\001$\002\t)\fg/Y\005\003\021\016\023aa\025;sS:<\007b\002&\001\003\003%\taS\001\raJ|G-^2u\003JLG/_\013\002\031B\021!\"T\005\003\035.\0211!\0238u\021\035\001\006!!A\005\002E\013a\002\035:pIV\034G/\0227f[\026tG\017\006\002S+B\021!bU\005\003).\0211!\0218z\021\0351v*!AA\0021\0131\001\037\0232\021\035A\006!!A\005Be\013q\002\035:pIV\034G/\023;fe\006$xN]\013\0025B\0311L\030*\016\003qS!!X\006\002\025\r|G\016\\3di&|g.\003\002`9\nA\021\n^3sCR|'\017C\004b\001\005\005I\021\0012\002\021\r\fg.R9vC2$\"a\0314\021\005)!\027BA3\f\005\035\021un\0347fC:DqA\0261\002\002\003\007!\013C\004i\001\005\005I\021I5\002\021!\f7\017[\"pI\026$\022\001\024\005\bW\002\t\t\021\"\021m\003!!xn\025;sS:<G#A!\t\0179\004\021\021!C!_\0061Q-];bYN$\"a\0319\t\017Yk\027\021!a\001%\"\032\001A];\021\005)\031\030B\001;\f\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\017!9(!!A\t\002\031A\030AB\"sK\006$X\r\005\002\021s\032A\021AAA\001\022\0031!pE\002zwZ\001B\001`@\036W5\tQP\003\002\027\0059!/\0368uS6,\027bAA\001{\n\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\r%JH\021AA\003)\005A\bbB6z\003\003%)\005\034\005\n\003\027I\030\021!CA\003\033\tQ!\0319qYf$2aKA\b\021\031Q\022\021\002a\001;!I\0211C=\002\002\023\005\025QC\001\bk:\f\007\017\0357z)\021\t9\"!\007\021\007)qR\004C\005\002\034\005E\021\021!a\001W\005\031\001\020\n\031\t\023\005}\0210!A\005\n\005\005\022a\003:fC\022\024Vm]8mm\026$\"!a\t\021\007\t\013)#C\002\002(\r\023aa\0242kK\016$\b")
/*     */ public class Create implements SystemMessage, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Option<ActorInitializationException> failure;
/*     */   
/*     */   private transient SystemMessage next;
/*     */   
/*     */   public static <A> Function1<Option<ActorInitializationException>, A> andThen(Function1<Create, A> paramFunction1) {
/*     */     return Create$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, Create> compose(Function1<A, Option<ActorInitializationException>> paramFunction1) {
/*     */     return Create$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public SystemMessage next() {
/* 210 */     return this.next;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void next_$eq(SystemMessage x$1) {
/* 210 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public void unlink() {
/* 210 */     SystemMessage$class.unlink(this);
/*     */   }
/*     */   
/*     */   public boolean unlinked() {
/* 210 */     return SystemMessage$class.unlinked(this);
/*     */   }
/*     */   
/*     */   public Option<ActorInitializationException> failure() {
/* 210 */     return this.failure;
/*     */   }
/*     */   
/*     */   public Create copy(Option<ActorInitializationException> failure) {
/* 210 */     return new Create(failure);
/*     */   }
/*     */   
/*     */   public Option<ActorInitializationException> copy$default$1() {
/* 210 */     return failure();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 210 */     return "Create";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 210 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 210 */     int i = x$1;
/* 210 */     switch (i) {
/*     */       default:
/* 210 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 210 */     return failure();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 210 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 210 */     return x$1 instanceof Create;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 210 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 210 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 80
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/dispatch/sysmsg/Create
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/sysmsg/Create
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual failure : ()Lscala/Option;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual failure : ()Lscala/Option;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 76
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 76
/*     */     //   63: aload #4
/*     */     //   65: aload_0
/*     */     //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   69: ifeq -> 76
/*     */     //   72: iconst_1
/*     */     //   73: goto -> 77
/*     */     //   76: iconst_0
/*     */     //   77: ifeq -> 84
/*     */     //   80: iconst_1
/*     */     //   81: goto -> 85
/*     */     //   84: iconst_0
/*     */     //   85: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #210	-> 0
/*     */     //   #63	-> 14
/*     */     //   #210	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/dispatch/sysmsg/Create;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Create(Option<ActorInitializationException> failure) {
/* 210 */     SystemMessage$class.$init$(this);
/* 210 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Create.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */