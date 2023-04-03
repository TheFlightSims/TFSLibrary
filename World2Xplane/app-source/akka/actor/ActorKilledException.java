/*     */ package akka.actor;
/*     */ 
/*     */ import akka.AkkaException;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.util.control.NoStackTrace;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055b\001B\001\003\001\036\021A#Q2u_J\\\025\016\0347fI\026C8-\0329uS>t'BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001aE\003\001\02111\"\004\005\002\n\0255\tA!\003\002\f\t\ti\021i[6b\013b\034W\r\035;j_:\004\"!\004\013\016\0039Q!a\004\t\002\017\r|g\016\036:pY*\021\021CE\001\005kRLGNC\001\024\003\025\0318-\0317b\023\t)bB\001\007O_N#\030mY6Ue\006\034W\r\005\002\03015\t!#\003\002\032%\t9\001K]8ek\016$\bCA\f\034\023\ta\"C\001\007TKJL\027\r\\5{C\ndW\r\003\005\037\001\tU\r\021\"\001 \003\035iWm]:bO\026,\022\001\t\t\003C\021r!a\006\022\n\005\r\022\022A\002)sK\022,g-\003\002&M\t11\013\036:j]\036T!a\t\n\t\021!\002!\021#Q\001\n\001\n\001\"\\3tg\006<W\r\t\005\007U\001!\t\001B\026\002\rqJg.\033;?)\tac\006\005\002.\0015\t!\001C\003\037S\001\007\001\005C\0041\001\005\005I\021A\031\002\t\r|\007/\037\013\003YIBqAH\030\021\002\003\007\001\005C\0045\001E\005I\021A\033\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\taG\013\002!o-\n\001\b\005\002:}5\t!H\003\002<y\005IQO\\2iK\016\\W\r\032\006\003{I\t!\"\0318o_R\fG/[8o\023\ty$HA\tv]\016DWmY6fIZ\013'/[1oG\026Dq!\021\001\002\002\023\005#)A\007qe>$Wo\031;Qe\0264\027\016_\013\002\007B\021A)S\007\002\013*\021aiR\001\005Y\006twMC\001I\003\021Q\027M^1\n\005\025*\005bB&\001\003\003%\t\001T\001\raJ|G-^2u\003JLG/_\013\002\033B\021qCT\005\003\037J\0211!\0238u\021\035\t\006!!A\005\002I\013a\002\035:pIV\034G/\0227f[\026tG\017\006\002T-B\021q\003V\005\003+J\0211!\0218z\021\0359\006+!AA\0025\0131\001\037\0232\021\035I\006!!A\005Bi\013q\002\035:pIV\034G/\023;fe\006$xN]\013\0027B\031AlX*\016\003uS!A\030\n\002\025\r|G\016\\3di&|g.\003\002a;\nA\021\n^3sCR|'\017C\004c\001\005\005I\021A2\002\021\r\fg.R9vC2$\"\001Z4\021\005])\027B\0014\023\005\035\021un\0347fC:DqaV1\002\002\003\0071\013C\004j\001\005\005I\021\t6\002\021!\f7\017[\"pI\026$\022!\024\005\bY\002\t\t\021\"\021n\003\031)\027/^1mgR\021AM\034\005\b/.\f\t\0211\001TQ\r\001\001o\035\t\003/EL!A\035\n\003!M+'/[1m-\026\0248/[8o+&#e$A\001\b\017U\024\021\021!E\001m\006!\022i\031;pe.KG\016\\3e\013b\034W\r\035;j_:\004\"!L<\007\017\005\021\021\021!E\001qN\031q/\037\016\021\til\b\005L\007\002w*\021APE\001\beVtG/[7f\023\tq8PA\tBEN$(/Y2u\rVt7\r^5p]FBaAK<\005\002\005\005A#\001<\t\023\005\025q/!A\005F\005\035\021\001\003;p'R\024\030N\\4\025\003\rC\021\"a\003x\003\003%\t)!\004\002\013\005\004\b\017\\=\025\0071\ny\001\003\004\037\003\023\001\r\001\t\005\n\003'9\030\021!CA\003+\tq!\0368baBd\027\020\006\003\002\030\005u\001\003B\f\002\032\001J1!a\007\023\005\031y\005\017^5p]\"I\021qDA\t\003\003\005\r\001L\001\004q\022\002\004\"CA\022o\006\005I\021BA\023\003-\021X-\0313SKN|GN^3\025\005\005\035\002c\001#\002*%\031\0211F#\003\r=\023'.Z2u\001")
/*     */ public class ActorKilledException extends AkkaException implements NoStackTrace, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final String message;
/*     */   
/*     */   public static <A> Function1<String, A> andThen(Function1<ActorKilledException, A> paramFunction1) {
/*     */     return ActorKilledException$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, ActorKilledException> compose(Function1<A, String> paramFunction1) {
/*     */     return ActorKilledException$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public Throwable scala$util$control$NoStackTrace$$super$fillInStackTrace() {
/* 137 */     return super.fillInStackTrace();
/*     */   }
/*     */   
/*     */   public Throwable fillInStackTrace() {
/* 137 */     return NoStackTrace.class.fillInStackTrace(this);
/*     */   }
/*     */   
/*     */   public String message() {
/* 137 */     return this.message;
/*     */   }
/*     */   
/*     */   public ActorKilledException copy(String message) {
/* 137 */     return new ActorKilledException(message);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/* 137 */     return message();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 137 */     return "ActorKilledException";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 137 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 137 */     int i = x$1;
/* 137 */     switch (i) {
/*     */       default:
/* 137 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 137 */     return message();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 137 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 137 */     return x$1 instanceof ActorKilledException;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 137 */     return ScalaRunTime$.MODULE$._hashCode(this);
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
/*     */     //   8: instanceof akka/actor/ActorKilledException
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/ActorKilledException
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
/*     */     //   #137	-> 0
/*     */     //   #63	-> 14
/*     */     //   #137	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/actor/ActorKilledException;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ActorKilledException(String message) {
/* 137 */     super(message);
/* 137 */     NoStackTrace.class.$init$(this);
/* 137 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorKilledException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */