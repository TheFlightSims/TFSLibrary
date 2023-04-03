/*     */ package akka.actor;
/*     */ 
/*     */ import akka.AkkaException;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005ua\001B\001\003\001\036\021\021$\0238wC2LG-Q2u_Jt\025-\\3Fq\016,\007\017^5p]*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b\007\001\031B\001\001\005\r%A\021\021BC\007\002\t%\0211\002\002\002\016\003.\\\027-\022=dKB$\030n\0348\021\0055\001R\"\001\b\013\003=\tQa]2bY\006L!!\005\b\003\017A\023x\016Z;diB\021QbE\005\003)9\021AbU3sS\006d\027N_1cY\026D\001B\006\001\003\026\004%\taF\001\b[\026\0348/Y4f+\005A\002CA\r\035\035\ti!$\003\002\034\035\0051\001K]3eK\032L!!\b\020\003\rM#(/\0338h\025\tYb\002\003\005!\001\tE\t\025!\003\031\003!iWm]:bO\026\004\003\"\002\022\001\t\003\031\023A\002\037j]&$h\b\006\002%MA\021Q\005A\007\002\005!)a#\ta\0011!9\001\006AA\001\n\003I\023\001B2paf$\"\001\n\026\t\017Y9\003\023!a\0011!9A\006AI\001\n\003i\023AD2paf$C-\0324bk2$H%M\013\002])\022\001dL\026\002aA\021\021GN\007\002e)\0211\007N\001\nk:\034\007.Z2lK\022T!!\016\b\002\025\005tgn\034;bi&|g.\003\0028e\t\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017e\002\021\021!C!u\005i\001O]8ek\016$\bK]3gSb,\022a\017\t\003y\005k\021!\020\006\003}}\nA\001\\1oO*\t\001)\001\003kCZ\f\027BA\017>\021\035\031\005!!A\005\002\021\013A\002\035:pIV\034G/\021:jif,\022!\022\t\003\033\031K!a\022\b\003\007%sG\017C\004J\001\005\005I\021\001&\002\035A\024x\016Z;di\026cW-\\3oiR\0211J\024\t\003\0331K!!\024\b\003\007\005s\027\020C\004P\021\006\005\t\031A#\002\007a$\023\007C\004R\001\005\005I\021\t*\002\037A\024x\016Z;di&#XM]1u_J,\022a\025\t\004)^[U\"A+\013\005Ys\021AC2pY2,7\r^5p]&\021\001,\026\002\t\023R,'/\031;pe\"9!\fAA\001\n\003Y\026\001C2b]\026\013X/\0317\025\005q{\006CA\007^\023\tqfBA\004C_>dW-\0318\t\017=K\026\021!a\001\027\"9\021\rAA\001\n\003\022\027\001\0035bg\"\034u\016Z3\025\003\025Cq\001\032\001\002\002\023\005S-\001\004fcV\fGn\035\013\0039\032DqaT2\002\002\003\0071\nK\002\001Q.\004\"!D5\n\005)t!\001E*fe&\fGNV3sg&|g.V%E=\005\tqaB7\003\003\003E\tA\\\001\032\023:4\030\r\\5e\003\016$xN\035(b[\026,\005pY3qi&|g\016\005\002&_\0329\021AAA\001\022\003\0018cA8r%A!!/\036\r%\033\005\031(B\001;\017\003\035\021XO\034;j[\026L!A^:\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\003#_\022\005\001\020F\001o\021\035Qx.!A\005Fm\f\001\002^8TiJLgn\032\013\002w!9Qp\\A\001\n\003s\030!B1qa2LHC\001\023\000\021\0251B\0201\001\031\021%\t\031a\\A\001\n\003\013)!A\004v]\006\004\b\017\\=\025\t\005\035\021Q\002\t\005\033\005%\001$C\002\002\f9\021aa\0249uS>t\007\"CA\b\003\003\t\t\0211\001%\003\rAH\005\r\005\n\003'y\027\021!C\005\003+\t1B]3bIJ+7o\0347wKR\021\021q\003\t\004y\005e\021bAA\016{\t1qJ\0316fGR\004")
/*     */ public class InvalidActorNameException extends AkkaException implements Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final String message;
/*     */   
/*     */   public static <A> Function1<String, A> andThen(Function1<InvalidActorNameException, A> paramFunction1) {
/*     */     return InvalidActorNameException$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, InvalidActorNameException> compose(Function1<A, String> paramFunction1) {
/*     */     return InvalidActorNameException$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public String message() {
/* 144 */     return this.message;
/*     */   }
/*     */   
/*     */   public InvalidActorNameException copy(String message) {
/* 144 */     return new InvalidActorNameException(message);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/* 144 */     return message();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 144 */     return "InvalidActorNameException";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 144 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 144 */     int i = x$1;
/* 144 */     switch (i) {
/*     */       default:
/* 144 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 144 */     return message();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 144 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 144 */     return x$1 instanceof InvalidActorNameException;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 144 */     return ScalaRunTime$.MODULE$._hashCode(this);
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
/*     */     //   8: instanceof akka/actor/InvalidActorNameException
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/InvalidActorNameException
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual message : ()Ljava/lang/String;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual message : ()Ljava/lang/String;
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
/*     */     //   #144	-> 0
/*     */     //   #63	-> 14
/*     */     //   #144	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/actor/InvalidActorNameException;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public InvalidActorNameException(String message) {
/* 144 */     super(message);
/* 144 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\InvalidActorNameException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */