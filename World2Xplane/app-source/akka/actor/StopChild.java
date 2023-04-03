/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055a!B\001\003\001\0221!!C*u_B\034\005.\0337e\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\024\t\0019Q\002\005\t\003\021-i\021!\003\006\002\025\005)1oY1mC&\021A\"\003\002\007\003:L(+\0324\021\005!q\021BA\b\n\005\035\001&o\0343vGR\004\"\001C\t\n\005II!\001D*fe&\fG.\033>bE2,\007\002\003\013\001\005+\007I\021\001\f\002\013\rD\027\016\0343\004\001U\tq\003\005\002\03135\t!!\003\002\033\005\tA\021i\031;peJ+g\r\003\005\035\001\tE\t\025!\003\030\003\031\031\007.\0337eA!)a\004\001C\001?\0051A(\0338jiz\"\"\001I\021\021\005a\001\001\"\002\013\036\001\0049\002bB\022\001\003\003%\t\001J\001\005G>\004\030\020\006\002!K!9AC\tI\001\002\0049\002bB\024\001#\003%\t\001K\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005I#FA\f+W\005Y\003C\001\0272\033\005i#B\001\0300\003%)hn\0315fG.,GM\003\0021\023\005Q\021M\0348pi\006$\030n\0348\n\005Ij#!E;oG\",7m[3e-\006\024\030.\0318dK\"9A\007AA\001\n\003*\024!\0049s_\022,8\r\036)sK\032L\0070F\0017!\t9D(D\0019\025\tI$(\001\003mC:<'\"A\036\002\t)\fg/Y\005\003{a\022aa\025;sS:<\007bB \001\003\003%\t\001Q\001\raJ|G-^2u\003JLG/_\013\002\003B\021\001BQ\005\003\007&\0211!\0238u\021\035)\005!!A\005\002\031\013a\002\035:pIV\034G/\0227f[\026tG\017\006\002H\025B\021\001\002S\005\003\023&\0211!\0218z\021\035YE)!AA\002\005\0131\001\037\0232\021\035i\005!!A\005B9\013q\002\035:pIV\034G/\023;fe\006$xN]\013\002\037B\031\001kU$\016\003ES!AU\005\002\025\r|G\016\\3di&|g.\003\002U#\nA\021\n^3sCR|'\017C\004W\001\005\005I\021A,\002\021\r\fg.R9vC2$\"\001W.\021\005!I\026B\001.\n\005\035\021un\0347fC:DqaS+\002\002\003\007q\tC\004^\001\005\005I\021\t0\002\021!\f7\017[\"pI\026$\022!\021\005\bA\002\t\t\021\"\021b\003!!xn\025;sS:<G#\001\034\t\017\r\004\021\021!C!I\0061Q-];bYN$\"\001W3\t\017-\023\027\021!a\001\017\036AqMAA\001\022\003!\001.A\005Ti>\0048\t[5mIB\021\001$\033\004\t\003\t\t\t\021#\001\005UN\031\021n\033\t\021\t1|w\003I\007\002[*\021a.C\001\beVtG/[7f\023\t\001XNA\tBEN$(/Y2u\rVt7\r^5p]FBQAH5\005\002I$\022\001\033\005\bA&\f\t\021\"\022b\021\035)\030.!A\005\002Z\fQ!\0319qYf$\"\001I<\t\013Q!\b\031A\f\t\017eL\027\021!CAu\0069QO\\1qa2LHCA>!\rAApF\005\003{&\021aa\0249uS>t\007bB@y\003\003\005\r\001I\001\004q\022\002\004\"CA\002S\006\005I\021BA\003\003-\021X-\0313SKN|GN^3\025\005\005\035\001cA\034\002\n%\031\0211\002\035\003\r=\023'.Z2u\001")
/*     */ public class StopChild implements Product, Serializable {
/*     */   private final ActorRef child;
/*     */   
/*     */   public static <A> Function1<ActorRef, A> andThen(Function1<StopChild, A> paramFunction1) {
/*     */     return StopChild$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, StopChild> compose(Function1<A, ActorRef> paramFunction1) {
/*     */     return StopChild$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public ActorRef child() {
/* 343 */     return this.child;
/*     */   }
/*     */   
/*     */   public StopChild copy(ActorRef child) {
/* 343 */     return new StopChild(child);
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$1() {
/* 343 */     return child();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 343 */     return "StopChild";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 343 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 343 */     int i = x$1;
/* 343 */     switch (i) {
/*     */       default:
/* 343 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 343 */     return child();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 343 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 343 */     return x$1 instanceof StopChild;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 343 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 343 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*     */     //   8: instanceof akka/actor/StopChild
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/StopChild
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual child : ()Lakka/actor/ActorRef;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual child : ()Lakka/actor/ActorRef;
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
/*     */     //   #343	-> 0
/*     */     //   #63	-> 14
/*     */     //   #343	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/actor/StopChild;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public StopChild(ActorRef child) {
/* 343 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\StopChild.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */