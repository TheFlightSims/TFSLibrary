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
/*     */ @ScalaSignature(bytes = "\006\001\005ua\001B\001\003\001\036\021!$\0237mK\036\fG.Q2u_J\034F/\031;f\013b\034W\r\035;j_:T!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\0011\003\002\001\t\031I\001\"!\003\006\016\003\021I!a\003\003\003\033\005[7.Y#yG\026\004H/[8o!\ti\001#D\001\017\025\005y\021!B:dC2\f\027BA\t\017\005\035\001&o\0343vGR\004\"!D\n\n\005Qq!\001D*fe&\fG.\033>bE2,\007\002\003\f\001\005+\007I\021A\f\002\0175,7o]1hKV\t\001\004\005\002\03299\021QBG\005\00379\ta\001\025:fI\0264\027BA\017\037\005\031\031FO]5oO*\0211D\004\005\tA\001\021\t\022)A\0051\005AQ.Z:tC\036,\007\005\003\004#\001\021\005AaI\001\007y%t\027\016\036 \025\005\0212\003CA\023\001\033\005\021\001\"\002\f\"\001\004A\002b\002\025\001\003\003%\t!K\001\005G>\004\030\020\006\002%U!9ac\nI\001\002\004A\002b\002\027\001#\003%\t!L\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005q#F\001\r0W\005\001\004CA\0317\033\005\021$BA\0325\003%)hn\0315fG.,GM\003\0026\035\005Q\021M\0348pi\006$\030n\0348\n\005]\022$!E;oG\",7m[3e-\006\024\030.\0318dK\"9\021\bAA\001\n\003R\024!\0049s_\022,8\r\036)sK\032L\0070F\001<!\ta\024)D\001>\025\tqt(\001\003mC:<'\"\001!\002\t)\fg/Y\005\003;uBqa\021\001\002\002\023\005A)\001\007qe>$Wo\031;Be&$\0300F\001F!\tia)\003\002H\035\t\031\021J\034;\t\017%\003\021\021!C\001\025\006q\001O]8ek\016$X\t\\3nK:$HCA&O!\tiA*\003\002N\035\t\031\021I\\=\t\017=C\025\021!a\001\013\006\031\001\020J\031\t\017E\003\021\021!C!%\006y\001O]8ek\016$\030\n^3sCR|'/F\001T!\r!vkS\007\002+*\021aKD\001\013G>dG.Z2uS>t\027B\001-V\005!IE/\032:bi>\024\bb\002.\001\003\003%\taW\001\tG\006tW)];bYR\021Al\030\t\003\033uK!A\030\b\003\017\t{w\016\\3b]\"9q*WA\001\002\004Y\005bB1\001\003\003%\tEY\001\tQ\006\034\bnQ8eKR\tQ\tC\004e\001\005\005I\021I3\002\r\025\fX/\0317t)\taf\rC\004PG\006\005\t\031A&)\007\001A7\016\005\002\016S&\021!N\004\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!A\004\b[\n\t\t\021#\001o\003iIE\016\\3hC2\f5\r^8s'R\fG/Z#yG\026\004H/[8o!\t)sNB\004\002\005\005\005\t\022\0019\024\007=\f(\003\005\003skb!S\"A:\013\005Qt\021a\002:v]RLW.Z\005\003mN\024\021#\0212tiJ\f7\r\036$v]\016$\030n\03482\021\025\021s\016\"\001y)\005q\007b\002>p\003\003%)e_\001\ti>\034FO]5oOR\t1\bC\004~_\006\005I\021\021@\002\013\005\004\b\017\\=\025\005\021z\b\"\002\f}\001\004A\002\"CA\002_\006\005I\021QA\003\003\035)h.\0319qYf$B!a\002\002\016A!Q\"!\003\031\023\r\tYA\004\002\007\037B$\030n\0348\t\023\005=\021\021AA\001\002\004!\023a\001=%a!I\0211C8\002\002\023%\021QC\001\fe\026\fGMU3t_24X\r\006\002\002\030A\031A(!\007\n\007\005mQH\001\004PE*,7\r\036")
/*     */ public class IllegalActorStateException extends AkkaException implements Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final String message;
/*     */   
/*     */   public static <A> Function1<String, A> andThen(Function1<IllegalActorStateException, A> paramFunction1) {
/*     */     return IllegalActorStateException$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, IllegalActorStateException> compose(Function1<A, String> paramFunction1) {
/*     */     return IllegalActorStateException$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public String message() {
/* 131 */     return this.message;
/*     */   }
/*     */   
/*     */   public IllegalActorStateException copy(String message) {
/* 131 */     return new IllegalActorStateException(message);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/* 131 */     return message();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 131 */     return "IllegalActorStateException";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 131 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 131 */     int i = x$1;
/* 131 */     switch (i) {
/*     */       default:
/* 131 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 131 */     return message();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 131 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 131 */     return x$1 instanceof IllegalActorStateException;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 131 */     return ScalaRunTime$.MODULE$._hashCode(this);
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
/*     */     //   8: instanceof akka/actor/IllegalActorStateException
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/IllegalActorStateException
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
/*     */     //   #131	-> 0
/*     */     //   #63	-> 14
/*     */     //   #131	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/actor/IllegalActorStateException;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public IllegalActorStateException(String message) {
/* 131 */     super(message);
/* 131 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\IllegalActorStateException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */